package com.lotoquebec.cardex.ejb.flux;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.lotoquebec.cardex.business.facade.FabriqueFacade;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.util.RapportUtils;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.rapport.PDFImpressionRapport;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.CourrielAction;
import com.lq.std.conf.impl.AppConfig;

/**
 * Rapport d'activit�s quotiennes
 * @author levassc
 *
 */
public class CDX00_00006_RAQ implements Flux{

	private final static Logger log = (Logger)LoggerCardex.getLogger(CDX00_00006_RAQ.class);
	private CardexAuthenticationSubject subject = null;
    private final static Map<Integer, String> siteSnIntervention = new HashMap<Integer, String>();
    private Date debutDate = null;
    private Date finDate = null;
    private final static String RAQ_GLOBAL = "RAQ_Global_";
    private final static String RAQ = "RAQ_";
    private final static String RAQ_DOSSIER_LQ_EVENEMENT_DCSI = "RAQ_Loto-Quebec_Evenement_DCSI_";
    private final static String RAQ_DOSSIER_LQ_SANS_EVENEMENT_DCSI = "RAQ_Loto-Quebec_Sauf_Evenement_DCSI_";
    
    // Provient de la table sn_site_intervention
    static {
    	//Un bogue avec le IPhone oblige � enlever les accents, les espaces blancs et � raccourcir les noms de fichier
    	siteSnIntervention.put(5, "Casino_Charlevoix_/F");	//Casino Charlevoix (F)
    	siteSnIntervention.put(6, "Casino_Lac-Leamy_/H");	//Casino Lac-Leamy (H)
    	siteSnIntervention.put(9, "SEJ_/B"); // Soci�t� des �tablissement de jeux
    	siteSnIntervention.put(24, "Casino_Mont-Tremblant_/N");	//Casino Mont-Tremblant (N)
    	siteSnIntervention.put(7, "Casino_Montreal_/D");	//Casino Montr�al (D)
    	siteSnIntervention.put(28, "Salon_de_jeux_Quebec_/SJQ");	//Salon de jeux Qu�bec (SJQ)
    	siteSnIntervention.put(23, "Salon_de_jeux_Trois-Rivieres_/SJTR");	//Salon de jeux Trois-Rivi�res (SJTR)
    	siteSnIntervention.put(72, "Surveillance_Corporative_/SC");	//Surveillance corporative (SC)
    	siteSnIntervention.put(75, "Espacejeux_/EJ"); // Espace Jeux (Maison Jeux)
    };	
	 
