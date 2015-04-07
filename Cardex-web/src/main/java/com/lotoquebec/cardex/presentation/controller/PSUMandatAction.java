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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Cette classe g�re les �v�nements en rapport
 * avec le cas d'utilisation PSU (Programme de Suivi des utilisateurs).
 * Cette fonction sert � �tiqueter des donn�es dont on d�sire �tre inform�
 * en temps r�el d'actions sp�cifiques les concernant, par exemple, chaque fois
 * qu'une fiche sujet est consult�e.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/04/30 12:18:08 $
 */
public class PSUMandatAction extends AbstractAction {

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


        log.debug("Cr�ation d'un nouveau mandat");
        ActionErrors errors = new ActionErrors();
        CardexUser user = (CardexUser)subject.getUser();
        String currentDate = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);

        PSUMandatForm psuMandatForm = new PSUMandatForm();

        //Valeur par d�faut
		psuMandatForm.setCreateur(user.getCode());
		psuMandatForm.setDateCreation(currentDate);
		psuMandatForm.setModifiable(true);
		psuMandatForm.setStatut(GlobalConstants.Statut.DOSSIER_INACTIF);
		psuMandatForm.setNumeroMandat(" "); //Pour indiquer qu'il s'agit d'un nouveau mandat.
        log.debug("Mandat : " + psuMandatForm);
        request.getSession().setAttribute("PSUMandat",psuMandatForm);
        
		//On vide le message de validation au cas o�.
		request.getSession().setAttribute("message", "");
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
    public ActionForward refresh(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Refresh des mandats PSU");

		//On vide le message de validation au cas o�.
		request.getSession().setAttribute("message", "");

        return mapping.findForward("success");
    }

    /**
     * Affichage d'un mandat � partir des r�sultats de recherche.
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
        log.debug("Acc�s � un mandat PSU");

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

			//On vide le message de validation au cas o�.
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
	 * Mise � jour d'un mandat � partir de l'�cran de consultation.
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
	public ActionForward update(CardexAuthenticationSubject subject,
							  ActionMapping mapping, ActionForm form,
							  HttpServletRequest request,
							  HttpServletResponse response) throws IOException,
							  ServletException {
		log.debug("Mise � jour d'un mandat PSU");

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

			// Stockage des donn�es de r�f�rence concernant le contenu des liste d�roulante
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
	 * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
	 * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
	 * @param request La requ�te HTTP trait�e
	 * @param response La r�ponse HTTP cr��e
	 * @param delegate Le business delegate offrant les services d'affaires
	 *
	 * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
	 * @exception ServletException si une exception servlet survient
	 */
	public ActionForward save(CardexAuthenticationSubject subject,
							  ActionMapping mapping, ActionForm form,
							  HttpServletRequest request,
							  HttpServletResponse response) throws IOException,
							  ServletException {
		log.debug("Sauvegarde d'un nouveau mandat PSU");

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

			// Stockage des donn�es de r�f�rence concernant le contenu des liste d�roulante
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
	 * Suppression ou d�sactivation d'un mandat.
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
	public ActionForward delete(CardexAuthenticationSubject subject,
							  ActionMapping mapping, ActionForm form,
							  HttpServletRequest request,
							  HttpServletResponse response) throws IOException,
							  ServletException {
		log.debug("Suppression d'un mandat PSU");

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
			//On relance la recherche par d�faut.
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
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
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
    public ActionForward refreshRechercheMandat(CardexAuthenticationSubject subject,
                                       ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws IOException,
                                       ServletException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        log.debug("Refresh de recherche des mandats");

        return mapping.findForward("success");
    }


    /**
     * Par d�faut, l'application remplit automatiquement les champs suivants :
     * <li>
     * <ul>Entite (Entite de l'utilisateur)
     * <ul>Site d'origine (Site de l'utilisateur)
     * <ul>Intervenant (code de l'intervenant)
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
        log.debug("Recherche par d�faut de mandats");

		//On inscrit les valeurs par d�faut.
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
     * Cet �v�nement survient lorsque dans l'�cran de recherche de mandats PSU, l'utilisateur a choisi
     * de rechercher les mandats selon des crit�res diff�rents. 
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
        log.debug("Recherche de mandats");

        ActionMessages errors = new ActionMessages();

        try {
			PSUMandatBusinessDelegate delegate =  new PSUMandatBusinessDelegate();
            CriteresRecherchePSUMandatForm criteresRecherchePSUMandatHtmlForm = (CriteresRecherchePSUMandatForm) form;
            CriteresRecherchePSUMandatVO criteresRecherchePSUMandat = new CriteresRecherchePSUMandatVO();

            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
            ValueObjectMapper.convertCriteresRecherchePSUMandatHtmlForm(criteresRecherchePSUMandatHtmlForm, criteresRecherchePSUMandat,subject.getLocale());

            // Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
            List<PSUMandat> list = delegate.select(subject,criteresRecherchePSUMandat);
            log.debug(criteresRecherchePSUMandat.toString());
            log.debug(list.size() + " Mandats trouv�s...");

            // Ajout des mandats dans le composant d'�tat (ActionForm)
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
	 * Cet �v�nement survient lorsque dans l'�cran de recherche de mandats PSU, l'utilisateur a choisi
	 * d'afficher les actions consign�es pour un mandat de la liste de recherche.
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
	public ActionForward findLiensConsignationAction(CardexAuthenticationSubject subject,
								ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response) throws IOException,
								ServletException {
		log.debug("Affichage des actions consign�es");

		ActionMessages errors = new ActionMessages();

		try {
			PSUMandatBusinessDelegate delegate =  new PSUMandatBusinessDelegate();
			PSUMandatForm psuMandatForm = (PSUMandatForm) form;
			ValueListIterator results;

			// Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
			//ValueObjectMapper.convertPSUMandatHtmlForm(psuMandatForm, psuMandat,subject.getLocale());

			// Aller rechercher les informations sur le mandat
			PSUMandat psuMandat = new PSUMandatVO();
            ValueObjectMapper.convertPSUMandatHtmlForm(psuMandatForm, psuMandat,
                    subject.getLocale());
			psuMandat = delegate.find(subject,psuMandat);
            ValueObjectMapper.convertPSUMandat(psuMandat, psuMandatForm,
                    subject.getLocale());
			
			// Ex�cution de la recherche via le service d'affaire(BusinessDelegate)
			results = delegate.findLiensConsignationAction(subject,psuMandatForm.getNumeroMandat());
			log.debug(psuMandatForm.toString());
			log.debug(results.getSize() + " Consignations trouv�es...");

			// Ajout des mandats dans le composant d'�tat (ActionForm)
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
	 * Approbation d'un mandat � partir de l'�cran de consultation.
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
	public ActionForward approbation(CardexAuthenticationSubject subject,
							  ActionMapping mapping, ActionForm form,
							  HttpServletRequest request,
							  HttpServletResponse response) throws IOException,
							  ServletException {
		log.debug("Approbation d'un mandat PSU");

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

			// Stockage des donn�es de r�f�rence concernant le contenu des liste d�roulante
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
	 * Renversement de l'approbation d'un mandat � partir de l'�cran de consultation.
	 * Un mandat approuv� ne pouvant plus �tre modifi�, cette action permet d'annuler
	 * l'approbation en cas de besoin de modifier le mandat (m�me principe que dans
	 * les narrations).
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
	public ActionForward permettreModification(CardexAuthenticationSubject subject,
							  ActionMapping mapping, ActionForm form,
							  HttpServletRequest request,
							  HttpServletResponse response) throws IOException,
							  ServletException {
		log.debug("Permettre la modification d'un mandat PSU");

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

			// Stockage des donn�es de r�f�rence concernant le contenu des liste d�roulante
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
	 * Cette fonction valide les informations retourn�es par un mandat pour
	 * s'assurer de la coh�rence. Voici la logique qui commande cette validation.
	 * � noter que d�s qu'une condition logique est rencontr�e dans cet ordre, la
	 * validation s'arr�te et une r�ponse positive est retourn�e. Dans le cas,
	 * contraire, l'erreur est retourn�e et elle sera renvoy�e � l'utilisateur.
	 * 1.	Crit�res de dossier : site, cat�gorie, fond�, intervenant.  Les champs Fond� et Site devront obligatoirement �tre associ�s � une cat�gorie pour r�duire la quantit� de messages g�n�r�s.  Le champ Intervenant peut �tre utilis� seul ou en combinaison avec Fond� et Cat�gorie. Actions possibles : Ajout ou Recherche.
	 * 2.	Crit�res de dossier : Num�ro de dossier ou Num�ro Cardex.  Actions possibles : Consultation, Liaison, Suppression ou Mise � jour.
	 * 3.	Crit�res de sujet : Num�ro de fiche. Actions possibles : Consultation, Liaison, Suppression ou Mise � jour.
	 * 4.	Crit�res de sujet : Nom, Pr�nom.  Une combinaison des deux crit�res est pr�f�rable, mais l�un ou l�autre pourra �tre utilis�.  Actions possibles : Ajout ou Recherche.
	 * 5.	Crit�res de soci�t� : Num�ro de fiche. Actions possibles : Consultation, Liaison, Suppression ou Mise � jour.
	 * 6.	Crit�res de soci�t� : Nom.  Il faudra s�assurer que le nom de soci�t� est inscrit exactement tel que voulu dans la base de donn�es �tant donn� l�impr�cision qui entoure souvent les noms de soci�t�.  Actions possibles : Recherche, Consultation, Mise � jour, Liaison, Suppression
	 * 7.	Crit�res de v�hicule : Num�ro d�immatriculation.  Actions possibles : Consultation, Mise � jour, Liaison, Suppression, Ajout.
	 * 8.	Crit�res de v�hicule : Province.  Si la province est Qu�bec, beaucoup de messages seront g�n�r�s.  Actions possibles : Ajout, Recherche
	 * 9.	Crit�res de narration : Mots cl�s.  Actions possibles : Ajout, Recherche
	 * Les actions LIAISON et SUPPRESSION ne s'appliquent qu'� des actions dans les onglets.  La mise � jour dans un onglet
	 * n'appara�t pas pertinente pour un suivi d'utilisateurs, �tant donn� le trop grand nombre de messages
	 * que cela pourrait g�n�rer.
	 * @param psuMandatForm
	 * @return
	 */
	private static String validation(PSUMandatForm psuMandatForm){

	String reponse = "OK";
	String action = psuMandatForm.getTypeAction();
		//D�s qu'une condition valable est rencontr�e, on supprime les valeurs non concern�es.
		//V�rification des crit�res de dossier
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
				reponse = "Le type d'action doit �tre Ajout ou Recherche avec un intervenant.";
			}
		}else{
			if(!OracleDAOUtils.isEmpty(psuMandatForm.getCategorie())){
				//Fond� obligatoires avec Cat�gorie
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
						reponse = "Le type d'action doit �tre Ajout ou Recherche avec une cat�gorie.";
					}
				}else{
					reponse = "La valeura Fond� est obligatoire avec une cat�gorie.";
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
					    	reponse = "Le type d'action doit �tre Consultation, Liaison ou Mise � jour avec un num�ro de dossier.";
					    }
				  }else{ //V�rification des crit�res de sujet
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
								reponse = "Le type d'action doit �tre Consultation, Liaison ou Suppression avec un num�ro de sujet.";
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
								reponse = "Le type d'action doit �tre Insertion, Mise � jour ou Recherche avec un nom de sujet.";
							}
						}else{//V�rification des crit�res de societe
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
									reponse = "Le type d'action doit �tre Consultation, Liaison, Suppression ou Mise � jour avec un num�ro de soci�t�.";
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
									reponse = "Le type d'action doit �tre Insertion, Consultation, Liaison, Suppression, Mise � jour ou Recherche avec un nom de soci�t�.";
								}
						   }else{//V�rification des crit�res de vehicule
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
									 reponse = "Le type d'action doit �tre Consultation, Insertion, Liaison ou Mise � jour avec un num�ro d'immatriculation.";
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
									 reponse = "Le type d'action doit �tre Insertion ou Recherche avec une province.";
								 }
							 }else{//V�rification des crit�res de narrations
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
										  reponse = "Le type d'action doit �tre Insertion ou Recherche avec les mots-cl�s.";
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
   * Cette fonction centrale est appel�e par toutes les actions effectu�es par 
   * l'application (recherche, consultation, liaison, mise-�-jour, insertion) pour
   * v�rifier si un mandat est associ� aux donn�es trait�es. Si c'est le cas, une 
   * entr�e est cr��e dans la table CS_CONSIGNATION_ACTION et un message est envoy�e
   * aux intervenants qui ont demand� le suivi.
   * 
   * @param psuMandatForm
   * @return
   */
  public static void verificationMandat(CardexAuthenticationSubject subject,
  					PSUMandatForm psuMandatForm, String genreFichier, String action)
  		 throws IOException, ServletException {
	
	//log.debug("V�rification d'un mandat PSU");
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
			    //Un mandat a �t� trouv�. Un message est envoy� et une incription
			    //est effectu�e dans la table cs_consignation_action
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
				
				//Lecture des donn�es du mandat � afficher dans le message, selon le genre de fichier. 
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
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�l�ment ajout� : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					if(action.equals(GlobalConstants.TypeAction.SUPPRESSION)){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�l�ment supprim� : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sujet : " + reference;
				}
				if(genreFichier.equals(GlobalConstants.GenreFichier.DOSSIER)){
					traitement = true;
					if(action.equals(GlobalConstants.TypeAction.LIAISON)){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�l�ment ajout� : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					if(action.equals(GlobalConstants.TypeAction.SUPPRESSION)){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�l�ment supprim� : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					//if((action.equals(GlobalConstants.TypeAction.CONSULTATION)) || (action.equals(GlobalConstants.TypeAction.MISE_A_JOUR)) || (action.equals(GlobalConstants.TypeAction.INSERTION))){
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getNumeroCardex())){
							reference = psuMandatForm.getNumeroCardex();
							message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Num�ro Cardex : " + reference;
						}
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getNumeroDossier())){
							message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Num�ro de dossier : " + psuMandatForm.getNumeroDossier();
						}
					//}
				}
				
				if(genreFichier.equals(GlobalConstants.GenreFichier.SOCIETE)){
					traitement = true;
					if(action.equals(GlobalConstants.TypeAction.LIAISON)){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�l�ment ajout� : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					if(action.equals(GlobalConstants.TypeAction.SUPPRESSION)){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�l�ment supprim� : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					if(!OracleDAOUtils.isEmpty(psuMandatForm.getFicheSociete())){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Soci�t� : " + psuMandatForm.getFicheSociete();
					}
					if(!OracleDAOUtils.isEmpty(psuMandatForm.getSocieteNom())){
						reference = psuMandatForm.getSocieteNom();
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nom de la soci�t� : " + reference;
					}
				}
				if(genreFichier.equals(GlobalConstants.GenreFichier.VEHICULE)){
					traitement = true;
					if(action.equals(GlobalConstants.TypeAction.LIAISON)){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�l�ment ajout� : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					if(action.equals(GlobalConstants.TypeAction.SUPPRESSION)){
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�l�ment supprim� : " + genreFichierAction;
						if(!OracleDAOUtils.isEmpty(psuMandatForm.getReference1())){
							message = message + " - " + psuMandatForm.getReference1();
						}
					}
					if(!OracleDAOUtils.isEmpty(psuMandatForm.getImmatriculation())){
						reference = psuMandatForm.getImmatriculation();
						message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;V�hicule : " + reference;
					}
				}
				if(genreFichier == GlobalConstants.GenreFichier.NARRATION){
				  //Les narrations constituent un cas sp�cial. Pour chaque mot-cl� de
				  //chaque mandat retourn�, on effectue une v�rification avec les valeurs
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
								message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mot cl� cherch� : " + reference;
					  	    }
				  	}
					if(!OracleDAOUtils.isEmpty(psuMandatForm.getMotCle2())){
						if((psuMandatForm.getMotCle2().toUpperCase().equals(motcle1.toUpperCase()))
							|| (psuMandatForm.getMotCle2().toUpperCase().equals(motcle2.toUpperCase()))
							|| (psuMandatForm.getMotCle2().toUpperCase().equals(motcle3.toUpperCase()))){
								traitement = true;
								reference = psuMandatForm.getMotCle2();
								message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mot cl� cherch� : " + reference;
							}
					}
					if(!OracleDAOUtils.isEmpty(psuMandatForm.getMotCle3())){
						if((psuMandatForm.getMotCle3().toUpperCase().equals(motcle1.toUpperCase()))
							|| (psuMandatForm.getMotCle3().toUpperCase().equals(motcle2.toUpperCase()))
							|| (psuMandatForm.getMotCle3().toUpperCase().equals(motcle3.toUpperCase()))){
								traitement = true;
								reference = psuMandatForm.getMotCle3();
								message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mot cl� cherch� : " + reference;
							}
					}
				  }//recherche
				  if(action.equals(GlobalConstants.TypeAction.AJOUT)){
				  	 //On v�rifie si les mots-cl�s se retrouvent dans la narration qui vient d'�tre saisie
					if(!OracleDAOUtils.isEmpty(psuMandatForm2.getMotCle1())){
						if(psuMandatForm.getReference1().toUpperCase().indexOf(psuMandatForm2.getMotCle1().toUpperCase()) > -1){
							traitement = true;
							reference = psuMandatForm2.getMotCle1();
							message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mot cl� cherch� : " + reference;
						}
					}
					if(!OracleDAOUtils.isEmpty(psuMandatForm2.getMotCle2())){
						if(psuMandatForm.getReference1().toUpperCase().indexOf(psuMandatForm2.getMotCle2().toUpperCase()) > -1){
							traitement = true;
							reference = psuMandatForm2.getMotCle2();
							message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mot cl� cherch� : " + reference;
						}
					}
					if(!OracleDAOUtils.isEmpty(psuMandatForm2.getMotCle3())){
						if(psuMandatForm.getReference1().toUpperCase().indexOf(psuMandatForm2.getMotCle3().toUpperCase()) > -1){
							traitement = true;
							reference = psuMandatForm2.getMotCle3();
							message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mot cl� cherch� : " + reference;
						}
					}
					if(traitement){
						//Si un mot cl� a �t� trouv�, on compl�te le message pour indiquer l'�l�ment auquel la narration a �t� ajout�e.
						if(psuMandatForm.getGenreFichier().equals(GlobalConstants.GenreFichier.SUJET)){
						   message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sujet : " + psuMandatForm.getFicheSujet();
						   psuMandatForm.setReference1(psuMandatForm.getFicheSujet());
						}
						if(psuMandatForm.getGenreFichier().equals(GlobalConstants.GenreFichier.DOSSIER)){
						   message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dossier : " + psuMandatForm.getNumeroCardex();
						   psuMandatForm.setReference1(psuMandatForm.getNumeroCardex());
						}
						if(psuMandatForm.getGenreFichier().equals(GlobalConstants.GenreFichier.SOCIETE)){
						   message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Soci�t� : " + psuMandatForm.getSocieteNom();
						   psuMandatForm.setReference1(psuMandatForm.getSocieteNom());
						}
						if(psuMandatForm.getGenreFichier().equals(GlobalConstants.GenreFichier.VEHICULE)){
						   message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;V�hicule : " + psuMandatForm.getImmatriculation();
						   psuMandatForm.setReference1(psuMandatForm.getImmatriculation());
						}
					}
				  }//Insertion
				}//narration
				//Si les conditions d'un mandat ont �t� rencontr�es, on envoie un message aux destinataires
				//concern�s et on cr�e une entr�e dans la table CS_CONSIGNATION_ACTION.
				if(traitement){				
					CardexUser user = (CardexUser)subject.getUser();
					
					String site = listeCache.obtenirLabel(subject, user.getSite(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.SELECTION));
					String intervenant = listeCache.obtenirLabel(subject, user.getCode(), new IntervenantCle(subject)); 
					message = message + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Intervenant : " + user.getCode() + " : " + intervenant + " - " + user.getSecteur() + " - " + site;
					if(!OracleDAOUtils.isEmpty(destinataireA)){
						envoiCourriel(subject, destinataireA, destinataireCC, destinataireCCI, message);
					}
					String dateEnvoi = TimestampFormat.format(new Timestamp(System.currentTimeMillis()),subject.getLocale(),true);
					//Ecriture de l'acc�s
					ConsignationActionPSUForm consignationActionPSUForm = new ConsignationActionPSUForm(); 
					consignationActionPSUForm.setDateConsignation(dateAction);
					consignationActionPSUForm.setDateCourriel(dateEnvoi);
					consignationActionPSUForm.setGenreFichier(genreFichier);
					consignationActionPSUForm.setNumeroMandat(psuMandat.getNumeroMandat());
					consignationActionPSUForm.setIntervenant(user.getCode());
					consignationActionPSUForm.setTypeAction(action);
					consignationActionPSUForm.setReferenceSource(reference);
					consignationActionPSUForm.setReferenceAction(psuMandatForm.getReference1()); //�l�ment en liaison
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
			//On force l'acceptation de caract�res latins avec setContent au lieu de setText
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

