package com.lotoquebec.cardex.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.lotoquebec.cardex.business.CriteresRechercheJournal;
import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.CriteresRechercheJournalVO;
import com.lotoquebec.cardex.presentation.rapport.RapportAssociation;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AuthenticationSubject;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.JDBCTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.util.StringHelper;
import com.lotoquebec.cardexCommun.util.StringUtils;
import com.lotoquebec.cardexCommun.util.VerificationSyntaxe;

/**
 * Ce servlet sert à afficher les rapports Jasper en format PDF à l'écran.
 * Il sert pour le journal.
 * Le code repose sur le concept AJAX (Asynchronous JavaScript and XML) et sur les librairies Jasper.
 * @date : novembre 2008
 */
public class AffichagePDFJournal extends HttpServlet {

	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	
    public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {
        /*CriteresRechercheJournalVO criteresRechercheJournal = new CriteresRechercheJournalVO();
        ResultSet resultSet = null;
        Map parameters = new HashMap();
        Connection connection = null;
        ServletOutputStream servletOutputStream = response.getOutputStream();
     	String procedure = "";
        try {
            connection = DAOConnection.getInstance().getConnection();
            //Lecture des paramètres du rapport.
        	String choixRapport = (String)request.getParameter("RAPPORT");
        	String site = (String)request.getParameter("SITE");
        	String dateDebut = (String)request.getParameter("DATE_DEBUT");
        	String dateFin = (String)request.getParameter("DATE_FIN");
           	CardexAuthenticationSubject subject = (CardexAuthenticationSubject)request.getSession().getAttribute(AuthenticationSubject.class.getName());
           	
           	GestionnaireSecuriteCardex.validerSecuriteURL((CardexAuthenticationSubject) subject, request.getServletPath());
           	GestionnaireSecuriteCardex.validerValeurAccessible(subject, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.RAPPORT_RECHERCHE_JOURNAL, "", GlobalConstants.ActionSecurite.TOUTES_ACTIONS), choixRapport);
           	choixRapport = RapportAssociation.obtenirFichierJasper(Integer.valueOf(choixRapport));           	
           	
           	String utilisateur = subject.getPrincipal().getName();
         	String intervenant = (String)request.getParameter("INTERVENANT");
        	String genre = (String)request.getParameter("GENRE");
        	String nature = (String)request.getParameter("NATURE");
        	String type = (String)request.getParameter("TYPE");
        	String categorie = (String)request.getParameter("CATEGORIE");
        	String origine = (String)request.getParameter("ORIGINE");
        	String entite = (String)request.getParameter("ENTITE");
        	String secteur = (String)request.getParameter("SECTEUR");
        	String descriptif = (String)request.getParameter("DESCRIPTIF");
        	String fonde = (String)request.getParameter("FONDE");
        	String endroit = (String)request.getParameter("ENDROIT");
        	String localisation = (String)request.getParameter("LOCALISATION");
            RapportBusinessDelegate delegate = new RapportBusinessDelegate();
            
			 if (!StringUtils.isEmpty(site)){
				 criteresRechercheJournal.setSite(Long.parseLong(site));
			 }
			 if (!StringUtils.isEmpty(entite)){
				 criteresRechercheJournal.setEntite(Long.parseLong(entite));
			 }
			 if (!StringUtils.isEmpty(fonde)){
				 criteresRechercheJournal.setFonde(Long.parseLong(fonde));
			 }
			 if (!StringUtils.isEmpty(endroit)){
				 criteresRechercheJournal.setEndroit(Long.parseLong(endroit));
			 }
			 if (!StringUtils.isEmpty(categorie)){
				 criteresRechercheJournal.setCategorie(Long.parseLong(categorie));
			 }
			 if (!StringUtils.isEmpty(type)){
				 criteresRechercheJournal.setType(Long.parseLong(type));
			 }
			 if (!StringUtils.isEmpty(genre)){
				 criteresRechercheJournal.setGenre(Long.parseLong(genre));
			 }
			 if (!StringUtils.isEmpty(nature)){
				 criteresRechercheJournal.setNature(Long.parseLong(nature));
			 }
			 if (!StringUtils.isEmpty(localisation)){
				 criteresRechercheJournal.setLocalisation(Long.parseLong(localisation));
			 }
			 if (StringUtils.isNotEmpty(origine)){
				 criteresRechercheJournal.setOrigine(Long.valueOf(origine));
			 }
			 
			 criteresRechercheJournal.setDescriptif(descriptif);
			 criteresRechercheJournal.setIntervenant(intervenant);
			 criteresRechercheJournal.setSecteur(secteur);
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
			 criteresRechercheJournal.setDateCreationDu(TimestampFormat.parse(dateDebut, new Locale("fr"), true));
			 criteresRechercheJournal.setDateCreationAu(TimestampFormat.parse(dateFin, new Locale("fr"), true));
			 
			if (GlobalConstants.Impression.JOURNAL_SOMMAIRE.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.JOURNAL_SOMMAIRE;
            }
        	if (GlobalConstants.Impression.JOURNAL_DETAIL.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.JOURNAL_DETAIL;
            }
        	if (GlobalConstants.Impression.JOURNAL_ORIGINE.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.JOURNAL_ORIGINE;
            }
        	if (GlobalConstants.Impression.JOURNAL_DESCRIPTIF.equals( choixRapport )){
               	choixRapport = "rapports/" + GlobalConstants.ChoixRapport.JOURNAL_DESCRIPTIF;
            }
        	if (GlobalConstants.ChoixRapport.JOURNAL_ENQUETES.equals( choixRapport )){
               	//Utilisation d'un resultSet comme source de données
	             choixRapport = "rapports/" + GlobalConstants.ChoixRapport.JOURNAL_ENQUETES;
	             procedure = "CARDEX_RAPPORT.SP_RAP_JOURNAL_ENQUETES";
	             //Si le critère Nature est vide, on inscrit par défaut celui du journal des enquêtes.
	 			 if(StringUtils.isEmpty(nature)){
	 			 	criteresRechercheJournal.setNature(Long.parseLong(GlobalConstants.Nature.JOURNAL_ENQUETES));
				 }
                 //Pour le journal des enquêteurs, on ne tient pas compte de l'heure.
	             dateDebut = dateDebut.substring(0,10);
	             dateFin = dateFin.substring(0,10);
				 criteresRechercheJournal.setDateCreationDu(TimestampFormat.parse(dateDebut, new Locale("fr"), false));
				 criteresRechercheJournal.setDateCreationAu(TimestampFormat.parse(dateFin, new Locale("fr"), false));
                 resultSet = delegate.rapportJournalEnquetes(criteresRechercheJournal, procedure);
        	 }else{
        		 resultSet = selectRapportJournal(criteresRechercheJournal, connection);
        	 }
           	 InputStream gabarit = getClass().getClassLoader().getResourceAsStream(choixRapport);
	         JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
	         ServletContext context = request.getSession().getServletContext();  
	         //System.out.println(context.getRealPath("/rapports/"));
	         parameters.put("SUBREPORT_DIR",context.getRealPath("/rapports/"));
	         parameters.put("REPORT_CONNECTION",connection);
	         parameters.put("UTILISATEUR",utilisateur);
	         parameters.put("DATE_DEBUT",dateDebut);
	         parameters.put("DATE_FIN",dateFin);
	         JasperPrint print = JasperFillManager.fillReport(gabarit, parameters, resultSetDataSource);
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

    /**
     * Recherche des données pour les rapports sommaire et détaillé du Journal
     * à l'aide de critères de recherche.
     * Date de création : (2009-08-12)
     * @author François Guérin
     * @param criteria CriteresRechercheDossier : Critères de recherche.
     * @throws DAOException lancée lorsqu'une SQLException est reçue lors d'une
     * rupture de connexion avec la base de données, ou que la table demandée
     * est non disponible, ou qu'un problème est survenu lors de l'exécution
     * d'une "stored procedure".
     * @return ValueListIterator Liste des dossiers retournés par la recherche.
     */
    public ResultSet selectRapportJournal(CriteresRechercheJournal criteria, Connection connection) throws DAOException {
              
        try {
            PreparerSQL preparerSQL = obtenirPreparerSQL(criteria);
            
			PreparedStatement ps = connection.prepareStatement( preparerSQL.getSQL().toString() );
			preparerSQL.assignerPreparedStatement(ps);
            
            // La requête est exécutée avec la variable contenant le
            // code SQL généré.
            return ps.executeQuery();
        
        }catch (SQLException se) {
          throw new DAOException(se);
        }
    }//select

	private PreparerSQL obtenirPreparerSQL(CriteresRechercheJournal criteria) {
        PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder query = new StringBuilder();
		//On ne traite ici que les rapports sommaires ou détaillés.  Les rapports
		//par catégories sont exécutés ailleurs.
		// Recherche normale avec les autres critères.  En fonction des
		// tests effectués et des vérifications des critères, on ajoute
		// le code SQL nécessaire à une variable.
		query.append(" SELECT S.V_SI_SITE, GS.V_GS_NOM  as \"GROUPE_SECURITE\", trin.v_tr_description AS \"GROUPE\", i.v_in_nom||', '||i.v_in_prenom as \"INTERVENANT\", ");
		query.append(" TRTY.V_TR_DESCRIPTION as \"TYPE\", TRCA.V_TR_DESCRIPTION as \"CATEGORIE\", C.V_CO_TEMPS AS \"TEMPS\", ");
		query.append(" DO.V_DO_REFERENCE2||' - '||DO.V_DO_LIEU AS \"DESCRIPTIF\", TROR.V_TR_DESCRIPTION AS \"ORIGINE\" "); 
		query.append(" FROM DO_DOSSIER DO, CA_CATEGORIE CA, CO_COMMENTAIRE2 C, TY_TYPE TY, NA_NATURE NA, GE_GENRE GE, ");
		query.append(" TR_TRADUCTION TRTY, TR_TRADUCTION TRCA, SI_SITE S, in_intervenant i, tr_traduction trin, ");
		query.append(" TR_TRADUCTION TROR, GS_GROUPE_SECURITE GS, IGS_INTERVENANT_GROUPE_SEC IGS ");                    
		//On ne tient pas compte des dossiers placés en confidentialité 8 (dossiers en attente de suppression).
		query.append(" WHERE DO.I_CC_CLE <> ?");
		preparerSQL.addParametre(GlobalConstants.Confidentialite.HUIT);
		query.append(" AND CA.I_CA_CLE = DO.I_CA_CLE  ");
		query.append(" AND i.NAME = DO.V_DO_ASSIGNE_A " );
		query.append(" AND trin.l_tr_cle = i.l_in_secteur " );
		query.append(" AND trin.I_LA_CLE = 1 " );
		query.append(" AND DO.L_DO_CLE = C.L_CO_REFERENCE AND DO.L_SI_CLE = C.L_CO_REF_SITE  " );
		query.append(" AND rtrim(C.C_CO_REF_GENRE) = ? " );
		preparerSQL.addParametre(GlobalConstants.GenreFichier.DOSSIER);
		query.append(" AND (CA.I_TY_CLE = TRTY.L_TR_CLE) ");
		query.append(" AND (TRTY.I_LA_CLE = 1)" );
		query.append(" AND (DO.L_DO_ORIGINE = TROR.L_TR_CLE AND TROR.I_LA_CLE = 1)");
		query.append(" AND I.NAME = IGS.V_NAME");
		query.append(" AND IGS.L_GS_CLE = GS.L_GS_CLE");
		query.append(" AND (DO.I_CA_CLE = TRCA.L_TR_CLE) ");
		query.append(" AND (TRCA.I_LA_CLE = 1) ");
		query.append(" AND (CA.I_TY_CLE = TY.I_TY_CLE) ");
		query.append(" AND (TY.I_NA_CLE = NA.I_NA_CLE) ");
		query.append(" AND (NA.I_GE_CLE = GE.I_GE_CLE) ");
		query.append(" AND C.V_CO_TEMPS is not null " );
		
		if (criteria.getEntite() != 0 && criteria.getSite() == 0) {
	        query.append(" AND S.L_SI_CLE = DO.L_SI_CLE");
	        query.append(" AND S.I_EN_CLE = ? " );
	        preparerSQL.addParametre(criteria.getEntite());
		}
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "I.NAME", criteria.getIntervenant());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "DO.I_CA_CLE", criteria.getCategorie());
		
