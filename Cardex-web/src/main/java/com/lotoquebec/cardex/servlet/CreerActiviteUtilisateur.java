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
 * Ce servlet créer de l'activité chez utilisateur dans le cas "keyPress"
 * dans les narrations.  Ce qui ne génère pas d'activité "requête".
 */
public class CreerActiviteUtilisateur extends HttpServlet {

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
        
        subject.assignerActivite();
        
        destroy();
    }
	
}