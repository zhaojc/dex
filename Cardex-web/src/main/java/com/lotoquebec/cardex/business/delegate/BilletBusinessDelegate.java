package com.lotoquebec.cardex.business.delegate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.collections.CollectionUtils;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.exception.BilletBusinessRuleException;
import com.lotoquebec.cardex.business.facade.DossierSessionFacade;
import com.lotoquebec.cardex.business.facade.FabriqueFacade;
import com.lotoquebec.cardex.business.facade.SocieteSessionFacade;
import com.lotoquebec.cardex.business.vo.BilletVO;
import com.lotoquebec.cardex.business.vo.CriteresRechercheBilletVO;
import com.lotoquebec.cardex.business.vo.CriteresRechercheSocieteVO;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.LiaisonBilletSocieteVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * Le BilletBusinessDelegate offre les
 * services d'affaires concernant l'objet
 * billet.
 * @author levassc
 *
 */
public class BilletBusinessDelegate extends BusinessDelegate {

	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    public BilletBusinessDelegate() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	/**
	 * Lors de l'ajout ou la modification d'un billet il faut ajouter/supprimer (gérer)
	 * les liaisons entres le dossier et la société.
	 * Pour ce billet
	 * @param subject
	 * @param billetVO
	 * @throws BusinessException
	 * @throws BusinessResourceException
	 */
    public BusinessRuleExceptionHandle ajouter(CardexAuthenticationSubject subject, BilletVO billetVO) throws BusinessException, BusinessResourceException {
		try {
			FabriqueFacade.getBilletSessionFacade().ajouter(subject, billetVO);
			return gererLiaisonBillet(subject, null, billetVO);
		} catch (BusinessRuleException e) {
			e.printStackTrace();
			throw handleBilletBusinessRuleException(e);
		}
	}
    
    public BusinessRuleExceptionHandle gererLiaisonBillet(CardexAuthenticationSubject subject, BilletVO ancienBilletVO, BilletVO billetVOFinal) throws BusinessRuleException, BusinessException{
		Dossier dossierLien = null;
		DossierSessionFacade dossierSessionFacade = FabriqueFacade.getDossierSessionFacade();
		BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
		List<LiaisonBilletSocieteVO> liaisonsPresente = new ArrayList<LiaisonBilletSocieteVO>();
		
		if (ancienBilletVO != null){
			dossierLien = new DossierVO(ancienBilletVO.getLien(),ancienBilletVO.getLienSite());
			liaisonsPresente = dossierSessionFacade.obtenirLienDossierSocietePresentsBillet(subject, dossierLien);
		}
		
		if (billetVOFinal != null){
			dossierLien = new DossierVO(billetVOFinal.getLien(),billetVOFinal.getLienSite());
			liaisonsPresente = dossierSessionFacade.obtenirLienDossierSocietePresentsBillet(subject, dossierLien);
		}

		List<LiaisonBilletSocieteVO> liaisonsRequis = dossierSessionFacade.obtenirLienDossierSocieteRequisBillet(subject, dossierLien);
		
		List<LiaisonBilletSocieteVO> liaisonsASupprimer = (List<LiaisonBilletSocieteVO>) CollectionUtils.subtract(liaisonsPresente, liaisonsRequis);
		List<LiaisonBilletSocieteVO> liaisonsAAjouter = (List<LiaisonBilletSocieteVO>) CollectionUtils.subtract(liaisonsRequis, liaisonsPresente);
		
		for(LiaisonBilletSocieteVO liaisonASupprimer:liaisonsASupprimer){
			liaisonASupprimer.getSociete().setLien( liaisonASupprimer.getCleLDD() );
			liaisonASupprimer.getSociete().setLienSite( liaisonASupprimer.getSiteLDD() );
			dossierSessionFacade.deleteLienSociete(subject, liaisonASupprimer.getDossier(), liaisonASupprimer.getSociete());
		}
		
		for(LiaisonBilletSocieteVO liaisonAAjouter:liaisonsAAjouter){
			liaisonAAjouter.getSociete().setRole( liaisonAAjouter.getRole() );
			businessRuleExceptionHandle = dossierSessionFacade.addLienSociete(subject, liaisonAAjouter.getDossier(), liaisonAAjouter.getSociete());
		}
		return businessRuleExceptionHandle;
    }
    
