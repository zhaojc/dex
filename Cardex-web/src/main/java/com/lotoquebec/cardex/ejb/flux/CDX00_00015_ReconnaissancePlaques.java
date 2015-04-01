package com.lotoquebec.cardex.ejb.flux;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.rapport.CSVImpressionRapport;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lq.std.conf.impl.AppConfig;

/**
 * Reconnaissance de plaques
 * Un cycle RMFT copie les fichiers produit vers les diff�rents serveurs de chaque site.
 * @author guerinf
 *
 */
public class CDX00_00015_ReconnaissancePlaques implements Flux{

	private final static Logger log = (Logger)LoggerCardex.getLogger(CDX00_00015_ReconnaissancePlaques.class);
	private CardexAuthenticationSubject subject = null;

	
	public void execute() throws Exception {
		log.fine("Entr�e flux CDX00_00015");
		
		subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
		
		log.fine("Production des fichiers de reconnaissance de plaques");
		Connection connection = null; 
		try {
			connection = DAOConnection.getInstance().getConnection(subject);
			produireFichiers(subject, connection);
		} finally{
			connection.close();
			connection = null;
		}		
		
		log.fine("Fin flux CDX00_00015");
	}

	private void produireFichiers(CardexAuthenticationSubject subject, Connection connection) throws FileNotFoundException, BusinessResourceException, DAOException {
		ListeCache cache = ListeCache.getInstance();
		//On doit produire 4 rapports, un par casino (4).
		//On va d'abord chercher la description du site pour l'inscrire sur le nom du fichier g�n�r�.
		//Puis on va chercher le nom du rapport avec ces donn�es avant de le produire.
		//On recommence pour chacun des casinos et les 3 natures
		String nomRapport = obtenirNomRapport(GlobalConstants.AbreviationSites.MONTREAL);
		log.fine("Choix nom rapport : '"+nomRapport+"'");
		produireRapportReconnaissance(nomRapport, GlobalConstants.SiteMaisonJeux.MONTREAL, connection);
		nomRapport = obtenirNomRapport(GlobalConstants.AbreviationSites.LAC_LEAMY);
		log.fine("Choix nom rapport : '"+nomRapport+"'");
		produireRapportReconnaissance(nomRapport, GlobalConstants.SiteMaisonJeux.LAC_LEAMY, connection);
		nomRapport = obtenirNomRapport(GlobalConstants.AbreviationSites.CHARLEVOIX);
		log.fine("Choix nom rapport : '"+nomRapport+"'");
		produireRapportReconnaissance(nomRapport, GlobalConstants.SiteMaisonJeux.CHARLEVOIX, connection);
		nomRapport = obtenirNomRapport(GlobalConstants.AbreviationSites.MONT_TREMBLANT);
		log.fine("Choix nom rapport : '"+nomRapport+"'");
		produireRapportReconnaissance(nomRapport, GlobalConstants.SiteMaisonJeux.MONT_TREMBLANT, connection);
		//String objectMessage = CourrielAction.constuireObjectMessage(subject, GlobalConstants.TypesIntervention.VerificationLivrets);
		//CourrielAction.envoyerCourrielEtFichierDestinataire(subject, connection, objectMessage, "", GlobalConstants.TypesIntervention.VerificationLivrets, "A", nomRapport);
	}

	private String obtenirNomRapport(String site){
		String rapportTemporaire = AppConfig.INSTANCE.get(GlobalConstants.Rapport.REPERTOIRE_SAUVEGARDE_RAPPORT_RECONNAISSANCE_PLAQUES);
		return rapportTemporaire+"AutoVu-"+ site + ".csv";
	}

	private void produireRapportReconnaissance(String nomRapport, String site,Connection connection) throws FileNotFoundException, DAOException {
		log.fine("produireRapportReconnaissance d�but "+nomRapport);
		
		try {
			Map parameters = new HashMap();
			InputStream gabarit = RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_RECONNAISSANCE_PLAQUES);

			// Utilisation d'un resultSet comme source de donn�es
			log.fine("produireRapportReconnaissance");
			
			ResultSet resultSet = FabriqueCardexDAO.getInstance().getRapportDAO().produireRapportReconnaissance(site, connection);
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
			// log.fine(context.getRealPath("/rapports/"));
			parameters.put("SUBREPORT_DIR", "/rapports/");
			parameters.put("REPORT_CONNECTION", connection);
			parameters.put("UTILISATEUR", "Diff�r� Cardex");
			JasperPrint print = JasperFillManager.fillReport(gabarit, parameters, resultSetDataSource);

			// Sauvegarde en format CSV
			log.fine("produireRapportReconnaissance (Sauvegarde dans un fichier)");
			(new CSVImpressionRapport()).impression(nomRapport, print);
			log.fine("produireRapportReconnaissance Fin");
		} catch (JRException e) {
			e.printStackTrace();
			throw new AssertionError("Probl�me dans le flux 'ReconnaissancePlaquesFlux'");
		}catch (DAOException de) {
	        	throw new DAOException(de);
	    }
	}
	
}