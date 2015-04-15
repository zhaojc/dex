package com.lotoquebec.cardex.presentation.rapport.dossier;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.Globals;
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.Resources;

import java.sql.Connection;
import java.sql.SQLException;

import com.lotoQuebec.correcteurAdresse.util.StringUtils;
import com.lotoquebec.cardex.business.CriteresRechercheDossier;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.vo.CriteresRechercheDossierVO;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheDossierForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.CodeLangue;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Production des rapports CDX_0143 et CDX_0144
 * @author guerinf
 * 2012-01-26
 * @Deprecated sera retirer dans la version 5.5
 */
public abstract class RechercheDossierRapport extends CritereGenererRapport {

	protected CardexAuthenticationSubject subject = null;

	// Obtenir le fichier .jasper
	protected abstract InputStream obtenirGabarit();
	
	// Appel des composants concept pour la production du rapport
	protected abstract int produireRapport(CardexAuthenticationSubject subject, CriteresRechercheDossier criteres) throws BusinessException; 
	
	// Construire le contenu du rapport
	protected abstract JRDataSource construireDataSource(CardexAuthenticationSubject subject, CriteresRechercheDossierForm criteres, MessageResources mResources) throws BusinessException;
	
	public JasperPrint executer(CardexAuthenticationSubject subject, HttpServletRequest request) throws BusinessException, JRException {
		InputStream gabarit = obtenirGabarit();
		Map parameterMap = request.getParameterMap();
		this.subject = subject;
		MessageResources mResources = (MessageResources) request.getSession().getAttribute(Globals.MESSAGES_KEY);

        CardexUser user = (CardexUser)subject.getUser();
        Map parameters = new HashMap();
        Connection connection = null;
        JRDataSource dataSource = null;
        JasperPrint print = new JasperPrint();
        try{
 		    CriteresRechercheDossierForm criteresRechercheDossierForm = (CriteresRechercheDossierForm) request.getSession().getAttribute("rechercheDossier");
 		    CriteresRechercheDossierVO criteresRechercheDossier = new CriteresRechercheDossierVO();
			ValueObjectMapper.convertCriteresRechercheDossierHtmlForm(criteresRechercheDossierForm, criteresRechercheDossier,subject.getLocale());
			int nombreDossiers = 0;
 		    nombreDossiers = produireRapport(subject, criteresRechercheDossier);
 		    criteresRechercheDossierForm.setNombreDossiers(String.valueOf(nombreDossiers));
	       	dataSource = construireDataSource(subject, criteresRechercheDossierForm, mResources);
	        ServletContext context = request.getSession().getServletContext();  
	        connection = DAOConnection.getInstance().getConnection();
	        parameters.put("SUBREPORT_DIR",context.getRealPath("/rapports/"));
	        parameters.put("REPORT_CONNECTION",connection);
	        parameters.put("UTILISATEUR",subject.getPrincipal().getName());
	        print =  JasperFillManager.fillReport(gabarit, parameters, dataSource);

	    } catch (DAOException se) {
			 se.printStackTrace();
        } catch (ValueObjectMapperException vome) {
			 vome.printStackTrace();
        }
	        finally {
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
	     return print;
	}
	
}
