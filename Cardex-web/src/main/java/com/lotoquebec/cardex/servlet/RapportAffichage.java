package com.lotoquebec.cardex.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

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
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.presentation.model.form.rapport.RapportForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.util.ValueObjectMapper;

/**
 * Cette classe est utilis� pour lancer une classe en r�flexion.
 * La classe lanc� est la classe qui pr�pare le rapport jasper.
 */
public abstract class RapportAffichage extends HttpServlet {

	private static final long serialVersionUID = -8083681278153808743L;

	protected abstract RapportForm obtenirRapportForm(HttpServletRequest request);
	//private static final Object MUTEX = new Object();
	
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response, Locale locale)
        throws IOException, ServletException {
        CardexAuthenticationSubject subject = (CardexAuthenticationSubject) request.getSession().getAttribute(AuthenticationSubject.class.getName());
        
        ServletOutputStream servletOutputStream = response.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	RapportForm rapportForm = obtenirRapportForm(request);
    	
        try {
            GenererRapport genererRapport = rapportForm.getGenererRapport();
            RapportVO rapportVO = genererRapport.construireNouveauRapportVO();
            ValueObjectMapper.convert(rapportForm, rapportVO);
            ResourceBundle bundle = ResourceBundle.getBundle("resources.application", locale);
            
			JasperPrint print = genererRapport.executer(subject, rapportVO, bundle, locale);
			
			/*
	        // Récupère le nom de l'attribut de session

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
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
		} finally {
			baos.flush();
			baos.close();
	        servletOutputStream.flush();
	        servletOutputStream.close();
		}
	}


	protected void setContentType(HttpServletResponse response) {
		response.setContentType(GlobalConstants.TypeSortieServlet.PDF);
	}
	protected JRAbstractExporter obtenirJRExporter() {
		return new JRPdfExporter();
	}


}