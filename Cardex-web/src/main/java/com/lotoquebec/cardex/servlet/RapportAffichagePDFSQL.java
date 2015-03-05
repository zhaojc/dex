package com.lotoquebec.cardex.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import com.lotoquebec.cardex.generateurRapport.sql.GenererRapportSQL;
import com.lotoquebec.cardex.presentation.rapport.RapportAssociation;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Cette classe est utilisé pour lancer une classe en réflexion.
 * La classe lancé est la classe qui prépare le rapport jasper.
 * @deprecated utiliser rapport affichagePDF a retirer dans v5.5
 */
public class RapportAffichagePDFSQL extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {
        ServletOutputStream servletOutputStream = response.getOutputStream();
        CardexAuthenticationSubject subject = (CardexAuthenticationSubject) request.getSession().getAttribute(AuthenticationSubject.class.getName());
        Connection connection = null;
        
    	GestionnaireSecurite.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
    	String choixRapport = (String)request.getParameter("choixRapport");
    	String tableValeurRapport = (String)request.getParameter("tableValeurRapport");
    	String roleAdhoc = RapportAssociation.getRoleAdhoc(choixRapport);
        
        try { 
        /*	connection = DAOConnection.getInstance().getConnection();
        	GenererRapportSQL genererRapportSQL = null;
        	
        	if (StringUtils.isEmpty(roleAdhoc)){
        		GestionnaireSecuriteCardex.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, tableValeurRapport, "", GlobalConstants.ActionSecurite.TOUTES_ACTIONS), choixRapport);
        		//genererRapportSQL = RapportAssociation.obtenirGenererRapportSQL(Integer.valueOf(choixRapport));
           	}else{
           		GestionnaireSecuriteCardex.validerSecuriteAdhoc(subject, roleAdhoc);
            	Class c = Class.forName( choixRapport );
            	genererRapportSQL = (GenererRapportSQL) c.newInstance();
           	}              	
        	
	        //JasperPrint print = genererRapportSQL.executer(subject, request);
        	JasperPrint print = null;
		    //Affichage à l'écran
	        response.setContentType("application/pdf");
	        request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, print);	         
	        JRExporter exporter = new JRPdfExporter();
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
	        exporter.exportReport();
	        servletOutputStream.flush();
	        servletOutputStream.close();
	        
  	  	} catch (JRException se) {
			se.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();*/
		} finally {
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			            connection.rollback();
	           	     connection.close();
                } catch (SQLException e) {
                	e.printStackTrace();
                }
 		    }
        }
	}

}