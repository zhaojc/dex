package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.CriteresRechercheVehicule;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Particularites;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.vo.VehiculeVO;
import com.lotoquebec.cardex.integration.dao.sql.recherche.VehiculeCompletSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.VehiculeCountSQL;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.ValueListHandler;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.UnEnregistrementPresent;
import com.lotoquebec.cardexCommun.user.CardexUser;

/**
 * Liste des appels � la base de donn�es pour tout ce qui concerne
 * les v�hicules.  Impl�mente l'interface VehiculeDAO.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.18 $, $Date: 2002/04/25 15:42:36 $
 * @see com.lotoquebec.cardex.integration.VehiculeDAO
 */
public class VehiculeDAO {

	private final static Logger      log =
        LoggerFactory.getLogger(VehiculeDAO.class);

/**
 * �criture d'un v�hicule, appel� par la m�thode "insert" ou "update".
 * Selon le param�tre "action" il peut s'agir d'une insertion ("I")
 * ou d'une mise � jour ("U").
 * Proc�dure appel�e : CARDEX_DOC.SP_E_VE_VEHICULE
 * Date de cr�ation : (2002-02-21)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param vehicule Vehicule : v�hicule saisi � l'�cran
 * @param action  java.lang.String : U ou I
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Sujet
 */
   private static Vehicule editVehicule(CardexAuthenticationSubject subject, Vehicule vehicule, String action) throws DAOException {
      Connection connection = DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
      try {
          callableStatement =
              connection.prepareCall("begin CARDEX_DOC.SP_E_VE_VEHICULE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
              callableStatement.setString(1,action);
              callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
              callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
              OracleDAOUtils.setLong(callableStatement,2, vehicule.getCle());
              OracleDAOUtils.setLong(callableStatement,3, vehicule.getSite());
              OracleDAOUtils.setLong(callableStatement,4, vehicule.getModele()); //i_md_cle
              OracleDAOUtils.setLong(callableStatement,5,vehicule.getConfidentialite()); //i_cc_cle
              callableStatement.setString(6,vehicule.getImmatriculation().trim().toUpperCase());  //v_ve_immatriculation
              callableStatement.setString(7,vehicule.getProvince()); //v_ve_province
              callableStatement.setString(8,vehicule.getAnnee()); //c_ve_annee
              callableStatement.setString(9, vehicule.getVignette().trim().toUpperCase()); //v_ve_vignette
              callableStatement.setString(10, vehicule.getNumeroSerie().trim().toUpperCase()); //v_ve_numero_serie
              callableStatement.setString(11, vehicule.getAssurance().trim().toUpperCase()); //v_ve_assureur
              callableStatement.setString(12, vehicule.getPolice().trim().toUpperCase()); //v_ve_police
              callableStatement.setTimestamp(13,(Timestamp)(vehicule.getDateExpirationVignette())); //d_ve_expiration_vignette
              callableStatement.setTimestamp(14,(Timestamp)(vehicule.getDateExpirationAssurance())); //d_ve_expiration_police
              callableStatement.setString(15,vehicule.getMotPasse()); //v_ve_mot_passe
              callableStatement.setString(16,vehicule.getCommentaire()); //v_ve_commentaire
              OracleDAOUtils.setLong(callableStatement,17,vehicule.getCleProvince()); //l_pr_cle
              callableStatement.execute();
              Vehicule newVehicule = new VehiculeVO();
              newVehicule.setCle(callableStatement.getLong(2));
              newVehicule.setSite(callableStatement.getLong(3));
              return newVehicule;
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
 * Appel de la m�thode editVehicule pour la cr�ation d'un v�hicule.
 * Date de cr�ation : (2002-02-21)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param vehicule Vehicule : v�hicule saisi � l'�cran
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Sujet
 */
    public static Vehicule insert(CardexAuthenticationSubject subject, Vehicule vehicule) throws DAOException {
      return editVehicule(subject, vehicule,"I");
    }

/**
 * Appel de la m�thode editVehicule pour la mise � jour d'un v�hicule.
 * Date de cr�ation : (2002-02-21)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param vehicule Vehicule : v�hicule saisi � l'�cran
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 */
    public static void update(CardexAuthenticationSubject subject, Vehicule vehicule) throws DAOException {
      editVehicule(subject, vehicule, "U");
    }

/**
 * Recherche des v�hicules cr��s dans les deux derniers jours lors de l'affichage
 * de l'�cran de recherche de v�hicules
 * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L_VE_VEHICULE
 * Date de cr�ation : (2002-02-21)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria CriteresRechercheVehicule : crit�res de recherche
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ValueListIterator : une liste de v�hicules retourn�s par la recherche.
 */

    public static List<Vehicule> selectDefault(CardexAuthenticationSubject subject,CriteresRechercheVehicule criteria) throws DAOException{
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      ArrayList results = new ArrayList();
      try {
         callableStatement = connection.prepareCall("begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L_VE_VEHICULE (?); end;");
         callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(1);
         ValueListHandler  listIterator = new ValueListHandler();
         //Traitement des donn�es retourn�es
         while(resultSet.next()){
            results.add(traitementResultSet(resultSet));
         }
         return results;
       } catch (SQLException se) {
         throw new DAOException(se);
       } finally {
         try{
 			if(resultSet != null) {
				resultSet.close();
	        }
			if(callableStatement != null) {
				callableStatement.close();
			}
 		    if (connection != null) {
		         if(!connection.getAutoCommit())
		         {
		            connection.rollback();
		         }
	           	   connection.close();
 		    }
         }catch (SQLException se) {
          throw new DAOException(se);
         }
       } //finally
    }

/**
 * Recherche directe d'un dossier par sa cl� unique avec �criture d'un audit.
 * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L2_VE_VEHICULE
 * Date de cr�ation : (2002-02-21)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria Vehicule : Vehicule � rechercher
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Vehicule : donn�es du v�hicule trouv�.
 */
    public static Vehicule find(CardexAuthenticationSubject subject, Vehicule criteria) throws DAOException{
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      Vehicule vehicule = new VehiculeVO();
      try {
         callableStatement = connection.prepareCall("begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L2_VE_VEHICULE (?,?,?); end;");
         callableStatement.setLong(1,criteria.getCle());
         callableStatement.setLong(2,criteria.getSite());
         callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(3);
         //Traitement du v�hicule retourn� (s'il y a lieu)
         if (resultSet.next()){
            vehicule = traitementResultSet(resultSet);
         }
         return vehicule;
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
	 * Recherche directe d'un v�hicule par sa cl� unique dans la table des audits de changements.
	 * Sert � retrouver les informations en date de la liaison d'un v�hicule � un dossier.
	 * Si des informations sont retrouv�es, elles sont imprim�es sur le dossier au lieu des informations actuelles
	 * Proc�dure appel�e : CARDEX_AUDIT.SP_L_FIND_AUDIT_VEHICULE
	 * Date de cr�ation : (2012-01-07)
	 * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
     * @param criteria Vehicule : Vehicule � rechercher
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e est
     * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
     * "stored procedure".
     * @return Vehicule : donn�es du v�hicule trouv�.
     */
        public static Vehicule findAudit(CardexAuthenticationSubject subject, Vehicule criteria) throws DAOException{
          Connection connection = DAOConnection.getInstance().getConnection(subject);
    	  CallableStatement callableStatement = null;
    	  ResultSet resultSet = null;
          Vehicule vehicule = new VehiculeVO();
          try {
             callableStatement = connection.prepareCall("begin CARDEX_AUDIT.SP_L_FIND_AUDIT_VEHICULE (?,?,?,?); end;");
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
             //Traitement du v�hicule retourn� (s'il y a lieu)
             if (resultSet.next()){
                vehicule = traitementResultSetFindAudit(resultSet);
             }
             return vehicule;
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
 * Recherche directe d'un dossier par sa cl� unique sans �criture d'un audit.
 * Cette proc�dure est n�cessaire en raison du mode web de l'application.  Dans ce 
 * mode, chaque fois qu'on revient au dossier (par exemple, apr�s une nouvelle narration),
 * une relecture du dossier est effectu�e, ce qui g�n�re une entr�e inutile dans la
 * table AC_ACCES et fausse les donn�es d'historique du dossier.
 * Proc�dure appel�e : Cardex_Lire_Doc.SP_L_VE_VEHICULE
 * Date de cr�ation : (2002-02-21)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria Vehicule : Vehicule � rechercher
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Vehicule : donn�es du v�hicule trouv�.
 */
    public static Vehicule findAcces(CardexAuthenticationSubject subject, Vehicule criteria) throws DAOException{
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      Vehicule vehicule = new VehiculeVO();
      try {
         callableStatement = connection.prepareCall("begin Cardex_Lire_Doc.SP_L_VE_VEHICULE (?,?,?); end;");
         callableStatement.setLong(1,criteria.getCle());
         callableStatement.setLong(2,criteria.getSite());
         callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(3);
         //Traitement du v�hicule retourn� (s'il y a lieu)
         if (resultSet.next()){
            vehicule = traitementResultSet(resultSet);
         }
         return vehicule;
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
 * Recherche directe d'un v�hicule par sa cl� unique et prot�g� par mot de passe.
 * Proc�dure appel�e : CARDEX_LIRE_DOC.SP_L3_VE_VEHICULE
 * Date de cr�ation : (2002-02-21)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria Vehicule : v�hicule � rechercher
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Vehicule : donn�es du v�hicule trouv�.
 */
    public static Vehicule findMotPasse(CardexAuthenticationSubject subject, Vehicule criteria) throws DAOException{
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
      Vehicule vehicule = new VehiculeVO();
      try {
         callableStatement = connection.prepareCall("begin SP_L3_VE_VEHICULE (?,?,?,?); end;");
         callableStatement.setLong(1,criteria.getCle());
         callableStatement.setLong(2,criteria.getSite());
         callableStatement.setString(3,criteria.getMotPasse());
         callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(4);
         //Traitement du sujet retourn� (s'il y a lieu)
         if (resultSet.next()){
            vehicule = traitementResultSet(resultSet);
         }
         return vehicule;
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
 * Routine pour traiter les ResultSet retourn�s par les recherches de v�hicules.
 * Date de cr�ation : (2002-02-21)
 * @author Fran�ois Gu�rin
 * @param resultSet  ResultSet : donn�es retourn�es par une recherche
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ArrayList : liste des v�hicules trait�s.
 */
    public static RowCallbackHandler constuireRowCallBackHandler(final List<Vehicule> listVehicule){
 	   return new RowCallbackHandler(){
 		   public void processRow(ResultSet resultSet) throws SQLException {
 			   Vehicule vehicule = traitementResultSet(resultSet);
 			   listVehicule.add(vehicule); 					
 		   }
 	   };
    }    
    
    private static Vehicule traitementResultSet(ResultSet resultSet) throws SQLException  {
          VehiculeVO vehicule = new VehiculeVO();
          vehicule.setCle(resultSet.getLong("L_VE_CLE"));
          vehicule.setSite(resultSet.getLong("L_SI_CLE"));
          vehicule.setEntite(resultSet.getLong("I_EN_CLE"));
          vehicule.setModele(resultSet.getLong("I_MD_CLE"));
          vehicule.setConfidentialite(resultSet.getLong("I_CC_CLE"));
          vehicule.setImmatriculation(OracleDAOUtils.getString(resultSet,"V_VE_IMMATRICULATION"));
          vehicule.setProvince(OracleDAOUtils.getString(resultSet,"V_VE_PROVINCE"));
          vehicule.setAnnee(OracleDAOUtils.getString(resultSet,"C_VE_ANNEE"));
          vehicule.setVignette(OracleDAOUtils.getString(resultSet,"V_VE_VIGNETTE"));
          vehicule.setNumeroSerie(OracleDAOUtils.getString(resultSet,"V_VE_NUMERO_SERIE"));
          vehicule.setAssurance(OracleDAOUtils.getString(resultSet,"V_VE_ASSUREUR"));
          vehicule.setPolice(OracleDAOUtils.getString(resultSet,"V_VE_POLICE"));
          vehicule.setDateExpirationVignette(resultSet.getTimestamp("D_VE_EXPIRATION_VIGNETTE"));
          vehicule.setDateExpirationAssurance(resultSet.getTimestamp("D_VE_EXPIRATION_POLICE"));
          vehicule.setCreateur(OracleDAOUtils.getString(resultSet,"V_VE_CREE_PAR"));
          vehicule.setDateCreation(resultSet.getTimestamp("D_VE_DATE_CREATION"));
          vehicule.setMotPasse(OracleDAOUtils.getString(resultSet,"V_VE_MOT_PASSE"));
          vehicule.setMarque(resultSet.getLong("I_MA_CLE"));
          vehicule.setCommentaire(OracleDAOUtils.getString(resultSet,"V_VE_COMMENTAIRE"));
          vehicule.setCleProvince(resultSet.getLong("L_PR_CLE"));
          log.debug("   [VEHICULE id='"+vehicule.getCle()+"']");
         return vehicule;
    }

    private static Vehicule traitementResultSetFindAudit(ResultSet resultSet) throws SQLException  {
        VehiculeVO vehicule = new VehiculeVO();
        vehicule.setCle(resultSet.getLong("L_VE_CLE"));
        vehicule.setSite(resultSet.getLong("L_SI_CLE"));
        vehicule.setEntite(resultSet.getLong("I_EN_CLE"));
        vehicule.setModele(resultSet.getLong("I_MD_CLE"));
        vehicule.setConfidentialite(resultSet.getLong("I_CC_CLE"));
        vehicule.setImmatriculation(OracleDAOUtils.getString(resultSet,"V_VE_IMMATRICULATION"));
        vehicule.setProvince(OracleDAOUtils.getString(resultSet,"V_VE_PROVINCE"));
        vehicule.setAnnee(OracleDAOUtils.getString(resultSet,"C_VE_ANNEE"));
        vehicule.setVignette(OracleDAOUtils.getString(resultSet,"V_VE_VIGNETTE"));
        vehicule.setNumeroSerie(OracleDAOUtils.getString(resultSet,"V_VE_NUMERO_SERIE"));
        vehicule.setAssurance(OracleDAOUtils.getString(resultSet,"V_VE_ASSUREUR"));
        vehicule.setPolice(OracleDAOUtils.getString(resultSet,"V_VE_POLICE"));
        vehicule.setDateExpirationVignette(resultSet.getTimestamp("D_VE_EXPIRATION_VIGNETTE"));
        vehicule.setDateExpirationAssurance(resultSet.getTimestamp("D_VE_EXPIRATION_POLICE"));
        vehicule.setCreateur(OracleDAOUtils.getString(resultSet,"V_VE_CREE_PAR"));
        vehicule.setDateCreation(resultSet.getTimestamp("D_VE_DATE_CREATION"));
        vehicule.setMarque(resultSet.getLong("I_MA_CLE"));
        vehicule.setCommentaire(OracleDAOUtils.getString(resultSet,"V_VE_COMMENTAIRE"));
        vehicule.setCleProvince(resultSet.getLong("L_PR_CLE"));
        log.debug("   [VEHICULE id='"+vehicule.getCle()+"']");
       return vehicule;
  }

/**
 * Routine pour traiter les ResultSet retourn�s par les recherches de v�hicules.
 * Date de cr�ation : (2002-02-21)
 * @author Fran�ois Gu�rin
 * @param resultSet  ResultSet : donn�es retourn�es par une recherche
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ArrayList : liste des v�hicules trait�s.
 */
  private static Vehicule traitementResultSetLink(ResultSet resultSet) throws DAOException {
       try {
              VehiculeVO vehicule = new VehiculeVO();
              vehicule.setCle(resultSet.getLong("L_VE_CLE"));
              vehicule.setSite(resultSet.getLong("L_SI_CLE"));
              vehicule.setModele(resultSet.getLong("I_MD_CLE"));
              vehicule.setConfidentialite(resultSet.getLong("I_CC_CLE"));
              vehicule.setImmatriculation(OracleDAOUtils.getString(resultSet,"V_VE_IMMATRICULATION"));
              vehicule.setProvince(OracleDAOUtils.getString(resultSet,"V_VE_PROVINCE"));
              vehicule.setAnnee(OracleDAOUtils.getString(resultSet,"C_VE_ANNEE"));
              vehicule.setVignette(OracleDAOUtils.getString(resultSet,"V_VE_VIGNETTE"));
              vehicule.setNumeroSerie(OracleDAOUtils.getString(resultSet,"V_VE_NUMERO_SERIE"));
              vehicule.setAssurance(OracleDAOUtils.getString(resultSet,"V_VE_ASSUREUR"));
              vehicule.setPolice(OracleDAOUtils.getString(resultSet,"V_VE_POLICE"));
              vehicule.setDateExpirationVignette(resultSet.getTimestamp("D_VE_EXPIRATION_VIGNETTE"));
              vehicule.setDateExpirationAssurance(resultSet.getTimestamp("D_VE_EXPIRATION_POLICE"));
              vehicule.setCreateur(OracleDAOUtils.getString(resultSet,"V_VE_CREE_PAR"));
              vehicule.setDateCreation(resultSet.getTimestamp("D_VE_DATE_CREATION"));
              vehicule.setMotPasse(OracleDAOUtils.getString(resultSet,"V_VE_MOT_PASSE"));
              vehicule.setMarque(resultSet.getLong("I_MA_CLE"));
              vehicule.setLien(resultSet.getLong("L_LDD_CLE"));
              vehicule.setLienSite(resultSet.getLong("L_LDD_SI_CLE"));
			  vehicule.setLienCreateur(OracleDAOUtils.getString(resultSet,"V_LDD_CREE_PAR"));
			  vehicule.setLienDateCreation(resultSet.getTimestamp("D_LDD_DATE_CREATION"));
              vehicule.setRole(resultSet.getLong("I_RO_CLE"));
              vehicule.setCommentaire(OracleDAOUtils.getString(resultSet,"V_VE_COMMENTAIRE"));
              vehicule.setCleProvince(resultSet.getLong("L_PR_CLE"));
              log.debug("   [VEHICULE id='"+vehicule.getCle()+"']");
         return vehicule;
      } catch (SQLException se) {
          throw new DAOException(se);
      }
    }

  /**
   * Routine pour traiter les ResultSet retourn�s par les recherches d'audits de v�hicules.
   * Date de cr�ation : (2002-02-21)
   * @author Fran�ois Gu�rin
   * @param resultSet  ResultSet : donn�es retourn�es par une recherche
   * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
   * rupture de connexion avec la base de donn�es, ou que la table demand�e est
   * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
   * "stored procedure".
   * @return ArrayList : liste des v�hicules trait�s.
   */
    private static ArrayList traitementResultSetAudit(ResultSet resultSet) throws DAOException {
         ArrayList results = new ArrayList();
         try {
             while (resultSet.next()){
            	VehiculeVO vehicule = new VehiculeVO();
                vehicule.setCle(resultSet.getLong("L_VE_CLE"));
                vehicule.setSite(resultSet.getLong("L_SI_CLE"));
                vehicule.setEntite(resultSet.getLong("I_EN_CLE"));
                vehicule.setModele(resultSet.getLong("I_MD_CLE"));
                vehicule.setConfidentialite(resultSet.getLong("I_CC_CLE"));
                vehicule.setImmatriculation(OracleDAOUtils.getString(resultSet,"V_VE_IMMATRICULATION"));
                vehicule.setProvince(OracleDAOUtils.getString(resultSet,"V_VE_PROVINCE"));
                vehicule.setAnnee(OracleDAOUtils.getString(resultSet,"C_VE_ANNEE"));
                vehicule.setVignette(OracleDAOUtils.getString(resultSet,"V_VE_VIGNETTE"));
                vehicule.setNumeroSerie(OracleDAOUtils.getString(resultSet,"V_VE_NUMERO_SERIE"));
                vehicule.setAssurance(OracleDAOUtils.getString(resultSet,"V_VE_ASSUREUR"));
                vehicule.setPolice(OracleDAOUtils.getString(resultSet,"V_VE_POLICE"));
                vehicule.setDateExpirationVignette(resultSet.getTimestamp("D_VE_EXPIRATION_VIGNETTE"));
                vehicule.setDateExpirationAssurance(resultSet.getTimestamp("D_VE_EXPIRATION_POLICE"));
                vehicule.setCreateur(OracleDAOUtils.getString(resultSet,"V_VE_CREE_PAR"));
                vehicule.setDateCreation(resultSet.getTimestamp("D_VE_DATE_CREATION"));
                vehicule.setMarque(resultSet.getLong("I_MA_CLE"));
                vehicule.setChangePar(OracleDAOUtils.getString(resultSet,"CHANGE_PAR"));
                vehicule.setDateChangement(resultSet.getTimestamp("D_VE_DATE_CHANGEMENT"));
                vehicule.setCommentaire(OracleDAOUtils.getString(resultSet,"V_VE_COMMENTAIRE"));
                vehicule.setCleProvince(resultSet.getLong("L_PR_CLE"));
                log.debug("   [VEHICULE id='"+vehicule.getCle()+"']");
                results.add(vehicule);
             }
           return results;
        } catch (SQLException se) {
            throw new DAOException(se);
        }
      }

    /**
 * Recherche de v�hicules � l'aide de crit�res de recherche.
 * La proc�dure appel�e autrefois est de type DBMS (SQL dynamique)
 * (SP_L4_ve_vehicule PACKAGE CARDEX_LIRE_DOC).  Avec Java, il ne semble pas possible de r�cup�rer les
 * donn�es retourn�es par ce genre de proc�dure.  C'est pourquoi la requ�te SQL est g�n�r�e plut�t
 * dans le code Java avant d'�tre envoy�e � Oracle.
 * Le resultSet retourn� par les recherches est trait� dans la routine traitementResultSet.
 * Proc�dure appel�e : g�n�r�e ici.
 * Date de cr�ation : (2002-02-12)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria CriteresRechercheVehicule : crit�res de recherche
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ValueListIterator : liste des v�hicules retourn�s par la recherche.
 */
    public static List<Vehicule> select(CardexAuthenticationSubject subject, CriteresRechercheVehicule criteria) throws DAOException {
  		JDBCTemplate template = new JDBCTemplate(subject);
    	List<Vehicule> vehiculeList = new ArrayList<Vehicule>();
    	PreparerSQL preparerSQL = (new VehiculeCompletSQL()).construireSQL(subject, criteria);
    	template.query(preparerSQL, criteria.getSequence(), constuireRowCallBackHandler(vehiculeList));
 	   
    	return vehiculeList;	
    }
    
    public static Integer nombreDeVehiculeRecherche(CardexAuthenticationSubject subject,CriteresRechercheVehicule criteria) throws DAOException {
    	JDBCTemplate template = new JDBCTemplate(subject);
    	PreparerSQL preparerSQL = (new VehiculeCountSQL()).construireSQL(subject, criteria);
  	   
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
     * �criture ou suppression d'un lien (association d'un v�hicule � un autre
     * v�hicule).
     * Proc�dure appel�e : CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER
     * Date de cr�ation : (2002-01-28)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param vehicule Vehicule : V�hicule source.
     * @param addedVehicule Vehicule : V�hicule associ�.
     * @param action String : "I" : ajout d'un lien; "D" : suppression d'un lien.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e est
     * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
     * "stored procedure".
     */
    public static void editLienVehicule(CardexAuthenticationSubject subject, Vehicule vehicule, Vehicule addedVehicule, String action) throws DAOException {
        Connection connection = DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
        try {
            callableStatement =
                connection.prepareCall("begin CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER (?,?,?,?,?,?,?,?,?,?,?); end;");
                callableStatement.setString(1,action); //action
                callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
                callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
                callableStatement.setLong(2, addedVehicule.getLien());
                callableStatement.setLong(3, addedVehicule.getLienSite());
                callableStatement.setLong(4, vehicule.getCle());
                callableStatement.setLong(5, addedVehicule.getCle());
                callableStatement.setLong(6, addedVehicule.getRole());
                callableStatement.setLong(7, addedVehicule.getTypeLien());
                callableStatement.setLong(8, vehicule.getSite());
                callableStatement.setString(9, GlobalConstants.GenreFichier.VEHICULE);
                callableStatement.setLong(10, addedVehicule.getSite());
                callableStatement.setString(11, GlobalConstants.GenreFichier.VEHICULE);
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
     * �criture ou suppression d'un lien (association d'un v�hicule � une
     * soci�t�).
     * Proc�dure appel�e : CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER
     * Date de cr�ation : (2002-01-28)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param vehicule Vehicule : V�hicule source.
     * @param societe Societe : Soci�t� associ�e.
     * @param action String : "I" : ajout d'un lien; "D" : suppression d'un lien.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e est
     * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
     * "stored procedure".
     */
    public static void editLienVehicule(CardexAuthenticationSubject subject, Vehicule vehicule, Societe societe, String action) throws DAOException {
        Connection connection = DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
        try {
            callableStatement =
                connection.prepareCall("begin CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER (?,?,?,?,?,?,?,?,?,?,?); end;");
                callableStatement.setString(1,action); //action
                callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
                callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
                callableStatement.setLong(2, societe.getLien());
                callableStatement.setLong(3, societe.getLienSite());
                callableStatement.setLong(4, vehicule.getCle());
                callableStatement.setLong(5, societe.getCle());
                callableStatement.setLong(6, societe.getRole());
                callableStatement.setLong(7, societe.getTypeLien());
                callableStatement.setLong(8, vehicule.getSite());
                callableStatement.setString(9, GlobalConstants.GenreFichier.VEHICULE);
                callableStatement.setLong(10, societe.getSite());
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


    // M�thode addLien


    public static Narration addLienNarration(CardexAuthenticationSubject subject, Vehicule vehicule, Narration narration) throws DAOException {
        narration.setLien(vehicule.getCle());
        narration.setLienSite(vehicule.getSite());
        return FabriqueCardexDAO.getInstance().getNarrationDAO().insert(subject, narration, GlobalConstants.GenreFichier.VEHICULE);
    }

    public static void addLienDossier(CardexAuthenticationSubject subject, Vehicule vehicule, Dossier dossier) throws DAOException {
    	FabriqueCardexDAO.getInstance().getDossierDAO().editLienDossier(subject, dossier, vehicule, "I");
    }

    public static void addLienVehicule(CardexAuthenticationSubject subject, Vehicule vehicule, Vehicule addedVehicule) throws DAOException {
        editLienVehicule(subject, vehicule, addedVehicule, "I");
    }

    public static void addLienSociete(CardexAuthenticationSubject subject, Vehicule vehicule, Societe societe) throws DAOException {
        editLienVehicule(subject, vehicule, societe, "I");
    }

    public static void addLienSujet(CardexAuthenticationSubject subject, Vehicule vehicule, Sujet sujet) throws DAOException {
    	FabriqueCardexDAO.getInstance().getSujetDAO().editLienSujet(subject, sujet, vehicule, "I");
    }

    public static Photo addLienPhoto(CardexAuthenticationSubject subject, Vehicule vehicule, Photo photo) throws DAOException {
        return FabriqueCardexDAO.getInstance().getPhotoDAO().insert(subject, photo, GlobalConstants.GenreFichier.VEHICULE);
    }


    // M�thode deleteLien


    public static void deleteLienNarration(CardexAuthenticationSubject subject, Vehicule vehicule, Narration narration) throws DAOException {
    	FabriqueCardexDAO.getInstance().getNarrationDAO().delete(subject, narration, GlobalConstants.GenreFichier.VEHICULE);
    }

    public static void deleteLienDossier(CardexAuthenticationSubject subject, Vehicule vehicule, Dossier dossier) throws DAOException {
    	FabriqueCardexDAO.getInstance().getDossierDAO().editLienDossier(subject, dossier, vehicule, "D");
    }

    public static void deleteLienDossier(CardexAuthenticationSubject subject, Sujet sujet, Dossier dossier) throws DAOException {
    	FabriqueCardexDAO.getInstance().getDossierDAO().editLienDossier(subject, dossier, sujet, "D");
    }
    
    /**
     * Suppression de tous les v�hicules plac�s en confidentialit� 8.
     * Cette m�thode est appel�e � partir de la recherche de dossiers.
     * Proc�dure appel�e : SP_EPURATION_VEHICULES_SITE
     * Date de cr�ation : (2002-10-17)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public static void delete(CardexAuthenticationSubject subject)
            throws DAOException {
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
        CallableStatement callableStatement = null;
        CardexUser user = (CardexUser)subject.getUser();

        try {
            callableStatement = connection.prepareCall(
                    "begin SP_EPURATION_VEHICULES_SITE(?); end;");
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

    public static void deleteLienVehicule(CardexAuthenticationSubject subject, Vehicule vehicule, Vehicule addedVehicule) throws DAOException {
        editLienVehicule(subject, vehicule, addedVehicule, "D");
    }

    public static void deleteLienSociete(CardexAuthenticationSubject subject, Vehicule vehicule, Societe societe) throws DAOException {
        editLienVehicule(subject, vehicule, societe, "D");
    }

    public static void deleteLienSujet(CardexAuthenticationSubject subject, Vehicule vehicule, Sujet sujet) throws DAOException {
    	FabriqueCardexDAO.getInstance().getSujetDAO().editLienSujet(subject, sujet, vehicule, "D");
    }

    public static void deleteLienPhoto(CardexAuthenticationSubject subject, Vehicule vehicule, Photo photo) throws DAOException {
    	FabriqueCardexDAO.getInstance().getPhotoDAO().delete(subject, photo, GlobalConstants.GenreFichier.VEHICULE);
    }

    public static void deleteLienParticularites(CardexAuthenticationSubject subject, Vehicule vehicule, Particularites particularites) throws DAOException {
        //ParticulariteDAO particulariteDAO = DAOFactory.getInstance().getParticulariteDAO();
        //return particulariteDAO.delete(subject, particularite, GlobalConstants.GenreFichier.VEHICULE);
    }


    // M�thode updateLien


    public static void updateLienNarration(CardexAuthenticationSubject subject, Vehicule vehicule, Narration narration) throws DAOException {
    	FabriqueCardexDAO.getInstance().getNarrationDAO().update(subject, narration, GlobalConstants.GenreFichier.VEHICULE);
    }

    public static void updateLienDossier(CardexAuthenticationSubject subject, Vehicule vehicule, Dossier dossier) throws DAOException {
    	FabriqueCardexDAO.getInstance().getDossierDAO().editLienDossier(subject, dossier, vehicule, "U");
    }

    public static void updateLienDossier(CardexAuthenticationSubject subject, Sujet sujet, Dossier dossier) throws DAOException {
    	FabriqueCardexDAO.getInstance().getDossierDAO().editLienDossier(subject, dossier, sujet, "U");
    }

    public static void updateLienVehicule(CardexAuthenticationSubject subject, Vehicule vehicule, Vehicule addedVehicule) throws DAOException {
        editLienVehicule(subject, vehicule, addedVehicule, "U");
    }

    public static void updateLienSociete(CardexAuthenticationSubject subject, Vehicule vehicule, Societe societe) throws DAOException {
        editLienVehicule(subject, vehicule, societe, "U");
    }

    public static void updateLienSujet(CardexAuthenticationSubject subject, Vehicule vehicule, Sujet sujet) throws DAOException {
    	FabriqueCardexDAO.getInstance().getSujetDAO().editLienSujet(subject, sujet, vehicule, "U");
    }

    public static void updateLienPhoto(CardexAuthenticationSubject subject, Vehicule vehicule, Photo photo) throws DAOException {
    	FabriqueCardexDAO.getInstance().getPhotoDAO().update(subject, photo, GlobalConstants.GenreFichier.VEHICULE);
    }

    public static void updateLienParticularite(CardexAuthenticationSubject subject, Vehicule vehicule, Particularites particularites) throws DAOException {
    	FabriqueCardexDAO.getInstance().getParticulariteDAO().update(subject, particularites, GlobalConstants.GenreFichier.VEHICULE);
    }

    public static void approuveLienNarration(CardexAuthenticationSubject subject, Vehicule vehicule, Narration narration) throws DAOException {
    	FabriqueCardexDAO.getInstance().getNarrationDAO().approbation(subject, narration, GlobalConstants.GenreFichier.VEHICULE);
    }

    /**
     * Lecture des dossiers associ�s � un v�hicule
     * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L5_DO_DOSSIER
     * Date de cr�ation : (2002-02-21)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
     * @param dossier Dossier : dossier de base
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e est
     * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
     * "stored procedure".
     * @return Collection : liste des dossiers associ�s
     */
    public static Collection findLiensDossier(CardexAuthenticationSubject subject, Vehicule vehicule) throws DAOException{
        return FabriqueCardexDAO.getInstance().getDossierDAO().findLiensDossier(subject, vehicule.getCle(),
                vehicule.getSite(), GlobalConstants.GenreFichier.VEHICULE);
    }

    /**
     * Lecture des narrations associ�es � un v�hicule
     * Proc�dure appel�e : SPW_L_CO_COMMENTAIRE2
     * Date de cr�ation : (2002-02-21)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
     * @param narration Narration : narrations associ�es
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e est
     * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
     * "stored procedure".
     * @return Collection : liste des narrations associ�es
     */
    public static Collection findLiensNarration(CardexAuthenticationSubject subject, Vehicule vehicule) throws DAOException{
      return FabriqueCardexDAO.getInstance().getNarrationDAO().findLiensNarration(subject, vehicule.getCle(), vehicule.getSite(), GlobalConstants.GenreFichier.VEHICULE);
    }

    /**
     * Retourne les dossiers associ�s � un certain dossier.
     * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L5_VE_VEHICULE
     * Date de cr�ation : (2002-01-28)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param cle : cle de l'object avec lequel on cherche l'association.
     * @param site : site de l'object avec lequel on cherche l'association.
     * @param genreFichier : genre de l'object avec lequel on cherche
     * l'association.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des dossiers associ�s.
     */
    public static Collection findLiensVehicule(CardexAuthenticationSubject subject,
            long cle, long site, String genreFichier) throws DAOException {
        log.debug("findLiensVehicule()");
        Connection connection =
        	DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

        try {
            callableStatement = connection.prepareCall(
                    "begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L5_VE_VEHICULE (?,?,?,?); end;"); // cette proc�dure contien un merge cart�sien
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

    public static Collection findLiensSociete(CardexAuthenticationSubject subject,
            Vehicule vehicule) throws DAOException {
        return FabriqueCardexDAO.getInstance().getSocieteDAO().findLiensSociete(subject, vehicule.getCle(),
                vehicule.getSite(), GlobalConstants.GenreFichier.VEHICULE);
    }

    public static Collection findLiensSujet(CardexAuthenticationSubject subject,
            Vehicule vehicule) throws DAOException {
        return FabriqueCardexDAO.getInstance().getSujetDAO().findLiensSujet(subject, vehicule.getCle(),
                vehicule.getSite(), GlobalConstants.GenreFichier.VEHICULE);
    }

    public static Collection findLiensPhoto(CardexAuthenticationSubject subject,
            Vehicule vehicule) throws DAOException {
        return FabriqueCardexDAO.getInstance().getPhotoDAO().findLiensPhoto(subject, vehicule.getCle(),
                vehicule.getSite(),vehicule.getLienDateCreation(),GlobalConstants.GenreFichier.VEHICULE);
    }

    public static Particularites findLiensParticularite(CardexAuthenticationSubject subject, Vehicule vehicule) throws DAOException{
      return FabriqueCardexDAO.getInstance().getParticulariteDAO().findLiensParticularite(subject,vehicule);
    }

    /**
     * Recherche de l'audit des changements d'un v�hicule.
     * 
     * Proc�dure appel�e : CARDEX_AUDIT.SP_L_AUDIT_VEHICULE
     * Date de cr�ation : (2011-03-08)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param criteria Vehicule : V�hicule � rechercher.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Dossier : Instance de dossier associ�e.
     */
    public static List audit(CardexAuthenticationSubject subject,Vehicule criteria)
            throws DAOException {
        Connection connection = null;            
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		List  resultats = new ArrayList();
		try {
        	connection = DAOConnection.getInstance().getConnection(subject);
            callableStatement = connection.prepareCall(
                    "begin CARDEX_AUDIT.SP_L_AUDIT_VEHICULE(?,?,?); end;");
        	callableStatement.setLong(1,criteria.getCle());
            callableStatement.setLong(2,criteria.getSite());
            callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
            resultSet = (ResultSet)callableStatement.getObject(3);
            //Traitement du r�sultat retourn�
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
     * Mise � jour d'un lien (association d'un v�hicule � un sujet, un dossier ou � une soci�t�).
     * Utilisant la stored procedure SP_E_LDD_LIEN_DOSSIER PACKAGE CARDEX_LIEN.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @param societe Societe : Soci�t� associ�e.
     * @throws DAOException lanc�e� lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     */
    public void updateLien(CardexAuthenticationSubject subject,
            Vehicule vehicule) throws DAOException {
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
                callableStatement.setLong(2, vehicule.getLien());
                callableStatement.setLong(3, vehicule.getLienSite());
                callableStatement.setLong(4, vehicule.getCle());
                callableStatement.setLong(5, 0);
                callableStatement.setLong(6, vehicule.getRole());
                callableStatement.setLong(7, vehicule.getTypeLien());
                callableStatement.setLong(8, vehicule.getSite());
                callableStatement.setString(9,
                        GlobalConstants.GenreFichier.DOSSIER); //valeur non trait�e par la proc�dure
                callableStatement.setLong(10, 0);
                callableStatement.setString(11,
                        GlobalConstants.GenreFichier.VEHICULE);
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
     * Recherche directe d'un dossier avec le num�ro de dossier.
     * Appel� � partir de la recherche directe dans le menu principal.
     * Proc�dure appel�e : CARDEX_LIRE_DOC.SP_L_VE_VEHICULE_DIRECT
     * Date de cr�ation : (2013-08-07)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
     * @param criteria Sujet : sujet � rechercher
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e est
     * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
     * "stored procedure".
     * @return dossier : donn�es du dossier trouv�.
     */
    	public Vehicule rechercheDirecte(CardexAuthenticationSubject subject, Vehicule criteria) throws DAOException{
    	  Connection connection = DAOConnection.getInstance().getConnection(subject);
    	  CallableStatement callableStatement = null;
    	  ResultSet resultSet = null;
    	  Vehicule vehicule = new VehiculeVO();
    	  try {
    		 callableStatement = connection.prepareCall("begin CARDEX_LIRE_DOC.SP_L_VE_VEHICULE_DIRECT (?,?); end;");
    		 callableStatement.setString(1,criteria.getImmatriculation());
    		 callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
    		 callableStatement.execute();
    		 resultSet = (ResultSet)callableStatement.getObject(2);
    		 //Traitement du v�hicule retourn� (s'il y a lieu)
    		 if (resultSet.next()){
    			 vehicule = traitementResultSet(resultSet);
    		 }
    		 return vehicule;
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

//Ajout d'une entr�e dans la table des acc�s
public void ajoutAcces(CardexAuthenticationSubject subject, Vehicule vehicule) throws DAOException {
	FabriqueCardexDAO.getInstance().getAccesDAO().ajoutAcces(subject, vehicule.getCle(), vehicule.getSite(), GlobalConstants.GenreFichier.VEHICULE);
}

}