		if (criteria.getType() != 0 && criteria.getCategorie() == 0) {
	        query.append(" AND TY.I_TY_CLE = ?" );
	        preparerSQL.addParametre(criteria.getType());
		}
		if (criteria.getNature() != 0 && criteria.getType() == 0) {
            query.append(" AND NA.I_NA_CLE = ? ");
            preparerSQL.addParametre(criteria.getNature());
		}
		if (criteria.getGenre() != 0 && criteria.getNature() == 0) {
            query.append(" AND GE.I_GE_CLE = ? " );
            preparerSQL.addParametre(criteria.getGenre());
		}
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "I.L_IN_SECTEUR", StringHelper.convertirStringEnLong(criteria.getSecteur()));
		OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, query, "DO.V_DO_LIEU", criteria.getDescriptif());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "DO.I_DO_FONDE", criteria.getFonde());
		
		if (criteria.getSite() != 0) {
            query.append(" AND C.L_SI_CLE = S.L_SI_CLE AND C.L_SI_CLE = ? ");
            preparerSQL.addParametre(criteria.getSite());
		}
		// On tient compte de l'heure dans la recherche
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "C.D_CO_DATE_CREATION", ">=", criteria.getDateCreationDu());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "C.D_CO_DATE_CREATION", "<=", criteria.getDateCreationAu());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "DO.L_DO_ORIGINE", criteria.getOrigine());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "DO.I_OR_CLE", criteria.getEndroit());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "DO.I_CR_CLE", criteria.getLocalisation());

		preparerSQL.setSQL(query.toString());
		return preparerSQL;
	}

}