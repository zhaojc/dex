package com.lotoquebec.cardexCommun.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.log.InformationAcces;

/**
 * Ce servlet permet de fermer la session de l'utilisateur
 * @author levassc
 *
 */
public class SessionInvalidate extends HttpServlet{

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response) {
    	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
    	
    	if (subject != null)
    		InformationAcces.getInstance().inscrireLogout(subject);
    	
    	request.getSession().invalidate();
    }
}
