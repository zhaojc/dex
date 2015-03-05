/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardex.business.delegate.SuiviBusinessDelegate;
import com.lotoquebec.cardex.business.vo.CriteresRechercheSuiviVO;
import com.lotoquebec.cardex.business.vo.SuiviVO;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheSuiviForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.SuiviForm;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.SuiviTrieListe;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Cette classe gère les événements en rapport
 * avec le cas d'utilisation gestion des dossiers.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/04/30 12:18:08 $
 */
public class SuiviAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    /**
     * <p>
     * <p>
     * Par défaut, l'application remplit automatiquement les champs suivants :
     *
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entrée/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward create(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.fine("Création d'un nouveau suivi");
        ListeCache listeCache = ListeCache.getInstance();
        ActionErrors errors = new ActionErrors();
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);

        SuiviForm suiviForm = new SuiviForm();
        if (form instanceof DossierForm) {
          log.fine("Création d'une suivi liée au dossier: " + form);
          DossierForm dossierForm = (DossierForm)form;
          suiviForm.setLien(dossierForm.getCle());
          suiviForm.setLienSite(dossierForm.getSite());
          suiviForm.setEntite(String.valueOf(user.getEntite()));
        }

        //Valeur par défaut
        suiviForm.setConfidentialiteSuivi(Long.toString(privilege.getNiveauConfidentialite()));
        suiviForm.setNiveauHierarchiqueSuivi(Long.toString(privilege.getNiveauAuthorite()));

        suiviForm.setSecteurAssigne(user.getSecteur()+"");
        suiviForm.setSecteurOrigine(user.getSecteur()+"");
        suiviForm.setDemandeur(user.getCode());
        suiviForm.setDateCreation(currentDate);
        suiviForm.setModifiable(true);
        suiviForm.setApprouvable(false);
        suiviForm.setPermettreComplete(false);
        suiviForm.setNiveauHierarchiqueCreateur(Long.toString(privilege.getNiveauAuthorite()));
        suiviForm.setConfidentialiteCreateur(Long.toString(privilege.getNiveauConfidentialite()));

        log.fine("Suivi : " + suiviForm);
        request.getSession().setAttribute("suivi",suiviForm);

        return mapping.findForward("success");
    }


    /**
     * <p>
     *
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entrée/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    public ActionForward refresh(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.fine("Refresh d'un suivi");

        return mapping.findForward("success");
    }

    /**
     * <p>
     *
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entrée/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward show(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Accès à une suivi");

        ActionMessages errors = new ActionMessages();

        try {
            SuiviBusinessDelegate delegate = new SuiviBusinessDelegate();
            SuiviForm suiviForm = (SuiviForm)form;
            Suivi suivi = new SuiviVO();
            ValueObjectMapper.convertSuiviHtmlForm(suiviForm, suivi,
                    subject.getLocale());
            suivi = delegate.find(subject,suivi);
            ValueObjectMapper.convertSuivi(suivi, suiviForm,
                    subject.getLocale());

            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
            return mapping.findForward("error");
        }
    }


    /**
     * Par défaut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
     * <ul>Genre (selon la sélection de l'écran principal)
     * <ul>Nature (selon la sélection de l'écran principal)
     * </li>
     *
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entrée/sortie survient
     * @exception ServletException si une exception servlet survient
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws SecurityException
     */
    public ActionForward refreshRechercheSuivi(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.fine("Refresh de recherche suivi");

        return mapping.findForward("success");
    }


    /**
     * Par défaut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
     * <ul>Genre (selon la sélection de l'écran principal)
     * <ul>Nature (selon la sélection de l'écran principal)
     * </li>
     *
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entrée/sortie survient
     * @exception ServletException si une exception servlet survient
     * @throws BusinessResourceException 
     */
    public ActionForward searchDefault(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException, BusinessResourceException {
        log.fine("Recherche par défault de suivi");

		//On inscrit les secteurs par défaut.
        CriteresRechercheSuiviForm suiviForm = new CriteresRechercheSuiviForm();
    	suiviForm.init(subject);
        //suiviForm.setSecteurOrigine(user.getGroupe());
        request.getSession().setAttribute("rechercheSuivi",suiviForm);
        

        return mapping.findForward("success");
    }


    /**
     * Par défaut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
     * <ul>Genre (selon la sélection de l'écran principal)
     * <ul>Nature (selon la sélection de l'écran principal)
     * </li>
     *
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entrée/sortie survient
     * @exception ServletException si une exception servlet survient
     * @throws BusinessResourceException 
     */
    public ActionForward resetSearchDefault(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException, BusinessResourceException {
        log.fine("Paramètres de recherche par défault de suivi");

        ActionMessages errors = new ActionMessages();

        try {
            CriteresRechercheSuiviForm criteresRechercheSuiviHtmlForm = (CriteresRechercheSuiviForm) form;
            CriteresRechercheSuiviVO criteresRechercheSuivi = new CriteresRechercheSuiviVO();

            criteresRechercheSuiviHtmlForm.init(subject);

            // Conversion du composant d'état(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSuiviHtmlForm(criteresRechercheSuiviHtmlForm,criteresRechercheSuivi,subject.getLocale());

            // Stockage des données de référence concernant le contenu des liste déroulante
            return mapping.findForward("success");
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet événement surivient lorsque dans l'écran de recherche de suivi, l'utilisateur a choisi
     * de rechercher un suivi selon des critères différents. L'application affiche alors le panneau de
     * recherche des suivis avec les résultats de la nouvelle recherche.
     * <p>
     *
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entrée/sortie survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward search(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.fine("Recherche de suivi");

        ActionMessages errors = new ActionMessages();

        try {
            SuiviBusinessDelegate delegate =  new SuiviBusinessDelegate();
            CriteresRechercheSuiviForm criteresRechercheSuiviHtmlForm = (CriteresRechercheSuiviForm) form;
            CriteresRechercheSuiviVO criteresRechercheSuivi = new CriteresRechercheSuiviVO();
            criteresRechercheSuiviHtmlForm.getListeResultat().vider();

            // Conversion du composant d'état(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSuiviHtmlForm(criteresRechercheSuiviHtmlForm, criteresRechercheSuivi,subject.getLocale());

            // Exécution de la recherche via le service d'affaire(BusinessDelegate)
            List<Suivi> list = delegate.select(subject,criteresRechercheSuivi);
            log.fine(criteresRechercheSuivi.toString());
            log.fine(list.size() + " photos trouvées ...");

            // Ajout des suivis dans le composant d'état (ActionForm)
            List currentList = new ArrayList();
            Iterator   it = list.iterator();

            while (it.hasNext()) {
                Suivi suivi = (Suivi)it.next();
                SuiviForm suiviForm = new SuiviForm();
                DossierForm dossierForm = new DossierForm();
                ValueObjectMapper.convertSuivi(suivi, suiviForm,subject.getLocale());
                ValueObjectMapper.convertDossier(suivi.getDossier(),dossierForm,subject.getLocale());
                suiviForm.setDossier(dossierForm);
                suiviForm.assignerValeurDeListe( subject );
                currentList.add(suiviForm);
            }

            criteresRechercheSuiviHtmlForm.setListeResultat( currentList );
            criteresRechercheSuiviHtmlForm.getListeResultat().assignerTrierDefault(SuiviTrieListe.CLE_DATE_PREVUE, true, new SuiviTrieListe());

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
   
}

