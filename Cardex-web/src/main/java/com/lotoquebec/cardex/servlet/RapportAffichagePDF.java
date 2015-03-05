package com.lotoquebec.cardex.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * Cette classe est utilisé pour lancer une classe en réflexion.
 * La classe lancé est la classe qui prépare le rapport jasper.
 */
public class RapportAffichagePDF extends RapportAffichage {

	protected CriteresRapportForm obtenirRapportForm(HttpServletRequest request){
        String rapportFormStr = (String)request.getParameter("rapportForm");
        CriteresRapportForm criteresRapportForm = (CriteresRapportForm) request.getSession().getAttribute(rapportFormStr);
        criteresRapportForm.initDateDebut();
        criteresRapportForm.setLancerRapport(false);
    	return criteresRapportForm;

	}
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {
    	CardexAuthenticationSubject subject = (CardexAuthenticationSubject) request.getSession().getAttribute(AuthenticationSubject.class.getName());
    	super.doGet(request, response, subject.getLocale());
	}

}