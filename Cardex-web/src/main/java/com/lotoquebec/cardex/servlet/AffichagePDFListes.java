package com.lotoquebec.cardex.servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;

import com.lotoquebec.cardex.business.Acces;
import com.lotoquebec.cardex.business.ConsignationActionPSU;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.delegate.AccesBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.PSUMandatBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.PhotoBusinessDelegate;
import com.lotoquebec.cardex.business.vo.CriteresRechercheDossierVO;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.FichierMultimediaVO;
import com.lotoquebec.cardex.business.vo.PhotoVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.VehiculeVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.presentation.model.form.AccesForm;
import com.lotoquebec.cardex.presentation.model.form.ConsignationActionPSUForm;
import com.lotoquebec.cardex.presentation.model.form.ConsignationForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheAdressesForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheConsignationForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheDossierForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRecherchePSUMandatForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRecherchePhotoForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheSocieteForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheSuiviForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheSujetForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheUrgenceForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheVehiculeForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.PSUMandatForm;
import com.lotoquebec.cardex.presentation.model.form.PhotoForm;
import com.lotoquebec.cardex.presentation.model.form.SocieteForm;
import com.lotoquebec.cardex.presentation.model.form.SuiviForm;
import com.lotoquebec.cardex.presentation.model.form.SujetForm;
import com.lotoquebec.cardex.presentation.model.form.UrgenceForm;
import com.lotoquebec.cardex.presentation.model.form.VehiculeForm;
import com.lotoquebec.cardex.presentation.rapport.RapportAssociation;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardex.util.RapportUtils;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.IteratorException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.NatureCleMultiListeCache;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Ce servlet sert � afficher les rapports Jasper en format PDF � l'�cran.
 * Il sert aux listes de r�sultats de recherche ou dans les onglets.
 * Le code repose sur le concept AJAX (Asynchronous JavaScript and XML) et sur les librairies Jasper.
 * @date : mars 2012
 */
public class AffichagePDFListes extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {

        ActionErrors errors = new ActionErrors();
        
