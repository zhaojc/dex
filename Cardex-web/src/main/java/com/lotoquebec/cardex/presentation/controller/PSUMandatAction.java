/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.lotoquebec.cardex.business.ConsignationActionPSU;
import com.lotoquebec.cardex.business.PSUMandat;
import com.lotoquebec.cardex.business.delegate.PSUMandatBusinessDelegate;
import com.lotoquebec.cardex.business.vo.ConsignationActionPSUVO;
import com.lotoquebec.cardex.business.vo.CriteresRecherchePSUMandatVO;
import com.lotoquebec.cardex.business.vo.PSUMandatVO;
import com.lotoquebec.cardex.presentation.model.PSUMandatHtmlForm;
import com.lotoquebec.cardex.presentation.model.form.ConsignationActionPSUForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRecherchePSUMandatForm;
import com.lotoquebec.cardex.presentation.model.form.PSUMandatForm;
import com.lotoquebec.cardex.presentation.model.util.trierListeColumns.PSUMandatTrieListe;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.IteratorException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.GenreFichierCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.InterventionCodeCourrielCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Cette classe gère les événements en rapport
 * avec le cas d'utilisation PSU (Programme de Suivi des utilisateurs).
 * Cette fonction sert à étiqueter des données dont on désire être informé
 * en temps réel d'actions spécifiques les concernant, par exemple, chaque fois
 * qu'une fiche sujet est consultée.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/04/30 12:18:08 $
 */
public class PSUMandatAction extends AbstractAction {

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


