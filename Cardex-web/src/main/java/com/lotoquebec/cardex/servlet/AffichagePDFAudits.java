package com.lotoquebec.cardex.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.delegate.AdresseBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.DossierBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.NarrationBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SocieteBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SuiviBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.SujetBusinessDelegate;
import com.lotoquebec.cardex.business.delegate.VehiculeBusinessDelegate;
import com.lotoquebec.cardex.business.vo.AdresseVO;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.NarrationVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.business.vo.SuiviVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.business.vo.VehiculeVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.presentation.model.form.AdresseForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.NarrationForm;
import com.lotoquebec.cardex.presentation.model.form.SocieteForm;
import com.lotoquebec.cardex.presentation.model.form.SuiviForm;
import com.lotoquebec.cardex.presentation.model.form.SujetForm;
import com.lotoquebec.cardex.presentation.model.form.VehiculeForm;
import com.lotoquebec.cardex.presentation.rapport.RapportAssociation;
import com.lotoquebec.cardex.presentation.util.ValueObjectMapper;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardex.util.RapportUtils;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Ce servlet sert � afficher les rapports Jasper en format PDF � l'�cran.
 * Il sert pour l'impression des fiches Sujet, Soci�t� et Dossier.
 * Le code repose sur le concept AJAX (Asynchronous JavaScript and XML) et sur les librairies Jasper.
 * @date : novembre 2008
 */
