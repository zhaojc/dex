package com.lotoquebec.cardex.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lotoquebec.cardexCommun.util.JourneeOuvrable;

/**
 * Ce servlet sert à retourner la date ouvrable qui est ajouté à la date
 * du jour
 */
public class CalculerDateOuvrableServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse  response) throws IOException, ServletException {
	    String sJoursAjoute = (String)request.getParameter("JoursAjoute");
	    
	    Date dateOuvrable = JourneeOuvrable.ajouterJours( Integer.valueOf( sJoursAjoute ) );

	    response.setContentType("text/xml; charset=UTF-8");
	    response.setHeader("Cache-Control", "no-cache");
	    response.getWriter().write( DateFormat.getDateTimeInstance().format(dateOuvrable) );    
	}    

}