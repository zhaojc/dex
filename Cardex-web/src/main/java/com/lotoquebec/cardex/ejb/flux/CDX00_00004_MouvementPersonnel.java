package com.lotoquebec.cardex.ejb.flux;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.MouvementVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.ejb.flux.util.SocieteInterne;
import com.lotoquebec.cardex.ejb.flux.vo.SujetMouvementVO;
import com.lotoquebec.cardex.integration.dao.DossierDAO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.integration.dao.MouvementsDAO;
import com.lotoquebec.cardex.integration.dao.SujetDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.util.CourrielAction;
import com.lotoquebec.cardexCommun.util.MessageDuCourriel;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Mouvements de personnel
 * @author levassc
 *
 */
public class CDX00_00004_MouvementPersonnel implements Flux{

	private final static Logger log = LoggerFactory.getLogger(CDX00_00004_MouvementPersonnel.class);
	private CardexAuthenticationSubject subject = null;
	private DossierDAO dossierDAO = null;
	private MouvementsDAO mouvementsDAO = null;
	private SujetDAO sujetDAO = null;
	
	private void init(){
		dossierDAO = FabriqueCardexDAO.getInstance().getDossierDAO();
		mouvementsDAO = FabriqueCardexDAO.getInstance().getMouvementsDAO();
		sujetDAO = FabriqueCardexDAO.getInstance().getSujetDAO();
	}
	
	public void execute() throws Exception {
		log.info("Entrée flux CDX00_00004");

		init();
		
		subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
		
		log.info("Obtenir nouveau mouvement de personnel");
		List<MouvementVO> nouveauxMouvements = mouvementsDAO.obtenirListeMouvement60joursNonTraite(subject);
		
		log.info("Traitement nouveaux mouvements");
		traitementNouveauxMouvements(nouveauxMouvements);
		
		log.info("Envoie courriel de fin de traitement");
		Connection connection = null; 
		try {
			connection = DAOConnection.getInstance().getConnection(subject);
			String objectMessage = CourrielAction.constuireObjectMessage(subject, GlobalConstants.TypesIntervention.Fin);
			envoyerCourrielDestinataire(subject, connection, objectMessage, "", GlobalConstants.TypesIntervention.Fin, "A");
		} finally{
			connection.close();
			connection = null;
		}		
		
		log.info("Fin flux CDX00_00004");
	}

	public void traitementNouveauxMouvements(List<MouvementVO> nouveauxMouvements) throws DAOException, BusinessResourceException, BusinessException, SQLException{
		Iterator<MouvementVO> iter = nouveauxMouvements.iterator();
  
		while (iter.hasNext()) {
			Connection connection = null; 
			try {
				connection = DAOConnection.getInstance().getConnection(subject);
				connection.setAutoCommit(false);

				MouvementVO mouvementVO = iter.next();
				log.info("Traitement du sujet "+mouvementVO.getNumeroClientEmploye());
				SujetMouvementVO sujetMouvementVO = obtenirSujet(mouvementVO, connection);
				  
				if(GlobalConstants.Mouvements.Embauche.equals(mouvementVO.getTypeMouvement())){
					  embauche(sujetMouvementVO, connection);
				}
				if(GlobalConstants.Mouvements.Depart.equals(mouvementVO.getTypeMouvement())){
					  depart(sujetMouvementVO, connection);
				}
				if(GlobalConstants.Mouvements.Conge.equals(mouvementVO.getTypeMouvement())){
					  conge(sujetMouvementVO, connection);
				}
				
				log.info("Ajouter nouveau mouvement trait�");
				mouvementsDAO.insert(mouvementVO, connection);
				
				connection.commit();
				
			} catch (SQLException e) {
				connection.rollback();
				throw new DAOException(e);
			} finally{
				connection.close();
				connection = null;
			}
		}
	}
	
