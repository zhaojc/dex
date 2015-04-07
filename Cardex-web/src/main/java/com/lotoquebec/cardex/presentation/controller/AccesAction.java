/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Acces;
import com.lotoquebec.cardex.business.delegate.AccesBusinessDelegate;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.VehiculeVO;
import com.lotoquebec.cardex.presentation.model.form.AccesForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.SocieteForm;
import com.lotoquebec.cardex.presentation.model.form.SujetForm;
import com.lotoquebec.cardex.presentation.model.form.VehiculeForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.IteratorException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;

/**
 * Cette classe g�re les �v�nements en rapport
 * avec le cas d'utilisation gestion des dossiers.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/03/20 22:06:38 $
 */
public class AccesAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));
	//Nombre de lignes affich�es dans les listes de r�sultat.
	//Par d�faut, pour les acc�s, l'utilisateur ne change pas cette valeur.
	private final int nombreDeResultats = 200;  


    /**
     * <p>
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward selectAccesDossier(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Affichage des acc�s");

        ActionMessages errors = new ActionMessages();

        try {
            AccesBusinessDelegate delegate = new AccesBusinessDelegate();
            DossierForm dossierHtmlForm = (DossierForm) form;
            DossierVO dossier = new DossierVO();
            ValueListIterator results;
            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertDossierHtmlForm(dossierHtmlForm, dossier,subject.getLocale());
            log.debug(dossier.toString());
            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            results = delegate.selectAccesDossier(subject,dossier);
            // Ajout des acc�s dans le composant d'�tat (ActionForm)
            //int nombreDeResultats = (int)criteresRechercheDossier.getMaximumResultatsRecherche();
            Collection list = results.getNextElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();
            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }
			request.getSession().setAttribute("listeAcces", GlobalConstants.RechercheList.ACCES);
            long nbOfPages = (long)Math.ceil(((double)results.getSize())/((double)nombreDeResultats));
            SearchUtils.storeSearchSessionObject(request,results,currentList,nbOfPages,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_MAX_NUMBER_OF_PAGES,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);

          return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * first pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchFirstDossier(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(first) ");

        ActionMessages errors = new ActionMessages();

        try {
            DossierForm dossierHtmlForm = (DossierForm) form;
            DossierVO dossier = new DossierVO();
            ValueObjectMapper.convertDossierHtmlForm(dossierHtmlForm, dossier,subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);

            // Ajout des dossiers dans le composant d'�tat (ActionForm)
            Collection list = completeList.getFirstElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();

            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchFirstSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }
    
    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * next pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchNextDossier(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(next) ");

        ActionMessages errors = new ActionMessages();

        try {
            DossierForm dossierHtmlForm = (DossierForm) form;
            DossierVO dossier = new DossierVO();
            ValueObjectMapper.convertDossierHtmlForm(dossierHtmlForm, dossier,subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);
            completeList.getNextElements(nombreDeResultats);

            // Ajout des dossiers dans le composant d'�tat (ActionForm)
            Collection list = completeList.getNextElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();
            log.debug("Dossier : " + dossier);
     
            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchNextSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);
//            DonneeReferenceCache.storeDossierDonneeReference(subject, mapping,form, request,response);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }
    
    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * previous pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchPreviousDossier(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(previous) ");

        ActionMessages errors = new ActionMessages();

        try {
            DossierForm dossierHtmlForm = (DossierForm) form;
            DossierVO dossier = new DossierVO();
            ValueObjectMapper.convertDossierHtmlForm(dossierHtmlForm, dossier,subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);

            // Ajout des dossiers dans le composant d'�tat (ActionForm)
            Collection list = completeList.getPreviousElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();

            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchPreviousSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }   
    
    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * last pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchLastDossier(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(last) ");

        ActionMessages errors = new ActionMessages();

        try {
            DossierForm dossierHtmlForm = (DossierForm) form;
            DossierVO dossier = new DossierVO();
            ValueObjectMapper.convertDossierHtmlForm(dossierHtmlForm, dossier,subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);

            // Ajout des dossiers dans le composant d'�tat (ActionForm)
            Collection list = completeList.getLastElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();

            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchLastSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_MAX_NUMBER_OF_PAGES,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }             

    /**
     * <p>
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward selectAccesSujet(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Affichage des acc�s");

        ActionMessages errors = new ActionMessages();

        try {
            AccesBusinessDelegate delegate = new AccesBusinessDelegate();
            SujetForm sujetHtmlForm = (SujetForm) form;
            SujetVO sujet = new SujetVO();
            ValueListIterator results;
            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertSujetHtmlForm(sujetHtmlForm, sujet,subject.getLocale());
            log.debug(sujet.toString());
            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            results = delegate.selectAccesSujet(subject,sujet);
            // Ajout des acc�s dans le composant d'�tat (ActionForm)
            //int nombreDeResultats = (int)criteresRechercheDossier.getMaximumResultatsRecherche();
            Collection list = results.getNextElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();
            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }
			request.getSession().setAttribute("listeAcces", GlobalConstants.RechercheList.ACCES);
            long nbOfPages = (long)Math.ceil(((double)results.getSize())/((double)nombreDeResultats));
            SearchUtils.storeSearchSessionObject(request,results,currentList,nbOfPages,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_MAX_NUMBER_OF_PAGES,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);

          return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * first pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchFirstSujet(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(first) ");

        ActionMessages errors = new ActionMessages();

        try {
            SujetForm sujetHtmlForm = (SujetForm) form;
            SujetVO sujet = new SujetVO();
            ValueObjectMapper.convertSujetHtmlForm(sujetHtmlForm, sujet,subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);

            // Ajout des dossiers dans le composant d'�tat (ActionForm)
            Collection list = completeList.getFirstElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();

            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchFirstSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }
    
    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * next pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchNextSujet(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(next) ");

        ActionMessages errors = new ActionMessages();

        try {
            SujetForm sujetHtmlForm = (SujetForm) form;
            SujetVO sujet = new SujetVO();
            ValueObjectMapper.convertSujetHtmlForm(sujetHtmlForm, sujet,subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);
            completeList.getNextElements(nombreDeResultats);

            // Ajout des sujets dans le composant d'�tat (ActionForm)
            Collection list = completeList.getNextElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();
            log.debug("sujet : " + sujet);
     
            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchNextSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);
//            DonneeReferenceCache.storeDossierDonneeReference(subject, mapping,form, request,response);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }
    
    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * previous pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchPreviousSujet(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(previous) ");

        ActionMessages errors = new ActionMessages();

        try {
            SujetForm sujetHtmlForm = (SujetForm) form;
            SujetVO sujet = new SujetVO();
            ValueObjectMapper.convertSujetHtmlForm(sujetHtmlForm, sujet,subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);

            // Ajout des dossiers dans le composant d'�tat (ActionForm)
            Collection list = completeList.getPreviousElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();

            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchPreviousSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }   
    
    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * last pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchLastSujet(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(last) ");

        ActionMessages errors = new ActionMessages();

        try {
            SujetForm sujetHtmlForm = (SujetForm) form;
            SujetVO sujet = new SujetVO();
            ValueObjectMapper.convertSujetHtmlForm(sujetHtmlForm, sujet,subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);

            // Ajout des dossiers dans le composant d'�tat (ActionForm)
            Collection list = completeList.getLastElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();

            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchLastSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_MAX_NUMBER_OF_PAGES,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }             
    
    /**
     * <p>
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward selectAccesSociete(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Affichage des acc�s des soci�t�s");

        ActionMessages errors = new ActionMessages();

        try {
            AccesBusinessDelegate delegate = new AccesBusinessDelegate();
            SocieteForm societeHtmlForm = (SocieteForm) form;
            SocieteVO societe = new SocieteVO();
            ValueListIterator results;
            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertSocieteHtmlForm(societeHtmlForm, societe,subject.getLocale());
            log.debug(societe.toString());
            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            results = delegate.selectAccesSociete(subject,societe);
            // Ajout des acc�s dans le composant d'�tat (ActionForm)
            //int nombreDeResultats = (int)criteresRechercheDossier.getMaximumResultatsRecherche();
            Collection list = results.getNextElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();
            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }
			request.getSession().setAttribute("listeAcces", GlobalConstants.RechercheList.ACCES);
            long nbOfPages = (long)Math.ceil(((double)results.getSize())/((double)nombreDeResultats));
            SearchUtils.storeSearchSessionObject(request,results,currentList,nbOfPages,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_MAX_NUMBER_OF_PAGES,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);
            
            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * first pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchFirstSociete(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(first) ");

        ActionMessages errors = new ActionMessages();

        try {
            SocieteForm societeHtmlForm = (SocieteForm) form;
            SocieteVO societe = new SocieteVO();
            ValueObjectMapper.convertSocieteHtmlForm(societeHtmlForm, societe,subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);

            // Ajout des dossiers dans le composant d'�tat (ActionForm)
            Collection list = completeList.getFirstElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();

            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchFirstSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }
    
    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * next pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchNextSociete(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(next) ");

        ActionMessages errors = new ActionMessages();

        try {
            SocieteForm societeHtmlForm = (SocieteForm) form;
            SocieteVO societe = new SocieteVO();
            ValueObjectMapper.convertSocieteHtmlForm(societeHtmlForm, societe,subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);
            completeList.getNextElements(nombreDeResultats);

            // Ajout des dossiers dans le composant d'�tat (ActionForm)
            Collection list = completeList.getNextElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();
            log.debug("societe : " + societe);
     
            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchNextSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);
//            DonneeReferenceCache.storeDossierDonneeReference(subject, mapping,form, request,response);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }
    
    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * previous pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchPreviousSociete(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(previous) ");

        ActionMessages errors = new ActionMessages();

        try {
            SocieteForm societeHtmlForm = (SocieteForm) form;
            SocieteVO societe = new SocieteVO();
            ValueObjectMapper.convertSocieteHtmlForm(societeHtmlForm, societe,subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);

            // Ajout des dossiers dans le composant d'�tat (ActionForm)
            Collection list = completeList.getPreviousElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();

            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchPreviousSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }   
    
    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * last pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchLastSociete(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(last) ");

        ActionMessages errors = new ActionMessages();

        try {
            SocieteForm societeHtmlForm = (SocieteForm) form;
            SocieteVO societe = new SocieteVO();
            ValueObjectMapper.convertSocieteHtmlForm(societeHtmlForm, societe,subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);

            // Ajout des dossiers dans le composant d'�tat (ActionForm)
            Collection list = completeList.getLastElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();

            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchLastSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_MAX_NUMBER_OF_PAGES,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }             
    /**
     * <p>
     *
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward selectAccesVehicule(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Affichage des acc�s");

        ActionMessages errors = new ActionMessages();

        try {
            AccesBusinessDelegate delegate = new AccesBusinessDelegate();
            VehiculeForm vehiculeHtmlForm = (VehiculeForm) form;
            VehiculeVO vehicule = new VehiculeVO();
            ValueListIterator results;
            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeHtmlForm, vehicule,subject.getLocale());
            log.debug(vehicule.toString());
            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            results = delegate.selectAccesVehicule(subject,vehicule);
            // Ajout des acc�s dans le composant d'�tat (ActionForm)
            //int nombreDeResultats = (int)criteresRechercheDossier.getMaximumResultatsRecherche();
            Collection list = results.getNextElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();
            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }
			request.getSession().setAttribute("listeAcces", GlobalConstants.RechercheList.ACCES);
            long nbOfPages = (long)Math.ceil(((double)results.getSize())/((double)nombreDeResultats));
            SearchUtils.storeSearchSessionObject(request,results,currentList,nbOfPages,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_MAX_NUMBER_OF_PAGES,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);

          return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * first pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchFirstVehicule(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(first) ");

        ActionMessages errors = new ActionMessages();

        try {
            VehiculeForm vehiculeHtmlForm = (VehiculeForm) form;
            VehiculeVO vehicule = new VehiculeVO();
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeHtmlForm, vehicule, subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);

            // Ajout des dossiers dans le composant d'�tat (ActionForm)
            Collection list = completeList.getFirstElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();

            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchFirstSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }
    
    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * next pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchNextVehicule(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(next) ");

        ActionMessages errors = new ActionMessages();

        try {
            VehiculeForm vehiculeHtmlForm = (VehiculeForm) form;
            VehiculeVO vehicule = new VehiculeVO();
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeHtmlForm, vehicule,subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);
            completeList.getNextElements(nombreDeResultats);

            // Ajout des dossiers dans le composant d'�tat (ActionForm)
            Collection list = completeList.getNextElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();
            log.debug("vehicule : " + vehicule);
     
            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchNextSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);
//            DonneeReferenceCache.storeDossierDonneeReference(subject, mapping,form, request,response);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }
    
    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * previous pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchPreviousVehicule(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(previous) ");

        ActionMessages errors = new ActionMessages();

        try {
            VehiculeForm vehiculeHtmlForm = (VehiculeForm) form;
            VehiculeVO vehicule = new VehiculeVO();
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeHtmlForm, vehicule,subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);

            // Ajout des dossiers dans le composant d'�tat (ActionForm)
            Collection list = completeList.getPreviousElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();

            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchPreviousSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }   
    
    /**
     * <p>
     * Cet �v�nement surivient lorsque l'utilisateur clique sur l'�l�ment de navigation
     * last pour les listes d'acc�s.
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward searchLastVehicule(CardexAuthenticationSubject subject,
                                     ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws IOException,
                                     ServletException {
        log.debug("Recherche des acc�s(last) ");

        ActionMessages errors = new ActionMessages();

        try {
            VehiculeForm vehiculeHtmlForm = (VehiculeForm) form;
            VehiculeVO vehicule = new VehiculeVO();
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeHtmlForm, vehicule,subject.getLocale());
 
            // R�cup�ration des r�sultats de la recherche original
            ValueListIterator completeList = (ValueListIterator) request.getSession().getAttribute(GlobalConstants.RechercheList.ACCES);

            // Ajout des dossiers dans le composant d'�tat (ActionForm)
            Collection list = completeList.getLastElements(nombreDeResultats);
            Collection currentList = new ArrayList();
            Iterator   it = list.iterator();

            while (it.hasNext()) {
                Acces acces = (Acces)it.next();
                AccesForm accesForm = new AccesForm();
                ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
                currentList.add(accesForm);
            }

            SearchUtils.storeSearchLastSessionObject(request,completeList,currentList,
                                     GlobalConstants.RechercheList.ACCES,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_LIST,
                                     GlobalConstants.RechercheList.ACCES_CURRENT_PAGE,
                                     GlobalConstants.RechercheList.ACCES_MAX_NUMBER_OF_PAGES,
                                     GlobalConstants.RechercheList.ACCES_HAS_NEXT,
                                     GlobalConstants.RechercheList.ACCES_HAS_PREVIOUS);

          return mapping.findForward("success");
//        } catch (BusinessResourceException bre) {
//            handleBusinessResourceException(bre, errors, request);
//            return mapping.findForward("error");
        } catch (IteratorException ie) {
            handleIteratorException(ie, errors, request);
            return mapping.findForward("error");
//        } catch (BusinessException be) {
//            handleBusinessException(be, errors, request);
//            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }             

}

