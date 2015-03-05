package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.business.Consignation;
import com.lotoquebec.cardex.business.CriteresRechercheConsignation;
import com.lotoquebec.cardex.business.vo.ConsignationVO;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.integration.dao.sql.recherche.ConsignationCompletSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.ConsignationCountSQL;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.UnEnregistrementPresent;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;

/**
 * Liste des appels à la base de données pour différents accès aux
 * consignations.  Implémente l'interface ConsignationDAO.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.11 $, $Date: 2002/05/02 13:06:09 $
 * @see com.lotoquebec.cardex.integration.ConsignationDAO
 */

public class ConsignationDAO {

   public ConsignationDAO(){

   }

   private final Logger      log =
       (Logger)LoggerCardex.getLogger((ConsignationDAO.class));
   
   private Map TYPES_CONSIGNATION = null;

/**
 * Écriture d'une consignation, appelée par la méthode "insert", "update",
 * "approbation" ou "delete".
 * Selon le paramètre "action" il peut s'agir d'une insertion ("I")
 * d'une mise à jour ("U"), d'une approbation et modification ("M") ou
 * d'une suppression ("D").
 * Procédure appelée : CARDEX_LIEN.SP_E_CN_CONSIGNATION
 * Date de création : (2006-05-29)
 * @author François Guérin
 * @param subject CardexAuthenticationSubject : Données nominatives sur
 * l'utilisateur.
 * @param consignation Consignation : consignation saisie à l'écran.
 * @param action  java.lang.String : U ou I
 * @param genreFichier String : code à deux lettres de la table qui lie laconsignation
 * (Dossier (DO).
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Consignation
 */
   private Consignation editConsignation(CardexAuthenticationSubject subject, Consignation consignation,
		String action, String genreFichier) throws DAOException {
		Connection connection
			= DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
	  try {
		  callableStatement =
			  connection.prepareCall("begin CARDEX_LIEN.SP_E_CN_CONSIGNATION "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
					+ "end;");
			  callableStatement.setString(1,action);
			  callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
			  OracleDAOUtils.setLong(callableStatement,2, consignation.getCle());
			  callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
			  OracleDAOUtils.setLong(callableStatement,3, consignation.getSite());
			  OracleDAOUtils.setLong(callableStatement,4, consignation.getLien());
			  OracleDAOUtils.setLong(callableStatement,5, consignation.getLienSite());
			  callableStatement.setString(6,genreFichier);
			  callableStatement.setLong(7, consignation.getDevise());
			  OracleDAOUtils.setLong(callableStatement,8, consignation.getTypeConsignation());
			  OracleDAOUtils.setLong(callableStatement,9, consignation.getQuantite());
			  callableStatement.setDouble(10,consignation.getPrix());
			  callableStatement.setDouble(11,consignation.getMontant());
			  callableStatement.setString(12,consignation.getPoids());
			  callableStatement.setString(13, consignation.getDimension());
			  callableStatement.setString(14, consignation.getMarque().toUpperCase());
			  callableStatement.setString(15,consignation.getModele().toUpperCase());
			  callableStatement.setString(16, consignation.getFournisseur().toUpperCase());
			  callableStatement.setString(17,consignation.getDescription());
			  callableStatement.setString(18,consignation.getCommentaire());
			  callableStatement.setString(19,consignation.getNumeroSerie().toUpperCase());
			  callableStatement.setString(20,consignation.getCreateur());
			  callableStatement.setTimestamp(21,(Timestamp)(consignation.getDateCreation()));
			  callableStatement.setString(22,consignation.getModificateur());
			  callableStatement.setTimestamp(23,(Timestamp)(consignation.getDateModification()));
			  callableStatement.setString(24,consignation.getApprobateur());
			  callableStatement.setTimestamp(25,(Timestamp)(consignation.getDateApprobation()));
			  String approuvable = isApprouvable(subject,consignation.getTypeConsignation());
			  callableStatement.setString(26, approuvable);
			  callableStatement.setString(27, OracleDAOUtils.convertirBooleanAString(consignation.getApprouve()));
			  if(!approuvable.equals("yes")){
				   callableStatement.setString(27, "no");
			  }
			  callableStatement.setString(28, consignation.getReference1());
			  callableStatement.setString(29, consignation.getReference2());
			  callableStatement.setLong(30, consignation.getDenomination());

			  callableStatement.execute();
			  Consignation newConsignation = new ConsignationVO();
			  newConsignation.setCle(callableStatement.getLong(2));
			  newConsignation.setSite(callableStatement.getLong(3));
			  return newConsignation;
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
 * Appel de la méthode editConsignation pour la création d'un consignation
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param subject CardexAuthenticationSubject : Données nominatives sur
 * l'utilisateur.
 * @param consignation Consignation : Consignation saisie à l'écran.
 * @param genreFichier String : Code identifiant la table source qui lie un
 * consignation à un Dossier.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Consignation
 */
	public Consignation insert(CardexAuthenticationSubject subject, Consignation consignation,
			String genreFichier) throws DAOException {
	  return editConsignation(subject, consignation, "I", genreFichier);
	}

/**
 * Appel de la méthode editConsignation pour la mise à jour d'une consignation
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param subject CardexAuthenticationSubject : Données nominatives sur
 * l'utilisateur.
 * @param consignation Consignation : Consignation saisie à l'écran.
 * @param genreFichier String : Code identifiant la table source qui lie un
 * consignation à un Dossier.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 */
	public void update(CardexAuthenticationSubject subject, Consignation consignation,
			String genreFichier) throws DAOException {
	  editConsignation(subject, consignation, "U", genreFichier);
	}

/**
 * Appel de la méthode editConsignation pour l'approbation ou la modification d'un
 * consignation.
 * L'approbation consiste à bloquer toute modification à un consignation.
 * La modification consiste à permettre de nouveau les modifications à un consignation
 * approuvé.
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param subject CardexAuthenticationSubject : Données nominatives sur
 * l'utilisateur.
 * @param consignation Consignation : Consignation saisie à l'écran.
 * @param genreFichier String : Code identifiant la table source qui lie un
 * consignation à un Dossier.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Consignation
 */
	public Consignation approbation(CardexAuthenticationSubject subject, Consignation consignation,
			String genreFichier) throws DAOException {
	  return editConsignation(subject, consignation, "M", genreFichier);
	}

/**
 * Appel de la méthode editConsignation pour la suppression d'un consignation
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param subject CardexAuthenticationSubject : Données nominatives sur
 * l'utilisateur.
 * @param consignation Consignation : Consignation saisie à l'écran.
 * @param genreFichier String : Code identifiant la table source qui lie un
 * consignation à un Dossier.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Consignation
 */
	public Consignation delete(CardexAuthenticationSubject subject, Consignation consignation,
			String genreFichier) throws DAOException {
	  return editConsignation(subject, consignation, "D", genreFichier);
	}

/**
 * Lecture des consignations associés à une entité Dossier.
 * Procédure appelée : CARDEX_WEB_LIRE_DOC_TRI.SPW_L_CN_CONSIGNATION
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param subject  CardexAuthenticationSubject : Données nominatives sur
 * l'utilisateur.
 * @param cle long : clé de référence de l'entité
 * @param site long : site de référence de l'entité
 * @param genreFichier String : identification de l'entité.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Collection : liste des consignations associées
 */
	public Collection findLiensConsignation(CardexAuthenticationSubject subject,
			long cle, long site, String genreFichier) throws DAOException {
	  log.fine("findLiensConsignation()");
	  Connection connection
			= DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;

	  try {
		 callableStatement =
			connection.prepareCall("begin SPW_L_CN_CONSIGNATION (?,?,?,?); end;");
		 callableStatement.setLong(1,cle);
		 callableStatement.setLong(2,site);
		 callableStatement.setString(3,genreFichier);
		 callableStatement.registerOutParameter(4, OracleTypes.CURSOR);
		 callableStatement.execute();
		 resultSet = (ResultSet)callableStatement.getObject(4);
		 ArrayList results = new ArrayList();
		 while (resultSet.next()){
			  ConsignationVO linkedConsignation = new ConsignationVO();
			  linkedConsignation.setCle(resultSet.getLong("L_CN_CLE"));
			  linkedConsignation.setSite(resultSet.getLong("L_SI_CLE"));
			  linkedConsignation.setLien(resultSet.getLong("L_CN_REF_CLE"));
			  linkedConsignation.setLienSite(resultSet.getLong("L_CN_REF_SITE"));
			  linkedConsignation.setLienGenre(resultSet.getString("C_CN_REF_GENRE"));
			  linkedConsignation.setDevise(resultSet.getLong("I_DE_CLE"));
			  linkedConsignation.setDenomination(resultSet.getLong("I_DN_CLE"));
			  linkedConsignation.setTypeConsignation(resultSet.getLong("I_TN_CLE"));
			  linkedConsignation.setQuantite(resultSet.getLong("L_CN_QUANTITE"));
			  linkedConsignation.setPrix(resultSet.getDouble("R_CN_PRIX"));
			  linkedConsignation.setMontant(resultSet.getDouble("R_CN_MONTANT"));  // ?
			  linkedConsignation.setPoids(resultSet.getString("V_CN_POIDS"));  // ?
			  linkedConsignation.setDimension(resultSet.getString("V_CN_DIMENSION"));// ?
			  linkedConsignation.setMarque(resultSet.getString("V_CN_MARQUE"));  // ?
			  linkedConsignation.setModele(resultSet.getString("V_CN_MODELE"));    // ?
			  linkedConsignation.setFournisseur(resultSet.getString("V_CN_FOURNISSEUR"));// ?
			  linkedConsignation.setDescription(resultSet.getString("V_CN_DESCRIPTION"));// ?
			  linkedConsignation.setCommentaire(resultSet.getString("V_CN_COMMENTAIRE"));// ?
			  linkedConsignation.setNumeroSerie(resultSet.getString("V_CN_NUMERO_SERIE"));
			  linkedConsignation.setCreateur(resultSet.getString("V_CN_CREE_PAR"));
			  linkedConsignation.setDateCreation(resultSet.getTimestamp("D_CN_DATE_CREATION"));    // ?
			  linkedConsignation.setModificateur(resultSet.getString("V_CN_MODIFIE_PAR"));
			  linkedConsignation.setDateModification(resultSet.getTimestamp("D_CN_DATE_MODIFICATION"));    // ?
			  linkedConsignation.setApprobateur(resultSet.getString("V_CN_APPROUVE_PAR"));// ?
			  linkedConsignation.setDateApprobation(resultSet.getTimestamp("D_CN_DATE_APPROBATION"));// ?
			  linkedConsignation.setApprouvable(OracleDAOUtils.convertirStringABoolean(resultSet.getString("C_CN_APPROUVABLE")));
			  linkedConsignation.setApprouve(OracleDAOUtils.convertirStringABoolean(resultSet.getString("C_CN_APPROUVE")));
			  linkedConsignation.setReference1(resultSet.getString("V_CN_REFERENCE1"));
			  linkedConsignation.setReference2(resultSet.getString("V_CN_REFERENCE2"));
			  results.add(linkedConsignation);
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
 * Routine pour traiter les ResultSet retournés par les recherches de consignation.
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param resultSet  ResultSet : Données retournées par une recherche.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return ArrayList : liste des résultats traités.
 */
    public RowCallbackHandler constuireRowCallBackHandler(final List<Consignation> listDossier){
  	   return new RowCallbackHandler(){
  		   public void processRow(ResultSet resultSet) throws SQLException {
  			   Consignation consignation = construireConsignation(resultSet);
               listDossier.add(consignation); 					
  		   }
  	   };
     }	
	
  private ArrayList traitementResultSet(ResultSet resultSet)
		throws DAOException {
	   ArrayList results = new ArrayList();
	   try { //On limite le nombre d'enregistrements retournés à 3000.
			while (resultSet.next() && results.size() < 3000){
				GererTacheUtilisateur.verifierThreadCourrant();
				
			  ConsignationVO consignationVo = construireConsignation(resultSet);
			  results.add(consignationVo);
		 }
		 return results;
	  } catch (SQLException se) {
		  throw new DAOException(se);
	  }
	}

private ConsignationVO construireConsignation(ResultSet resultSet)
		throws SQLException {
	ConsignationVO consignationVo = new ConsignationVO();
	  DossierVO dossier = new DossierVO();
	  consignationVo.setCle(resultSet.getLong("L_CN_CLE"));
	  consignationVo.setSite(resultSet.getLong("L_SI_CLE"));
	  consignationVo.setLien(resultSet.getLong("L_CN_REF_CLE"));
	  consignationVo.setLienSite(resultSet.getLong("L_CN_REF_SITE"));
	  consignationVo.setLienGenre(resultSet.getString("C_CN_REF_GENRE"));
	  consignationVo.setDevise(resultSet.getLong("I_DE_CLE"));
	  consignationVo.setDenomination(resultSet.getLong("I_DN_CLE"));
	  consignationVo.setTypeConsignation(resultSet.getLong("I_TN_CLE"));
	  consignationVo.setQuantite(resultSet.getLong("L_CN_QUANTITE"));
	  consignationVo.setPrix(resultSet.getDouble("R_CN_PRIX"));
	  consignationVo.setMontant(resultSet.getDouble("R_CN_MONTANT"));  // ?
	  consignationVo.setPoids(resultSet.getString("V_CN_POIDS"));  // ?
	  consignationVo.setDimension(resultSet.getString("V_CN_DIMENSION"));// ?
	  consignationVo.setMarque(resultSet.getString("V_CN_MARQUE"));  // ?
	  consignationVo.setModele(resultSet.getString("V_CN_MODELE"));    // ?
	  consignationVo.setFournisseur(resultSet.getString("V_CN_FOURNISSEUR"));// ?
	  consignationVo.setDescription(resultSet.getString("V_CN_DESCRIPTION"));// ?
	  consignationVo.setCommentaire(resultSet.getString("V_CN_COMMENTAIRE"));// ?
	  consignationVo.setNumeroSerie(resultSet.getString("V_CN_NUMERO_SERIE"));
	  consignationVo.setCreateur(resultSet.getString("V_CN_CREE_PAR"));
	  consignationVo.setDateCreation(resultSet.getTimestamp("D_CN_DATE_CREATION"));    // ?
	  consignationVo.setModificateur(resultSet.getString("V_CN_MODIFIE_PAR"));
	  consignationVo.setDateModification(resultSet.getTimestamp("D_CN_DATE_MODIFICATION"));    // ?
	  consignationVo.setApprobateur(resultSet.getString("V_CN_APPROUVE_PAR"));// ?
	  consignationVo.setDateApprobation(resultSet.getTimestamp("D_CN_DATE_APPROBATION"));// ?
	  consignationVo.setApprouvable(OracleDAOUtils.convertirStringABoolean(resultSet.getString("C_CN_APPROUVABLE")));
	  consignationVo.setApprouve(OracleDAOUtils.convertirStringABoolean(resultSet.getString("C_CN_APPROUVE")));
	  consignationVo.setReference1(resultSet.getString("V_CN_REFERENCE1"));
	  consignationVo.setReference2(resultSet.getString("V_CN_REFERENCE2"));
	  //Inscription des valeurs sur les dossiers associés aux recherches de suivis
	  dossier.setCle(resultSet.getLong("L_DO_CLE"));
	  dossier.setSite(resultSet.getLong("SITE_DOSSIER"));
	  dossier.setNumeroCardex(resultSet.getString("V_DO_NUMERO_DOSSIER"));
	  dossier.setHierarchie(resultSet.getLong("I_NH_CLE"));
	  dossier.setMotPasse(resultSet.getString("V_DO_MOT_PASSE"));
	  
	  dossier.setEntite(resultSet.getLong("I_GE_ENTITE"));
	  dossier.setHierarchie(resultSet.getLong("I_NH_CLE"));
	  dossier.setGenre(resultSet.getLong("I_GE_CLE"));
	  dossier.setNature(resultSet.getLong("I_NA_CLE"));
	  dossier.setType(resultSet.getLong("I_TY_CLE"));
	  dossier.setCategorie(resultSet.getLong("I_CA_CLE"));
	  dossier.setConfidentialite(resultSet.getLong("I_CC_CLE"));
	  
	  
	  consignationVo.setDossier(dossier);
	return consignationVo;
}
	/**
	 * Routine pour traiter les ResultSet retournés par les recherches de consignation
	 * pour l'impression du rapport.
	 * @param resultSet  ResultSet : Données retournées par une recherche.
	 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
	 * rupture de connexion avec la base de données, ou que la table demandée est
	 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
	 * "stored procedure".
	 * @return ArrayList : liste des résultats traités.
	 */
	  private ArrayList traitementResultSetRapport(ResultSet resultSet)
			throws DAOException {
		   ArrayList results = new ArrayList();
		   try { 
				while (resultSet.next()){
				  ConsignationVO consignationVo = new ConsignationVO();
				  //consignationVo.setCle(resultSet.getLong("L_CN_CLE"));
				  consignationVo.setSite(resultSet.getLong("L_SI_CLE"));
				  //consignationVo.setLien(resultSet.getLong("L_CN_REF_CLE"));
				  //consignationVo.setLienSite(resultSet.getLong("L_CN_REF_SITE"));
				  //consignationVo.setLienGenre(resultSet.getString("C_CN_REF_GENRE"));
				  //consignationVo.setDevise(resultSet.getLong("I_DE_CLE"));
				  //consignationVo.setTypeConsignation(resultSet.getLong("I_TN_CLE"));
				  consignationVo.setQuantite(resultSet.getLong("NOMBRE"));
				  //consignationVo.setPrix(resultSet.getDouble("R_CN_PRIX"));
				  consignationVo.setMontant(resultSet.getDouble("TOTAL"));  // ?
				  //consignationVo.setPoids(resultSet.getString("V_CN_POIDS"));  // ?
				  /*consignationVo.setDimension(resultSet.getString("V_CN_DIMENSION"));// ?
				  consignationVo.setMarque(resultSet.getString("V_CN_MARQUE"));  // ?
				  consignationVo.setModele(resultSet.getString("V_CN_MODELE"));    // ?
				  consignationVo.setFournisseur(resultSet.getString("V_CN_FOURNISSEUR"));// ?
				  consignationVo.setDescription(resultSet.getString("V_CN_DESCRIPTION"));// ?
				  consignationVo.setCommentaire(resultSet.getString("V_CN_COMMENTAIRE"));// ?
				  consignationVo.setNumeroSerie(resultSet.getString("V_CN_NUMERO_SERIE"));
				  */
				  consignationVo.setCreateur(OracleDAOUtils.getString(resultSet,"V_CN_CREE_PAR"));
				  /*consignationVo.setDateCreation(resultSet.getTimestamp("D_CN_DATE_CREATION"));    // ?
				  consignationVo.setModificateur(resultSet.getString("V_CN_MODIFIE_PAR"));
				  consignationVo.setDateModification(resultSet.getTimestamp("D_CN_DATE_MODIFICATION"));    // ?
				  consignationVo.setApprobateur(resultSet.getString("V_CN_APPROUVE_PAR"));// ?
				  consignationVo.setDateApprobation(resultSet.getTimestamp("D_CN_DATE_APPROBATION"));// ?
				  consignationVo.setApprouvable(resultSet.getString("C_CN_APPROUVABLE"));
				  consignationVo.setApprouve(resultSet.getString("C_CN_APPROUVE"));
				  */
				  consignationVo.setReference1(resultSet.getString("V_TR_DESCRIPTION"));
				  consignationVo.setReference2(resultSet.getString("MOIS-ANNEE"));
				  results.add(consignationVo);
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
		  }

		}

/**
 * Recherche directe d'une consignation par sa clé unique.
 * Procédure appelée : SP_L2_CN_CONSIGNATION
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param subject  CardexAuthenticationSubject : Données nominatives sur
 * l'utilisateur.
 * @param criteria Consignation : Consignation à rechercher.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Consignation : Données du dossier trouvé.
 */
	public Consignation find(CardexAuthenticationSubject subject, Consignation criteria)
			throws DAOException {
	  Connection connection
			= DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
	  ConsignationVO consignationVo = new ConsignationVO();
	  CardexUser user = (CardexUser)subject.getUser();
	  CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();

	  try {
		 callableStatement =
		 connection.prepareCall("begin SP_L2_CN_CONSIGNATION (?,?,?); end;");
		 callableStatement.setLong(1,criteria.getCle());
		 callableStatement.setLong(2,criteria.getSite());
		 callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
		 callableStatement.execute();
		 resultSet = (ResultSet)callableStatement.getObject(3);
		 //Traitement du résultat retourné
		 if (resultSet.next()) {
			  consignationVo.setCle(resultSet.getLong("L_CN_CLE"));
			  consignationVo.setSite(resultSet.getLong("L_SI_CLE"));
			  consignationVo.setLien(resultSet.getLong("L_CN_REF_CLE"));
			  consignationVo.setLienSite(resultSet.getLong("L_CN_REF_SITE"));
			  consignationVo.setLienGenre(resultSet.getString("C_CN_REF_GENRE"));
			  consignationVo.setDevise(resultSet.getLong("I_DE_CLE"));
			  consignationVo.setDenomination(resultSet.getLong("I_DN_CLE"));
			  consignationVo.setTypeConsignation(resultSet.getLong("I_TN_CLE"));
			  consignationVo.setQuantite(resultSet.getLong("L_CN_QUANTITE"));
			  consignationVo.setPrix(resultSet.getDouble("R_CN_PRIX"));
			  consignationVo.setMontant(resultSet.getDouble("R_CN_MONTANT"));  // ?
			  consignationVo.setPoids(resultSet.getString("V_CN_POIDS"));  // ?
			  consignationVo.setDimension(resultSet.getString("V_CN_DIMENSION"));// ?
			  consignationVo.setMarque(resultSet.getString("V_CN_MARQUE"));  // ?
			  consignationVo.setModele(resultSet.getString("V_CN_MODELE"));    // ?
			  consignationVo.setFournisseur(resultSet.getString("V_CN_FOURNISSEUR"));// ?
			  consignationVo.setDescription(resultSet.getString("V_CN_DESCRIPTION"));// ?
			  consignationVo.setCommentaire(resultSet.getString("V_CN_COMMENTAIRE"));// ?
			  consignationVo.setNumeroSerie(resultSet.getString("V_CN_NUMERO_SERIE"));
			  consignationVo.setCreateur(resultSet.getString("V_CN_CREE_PAR"));
			  consignationVo.setDateCreation(resultSet.getTimestamp("D_CN_DATE_CREATION"));    // ?
			  consignationVo.setModificateur(resultSet.getString("V_CN_MODIFIE_PAR"));
			  consignationVo.setDateModification(resultSet.getTimestamp("D_CN_DATE_MODIFICATION"));    // ?
			  consignationVo.setApprobateur(resultSet.getString("V_CN_APPROUVE_PAR"));// ?
			  consignationVo.setDateApprobation(resultSet.getTimestamp("D_CN_DATE_APPROBATION"));// ?
			  consignationVo.setApprouvable(OracleDAOUtils.convertirStringABoolean(resultSet.getString("C_CN_APPROUVABLE")));
			  consignationVo.setApprouve(OracleDAOUtils.convertirStringABoolean(resultSet.getString("C_CN_APPROUVE")));
			  consignationVo.setReference1(resultSet.getString("V_CN_REFERENCE1"));
			  consignationVo.setReference2(resultSet.getString("V_CN_REFERENCE2"));
		 }

		 //Un consignation peut être modifiée seulement si elle n'a pas été approuvée
		 if (consignationVo.getDateApprobation() == null){
			  consignationVo.setModifiable(true);
		 }
/*
		 //Un consignation ne peut être complétée que par l'intervenant assigné
		 if ( user.getCode().equals(consignationVo.getIntervenant())) {
			consignationVo.setPermettreComplete(true);
		 }

		 // Un consignation peut être approuvé si le niveau d'autorité de l'utilisateur
		 // est supérieur à celui du consignation et si le site est le même ou si
		 // l'utilisateur est le demandeur du consignation ou si le niveau d'autorité,
		 // le site et le secteur de l'utilisateur sont les mêmes que ceux du demandeur
		 if ((privilege.getNiveauAuthorite() > consignationVo.getNiveauHierarchiqueConsignation() &&
			 user.getSite() == consignationVo.getSite()) ||
			 (user.getCode().equals(consignationVo.getDemandeur()) ||
			 (privilege.getNiveauAuthorite() == consignationVo.getNiveauHierarchiqueCreateur()) &&
			  user.getSite() ==  consignationVo.getSite() &&
			  user.getPoste() == consignationVo.getSecteurOrigine() ) &&
			  consignationVo.getDateApprobation() == null) {
			consignationVo.setApprouvable(true);
		 }
*/
		 if (log.isLoggable(Level.FINE)){
		   log.fine("  user.code = '"+user.getCode()+"'");
		   log.fine("  user.site = '"+user.getSite()+"'");
		   log.fine("  user.niveauAuthorite = '"+privilege.getNiveauAuthorite()+"'");
		   log.fine("  consignation.createur = '"+consignationVo.getCreateur()+"'");
		   log.fine("  consignation.site = '"+consignationVo.getSite()+"'");
		   log.fine("  consignation.approbateur = '"+consignationVo.getApprobateur()+"'");
		   log.fine("Détermine si le consignation est modifiable");
		   log.fine("  consignation.isModifiable = '"+consignationVo.isModifiable()+"'");
		   log.fine("Détermine si le consignation est approuvable");
		   log.fine("  consignation.isApprouvable = '"+consignationVo.isApprouvable()+"'");
		   log.fine("Détermine si on peut compléter le consignation");

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
	  return consignationVo;
	}

/**
 * Recherche de consignations à l'aide de critères de recherche.
 * Cette recherche sert principalement à chercher des consignations.
 * La requête SQL est générée dans le code Java, avant d'être envoyée à Oracle,
 * en fonction des critères de recherche.
 * Le resultSet retourné par les recherches est traité dans la routine traitementResultSet.
 * Procédure appelée : générée ici.
 * Date de création : (2002-02-19)
 * @author François Guérin
 * @param subject  CardexAuthenticationSubject : données nominatives sur l'utilisateur
 * @param criteria CriteresRechercheConsignation : critères de recherche
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return ValueListIterator : liste des dossiers retournés par la recherche.
 */
    public List<Consignation> select(CardexAuthenticationSubject subject, CriteresRechercheConsignation criteria) throws DAOException {
  		JDBCTemplate template = new JDBCTemplate(subject);
    	List<Consignation> listDossier = new ArrayList<Consignation>();
    	PreparerSQL preparerSQL = (new ConsignationCompletSQL()).construireSQL(subject, criteria);
    	template.query(preparerSQL, criteria.getSequence(), constuireRowCallBackHandler(listDossier));
 	   
    	return listDossier;	
    }
    
    public Integer nombreDeConsignationRecherche(CardexAuthenticationSubject subject,CriteresRechercheConsignation criteria) throws DAOException {
    	JDBCTemplate template = new JDBCTemplate(subject);
    	PreparerSQL preparerSQL = (new ConsignationCountSQL()).construireSQL(subject, criteria);
  	   
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
	 * Détermine si une consignation est approuvable selon le type de consignation
	 * choisi.
	 *
	 * @param subject Le sujet qui créé le dossier
	 * @param info Le dossier
	 *
	 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
	 * rupture de connexion avec la base de données, ou que la table demandée
	 * est non disponible, ou qu'un problème est survenu lors de l'exécution
	 */
	public synchronized String isApprouvable(CardexAuthenticationSubject subject,
					   long cleType) throws DAOException {

		  log.fine("isApprouvable()");

		  if (TYPES_CONSIGNATION == null) {
			  log.fine("Loading TYPES_CONSIGNATION in cache ...");
			TYPES_CONSIGNATION = new HashMap();
			  Connection connection =
				  DAOConnection.getInstance().getConnection(subject);
			  CallableStatement callableStatement = null;
			  ResultSet resultSet = null;

			  try {
				  callableStatement =
					connection.prepareCall("begin CARDEX_LIRE_PILOTAGE.SP_L_TN_TYPE_CONSIGNATION (?); end;");
				  callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
				  callableStatement.execute();
				  resultSet = (ResultSet)callableStatement.getObject(1);
				  while (resultSet.next()) {
					  String inscriptionFlag = OracleDAOUtils.getString(resultSet,"B_TN_APPROBATION");
					  String cle = OracleDAOUtils.getString(resultSet,"I_TN_CLE");
					  log.fine("   [TYPES_CONSIGNATION cle='" + cle
								+ "' flag='" + inscriptionFlag + "']");
					TYPES_CONSIGNATION.put(cle,inscriptionFlag);
				  }
			  } catch (SQLException se) {
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
			  }
		}
		String type = Long.toString(cleType);
		String approuvable = (String)TYPES_CONSIGNATION.get(type);
		log.fine("  type = '"+type+"'");
		log.fine("  approuvable = '"+approuvable+"'");
		return (approuvable);
   }

   



}