/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

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
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Cette classe g�re les �v�nements en rapport
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
        LoggerFactory.getLogger((this.getClass()));

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
    public ActionForward create(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Cr�ation d'une inscription");

        ActionErrors errors = new ActionErrors();

        try {
            DossierForm dossierForm = (DossierForm)form;
            InscriptionForm inscriptionForm = new InscriptionForm();
 
            inscriptionForm.setLien(dossierForm.getCle());
            inscriptionForm.setLienSite(dossierForm.getSite());
            
            inscriptionForm.init( subject );
            assignerTousSiteApplicable(subject, inscriptionForm, dossierForm);
            //Pour les jeux en ligne, seul le site EspaceJeux doit �tre disponible.
            if(dossierForm.getCategorie().equals(GlobalConstants.Categorie.AE_ESPACEJEUX)){
	            String[] sitesChoisis = new String[1];
	            sitesChoisis[0] = GlobalConstants.SiteMaisonJeux.ESPACEJEUX;
	            inscriptionForm.setSitesChoisis(sitesChoisis);
	            //La p�riode est obligatoirement fixe.
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
     * On assigne "tous les sites applicables" si c'est un dossier d'"acc�s interdit"
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
     * Cet �v�nement survient lorsque l'utilisateur change l'entit� dans l'�cran d'ajout
     * d'une inscription.  Cette op�ration n'est normalement permise qu'aux Investigations.
     * Elle permet de rafra�chir la liste des sites disponibles pour l'origine de la demande.
     * <p>
     *
     * @param subject Le sujet authentifi�
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward refreshInscription(CardexAuthenticationSubject subject,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,
                                 ServletException {
        log.debug("Refresh ecran d'inscription");

        return mapping.findForward("success");
    }
    /**
     * <p>
     * La consultation d'une inscription sert � mettre � jour les champs sur les rencontres.
     * La fonction n'est en pricipe accessible qu'aux consultants des centres d'�valuation.
     * @param mapping L' ActionMapping utils� pour s�lectionner cette instance
     * @param actionForm L'ActionForm bean pour cette requ�te (optionnelle)
     * @param request La requ�te HTTP trait�e
     * @param response La r�ponse HTTP cr��e
     * @param delegate Le business delegate offrant les services d'affaires
     *
     * @exception IOException si une erreur d'entr�e/sortieif an input/output survient
     * @exception ServletException si une exception servlet survient
     */
    public ActionForward consulter(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Consultation d'une inscription");

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
     * La mise � jour d'une inscription sert � inscrire les informations sur les rencontres.
     * La fonction n'est en pricipe accessible qu'aux consultants des centres d'�valuation.
     * L'inscription permet de rendre inactif le contrat (t�che en diff�r�) sans avoir
     * � modifier la nature du contrat (et donc de conserver l'historique des donn�es).
     * La fonction vise � remplacer la proc�dure en place qui consistait � rendre un 
     * contrat bonifi� de Montr�al en contrat fixe pour qu'il soit invalid� par la t�che en 
     * diff�r�. Cette fa�on de faire modifiait la nature du contrat et donc faisait
     * perdre l'historique des donn�es.
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
        log.debug("Mise � jour d'une inscription");

        ActionMessages errors = new ActionMessages();

        try {
        	verifierToken(request);
        	DossierBusinessDelegate delegate = new DossierBusinessDelegate();
        	InscriptionForm inscriptionForm = (InscriptionForm)form;
        	//On s'assure qu'une date a �t� saisie.
        	if (StringUtils.isNotEmpty(inscriptionForm.getDateRencontreFinale()) 
        	|| StringUtils.isNotEmpty(inscriptionForm.getDateRencontreInitiale())) {
        		//On vide l'intervenant si les dates de rencontre n'ont pas �t� saisiee.
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