        log.fine("Création d'un nouveau mandat");
        ActionErrors errors = new ActionErrors();
        CardexUser user = (CardexUser)subject.getUser();
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);

        PSUMandatForm psuMandatForm = new PSUMandatForm();

        //Valeur par défaut
		psuMandatForm.setCreateur(user.getCode());
		psuMandatForm.setDateCreation(currentDate);
		psuMandatForm.setModifiable(true);
		psuMandatForm.setStatut(GlobalConstants.Statut.DOSSIER_INACTIF);
		psuMandatForm.setNumeroMandat(" "); //Pour indiquer qu'il s'agit d'un nouveau mandat.
        log.fine("Mandat : " + psuMandatForm);
        request.getSession().setAttribute("PSUMandat",psuMandatForm);
        
		//On vide le message de validation au cas où.
		request.getSession().setAttribute("message", "");
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
        log.fine("Refresh des mandats PSU");

		//On vide le message de validation au cas où.
		request.getSession().setAttribute("message", "");

        return mapping.findForward("success");
    }

    /**
     * Affichage d'un mandat à partir des résultats de recherche.
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
        log.fine("Accès à un mandat PSU");

        ActionMessages errors = new ActionMessages();

        try {
            PSUMandatBusinessDelegate delegate = new PSUMandatBusinessDelegate();
			PSUMandatForm psuMandatForm = (PSUMandatForm)form;
			PSUMandat psuMandat = new PSUMandatVO();
            ValueObjectMapper.convertPSUMandatHtmlForm(psuMandatForm, psuMandat,
                    subject.getLocale());
			psuMandat = delegate.find(subject,psuMandat);
            ValueObjectMapper.convertPSUMandat(psuMandat, psuMandatForm,
                    subject.getLocale());

			//On vide le message de validation au cas où.
			request.getSession().setAttribute("message", "");
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
	 * Mise à jour d'un mandat à partir de l'écran de consultation.
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
	public ActionForward update(CardexAuthenticationSubject subject,
							  ActionMapping mapping, ActionForm form,
							  HttpServletRequest request,
							  HttpServletResponse response) throws IOException,
							  ServletException {
		log.fine("Mise à jour d'un mandat PSU");

		ActionMessages errors = new ActionMessages();

		try {
			verifierToken(request);
			PSUMandatBusinessDelegate delegate = new PSUMandatBusinessDelegate();
			PSUMandatForm psuMandatForm = (PSUMandatForm)form;
			PSUMandat psuMandat = new PSUMandatVO();
			String reponse = "";
			reponse = validation(psuMandatForm);
			if(!reponse.equals("OK")){
				request.getSession().setAttribute("message", reponse);
				return mapping.findForward("erreurValidation");
			}
			ValueObjectMapper.convertPSUMandatHtmlForm(psuMandatForm, psuMandat,
					subject.getLocale());
			delegate.update(subject,psuMandat);
			//ValueObjectMapper.convertPSUMandat(psuMandat, psuMandatForm,subject.getLocale());

			// Stockage des données de référence concernant le contenu des liste déroulante
			//DonneeReferenceCache.storePSUMandatDonneeReference(subject, mapping,form, request,response);

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
	 * Sauvegarde d'un nouveau mandat.
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
	public ActionForward save(CardexAuthenticationSubject subject,
							  ActionMapping mapping, ActionForm form,
							  HttpServletRequest request,
							  HttpServletResponse response) throws IOException,
							  ServletException {
		log.fine("Sauvegarde d'un nouveau mandat PSU");

		ActionMessages errors = new ActionMessages();

		try {
			verifierToken(request);
			PSUMandatBusinessDelegate delegate = new PSUMandatBusinessDelegate();
			PSUMandatForm psuMandatForm = (PSUMandatForm)form;
			PSUMandat psuMandat = new PSUMandatVO();
			String reponse = "";
		 	reponse = validation(psuMandatForm);
		 	if(!reponse.equals("OK")){
				request.getSession().setAttribute("message", reponse);
				return mapping.findForward("erreurValidation");
		 	}
			ValueObjectMapper.convertPSUMandatHtmlForm(psuMandatForm, psuMandat,
					subject.getLocale());
			delegate.insert(subject,psuMandat);
			//ValueObjectMapper.convertPSUMandat(psuMandat, psuMandatForm,subject.getLocale());

			// Stockage des données de référence concernant le contenu des liste déroulante
			//DonneeReferenceCache.storePSUMandatDonneeReference(subject, mapping,form, request,response);

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
	 * Suppression ou désactivation d'un mandat.
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
	public ActionForward delete(CardexAuthenticationSubject subject,
							  ActionMapping mapping, ActionForm form,
							  HttpServletRequest request,
							  HttpServletResponse response) throws IOException,
							  ServletException {
		log.fine("Suppression d'un mandat PSU");

		ActionMessages errors = new ActionMessages();

		try {
			verifierToken(request);
			PSUMandatBusinessDelegate delegate = new PSUMandatBusinessDelegate();
			PSUMandatForm psuMandatForm = (PSUMandatForm)form;
			PSUMandat psuMandat = new PSUMandatVO();
			psuMandatForm.setStatut(GlobalConstants.Statut.DOSSIER_INACTIF);
			ValueObjectMapper.convertPSUMandatHtmlForm(psuMandatForm, psuMandat,
					subject.getLocale());
			delegate.delete(subject,psuMandat);
			//On relance la recherche par défaut.
			CriteresRecherchePSUMandatForm psuMandatForm2 = new CriteresRecherchePSUMandatForm();
			CardexUser user = (CardexUser)subject.getUser();
			psuMandatForm2.init();
			psuMandatForm2.setEntite(Long.toString(user.getEntite()));
			psuMandatForm2.setSiteOrigine(Long.toString(user.getSite()));
			request.getSession().setAttribute("recherchePSUMandat",psuMandatForm2);
			
			return mapping.findForward("success");
	    } catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);
			return mapping.findForward("success");
		} catch (BusinessException be) {
			handleBusinessException(be, errors, request);
			return mapping.findForward("success");
		} catch (ValueObjectMapperException vome) {
			return mapping.findForward("success");
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
    public ActionForward refreshRechercheMandat(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.fine("Refresh de recherche des mandats");

        return mapping.findForward("success");
    }


    /**
     * Par défaut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
     * <ul>Intervenant (code de l'intervenant)
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
        log.fine("Recherche par défaut de mandats");

		//On inscrit les valeurs par défaut.
        CriteresRecherchePSUMandatForm psuMandatForm = new CriteresRecherchePSUMandatForm();
    	CardexUser user = (CardexUser)subject.getUser();
    	psuMandatForm.init();
		psuMandatForm.setEntite(Long.toString(user.getEntite()));
		//psuMandatForm.setSiteOrigine(Long.toString(user.getSite()));
		//psuMandatForm.setIntervenant(user.getCode());
        request.getSession().setAttribute("recherchePSUMandat",psuMandatForm);
        

        return mapping.findForward("success");
    }

    /**
     * <p>
     * Cet événement survient lorsque dans l'écran de recherche de mandats PSU, l'utilisateur a choisi
     * de rechercher les mandats selon des critères différents. 
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
        log.fine("Recherche de mandats");

        ActionMessages errors = new ActionMessages();

        try {
			PSUMandatBusinessDelegate delegate =  new PSUMandatBusinessDelegate();
            CriteresRecherchePSUMandatForm criteresRecherchePSUMandatHtmlForm = (CriteresRecherchePSUMandatForm) form;
            CriteresRecherchePSUMandatVO criteresRecherchePSUMandat = new CriteresRecherchePSUMandatVO();

            // Conversion du composant d'état(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRecherchePSUMandatHtmlForm(criteresRecherchePSUMandatHtmlForm, criteresRecherchePSUMandat,subject.getLocale());

            // Exécution de la recherche via le service d'affaire(BusinessDelegate)
            List<PSUMandat> list = delegate.select(subject,criteresRecherchePSUMandat);
            log.fine(criteresRecherchePSUMandat.toString());
            log.fine(list.size() + " Mandats trouvés...");

            // Ajout des mandats dans le composant d'état (ActionForm)
            List currentList = new ArrayList();
            Iterator   it = list.iterator();

            while (it.hasNext()) {
				PSUMandat psuMandat = (PSUMandat)it.next();
				PSUMandatForm psuMandatForm = new PSUMandatForm();
				ValueObjectMapper.convertPSUMandat(psuMandat, psuMandatForm,subject.getLocale());
				psuMandatForm.assignerValeurDeListe( subject );
                currentList.add(psuMandatForm);
            }

            criteresRecherchePSUMandatHtmlForm.setListeResultat( currentList );
            criteresRecherchePSUMandatHtmlForm.getListeResultat().assignerTrierDefault(PSUMandatTrieListe.CLE_NUMERO_MANDAT, true, new PSUMandatTrieListe());

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

	/**
	 * <p>
	 * Cet événement survient lorsque dans l'écran de recherche de mandats PSU, l'utilisateur a choisi
	 * d'afficher les actions consignées pour un mandat de la liste de recherche.
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
	public ActionForward findLiensConsignationAction(CardexAuthenticationSubject subject,
								ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response) throws IOException,
								ServletException {
		log.fine("Affichage des actions consignées");

		ActionMessages errors = new ActionMessages();

		try {
			PSUMandatBusinessDelegate delegate =  new PSUMandatBusinessDelegate();
			PSUMandatForm psuMandatForm = (PSUMandatForm) form;
			ValueListIterator results;

			// Conversion du composant d'état(ActionForm) en composant d'affaire(Value Object)
			//ValueObjectMapper.convertPSUMandatHtmlForm(psuMandatForm, psuMandat,subject.getLocale());

			// Aller rechercher les informations sur le mandat
			PSUMandat psuMandat = new PSUMandatVO();
            ValueObjectMapper.convertPSUMandatHtmlForm(psuMandatForm, psuMandat,
                    subject.getLocale());
			psuMandat = delegate.find(subject,psuMandat);
            ValueObjectMapper.convertPSUMandat(psuMandat, psuMandatForm,
                    subject.getLocale());
			
			// Exécution de la recherche via le service d'affaire(BusinessDelegate)
			results = delegate.findLiensConsignationAction(subject,psuMandatForm.getNumeroMandat());
			log.fine(psuMandatForm.toString());
			log.fine(results.getSize() + " Consignations trouvées...");

			// Ajout des mandats dans le composant d'état (ActionForm)
			//int nombreDeResultats = (int)criteresRecherchePSUMandat.getMaximumResultatsRecherche();
			Collection list = results.getNextElements(2000);
			Collection currentList = new ArrayList();
			Iterator   it = list.iterator();

			while (it.hasNext()) {
				ConsignationActionPSU consignationActionPSU = (ConsignationActionPSU)it.next();
				ConsignationActionPSUForm consignationActionPSUForm = new ConsignationActionPSUForm();
				ValueObjectMapper.convertConsignationActionPSU(consignationActionPSU, consignationActionPSUForm,subject.getLocale());
				consignationActionPSUForm.assignerValeurDeListe(subject);
				currentList.add(consignationActionPSUForm);
			}
			request.getSession().setAttribute("consignationActionResultats",results);
			request.getSession().setAttribute("consignationAction",currentList);
			request.getSession().setAttribute("PSUMandat",psuMandatForm);
			return mapping.findForward("success");
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);
			return mapping.findForward("error");
		} catch (BusinessException be) {
			handleBusinessException(be, errors, request);
			return (new ActionForward(mapping.getInput()));
		} catch (IteratorException ie) {
			handleIteratorException(ie, errors, request);
			return mapping.findForward("error");
		} catch (ValueObjectMapperException vome) {
			handleValueObjectMapperException(vome, errors, request);
			return mapping.findForward("error");
		}
	}

	/**
	 * Approbation d'un mandat à partir de l'écran de consultation.
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
	public ActionForward approbation(CardexAuthenticationSubject subject,
							  ActionMapping mapping, ActionForm form,
							  HttpServletRequest request,
							  HttpServletResponse response) throws IOException,
							  ServletException {
		log.fine("Approbation d'un mandat PSU");

		ActionMessages errors = new ActionMessages();
		CardexUser user = (CardexUser)subject.getUser();
		String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);

		try {
			PSUMandatBusinessDelegate delegate = new PSUMandatBusinessDelegate();
			PSUMandatForm psuMandatForm = (PSUMandatForm)form;
			PSUMandat psuMandat = new PSUMandatVO();
			psuMandatForm.setDateApprobation(currentDate);
			psuMandatForm.setApprobateur(user.getCode());
			psuMandatForm.setStatut(String.valueOf(GlobalConstants.Statut.DOSSIER_ACTIF));
			ValueObjectMapper.convertPSUMandatHtmlForm(psuMandatForm, psuMandat,
					subject.getLocale());
			delegate.approbation(subject,psuMandat);
			//ValueObjectMapper.convertPSUMandat(psuMandat, psuMandatForm,subject.getLocale());

			// Stockage des données de référence concernant le contenu des liste déroulante
			//DonneeReferenceCache.storePSUMandatDonneeReference(subject, mapping,form, request,response);

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
	 * Renversement de l'approbation d'un mandat à partir de l'écran de consultation.
	 * Un mandat approuvé ne pouvant plus être modifié, cette action permet d'annuler
	 * l'approbation en cas de besoin de modifier le mandat (même principe que dans
	 * les narrations).
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
	public ActionForward permettreModification(CardexAuthenticationSubject subject,
							  ActionMapping mapping, ActionForm form,
							  HttpServletRequest request,
							  HttpServletResponse response) throws IOException,
							  ServletException {
		log.fine("Permettre la modification d'un mandat PSU");

		ActionMessages errors = new ActionMessages();

		try {
			verifierToken(request);
			PSUMandatBusinessDelegate delegate = new PSUMandatBusinessDelegate();
			PSUMandatForm psuMandatForm = (PSUMandatForm)form;
			PSUMandat psuMandat = new PSUMandatVO();
			psuMandatForm.setDateApprobation("");
			psuMandatForm.setApprobateur("");
			psuMandatForm.setStatut(GlobalConstants.Statut.DOSSIER_INACTIF);
			ValueObjectMapper.convertPSUMandatHtmlForm(psuMandatForm, psuMandat,
					subject.getLocale());
			delegate.approbation(subject,psuMandat);
			//ValueObjectMapper.convertPSUMandat(psuMandat, psuMandatForm,subject.getLocale());

			// Stockage des données de référence concernant le contenu des liste déroulante
			//DonneeReferenceCache.storePSUMandatDonneeReference(subject, mapping,form, request,response);

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
	 * Cette fonction valide les informations retournées par un mandat pour
	 * s'assurer de la cohérence. Voici la logique qui commande cette validation.
	 * À noter que dès qu'une condition logique est rencontrée dans cet ordre, la
	 * validation s'arrête et une réponse positive est retournée. Dans le cas,
	 * contraire, l'erreur est retournée et elle sera renvoyée à l'utilisateur.
	 * 1.	Critères de dossier : site, catégorie, fondé, intervenant.  Les champs Fondé et Site devront obligatoirement être associés à une catégorie pour réduire la quantité de messages générés.  Le champ Intervenant peut être utilisé seul ou en combinaison avec Fondé et Catégorie. Actions possibles : Ajout ou Recherche.
	 * 2.	Critères de dossier : Numéro de dossier ou Numéro Cardex.  Actions possibles : Consultation, Liaison, Suppression ou Mise à jour.
	 * 3.	Critères de sujet : Numéro de fiche. Actions possibles : Consultation, Liaison, Suppression ou Mise à jour.
	 * 4.	Critères de sujet : Nom, Prénom.  Une combinaison des deux critères est préférable, mais l’un ou l’autre pourra être utilisé.  Actions possibles : Ajout ou Recherche.
	 * 5.	Critères de société : Numéro de fiche. Actions possibles : Consultation, Liaison, Suppression ou Mise à jour.
	 * 6.	Critères de société : Nom.  Il faudra s’assurer que le nom de société est inscrit exactement tel que voulu dans la base de données étant donné l’imprécision qui entoure souvent les noms de société.  Actions possibles : Recherche, Consultation, Mise à jour, Liaison, Suppression
	 * 7.	Critères de véhicule : Numéro d’immatriculation.  Actions possibles : Consultation, Mise à jour, Liaison, Suppression, Ajout.
	 * 8.	Critères de véhicule : Province.  Si la province est Québec, beaucoup de messages seront générés.  Actions possibles : Ajout, Recherche
	 * 9.	Critères de narration : Mots clés.  Actions possibles : Ajout, Recherche
	 * Les actions LIAISON et SUPPRESSION ne s'appliquent qu'à des actions dans les onglets.  La mise à jour dans un onglet
	 * n'apparaît pas pertinente pour un suivi d'utilisateurs, étant donné le trop grand nombre de messages
	 * que cela pourrait générer.
	 * @param psuMandatForm
	 * @return
	 */
	private static String validation(PSUMandatForm psuMandatForm){

	String reponse = "OK";
	String action = psuMandatForm.getTypeAction();
		//Dès qu'une condition valable est rencontrée, on supprime les valeurs non concernées.
		//Vérification des critères de dossier
		if(!OracleDAOUtils.isEmpty(psuMandatForm.getIntervenant()) && 
		   OracleDAOUtils.isEmpty(psuMandatForm.getCategorie())){
			if((action.equals(GlobalConstants.TypeAction.AJOUT)) ||
			   (action.equals(GlobalConstants.TypeAction.RECHERCHE))){
				psuMandatForm.setNumeroDossier("");
				psuMandatForm.setNumeroCardex("");
				psuMandatForm.setFicheSujet("");
				psuMandatForm.setSujetNom("");
				psuMandatForm.setSujetPrenom("");
				psuMandatForm.setFicheSociete("");
				psuMandatForm.setSocieteNom("");
				psuMandatForm.setImmatriculation("");
				psuMandatForm.setProvince("");
				psuMandatForm.setMotCle1("");
				psuMandatForm.setMotCle2("");
				psuMandatForm.setMotCle3("");
				psuMandatForm.setGenreFichier(GlobalConstants.GenreFichier.DOSSIER);
			}else{
				reponse = "Le type d'action doit être Ajout ou Recherche avec un intervenant.";
			}
		}else{
			if(!OracleDAOUtils.isEmpty(psuMandatForm.getCategorie())){
				//Fondé obligatoires avec Catégorie
				if(!OracleDAOUtils.isEmpty(psuMandatForm.getFonde())){
					if((action.equals(GlobalConstants.TypeAction.AJOUT)) ||
   					   (action.equals(GlobalConstants.TypeAction.RECHERCHE))){
						psuMandatForm.setNumeroDossier("");
						psuMandatForm.setNumeroCardex("");
						psuMandatForm.setFicheSujet("");
						psuMandatForm.setSujetNom("");
						psuMandatForm.setSujetPrenom("");
						psuMandatForm.setFicheSociete("");
						psuMandatForm.setSocieteNom("");
						psuMandatForm.setImmatriculation("");
						psuMandatForm.setProvince("");
						psuMandatForm.setMotCle1("");
						psuMandatForm.setMotCle2("");
						psuMandatForm.setMotCle3("");
						psuMandatForm.setGenreFichier(GlobalConstants.GenreFichier.DOSSIER);
					}else{
						reponse = "Le type d'action doit être Ajout ou Recherche avec une catégorie.";
					}
				}else{
					reponse = "La valeura Fondé est obligatoire avec une catégorie.";
				}
			}else{
				if((!OracleDAOUtils.isEmpty(psuMandatForm.getNumeroDossier())) ||
				  (!OracleDAOUtils.isEmpty(psuMandatForm.getNumeroCardex()))){
					if((action.equals(GlobalConstants.TypeAction.CONSULTATION)) ||
					   (action.equals(GlobalConstants.TypeAction.LIAISON))  ||
					   (action.equals(GlobalConstants.TypeAction.SUPPRESSION)) ||
					   (action.equals(GlobalConstants.TypeAction.MISE_A_JOUR))){
								psuMandatForm.setFonde("");
								psuMandatForm.setCategorie("");
								psuMandatForm.setType("");
								psuMandatForm.setNature("");
								psuMandatForm.setGenre("");
								psuMandatForm.setFicheSujet("");
								psuMandatForm.setSujetNom("");
								psuMandatForm.setSujetPrenom("");
								psuMandatForm.setFicheSociete("");
								psuMandatForm.setSocieteNom("");
								psuMandatForm.setImmatriculation("");
								psuMandatForm.setProvince("");
								psuMandatForm.setMotCle1("");
								psuMandatForm.setMotCle2("");
								psuMandatForm.setMotCle3("");
								psuMandatForm.setGenreFichier(GlobalConstants.GenreFichier.DOSSIER);
					    }else{
					    	reponse = "Le type d'action doit être Consultation, Liaison ou Mise à jour avec un numéro de dossier.";
					    }
				  }else{ //Vérification des critères de sujet
					if(!OracleDAOUtils.isEmpty(psuMandatForm.getFicheSujet())){
						if((action.equals(GlobalConstants.TypeAction.CONSULTATION)) ||
						   (action.equals(GlobalConstants.TypeAction.LIAISON))   ||
						   (action.equals(GlobalConstants.TypeAction.SUPPRESSION))){
								psuMandatForm.setNumeroDossier("");
								psuMandatForm.setNumeroCardex("");
								psuMandatForm.setFonde("");
								psuMandatForm.setCategorie("");
								psuMandatForm.setType("");
								psuMandatForm.setNature("");
								psuMandatForm.setGenre("");
							    psuMandatForm.setIntervenant("");
								psuMandatForm.setSujetNom("");
								psuMandatForm.setSujetPrenom("");
								psuMandatForm.setFicheSociete("");
								psuMandatForm.setSocieteNom("");
								psuMandatForm.setImmatriculation("");
								psuMandatForm.setProvince("");
								psuMandatForm.setMotCle1("");
								psuMandatForm.setMotCle2("");
								psuMandatForm.setMotCle3("");
								psuMandatForm.setGenreFichier(GlobalConstants.GenreFichier.SUJET);
							}else{
								reponse = "Le type d'action doit être Consultation, Liaison ou Suppression avec un numéro de sujet.";
							}
					}else{
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getSujetNom()) || 
						   !OracleDAOUtils.isEmpty(psuMandatForm.getSujetPrenom())){
							if((action.equals(GlobalConstants.TypeAction.AJOUT)) ||
   							   (action.equals(GlobalConstants.TypeAction.RECHERCHE)) ||
   							   (action.equals(GlobalConstants.TypeAction.MISE_A_JOUR))){
								psuMandatForm.setNumeroDossier("");
								psuMandatForm.setNumeroCardex("");
								psuMandatForm.setFicheSujet("");
								psuMandatForm.setFonde("");
								psuMandatForm.setCategorie("");
								psuMandatForm.setType("");
								psuMandatForm.setNature("");
								psuMandatForm.setGenre("");
								psuMandatForm.setIntervenant("");
								psuMandatForm.setFicheSociete("");
								psuMandatForm.setSocieteNom("");
								psuMandatForm.setImmatriculation("");
								psuMandatForm.setProvince("");
								psuMandatForm.setMotCle1("");
								psuMandatForm.setMotCle2("");
								psuMandatForm.setMotCle3("");
							}else{
								reponse = "Le type d'action doit être Insertion, Mise à jour ou Recherche avec un nom de sujet.";
							}
						}else{//Vérification des critères de societe
						  if(!OracleDAOUtils.isEmpty(psuMandatForm.getFicheSociete())){
							if((action.equals(GlobalConstants.TypeAction.CONSULTATION)) ||
							   (action.equals(GlobalConstants.TypeAction.LIAISON))   ||
							   (action.equals(GlobalConstants.TypeAction.SUPPRESSION)) ||
							   (action.equals(GlobalConstants.TypeAction.MISE_A_JOUR))){
									psuMandatForm.setNumeroDossier("");
									psuMandatForm.setNumeroCardex("");
									psuMandatForm.setFonde("");
									psuMandatForm.setCategorie("");
									psuMandatForm.setType("");
									psuMandatForm.setNature("");
									psuMandatForm.setGenre("");
									psuMandatForm.setIntervenant("");
									psuMandatForm.setSujetNom("");
									psuMandatForm.setSujetPrenom("");
									psuMandatForm.setFicheSujet("");
									psuMandatForm.setSocieteNom("");
									psuMandatForm.setImmatriculation("");
									psuMandatForm.setProvince("");
									psuMandatForm.setMotCle1("");
									psuMandatForm.setMotCle2("");
									psuMandatForm.setMotCle3("");
									psuMandatForm.setGenreFichier(GlobalConstants.GenreFichier.SOCIETE);
								}else{
									reponse = "Le type d'action doit être Consultation, Liaison, Suppression ou Mise à jour avec un numéro de société.";
								}
						}else{
							if(!OracleDAOUtils.isEmpty(psuMandatForm.getSocieteNom())){
								if((action.equals(GlobalConstants.TypeAction.CONSULTATION)) ||
								   (action.equals(GlobalConstants.TypeAction.RECHERCHE))||
								(action.equals(GlobalConstants.TypeAction.LIAISON)) ||
								(action.equals(GlobalConstants.TypeAction.SUPPRESSION))  ||
								   (action.equals(GlobalConstants.TypeAction.AJOUT)) ||
								   (action.equals(GlobalConstants.TypeAction.MISE_A_JOUR))){
									psuMandatForm.setNumeroDossier("");
									psuMandatForm.setNumeroCardex("");
									psuMandatForm.setFicheSujet("");
									psuMandatForm.setFonde("");
									psuMandatForm.setCategorie("");
									psuMandatForm.setType("");
									psuMandatForm.setNature("");
									psuMandatForm.setGenre("");
									psuMandatForm.setIntervenant("");
									psuMandatForm.setFicheSociete("");
									psuMandatForm.setImmatriculation("");
									psuMandatForm.setProvince("");
									psuMandatForm.setMotCle1("");
									psuMandatForm.setMotCle2("");
									psuMandatForm.setMotCle3("");
									psuMandatForm.setGenreFichier(GlobalConstants.GenreFichier.SOCIETE);
								}else{
									reponse = "Le type d'action doit être Insertion, Consultation, Liaison, Suppression, Mise à jour ou Recherche avec un nom de société.";
								}
						   }else{//Vérification des critères de vehicule
						   if(!OracleDAOUtils.isEmpty(psuMandatForm.getImmatriculation())){
							 if((action.equals(GlobalConstants.TypeAction.CONSULTATION)) ||
								(action.equals(GlobalConstants.TypeAction.LIAISON))   ||
							    (action.equals(GlobalConstants.TypeAction.SUPPRESSION)) ||
							    (action.equals(GlobalConstants.TypeAction.AJOUT)) ||
								(action.equals(GlobalConstants.TypeAction.MISE_A_JOUR))){
									 psuMandatForm.setNumeroDossier("");
									 psuMandatForm.setNumeroCardex("");
									 psuMandatForm.setFonde("");
									 psuMandatForm.setCategorie("");
									 psuMandatForm.setType("");
									 psuMandatForm.setNature("");
									 psuMandatForm.setGenre("");
									 psuMandatForm.setIntervenant("");
									 psuMandatForm.setSujetNom("");
									 psuMandatForm.setSujetPrenom("");
									 psuMandatForm.setFicheSujet("");
									 psuMandatForm.setSocieteNom("");
									 psuMandatForm.setFicheSociete("");
									 psuMandatForm.setProvince("");
									 psuMandatForm.setMotCle1("");
									 psuMandatForm.setMotCle2("");
									 psuMandatForm.setMotCle3("");
									psuMandatForm.setGenreFichier(GlobalConstants.GenreFichier.VEHICULE);
								 }else{
									 reponse = "Le type d'action doit être Consultation, Insertion, Liaison ou Mise à jour avec un numéro d'immatriculation.";
								 }
						 }else{
							 if(!OracleDAOUtils.isEmpty(psuMandatForm.getProvince())){
								if((action.equals(GlobalConstants.TypeAction.AJOUT)) ||
								   (action.equals(GlobalConstants.TypeAction.RECHERCHE))){
									 psuMandatForm.setNumeroDossier("");
									 psuMandatForm.setNumeroCardex("");
									 psuMandatForm.setFicheSujet("");
									 psuMandatForm.setFonde("");
									 psuMandatForm.setCategorie("");
									 psuMandatForm.setType("");
									 psuMandatForm.setNature("");
									 psuMandatForm.setGenre("");
									 psuMandatForm.setIntervenant("");
									 psuMandatForm.setFicheSujet("");
									 psuMandatForm.setImmatriculation("");
									 psuMandatForm.setFicheSociete("");
									 psuMandatForm.setMotCle1("");
									 psuMandatForm.setMotCle2("");
									 psuMandatForm.setMotCle3("");
									 psuMandatForm.setGenreFichier(GlobalConstants.GenreFichier.VEHICULE);
								 }else{
									 reponse = "Le type d'action doit être Insertion ou Recherche avec une province.";
								 }
							 }else{//Vérification des critères de narrations
								if((!OracleDAOUtils.isEmpty(psuMandatForm.getMotCle1())) || (!OracleDAOUtils.isEmpty(psuMandatForm.getMotCle2())) || (!OracleDAOUtils.isEmpty(psuMandatForm.getMotCle3()))){
									if((action.equals(GlobalConstants.TypeAction.AJOUT)) ||
									   (action.equals(GlobalConstants.TypeAction.RECHERCHE))){
										  psuMandatForm.setNumeroDossier("");
										  psuMandatForm.setNumeroCardex("");
										  psuMandatForm.setFonde("");
										  psuMandatForm.setCategorie("");
										  psuMandatForm.setType("");
										  psuMandatForm.setNature("");
										  psuMandatForm.setGenre("");
										  psuMandatForm.setIntervenant("");
										  psuMandatForm.setSujetNom("");
										  psuMandatForm.setSujetPrenom("");
										  psuMandatForm.setFicheSujet("");
										  psuMandatForm.setSocieteNom("");
										  psuMandatForm.setFicheSociete("");
										  psuMandatForm.setProvince("");
										  psuMandatForm.setImmatriculation("");
										  psuMandatForm.setGenreFichier(GlobalConstants.GenreFichier.NARRATION);
									  }else{
										  reponse = "Le type d'action doit être Insertion ou Recherche avec les mots-clés.";
									  }
								}
							 }
						   }
						}
					}
				}
			}
		  }
	   }
	}
					
	return reponse;
  
  }

  /**
   * Cette fonction centrale est appelée par toutes les actions effectuées par 
   * l'application (recherche, consultation, liaison, mise-à-jour, insertion) pour
   * vérifier si un mandat est associé aux données traitées. Si c'est le cas, une 
   * entrée est créée dans la table CS_CONSIGNATION_ACTION et un message est envoyée
   * aux intervenants qui ont demandé le suivi.
   * 
   * @param psuMandatForm
   * @return
   */
  public static void verificationMandat(CardexAuthenticationSubject subject,
  					PSUMandatForm psuMandatForm, String genreFichier, String action)
  		 throws IOException, ServletException {
	
	//log.fine("Vérification d'un mandat PSU");
	ValueListIterator results;
	ListeCache listeCache = ListeCache.getInstance();
	
	try {
		PSUMandatBusinessDelegate delegate = new PSUMandatBusinessDelegate();
		PSUMandat psuMandat = new PSUMandatVO();
		ValueObjectMapper.convertPSUMandatHtmlForm(psuMandatForm, psuMandat,
				subject.getLocale());
		List<PSUMandat> mandatList = delegate.verification(subject, psuMandat, genreFichier, action);
		String genreFichierAction = listeCache.obtenirLabel(subject, psuMandatForm.getGenreFichier()+" ", new GenreFichierCle(subject));

			Collection currentList = new ArrayList();
			Iterator   it = mandatList.iterator();
			String dateAction = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);
			boolean traitement = false;
			while(it.hasNext()){
			    //Un mandat a été trouvé. Un message est envoyé et une incription
			    //est effectuée dans la table cs_consignation_action
				psuMandat = (PSUMandat)it.next();
				PSUMandatHtmlForm psuMandatForm2 = new PSUMandatForm();
				ValueObjectMapper.convertPSUMandat(psuMandat, psuMandatForm2,subject.getLocale());
				String destinataireA = "";
				String destinataireCC = "";
				String destinataireCCI = "";
				if(!OracleDAOUtils.isEmpty(psuMandatForm2.getDestinataireA())){
					destinataireA = psuMandatForm2.getDestinataireA();
				}
				if(!OracleDAOUtils.isEmpty(psuMandatForm2.getDestinataireCC())){
					destinataireCC = psuMandatForm2.getDestinataireCC();
				}
				if(!OracleDAOUtils.isEmpty(psuMandatForm2.getDestinataireCCI())){
					destinataireCCI = psuMandatForm2.getDestinataireCCI();
				}
				String reference = "";
				String message = "";
				if(!OracleDAOUtils.isEmpty(psuMandatForm2.getMessage())){
					message = psuMandatForm2.getMessage();
				}
				
				//Lecture des données du mandat à afficher dans le message, selon le genre de fichier. 
				if(genreFichier.equals(GlobalConstants.GenreFichier.SUJET)){
					traitement = true;
					if(!OracleDAOUtils.isEmpty(psuMandatForm.getFicheSujet())){
						reference = psuMandatForm.getFicheSujet();
					}else{
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getSujetNom())){
							reference = psuMandatForm.getSujetNom();
						}
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getSujetPrenom())){
							reference = reference  + ", " + psuMandatForm.getSujetPrenom();
						}
					}
					if(action.equals(GlobalConstants.TypeAction.LIAISON)){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Élément ajouté : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					if(action.equals(GlobalConstants.TypeAction.SUPPRESSION)){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Élément supprimé : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sujet : " + reference;
				}
				if(genreFichier.equals(GlobalConstants.GenreFichier.DOSSIER)){
					traitement = true;
					if(action.equals(GlobalConstants.TypeAction.LIAISON)){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Élément ajouté : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					if(action.equals(GlobalConstants.TypeAction.SUPPRESSION)){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Élément supprimé : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					//if((action.equals(GlobalConstants.TypeAction.CONSULTATION)) || (action.equals(GlobalConstants.TypeAction.MISE_A_JOUR)) || (action.equals(GlobalConstants.TypeAction.INSERTION))){
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getNumeroCardex())){
							reference = psuMandatForm.getNumeroCardex();
							message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Numéro Cardex : " + reference;
						}
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getNumeroDossier())){
							message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Numéro de dossier : " + psuMandatForm.getNumeroDossier();
						}
					//}
				}
				
				if(genreFichier.equals(GlobalConstants.GenreFichier.SOCIETE)){
					traitement = true;
					if(action.equals(GlobalConstants.TypeAction.LIAISON)){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Élément ajouté : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					if(action.equals(GlobalConstants.TypeAction.SUPPRESSION)){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Élément supprimé : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					if(!OracleDAOUtils.isEmpty(psuMandatForm.getFicheSociete())){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Société : " + psuMandatForm.getFicheSociete();
					}
					if(!OracleDAOUtils.isEmpty(psuMandatForm.getSocieteNom())){
						reference = psuMandatForm.getSocieteNom();
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nom de la société : " + reference;
					}
				}
				if(genreFichier.equals(GlobalConstants.GenreFichier.VEHICULE)){
					traitement = true;
					if(action.equals(GlobalConstants.TypeAction.LIAISON)){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Élément ajouté : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					if(action.equals(GlobalConstants.TypeAction.SUPPRESSION)){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Élément supprimé : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					if(!OracleDAOUtils.isEmpty(psuMandatForm.getImmatriculation())){
						reference = psuMandatForm.getImmatriculation();
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Véhicule : " + reference;
					}
				}
				if(genreFichier == GlobalConstants.GenreFichier.NARRATION){
				  //Les narrations constituent un cas spécial. Pour chaque mot-clé de
				  //chaque mandat retourné, on effectue une vérification avec les valeurs
				  //saisies soit dans une recherche, soit dans un ajout.
				  String motcle1 = " ";
				  String motcle2 = " ";
				  String motcle3 = " ";
				  if(!OracleDAOUtils.isEmpty(psuMandatForm2.getMotCle1())){
					  motcle1 = psuMandatForm2.getMotCle1();
				  }
				  if(!OracleDAOUtils.isEmpty(psuMandatForm2.getMotCle2())){
					  motcle2 = psuMandatForm2.getMotCle2();
				  }
				  if(!OracleDAOUtils.isEmpty(psuMandatForm2.getMotCle3())){
					  motcle3 = psuMandatForm2.getMotCle3();
				  }
				  if(action.equals(GlobalConstants.TypeAction.RECHERCHE)){
				  	if(!OracleDAOUtils.isEmpty(psuMandatForm.getMotCle1())){
					  	if((psuMandatForm.getMotCle1().toUpperCase().equals(motcle1.toUpperCase()))
					  	    || (psuMandatForm.getMotCle1().toUpperCase().equals(motcle2.toUpperCase()))
					  	    || (psuMandatForm.getMotCle1().toUpperCase().equals(motcle3.toUpperCase()))){
								traitement = true;
								reference = psuMandatForm.getMotCle1();
								message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mot clé cherché : " + reference;
					  	    }
				  	}
					if(!OracleDAOUtils.isEmpty(psuMandatForm.getMotCle2())){
						if((psuMandatForm.getMotCle2().toUpperCase().equals(motcle1.toUpperCase()))
							|| (psuMandatForm.getMotCle2().toUpperCase().equals(motcle2.toUpperCase()))
							|| (psuMandatForm.getMotCle2().toUpperCase().equals(motcle3.toUpperCase()))){
								traitement = true;
								reference = psuMandatForm.getMotCle2();
								message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mot clé cherché : " + reference;
							}
					}
					if(!OracleDAOUtils.isEmpty(psuMandatForm.getMotCle3())){
						if((psuMandatForm.getMotCle3().toUpperCase().equals(motcle1.toUpperCase()))
							|| (psuMandatForm.getMotCle3().toUpperCase().equals(motcle2.toUpperCase()))
							|| (psuMandatForm.getMotCle3().toUpperCase().equals(motcle3.toUpperCase()))){
								traitement = true;
								reference = psuMandatForm.getMotCle3();
								message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mot clé cherché : " + reference;
							}
					}
				  }//recherche
				  if(action.equals(GlobalConstants.TypeAction.AJOUT)){
				  	 //On vérifie si les mots-clés se retrouvent dans la narration qui vient d'être saisie
					if(!OracleDAOUtils.isEmpty(psuMandatForm2.getMotCle1())){
						if(psuMandatForm.getReference1().toUpperCase().indexOf(psuMandatForm2.getMotCle1().toUpperCase()) > -1){
							traitement = true;
							reference = psuMandatForm2.getMotCle1();
							message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mot clé cherché : " + reference;
						}
					}
					if(!OracleDAOUtils.isEmpty(psuMandatForm2.getMotCle2())){
						if(psuMandatForm.getReference1().toUpperCase().indexOf(psuMandatForm2.getMotCle2().toUpperCase()) > -1){
							traitement = true;
							reference = psuMandatForm2.getMotCle2();
							message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mot clé cherché : " + reference;
						}
					}
					if(!OracleDAOUtils.isEmpty(psuMandatForm2.getMotCle3())){
						if(psuMandatForm.getReference1().toUpperCase().indexOf(psuMandatForm2.getMotCle3().toUpperCase()) > -1){
							traitement = true;
							reference = psuMandatForm2.getMotCle3();
							message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mot clé cherché : " + reference;
						}
					}
					if(traitement){
						//Si un mot clé a été trouvé, on complète le message pour indiquer l'élément auquel la narration a été ajoutée.
						if(psuMandatForm.getGenreFichier().equals(GlobalConstants.GenreFichier.SUJET)){
						   message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sujet : " + psuMandatForm.getFicheSujet();
						   psuMandatForm.setReference1(psuMandatForm.getFicheSujet());
						}
						if(psuMandatForm.getGenreFichier().equals(GlobalConstants.GenreFichier.DOSSIER)){
						   message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dossier : " + psuMandatForm.getNumeroCardex();
						   psuMandatForm.setReference1(psuMandatForm.getNumeroCardex());
						}
						if(psuMandatForm.getGenreFichier().equals(GlobalConstants.GenreFichier.SOCIETE)){
						   message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Société : " + psuMandatForm.getSocieteNom();
						   psuMandatForm.setReference1(psuMandatForm.getSocieteNom());
						}
						if(psuMandatForm.getGenreFichier().equals(GlobalConstants.GenreFichier.VEHICULE)){
						   message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Véhicule : " + psuMandatForm.getImmatriculation();
						   psuMandatForm.setReference1(psuMandatForm.getImmatriculation());
						}
					}
				  }//Insertion
				}//narration
				//Si les conditions d'un mandat ont été rencontrées, on envoie un message aux destinataires
				//concernés et on crée une entrée dans la table CS_CONSIGNATION_ACTION.
				if(traitement){				
					CardexUser user = (CardexUser)subject.getUser();
					
					String site = listeCache.obtenirLabel(subject, user.getSite(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.SELECTION));
					String intervenant = listeCache.obtenirLabel(subject, user.getCode(), new IntervenantCle(subject)); 
					message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Intervenant : " + user.getCode() + " : " + intervenant + " - " + user.getSecteur() + " - " + site;
					if(!OracleDAOUtils.isEmpty(destinataireA)){
						envoiCourriel(subject, destinataireA, destinataireCC, destinataireCCI, message);
					}
					String dateEnvoi = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);
					//Ecriture de l'accès
					ConsignationActionPSUForm consignationActionPSUForm = new ConsignationActionPSUForm(); 
					consignationActionPSUForm.setDateConsignation(dateAction);
					consignationActionPSUForm.setDateCourriel(dateEnvoi);
					consignationActionPSUForm.setGenreFichier(genreFichier);
					consignationActionPSUForm.setNumeroMandat(psuMandat.getNumeroMandat());
					consignationActionPSUForm.setIntervenant(user.getCode());
					consignationActionPSUForm.setTypeAction(action);
					consignationActionPSUForm.setReferenceSource(reference);
					consignationActionPSUForm.setReferenceAction(psuMandatForm.getReference1()); //Élément en liaison
					consignationActionPSUForm.setGenreFichierAction(psuMandatForm.getGenreFichier());
					ConsignationActionPSU  consignationActionPSU = new ConsignationActionPSUVO();
					ValueObjectMapper.convertConsignationActionPSUHtmlForm(consignationActionPSUForm, consignationActionPSU, subject.getLocale());
					consignationActionPSU.setReferenceSourceCle(psuMandat.getReferenceCle());
					consignationActionPSU.setReferenceSourceSite(psuMandat.getReferenceSite());
					delegate.ecrireConsignationAction(subject, consignationActionPSU);
				}
		}//while
	} catch (BusinessResourceException bre) {
		System.out.println(bre);
	} catch (BusinessException be) {
		System.out.println(be);
	} catch (ValueObjectMapperException vome) {
		System.out.println(vome);
	} 
  }

  private static void envoiCourriel(CardexAuthenticationSubject subject,
  							 String destinataireA,
  							 String destinataireCC,
							 String destinataireCCI, String corpsMessage) throws IOException,
								  ServletException, BusinessResourceException {
		 //String smtpServer;
		 // get the SMTP server from the servlet properties
		 //smtpServer = config.getInitParameter("smtpServer");
		 // get the message parameters from the HTML page
	  	 ListeCache listeCache = ListeCache.getInstance();
		 String from = listeCache.obtenirLabel(subject, GlobalConstants.ListeCache.EXPEDITEUR_COURRIEL, new InterventionCodeCourrielCle());
		 String to = destinataireA;
		 String cc = destinataireCC;
		 String cci = destinataireCCI;
		 String objet = "Suivi des utilisateurs";
		 String texte = corpsMessage;

		 //PrintWriter out = res.getWriter(); 
		 //res.setContentType("text/html");

		 try {
			 // set the SMTP host property value
			 Properties properties = System.getProperties();
			 properties.put("mail.smtp.host", "relais1.loto-quebec.com");

			 // create a JavaMail session
			 Session session = Session.getInstance(properties, null);

			 // create a new MIME message 
			 MimeMessage message = new MimeMessage(session);

			 // set the from address
			 Address fromAddress = new InternetAddress(from);
			 message.setFrom(fromAddress);

			 // set the to address
			 if (to != null) {
				 Address[] toAddress = InternetAddress.parse(to);
				 message.setRecipients(Message.RecipientType.TO, toAddress);
			 }
			 else
				 throw new MessagingException("No \"To\" address specified");
			//Copie conforme
			if (cc != null) {
				Address[] ccAddress = InternetAddress.parse(cc);
				message.setRecipients(Message.RecipientType.CC, ccAddress);
			}
			//Copie conforme invisible
			if (cci != null) {
				Address[] cciAddress = InternetAddress.parse(cci);
				message.setRecipients(Message.RecipientType.BCC, cciAddress);
			}

			// set the subject
			message.setSubject(objet, "iso-8859-1");

			// set the message body
			//On force l'acceptation de caractères latins avec setContent au lieu de setText
			//message.setText(texte);
			message.setContent(texte, "text/html;charset=\"UTF-8\"");

			 // send the message
			 Transport.send(message);

			 System.out.println("Message sent successfully.");
		 }
		 catch (AddressException e) {
			System.out.println("Invalid e-mail address.<br>" + e.getMessage());
		 }
		 catch (SendFailedException e) {
			System.out.println("Send failed.<br>" + e.getMessage()); 
		 }
		 catch (MessagingException e) {
			System.out.println("Unexpected error.<br>" + e.getMessage());
		 }
	  }

}

