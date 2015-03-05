package com.lotoquebec.cardex.servlet;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

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
 * Cette classe est utilisé pour lancer une classe en réflexion.
 * La classe lancé est la classe qui prépare le rapport jasper.
 */
public abstract class RapportAffichage extends HttpServlet {

	protected abstract RapportForm obtenirRapportForm(HttpServletRequest request);
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response, Locale locale)
        throws IOException, ServletException {
        CardexAuthenticationSubject subject = (CardexAuthenticationSubject) request.getSession().getAttribute(AuthenticationSubject.class.getName());
    	RapportForm rapportForm = obtenirRapportForm(request);
    	
        try {
            GenererRapport genererRapport = rapportForm.getGenererRapport();
            RapportVO rapportVO = genererRapport.construireNouveauRapportVO();
            ValueObjectMapper.convert(rapportForm, rapportVO);
            ResourceBundle bundle = ResourceBundle.getBundle("resources.application", locale);
            
	        JasperPrint print = genererRapport.executer(subject, rapportVO, bundle, locale);
	        
	        assignerServletOutput(request, response, print);
	        
  	  	} catch (JRException se) {
			se.printStackTrace();
		} catch (BusinessResourceException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (ValueObjectMapperException e) {
			e.printStackTrace();
		}
	}

    protected void assignerServletOutput(HttpServletRequest request, HttpServletResponse  response, JasperPrint print) throws IOException, JRException{
    	ServletOutputStream servletOutputStream = response.getOutputStream();
    	
        response.setContentType( GlobalConstants.TypeSortieServlet.PDF );
        
        request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, print);	         
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
        exporter.exportReport();
        
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}