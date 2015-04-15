package com.lotoquebec.cardex.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import com.lotoquebec.cardex.presentation.model.form.rapport.RapportForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Cette classe est utilisé pour lancer une classe en réflexion.
 * La classe lancé est la classe qui prépare le rapport jasper.
 */
public class RapportAffichage extends HttpServlet {

	private static final long serialVersionUID = -8083681278153808743L;

	//private static final Object MUTEX = new Object();

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	CardexAuthenticationSubject subject = (CardexAuthenticationSubject) request.getSession().getAttribute(AuthenticationSubject.class.getName());
    	String langue = (String)request.getParameter("langue");
    	Locale locale = subject.getLocale();
    	
        if (StringUtils.isNotEmpty(langue))
        	locale = OracleDAOUtils.getLocale(Integer.valueOf(langue));
    	
        ServletOutputStream servletOutputStream = response.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	RapportForm rapportForm = obtenirRapportForm(request);
    	
        try {
        	/*
            GenererRapport genererRapport = rapportForm.getGenererRapport();
            RapportVO rapportVO = rapportForm.obtenirRapportVO();
            ResourceBundle bundle = ResourceBundle.getBundle("resources.application", locale);
            
			JasperPrint print = genererRapport.executer(subject, rapportVO, bundle, locale);
			*/
			JasperPrint print = rapportForm.genererRapport(subject, request, locale);
			
			/*
	        // On veut éviter toute concurrence lors de l'écriture de la réponse
	        synchronized (RapportAffichage.MUTEX) {
	        	String userAgent = request.getHeader("User-Agent");
				if(userAgent.indexOf("Trident/7")>-1) {
					response.reset();
			        response.setHeader("Content-disposition","inline; filename=test.pdf");
			        response.setHeader("Cache-Control", "no-cache");
			        response.setDateHeader("Expires", 0);
			        response.setHeader("Pragma", "No-cache");
				} else {
					response.setHeader("Pragma", "public"); 
				}
	        }			
			*/
			setContentType(response);
	        response.setHeader("Cache-Control", "max-age=0");
			JasperExportManager.exportReportToPdfStream(print, baos);
			JRAbstractExporter exporter = obtenirJRExporter();
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
			exporter.exportReport();
	        servletOutputStream.write(baos.toByteArray());
	        //FacesContext.getCurrentInstance().responseComplete();
	        
  	  	} catch (JRException se) {
			se.printStackTrace();
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		} finally {
			baos.flush();
			baos.close();
	        servletOutputStream.flush();
	        servletOutputStream.close();
		}
	}

	protected RapportForm obtenirRapportForm(HttpServletRequest request){
        String rapportFormStr = (String)request.getParameter("rapportForm");
        
        if (StringUtils.isNotEmpty(rapportFormStr)){
        	return (RapportForm) request.getSession().getAttribute(rapportFormStr);
        }
        
        if (StringUtils.isEmpty(rapportFormStr)){
        	String classRapportForm = (String)request.getParameter("classRapportForm");
        	
        	if (StringUtils.isNotEmpty(classRapportForm)){
	        	try {
					return (RapportForm) Class.forName(classRapportForm).newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        }
        throw new RuntimeException("Pas d'instance de rapport form");
	}

	protected void setContentType(HttpServletResponse response) {
		response.setContentType(GlobalConstants.TypeSortieServlet.PDF);
	}
	protected JRAbstractExporter obtenirJRExporter() {
		return new JRPdfExporter();
	}


}