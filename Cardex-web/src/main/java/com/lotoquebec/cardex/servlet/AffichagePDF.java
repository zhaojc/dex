package com.lotoquebec.cardex.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionErrors;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.CriteresRechercheDossierVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheAdressesForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheDossierForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheSocieteForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheSujetForm;
import com.lotoquebec.cardex.presentation.model.form.CriteresRechercheVehiculeForm;
import com.lotoquebec.cardex.presentation.model.form.DossierForm;
import com.lotoquebec.cardex.presentation.model.form.SocieteForm;
import com.lotoquebec.cardex.presentation.model.form.SujetForm;
import com.lotoquebec.cardex.presentation.model.form.VehiculeForm;
import com.lotoquebec.cardex.presentation.rapport.RapportAssociation;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.exception.ValueObjectMapperException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.NatureCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Ce servlet sert à afficher les rapports Jasper en format PDF à l'écran.
 * Il sert pour tous les rapports disponibles dans la liste déroulante de l'écran de recherche des dossiers.
 * Le code repose sur le concept AJAX (Asynchronous JavaScript and XML) et sur les librairies Jasper.
 * @date : novembre 2008
 */
public class AffichagePDF extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {

        /*ActionErrors errors = new ActionErrors();
        
        CriteresRechercheDossierVO criteresRechercheDossier = new CriteresRechercheDossierVO();
        ResultSet resultSet = null;
        Map parameters = new HashMap();
        Connection connection = null;
        List liste = new ArrayList();
        ServletOutputStream servletOutputStream = response.getOutputStream();
        JasperPrint print = new JasperPrint();
        try {
            connection = DAOConnection.getInstance().getConnection();
        	String choixRapport = (String)request.getParameter("RAPPORT");
        	String site = (String)request.getParameter("SITE");
        	String dateDebut = (String)request.getParameter("DATE_DEBUT");
        	String dateFin = (String)request.getParameter("DATE_FIN");
           	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
           	
           	GestionnaireSecuriteCardex.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
           	String roleAdhoc = RapportAssociation.getRoleAdhoc(choixRapport);
           	
           	if (StringUtils.isEmpty(roleAdhoc)){
	           	GestionnaireSecuriteCardex.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.RAPPORT_RECHERCHE_DOSSIER, "", GlobalConstants.ActionSecurite.TOUTES_ACTIONS), choixRapport);
	           	choixRapport = RapportAssociation.obtenirFichierJasper(Integer.valueOf(choixRapport));
           	}else
           		GestionnaireSecuriteCardex.validerSecuriteAdhoc(subject, roleAdhoc);
        	String utilisateur = (String)request.getParameter("UTILISATEUR");
        	String genre = (String)request.getParameter("GENRE");
        	String categorie = (String)request.getParameter("CATEGORIE");
        	String nature = (String)request.getParameter("NATURE");
        	String procedure = "";
         	
         	if (StringUtils.isNotEmpty(site))
         		criteresRechercheDossier.setSiteOrigine(Long.parseLong(site));
         	
         	if (StringUtils.isNotEmpty(nature))
         		criteresRechercheDossier.setNature(Long.valueOf(nature));
         	
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
			criteresRechercheDossier.setDateDebutDu(TimestampFormat.parse(dateDebut, new Locale("fr"), true));
			criteresRechercheDossier.setDateDebutAu(TimestampFormat.parse(dateFin, new Locale("fr"), true));
			criteresRechercheDossier.setIntervenant(utilisateur);
			
            RapportBusinessDelegate delegate = new RapportBusinessDelegate();
	         ServletContext context = request.getSession().getServletContext();  
	         //System.out.println(context.getRealPath("/rapports/"));
	         //On inscrit les paramètres des rapports
	         parameters.put("SUBREPORT_DIR",context.getRealPath("/rapports/"));
	         parameters.put("REPORT_CONNECTION",connection);
	         parameters.put("DATE_DEBUT",dateDebut);
	         parameters.put("DATE_FIN",dateFin);
	         parameters.put("UTILISATEUR",subject.getPrincipal().getName());
	         
	         //Exécution du rapport choisi
        	if (GlobalConstants.ChoixRapport.REPERAGES_AUTOEXCLUSIONS_PAR_NUMERO.equals( choixRapport )){
        		//Au moins un repérage
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.REPERAGES_AUTOEXCLUSIONS_PAR_NUMERO;
               	procedure = "CARDEX_RAPPORT.SP_RAP_REPERAGE_AU_TRI_NUMERO";
    			//Utilisation d'un resultSet comme source de données
               	resultSet = delegate.rapportReperageAutoexclusion(criteresRechercheDossier, procedure, 1);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
            }
        	if (GlobalConstants.ChoixRapport.REPERAGES_AUTOEXCLUSIONS_PAR_NOMBRE.equals( choixRapport )){
        		//Au moins un repérage
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.REPERAGES_AUTOEXCLUSIONS_PAR_NOMBRE;
               	procedure = "CARDEX_RAPPORT.SP_RAP_REPERAGE_AU_TRI_NOMBRE";
               	resultSet = delegate.rapportReperageAutoexclusion(criteresRechercheDossier, procedure, 1);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
            }
        	if (GlobalConstants.ChoixRapport.CINQ_REPERAGES_AUTOEXCLUSIONS_PAR_NUMERO.equals( choixRapport )){
        		//Au moins cinq repérages
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.REPERAGES_AUTOEXCLUSIONS_PAR_NUMERO;
               	procedure = "CARDEX_RAPPORT.SP_RAP_REPERAGE_AU_TRI_NUMERO";
    			//Utilisation d'un resultSet comme source de données
               	resultSet = delegate.rapportReperageAutoexclusion(criteresRechercheDossier, procedure, 5);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
            }
        	if (GlobalConstants.ChoixRapport.CINQ_REPERAGES_AUTOEXCLUSIONS_PAR_NOMBRE.equals( choixRapport )){
        		//Au moins cinq repérages
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.REPERAGES_AUTOEXCLUSIONS_PAR_NOMBRE;
               	procedure = "CARDEX_RAPPORT.SP_RAP_REPERAGE_AU_TRI_NOMBRE";
               	resultSet = delegate.rapportReperageAutoexclusion(criteresRechercheDossier, procedure, 5);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
            }
        	if (GlobalConstants.ChoixRapport.REPERAGES_ACCES_INTERDITS.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.REPERAGES_ACCES_INTERDITS;
               	procedure = "CARDEX_RAPPORT.SP_RAP_REP_ACCES_INTERDITS";
               	resultSet = delegate.rapportReperage(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
            }
        	if (GlobalConstants.ChoixRapport.REPERAGES_ACCES_INTERDITS_NOMBRE.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.REPERAGES_ACCES_INTERDITS_NOMBRE;
               	procedure = "CARDEX_RAPPORT.SP_RAP_REP_AI_TRI_NOMBRE";
               	resultSet = delegate.rapportReperage(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
            }
        	if (GlobalConstants.ChoixRapport.REPERAGES_AVIS_DE_GUET.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.REPERAGES_AVIS_DE_GUET;
               	procedure = "CARDEX_RAPPORT.SP_RAP_REPERAGE_AVIS_GUET";
               	resultSet = delegate.rapportReperage(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
            }
			if (GlobalConstants.ChoixRapport.MENSUEL_AUTOEXCLUSIONS.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.MENSUEL_AUTOEXCLUSIONS;
               	procedure = "CARDEX_RAPPORT.SP_RAP_MENSUEL_AUTOEXCLUSIONS";
               	if(StringUtils.isEmpty(categorie)){
               		categorie = "0";
               	}
               	criteresRechercheDossier.setCategorie(Long.parseLong(categorie));
               	resultSet = delegate.rapportContrats(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
            }
         	if (GlobalConstants.ChoixRapport.MENSUEL_RENCONTRES.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.MENSUEL_RENCONTRES;
               	procedure = "CARDEX_RAPPORT.SP_RAPPORT_RENCONTRES";
               	resultSet = delegate.rapportRencontres(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
            }
         	if (GlobalConstants.ChoixRapport.MENSUEL_RENCONTRES_DETAIL.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.MENSUEL_RENCONTRES_DETAIL;
               	procedure = "CARDEX_RAPPORT.SP_RAPPORT_RENCONTRES_DETAIL";
               	resultSet = delegate.rapportRencontres(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
            }
         	if (GlobalConstants.ChoixRapport.MENSUEL_RENCONTRES_FINALES.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.MENSUEL_RENCONTRES_FINALES;
               	procedure = "CARDEX_RAPPORT.SP_RAPPORT_RENCONTRES_FINALES";
               	resultSet = delegate.rapportRencontres(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
            }
         	if (GlobalConstants.ChoixRapport.MENSUEL_RENCONTRES_FINALES_DETAIL.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.MENSUEL_RENCONTRES_FINALES_DETAIL;
               	procedure = "CARDEX_RAPPORT.SP_MENSUEL_RENC_FINALES_DETAIL";
               	resultSet = delegate.rapportRencontres(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
            }
        	if (GlobalConstants.ChoixRapport.REGISTRE_RFC_MAITRE.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.REGISTRE_RFC_MAITRE;
               	procedure = "CARDEX_RAPPORT.SP_RAP_REGISTRE_RFC_MAITRE";
               	resultSet = delegate.rapportRencontres(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
            }
        	if (GlobalConstants.ChoixRapport.STATUT_DOSSIERS.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.STATUT_DOSSIERS;
               	procedure = "CARDEX_RAPPORT.SP_RAP_STATUT_DOSSIERS";
               	parameters.put("NATURE",nature);
               	resultSet = delegate.statutDossiers(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
        	}
        	if (GlobalConstants.ChoixRapport.CUMUL_HEBDOMADAIRE.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.CUMUL_HEBDOMADAIRE;
               	procedure = "CARDEX_RAPPORT.SP_RAP_CUMUL_HEBDO";
               	resultSet = delegate.rapportReperage(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
        	}
        	if (GlobalConstants.ChoixRapport.CUMUL_ANNUEL.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.CUMUL_ANNUEL;
    			InputStream gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
               	procedure = "CARDEX_RAPPORT.SP_RAP_CUMUL_ANNUEL";
               	resultSet = delegate.rapportCumulAnnuel(criteresRechercheDossier,procedure);
               	//Dans le cas d'un rapport de cumul, on utilise une liste au lieu d'un ResultSet parce que le ResultSet 
               	//ne peut pas être utilisé directement dans le rapport
       		    liste = remplirCumul(resultSet);
       		    JRDataSource dataSource = new JRMapCollectionDataSource(liste);
       		    print = JasperFillManager.fillReport(gabarit, parameters, dataSource);
        	}
        	if (GlobalConstants.ChoixRapport.RAPPORT_PAR.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.RAPPORT_PAR;
    			InputStream gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
               	procedure = "CARDEX_RAPPORT.SP_RAPPORT_PAR";
               	liste = remplirPAR(dateDebut, procedure, resultSet, delegate);
        	    //On utilise également une liste pour ce rapport.
	      		JRDataSource dataSource = new JRMapCollectionDataSource(liste);
	    		print = JasperFillManager.fillReport(gabarit, parameters, dataSource);
        	}
        	if (GlobalConstants.ChoixRapport.TEMPS_CONSACRE.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.TEMPS_CONSACRE;
               	procedure = "CARDEX_RAPPORT.SP_TEMPS_CONSACRE";
               	criteresRechercheDossier.setGenre(Long.valueOf(genre));
               	resultSet = delegate.tempsConsacre(criteresRechercheDossier, procedure);
               	print = traitementResultSet(resultSet, parameters, choixRapport);
        	}


        	//Affichage à l'écran
	         response.setContentType("application/pdf");
	         request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, print);	         
	         JRExporter exporter = new JRPdfExporter();
	         exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
	         exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
	         exporter.exportReport();
	         servletOutputStream.flush();
	         servletOutputStream.close();

      } catch (SQLException se) {
  			//System.out.println(se.getMessage());
  			se.printStackTrace();
      } catch (DAOException se) {
			//System.out.println(se.getMessage());
			se.printStackTrace();
      } catch (ParseException pe) {
          pe.printStackTrace();
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
        }*/
	}

    private List remplirCumul(ResultSet resultSet)
    		throws SQLException{
    	  List list = new ArrayList();

    	  try{
    	  //Le nombre d'entrée retourné est fixe. On traite chacune des entrées en fonction de la nature des enquêtes.
          //Nature Événements
    	  Map evenements = new HashMap();
    	  resultSet.next();
    	  evenements.put("NATURE", resultSet.getString(1));
    	  evenements.put("ENQUETES_OUVERTS_COURANT", resultSet.getBigDecimal(2));
    	  resultSet.next();
    	  evenements.put("NATURE", resultSet.getString(1));
    	  evenements.put("ENQUETES_OUVERTS_PASSES", resultSet.getBigDecimal(2));
    	  resultSet.next();
    	  evenements.put("NATURE", resultSet.getString(1));
    	  evenements.put("ENQUETES_FERMES_COURANT", resultSet.getBigDecimal(2));
    	  resultSet.next();
    	  evenements.put("NATURE", resultSet.getString(1));
    	  evenements.put("ENQUETES_FERMES_PASSES", resultSet.getBigDecimal(2));
    	  list.add(evenements);
    	  //Nature Plaintes
    	  Map plaintes = new HashMap();
    	  resultSet.next();
    	  plaintes.put("NATURE", resultSet.getString(1));
    	  plaintes.put("ENQUETES_OUVERTS_COURANT", resultSet.getBigDecimal(2));
    	  resultSet.next();
    	  plaintes.put("NATURE", resultSet.getString(1));
    	  plaintes.put("ENQUETES_OUVERTS_PASSES", resultSet.getBigDecimal(2));
    	  resultSet.next();
    	  plaintes.put("NATURE", resultSet.getString(1));
    	  plaintes.put("ENQUETES_FERMES_COURANT", resultSet.getBigDecimal(2));
    	  resultSet.next();
    	  plaintes.put("NATURE", resultSet.getString(1));
    	  plaintes.put("ENQUETES_FERMES_PASSES", resultSet.getBigDecimal(2));
    	  list.add(plaintes);
    	  //Nature Réclamations
    	  Map reclamations = new HashMap();
    	  resultSet.next();
    	  reclamations.put("NATURE", resultSet.getString(1));
    	  reclamations.put("ENQUETES_OUVERTS_COURANT", resultSet.getBigDecimal(2));
    	  resultSet.next();
    	  reclamations.put("NATURE", resultSet.getString(1));
    	  reclamations.put("ENQUETES_OUVERTS_PASSES", resultSet.getBigDecimal(2));
    	  resultSet.next();
    	  reclamations.put("NATURE", resultSet.getString(1));
    	  reclamations.put("ENQUETES_FERMES_COURANT", resultSet.getBigDecimal(2));
    	  resultSet.next();
    	  reclamations.put("NATURE", resultSet.getString(1));
    	  reclamations.put("ENQUETES_FERMES_PASSES", resultSet.getBigDecimal(2));
    	  list.add(reclamations);

		  } catch (SQLException se) {
				//System.out.println(se.getMessage());
				se.printStackTrace();
	      }
		  return list;
	  }

    private List remplirPAR(String dateDebut, String procedure, ResultSet resultSet, RapportBusinessDelegate delegate)
	throws SQLException{
    	List list = new ArrayList();
    	String sAnnee = "";
    	String sMoisNombre = "";
    	try{
   		 	Calendar calendar = new GregorianCalendar();
   		 	SimpleDateFormat annee = new SimpleDateFormat("yyyy");
   		 	SimpleDateFormat moisNombre = new SimpleDateFormat("MM");
   		 	SimpleDateFormat mois = new SimpleDateFormat("MMMM", java.util.Locale.FRENCH);
   		 	//Pour ce rapport, il faut remonter au début de l'année financière. Si le mois courant est inférieur à avril,
   		 	//la date de départ sera avril de l'année précédente. Sinon, avril de l'année en cours.
   		 	//On vérifie d'abord si un critère de date a été saisi.
   		 	if(StringUtils.isNotEmpty(dateDebut)){
   		 	   sAnnee = dateDebut.substring(0,4);
   		 	   sMoisNombre = dateDebut.substring(5,7);
   		 	}else{//Sinon, on prend la date du jour.
   		 		sAnnee = annee.format(calendar.getTime()); 
   		 		sMoisNombre = moisNombre.format(calendar.getTime());
   		 	}
    		if(5 > Integer.parseInt(sMoisNombre)){
    			int anneePrecedente = Integer.parseInt(sAnnee)-1 ;
    			calendar = new GregorianCalendar(anneePrecedente,3,1);
    		}else{
    			calendar = new GregorianCalendar(Integer.parseInt(sAnnee),3,1);
    		}
    		for(int i=0;i<=11;i++){
    			Map enquetesPAR = new HashMap();
	        	sAnnee = annee.format(calendar.getTime()); 
	    		sMoisNombre = moisNombre.format(calendar.getTime()); 
	    		String sMois = mois.format(calendar.getTime()); 
	    		String periode = sAnnee + "-" + sMoisNombre;
	    		//resultSet = delegate.rapportPAR(periode, procedure);
	    		resultSet.next();
	    		enquetesPAR.put("ANNEE", sAnnee);
	    		enquetesPAR.put("MOIS", sMois);
	    		enquetesPAR.put("TOTAL", resultSet.getBigDecimal(2));
	    		resultSet.next();
	    		enquetesPAR.put("PAR_MOINS_10000", resultSet.getBigDecimal(2));
	    		resultSet.next();
	    		enquetesPAR.put("PAR_PLUS_10000", resultSet.getBigDecimal(2));
	    		resultSet.next();
	    		enquetesPAR.put("NON_PAR", resultSet.getBigDecimal(2));
	      	    list.add(enquetesPAR);
    			calendar.add(Calendar.MONTH,1);
    		}
    	} catch (SQLException se) {
			//System.out.println(se.getMessage());
			se.printStackTrace();
        } /*catch (BusinessResourceException bre) {
            bre.printStackTrace();
        } catch (BusinessException be) {
            be.printStackTrace();
        }*/
        finally {
			if(resultSet != null) {
				try {
						resultSet.close();
			    } catch (java.sql.SQLException e) {
			    	e.printStackTrace();
		        }
	        }
        }
  	  return list;
    }

  private List remplirResultatDossiers(Iterator iter){
	  List list = new ArrayList();
	
	while (iter.hasNext()) {
		Map dossier = new HashMap();
		DossierForm dossierForm = (DossierForm) iter.next();
		dossier.put("TypeDescription", dossierForm.getTypeDescription());
		dossier.put("CategorieDescription", dossierForm.getCategorieDescription());
		dossier.put("Numero_Cardex",dossierForm.getNumeroCardex().toString());
		dossier.put("Numero_dossier",dossierForm.getNumeroDossier());
		dossier.put("Reference_1", dossierForm.getReference1());
		dossier.put("Reference_2", dossierForm.getReference2());
		dossier.put("Reference_3", dossierForm.getReference3());
		dossier.put("Date_debut", dossierForm.getDateDebut());
		dossier.put("Date_fin", dossierForm.getDateFin());
		dossier.put("Periode", dossierForm.getPeriodeDescription());
		dossier.put("Statut", dossierForm.getStatutDescription());
		dossier.put("Intervenant", dossierForm.getIntervenant());
		dossier.put("Severite", dossierForm.getSeveriteDescription());
		dossier.put("Confidentialite", dossierForm.getConfidentialiteDescription());
		list.add(dossier);
	}

  return list;
 }

  private List remplirResultatSujets(Iterator iter)
  	throws ValueObjectMapperException {
	  List list = new ArrayList();
//On récupère les valeurs directement dans un Map avec la fonction BeanUtils.describe.
//Cela évite de traiter les données champ par champ. Il faut cependant que les noms de champ utilisés dans
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
//On récupère les valeurs directement dans un Map avec la fonction BeanUtils.describe.
//Cela évite de traiter les données champ par champ. Il faut cependant que les noms de champ utilisés dans
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
//On récupère les valeurs directement dans un Map avec la fonction BeanUtils.describe.
//Cela évite de traiter les données champ par champ. Il faut cependant que les noms de champ utilisés dans
//le rapport soient identiques au "describe" des champs de la fonction.	  
	try{
	  while (iter.hasNext()) {
		VehiculeForm vehiculeForm = (VehiculeForm) iter.next();
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