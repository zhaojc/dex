package com.lotoquebec.cardex.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lotoquebec.cardex.business.vo.MessageUtilisateurVO;
import com.lotoquebec.cardex.integration.dao.cache.MessageUtilisateurCache;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;

/**
 * Ce servlet sert à afficher les adresses d'un sujet à partir
 * d'un script ajax 
 */
public class AfficherMessageUtilisateur extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {
        CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
        
        // L'usager n'est plus logger
        if (subject == null)  
        	return;
        
        GestionnaireSecuriteCardex.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
        
        String xml = "";
		try {
			xml = "<messages>" + construireListeMessage(subject) + "</messages>";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		}
        
		//On encode la réponse pour supporter les charactères français
	    response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(xml);    
    }
	
	private String construireListeMessage(CardexAuthenticationSubject subject) throws BusinessResourceException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	StringBuffer xml = new StringBuffer("");
    	List listMessageUtilisateur = MessageUtilisateurCache.obtenirListeMessageUtilisateur(subject);     

    	if (listMessageUtilisateur.size() == 0)
    		return xml.toString();
    	
    	Iterator iter = listMessageUtilisateur.iterator();
    	
    	while (iter.hasNext()) {
    		MessageUtilisateurVO messageUtilisateurVO = (MessageUtilisateurVO) iter.next();
    		xml.append("<message niveau='");
    		xml.append(messageUtilisateurVO.getNiveau());
    		xml.append("'>");
    		xml.append(messageUtilisateurVO.getMessage());
    		xml.append("</message>");
		}
    	
    	return xml.toString();
	}


}