	/**
	 * Lors d�un d�part ou cong�
	 * Pour les employ�s � temps complet, le syst�me change la s�v�rit� � 2, 
	 * seulement si la s�v�rit� du sujet est diff�rent � 2 et son statut est 
	 * diff�rent d�inactif.
	 * Pour les employ�s  � temps temporaire, le syst�me change la s�v�rit� � 2, 
	 * seulement s�il n�a pas de dossier actif  dans le syst�me Cardex.
	 * Si l�employ� n�a pas de s�v�rit� d�finie, le syst�me met sa s�v�rit� � 2.
	 * 
	 * @param sujetMouvementVO
	 * @param connection
	 * @throws DAOException
	 */
    private void conge(SujetMouvementVO sujetMouvementVO, Connection connection) throws DAOException {
		MouvementVO mouvementVO = sujetMouvementVO.getMouvementVO();
		log.info("Cong� "+sujetMouvementVO.getMouvementVO().getNumeroClientEmploye());
		 
		if (sujetMouvementVO.isRienTrouve() == false){
			
			//Si un seul sujet est trouv�, on inscrit le mouvement.
			if (sujetMouvementVO.getListeSujetVO().size() == 1){
				log.info("Un seul sujet trouv�");
				Sujet sujet = sujetMouvementVO.getSujetConcerne();
				//On modifie la fiche du sujet dans le cas des employ�s � temps complet (temporaire ou non)
				
				if(mouvementVO.getStatut() == GlobalConstants.Emplois.TC || mouvementVO.getStatut() == GlobalConstants.Emplois.TCT){
					
					if(sujet.getSeverite() != GlobalConstants.Severite.SEVERITE_2 
					&& sujet.getStatut() != Long.valueOf(GlobalConstants.Statut.SUJET_INACTIF).longValue()) {
						assignerSeverite2(sujetMouvementVO, connection);
				    }
				}else{
				    // Pour un employ� temporaire, nous assignons sa s�v�rit� � 2
					// Seulement s'il ne poss�de pas de dossier actif.
					if(isPossedeDossiersActif(sujet, connection) == false){ //Aucun dossier actif trouv�
						assignerSeverite2(sujetMouvementVO, connection);
					}
				}
				
				if(Long.toString(sujet.getSeverite()) == null || sujet.getSeverite() == 0){
					sujet.setSeverite(GlobalConstants.Severite.SEVERITE_2);
				}
				//Ensuite, on cr�e un dossier pour conserver les donn�es du mouvement.
				ecrireDossier(sujetMouvementVO, connection);
				//On avise les intervenants du d�part de l'employ�.
				envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.Conge, sujetMouvementVO.getMouvementVO().getCodeSite());
			         
			}else{//Plus d'un sujet trouv� avec des renseignements identiques
			
				if (sujetMouvementVO.isTrouveMatricule()){
					//Plus d'un sujet trouv� avec un num�ro identique.
				log.info("Il existe plus d'un enregistrement pour le num�ro : "+sujetMouvementVO.getMouvementVO().getNumeroClientEmploye()+" - nombre trouv� "+sujetMouvementVO.getListeSujetVO().size());
				//On demande une v�rification aux personnes concern�es
					envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.NoDoubleDepart, sujetMouvementVO.getMouvementVO().getCodeSite());
					envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.Conge, sujetMouvementVO.getMouvementVO().getCodeSite());
				}else{
					if (sujetMouvementVO.isTrouveNomPrenomDateNaissance()){
						//Plus d'un sujet trouv�.
						log.info("Il existe plus d'un enregistrement pour : " + sujetMouvementVO.getMouvementVO().getNom() + ", " + sujetMouvementVO.getMouvementVO().getPrenom() + ",  " + sujetMouvementVO.getMouvementVO().getDateNaissance().toString().substring(0,11) + " : " + sujetMouvementVO.getListeSujetVO().size());
						//On demande une v�rification aux personnes concern�es
		 	        	envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.SujetDoubleDepart, sujetMouvementVO.getMouvementVO().getCodeSite());
		 	        	envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.Conge, sujetMouvementVO.getMouvementVO().getCodeSite());
					}
				}
			}
		}else{//Aucun r�sultat. On avise simplement qu'il y a eu d�part.
			log.info("Aucun sujet trouv�");
			envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.Conge, sujetMouvementVO.getMouvementVO().getCodeSite());
		}    	 
    }
    	

	/**
     * Traitement d'un employ� lors d'un d�part. On cherche le sujet correspondant
     * pour changer sa s�v�rit� et son statut.  Puis, les personnes concern�es sont avis�es
     * pour intervention.
     * 
     * Lors d�un d�part ou cong�
     * Pour les employ�s � temps complet, le syst�me change la s�v�rit� � 2, 
     * seulement si la s�v�rit� du sujet est diff�rent � 2 et son statut est 
     * diff�rent d�inactif.
     * Pour les employ�s  � temps temporaire, le syst�me change la s�v�rit� � 2, 
     * seulement s�il n�a pas de dossier actif  dans le syst�me Cardex.
     * Si l�employ� n�a pas de s�v�rit� d�finie, le syst�me met sa s�v�rit� � 2.
     * 
     * @throws DAOException si une exception SQL est recue (si le driver de
     * base de donn�e ne peut �tre trouv�, etc.)
     */
     public void depart(SujetMouvementVO sujetMouvementVO, Connection connection) throws DAOException, BusinessResourceException, BusinessException {
    	 MouvementVO mouvementVO = sujetMouvementVO.getMouvementVO();
    	 log.info("Départ "+sujetMouvementVO.getMouvementVO().getNumeroClientEmploye());
    	 
     	 if (sujetMouvementVO.isRienTrouve() == false){
     		
     		//Si un seul sujet est trouv�, on inscrit le mouvement.
     		if (sujetMouvementVO.getListeSujetVO().size() == 1){
     			log.info("Un seul sujet trouv�");
     			Sujet sujet = sujetMouvementVO.getSujetConcerne();
     			//On modifie la fiche du sujet dans le cas des employ�s � temps complet (temporaire ou non)
     			
			    if(mouvementVO.getStatut() == GlobalConstants.Emplois.TC || mouvementVO.getStatut() == GlobalConstants.Emplois.TCT){
			    	
			    	if(sujet.getSeverite() != GlobalConstants.Severite.SEVERITE_2 
			    	&& sujet.getStatut() != Long.valueOf(GlobalConstants.Statut.SUJET_INACTIF).longValue()) {
			    		assignerSeverite2(sujetMouvementVO, connection);
				    }
			    }else{
			        // Pour un employ� temporaire, nous assignons sa s�v�rit� � 2
			    	// Seulement s'il ne poss�de pas de dossier actif.
         			if(isPossedeDossiersActif(sujet, connection) == false){ //Aucun dossier actif trouv�
			    		assignerSeverite2(sujetMouvementVO, connection);
         			}
			    }
			    
			    if(Long.toString(sujet.getSeverite()) == null || sujet.getSeverite() == 0){
			    	sujet.setSeverite(GlobalConstants.Severite.SEVERITE_2);
			    }
		        //Ensuite, on cr�e un dossier pour conserver les donn�es du mouvement.
     			ecrireDossier(sujetMouvementVO, connection);
				//On avise les intervenants du d�part de l'employ�.
     			envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.Depart, sujetMouvementVO.getMouvementVO().getCodeSite());
 	             
     		}else{//Plus d'un sujet trouv� avec des renseignements identiques
     			
     			if (sujetMouvementVO.isTrouveMatricule()){
     				//Plus d'un sujet trouv� avec un num�ro identique.
     	        	log.info("Il existe plus d'un enregistrement pour le num�ro : "+sujetMouvementVO.getMouvementVO().getNumeroClientEmploye()+" - nombre trouv� "+sujetMouvementVO.getListeSujetVO().size());
     	     		//On demande une v�rification aux personnes concern�es
     	        	envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.NoDoubleDepart, sujetMouvementVO.getMouvementVO().getCodeSite());
     	        	envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.Depart, sujetMouvementVO.getMouvementVO().getCodeSite());
     			}else{
     				if (sujetMouvementVO.isTrouveNomPrenomDateNaissance()){
     					//Plus d'un sujet trouv�.
     		         	log.info("Il existe plus d'un enregistrement pour : " + sujetMouvementVO.getMouvementVO().getNom() + ", " + sujetMouvementVO.getMouvementVO().getPrenom() + ",  " + sujetMouvementVO.getMouvementVO().getDateNaissance().toString().substring(0,11) + " : " + sujetMouvementVO.getListeSujetVO().size());
     	         		//On demande une v�rification aux personnes concern�es
         	        	envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.SujetDoubleDepart, sujetMouvementVO.getMouvementVO().getCodeSite());
         	        	envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.Depart, sujetMouvementVO.getMouvementVO().getCodeSite());
     				}
     			}
     		}
     	 }else{//Aucun r�sultat. On avise simplement qu'il y a eu d�part.
     		 log.info("Aucun sujet trouv�");
     		 envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.Depart, sujetMouvementVO.getMouvementVO().getCodeSite());
     	 }    	 
     }

    private boolean isPossedeDossiersActif(Sujet sujet, Connection connection) throws DAOException {
    	 Collection listeDossier = sujetDAO.findLiensDossier(sujet, connection);
    	 Iterator<DossierVO> iter = listeDossier.iterator();
    	 
    	 while (iter.hasNext()) {
			DossierVO dossierVO = iter.next();
			
			if (GlobalConstants.Statut.DOSSIER_ACTIF == dossierVO.getStatut())
				return true;
		}
    	 
    	return false;
	}

	/**
      * Lors d'un d�part, la s�v�rit� du sujet doit �tre mise � 1 (pour interdire ses
      * acc�s). On inscrit �galement son num�ro d'employ� si ce n'est pas fait.
      * @param sujetMouvementVO
      * @throws DAOException
      */
	private void assignerSeverite2(SujetMouvementVO sujetMouvementVO, Connection connection) throws DAOException {
		Sujet sujet = sujetMouvementVO.getSujetConcerne();
		sujet.setNumeroClientEmploye( sujetMouvementVO.getMouvementVO().getNumeroClientEmploye() );
		sujet.setSeverite(GlobalConstants.Severite.SEVERITE_2);
		sujet.setStatut(Long.valueOf(GlobalConstants.Statut.SUJET_INACTIF));
		sujetDAO.update(sujet, connection);
		
		if (isPossedeDossiersActif(sujet, connection))
			envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.DossierActifDepart, sujetMouvementVO.getMouvementVO().getCodeSite());
	}	

	/**
	 * Lors de l�embauche
	 * Si la fiche de l�employ� n�est pas trouv�e, le syst�me met sa s�v�rit� � 3.
	 * Si la fiche est trouv�e, le syst�me cr�e un dossier d�investigation. La 
	 * s�v�rit� du dossier est la m�me que celle de la fiche de l�employ�. Si 
	 * l�employ� n�a pas de s�v�rit� d�finie, le syst�me met 2 comme s�v�rit� du dossier.
	 * 
	 * @param sujetMouvementVO
	 * @param connection
	 * @throws DAOException
	 * @throws BusinessResourceException
	 * @throws BusinessException
	 */
	private void embauche(SujetMouvementVO sujetMouvementVO, Connection connection) throws DAOException, BusinessResourceException, BusinessException {
     	log.info("Embauche "+sujetMouvementVO.getMouvementVO().getNumeroClientEmploye());
	 	
     	if (sujetMouvementVO.isRienTrouve() == false){
     		
     		//Si un seul sujet est trouv�, on inscrit le mouvement.
     		if (sujetMouvementVO.getListeSujetVO().size() == 1){
     			log.info("Un seul sujet trouv�");
     			ecrireDossier(sujetMouvementVO, connection);
     			envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.Embauche, sujetMouvementVO.getMouvementVO().getCodeSite());

     	        //Lier le sujet � �a soci�t�
     	        SocieteInterne societeInterne = SocieteInterne.getInstance(subject);
     	        societeInterne.lierSociete( sujetMouvementVO );
     		}else{//Plus d'un sujet trouv� avec des renseignements identiques
     			
     			if (sujetMouvementVO.isTrouveMatricule()){
     				//Plus d'un sujet trouv� avec un num�ro identique.
     	        	log.info("Il existe plus d'un enregistrement pour le num�ro : "+sujetMouvementVO.getMouvementVO().getNumeroClientEmploye()+" - nombre trouv� "+sujetMouvementVO.getListeSujetVO().size());
     	     		//On demande une v�rification aux personnes concern�es
     	        	envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.NoDoubleDepart, sujetMouvementVO.getMouvementVO().getCodeSite());
     	        	envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.Embauche, sujetMouvementVO.getMouvementVO().getCodeSite());
     			}else{
     				if (sujetMouvementVO.isTrouveNomPrenomDateNaissance()){
     					//Plus d'un sujet trouv�.
     		         	log.info("Il existe plus d'un enregistrement pour : " + sujetMouvementVO.getMouvementVO().getNom() + ", " + sujetMouvementVO.getMouvementVO().getPrenom() + ",  " + sujetMouvementVO.getMouvementVO().getDateNaissance().toString().substring(0,11) + " : " + sujetMouvementVO.getListeSujetVO().size());
     	         		//On demande une v�rification aux personnes concern�es
         	        	envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.SujetDouble, sujetMouvementVO.getMouvementVO().getCodeSite());
         	        	envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.Embauche, sujetMouvementVO.getMouvementVO().getCodeSite());
     				}
     			}
     		}
     	}else{
     		log.info("Aucun sujet trouv�");
     		Sujet sujet = new SujetVO();
     		sujet.setSite( 30 );
     		sujet.setPrenom(sujetMouvementVO.getMouvementVO().getPrenom());
     		sujet.setNom(sujetMouvementVO.getMouvementVO().getNom());
     		sujet.setDateNaissance(sujetMouvementVO.getMouvementVO().getDateNaissance());
     		sujet.setConfidentialite( GlobalConstants.Confidentialite.UN );
     		sujet.setStatut( Long.valueOf(GlobalConstants.Statut.SUJET_ACTIF).longValue() );
     		sujet.setSeverite( GlobalConstants.Severite.SEVERITE_3 );
     		sujet.setNumeroClientEmploye(sujetMouvementVO.getMouvementVO().getNumeroClientEmploye());
     		sujet.setNumeroFiche("                                                 *"); // nouveau sujet
         	creerSujet( sujet, connection );
         	sujetMouvementVO.setSujetConcerne(sujet);
	        envoyerCourrielDestinataire(subject, connection, sujetMouvementVO, GlobalConstants.TypesIntervention.EmbaucheSansEnquete, sujetMouvementVO.getMouvementVO().getCodeSite());

	        //Lier le sujet � �a soci�t�
	        SocieteInterne societeInterne = SocieteInterne.getInstance(subject);
	        societeInterne.lierSociete( sujetMouvementVO );
     	}
	}
	
    private void creerSujet(Sujet sujetVO, Connection connection) throws DAOException{
 		log.info("Cr�ation d'un sujet pour " + sujetVO.getNom() + ", " + sujetVO.getPrenom() + ",  " + sujetVO.getDateNaissance().toString().substring(0,11) );
		Sujet sujetVONouveau = sujetDAO.insert(sujetVO, connection);
		sujetVO.setCle(sujetVONouveau.getCle());
		sujetVO.setSite(sujetVONouveau.getSite());
		sujetVO.setNumeroFiche(sujetVONouveau.getNumeroFiche());
	}	
	
	private void ecrireDossier(SujetMouvementVO sujetMouvementVO, Connection connection) throws DAOException {
		long categorie = 0;
		MouvementVO mouvementVO = sujetMouvementVO.getMouvementVO();
		Sujet sujet = sujetMouvementVO.getSujetConcerne();
		String typeMouvement = mouvementVO.getTypeMouvement();
		String typeEmploi = mouvementVO.getStatut();
		Timestamp dateDebut = null;
		Timestamp dateFin = null;
		
		//Recherche du num�ro de cl� de la cat�gorie
        if(typeMouvement.equals(GlobalConstants.Mouvements.Conge)){
        	if(typeEmploi.equals(GlobalConstants.Emplois.TempsComplet))
        		categorie = GlobalConstants.Categories.CongeTC;
        	if(typeEmploi.equals(GlobalConstants.Emplois.TempsCompletTemporaire))
        		categorie = GlobalConstants.Categories.CongeTCT;
        	if(typeEmploi.equals(GlobalConstants.Emplois.TempsPartiel))
        		categorie = GlobalConstants.Categories.CongeTP;
        	if(typeEmploi.equals(GlobalConstants.Emplois.TempsPartielTemporaire))
        		categorie = GlobalConstants.Categories.CongeTPT;
        	dateDebut = mouvementVO.getDateConge();
        	dateFin = mouvementVO.getDateFinConge();
        }
        if(typeMouvement.equals(GlobalConstants.Mouvements.Depart)){
        	if(typeEmploi.equals(GlobalConstants.Emplois.TempsComplet))
        		categorie = GlobalConstants.Categories.DepartTC;
        	if(typeEmploi.equals(GlobalConstants.Emplois.TempsCompletTemporaire))
        		categorie = GlobalConstants.Categories.DepartTCT;
        	if(typeEmploi.equals(GlobalConstants.Emplois.TempsPartiel))
        		categorie = GlobalConstants.Categories.DepartTP;
        	if(typeEmploi.equals(GlobalConstants.Emplois.TempsPartielTemporaire))
        		categorie = GlobalConstants.Categories.DepartTPT;
        	dateDebut = mouvementVO.getDateDepart();
        }
        if(typeMouvement.equals(GlobalConstants.Mouvements.Embauche)){
        	if(typeEmploi.equals(GlobalConstants.Emplois.TempsComplet))
        		categorie = GlobalConstants.Categories.EmbaucheTC;
        	if(typeEmploi.equals(GlobalConstants.Emplois.TempsCompletTemporaire))
        		categorie = GlobalConstants.Categories.EmbaucheTCT;
        	if(typeEmploi.equals(GlobalConstants.Emplois.TempsPartiel))
        		categorie = GlobalConstants.Categories.EmbaucheTP;
        	if(typeEmploi.equals(GlobalConstants.Emplois.TempsPartielTemporaire))
        		categorie = GlobalConstants.Categories.EmbaucheTPT;
        	dateDebut = mouvementVO.getDateEmbauche();
        }
        //i_ca_cle
        if(categorie != 0){ //On ne peut cr�er un dossier sans cat�gorie
        	Dossier dossier = new DossierVO();
        	dossier.setCategorie(categorie);
        	dossier.setSeverite(sujet.getSeverite());
        	
		    if(sujet.getSeverite() == 0)
		    	 dossier.setSeverite(GlobalConstants.Severite.SEVERITE_2);
		    else
		    	 dossier.setSeverite(sujet.getSeverite());
        	
        	dossier.setConfidentialite(GlobalConstants.Confidentialite.QUATRE); //confidentialit� 4
        	dossier.setHierarchie(GlobalConstants.NiveauHierachique.UN); //autorit� 1
        	dossier.setStatut(Long.valueOf(GlobalConstants.Statut.DOSSIER_INACTIF)); //actif
        	dossier.setDateDebut(dateDebut);
        	dossier.setDateFin(dateFin);
        	String siteConcerne = mouvementVO.getSiteConcerne().trim();
        	
        	if (StringUtils.isNotEmpty(siteConcerne)){
        		if(siteConcerne.length() < 40)
        			dossier.setNumeroDossier(siteConcerne);
           	    else
           	    	dossier.setNumeroDossier(siteConcerne.substring(0,39));
           }
           String emploi = mouvementVO.getDescriptionEmploi().trim();
           
           if (StringUtils.isNotEmpty(emploi)){
           	    if(emploi.length() < 20)
        			dossier.setReference1(emploi);
           	    else
           	    	dossier.setReference1(emploi.substring(0,19));
           }
           dossier.setIntervenant("CARDEX");
           dossier.setFonde(Long.valueOf(GlobalConstants.Fonde.OUI).longValue()); //Fond�
           dossier.setEnregistrementConserve("no");
           dossier.setEnregistrementNumerique("no");
           sujet.setRole(GlobalConstants.Role.ENQUETE);
			
		   dossier = dossierDAO.insert(subject, dossier, connection);
		   dossierDAO.editLienDossier(dossier, sujet, "I", connection);
        }
	}

	private SujetMouvementVO obtenirSujet(MouvementVO mouvementVO, Connection connection) throws DAOException{
		List<Sujet> listeSujetVO = new ArrayList<Sujet>();
		SujetMouvementVO sujetMouvementVO = new SujetMouvementVO();
		
	 	//Pour chaque embauche, on v�rifie si l'employ� a �t� enqu�t�.
	    //On commence la recherche par le num�ro d'employ� pour traiter les cas d'erreur
		//de date d'embauche.		
		if (StringUtils.isNotEmpty(mouvementVO.getNumeroClientEmploye())){
			log.info("Recherche sujet par num�ro : "+mouvementVO.getNumeroClientEmploye());
			listeSujetVO = sujetDAO.rechercheSujet(mouvementVO.getNumeroClientEmploye(), connection);
			
			if (listeSujetVO.size() > 0)
				sujetMouvementVO.setTrouveMatricule(true);
		}
		
		if (sujetMouvementVO.isTrouveMatricule() == false){
         	//Aucun r�sultat en recherchant avec le num�ro d'employ�. On essaie donc
         	//avec le nom, pr�nom et date de naissance.
			log.info("Num�ro sujet non trouv� par matricule, recherche par pr�nom, nom et date de naissance.");
			listeSujetVO = sujetDAO.rechercheSujet(mouvementVO.getPrenom(), mouvementVO.getNom(), mouvementVO.getDateNaissance(), connection);
        
			if (listeSujetVO.size() > 0)
				sujetMouvementVO.setTrouveNomPrenomDateNaissance(true);
		}
		sujetMouvementVO.setListeSujetVO(listeSujetVO);
		sujetMouvementVO.setMouvementVO(mouvementVO);
		return sujetMouvementVO;
	}
	
	public static void envoyerCourrielDestinataire(final CardexAuthenticationSubject subject, Connection connection, final MessageDuCourriel messageDuCourriel, final String type, final String siteConcerne) throws DAOException {
		CourrielAction.envoyerCourrielDestinataire(subject, connection, GlobalConstants.ListeCache.EXPEDITEUR_COURRIEL_RH, messageDuCourriel, type, siteConcerne);
	}
	
	private void envoyerCourrielDestinataire(CardexAuthenticationSubject subject, Connection connection, String objectMessage, String type, String fin, String snSite) throws DAOException {
		CourrielAction.envoyerCourrielDestinataire(subject, connection, GlobalConstants.ListeCache.EXPEDITEUR_COURRIEL_RH, objectMessage, type, fin, snSite);
	}

	
}
