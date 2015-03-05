package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.business.ConsignationActionPSU;
import com.lotoquebec.cardex.business.CriteresRecherchePSUMandat;
import com.lotoquebec.cardex.business.PSUMandat;
import com.lotoquebec.cardex.business.vo.ConsignationActionPSUVO;
import com.lotoquebec.cardex.business.vo.PSUMandatVO;
import com.lotoquebec.cardex.integration.dao.sql.VerificationMandatSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.PSUMandatCompletSQL;
import com.lotoquebec.cardex.integration.dao.sql.recherche.PSUMandatCountSQL;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.ValueListHandler;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.IteratorException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.UnEnregistrementPresent;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;

/**
 * Liste des appels à la base de données pour différents accès aux
 * mandats PSU.  Implémente l'interface PSUMandatDAO.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.11 $, $Date: 2002/05/02 13:06:09 $
 * @see com.lotoquebec.cardex.integration.PSUMandatDAO
 */

public class PSUMandatDAO {

   private final Logger      log =
       (Logger)LoggerCardex.getLogger(PSUMandatDAO.class);
   
/**
 * Écriture d'un mandat PSU, appelée par la méthode "insert", "update",
 * "approbation" ou "delete".
 * Selon le paramètre "action" il peut s'agir d'une insertion ("I")
 * d'une mise à jour ("U"), d'une approbation et modification ("M") ou
 * d'une suppression ("D").
 * Procédure appelée : CARDEX_LIEN.SP_E_PSU_MANDAT
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
   private PSUMandat editPSUMandat(CardexAuthenticationSubject subject, PSUMandat psuMandat,
		String action) throws DAOException {
		Connection connection
			= DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
	  try {
		  callableStatement =
			  connection.prepareCall("begin CARDEX_LIEN.SP_E_PSU_MANDAT "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
					+ "end;");
			  callableStatement.setString(1,action);
			  callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
			  OracleDAOUtils.setLong(callableStatement,2, psuMandat.getCle());
			  callableStatement.registerOutParameter(3, java.sql.Types.DECIMAL);
			  OracleDAOUtils.setLong(callableStatement,3, psuMandat.getSite());
			  callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
		      callableStatement.setString(4, psuMandat.getNumeroMandat());
			  OracleDAOUtils.setLong(callableStatement,5, psuMandat.getTypeAction());
			  callableStatement.setString(6, psuMandat.getDescription());
			  callableStatement.setString(7, psuMandat.getDestinataireA());
			  callableStatement.setString(8, psuMandat.getDestinataireCC());
			  callableStatement.setString(9, psuMandat.getDestinataireCCI());
			  OracleDAOUtils.setLong(callableStatement,10, psuMandat.getStatut());
			  callableStatement.setString(11, psuMandat.getMessage());
		      callableStatement.setTimestamp(12,(Timestamp)(psuMandat.getDateDebut()));
		      callableStatement.setTimestamp(13,(Timestamp)(psuMandat.getDateFin()));
			  callableStatement.setString(14, psuMandat.getGenreFichier());
			  OracleDAOUtils.setLong(callableStatement,15, psuMandat.getReferenceCle());
	  		  OracleDAOUtils.setLong(callableStatement,16, psuMandat.getReferenceSite());
			  OracleDAOUtils.setLong(callableStatement,17, psuMandat.getEntite());
			  OracleDAOUtils.setLong(callableStatement,18, psuMandat.getSiteCible());
			  OracleDAOUtils.setLong(callableStatement,19, psuMandat.getGenre());
			  OracleDAOUtils.setLong(callableStatement,20, psuMandat.getNature());
			  OracleDAOUtils.setLong(callableStatement,21, psuMandat.getType());
		      OracleDAOUtils.setLong(callableStatement,22, psuMandat.getCategorie());
			  OracleDAOUtils.setLong(callableStatement,23, psuMandat.getFonde());
			  callableStatement.setString(24, psuMandat.getIntervenant());
			  callableStatement.setString(25, psuMandat.getNumeroDossier().toUpperCase());
			  callableStatement.setString(26, psuMandat.getNumeroCardex().toUpperCase());
			  callableStatement.setString(27, psuMandat.getReference1());
			  callableStatement.setString(28, psuMandat.getFicheSujet().toUpperCase());
			  callableStatement.setString(29, psuMandat.getSujetNom());
			  callableStatement.setString(30, psuMandat.getSujetPrenom());
			  callableStatement.setString(31, psuMandat.getFicheSociete().toUpperCase());
			  callableStatement.setString(32, psuMandat.getSocieteNom());
			  callableStatement.setString(33, psuMandat.getImmatriculation());
			  callableStatement.setString(34, psuMandat.getProvince());
			  callableStatement.setString(35, psuMandat.getMotCle1());
			  callableStatement.setString(36, psuMandat.getMotCle2());
			  callableStatement.setString(37, psuMandat.getMotCle3());
		      callableStatement.setString(38, psuMandat.getCreateur());
		      callableStatement.setTimestamp(39,(Timestamp)(psuMandat.getDateCreation()));
			  callableStatement.setString(40, psuMandat.getModificateur());
			  callableStatement.setTimestamp(41,(Timestamp)(psuMandat.getDateModification()));
			  callableStatement.setString(42, psuMandat.getApprobateur());
			  callableStatement.setTimestamp(43,(Timestamp)(psuMandat.getDateApprobation()));

			  callableStatement.execute();
			  PSUMandat newPSUMandat = new PSUMandatVO();
			  newPSUMandat.setCle(callableStatement.getLong(2));
			  newPSUMandat.setSite(callableStatement.getLong(3));
			  newPSUMandat.setNumeroMandat(callableStatement.getString(4));
			  return newPSUMandat;
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
 * Appel de la méthode editPSUMandat pour la création d'un mandat PSU.
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
	public PSUMandat insert(CardexAuthenticationSubject subject, PSUMandat psuMandat) throws DAOException {
	  return editPSUMandat(subject, psuMandat, "I");
	}

/**
 * Appel de la méthode editPSUMandat pour la mise à jour d'un mandat.
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
	public void update(CardexAuthenticationSubject subject, PSUMandat psuMandat) throws DAOException {
	  editPSUMandat(subject, psuMandat, "U");
	}

/**
 * Appel de la méthode editPSUMandat pour l'approbation ou la modification d'un
 * mandat.
 * L'approbation active un mandat.
 * La modification consiste à permettre de nouveau les modifications à un mandat
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
	public void approbation(CardexAuthenticationSubject subject, PSUMandat psuMandat) throws DAOException {
	  editPSUMandat(subject, psuMandat, "M");
	}

/**
 * Appel de la méthode editPSUMandat pour la suppression d'un consignation.
 * Un mandat ne peut être supprimé s'il n'a pas été approuvé ou si aucune consignation
 * d'action n'a été enregistré.  Autrement, le mandat est simplement désactivé.
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
	public void delete(CardexAuthenticationSubject subject, PSUMandat psuMandat) throws DAOException {
	  editPSUMandat(subject, psuMandat, "D");
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
	public ValueListIterator findLiensConsignationAction(CardexAuthenticationSubject subject,
			String mandat) throws DAOException {
	  log.fine("findLiensConsignationAction()");
	  Connection connection
			= DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
	  ValueListHandler listIterator = new ValueListHandler();
	  try {
		 callableStatement =
			connection.prepareCall("begin SPW_L_CS_CONSIGNATION_ACTION (?,?); end;");
		 callableStatement.setString(1,mandat);
		 callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
		 callableStatement.execute();
		 resultSet = (ResultSet)callableStatement.getObject(2);
		 ArrayList results = new ArrayList();
		 while (resultSet.next()){
			  ConsignationActionPSUVO linkedConsignation = new ConsignationActionPSUVO();
			  linkedConsignation.setCle(resultSet.getLong("L_CS_CLE"));
			  linkedConsignation.setNumeroMandat(resultSet.getString("V_CS_PSU_NUMERO_MANDAT"));
			  linkedConsignation.setTypeAction(resultSet.getLong("I_TA_CLE"));
			  linkedConsignation.setGenreFichier(resultSet.getString("C_CS_GENRE_FICHIER"));
			  linkedConsignation.setGenreFichierAction(resultSet.getString("C_CS_GENRE_FICHIER_ACTION"));
			  linkedConsignation.setIntervenant(resultSet.getString("V_CS_INTERVENANT"));
			  linkedConsignation.setDateConsignation(resultSet.getTimestamp("D_CS_DATE_CONSIGNATION")); 
			  linkedConsignation.setReferenceSource(resultSet.getString("V_CS_REFERENCE_SOURCE"));
			  linkedConsignation.setReferenceAction(resultSet.getString("V_CS_REFERENCE_ACTION"));
			  results.add(linkedConsignation);
		 }//while
		  listIterator.setList(results);
		 return listIterator;
	  } catch (IteratorException ie) {
	 	  throw new DAOException(ie);
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
 * Routine pour traiter les ResultSet retournés par les recherches de mandas PSU.
 * Date de création : (2002-02-27)
 * @author Philippe Caron
 * @param resultSet  ResultSet : Données retournées par une recherche.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return ArrayList : liste des résultats traités.
 */
    public RowCallbackHandler constuireRowCallBackHandler(final List<PSUMandat> psuMandatList){
  	   return new RowCallbackHandler(){
  		   public void processRow(ResultSet resultSet) throws SQLException {
  			 PSUMandat psuMandat = construirePSUMandat(resultSet, "select");
  			psuMandatList.add(psuMandat); 					
  		   }
  	   };
     }	
	
