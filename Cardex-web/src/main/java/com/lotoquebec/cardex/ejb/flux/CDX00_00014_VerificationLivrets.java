package com.lotoquebec.cardex.ejb.flux;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.lotoquebec.cardexCommun.rapport.PDFImpressionRapport;
import com.lotoquebec.cardexCommun.util.CourrielAction;
import com.lq.std.conf.impl.AppConfig;

/**
 * Mouvements de personnel
 * @author levassc
 *
 */
public class CDX00_00014_VerificationLivrets implements Flux{

	private final static Logger log = (Logger)LoggerCardex.getLogger(CDX00_00014_VerificationLivrets.class);
	private CardexAuthenticationSubject subject = null;

	
	public void execute() throws Exception {
		log.fine("Entr�e flux CDX00_00014");
		
		subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
		
		log.fine("V�rification des livrets de clients myst�res");
		Connection connection = null; 
		try {
			connection = DAOConnection.getInstance().getConnection(subject);
			envoyerConfirmation(subject, connection);
		} finally{
			connection.close();
			connection = null;
		}		
		
		log.fine("Fin flux CDX00_00014");
	}

	private void envoyerConfirmation(CardexAuthenticationSubject subject, Connection connection) throws FileNotFoundException, JRException, BusinessResourceException, DAOException{
		String nomRapport = obtenirNomRapport();
		//log.fine("Choix nom rapport : '"+nomRapport+"'");
		log.fine("Envoi du courriel");
		produireRapportVerificationLivrets(nomRapport, connection);
		String objectMessage = CourrielAction.constuireObjectMessage(subject, GlobalConstants.TypesIntervention.VerificationLivrets);
		CourrielAction.envoyerCourrielEtFichierDestinataire(subject, connection, objectMessage, "", GlobalConstants.TypesIntervention.VerificationLivrets, "A", nomRapport);
	}

	private String obtenirNomRapport(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String rapportTemporaire = AppConfig.INSTANCE.get(GlobalConstants.Rapport.REPERTOIRE_SAUVEGARDE_RAPPORT_VERIFICATION_LIVRETS);
		String dateRapport = dateFormat.format(new Date());
		return rapportTemporaire+"Rapport sur la v�rification des livrets ("+dateRapport+").pdf";
	}

	private void produireRapportVerificationLivrets(String nomRapport, Connection connection) throws JRException, FileNotFoundException, DAOException {
		log.fine("produireRapportVerificationLivrets d�but "+nomRapport);
		
		Map parameters = new HashMap();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateRapport = dateFormat.format(new Date());
		InputStream gabarit = RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_VERIFICATION_LIVRETS);

		// Utilisation d'un resultSet comme source de donn�es
		//ResultSet resultSet = delegate.rapportActivites(dateDebut, dateFin, procedure);
		log.fine("produireRapportVerificationLivrets");
		ResultSet resultSet = FabriqueCardexDAO.getInstance().getRapportDAO().rapportVerificationLivrets(connection);
		JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
		// log.fine(context.getRealPath("/rapports/"));
		parameters.put("SUBREPORT_DIR", "/rapports/");
		parameters.put("REPORT_CONNECTION", connection);
		parameters.put("UTILISATEUR", "Diff�r� Cardex");
		JasperPrint print = JasperFillManager.fillReport(gabarit, parameters, resultSetDataSource);

		// Sauvegarde dans un fichier
		log.fine("produireRapportVerificationLivrets (Sauvegarde dans un fichier)");
		(new PDFImpressionRapport()).impression(nomRapport, print);
		log.fine("produireRapportVerificationLivrets Fin");

	}
	
}