        CriteresRechercheDossierVO criteresRechercheDossier = new CriteresRechercheDossierVO();
        ResultSet resultSet = null;
        Map parameters = new HashMap();
        List liste = new ArrayList();
        ServletOutputStream servletOutputStream = response.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JasperReport jasperReport = null;
        JasperPrint print;
        Connection connection = null;
        try {
            connection = DAOConnection.getInstance().getConnection();
        	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
           	
           	GestionnaireSecuriteCardex.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
        	String choixRapport = (String)request.getParameter("RAPPORT");
           	String roleAdhoc = RapportAssociation.getRoleAdhoc(choixRapport);
           	InputStream gabarit = null;
           	if (StringUtils.isNotEmpty(roleAdhoc)){
           		GestionnaireSecuriteCardex.validerSecuriteAdhoc(subject, roleAdhoc);
           	}

	         ServletContext context = request.getSession().getServletContext();  
	         //On inscrit les param�tres des rapports
	         parameters.put("UTILISATEUR",subject.getPrincipal().getName());
	       //On utilise des listes pour ces rapports.
        	if (GlobalConstants.ChoixImpressionListe.IMPRIMER_PAGE_RESULTATS.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_DOSSIERS;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
     		    CriteresRechercheDossierForm criteresRechercheDossierForm = (CriteresRechercheDossierForm) request.getSession().getAttribute("rechercheDossier");
        	    
     		    ListeResultat listeResultat = criteresRechercheDossierForm.getListeResultat();
     			Iterator iter = listeResultat.getResultatAffichage().iterator();
    		    liste = remplirResultatDossiers(iter);
    		    //Recherche de la nature pour l'afficher dans l'ent�te du rapport.
    		    if(StringUtils.isNotEmpty(criteresRechercheDossierForm.getNature())){
        		    ListeCache cache = ListeCache.getInstance();
        		    parameters.put("NATURE",cache.obtenirLabel(subject, criteresRechercheDossierForm.getNature(), new NatureCleMultiListeCache(subject, criteresRechercheDossierForm.getGenre())));	  
    		    }
        	}
        	if (GlobalConstants.ChoixImpressionListe.IMPRIMER_TOUTES_PAGES_RESULTATS.equals( choixRapport )){
        		//On utilise le m�me rapport que pour l'impression de la page courante
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_DOSSIERS;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
     		    CriteresRechercheDossierForm criteresRechercheDossierForm = (CriteresRechercheDossierForm) request.getSession().getAttribute("rechercheDossier");
     		    ListeResultat listeResultat = criteresRechercheDossierForm.getListeResultat();
     			Iterator iter = listeResultat.getResultatComplet().iterator();
    		    liste = remplirResultatDossiers(iter);
    		    //Recherche de la nature pour l'afficher dans l'ent�te du rapport.
    		    if(StringUtils.isNotEmpty(criteresRechercheDossierForm.getNature())){
        		    ListeCache cache = ListeCache.getInstance();
        		    parameters.put("NATURE",cache.obtenirLabel(subject, criteresRechercheDossierForm.getNature(), new NatureCleMultiListeCache(subject, criteresRechercheDossierForm.getGenre())));	  
    		    }

        	}
        	if (GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SUJETS.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SUJETS;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
     		    CriteresRechercheSujetForm criteresRechercheSujetForm = (CriteresRechercheSujetForm) request.getSession().getAttribute("rechercheSujet");
     		    ListeResultat listeResultat = criteresRechercheSujetForm.getListeResultat();
     			Iterator iter = listeResultat.getResultatAffichage().iterator();
     			try{
     				liste = remplirResultatSujets(iter);
    			} catch (ValueObjectMapperException nsme) {
    				 nsme.printStackTrace();
     			}
        	}
        	if (GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SUJETS_COMPLET.equals( choixRapport )){
        		//On utilise le m�me rapport que pour l'impression de la page courante
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SUJETS;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
     		    CriteresRechercheSujetForm criteresRechercheSujetForm = (CriteresRechercheSujetForm) request.getSession().getAttribute("rechercheSujet");
     		    ListeResultat listeResultat = criteresRechercheSujetForm.getListeResultat();
     			Iterator iter = listeResultat.getResultatComplet().iterator();
     			try{
     				liste = remplirResultatSujets(iter);
    			} catch (ValueObjectMapperException nsme) {
    				 nsme.printStackTrace();
     			}
        	}
            
        	if (GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SOCIETES.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SOCIETES;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
     		    CriteresRechercheSocieteForm criteresRechercheSocieteForm = (CriteresRechercheSocieteForm) request.getSession().getAttribute("rechercheSociete");
     		    ListeResultat listeResultat = criteresRechercheSocieteForm.getListeResultat();
     			Iterator iter = listeResultat.getResultatAffichage().iterator();
     			try{
     				liste = remplirResultatSocietes(iter);
    			} catch (ValueObjectMapperException nsme) {
    				 nsme.printStackTrace();
     			}
        	}
        	if (GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SOCIETES_COMPLET.equals( choixRapport )){
        		//On utilise le m�me rapport que pour l'impression de la page courante
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SOCIETES;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
    			CriteresRechercheSocieteForm criteresRechercheSocieteForm = (CriteresRechercheSocieteForm) request.getSession().getAttribute("rechercheSociete");
     		    ListeResultat listeResultat = criteresRechercheSocieteForm.getListeResultat();
     			Iterator iter = listeResultat.getResultatComplet().iterator();
     			try{
     				liste = remplirResultatSocietes(iter);
    			} catch (ValueObjectMapperException nsme) {
    				 nsme.printStackTrace();
     			}
        	}

        	if (GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_ADRESSE_SUJETS.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SUJETS;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
     		    CriteresRechercheAdressesForm criteresRechercheAdressesForm = (CriteresRechercheAdressesForm) request.getSession().getAttribute("rechercheAdresses");
     		    ListeResultat listeResultat = criteresRechercheAdressesForm.getListeResultat();
     			Iterator iter = listeResultat.getResultatAffichage().iterator();
     			try{
     				liste = remplirResultatSujets(iter);
    			} catch (ValueObjectMapperException nsme) {
    				 nsme.printStackTrace();
     			}
        	}
        	if (GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_ADRESSE_SOCIETES.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SOCIETES;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
     		    CriteresRechercheAdressesForm criteresRechercheAdressesForm = (CriteresRechercheAdressesForm) request.getSession().getAttribute("rechercheAdresses");
     		    ListeResultat listeResultat = criteresRechercheAdressesForm.getListeResultat();
     			Iterator iter = listeResultat.getResultatAffichage().iterator();
     			try{
     				liste = remplirResultatSocietes(iter);
    			} catch (ValueObjectMapperException nsme) {
    				 nsme.printStackTrace();
     			}
        	}
        	if (GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_VEHICULES.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_VEHICULES;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
     		    CriteresRechercheVehiculeForm criteresRechercheVehiculeForm = (CriteresRechercheVehiculeForm) request.getSession().getAttribute("rechercheVehicule");
     		    ListeResultat listeResultat = criteresRechercheVehiculeForm.getListeResultat();
     			Iterator iter = listeResultat.getResultatAffichage().iterator();
     			try{
     				liste = remplirResultatVehicules(iter);
    			} catch (ValueObjectMapperException nsme) {
     			      nsme.printStackTrace();
     			}
        	}
        	if (GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_VEHICULES_COMPLET.equals( choixRapport )){
        		//On utilise le m�me rapport que pour l'impression de la page courante
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_VEHICULES;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
     		    CriteresRechercheVehiculeForm criteresRechercheVehiculeForm = (CriteresRechercheVehiculeForm) request.getSession().getAttribute("rechercheVehicule");
     		    ListeResultat listeResultat = criteresRechercheVehiculeForm.getListeResultat();
     			Iterator iter = listeResultat.getResultatAffichage().iterator();
     			try{
     				liste = remplirResultatVehicules(iter);
    			} catch (ValueObjectMapperException nsme) {
    				 nsme.printStackTrace();
     			}
        	}
            if (GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_URGENCE.equals( choixRapport )){
                choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_URGENCE;
                gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
                CriteresRechercheUrgenceForm criteresRechercheUrgenceForm = (CriteresRechercheUrgenceForm) request.getSession().getAttribute("rechercheUrgence");
                ListeResultat listeResultat = criteresRechercheUrgenceForm.getListeResultat();
                Iterator iter = listeResultat.getResultatAffichage().iterator();
                try{
                    liste = remplirResultatUrgences(iter);
                } catch (ValueObjectMapperException nsme) {
                      nsme.printStackTrace();
                }
            }
            if (GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_URGENCE_COMPLET.equals( choixRapport )){
                //On utilise le m�me rapport que pour l'impression de la page courante
                choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_URGENCE;
                gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
                CriteresRechercheUrgenceForm criteresRechercheUrgenceForm = (CriteresRechercheUrgenceForm) request.getSession().getAttribute("rechercheUrgence");
                ListeResultat listeResultat = criteresRechercheUrgenceForm.getListeResultat();
                Iterator iter = listeResultat.getResultatAffichage().iterator();
                try{
                    liste = remplirResultatUrgences(iter);
                } catch (ValueObjectMapperException nsme) {
                     nsme.printStackTrace();
                }
            }
        	if (GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SUIVIS.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_SUIVIS;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
    			CriteresRechercheSuiviForm criteresRechercheSuiviForm = (CriteresRechercheSuiviForm) request.getSession().getAttribute("rechercheSuivi");
     		    ListeResultat listeResultat = criteresRechercheSuiviForm.getListeResultat();
     			Iterator iter = listeResultat.getResultatComplet().iterator();
     			try{
     				liste = remplirResultatSuivis(iter);
    			} catch (ValueObjectMapperException nsme) {
    				 nsme.printStackTrace();
     			}
    			parameters.put("SUBREPORT_DIR",context.getRealPath("/images/"));
        	}
        	if (GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_CONSIGNATIONS.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_CONSIGNATIONS;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
    			CriteresRechercheConsignationForm criteresRechercheConsignationForm = (CriteresRechercheConsignationForm) request.getSession().getAttribute("rechercheConsignation");
     		    ListeResultat listeResultat = criteresRechercheConsignationForm.getListeResultat();
     			Iterator iter = listeResultat.getResultatComplet().iterator();
     			try{
     				liste = remplirResultatConsignations(iter);
    			} catch (ValueObjectMapperException nsme) {
    				 nsme.printStackTrace();
     			}
    			parameters.put("SUBREPORT_DIR",context.getRealPath("/images/"));
        	}
        	if (GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_MANDATS.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_MANDATS;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
    			CriteresRecherchePSUMandatForm criteresRecherchePSUMandatForm = (CriteresRecherchePSUMandatForm) request.getSession().getAttribute("recherchePSUMandat");
    			ListeResultat listeResultat = criteresRecherchePSUMandatForm.getListeResultat();
     			Iterator iter = listeResultat.getResultatComplet().iterator();
     			try{
     				liste = remplirResultatMandats(iter);
    			} catch (ValueObjectMapperException nsme) {
    				 nsme.printStackTrace();
     			}
    			parameters.put("SUBREPORT_DIR",context.getRealPath("/images/"));
        	}
        	if (GlobalConstants.ChoixRapport.CONSIGNATION_ACTIONS_MANDATS.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.CONSIGNATION_ACTIONS_MANDATS;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
            	String mandat = (String)request.getParameter("MANDAT");
    			liste = remplirConsignationActionsMandats(subject, mandat);
    			parameters.put("MANDAT", mandat);
        	}
        	if (GlobalConstants.ChoixRapport.ONGLET_DOSSIERS_DOSSIER.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.ONGLET_DOSSIERS_DOSSIER;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
    			DossierForm dossierForm = (DossierForm) request.getSession().getAttribute("dossier");
     		    ListeResultat listeResultat = dossierForm.getListeDossiers();
     			Iterator iter = listeResultat.getResultatComplet().iterator();
   				liste = remplirResultatDossiers(iter);
   				//parameters.put("DOSSIER",dossierForm.getNumeroCardex().toString());
   				parameters.put("DOSSIER",dossierForm.getNumeroCardexTexte());
        	}
        	if (GlobalConstants.ChoixRapport.ONGLET_DOSSIERS_SUJET.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.ONGLET_DOSSIERS_SUJET;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
    			SujetForm sujetForm = (SujetForm) request.getSession().getAttribute("sujet");
     		    ListeResultat listeResultat = sujetForm.getListeDossiers();
     			Iterator iter = listeResultat.getResultatComplet().iterator();
   				liste = remplirResultatDossiers(iter);
   				parameters.put("SUJET",sujetForm.getNumeroFiche());
        	}
        	if (GlobalConstants.ChoixRapport.ONGLET_SUJET_SOCIETE.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.ONGLET_SUJET_SOCIETE;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
    			SocieteForm societeForm = (SocieteForm) request.getSession().getAttribute("societe");
     		    ListeResultat listeResultat = societeForm.getListeSujets();
     			Iterator iter = listeResultat.getResultatComplet().iterator();
     			try{
     				liste = remplirResultatSujets(iter);
    			} catch (ValueObjectMapperException nsme) {
    				 nsme.printStackTrace();
     			}
   				parameters.put("SOCIETE",societeForm.getNom());
        	}
        	if (GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_GALERIE.equals( choixRapport )){
        		CriteresRecherchePhotoForm photoForm = (CriteresRecherchePhotoForm) request.getSession().getAttribute("rechercheGalerie");
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_GALERIE;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
    			Collection photos = (Collection) photoForm.getListeResultat().getResultatAffichage();
    			Iterator iter = photos.iterator();
     			try{
     				liste = remplirResultatGalerie(iter, subject);
    			} catch (BusinessResourceException nsme) {
    				 nsme.printStackTrace();
     			}
   	            parameters.put("SUBREPORT_DIR",context.getRealPath("/rapports/"));
   	            parameters.put("REPORT_CONNECTION",connection);
        	}
        	if (GlobalConstants.ChoixRapport.AUDIT_ACCES_DOSSIERS.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.AUDIT_ACCES_DOSSIERS;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
    			DossierForm dossierForm = (DossierForm) request.getSession().getAttribute("dossier");
    			dossierForm.assignerValeurDeListe(subject);
    			//parameters.put("NUMERO_DOSSIER",dossierForm.getNumeroCardex().toString());
    			parameters.put("NUMERO_DOSSIER",dossierForm.getNumeroCardexTexte());
    			parameters.put("CREATEUR",dossierForm.getCreateurDescription());
    			parameters.put("DATE_CREATION", dossierForm.getDateCreation());
     			liste = remplirResultatAccesDossier(subject, dossierForm);
        	}
        	if (RapportsConfiguration.AUDIT_ACCES_SUJETS.equals( choixRapport )){
               	//choixRapport = "rapports/" + GlobalConstants.ChoixRapport.AUDIT_ACCES_SUJETS;
    			//gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
        		jasperReport = RapportUtils.compiler(RapportsConfiguration.AUDIT_ACCES_SUJETS);
    			SujetForm sujetForm = (SujetForm) request.getSession().getAttribute("sujet");
    			sujetForm.assignerValeurDeListe(subject);
    			parameters.put("NUMERO_DOSSIER",sujetForm.getNumeroFiche());
    			parameters.put("CREATEUR",sujetForm.getCreateurDescription());
    			parameters.put("DATE_CREATION", sujetForm.getDateCreation());
     			liste = remplirResultatAccesSujet(subject, sujetForm);
        	}
        	if (GlobalConstants.ChoixRapport.AUDIT_ACCES_SOCIETES.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.AUDIT_ACCES_SOCIETES;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
    			SocieteForm societeForm = (SocieteForm) request.getSession().getAttribute("societe");
    			societeForm.assignerValeurDeListe(subject);
    			parameters.put("NUMERO_DOSSIER",societeForm.getNom());
    			parameters.put("CREATEUR",societeForm.getCreateurDescription());
    			parameters.put("DATE_CREATION", societeForm.getDateCreation());
     			liste = remplirResultatAccesSociete(subject, societeForm);
        	}
        	if (GlobalConstants.ChoixRapport.AUDIT_ACCES_VEHICULES.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.AUDIT_ACCES_VEHICULES;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
    			VehiculeForm vehiculeForm = (VehiculeForm) request.getSession().getAttribute("vehicule");
    			vehiculeForm.assignerValeurDeListe(subject);
    			parameters.put("NUMERO_DOSSIER",vehiculeForm.getImmatriculation());
    			parameters.put("CREATEUR",vehiculeForm.getCreateurDescription());
    			parameters.put("DATE_CREATION", vehiculeForm.getDateCreation());
     			liste = remplirResultatAccesVehicule(subject, vehiculeForm);
        	}
        	if (GlobalConstants.ChoixImpressionListe.IMPRIMER_DOSSIERS_AVEC_SUJETS.equals( choixRapport )){
        		//Impression de tous les dossiers retourn�s par la recherche avec les sujets li�s.
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_DOSSIERS_AVEC_SUJETS;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
     		    CriteresRechercheDossierForm criteresRechercheDossierForm = (CriteresRechercheDossierForm) request.getSession().getAttribute("rechercheDossier");
     		    ListeResultat listeResultat = criteresRechercheDossierForm.getListeResultat();
     			Iterator iter = listeResultat.getResultatComplet().iterator();
    		    liste = remplirResultatDossiers(iter);
    		    parameters.put("SUBREPORT_DIR",context.getRealPath("/rapports/"));
        	}
        	if (GlobalConstants.ChoixImpressionListe.IMPRIMER_DOSSIERS_SANS_SUJETS.equals( choixRapport )){
        		//Impression de tous les dossiers retourn�s par la recherche sans les sujets li�s.
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RESULTATS_RECHERCHE_DOSSIERS_SANS_SUJETS;
    			gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
     		    CriteresRechercheDossierForm criteresRechercheDossierForm = (CriteresRechercheDossierForm) request.getSession().getAttribute("rechercheDossier");
     		    ListeResultat listeResultat = criteresRechercheDossierForm.getListeResultat();
     			Iterator iter = listeResultat.getResultatComplet().iterator();
    		    liste = remplirResultatDossiers(iter);
    		    parameters.put("SUBREPORT_DIR",context.getRealPath("/rapports/"));
        	}
        	parameters.put("REPORT_CONNECTION",connection);
      		JRDataSource dataSource = new JRMapCollectionDataSource(liste);
    		
      		if (gabarit != null)
      			print = JasperFillManager.fillReport(gabarit, parameters, dataSource);
      		else
      			print = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        	//Affichage � l'�cran
	         response.setContentType("application/pdf");
			response.setHeader("Cache-Control", "max-age=0");
			JasperExportManager.exportReportToPdfStream(print, baos);
			JRAbstractExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
			exporter.exportReport();
			servletOutputStream.write(baos.toByteArray());

			baos.flush();
			baos.close();

	         servletOutputStream.flush();
	         servletOutputStream.close();
	         
	         
	         
	         

      } catch (BusinessResourceException bre) {
          bre.printStackTrace();
      } catch (BusinessException be) {
          be.printStackTrace();
	  } catch (JRException se) {
	   	  se.printStackTrace();
	  } catch (ValueObjectMapperException vome) {
		  vome.printStackTrace();
      } catch (DAOException se) {
			//System.out.println(se.getMessage());
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
	        }
  }


  private List remplirResultatDossiers(Iterator iter){
	  List list = new ArrayList();
	
	while (iter.hasNext()) {
		Map dossier = new HashMap();
		DossierForm dossierForm = (DossierForm) iter.next();
		dossier.put("NatureDescription", dossierForm.getNatureDescription());
		dossier.put("TypeDescription", dossierForm.getTypeDescription());
		dossier.put("CategorieDescription", dossierForm.getCategorieDescription());
		//dossier.put("Numero_Cardex",dossierForm.getNumeroCardex().toString());
		dossier.put("Numero_Cardex",dossierForm.getNumeroCardexTexte());
		dossier.put("Numero_dossier",dossierForm.getNumeroDossier());
		dossier.put("Reference_1", dossierForm.getReference1());
		dossier.put("Reference_2", dossierForm.getReference2());
		dossier.put("Reference_3", dossierForm.getReference3());
		dossier.put("Date_debut", dossierForm.getDateDebut());
		dossier.put("Date_fin", dossierForm.getDateFin());
		dossier.put("Periode", dossierForm.getPeriodeDescription());
		dossier.put("Statut", dossierForm.getStatutDescription());
		dossier.put("Intervenant", dossierForm.getIntervenant());
		dossier.put("IntervenantDescription", dossierForm.getIntervenantDescription());
		dossier.put("Severite", dossierForm.getSeveriteDescription());
		dossier.put("Confidentialite", dossierForm.getConfidentialiteDescription());
		dossier.put("FondeDescription", dossierForm.getFondeDescription());
		dossier.put("Role", dossierForm.getRoleDescription());
		dossier.put("OrigineDescription", dossierForm.getOrigineDescription());
		dossier.put("EndroitDescription", dossierForm.getEndroitDescription());
		dossier.put("LocalisationDescription", dossierForm.getLocalisationDescription());
		dossier.put("Descriptif", dossierForm.getDescriptif());
		dossier.put("Numero_sequentiel", dossierForm.getReferenceVideo());
		dossier.put("SiteDescription", dossierForm.getSiteDescription());
		dossier.put("DOSSIERCLE", new BigDecimal(dossierForm.getCle()));
		dossier.put("SITECLE", new BigDecimal(dossierForm.getSite()));
		if(StringUtils.isNotEmpty(dossierForm.getLienCreateur())){
			dossier.put("LiePar", dossierForm.getLienCreateur());
		}
		list.add(dossier);
	}

  return list;
 }

  private List remplirResultatSujets(Iterator iter)
  	throws ValueObjectMapperException {
	  List list = new ArrayList();
//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
//le rapport soient identiques au "describe" des champs de la fonction.	  
   try{
	  while (iter.hasNext()) {
		SujetForm sujetForm = (SujetForm) iter.next();
		Map sujet = BeanUtils.describe(sujetForm);
		list.add(sujet);
	  }
	  return list;
	  } catch (IllegalAccessException iae) {
	      throw new ValueObjectMapperException(iae);
	  } catch (InvocationTargetException ite) {
	      throw new ValueObjectMapperException(ite);
	  } catch (NoSuchMethodException nsme) {
	      throw new ValueObjectMapperException(nsme);
	  }
 }

  private List remplirResultatSocietes(Iterator iter)
	throws ValueObjectMapperException {
	  List list = new ArrayList();
//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
//le rapport soient identiques au "describe" des champs de la fonction.	  
 try{
	  while (iter.hasNext()) {
		SocieteForm societeForm = (SocieteForm) iter.next();
		Map societe = BeanUtils.describe(societeForm);
		list.add(societe);
	  }
	  return list;
	  } catch (IllegalAccessException iae) {
	      throw new ValueObjectMapperException(iae);
	  } catch (InvocationTargetException ite) {
	      throw new ValueObjectMapperException(ite);
	  } catch (NoSuchMethodException nsme) {
	      throw new ValueObjectMapperException(nsme);
	  }
}

  private List remplirResultatVehicules(Iterator iter)
	throws ValueObjectMapperException {
	  List list = new ArrayList();
//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
//le rapport soient identiques au "describe" des champs de la fonction.	  
	try{
	  while (iter.hasNext()) {
		VehiculeForm vehiculeForm = (VehiculeForm) iter.next();
		vehiculeForm.setCle(vehiculeForm.getCle() + " " + vehiculeForm.getSite());
		Map vehicule = BeanUtils.describe(vehiculeForm);
		list.add(vehicule);
	  }
	  return list;
	  } catch (IllegalAccessException iae) {
	      throw new ValueObjectMapperException(iae);
	  } catch (InvocationTargetException ite) {
	      throw new ValueObjectMapperException(ite);
	  } catch (NoSuchMethodException nsme) {
	      throw new ValueObjectMapperException(nsme);
	  }
  }

  private List remplirResultatUrgences(Iterator iter) throws ValueObjectMapperException{
        List list = new ArrayList();
        // On r�cup�re les valeurs directement dans un Map avec la fonction
        // BeanUtils.describe.
        // Cela �vite de traiter les donn�es champ par champ. Il faut cependant
        // que les noms de champ utilis�s dans
        // le rapport soient identiques au "describe" des champs de la fonction.
        try{
            while (iter.hasNext()){
                UrgenceForm urgenceForm = (UrgenceForm) iter.next();
                Map urgence = BeanUtils.describe(urgenceForm);
                list.add(urgence);
            }
            return list;
        }
        catch (IllegalAccessException iae){
            throw new ValueObjectMapperException(iae);
        }
        catch (InvocationTargetException ite){
            throw new ValueObjectMapperException(ite);
        }
        catch (NoSuchMethodException nsme){
            throw new ValueObjectMapperException(nsme);
        }
    }
  
  private List remplirResultatSuivis(Iterator iter)
	throws ValueObjectMapperException {
	  List list = new ArrayList();
//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
//le rapport soient identiques au "describe" des champs de la fonction.	  
	try{
	  while (iter.hasNext()) {
		  SuiviForm suiviForm = (SuiviForm) iter.next();
		Map suivi = BeanUtils.describe(suiviForm);
		//suivi.put("numeroCardex",suiviForm.getDossier().getNumeroCardex().toString());
		suivi.put("numeroCardex",suiviForm.getDossier().getNumeroCardexTexte());
		list.add(suivi);
	  }
	  return list;
	  } catch (IllegalAccessException iae) {
	      throw new ValueObjectMapperException(iae);
	  } catch (InvocationTargetException ite) {
	      throw new ValueObjectMapperException(ite);
	  } catch (NoSuchMethodException nsme) {
	      throw new ValueObjectMapperException(nsme);
	  }
  }

  private List remplirResultatConsignations(Iterator iter)
	throws ValueObjectMapperException {
	  List list = new ArrayList();
//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
//le rapport soient identiques au "describe" des champs de la fonction.	  
	try{
	  while (iter.hasNext()) {
		  ConsignationForm consignationForm = (ConsignationForm) iter.next();
		Map consignation = BeanUtils.describe(consignationForm);
		//suivi.put("numeroCardex",consignationForm.getDossier().getNumeroCardex().toString());
		consignation.put("numeroCardex",consignationForm.getDossier().getNumeroCardexTexte());
		list.add(consignation);
	  }
	  return list;
	  } catch (IllegalAccessException iae) {
	      throw new ValueObjectMapperException(iae);
	  } catch (InvocationTargetException ite) {
	      throw new ValueObjectMapperException(ite);
	  } catch (NoSuchMethodException nsme) {
	      throw new ValueObjectMapperException(nsme);
	  }
  }
  private List remplirResultatMandats(Iterator iter)
	throws ValueObjectMapperException {
	  List list = new ArrayList();
//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
//le rapport soient identiques au "describe" des champs de la fonction.	  
	try{
	  while (iter.hasNext()) {
		  PSUMandatForm psuMandatForm = (PSUMandatForm) iter.next();
		Map mandat = BeanUtils.describe(psuMandatForm);
		list.add(mandat);
	  }
	  return list;
	  } catch (IllegalAccessException iae) {
	      throw new ValueObjectMapperException(iae);
	  } catch (InvocationTargetException ite) {
	      throw new ValueObjectMapperException(ite);
	  } catch (NoSuchMethodException nsme) {
	      throw new ValueObjectMapperException(nsme);
	  }
  }

  private List remplirResultatGalerie(Iterator iter, CardexAuthenticationSubject subject)
	throws ValueObjectMapperException, BusinessResourceException, DAOException, BusinessException {
	  List list = new ArrayList();
	  try{
		  while (iter.hasNext()) {
			PhotoForm photoForm = (PhotoForm) iter.next();
			photoForm.assignerValeurDeListe(subject);
			Map galerie = BeanUtils.describe(photoForm);
			Photo photoVO = new PhotoVO();
			ValueObjectMapper.convertPhotoHtmlForm(photoForm, photoVO, subject.getLocale());
	        InputStream photo = recherchePhoto(subject, photoVO);
   	        galerie.put("photo",photo);
			list.add(galerie);
		  }
	  return list;
	  } catch (IllegalAccessException iae) {
	      throw new ValueObjectMapperException(iae);
	  } catch (InvocationTargetException ite) {
	      throw new ValueObjectMapperException(ite);
	  } catch (NoSuchMethodException nsme) {
	      throw new ValueObjectMapperException(nsme);
	  }
  }
  
  private List remplirResultatAccesDossier(CardexAuthenticationSubject subject, DossierForm dossierForm)
	throws ValueObjectMapperException{
	  List resultat = new ArrayList();
	  List liste = new ArrayList();
  try{
		AccesBusinessDelegate delegate =  new AccesBusinessDelegate();
		ValueListIterator results;
        DossierVO dossier = new DossierVO();
        ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossier,subject.getLocale());
		results = delegate.selectAccesDossier(subject,dossier);
		liste = traitementAcces(subject, results);
	  } catch (BusinessResourceException bre) {
	      bre.printStackTrace();
	  } catch (BusinessException be) {
	      be.printStackTrace();
	  } catch (IteratorException ie) {
	      ie.printStackTrace();
	  }
	  return liste;
}

  private List remplirResultatAccesSujet(CardexAuthenticationSubject subject, SujetForm sujetForm)
	throws ValueObjectMapperException{
	  List resultat = new ArrayList();
	  List liste = new ArrayList();
	  try{
		AccesBusinessDelegate delegate =  new AccesBusinessDelegate();
		ValueListIterator results;
        SujetVO sujet = new SujetVO();
        ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujet,subject.getLocale());
		results = delegate.selectAccesSujet(subject,sujet);
		liste = traitementAcces(subject, results);
	} catch (BusinessResourceException bre) {
	    bre.printStackTrace();
	} catch (BusinessException be) {
	    be.printStackTrace();
	} catch (IteratorException ie) {
	    ie.printStackTrace();
	}
	return liste;
}

  private List remplirResultatAccesSociete(CardexAuthenticationSubject subject, SocieteForm societeForm)
	throws ValueObjectMapperException{
	  List resultat = new ArrayList();
	  List liste = new ArrayList();
	  try{
		AccesBusinessDelegate delegate =  new AccesBusinessDelegate();
		ValueListIterator results;
      SocieteVO societe = new SocieteVO();
      ValueObjectMapper.convertSocieteHtmlForm(societeForm, societe,subject.getLocale());
		results = delegate.selectAccesSociete(subject,societe);
		liste = traitementAcces(subject, results);
	} catch (BusinessResourceException bre) {
	    bre.printStackTrace();
	} catch (BusinessException be) {
	    be.printStackTrace();
	} catch (IteratorException ie) {
	    ie.printStackTrace();
	}
	return liste;
}

  private List remplirResultatAccesVehicule(CardexAuthenticationSubject subject, VehiculeForm vehiculeForm)
	throws ValueObjectMapperException{
	  List resultat = new ArrayList();
	  List liste = new ArrayList();
	  try{
		AccesBusinessDelegate delegate =  new AccesBusinessDelegate();
		ValueListIterator results;
    VehiculeVO vehicule = new VehiculeVO();
    ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm, vehicule,subject.getLocale());
		results = delegate.selectAccesVehicule(subject,vehicule);
		liste = traitementAcces(subject, results);
	} catch (BusinessResourceException bre) {
	    bre.printStackTrace();
	} catch (BusinessException be) {
	    be.printStackTrace();
	} catch (IteratorException ie) {
	    ie.printStackTrace();
	}
	return liste;
}

  private List traitementAcces(CardexAuthenticationSubject subject, ValueListIterator results)
  	throws ValueObjectMapperException, IteratorException{
  Collection list = results.getNextElements(5000);
  Collection currentList = new ArrayList();
  Iterator   it = list.iterator();
  List liste = new ArrayList();
  try{
	  while (it.hasNext()) {
	      Acces acces = (Acces)it.next();
	      AccesForm accesForm = new AccesForm();
	      ValueObjectMapper.convertAcces(acces, accesForm,subject.getLocale());
	      currentList.add(accesForm);
	  }
		//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
		//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
		//le rapport soient identiques au "describe" des champs de la fonction.	  
	  Iterator iter = currentList.iterator();
	  while (iter.hasNext()) {
		  AccesForm accesForm = (AccesForm) iter.next();
		  accesForm.assignerValeurDeListe(subject);
		Map acces = BeanUtils.describe(accesForm);
		liste.add(acces);
	  }
	} catch (BusinessResourceException bre) {
	    bre.printStackTrace();
	} catch (BusinessException be) {
	    be.printStackTrace();
	} catch (IllegalAccessException iae) {
	    throw new ValueObjectMapperException(iae);
	} catch (InvocationTargetException ite) {
	    throw new ValueObjectMapperException(ite);
	} catch (NoSuchMethodException nsme) {
	    throw new ValueObjectMapperException(nsme);
	}
  return liste;
}

  private List remplirConsignationActionsMandats(CardexAuthenticationSubject subject, String mandat)
	throws ValueObjectMapperException {
	  List resultat = new ArrayList();
	  List liste = new ArrayList();
	  try{
			PSUMandatBusinessDelegate delegate =  new PSUMandatBusinessDelegate();
			ValueListIterator results;
			results = delegate.findLiensConsignationAction(subject,mandat);
			resultat = results.getNextElements(2000);
			Collection currentList = new ArrayList();
			Iterator   it = resultat.iterator();
	
			while (it.hasNext()) {
				ConsignationActionPSU consignationActionPSU = (ConsignationActionPSU)it.next();
				ConsignationActionPSUForm consignationActionPSUForm = new ConsignationActionPSUForm();
				ValueObjectMapper.convertConsignationActionPSU(consignationActionPSU, consignationActionPSUForm,subject.getLocale());
				consignationActionPSUForm.assignerValeurDeListe(subject);
				currentList.add(consignationActionPSUForm);
			}  
			//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
			//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
			//le rapport soient identiques au "describe" des champs de la fonction.	  
		  Iterator iter = currentList.iterator();
		  while (iter.hasNext()) {
			  ConsignationActionPSUForm consignationActionPSUForm = (ConsignationActionPSUForm) iter.next();
			Map psuFormMandat = BeanUtils.describe(consignationActionPSUForm);
			liste.add(psuFormMandat);
		  }
      } catch (BusinessResourceException bre) {
          bre.printStackTrace();
      } catch (BusinessException be) {
          be.printStackTrace();
	  } catch (IteratorException ie) {
          ie.printStackTrace();
	  } catch (IllegalAccessException iae) {
	      throw new ValueObjectMapperException(iae);
	  } catch (InvocationTargetException ite) {
	      throw new ValueObjectMapperException(ite);
	  } catch (NoSuchMethodException nsme) {
	      throw new ValueObjectMapperException(nsme);
	  }
	  return liste;
  }
  
	private InputStream recherchePhoto(CardexAuthenticationSubject subject, Photo photoVO)
	throws BusinessException, DAOException{
	InputStream photo = null;
		PhotoBusinessDelegate photoBusinessDelegate = new PhotoBusinessDelegate();
		FichierMultimediaVO fichierMultimediaVO = photoBusinessDelegate.obtenirFichier(subject, photoVO.getLienElement(), photoVO.getLienSiteElement(), false);
		if(fichierMultimediaVO.getImageByte() != null){
			photo = new ByteArrayInputStream(fichierMultimediaVO.getImageByte());
		}
		return photo;
	}

  
}