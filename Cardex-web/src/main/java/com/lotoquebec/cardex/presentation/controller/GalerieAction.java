package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.PhotoBusinessDelegate;
import com.lotoquebec.cardex.business.vo.CriteresRecherchePhotoVO;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.presentation.model.form.CriteresRecherchePhotoForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.PhotoForm;
import com.lotoquebec.cardex.presentation.model.form.SujetForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.presentation.util.ListeResultatAction;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class GalerieAction extends AbstractAction  {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));


    /**
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
     * <ul>Genre (selon la s�lection de l'�cran principal)
     * <ul>Nature (selon la s�lection de l'�cran principal)
     * </li> 
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
    public ActionForward refreshRechercheGalerie(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.fine("Refresh de recherche photo");

        return mapping.findForward("success");
    }


    /**
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
     * <ul>Genre (selon la s�lection de l'�cran principal)
     * <ul>Nature (selon la s�lection de l'�cran principal)
     * </li>
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
        log.fine("Recherche par d�fault de photo");

        ActionMessages errors = new ActionMessages();
        CriteresRecherchePhotoForm criteresRecherchePhotoForm = (CriteresRecherchePhotoForm) form;
        
        try {
            String genre = request.getParameter("genre");
            String nature = request.getParameter("nature");
        	
            DossierBusinessDelegate dossierDelegate =  new DossierBusinessDelegate();
            DossierVO dossierVO = new DossierVO();
            CriteresRecherchePhotoForm criteresRecherchePhotoHtmlForm = (CriteresRecherchePhotoForm) form;
        	dossierVO.setNature(Long.parseLong(nature));
        	criteresRecherchePhotoForm.init();
            CardexUser user = (CardexUser) subject.getUser();
            
            criteresRecherchePhotoHtmlForm.setEntite(Long.toString(user.getEntite()));
            criteresRecherchePhotoHtmlForm.setGenre(genre);
            criteresRecherchePhotoHtmlForm.setNature(nature);
                        
            if (dossierDelegate.isAvecInscription(subject,dossierVO)){
            	criteresRecherchePhotoHtmlForm.setSiteApplicable(Long.toString(user.getSite()));
            }else {
            	criteresRecherchePhotoHtmlForm.setSiteOrigine(Long.toString(user.getSite()));
            }

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } 
    }


    /**
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
     * <ul>Genre (selon la s�lection de l'�cran principal)
     * <ul>Nature (selon la s�lection de l'�cran principal)
     * </li>
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
        log.fine("Param�tres de recherche par d�fault de photo");

        ActionMessages errors = new ActionMessages();

        try {
            DossierBusinessDelegate dossierDelegate =  new DossierBusinessDelegate();
            DossierVO dossierVO = new DossierVO();
            String nature = "";
            CriteresRecherchePhotoForm criteresRecherchePhotoHtmlForm = (CriteresRecherchePhotoForm) form;
            if(StringUtils.isNotEmpty(criteresRecherchePhotoHtmlForm.getNature())){
            	dossierVO.setNature(Long.parseLong(criteresRecherchePhotoHtmlForm.getNature()));
            	nature = criteresRecherchePhotoHtmlForm.getNature();
            }
            CardexUser user = (CardexUser) subject.getUser();
            
            String genre = criteresRecherchePhotoHtmlForm.getGenre();
            criteresRecherchePhotoHtmlForm.init();
            criteresRecherchePhotoHtmlForm.setEntite(Long.toString(user.getEntite()));
            criteresRecherchePhotoHtmlForm.setGenre(genre);
            criteresRecherchePhotoHtmlForm.setNature(nature);
            if (dossierDelegate.isAvecInscription(subject,dossierVO)){
                criteresRecherchePhotoHtmlForm.setSiteApplicable(Long.toString(user.getSite()));
            }else {
                criteresRecherchePhotoHtmlForm.setSiteOrigine(Long.toString(user.getSite()));
            }

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);

            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);

            return (new ActionForward(mapping.getInput()));
        } 
    }

    /**
     * <p>
     * Cet �v�nement surivient lorsque dans l'�cran de recherche de photo, l'utilisateur a choisi
     * de rechercher un photo selon des crit�res diff�rents. L'application affiche alors le panneau de
     * recherche des photos avec les r�sultats de la nouvelle recherche.
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
        log.fine("Recherche de photo");

        ActionMessages errors = new ActionMessages();

        try {
            PhotoBusinessDelegate delegate =  new PhotoBusinessDelegate();
            CriteresRecherchePhotoForm criteresRecherchePhotoHtmlForm = (CriteresRecherchePhotoForm) form;
            CriteresRecherchePhotoVO criteresRecherchePhoto = new CriteresRecherchePhotoVO();

            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRecherchePhotoHtmlForm(criteresRecherchePhotoHtmlForm, criteresRecherchePhoto,subject.getLocale());

            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Photo> photoList = delegate.select(subject,criteresRecherchePhoto);
            log.fine(photoList.size() + " photos trouv�es ...");

            criteresRecherchePhotoHtmlForm.assignerDateExecutionGalerie();
            
            populateSearchResults(subject,criteresRecherchePhotoHtmlForm,photoList);

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);
            return mapping.findForward("error");
        }
    }
    public ActionForward changerPage(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {
    	CriteresRecherchePhotoForm criteresRecherchePhotoHtmlForm = (CriteresRecherchePhotoForm) form;
    	
    	if (criteresRecherchePhotoHtmlForm.isRexecutionGalerie())
    		return search(subject, mapping, form, request, response);
    	else
    		return (new ListeResultatAction()).changerPage(subject, mapping, criteresRecherchePhotoHtmlForm, request, response);
    		
    }

    
    
    /**
     * <p>
     * Popule les informations d'un dossier obtenu dans la base de donn�e
     * dans le DossierForm sp�cifi�s.
     *
     * @param Dossier Les crit�res du dossier a obtenir
     * @param DossierForm L'ActionForm bean a popul� � partir du dossier obtenu
     * @throws ValueObjectMapperException 
     *
     * @exception BusinessResourceException si une erreur syst�me survient
     * @exception BusinessException si une r�gle d'affaire n'est pas respect�e
     */
    private void populateSearchResults(CardexAuthenticationSubject subject, CriteresRecherchePhotoForm criteresRecherchePhotoHtmlForm, List<Photo> photoList) throws ValueObjectMapperException {
        log.fine("populateSearchResults()");
        criteresRecherchePhotoHtmlForm.getListeResultat().vider();
        criteresRecherchePhotoHtmlForm.getListeResultat().setPlageResultats("6");
        
        for (Photo photo:photoList){
            PhotoForm photoForm = new PhotoForm();
            ValueObjectMapper.convertPhoto(photo, photoForm,subject.getLocale());
            
            if (photo.isAttachDossier()) {
              Dossier dossier = photo.getDossier();
              DossierForm dossierForm = new DossierForm();
              ValueObjectMapper.convertDossier(dossier,dossierForm,subject.getLocale());
              photoForm.setDossier(dossierForm);
              
            }else if (photo.isAttachSujet()){
			  Dossier dossier = photo.getDossier();
			  DossierForm dossierForm = new DossierForm();
			  ValueObjectMapper.convertDossier(dossier,dossierForm,subject.getLocale());
			  dossierForm.setDateCreation(StringUtils.left(dossierForm.getDateCreation(), 10));
			  photoForm.setDossier(dossierForm);
              Sujet sujet = photo.getSujet();
              SujetForm sujetForm = new SujetForm();
              ValueObjectMapper.convertSujet(sujet,sujetForm,subject.getLocale());
              photoForm.setSujet(sujetForm);
            }
            criteresRecherchePhotoHtmlForm.getListeResultat().add(photoForm);
        }
        criteresRecherchePhotoHtmlForm.getListeResultat().chargerAffichage();
    }

}