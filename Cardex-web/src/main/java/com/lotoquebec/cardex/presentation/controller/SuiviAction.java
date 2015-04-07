/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Cette classe g�re les �v�nements en rapport
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
        LoggerFactory.getLogger((this.getClass()));

    /**
     * <p>
     * <p>
     * Par d�faut, l'application remplit automatiquement les champs suivants :
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
    public ActionForward create(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.debug("Cr�ation d'un nouveau suivi");
        ListeCache listeCache = ListeCache.getInstance();
        ActionErrors errors = new ActionErrors();
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege)subject.getPrivilege();
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);

        SuiviForm suiviForm = new SuiviForm();
        if (form instanceof DossierForm) {
          log.debug("Cr�ation d'une suivi li�e au dossier: " + form);
          DossierForm dossierForm = (DossierForm)form;
          suiviForm.setLien(dossierForm.getCle());
          suiviForm.setLienSite(dossierForm.getSite());
          suiviForm.setEntite(String.valueOf(user.getEntite()));
        }

        //Valeur par d�faut
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

        log.debug("Suivi : " + suiviForm);
        request.getSession().setAttribute("suivi",suiviForm);

        return mapping.findForward("success");
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
        log.debug("Refresh d'un suivi");

        return mapping.findForward("success");
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
    public ActionForward show(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Acc�s � une suivi");

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
        log.debug("Refresh de recherche suivi");

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
     * @throws BusinessResourceException 
     */
    public ActionForward searchDefault(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException, BusinessResourceException {
        log.debug("Recherche par d�fault de suivi");

		//On inscrit les secteurs par d�faut.
        CriteresRechercheSuiviForm suiviForm = new CriteresRechercheSuiviForm();
    	suiviForm.init(subject);
        //suiviForm.setSecteurOrigine(user.getGroupe());
        request.getSession().setAttribute("rechercheSuivi",suiviForm);
        

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
     * @throws BusinessResourceException 
     */
    public ActionForward resetSearchDefault(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException, BusinessResourceException {
        log.debug("Param�tres de recherche par d�fault de suivi");

        ActionMessages errors = new ActionMessages();

        try {
            CriteresRechercheSuiviForm criteresRechercheSuiviHtmlForm = (CriteresRechercheSuiviForm) form;
            CriteresRechercheSuiviVO criteresRechercheSuivi = new CriteresRechercheSuiviVO();

            criteresRechercheSuiviHtmlForm.init(subject);

            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSuiviHtmlForm(criteresRechercheSuiviHtmlForm,criteresRechercheSuivi,subject.getLocale());

            // Stockage des donn�es de r�f�rence concernant le contenu des liste d�roulante
            return mapping.findForward("success");
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet �v�nement surivient lorsque dans l'�cran de recherche de suivi, l'utilisateur a choisi
     * de rechercher un suivi selon des crit�res diff�rents. L'application affiche alors le panneau de
     * recherche des suivis avec les r�sultats de la nouvelle recherche.
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
        log.debug("Recherche de suivi");

        ActionMessages errors = new ActionMessages();

        try {
            SuiviBusinessDelegate delegate =  new SuiviBusinessDelegate();
            CriteresRechercheSuiviForm criteresRechercheSuiviHtmlForm = (CriteresRechercheSuiviForm) form;
            CriteresRechercheSuiviVO criteresRechercheSuivi = new CriteresRechercheSuiviVO();
            criteresRechercheSuiviHtmlForm.getListeResultat().vider();

            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheSuiviHtmlForm(criteresRechercheSuiviHtmlForm, criteresRechercheSuivi,subject.getLocale());

            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            List<Suivi> list = delegate.select(subject,criteresRechercheSuivi);
            log.debug(criteresRechercheSuivi.toString());
            log.debug(list.size() + " photos trouv�es ...");

            // Ajout des suivis dans le composant d'�tat (ActionForm)
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