  private ArrayList traitementResultSet(ResultSet resultSet, String source)
		throws DAOException {
	   ArrayList results = new ArrayList();
	   try { //On limite le nombre d'enregistrements retournés à 3000.
			while (resultSet.next() && results.size() < GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_MANDAT_PSU){
				GererTacheUtilisateur.verifierThreadCourrant();
			  PSUMandatVO psuMandatVo = construirePSUMandat(resultSet, source);			  
			  results.add(psuMandatVo);
		 }
		 return results;
	  } catch (SQLException se) {
		  throw new DAOException(se);
	  }
	}

private PSUMandatVO construirePSUMandat(ResultSet resultSet, String source)
		throws SQLException {
	PSUMandatVO psuMandatVo = new PSUMandatVO();
	  psuMandatVo.setCle(resultSet.getLong("L_PSU_CLE"));
	  psuMandatVo.setSite(resultSet.getLong("L_SI_CLE"));
	  psuMandatVo.setNumeroMandat(resultSet.getString("V_PSU_NUMERO_MANDAT"));
	  psuMandatVo.setTypeAction(resultSet.getLong("I_TA_CLE"));
	  psuMandatVo.setDescription(resultSet.getString("V_PSU_DESCRIPTION"));
	  psuMandatVo.setDestinataireA(resultSet.getString("V_PSU_DESTINATAIRE_A"));
	  psuMandatVo.setDestinataireCC(resultSet.getString("V_PSU_DESTINATAIRE_CC"));
	  psuMandatVo.setDestinataireCCI(resultSet.getString("V_PSU_DESTINATAIRE_CCI"));
	  psuMandatVo.setDateDebut(resultSet.getTimestamp("D_PSU_DATE_DEBUT"));    
	  psuMandatVo.setDateFin(resultSet.getTimestamp("D_PSU_DATE_FIN"));    
	  psuMandatVo.setStatut(resultSet.getLong("I_ST_CLE"));
	  psuMandatVo.setMessage(resultSet.getString("V_PSU_MESSAGE"));  
	  psuMandatVo.setGenreFichier(resultSet.getString("C_PSU_GENRE_FICHIER"));  
	  psuMandatVo.setEntite(resultSet.getLong("I_PSU_EN_CLE"));
	  psuMandatVo.setSiteCible(resultSet.getLong("L_PSU_SI_CLE"));
	  psuMandatVo.setGenre(resultSet.getLong("I_PSU_GE_CLE"));
	  psuMandatVo.setNature(resultSet.getLong("I_PSU_NA_CLE"));
	  psuMandatVo.setType(resultSet.getLong("I_PSU_TY_CLE"));  
	  psuMandatVo.setCategorie(resultSet.getLong("I_PSU_CA_CLE"));  
	  psuMandatVo.setFonde(resultSet.getLong("I_PSU_FO_CLE"));  
	  psuMandatVo.setReferenceCle(resultSet.getLong("L_PSU_REFERENCE_CLE"));  
	  psuMandatVo.setReferenceSite(resultSet.getLong("L_PSU_REFERENCE_SITE"));  
	  psuMandatVo.setIntervenant(resultSet.getString("V_PSU_NAME"));
	  psuMandatVo.setNumeroDossier(resultSet.getString("V_PSU_DO_ANCIENNE_REFERENCE"));  
	  psuMandatVo.setNumeroCardex(resultSet.getString("V_PSU_DO_NUMERO_DOSSIER"));    
	  psuMandatVo.setReference1(resultSet.getString("V_PSU_DO_REFERENCE_1"));  
	  psuMandatVo.setFicheSujet(resultSet.getString("V_PSU_SU_REFERENCE_3"));  
	  psuMandatVo.setSujetNom(resultSet.getString("V_PSU_SU_NOM"));  
	  psuMandatVo.setSujetPrenom(resultSet.getString("V_PSU_SU_PRENOM"));  
	  psuMandatVo.setFicheSociete(resultSet.getString("V_PSU_SO_REFERENCE_3"));  
	  psuMandatVo.setSocieteNom(resultSet.getString("V_PSU_SO_NOM"));  
	  psuMandatVo.setImmatriculation(resultSet.getString("V_PSU_VE_IMMATRICULATION"));  
	  psuMandatVo.setProvince(resultSet.getString("V_PSU_VE_PROVINCE"));  
	  psuMandatVo.setMotCle1(resultSet.getString("V_PSU_MOT_CLE1"));  
	  psuMandatVo.setMotCle2(resultSet.getString("V_PSU_MOT_CLE2"));  
	  psuMandatVo.setMotCle3(resultSet.getString("V_PSU_MOT_CLE3"));  
	  psuMandatVo.setCreateur(resultSet.getString("V_PSU_CREE_PAR"));
	  psuMandatVo.setDateCreation(resultSet.getTimestamp("D_PSU_DATE_CREATION"));    
	  psuMandatVo.setModificateur(resultSet.getString("V_PSU_MODIFIE_PAR"));
	  psuMandatVo.setDateModification(resultSet.getTimestamp("D_PSU_DATE_MODIFICATION"));    
	  psuMandatVo.setApprobateur(resultSet.getString("V_PSU_APPROUVE_PAR"));
	  //On met un blanc si l'approbateur est vide pour déterminer le statut d'approbation
	  //dans l'affichage des résultats.
	  if(psuMandatVo.getApprobateur() == null){
		 psuMandatVo.setApprobateur(" ");
	  }
	  psuMandatVo.setDateApprobation(resultSet.getTimestamp("D_PSU_DATE_APPROBATION"));
	  if(source.equals("select")){
		  psuMandatVo.setTotal(resultSet.getLong("TOTAL"));
	  }
	return psuMandatVo;
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
/*	  private ArrayList traitementResultSetRapport(ResultSet resultSet)
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
				  consignationVo.setDimension(resultSet.getString("V_CN_DIMENSION"));// ?
				  consignationVo.setMarque(resultSet.getString("V_CN_MARQUE"));  // ?
				  consignationVo.setModele(resultSet.getString("V_CN_MODELE"));    // ?
				  consignationVo.setFournisseur(resultSet.getString("V_CN_FOURNISSEUR"));// ?
				  consignationVo.setDescription(resultSet.getString("V_CN_DESCRIPTION"));// ?
				  consignationVo.setCommentaire(resultSet.getString("V_CN_COMMENTAIRE"));// ?
				  consignationVo.setNumeroSerie(resultSet.getString("V_CN_NUMERO_SERIE"));
				  
				  consignationVo.setCreateur(OracleDAOUtils.getString(resultSet,"V_CN_CREE_PAR"));
				  consignationVo.setDateCreation(resultSet.getTimestamp("D_CN_DATE_CREATION"));    // ?
				  consignationVo.setModificateur(resultSet.getString("V_CN_MODIFIE_PAR"));
				  consignationVo.setDateModification(resultSet.getTimestamp("D_CN_DATE_MODIFICATION"));    // ?
				  consignationVo.setApprobateur(resultSet.getString("V_CN_APPROUVE_PAR"));// ?
				  consignationVo.setDateApprobation(resultSet.getTimestamp("D_CN_DATE_APPROBATION"));// ?
				  consignationVo.setApprouvable(resultSet.getString("C_CN_APPROUVABLE"));
				  consignationVo.setApprouve(resultSet.getString("C_CN_APPROUVE"));
				  
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
*/
/**
 * Recherche directe d'un mandat PSU par sa clé unique.
 * Procédure appelée : SP_L2_PSU_MANDAT
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
	public PSUMandat find(CardexAuthenticationSubject subject, PSUMandat criteria)
			throws DAOException {
	  Connection connection
			= DAOConnection.getInstance().getConnection(subject);
	  CallableStatement callableStatement = null;
	  ResultSet resultSet = null;
	  PSUMandatVO psuMandatVo = new PSUMandatVO();

	  try {
		 callableStatement =
		 connection.prepareCall("begin SP_L_PSU_MANDAT (?,?,?); end;");
		 callableStatement.setLong(1,criteria.getCle());
		 callableStatement.setLong(2,criteria.getSite());
		 callableStatement.registerOutParameter(3, OracleTypes.CURSOR);
		 callableStatement.execute();
		 resultSet = (ResultSet)callableStatement.getObject(3);
		 //Traitement du résultat retourné
		 if (resultSet.next()) {
			psuMandatVo.setCle(resultSet.getLong("L_PSU_CLE"));
			psuMandatVo.setSite(resultSet.getLong("L_SI_CLE"));
			psuMandatVo.setNumeroMandat(resultSet.getString("V_PSU_NUMERO_MANDAT"));
			psuMandatVo.setTypeAction(resultSet.getLong("I_TA_CLE"));
			psuMandatVo.setDescription(resultSet.getString("V_PSU_DESCRIPTION"));
			psuMandatVo.setDestinataireA(resultSet.getString("V_PSU_DESTINATAIRE_A"));
			psuMandatVo.setDestinataireCC(resultSet.getString("V_PSU_DESTINATAIRE_CC"));
			psuMandatVo.setDestinataireCCI(resultSet.getString("V_PSU_DESTINATAIRE_CCI"));
			psuMandatVo.setDateDebut(resultSet.getTimestamp("D_PSU_DATE_DEBUT"));    
			psuMandatVo.setDateFin(resultSet.getTimestamp("D_PSU_DATE_FIN"));    
			psuMandatVo.setStatut(resultSet.getLong("I_ST_CLE"));
			psuMandatVo.setMessage(resultSet.getString("V_PSU_MESSAGE"));  
			psuMandatVo.setGenreFichier(resultSet.getString("C_PSU_GENRE_FICHIER"));  
			psuMandatVo.setEntite(resultSet.getLong("I_PSU_EN_CLE"));
			psuMandatVo.setSiteCible(resultSet.getLong("L_PSU_SI_CLE"));
			psuMandatVo.setGenre(resultSet.getLong("I_PSU_GE_CLE"));
			psuMandatVo.setNature(resultSet.getLong("I_PSU_NA_CLE"));
			psuMandatVo.setType(resultSet.getLong("I_PSU_TY_CLE"));  
			psuMandatVo.setCategorie(resultSet.getLong("I_PSU_CA_CLE"));  
			psuMandatVo.setFonde(resultSet.getLong("I_PSU_FO_CLE"));  
			psuMandatVo.setReferenceCle(resultSet.getLong("L_PSU_REFERENCE_CLE"));  
			psuMandatVo.setReferenceSite(resultSet.getLong("L_PSU_REFERENCE_SITE"));  
			psuMandatVo.setIntervenant(resultSet.getString("V_PSU_NAME"));
			psuMandatVo.setNumeroDossier(resultSet.getString("V_PSU_DO_ANCIENNE_REFERENCE"));  
			psuMandatVo.setNumeroCardex(resultSet.getString("V_PSU_DO_NUMERO_DOSSIER"));    
			psuMandatVo.setReference1(resultSet.getString("V_PSU_DO_REFERENCE_1"));  
			psuMandatVo.setFicheSujet(resultSet.getString("V_PSU_SU_REFERENCE_3"));  
			psuMandatVo.setSujetNom(resultSet.getString("V_PSU_SU_NOM"));  
			psuMandatVo.setSujetPrenom(resultSet.getString("V_PSU_SU_PRENOM"));  
			psuMandatVo.setFicheSociete(resultSet.getString("V_PSU_SO_REFERENCE_3"));  
			psuMandatVo.setSocieteNom(resultSet.getString("V_PSU_SO_NOM"));  
			psuMandatVo.setImmatriculation(resultSet.getString("V_PSU_VE_IMMATRICULATION"));  
			psuMandatVo.setProvince(resultSet.getString("V_PSU_VE_PROVINCE"));  
			psuMandatVo.setMotCle1(resultSet.getString("V_PSU_MOT_CLE1"));  
			psuMandatVo.setMotCle2(resultSet.getString("V_PSU_MOT_CLE2"));  
			psuMandatVo.setMotCle3(resultSet.getString("V_PSU_MOT_CLE3"));  
			psuMandatVo.setCreateur(resultSet.getString("V_PSU_CREE_PAR"));
			psuMandatVo.setDateCreation(resultSet.getTimestamp("D_PSU_DATE_CREATION"));    
			psuMandatVo.setModificateur(resultSet.getString("V_PSU_MODIFIE_PAR"));
			psuMandatVo.setDateModification(resultSet.getTimestamp("D_PSU_DATE_MODIFICATION"));    
			psuMandatVo.setApprobateur(resultSet.getString("V_PSU_APPROUVE_PAR"));
			psuMandatVo.setDateApprobation(resultSet.getTimestamp("D_PSU_DATE_APPROBATION"));
			//On met un blanc si l'approbateur est vide pour déterminer le statut d'approbation
			//dans l'affichage du mandat. Si un mandat est approuvé, il ne peut pas être modifié.
			if(psuMandatVo.getApprobateur() == null || psuMandatVo.getApprobateur().equals(" ")){
			   psuMandatVo.setApprobateur(" ");
			   psuMandatVo.setModifiable(true);
			}else{
				psuMandatVo.setModifiable(false);
			}
		 }

/*		 if (log.isDebugEnabled()){
		   log.fine("  user.code = '"+user.getCode()+"'");
		   log.fine("  user.site = '"+user.getSite()+"'");
		   log.fine("  user.niveauAuthorite = '"+privilege.getNiveauAuthorite()+"'");
		   log.fine("  consignation.createur = '"+consignationVo.getCreateur()+"'");
		   log.fine("  consignation.site = '"+consignationVo.getSite()+"'");
		   log.fine("  consignation.approbateur = '"+consignationVo.getApprobateur()+"'");
		   log.fine("Détermine si le consignation est modifiable");
		   log.fine("  consignation.isModifiable = '"+consignationVo.isModifiable()+"'");
		   log.fine("Détermine si le consignation est approuvable");
		   log.fine("  consignation.isApprouvable = '"+consignationVo.getApprouvable()+"'");
		   log.fine("Détermine si on peut compléter le consignation");

		 }
*/
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
	  return psuMandatVo;
	}

/**
 * Recherche de mandats PSU à l'aide de critères de recherche.
 * Cette recherche sert principalement à chercher des mandats.
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
    public List<PSUMandat> select(CardexAuthenticationSubject subject, CriteresRecherchePSUMandat criteria) throws DAOException {
  		JDBCTemplate template = new JDBCTemplate(subject);
    	List<PSUMandat> psuMandatList = new ArrayList<PSUMandat>();
    	PreparerSQL preparerSQL = (new PSUMandatCompletSQL()).construireSQL(subject, criteria);
    	template.query(preparerSQL, criteria.getSequence(), constuireRowCallBackHandler(psuMandatList));
 	   
    	return psuMandatList;	
    }
    
    public Integer nombreDePSUMandatRecherche(CardexAuthenticationSubject subject,CriteresRecherchePSUMandat criteria) throws DAOException {
    	JDBCTemplate template = new JDBCTemplate(subject);
    	PreparerSQL preparerSQL = (new PSUMandatCountSQL()).construireSQL(subject, criteria);
  	   
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
 * À chaque action dans l'application, une recherche est effectuée pour vérifier 
 * si un mandat de suivi des utilisateurs est associé aux données concernées.
 * Si un mandat est trouvé, les informations sont retournées pour qu'une consignation
 * d'action soit effectuée et que des messages soient envoyés aux destinataires inscrits
 * dans le mandat.
 * Procédure appelée : générée ici.
 * Date de création : (2006-12-19)
 * @author François Guérin
 * @param subject  CardexAuthenticationSubject : données nominatives sur l'utilisateur
 * @param criteria CriteresRechercheConsignation : critères de recherche
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return ValueListIterator : liste des dossiers retournés par la recherche.
 */
    public List<PSUMandat> verification(CardexAuthenticationSubject subject, PSUMandat criteria, String genreFichier, String action) throws DAOException {
	   final List<PSUMandat> mandatList = new ArrayList<PSUMandat>();
	   JDBCTemplate template = new JDBCTemplate(subject);
	   
	   PreparerSQL preparerSQL = (new VerificationMandatSQL()).obtenirPreparerSQL(criteria, genreFichier, action);
	   RowCallbackHandler callbackHandler = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				mandatList.add( construirePSUMandat(rs, "verification") );	
			}
	   };
	   
	   template.query(preparerSQL, callbackHandler);
	   return mandatList;
    }//select

