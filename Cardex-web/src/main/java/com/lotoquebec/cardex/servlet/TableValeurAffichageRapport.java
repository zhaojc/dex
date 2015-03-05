package com.lotoquebec.cardex.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.RequestUtils;

import com.lotoquebec.cardex.presentation.model.form.rapport.RapportForm;
import com.lotoquebec.cardex.presentation.model.form.rapport.TableValeurRapportAssociation;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Cette classe est utilisé pour lancer une classe en réflexion.
 * La classe lancé est la classe qui prépare le rapport jasper.
 */
public class TableValeurAffichageRapport extends RapportAffichage {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {
    	CardexAuthenticationSubject subject = (CardexAuthenticationSubject) request.getSession().getAttribute(AuthenticationSubject.class.getName());
    	String langue = (String)request.getParameter("langue");
    	Locale locale = subject.getLocale();
    	
        if (StringUtils.isNotEmpty(langue))
        	locale = OracleDAOUtils.getLocale(Integer.valueOf(langue));
    	
    	super.doGet(request, response, locale);
    }

    /**
     * Cette classe permet d'obtenir la classe de rapport et de la populer avec les arguements
     * @param request
     * @return
     */
    protected RapportForm obtenirRapportForm(HttpServletRequest request){
        String choixRapport = (String)request.getParameter("choixRapport");
        
		try {
			RapportForm rapportForm = TableValeurRapportAssociation.obtenirEntiteRapport(Integer.valueOf(choixRapport));
			RequestUtils.populate(rapportForm, request);
			return rapportForm;
			
		} catch (Exception e) {
			throw new RuntimeException("Impossible de créer la classe "+choixRapport);
		}
    }
    
}