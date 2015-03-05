package com.lotoquebec.cardexCommun.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lotoquebec.cardexCommun.GlobalConstants;

/**
 * Ce servlet permet de fermer la liste cache d'autocompleter
 * @author levassc
 *
 */
public class FermerListeCacheString extends HttpServlet{

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response) throws IOException, ServletException {
    	request.getSession().removeAttribute( GlobalConstants.AutoCompleterClass.LISTE_CACHE_STRING );
		
		destroy();
    }

}
