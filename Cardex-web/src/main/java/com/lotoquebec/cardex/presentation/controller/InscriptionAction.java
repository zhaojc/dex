/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.lotoquebec.cardex.business.Inscription;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.vo.InscriptionVO;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.InscriptionForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SiteApplicableTableValeurCle;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Cette classe gère les événements en rapport
 * avec le cas d'utilisation onglets inscription.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/03/13 17:50:29 $
 */
public class InscriptionAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

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
    public ActionForward create(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.fine("Création d'une inscription");

        ActionErrors errors = new ActionErrors();

        try {
            DossierForm dossierForm = (DossierForm)form;
            InscriptionForm inscriptionForm = new InscriptionForm();
 
            inscriptionForm.setLien(dossierForm.getCle());
            inscriptionForm.setLienSite(dossierForm.getSite());
            
            inscriptionForm.init( subject );
            assignerTousSiteApplicable(subject, inscriptionForm, dossierForm);
            //Pour les jeux en ligne, seul le site EspaceJeux doit être disponible.
            if(dossierForm.getCategorie().equals(GlobalConstants.Categorie.AE_ESPACEJEUX)){
	            String[] sitesChoisis = new String[1];
	            sitesChoisis[0] = GlobalConstants.SiteMaisonJeux.ESPACEJEUX;
	            inscriptionForm.setSitesChoisis(sitesChoisis);
	            //La période est obligatoirement fixe.
	            inscriptionForm.setPeriode(GlobalConstants.Periode.FIXE);
            }
            request.getSession().setAttribute("inscription",inscriptionForm);
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
     * On assigne "tous les sites applicables" si c'est un dossier d'"accès interdit"
     * ou "avis de guet".
     * @param subject
     * @param inscription
     * @param dossier
	 * @throws BusinessResourceException 
	 * @throws ValueObjectMapperException 
     */
    private void assignerTousSiteApplicable(CardexAuthenticationSubject subject, InscriptionForm inscriptionForm, DossierForm dossierForm) throws BusinessResourceException, ValueObjectMapperException {
    	
    	if (String.valueOf(GlobalConstants.Nature.ACCES_INTERDIT).equals( dossierForm.getNature() ) 
    	|| String.valueOf(GlobalConstants.Nature.AVIS_DE_GUET).equals( dossierForm.getNature() )){
    		inscriptionForm.setTousSitesApplicables(true);
    		String[] site = obtenirArraySite(subject);
    		inscriptionForm.setSitesChoisis(site);
    	}
	}

	private String[] obtenirArraySite(CardexAuthenticationSubject subject)
			throws BusinessResourceException, ValueObjectMapperException {
		ListeCache listeCache = ListeCache.getInstance();
		Collection listeTousSitesApplicables = listeCache.obtenirListe(subject, new SiteApplicableTableValeurCle(subject, GlobalConstants.Entite.MAISON_JEUX, GlobalConstants.ActionSecurite.MODIFICATION));
		
		Iterator iter = listeTousSitesApplicables.iterator();
		String[] site = new String[listeTousSitesApplicables.size()];

		for (int i = 0; i < site.length; i++) {
			LabelValueBean labelValueBean = (LabelValueBean) iter.next();
			
			if (StringUtils.isNotEmpty(labelValueBean.getValue()))
				site[i] = labelValueBean.getValue();
		}
		return site;
	}    
    
	/**
     * <p>
     * Cet événement survient lorsque l'utilisateur change l'entité dans l'écran d'ajout
     * d'une inscription.  Cette opération n'est normalement permise qu'aux Investigations.
     * Elle permet de rafraîchir la liste des sites disponibles pour l'origine de la demande.
     * <p>
     *
     * @param subject Le sujet authentifié
     * @param mapping L' ActionMapping utilsé pour sélectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requête (optionnelle)
     * @param request La requête HTTP traitée
     * @param response La réponse HTTP créée
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entrée/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward refreshInscription(CardexAuthenticationSubject subject,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,
                                 ServletException {
        log.fine("Refresh ecran d'inscription");

        return mapping.findForward("success");
    }
    /**
     * <p>
     * La consultation d'une inscription sert à mettre à jour les champs sur les rencontres.
     * La fonction n'est en pricipe accessible qu'aux consultants des centres d'évaluation.
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
        log.fine("Consultation d'une inscription");

        ActionMessages errors = new ActionMessages();
		CardexUser  cardexUser = (CardexUser)subject.getUser();

        try {
        	DossierBusinessDelegate delegate = new DossierBusinessDelegate();
        	InscriptionForm inscriptionForm = (InscriptionForm)form;
        	Inscription inscription = new InscriptionVO();
        	ValueObjectMapper.convertInscriptionHtmlForm(inscriptionForm, inscription,
                    subject.getLocale());
            inscription = delegate.findInscription(subject,inscription);
            inscriptionForm.init(subject);
            ValueObjectMapper.convertInscription(inscription, inscriptionForm,
                    subject.getLocale());
            if(inscriptionForm.getIntervenantRencontreFinale() == null){
            	inscriptionForm.setIntervenantRencontreFinale(cardexUser.getCode().toString());
            }
            if(inscriptionForm.getIntervenantRencontreInitiale() == null){
            	inscriptionForm.setIntervenantRencontreInitiale(cardexUser.getCode().toString());
            }
           
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
     * <p>
     * La mise à jour d'une inscription sert à inscrire les informations sur les rencontres.
     * La fonction n'est en pricipe accessible qu'aux consultants des centres d'évaluation.
     * L'inscription permet de rendre inactif le contrat (tâche en différé) sans avoir
     * à modifier la nature du contrat (et donc de conserver l'historique des données).
     * La fonction vise à remplacer la procédure en place qui consistait à rendre un 
     * contrat bonifié de Montréal en contrat fixe pour qu'il soit invalidé par la tâche en 
     * différé. Cette façon de faire modifiait la nature du contrat et donc faisait
     * perdre l'historique des données.
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
        log.fine("Mise à jour d'une inscription");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
        	DossierBusinessDelegate delegate = new DossierBusinessDelegate();
        	InscriptionForm inscriptionForm = (InscriptionForm)form;
        	//On s'assure qu'une date a été saisie.
        	if (StringUtils.isNotEmpty(inscriptionForm.getDateRencontreFinale()) 
        	|| StringUtils.isNotEmpty(inscriptionForm.getDateRencontreInitiale())) {
        		//On vide l'intervenant si les dates de rencontre n'ont pas été saisiee.
            	if (StringUtils.isEmpty(inscriptionForm.getDateRencontreFinale())) {
            		inscriptionForm.setIntervenantRencontreFinale("");
            	}
            	if (StringUtils.isEmpty(inscriptionForm.getDateRencontreInitiale()) ) {
            		inscriptionForm.setIntervenantRencontreInitiale("");
            	}
	        	Inscription inscription = new InscriptionVO();
	            ValueObjectMapper.convertInscriptionHtmlForm(inscriptionForm, inscription,
	                    subject.getLocale());
	            delegate.updateInscription(subject,inscription);
	            
	            DossierAction dossierAction = new DossierAction();
	            DossierForm dossierForm = (DossierForm) request.getSession().getAttribute("dossier");
	            dossierAction.show(subject, mapping, dossierForm, request, response);
        	}
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
    
}

