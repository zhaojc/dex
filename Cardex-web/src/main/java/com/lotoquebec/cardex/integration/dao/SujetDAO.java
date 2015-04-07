package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoQuebec.correcteurAdresse.AdresseSortie;
import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Caracteristiques;
import com.lotoquebec.cardex.business.CriteresRechercheAdresses;
import com.lotoquebec.cardex.business.CriteresRechercheSujet;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.delegate.AdresseBusinessDelegate;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.integration.dao.sql.recherche.AdresseSujetCompletSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.AdresseSujetCountSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.AdresseSujetSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.SujetCompletSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.SujetCountSQL;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.ValueListHandler;
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
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringHelper;
import com.lotoquebec.cardexCommun.util.StringUtils;
import com.lotoquebec.cardexCommun.util.ValiderNAS;
/**
 * Liste des appels � la base de donn�es pour tout ce qui concerne
 * les sujets.  Impl�mente l'interface SujetDAO.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.30 $, $Date: 2002/04/25 15:42:36 $
 * @see com.lotoquebec.cardex.integration.SujetDAO
 */

public class SujetDAO {

	private final Logger      log =
        LoggerFactory.getLogger(SujetDAO.class);
	
	private Logger courantLog = LoggerFactory.getLogger(SujetDAO.class);
	
/**
 * �criture d'un sujet, appel� par la m�thode "insert" ou "update".
 * Selon le param�tre "action" il peut s'agir d'une insertion ("I")
 * ou d'une mise � jour ("U").
 * Proc�dure appel�e : CARDEX_DOC.SP_E_SU_SUJET
 * Date de cr�ation : (2002-02-11)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param sujet Sujet : sujet saisi � l'�cran
 * @param action  java.lang.String : U ou I
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Sujet
 */
   private Sujet editSujet(Sujet sujet, String action, Connection connection) throws DAOException {

	   CallableStatement callableStatement = null;
		  try {
			  callableStatement =
				  connection.prepareCall("begin CARDEX_DOC.SP_E_SU_SUJET (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
				  callableStatement.setString(1,action); // action IN CHAR,
				  callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
				  callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
				  OracleDAOUtils.setLong(callableStatement,2, sujet.getCle()); // NEW_L_SU_CLE IN OUT SU_SUJET.L_SU_CLE%TYPE,
				  OracleDAOUtils.setLong(callableStatement,3, sujet.getSite()); // NEW_L_SI_CLE IN OUT SU_SUJET.L_SI_CLE%TYPE,
				  OracleDAOUtils.setLong(callableStatement,4, sujet.getSexe()); //i_sx_cle
				  OracleDAOUtils.setLong(callableStatement,5,sujet.getStatut()); //i_st_cle
				  OracleDAOUtils.setLong(callableStatement,6,sujet.getEthnie());  //i_nt_cle
				  OracleDAOUtils.setLong(callableStatement,7,sujet.getRace()); //i_ra_cle
				  OracleDAOUtils.setLong(callableStatement,8,sujet.getLangue()); //i_ls_cle
				  callableStatement.setString(9,sujet.getNom().trim());  //v_su_nom
				  callableStatement.setString(10,sujet.getPrenom().trim()); //i_su_prenom
				  callableStatement.setString(11,StringUtils.trim(sujet.getAlias())); //v_su_surnom
				  callableStatement.setTimestamp(12,(Timestamp)(sujet.getDateNaissance())); //d_su_date_naissance
				  OracleDAOUtils.setLong(callableStatement,13, sujet.getTypeAge()); //l_su_type_age
				  callableStatement.setString(14, sujet.getNumeroAssuranceSociale()); //c_su_assurance_sociale
				  callableStatement.setString(15,StringHelper.trimUppercase(sujet.getNumeroPermisConduire())); //v_su_permis_conduire
				  callableStatement.setString(16,"");  //v_su_grandeur_metrique
				  callableStatement.setString(17,"");  //v_su_grandeur_imperial
				  callableStatement.setString(18,""); //v_su_poids_metrique
				  callableStatement.setString(19,"");  //v_su_poids_imperial
				  callableStatement.setString(20,""); //c_su_systeme
				  OracleDAOUtils.setLong(callableStatement,21,sujet.getConfidentialite()); //i_cc_cle
				  callableStatement.setString(22,sujet.getMotPasse()); //v_su_mot_passe
				  callableStatement.setString(23,StringUtils.trim(sujet.getReference1())); //v_su_reference1 : r�f�rence 1
				  callableStatement.setString(24,StringUtils.trim(sujet.getNumeroClientEmploye())); //v_su_reference2
				  callableStatement.registerOutParameter(25, java.sql.Types.VARCHAR);
				  callableStatement.setString(25,StringUtils.upperCase(sujet.getNumeroFiche())); //v_su_reference_3 : num�ro de fiche
				  OracleDAOUtils.setLong(callableStatement,26,sujet.getSeverite()); //i_se_cle
				  callableStatement.setString(27,StringHelper.trimUppercase(sujet.getPasseport())); //v_su_no_passeport
				  callableStatement.setString(28,StringHelper.trimUppercase(sujet.getNumeroAssuranceMaladie())); //v_su_assurance_maladie
				  OracleDAOUtils.setLong(callableStatement,29,sujet.getSeveriteAutres()); //i_se_cle_autres
				  callableStatement.setString(30,OracleDAOUtils.convertirBooleanAString(sujet.isIndicateurRdd())); //B_SU_IND_RDD
				  OracleDAOUtils.setLong(callableStatement,31,sujet.getSeveriteCasino()); //i_se_cle_casino
				  callableStatement.setTimestamp(32,(Timestamp)(sujet.getDateFinEmploi())); //d_su_date_fin_emploi
				  callableStatement.execute();
				  //connection.commit();
				  connection.setAutoCommit(true);
				  Sujet newSujet = new SujetVO();
				  newSujet.setCle(callableStatement.getLong(2));
				  newSujet.setSite(callableStatement.getLong(3));
				  newSujet.setNumeroFiche(callableStatement.getString(25));
				  return newSujet;
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
		  }
   }
   private Sujet editSujet(CardexAuthenticationSubject subject, Sujet sujet, String action) throws DAOException {
	  Connection connection = DAOConnection.getInstance().getConnection(subject);
	  try {
			  return editSujet(sujet, action, connection);
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

/**
 * Appel de la m�thode editSujet pour la cr�ation d'un sujet.
 * Date de cr�ation : (2002-01-28)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param sujet Sujet : sujet saisi � l'�cran
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Sujet
 */
	public Sujet insert(Sujet sujet, Connection connection) throws DAOException {
		return editSujet(sujet, "I", connection);
	}
	public Sujet insert(CardexAuthenticationSubject subject, Sujet sujet) throws DAOException {
	  return editSujet(subject,sujet,"I");
	}

/**
 * Appel de la m�thode editSujet pour la mise � jour d'un sujet.
 * Date de cr�ation : (2002-01-28)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param sujet Sujet : sujet saisi � l'�cran
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 */
	public void update(Sujet sujet, Connection connection) throws DAOException {
	  editSujet(sujet, "U", connection);
	}	
	public void update(CardexAuthenticationSubject subject, Sujet sujet) throws DAOException {
	  editSujet(subject, sujet, "U");
	}

/**
 * Recherche des sujets cr��s dans les deux derniers jours lors de l'affichage
 * de l'�cran de recherche de sujets
 * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L_SU_SUJET
 * Date de cr�ation : (2002-02-12)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria CriteresRechercheSujet : crit�res de recherche
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ValueListIterator : une liste de sujets retourn�s par la recherche.
 */

	public List<Sujet> selectDefault(CardexAuthenticationSubject subject,CriteresRechercheSujet criteria) throws DAOException{
	  Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
	  List<Sujet> results = new ArrayList<Sujet>();
	  try {
		 callableStatement = connection.prepareCall("begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L_SU_SUJET (?); end;");
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
 * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L2_SU_SUJET
 * Date de cr�ation : (2002-02-12)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria Sujet : sujet � rechercher
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Sujet : donn�es du sujet trouv�.
 */
	public Sujet find(CardexAuthenticationSubject subject, Sujet criteria) throws DAOException{
	  Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
	  Sujet sujet = new SujetVO();
	  try {
		 callableStatement = connection.prepareCall("begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L2_SU_SUJET (?,?,?); end;");
		 callableStatement.setLong(1,criteria.getCle());
		 callableStatement.setLong(2,criteria.getSite());
		 callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
		 callableStatement.execute();
		 resultSet = (ResultSet)callableStatement.getObject(3);
		 //Traitement du sujet retourn� (s'il y a lieu)
		 if (resultSet.next()){
			sujet = traitementResultSet(resultSet);
		 }
		 return sujet;
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
	 * Recherche directe d'un sujet par sa cl� unique dans la table des audits de changements.
	 * Sert � retrouver les informations en date de la liaison d'un sujet � un dossier.
	 * Si des informations sont retrouv�es, elles sont imprim�es sur le dossier au lieu des informations actuelles
	 * Proc�dure appel�e : CARDEX_AUDIT.SP_L_FIND_AUDIT_SUJET
	 * Date de cr�ation : (2012-01-07)
	 * @author Fran�ois Gu�rin
	 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
	 * @param criteria Sujet : sujet � rechercher
	 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
	 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
	 * "stored procedure".
	 * @return Sujet : donn�es du sujet trouv�.
	 */
		public Sujet findAudit(CardexAuthenticationSubject subject, Sujet criteria) throws DAOException{
		  Connection connection = DAOConnection.getInstance().getConnection(subject);
		  CallableStatement callableStatement = null;
		  ResultSet resultSet = null;
		  Sujet sujet = new SujetVO();
		  try {
			 callableStatement = connection.prepareCall("begin CARDEX_AUDIT.SP_L_FIND_AUDIT_SUJET (?,?,?,?); end;");
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
			 //Traitement du sujet retourn� (s'il y a lieu)
			 if (resultSet.next()){
				sujet = traitementResultSetFindAudit(resultSet);
			 }
			 return sujet;
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
 * Recherche directe d'un sujet par sa cl� unique avec �criture d'un audit.
 * Proc�dure appel�e : CARDEX_LIRE_DOC.SP_L2_SU_SUJET
 * Date de cr�ation : (2002-02-12)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria Sujet : sujet � rechercher
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Sujet : donn�es du sujet trouv�.
 */
	public Sujet findAcces(CardexAuthenticationSubject subject, Sujet criteria) throws DAOException{
	  Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
	  Sujet sujet = new SujetVO();
	  try {
		 callableStatement = connection.prepareCall("begin CARDEX_LIRE_DOC.SP_L_SU_SUJET (?,?,?); end;");
		 callableStatement.setLong(1,criteria.getCle());
		 callableStatement.setLong(2,criteria.getSite());
		 callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
		 callableStatement.execute();
		 resultSet = (ResultSet)callableStatement.getObject(3);
		 //Traitement du sujet retourn� (s'il y a lieu)
		 if (resultSet.next()){
			sujet = traitementResultSet(resultSet);
		 }
		 return sujet;
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
 * Recherche directe d'un sujet par sa cl� unique et prot�g� par mot de passe.
 * Proc�dure appel�e : CARDEX_LIRE_DOC.SP_L3_SU_SUJET
 * Date de cr�ation : (2002-02-12)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria Sujet : sujet � rechercher
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Sujet : donn�es du sujet trouv�.
 */
	public Sujet findMotPasse(CardexAuthenticationSubject subject, Sujet criteria) throws DAOException{
	  Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
	  Sujet sujet = new SujetVO();
	  try {
		 callableStatement = connection.prepareCall("begin CARDEX_LIRE_DOC.SP_L_SU_SUJET (?,?,?,?); end;");
		 callableStatement.setLong(1,criteria.getCle());
		 callableStatement.setLong(2,criteria.getSite());
		 callableStatement.setString(3,criteria.getMotPasse());
		 callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
		 callableStatement.execute();
		 resultSet = (ResultSet)callableStatement.getObject(4);
		 //Traitement du sujet retourn� (s'il y a lieu)
		 if (resultSet.next()){
			sujet = traitementResultSet(resultSet);
		 }
		 return sujet;
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
 * Routine pour traiter les ResultSet retourn�s par les recherches de sujets.
 * Date de cr�ation : (2002-01-28)
 * @author Fran�ois Gu�rin
 * @param resultSet  ResultSet : donn�es retourn�es par une recherche
 * @throws SQLException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ArrayList : liste des sujets trait�s.
 */
    public RowCallbackHandler constuireRowCallBackHandler(final List<Sujet> listSujet){
  	   return new RowCallbackHandler(){
  		   public void processRow(ResultSet resultSet) throws SQLException {
  			   Sujet sujet = traitementResultSet(resultSet);
  			   listSujet.add(sujet); 					
  		   }
  	   };
     }	
	
  private Sujet traitementResultSet(ResultSet resultSet) throws SQLException {
			  SujetVO sujet = new SujetVO();
			  sujet.setCle(resultSet.getLong("L_SU_CLE"));
			  sujet.setSite(resultSet.getLong("L_SI_CLE"));
			  sujet.setEntite(resultSet.getLong("I_EN_CLE"));
			  sujet.setSexe(resultSet.getLong("I_SX_CLE"));
			  sujet.setStatut(resultSet.getLong("I_ST_CLE"));
			  sujet.setEthnie(resultSet.getLong("I_NT_CLE"));
			  sujet.setRace(resultSet.getLong("I_RA_CLE"));
			  sujet.setLangue(resultSet.getLong("I_LS_CLE"));
			  sujet.setNom(resultSet.getString("V_SU_NOM"));
			  sujet.setPrenom(resultSet.getString("V_SU_PRENOM"));
			  sujet.setAlias(OracleDAOUtils.getString(resultSet,"V_SU_SURNOM"));
			  sujet.setDateNaissance(resultSet.getTimestamp("D_SU_DATE_NAISSANCE"));
			  sujet.setTypeAge(resultSet.getLong("L_SU_TYPE_AGE"));
			  sujet.setNumeroAssuranceSociale(OracleDAOUtils.getString(resultSet,"C_SU_ASSURANCE_SOCIALE"));
			  sujet.setNumeroAssuranceMaladie(OracleDAOUtils.getString(resultSet,"V_SU_ASSURANCE_MALADIE"));
			  sujet.setNumeroPermisConduire(OracleDAOUtils.getString(resultSet,"V_SU_PERMIS_CONDUIRE"));
			  sujet.setConfidentialite(resultSet.getLong("I_CC_CLE"));
			  sujet.setMotPasse(OracleDAOUtils.getString(resultSet,"V_SU_MOT_PASSE"));
			  sujet.setReference1(OracleDAOUtils.getString(resultSet,"V_SU_REFERENCE_1"));
			  sujet.setNumeroClientEmploye(OracleDAOUtils.getString(resultSet,"V_SU_REFERENCE_2"));
			  sujet.setNASCanadien(isNASCanadien(sujet));
			  sujet.setNumeroFiche(OracleDAOUtils.getString(resultSet,"V_SU_REFERENCE_3"));
			  sujet.setSeverite(resultSet.getLong("I_SE_CLE"));
			  sujet.setSeveriteAutres(resultSet.getLong("I_SE_CLE_AUTRES"));
  			  sujet.setSeveriteCasino(resultSet.getLong("I_SE_CLE_CASINO"));
			  sujet.setPasseport(OracleDAOUtils.getString(resultSet,"V_SU_NO_PASSEPORT"));
			  sujet.setCreateur(OracleDAOUtils.getString(resultSet,"V_SU_CREE_PAR"));
			  sujet.setDateCreation(resultSet.getTimestamp("D_SU_DATE_CREATION"));
			  sujet.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SU_IND_RDD")));
			  sujet.setDateFinEmploi(resultSet.getTimestamp("D_SU_DATE_FIN_EMPLOI"));
			  log.debug("   [SUJET id='"+sujet.getNumeroFiche()+"' Nom='"+sujet.getNom()+"']");
		 return sujet;
	}

  private Sujet traitementResultSetFindAudit(ResultSet resultSet) throws SQLException {
	  SujetVO sujet = new SujetVO();
	  sujet.setCle(resultSet.getLong("L_SU_CLE"));
	  sujet.setSite(resultSet.getLong("L_SI_CLE"));
	  sujet.setEntite(resultSet.getLong("I_EN_CLE"));
	  sujet.setSexe(resultSet.getLong("I_SX_CLE"));
	  sujet.setStatut(resultSet.getLong("I_ST_CLE"));
	  sujet.setEthnie(resultSet.getLong("I_NT_CLE"));
	  sujet.setRace(resultSet.getLong("I_RA_CLE"));
	  sujet.setLangue(resultSet.getLong("I_LS_CLE"));
	  sujet.setNom(resultSet.getString("V_SU_NOM"));
	  sujet.setPrenom(resultSet.getString("V_SU_PRENOM"));
	  sujet.setAlias(OracleDAOUtils.getString(resultSet,"V_SU_SURNOM"));
	  sujet.setDateNaissance(resultSet.getTimestamp("D_SU_DATE_NAISSANCE"));
	  sujet.setTypeAge(resultSet.getLong("L_SU_TYPE_AGE"));
	  sujet.setNumeroAssuranceSociale(OracleDAOUtils.getString(resultSet,"C_SU_ASSURANCE_SOCIALE"));
	  sujet.setNumeroAssuranceMaladie(OracleDAOUtils.getString(resultSet,"V_SU_ASSURANCE_MALADIE"));
	  sujet.setNumeroPermisConduire(OracleDAOUtils.getString(resultSet,"V_SU_PERMIS_CONDUIRE"));
	  sujet.setConfidentialite(resultSet.getLong("I_CC_CLE"));
	  sujet.setReference1(OracleDAOUtils.getString(resultSet,"V_SU_REFERENCE_1"));
	  sujet.setNumeroClientEmploye(OracleDAOUtils.getString(resultSet,"V_SU_REFERENCE_2"));
	  sujet.setNASCanadien(isNASCanadien(sujet));
	  sujet.setNumeroFiche(OracleDAOUtils.getString(resultSet,"V_SU_REFERENCE_3"));
	  sujet.setSeverite(resultSet.getLong("I_SE_CLE"));
	  sujet.setSeveriteAutres(resultSet.getLong("I_SE_CLE_AUTRES"));
	  sujet.setSeveriteCasino(resultSet.getLong("I_SE_CLE_CASINO"));
	  sujet.setPasseport(OracleDAOUtils.getString(resultSet,"V_SU_NO_PASSEPORT"));
	  sujet.setCreateur(OracleDAOUtils.getString(resultSet,"V_SU_CREE_PAR"));
	  sujet.setDateCreation(resultSet.getTimestamp("D_SU_DATE_CREATION"));
	  sujet.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SU_IND_RDD")));
	  sujet.setDateFinEmploi(resultSet.getTimestamp("D_SU_DATE_FIN_EMPLOI"));
	  log.debug("   [SUJET id='"+sujet.getNumeroFiche()+"' Nom='"+sujet.getNom()+"']");
	  return sujet;
}

  /**
   * Routine pour traiter les ResultSet retourn�s par l'audit des changements.
   * Date de cr�ation : (2011-03-08)
   * @author Fran�ois Gu�rin
   * @param resultSet  ResultSet : donn�es retourn�es par une recherche
   * @throws SQLException lanc�e lorsqu'une SQLException est re�ue lors d'une
   * rupture de connexion avec la base de donn�es, ou que la table demand�e est
   * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
   * "stored procedure".
   * @return ArrayList : liste des sujets trait�s.
   */
    private ArrayList traitementResultSetAudit(ResultSet resultSet) throws DAOException {
        ArrayList results = new ArrayList();
        try { 
            while (resultSet.next()){
  			  SujetVO sujet = new SujetVO();
  			  sujet.setCle(resultSet.getLong("L_SU_CLE"));
  			  sujet.setSite(resultSet.getLong("L_SI_CLE"));
  			  sujet.setEntite(resultSet.getLong("I_EN_CLE"));
  			  sujet.setSexe(resultSet.getLong("I_SX_CLE"));
  			  sujet.setStatut(resultSet.getLong("I_ST_CLE"));
  			  sujet.setEthnie(resultSet.getLong("I_NT_CLE"));
  			  sujet.setRace(resultSet.getLong("I_RA_CLE"));
  			  sujet.setLangue(resultSet.getLong("I_LS_CLE"));
  			  sujet.setNom(resultSet.getString("V_SU_NOM"));
  			  sujet.setPrenom(OracleDAOUtils.getString(resultSet,"V_SU_PRENOM"));
  			  sujet.setAlias(OracleDAOUtils.getString(resultSet,"V_SU_SURNOM"));
  			  sujet.setDateNaissance(resultSet.getTimestamp("D_SU_DATE_NAISSANCE"));
  			  sujet.setTypeAge(resultSet.getLong("L_SU_TYPE_AGE"));
  			  sujet.setNumeroAssuranceSociale(OracleDAOUtils.getString(resultSet,"C_SU_ASSURANCE_SOCIALE"));
  			  sujet.setNumeroAssuranceMaladie(OracleDAOUtils.getString(resultSet,"V_SU_ASSURANCE_MALADIE"));
  			  sujet.setNumeroPermisConduire(OracleDAOUtils.getString(resultSet,"V_SU_PERMIS_CONDUIRE"));
  			  sujet.setConfidentialite(resultSet.getLong("I_CC_CLE"));
  			  //sujet.setMotPasse(resultSet.getString("V_SU_MOT_PASSE"));
  			  sujet.setReference1(OracleDAOUtils.getString(resultSet,"V_SU_REFERENCE_1"));
  			  sujet.setNumeroClientEmploye(OracleDAOUtils.getString(resultSet,"V_SU_REFERENCE_2"));
  			  sujet.setNASCanadien(isNASCanadien(sujet));
  			  sujet.setNumeroFiche(OracleDAOUtils.getString(resultSet,"V_SU_REFERENCE_3"));
  			  sujet.setSeverite(resultSet.getLong("I_SE_CLE"));
  			  sujet.setSeveriteAutres(resultSet.getLong("I_SE_CLE_AUTRES"));
  			  sujet.setSeveriteCasino(resultSet.getLong("I_SE_CLE_CASINO"));
  			  sujet.setPasseport(OracleDAOUtils.getString(resultSet,"V_SU_NO_PASSEPORT"));
  			  sujet.setCreateur(OracleDAOUtils.getString(resultSet,"V_SU_CREE_PAR"));
  			  sujet.setDateCreation(resultSet.getTimestamp("D_SU_DATE_CREATION"));
  			  sujet.setChangePar(OracleDAOUtils.getString(resultSet,"CHANGE_PAR"));
  			  sujet.setDateChangement(resultSet.getTimestamp("D_SU_DATE_CHANGEMENT"));
			  sujet.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SU_IND_RDD")));
			  sujet.setDateFinEmploi(resultSet.getTimestamp("D_SU_DATE_FIN_EMPLOI"));
		      results.add(sujet);
            }
        }catch (SQLException se) {
	       throw new DAOException(se);
		}
			return results;
  	}

    /**
  		 * Pour le sujet seulement
  		 * @param resultSet
  		 * @return
  		 * @throws SQLException
  		 */
	  private Sujet traitementSujet(ResultSet resultSet) throws SQLException {
		  SujetVO sujet = new SujetVO();
		  sujet.setCle(resultSet.getLong("L_SU_CLE"));
		  sujet.setSite(resultSet.getLong("L_SI_CLE"));
		  sujet.setSexe(resultSet.getLong("I_SX_CLE"));
		  sujet.setStatut(resultSet.getLong("I_ST_CLE"));
		  sujet.setEthnie(resultSet.getLong("I_NT_CLE"));
		  sujet.setRace(resultSet.getLong("I_RA_CLE"));
		  sujet.setLangue(resultSet.getLong("I_LS_CLE"));
		  sujet.setNom(resultSet.getString("V_SU_NOM"));
		  sujet.setPrenom(resultSet.getString("V_SU_PRENOM"));
		  sujet.setAlias(OracleDAOUtils.getString(resultSet,"V_SU_SURNOM"));
		  sujet.setDateNaissance(resultSet.getTimestamp("D_SU_DATE_NAISSANCE"));
		  sujet.setTypeAge(resultSet.getLong("L_SU_TYPE_AGE"));
		  sujet.setNumeroAssuranceSociale(OracleDAOUtils.getString(resultSet,"C_SU_ASSURANCE_SOCIALE"));
		  sujet.setNumeroAssuranceMaladie(OracleDAOUtils.getString(resultSet,"V_SU_ASSURANCE_MALADIE"));
		  sujet.setNumeroPermisConduire(OracleDAOUtils.getString(resultSet,"V_SU_PERMIS_CONDUIRE"));
		  sujet.setConfidentialite(resultSet.getLong("I_CC_CLE"));
		  sujet.setMotPasse(OracleDAOUtils.getString(resultSet,"V_SU_MOT_PASSE"));
		  sujet.setReference1(OracleDAOUtils.getString(resultSet,"V_SU_REFERENCE_1"));
		  sujet.setNumeroClientEmploye(OracleDAOUtils.getString(resultSet,"V_SU_REFERENCE_2"));
		  sujet.setNASCanadien(isNASCanadien(sujet));
		  sujet.setNumeroFiche(OracleDAOUtils.getString(resultSet,"V_SU_REFERENCE_3"));
		  sujet.setSeverite(resultSet.getLong("I_SE_CLE"));
		  sujet.setSeveriteAutres(resultSet.getLong("I_SE_CLE_AUTRES"));
		  sujet.setSeveriteCasino(resultSet.getLong("I_SE_CLE_CASINO"));
		  sujet.setPasseport(OracleDAOUtils.getString(resultSet,"V_SU_NO_PASSEPORT"));
		  sujet.setCreateur(OracleDAOUtils.getString(resultSet,"V_SU_CREE_PAR"));
		  sujet.setDateCreation(resultSet.getTimestamp("D_SU_DATE_CREATION"));
		  sujet.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SU_IND_RDD")));
		  sujet.setDateFinEmploi(resultSet.getTimestamp("D_SU_DATE_FIN_EMPLOI"));
		  log.debug("   [SUJET id='"+sujet.getNumeroFiche()+"']");
	 return sujet;
	}
	/*
	 * Une NAS Canadien est un NAS Canadien s'il est valide.
	 * S'il est vide, on pense qu'il est Canadien.
	 */
	private boolean isNASCanadien(SujetVO sujetVO){
		String numeroAssuranceSociale = sujetVO.getNumeroAssuranceSociale();
		
		if (StringUtils.isNotEmpty(numeroAssuranceSociale)) {
			ValiderNAS validerNAS = new ValiderNAS( numeroAssuranceSociale);
			return validerNAS.isValide();
			
		}else
			return true;
	}
	
/**
 * Recherche de sujets � l'aide de crit�res de recherche.
 * La proc�dure appel�e autrefois est de type DBMS (SQL dynamique)
 * (SP_L4_su_sujet PACKAGE CARDEX_LIRE_DOC).  Avec Java, il ne semble pas possible de r�cup�rer les
 * donn�es retourn�es par ce genre de proc�dure.  C'est pourquoi la requ�te SQL est g�n�r�e plut�t
 * dans le code Java avant d'�tre envoy�e � Oracle.
 * Le resultSet retourn� par les recherches est trait� dans la routine traitementResultSet.
 * Proc�dure appel�e : g�n�r�e ici.
 * Date de cr�ation : (2002-02-12)
 * @author Fran�ois Gu�rin
 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
 * @param criteria CriteresRechercheSujet : crit�res de recherche
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ValueListIterator : liste des sujets retourn�s par la recherche.
 */
	public List<Sujet> select(CardexAuthenticationSubject subject, CriteresRechercheSujet criteria) throws DAOException {
  		JDBCTemplate template = new JDBCTemplate(subject);
    	List<Sujet> listSujet = new ArrayList<Sujet>();
    	PreparerSQL preparerSQL = (new SujetCompletSQL()).construireSQL(subject, criteria);
    	template.query(preparerSQL, criteria.getSequence(), constuireRowCallBackHandler(listSujet));
 	   
    	return listSujet;	
	}
	
    public Integer nombreDeSujetRecherche(CardexAuthenticationSubject subject, CriteresRechercheSujet criteria) throws DAOException {
    	JDBCTemplate template = new JDBCTemplate(subject);
    	PreparerSQL preparerSQL = (new SujetCountSQL()).construireSQL(subject, criteria);
  	   
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
	 * �criture ou suppression d'un lien (association d'un sujet � un autre
	 * sujet).
	 * Proc�dure appel�e : CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER
	 * Date de cr�ation : (2002-01-28)
	 * @author Philippe Caron
	 * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
	 * l'utilisateur.
	 * @param sujet Sujet : Sujet source.
	 * @param addedSujet Sujet : Sujet associ�.
	 * @param action String : "I" : ajout d'un lien; "D" : suppression d'un lien.
	 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
	 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
	 * "stored procedure".
	 */
	public void editLienSujet(CardexAuthenticationSubject subject, Sujet sujet, Sujet addedSujet, String action) throws DAOException {
		Connection connection = DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		try {
			log.debug("begin CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER");
			log.debug("  action '" + action + "'");
			log.debug("  L_LDD_CLE '" + addedSujet.getLien() + "'");
			log.debug("  L_SI_CLE '" + addedSujet.getLienSite() + "'");

			callableStatement =
				connection.prepareCall("begin CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER (?,?,?,?,?,?,?,?,?,?,?); end;");
				callableStatement.setString(1,action); //action
				callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
				callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
				callableStatement.setLong(2, addedSujet.getLien());
				callableStatement.setLong(3, addedSujet.getLienSite());
				callableStatement.setLong(4, sujet.getCle());
				callableStatement.setLong(5, addedSujet.getCle());
				callableStatement.setLong(6, addedSujet.getRole());
				callableStatement.setLong(7, addedSujet.getTypeLien());
				callableStatement.setLong(8, sujet.getSite());
				callableStatement.setString(9, GlobalConstants.GenreFichier.SUJET);
				callableStatement.setLong(10, addedSujet.getSite());
				callableStatement.setString(11, GlobalConstants.GenreFichier.SUJET);
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
	 * �criture ou suppression d'un lien (association d'un sujet � un v�hicule).
	 * Proc�dure appel�e : CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER
	 * Date de cr�ation : (2002-01-28)
	 * @author Philippe Caron
	 * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
	 * l'utilisateur.
	 * @param sujet Sujet : Sujet source.
	 * @param vehicule Vehicule : V�hicule associ�.
	 * @param action String : "I" : ajout d'un lien; "D" : suppression d'un
	 * lien.
	 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	 * rupture de connexion avec la base de donn�es, ou que la table demand�e
	 * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
	 * d'une "stored procedure".
	 */
	public void editLienSujet(CardexAuthenticationSubject subject, Sujet sujet, Vehicule vehicule, String action) throws DAOException {
		Connection connection = DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		try {
			callableStatement =
				connection.prepareCall("begin CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER (?,?,?,?,?,?,?,?,?,?,?); end;");
				callableStatement.setString(1,action); //action
				callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
				callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
				callableStatement.setLong(2, vehicule.getLien());
				callableStatement.setLong(3, vehicule.getLienSite());
				callableStatement.setLong(4, sujet.getCle());
				callableStatement.setLong(5, vehicule.getCle());
				callableStatement.setLong(6, vehicule.getRole());
				callableStatement.setLong(7, vehicule.getTypeLien());
				callableStatement.setLong(8, sujet.getSite());
				callableStatement.setString(9, GlobalConstants.GenreFichier.SUJET);
				callableStatement.setLong(10, vehicule.getSite());
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
	 * �criture ou suppression d'un lien (association d'un sujet � une soci�t�).
	 * Proc�dure appel�e : CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER
	 * Date de cr�ation : (2002-01-28)
	 * @author Philippe Caron
	 * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
	 * l'utilisateur.
	 * @param sujet Sujet : Sujet source.
	 * @param societe Societe : Soci�t� associ�e.
	 * @param action String : "I" : ajout d'un lien; "D" : suppression d'un lien.
	 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
	 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
	 * "stored procedure".
	 */
	public void editLienSujet(CardexAuthenticationSubject subject, Sujet sujet, Societe societe, String action) throws DAOException {
		log.debug("editLienSujet");
		Connection connection = DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		try {
			log.debug("begin CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER");
			log.debug("  action '" + action + "'");
			log.debug("  L_LDD_CLE '" + societe.getLien() + "'");
			log.debug("  L_SI_CLE '" + societe.getLienSite() + "'");
			log.debug("  L_DO_CLE '" + sujet.getCle() + "'");
			log.debug("  L_LDD_DOSSIER_ASSOCIE '" + societe.getCle() + "'");
			log.debug("  I_RO_CLE '" + societe.getRole() + "'");
			log.debug("  I_TL_CLE '" + societe.getTypeLien() + "'");
			log.debug("  L_DO_SITE '" + sujet.getSite() + "'");
			log.debug("  C_DO_GENRE '" + GlobalConstants.GenreFichier.SUJET + "'");
			log.debug("  L_LDD_SITE '" + societe.getSite() + "'");
			log.debug("  C_LDD_GENRE '" + GlobalConstants.GenreFichier.SOCIETE + "'");

			callableStatement =
				connection.prepareCall("begin CARDEX_LIEN.SP_E_LDD_LIEN_DOSSIER (?,?,?,?,?,?,?,?,?,?,?); end;");
				callableStatement.setString(1,action); //action
				callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
				callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
				callableStatement.setLong(2, societe.getLien());
				callableStatement.setLong(3, societe.getLienSite());
				callableStatement.setLong(4, sujet.getCle());
				callableStatement.setLong(5, societe.getCle());
				callableStatement.setLong(6, societe.getRole());
				callableStatement.setLong(7, societe.getTypeLien());
				callableStatement.setLong(8, sujet.getSite());
				callableStatement.setString(9, GlobalConstants.GenreFichier.SUJET);
				callableStatement.setLong(10, societe.getSite());
				callableStatement.setString(11, GlobalConstants.GenreFichier.SOCIETE);
				callableStatement.execute();
		log.debug("editLienSujet ex�cut�");
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


	public Adresse addLienAdresse(CardexAuthenticationSubject subject, Sujet sujet, Adresse adresse) throws DAOException {
		return FabriqueCardexDAO.getInstance().getAdresseDAO().insert(subject, adresse, GlobalConstants.GenreFichier.SUJET);
	}

	public Narration addLienNarration(CardexAuthenticationSubject subject, Sujet sujet, Narration narration) throws DAOException {
		narration.setLien(sujet.getCle());
		narration.setLienSite(sujet.getSite());
		return FabriqueCardexDAO.getInstance().getNarrationDAO().insert(subject, narration, GlobalConstants.GenreFichier.SUJET);
	}

	public void addLienDossier(CardexAuthenticationSubject subject, Sujet sujet, Dossier dossier) throws DAOException {
		FabriqueCardexDAO.getInstance().getDossierDAO().editLienDossier(subject, dossier, sujet, "I");
	}

	public void addLienVehicule(CardexAuthenticationSubject subject, Sujet sujet, Vehicule vehicule) throws DAOException {
		editLienSujet(subject, sujet, vehicule, "I");
	}

	public void addLienSociete(CardexAuthenticationSubject subject, Sujet sujet, Societe societe) throws DAOException {
		editLienSujet(subject, sujet, societe, "I");
	}

	public void addLienSujet(CardexAuthenticationSubject subject, Sujet sujet, Sujet addedSujet) throws DAOException {
		editLienSujet(subject, sujet, addedSujet, "I");
	}

	public Photo addLienPhoto(CardexAuthenticationSubject subject, Sujet sujet, Photo photo) throws DAOException {
		Photo photoRetour = FabriqueCardexDAO.getInstance().getPhotoDAO().insert(subject, photo, GlobalConstants.GenreFichier.SUJET);
		selectionnerPhotoGalerie(subject, sujet, photoRetour);
		return photoRetour;
	}

	public void updateLiensCaracteristique(CardexAuthenticationSubject subject, Sujet sujet, Caracteristiques caracteristiques) throws DAOException {
		FabriqueCardexDAO.getInstance().getCaracteristiqueDAO().update(subject, caracteristiques, GlobalConstants.GenreFichier.SUJET);
	}


	// M�thode deleteLien


	public void deleteLienAdresse(CardexAuthenticationSubject subject, Sujet sujet, Adresse adresse) throws DAOException {
		FabriqueCardexDAO.getInstance().getAdresseDAO().delete(subject, adresse, GlobalConstants.GenreFichier.SUJET);
	}
    
    
	/**
	 * Suppression de tous les sujets plac�s en confidentialit� 8.
	 * Cette m�thode est appel�e � partir de la recherche de dossiers.
	 * Proc�dure appel�e : SP_EPURATION_SUJETS_SITE
	 * Date de cr�ation : (2002-10-17)
	 * @author Fran�ois Gu�rin
	 * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
	 * l'utilisateur.
	 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	 * rupture de connexion avec la base de donn�es, ou que la table demand�e
	 * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
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
					"begin SP_EPURATION_SUJETS_SITE(?); end;");
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

	public void deleteLienNarration(CardexAuthenticationSubject subject, Sujet sujet, Narration narration) throws DAOException {
		FabriqueCardexDAO.getInstance().getNarrationDAO().delete(subject, narration, GlobalConstants.GenreFichier.SUJET);
	}

	public void deleteLienDossier(CardexAuthenticationSubject subject, Sujet sujet, Dossier dossier) throws DAOException {
		FabriqueCardexDAO.getInstance().getDossierDAO().editLienDossier(subject, dossier, sujet, "D");
	}

	public void deleteLienVehicule(CardexAuthenticationSubject subject, Sujet sujet,Vehicule vehicule) throws DAOException {
		editLienSujet(subject, sujet, vehicule, "D");
	}

	public void deleteLienSociete(CardexAuthenticationSubject subject, Sujet sujet, Societe societe) throws DAOException {
		editLienSujet(subject, sujet, societe, "D");
	}

	public void deleteLienSujet(CardexAuthenticationSubject subject, Sujet sujet, Sujet addedSujet) throws DAOException {
		editLienSujet(subject, sujet, addedSujet, "D");
	}

	public void deleteLienPhoto(CardexAuthenticationSubject subject, Sujet sujet, Photo photo) throws DAOException {
		FabriqueCardexDAO.getInstance().getPhotoDAO().delete(subject, photo, GlobalConstants.GenreFichier.SUJET);
		
		if (photo.isSelectionner())
			selectionnerDernierePhotoGalerie(subject, sujet);
	}

	public void deleteLienCaracteristique(CardexAuthenticationSubject subject, Sujet sujet, Caracteristiques caracteristiques) throws DAOException {
		//CaracteristiqueDAO caracteristiqueDAO = DAOFactory.getInstance().getCaracteristiqueDAO();
		//caracteristiqueDAO.delete(subject, caracteristique, GlobalConstants.GenreFichier.SUJET);
	}


	// M�thode updateLien


	public void updateLienAdresse(CardexAuthenticationSubject subject, Sujet sujet, Adresse adresse) throws DAOException {
		FabriqueCardexDAO.getInstance().getAdresseDAO().update(subject, adresse, GlobalConstants.GenreFichier.SUJET);
	}

	public void updateLienNarration(CardexAuthenticationSubject subject, Sujet sujet, Narration narration) throws DAOException {
		FabriqueCardexDAO.getInstance().getNarrationDAO().update(subject, narration, GlobalConstants.GenreFichier.SUJET);
	}

	public void approuveLienNarration(CardexAuthenticationSubject subject, Sujet sujet, Narration narration) throws DAOException {
		FabriqueCardexDAO.getInstance().getNarrationDAO().approbation(subject, narration, GlobalConstants.GenreFichier.SUJET);
	}

	public void updateLienDossier(CardexAuthenticationSubject subject, Sujet sujet, Dossier dossier) throws DAOException {
		FabriqueCardexDAO.getInstance().getDossierDAO().editLienDossier(subject, dossier, sujet, "U");
	}

	public void updateLienVehicule(CardexAuthenticationSubject subject, Sujet sujet, Vehicule vehicule) throws DAOException {
		editLienSujet(subject, sujet, vehicule, "U");
	}

	public void updateLienSociete(CardexAuthenticationSubject subject, Sujet sujet, Societe societe) throws DAOException {
		editLienSujet(subject, sujet, societe, "U");
	}

	public void updateLienSujet(CardexAuthenticationSubject subject, Sujet sujet, Sujet addedSujet) throws DAOException {
		editLienSujet(subject, sujet, addedSujet, "U");
	}

	public void updateLienPhoto(CardexAuthenticationSubject subject, Sujet sujet, Photo photo) throws DAOException {
		FabriqueCardexDAO.getInstance().getPhotoDAO().update(subject, photo, GlobalConstants.GenreFichier.SUJET);
	}

	/**
	 * Retourne les sujets associ�s � un dossier.
	 * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L5_SU_SUJET
	 * Date de cr�ation : (2002-01-28)
	 * @author Fran�ois Gu�rin
	 * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
	 * l'utilisateur.
	 * @param cle : cle de l'object avec lequel on cherche l'association.
	 * @param site : site de l'object avec lequel on cherche l'association.
	 * @param genreFichier : genre de l'object avec lequel on cherche
	 * l'association.
	 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
	 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
	 * "stored procedure".
	 * @return Collection : Liste des sujet associ�s.
	 */
	public Collection findLiensSujet(CardexAuthenticationSubject subject,
			long cle, long site, String genreFichier) throws DAOException {
		log.debug("findLiensSujet()");
		Connection connection =
			DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

		try {
			callableStatement = connection.prepareCall(
					"begin SPW_L5_SU_SUJET (?,?,?,?); end;"); 
			callableStatement.setLong(1, cle);
			callableStatement.setLong(2, site);
			callableStatement.setString(3, genreFichier);
			callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
			
			resultSet = (ResultSet)callableStatement.getObject(4);
            ArrayList results = new ArrayList();
            while ( resultSet.next() ) {
                results.add(traitementResultSetLiens(resultSet));
            }
            //Pour les soci�t�s, on cherche ensuite les audits de liaison pour afficher tous les sujets qui ont appartenu
            //� la soci�t�.
            if(genreFichier.equals(GlobalConstants.GenreFichier.SOCIETE)){
            	callableStatement = connection.prepareCall(
	            	"begin CARDEX_AUDIT.SP_L_SU_SUJET_LIAISON (?,?,?,?); end;"); 
			    callableStatement.setLong(1, cle);
			    callableStatement.setLong(2, site);
			    callableStatement.setString(3, genreFichier);
			    callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			    callableStatement.execute();
			    resultSet = (ResultSet)callableStatement.getObject(4);
			    while ( resultSet.next() ) {
			        results.add(traitementResultSetLiensAudit(resultSet));
			    }
            }
            return results;
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
	}

	/**
	 * Retourne les sujets associ�s � une soci�t� avec la date de fin d'enqu�te.
	 * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L6_SU_SUJET
	 * Date de cr�ation : (2014-05-01)
	 * @author Fran�ois Gu�rin
	 * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
	 * l'utilisateur.
	 * @param cle : cle de l'object avec lequel on cherche l'association.
	 * @param site : site de l'object avec lequel on cherche l'association.
	 * @param genreFichier : genre de l'object avec lequel on cherche
	 * l'association.
	 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
	 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
	 * "stored procedure".
	 * @return Collection : Liste des sujet associ�s.
	 */
	public Collection findLiensSujetEnquete(CardexAuthenticationSubject subject,
			long cle, long site, String genreFichier) throws DAOException {
		log.debug("findLiensSujetEnquete()");
		Connection connection =
			DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

		try {
			callableStatement = connection.prepareCall(
					"begin Cardex_Web_Lire_Doc_Tri.SPW_L6_SU_SUJET (?,?,?); end;"); 
			callableStatement.setLong(1, cle);
			callableStatement.setLong(2, site);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
			
			resultSet = (ResultSet)callableStatement.getObject(3);
            ArrayList results = new ArrayList();
            while ( resultSet.next() ) {
                results.add(traitementResultSetLiensEnquete(resultSet));
            }
            //Pour les soci�t�s, on cherche ensuite les audits de liaison pour afficher tous les sujets qui ont appartenu
            //� la soci�t�.
            if(genreFichier.equals(GlobalConstants.GenreFichier.SOCIETE)){
            	callableStatement = connection.prepareCall(
	            	"begin CARDEX_AUDIT.SP_L_SU_SUJET_LIAISON (?,?,?,?); end;"); 
			    callableStatement.setLong(1, cle);
			    callableStatement.setLong(2, site);
			    callableStatement.setString(3, genreFichier);
			    callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			    callableStatement.execute();
			    resultSet = (ResultSet)callableStatement.getObject(4);
			    while ( resultSet.next() ) {
			        results.add(traitementResultSetLiensAudit(resultSet));
			    }
            }
            return results;
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
	}

	/**
	 * Retourne les sujets provisoires associ�s � un dossier.
	 * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L5_SU_SUJET
	 * Date de cr�ation : (2002-01-28)
	 * @author Fran�ois Gu�rin
	 * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
	 * l'utilisateur.
	 * @param cle : cle de l'object avec lequel on cherche l'association.
	 * @param site : site de l'object avec lequel on cherche l'association.
	 * @param genreFichier : genre de l'object avec lequel on cherche
	 * l'association.
	 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
	 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
	 * "stored procedure".
	 * @return Collection : Liste des sujet associ�s.
	 */
	public Collection findLiensSujetProvisoire(CardexAuthenticationSubject subject,
			long cle, long site, String genreFichier) throws DAOException {
		log.debug("findLiensSujet()");
		Connection connection =
			DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;

		try {
			callableStatement = connection.prepareCall(
					"begin Cardex_Web_Lire_Doc_Tri.SPW_L5_SU_SUJET_PROVISOIRE (?,?,?,?); end;"); 
			callableStatement.setLong(1, cle);
			callableStatement.setLong(2, site);
			callableStatement.setString(3, genreFichier);
			callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
            callableStatement.execute();
			
			resultSet = (ResultSet)callableStatement.getObject(4);
            ArrayList results = new ArrayList();
            while ( resultSet.next() ) {
                results.add(traitementResultSetLiens(resultSet));
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

	
//Traite les sujets li�s dans un onglet	
private Sujet traitementResultSetLiens(ResultSet resultSet) throws SQLException {
		SujetVO linkedSujet = new SujetVO();
		linkedSujet.setCle(resultSet.getLong("L_SU_CLE"));
		linkedSujet.setSite(resultSet.getLong("L_SI_CLE"));
		linkedSujet.setEntite(resultSet.getLong("I_EN_CLE"));
		linkedSujet.setSexe(resultSet.getLong("I_SX_CLE"));
		linkedSujet.setStatut(resultSet.getLong("I_ST_CLE"));
		linkedSujet.setEthnie(resultSet.getLong("I_NT_CLE"));
		linkedSujet.setRace(resultSet.getLong("I_RA_CLE"));
		linkedSujet.setLangue(resultSet.getLong("I_LS_CLE"));
		linkedSujet.setNom(resultSet.getString("V_SU_NOM"));
		linkedSujet.setPrenom(resultSet.getString("V_SU_PRENOM"));
		linkedSujet.setAlias(resultSet.getString("V_SU_SURNOM"));
		linkedSujet.setDateNaissance(resultSet.getTimestamp(
				"D_SU_DATE_NAISSANCE"));
		linkedSujet.setTypeAge(resultSet.getLong("L_SU_TYPE_AGE"));
		linkedSujet.setNumeroAssuranceSociale(resultSet.getString(
				"C_SU_ASSURANCE_SOCIALE"));
		linkedSujet.setNumeroPermisConduire(resultSet.getString(
				"V_SU_PERMIS_CONDUIRE"));
		linkedSujet.setConfidentialite(resultSet.getLong("I_CC_CLE"));
		linkedSujet.setMotPasse(resultSet.getString("V_SU_MOT_PASSE"));
		linkedSujet.setReference1(resultSet.getString(
				"V_SU_REFERENCE_1"));
		linkedSujet.setReference2(resultSet.getString(
				"V_SU_REFERENCE_2"));
		linkedSujet.setNumeroFiche(resultSet.getString(
				"V_SU_REFERENCE_3"));
		linkedSujet.setSeverite(resultSet.getLong("I_SE_CLE"));
		linkedSujet.setSeveriteAutres(resultSet.getLong("I_SE_CLE_AUTRES"));
		linkedSujet.setSeveriteCasino(resultSet.getLong("I_SE_CLE_CASINO"));
		linkedSujet.setPasseport(resultSet.getString(
				"V_SU_NO_PASSEPORT"));
		log.debug("   [SUJET id='" + linkedSujet.getNumeroFiche()
				+ "' Nom='"+linkedSujet.getNom() + "']");
		linkedSujet.setLien(resultSet.getLong("L_LDD_CLE"));
		linkedSujet.setLienSite(resultSet.getLong("L_LDD_SI_CLE"));
		linkedSujet.setLienCreateur(resultSet.getString("V_LDD_CREE_PAR"));
		linkedSujet.setRole(resultSet.getLong("I_RO_CLE"));
		linkedSujet.setLienDateCreation(resultSet.getTimestamp("D_LDD_DATE_CREATION"));
		linkedSujet.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SU_IND_RDD")));
		linkedSujet.setDateFinEmploi(resultSet.getTimestamp("D_SU_DATE_FIN_EMPLOI"));
		log.debug("   [SUJET id='" + linkedSujet.getNumeroFiche()
				+ "' Nom='" + linkedSujet.getNom() + "']");
	return linkedSujet;
}
/**
 * Routine pour traiter les ResultSet retourn�s par les recherches des sujets qui ont
 * �t� li�es � un module et qui sont conserv�es dans l'audit des liaisons (AUD_LI_LIAISON).
 * Date de cr�ation : (2012-02-13)
 * @author Fran�ois Gu�rin
 */
  private Sujet traitementResultSetLiensAudit(ResultSet resultSet) throws SQLException {
		  SujetVO sujet = new SujetVO();
		  sujet.setCle(resultSet.getLong("L_SU_CLE"));
		  sujet.setSite(resultSet.getLong("L_SI_CLE"));
		  sujet.setSexe(resultSet.getLong("I_SX_CLE"));
		  sujet.setStatut(resultSet.getLong("I_ST_CLE"));
		  sujet.setEthnie(resultSet.getLong("I_NT_CLE"));
		  sujet.setRace(resultSet.getLong("I_RA_CLE"));
		  sujet.setLangue(resultSet.getLong("I_LS_CLE"));
		  sujet.setNom(resultSet.getString("V_SU_NOM"));
		  sujet.setPrenom(resultSet.getString("V_SU_PRENOM"));
		  sujet.setAlias(OracleDAOUtils.getString(resultSet,"V_SU_SURNOM"));
		  sujet.setDateNaissance(resultSet.getTimestamp("D_SU_DATE_NAISSANCE"));
		  sujet.setTypeAge(resultSet.getLong("L_SU_TYPE_AGE"));
		  sujet.setNumeroAssuranceSociale(OracleDAOUtils.getString(resultSet,"C_SU_ASSURANCE_SOCIALE"));
		  sujet.setNumeroAssuranceMaladie(OracleDAOUtils.getString(resultSet,"V_SU_ASSURANCE_MALADIE"));
		  sujet.setNumeroPermisConduire(OracleDAOUtils.getString(resultSet,"V_SU_PERMIS_CONDUIRE"));
		  sujet.setConfidentialite(resultSet.getLong("I_CC_CLE"));
		  sujet.setMotPasse(OracleDAOUtils.getString(resultSet,"V_SU_MOT_PASSE"));
		  sujet.setReference1(OracleDAOUtils.getString(resultSet,"V_SU_REFERENCE_1"));
		  sujet.setNumeroClientEmploye(OracleDAOUtils.getString(resultSet,"V_SU_REFERENCE_2"));
		  sujet.setNASCanadien(isNASCanadien(sujet));
		  sujet.setNumeroFiche(OracleDAOUtils.getString(resultSet,"V_SU_REFERENCE_3"));
		  sujet.setSeverite(resultSet.getLong("I_SE_CLE"));
		  sujet.setSeveriteAutres(resultSet.getLong("I_SE_CLE_AUTRES"));
		  sujet.setSeveriteCasino(resultSet.getLong("I_SE_CLE_CASINO"));
		  sujet.setPasseport(OracleDAOUtils.getString(resultSet,"V_SU_NO_PASSEPORT"));
		  sujet.setCreateur(OracleDAOUtils.getString(resultSet,"V_SU_CREE_PAR"));
		  sujet.setDateCreation(resultSet.getTimestamp("D_SU_DATE_CREATION"));
          sujet.setAudit(GlobalConstants.BooleanString.TRUE); //On indique que le sujet provient de l'audit des liaisons.
		  sujet.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SU_IND_RDD")));
		  sujet.setDateFinEmploi(resultSet.getTimestamp("D_SU_DATE_FIN_EMPLOI"));
		  log.debug("   [SUJET id='" + sujet.getNumeroFiche()
				+ "' Nom='" + sujet.getNom() + "']");
	return sujet;
}

/**
	 * Retourne l'historique des propri�taires d'une soci�t�.
	 * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L6_SU_SUJET
	 * Date de cr�ation : (2009-12-01)
	 * @author Fran�ois Gu�rin
	 * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
	 * l'utilisateur.
	 * @param cle : cle de l'object avec lequel on cherche l'association.
	 * @param site : site de l'object avec lequel on cherche l'association.
	 * @param genreFichier : genre de l'object avec lequel on cherche
	 * l'association.
	 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
	 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
	 * "stored procedure".
	 * @return Collection : Liste des sujet associ�s.
	 */
	public Collection findLiensProprietaires(CardexAuthenticationSubject subject,
			long cle, long site, String genreFichier) throws DAOException {
		log.debug("findLiensProprietaires()");
		Connection connection =
			DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		try {
			callableStatement = connection.prepareCall(
					"begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L6_SU_SUJET (?,?,?); end;"); // cette proc�dure contien un merge cart�sien
			callableStatement.setLong(1, cle);
			callableStatement.setLong(2, site);
			callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
            callableStatement.execute();
			
			resultSet = (ResultSet)callableStatement.getObject(3);
			ArrayList results = new ArrayList();
            while ( resultSet.next() ) {
                results.add(traitementResultSetLiensEnquete(resultSet));
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
	 * Lecture des dossiers associ�s � un sujet
	 * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L5_DO_DOSSIER
	 * Date de cr�ation : (2002-02-12)
	 * @author Fran�ois Gu�rin
	 * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
	 * @param sujet Sujet : Sujet de base.
	 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
	 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
	 * "stored procedure".
	 * @return Collection : liste des dossiers associ�s
	 */
	public Collection findLiensDossier(CardexAuthenticationSubject subject, Sujet sujet) throws DAOException{
		return FabriqueCardexDAO.getInstance().getDossierDAO().findLiensDossier(subject, sujet.getCle(),
				sujet.getSite(), GlobalConstants.GenreFichier.SUJET);
	}

	public Collection findLiensDossier(Sujet sujet, Connection connection) throws DAOException{
		return FabriqueCardexDAO.getInstance().getDossierDAO().findLiensDossier(sujet.getCle(),
				sujet.getSite(), GlobalConstants.GenreFichier.SUJET, connection);
	}
	
	public Collection findLiensAdresse(CardexAuthenticationSubject subject, Sujet sujet) throws DAOException{
	  return FabriqueCardexDAO.getInstance().getAdresseDAO().findLiensAdresseSujet(subject, sujet.getCle(), sujet.getSite(), sujet.getLienDateCreation(), GlobalConstants.GenreFichier.SUJET);
	}

	public Collection findLiensAdresseAudit(CardexAuthenticationSubject subject, Sujet sujet) throws DAOException{
		  return FabriqueCardexDAO.getInstance().getAdresseDAO().findLiensAdresseAudit(subject, sujet.getCle(), sujet.getSite(), sujet.getLienDateCreation(), GlobalConstants.GenreFichier.SUJET);
		}

	public Collection findLiensNarration(CardexAuthenticationSubject subject, Sujet sujet) throws DAOException{
	  return FabriqueCardexDAO.getInstance().getNarrationDAO().findLiensNarration(subject, sujet.getCle(), sujet.getSite(), GlobalConstants.GenreFichier.SUJET);
	}

	public Collection findLiensVehicule(CardexAuthenticationSubject subject,
			Sujet sujet) throws DAOException {
		return VehiculeDAO.findLiensVehicule(subject, sujet.getCle(),
				sujet.getSite(), GlobalConstants.GenreFichier.SUJET);
	}

	public Collection findLiensSociete(CardexAuthenticationSubject subject,
			Sujet sujet) throws DAOException {
		return FabriqueCardexDAO.getInstance().getSocieteDAO().findLiensSociete(subject, sujet.getCle(),
				sujet.getSite(), GlobalConstants.GenreFichier.SUJET);
	}

	public Collection findLiensSujet(CardexAuthenticationSubject subject, Sujet sujet) throws DAOException{
	  return findLiensSujet(subject,sujet.getCle(),sujet.getSite(),GlobalConstants.GenreFichier.SUJET);
	}

	public Collection findLiensPhoto(CardexAuthenticationSubject subject, Sujet sujet) throws DAOException{
	  return FabriqueCardexDAO.getInstance().getPhotoDAO().findLiensPhoto(subject,sujet.getCle(),sujet.getSite(), null, GlobalConstants.GenreFichier.SUJET);
	}

	public Collection findLiensPhotoAudit(CardexAuthenticationSubject subject, Sujet sujet) throws DAOException{
		  return FabriqueCardexDAO.getInstance().getPhotoDAO().findLiensPhotoAudit(subject,sujet.getCle(),sujet.getSite(),sujet.getLienDateCreation(),GlobalConstants.GenreFichier.SUJET);
		}

	public Caracteristiques findLiensCaracteristique(CardexAuthenticationSubject subject, Sujet sujet) throws DAOException{
	  return FabriqueCardexDAO.getInstance().getCaracteristiqueDAO().findLiensCaracteristique(subject, sujet);
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.SujetDAO#rechercheAdresseSujet(com.lotoquebec.cardex.authentication.CardexAuthenticationSubject, com.lotoquebec.cardex.business.CriteresRechercheAdresses)
	 */
	public List<Sujet> rechercheAdresseSujet(CardexAuthenticationSubject subject, CriteresRechercheAdresses criteres) throws DAOException {
		final List<Sujet> liste = new ArrayList<Sujet>();
    	JDBCTemplate template = new JDBCTemplate(subject);
    	
    	PreparerSQL preparerSQL = (new AdresseSujetCompletSQL()).construireSQL( subject, criteres );
    	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				Sujet sujet = new SujetVO();

				sujet.setCle( rs.getLong( AdresseSujetSQL.CLE_SUJET ) );
				sujet.setSite( rs.getLong( AdresseSujetSQL.SITE_SUJET ) );
				sujet.setNumeroFiche( rs.getString( AdresseSujetSQL.NUMERO_FICHE ) );
				sujet.setSeverite( rs.getLong( AdresseSujetSQL.SEVERITE ) );
				sujet.setNom( rs.getString( AdresseSujetSQL.NOM ) );
				sujet.setPrenom( rs.getString( AdresseSujetSQL.PRENOM ) );
				sujet.setAlias( rs.getString( AdresseSujetSQL.ALIAS ) );
				sujet.setSexe( rs.getLong( AdresseSujetSQL.SEXE ) );
				sujet.setEthnie( rs.getLong( AdresseSujetSQL.ETHNIE ) );
				sujet.setRace( rs.getLong( AdresseSujetSQL.RACE ) );
				sujet.setLangue( rs.getLong( AdresseSujetSQL.LANGUE ) );
				sujet.setDateNaissance( rs.getTimestamp( AdresseSujetSQL.DATE_NAISSANCE ) );
				
				liste.add(sujet);
			}
    	};
    	
    	template.query(preparerSQL, criteres.getSequence(), rch);
    	
		return liste;
	}

    public Integer nombreDeSujetRechercheAdresse(CardexAuthenticationSubject subject, CriteresRechercheAdresses criteria) throws DAOException {
    	JDBCTemplate template = new JDBCTemplate(subject);
    	PreparerSQL preparerSQL = (new AdresseSujetCountSQL()).construireSQL(subject, criteria);
  	   
    	UnEnregistrementPresent unEnregistrementPresent = new UnEnregistrementPresent(){
  			@Override
  			public void processRow(ResultSet rs) throws SQLException {
  				object = rs.getInt(1);
  			}
    	};
  	   
    	template.query(preparerSQL, criteria.getSequence(), unEnregistrementPresent);
  	   
        return (Integer) unEnregistrementPresent.getObject();
     } 
	
	public void selectionnerPhotoGalerie(CardexAuthenticationSubject subject,
			final Sujet sujetVO, final Photo photoVO) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.setLong(1, sujetVO.getCle());
    	        callableStatement.setLong(2, sujetVO.getSite());
    	        callableStatement.setLong(3, photoVO.getCle());
    	        callableStatement.setLong(4, photoVO.getSite());
			}
    	};		
		
		storeProcTemplate.prepareCall("CARDEX_LIEN.SP_E_MM_SUJET_SELECTIONNER", 4, rch);
		storeProcTemplate.query(true);	
	}
	
	public void selectionnerDernierePhotoGalerie(CardexAuthenticationSubject subject,
			final Sujet sujetVO) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(subject);
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.setLong(1, sujetVO.getCle());
    	        callableStatement.setLong(2, sujetVO.getSite());
			}
    	};		
		
		storeProcTemplate.prepareCall("CARDEX_LIEN.SP_E_MM_SUJET_SELECT_DERNIER", 2, rch);
		storeProcTemplate.query(true);	
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.SocieteDAO#rechercheAdresseInvalideSociete(com.lotoquebec.cardex.authentication.CardexAuthenticationSubject, com.lotoquebec.cardex.business.CriteresRechercheAdresses)
	 */
	public Collection rechercheAdresseInvalideSujet(final CardexAuthenticationSubject subject, final CriteresRechercheAdresses criteres) throws DAOException {
		final Map mapSujetAdresseInvalide = new LinkedHashMap();
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
		
		template.prepareCall("Cardex_Lire_Lien.Sp_L_Su_Sujet_Ad_Adresse", 4, 4, rowCallHandler);
	
    	RowCallbackHandler rch = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				try {
					AdresseBusinessDelegate delegate = new AdresseBusinessDelegate();
					Sujet sujet = traitementResultSet(rs);
					Adresse adresse = FabriqueCardexDAO.getInstance().getAdresseDAO().obtenirAdresseVOde(rs);
					AdresseSortie adresseSortie = delegate.validationAdresse(subject, adresse);
					
					if (adresseSortie.isValide())
						return;
					adresse.setMessage(adresseSortie.obtenirMessageHTML());
					
					if (mapSujetAdresseInvalide.containsKey(sujet.getCleUnique())){
						sujet = (Sujet) mapSujetAdresseInvalide.get(sujet.getCleUnique());
						sujet.addAdresse(adresse);
					}else{
						sujet.addAdresse(adresse);
						mapSujetAdresseInvalide.put(sujet.getCleUnique(), sujet);
					}
					
				} catch (BusinessResourceException e) {
					e.printStackTrace();
				}
			}
    	};
    	
    	template.query(rch, true);
    	
		return mapSujetAdresseInvalide.values();
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.SujetDAO#assignerSujetConfidentialiteC(java.sql.Connection, com.lotoquebec.cardex.business.vo.SujetVO)
	 */
	public void assignerSujetConfidentialiteC(Connection connection, final SujetVO sujetVO) throws DAOException {
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			OracleDAOUtils.setLong(callableStatement, 1, sujetVO.getCle());
    			OracleDAOUtils.setLong(callableStatement, 2, sujetVO.getSite());
			}
    	};
    	
    	storeProcTemplate.prepareCall("CARDEX_DOC.SP_E_SU_SUJET_ASSIGNER_CC_C", 2, rch);
    	
    	storeProcTemplate.query( false );
	}
	

	public List<Sujet> rechercheSujet(final String matricule, Connection connection) throws DAOException{
		final List<Sujet> listeSujet = new ArrayList<Sujet>();
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
		
		if (StringUtils.isEmpty(matricule))
			return listeSujet;
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){
    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.setString(1, matricule);
    			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			}
    	};		
		storeProcTemplate.prepareCall("Cardex_Lire_Doc.SP_L_SUJET_MouvRechNoEmploye", 2, 2, rch);
		
		RowCallbackHandler rowCallbackHandler = new RowCallbackHandler(){
	
			public void processRow(ResultSet rs) throws SQLException {
				Sujet sujetVO = traitementSujet(rs);
				listeSujet.add(sujetVO);
			}
		};
		
		storeProcTemplate.query(rowCallbackHandler, false);
		
		return listeSujet;
	}	
	
	public List<Sujet> rechercheSujet(final String prenom, final String nom, final Timestamp dateNaissance, Connection connection) throws DAOException{
		final List<Sujet> listeSujet = new ArrayList<Sujet>();
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){
    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.setString(1, nom);
    			callableStatement.setString(2, prenom);
    			callableStatement.setTimestamp(3, dateNaissance);
    			callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
			}
    	};		
		
		storeProcTemplate.prepareCall("Cardex_Lire_Doc.SP_L_SUJET_MouvRechDNNomPrenom", 4, 4, rch);
		
		RowCallbackHandler rowCallbackHandler = new RowCallbackHandler(){

			public void processRow(ResultSet rs) throws SQLException {
				Sujet sujetVO = traitementSujet(rs);
				listeSujet.add(sujetVO);
			}
		};
		
		storeProcTemplate.query(rowCallbackHandler, false);
		
		return listeSujet;
	}	

    /**
     * Recherche de l'audit des changements d'un sujet.
     * 
     * Proc�dure appel�e : CARDEX_AUDIT.SP_L_AUDIT_SUJET
     * Date de cr�ation : (2011-03-08)
     * @author Fran�ois Gu�rin
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param criteria Sujet : Sujet � rechercher.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Dossier : Instance de dossier associ�e.
     */
    public List audit(CardexAuthenticationSubject subject,Sujet criteria)
            throws DAOException {
        Connection connection = null;            
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		List  resultats = new ArrayList();
          try {
        	connection = DAOConnection.getInstance().getConnection(subject);
            callableStatement = connection.prepareCall(
                    "begin CARDEX_AUDIT.SP_L_AUDIT_SUJET(?,?,?); end;");
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
	
	
    //Mise-�-jour de la s�v�rit� � 4 pour les sujets d'invesigation dont le dossier actif arrive � expiration dans 90 jours.
    public void severite4SujetInvestigationExpiration90Jours(Connection connection) throws DAOException{
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
    		public void preparer(CallableStatement callableStatement) throws SQLException {
			}
    	};
    	
    	storeProcTemplate.prepareCall("CARDEX_DOC.SP_E_SU_SEVERITE_4", 0, null);

    	storeProcTemplate.query( false );		
   }

    //Mise-�-jour de la s�v�rit� � 2 pour les sujets d'invesigation dont le dossier est arriv� � expiration et dont la s�v�rit� n'est pas � 2.
    public void severite2SujetInvestigationExpiration(Connection connection) throws DAOException{
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);

		PreparerCallableStatement rch = new PreparerCallableStatement(){
    		public void preparer(CallableStatement callableStatement) throws SQLException {
			}
    	};
    	
    	storeProcTemplate.prepareCall("CARDEX_DOC.SP_E_SU_SEVERITE_2", 0, null);

    	storeProcTemplate.query( false );		
   }

    /**
     * Mise � jour d'un lien (association d'un sujet � un sujet, un dossier ou � une soci�t�).
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
            Sujet sujet) throws DAOException {
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
                callableStatement.setLong(2, sujet.getLien());
                callableStatement.setLong(3, sujet.getLienSite());
                callableStatement.setLong(4, sujet.getCle());
                callableStatement.setLong(5, 0);
                callableStatement.setLong(6, sujet.getRole());
                callableStatement.setLong(7, sujet.getTypeLien());
                callableStatement.setLong(8, sujet.getSite());
                callableStatement.setString(9,
                        GlobalConstants.GenreFichier.DOSSIER);
                callableStatement.setLong(10, 0);
                callableStatement.setString(11,
                        GlobalConstants.GenreFichier.SUJET);
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

    //Mise-�-jour de la s�v�rit� des sujets d'invesigation qui ont un dossier actif et dont la s�v�rit� n'est pas � 3
	  public void severite3SujetInvestigationDossierActif(Connection connection) throws DAOException {
			StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);

			PreparerCallableStatement rch = new PreparerCallableStatement(){
	    		public void preparer(CallableStatement callableStatement) throws SQLException {
				}
	    	};
	    	
	    	storeProcTemplate.prepareCall("CARDEX_DOC.SP_E_SU_SEVERITE_3", 0, null);

	    	storeProcTemplate.query( false );		
	   }
	  
	  /**
	   * �criture d'un sujet en provenance du syst�me RDD (R�seau des d�taillants).
	   * Ces sujets sont des personnes physiques dans RDD, responsables d'un d�taillant.
	   * Proc�dure appel�e : CARDEX_DOC.SP_E_SU_SUJET_DETAILLANTS
	   * Date de cr�ation : (2002-02-11)
	   * @author Fran�ois Gu�rin
	   * @param subject CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
	   * @param sujet Sujet : sujet saisi � l'�cran
	   * @param action  java.lang.String : U ou I
	   * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	   * rupture de connexion avec la base de donn�es, ou que la table demand�e est
	   * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
	   * "stored procedure".
	   * @return Sujet
	   */
	     public Sujet insertSujetDetaillant(Sujet sujet, Connection connection) throws DAOException {

	  	   CallableStatement callableStatement = null;
	  		  try {
	  			  callableStatement =
	  				  connection.prepareCall("begin CARDEX_DOC.SP_E_SU_SUJET_DETAILLANTS (?,?,?,?,?,?,?,?,?,?); end;");
	  				  callableStatement.registerOutParameter(1, java.sql.Types.DECIMAL);
	  				  callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
	  				  OracleDAOUtils.setLong(callableStatement,1, sujet.getCle()); // NEW_L_SU_CLE IN OUT SU_SUJET.L_SU_CLE%TYPE,
	  				  OracleDAOUtils.setLong(callableStatement,2, sujet.getSite()); // NEW_L_SI_CLE IN OUT SU_SUJET.L_SI_CLE%TYPE,
	  				  callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
	  				  callableStatement.setString(3,sujet.getNumeroFiche()); //v_su_reference_3 : num�ro de fiche
	  				  callableStatement.setString(4,sujet.getNom().trim());  //v_su_nom
	  				  callableStatement.setString(5,sujet.getPrenom().trim()); //i_su_prenom
	  				  OracleDAOUtils.setLong(callableStatement,6,sujet.getTypeAge()); //L_SU_TYPE_AGE
	  				  OracleDAOUtils.setLong(callableStatement,7,sujet.getConfidentialite()); //i_cc_cle
	  				  OracleDAOUtils.setLong(callableStatement,8,sujet.getSeverite()); //i_se_cle
	  				  OracleDAOUtils.setLong(callableStatement,9,sujet.getSeveriteAutres()); //i_se_cle_autres
	  				  callableStatement.setString(10,OracleDAOUtils.convertirBooleanAString(sujet.isIndicateurRdd())); //B_SU_IND_RDD
	  				  callableStatement.execute();
	  				  connection.setAutoCommit(true);
	  				  //connection.commit();
	  				  Sujet newSujet = new SujetVO();
	  				  newSujet.setCle(callableStatement.getLong(1));
	  				  newSujet.setSite(callableStatement.getLong(2));
	  				  newSujet.setNumeroFiche(callableStatement.getString(3));
	  				  return newSujet;
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
	  		  }
	     }

	     /**
	      * Copie des donn�es d'un suejt � une autre.
	      * Cette proc�dure ne devrait �tre que temporaire, le temps de permettre � l'entit� Loto-Qu�bec
	      * de supprimer les doublons.
	      * Utilisant la stored procedure SP_E_COPIER_SUJET
	      * Date de cr�ation : (2013-04-10)
	      * @author GUERINF
	      * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
	      * l'utilisateur.
	      * @throws DAOException lanc�e� lorsqu'une SQLException est re�ue lors d'une
	      * rupture de connexion avec la base de donn�es, ou que la table demand�e
	      * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
	      * d'une "stored procedure".
	      */
	     public void copier(CardexAuthenticationSubject subject,
	     		long cleSource, long siteSource, long cleDestination, long siteDestination) throws DAOException {
	         Connection connection =
	         	DAOConnection.getInstance().getConnection(subject);
	         CallableStatement callableStatement = null;
	         try {
	             callableStatement = connection.prepareCall(
	                     "begin CARDEX_SPECIAL.SP_E_COPIER_SUJET"
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
	     
	     /**
	      * Recherche directe d'un sujet avec le num�ro de fiche avec �criture d'un audit.
	      * Appel� � partir de la recherche directe dans le menu principal.
	      * Proc�dure appel�e : CARDEX_LIRE_DOC.SP_L_SU_SUJET_DIRECT
	      * Date de cr�ation : (2013-08-07)
	      * @author Fran�ois Gu�rin
	      * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
	      * @param criteria Sujet : sujet � rechercher
	      * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	      * rupture de connexion avec la base de donn�es, ou que la table demand�e est
	      * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
	      * "stored procedure".
	      * @return Sujet : donn�es du sujet trouv�.
	      */
	     	public Sujet rechercheDirecte(CardexAuthenticationSubject subject, Sujet criteria) throws DAOException{
	     	  Connection connection = DAOConnection.getInstance().getConnection(subject);
	     	  CallableStatement callableStatement = null;
	     	  ResultSet resultSet = null;
	     	  Sujet sujet = new SujetVO();
	     	  try {
	     		 callableStatement = connection.prepareCall("begin CARDEX_LIRE_DOC.SP_L_SU_SUJET_DIRECT (?,?); end;");
	     		 callableStatement.setString(1,criteria.getNumeroFiche());
	     		 callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
	     		 callableStatement.execute();
	     		 resultSet = (ResultSet)callableStatement.getObject(2);
	     		 //Traitement du sujet retourn� (s'il y a lieu)
	     		 if (resultSet.next()){
	     			sujet = traitementResultSet(resultSet);
	     		 }
	     		 return sujet;
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
	public void ajoutAcces(CardexAuthenticationSubject subject, Sujet sujet) throws DAOException {
		FabriqueCardexDAO.getInstance().getAccesDAO().ajoutAcces(subject, sujet.getCle(), sujet.getSite(), GlobalConstants.GenreFichier.SUJET);
	}


    //Mise-�-jour de la s�v�rit� Casino des sujets d'invesigation
	  public void severiteSujetInvestigationCasino(Connection connection) throws DAOException {
		  CallableStatement callableStatement = null;
		  ResultSet resultSet = null;
		  try{
			 callableStatement = connection.prepareCall("begin CARDEX_LIRE_DOC.SP_L_SU_SEVERITE_CASINO (?); end;");
			 CallableStatement callableStatement2 = null;
			 callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			 callableStatement.execute();
			 resultSet = (ResultSet)callableStatement.getObject(1);
			 //Traitement des donn�es retourn�es pour mettre � jour la s�v�rit�
			 while(resultSet.next()){
				long cle = resultSet.getLong("L_SU_CLE");
				long site = resultSet.getLong("L_SI_CLE");
				String numeroFiche = resultSet.getString("NumeroFiche");
				long severiteSujet = resultSet.getLong("SEVERITE");
				String NumeroCardex = resultSet.getString("NumeroCardex");
				long severiteDossier = resultSet.getLong("SEVERITE_DOSSIER");
				courantLog.info("Assignation s�v�rit� "+severiteSujet+" du dossier "+NumeroCardex+" au sujet "+numeroFiche+" ayant la s�v�rit� "+severiteSujet);
				try{
					callableStatement2 = connection.prepareCall("begin CARDEX_DOC.SP_E_SU_SEVERITE_CASINO (?,?,?); end;");
					OracleDAOUtils.setLong(callableStatement2,1, cle);
					OracleDAOUtils.setLong(callableStatement2,2, site); 
					OracleDAOUtils.setLong(callableStatement2,3, severiteDossier); 
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

	    //Mise-�-jour de la s�v�rit� Autre des sujets d'invesigation
	  public void severiteSujetInvestigationAutre(Connection connection) throws DAOException {
			  CallableStatement callableStatement = null;
			  ResultSet resultSet = null;
			  try{
				 callableStatement = connection.prepareCall("begin CARDEX_LIRE_DOC.SP_L_SU_SEVERITE_AUTRE (?); end;");
				 CallableStatement callableStatement2 = null;
				 callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
				 callableStatement.execute();
				 resultSet = (ResultSet)callableStatement.getObject(1);
				 //Traitement des donn�es retourn�es pour mettre � jour la s�v�rit�
				 while(resultSet.next()){
					long cle = resultSet.getLong("L_SU_CLE");
					long site = resultSet.getLong("L_SI_CLE");
					String numeroFiche = resultSet.getString("NumeroFiche");
					long severiteSujet = resultSet.getLong("SEVERITE");
					String NumeroCardex = resultSet.getString("NumeroCardex");
					long severiteDossier = resultSet.getLong("SEVERITE_DOSSIER");
					courantLog.info("Assignation s�v�rit� "+severiteSujet+" du dossier "+NumeroCardex+" au sujet "+numeroFiche+" ayant la s�v�rit� "+severiteSujet);
					try{
						callableStatement2 = connection.prepareCall("begin CARDEX_DOC.SP_E_SU_SEVERITE_AUTRE (?,?,?); end;");
						OracleDAOUtils.setLong(callableStatement2,1, cle);
						OracleDAOUtils.setLong(callableStatement2,2, site); 
						OracleDAOUtils.setLong(callableStatement2,3, severiteDossier); 
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
	  /**
	   * Recherche d'un sujet RDD par le nom, le pr�nom pour �viter de le recr�er dans
	   * le diff�r� du traitement des d�taillants
	   * Proc�dure appel�e : CARDEX_WEB_LIRE_DOC_TRI.SPW_L_SU_SUJET_RDD
	   * Date de cr�ation : (2002-02-12)
	   * @author Fran�ois Gu�rin
	   * @param subject  CardexAuthenticationSubject : donn�es nominatives sur l'utilisateur
	   * @param criteria Sujet : sujet � rechercher
	   * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
	   * rupture de connexion avec la base de donn�es, ou que la table demand�e est
	   * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
	   * "stored procedure".
	   * @return Sujet : donn�es du sujet trouv�.
	   */
	  	public Sujet findSujetRDD(CardexAuthenticationSubject subject, Sujet criteria) throws DAOException{
	  	  Connection connection = DAOConnection.getInstance().getConnection(subject);
	  	  CallableStatement callableStatement = null;
	  	  ResultSet resultSet = null;
	  	  Sujet sujet = new SujetVO();
	  	  try {
	  		 callableStatement = connection.prepareCall("begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L_SU_SUJET_RDD (?,?,?); end;");
	  		 callableStatement.setString(1,criteria.getNom());
	  		 callableStatement.setString(2,criteria.getPrenom());
	  		 callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
	  		 callableStatement.execute();
	  		 resultSet = (ResultSet)callableStatement.getObject(3);
	  		 //Traitement du sujet retourn� (s'il y a lieu)
	  		 if (resultSet.next()){
				  sujet.setCle(resultSet.getLong("L_SU_CLE"));
				  sujet.setSite(resultSet.getLong("L_SI_CLE"));
				  sujet.setNom(resultSet.getString("V_SU_NOM"));
				  sujet.setPrenom(resultSet.getString("V_SU_PRENOM"));
				  sujet.setDateNaissance(resultSet.getTimestamp("D_SU_DATE_NAISSANCE"));
				  sujet.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SU_IND_RDD")));
	  		 }
	  		 return sujet;
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

  //Traite les sujets d'une soci�t� li�s dans un onglet	
  	private Sujet traitementResultSetLiensEnquete(ResultSet resultSet) throws SQLException {
  			SujetVO linkedSujet = new SujetVO();
  			linkedSujet.setCle(resultSet.getLong("L_SU_CLE"));
  			linkedSujet.setSite(resultSet.getLong("L_SI_CLE"));
  			linkedSujet.setEntite(resultSet.getLong("I_EN_CLE"));
  			linkedSujet.setSexe(resultSet.getLong("I_SX_CLE"));
  			linkedSujet.setStatut(resultSet.getLong("I_ST_CLE"));
  			linkedSujet.setEthnie(resultSet.getLong("I_NT_CLE"));
  			linkedSujet.setRace(resultSet.getLong("I_RA_CLE"));
  			linkedSujet.setLangue(resultSet.getLong("I_LS_CLE"));
  			linkedSujet.setNom(resultSet.getString("V_SU_NOM"));
  			linkedSujet.setPrenom(resultSet.getString("V_SU_PRENOM"));
  			linkedSujet.setAlias(resultSet.getString("V_SU_SURNOM"));
  			linkedSujet.setDateNaissance(resultSet.getTimestamp(
  					"D_SU_DATE_NAISSANCE"));
  			linkedSujet.setTypeAge(resultSet.getLong("L_SU_TYPE_AGE"));
  			linkedSujet.setNumeroAssuranceSociale(resultSet.getString(
  					"C_SU_ASSURANCE_SOCIALE"));
  			linkedSujet.setNumeroPermisConduire(resultSet.getString(
  					"V_SU_PERMIS_CONDUIRE"));
  			linkedSujet.setConfidentialite(resultSet.getLong("I_CC_CLE"));
  			linkedSujet.setMotPasse(resultSet.getString("V_SU_MOT_PASSE"));
  			linkedSujet.setReference1(resultSet.getString(
  					"V_SU_REFERENCE_1"));
  			linkedSujet.setReference2(resultSet.getString(
  					"V_SU_REFERENCE_2"));
  			linkedSujet.setNumeroFiche(resultSet.getString(
  					"V_SU_REFERENCE_3"));
  			linkedSujet.setSeverite(resultSet.getLong("I_SE_CLE"));
  			linkedSujet.setSeveriteAutres(resultSet.getLong("I_SE_CLE_AUTRES"));
  			linkedSujet.setSeveriteCasino(resultSet.getLong("I_SE_CLE_CASINO"));
  			linkedSujet.setPasseport(resultSet.getString(
  					"V_SU_NO_PASSEPORT"));
  			log.debug("   [SUJET id='" + linkedSujet.getNumeroFiche()
  					+ "' Nom='"+linkedSujet.getNom() + "']");
  			linkedSujet.setLien(resultSet.getLong("L_LDD_CLE"));
  			linkedSujet.setLienSite(resultSet.getLong("L_LDD_SI_CLE"));
  			linkedSujet.setLienCreateur(resultSet.getString("V_LDD_CREE_PAR"));
  			linkedSujet.setRole(resultSet.getLong("I_RO_CLE"));
  			linkedSujet.setLienDateCreation(resultSet.getTimestamp("D_LDD_DATE_CREATION"));
  			linkedSujet.setIndicateurRdd(OracleDAOUtils.convertirStringABoolean(resultSet.getString("B_SU_IND_RDD")));
  			linkedSujet.setDateFinEmploi(resultSet.getTimestamp("D_SU_DATE_FIN_EMPLOI"));
  			linkedSujet.setDateFinEnquete(resultSet.getTimestamp("DATE_FIN_ENQUETE"));
  			log.debug("   [SUJET id='" + linkedSujet.getNumeroFiche()
  					+ "' Nom='" + linkedSujet.getNom() + "']");
  		return linkedSujet;
  	}
	
  	public void assignerCourantLog(Logger courantLog) {
		this.courantLog = courantLog;
	}
  	
  	public void assignerLocalCourantLog() {
		this.courantLog = log;
	}
  	
}