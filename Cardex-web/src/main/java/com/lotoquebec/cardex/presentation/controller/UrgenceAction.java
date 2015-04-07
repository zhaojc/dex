package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Urgence;
import com.lotoquebec.cardex.business.delegate.UrgenceBusinessDelegate;
import com.lotoquebec.cardex.business.vo.CriteresRechercheUrgenceVO;
import com.lotoquebec.cardex.business.vo.UrgenceVO;
import com.lotoquebec.cardex.presentation.model.UrgenceHtmlForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheUrgenceForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.UrgenceForm;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.UrgenceTrieListe;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.IteratorException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class UrgenceAction extends AbstractAction {

	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));
	private UrgenceBusinessDelegate delegate = null;
	
	public UrgenceAction() throws BusinessResourceException {
		super();
		delegate = new UrgenceBusinessDelegate();
	}

	public ActionForward modifier(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
        log.debug("Acc�s � une intervention d'un service d'urgence");
        ActionMessages errors = new ActionMessages();
        UrgenceForm urgenceForm = (UrgenceForm)form;    	
        Urgence urgenceVO = new UrgenceVO();

        try {
        	ValueObjectMapper.convertUrgenceHtmlForm(urgenceForm, urgenceVO, request.getLocale());
        	urgenceVO = delegate.find(subject, urgenceVO);
			ValueObjectMapper.convertUrgence(urgenceVO, urgenceForm, request.getLocale());
			//On recompose la cl� pour l'affichage de la soci�t�
				urgenceForm.setLienSociete(urgenceForm.getLienSociete()+"-"+urgenceForm.getLienSiteSociete());
	        //Selon la classe s�lectionn�e, on affiche l'�cran correspondant
			String classe = urgenceForm.getClasse();
	        if(classe.equals(String.valueOf(GlobalConstants.Classes.AMBULANCE)) || classe.equals("0")){
	        	//La classe peut �tre "0" dans le cas d'un statut Refus avant appel.
	        	return mapping.findForward("successAmbulance");
	        }
	        if(classe.equals(String.valueOf(GlobalConstants.Classes.POLICE))){
	        	return mapping.findForward("successPolice");
	        }
	        if(classe.equals(String.valueOf(GlobalConstants.Classes.POMPIER))){
	        	return mapping.findForward("successPompier");
	        }
	    	return mapping.findForward("error");
			
		} catch (BusinessResourceException e) {
            handleBusinessResourceException(e, errors, request);
            return mapping.getInputForward();
		} catch (BusinessException e) {
			handleBusinessException(e, errors, request);
			return mapping.getInputForward();
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
			return mapping.getInputForward();
		}	
        
    }
    
    public ActionForward ajouter(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
        log.debug("Cr�er une entr�e de service d'urgence");
        UrgenceForm urgenceForm = (UrgenceForm)form;    	
        urgenceForm.init();
        ajouterLienDossier(request, urgenceForm);
        String classe = (String)request.getParameter("choixClasse");
        urgenceForm.setClasse(classe);
        //Selon la classe s�lectionn�e, on affiche l'�cran correspondant
        if(classe.equals(String.valueOf(GlobalConstants.Classes.AMBULANCE))){
        	return mapping.findForward("successAmbulance");
        }
        if(classe.equals(String.valueOf(GlobalConstants.Classes.POLICE))){
        	return mapping.findForward("successPolice");
        }
        if(classe.equals(String.valueOf(GlobalConstants.Classes.POMPIER))){
        	return mapping.findForward("successPompier");
        }
    	return mapping.findForward("error");
    }    
    
    private void ajouterLienDossier(HttpServletRequest request, UrgenceForm urgenceForm) {
		DossierForm dossierForm = (DossierForm) request.getSession().getAttribute("dossier");
		urgenceForm.setLien( dossierForm.getCle() );
		urgenceForm.setLienSite( dossierForm.getSite());
	}

    private void remettreDossier(UrgenceForm urgenceForm, HttpServletRequest request){
    	DossierForm dossierForm = new DossierForm();
    	dossierForm.setCle(String.valueOf(urgenceForm.getLien()));
    	dossierForm.setSite(String.valueOf(urgenceForm.getLienSite()));
    	request.getSession().setAttribute("dossier", dossierForm);
    }
    
    public ActionForward retour(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
    	log.debug("Retour d'une intervention de service d'urgence");
    	UrgenceForm urgenceForm = (UrgenceForm)form;
    	remettreDossier(urgenceForm, request);
    	
    	return mapping.findForward("success");
    }    

    /**
     * <p>
     * Cet �v�nement survient lorsque dans le menu principal, l'utilisateur
     * a choisi de rechercher un service d'urgence. L'application affiche alors 
     * le panneau de recherche des services d'urgence.
     * <p>
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchDefault(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.debug("Recherche par d�fault du service d'urgence");
        ActionMessages errors = new ActionMessages();
        try {

            UrgenceBusinessDelegate delegate = new UrgenceBusinessDelegate();
            CriteresRechercheUrgenceForm criteresRechercheUrgenceHtmlForm = (CriteresRechercheUrgenceForm) form;
            CriteresRechercheUrgenceVO criteresRechercheUrgence = new CriteresRechercheUrgenceVO();

            //Valeur par d�faut
            criteresRechercheUrgenceHtmlForm.init(subject);
            //Initialiset l'�cran avec la classe AMBULANCE par d�faut
            criteresRechercheUrgenceHtmlForm.setClasse(String.valueOf(GlobalConstants.Classes.AMBULANCE));
            
            //Conversion du composant d'�tat(ActionForm) en composant
            //d'affaire(Value Object).
            ValueObjectMapper.convertCriteresRechercheUrgenceHtmlForm(
                    criteresRechercheUrgenceHtmlForm, criteresRechercheUrgence,
                    subject.getLocale());

            //Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Urgence> results = delegate.selectDefault(subject,criteresRechercheUrgence);

            ajouterResultatUrgence(subject, criteresRechercheUrgenceHtmlForm, results);

            // Ajout des services d'urgence dans la composante d'�tat (ActionForm)
            List currentList = new ArrayList();
            Iterator   it = results.iterator();

            while (it.hasNext()) {
                Urgence urgence = (Urgence)it.next();
                UrgenceForm urgenceForm = new UrgenceForm();
                DossierForm dossierForm = new DossierForm();
                ValueObjectMapper.convertUrgence(urgence, urgenceForm,subject.getLocale());
                ValueObjectMapper.convertDossier(urgence.getDossier(),dossierForm,subject.getLocale());
                urgenceForm.setDossier(dossierForm);
                urgenceForm.assignerValeurDeListe( subject );
                currentList.add(urgenceForm);
            }

            criteresRechercheUrgenceHtmlForm.setListeResultat( currentList );
            criteresRechercheUrgenceHtmlForm.getListeResultat().assignerTrierDefault(UrgenceTrieListe.CLE_DOSSIER, true, new UrgenceTrieListe());
            
            return mapping.findForward("success");

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre,errors,request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be,errors,request);
            return (new ActionForward(mapping.getInput()));
        }catch (IteratorException ie) {
            handleIteratorException(ie,errors,request);
            return mapping.findForward("error");
        }catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome,errors,request);
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet �v�nement survient lorsque dans l'�cran de recherche des services d'urgence,
     * l'utilisateur a choisi de rechercher un service d'urgence selon des crit�res
     * diff�rents. L'application affiche alors le panneau de recherche
     * des services d'urgence avec les r�sultats de la nouvelle recherche.
     * <p>
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward search(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {

        log.debug("Recherche du service d'urgence");
        ActionMessages errors = new ActionMessages();
        try {
            UrgenceBusinessDelegate delegate = new UrgenceBusinessDelegate();
            CriteresRechercheUrgenceForm criteresRechercheUrgenceHtmlForm = (CriteresRechercheUrgenceForm) form;
            CriteresRechercheUrgenceVO criteresRechercheUrgence = new CriteresRechercheUrgenceVO();
            criteresRechercheUrgenceHtmlForm.getListeResultat().vider();

            //On extrait la cl� et le site de la soci�t� pour l'insertion
            String cle ="";
            String site ="";
            if(StringUtils.isNotEmpty(criteresRechercheUrgenceHtmlForm.getLienSociete())) {
                int pos = criteresRechercheUrgenceHtmlForm.getLienSociete().indexOf("-");
                cle = criteresRechercheUrgenceHtmlForm.getLienSociete().substring(0,pos);
                site = criteresRechercheUrgenceHtmlForm.getLienSociete().substring(pos+1, criteresRechercheUrgenceHtmlForm.getLienSociete().length());                
            }
            criteresRechercheUrgenceHtmlForm.setLienSociete(cle);
            criteresRechercheUrgenceHtmlForm.setLienSiteSociete(site);

            //Conversion du composant d'�tat(ActionForm) en composant
            //d'affaire(Value Object).
            ValueObjectMapper.convertCriteresRechercheUrgenceHtmlForm(criteresRechercheUrgenceHtmlForm, criteresRechercheUrgence, subject.getLocale());
            //Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Urgence> results = delegate.select(subject,criteresRechercheUrgence);
            ajouterResultatUrgence(subject, criteresRechercheUrgenceHtmlForm, results);

            // Ajout des services d'urgence dans la composante d'�tat (ActionForm)
            List currentList = new ArrayList();
            Iterator   it = results.iterator();

            while (it.hasNext()) {
                Urgence urgence = (Urgence)it.next();
                UrgenceForm urgenceForm = new UrgenceForm();
                DossierForm dossierForm = new DossierForm();
                ValueObjectMapper.convertUrgence(urgence, urgenceForm,subject.getLocale());
                ValueObjectMapper.convertDossier(urgence.getDossier(),dossierForm,subject.getLocale());
                urgenceForm.setDossier(dossierForm);
                urgenceForm.assignerValeurDeListe( subject );
                currentList.add(urgenceForm);
            }

            criteresRechercheUrgenceHtmlForm.setListeResultat( currentList );
            criteresRechercheUrgenceHtmlForm.getListeResultat().assignerTrierDefault(UrgenceTrieListe.CLE_DOSSIER, true, new UrgenceTrieListe());
            
            return mapping.findForward("success");

        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre,errors,request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be,errors,request);
            return (new ActionForward(mapping.getInput()));
        }catch (IteratorException ie) {
            handleIteratorException(ie,errors,request);
            return mapping.findForward("error");
        }catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome,errors,request);
            return mapping.findForward("error");
        }
    }

    /**
     * Rafraichissement de l'�cran de service d'urgence lorsqu'une classe d'urgence est s�lectionn�e
     * de mani�re � initialiser le form et la liste des r�sultats des services d'urgence.
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    public ActionForward refreshUrgence(CardexAuthenticationSubject subject,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,
                                 ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        
        log.debug("Refresh �cran du service d'urgence");
        CriteresRechercheUrgenceForm urgenceForm = (CriteresRechercheUrgenceForm)form;       
        
        urgenceForm.getListeResultat().vider();
        urgenceForm.init(subject);

        return mapping.findForward("success");
    }

    /**
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward resetSearchDefault(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.debug("Param�tres de recherche par d�fault du service d'urgence");

        ActionMessages errors = new ActionMessages();

        try {
            CriteresRechercheUrgenceForm criteresRechercheUrgenceHtmlForm = (CriteresRechercheUrgenceForm) form;
            CriteresRechercheUrgenceVO criteresRechercheUrgence = new CriteresRechercheUrgenceVO();

            criteresRechercheUrgenceHtmlForm.init( subject );

            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheUrgenceHtmlForm(criteresRechercheUrgenceHtmlForm,criteresRechercheUrgence,subject.getLocale());

            return mapping.findForward("success");
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }
    }

    /**
     * @param subject
     * @param criteresRechercheUrgenceHtmlForm
     * @param results
     * @throws IteratorException
     * @throws ValueObjectMapperException
     * @throws BusinessResourceException
     */
    private void ajouterResultatUrgence(CardexAuthenticationSubject subject, CriteresRechercheUrgenceForm criteresRechercheUrgenceHtmlForm, List<Urgence> list) throws IteratorException, ValueObjectMapperException, BusinessResourceException {
        //Ajout des v�hicules dans le composant d'�tat (ActionForm)
        List currentList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()){
          Urgence urgence = (Urgence)it.next();
          log.debug(urgence.toString());
          UrgenceHtmlForm urgenceForm = new  UrgenceForm();
          ValueObjectMapper.convertUrgence(urgence, urgenceForm,
                subject.getLocale());
          urgenceForm.assignerValeurDeListe( subject );
          currentList.add(urgenceForm);
        }
        
        criteresRechercheUrgenceHtmlForm.setListeResultat(currentList);
        criteresRechercheUrgenceHtmlForm.getListeResultat().assignerTrierDefault(UrgenceTrieListe.CONTACT, false, new UrgenceTrieListe());
    }
}
