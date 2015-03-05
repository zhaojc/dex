/*
 * Created on 16-Apr-2008
 */
package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.lotoquebec.cardex.business.delegate.BilletBusinessDelegate;
import com.lotoquebec.cardex.business.vo.BilletVO;
import com.lotoquebec.cardex.business.vo.CriteresRechercheBilletVO;
import com.lotoquebec.cardex.presentation.model.form.BilletForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheBilletForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.BilletDossierTrieListe;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
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
public class RechercheBilletsAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger log = (Logger)LoggerCardex.getLogger((this.getClass()));
    
    public ActionForward rechercheDefaut(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
		log.fine("Recherche de billets");
        CardexUser user = (CardexUser) subject.getUser();

		CriteresRechercheBilletForm criteresRechercheBilletForm = (CriteresRechercheBilletForm) form;
		criteresRechercheBilletForm.init();
		criteresRechercheBilletForm.setEntite(String.valueOf(user.getEntite()));
		
        return mapping.findForward("success");
    }

    public ActionForward rafraichirRecherche(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		log.fine("Rafraichir recherche de billets");

		CriteresRechercheBilletForm criteresRechercheBilletForm = (CriteresRechercheBilletForm) form;
		
        return mapping.findForward("success");
    }
    
    public ActionForward soumettreRecherche(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
		log.fine("Soumettre la recherche de billets");
		ActionMessages errors = new ActionMessages();
		CriteresRechercheBilletForm criteresRechercheBilletForm = (CriteresRechercheBilletForm) form;
		CriteresRechercheBilletVO criteresRechercheBilletVO = new CriteresRechercheBilletVO();
		
		if (criteresRechercheBilletForm == null)
			throw new RuntimeException("criteresRechercheBilletForm est null");
		
        try {
    		if (criteresRechercheBilletForm != null && criteresRechercheBilletForm.getListeResultat() != null)
    			criteresRechercheBilletForm.getListeResultat().vider();
    		
    		ValueObjectMapper.convert(criteresRechercheBilletForm, criteresRechercheBilletVO, subject.getLocale());
			
			List<BilletForm> currentList = new ArrayList<BilletForm>();		
			BilletBusinessDelegate delegate = new BilletBusinessDelegate();
			List<BilletVO> listeBillet = delegate.recherche(subject, criteresRechercheBilletVO);
			
	        for (BilletVO billetVO:listeBillet) {
	        	BilletForm billetForm = new BilletForm();
	        	ValueObjectMapper.convert(billetVO, billetForm, request.getLocale());
	        	DossierForm dossierForm = new DossierForm();
	        	ValueObjectMapper.convertDossier(billetVO.getDossierVO(), dossierForm, request.getLocale());
	        	dossierForm.assignerValeurDeListe(subject);
	        	billetForm.setDossierForm(dossierForm);
	            currentList.add(billetForm);
	        }
			
	        criteresRechercheBilletForm.setListeResultat( currentList );
	        criteresRechercheBilletForm.getListeResultat().assignerTrierDefault(BilletDossierTrieListe.CLE_NUMERO_CARDEX, true, new BilletDossierTrieListe());
			
		} catch (BusinessResourceException e) {
            handleBusinessResourceException(e, errors, request);
            return mapping.getInputForward();
		} catch (BusinessException e) {
			handleBusinessException(e, errors, request);
			return mapping.getInputForward();
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
			return mapping.findForward("error");
		} catch (ParseException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		}

		return mapping.findForward("success");
    }
	
}
