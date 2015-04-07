/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Partage;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.PartageForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;
import com.lotoquebec.cardexCommun.user.CardexUser;

/**
 * Cette classe g�re les �v�nements en rapport
 * avec le cas d'utilisation onglets Partage.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/03/13 22:43:44 $
 */
public class PartageAction extends AbstractAction {

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
    public ActionForward show(CardexAuthenticationSubject subject,
                              ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws IOException,
                              ServletException {
        log.debug("Acc�s au Partage");

        ActionMessages errors = new ActionMessages();
        CardexUser user = (CardexUser)subject.getUser();
        try {
            DossierBusinessDelegate delegate = new DossierBusinessDelegate();
            DossierForm dossierForm = (DossierForm)form;
            Dossier dossier = new DossierVO();
            PartageForm partageForm = new PartageForm();
            partageForm.init(subject, dossierForm.getSite());
            ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,
                    subject.getLocale());
            Partage partage = delegate.ouvrirPartage(subject,dossier);
            ValueObjectMapper.convertPartage(partage, partageForm,subject.getLocale());
            partageForm.setLien(dossierForm.getCle());
            partageForm.setLienSite(dossierForm.getSite());
            partageForm.setIntervenant(user.getCode());
            partageForm.setNumeroDossier(dossierForm.getStringNumeroCardex());
            partageForm.setType(dossierForm.getType());
            partageForm.setCategorie(dossierForm.getCategorie());
            partageForm.setEntite(String.valueOf(user.getEntite()));
            partageForm.setSiteOrigine(String.valueOf(user.getSite()));
            request.getSession().setAttribute("partage",partageForm);
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
     * Rafraichir les listes d�roulantes lors d'un changement de valeur.
     * @param subject
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws ValueObjectMapperException 
     * @throws BusinessResourceException 
     */
    public ActionForward rafraichirPartage(CardexAuthenticationSubject subject,
    ActionMapping mapping, ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException, BusinessResourceException, ValueObjectMapperException {
		log.debug("Rafraichir les intervenants (partage)");
		
		PartageForm partageForm = (PartageForm)form;
		partageForm.assignerIntervenantsActifParSite(subject, partageForm.getSiteOrigine());
		
		return mapping.findForward("success");
		
	}
    
}

