package com.lotoquebec.cardex.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.CriteresRechercheDossierVO;
import com.lotoquebec.cardex.presentation.rapport.RapportAssociation;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Ce servlet sert à afficher les rapports Jasper en format PDF à l'écran.
 * Il sert pour les rapports sur les suivis.
 * Le code repose sur le concept AJAX (Asynchronous JavaScript and XML) et sur les librairies Jasper.
 * @date : novembre 2008
 */
public class AffichagePDFSuivis extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {

        CriteresRechercheDossierVO criteresRechercheDossier = new CriteresRechercheDossierVO();
        ResultSet resultSet = null;
        Map parameters = new HashMap();
        Connection connection = null;
        ServletOutputStream servletOutputStream = response.getOutputStream();
		Statement commandeSql = null;
     	String procedure = "";
        JasperPrint print = new JasperPrint();
        try {
            connection = DAOConnection.getInstance().getConnection();
            //Lecture des paramètres du rapport.
        	String choixRapport = (String)request.getParameter("RAPPORT");
        	String site = (String)request.getParameter("SITE");
        	String dateDebut = (String)request.getParameter("DATE_DEBUT");
        	String dateFin = (String)request.getParameter("DATE_FIN");
        	String intervenant = (String)request.getParameter("UTILISATEUR");
           	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
			
           	GestionnaireSecuriteCardex.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
           	GestionnaireSecuriteCardex.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.RAPPORT_RECHERCHE_SUIVI, "", GlobalConstants.ActionSecurite.TOUTES_ACTIONS), choixRapport);
           	choixRapport = RapportAssociation.obtenirFichierJasper(Integer.valueOf(choixRapport));
			
           	String utilisateur = subject.getPrincipal().getName();
			//On s'assure qu'il y a des dates pour les rapports POL. Sinon, on prend la dernière semaine.
            String DATE_FORMAT_NOW = "yyyy-MM-dd";
            Calendar dateDebutCal = Calendar.getInstance();
            Calendar dateFinCal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
            dateDebutCal.add(Calendar.DATE,-7);
			if(StringUtils.isEmpty(dateDebut)){
				dateDebut = sdf.format(dateDebutCal.getTime());
			}
			if(StringUtils.isEmpty(dateFin)){
				dateFin = sdf.format(dateFinCal.getTime());
			}
			criteresRechercheDossier.setSiteOrigine(Long.parseLong(site));
			criteresRechercheDossier.setDateDebutDu(TimestampFormat.parse(dateDebut, new Locale("fr"), true));
			criteresRechercheDossier.setDateDebutAu(TimestampFormat.parse(dateFin, new Locale("fr"), true));
			criteresRechercheDossier.setIntervenant(intervenant);
         	RapportBusinessDelegate delegate = new RapportBusinessDelegate();
	         ServletContext context = request.getSession().getServletContext();  
	         parameters.put("SUBREPORT_DIR",context.getRealPath("/rapports/"));
	         parameters.put("REPORT_CONNECTION",connection);
	         parameters.put("UTILISATEUR",utilisateur);
	         parameters.put("DATE_DEBUT",dateDebut);
	         parameters.put("DATE_FIN",dateFin);
	         //parameters.put("SITE",site);

	         /*if (GlobalConstants.ChoixRapport.SUIVIS_30_JOURS.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.SUIVIS_30_JOURS;
               	procedure = "CARDEX_RAPPORT.SP_RAP_SUIVI_30_JOURS";
               	resultSet = delegate.rapportSuivis(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
        	}
        	if (GlobalConstants.ChoixRapport.SUIVIS_30_JOURS_INTERVENANT.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.SUIVIS_30_JOURS_INTERVENANT;
               	procedure = "CARDEX_RAPPORT.SP_RAP_30_JOURS_INTERVENANT";
               	resultSet = delegate.rapportSuivis(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
        	}
        	if (GlobalConstants.ChoixRapport.SUIVIS_21_JOURS.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.SUIVIS_21_JOURS;
               	procedure = "CARDEX_RAPPORT.SP_RAP_SUIVI_21_JOURS";
               	resultSet = delegate.rapportSuivis(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
        	}
        	if (GlobalConstants.ChoixRapport.SUIVIS_24_HEURES.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.SUIVIS_24_HEURES;
               	procedure = "CARDEX_RAPPORT.SP_RAP_SUIVI_24_HEURES";
               	resultSet = delegate.rapportSuivis(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
        	}
        	if (GlobalConstants.ChoixRapport.SUIVIS_INVESTIGATION.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.SUIVIS_INVESTIGATION;
               	procedure = "CARDEX_RAPPORT.SP_RAP_SUIVI_INVESTIGATION";
               	resultSet = delegate.rapportSuivisIntervenant(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
        	}
        	if (GlobalConstants.ChoixRapport.SUIVIS_RETARDS.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.SUIVIS_RETARDS;
               	procedure = "CARDEX_RAPPORT.SP_RAP_SUIVI_RETARDS";
               	resultSet = delegate.rapportSuivisIntervenant(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
        	}*/
		    //			
	         //Affichage à l'écran
	         response.setContentType("application/pdf");
	         request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, print);	         
	         JRExporter exporter = new JRPdfExporter();
	         exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
	         exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
	         exporter.exportReport();
	         servletOutputStream.flush();
	         servletOutputStream.close();

  	  } catch (DAOException se) {
			//System.out.println(se.getMessage());
			se.printStackTrace();
      } catch (ParseException pe) {
          pe.printStackTrace();
      }/* catch (BusinessResourceException bre) {
          bre.printStackTrace();
      } catch (BusinessException be) {
          be.printStackTrace();
	    }*/catch (JRException se) {
	    	se.printStackTrace();
        }
        finally {
			if(commandeSql != null) {
				try {
						commandeSql.close();
			    } catch (java.sql.SQLException e) {
			    	e.printStackTrace();
		        }
			}
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
 			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
			    	e.printStackTrace();
		        }
	        }
        }
	}

  private JasperPrint traitementResultSet(ResultSet resultSet, Map parameters, String choixRapport)
  	throws JRException{
   	JasperPrint print = new JasperPrint();

	  try{
		InputStream gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
		JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
		print = JasperFillManager.fillReport(gabarit, parameters, resultSetDataSource);
	    }catch (JRException se) {
	    	se.printStackTrace();
        }
     	return print;
  }

}