    public void supprimer(CardexAuthenticationSubject subject, BilletVO billetVO) throws BusinessException, BusinessResourceException {
    	try {
    		FabriqueFacade.getBilletSessionFacade().supprimer(subject, billetVO);
			gererLiaisonBillet(subject, billetVO, null);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}
    
    public void modifier(CardexAuthenticationSubject subject, BilletVO billetVO) throws BusinessException, BusinessResourceException {
		try {
			BilletVO ancienBilletVO = trouverBillet(subject, billetVO);
			FabriqueFacade.getBilletSessionFacade().modifier(subject, billetVO);
			gererLiaisonBillet(subject, ancienBilletVO, billetVO);
		} catch (BusinessRuleException e) {
			throw handleBilletBusinessRuleException(e);
		}
	}

    public void rechercheProvenance(CardexAuthenticationSubject subject, BilletVO billetVO) throws BusinessException, BusinessResourceException {

		try {
			SocieteVO societeVO = obtenirSocieteParNumeroFiche(subject, billetVO.getNumeroDetaillantProvenance());
			billetVO.setCleSocieteProvenance(societeVO.getCle());
			billetVO.setSiteSocieteProvenance(societeVO.getSite());
			billetVO.setNomDetaillantProvenance(societeVO.getNumeroFiche()+"-"+societeVO.getNom());
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}
    
    public void rechercheVerification(CardexAuthenticationSubject subject, BilletVO billetVO) throws BusinessException, BusinessResourceException {

		try {
			SocieteVO societeVO = obtenirSocieteParNumeroFiche(subject, billetVO.getNumeroDetaillantVerification());
			billetVO.setCleSocieteVerification(societeVO.getCle());
			billetVO.setSiteSocieteVerification(societeVO.getSite());
			billetVO.setNomDetaillantVerification(societeVO.getNumeroFiche()+"-"+societeVO.getNom());
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}

    public void rechercheFautif(CardexAuthenticationSubject subject, BilletVO billetVO) throws BusinessException, BusinessResourceException {

		try {
			SocieteVO societeVO = obtenirSocieteParNumeroFiche(subject, billetVO.getNumeroDetaillantFautif());
			billetVO.setCleSocieteFautif(societeVO.getCle());
			billetVO.setSiteSocieteFautif(societeVO.getSite());
			billetVO.setNomDetaillantFautif(societeVO.getNumeroFiche()+"-"+societeVO.getNom());
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}

    private SocieteVO obtenirSocieteParNumeroFiche(CardexAuthenticationSubject subject, String numeroFiche) throws BusinessException, BusinessRuleException{
    	CriteresRechercheSocieteVO criteria = new CriteresRechercheSocieteVO();
    	criteria.setNumeroFiche(numeroFiche);
    	
    	List<Societe> listSociete = FabriqueFacade.getSocieteSessionFacade().select(subject, criteria);

		if (listSociete.size() == 0){
			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
			businessRuleExceptionHandle.add("err.aucune.societe.trouvee", numeroFiche);
			throw businessRuleExceptionHandle.getBusinessException();
		}
		if (listSociete.size() > 1){
			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
			businessRuleExceptionHandle.add("err.plusieur.societe.trouvee", numeroFiche);
			throw businessRuleExceptionHandle.getBusinessException();				
		}
		return (SocieteVO) listSociete.get(0);    	
    }
    
    public void rechercheValidation(CardexAuthenticationSubject subject, BilletVO billetVO) throws BusinessException, BusinessResourceException {
		try {
			SocieteVO societeVO = obtenirSocieteParNumeroFiche(subject, billetVO.getNumeroDetaillantValidation());
			billetVO.setCleSocieteValidation(societeVO.getCle());
			billetVO.setSiteSocieteValidation(societeVO.getSite());
			billetVO.setNomDetaillantValidation(societeVO.getNumeroFiche()+"-"+societeVO.getNom());
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}

    public List<BilletVO> recherche(CardexAuthenticationSubject subject, CriteresRechercheBilletVO criteresRechercheBilletVO) throws BusinessException, BusinessResourceException {
		try {
			return FabriqueFacade.getBilletSessionFacade().recherche(subject, criteresRechercheBilletVO);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}
    
    public BilletVO trouverBillet(CardexAuthenticationSubject subject, BilletVO billetVO) throws BusinessException, BusinessResourceException {
		try {
			BilletVO billetVOSortie =  FabriqueFacade.getBilletSessionFacade().trouverBillet(subject, billetVO);
			assignerSocieteProvenanceEtValidation(subject, billetVOSortie);
			return billetVOSortie;
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}
    
    public void assignerSocieteProvenanceEtValidation(CardexAuthenticationSubject subject, BilletVO billetVO) throws BusinessException{
		try {
			SocieteVO societeVOCritere = new SocieteVO();
			SocieteSessionFacade societeSessionFacade = FabriqueFacade.getSocieteSessionFacade();
			
			if (billetVO.isPossedeSocieteProvenance()){
				societeVOCritere.setCle(billetVO.getCleSocieteProvenance());
				societeVOCritere.setSite(billetVO.getSiteSocieteProvenance());
				Societe societeVO = societeSessionFacade.find(subject, societeVOCritere);
				billetVO.setNumeroDetaillantProvenance(societeVO.getNumeroFiche());
				billetVO.setNomDetaillantProvenance(societeVO.getNom());
			}
			
			if (billetVO.isPossedeSocieteValidation()){
				societeVOCritere.setCle(billetVO.getCleSocieteValidation());
				societeVOCritere.setSite(billetVO.getSiteSocieteValidation());
				Societe societeVO = societeSessionFacade.find(subject, societeVOCritere);
				billetVO.setNumeroDetaillantValidation(societeVO.getNumeroFiche());
				billetVO.setNomDetaillantValidation(societeVO.getNom());
			}			
			
			if (billetVO.isPossedeSocieteVerification()){
				societeVOCritere.setCle(billetVO.getCleSocieteVerification());
				societeVOCritere.setSite(billetVO.getSiteSocieteVerification());
				Societe societeVO = societeSessionFacade.find(subject, societeVOCritere);
				billetVO.setNumeroDetaillantVerification(societeVO.getNumeroFiche());
				billetVO.setNomDetaillantVerification(societeVO.getNom());
			}			
			if (billetVO.isPossedeSocieteFautif()){
				societeVOCritere.setCle(billetVO.getCleSocieteFautif());
				societeVOCritere.setSite(billetVO.getSiteSocieteFautif());
				Societe societeVO = societeSessionFacade.find(subject, societeVOCritere);
				billetVO.setNumeroDetaillantFautif(societeVO.getNumeroFiche());
				billetVO.setNomDetaillantFautif(societeVO.getNom());
			}			

		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}    	
    }

    /**
     * Construit une BusinessException contenant les messages d'erreurs qui
     * doivent être présenté à un utilisateur. Cette méthode fait la mise en
     * correspondance entre les codes de règles d'affaires reçus d'une
     * BusinessRuleException et les messsages qui doivent être affiché
     * à un utilisateur.
     *
     * @param bre BusinessRuleException BusinessRuleException contenant
     *            les codes de règles d'affaires
     *
     * @return BusinessException BusinessException contenant les messages
     * d'erreurs qui doivent être présenté à un utilisateur.
     */
    private BusinessException handleBilletBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        int                   businessRule = bre.getBusinessRule();

        log.fine("handleBilletBusinessRuleException" );
        switch (businessRule) {

        case BilletBusinessRuleException.NUMERO_DE_CONTROLE_INVALID:
            message.setKey("cardex_numero_controle_invalid");
            break;

        default:
            throw new IllegalArgumentException("La règle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une règle supportée pour un objet d'affaire du type '"
                                               + Dossier.class.getName()
                                               + "'");
        }
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }
}