public class AffichagePDFAudits extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}

    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {

        ActionErrors errors = new ActionErrors();
        ResultSet resultSet = null;
        Map parameters = new HashMap();
        Connection connection = null;
        ServletOutputStream servletOutputStream = response.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
       	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());

    	GestionnaireSecuriteCardex.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
    	String choixRapport = (String)request.getParameter("RAPPORT");
    	String tableValeurRapport = (String)request.getParameter("tableValeurRapport");
    	String roleAdhoc = RapportAssociation.getRoleAdhoc(choixRapport);

        try {
        	if (StringUtils.isEmpty(roleAdhoc)){
        		GestionnaireSecuriteCardex.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, tableValeurRapport, "", GlobalConstants.ActionSecurite.TOUTES_ACTIONS), choixRapport);
           	}else{
           		GestionnaireSecuriteCardex.validerSecuriteAdhoc(subject, roleAdhoc);
           	}

        	connection = DAOConnection.getInstance().getConnection();
	         ServletContext context = request.getSession().getServletContext();
	         parameters.put("SUBREPORT_DIR",context.getRealPath("/rapports/"));
	         parameters.put("REPORT_CONNECTION",connection);
	         parameters.put("UTILISATEUR",subject.getPrincipal().getName());
             List results = new ArrayList();

        	if (RapportsConfiguration.AUDIT_CHANGEMENTS_DOSSIERS.equals( choixRapport )){
               	DossierBusinessDelegate dossierDelegate = new DossierBusinessDelegate();
               	DossierForm dossierForm = (DossierForm)request.getSession().getAttribute("dossier");
               	dossierForm.assignerValeurDeListe(subject);
               	Dossier     dossierVO     = new DossierVO();
                ValueObjectMapper.convertDossierHtmlForm(dossierForm, dossierVO,subject.getLocale());
                List liste = new ArrayList();
   	         	parameters.put("NUMERO_CARDEX",dossierForm.getNumeroCardex().toString());
                //parameters.put("NUMERO_CARDEX",dossierForm.getNumeroCardexTexte());
   	         	parameters.put("CLE_CREATEUR",dossierForm.getCreateur());
   	         	parameters.put("DATE_CREATION",dossierForm.getDateCreation());
                liste = dossierDelegate.audit(subject, dossierVO);
        		Iterator   it = liste.iterator();
                results = remplirResultatDossiers(subject, dossierForm, it);
            }
        	if (RapportsConfiguration.AUDIT_CHANGEMENTS_SUJETS_CDX_0185.equals( choixRapport )){
               	SujetBusinessDelegate sujetDelegate = new SujetBusinessDelegate();
               	SujetForm sujetForm = (SujetForm)request.getSession().getAttribute("sujet");
               	sujetForm.assignerValeurDeListe(subject);
               	Sujet     sujetVO     = new SujetVO();
                ValueObjectMapper.convertSujetHtmlForm(sujetForm, sujetVO, subject.getLocale());
                List liste = new ArrayList();
                parameters.put("NUMERO_FICHE",sujetForm.getNumeroFiche());
   	         	parameters.put("CLE_CREATEUR",sujetForm.getCreateur());
   	         	parameters.put("DATE_CREATION",sujetForm.getDateCreation());
                liste = sujetDelegate.audit(subject, sujetVO);
        		Iterator   it = liste.iterator();
                results = remplirResultatSujets(subject, sujetForm, it);
                
                JasperReport subJasperReport = RapportUtils.compiler(RapportsConfiguration.AUDIT_CHANGEMENTS_SUJETS_CDX_0185);
            }
        	if (RapportsConfiguration.AUDIT_CHANGEMENTS_SOCIETES.equals( choixRapport )){
               	SocieteBusinessDelegate societeDelegate = new SocieteBusinessDelegate();
               	SocieteForm societeForm = (SocieteForm)request.getSession().getAttribute("societe");
               	societeForm.assignerValeurDeListe(subject);
               	Societe     societeVO     = new SocieteVO();
                ValueObjectMapper.convertSocieteHtmlForm(societeForm, societeVO, subject.getLocale());
                List liste = new ArrayList();
                parameters.put("CLE",societeForm.getCle());
                parameters.put("SITE",societeForm.getSite());
   	         	parameters.put("CLE_CREATEUR",societeForm.getCreateur());
   	         	parameters.put("DATE_CREATION",societeForm.getDateCreation());
                liste = societeDelegate.audit(subject, societeVO);
        		Iterator   it = liste.iterator();
                results = remplirResultatSocietes(subject, societeForm, it);
            }
        	if (RapportsConfiguration.AUDIT_CHANGEMENTS_VEHICULES.equals( choixRapport )){
               	VehiculeBusinessDelegate vehiculeDelegate = new VehiculeBusinessDelegate();
               	VehiculeForm vehiculeForm = (VehiculeForm)request.getSession().getAttribute("vehicule");
               	vehiculeForm.assignerValeurDeListe(subject);
               	Vehicule     vehiculeVO     = new VehiculeVO();
                ValueObjectMapper.convertVehiculeHtmlForm(vehiculeForm, vehiculeVO, subject.getLocale());
                List liste = new ArrayList();
                parameters.put("CLE",vehiculeForm.getCle());
                parameters.put("SITE",vehiculeForm.getSite());
   	         	parameters.put("CLE_CREATEUR",vehiculeForm.getCreateur());
   	         	parameters.put("DATE_CREATION",vehiculeForm.getDateCreation());
                liste = vehiculeDelegate.audit(subject, vehiculeVO);
        		Iterator   it = liste.iterator();
                results = remplirResultatVehicules(subject, vehiculeForm, it);
            }
        	if (RapportsConfiguration.AUDIT_CHANGEMENTS_NARRATIONS.equals( choixRapport )){
               	choixRapport = "rapports/" + RapportsConfiguration.AUDIT_CHANGEMENTS_NARRATIONS;
               	NarrationBusinessDelegate narrationDelegate = new NarrationBusinessDelegate();
               	NarrationForm narrationForm = (NarrationForm)request.getSession().getAttribute("narration");
               	narrationForm.assignerValeurDeListe(subject);
               	Narration     narrationVO     = new NarrationVO();
                ValueObjectMapper.convertNarrationHtmlForm(narrationForm, narrationVO, subject.getLocale());
                List liste = new ArrayList();
                parameters.put("CLE",narrationForm.getCle());
                parameters.put("SITE",narrationForm.getSite());
   	         	parameters.put("CLE_CREATEUR",narrationForm.getCreateur());
   	         	parameters.put("DATE_CREATION",narrationForm.getDateCreation());
                liste = narrationDelegate.audit(subject, narrationVO);
        		Iterator   it = liste.iterator();
                results = remplirResultatNarrations(subject, narrationForm, it);
            }
        	if (RapportsConfiguration.AUDIT_CHANGEMENTS_SUIVIS.equals( choixRapport )){
               	SuiviBusinessDelegate suiviDelegate = new SuiviBusinessDelegate();
               	SuiviForm suiviForm = (SuiviForm)request.getSession().getAttribute("suivi");
               	suiviForm.assignerValeurDeListe(subject);
               	Suivi     suiviVO     = new SuiviVO();
                ValueObjectMapper.convertSuiviHtmlForm(suiviForm, suiviVO, subject.getLocale());
                List liste = new ArrayList();
                parameters.put("CLE",suiviForm.getCle());
                parameters.put("SITE",suiviForm.getSite());
   	         	parameters.put("CLE_CREATEUR",suiviForm.getCreateur());
   	         	parameters.put("DATE_CREATION",suiviForm.getDateCreation());
                liste = suiviDelegate.audit(subject, suiviVO);
        		Iterator   it = liste.iterator();
                results = remplirResultatSuivis(subject, suiviForm, it);
            }
        	if (RapportsConfiguration.AUDIT_CHANGEMENTS_ADRESSES.equals( choixRapport )){
               	AdresseBusinessDelegate adresseDelegate = new AdresseBusinessDelegate();
               	AdresseForm adresseForm = (AdresseForm)request.getSession().getAttribute("adresse");
               	adresseForm.assignerValeurDeListe(subject);
               	Adresse     adresseVO     = new AdresseVO();
                ValueObjectMapper.convertAdresseHtmlForm(adresseForm, adresseVO, subject.getLocale());
                List liste = new ArrayList();
                parameters.put("CLE",adresseForm.getCle());
                parameters.put("SITE",adresseForm.getSite());
   	         	parameters.put("CLE_CREATEUR",adresseForm.getCreateur());
   	         	parameters.put("DATE_CREATION",adresseForm.getDateCreation());
                liste = adresseDelegate.audit(subject, adresseVO);
        		Iterator   it = liste.iterator();
                results = remplirResultatAdresses(subject, adresseForm, it);
            }
            //Pour ce rapport, on utilise une liste et non un resultSet.
      		JRDataSource dataSource = new JRMapCollectionDataSource(results);
      		
    		JasperReport jasperReport = RapportUtils.compiler(choixRapport);
    		JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

    		//Affichage à l'écran
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

  	  } catch (DAOException se) {
			//System.out.println(se.getMessage());
			se.printStackTrace();
      } catch (BusinessResourceException bre) {
          bre.printStackTrace();
      } catch (BusinessException be) {
          be.printStackTrace();
      }catch (JRException se) {
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
 			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
			    	e.printStackTrace();
		        }
	        }
        }
	}

    private List remplirResultatDossiers(CardexAuthenticationSubject subject, DossierForm courant, Iterator iter)
	throws ValueObjectMapperException {
	List list = new ArrayList();
	//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
	//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
	//le rapport soient identiques au "describe" des champs de la fonction.
    //On met l'enregistrement courant dans precedent pour amorcer la comparaison des changements.
    DossierForm precedent = new DossierForm();
    precedent = courant;
    try{
		while (iter.hasNext()) {
		    Dossier dossier = (Dossier)iter.next();
		    DossierForm avantChangement = new DossierForm();
		    DossierForm dossierFormResultat = new DossierForm();
		    ValueObjectMapper.convertDossier(dossier, dossierFormResultat,subject.getLocale());
		    dossierFormResultat.assignerValeurDeListe(subject);
		    ValueObjectMapper.convertDossier(dossier, avantChangement,subject.getLocale());
		    //avantChangement = dossierFormResultat;
		    avantChangement.assignerValeurDeListe(subject);
            //Sur le rapport Jasper, on doit indiquer quels sont les champs qui ont chang�. On doit donc comparer
            //chacun des champs avec l'enregistrement pr�c�dent.
        	//Pour chacun des champs de l'enregistrement courant, on v�rifie s'il y a eu un changement avec l'enregistrement pr�c�dent. Si oui, on ajoute une fl�che � la fin pour l'indiquer sur le rapport.
		    dossierFormResultat.setSiteDescription( StringUtils.concateneFleche(dossierFormResultat.getSiteDescription(), false));
		    dossierFormResultat.setGenreDescription(StringUtils.concateneFleche(dossierFormResultat.getGenreDescription(), StringUtils.isDifferent(precedent.getGenreDescription(), dossierFormResultat.getGenreDescription())));
		    dossierFormResultat.setNatureDescription(StringUtils.concateneFleche(dossierFormResultat.getNatureDescription(), StringUtils.isDifferent(precedent.getNatureDescription(), dossierFormResultat.getNatureDescription())));
		    dossierFormResultat.setTypeDescription(StringUtils.concateneFleche(dossierFormResultat.getTypeDescription(), StringUtils.isDifferent(precedent.getTypeDescription(), dossierFormResultat.getTypeDescription())));
		    dossierFormResultat.setCategorieDescription(StringUtils.concateneFleche(dossierFormResultat.getCategorieDescription(), StringUtils.isDifferent(precedent.getCategorieDescription(), dossierFormResultat.getCategorieDescription())));
		    dossierFormResultat.setNumeroDossier(StringUtils.concateneFleche(dossierFormResultat.getNumeroDossier(), StringUtils.isDifferent(precedent.getNumeroDossier(), dossierFormResultat.getNumeroDossier())));
		    dossierFormResultat.setNumeroCardexTexte(StringUtils.concateneFleche(dossierFormResultat.getNumeroCardexTexte().toString(), StringUtils.isDifferent(precedent.getNumeroCardexTexte().toString(), dossierFormResultat.getNumeroCardexTexte().toString())));
		    dossierFormResultat.setStatutDescription(StringUtils.concateneFleche(dossierFormResultat.getStatutDescription(), StringUtils.isDifferent(precedent.getStatutDescription(), dossierFormResultat.getStatutDescription())));
		    dossierFormResultat.setFondeDescription(StringUtils.concateneFleche(dossierFormResultat.getFondeDescription(), StringUtils.isDifferent(precedent.getFondeDescription(), dossierFormResultat.getFondeDescription())));
		    dossierFormResultat.setDateDebut(StringUtils.concateneFleche(dossierFormResultat.getDateDebut(), StringUtils.isDifferent(precedent.getDateDebut(), dossierFormResultat.getDateDebut())));
		    dossierFormResultat.setDateFin(StringUtils.concateneFleche(dossierFormResultat.getDateFin(), StringUtils.isDifferent(precedent.getDateFin(), dossierFormResultat.getDateFin())));
		    dossierFormResultat.setReference1(StringUtils.concateneFleche(dossierFormResultat.getReference1(), StringUtils.isDifferent(precedent.getReference1(), dossierFormResultat.getReference1())));
		    dossierFormResultat.setReference2(StringUtils.concateneFleche(dossierFormResultat.getReference2(), StringUtils.isDifferent(precedent.getReference2(), dossierFormResultat.getReference2())));
		    dossierFormResultat.setReference3(StringUtils.concateneFleche(dossierFormResultat.getReference3(), StringUtils.isDifferent(precedent.getReference3(), dossierFormResultat.getReference3())));
		    precedent.setEnregistrementNumerique(StringUtils.convertirBoolean(precedent.getEnregistrementNumerique()));
		    dossierFormResultat.setEnregistrementNumerique(StringUtils.convertirBoolean(dossierFormResultat.getEnregistrementNumerique()));
		    dossierFormResultat.setEnregistrementNumerique(StringUtils.concateneFleche(dossierFormResultat.getEnregistrementNumerique(), StringUtils.isDifferent(precedent.getEnregistrementNumerique(), dossierFormResultat.getEnregistrementNumerique())));
		    dossierFormResultat.setReferenceVideo(StringUtils.concateneFleche(dossierFormResultat.getReferenceVideo(), StringUtils.isDifferent(precedent.getReferenceVideo(), dossierFormResultat.getReferenceVideo())));
		    precedent.setEnregistrementConserve(StringUtils.convertirBoolean(precedent.getEnregistrementConserve()));
		    dossierFormResultat.setEnregistrementConserve(StringUtils.convertirBoolean(dossierFormResultat.getEnregistrementConserve()));
		    dossierFormResultat.setEnregistrementConserve(StringUtils.concateneFleche(dossierFormResultat.getEnregistrementConserve(), StringUtils.isDifferent(precedent.getEnregistrementConserve(), dossierFormResultat.getEnregistrementConserve())));
		    dossierFormResultat.setIntervenantDescription(StringUtils.concateneFleche(dossierFormResultat.getIntervenantDescription(), StringUtils.isDifferent(precedent.getIntervenantDescription(), dossierFormResultat.getIntervenantDescription())));
		    dossierFormResultat.setOrigineDescription(StringUtils.concateneFleche(dossierFormResultat.getOrigineDescription(), StringUtils.isDifferent(precedent.getOrigineDescription(), dossierFormResultat.getOrigineDescription())));
		    dossierFormResultat.setSeveriteDescription(StringUtils.concateneFleche(dossierFormResultat.getSeveriteDescription(), StringUtils.isDifferent(precedent.getSeveriteDescription(), dossierFormResultat.getSeveriteDescription())));
		    dossierFormResultat.setConfidentialiteDescription(StringUtils.concateneFleche(dossierFormResultat.getConfidentialiteDescription(), StringUtils.isDifferent(precedent.getConfidentialiteDescription(), dossierFormResultat.getConfidentialiteDescription())));
		    dossierFormResultat.setEndroitDescription(StringUtils.concateneFleche(dossierFormResultat.getEndroitDescription(), StringUtils.isDifferent(precedent.getEndroitDescription(), dossierFormResultat.getEndroitDescription())));
		    dossierFormResultat.setLocalisationDescription(StringUtils.concateneFleche(dossierFormResultat.getLocalisationDescription(), StringUtils.isDifferent(precedent.getLocalisationDescription(), dossierFormResultat.getLocalisationDescription())));
		    dossierFormResultat.setDescriptif(StringUtils.concateneFleche(dossierFormResultat.getDescriptif(), StringUtils.isDifferent(precedent.getDescriptif(), dossierFormResultat.getDescriptif())));
		    dossierFormResultat.setMotPasse(StringUtils.concateneFleche(StringUtils.camoufleMotPasse(dossierFormResultat.getMotPasse()), StringUtils.isDifferent(precedent.getMotPasse(), dossierFormResultat.getMotPasse())));
	        //On ajoute les valeurs modifi�es aux r�sultats.
			Map dossierMapResultat = new HashMap();
			dossierMapResultat = BeanUtils.describe(dossierFormResultat);
	        list.add(dossierMapResultat);
            //On conserve les valeurs (sans les fl�ches d'indication des changements) pour la comparaison avec l'enregistrement suivant.
            precedent = avantChangement;
		}
	  } catch (IllegalAccessException iae) {
	      throw new ValueObjectMapperException(iae);
	  } catch (InvocationTargetException ite) {
	      throw new ValueObjectMapperException(ite);
	  } catch (NoSuchMethodException nsme) {
	      throw new ValueObjectMapperException(nsme);
      } catch (BusinessResourceException bre) {
          bre.printStackTrace();
	  }
	  return list;
}
    private List remplirResultatSujets(CardexAuthenticationSubject subject, SujetForm courant, Iterator iter)
	throws ValueObjectMapperException {
	List list = new ArrayList();
	//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
	//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
	//le rapport soient identiques au "describe" des champs de la fonction.
    //On met l'enregistrement courant dans precedent pour amorcer la comparaison des changements.
    SujetForm precedent = new SujetForm();
    precedent = courant;
    try{
		while (iter.hasNext()) {
			Sujet sujet = (Sujet)iter.next();
		    SujetForm avantChangement = new SujetForm();
		    SujetForm sujetFormResultat = new SujetForm();
		    ValueObjectMapper.convertSujet(sujet, sujetFormResultat,subject.getLocale());
		    sujetFormResultat.assignerValeurDeListe(subject);
		    ValueObjectMapper.convertSujet(sujet, avantChangement,subject.getLocale());
		    //avantChangement = dossierFormResultat;
		    avantChangement.assignerValeurDeListe(subject);
            //Sur le rapport Jasper, on doit indiquer quels sont les champs qui ont chang�. On doit donc comparer
            //chacun des champs avec l'enregistrement pr�c�dent.
        	//Pour chacun des champs de l'enregistrement courant, on v�rifie s'il y a eu un changement avec l'enregistrement pr�c�dent. Si oui, on ajoute une fl�che � la fin pour l'indiquer sur le rapport.
		    sujetFormResultat.setSeveriteDescriptionAutres(StringUtils.concateneFleche(sujetFormResultat.getSeveriteDescriptionAutres(), StringUtils.isDifferent(precedent.getSeveriteDescriptionAutres(), sujetFormResultat.getSeveriteDescriptionAutres())));
		    sujetFormResultat.setSexeDescription(StringUtils.concateneFleche(sujetFormResultat.getSexeDescription(), StringUtils.isDifferent(precedent.getSexeDescription(), sujetFormResultat.getSexeDescription())));
		    sujetFormResultat.setLangueDescription(StringUtils.concateneFleche(sujetFormResultat.getLangueDescription(), StringUtils.isDifferent(precedent.getLangueDescription(), sujetFormResultat.getLangueDescription())));
		    sujetFormResultat.setNom(StringUtils.concateneFleche(sujetFormResultat.getNom(), StringUtils.isDifferent(precedent.getNom(), sujetFormResultat.getNom())));
		    sujetFormResultat.setPrenom(StringUtils.concateneFleche(sujetFormResultat.getPrenom(), StringUtils.isDifferent(precedent.getPrenom(), sujetFormResultat.getPrenom())));
		    sujetFormResultat.setAlias(StringUtils.concateneFleche(sujetFormResultat.getAlias().toString(), StringUtils.isDifferent(precedent.getAlias().toString(), sujetFormResultat.getAlias().toString())));
		    sujetFormResultat.setStatutDescription(StringUtils.concateneFleche(sujetFormResultat.getStatutDescription(), StringUtils.isDifferent(precedent.getStatutDescription(), sujetFormResultat.getStatutDescription())));
		    sujetFormResultat.setDateNaissance(StringUtils.concateneFleche(sujetFormResultat.getDateNaissance(), StringUtils.isDifferent(precedent.getDateNaissance(), sujetFormResultat.getDateNaissance())));
		    //On camoufle l'information du NAS
		    sujetFormResultat.setNumeroAssuranceSociale(StringUtils.concateneFleche(StringUtils.camoufleMotPasse(sujetFormResultat.getNumeroAssuranceSociale()), StringUtils.isDifferent(precedent.getNumeroAssuranceSociale(), sujetFormResultat.getNumeroAssuranceSociale())));
		    sujetFormResultat.setNumeroPermisConduire(StringUtils.concateneFleche(sujetFormResultat.getNumeroPermisConduire(), StringUtils.isDifferent(precedent.getNumeroPermisConduire(), sujetFormResultat.getNumeroPermisConduire())));
		    sujetFormResultat.setReference1(StringUtils.concateneFleche(sujetFormResultat.getReference1(), StringUtils.isDifferent(precedent.getReference1(), sujetFormResultat.getReference1())));
		    sujetFormResultat.setReference2(StringUtils.concateneFleche(sujetFormResultat.getReference2(), StringUtils.isDifferent(precedent.getReference2(), sujetFormResultat.getReference2())));
		    sujetFormResultat.setNumeroFiche(StringUtils.concateneFleche(sujetFormResultat.getNumeroFiche(), StringUtils.isDifferent(precedent.getNumeroFiche(), sujetFormResultat.getNumeroFiche())));
		    sujetFormResultat.setTypeAgeDescription(StringUtils.concateneFleche(sujetFormResultat.getTypeAgeDescription(), StringUtils.isDifferent(precedent.getTypeAgeDescription(), sujetFormResultat.getTypeAgeDescription())));
		    sujetFormResultat.setRaceDescription(StringUtils.concateneFleche(sujetFormResultat.getRaceDescription(), StringUtils.isDifferent(precedent.getRaceDescription(), sujetFormResultat.getRaceDescription())));
		    sujetFormResultat.setPasseport(StringUtils.concateneFleche(sujetFormResultat.getPasseport(), StringUtils.isDifferent(precedent.getPasseport(), sujetFormResultat.getPasseport())));
		    sujetFormResultat.setNumeroAssuranceMaladie(StringUtils.concateneFleche(sujetFormResultat.getNumeroAssuranceMaladie(), StringUtils.isDifferent(precedent.getNumeroAssuranceMaladie(), sujetFormResultat.getNumeroAssuranceMaladie())));
		    sujetFormResultat.setSeveriteDescription(StringUtils.concateneFleche(sujetFormResultat.getSeveriteDescription(), StringUtils.isDifferent(precedent.getSeveriteDescription(), sujetFormResultat.getSeveriteDescription())));
		    sujetFormResultat.setSeveriteCasinoDescription(StringUtils.concateneFleche(sujetFormResultat.getSeveriteCasinoDescription(), StringUtils.isDifferent(precedent.getSeveriteCasinoDescription(), sujetFormResultat.getSeveriteCasinoDescription())));
		    sujetFormResultat.setConfidentialiteDescription(StringUtils.concateneFleche(sujetFormResultat.getConfidentialiteDescription(), StringUtils.isDifferent(precedent.getConfidentialiteDescription(), sujetFormResultat.getConfidentialiteDescription())));
		    sujetFormResultat.setNumeroClientEmploye(StringUtils.concateneFleche(sujetFormResultat.getNumeroClientEmploye(), StringUtils.isDifferent(precedent.getNumeroClientEmploye(), sujetFormResultat.getNumeroClientEmploye())));
		    sujetFormResultat.setEthnieDescription(StringUtils.concateneFleche(sujetFormResultat.getEthnieDescription(), StringUtils.isDifferent(precedent.getEthnieDescription(), sujetFormResultat.getEthnieDescription())));
	        //On ajoute les valeurs modifi�es aux r�sultats.
			Map sujetMapResultat = new HashMap();
			sujetMapResultat = BeanUtils.describe(sujetFormResultat);
	        list.add(sujetMapResultat);
            //On conserve les valeurs (sans les fl�ches d'indication des changements) pour la comparaison avec l'enregistrement suivant.
            precedent = avantChangement;
		}
	  } catch (IllegalAccessException iae) {
	      throw new ValueObjectMapperException(iae);
	  } catch (InvocationTargetException ite) {
	      throw new ValueObjectMapperException(ite);
	  } catch (NoSuchMethodException nsme) {
	      throw new ValueObjectMapperException(nsme);
      } catch (BusinessResourceException bre) {
          bre.printStackTrace();
	  }
	  return list;
}

    private List remplirResultatSocietes(CardexAuthenticationSubject subject, SocieteForm courant, Iterator iter)
	throws ValueObjectMapperException {
	List list = new ArrayList();
	//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
	//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
	//le rapport soient identiques au "describe" des champs de la fonction.
    //On met l'enregistrement courant dans precedent pour amorcer la comparaison des changements.
    SocieteForm precedent = new SocieteForm();
    precedent = courant;
    try{
		while (iter.hasNext()) {
			Societe societe = (Societe)iter.next();
			SocieteForm avantChangement = new SocieteForm();
			SocieteForm societeFormResultat = new SocieteForm();
		    ValueObjectMapper.convertSociete(societe, societeFormResultat,subject.getLocale());
		    societeFormResultat.assignerValeurDeListe(subject);
		    ValueObjectMapper.convertSociete(societe, avantChangement,subject.getLocale());
		    //avantChangement = dossierFormResultat;
		    avantChangement.assignerValeurDeListe(subject);
            //Sur le rapport Jasper, on doit indiquer quels sont les champs qui ont chang�. On doit donc comparer
            //chacun des champs avec l'enregistrement pr�c�dent.
        	//Pour chacun des champs de l'enregistrement courant, on v�rifie s'il y a eu un changement avec l'enregistrement pr�c�dent. Si oui, on ajoute une fl�che � la fin pour l'indiquer sur le rapport.
		    societeFormResultat.setLangueDescription(StringUtils.concateneFleche(societeFormResultat.getLangueDescription(), StringUtils.isDifferent(precedent.getLangueDescription(), societeFormResultat.getLangueDescription())));
		    societeFormResultat.setNom(StringUtils.concateneFleche(societeFormResultat.getNom(), StringUtils.isDifferent(precedent.getNom(), societeFormResultat.getNom())));
		    societeFormResultat.setReferencePrenom(StringUtils.concateneFleche(societeFormResultat.getReferencePrenom(), StringUtils.isDifferent(precedent.getReferencePrenom(), societeFormResultat.getReferencePrenom())));
		    societeFormResultat.setReferenceNom(StringUtils.concateneFleche(societeFormResultat.getReferenceNom().toString(), StringUtils.isDifferent(precedent.getReferenceNom().toString(), societeFormResultat.getReferenceNom().toString())));
		    societeFormResultat.setStatutDescription(StringUtils.concateneFleche(societeFormResultat.getStatutDescription(), StringUtils.isDifferent(precedent.getStatutDescription(), societeFormResultat.getStatutDescription())));
		    societeFormResultat.setDateDeFondation(StringUtils.concateneFleche(societeFormResultat.getDateDeFondation(), StringUtils.isDifferent(precedent.getDateDeFondation(), societeFormResultat.getDateDeFondation())));
		    societeFormResultat.setReference1(StringUtils.concateneFleche(societeFormResultat.getReference1(), StringUtils.isDifferent(precedent.getReference1(), societeFormResultat.getReference1())));
		    societeFormResultat.setReference2(StringUtils.concateneFleche(societeFormResultat.getReference2(), StringUtils.isDifferent(precedent.getReference2(), societeFormResultat.getReference2())));
		    societeFormResultat.setNumeroFiche(StringUtils.concateneFleche(societeFormResultat.getNumeroFiche(), StringUtils.isDifferent(precedent.getNumeroFiche(), societeFormResultat.getNumeroFiche())));
		    societeFormResultat.setClasseDescription(StringUtils.concateneFleche(societeFormResultat.getClasseDescription(), StringUtils.isDifferent(precedent.getClasseDescription(), societeFormResultat.getClasseDescription())));
		    societeFormResultat.setSeveriteDescription(StringUtils.concateneFleche(societeFormResultat.getSeveriteDescription(), StringUtils.isDifferent(precedent.getSeveriteDescription(), societeFormResultat.getSeveriteDescription())));
		    societeFormResultat.setSeveriteCasinoDescription(StringUtils.concateneFleche(societeFormResultat.getSeveriteCasinoDescription(), StringUtils.isDifferent(precedent.getSeveriteCasinoDescription(), societeFormResultat.getSeveriteCasinoDescription())));
		    societeFormResultat.setConfidentialiteDescription(StringUtils.concateneFleche(societeFormResultat.getConfidentialiteDescription(), StringUtils.isDifferent(precedent.getConfidentialiteDescription(), societeFormResultat.getConfidentialiteDescription())));
		    societeFormResultat.setRaisonEtre(StringUtils.concateneFleche(societeFormResultat.getRaisonEtre(), StringUtils.isDifferent(precedent.getRaisonEtre(), societeFormResultat.getRaisonEtre())));
	        //On ajoute les valeurs modifi�es aux r�sultats.
			Map societeMapResultat = new HashMap();
			societeMapResultat = BeanUtils.describe(societeFormResultat);
	        list.add(societeMapResultat);
            //On conserve les valeurs (sans les fl�ches d'indication des changements) pour la comparaison avec l'enregistrement suivant.
            precedent = avantChangement;
		}
	  } catch (IllegalAccessException iae) {
	      throw new ValueObjectMapperException(iae);
	  } catch (InvocationTargetException ite) {
	      throw new ValueObjectMapperException(ite);
	  } catch (NoSuchMethodException nsme) {
	      throw new ValueObjectMapperException(nsme);
      } catch (BusinessResourceException bre) {
          bre.printStackTrace();
	  }
	  return list;
}

    private List remplirResultatVehicules(CardexAuthenticationSubject subject, VehiculeForm courant, Iterator iter)
	throws ValueObjectMapperException {
	List list = new ArrayList();
	//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
	//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
	//le rapport soient identiques au "describe" des champs de la fonction.
    //On met l'enregistrement courant dans precedent pour amorcer la comparaison des changements.
	VehiculeForm precedent = new VehiculeForm();
    precedent = courant;
    try{
		while (iter.hasNext()) {
			Vehicule vehicule = (Vehicule)iter.next();
			VehiculeForm avantChangement = new VehiculeForm();
			VehiculeForm vehiculeFormResultat = new VehiculeForm();
		    ValueObjectMapper.convertVehicule(vehicule, vehiculeFormResultat,subject.getLocale());
		    vehiculeFormResultat.assignerValeurDeListe(subject);
		    ValueObjectMapper.convertVehicule(vehicule, avantChangement,subject.getLocale());
		    //avantChangement = dossierFormResultat;
		    avantChangement.assignerValeurDeListe(subject);
            //Sur le rapport Jasper, on doit indiquer quels sont les champs qui ont chang�. On doit donc comparer
            //chacun des champs avec l'enregistrement pr�c�dent.
        	//Pour chacun des champs de l'enregistrement courant, on v�rifie s'il y a eu un changement avec l'enregistrement pr�c�dent. Si oui, on ajoute une fl�che � la fin pour l'indiquer sur le rapport.
		    vehiculeFormResultat.setConfidentialiteDescription(StringUtils.concateneFleche(vehiculeFormResultat.getConfidentialiteDescription(), StringUtils.isDifferent(precedent.getConfidentialiteDescription(), vehiculeFormResultat.getConfidentialiteDescription())));
		    vehiculeFormResultat.setModeleDescription(StringUtils.concateneFleche(vehiculeFormResultat.getModeleDescription(), StringUtils.isDifferent(precedent.getModeleDescription(), vehiculeFormResultat.getModeleDescription())));
		    vehiculeFormResultat.setMarqueDescription(StringUtils.concateneFleche(vehiculeFormResultat.getMarqueDescription(), StringUtils.isDifferent(precedent.getMarqueDescription(), vehiculeFormResultat.getMarqueDescription())));
		    vehiculeFormResultat.setImmatriculation(StringUtils.concateneFleche(vehiculeFormResultat.getImmatriculation(), StringUtils.isDifferent(precedent.getImmatriculation(), vehiculeFormResultat.getImmatriculation())));
		    vehiculeFormResultat.setProvince(StringUtils.concateneFleche(vehiculeFormResultat.getProvince().toString(), StringUtils.isDifferent(precedent.getProvince().toString(), vehiculeFormResultat.getProvince().toString())));
		    vehiculeFormResultat.setAnnee(StringUtils.concateneFleche(vehiculeFormResultat.getAnnee(), StringUtils.isDifferent(precedent.getAnnee(), vehiculeFormResultat.getAnnee())));
		    vehiculeFormResultat.setVignette(StringUtils.concateneFleche(vehiculeFormResultat.getVignette(), StringUtils.isDifferent(precedent.getVignette(), vehiculeFormResultat.getVignette())));
		    vehiculeFormResultat.setDateExpirationVignette(StringUtils.concateneFleche(vehiculeFormResultat.getDateExpirationVignette(), StringUtils.isDifferent(precedent.getDateExpirationVignette(), vehiculeFormResultat.getDateExpirationVignette())));
		    vehiculeFormResultat.setAssurance(StringUtils.concateneFleche(vehiculeFormResultat.getAssurance(), StringUtils.isDifferent(precedent.getAssurance(), vehiculeFormResultat.getAssurance())));
		    vehiculeFormResultat.setDateExpirationAssurance(StringUtils.concateneFleche(vehiculeFormResultat.getDateExpirationAssurance(), StringUtils.isDifferent(precedent.getDateExpirationAssurance(), vehiculeFormResultat.getDateExpirationAssurance())));
		    vehiculeFormResultat.setPolice(StringUtils.concateneFleche(vehiculeFormResultat.getPolice(), StringUtils.isDifferent(precedent.getPolice(), vehiculeFormResultat.getPolice())));
		    vehiculeFormResultat.setNumeroSerie(StringUtils.concateneFleche(vehiculeFormResultat.getNumeroSerie(), StringUtils.isDifferent(precedent.getNumeroSerie(), vehiculeFormResultat.getNumeroSerie())));
		    vehiculeFormResultat.setCommentaire(StringUtils.concateneFleche(vehiculeFormResultat.getCommentaire().toString(), StringUtils.isDifferent(precedent.getCommentaire().toString(), vehiculeFormResultat.getCommentaire().toString())));
	        //On ajoute les valeurs modifi�es aux r�sultats.
			Map vehiculeMapResultat = new HashMap();
			vehiculeMapResultat = BeanUtils.describe(vehiculeFormResultat);
	        list.add(vehiculeMapResultat);
            //On conserve les valeurs (sans les fl�ches d'indication des changements) pour la comparaison avec l'enregistrement suivant.
            precedent = avantChangement;
		}
	  } catch (IllegalAccessException iae) {
	      throw new ValueObjectMapperException(iae);
	  } catch (InvocationTargetException ite) {
	      throw new ValueObjectMapperException(ite);
	  } catch (NoSuchMethodException nsme) {
	      throw new ValueObjectMapperException(nsme);
      } catch (BusinessResourceException bre) {
          bre.printStackTrace();
	  }
	  return list;
}

    private List remplirResultatNarrations(CardexAuthenticationSubject subject, NarrationForm courant, Iterator iter)
	throws ValueObjectMapperException {
	List list = new ArrayList();
	//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
	//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
	//le rapport soient identiques au "describe" des champs de la fonction.
    //On met l'enregistrement courant dans precedent pour amorcer la comparaison des changements.
	NarrationForm precedent = new NarrationForm();
    precedent = courant;
    try{
		while (iter.hasNext()) {
			Narration narration = (Narration)iter.next();
			NarrationForm avantChangement = new NarrationForm();
			NarrationForm narrationFormResultat = new NarrationForm();
		    ValueObjectMapper.convertNarration(narration, narrationFormResultat,subject.getLocale());
		    narrationFormResultat.assignerValeurDeListe(subject);
		    ValueObjectMapper.convertNarration(narration, avantChangement,subject.getLocale());
		    //avantChangement = dossierFormResultat;
		    avantChangement.assignerValeurDeListe(subject);
            //Sur le rapport Jasper, on doit indiquer quels sont les champs qui ont chang�. On doit donc comparer
            //chacun des champs avec l'enregistrement pr�c�dent.
        	//Pour chacun des champs de l'enregistrement courant, on v�rifie s'il y a eu un changement avec l'enregistrement pr�c�dent. Si oui, on ajoute une fl�che � la fin pour l'indiquer sur le rapport.
		    narrationFormResultat.setConfidentialiteNarrationDescription(StringUtils.concateneFleche(narrationFormResultat.getConfidentialiteNarrationDescription(), StringUtils.isDifferent(precedent.getConfidentialiteNarrationDescription(), narrationFormResultat.getConfidentialiteNarrationDescription())));
		    narrationFormResultat.setApprobateurDescription(StringUtils.concateneFleche(narrationFormResultat.getApprobateurDescription(), StringUtils.isDifferent(precedent.getApprobateurDescription(), narrationFormResultat.getApprobateurDescription())));
		    narrationFormResultat.setRapporteurDescription(StringUtils.concateneFleche(narrationFormResultat.getRapporteurDescription(), StringUtils.isDifferent(precedent.getRapporteurDescription(), narrationFormResultat.getRapporteurDescription())));
		    narrationFormResultat.setMontant(StringUtils.concateneFleche(narrationFormResultat.getMontant(), StringUtils.isDifferent(precedent.getMontant(), narrationFormResultat.getMontant())));
		    narrationFormResultat.setReference(StringUtils.concateneFleche(narrationFormResultat.getReference(), StringUtils.isDifferent(precedent.getReference(), narrationFormResultat.getReference())));
		    narrationFormResultat.setDateApprobation(StringUtils.concateneFleche(narrationFormResultat.getDateApprobation(), StringUtils.isDifferent(precedent.getDateApprobation(), narrationFormResultat.getDateApprobation())));
		    narrationFormResultat.setTempsConsacre(StringUtils.concateneFleche(narrationFormResultat.getTempsConsacre(), StringUtils.isDifferent(precedent.getTempsConsacre(), narrationFormResultat.getTempsConsacre())));
	        //On ajoute les valeurs modifi�es aux r�sultats.
			Map narrationMapResultat = new HashMap();
			narrationMapResultat = BeanUtils.describe(narrationFormResultat);
	        list.add(narrationMapResultat);
            //On conserve les valeurs (sans les fl�ches d'indication des changements) pour la comparaison avec l'enregistrement suivant.
            precedent = avantChangement;
		}
	  } catch (IllegalAccessException iae) {
	      throw new ValueObjectMapperException(iae);
	  } catch (InvocationTargetException ite) {
	      throw new ValueObjectMapperException(ite);
	  } catch (NoSuchMethodException nsme) {
	      throw new ValueObjectMapperException(nsme);
      } catch (BusinessResourceException bre) {
          bre.printStackTrace();
	  }
	  return list;
    }

    private List remplirResultatSuivis(CardexAuthenticationSubject subject, SuiviForm courant, Iterator iter)
	throws ValueObjectMapperException {
	List list = new ArrayList();
	//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
	//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
	//le rapport soient identiques au "describe" des champs de la fonction.
    //On met l'enregistrement courant dans precedent pour amorcer la comparaison des changements.
	SuiviForm precedent = new SuiviForm();
    precedent = courant;
    try{
		while (iter.hasNext()) {
			Suivi narration = (Suivi)iter.next();
			SuiviForm avantChangement = new SuiviForm();
			SuiviForm suiviFormResultat = new SuiviForm();
		    ValueObjectMapper.convertSuivi(narration, suiviFormResultat,subject.getLocale());
		    suiviFormResultat.assignerValeurDeListe(subject);
		    ValueObjectMapper.convertSuivi(narration, avantChangement,subject.getLocale());
		    //avantChangement = dossierFormResultat;
		    avantChangement.assignerValeurDeListe(subject);
            //Sur le rapport Jasper, on doit indiquer quels sont les champs qui ont chang�. On doit donc comparer
            //chacun des champs avec l'enregistrement pr�c�dent.
        	//Pour chacun des champs de l'enregistrement courant, on v�rifie s'il y a eu un changement avec l'enregistrement pr�c�dent. Si oui, on ajoute une fl�che � la fin pour l'indiquer sur le rapport.
		    suiviFormResultat.setConfidentialiteSuiviDescription(StringUtils.concateneFleche(suiviFormResultat.getConfidentialiteSuiviDescription(), StringUtils.isDifferent(precedent.getConfidentialiteSuiviDescription(), suiviFormResultat.getConfidentialiteSuiviDescription())));
		    suiviFormResultat.setApprobateurDescription(StringUtils.concateneFleche(suiviFormResultat.getApprobateurDescription(), StringUtils.isDifferent(precedent.getApprobateurDescription(), suiviFormResultat.getApprobateurDescription())));
		    suiviFormResultat.setDemandeurDescription(StringUtils.concateneFleche(suiviFormResultat.getDemandeurDescription(), StringUtils.isDifferent(precedent.getDemandeurDescription(), suiviFormResultat.getDemandeurDescription())));
		    suiviFormResultat.setSecteurOrigineDescription(StringUtils.concateneFleche(suiviFormResultat.getSecteurOrigineDescription(), StringUtils.isDifferent(precedent.getSecteurOrigineDescription(), suiviFormResultat.getSecteurOrigineDescription())));
		    suiviFormResultat.setIntervenantDescription(StringUtils.concateneFleche(suiviFormResultat.getIntervenantDescription(), StringUtils.isDifferent(precedent.getIntervenantDescription(), suiviFormResultat.getIntervenantDescription())));
		    suiviFormResultat.setSecteurAssigneDescription(StringUtils.concateneFleche(suiviFormResultat.getSecteurAssigneDescription(), StringUtils.isDifferent(precedent.getSecteurAssigneDescription(), suiviFormResultat.getSecteurAssigneDescription())));
		    suiviFormResultat.setActiviteDescription(StringUtils.concateneFleche(suiviFormResultat.getActiviteDescription(), StringUtils.isDifferent(precedent.getActiviteDescription(), suiviFormResultat.getActiviteDescription())));
		    suiviFormResultat.setStatutDescription(StringUtils.concateneFleche(suiviFormResultat.getStatutDescription(), StringUtils.isDifferent(precedent.getStatutDescription(), suiviFormResultat.getStatutDescription())));
		    suiviFormResultat.setDatePrevue(StringUtils.concateneFleche(suiviFormResultat.getDatePrevue(), StringUtils.isDifferent(precedent.getDatePrevue(), suiviFormResultat.getDatePrevue())));
		    suiviFormResultat.setDateCompletee(StringUtils.concateneFleche(suiviFormResultat.getDateCompletee(), StringUtils.isDifferent(precedent.getDateCompletee(), suiviFormResultat.getDateCompletee())));
		    suiviFormResultat.setDateApprobation(StringUtils.concateneFleche(suiviFormResultat.getDateApprobation(), StringUtils.isDifferent(precedent.getDateApprobation(), suiviFormResultat.getDateApprobation())));
		    suiviFormResultat.setSuivi(StringUtils.concateneFleche(suiviFormResultat.getSuivi(), StringUtils.isDifferent(precedent.getSuivi(), suiviFormResultat.getSuivi())));
	        //On ajoute les valeurs modifi�es aux r�sultats.
			Map suiviMapResultat = new HashMap();
			suiviMapResultat = BeanUtils.describe(suiviFormResultat);
	        list.add(suiviMapResultat);
            //On conserve les valeurs (sans les fl�ches d'indication des changements) pour la comparaison avec l'enregistrement suivant.
            precedent = avantChangement;
		}
	  } catch (IllegalAccessException iae) {
	      throw new ValueObjectMapperException(iae);
	  } catch (InvocationTargetException ite) {
	      throw new ValueObjectMapperException(ite);
	  } catch (NoSuchMethodException nsme) {
	      throw new ValueObjectMapperException(nsme);
      } catch (BusinessResourceException bre) {
          bre.printStackTrace();
	  }
	  return list;
    }

    private List remplirResultatAdresses(CardexAuthenticationSubject subject, AdresseForm courant, Iterator iter)
	throws ValueObjectMapperException {
	List list = new ArrayList();
	//On r�cup�re les valeurs directement dans un Map avec la fonction BeanUtils.describe.
	//Cela �vite de traiter les donn�es champ par champ. Il faut cependant que les noms de champ utilis�s dans
	//le rapport soient identiques au "describe" des champs de la fonction.
    //On met l'enregistrement courant dans precedent pour amorcer la comparaison des changements.
	AdresseForm precedent = new AdresseForm();
    precedent = courant;
    try{
		while (iter.hasNext()) {
			Adresse adresse = (Adresse)iter.next();
			AdresseForm avantChangement = new AdresseForm();
			AdresseForm adresseFormResultat = new AdresseForm();
		    ValueObjectMapper.convertAdresse(adresse, adresseFormResultat,subject.getLocale());
		    adresseFormResultat.assignerValeurDeListe(subject);
		    ValueObjectMapper.convertAdresse(adresse, avantChangement,subject.getLocale());
		    //avantChangement = dossierFormResultat;
		    avantChangement.assignerValeurDeListe(subject);
            //Sur le rapport Jasper, on doit indiquer quels sont les champs qui ont chang�. On doit donc comparer
            //chacun des champs avec l'enregistrement pr�c�dent.
        	//Pour chacun des champs de l'enregistrement courant, on v�rifie s'il y a eu un changement avec l'enregistrement pr�c�dent. Si oui, on ajoute une fl�che � la fin pour l'indiquer sur le rapport.
		    adresseFormResultat.setNumeroMunicipal(StringUtils.concateneFleche(adresseFormResultat.getNumeroMunicipal(), StringUtils.isDifferent(precedent.getNumeroMunicipal(), adresseFormResultat.getNumeroMunicipal())));
		    adresseFormResultat.setTypeRueDescription(StringUtils.concateneFleche(adresseFormResultat.getTypeRueDescription(), StringUtils.isDifferent(precedent.getTypeRueDescription(), adresseFormResultat.getTypeRueDescription())));
		    adresseFormResultat.setNomRue(StringUtils.concateneFleche(adresseFormResultat.getNomRue(), StringUtils.isDifferent(precedent.getNomRue(), adresseFormResultat.getNomRue())));
		    adresseFormResultat.setPointCardinalDescription(StringUtils.concateneFleche(adresseFormResultat.getPointCardinalDescription(), StringUtils.isDifferent(precedent.getPointCardinalDescription(), adresseFormResultat.getPointCardinalDescription())));
		    adresseFormResultat.setUniteDescription(StringUtils.concateneFleche(adresseFormResultat.getUniteDescription(), StringUtils.isDifferent(precedent.getUniteDescription(), adresseFormResultat.getUniteDescription())));
		    adresseFormResultat.setNumeroUnite(StringUtils.concateneFleche(adresseFormResultat.getNumeroUnite(), StringUtils.isDifferent(precedent.getNumeroUnite(), adresseFormResultat.getNumeroUnite())));
		    adresseFormResultat.setAdressePostal(StringUtils.concateneFleche(adresseFormResultat.getAdressePostal(), StringUtils.isDifferent(precedent.getAdressePostal(), adresseFormResultat.getAdressePostal())));
		    adresseFormResultat.setPaysDescription(StringUtils.concateneFleche(adresseFormResultat.getPaysDescription(), StringUtils.isDifferent(precedent.getPaysDescription(), adresseFormResultat.getPaysDescription())));
		    adresseFormResultat.setProvinceDescription(StringUtils.concateneFleche(adresseFormResultat.getProvinceDescription(), StringUtils.isDifferent(precedent.getProvinceDescription(), adresseFormResultat.getProvinceDescription())));
		    adresseFormResultat.setVilleDescription(StringUtils.concateneFleche(adresseFormResultat.getVilleDescription(), StringUtils.isDifferent(precedent.getVilleDescription(), adresseFormResultat.getVilleDescription())));
		    adresseFormResultat.setTelephone1(StringUtils.concateneFleche(adresseFormResultat.getTelephone1(), StringUtils.isDifferent(precedent.getTelephone1(), adresseFormResultat.getTelephone1())));
		    adresseFormResultat.setTelephone2(StringUtils.concateneFleche(adresseFormResultat.getTelephone2(), StringUtils.isDifferent(precedent.getTelephone2(), adresseFormResultat.getTelephone2())));
		    adresseFormResultat.setTelephone3(StringUtils.concateneFleche(adresseFormResultat.getTelephone3(), StringUtils.isDifferent(precedent.getTelephone3(), adresseFormResultat.getTelephone3())));
		    adresseFormResultat.setCodePostal(StringUtils.concateneFleche(adresseFormResultat.getCodePostal(), StringUtils.isDifferent(precedent.getCodePostal(), adresseFormResultat.getCodePostal())));
		    adresseFormResultat.setStatutDescription(StringUtils.concateneFleche(adresseFormResultat.getStatutDescription(), StringUtils.isDifferent(precedent.getStatutDescription(), adresseFormResultat.getStatutDescription())));
		    adresseFormResultat.setAdresseElectronique1(StringUtils.concateneFleche(adresseFormResultat.getAdresseElectronique1(), StringUtils.isDifferent(precedent.getAdresseElectronique1(), adresseFormResultat.getAdresseElectronique1())));
		    adresseFormResultat.setAdresseElectronique2(StringUtils.concateneFleche(adresseFormResultat.getAdresseElectronique2(), StringUtils.isDifferent(precedent.getAdresseElectronique2(), adresseFormResultat.getAdresseElectronique2())));
		    adresseFormResultat.setCommentaire(StringUtils.concateneFleche(adresseFormResultat.getCommentaire(), StringUtils.isDifferent(precedent.getCommentaire(), adresseFormResultat.getCommentaire())));
	        //On ajoute les valeurs modifi�es aux r�sultats.
			Map adresseMapResultat = new HashMap();
			adresseMapResultat = BeanUtils.describe(adresseFormResultat);
	        list.add(adresseMapResultat);
            //On conserve les valeurs (sans les fl�ches d'indication des changements) pour la comparaison avec l'enregistrement suivant.
            precedent = avantChangement;
		}
	  } catch (IllegalAccessException iae) {
	      throw new ValueObjectMapperException(iae);
	  } catch (InvocationTargetException ite) {
	      throw new ValueObjectMapperException(ite);
	  } catch (NoSuchMethodException nsme) {
	      throw new ValueObjectMapperException(nsme);
      } catch (BusinessResourceException bre) {
          bre.printStackTrace();
	  }
	  return list;
    }
}