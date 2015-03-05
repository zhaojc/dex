package com.lotoquebec.cardexCommun.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;

/**
 * Ce servlet permet de fermer la t�che de l'utilisateur laisser ouvert.
 * @author levassc
 *
 */
public class FermerConnexion extends HttpServlet{

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response) throws IOException, ServletException {
        //R�cup�ration des param�tres
    	int sequence = Integer.valueOf( request.getParameter("SEQUENCE") );
    	GererTacheUtilisateur gererTacheUtilisateur = GererTacheUtilisateur.getInstanceOf();
    	
		try {
			gererTacheUtilisateur.cancelTacheUtilisateur(sequence);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    		
		// Il faut cr�er une response, pour que la servlet fonctionne correctement
		// Sinon, certains appels sont manqu�s
	    response.setContentType("text/xml; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("Vide");
		
		destroy();
    }

}
