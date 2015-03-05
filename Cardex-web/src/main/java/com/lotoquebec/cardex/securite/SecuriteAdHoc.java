package com.lotoquebec.cardex.securite;

import java.util.Collection;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Modifiable;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Partage;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.facade.DossierSessionFacade;
import com.lotoquebec.cardex.business.facade.SocieteSessionFacade;
import com.lotoquebec.cardex.business.facade.SujetSessionFacade;
import com.lotoquebec.cardex.business.facade.VehiculeSessionFacade;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.FichierMultimediaVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.VehiculeVO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.user.CardexUser;

public class SecuriteAdHoc {

	/**
	 * Il faut s'assurer qu'il était possible de modifier l'enregistrement avant de le modifier.
	 * Ici on regarde, indirectement, que l'utilisateur est dans le même site que le dossier qu'il modifie.
	 * @param subject
	 * @param dossierSource
	 */
	public static void validerSecuriteModification(CardexAuthenticationSubject subject, Modifiable modifiable){
		
		if (modifiable.isModifiable() == false){
			String message = "modification du "+modifiable.toString();
			GestionnaireSecurite.genererErreurSecurite(subject, "Aucun rôle", message);
		}
	}
	
	public static void validerSecuriteModificationNarration(CardexAuthenticationSubject subject, Narration narrationSource, Narration narrationDestination){
		
		if (narrationSource.isModifiable() == false){
			
			if (narrationSource.getNarrationAvecFormat().equals(narrationDestination.getNarrationAvecFormat()) == false){
				String message = "modification du "+narrationSource.toString();
				GestionnaireSecurite.genererErreurSecurite(subject, "Aucun rôle", message);
			}
		}
	}	
	
	/**
     * On valide si l'usager a le droit à l'onglet de l'entité qu'il consulte
     * Ensuite, on va consulter cette entité pour être sur qu'il a le droit de la consulter. 
	 * @param subject
	 * @param fichierMultimediaVO
	 * @throws BusinessRuleException 
	 * @throws BusinessException 
	 */
	public static void validerSecuriteConsulterPhoto(CardexAuthenticationSubject subject, FichierMultimediaVO fichierMultimediaVO) throws BusinessRuleException, BusinessException{
		//GestionnaireSecurite.validerEtFiltrerSecuriteConsulter(subject, fichierMultimediaVO);
		String message = "le fichier numéro "+fichierMultimediaVO.getCle()+"-"+fichierMultimediaVO.getSite();
		
		if (GlobalConstants.GenreFichier.SOCIETE.equals(fichierMultimediaVO.getGenre())){
			
			if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_PH0TOS_ONGLET) == false)
				GestionnaireSecuriteCardex.genererErreurSecurite(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_PH0TOS_ONGLET, message);								
			
			Societe criteria = new SocieteVO(fichierMultimediaVO.getCle(), fichierMultimediaVO.getSite());
			(new SocieteSessionFacade()).find(subject, criteria);
			
		}else if (GlobalConstants.GenreFichier.SUJET.equals(fichierMultimediaVO.getGenre())){
			
			if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_PHOTOS_ONGLET) == false)
				GestionnaireSecuriteCardex.genererErreurSecurite(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_PHOTOS_ONGLET, message);								
			
			Sujet criteria = new SujetVO(fichierMultimediaVO.getCle(), fichierMultimediaVO.getSite());
			(new SujetSessionFacade()).find(subject, criteria);
			
		} else if (GlobalConstants.GenreFichier.DOSSIER.equals(fichierMultimediaVO.getGenre())){
			
			// Onglet pièces jointes
			if (GlobalConstants.TypeMutliMedia.AUTRE.equals(fichierMultimediaVO.getStringTypeMultimedia())
			|| GlobalConstants.TypeMutliMedia.DOCUMENT_ANNEXE.equals(fichierMultimediaVO.getStringTypeMultimedia())
			|| GlobalConstants.TypeMutliMedia.FICHIER.equals(fichierMultimediaVO.getStringTypeMultimedia())
			|| GlobalConstants.TypeMutliMedia.SON.equals(fichierMultimediaVO.getStringTypeMultimedia())
			|| GlobalConstants.TypeMutliMedia.VIDEO.equals(fichierMultimediaVO.getStringTypeMultimedia())){
				
				if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_PIECES_ONGLET) == false){
					GestionnaireSecuriteCardex.genererErreurSecurite(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_PIECES_ONGLET, message);								
				}
			}else{ // Onglet Images
				if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_PHOTOS_ONGLET) == false){
					GestionnaireSecuriteCardex.genererErreurSecurite(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_PHOTOS_ONGLET, message);								
				}				
			}
			
			Dossier criteria = new DossierVO(fichierMultimediaVO.getCle(), fichierMultimediaVO.getSite());
			(new DossierSessionFacade()).find(subject, criteria);
		} else if (GlobalConstants.GenreFichier.VEHICULE.equals(fichierMultimediaVO.getGenre())){
			
			if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.VEHICULE_PHOTOS_ONGLET) == false)
				GestionnaireSecuriteCardex.genererErreurSecurite(subject, GlobalConstants.SecuriteRoleAdhoc.VEHICULE_PHOTOS_ONGLET, message);								
			
			Vehicule criteria = new VehiculeVO(fichierMultimediaVO.getCle(), fichierMultimediaVO.getSite());
			(new VehiculeSessionFacade()).find(subject, criteria);
		}
	}
	
	public static void validerSecuriteConsultationDossierPartage(CardexAuthenticationSubject subject, Dossier dossier) throws DAOException{
		CardexUser user = (CardexUser)subject.getUser();
		//On vérifie s'il s'agit d'un dossier partagé.
    	Partage partage = FabriqueCardexDAO.getInstance().getPartageDAO().findPartage(subject, dossier);
    	//Si le dossier est partagé, on vérifie si l'utilisateur y a droit.
    	if(!partage.getIntervenantsChoisis().isEmpty()){
            Collection intervenantsChoisis = partage.getIntervenantsChoisis();
            //S'il s'agit d'un partage restreint, l'utilisateur doit absolument faire partie de la liste des intervenants autorisés.
            //Sinon, il s'agit d'un partage ouvert. Dans ce cas, on donne accès, sans égard aux autres contrainte de sécurité, 
            //si l'utilisateur a été en mesure de cliquer sur le dossier.
            if(partage.getGenrePartage().equals(GlobalConstants.GenrePartage.RESTREINT)){
            	if(intervenantsChoisis.contains(user.getCode()) == false)
            		//Si l'utilisateur n'est pas dans la liste de partage, on lui bloque l'accès. Dans le cas contraire, le dossier passe.
            		GestionnaireSecuriteCardex.genererErreurSecurite(subject, "", dossier.getNumeroCardex() + "!");
            }
    	}else{
    		//Le dossier n'est pas partagé; on effectue la vérification normale de l'accès.
    		GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulterDossierIntervenantEstAssigne(subject, dossier);
    	}
	}
}