	public void execute() throws Exception {
		Connection connection = null;
		log.fine("Entr�e flux CDX00_00006");
		
		try{
			subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
			log.fine("Ouverture de connection");
			connection = DAOConnection.getInstance().getConnection(subject);
			debutDate = RapportUtils.dateHier7h(null);
			finDate = RapportUtils.dateAujourdHuiFin6h59(null);

			envoyerRAQGlobal(subject, connection);
			envoyerRAQParSite(subject, connection);
			envoyerRAQLQDossierLQEvenementDCSI(subject, connection);
			envoyerRAQLQSansDossierLQEvenementDCSI(subject, connection);
			//On approuve automatiquement les narrations du RAQ
			approbationAutomatique(connection);
		} catch (Exception e) {
			e.printStackTrace();
			log.severe("Erreur dans le diff�r� CDX00_00006 RAQ");
        	try {
				connection.rollback();
			    connection.close();
			    connection = null;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        	throw new Exception(e);
		} finally {
 		    if (connection != null) {
                try{
			         if(!connection.getAutoCommit()){
			            connection.rollback();
			         }
			         connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
 		    }
		}
		log.fine("Fin flux CDX00_00006");
	}

	private void envoyerRAQParSite(CardexAuthenticationSubject subject, Connection connection) throws NumberFormatException, DAOException, FileNotFoundException, JRException, SQLException, BusinessResourceException {
		
		for(Map.Entry<Integer, String> entryRAQSite:siteSnIntervention.entrySet()){
			long site = entryRAQSite.getKey();
			int pos = entryRAQSite.getValue().indexOf("/");
			//Le nom du site pour le nom du rapport
			String nomSite = entryRAQSite.getValue().substring(0,pos);
			//Le sigle du site pour l'envoi du courriel
			String snSite = entryRAQSite.getValue().substring(pos+1, entryRAQSite.getValue().length());
            
			String nomRapport = obtenirNomRapport(RAQ+nomSite);
			JasperPrint print = FabriqueFacade.getRapportSessionFacade().siteRAQCDX_0070(subject, debutDate, finDate, site);
			(new PDFImpressionRapport()).impression(nomRapport, print);
			
			log.fine("Envoie du courriel");
			String objectMessage = CourrielAction.constuireObjectMessageAvecDate(subject, GlobalConstants.TypesIntervention.RAQ);
			CourrielAction.envoyerCourrielEtFichierDestinataire(subject, connection, objectMessage, nomSite, GlobalConstants.TypesIntervention.RAQ, snSite, nomRapport);
		}
	}

	private void envoyerRAQGlobal(CardexAuthenticationSubject subject, Connection connection) throws NumberFormatException, DAOException, FileNotFoundException, JRException, SQLException, BusinessResourceException {
		String nomRapport = obtenirNomRapport(RAQ_GLOBAL);
		log.fine("Choix nom rapport : '"+nomRapport+"'");
		
		JasperPrint print = FabriqueFacade.getRapportSessionFacade().globalRAQCDX_0070(subject, debutDate, finDate);
		(new PDFImpressionRapport()).impression(nomRapport, print);
		
		log.fine("Envoie du courriel");
		String objectMessage = CourrielAction.constuireObjectMessageAvecDate(subject, GlobalConstants.TypesIntervention.RAQ);
		CourrielAction.envoyerCourrielEtFichierDestinataire(subject, connection, objectMessage, "Rapport global d'activit� quotidien", GlobalConstants.TypesIntervention.RAQ, "A", nomRapport);
	}
	
	private void envoyerRAQLQDossierLQEvenementDCSI(CardexAuthenticationSubject subject, Connection connection) throws NumberFormatException, DAOException, FileNotFoundException, JRException, SQLException, BusinessResourceException {
		String nomRapport = obtenirNomRapport(RAQ_DOSSIER_LQ_EVENEMENT_DCSI);
		log.fine("Choix nom rapport : '"+nomRapport+"'");
		
		JasperPrint print = FabriqueFacade.getRapportSessionFacade().natureRAQRapportCDX_0070(subject, debutDate, finDate, GlobalConstants.Nature.EVENEMENTS_DCSI);
		(new PDFImpressionRapport()).impression(nomRapport, print);		
		
		log.fine("Envoie du courriel");
		String objectMessage = CourrielAction.constuireObjectMessageAvecDate(subject, GlobalConstants.TypesIntervention.RAQ_LQ_EVENEMENT_DCSI);
		CourrielAction.envoyerCourrielEtFichierDestinataire(subject, connection, objectMessage, GlobalConstants.TypesIntervention.RAQ_LQ_EVENEMENT_DCSI, GlobalConstants.TypesIntervention.RAQ_LQ_EVENEMENT_DCSI, "A", nomRapport);
	}
	
	private void envoyerRAQLQSansDossierLQEvenementDCSI(CardexAuthenticationSubject subject, Connection connection) throws NumberFormatException, DAOException, FileNotFoundException, JRException, SQLException, BusinessResourceException {
		String nomRapport = obtenirNomRapport(RAQ_DOSSIER_LQ_SANS_EVENEMENT_DCSI);
		log.fine("Choix nom rapport : '"+nomRapport+"'");
		
		JasperPrint print = FabriqueFacade.getRapportSessionFacade().sansNatureRAQRapportCDX_0070(subject, debutDate, finDate, GlobalConstants.Nature.EVENEMENTS_DCSI);
		(new PDFImpressionRapport()).impression(nomRapport, print);		
		
		log.fine("Envoi du courriel");
		String objectMessage = CourrielAction.constuireObjectMessageAvecDate(subject, GlobalConstants.TypesIntervention.RAQ_LQ_SANS_EVENEMENT_DCSI);
		CourrielAction.envoyerCourrielEtFichierDestinataire(subject, connection, objectMessage, GlobalConstants.TypesIntervention.RAQ_LQ_SANS_EVENEMENT_DCSI, GlobalConstants.TypesIntervention.RAQ_LQ_SANS_EVENEMENT_DCSI, "A", nomRapport);
	}

	private String obtenirNomRapport(String nomPrefix){
		String rapportTemporaire = AppConfig.INSTANCE.get(GlobalConstants.Rapport.REPERTOIRE_SAUVEGARDE_RAPPORT_ACTIVITE_QUOTIDIEN);
		String dateRapport = DateFormat.format(new Date());
		return rapportTemporaire+nomPrefix+"("+dateRapport+").pdf";		
	}
	
	private void produireRapportActiviteQuotidienne(String nomRapport, ResultSet resultSet) throws FileNotFoundException, JRException {
		log.fine("produireRapportActiviteQuotidienne d�but "+nomRapport);
		
		Map parameters = new HashMap();

		InputStream gabarit = RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_ACTIVITES);

		// Utilisation d'un resultSet comme source de donn�es
		//ResultSet resultSet = delegate.rapportActivites(dateDebut, dateFin, procedure);
		log.fine("produireRapportActiviteQuotidienne (Constuire RS)");
		JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);

		parameters.put("DATE_DEBUT", DateFormat.format(debutDate, DateFormat.DATE_FORMAT_AVEC_HEURE));
		parameters.put("DATE_FIN", DateFormat.format(finDate, DateFormat.DATE_FORMAT_AVEC_HEURE));

		parameters.put("UTILISATEUR", "diff�r� Cardex");
		JasperPrint print = JasperFillManager.fillReport(gabarit, parameters, resultSetDataSource);
		
		log.fine("produireRapportActiviteQuotidienne (Sauvegarde dans un fichier)");
		 
		(new PDFImpressionRapport()).impression(nomRapport, print);
		
		log.fine("produireRapportActiviteQuotidienne Fin");

	}

	private void approbationAutomatique(Connection connection) throws DAOException {
    //Dans le cas des narrations du rapport d'activit�s quotidiens (RAQ), on fait une approbation automatique une fois le rapport �mis.
		try{
			FabriqueCardexDAO.getInstance().getNarrationDAO().approbationAutomatique(debutDate, finDate, connection);
 	   } catch (DAOException e) {
		   e.printStackTrace();
		   throw new DAOException(e);
 	   }
	}

}