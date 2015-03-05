package com.lotoquebec.cardexCommun.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.log.InformationAcces;

/**
 * Cette servlet inscript que le presse papier de l'utilisateur est désactivé
 * @author levassc
 *
 */
public class InscrireLog extends HttpServlet{
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response) {
    	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
    	
		InformationAcces.getInstance().inscrireLogon(subject, request);
    }
}
