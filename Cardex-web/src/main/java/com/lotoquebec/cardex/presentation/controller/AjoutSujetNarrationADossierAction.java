/*
 * Created on 19-Mar-2008
 */
package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.AdresseVO;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.presentation.controller.util.narration.NarrationVersSujet;
import com.lotoquebec.cardex.presentation.controller.util.narration.SujetNarration;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.NarrationForm;
import com.lotoquebec.cardex.presentation.model.form.PSUMandatForm;
import com.lotoquebec.cardex.presentation.model.util.ValidationFabrique;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;

/**
 * @author levassc
 */
public class AjoutSujetNarrationADossierAction extends DossierAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));
	
	
    public ActionForward ajoutSujet(CardexAuthenticationSubject subject,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, ParserConfigurationException, SAXException {
    	log.debug("Ajout d'un sujet � partir d'une grille de narration");
		ActionMessages errors = new ActionMessages();
		
		try {
			verifierToken(request);
			DossierBusinessDelegate delegate = new DossierBusinessDelegate();
			
			NarrationForm narrationForm = (NarrationForm) form;
			SujetNarration sujetNarration = NarrationVersSujet.obtenirSujetDeNarration( narrationForm.getNarrationAvecFormat() );
			ValidationFabrique.valideSujetNarration(subject, sujetNarration, this.getResources(request)).valider( errors );
			
			if (errors.isEmpty() == false){
				saveErrors(request, errors);
				return new ActionForward(mapping.getInput());
			}
			Sujet sujetVONew = obtenirSujetVO(subject, sujetNarration);
			creationSujet(subject, sujetVONew);
			Dossier dossierLierANarration = obtenirDossierLierANarration(subject, narrationForm);
			lierSujet(subject, dossierLierANarration, sujetVONew);
			
			if (sujetNarration.isPossedeAdresse()){
	            Adresse adresse = obtenirAdresseVO(subject, sujetNarration);
	            creationAdresseLienSujet(subject, sujetVONew, adresse);
			}
			//On ne fait pas les caract�risques

			// Sauvegarder la narration si elle a chang�e
            //sauvegardeNarration(subject, narrationForm);
			
			//V�rification d'un mandat PSU associ� � la consultation d'un dossier
			verificationMandat(subject, dossierLierANarration);

			// retour consultation dossier
			DossierForm dossierForm = new DossierForm();
			request.getSession().setAttribute("dossier",dossierForm);
			
			dossierLierANarration = delegate.find(subject, dossierLierANarration);
            ValueObjectMapper.convertDossier(dossierLierANarration, dossierForm,subject.getLocale());
            populateDossierFormShow(subject, dossierLierANarration, dossierForm);
			
			return mapping.findForward("success");
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);
			return (new ActionForward(mapping.getInput()));
		} catch (BusinessException be) {
			handleBusinessException(be, errors, request);
			return (new ActionForward(mapping.getInput()));
		} catch (ValueObjectMapperException vome) {
			vome.printStackTrace();
			return (new ActionForward(mapping.getInput()));
		}
	}
    
	/**
	 * @param subject
	 * @param dossierLierANarration
	 * @throws IOException
	 * @throws ServletException
	 */
	private void verificationMandat(CardexAuthenticationSubject subject, Dossier dossierLierANarration) throws IOException, ServletException {
		PSUMandatForm psuMandat = new PSUMandatForm();
		psuMandat.setNumeroDossier(dossierLierANarration.getNumeroDossier());
		psuMandat.setNumeroCardex(dossierLierANarration.getNumeroCardex().toString());
		PSUMandatAction.verificationMandat(subject, psuMandat, GlobalConstants.GenreFichier.DOSSIER, GlobalConstants.TypeAction.CONSULTATION);
	}

	/*private void sauvegardeNarration(CardexAuthenticationSubject subject, NarrationForm narrationForm) throws ValueObjectMapperException, BusinessException, BusinessResourceException {
		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
		Narration narration = new NarrationVO();
		ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narration,
		        subject.getLocale());
		delegate.updateLienNarration(subject, narration);
	}*/

	private void creationAdresseLienSujet(CardexAuthenticationSubject subject, Sujet sujetVONew, Adresse adresse) throws BusinessResourceException, BusinessException {
		log.debug("Ajout d'une adresse et lien avec le sujet de la narration");
		SujetBusinessDelegate delegate = new SujetBusinessDelegate();
		adresse.setLien( sujetVONew.getCle() );
		adresse.setLienSite( sujetVONew.getSite() );
		delegate.addLienAdresse(subject, sujetVONew, adresse);
	}

	/**
	 * @param subject
	 * @param sujetNarration
	 * @return
     * @throws ValueObjectMapperException
     * @throws BusinessResourceException
	 */
	private Adresse obtenirAdresseVO(CardexAuthenticationSubject subject, SujetNarration sujetNarration) throws ValueObjectMapperException, BusinessResourceException {
		Adresse adresse = new AdresseVO();
		ValueObjectMapper.convertSujetNarration(sujetNarration, adresse);
		adresse.setTypeRue( sujetNarration.getTypeRueLong(subject) );
		adresse.setUnite( sujetNarration.getUniteLong(subject) );
		adresse.setPointCardinal( sujetNarration.getPointCardinalLong(subject) );
		adresse.setPays( sujetNarration.getPaysLong(subject) );
		adresse.setProvince( sujetNarration.getProvinceLong(subject) );
		adresse.setVille( sujetNarration.getVilleLong(subject) );
		adresse.setTelephone1( sujetNarration.getTelephoneResidence() );
		adresse.setTelephone2( sujetNarration.getTelephoneAutre() );
		
		return adresse;
	}

	/**
	 * @param subject
	 * @param narrationForm
	 * @param sujetVONew
     * @throws BusinessException
     * @throws BusinessResourceException
     * @throws BusinessResourceException
     * @throws BusinessException
	 */
	private void lierSujet(CardexAuthenticationSubject subject, Dossier dossierLierANarration, Sujet sujetVONew) throws BusinessResourceException, BusinessException{
		log.debug("Liaison d'un sujet au dossier.");
		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
		
		delegate.addLienSujet(subject, dossierLierANarration, sujetVONew);
	}

	private Dossier obtenirDossierLierANarration(CardexAuthenticationSubject subject, NarrationForm narrationForm) throws BusinessResourceException, BusinessException{
		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
		
		Dossier dossierCritere = new DossierVO();
		dossierCritere.setCle( Long.parseLong(narrationForm.getLien()) );
		dossierCritere.setSite( Long.parseLong(narrationForm.getLienSite()) );

        // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
		Dossier dossierLierANarration = delegate.find(subject, dossierCritere);

        if (dossierLierANarration == null)
        	throw new AssertionError("Le dossier rechercher est null dans SujetAction.obtenirDossierLierANarration");
        
        return dossierLierANarration;
	}
	
	/**
	 * @param sujetNarration
	 * @return
	 * @throws ValueObjectMapperException
	 * @throws BusinessResourceException
	 */
	private Sujet obtenirSujetVO(CardexAuthenticationSubject subject, SujetNarration sujetNarration) throws ValueObjectMapperException, BusinessResourceException {
		Sujet sujetVONew = new SujetVO();
		ValueObjectMapper.convertSujetNarration(sujetNarration, sujetVONew);
		sujetVONew.setDateNaissance( new Timestamp( sujetNarration.getDateNaissanceDate().getTime() ) );
		sujetVONew.setRole( sujetNarration.getRoleLong(subject) );
		sujetVONew.setEthnie( sujetNarration.getEthnieLong(subject) );
		sujetVONew.setLangue( sujetNarration.getLangueLong(subject) );
		sujetVONew.setRace( sujetNarration.getRaceLong(subject) );
		sujetVONew.setSexe( sujetNarration.getSexeLong(subject) );
		return sujetVONew;
	}

	/**
     * Cr�ation d'un sujet
	 * @param subject
	 * @param sujetVO
     * @throws BusinessResourceException
     * @throws BusinessException
	 */
	private void creationSujet(CardexAuthenticationSubject subject, Sujet sujetVO) throws BusinessResourceException, BusinessException {
		log.debug("Cr�ation d'un sujet.");
		SujetBusinessDelegate delegate = new SujetBusinessDelegate();
		sujetVO.setConfidentialite( GlobalConstants.Confidentialite.UN );
		Sujet sujetVONew = delegate.create(subject, sujetVO);
		
		sujetVO.setCle( sujetVONew.getCle() );
		sujetVO.setSite( sujetVONew.getSite() );
	}
	
}
