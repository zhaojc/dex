package com.lotoquebec.cardex.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardex.util.RapportUtils;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Ce servlet sert � afficher les rapports Jasper en format PDF � l'�cran.
 * Il sert pour les dossiers.
 * Le code repose sur le concept AJAX (Asynchronous JavaScript and XML) et sur les librairies Jasper.
 * @date : novembre 2008
 */
public class AffichageRapportActivites extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {
        Connection connection = null;
        
        try {
            connection = DAOConnection.getInstance().getConnection();
        	String dateDebutString = (String)request.getParameter("DATE_DEBUT");
        	String dateFinString = (String)request.getParameter("DATE_FIN");
        	String siteString = (String)request.getParameter("SITE");
        	long site = 0;
        	
        	if (StringUtils.isNotEmpty(siteString))
        		site = Long.valueOf( siteString );
        	Date debutDate = DateFormat.parse(dateDebutString);
        	Date finDate = DateFormat.parse(dateFinString);

            //Si les dates n'ont pas �t� s�lectionn�es, on met par d�faut la date de la veille � 6 h 
            //jusqu'� la date du jour � 6 h.
        	if (debutDate == null)
        		debutDate = RapportUtils.dateHier7h(DateFormat.parse(dateDebutString));
        	
        	if (finDate == null)
        		finDate = RapportUtils.dateAujourdHuiFin6h59(DateFormat.parse(dateFinString));
        	
           	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
           	GestionnaireSecuriteCardex.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
           	
            RapportBusinessDelegate delegate = new RapportBusinessDelegate();
            JasperPrint print = delegate.siteRAQCDX_0070(subject, debutDate, finDate, site);

			ServletOutputStream servletOutputStream = response.getOutputStream();
			response.setContentType("application/pdf");
			request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, print);	         
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
			exporter.exportReport();
			servletOutputStream.flush();
			servletOutputStream.close();

  	  } catch (DAOException se) {
  		  se.printStackTrace();
      } catch (BusinessResourceException bre) {
          bre.printStackTrace();
      } catch (JRException se) {
	    	se.printStackTrace();
      } catch (ParseException e) {
			e.printStackTrace();
      }finally {
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit())
			         {
			            connection.rollback();
			         }
 		           	   connection.close();
                } catch (SQLException e) {
                	e.printStackTrace();
                }
 		    }
        }
	}
    
	private String dateFin5h_59(Date dateFin){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFin);
		calendar.set(Calendar.HOUR_OF_DAY, 5);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		SimpleDateFormat simpleDateFormat = TimestampFormat.getFormatter(true);
		return simpleDateFormat.format(calendar.getTime());
	}

	private String dateDebut6h(Date dateDebut){
		SimpleDateFormat simpleDateFormat = TimestampFormat.getFormatter(true);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateDebut);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 6);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return simpleDateFormat.format(calendar.getTime());
	}

}