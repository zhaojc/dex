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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.lotoquebec.cardex.business.Consignation;
import com.lotoquebec.cardex.business.delegate.ConsignationBusinessDelegate;
import com.lotoquebec.cardex.business.vo.ConsignationVO;
import com.lotoquebec.cardex.business.vo.CriteresRechercheConsignationVO;
import com.lotoquebec.cardex.presentation.model.form.ConsignationForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheConsignationForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.ConsignationTrieListe;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.user.CardexUser;

/**
 * Cette classe gère les événements en rapport
 * avec le cas d'utilisation gestion des dossiers.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/04/30 12:18:08 $
 */
public class ConsignationAction extends AbstractAction {

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
     * @throws BusinessResourceException 
     */
    public ActionForward create(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException, BusinessResourceException {


        log.fine("Création d'une nouvelle consignation");
        CardexUser user = (CardexUser)subject.getUser();
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);

        ConsignationForm consignationForm = new ConsignationForm();
        if (form instanceof DossierForm) {
          log.fine("Création d'une consignation liée au dossier: " + form);
          DossierForm dossierForm = (DossierForm)form;
		  consignationForm.setLien(dossierForm.getCle());
		  consignationForm.setLienSite(dossierForm.getSite());;
        }

        //Valeur par défaut
		consignationForm.setCreateur(user.getCode());
		consignationForm.setDateCreation(currentDate);
		consignationForm.setModifiable(true);
		consignationForm.setApprouvable(false);
		consignationForm.setDevise(Long.toString(GlobalConstants.Consignations.CDN));

        log.fine("Consignation : " + consignationForm);
        request.getSession().setAttribute("consignation",consignationForm);
        // Stockage des données de référence concernant le contenu des liste déroulante

		request.getSession().setAttribute("consignation",consignationForm);
		
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
    public ActionForward refresh(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Refresh d'une consignation");

        return mapping.findForward("success");

    }

    /**
     * Affichage d'une consignation à partir de l'onglet.
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
        log.fine("Accès à une consignation");

        ActionMessages errors = new ActionMessages();

        try {
            ConsignationBusinessDelegate delegate = new ConsignationBusinessDelegate();
            ConsignationForm consignationForm = (ConsignationForm)form;
            Consignation consignation = new ConsignationVO();
            ValueObjectMapper.convertConsignationHtmlForm(consignationForm, consignation,
                    subject.getLocale());
            consignation = delegate.find(subject,consignation);
            ValueObjectMapper.convertConsignation(consignation, consignationForm,
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
    public ActionForward refreshRechercheConsignation(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.fine("Refresh de recherche Consignation");

        return mapping.findForward("success");
    }


    /**
     * Par défaut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
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
     */
    public ActionForward searchDefault(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.fine("Recherche par défault de consignation");


		//On inscrit les valeurs par défaut.
        CriteresRechercheConsignationForm consignationForm = new CriteresRechercheConsignationForm();
    	CardexUser user = (CardexUser)subject.getUser();
        consignationForm.setEntite(Long.toString(user.getEntite()));
        consignationForm.setSiteOrigine(Long.toString(user.getSite()));
        request.getSession().setAttribute("rechercheConsignation",consignationForm);
        
        consignationForm.init();

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
     */
    public ActionForward resetSearchDefault(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException {
        log.fine("Réinitialisation de la recherche de consignations");

        ActionMessages errors = new ActionMessages();

        try {
            CriteresRechercheConsignationForm criteresRechercheConsignationHtmlForm = (CriteresRechercheConsignationForm) form;
            CriteresRechercheConsignationVO criteresRechercheConsignation = new CriteresRechercheConsignationVO();

            criteresRechercheConsignationHtmlForm.init();

            // Conversion du composant d'état(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheConsignationHtmlForm(criteresRechercheConsignationHtmlForm,criteresRechercheConsignation,subject.getLocale());

            return mapping.findForward("success");
        } catch (ValueObjectMapperException vome) {
            handleValueObjectMapperException(vome, errors, request);

            return mapping.findForward("error");
        }
    }

    /**
     * <p>
     * Cet événement surivient lorsque dans l'écran de recherche de consignation, l'utilisateur a choisi
     * de rechercher les consignations selon des critères différents. L'application affiche alors le panneau de
     * recherche des consignations avec les résultats de la nouvelle recherche.
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
        log.fine("Recherche de consignations");

        ActionMessages errors = new ActionMessages();

        try {
            ConsignationBusinessDelegate delegate =  new ConsignationBusinessDelegate();
            CriteresRechercheConsignationForm criteresRechercheConsignationHtmlForm = (CriteresRechercheConsignationForm) form;
            CriteresRechercheConsignationVO criteresRechercheConsignation = new CriteresRechercheConsignationVO();
            criteresRechercheConsignationHtmlForm.getListeResultat().vider();

            // Conversion du composant d'état(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRechercheConsignationHtmlForm(criteresRechercheConsignationHtmlForm, criteresRechercheConsignation,subject.getLocale());

            // Exécution de la recherche via le service d'affaire(BusinessDelegate)
            List<Consignation> list = delegate.select(subject,criteresRechercheConsignation);
            log.fine(criteresRechercheConsignation.toString());
            log.fine(list.size() + " Consignations trouvées ...");

            // Ajout des suivis dans le composant d'état (ActionForm)
            List currentList = new ArrayList();
            Iterator   it = list.iterator();

            while (it.hasNext()) {
                Consignation consignation = (Consignation)it.next();
                ConsignationForm consignationForm = new ConsignationForm();
				DossierForm dossierForm = new DossierForm();
				ValueObjectMapper.convertDossier(consignation.getDossier(),dossierForm,subject.getLocale());
				ValueObjectMapper.convertConsignation(consignation, consignationForm,subject.getLocale());
				consignationForm.setDossier(dossierForm);
				consignationForm.assignerValeurDeListe( subject );
                currentList.add(consignationForm);
            }

            criteresRechercheConsignationHtmlForm.setListeResultat( currentList );
            criteresRechercheConsignationHtmlForm.getListeResultat().assignerTrierDefault(ConsignationTrieListe.CLE_DATE_CREATION, true, new ConsignationTrieListe());

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

