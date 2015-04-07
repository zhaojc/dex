package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import oracle.jdbc.OracleTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Partage;
import com.lotoquebec.cardex.business.vo.PartageVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.user.CardexUser;

/**
 * Liste des appels � la base de donn�es pour diff�rents acc�s aux dossiers. Les
 * jeux sont li�s aux dossiers.
 * Impl�mente l'interface JeuDAO.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/03/13 17:49:34 $
 * @see com.lotoquebec.cardex.integration.JeuDAO
 */
public class PartageDAO {

	private final Logger      log =
        LoggerFactory.getLogger(PartageDAO.class);

    /**
     * Mise � jour du partage associ� � un dossier, appel�e
     * par update afin de faire une action "clear" et "insert".
     * Selon le param�tre "action" il peut s'agir d'une insertion ("I")
     * ou d'un nettoyage ("C").
     * Proc�dure appel�e : CARDEX_LIEN.SP_E_LPD_PARTAGE_DOSSIER
     * Date de cr�ation : (2009-11-04)
     * @author guerinf
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param partage Partage : Intervenants saisis � l'�cran.
     * @param action String : "I" ou "U"
     * @param genreFichier String : Code � deux lettres de la table qui lie des
     * jeux � un Dossier (DO).  Pour l'instant, seuls les dossiers poss�dent des
     * jeux.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    private void editPartage(CardexAuthenticationSubject subject, Partage partage, String action) throws DAOException {
        Connection connection = DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		CardexUser user = (CardexUser)subject.getUser();
        try {
            if ( action.equalsIgnoreCase("C") ) {
                callableStatement = connection.prepareCall("begin CARDEX_LIEN.SP_E_LPD_PARTAGE_DOSSIER (?,?,?,?,?,?,?); end;");
                callableStatement.setString(1, action);
                callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
                callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
                OracleDAOUtils.setLong(callableStatement,4, partage.getLien());
                callableStatement.setString(5, "");
                OracleDAOUtils.setLong(callableStatement,6, partage.getLienSite());
                callableStatement.setString(7, partage.getGenrePartage());
                callableStatement.execute();
            }
            else if ( action.equalsIgnoreCase("I") ) {
                Collection intervenantsChoisis = partage.getIntervenantsChoisis();
                Iterator it = intervenantsChoisis.iterator();
                String trouve = "false";
                while (it.hasNext()){
                    String intervenant = (String)it.next();
                    if (intervenant.equals(user.getCode())){
                    	trouve = "true";
                    }
                    callableStatement = connection.prepareCall("begin CARDEX_LIEN.SP_E_LPD_PARTAGE_DOSSIER (?,?,?,?,?,?,?); end;");
                    callableStatement.setString(1, action);
                    callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
                    callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
                    OracleDAOUtils.setLong(callableStatement,4, partage.getLien());
                    callableStatement.setString(5, intervenant);
                    OracleDAOUtils.setLong(callableStatement,6, partage.getLienSite());
                    callableStatement.setString(7, partage.getGenrePartage());
                    callableStatement.execute();
                }
                //Si la liste contient des valeurs, on ajoute le nom de l'intervenant qui cr�e le partage afin qu'il puisse lui aussi
                //acc�der � ces dossiers.
                it = intervenantsChoisis.iterator(); 
                if ((it.hasNext()) && (trouve.equals("false"))){
                	callableStatement = connection.prepareCall("begin CARDEX_LIEN.SP_E_LPD_PARTAGE_DOSSIER (?,?,?,?,?,?,?); end;");
                    callableStatement.setString(1, action);
                    callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
                    callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
                    OracleDAOUtils.setLong(callableStatement,4, partage.getLien());
                    callableStatement.setString(5, user.getCode()); //L'intervenant qui cr�e le partage.
                    OracleDAOUtils.setLong(callableStatement,6, partage.getLienSite());
                    callableStatement.setString(7, partage.getGenrePartage());
                    callableStatement.execute();
                }
            }else {
                log.debug("Le code d'action '" + action + "', est invalide pour la m�thode editPartage!");
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
     * Met � jour du partage de dossier.
     * Date de cr�ation : (2009-11-04)
     * @author guerinf
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param partage Partage : Intervenants saisis � l'�cran.
     * @param genreFichier String : Code � deux lettres de la table qui lie un
     * jeu � un Dossier (DO).  Pour l'instant, seuls les sujets poss�dent des
     * jeux.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void update(CardexAuthenticationSubject subject, Partage partage) throws DAOException {
        editPartage(subject, partage, "C");
        editPartage(subject, partage, "I");
    }

    /**
     * Lecture des intervenants associ�s � un dossier.
     * Proc�dure appel�e : SP_L_LPD_PARTAGE_DOSSIER
     * Date de cr�ation : (2009-11-04)
     * @author guerinf
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param cle long : Cl� de r�f�rence du sujet.
     * @param site long : Site de r�f�rence du sujet.
     * @param genreFichier String : ("DO").
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Partage : Liste des intervenants associ�s.
     */
    public Collection findLiensPartage(CardexAuthenticationSubject subject, long cle, long site) throws DAOException {
      log.debug("findLiensPartage()");
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
	
      try {
         callableStatement =
            connection.prepareCall("begin CARDEX_LIRE_LIEN.SP_L_LPD_PARTAGE_DOSSIER (?,?,?); end;");
         callableStatement.setLong(1,cle);
         callableStatement.setLong(2,site);
         callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(3);
         ArrayList results = new ArrayList();
		 while (resultSet.next()){
            	  PartageVO linkedPartage = new PartageVO();
            	  linkedPartage.setCle(resultSet.getLong("L_LPD_CLE"));
            	  linkedPartage.setSite(resultSet.getLong("L_SI_CLE"));
            	  linkedPartage.setLien(resultSet.getLong("L_LPD_REFERENCE"));
            	  linkedPartage.setLienSite(resultSet.getLong("L_LPD_REF_SITE"));
            	  linkedPartage.setProfil(resultSet.getString("V_TR_DESCRIPTION"));
            	  linkedPartage.setSiteIntervenant(resultSet.getLong("SITE"));
                  linkedPartage.setIntervenant(resultSet.getString("V_LPD_NAME"));
                  linkedPartage.setCreateur(resultSet.getString("V_LPD_CREE_PAR"));
		          linkedPartage.setGenrePartage(resultSet.getString("C_LPD_GENRE"));
                  log.debug("   [Intervenant ='" + resultSet.getString("V_LPD_NAME")+"', site='" + linkedPartage.getLienSite() + "']");
                  results.add(linkedPartage);
         }//while
           return results;
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
     * Lecture des intervenants lors de l'ouverture de l'�cran de gestion du partage.
     * Proc�dure appel�e : CARDEX_LIRE_LIEN.SP_L_LPD_PARTAGE_DOSSIER2
     * Date de cr�ation : (2009-11-04)
     * @author guerinf
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param cle long : Cl� de r�f�rence du sujet.
     * @param site long : Site de r�f�rence du sujet.
     * @param genreFichier String : ("DO").
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Inscription : Inscription comportant une liste des sites admis.
     */
    public Partage findPartage(CardexAuthenticationSubject subject, Dossier dossier) throws DAOException {
      log.debug("findPartage()");
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      try {
         callableStatement =
            connection.prepareCall("begin CARDEX_LIRE_LIEN.Sp_L_LPD_PARTAGE_DOSSIER2 (?,?,?); end;");
         callableStatement.setLong(1,dossier.getCle());
         callableStatement.setLong(2,dossier.getSite());
         callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(3);
         PartageVO linkedPartage = null;
         while (resultSet.next()){
        	 if ( linkedPartage == null ) {
        		  linkedPartage = new PartageVO();
		       	  linkedPartage.setCle(resultSet.getLong("L_LPD_CLE"));
		    	  linkedPartage.setSite(resultSet.getLong("L_SI_CLE"));
		    	  linkedPartage.setLien(resultSet.getLong("L_LPD_REFERENCE"));
		    	  linkedPartage.setLienSite(resultSet.getLong("L_LPD_REF_SITE"));
		    	  linkedPartage.setProfil(resultSet.getString("V_TR_DESCRIPTION"));
		    	  linkedPartage.setSiteIntervenant(resultSet.getLong("SITE"));
		          linkedPartage.setCreateur(resultSet.getString("V_LPD_CREE_PAR"));
		          linkedPartage.setGenrePartage(resultSet.getString("C_LPD_GENRE"));
        	 }
             log.debug("   [Intervenant ='" + resultSet.getString("V_LPD_NAME")+"', site='" + linkedPartage.getLienSite() + "']");
             linkedPartage.addIntervenant(resultSet.getString("V_LPD_NAME")); // Intervenant
         }//while
         if (linkedPartage == null){
             return new PartageVO();
            }else{
              return linkedPartage;
            }

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
     * Lecture du partage.
     * Proc�dure appel�e : CARDEX_LIRE_LIEN.SP_L_LPD_PARTAGE_DOSSIER
     * Date de cr�ation : (2009-11-04)
     * @author guerinf
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param cle long : Cl� de r�f�rence du sujet.
     * @param site long : Site de r�f�rence du sujet.
     * @param genreFichier String : ("DO").
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Inscription : Inscription comportant une liste des sites admis.
     */
    public Partage ouvrirPartage(CardexAuthenticationSubject subject, Dossier dossier) throws DAOException {
      log.debug("ouvrirPartage()");
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      try {
         callableStatement =
            connection.prepareCall("begin CARDEX_LIRE_LIEN.Sp_L_LPD_PARTAGE_DOSSIER(?,?,?); end;");
         callableStatement.setLong(1,dossier.getCle());
         callableStatement.setLong(2,dossier.getSite());
         callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(3);
         PartageVO linkedPartage = null;
         while (resultSet.next()){
        	 if ( linkedPartage == null ) {
        		  linkedPartage = new PartageVO();
		       	  linkedPartage.setCle(resultSet.getLong("L_LPD_CLE"));
		    	  linkedPartage.setSite(resultSet.getLong("L_SI_CLE"));
		    	  linkedPartage.setLien(resultSet.getLong("L_LPD_REFERENCE"));
		    	  linkedPartage.setLienSite(resultSet.getLong("L_LPD_REF_SITE"));
		    	  linkedPartage.setProfil(resultSet.getString("V_TR_DESCRIPTION"));
		    	  linkedPartage.setSiteIntervenant(resultSet.getLong("SITE"));
		          linkedPartage.setCreateur(resultSet.getString("V_LPD_CREE_PAR"));
		          linkedPartage.setGenrePartage(resultSet.getString("C_LPD_GENRE"));
        	 }
             log.debug("   [Intervenant ='" + resultSet.getString("V_LPD_NAME")+"', site='" + linkedPartage.getLienSite() + "']");
             linkedPartage.addIntervenant(resultSet.getString("V_LPD_NAME")); // Intervenant
         }//while
         if (linkedPartage == null){
             return new PartageVO();
            }else{
              return linkedPartage;
            }

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

}