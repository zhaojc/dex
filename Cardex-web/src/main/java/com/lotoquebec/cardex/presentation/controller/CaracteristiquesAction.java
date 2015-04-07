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

import com.lotoquebec.cardex.business.Caracteristiques;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.presentation.model.form.CaracteristiquesForm;
import com.lotoquebec.cardex.presentation.model.form.SujetForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;

/**
 * Cette classe g�re les �v�nements en rapport
 * avec le cas d'utilisation onglets caracteristiques.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.3 $, $Date: 2002/03/12 21:41:20 $
 */
public class CaracteristiquesAction extends AbstractAction {

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
        log.debug("Acc�s aux caracteristiques");

        ActionMessages errors = new ActionMessages();

        try {
            SujetBusinessDelegate delegate = new SujetBusinessDelegate();
            SujetForm sujetForm = (SujetForm)form;
            Sujet sujet = new SujetVO();
            CaracteristiquesForm caracteristiquesForm = new CaracteristiquesForm();
            ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,
                    subject.getLocale());
            Caracteristiques caracteristiques = delegate.findLiensCaracteristique(subject,sujet);
            ValueObjectMapper.convertCaracteristiques(subject, caracteristiques, caracteristiquesForm,subject.getLocale());
            caracteristiquesForm.setLien(sujetForm.getCle());
            caracteristiquesForm.setLienSite(sujetForm.getSite());
            caracteristiquesForm.setNom(sujetForm.getNom());
            caracteristiquesForm.setPrenom(sujetForm.getPrenom());
            caracteristiquesForm.setNumeroFiche(sujetForm.getNumeroFiche());
            request.getSession().setAttribute("caracteristiques",caracteristiquesForm);
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

