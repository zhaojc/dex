/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
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

import com.lotoQuebec.correcteurAdresse.AdresseSortie;
import com.lotoQuebec.correcteurAdresse.AdresseSortieRecherche;
import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.delegate.AdresseBusinessDelegate;
import com.lotoquebec.cardex.business.vo.AdresseVO;
import com.lotoquebec.cardex.presentation.model.form.AdresseForm;
import com.lotoquebec.cardex.presentation.model.form.AdresseRechercheForm;
import com.lotoquebec.cardex.presentation.model.form.RechercheValidationAdresseForm;
import com.lotoquebec.cardex.presentation.model.form.SocieteForm;
import com.lotoquebec.cardex.presentation.model.form.SujetForm;
import com.lotoquebec.cardex.presentation.model.form.ValidationAdresseForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Cette classe gère les événements en rapport
 * avec le cas d'utilisation gestion des dossiers.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/03/20 22:06:38 $
 */
public class AdresseAction extends AbstractAction {

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
    public ActionForward ajouter(CardexAuthenticationSubject subject,
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                ServletException {
        log.fine("Création d'une nouvelle adresse");

         AdresseForm adresseForm = new AdresseForm();
         
		  if (form instanceof SujetForm) {
		    log.fine("Création d'une adresse liée au sujet: " + form);
		    SujetForm sujetForm = (SujetForm)form;
		    adresseForm.setLien(sujetForm.getCle());
		    adresseForm.setLienSite(sujetForm.getSite());
		  }else if (form instanceof SocieteForm) {
		    log.fine("Création d'une adresse liée a la societe: " + form);
		    SocieteForm societeForm = (SocieteForm)form;
		    adresseForm.setLien(societeForm.getCle());
		    adresseForm.setLienSite(societeForm.getSite());
		  }
		
		  log.fine("Adresse : " + adresseForm);
		  request.getSession().setAttribute("adresse",adresseForm);
		
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
    public ActionForward consulter(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Accès à une adresse");

        ActionMessages errors = new ActionMessages();

        try {
            AdresseBusinessDelegate delegate = new AdresseBusinessDelegate();
            AdresseForm adresseForm = (AdresseForm)form;
            Adresse adresse = new AdresseVO();
            ValueObjectMapper.convertAdresseHtmlForm(adresseForm, adresse,
                    subject.getLocale());
            adresse = delegate.find(subject,adresse);
            adresseForm.init();
            ValueObjectMapper.convertAdresse(adresse, adresseForm,
                    subject.getLocale());
            
            return mapping.findForward("success");
        } catch (BusinessResourceException bre) {
            handleBusinessResourceException(bre, errors, request);
            return mapping.findForward("error");
        } catch (BusinessException be) {
            handleBusinessException(be, errors, request);
            return (new ActionForward(mapping.getInput()));
        } catch (ValueObjectMapperException vome) {
        	handleValueObjectMapperException(vome, request);
            return mapping.findForward("error");
        }
    }

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


    
    /**
     * Validation de l'adresse, cette méthode est appelé à l'entré de la validation
     * @param subject
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public ActionForward initialiserValider(CardexAuthenticationSubject subject,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		log.fine("Validation d'une adresse (Default).");
		ActionMessages errors = new ActionMessages();

		try {
			
			AdresseBusinessDelegate delegate = new AdresseBusinessDelegate();
			AdresseForm adresseForm = (AdresseForm) form;
			
			Adresse adresse = new AdresseVO();
			ValueObjectMapper.convertAdresseHtmlForm(adresseForm, adresse, subject.getLocale());
			log.fine("Adresse: " + adresse);
			
			ValidationAdresseForm validationAdresseForm = new ValidationAdresseForm();
			adresseForm.assignerValeurDeListe(subject);
			validationAdresseForm.setAdresseSaisie(adresseForm);
			
			try {
				AdresseSortie adresseSortie = delegate.validationAdresse(subject, adresse);
				
				// l'adresse est valide dans sa forme actuelle
				if (GlobalConstants.Adresse.Statut.VALIDE.equals(adresseSortie.getStatut()))
					return mapping.findForward("sansValidation");
				AdresseForm adresseValideForm = new AdresseForm();
				ValueObjectMapper.convertAdresseEntreeAForm(subject, adresseSortie, adresseValideForm);
				validationAdresseForm.setAdresseValide(adresseValideForm);
				request.getSession().setAttribute("validationAdresse", validationAdresseForm);
			} catch (LinkageError e) {
				e.printStackTrace();
				return mapping.findForward("sansValidation");
			}
			
			return mapping.findForward("success");
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);
			return mapping.findForward("error");
		} catch (ValueObjectMapperException vome) {
			handleValueObjectMapperException(vome, request);
			return mapping.findForward("error");
		}
	}
    
    /**
     * Valider l'adresse retourné par la validation de l'adresse
     * @param subject
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public ActionForward valider(CardexAuthenticationSubject subject,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		log.fine("Validation d'une adresse.");
		ActionMessages errors = new ActionMessages();

		try {
			AdresseBusinessDelegate delegate = new AdresseBusinessDelegate();
			ValidationAdresseForm validationAdresseForm = (ValidationAdresseForm) form;
			
			Adresse adresse = new AdresseVO();
			ValueObjectMapper.convertAdresseHtmlForm(validationAdresseForm.getAdresseValide(), adresse, subject.getLocale());
			log.fine("Adresse: " + adresse);
			AdresseSortie adresseSortie = delegate.validationAdresse(subject, adresse);
			
			AdresseForm adresseValideForm = new AdresseForm();
			ValueObjectMapper.convertAdresseEntreeAForm(subject, adresseSortie, adresseValideForm);
			validationAdresseForm.setAdresseValide(adresseValideForm);
			request.getSession().setAttribute("validationAdresse", validationAdresseForm);
			
			return mapping.findForward("success");
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);

			return mapping.findForward("error");
		} catch (ValueObjectMapperException vome) {
			return mapping.findForward("error");
		}
	}
    
    /**
     * Transférer l'adresse à l'écran de confirmation de l'adresse
     * @param subject
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public ActionForward choisirValider(CardexAuthenticationSubject subject,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		log.fine("Choix d'une adresse.");
		ActionMessages errors = new ActionMessages();

		try {
			ValidationAdresseForm validationAdresseForm = (ValidationAdresseForm) form;
			validationAdresseForm.getAdresseValide().assignerValeurDeListe(subject);
			
			AdresseForm adresseForm = validationAdresseForm.obtenirAdresseChoisi();
			request.getSession().setAttribute("adresse", adresseForm);
			
			return mapping.findForward("success");
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);

			return mapping.findForward("error");
		}
	}
   
    /**
     * Recherche des adresses venant du progiciel de validation d'adresse
     * @param subject
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public ActionForward initialiserRechercher(CardexAuthenticationSubject subject,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		log.fine("Recherche des adresses.");
		ActionMessages errors = new ActionMessages();

		try {
			AdresseBusinessDelegate delegate = new AdresseBusinessDelegate();
			AdresseForm adresseForm = (AdresseForm) form;
			
			Adresse adresse = new AdresseVO();
			ValueObjectMapper.convertAdresseHtmlForm(adresseForm, adresse, subject.getLocale());
			log.fine("Adresse: " + adresse);
			List listeAdresseRecherche = delegate.rechercheValidationAdresse(subject, adresse);

			if (listeAdresseRecherche == null)
				return mapping.getInputForward();
			
			if (listeAdresseRecherche.size() == 1){
				AdresseSortieRecherche adresseSortieRecherche = (AdresseSortieRecherche) listeAdresseRecherche.get(0);
				AdresseRechercheForm adresseRechercheForm = new AdresseRechercheForm();
				ValueObjectMapper.convertAdresseSortieRechercheAForm(subject, adresseSortieRecherche, adresseRechercheForm);
				
				if (adresseRechercheForm.isAdresseTrouve(adresseForm)){
					adresseRechercheForm.assignerInformationSaisie(adresseForm);
					adresseRechercheForm.copierExtentionDansAdresse(adresseForm);
					request.getSession().setAttribute("adresse", adresseRechercheForm);
				}
			}
			RechercheValidationAdresseForm rechercheValidationAdresseForm = new RechercheValidationAdresseForm();
			rechercheValidationAdresseForm.setAdresseSaisie(adresseForm);
			rechercheValidationAdresseForm.getListeResultat().init();
			Iterator iter = listeAdresseRecherche.iterator();
			
			while (iter.hasNext()) {
				AdresseSortieRecherche adresseSortieRecherche = (AdresseSortieRecherche) iter.next();
				AdresseRechercheForm adresseRechercheForm = new AdresseRechercheForm();
				ValueObjectMapper.convertAdresseSortieRechercheAForm(subject, adresseSortieRecherche, adresseRechercheForm);
				adresseRechercheForm.assignerValeurDeListe(subject);
				rechercheValidationAdresseForm.addListeResultat(adresseRechercheForm);
			}
			request.getSession().setAttribute("rechercheValidationAdresse", rechercheValidationAdresseForm);
			
			return mapping.findForward("success");
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);

			return mapping.findForward("error");
		} catch (ValueObjectMapperException vome) {
			return mapping.findForward("error");
		}
	}
    
    /**
     * Recherche d'une adresse
     * @param subject
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public ActionForward rechercher(CardexAuthenticationSubject subject,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		log.fine("Recherche des adresses.");
		ActionMessages errors = new ActionMessages();

		try {
			AdresseBusinessDelegate delegate = new AdresseBusinessDelegate();
			RechercheValidationAdresseForm rechercheValidationAdresseForm = (RechercheValidationAdresseForm) form;
			rechercheValidationAdresseForm.getListeResultat().vider();
			
			Adresse adresse = new AdresseVO();
			ValueObjectMapper.convertAdresseHtmlForm(rechercheValidationAdresseForm.getAdresseSaisie(), adresse, subject.getLocale());
			log.fine("Adresse: " + adresse);
			List listeAdresseRecherche = delegate.rechercheValidationAdresse(subject, adresse);

			rechercheValidationAdresseForm.getListeResultat().init();
			Iterator iter = listeAdresseRecherche.iterator();
			
			while (iter.hasNext()) {
				AdresseSortieRecherche adresseSortieRecherche = (AdresseSortieRecherche) iter.next();
				AdresseRechercheForm adresseRechercheForm = new AdresseRechercheForm();
				ValueObjectMapper.convertAdresseSortieRechercheAForm(subject, adresseSortieRecherche, adresseRechercheForm);
				adresseRechercheForm.assignerValeurDeListe(subject);
				rechercheValidationAdresseForm.addListeResultat(adresseRechercheForm);
			}
			
			return mapping.findForward("success");
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);

			return mapping.findForward("error");
		} catch (ValueObjectMapperException vome) {
			return mapping.findForward("error");
		}
	}
    
    /**
     * Choix d'une adresse retrouvé
     * @param subject
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public ActionForward choisirRechercher(CardexAuthenticationSubject subject,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		log.fine("Choix d'une adresse touvée.");
		ActionMessages errors = new ActionMessages();

		try {
			RechercheValidationAdresseForm rechercheValidationAdresseForm = (RechercheValidationAdresseForm) form;
			int choix = Integer.valueOf(rechercheValidationAdresseForm.getChoixAdresse()).intValue();
			AdresseRechercheForm adresseRechercheForm = (AdresseRechercheForm) rechercheValidationAdresseForm.getListeResultat().getResultatComplet().get(choix);
			adresseRechercheForm.assignerValeurDeListe(subject);
			errors.add(adresseRechercheForm.validate(mapping, request));
			
			if (errors.isEmpty() == false){
				saveErrors(request, errors);
				return mapping.getInputForward();
			}
			adresseRechercheForm.copierExtentionDansAdresse(rechercheValidationAdresseForm.getAdresseSaisie());
			request.getSession().setAttribute("adresse", adresseRechercheForm);
			
			return mapping.findForward("success");
		} catch (BusinessResourceException bre) {
			handleBusinessResourceException(bre, errors, request);

			return mapping.findForward("error");
		}
	}
    
}

