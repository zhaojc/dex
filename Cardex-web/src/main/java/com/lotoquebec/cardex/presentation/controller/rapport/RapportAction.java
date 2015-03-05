/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.presentation.controller.rapport;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.presentation.util.AbstractAction;


public class RapportAction extends AbstractAction {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

   
    public ActionForward defaut(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
        log.fine("Entré dans un choix de rapport");
        
        if (form != null){
	        CriteresRapportForm rapportForm = (CriteresRapportForm) form;
	        rapportForm.init(subject);
        }
        return mapping.findForward("success");
    }

    public ActionForward imprimer(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
		log.fine("Imprimer");
		CriteresRapportForm rapportForm = (CriteresRapportForm) form;
		rapportForm.setLancerRapport(true);
		rapportForm.setTypeSortieServlet(GlobalConstants.TypeSortieServlet.PDF);
		
		return mapping.findForward("success");
	}
    
    public ActionForward imprimerExcel(CardexAuthenticationSubject subject,
    ActionMapping mapping,
    ActionForm form,
    HttpServletRequest request,
    HttpServletResponse response) throws IOException,
    ServletException {
		log.fine("Imprimer Excel");
		CriteresRapportForm rapportForm = (CriteresRapportForm) form;
		rapportForm.setLancerRapport(true);
		rapportForm.setTypeSortieServlet(GlobalConstants.TypeSortieServlet.EXCEL);
		
		return mapping.findForward("success");
	}    
    
}

