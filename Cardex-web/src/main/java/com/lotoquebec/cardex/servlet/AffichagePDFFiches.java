package com.lotoquebec.cardex.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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

import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.PhotoBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.vo.FichierMultimediaVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;

/**
 * Ce servlet sert � afficher les rapports Jasper en format PDF � l'�cran.
 * Il sert pour l'impression des fiches Sujet, Soci�t� et Dossier.
 * Le code repose sur le concept AJAX (Asynchronous JavaScript and XML) et sur les librairies Jasper.
 * @date : novembre 2008
 */
public class AffichagePDFFiches extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {

        ResultSet resultSet = null;
        Map parameters = new HashMap();
        Connection connection = null;
        ServletOutputStream servletOutputStream = response.getOutputStream();
        
        try {
            connection = DAOConnection.getInstance().getConnection();
        	String choixRapport = (String)request.getParameter("FICHE");
        	String site = (String)request.getParameter("SITE");
        	String cle = (String)request.getParameter("CLE");
        	String rapport = (String)request.getParameter("RAPPORT");
        	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
           	GestionnaireSecuriteCardex.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
        	
        	//String utilisateur = (String)request.getParameter("UTILISATEUR");
        	String utilisateur = subject.getPrincipal().getName();
        	String procedure = "";
            RapportBusinessDelegate delegate = new RapportBusinessDelegate();
        	/*if (GlobalConstants.GenreFichier.SUJET.equals( choixRapport )){
               	//choixRapport = "rapports/" + GlobalConstants.ChoixRapport.IMPRESSION_SUJET;
               	procedure = "CARDEX_RAPPORT.SP_IMPRESSION_SUJET";
            }*/
        	if (GlobalConstants.GenreFichier.SOCIETE.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.IMPRESSION_SOCIETE;
               	procedure = "CARDEX_RAPPORT.SP_IMPRESSION_SOCIETE";
            }
        	/*if (GlobalConstants.GenreFichier.GALERIE.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.IMPRESSION_GALERIE;
               	procedure = "CARDEX_RAPPORT.SP_IMPRESSION_SUJET";
	   	         //On ajoute la photo pour les rapports qui en ont besoin
	   	         Sujet sujet = new SujetVO(); 
	   	         sujet.setCle(Long.parseLong(cle));
	   	         sujet.setSite(Long.parseLong(site));
	   	         InputStream photo = recherchePhoto(subject, sujet);
	   	         parameters.put("PHOTO",photo);
            }*/
        	if (GlobalConstants.GenreFichier.INSCRIPTION.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.IMPRESSION_INSCRIPTION;
               	procedure = "CARDEX_RAPPORT.SP_IMPRESSION_INSCRIPTION";
               	parameters.put("CLE",cle);
               	parameters.put("SITE",site);
            }
        	if (GlobalConstants.GenreFichier.SUIVI.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.IMPRESSION_SUIVI;
               	procedure = "CARDEX_RAPPORT.SP_IMPRESSION_SUIVI";
               	parameters.put("CLE",cle);
               	parameters.put("SITE",site);
            }
        	//Impression du dossier de vigilance
        	/*if (GlobalConstants.ChoixImpressionDossier.DOSSIER_VIGILANCE_SOMMAIRE.equals( rapport )){
        		GestionnaireSecurite.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.RAPPORT_DOSSIER, GlobalConstants.ActionSecurite.CONSULTER_DOSSIER), GlobalConstants.ChoixImpressionDossier.DOSSIER_VIGILANCE_SOMMAIRE);
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.IMPRESSION_SOMMAIRE_DOSSIER_VIGILANCE;
               	procedure = "CARDEX_RAPPORT.SP_RAP_DO_VIGILANCE_SOMMAIRE";
               	parameters.put("CLE",cle);
               	parameters.put("SITE",site);
            }

        	if (GlobalConstants.ChoixImpressionDossier.DOSSIER_VIGILANCE_DETAILLE.equals( rapport )){
        		GestionnaireSecurite.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.RAPPORT_DOSSIER, GlobalConstants.ActionSecurite.CONSULTER_DOSSIER), GlobalConstants.ChoixImpressionDossier.DOSSIER_VIGILANCE_DETAILLE);
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.IMPRESSION_DETAILLE_DOSSIER_VIGILANCE;
               	procedure = "CARDEX_RAPPORT.SP_RAP_DO_VIGILANCE_DETAILLE";
               	parameters.put("CLE",cle);
               	parameters.put("SITE",site);
            }*/

        	InputStream gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
			//Utilisation d'un resultSet comme source de donn�es
	         resultSet = delegate.rapportImpressionFiche(Long.parseLong(cle), Long.parseLong(site), procedure,connection);
	         JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
	         ServletContext context = request.getSession().getServletContext();  
	         CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();
	         
	         //System.out.println(context.getRealPath("/rapports/"));
	         parameters.put("SUBREPORT_DIR",context.getRealPath("/rapports/"));
	         parameters.put("REPORT_CONNECTION",connection);
	         parameters.put("UTILISATEUR",utilisateur.toString());
	         parameters.put("CONFIDENTIALITE", Long.toString(privilege.getNiveauConfidentialite()));
	         JasperPrint print = JasperFillManager.fillReport(gabarit, parameters, resultSetDataSource);
	         
		    //Affichage � l'�cran
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
      } catch (BusinessResourceException bre) {
          bre.printStackTrace();
      } catch (BusinessException be) {
          be.printStackTrace();
	    }catch (JRException se) {
	    	se.printStackTrace();
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
 			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
			    	e.printStackTrace();
		        }
	        }
        }
	}

	private InputStream recherchePhoto(CardexAuthenticationSubject subject, Sujet sujet)
	throws BusinessException, DAOException{
	InputStream photo = null;
	SujetBusinessDelegate delegateSujet = new SujetBusinessDelegate();
		Collection liensPhoto = delegateSujet.findLiensPhoto(subject, sujet);
		Iterator it = liensPhoto.iterator();
		PhotoBusinessDelegate photoBusinessDelegate = new PhotoBusinessDelegate();
		while (it.hasNext()) {
		    Photo     linkPhoto = (Photo) it.next();
		    if(linkPhoto.isSelectionner()){
		    	FichierMultimediaVO fichierMultimediaVO = photoBusinessDelegate.obtenirFichier(subject, linkPhoto.getLienElement(), linkPhoto.getLienSiteElement(), false);
		    	if(fichierMultimediaVO.getImageByte() != null){
		    		photo = new ByteArrayInputStream(fichierMultimediaVO.getImageByte());
		    	}
		    }
		}//while
		return photo;
	}

}