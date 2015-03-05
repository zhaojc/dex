package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.text.ParseException;
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
import com.lotoquebec.cardex.presentation.model.form.BilletForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.util.ValidationFabrique;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class BilletAction extends AbstractAction {

	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));
	private BilletBusinessDelegate delegate = null;
	
	public BilletAction() throws BusinessResourceException {
		super();
		delegate = new BilletBusinessDelegate();
	}

	public ActionForward consulter(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
        log.fine("Accès à un billet");
        ActionMessages errors = new ActionMessages();
        BilletForm billetForm = (BilletForm)form;    	
        BilletVO billetVO = new BilletVO();

        try {
        	ValueObjectMapper.convert(billetForm, billetVO, request.getLocale());
        	billetVO = delegate.trouverBillet(subject, billetVO);
			ValueObjectMapper.convert(billetVO, billetForm, request.getLocale());
		} catch (BusinessResourceException e) {
            handleBusinessResourceException(e, errors, request);
            return mapping.getInputForward();
		} catch (BusinessException e) {
			handleBusinessException(e, errors, request);
			return mapping.getInputForward();
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		} catch (ParseException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		}	
        
    	return mapping.findForward("success");
    }
    
    public ActionForward creerDossier(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
        log.fine("Créer un billet");
        BilletForm billetForm = (BilletForm)form;    	
        billetForm.init();
        
        ajouterLienDossier(request, billetForm);
        
    	return mapping.findForward("success");
    }    
    
    private void ajouterLienDossier(HttpServletRequest request, BilletForm billetForm) {
		DossierForm dossierForm = (DossierForm) request.getSession().getAttribute("dossier");
		billetForm.setLien( Long.valueOf(dossierForm.getCle()) );
		billetForm.setLienSite( Long.valueOf(dossierForm.getSite()) );
		billetForm.setLienGenre(GlobalConstants.GenreFichier.DOSSIER);
	}

	public ActionForward ajouter(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
    	log.fine("Ajouter un billet");
        ActionMessages errors = new ActionMessages();
        BilletForm billetForm = (BilletForm)form;
        BilletVO billetVO = new BilletVO();
        errors.add( billetForm.validate(mapping, request) );
        BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
        
        if (errors.isEmpty() == false){
        	saveErrors(request, errors);
        	return mapping.getInputForward();
        }
        
        try {
        	ValueObjectMapper.convert(billetForm, billetVO, request.getLocale());
        	businessRuleExceptionHandle = delegate.ajouter(subject, billetVO);
	         //On vérifie le message d'avertissement retourné s'il y a lieu pour pouvoir l'afficher à l'utilisateur
            if(StringUtils.isNotEmpty(businessRuleExceptionHandle.getBusinessException().getBusinessMessageResult().toString())){
                handleBusinessException(businessRuleExceptionHandle.getBusinessException(), errors, request);
             }

			ValueObjectMapper.convert(billetVO, billetForm, request.getLocale());
			remettreDossier(billetForm, request);
		} catch (BusinessResourceException e) {
            handleBusinessResourceException(e, errors, request);
            return mapping.getInputForward();
		} catch (BusinessException e) {
			handleBusinessException(e, errors, request);
			return mapping.getInputForward();
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		} catch (ParseException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		}

    	return mapping.findForward("success");
    }    

    public ActionForward modifier(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
    	log.fine("Modifier un billet");
        ActionMessages errors = new ActionMessages();
        BilletForm billetForm = (BilletForm)form;    	
        BilletVO billetVO = new BilletVO();
        errors.add( billetForm.validate(mapping, request) );
        
        if (errors.isEmpty() == false){
        	saveErrors(request, errors);
        	return mapping.getInputForward();
        }
        
        try {
        	ValueObjectMapper.convert(billetForm, billetVO, request.getLocale());
			delegate.modifier(subject, billetVO);
			ValueObjectMapper.convert(billetVO, billetForm, request.getLocale());
			remettreDossier(billetForm, request);
		} catch (BusinessResourceException e) {
            handleBusinessResourceException(e, errors, request);
            return mapping.getInputForward();
		} catch (BusinessException e) {
			handleBusinessException(e, errors, request);
			return mapping.getInputForward();
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		} catch (ParseException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		}
		
    	return mapping.findForward("success");
    }

    public ActionForward rechercheProvenance(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException, ServletException {
    	log.fine("Recherche provenance d'un billet");
        ActionMessages errors = new ActionMessages();
        BilletForm billetForm = (BilletForm)form;
        BilletVO billetVO = new BilletVO();
        billetForm.setNomDetaillantProvenance("");
        ValidationFabrique.valideRechercheProvenance(subject, billetForm, this.getResources(request)).valider( errors );
        
        if (errors.isEmpty() == false){
        	saveErrors(request, errors);
        	return mapping.getInputForward();
        }
        
        try {
        	ValueObjectMapper.convert(billetForm, billetVO, request.getLocale());
			delegate.rechercheProvenance(subject, billetVO);
			ValueObjectMapper.convert(billetVO, billetForm, request.getLocale());
		} catch (BusinessResourceException e) {
            handleBusinessResourceException(e, errors, request);
            return mapping.getInputForward();
		} catch (BusinessException e) {
			handleBusinessException(e, errors, request);
			return mapping.getInputForward();
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		} catch (ParseException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		}
        
    	return mapping.findForward("successRechercheSociete");
    }

    public ActionForward rechercheVerification(CardexAuthenticationSubject subject,
    	    ActionMapping mapping,
    	    ActionForm form,
    	    HttpServletRequest request,
    	    HttpServletResponse response) throws IOException, ServletException {
    	    	log.fine("Recherche vérification d'un billet");
    	        ActionMessages errors = new ActionMessages();
    	        BilletForm billetForm = (BilletForm)form;
    	        BilletVO billetVO = new BilletVO();
    	        billetForm.setNomDetaillantVerification("");
    	        ValidationFabrique.valideRechercheVerification(subject, billetForm, this.getResources(request)).valider( errors );
    	        
    	        if (errors.isEmpty() == false){
    	        	saveErrors(request, errors);
    	        	return mapping.getInputForward();
    	        }
    	        
    	        try {
    	        	ValueObjectMapper.convert(billetForm, billetVO, request.getLocale());
    				delegate.rechercheVerification(subject, billetVO);
    				ValueObjectMapper.convert(billetVO, billetForm, request.getLocale());
    			} catch (BusinessResourceException e) {
    	            handleBusinessResourceException(e, errors, request);
    	            return mapping.getInputForward();
    			} catch (BusinessException e) {
    				handleBusinessException(e, errors, request);
    				return mapping.getInputForward();
    			} catch (ValueObjectMapperException e) {
    				e.printStackTrace();
    				return mapping.getInputForward();
    			} catch (ParseException e) {
    				e.printStackTrace();
    				return mapping.getInputForward();
    			}
    	        
    	    	return mapping.findForward("successRechercheSociete");
    	    }

    public ActionForward rechercheFautif(CardexAuthenticationSubject subject,
    	    ActionMapping mapping,
    	    ActionForm form,
    	    HttpServletRequest request,
    	    HttpServletResponse response) throws IOException, ServletException {
    	    	log.fine("Recherche détaillant fautif");
    	        ActionMessages errors = new ActionMessages();
    	        BilletForm billetForm = (BilletForm)form;
    	        BilletVO billetVO = new BilletVO();
    	        billetForm.setNomDetaillantFautif("");
    	        ValidationFabrique.valideRechercheFautif(subject, billetForm, this.getResources(request)).valider( errors );
    	        
    	        if (errors.isEmpty() == false){
    	        	saveErrors(request, errors);
    	        	return mapping.getInputForward();
    	        }
    	        
    	        try {
    	        	ValueObjectMapper.convert(billetForm, billetVO, request.getLocale());
    				delegate.rechercheFautif(subject, billetVO);
    				ValueObjectMapper.convert(billetVO, billetForm, request.getLocale());
    			} catch (BusinessResourceException e) {
    	            handleBusinessResourceException(e, errors, request);
    	            return mapping.getInputForward();
    			} catch (BusinessException e) {
    				handleBusinessException(e, errors, request);
    				return mapping.getInputForward();
    			} catch (ValueObjectMapperException e) {
    				e.printStackTrace();
    				return mapping.getInputForward();
    			} catch (ParseException e) {
    				e.printStackTrace();
    				return mapping.getInputForward();
    			}
    	        
    	    	return mapping.findForward("successRechercheSociete");
    	    }

    private void remettreDossier(BilletForm billetForm, HttpServletRequest request){
    	DossierForm dossierForm = new DossierForm();
    	dossierForm.setCle(String.valueOf(billetForm.getLien()));
    	dossierForm.setSite(String.valueOf(billetForm.getLienSite()));
    	request.getSession().setAttribute("dossier", dossierForm);
    }
    
    public ActionForward rechercheValidation(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
    	log.fine("Recherche validation d'un billet");
        ActionMessages errors = new ActionMessages();
        BilletForm billetForm = (BilletForm)form;
        BilletVO billetVO = new BilletVO();
        billetForm.setNomDetaillantValidation("");
        ValidationFabrique.valideRechercheValidation(subject, billetForm, this.getResources(request)).valider( errors );
        
        if (errors.isEmpty() == false){
        	saveErrors(request, errors);
        	return mapping.getInputForward();
        }
        
        try {
        	ValueObjectMapper.convert(billetForm, billetVO, request.getLocale());
			delegate.rechercheValidation(subject, billetVO);
			ValueObjectMapper.convert(billetVO, billetForm, request.getLocale());
		} catch (BusinessResourceException e) {
            handleBusinessResourceException(e, errors, request);
            return mapping.getInputForward();
		} catch (BusinessException e) {
			handleBusinessException(e, errors, request);
			return mapping.getInputForward();
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		} catch (ParseException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		}
        
    	return mapping.findForward("successRechercheSociete");
    }
    
    public ActionForward retour(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
    	log.fine("Retour d'un billet");
    	BilletForm billetForm = (BilletForm)form;
    	remettreDossier(billetForm, request);
    	
    	return mapping.findForward("success");
    }    
}
