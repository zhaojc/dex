/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller.rapport;

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

import com.lotoquebec.cardex.business.delegate.RegroupementBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.RegroupementRapportVO;
import com.lotoquebec.cardex.presentation.model.form.rapport.regroupement.RegroupementRapportForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;

/**
 * Cette classe g�re les �v�nements en rapport
 * avec le cas d'utilisation gestion des journaux.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.27 $, $Date: 2002/04/30 17:47:53 $
 */
public class RegroupementAction extends RapportAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

    public ActionForward imprimer(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
		log.debug("Imprimer");
		ActionMessages errors = new ActionMessages();
		
		RegroupementRapportForm criteresRechercheRegroupementForm = (RegroupementRapportForm) form;
        RegroupementRapportVO criteresRechercheRegroupement = (RegroupementRapportVO) criteresRechercheRegroupementForm.getGenererRapport().construireNouveauRapportVO();

        try {
            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
			ValueObjectMapper.convert(criteresRechercheRegroupementForm,criteresRechercheRegroupement);
			
			RegroupementBusinessDelegate delegate = new RegroupementBusinessDelegate();
			delegate.validerRapport(subject, criteresRechercheRegroupement);
			
			super.imprimer(subject, mapping, criteresRechercheRegroupementForm, request, response);
			
		} catch (ValueObjectMapperException e) {
			 handleValueObjectMapperException(e, errors, request);
		} catch (BusinessResourceException e) {
			handleBusinessResourceException(e, errors, request);
		} catch (BusinessException e) {
			handleBusinessException(e, errors, request);
		}
        
		
		return mapping.findForward("success");
	}
    
    public ActionForward imprimerExcel(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
		log.debug("imprimerExcel");
		ActionMessages errors = new ActionMessages();
		
		RegroupementRapportForm criteresRechercheRegroupementForm = (RegroupementRapportForm) form;
		RegroupementRapportVO criteresRechercheRegroupement = (RegroupementRapportVO) criteresRechercheRegroupementForm.getGenererRapport().construireNouveauRapportVO();

        try {
            // Conversion du composant d'�tat(ActionForm) en composant d'affaire(Value Object)
			ValueObjectMapper.convert(criteresRechercheRegroupementForm,criteresRechercheRegroupement);
			
			RegroupementBusinessDelegate delegate = new RegroupementBusinessDelegate();
			delegate.validerRapport(subject, criteresRechercheRegroupement);
			
			super.imprimerExcel(subject, mapping, criteresRechercheRegroupementForm, request, response);
			
		} catch (ValueObjectMapperException e) {
			 handleValueObjectMapperException(e, errors, request);
		} catch (BusinessResourceException e) {
			handleBusinessResourceException(e, errors, request);
		} catch (BusinessException e) {
			handleBusinessException(e, errors, request);
		}
        
		
		return mapping.findForward("success");
	}
    	    

}


