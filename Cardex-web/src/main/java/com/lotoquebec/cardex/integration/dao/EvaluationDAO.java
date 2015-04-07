package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Evaluation;
import com.lotoquebec.cardex.business.FrequenceVisites;
import com.lotoquebec.cardex.business.Jeux;
import com.lotoquebec.cardex.business.vo.EvaluationVO;
import com.lotoquebec.cardex.business.vo.FrequenceVisitesVO;
import com.lotoquebec.cardex.business.vo.MiseEvaluationVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.CallStatementHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;

/**
 * Liste des appels � la base de donn�es pour diff�rents acc�s aux
 * �valulation.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.11 $, $Date: 2002/05/02 13:06:09 $
 * @see com.lotoquebec.cardex.integration.ConsignationDAO
 */

public class EvaluationDAO {

   public EvaluationDAO(){

   }

   private final Logger      log =
       LoggerFactory.getLogger((EvaluationDAO.class));
   
   private Map TYPES_CONSIGNATION = null;

/**
 * �criture d'une �valuation, appel�e par la m�thode "insert", "update",
 * "approbation" ou "delete".
 * Selon le param�tre "action" il peut s'agir d'une insertion ("I")
 * d'une mise � jour ("U"), d'une approbation et modification ("M") ou
 * d'une suppression ("D").
 * Proc�dure appel�e : CARDEX_LIEN.SP_E_EV_EVALUATION
 * Date de cr�ation : (2012-05-31)
 * @author Fran�ois Gu�rin
 * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
 * l'utilisateur.
 * @param evaluation Evaluation : evaluation saisie � l'�cran.
 * @param action  java.lang.String : U ou I
 * @param genreFichier String : code � deux lettres de la table qui lie laconsignation
 * (Dossier (DO).
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Consignation
 */
   private Evaluation editEvaluation(CardexAuthenticationSubject subject, Evaluation evaluation,
		String action) throws DAOException {
		Connection connection
			= DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
	  try {
		  final Evaluation newEvaluation = new EvaluationVO();
		  
		  callableStatement =
			  connection.prepareCall("begin CARDEX_LIEN.SP_E_EV_EVALUATION "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
					+ "end;");
	         callableStatement.setString(1,action);
			  callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
			  OracleDAOUtils.setLong(callableStatement,2, evaluation.getCle());
			  callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
			  OracleDAOUtils.setLong(callableStatement,3, evaluation.getSite());
			  OracleDAOUtils.setLong(callableStatement,4, evaluation.getLien());
			  OracleDAOUtils.setLong(callableStatement,5, evaluation.getLienSite());
			  callableStatement.setTimestamp(6,(Timestamp)(evaluation.getDateDebutEval()));
			  callableStatement.setTimestamp(7, (Timestamp)(evaluation.getDateFinEval()));
			  OracleDAOUtils.setLong(callableStatement,8, evaluation.getNumeroClientBijou());
			  callableStatement.setString(9,evaluation.getFaitsConnus());
			  callableStatement.setString(10,evaluation.getProximite());
			  callableStatement.setString(11,evaluation.getGradation());
			  callableStatement.setString(12,evaluation.getTransaction());
			  callableStatement.setString(13,evaluation.getCommentaireEtat());
			  callableStatement.setString(14,evaluation.getCommentairePropos());
			  callableStatement.setString(15,evaluation.getCommentaireAutre());
			  callableStatement.setString(16,evaluation.getCommentaireSigne());
			  callableStatement.setString(17,evaluation.getSignataire1());
			  callableStatement.setString(18,evaluation.getSignataire2());
			  callableStatement.setString(19,evaluation.getSignataire3());
			  callableStatement.setString(20,evaluation.getSignataire4());
			  callableStatement.setString(21,evaluation.getSignataire5());
			  callableStatement.setTimestamp(22,(Timestamp)(evaluation.getDateEvaluation()));
			  callableStatement.execute();
			  
			  newEvaluation.setCle(callableStatement.getLong(2));
			  newEvaluation.setSite(callableStatement.getLong(3));

			  
			  //Sauvegarde des mises
			  // On supprime d'abbords les mises et les sous tables (jeux et fr�quence visite)
				StoreProcTemplate suppressionMisesSP = new StoreProcTemplate(subject);
				PreparerCallableStatement suppressionMisesPCS = new PreparerCallableStatement(){
					
					public void preparer(CallableStatement callableStatement) throws SQLException {
						callableStatement.setString(1, "C");
				        callableStatement.setNull(2, Types.DECIMAL);
				        callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
				        callableStatement.setNull(3, Types.DECIMAL);
				        callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
				        callableStatement.setLong(4, newEvaluation.getCle());
				        callableStatement.setLong(5, newEvaluation.getSite());
				        callableStatement.setNull(6, Types.DECIMAL);
				        callableStatement.setNull(7, Types.VARCHAR);
				        callableStatement.setNull(8, Types.DOUBLE);
				        callableStatement.setNull(9, Types.DOUBLE);
				        callableStatement.setNull(10, Types.DOUBLE);
				        callableStatement.setNull(11, Types.DOUBLE);
				        callableStatement.setNull(12, Types.DOUBLE);
				        callableStatement.setNull(13, Types.DECIMAL);
				        callableStatement.setNull(14, Types.DECIMAL);
				        callableStatement.setNull(15, Types.DOUBLE);
					}
				};		
				suppressionMisesSP.prepareCall("CARDEX_LIEN.SP_E_LME_LIEN_MISE_EVAL", 15, suppressionMisesPCS);
				suppressionMisesSP.query(true);	
				
				// On ajoute ensuite les jeux choisis si l'action n'est pas une suppression
				for (final MiseEvaluationVO miseEvaluationVO:evaluation.getMisesEvaluation()){
					StoreProcTemplate ajoutMiseSP = new StoreProcTemplate(subject);
					PreparerCallableStatement ajoutMisePCS = new PreparerCallableStatement(){
						public void preparer(CallableStatement callableStatement) throws SQLException {
							callableStatement.setString(1, "I");
							callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
							OracleDAOUtils.setLong(callableStatement,2, miseEvaluationVO.getCle());
							callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
							OracleDAOUtils.setLong(callableStatement,3, miseEvaluationVO.getSite());
					        callableStatement.setLong(4, newEvaluation.getCle());
					        callableStatement.setLong(5, newEvaluation.getSite());
					        callableStatement.setLong(6, miseEvaluationVO.getTypeJeu());
					        callableStatement.setString(7, miseEvaluationVO.getCommentaireJeu());
							callableStatement.setDouble(8, miseEvaluationVO.getMiseMinimum());
							callableStatement.setDouble(9, miseEvaluationVO.getMiseMaximum());
							callableStatement.setDouble(10, miseEvaluationVO.getMiseMoyenne());
							callableStatement.setDouble(11, miseEvaluationVO.getGainPerte());
							callableStatement.setDouble(12, miseEvaluationVO.getMiseTotal());
							OracleDAOUtils.setLong(callableStatement,13, miseEvaluationVO.getTempsJeuMinimum());
							OracleDAOUtils.setLong(callableStatement,14, miseEvaluationVO.getTempsJeuMaximum());
							callableStatement.setDouble(15, miseEvaluationVO.getTempsJeuTotal());
						}
					};		
					ajoutMiseSP.prepareCall("CARDEX_LIEN.SP_E_LME_LIEN_MISE_EVAL", 15, ajoutMisePCS);

					CallStatementHandler ajoutMiseCSH = new CallStatementHandler(){
						public void process(CallableStatement callableStatement)throws SQLException {
			  			   miseEvaluationVO.setCle(callableStatement.getLong(2));
			  			   miseEvaluationVO.setSite(callableStatement.getLong(3));	
						}
			  	    };
					ajoutMiseSP.query(ajoutMiseCSH, true);
					
					// Ajout des jeux
					for (final Long jeu:miseEvaluationVO.getJeux()){
						StoreProcTemplate ajoutJeuSP = new StoreProcTemplate(subject);
						PreparerCallableStatement ajoutJeuPCS = new PreparerCallableStatement(){
							public void preparer(CallableStatement callableStatement) throws SQLException {
					              callableStatement.setString(1, "I");
					              callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
					              callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
					              OracleDAOUtils.setLong(callableStatement,4, miseEvaluationVO.getCle());
					              OracleDAOUtils.setLong(callableStatement,5, miseEvaluationVO.getSite());
					              OracleDAOUtils.setLong(callableStatement,6, jeu);
							}
						};		
						ajoutJeuSP.prepareCall("CARDEX_LIEN.SP_E_LJV_JEU_EVAL", 6, ajoutJeuPCS);
						ajoutJeuSP.query(true);
					}
					
					// Ajout fr�quences
					for (final FrequenceVisitesVO frequenceVisitesVO:miseEvaluationVO.getFrequenceVisites()){
						StoreProcTemplate ajoutFrequencesSP = new StoreProcTemplate(subject);
						PreparerCallableStatement ajoutFrequencesPCS = new PreparerCallableStatement(){
							public void preparer(CallableStatement callableStatement) throws SQLException {
				                  callableStatement.setString(1, "I");
				                  callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
				                  callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
					              OracleDAOUtils.setLong(callableStatement,4, miseEvaluationVO.getCle());
					              OracleDAOUtils.setLong(callableStatement,5, miseEvaluationVO.getSite());
				                  callableStatement.setString(6, frequenceVisitesVO.getPeriode());
				                  OracleDAOUtils.setLong(callableStatement,7, frequenceVisitesVO.getNombreVisites());
				                  OracleDAOUtils.setLong(callableStatement,8, frequenceVisitesVO.getGainPerte());
							}
						};		
						ajoutFrequencesSP.prepareCall("CARDEX_LIEN.SP_E_LVE_VISITE_EVAL", 8, ajoutFrequencesPCS);
						ajoutFrequencesSP.query(true);
					}
				}
			  //Sauvegarde des �tats d'esprit associ�s
              Collection etatsChoisis = evaluation.getEtats();
              Iterator itEtats = etatsChoisis.iterator();
              //On supprime d'abord les �tats d�j� associ�s
              callableStatement = connection.prepareCall("begin CARDEX_LIEN.SP_E_LEE_ETAT_EVAL (?,?,?,?,?,?); end;");
              callableStatement.setString(1, "C");
              callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
              callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
              OracleDAOUtils.setLong(callableStatement,4, newEvaluation.getCle());
              OracleDAOUtils.setLong(callableStatement,5, newEvaluation.getSite());
              OracleDAOUtils.setLong(callableStatement,6, 0);
              callableStatement.execute();      
              //On ajoute ensuite les �tats choisis si l'action n'est pas une suppression
              if(!action.equals("D")){
	              while (itEtats.hasNext()){
	                  String etat = (String)itEtats.next();
	                  callableStatement = connection.prepareCall("begin CARDEX_LIEN.SP_E_LEE_ETAT_EVAL (?,?,?,?,?,?); end;");
	                  callableStatement.setString(1, "I");
	                  callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
	                  callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
	                  OracleDAOUtils.setLong(callableStatement,4, newEvaluation.getCle());
	                  OracleDAOUtils.setLong(callableStatement,5, newEvaluation.getSite());
	                  OracleDAOUtils.setLong(callableStatement,6, Long.parseLong(etat));
	                  callableStatement.execute();
	              }
              }
			  //Sauvegarde des propos du client associ�s
              Collection proposChoisis = evaluation.getPropos();
              Iterator itPropos = proposChoisis.iterator();
              //On supprime d'abord les propos d�j� associ�s
              callableStatement = connection.prepareCall("begin CARDEX_LIEN.SP_E_LPE_PROPOS_EVAL (?,?,?,?,?,?); end;");
              callableStatement.setString(1, "C");
              callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
              callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
              OracleDAOUtils.setLong(callableStatement,4, newEvaluation.getCle());
              OracleDAOUtils.setLong(callableStatement,5, newEvaluation.getSite());
              OracleDAOUtils.setLong(callableStatement,6, 0);
              callableStatement.execute();      
              //On ajoute ensuite les propos choisis si l'action n'est pas une suppression
              if(!action.equals("D")){
	              while (itPropos.hasNext()){
	                  String propos = (String)itPropos.next();
	                  callableStatement = connection.prepareCall("begin CARDEX_LIEN.SP_E_LPE_PROPOS_EVAL (?,?,?,?,?,?); end;");
	                  callableStatement.setString(1, "I");
	                  callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
	                  callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
	                  OracleDAOUtils.setLong(callableStatement,4, newEvaluation.getCle());
	                  OracleDAOUtils.setLong(callableStatement,5, newEvaluation.getSite());
	                  OracleDAOUtils.setLong(callableStatement,6, Long.parseLong(propos));
	                  callableStatement.execute();
	              }
              }
              
			  return newEvaluation;
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
 * Appel de la m�thode editEvaluation pour la cr�ation d'une �valuation
 * Date de cr�ation : (2012-05-31)
 * @author guerinf
 * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
 * l'utilisateur.
 * @param evaluation Evaluation : Evaluation saisie � l'�cran.
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Consignation
 */
	public Evaluation insert(CardexAuthenticationSubject subject, Evaluation evaluation) throws DAOException {
	  return editEvaluation(subject, evaluation, "I");
	}

/**
 * Appel de la m�thode editEvaluation pour la mise � jour d'une evaluation
 * Date de cr�ation : (2012-05-31)
 * @author guerinf
 * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
 * l'utilisateur.
 * @param consignation Consignation : Consignation saisie � l'�cran.
 * @param genreFichier String : Code identifiant la table source qui lie un
 * consignation � un Dossier.
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 */
	public void update(CardexAuthenticationSubject subject, Evaluation evaluation) throws DAOException {
	  editEvaluation(subject, evaluation, "U");
	}


/**
 * Appel de la m�thode editEvaluation pour la suppression d'une evaluation
 * Date de cr�ation : (2012-05-31)
 * @author guerinf
 * @param subject CardexAuthenticationSubject : Donn�es nominatives sur
 * l'utilisateur.
 * @param consignation Consignation : Consignation saisie � l'�cran.
 * @param genreFichier String : Code identifiant la table source qui lie un
 * consignation � un Dossier.
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Consignation
 */
	public Evaluation delete(CardexAuthenticationSubject subject, Evaluation evaluation) throws DAOException {
	  return editEvaluation(subject, evaluation, "D");
	}

/**
 * Lecture des consignations associ�s � une entit� Dossier.
 * Proc�dure appel�e : CARDEX_LIRE_LIEN.SP_L_EV_EVALUATION
 * Date de cr�ation : (2012-12-31)
 * @author guerinf
 * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
 * l'utilisateur.
 * @param cle long : cl� de r�f�rence de l'entit�
 * @param site long : site de r�f�rence de l'entit�
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Collection : liste des �valuations associ�es
 */
	public Collection findLiensEvaluation(CardexAuthenticationSubject subject,
			long cle, long site) throws DAOException {
	  log.debug("findLiensEvaluation()");
	  Connection connection
			= DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;

	  try {
		 callableStatement =
			connection.prepareCall("begin CARDEX_LIRE_LIEN.SP_L_EV_EVALUATION (?,?,?); end;");
		 callableStatement.setLong(1,cle);
		 callableStatement.setLong(2,site);
		 callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
		 callableStatement.execute();
		 resultSet = (ResultSet)callableStatement.getObject(3);
		 ArrayList results = new ArrayList();
		 while (resultSet.next()){
			 EvaluationVO linkedEvaluation = new EvaluationVO();
			 linkedEvaluation.setCle(resultSet.getLong("L_EV_CLE"));
			 linkedEvaluation.setSite(resultSet.getLong("L_SI_CLE"));
			 linkedEvaluation.setLien(resultSet.getLong("L_EV_REF_CLE"));
			 linkedEvaluation.setLienSite(resultSet.getLong("L_EV_REF_SITE"));
	         linkedEvaluation.setDateDebutEval(resultSet.getTimestamp("D_DATE_DEBUT_EVAL"));
	         linkedEvaluation.setDateFinEval(resultSet.getTimestamp("D_DATE_FIN_EVAL"));
	         linkedEvaluation.setNumeroClientBijou(resultSet.getLong("L_EV_NO_CLIENT_BIJOU"));
	         linkedEvaluation.setFaitsConnus(resultSet.getString("V_EV_FAITS_CONNUS"));
	         linkedEvaluation.setProximite(resultSet.getString("V_EV_PROXIMITE"));  
	         linkedEvaluation.setGradation(resultSet.getString("V_EV_GRADATION"));  
	         linkedEvaluation.setTransaction(resultSet.getString("V_EV_TRANSACTION"));
	         linkedEvaluation.setCommentaireEtat(resultSet.getString("V_EV_COMMENTAIRE_ETAT"));
	         linkedEvaluation.setCommentairePropos(resultSet.getString("V_EV_COMMENTAIRE_PROPOS"));
	         linkedEvaluation.setCommentaireAutre(resultSet.getString("V_EV_COMMENTAIRE_AUTRE"));
	         linkedEvaluation.setCommentaireSigne(resultSet.getString("V_EV_COMMENTAIRE_SIGNE"));
			 linkedEvaluation.setSignataire1(resultSet.getString("V_EV_SIGNATAIRE1"));
			 linkedEvaluation.setSignataire2(resultSet.getString("V_EV_SIGNATAIRE2"));
			 linkedEvaluation.setSignataire3(resultSet.getString("V_EV_SIGNATAIRE3"));
			 linkedEvaluation.setSignataire4(resultSet.getString("V_EV_SIGNATAIRE4"));
			 linkedEvaluation.setSignataire5(resultSet.getString("V_EV_SIGNATAIRE5"));
			 linkedEvaluation.setCreateur(resultSet.getString("V_EV_CREE_PAR"));
			 linkedEvaluation.setDateCreation(resultSet.getTimestamp("D_EV_DATE_CREATION"));    
			 linkedEvaluation.setModificateur(resultSet.getString("V_EV_MODIFIE_PAR"));
			 linkedEvaluation.setDateModification(resultSet.getTimestamp("D_EV_DATE_MODIFICATION")); 
		     linkedEvaluation.setDateEvaluation(resultSet.getTimestamp("D_EV_DATE_EVALUATION"));
		     results.add(linkedEvaluation);
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
 * Routine pour traiter les ResultSet retourn�s par les recherches de consignation.
 * Date de cr�ation : (2002-02-27)
 * @author Philippe Caron
 * @param resultSet  ResultSet : Donn�es retourn�es par une recherche.
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return ArrayList : liste des r�sultats trait�s.
 */
/*    public RowCallbackHandler constuireRowCallBackHandler(final List<Consignation> listDossier){
  	   return new RowCallbackHandler(){
  		   public void processRow(ResultSet resultSet) throws SQLException {
  			   Consignation consignation = construireConsignation(resultSet);
               listDossier.add(consignation); 					
  		   }
  	   };
     }	
*/	
 
/**
 * Recherche directe d'une �valuation par sa cl� unique.
 * Proc�dure appel�e : CARDEX_LIRE_LIEN.SP_L2_EV_EVALUATION
 * Date de cr�ation : (2012-05-31)
 * @author GUERINF
 * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
 * l'utilisateur.
 * @param criteria Evaluation : Evaluation � rechercher.
 * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
 * rupture de connexion avec la base de donn�es, ou que la table demand�e est
 * non disponible, ou qu'un probl�me est survenu lors de l'ex�cution d'une
 * "stored procedure".
 * @return Consignation : Donn�es du dossier trouv�.
 */
	public Evaluation find(CardexAuthenticationSubject subject, Evaluation criteria)
			throws DAOException {
	  Connection connection
			= DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
	  EvaluationVO evaluationVO = new EvaluationVO();

	  try {
		 callableStatement =
		 connection.prepareCall("begin CARDEX_LIRE_LIEN.SP_L2_EV_EVALUATION (?,?,?); end;");
		 callableStatement.setLong(1,criteria.getCle());
		 callableStatement.setLong(2,criteria.getSite());
		 callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
		 callableStatement.execute();
		 resultSet = (ResultSet)callableStatement.getObject(3);
		 //Traitement du r�sultat retourn�
		 if (resultSet.next()) {
			 evaluationVO.setCle(resultSet.getLong("L_EV_CLE"));
			 evaluationVO.setSite(resultSet.getLong("L_SI_CLE"));
			 evaluationVO.setLien(resultSet.getLong("L_EV_REF_CLE"));
			 evaluationVO.setLienSite(resultSet.getLong("L_EV_REF_SITE"));
	         evaluationVO.setDateDebutEval(resultSet.getTimestamp("D_DATE_DEBUT_EVAL"));
	         evaluationVO.setDateFinEval(resultSet.getTimestamp("D_DATE_FIN_EVAL"));
	         evaluationVO.setNumeroClientBijou(resultSet.getLong("L_EV_NO_CLIENT_BIJOU"));
	         evaluationVO.setFaitsConnus(resultSet.getString("V_EV_FAITS_CONNUS"));
	         evaluationVO.setProximite(resultSet.getString("V_EV_PROXIMITE"));  
	         evaluationVO.setGradation(resultSet.getString("V_EV_GRADATION"));  
	         evaluationVO.setTransaction(resultSet.getString("V_EV_TRANSACTION"));
	         evaluationVO.setCommentaireEtat(resultSet.getString("V_EV_COMMENTAIRE_ETAT"));
	         evaluationVO.setCommentairePropos(resultSet.getString("V_EV_COMMENTAIRE_PROPOS"));
	         evaluationVO.setCommentaireAutre(resultSet.getString("V_EV_COMMENTAIRE_AUTRE"));
	         evaluationVO.setCommentaireSigne(resultSet.getString("V_EV_COMMENTAIRE_SIGNE"));
			 evaluationVO.setSignataire1(resultSet.getString("V_EV_SIGNATAIRE1"));
			 evaluationVO.setSignataire2(resultSet.getString("V_EV_SIGNATAIRE2"));
			 evaluationVO.setSignataire3(resultSet.getString("V_EV_SIGNATAIRE3"));
			 evaluationVO.setSignataire4(resultSet.getString("V_EV_SIGNATAIRE4"));
			 evaluationVO.setSignataire5(resultSet.getString("V_EV_SIGNATAIRE5"));
			 evaluationVO.setCreateur(resultSet.getString("V_EV_CREE_PAR"));
			 evaluationVO.setDateCreation(resultSet.getTimestamp("D_EV_DATE_CREATION"));    
			 evaluationVO.setModificateur(resultSet.getString("V_EV_MODIFIE_PAR"));
			 evaluationVO.setDateModification(resultSet.getTimestamp("D_EV_DATE_MODIFICATION")); 
		     evaluationVO.setDateEvaluation(resultSet.getTimestamp("D_EV_DATE_EVALUATION"));
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
	  return evaluationVO;
	}

    /**
     * Retourne les jeux associ�s � une �valuation.  La proc�dure appel�e est dans
     * OracleJeuDAO.
     * Date de cr�ation : (2002-03-05)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param dossier Dossier : Dossier de base.
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Collection : Liste des jeux associ�s.
     */
    public Jeux findLiensJeux(CardexAuthenticationSubject subject,
    		MiseEvaluationVO miseEvaluationVO) throws DAOException {
        return FabriqueCardexDAO.getInstance().getJeuDAO().findLiensJeuxMiseEvaluation(subject, miseEvaluationVO.getCle(),
        		miseEvaluationVO.getSite());
    }

    /**
     * Lecture des �tats d'esprit associ�s � une �valuation.
     * Proc�dure appel�e : SPW_L2_LIEN_ETAT_EVAL
     * Date de cr�ation : (2002-03-04)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param cle long : Cl� de r�f�rence du sujet.
     * @param site long : Site de r�f�rence du sujet.
     * @param genreFichier String : ("DO").
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Liste : Liste des �tats associ�s.
     */
    public List findLiensEtats(CardexAuthenticationSubject subject, Evaluation critere) throws DAOException {
      log.debug("findLiensEtats()");
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;

      try {
         callableStatement =
            connection.prepareCall("begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L2_LIEN_ETAT_EVAL (?,?,?); end;");
         callableStatement.setLong(1,critere.getCle());
         callableStatement.setLong(2,critere.getSite());
         callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(3);
         List etats = new ArrayList();
         while (resultSet.next()){
              etats.add(String.valueOf(resultSet.getLong("L_EE_CLE")));
              log.debug("   [Etat ='" + resultSet.getLong("L_EE_CLE")+"']");
         }//while
           return etats;
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
     * Lecture des propos du client associ�s � une �valuation.
     * Proc�dure appel�e : SPW_L2_LIEN_PROPOS_EVAL
     * Date de cr�ation : (2002-03-04)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param cle long : Cl� de r�f�rence du sujet.
     * @param site long : Site de r�f�rence du sujet.
     * @param genreFichier String : ("DO").
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Liste : Liste des �tats associ�s.
     */
    public List findLiensPropos(CardexAuthenticationSubject subject, Evaluation critere) throws DAOException {
      log.debug("findLiensPropos()");
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;

      try {
         callableStatement =
            connection.prepareCall("begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L2_LIEN_PROPOS_EVAL (?,?,?); end;");
         callableStatement.setLong(1,critere.getCle());
         callableStatement.setLong(2,critere.getSite());
         callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(3);
         List propos = new ArrayList();
         while (resultSet.next()){
        	 propos.add(String.valueOf(resultSet.getLong("L_PE_CLE")));
              log.debug("   [Etat ='" + resultSet.getLong("L_PE_CLE")+"']");
         }//while
           return propos;
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
     * Lecture des fr�quences de visites du client associ�s � une �valuation.
     * Proc�dure appel�e : SPW_L_LIEN_VISITE_EVAL
     * Date de cr�ation : (2002-03-04)
     * @author Philippe Caron
     * @param subject  CardexAuthenticationSubject : Donn�es nominatives sur
     * l'utilisateur.
     * @param cle long : Cl� de r�f�rence du sujet.
     * @param site long : Site de r�f�rence du sujet.
     * @param genreFichier String : ("DO").
     * @throws DAOException lanc�e lorsqu'une SQLException est re�ue lors d'une
     * rupture de connexion avec la base de donn�es, ou que la table demand�e
     * est non disponible, ou qu'un probl�me est survenu lors de l'ex�cution
     * d'une "stored procedure".
     * @return Liste : Liste des �tats associ�s.
     */
    public List<FrequenceVisites> findLiensFrequenceVisites(CardexAuthenticationSubject subject, MiseEvaluationVO miseEvaluationVO) throws DAOException {
      log.debug("findLiensFrequenceVisites()");
      Connection connection = DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;

      try {
         callableStatement =
            connection.prepareCall("begin CARDEX_WEB_LIRE_DOC_TRI.SPW_L_LIEN_VISITE_EVAL (?,?,?); end;");
         callableStatement.setLong(1,miseEvaluationVO.getCle());
         callableStatement.setLong(2,miseEvaluationVO.getSite());
         callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
         callableStatement.execute();
         resultSet = (ResultSet)callableStatement.getObject(3);
         List<FrequenceVisites> results = new ArrayList();
         while ( resultSet.next() ) {
        	 FrequenceVisitesVO frequenceVisites = new FrequenceVisitesVO();
        	 frequenceVisites.setCle(resultSet.getLong("L_LVE_CLE"));
        	 frequenceVisites.setSite(resultSet.getLong("L_SI_CLE"));
        	 frequenceVisites.setLien(resultSet.getLong("L_LME_CLE"));
        	 frequenceVisites.setLienSite(resultSet.getLong("L_LME_REF_SITE"));
        	 frequenceVisites.setPeriode(resultSet.getString("V_LVE_PERIODE"));
        	 frequenceVisites.setNombreVisites(resultSet.getLong("L_LVE_NOMBRE_VISITE"));
        	 frequenceVisites.setGainPerte(resultSet.getLong("L_LVE_GAIN_PERTE"));
        	 frequenceVisites.setCreateur(resultSet.getString("V_LVE_CREE_PAR"));
        	 frequenceVisites.setDateCreation(resultSet.getTimestamp("D_LVE_DATE_CREATION"));
             results.add(frequenceVisites);
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

    /*
     * 
     */
	public List<MiseEvaluationVO> findLiensMisesEvaluation(CardexAuthenticationSubject subject, final Evaluation evaluation) throws DAOException{
		final List<MiseEvaluationVO> misesEvaluation = new ArrayList<MiseEvaluationVO>();
		
		StoreProcTemplate liensMisesEvaluationSP = new StoreProcTemplate(subject);
		PreparerCallableStatement liensMisesEvaluationPCS = new PreparerCallableStatement(){
			public void preparer(CallableStatement callableStatement) throws SQLException {
    			OracleDAOUtils.setLong(callableStatement, 1, evaluation.getCle()); // NEW_L_EV_CLE IN LME_LIEN_MISE_EVAL.L_EV_CLE%TYPE,
    			OracleDAOUtils.setLong(callableStatement, 2, evaluation.getSite()); // NEW_L_EV_SI_CLE  IN LME_LIEN_MISE_EVAL.L_EV_SI_CLE%TYPE,
    			callableStatement.registerOutParameter(3, OracleTypes.CURSOR); // rc1          OUT DTS_LIEN_BILLET
			}
		};		
		liensMisesEvaluationSP.prepareCall("CARDEX_LIRE_LIEN.SP_L_LME_LIEN_MISE_EVAL", 3, 3, liensMisesEvaluationPCS);
		
		RowCallbackHandler liensMisesEvaluationRCBH = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				MiseEvaluationVO miseEvaluationVO = new MiseEvaluationVO();
				
				miseEvaluationVO.setCle(rs.getLong("L_LME_CLE"));
				miseEvaluationVO.setSite(rs.getLong("L_SI_CLE"));
				miseEvaluationVO.setLien(rs.getLong("L_EV_CLE"));
				miseEvaluationVO.setLienSite(rs.getLong("L_EV_SI_CLE"));
				miseEvaluationVO.setTypeJeu(rs.getLong("L_LME_TYPE_JEU"));
				miseEvaluationVO.setCommentaireJeu(rs.getString("V_LME_COMMENTAIRE_JEU"));
				miseEvaluationVO.setMiseMinimum(rs.getDouble("L_LME_MISE_MINIMUM"));
				miseEvaluationVO.setMiseMaximum(rs.getDouble("L_LME_MISE_MAXIMUM"));
				miseEvaluationVO.setMiseMoyenne(rs.getDouble("L_LME_MISE_MOYENNE"));
				miseEvaluationVO.setGainPerte(rs.getDouble("L_LME_GAIN_PERTE"));
				miseEvaluationVO.setMiseTotal(rs.getDouble("L_LME_MISE_TOTAL"));
				miseEvaluationVO.setTempsJeuMinimum(rs.getLong("L_LME_TEMPS_JEU_MIN"));
				miseEvaluationVO.setTempsJeuMaximum(rs.getLong("L_LME_TEMPS_JEU_MAX"));
				miseEvaluationVO.setTempsJeuTotal(rs.getDouble("L_LME_TEMPS_JEU_TOTAL"));
				miseEvaluationVO.setCreateur(rs.getString("V_LME_CREE_PAR"));
				miseEvaluationVO.setDateCreation(rs.getTimestamp("D_LME_DATE_CREATION"));
				
				misesEvaluation.add(miseEvaluationVO);
			}
    	};		
		
		liensMisesEvaluationSP.query(liensMisesEvaluationRCBH, true);
		
		return misesEvaluation;
	}
}