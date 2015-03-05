/*
 * Created on 16-Apr-2008
 */
package com.lotoquebec.cardex.presentation.controller.adresses;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.lotoquebec.cardex.business.CriteresRechercheAdresses;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.SocieteBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.CriteresRechercheAdressesVO;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheAdressesForm;
import com.lotoquebec.cardex.presentation.model.form.SocieteForm;
import com.lotoquebec.cardex.presentation.model.form.SujetForm;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SocieteTrieListe;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SujetTrieListe;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.user.CardexUser;

/**
 * @author levassc
 */
public class RechercheAdressesAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));
    
    public ActionForward rechercheDefault(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
		log.fine("Recherche d'une adresses");

		CriteresRechercheAdressesForm adressesForm = (CriteresRechercheAdressesForm) form;
		CardexUser user = (CardexUser) subject.getUser();
		adressesForm.init( user );
		
        return mapping.findForward("success");
    }

    public ActionForward rafraichirRecherche(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		log.fine("Rafraichir recherche d'une adresses");

        return mapping.findForward("success");
    }
    
    public ActionForward soumettreRecherche(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException, ValueObjectMapperException {
		log.fine("Soumettre la recherche d'une adresses");
		CriteresRechercheAdressesForm adressesForm = (CriteresRechercheAdressesForm) form;
		CriteresRechercheAdresses rechercheAdressesVO = new CriteresRechercheAdressesVO();
		
    	adressesForm.preRecherche();
		ValueObjectMapper.convert(adressesForm, rechercheAdressesVO);
		
		if (GlobalConstants.ChoixRechercheAdresse.SOCIETE.equals( adressesForm.getEntiteRecherche() )){
			rechercheAdresseSociete(subject, request, adressesForm, rechercheAdressesVO);				
			return mapping.findForward("successSociete");
			
		}else if (GlobalConstants.ChoixRechercheAdresse.SUJET.equals( adressesForm.getEntiteRecherche() )){
			rechercheAdresseSujet(subject, request, adressesForm, rechercheAdressesVO);
			return mapping.findForward("successSujet");
		}
		
		if (adressesForm != null && adressesForm.getListeResultat() != null)
			adressesForm.getListeResultat().vider();
		
		return mapping.getInputForward();
    }

	private void rechercheAdresseSociete(CardexAuthenticationSubject subject, HttpServletRequest request, CriteresRechercheAdressesForm adressesForm, CriteresRechercheAdresses rechercheAdressesVO)  {
		List currentList = new ArrayList();
		ActionMessages errors = new ActionMessages();
		try {
			SocieteBusinessDelegate delegate = new SocieteBusinessDelegate();
			List listeSociete = delegate.rechercheAdresseSociete(subject, rechercheAdressesVO);
			Iterator iter = listeSociete.iterator();
			
	        while (iter.hasNext()) {
	            Societe societe = (Societe)iter.next();
	            SocieteForm societeForm = new SocieteForm();
	            ValueObjectMapper.convertSociete(societe, societeForm, subject.getLocale());
	            societeForm.assignerValeurDeListe(subject);
	            currentList.add(societeForm);
	        }		
			
			adressesForm.setListeResultat( currentList );
			adressesForm.getListeResultat().assignerTrierDefault(SocieteTrieListe.CLE_NOM, false, new SocieteTrieListe());
			adressesForm.genererNumeroSequence();
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
		} catch (BusinessResourceException e) {
			handleBusinessResourceException(e, errors, request);
		} catch (BusinessException e) {
			handleBusinessException(e, errors, request);
		}
	}
	
	private void rechercheAdresseSujet(CardexAuthenticationSubject subject, HttpServletRequest request, CriteresRechercheAdressesForm adressesForm, CriteresRechercheAdresses rechercheAdressesVO){
		List currentList = new ArrayList();	
		ActionMessages errors = new ActionMessages();
		
		try {
			SujetBusinessDelegate delegate = new SujetBusinessDelegate();
			List listeSujet = delegate.rechercheAdresseSujet(subject, rechercheAdressesVO);
			Iterator iter = listeSujet.iterator();
			
	        while (iter.hasNext()) {
	            Sujet sujet = (Sujet)iter.next();
	            SujetForm sujetForm = new SujetForm();
	            ValueObjectMapper.convertSujet(sujet, sujetForm, subject.getLocale());
	            sujetForm.assignerValeurDeListe(subject);
	            currentList.add(sujetForm);
	        }
			
			adressesForm.setListeResultat( currentList );
			adressesForm.getListeResultat().assignerTrierDefault(SujetTrieListe.CLE_NOM, false, new SujetTrieListe());
			adressesForm.genererNumeroSequence();
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
		} catch (BusinessResourceException e) {
			handleBusinessResourceException(e, errors, request);
		} catch (BusinessException e) {
			handleBusinessException(e, errors, request);
		}
	}	
	
}
