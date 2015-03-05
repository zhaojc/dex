/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.SousCategoriesVO;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.SousCategoriesForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;

/**
 * Cette classe gère les événements en rapport
 * avec le cas d'utilisation onglets jeux.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/03/13 22:43:44 $
 */
public class SousCategoriesAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    /**
     * Afficher les listes des sous catégories
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
        log.fine("Accès aux sous-catégories");

        ActionMessages errors = new ActionMessages();

        try {
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = (DossierForm)form;
            Dossier dossier = new DossierVO();
            
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            dossier = delegate.find(subject, dossier);
            ValueObjectMapper.convertDossier(dossier, dossierForm,
                    subject.getLocale());            
            SousCategoriesVO sousCategoriesVO = delegate.findLiensSousCategories(subject, dossier);
            SousCategoriesForm sousCategoriesForm = new SousCategoriesForm(sousCategoriesVO);
            sousCategoriesForm.init(subject, dossierForm);
            
            // Obtenir liste sans premier choix vide.
            sousCategoriesForm.initDoubleListe(subject);
            request.getSession().setAttribute("sousCategories",sousCategoriesForm);
            
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
     * Rafraichir les différentes listes.
     * @param subject
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public ActionForward rafraichirSousCategories(CardexAuthenticationSubject subject,
    ActionMapping mapping, ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
		log.fine("Rafraichir les sous-catégories");
        SousCategoriesForm sousCategoriesForm  = (SousCategoriesForm)form;
        
		ActionMessages errors = new ActionMessages();
		
		try {

		    sousCategoriesForm.initListeGauche(subject);
		
			return mapping.findForward("success");
	    } catch (BusinessResourceException bre) {
	        handleBusinessResourceException(bre, errors, request);
	        return mapping.findForward("error");
	    } catch (ValueObjectMapperException vome) {
	        return mapping.findForward("error");
	    }		
		
	}
}

