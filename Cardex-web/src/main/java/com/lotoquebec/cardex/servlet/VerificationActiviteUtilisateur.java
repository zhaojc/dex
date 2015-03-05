package com.lotoquebec.cardex.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * Ce servlet retourne si l'utilisateur a généré de l'activite.
 * Sinon, il faut fermer le Cardexé
 */
public class VerificationActiviteUtilisateur extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {
        CardexAuthenticationSubject subject = (CardexAuthenticationSubject)(new AutentificationCardex()).obtenirSubjet(request);
        
        // L'usager n'est plus logger
        if (subject == null)  
        	return;
        
        GestionnaireSecuriteCardex.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
        
        StringBuilder xml = new StringBuilder();
        xml.append(subject.isActif(480));
        
		//On encode la réponse pour supporter les charactères français
	    response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(xml.toString());
        
        destroy();
    }
	
}