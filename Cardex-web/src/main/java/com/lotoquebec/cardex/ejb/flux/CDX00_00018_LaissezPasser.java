package com.lotoquebec.cardex.ejb.flux;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.rapport.ExcelImpressionRapport;
import com.lq.std.conf.impl.AppConfig;

/**
 * G�n�rer les donn�es pour le syst�me des laissez-passer dans les casinos.
 * @author guerinf
 *
 */
public class CDX00_00018_LaissezPasser implements Flux{

	private final static Logger log = LoggerFactory.getLogger(CDX00_00018_LaissezPasser.class);
	private CardexAuthenticationSubject subject = null;

	
	public void execute() throws Exception {
		log.info("Entr�e flux CDX00_00018");
		
		subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
		
		log.info("Production des fichiers pour les laissez-passer");
		Connection connection = null; 
		try {
			connection = DAOConnection.getInstance().getConnection(subject);
			produireFichiers(subject, connection);
		} finally{
			connection.close();
			connection = null;
		}		
		
		log.info("Fin flux CDX00_00018");
	}

	private void produireFichiers(CardexAuthenticationSubject subject, Connection connection) throws FileNotFoundException, BusinessResourceException, DAOException {
		//On doit produire 2 fichiers, un pour les sujets, un pour les soci�t�s.
		String nomRapport = obtenirNomRapport(GlobalConstants.GenreFichier.SUJET);
		log.info("Choix nom rapport : '"+nomRapport+"'");
		produireRapportLaissezPasserSujet(nomRapport, connection);
		nomRapport = obtenirNomRapport(GlobalConstants.GenreFichier.SOCIETE);
		log.info("Choix nom rapport : '"+nomRapport+"'");
		produireRapportLaissezPasserSociete(nomRapport, connection);
	}

	private String obtenirNomRapport(String fichier){
		String rapportTemporaire = AppConfig.INSTANCE.get(GlobalConstants.Rapport.REPERTOIRE_SAUVEGARDE_RAPPORT_LAISSEZ_PASSER);
		if(fichier.equals(GlobalConstants.GenreFichier.SUJET)){
			rapportTemporaire = rapportTemporaire+"Fichier des laissez-passer sujets.xls";
		}
		if(fichier.equals(GlobalConstants.GenreFichier.SOCIETE)){
			rapportTemporaire = rapportTemporaire+"Fichier des laissez-passer soci�t�s.xls";
		}
		return rapportTemporaire;
	}

	private void produireRapportLaissezPasserSujet(String nomRapport, Connection connection) throws FileNotFoundException, DAOException {
		log.info("produireRapportLaissezPasserSujet d�but "+nomRapport);
		
		try {
			Map parameters = new HashMap();
			InputStream gabarit = RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_LAISSEZ_PASSER_SUJETS);

			// Utilisation d'un resultSet comme source de donn�es
		
			ResultSet resultSet = FabriqueCardexDAO.getInstance().getRapportDAO().produireRapportLaissezPasserSujet(connection);
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
			// log.info(context.getRealPath("/rapports/"));
			parameters.put("SUBREPORT_DIR", "/rapports/");
			parameters.put("REPORT_CONNECTION", connection);
			parameters.put("UTILISATEUR", "Diff�r� Cardex");
			JasperPrint print = JasperFillManager.fillReport(gabarit, parameters, resultSetDataSource);

			// Sauvegarde en format Excel
			log.info("produireRapportLaissezPasserSujet (Sauvegarde dans un fichier)");
			(new ExcelImpressionRapport()).impression(nomRapport, print);
			log.info("produireRapportLaissezPasserSujet Fin");
		} catch (JRException e) {
			e.printStackTrace();
			throw new AssertionError("Probl�me dans le flux 'LaissezPasserSujetFlux'");
		}catch (DAOException de) {
	        	throw new DAOException(de);
	    }
	}
	private void produireRapportLaissezPasserSociete(String nomRapport, Connection connection) throws FileNotFoundException, DAOException {
		log.info("produireRapportLaissezPasserSociete d�but "+nomRapport);
		
		try {
			Map parameters = new HashMap();
			InputStream gabarit = RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_LAISSEZ_PASSER_SOCIETES);

			// Utilisation d'un resultSet comme source de donn�es
		
			ResultSet resultSet = FabriqueCardexDAO.getInstance().getRapportDAO().produireRapportLaissezPasserSociete(connection);
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
			// log.info(context.getRealPath("/rapports/"));
			parameters.put("SUBREPORT_DIR", "/rapports/");
			parameters.put("REPORT_CONNECTION", connection);
			parameters.put("UTILISATEUR", "Diff�r� Cardex");
			JasperPrint print = JasperFillManager.fillReport(gabarit, parameters, resultSetDataSource);

			// Sauvegarde en format Excel
			log.info("produireRapportLaissezPasserSociete(Sauvegarde dans un fichier)");
			(new ExcelImpressionRapport()).impression(nomRapport, print);
			log.info("produireRapportLaissezPasserSociete Fin");
		} catch (JRException e) {
			e.printStackTrace();
			throw new AssertionError("Probl�me dans le flux 'LaissezPasserSocieteFlux'");
		}catch (DAOException de) {
	        	throw new DAOException(de);
	    }
	}
	
}
