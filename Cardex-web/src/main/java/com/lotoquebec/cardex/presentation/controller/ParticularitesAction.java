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

import com.lotoquebec.cardex.business.Particularites;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.delegate.VehiculeBusinessDelegate;
import com.lotoquebec.cardex.business.vo.VehiculeVO;
import com.lotoquebec.cardex.presentation.model.form.ParticularitesForm;
import com.lotoquebec.cardex.presentation.model.form.VehiculeForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;

/**
 * Cette classe g�re les �v�nements en rapport
 * avec le cas d'utilisation de l'onglet particularites dans V�hicule.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.2 $, $Date: 2002/04/11 18:49:10 $
 */
public class ParticularitesAction extends AbstractAction {

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
        log.debug("Acc�s aux Particularites");

        ActionMessages errors = new ActionMessages();

        try {
            VehiculeBusinessDelegate delegate = new VehiculeBusinessDelegate();
            VehiculeForm vehiculeForm = (VehiculeForm)form;
            Vehicule vehicule = new VehiculeVO();
            ParticularitesForm particularitesForm = new ParticularitesForm();
            ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm, vehicule,
                    subject.getLocale());
            Particularites particularites = delegate.findLiensParticularite(subject,vehicule);
            ValueObjectMapper.convertParticularites(subject, particularites, particularitesForm,subject.getLocale());
            particularitesForm.setCle(vehiculeForm.getCle());
            particularitesForm.setLien(vehiculeForm.getCle());
            particularitesForm.setLienSite(vehiculeForm.getSite());
            particularitesForm.setImmatriculation(vehiculeForm.getImmatriculation());
            particularitesForm.setMarque(vehiculeForm.getMarque());
            particularitesForm.setModele(vehiculeForm.getModele());
            request.getSession().setAttribute("particularites",particularitesForm);
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