/**
 * Écriture d'une consignation lorsqu'une action dans le système correspond
 * à une trace définie par un mandat PSU (Programme de Suivi des utilisateurs).
 * Procédure appelée : CARDEX_LIEN.SP_E_CS_CONSIGNATION_ACTION
 * Date de création : (2007-01-10)
 * @author François Guérin
 * @param subject CardexAuthenticationSubject : Données nominatives sur
 * l'utilisateur.
 * @param consignation Consignation : consignation générée par un mandat.
 * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
 * rupture de connexion avec la base de données, ou que la table demandée est
 * non disponible, ou qu'un problème est survenu lors de l'exécution d'une
 * "stored procedure".
 * @return Consignation
 */
   public void ecrireConsignationAction(CardexAuthenticationSubject subject, ConsignationActionPSU consignationActionPSU) throws DAOException {
		Connection connection
			= DAOConnection.getInstance().getConnection(subject);
		CallableStatement callableStatement = null;
	  try {
		  callableStatement =
			  connection.prepareCall("begin CARDEX_LIEN.SP_E_CS_CONSIGNATION_ACTION "
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
					+ "end;");
			  callableStatement.setString(1,"I");
			  callableStatement.registerOutParameter(2, java.sql.Types.DECIMAL);
			  callableStatement.setString(3, consignationActionPSU.getNumeroMandat());
		      callableStatement.setTimestamp(4, (Timestamp)(consignationActionPSU.getDateConsignation()));
			  callableStatement.setString(5, consignationActionPSU.getIntervenant());
			  OracleDAOUtils.setLong(callableStatement,6, consignationActionPSU.getTypeAction());
			  callableStatement.setString(7, consignationActionPSU.getGenreFichier());
			  OracleDAOUtils.setLong(callableStatement,8, consignationActionPSU.getReferenceSourceCle());
		      OracleDAOUtils.setLong(callableStatement,9, consignationActionPSU.getReferenceSourceSite());
			  callableStatement.setString(10, consignationActionPSU.getReferenceSource());
		      callableStatement.setTimestamp(11, (Timestamp)(consignationActionPSU.getDateCourriel()));
			  callableStatement.setString(12, consignationActionPSU.getReferenceAction());
			  callableStatement.setString(13, consignationActionPSU.getGenreFichierAction());
			  OracleDAOUtils.setLong(callableStatement,14, consignationActionPSU.getReferenceActionCle());
			  OracleDAOUtils.setLong(callableStatement,15, consignationActionPSU.getReferenceActionSite());

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


}