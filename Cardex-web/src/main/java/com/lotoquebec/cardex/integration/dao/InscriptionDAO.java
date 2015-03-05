package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.business.Inscription;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.InscriptionVO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * Liste des appels à la base de données pour différents accès aux dossiers.
 * L'inscription sont liés aux dossiers.  Implémente l'interface InscriptionDAO.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.3 $, $Date: 2002/03/13 17:49:34 $
 * @see com.lotoquebec.cardex.integration.InscriptionDAO
 */
public class InscriptionDAO {

	private final Logger      log =
        (Logger)LoggerCardex.getLogger(InscriptionDAO.class);

    /**
     * Mise à jour d'une nouvelle inscription associé à un dossier, appelée
     * par update afin de faire une action "clear" et "insert".
     * Selon le paramètre "action" il peut s'agir d'une insertion ("I")
     * ou d'un nettoyage ("C").
     * Procédure appelée : CARDEX_LIEN.SP_E_IS_INSCRIPTION
     * Date de création : (2002-03-04)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param inscription Inscription : Inscription saisie à l'écran.
     * @param action String : "I" ou "C"
     * @param genreFichier String : Code à deux lettres de la table qui lie une
     * inscription à un Dossier (DO).  Pour l'instant, seuls les dossiers
     * possèdent une inscription.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     */
    private void editInscription(CardexAuthenticationSubject subject, Inscription inscription, String action, String genreFichier) throws DAOException {
        Connection connection = DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
        try {
            Collection sitesChoisis = inscription.getSitesChoisis();
            Iterator it = sitesChoisis.iterator();
            callableStatement = connection.prepareCall("begin CARDEX_LIEN.SP_E_IS_INSCRIPTION (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
            callableStatement.setString(1, action);
            callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
            callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
            OracleDAOUtils.setLong(callableStatement,2, inscription.getCle());
            OracleDAOUtils.setLong(callableStatement,3, inscription.getSite());
            OracleDAOUtils.setLong(callableStatement,4, inscription.getLien());
            callableStatement.setString(5, inscription.getDuree());
            callableStatement.setTimestamp(6,(Timestamp)(inscription.getDateDebut()));
            callableStatement.setTimestamp(7,(Timestamp)(inscription.getDateFin()));
            OracleDAOUtils.setLong(callableStatement, 8, inscription.getPeriode());
            OracleDAOUtils.setLong(callableStatement, 9, 0);
            OracleDAOUtils.setLong(callableStatement, 10, inscription.getStatut());
            callableStatement.setString(11, genreFichier);
            OracleDAOUtils.setLong(callableStatement, 12, inscription.getLienSite());
            callableStatement.setString(13, OracleDAOUtils.convertirBooleanAString( inscription.isAideInitiale() ) );
            callableStatement.setString(14, OracleDAOUtils.convertirBooleanAString( inscription.isAideImmediate() ) );
            callableStatement.setTimestamp(15,(Timestamp)(inscription.getDateCourrielAide()));
            callableStatement.setTimestamp(16,(Timestamp)(inscription.getDateCourrielSuivi()));
            callableStatement.setString(17, inscription.getIntervenantRencontreFinale());
            callableStatement.setString(18, inscription.getIntervenantRencontreInitiale());
            callableStatement.setTimestamp(19,(Timestamp)(inscription.getDateRencontreFinale()));
            callableStatement.setTimestamp(20,(Timestamp)(inscription.getDateRencontreInitiale()));
            callableStatement.setString(21, OracleDAOUtils.convertirBooleanAString(inscription.isTousSitesApplicables()));

            callableStatement.execute();
           if ( action.equalsIgnoreCase("I") ) {
                long cle  = callableStatement.getLong(2);
                long site = callableStatement.getLong(3);
                callableStatement = connection.prepareCall("begin CARDEX_LIEN.SP_E_SIS_SITE_INSCRIPTION (?,?,?,?); end;");
                while (it.hasNext()) {
                    callableStatement.setString(1, action);
                    callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
                    callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
                    callableStatement.registerOutParameter(4, java.sql.Types.DECIMAL);
                    callableStatement.setString(1,"I");
                    OracleDAOUtils.setLong(callableStatement,2, cle);
                    OracleDAOUtils.setLong(callableStatement,3, site);
                    OracleDAOUtils.setLong(callableStatement,4, Long.parseLong((String)it.next()));
                    callableStatement.execute();
                }
            }
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
        }
    }

    /**
     * Crée l'inscription.
     * Date de création : (2002-03-04)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param inscription Inscription : Inscription saisies à l'écran.
     * @param genreFichier String : Code à deux lettres de la table qui lie un
     * jeu à un Dossier (DO).  Pour l'instant, seuls les sujets possèdent des
     * jeux.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     */
    public void insert(CardexAuthenticationSubject subject, Inscription inscription, String genreFichier) throws DAOException {
        editInscription(subject, inscription, "I", genreFichier);
    }

    /**
     * Détruit l'inscription.
     * Date de création : (2002-03-04)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param inscription Inscription : Inscription saisies à l'écran.
     * @param genreFichier String : Code à deux lettres de la table qui lie un
     * jeu à un Dossier (DO).  Pour l'instant, seuls les sujets possèdent des
     * jeux.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     */
    public void delete(CardexAuthenticationSubject subject, Inscription inscription, String genreFichier) throws DAOException {
        editInscription(subject, inscription, "D", genreFichier);
    }

    /**
     * Mise à jour de l'inscription.
     * Date de création : (2002-03-04)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param inscription Inscription : Inscription saisies à l'écran.
     * @param genreFichier String : Code à deux lettres de la table qui lie un
     * jeu à un Dossier (DO).  Pour l'instant, seuls les sujets possèdent des
     * jeux.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     */
    public void updateInscription(CardexAuthenticationSubject subject, Inscription inscription) throws DAOException {
        editInscription(subject, inscription, "U", GlobalConstants.GenreFichier.DOSSIER);
    }

    /**
     * Lecture des inscriptions associées à un dossier.
     * Procédure appelée : CARDEX_WEB_LIRE_DOC_TRI.SPW_L_IS_INSCRIPTION
     * Date de création : (2002-03-04)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param cle long : Clé de référence du sujet.
     * @param site long : Site de référence du sujet.
     * @param genreFichier String : ("DO").
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Inscription : Inscription comportant une liste des sites admis.
     */
    public Collection findLiensInscription(CardexAuthenticationSubject subject, long cle, long site, String genreFichier) throws DAOException {
      log.fine("findLiensInscription()");
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      ArrayList list = new ArrayList();
      try {
         callableStatement =
            connection.prepareCall("begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L_IS_INSCRIPTION (?,?,?,?); end;");
         callableStatement.setLong(1,cle);
         callableStatement.setLong(2,site);
         callableStatement.setString(3,genreFichier);
         callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(4);
         InscriptionVO linkedInscription = null;
         long previousCle = 0;
         while (resultSet.next()){
              if (previousCle != resultSet.getLong("L_IS_CLE")) {
                linkedInscription = new InscriptionVO();
                linkedInscription.setCle(resultSet.getLong("L_IS_CLE"));
                linkedInscription.setSite(resultSet.getLong("L_SI_CLE"));
                linkedInscription.setLien(resultSet.getLong("L_IS_REFERENCE"));
                linkedInscription.setDuree(resultSet.getString("V_IS_DUREE"));
                linkedInscription.setDateDebut(resultSet.getTimestamp("D_IS_DATE_DEBUT"));
                linkedInscription.setDateInscription(resultSet.getTimestamp("D_IS_DATE_CREATION"));
                linkedInscription.setDateFin(resultSet.getTimestamp("D_IS_DATE_FIN"));
                linkedInscription.setPeriode(resultSet.getLong("I_IS_PERIODE"));
                linkedInscription.setStatut(resultSet.getLong("I_ST_CLE"));
                linkedInscription.setLienSite(resultSet.getLong("L_IS_REF_SITE"));
                linkedInscription.setAideInitiale( OracleDAOUtils.convertirStringABoolean( resultSet.getString("B_IS_AIDE_INITIALE") ));
                linkedInscription.setAideImmediate( OracleDAOUtils.convertirStringABoolean( resultSet.getString("B_IS_AIDE_IMMEDIATE") ));
                linkedInscription.setCreateur(resultSet.getString("V_IS_CREE_PAR"));
                linkedInscription.setIntervenantRencontreFinale(resultSet.getString("V_IS_INTERVENANT_FINAL"));
                linkedInscription.setIntervenantRencontreInitiale(resultSet.getString("V_IS_INTERVENANT_INITIAL"));
                linkedInscription.setDateCourrielAide(resultSet.getTimestamp("D_IS_COURRIEL_AIDE"));
                linkedInscription.setDateCourrielSuivi(resultSet.getTimestamp("D_IS_COURRIEL_SUIVI"));
                linkedInscription.setDateRencontreFinale(resultSet.getTimestamp("D_IS_RENCONTRE_FINALE"));
                linkedInscription.setDateRencontreInitiale(resultSet.getTimestamp("D_IS_RENCONTRE_INITIALE"));
                linkedInscription.setTousSitesApplicables(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_IS_TOUS_SITE_APPLICABLE")));
                log.fine("   Retrieving [Inscription cle='" + linkedInscription.getCle()+"', site='" + linkedInscription.getSite() + "']");
                list.add(linkedInscription);
              }
              log.fine("      Adding site '" + resultSet.getLong("L_IS_SITE") + "'");
              linkedInscription.addSite( Long.toString(resultSet.getLong("L_IS_SITE")) ); // I_SI_SITE
              previousCle = resultSet.getLong("L_IS_CLE");
         }//while
         return list;
      }catch (SQLException se) {
          throw new DAOException(se);
      } finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
      } //finally
   }

    /**
     * Lecture d'une inscription.
     * Procédure appelée : CARDEX_WEB_LIRE_DOC_TRI.SPW_L2_IS_INSCRIPTION
     * Date de création : (2002-03-04)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param cle long : Clé de référence du sujet.
     * @param site long : Site de référence du sujet.
     * @param genreFichier String : ("DO").
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Inscription : Inscription comportant une liste des sites admis.
     */
    public Inscription findInscription(CardexAuthenticationSubject subject, Inscription inscription) throws DAOException {
      log.fine("findInscription()");
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      try {
         callableStatement =
            connection.prepareCall("begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L2_IS_INSCRIPTION (?,?,?); end;");
         callableStatement.setLong(1,inscription.getCle());
         callableStatement.setLong(2,inscription.getSite());
         callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(3);
         InscriptionVO linkedInscription = null;
         long previousCle = 0;
         while (resultSet.next()){
              if (previousCle != resultSet.getLong("L_IS_CLE")) {
                linkedInscription = new InscriptionVO();
                linkedInscription.setCle(resultSet.getLong("L_IS_CLE"));
                linkedInscription.setSite(resultSet.getLong("L_SI_CLE"));
                linkedInscription.setLien(resultSet.getLong("L_IS_REFERENCE"));
                linkedInscription.setDuree(resultSet.getString("V_IS_DUREE"));
                linkedInscription.setDateDebut(resultSet.getTimestamp("D_IS_DATE_DEBUT"));
                linkedInscription.setDateInscription(resultSet.getTimestamp("D_IS_DATE_CREATION"));
                linkedInscription.setDateFin(resultSet.getTimestamp("D_IS_DATE_FIN"));
                linkedInscription.setPeriode(resultSet.getLong("I_IS_PERIODE"));
                linkedInscription.setStatut(resultSet.getLong("I_ST_CLE"));
                linkedInscription.setLienSite(resultSet.getLong("L_IS_REF_SITE"));
                linkedInscription.setAideInitiale( OracleDAOUtils.convertirStringABoolean( resultSet.getString("B_IS_AIDE_INITIALE") ));
                linkedInscription.setAideImmediate( OracleDAOUtils.convertirStringABoolean( resultSet.getString("B_IS_AIDE_IMMEDIATE") ));
                linkedInscription.setCreateur(resultSet.getString("V_IS_CREE_PAR"));
                linkedInscription.setIntervenantRencontreFinale(resultSet.getString("V_IS_INTERVENANT_FINAL"));
                linkedInscription.setIntervenantRencontreInitiale(resultSet.getString("V_IS_INTERVENANT_INITIAL"));
                linkedInscription.setDateCourrielAide(resultSet.getTimestamp("D_IS_COURRIEL_AIDE"));
                linkedInscription.setDateCourrielSuivi(resultSet.getTimestamp("D_IS_COURRIEL_SUIVI"));
                linkedInscription.setDateRencontreFinale(resultSet.getTimestamp("D_IS_RENCONTRE_FINALE"));
                linkedInscription.setDateRencontreInitiale(resultSet.getTimestamp("D_IS_RENCONTRE_INITIALE"));
                linkedInscription.setTousSitesApplicables(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_IS_TOUS_SITE_APPLICABLE")));
                linkedInscription.setEntite(resultSet.getLong("I_EN_CLE"));
                log.fine("   Retrieving [Inscription cle='" + linkedInscription.getCle()+"', site='" + linkedInscription.getSite() + "']");
              }
              log.fine("      Adding site '" + resultSet.getLong("L_IS_SITE") + "'");
              linkedInscription.addSite( Long.toString(resultSet.getLong("L_IS_SITE")) ); // I_SI_SITE
              previousCle = resultSet.getLong("L_IS_CLE");
         }//while
         return linkedInscription;
      }catch (SQLException se) {
          throw new DAOException(se);
      } finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
	        }
			if(callableStatement != null) {
				try {
						callableStatement.close();
			    } catch (java.sql.SQLException e) {
                    throw new DAOException(e);
		        }
			}
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
 		    }
      } //finally
   }

    /*
     * (non-Javadoc)
     * @see com.lotoquebec.cardex.integration.dao.InscriptionDAO#enregisterDateSuiviInscription(java.sql.Connection, com.lotoquebec.cardex.business.vo.DossierVO)
     */
    public void enregisterDateSuiviInscription(Connection connection, final DossierVO dossierVO) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			OracleDAOUtils.setLong(callableStatement, 1, dossierVO.getCle());
    			OracleDAOUtils.setLong(callableStatement, 2, dossierVO.getSite());
			}
    	};
    	
    	storeProcTemplate.prepareCall("CARDEX_LIEN.SP_E_IS_INSCRIPTION_DATE_SUIVI", 2, rch);
    	
    	storeProcTemplate.query( false );		
	}
    
    /*
     * (non-Javadoc)
     * @see com.lotoquebec.cardex.integration.dao.InscriptionDAO#enregisterDateAideInitiale(java.sql.Connection, com.lotoquebec.cardex.business.vo.DossierVO)
     */
    public void enregisterDateAideInitiale(Connection connection, final DossierVO dossierVO) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			OracleDAOUtils.setLong(callableStatement, 1, dossierVO.getCle());
    			OracleDAOUtils.setLong(callableStatement, 2, dossierVO.getSite());
			}
    	};
    	
    	storeProcTemplate.prepareCall("CARDEX_LIEN.SP_E_IS_INSCRIPTION_DATE_AIDE", 2, rch);
    	
    	storeProcTemplate.query( false );		
	}
    
}