package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

import com.lotoQuebec.correcteurAdresse.AdresseSortie;
import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Caracteristiques;
import com.lotoquebec.cardex.business.CriteresRechercheAdresses;
import com.lotoquebec.cardex.business.CriteresRechercheSociete;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.delegate.AdresseBusinessDelegate;
import com.lotoquebec.cardex.business.vo.AdresseVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.integration.dao.sql.recherche.AdresseSocieteCompletSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.AdresseSocieteCountSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.AdresseSocieteSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.SocieteCompletSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.SocieteCountSQL;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.UnEnregistrementPresent;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Liste des appels à la base de données pour tout ce qui concerne
 * les sociétés.  Implémente l'interface SocieteDAO.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.18 $, $Date: 2002/04/25 15:42:36 $
 * @see com.lotoquebec.cardex.integration.SocieteDAO
 */

public class SocieteDAO {

	private final Logger      log =
        (Logger)LoggerCardex.getLogger(SocieteDAO.class);
	private Logger courantLog = (Logger)LoggerCardex.getLogger(SujetDAO.class);
/**
 * Écriture d'une société, appelé par la méthode "insert" ou "update".
 * Selon le paramètre "action" il peut s'agir d'une insertion ("I")
 * ou d'une mise à jour ("U").
 * Procédure appelée : CARDEX_DOC.SP_E_SO_SOCIETE
 * Date de création : (2002-02-11)
 * @author François Guérin
 * @param subject CardexAuthenticationSubject : données nominatives sur l'utilisateur
 * @param societe Societe : société saisi à l'écran
 * @param action  java.lang.String : U ou I
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Societe
 */
   private Societe editSociete(CardexAuthenticationSubject subject, Societe societe, String action) throws DAOException {
      Connection connection = DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
      try {
          callableStatement =
              connection.prepareCall("begin CARDEX_DOC.SP_E_SO_SOCIETE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
              callableStatement.setString(1,action);
              callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
              callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
              OracleDAOUtils.setLong(callableStatement,2, societe.getCle());
              OracleDAOUtils.setLong(callableStatement,3, societe.getSite());
              OracleDAOUtils.setLong(callableStatement,4, societe.getStatut());
              OracleDAOUtils.setLong(callableStatement,5, societe.getLangue());
              callableStatement.setString(6, societe.getRaisonEtre());
              callableStatement.setString(7, societe.getNom().trim());
              OracleDAOUtils.setLong(callableStatement,8, societe.getClasse());
              callableStatement.setTimestamp(9, (Timestamp)(societe.getDateDeFondation()));
              OracleDAOUtils.setLong(callableStatement,10, societe.getConfidentialite());
              callableStatement.setString(11, societe.getMotPasse());
              callableStatement.setString(12, societe.getReferenceNom().trim());
              callableStatement.setString(13, societe.getReferencePrenom().trim());
              callableStatement.setString(14, societe.getReference1().trim().toUpperCase());
              callableStatement.setString(15, societe.getReference2().trim().toUpperCase());
              callableStatement.setString(16, societe.getNumeroFiche().trim().toUpperCase());
              OracleDAOUtils.setLong(callableStatement,17, societe.getSeverite());
              callableStatement.setString(18, societe.getCentreRegional());
              callableStatement.setString(19, societe.getDistrict().trim().toUpperCase());
              callableStatement.setString(20, societe.getCodeCompte().trim().toUpperCase());
              callableStatement.setString(21, OracleDAOUtils.convertirBooleanAString(societe.isActif()));
              callableStatement.setTimestamp(22, (Timestamp)(societe.getDateInactif()));
              callableStatement.setString(23, OracleDAOUtils.convertirBooleanAString(societe.isIndicateurRdd()));
              callableStatement.setString(24, societe.getCommentaire());
              callableStatement.setString(25, societe.getRaisonDesactivation());
              OracleDAOUtils.setLong(callableStatement,26, societe.getSeveriteCasino());
              OracleDAOUtils.setLong(callableStatement,27, societe.getEchantillonnage());
              callableStatement.execute();
              Societe newSociete = new SocieteVO();
              newSociete.setCle(callableStatement.getLong(2));
              newSociete.setSite(callableStatement.getLong(3));
              return newSociete;
      } catch (SQLException se) {
          throw new DAOException(se);
      } finally {
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
    * Écriture d'une société qui est un nouveau détaillant de RDD (Réseau des détaillants).
    * Procédure appelée : CARDEX_DOC.SP_E_SO_DETAILLANTS
    * Date de création : (2012-09-10)
    * @author François Guérin
    * @param subject CardexAuthenticationSubject : données nominatives sur l'utilisateur
    * @param societe Societe : société saisi à l'écran
    * @param action  java.lang.String : U ou I
    * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
    * rupture de connexion avec la base de données, ou que la table demandée est
    * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
    * "stored procedure".
    * @return Societe
    */
      private void ajoutDetaillant(CardexAuthenticationSubject subject, Societe societe) throws DAOException {
         Connection connection = DAOConnection.getInstance().getConnection(subject);
   		CallableStatement callableStatement = null;
         try {
             callableStatement =
                 connection.prepareCall("begin CARDEX_DOC.SP_E_SO_DETAILLANTS (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
                 callableStatement.registerOutParameter(1, java.sql.Types.DECIMAL);
                 callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
                 OracleDAOUtils.setLong(callableStatement,1, societe.getCle());
                 OracleDAOUtils.setLong(callableStatement,2, societe.getSite());
                 OracleDAOUtils.setLong(callableStatement,3, societe.getStatut());
                 OracleDAOUtils.setLong(callableStatement,4, societe.getLangue());
                 callableStatement.setString(5, societe.getRaisonEtre());
                 callableStatement.setString(6, societe.getNom().trim());
                 OracleDAOUtils.setLong(callableStatement,7, societe.getClasse());
                 callableStatement.setTimestamp(8, (Timestamp)(societe.getDateDeFondation()));
                 OracleDAOUtils.setLong(callableStatement,9, societe.getConfidentialite());
                 callableStatement.setString(10, societe.getMotPasse());
                 callableStatement.setString(11, societe.getReferenceNom().trim());
                 callableStatement.setString(12, societe.getReferencePrenom().trim());
                 callableStatement.setString(13, societe.getReference1().trim().toUpperCase());
                 callableStatement.setString(14, societe.getReference2().trim().toUpperCase());
                 callableStatement.setString(15, societe.getNumeroFiche().trim().toUpperCase());
                 callableStatement.setNull(16, OracleTypes.NULL);
                 callableStatement.setString(17, societe.getCentreRegional());
                 callableStatement.setString(18, societe.getDistrict().trim().toUpperCase());
                 callableStatement.setString(19, societe.getCodeCompte().trim().toUpperCase());
                 callableStatement.setString(20, OracleDAOUtils.convertirBooleanAString(societe.isActif()));
                 callableStatement.setTimestamp(21, (Timestamp)(societe.getDateInactif()));
                 callableStatement.setString(22, OracleDAOUtils.convertirBooleanAString(societe.isIndicateurRdd()));
                 callableStatement.setString(23, societe.getCommentaire());
                 callableStatement.setString(24, societe.getRaisonDesactivation());
                 OracleDAOUtils.setLong(callableStatement,25, societe.getSeveriteCasino());
                 callableStatement.execute();
         } catch (SQLException se) {
             throw new DAOException(se);
         } finally {
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
 * Appel de la méthode editSociete pour la création d'un société.
 * Date de création : (2002-01-28)
 * @author François Guérin
 * @param subject CardexAuthenticationSubject : données nominatives sur l'utilisateur
 * @param societe Societe : société saisi à l'écran
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Societe
 */
    public Societe insert(CardexAuthenticationSubject subject, Societe societe) throws DAOException {
      return editSociete(subject,societe,"I");
    }

/**
 * Appel de la méthode editSociete pour la mise à jour d'un societe.
 * Date de création : (2002-01-28)
 * @author François Guérin
 * @param subject CardexAuthenticationSubject : données nominatives sur l'utilisateur
 * @param societe Societe : société saisi à l'écran
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 */
    public void update(CardexAuthenticationSubject subject, Societe societe) throws DAOException {
      editSociete(subject, societe, "U");
    }

/**
 * Recherche des sociétés créés dans les deux derniers jours lors de l'affichage
 * de l'écran de recherche de sociétés
 * Procédure appelée : CARDEX_WEB_LIRE_DOC_TRI.SPW_L_SO_SOCIETE
 * Date de création : (2002-02-12)
 * @author François Guérin
 * @param subject  CardexAuthenticationSubject : données nominatives sur l'utilisateur
 * @param criteria CriteresRechercheSociete : critères de recherche
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return ValueListIterator : une liste de sociétés retournés par la recherche.
 */

    public List<Societe> selectDefault(CardexAuthenticationSubject subject,CriteresRechercheSociete criteria) throws DAOException{
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
	  List<Societe> results = new ArrayList<Societe>();
      try {
         callableStatement = connection.prepareCall("begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L_SO_SOCIETE (?); end;");
         callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(1);
         //Traitement des données retournées
         while(resultSet.next()){
            results.add(traitementResultSet(resultSet));
         }
         return results;
       } catch (SQLException se) {
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
 * Recherche directe d'un dossier par sa clé unique sans écriture d'un audit.
 * Cette procédure est nécessaire en raison du mode web de l'application.  Dans ce 
 * mode, chaque fois qu'on revient au dossier (par exemple, après une nouvelle narration),
 * une relecture du dossier est effectuée, ce qui génère une entrée inutile dans la
 * table AC_ACCES et fausse les données d'historique du dossier.
 * Procédure appelée : CARDEX_WEB_LIRE_DOC_TRI.SPW_L2_SO_SOCIETE
 * Date de création : (2002-02-12)
 * @author François Guérin
 * @param subject  CardexAuthenticationSubject : données nominatives sur l'utilisateur
 * @param criteria Societe : société à rechercher
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Societe : données d'une société trouvée.
 */
    public Societe find(CardexAuthenticationSubject subject, Societe criteria) throws DAOException{
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      Societe societe = new SocieteVO();
      log.fine("société cherchée: " + criteria.toString());
      try {
         callableStatement = connection.prepareCall("begin Cardex_Web_Lire_Doc_Tri.SPW_L2_SO_SOCIETE (?,?,?); end;");
         callableStatement.setLong(1,criteria.getCle());
         callableStatement.setLong(2,criteria.getSite());
         callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(3);
         //Traitement d'une société retournée (s'il y a lieu)
         if (resultSet.next()){
            log.fine("société trouvée");
            societe = traitementResultSet(resultSet);
         }
         else {
            log.fine("pas de résultats!");
         }
         return societe;
       } catch (SQLException se) {
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
	 * Recherche directe d'une société par sa clé unique dans la table des audits de changements.
	 * Sert à retrouver les informations en date de la liaison d'une société à un dossier.
	 * Si des informations sont retrouvées, elles sont imprimées sur le dossier au lieu des informations actuelles
	 * Procédure appelée : CARDEX_AUDIT.SP_L_FIND_AUDIT_SOCIETE
	 * Date de création : (2012-01-07)
	 * @author François Guérin
     * @param subject  CardexAuthenticationSubject : données nominatives sur l'utilisateur
     * @param criteria Societe : société à rechercher
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée est
     * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
     * "stored procedure".
     * @return Societe : données d'une société trouvée.
     */
        public Societe findAudit(CardexAuthenticationSubject subject, Societe criteria) throws DAOException{
          Connection connection = DAOConnection.getInstance().getConnection(subject);
    	  CallableStatement callableStatement = null;
    	  ResultSet resultSet = null;
          Societe societe = new SocieteVO();
          log.fine("société cherchée: " + criteria.toString());
          try {
             callableStatement = connection.prepareCall("begin CARDEX_AUDIT.SP_L_FIND_AUDIT_SOCIETE (?,?,?,?); end;");
             callableStatement.setLong(1,criteria.getCle());
             callableStatement.setLong(2,criteria.getSite());
             if(criteria.getLienDateCreation() == null){
             	callableStatement.setNull(3, java.sql.Types.TIMESTAMP);
             }else{
             	callableStatement.setTimestamp(3,criteria.getLienDateCreation());
             }
             callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
             callableStatement.execute();
             resultSet = (ResultSet)callableStatement.getObject(4);
             //Traitement d'une société retournée (s'il y a lieu)
             if (resultSet.next()){
                log.fine("société trouvée");
                societe = traitementResultSetFindAudit(resultSet);
             }
             else {
                log.fine("pas de résultats!");
             }
             return societe;
           } catch (SQLException se) {
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
 * Recherche directe d'une société par sa clé unique avec écriture d'un audit.
 * Procédure appelée : CARDEX_LIRE_DOC.SP_L_SO_SOCIETE
 * Date de création : (2002-02-12)
 * @author François Guérin
 * @param subject  CardexAuthenticationSubject : données nominatives sur l'utilisateur
 * @param criteria Societe : société à rechercher
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Societe : données d'une société trouvée.
 */
    public Societe findAcces(CardexAuthenticationSubject subject, Societe criteria) throws DAOException{
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      Societe societe = new SocieteVO();
      log.fine("société cherchée: " + criteria.toString());
      try {
         callableStatement = connection.prepareCall("begin CARDEX_LIRE_DOC.SP_L_SO_SOCIETE (?,?,?); end;");
         callableStatement.setLong(1,criteria.getCle());
         callableStatement.setLong(2,criteria.getSite());
         callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(3);
         //Traitement d'une société retournée (s'il y a lieu)
         if (resultSet.next()){
            log.fine("société trouvée");
            societe = traitementResultSet(resultSet);
         }
         else {
            log.fine("pas de résultats!");
         }
         return societe;
       } catch (SQLException se) {
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
 * Recherche directe d'une société par sa clé unique et protégé par mot de passe.
 * Procédure appelée : CARDEX_LIRE_DOC.SP_L_SO_SOCIETE
 * Date de création : (2002-02-12)
 * @author François Guérin
 * @param subject  CardexAuthenticationSubject : données nominatives sur l'utilisateur
 * @param criteria Societe : société à rechercher
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Societe : données d'une société trouvée.
 */
    public Societe findMotPasse(CardexAuthenticationSubject subject, Societe criteria) throws DAOException{
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      Societe societe = new SocieteVO();
      try {
         callableStatement = connection.prepareCall("begin CARDEX_LIRE_DOC.SP_L_SO_SOCIETE (?,?,?,?); end;");
         callableStatement.setLong(1,criteria.getCle());
         callableStatement.setLong(2,criteria.getSite());
         callableStatement.setString(3,criteria.getMotPasse());
         callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(4);
         //Traitement d'une société retourné (s'il y a lieu)
         if (resultSet.next()){
            societe = traitementResultSet(resultSet);
         }
         return societe;
       } catch (SQLException se) {
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
 * Routine pour traiter les ResultSet retournés par les recherches des sociétés.
 * Date de création : (2002-01-28)
 * @author François Guérin
 * @param resultSet  ResultSet : données retournées par une recherche
 * @throws SQLException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return ArrayList : liste des sociétés traités.
 */
    public RowCallbackHandler constuireRowCallBackHandler(final List<Societe> listDossier){
  	   return new RowCallbackHandler(){
  		   public void processRow(ResultSet resultSet) throws SQLException {
  			   Societe societe = traitementResultSet(resultSet);
               listDossier.add(societe); 					
  		   }
  	   };
     }    
    
    
  private Societe traitementResultSet(ResultSet resultSet) throws SQLException {
              SocieteVO societe = new SocieteVO();
              societe.setCle(resultSet.getLong("L_SO_CLE"));
              societe.setSite(resultSet.getLong("L_SI_CLE"));
              societe.setEntite(resultSet.getLong("I_EN_CLE"));
              societe.setStatut(resultSet.getLong("I_ST_CLE"));
              societe.setLangue(resultSet.getLong("I_LS_CLE"));
              societe.setRaisonEtre(OracleDAOUtils.getString(resultSet,"V_SO_RAISON_SOCIALE"));
              societe.setNom(OracleDAOUtils.getString(resultSet,"V_SO_NOM"));
              societe.setMotPasse(resultSet.getString("V_SO_MOT_PASSE"));
              societe.setClasse(resultSet.getLong("I_CL_CLE"));
              societe.setDateDeFondation(resultSet.getTimestamp("D_SO_DATE_FONDATION"));
              societe.setDateCreation(resultSet.getTimestamp("D_SO_DATE_CREATION"));
              societe.setConfidentialite(resultSet.getLong("I_CC_CLE"));
              societe.setReferenceNom(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_NOM"));
              societe.setReferencePrenom(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_PRENOM"));
              societe.setReference1(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_1"));
              societe.setReference2(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_2"));
              societe.setNumeroFiche(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_3"));
              societe.setSeverite(resultSet.getLong("I_SE_CLE"));
              societe.setSeveriteCasino(resultSet.getLong("I_SE_CLE_CASINO"));
              societe.setCreateur(resultSet.getString("V_SO_CREE_PAR"));
              societe.setCentreRegional(resultSet.getString("L_SO_CENTRE_REGIONAL"));
              societe.setDistrict(OracleDAOUtils.getString(resultSet,"V_SO_DISTRICT"));
              societe.setCodeCompte(OracleDAOUtils.getString(resultSet,"V_SO_CODE_COMPTE"));
    		  societe.setActif(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SO_ACTIF")));
    		  societe.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SO_IND_RDD")));
              societe.setDateInactif(resultSet.getTimestamp("D_SO_DATE_INACTIVATION"));
              societe.setCommentaire(OracleDAOUtils.getString(resultSet,"V_SO_COMMENTAIRE"));
              societe.setRaisonDesactivation(OracleDAOUtils.getString(resultSet,"V_SO_RAISON_INACTIVATION"));
              societe.setCodeCompteDescription(OracleDAOUtils.getString(resultSet,"NOM_COC"));
              societe.setCentreRegionalDescription(OracleDAOUtils.getString(resultSet,"NOM_CENTREREG"));
              societe.setDistrictDescription(OracleDAOUtils.getString(resultSet,"NOM_DISTRICT"));
              societe.setEchantillonnage(resultSet.getLong("I_SO_ECHANTILLONAGE"));
              log.fine("   [SOCIETE id='" + societe.getNumeroFiche() + "' Nom='" + societe.getNom()+"']");
         return societe;
    }

  private Societe traitementResultSetFindAudit(ResultSet resultSet) throws SQLException {
      SocieteVO societe = new SocieteVO();
      societe.setCle(resultSet.getLong("L_SO_CLE"));
      societe.setSite(resultSet.getLong("L_SI_CLE"));
      societe.setEntite(resultSet.getLong("I_EN_CLE"));
      societe.setStatut(resultSet.getLong("I_ST_CLE"));
      societe.setLangue(resultSet.getLong("I_LS_CLE"));
      societe.setRaisonEtre(OracleDAOUtils.getString(resultSet,"V_SO_RAISON_SOCIALE"));
      societe.setNom(OracleDAOUtils.getString(resultSet,"V_SO_NOM"));
      societe.setClasse(resultSet.getLong("I_CL_CLE"));
      societe.setDateDeFondation(resultSet.getTimestamp("D_SO_DATE_FONDATION"));
      societe.setDateCreation(resultSet.getTimestamp("D_SO_DATE_CREATION"));
      societe.setConfidentialite(resultSet.getLong("I_CC_CLE"));
      societe.setReferenceNom(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_NOM"));
      societe.setReferencePrenom(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_PRENOM"));
      societe.setReference1(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_1"));
      societe.setReference2(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_2"));
      societe.setNumeroFiche(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_3"));
      societe.setSeverite(resultSet.getLong("I_SE_CLE"));
      societe.setSeveriteCasino(resultSet.getLong("I_SE_CLE_CASINO"));
      societe.setCreateur(resultSet.getString("V_SO_CREE_PAR"));
      societe.setCentreRegional(resultSet.getString("L_SO_CENTRE_REGIONAL"));
      societe.setDistrict(OracleDAOUtils.getString(resultSet,"V_SO_DISTRICT"));
      societe.setCodeCompte(OracleDAOUtils.getString(resultSet,"V_SO_CODE_COMPTE"));
	  societe.setActif(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SO_ACTIF")));
	  societe.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SO_IND_RDD")));
      societe.setDateInactif(resultSet.getTimestamp("D_SO_DATE_INACTIVATION"));
      societe.setCommentaire(OracleDAOUtils.getString(resultSet,"V_SO_COMMENTAIRE"));
      societe.setRaisonDesactivation(OracleDAOUtils.getString(resultSet,"V_SO_RAISON_INACTIVATION"));
      log.fine("   [SOCIETE id='" + societe.getNumeroFiche() + "' Nom='" + societe.getNom()+"']");
 return societe;
}

  /**
 * Routine pour traiter les ResultSet retournés par les recherches des sociétés.
 * Date de création : (2002-01-28)
 * @author François Guérin
 * @param resultSet  ResultSet : données retournées par une recherche
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return ArrayList : liste des sociétés traités.
 */
  private Societe traitementResultSetLink(ResultSet resultSet) throws DAOException {
       try {
              SocieteVO societe = new SocieteVO();
              societe.setCle(resultSet.getLong("L_SO_CLE"));
              societe.setSite(resultSet.getLong("L_SI_CLE"));
              societe.setStatut(resultSet.getLong("I_ST_CLE"));
              societe.setLangue(resultSet.getLong("I_LS_CLE"));
              societe.setRaisonEtre(resultSet.getString("V_SO_RAISON_SOCIALE"));
              societe.setNom(resultSet.getString("V_SO_NOM"));
              societe.setClasse(resultSet.getLong("I_CL_CLE"));
              societe.setDateDeFondation(resultSet.getTimestamp("D_SO_DATE_FONDATION"));
              societe.setConfidentialite(resultSet.getLong("I_CC_CLE"));
              societe.setMotPasse(resultSet.getString("V_SO_MOT_PASSE"));
              societe.setReferenceNom(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_NOM"));
              societe.setReferencePrenom(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_PRENOM"));
              societe.setReference1(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_1"));
              societe.setReference2(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_2"));
              societe.setNumeroFiche(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_3"));
              societe.setSeverite(resultSet.getLong("I_SE_CLE"));
              societe.setSeveriteCasino(resultSet.getLong("I_SE_CLE_CASINO"));
              societe.setLien(resultSet.getLong("L_LDD_CLE"));
              societe.setLienSite(resultSet.getLong("L_LDD_SI_CLE"));
			  societe.setLienCreateur(resultSet.getString("V_LDD_CREE_PAR"));
              societe.setRole(resultSet.getLong("I_RO_CLE"));
      		  societe.setLienDateCreation(resultSet.getTimestamp("D_LDD_DATE_CREATION"));
      		  societe.setAudit(GlobalConstants.BooleanString.FALSE);
              societe.setCentreRegional(resultSet.getString("L_SO_CENTRE_REGIONAL"));
              societe.setDistrict(OracleDAOUtils.getString(resultSet,"V_SO_DISTRICT"));
              societe.setCodeCompte(OracleDAOUtils.getString(resultSet,"V_SO_CODE_COMPTE"));
    		  societe.setActif(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SO_ACTIF")));
    		  societe.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SO_IND_RDD")));
              societe.setDateInactif(resultSet.getTimestamp("D_SO_DATE_INACTIVATION"));
              societe.setCommentaire(OracleDAOUtils.getString(resultSet,"V_SO_COMMENTAIRE"));
              societe.setRaisonDesactivation(OracleDAOUtils.getString(resultSet,"V_SO_RAISON_INACTIVATION"));
              log.fine("   [SOCIETE id='" + societe.getNumeroFiche() + "' Nom='" + societe.getNom()+"']");
         return societe;
      } catch (SQLException se) {
          throw new DAOException(se);
      }
    }

  /**
   * Routine pour traiter les ResultSet retournés par les recherches des sociétés qui ont
   * été liées à un module et qui sont conservées dans l'audit des liaisons (AUD_LI_LIAISON).
   * Date de création : (2012-01-12)
   * @author François Guérin
   * @param resultSet  ResultSet : données retournées par une recherche
   * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
   * rupture de connexion avec la base de données, ou que la table demandée est
   * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
   * "stored procedure".
   * @return ArrayList : liste des sociétés traités.
   */
    private Societe traitementResultSetLinkAudit(ResultSet resultSet) throws DAOException {
         try {
                SocieteVO societe = new SocieteVO();
                societe.setCle(resultSet.getLong("L_SO_CLE"));
                societe.setSite(resultSet.getLong("L_SI_CLE"));
                societe.setStatut(resultSet.getLong("I_ST_CLE"));
                societe.setLangue(resultSet.getLong("I_LS_CLE"));
                societe.setRaisonEtre(resultSet.getString("V_SO_RAISON_SOCIALE"));
                societe.setNom(resultSet.getString("V_SO_NOM"));
                societe.setClasse(resultSet.getLong("I_CL_CLE"));
                societe.setDateDeFondation(resultSet.getTimestamp("D_SO_DATE_FONDATION"));
                societe.setConfidentialite(resultSet.getLong("I_CC_CLE"));
                societe.setMotPasse(resultSet.getString("V_SO_MOT_PASSE"));
                societe.setReferenceNom(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_NOM"));
                societe.setReferencePrenom(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_PRENOM"));
                societe.setReference1(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_1"));
                societe.setReference2(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_2"));
                societe.setNumeroFiche(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_3"));
                societe.setSeverite(resultSet.getLong("I_SE_CLE"));
                societe.setSeveriteCasino(resultSet.getLong("I_SE_CLE_CASINO"));
                societe.setAudit(GlobalConstants.BooleanString.TRUE); //On indique que la société provient de l'audit des liaisons.
                societe.setCentreRegional(OracleDAOUtils.getString(resultSet,"L_SO_CENTRE_REGIONAL"));
                societe.setDistrict(OracleDAOUtils.getString(resultSet,"V_SO_DISTRICT"));
                societe.setCodeCompte(OracleDAOUtils.getString(resultSet,"V_SO_CODE_COMPTE"));
      		    societe.setActif(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SO_ACTIF")));
      		    societe.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SO_IND_RDD")));
                societe.setDateInactif(resultSet.getTimestamp("D_SO_DATE_INACTIVATION"));
                societe.setCommentaire(OracleDAOUtils.getString(resultSet,"V_SO_COMMENTAIRE"));
                societe.setRaisonDesactivation(OracleDAOUtils.getString(resultSet,"V_SO_RAISON_INACTIVATION"));
                log.fine("   [SOCIETE id='" + societe.getNumeroFiche() + "' Nom='" + societe.getNom()+"']");
           return societe;
        } catch (SQLException se) {
            throw new DAOException(se);
        }
      }

    /**
 * Recherche de sociétés à l'aide de critères de recherche.
 * La procédure appelée autrefois est de type DBMS (SQL dynamique)
 * (SP_L4_so_societe PACKAGE CARDEX_LIRE_DOC).  Avec Java, il ne semble pas possible de récupérer les
 * données retournées par ce genre de procédure.  C'est pourquoi la requête SQL est générée plutôt
 * dans le code Java avant d'être envoyée à Oracle.
 * Le resultSet retourné par les recherches est traité dans la routine traitementResultSet.
 * Procédure appelée : générée ici.
 * Date de création : (2002-02-12)
 * @author François Guérin
 * @param subject  CardexAuthenticationSubject : données nominatives sur l'utilisateur
 * @param criteria CriteresRechercheSociete : critères de recherche
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return ValueListIterator : liste des sociétés retournées par la recherche.
 */
  public List<Societe> select(CardexAuthenticationSubject subject, CriteresRechercheSociete criteria) throws DAOException {
		JDBCTemplate template = new JDBCTemplate(subject);
  	List<Societe> societeList = new ArrayList<Societe>();
  	PreparerSQL preparerSQL = (new SocieteCompletSQL()).construireSQL(subject, criteria);
  	template.query(preparerSQL, criteria.getSequence(), constuireRowCallBackHandler(societeList));
	   
  	return societeList;	
  }
  
  public Integer nombreDeSocieteRecherche(CardexAuthenticationSubject subject,CriteresRechercheSociete criteria) throws DAOException {
	  JDBCTemplate template = new JDBCTemplate(subject);
	  PreparerSQL preparerSQL = (new SocieteCountSQL()).construireSQL(subject, criteria);
	   
	  UnEnregistrementPresent unEnregistrementPresent = new UnEnregistrementPresent(){
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				object = rs.getInt(1);
			}
	  };
	   
	  template.query(preparerSQL, criteria.getSequence(), unEnregistrementPresent);
	   
      return (Integer) unEnregistrementPresent.getObject();
  }  
  
    /**
     * Écriture ou suppression d'un lien (association d'une société à une autre
     * société).
     * Procédure appelée : CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER
     * Date de création : (2002-01-28)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param societe Societe : Société source.
     * @param addedSociete Societe : Société associée.
     * @param action String : "I" : ajout d'un lien; "D" : suppression d'un lien.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée est
     * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
     * "stored procedure".
     */
    public void editLienSociete(CardexAuthenticationSubject subject, Societe societe, Societe addedSociete, String action) throws DAOException {
        Connection connection = DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
        try {
            callableStatement =
                connection.prepareCall("begin CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER (?,?,?,?,?,?,?,?,?,?,?); end;");
                callableStatement.setString(1,action); //action
                callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
                callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
                callableStatement.setLong(2, addedSociete.getLien());
                callableStatement.setLong(3, addedSociete.getLienSite());
                callableStatement.setLong(4, societe.getCle());
                callableStatement.setLong(5, addedSociete.getCle());
                callableStatement.setLong(6, addedSociete.getRole());
                callableStatement.setLong(7, addedSociete.getTypeLien());
                callableStatement.setLong(8, societe.getSite());
                callableStatement.setString(9, GlobalConstants.GenreFichier.SOCIETE);
                callableStatement.setLong(10, addedSociete.getSite());
                callableStatement.setString(11, GlobalConstants.GenreFichier.SOCIETE);
                callableStatement.execute();
        } catch (SQLException se) {
            throw new DAOException(se);
        } finally {
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


    // Méthode addLien


    public Adresse addLienAdresse(CardexAuthenticationSubject subject, Societe societe, Adresse adresse) throws DAOException {
        return FabriqueCardexDAO.getInstance().getAdresseDAO().insert(subject, adresse, GlobalConstants.GenreFichier.SOCIETE);
    }

    public Narration addLienNarration(CardexAuthenticationSubject subject, Societe societe, Narration narration) throws DAOException {
        narration.setLien(societe.getCle());
        narration.setLienSite(societe.getSite());
        return FabriqueCardexDAO.getInstance().getNarrationDAO().insert(subject, narration, GlobalConstants.GenreFichier.SOCIETE);
    }

    public void addLienDossier(CardexAuthenticationSubject subject, Societe societe, Dossier dossier) throws DAOException {
    	FabriqueCardexDAO.getInstance().getDossierDAO().editLienDossier(subject, dossier, societe, "I");
    }

    public void addLienVehicule(CardexAuthenticationSubject subject, Societe societe, Vehicule vehicule) throws DAOException {
        VehiculeDAO.editLienVehicule(subject, vehicule, societe, "I");
    }

    public void addLienSujet(CardexAuthenticationSubject subject, Societe societe, Sujet sujet) throws DAOException {
    	FabriqueCardexDAO.getInstance().getSujetDAO().editLienSujet(subject, sujet, societe, "I");
    }

    public void addLienSujetDetaillant(CardexAuthenticationSubject subject, Societe societe, Sujet sujet) throws DAOException {
    	FabriqueCardexDAO.getInstance().getSujetDAO().editLienSujet(subject, sujet, societe, "M");
    }

    public void addLienSociete(CardexAuthenticationSubject subject, Societe societe, Societe addedSociete) throws DAOException {
        editLienSociete(subject, societe, addedSociete, "I");
    }

    public Photo addLienPhoto(CardexAuthenticationSubject subject, Societe societe,Photo photo) throws DAOException {
        return FabriqueCardexDAO.getInstance().getPhotoDAO().insert(subject, photo, GlobalConstants.GenreFichier.SOCIETE);
    }


    // Méthode deleteLien


    public void deleteLienAdresse(CardexAuthenticationSubject subject, Societe societe, Adresse adresse) throws DAOException {
    	FabriqueCardexDAO.getInstance().getAdresseDAO().delete(subject, adresse, GlobalConstants.GenreFichier.SOCIETE);
    }
    
    /**
     * Suppression de toutes les sociétés placées en confidentialité 8.
     * Cette méthode est appelée à partir de la recherche de dossiers.
     * Procédure appelée : SP_EPURATION_SOCIETES_SITE
     * Date de création : (2002-10-17)
     * @author François Guérin
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     */
    public void delete(CardexAuthenticationSubject subject)
            throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
        CallableStatement callableStatement = null;
        CardexUser user = (CardexUser)subject.getUser();

        try {
            callableStatement = connection.prepareCall(
                    "begin SP_EPURATION_SOCIETES_SITE(?); end;");
                callableStatement.setLong(1, user.getSite());
                callableStatement.execute();
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

    public void deleteLienNarration(CardexAuthenticationSubject subject, Societe societe, Narration narration) throws DAOException {
    	FabriqueCardexDAO.getInstance().getNarrationDAO().delete(subject, narration, GlobalConstants.GenreFichier.SOCIETE);
    }

    public void deleteLienDossier(CardexAuthenticationSubject subject, Societe societe, Dossier dossier) throws DAOException {
    	FabriqueCardexDAO.getInstance().getDossierDAO().editLienDossier(subject, dossier, societe, "D");
    }

    public void deleteLienVehicule(CardexAuthenticationSubject subject, Societe societe,Vehicule vehicule) throws DAOException {
        VehiculeDAO.editLienVehicule(subject, vehicule, societe, "D");
    }

    public void deleteLienSujet(CardexAuthenticationSubject subject, Societe societe,Sujet sujet) throws DAOException {
        log.fine("deleteLienSujet: société: " + societe);
        log.fine("deleteLienSujet: sujet: " + sujet);
        FabriqueCardexDAO.getInstance().getSujetDAO().editLienSujet(subject, sujet, societe, "D");
    }

    public void deleteLienSociete(CardexAuthenticationSubject subject, Societe societe, Societe addedSociete) throws DAOException {
        editLienSociete(subject, societe, addedSociete, "D");
    }

    public void deleteLienPhoto(CardexAuthenticationSubject subject, Societe societe, Photo photo) throws DAOException {
    	FabriqueCardexDAO.getInstance().getPhotoDAO().delete(subject, photo, GlobalConstants.GenreFichier.SOCIETE);
    }


    // Méthode updateLien


    public void updateLienAdresse(CardexAuthenticationSubject subject, Societe societe, Adresse adresse) throws DAOException {
    	FabriqueCardexDAO.getInstance().getAdresseDAO().update(subject, adresse, GlobalConstants.GenreFichier.SOCIETE);
    }

    public void updateLienNarration(CardexAuthenticationSubject subject, Societe societe, Narration narration) throws DAOException {
    	FabriqueCardexDAO.getInstance().getNarrationDAO().update(subject, narration, GlobalConstants.GenreFichier.SOCIETE);
    }

    public void approuveLienNarration(CardexAuthenticationSubject subject, Societe societe, Narration narration) throws DAOException {
    	FabriqueCardexDAO.getInstance().getNarrationDAO().approbation(subject, narration, GlobalConstants.GenreFichier.SOCIETE);
    }

    public void updateLienDossier(CardexAuthenticationSubject subject, Societe societe, Dossier dossier) throws DAOException {
    	FabriqueCardexDAO.getInstance().getDossierDAO().editLienDossier(subject, dossier, societe, "U");
    }

    public void updateLienVehicule(CardexAuthenticationSubject subject, Societe societe,Vehicule vehicule) throws DAOException {
        VehiculeDAO.editLienVehicule(subject, vehicule, societe, "U");
    }

    public void updateLienSujet(CardexAuthenticationSubject subject, Societe societe,Sujet sujet) throws DAOException {
    	FabriqueCardexDAO.getInstance().getSujetDAO().editLienSujet(subject, sujet, societe, "U");
    }

    public void updateLienSociete(CardexAuthenticationSubject subject, Societe societe, Societe addedSociete) throws DAOException {
        editLienSociete(subject, societe, addedSociete, "U");
    }

    public void updateLienPhoto(CardexAuthenticationSubject subject, Societe societe, Photo photo) throws DAOException {
    	FabriqueCardexDAO.getInstance().getPhotoDAO().update(subject, photo, GlobalConstants.GenreFichier.SOCIETE);
    }


    /**
     * Retourne les données associées à une société.
     * Procédure appelée : CARDEX_WEB_LIRE_DOC_TRI.SPW_L5_SO_SOCIETE
     * Date de création : (2002-01-28)
     * @author François Guérin
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param cle : cle de l'object avec lequel on cherche l'association.
     * @param site : site de l'object avec lequel on cherche l'association.
     * @param genreFichier : genre de l'object avec lequel on cherche
     * l'association.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Collection : Liste des dossiers associés.
     */
    public Collection findLiensSociete(CardexAuthenticationSubject subject,
            long cle, long site, String genreFichier) throws DAOException {
        log.fine("findLiensSociete()");
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin Cardex_Web_Lire_Doc_Tri.SPW_L5_SO_SOCIETE (?,?,?,?); end;"); 
            callableStatement.setLong(1, cle);
            callableStatement.setLong(2, site);
            callableStatement.setString(3, genreFichier);
            callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
            
            resultSet = (ResultSet)callableStatement.getObject(4);
            ArrayList results = new ArrayList();
            while ( resultSet.next() ) {
                results.add(traitementResultSetLink(resultSet));
            }
            //Pour les sujets, on cherche ensuite les audits de liaison pour afficher toutes les sociétés auxquelles 
            //le sujet a appartenu.
            if(genreFichier.equals(GlobalConstants.GenreFichier.SUJET)){
            	callableStatement = connection.prepareCall(
	            	"begin CARDEX_AUDIT.SP_L_SO_SOCIETE_LIAISON (?,?,?,?); end;"); 
			    callableStatement.setLong(1, cle);
			    callableStatement.setLong(2, site);
			    callableStatement.setString(3, genreFichier);
			    callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			    callableStatement.execute();
			    resultSet = (ResultSet)callableStatement.getObject(4);
			    while ( resultSet.next() ) {
			        results.add(traitementResultSetLinkAudit(resultSet));
			    }
            }
            
            return results;
        }
        catch (SQLException se) {
            throw new DAOException(se);
        }
        finally {
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
 * Lecture des dossiers associés à une société
 * Procédure appelée : CARDEX_WEB_LIRE_DOC_TRI.SPW_L5_DO_DOSSIER
 * Date de création : (2002-02-12)
 * @author François Guérin
 * @param subject  CardexAuthenticationSubject : données nominatives sur l'utilisateur
 * @param societe Societe : société de base
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Collection : liste des dossiers associés
 */
    public Collection findLiensDossier(CardexAuthenticationSubject subject, Societe societe) throws DAOException{
    	
    	if (societe == null)
    		throw new IllegalArgumentException("Le champ société ne peut pas être null ");
    	
        return FabriqueCardexDAO.getInstance().getDossierDAO().findLiensDossier(subject, societe.getCle(),
                societe.getSite(), GlobalConstants.GenreFichier.SOCIETE);
   }

    /**
     * Retourne les sociétés associés à un dossier.  La procédure appelée est
     * dans OracleSocieteDAO.
     * Date de création : (2002-03-05)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Collection : Liste des véhicules associés.
     */
    public Collection findLiensSociete(CardexAuthenticationSubject subject,
            Societe societe) throws DAOException {
        return findLiensSociete(subject, societe.getCle(),
                societe.getSite(), GlobalConstants.GenreFichier.SOCIETE);
    }


    public Collection findLiensAdresse(CardexAuthenticationSubject subject, Societe societe) throws DAOException {
        return FabriqueCardexDAO.getInstance().getAdresseDAO().findLiensAdresse(subject, societe.getCle(),
                societe.getSite(), societe.getLienDateCreation(), GlobalConstants.GenreFichier.SOCIETE);
    }

    public Collection findLiensAdresseAudit(CardexAuthenticationSubject subject, Societe societe) throws DAOException {
        return FabriqueCardexDAO.getInstance().getAdresseDAO().findLiensAdresseAudit(subject, societe.getCle(),
                societe.getSite(), societe.getLienDateCreation(), GlobalConstants.GenreFichier.SOCIETE);
    }

    /**
     * Retourne les narrations associées à une société.  La procédure appelée est
     * dans OracleNarrationDAO.
     * Date de création : (2002-01-28)
     * @author François Guérin
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param dossier Societe : Societe de base.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Collection : Liste des narrations associées.
     */
    public Collection findLiensNarration(CardexAuthenticationSubject subject,
            Societe societe) throws DAOException {
        return FabriqueCardexDAO.getInstance().getNarrationDAO().findLiensNarration(subject, societe.getCle(),
                societe.getSite(), GlobalConstants.GenreFichier.SOCIETE);
    }

    /**
     * Retourne les véhicule associés à un dossier.  La procédure appelée est
     * dans OracleVehiculeDAO.
     * Date de création : (2002-03-05)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Collection : Liste des véhicules associés.
     */
    public Collection findLiensVehicule(CardexAuthenticationSubject subject,
            Societe societe) throws DAOException {
        return VehiculeDAO.findLiensVehicule(subject, societe.getCle(),
                societe.getSite(), GlobalConstants.GenreFichier.SOCIETE);
    }

    public Collection findLiensSujet(CardexAuthenticationSubject subject,
            Societe societe) throws DAOException {
        return FabriqueCardexDAO.getInstance().getSujetDAO().findLiensSujetEnquete(subject, societe.getCle(),
                societe.getSite(), GlobalConstants.GenreFichier.SOCIETE);
    }

    public Collection findLiensProprietaires(CardexAuthenticationSubject subject,
            Societe societe) throws DAOException {
        return FabriqueCardexDAO.getInstance().getSujetDAO().findLiensProprietaires(subject, societe.getCle(),
                societe.getSite(), GlobalConstants.GenreFichier.SOCIETE);
    }

    public Collection findLiensPhoto(CardexAuthenticationSubject subject,
            Societe societe) throws DAOException {
        return FabriqueCardexDAO.getInstance().getPhotoDAO().findLiensPhoto(subject, societe.getCle(),
                societe.getSite(), societe.getLienDateCreation(), GlobalConstants.GenreFichier.SOCIETE);
    }
    public Collection findLiensCaracteristique(CardexAuthenticationSubject subject, Caracteristiques caracteristiques) throws DAOException { return new ArrayList(); }

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.SocieteDAO#rechercheAdresseSociete(com.lotoquebec.cardex.authentication.CardexAuthenticationSubject, com.lotoquebec.cardex.business.CriteresRechercheAdresses)
	 */
	public List<Societe> rechercheAdresseSociete(CardexAuthenticationSubject subject, CriteresRechercheAdresses criteres) throws DAOException {
		final List<Societe> liste = new ArrayList<Societe>();
    	JDBCTemplate template = new JDBCTemplate(subject);
    	
    	PreparerSQL preparerSQL = (new AdresseSocieteCompletSQL()).construireSQL( subject, criteres );
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				Societe societe = new SocieteVO();
				
				societe.setCle( rs.getLong( AdresseSocieteSQL.CLE_SOCIETE ) );
				societe.setSite( rs.getLong( AdresseSocieteSQL.SITE_SOCIETE ) );
				societe.setNumeroFiche( rs.getString( AdresseSocieteSQL.NUMERO_FICHE ) );
				societe.setSeverite( rs.getLong( AdresseSocieteSQL.SEVERITE ) );
				societe.setRaisonEtre( rs.getString( AdresseSocieteSQL.RAISON_SOCIALE ) );
				societe.setClasse( rs.getLong( AdresseSocieteSQL.CLASSE ) );
				societe.setNom( rs.getString( AdresseSocieteSQL.NOM_SOCIETE ) );
				societe.setReferenceNom( rs.getString( AdresseSocieteSQL.NOM_REFERENCE ) );
				societe.setReferencePrenom( rs.getString( AdresseSocieteSQL.PRENOM_REFERENCE ) );
				
				liste.add(societe);
			}
    	};
    	
    	template.query(preparerSQL, criteres.getSequence(), rch);
    	
		return liste;
	}

    public Integer nombreDeSocieteRechercheAdresse(CardexAuthenticationSubject subject, CriteresRechercheAdresses criteria) throws DAOException {
    	JDBCTemplate template = new JDBCTemplate(subject);
    	PreparerSQL preparerSQL = (new AdresseSocieteCountSQL()).construireSQL(subject, criteria);
  	   
    	UnEnregistrementPresent unEnregistrementPresent = new UnEnregistrementPresent(){
  			@Override
  			public void processRow(ResultSet rs) throws SQLException {
  				object = rs.getInt(1);
  			}
    	};
  	   
    	template.query(preparerSQL, criteria.getSequence(), unEnregistrementPresent);
  	   
        return (Integer) unEnregistrementPresent.getObject();
     }    
	
	/*
	 * (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.SocieteDAO#rechercheAdresseInvalideSociete(com.lotoquebec.cardex.authentication.CardexAuthenticationSubject, com.lotoquebec.cardex.business.CriteresRechercheAdresses)
	 */
	public Collection rechercheAdresseInvalideSociete(final CardexAuthenticationSubject subject, final CriteresRechercheAdresses criteres) throws DAOException {
		final Map mapSocieteAdresseInvalide = new LinkedHashMap();
		StoreProcTemplate template = new StoreProcTemplate(subject);
    	
		PreparerCallableStatement rowCallHandler = new PreparerCallableStatement(){

			public void preparer(CallableStatement callableStatement) throws SQLException {
				callableStatement.setLong(1, criteres.getSiteOrigine());
				
				if (criteres.getDateCreationDu() != null)
					callableStatement.setDate(2, new Date(criteres.getDateCreationDu().getTime()));
				else
					callableStatement.setDate(2, null);

				if (criteres.getDateCreationAu() != null)
					callableStatement.setDate(3, new Date(criteres.getDateCreationAu().getTime()));
				else
					callableStatement.setDate(3, null);
				
				callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			}
			
		};
		
		template.prepareCall("Cardex_Lire_Lien.Sp_L_So_Societe_Ad_Adresse", 4, 4, rowCallHandler);
	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				try {
					AdresseBusinessDelegate delegate = new AdresseBusinessDelegate();
					Societe societe = traitementResultSet(rs);
					Adresse adresse = FabriqueCardexDAO.getInstance().getAdresseDAO().obtenirAdresseVOde(rs);
					
					AdresseSortie adresseSortie = delegate.validationAdresse(subject, adresse);
					
					if (adresseSortie.isValide())
						return;
					adresse.setMessage(adresseSortie.obtenirMessageHTML());
					
					if (mapSocieteAdresseInvalide.containsKey(societe.getCleUnique())){
						societe = (Societe) mapSocieteAdresseInvalide.get(societe.getCleUnique());
						societe.addAdresse(adresse);
					}else{
						societe.addAdresse(adresse);
						mapSocieteAdresseInvalide.put(societe.getCleUnique(), societe);
					}
				} catch (BusinessResourceException e) {
					e.printStackTrace();
				}
			}
    	};
    	
    	template.query(rch, true);
    	
		return mapSocieteAdresseInvalide.values();
	}

    /**
     * Recherche de l'audit des changements d'une société.
     * 
     * Procédure appelée : CARDEX_AUDIT.SP_L_AUDIT_SOCIETE
     * Date de création : (2011-03-08)
     * @author François Guérin
     * @param subject  CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param criteria Sujet : Sujet à rechercher.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return Dossier : Instance de dossier associée.
     */
    public List audit(CardexAuthenticationSubject subject,Societe criteria)
            throws DAOException {
        Connection connection = null;            
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		List  resultats = new ArrayList();
          try {
        	connection = DAOConnection.getInstance().getConnection(subject);
            callableStatement = connection.prepareCall(
                    "begin CARDEX_AUDIT.SP_L_AUDIT_SOCIETE(?,?,?); end;");
        	callableStatement.setLong(1,criteria.getCle());
            callableStatement.setLong(2,criteria.getSite());
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(3);
            //Traitement du résultat retourné
			resultats = traitementResultSetAudit(resultSet);

		}catch (SQLException se) {
            throw new DAOException(se);
        }

        finally {
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
        
        return resultats;
    }   

    /**
     * Routine pour traiter les ResultSet retournés par l'audit des changements.
     * Date de création : (2002-01-28)
     * @author François Guérin
     * @param resultSet  ResultSet : données retournées par une recherche
     * @throws SQLException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée est
     * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
     * "stored procedure".
     * @return ArrayList : liste des sociétés traités.
     */
      private ArrayList traitementResultSetAudit(ResultSet resultSet) throws DAOException {
          ArrayList results = new ArrayList();
          try { 
              while (resultSet.next()){
                  SocieteVO societe = new SocieteVO();
                  societe.setCle(resultSet.getLong("L_SO_CLE"));
                  societe.setSite(resultSet.getLong("L_SI_CLE"));
                  societe.setEntite(resultSet.getLong("I_EN_CLE"));
                  societe.setStatut(resultSet.getLong("I_ST_CLE"));
                  societe.setLangue(resultSet.getLong("I_LS_CLE"));
                  societe.setRaisonEtre(resultSet.getString("V_SO_RAISON_SOCIALE"));
                  societe.setNom(resultSet.getString("V_SO_NOM"));
                  societe.setClasse(resultSet.getLong("I_CL_CLE"));
                  societe.setDateDeFondation(resultSet.getTimestamp("D_SO_DATE_FONDATION"));
                  societe.setDateCreation(resultSet.getTimestamp("D_SO_DATE_CREATION"));
                  societe.setConfidentialite(resultSet.getLong("I_CC_CLE"));
                  societe.setReferenceNom(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_NOM"));
                  societe.setReferencePrenom(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_PRENOM"));
                  societe.setReference1(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_1"));
                  societe.setReference2(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_2"));
                  societe.setNumeroFiche(OracleDAOUtils.getString(resultSet,"V_SO_REFERENCE_3"));
                  societe.setSeverite(resultSet.getLong("I_SE_CLE"));
                  societe.setSeveriteCasino(resultSet.getLong("I_SE_CLE_CASINO"));
                  societe.setCreateur(resultSet.getString("V_SO_CREE_PAR"));
                  societe.setChangePar(OracleDAOUtils.getString(resultSet,"CHANGE_PAR"));
                  societe.setDateChangement(resultSet.getTimestamp("D_SO_DATE_CHANGEMENT"));
                  societe.setCentreRegional(resultSet.getString("L_SO_CENTRE_REGIONAL"));
                  societe.setDistrict(OracleDAOUtils.getString(resultSet,"V_SO_DISTRICT"));
                  societe.setCodeCompte(OracleDAOUtils.getString(resultSet,"V_SO_CODE_COMPTE"));
        		  societe.setActif(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SO_ACTIF")));
        		  societe.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SO_IND_RDD")));
                  societe.setDateInactif(resultSet.getTimestamp("D_SO_DATE_INACTIVATION"));
                  societe.setCommentaire(OracleDAOUtils.getString(resultSet,"V_SO_COMMENTAIRE"));
                  societe.setRaisonDesactivation(OracleDAOUtils.getString(resultSet,"V_SO_RAISON_INACTIVATION"));
                  log.fine("   [SOCIETE id='" + societe.getNumeroFiche() + "' Nom='" + societe.getNom()+"']");
                  results.add(societe);
              }
          }catch (SQLException se) {
   	       throw new DAOException(se);
   		}
             return results;
    }

  	public List<Societe> rechercheSocieteInvestigationExpiration90Jours(Connection connection) throws DAOException{
		final List<Societe> listeSociete = new ArrayList<Societe>();
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){
    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			}
    	};		
		
		storeProcTemplate.prepareCall("Cardex_Lire_Doc.SP_L_SO_EXPIRE_90_JOURS", 1, 1, rch);
		
		RowCallbackHandler rowCallbackHandler = new RowCallbackHandler(){

			public void processRow(ResultSet rs) throws SQLException {
				Societe societeVO = traitementResultSet(rs);
				listeSociete.add(societeVO);
			}
		};
		
		storeProcTemplate.query(rowCallbackHandler, false);
		
		return listeSociete;
	}	

	public List<Societe> rechercheSocieteInvestigationExpire(Connection connection) throws DAOException{
		final List<Societe> listeSociete = new ArrayList<Societe>();
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){
    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			}
    	};		
		
		storeProcTemplate.prepareCall("Cardex_Lire_Doc.SP_L_SO_SOCIETE_EXPIRE", 1, 1, rch);
		
		RowCallbackHandler rowCallbackHandler = new RowCallbackHandler(){

			public void processRow(ResultSet rs) throws SQLException {
				Societe societeVO = traitementResultSet(rs);
				listeSociete.add(societeVO);
			}
		};
		
		storeProcTemplate.query(rowCallbackHandler, false);
		
		return listeSociete;
	}	
 
    /**
     * Mise à jour d'un lien (association d'une société à un sujet, à un dossier ou à une société).
     * Utilisant la stored procedure SP_E_LDD_LIEN_DOSSIER PACKAGE CARDEX_LIEN.
     * Date de création : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param societe Societe : Société associée.
     * @throws DAOException lancéeé lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     */
    public void updateLien(CardexAuthenticationSubject subject,
            Societe societe) throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER"
                    + " (?,?,?,?,?,?,?,?,?,?,?); end;");
                callableStatement.setString(1,"U"); //action
                callableStatement.registerOutParameter(2,
                        java.sql.Types.DECIMAL);
                callableStatement.registerOutParameter(3,
                        java.sql.Types.DECIMAL);
                callableStatement.setLong(2, societe.getLien());
                callableStatement.setLong(3, societe.getLienSite());
                callableStatement.setLong(4, societe.getCle());
                callableStatement.setLong(5, 0);
                callableStatement.setLong(6, societe.getRole());
                callableStatement.setLong(7, societe.getTypeLien());
                callableStatement.setLong(8, societe.getSite());
                callableStatement.setString(9,
                        GlobalConstants.GenreFichier.DOSSIER); //valeur non prise en compte dans la procédure
                callableStatement.setLong(10, 0);
                callableStatement.setString(11,
                        GlobalConstants.GenreFichier.SOCIETE);
                callableStatement.execute();
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
     * Copie des données d'une société à une autre.
     * Cette procédure ne devrait être que temporaire, le temps de permettre à l'entité Loto-Québec
     * de supprimer les doublons.
     * Utilisant la stored procedure SP_E_COPIER_SOCIETE
     * Date de création : (2012-01-10)
     * @author GUERINF
     * @param subject CardexAuthenticationSubject : Données nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param societe Societe : Société associée.
     * @throws DAOException lancéeé lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     */
    public void copier(CardexAuthenticationSubject subject,
    		long cleSource, long siteSource, long cleDestination, long siteDestination) throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_SPECIAL.SP_E_COPIER_SOCIETE"
                    + " (?,?,?,?); end;");
                callableStatement.setLong(1,cleSource); 
                callableStatement.setLong(2, siteSource);
                callableStatement.setLong(3, cleDestination);
                callableStatement.setLong(4, siteDestination);
                callableStatement.execute();
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

    //Mise-à-jour de la sévérité des sociétés d'invesigation qui ont un dossier actif et dont la sévérité n'est pas à 3
	  public void severite3SocieteInvestigationDossierActif(Connection connection) throws DAOException {
			StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);

			PreparerCallableStatement rch = new PreparerCallableStatement(){
	    		public void preparer(CallableStatement callableStatement) throws SQLException {
				}
	    	};
	    	
	    	storeProcTemplate.prepareCall("CARDEX_DOC.SP_E_SO_SEVERITE_3", 0, null);

	    	storeProcTemplate.query( false );		
	   }
    
    //Mise-à-jour de la sévérité à 2 pour les sujets d'invesigation dont le dossier est arrivé à expiration et dont la sévérité n'est pas à 2.
	  public void severite2SocieteInvestigationExpiration(Connection connection) throws DAOException {
			StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);

			PreparerCallableStatement rch = new PreparerCallableStatement(){
	    		public void preparer(CallableStatement callableStatement) throws SQLException {
				}
	    	};
	    	
	    	storeProcTemplate.prepareCall("CARDEX_DOC.SP_E_SO_SEVERITE_2", 0, null);

	    	storeProcTemplate.query( false );		
	   }

    //Mise-à-jour de la sévérité à 4 pour les sujets d'invesigation dont le dossier actif arrive à expiration dans 90 jours.
	  public void severite4SocieteInvestigationExpiration90Jours(Connection connection) throws DAOException {
			StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);

			PreparerCallableStatement rch = new PreparerCallableStatement(){
	    		public void preparer(CallableStatement callableStatement) throws SQLException {
				}
	    	};
	    	
	    	storeProcTemplate.prepareCall("CARDEX_DOC.SP_E_SO_SEVERITE_4", 0, null);

	    	storeProcTemplate.query( false );		
	   }

		/**
		 * Ajout des audits pour les sociétés (détaillants) qui seront mises à jour
		 * dans la tâche CDX00_00013.
		 */

		public void creerAuditSocietes(Connection connection) {
			CallableStatement callableStatement = null;

	        try {
	            callableStatement = connection.prepareCall(
	                    "begin CARDEX_AUDIT.SP_E_AUDIT_DETAILLANT ; end;");
	            callableStatement.execute();
	        }
	        catch (SQLException se) {
	        	se.printStackTrace();
	        }

	        finally {
	        	if(callableStatement != null) {
					try {
							callableStatement.close();
				    } catch (java.sql.SQLException e) {
				    	e.printStackTrace();
			        }
				}
		}
	}

		/**
		 * Ajout des audits pour les sociétés (détaillants) qui seront mises à jour
		 * dans la tâche CDX00_00013 pour les personnes morales.
		 */

		public void creerAuditPersonnesMorales(Connection connection) {
			CallableStatement callableStatement = null;

	        try {
	            callableStatement = connection.prepareCall(
	                    "begin CARDEX_AUDIT.SP_E_AUDIT_PERSONNE_MORALE ; end;");
	            callableStatement.execute();
	        }
	        catch (SQLException se) {
	        	se.printStackTrace();
	        }

	        finally {
	        	if(callableStatement != null) {
					try {
							callableStatement.close();
				    } catch (java.sql.SQLException e) {
				    	e.printStackTrace();
			        }
				}
		}
	}

		/**
		 * Ajout des audits pour les sociétés (détaillants) qui seront mises à jour
		 * dans la tâche CDX00_00013 pour les personnes morales.
		 */

		public void creerAuditAdresses(Connection connection) {
			CallableStatement callableStatement = null;

	        try {
	            callableStatement = connection.prepareCall(
	                    "begin CARDEX_AUDIT.SP_E_AUDIT_ADRESSE_RDD ; end;");
	            callableStatement.execute();
	        }
	        catch (SQLException se) {
	        	se.printStackTrace();
	        }

	        finally {
	        	if(callableStatement != null) {
					try {
							callableStatement.close();
				    } catch (java.sql.SQLException e) {
				    	e.printStackTrace();
			        }
				}
		}
	}
		/**
		 * Mise à jour des sociétés à partir de RDD
		 * dans la tâche CDX00_00013.
		 */

		public void miseAJourDetaillants(Connection connection) {
			CallableStatement callableStatement = null;

	        try {
	            callableStatement = connection.prepareCall(
	                    "begin CARDEX_DOC.SP_U_DETAILLANTS ; end;");
	            callableStatement.execute();
	        }
	        catch (SQLException se) {
	        	se.printStackTrace();
	        }

	        finally {
	        	if(callableStatement != null) {
					try {
							callableStatement.close();
				    } catch (java.sql.SQLException e) {
				    	e.printStackTrace();
			        }
				}
		}
	}

		/**
		 * Mise à jour des adresses des sociétés à partir de RDD
		 * dans la tâche CDX00_00013.
		 */

		public void miseAJourAdressesDetaillant(Connection connection) {
			CallableStatement callableStatement = null;

	        try {
	            callableStatement = connection.prepareCall(
	                    "begin CARDEX_LIEN.SP_U_MAJ_ADRESSE_RDD ; end;");
	            callableStatement.execute();
	        }
	        catch (SQLException se) {
	        	se.printStackTrace();
	        }

	        finally {
	        	if(callableStatement != null) {
					try {
							callableStatement.close();
				    } catch (java.sql.SQLException e) {
				    	e.printStackTrace();
			        }
				}
		}
	}

		/**
		 * Mise à jour des personnes morales à partir de RDD
		 * dans la tâche CDX00_00013.
		 */

		public void miseAJourPersonneMorale(Connection connection) {
			CallableStatement callableStatement = null;

	        try {
	            callableStatement = connection.prepareCall(
	                    "begin CARDEX_DOC.SP_U_PERSONNE_MORALE ; end;");
	            callableStatement.execute();
	        }
	        catch (SQLException se) {
	        	se.printStackTrace();
	        }

	        finally {
	        	if(callableStatement != null) {
					try {
							callableStatement.close();
				    } catch (java.sql.SQLException e) {
				    	e.printStackTrace();
			        }
				}
		}
	}
		/**
		 * Ajout dans Cardex des nouveaux détaillants RDD
		 * dans la tâche CDX00_00013.
		 */

		public void ajoutNouveauxDetaillants(CardexAuthenticationSubject subject, Connection connection) throws DAOException  {
			CallableStatement callableStatement = null;
			ResultSet resultSet = null;
	        try {
	        	//On lit d'abord la liste des nouveaux détaillants de RDD qu'on ne retrouve pas dans Cardex.
	            callableStatement = connection.prepareCall(
	                    "begin CARDEX_LIRE_DOC.SP_L_SO_RDD(?) ; end;");
	            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
	            callableStatement.execute();
	            resultSet = (ResultSet)callableStatement.getObject(1);
	            ArrayList results = new ArrayList();
	            while ( resultSet.next() ) {
	                results.add(traitementResultSet(resultSet));
	            }
	            //On lit ensuite la liste pour l'insertion dans Cardex
	            Iterator it = results.iterator();
	            while (it.hasNext()) {
	                Societe     detaillant = (Societe) it.next();
	                ajoutDetaillant(subject, detaillant);
	            }

	            //On fait la même chose pour les adresses
	            callableStatement = null;
	            resultSet = null;
	            callableStatement = connection.prepareCall("begin CARDEX_LIRE_LIEN.SP_L_ADRESSE_RDD(?) ; end;");
		        callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
		        callableStatement.execute();
		        resultSet = (ResultSet)callableStatement.getObject(1);
	            while ( resultSet.next() ) {
	                Adresse adresse = new AdresseVO();
	                adresse.setLien(resultSet.getLong("L_SO_CLE"));
	                adresse.setLienSite(resultSet.getLong("L_SI_CLE"));
	                adresse.setNumeroMunicipal(resultSet.getString("NO_CIVIQUE"));
	                adresse.setTypeRue(resultSet.getLong("TYPE_RUE_DESC_LONGUE"));
	                adresse.setNomRue(resultSet.getString("NOM_RUE"));
	                adresse.setPointCardinal(resultSet.getLong("POINT_CARDINAL_DESC_LONGUE"));
	                adresse.setNumeroUnite(resultSet.getString("NO_UNITE"));
	                adresse.setVille(resultSet.getLong("L_VI_CLE"));
	                adresse.setPays(Long.valueOf(GlobalConstants.Adresse.PAYS));
	                adresse.setProvince(Long.valueOf(GlobalConstants.Adresse.PROVINCE));
	                adresse.setCodePostal(resultSet.getString("CODE_POSTAL"));
	                adresse.setStatut(Long.valueOf(GlobalConstants.Adresse.RESIDENCE_PRINCIPALE));
	                String telephoneAffaire = resultSet.getString("AFFAIRE");
	                if(StringUtils.isNotEmpty(telephoneAffaire)){
	                	adresse.setTypeUtilTelephone1(GlobalConstants.TypeTelephone.AFFAIRE);
	                	adresse.setTelephone1(telephoneAffaire);
	                }
	                String fax = resultSet.getString("FAX");
	                if(StringUtils.isNotEmpty(fax)){
	                	adresse.setTypeUtilTelephone2(GlobalConstants.TypeTelephone.FAX);
	                	adresse.setTelephone2(fax);
	                }
	                String sansFrais = resultSet.getString("SANS_FRAIS");
	                if(StringUtils.isNotEmpty(sansFrais)){
	                	adresse.setTypeUtilTelephone3(GlobalConstants.TypeTelephone.SANS_FRAIS);
	                	adresse.setTelephone3(sansFrais);
	                }
	                adresse.setAdresseElectronique1(resultSet.getString("EMAIL"));
	                adresse.setIndicateurRdd(true);
	                //Ajout de l'adresse
	                FabriqueCardexDAO.getInstance().getAdresseDAO().insertAdresseRDD(subject, adresse, GlobalConstants.GenreFichier.SOCIETE);
	            }
	            
	        }
	        catch (SQLException se) {
	        	throw new DAOException(se);
	        }

	        finally {
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
				    	e.printStackTrace();
			        }
				}
		}
	}

        /**
         * Ajout dans Cardex des adresses manquantes des détaillants RDD
         * dans la tâche CDX00_00013.
         */

        public void ajoutAdressesManquantes(CardexAuthenticationSubject subject, Connection connection) throws DAOException  {
            CallableStatement callableStatement = null;
            ResultSet resultSet = null;
            try {
                callableStatement = connection.prepareCall("begin CARDEX_LIRE_LIEN.SP_L_ADRESSE_RDD(?) ; end;");
                callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
                callableStatement.execute();
                resultSet = (ResultSet)callableStatement.getObject(1);
                while ( resultSet.next() ) {
                    Adresse adresse = new AdresseVO();
                    adresse.setLien(resultSet.getLong("L_SO_CLE"));
                    adresse.setLienSite(resultSet.getLong("L_SI_CLE"));
                    adresse.setNumeroMunicipal(resultSet.getString("NO_CIVIQUE"));
                    adresse.setTypeRue(resultSet.getLong("TYPE_RUE_DESC_LONGUE"));
                    adresse.setNomRue(resultSet.getString("NOM_RUE"));
                    adresse.setPointCardinal(resultSet.getLong("POINT_CARDINAL_DESC_LONGUE"));
                    adresse.setNumeroUnite(resultSet.getString("NO_UNITE"));
                    adresse.setVille(resultSet.getLong("L_VI_CLE"));
                    adresse.setPays(Long.valueOf(GlobalConstants.Adresse.PAYS));
                    adresse.setProvince(Long.valueOf(GlobalConstants.Adresse.PROVINCE));
                    adresse.setCodePostal(resultSet.getString("CODE_POSTAL"));
                    adresse.setStatut(Long.valueOf(GlobalConstants.Adresse.RESIDENCE_PRINCIPALE));
                    String telephoneAffaire = resultSet.getString("AFFAIRE");
                    if(StringUtils.isNotEmpty(telephoneAffaire)){
                        adresse.setTypeUtilTelephone1(GlobalConstants.TypeTelephone.AFFAIRE);
                        adresse.setTelephone1(telephoneAffaire);
                    }
                    String fax = resultSet.getString("FAX");
                    if(StringUtils.isNotEmpty(fax)){
                        adresse.setTypeUtilTelephone2(GlobalConstants.TypeTelephone.FAX);
                        adresse.setTelephone2(fax);
                    }
                    String sansFrais = resultSet.getString("SANS_FRAIS");
                    if(StringUtils.isNotEmpty(sansFrais)){
                        adresse.setTypeUtilTelephone3(GlobalConstants.TypeTelephone.SANS_FRAIS);
                        adresse.setTelephone3(sansFrais);
                    }
                    adresse.setAdresseElectronique1(resultSet.getString("EMAIL"));
                    adresse.setIndicateurRdd(true);
                    //Ajout de l'adresse
                    FabriqueCardexDAO.getInstance().getAdresseDAO().insertAdresseRDD(subject, adresse, GlobalConstants.GenreFichier.SOCIETE);
                }
                
            }
            catch (SQLException se) {
                throw new DAOException(se);
            }

            finally {
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
                        e.printStackTrace();
                    }
                }
        }
    }

		/**
		 * Traitement des personnes physiques de RDD dans la tâche CDX00_00013.
		 * Les personnes physiques sont les responsables attitrés des détaillants.
		 * On vérifie si une telle personne est reliée au détaillant.
		 * Si ce n'est pas le cas, on crée une fiche sujet et on relie ce sujet à la société avec un rôle Responsable
		 * 
		 */

		public void traitementPersonnesPhysiques(CardexAuthenticationSubject subject, Connection connection) throws DAOException  {
			CallableStatement callableStatement = null;
			ResultSet resultSet = null;
	        try {
	        	//On lit d'abord la liste des détaillants de Cardex dont le sujet responsable ou propriétaire lié
	        	//n'est pas le même que la personne physique de RDD.
	            callableStatement = connection.prepareCall(
	                    "begin CARDEX_LIRE_DOC.SP_L_SO_PERSONNE_PHYSIQUE(?) ; end;");
	            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
	            callableStatement.execute();
	            resultSet = (ResultSet)callableStatement.getObject(1);
	            ArrayList results = new ArrayList();
	            while ( resultSet.next() ) {
	            	//On ajoute d'abord les personnes physiques retrouvées dans RDD
	            	Sujet nouveauSujet = new SujetVO();
	            	nouveauSujet.setSite(Long.valueOf(GlobalConstants.Sites.LOTO_QUEBEC));
	                nouveauSujet.setNom(resultSet.getString("V_SO_REFERENCE_NOM"));
	                nouveauSujet.setPrenom(resultSet.getString("V_SO_REFERENCE_PRENOM"));
	                
	                //La date de naissance n'étant pas connue, on met le sujet à type d'âge inconnu et son âge à null.
	                nouveauSujet.setDateNaissance(null);
	                nouveauSujet.setTypeAge(GlobalConstants.TypeAge.INCONNU);
	                
	                nouveauSujet.setNumeroFiche(GlobalConstants.NumeroFiche.DEFAULT);
	                nouveauSujet.setConfidentialite(GlobalConstants.Confidentialite.UN);
	                //nouveauSujet.setSeverite(GlobalConstants.Severite.SEVERITE_2);
	                //nouveauSujet.setSeveriteAutres(GlobalConstants.Severite.SEVERITE_2);
	                nouveauSujet.setIndicateurRdd(true);
	                //Avant d'ajouter, on s'assure que le sujet n'existe pas déjà. Si c'est le cas, on récupère ses clés. Sinon, on l'ajoute.
	                Sujet sujetTrouve = null;
	                sujetTrouve = FabriqueCardexDAO.getInstance().getSujetDAO().findSujetRDD(subject, nouveauSujet);
	                if(sujetTrouve.getCle() != 0){
	                	nouveauSujet.setCle(sujetTrouve.getCle());
	                }else{
	                	nouveauSujet = FabriqueCardexDAO.getInstance().getSujetDAO().insertSujetDetaillant(nouveauSujet, connection);
	                }
	                //On récupére ensuite les clés pour l'insertion du lien
	                Societe detaillant = new SocieteVO();
	                detaillant.setCle(resultSet.getLong("L_SO_CLE"));
	                detaillant.setSite(resultSet.getLong("L_SI_CLE"));
	                detaillant.setRole(GlobalConstants.Role.RESPONSABLE);
	                //On traite d'abord les responsables associés (pas pour l'instant)
	                //addLienSujetDetaillant(subject, detaillant, nouveauSujet);
	                //Puis on ajoute le nouveau responsable
	                addLienSujet(subject, detaillant, nouveauSujet);
	            }
	        }
	        catch (SQLException se) {
	        	throw new DAOException(se);
	        }

	        finally {
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
				    	e.printStackTrace();
			        }
				}
		}
	}

		/**
		 * Traitement des personnes morales de RDD dans la tâche CDX00_00013.
		 * Les personnes morales sont les références nom et prénom d'une fiche de détaillant.
		 * On corrige au besoin les champs références.
		 * On vérifie si la société morale existe. Sinon, on la crée.
		 * On doit également vérifier si la société morale est reliée au détaillant.
		 * Si ce n'est pas le cas, on relie la société au détaillant avec un rôle Responsable
		 * 
		 */

		public void traitementPersonnesMorales(CardexAuthenticationSubject subject, Connection connection) throws DAOException  {
			CallableStatement callableStatement = null;
			ResultSet resultSet = null;
	        try {
	        	//On lit d'abord la liste des sociétés morales qui n'existent pas dans Cardex.
	            callableStatement = connection.prepareCall(
	                    "begin CARDEX_LIRE_DOC.SP_L_SO_SOCIETE_MORALE(?) ; end;");
	            callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
	            callableStatement.execute();
	            resultSet = (ResultSet)callableStatement.getObject(1);
	            while ( resultSet.next() ) {
	            	//On ajoute d'abord les sociétés morales retrouvées dans RDD et qui n'existent pas dans Cardex.
	                Societe detaillant = new SocieteVO();
	                //On limite le nom à 50 caractères pour respecter la longueur du champ dans Cardex.
	                String nom = StringUtils.substring(resultSet.getString("NOM_PM"),0,49);
	                detaillant.setNom(nom);
	                //On ne met rien dans les champs de référence.
	                //detaillant.setReferenceNom(resultSet.getString("NOM_REPRESENTANT_PM"));
	                //detaillant.setReferencePrenom(resultSet.getString("PRENOM_REPRESENTANT_PM"));
	                detaillant.setSite(Long.valueOf(GlobalConstants.Sites.LOTO_QUEBEC));
	                detaillant.setConfidentialite(GlobalConstants.Confidentialite.UN);
	                //detaillant.setSeverite(GlobalConstants.Severite.SEVERITE_2);
	                detaillant.setStatut(Long.valueOf(GlobalConstants.Statut.SOCIETE_REGULIER));
	                detaillant.setIndicateurRdd(true);
	                detaillant.setReference2("Société morale");
	                ajoutDetaillant(subject, detaillant);
                }
                //On cherche ensuite les détaillants qui n'ont pas de sociétés morales ou dont la société morale est différente.
	            //On ajoute la relation au besoin
                callableStatement = null;
                resultSet = null;
	            callableStatement = connection.prepareCall(
                "begin CARDEX_LIRE_DOC.SP_L_SO_SOCIETE_MORALE_LIEE(?) ; end;");
		        callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
		        callableStatement.execute();
		        resultSet = (ResultSet)callableStatement.getObject(1);
		        while ( resultSet.next() ) {
		        	Societe detaillantSource = new SocieteVO();
		        	Societe detaillantResponsable = new SocieteVO();
		        	detaillantSource.setCle(resultSet.getLong("SOURCE"));
		        	detaillantSource.setSite(Long.valueOf(GlobalConstants.Sites.LOTO_QUEBEC));
		        	detaillantResponsable.setCle(resultSet.getLong("LIAISON"));
		        	detaillantResponsable.setSite(Long.valueOf(GlobalConstants.Sites.LOTO_QUEBEC));
		        	detaillantResponsable.setRole(GlobalConstants.Role.RESPONSABLE);
		        	addLienSociete(subject, detaillantSource, detaillantResponsable);
		        }
                //On met finalement à jour les champs références nom et prénom des détaillants qui diffèrent des personnes morales de RDD.
                miseAJourPersonneMorale(connection);
	        }
	        catch (SQLException se) {
	        	throw new DAOException(se);
	        }

	        finally {
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
				    	e.printStackTrace();
			        }
				}
		}
	}

 
		   /**
		    * Écriture d'une société qui est un nouveau détaillant de RDD (Réseau des détaillants).
		    * Procédure appelée : CARDEX_DOC.SP_E_SO_DETAILLANTS
		    * Date de création : (2012-09-10)
		    * @author François Guérin
		    * @param subject CardexAuthenticationSubject : données nominatives sur l'utilisateur
		    * @param societe Societe : société saisi à l'écran
		    * @param action  java.lang.String : U ou I
		    * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
		    * rupture de connexion avec la base de données, ou que la table demandée est
		    * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
		    * "stored procedure".
		    * @return Societe
		    */
		      private void ajoutPersonnesPhysiques(CardexAuthenticationSubject subject, Societe societe) throws DAOException {
		         Connection connection = DAOConnection.getInstance().getConnection(subject);
		         try {
						Sujet nouveauSujet = new SujetVO();
				    	nouveauSujet.setSite(Long.valueOf(GlobalConstants.Sites.LOTO_QUEBEC));
				        nouveauSujet.setNom(societe.getReferenceNom());
				        nouveauSujet.setPrenom(societe.getReferencePrenom());
				        
				        //La date de naissance n'étant pas connue, on met le sujet à type d'âge inconnu et son âge à null.
	                    nouveauSujet.setDateNaissance(null);
	                    nouveauSujet.setTypeAge(GlobalConstants.TypeAge.INCONNU);
				        
	                    nouveauSujet.setNumeroFiche(GlobalConstants.NumeroFiche.DEFAULT);
				        nouveauSujet.setConfidentialite(GlobalConstants.Confidentialite.UN);
				        nouveauSujet.setSeverite(GlobalConstants.Severite.SEVERITE_2);
				        nouveauSujet.setSeveriteAutres(GlobalConstants.Severite.SEVERITE_2);
				        nouveauSujet.setIndicateurRdd(true);
				        nouveauSujet = FabriqueCardexDAO.getInstance().getSujetDAO().insertSujetDetaillant(nouveauSujet, connection);
				        //Puis on ajoute le lien
				        societe.setRole(GlobalConstants.Role.RESPONSABLE);
				        addLienSujet(subject, societe, nouveauSujet);
		         } finally {
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

			    //Mise-à-jour de la sévérité Casino des sociétés d'invesigation
			  public void severiteSocieteInvestigationCasino(Connection connection) throws DAOException {
				  CallableStatement callableStatement = null;
				  ResultSet resultSet = null;
				  try{
					 callableStatement = connection.prepareCall("begin CARDEX_LIRE_DOC.SP_L_SO_SEVERITE_CASINO (?); end;");
					 CallableStatement callableStatement2 = null;
					 callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
					 callableStatement.execute();
					 resultSet = (ResultSet)callableStatement.getObject(1);
					 //Traitement des données retournées pour mettre à jour la sévérité
					 while(resultSet.next()){
						long cle = resultSet.getLong("L_SO_CLE");
						long site = resultSet.getLong("L_SI_CLE");
						String numeroFiche = resultSet.getString("NumeroFiche");
						long severiteSujet = resultSet.getLong("SEVERITE");
						String NumeroCardex = resultSet.getString("NumeroCardex");						
						long severite = resultSet.getLong("SEVERITE_DOSSIER");
						courantLog.info("Assignation sévérité "+severiteSujet+" du dossier "+NumeroCardex+" au sujet "+numeroFiche+" ayant la sévérité "+severiteSujet);
						try{
							callableStatement2 = connection.prepareCall("begin CARDEX_DOC.SP_E_SO_SEVERITE_CASINO (?,?,?); end;");
							OracleDAOUtils.setLong(callableStatement2,1, cle);
							OracleDAOUtils.setLong(callableStatement2,2, site); 
							OracleDAOUtils.setLong(callableStatement2,3, severite); 
							callableStatement2.execute();
				         }
				         catch (SQLException se) {
				             throw new DAOException(se);
				         }
				         finally {
				 			if(callableStatement2 != null) {
				 				try {
				 						callableStatement2.close();
				 			    } catch (java.sql.SQLException e) {
				                     throw new DAOException(e);
				 		        }
				 			}
				         }
					 }//while
				   } catch (SQLException se) {
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
					   } //finally
			   }

	    //Mise-à-jour de la sévérité Autre des sociétés d'invesigation
	  public void severiteSocieteInvestigationAutre(Connection connection) throws DAOException {
		  CallableStatement callableStatement = null;
		  ResultSet resultSet = null;
		  try{
			 callableStatement = connection.prepareCall("begin CARDEX_LIRE_DOC.SP_L_SO_SEVERITE_AUTRE (?); end;");
			 CallableStatement callableStatement2 = null;
			 callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			 callableStatement.execute();
			 resultSet = (ResultSet)callableStatement.getObject(1);
			 //Traitement des données retournées pour mettre à jour la sévérité
			 while(resultSet.next()){
				long cle = resultSet.getLong("L_SO_CLE");
				long site = resultSet.getLong("L_SI_CLE");
				String numeroFiche = resultSet.getString("NumeroFiche");
				long severiteSujet = resultSet.getLong("SEVERITE");
				String NumeroCardex = resultSet.getString("NumeroCardex");
				long severite = resultSet.getLong("SEVERITE_DOSSIER");
				courantLog.info("Assignation sévérité "+severiteSujet+" du dossier "+NumeroCardex+" au sujet "+numeroFiche+" ayant la sévérité "+severiteSujet);
				try{
					callableStatement2 = connection.prepareCall("begin CARDEX_DOC.SP_E_SO_SEVERITE_AUTRE (?,?,?); end;");
					OracleDAOUtils.setLong(callableStatement2,1, cle);
					OracleDAOUtils.setLong(callableStatement2,2, site); 
					OracleDAOUtils.setLong(callableStatement2,3, severite); 
					callableStatement2.execute();
		         }
		         catch (SQLException se) {
		             throw new DAOException(se);
		         }
		         finally {
		 			if(callableStatement2 != null) {
		 				try {
		 						callableStatement2.close();
		 			    } catch (java.sql.SQLException e) {
		                     throw new DAOException(e);
		 		        }
		 			}
		         }
			 }//while
		   } catch (SQLException se) {
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
			   } //finally
	   }
	  
	public void assignerCourantLog(Logger courantLog) {
		this.courantLog = courantLog;
	}
	
  	public void assignerLocalCourantLog() {
		this.courantLog = log;
	}
  	
}