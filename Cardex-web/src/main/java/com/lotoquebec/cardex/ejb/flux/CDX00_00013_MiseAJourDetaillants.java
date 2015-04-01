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
 * Ce EJB effectue plusieurs t�ches � partir du d�p�t de donn�es RDD (R�seau des d�taillants).
 * Il a principalement pour but de transf�rer et d'actualiser les donn�es sur les d�taillants
 * inscrits dans Cardex afin d'�viter aux enqu�teurs de recourir � deux syst�mes, ainsi que
 * pour les validations et les traitements qui s'appliquent aux acheteurs myst�res (conformit� loi 84).
 * Cr�ation d'abord des entr�es dans l'audit des soci�t�s qui seront mises � jour.
 * Mise � jour des soci�t�s de Loto-Qu�bec qui diff�rent de RDD.
 * Ajout des d�taillants RDD qui ne sont pas dans Cardex.
 * Mise � jour les adresses des soci�t�s.
 * Ajout des personnes ressources qui ne sont pas dans Cardex.
 * Ajout des personnes morales.
 * V�rfication des changements par rapport aux soci�t�s faisant partie de l'�chantillon des acheteurs myst�res.
 * @author guerinf
 *
 */
public class CDX00_00013_MiseAJourDetaillants implements Flux{

	private final static Logger log = (Logger)LoggerCardex.getLogger(CDX00_00013_MiseAJourDetaillants.class);
	private CardexAuthenticationSubject subject = null;

	
	public void execute() throws Exception {
		log.fine("Entr�e flux CDX00_00013");
		
		subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
		
		log.fine("Traitement de la mise � jour des d�taillants");
		Connection connection = null; 
		try {
			connection = DAOConnection.getInstance().getConnection(subject);
			creerAuditSocietes(subject, connection);
			miseAJourDetaillants(subject, connection);
			ajoutNouveauxDetaillants(subject, connection);
			traitementPersonnesPhysiques(subject, connection);
			creerAuditPersonnesMorales(subject, connection);
			traitementPersonnesMorales(subject, connection);
			ajoutAdressesManquantes(subject, connection);
			creerAuditAdresses(subject, connection);
			miseAJourAdresses(subject, connection);
			produireRapportChangementsDetaillants(subject, connection);
		} finally{
			connection.close();
			connection = null;
		}		
		
		log.fine("Fin flux CDX00_00013");
	}

	private void creerAuditSocietes(CardexAuthenticationSubject subject, Connection connection) throws AssertionError {
		log.fine("Cr�ation de l'audit des soci�t�s");
			
			FabriqueCardexDAO.getInstance().getSocieteDAO().creerAuditSocietes(connection);

			log.fine("Fin de la cr�ation de l'audit des soci�t�s");
	}
	
	private void creerAuditPersonnesMorales(CardexAuthenticationSubject subject, Connection connection) throws AssertionError {
		log.fine("Cr�ation de l'audit des soci�t�s lors de la mise � jour des personnes morales");
			
			FabriqueCardexDAO.getInstance().getSocieteDAO().creerAuditPersonnesMorales(connection);

			log.fine("Fin de la cr�ation de l'audit des soci�t�s lors de la mise � jour des personnes morales");
	}
	
	private void creerAuditAdresses(CardexAuthenticationSubject subject, Connection connection) throws AssertionError {
		log.fine("Cr�ation de l'audit des adresses lors de la mise � jour des adresses");
			
			FabriqueCardexDAO.getInstance().getSocieteDAO().creerAuditAdresses(connection);

			log.fine("Fin de la cr�ation de l'audit des adresses lors de la mise � jour des adresses");
	}
	
	private void miseAJourDetaillants(CardexAuthenticationSubject subject, Connection connection) throws AssertionError {
		log.fine("Mise � jour des d�taillants Loto-Qu�bec � partir de RDD");
			
			FabriqueCardexDAO.getInstance().getSocieteDAO().miseAJourDetaillants(connection);

			log.fine("Fin de la mise � jour des d�taillants Loto-Qu�bec � partir de RDD");
	}

    private void miseAJourAdresses(CardexAuthenticationSubject subject, Connection connection) throws AssertionError {
        log.fine("Mise � jour des adresses des d�taillants Loto-Qu�bec � partir de RDD");
            
            FabriqueCardexDAO.getInstance().getSocieteDAO().miseAJourAdressesDetaillant(connection);

            log.fine("Fin de la mise � jour des adresses des d�taillants Loto-Qu�bec � partir de RDD");
    }

	private void ajoutAdressesManquantes(CardexAuthenticationSubject subject, Connection connection) throws DAOException, AssertionError {
		log.fine("Ajout des adresses manquantes aux d�taillants Loto-Qu�bec � partir de RDD");
        try{   
            FabriqueCardexDAO.getInstance().getSocieteDAO().ajoutAdressesManquantes(subject, connection);

            log.fine("Fin de l'ajout des adresses manquantes aux d�taillants Loto-Qu�bec � partir de RDD");
         }catch (DAOException de) {
                throw new DAOException(de);
         }
			
	}

	private void ajoutNouveauxDetaillants(CardexAuthenticationSubject subject, Connection connection) throws DAOException, AssertionError {
		log.fine("Ajout des nouveaux d�taillants de RDD dans Cardex");
		 try{	
			FabriqueCardexDAO.getInstance().getSocieteDAO().ajoutNouveauxDetaillants(subject, connection);

			log.fine("Fin de l'ajout des d�taillants de RDD dans Cardex");
		 }catch (DAOException de) {
	        	throw new DAOException(de);
	     }
	}

	private void traitementPersonnesPhysiques(CardexAuthenticationSubject subject, Connection connection) throws DAOException, AssertionError {
		log.fine("Traitement des personnes physiques de RDD dans Cardex");
		 try{	
			FabriqueCardexDAO.getInstance().getSocieteDAO().traitementPersonnesPhysiques(subject, connection);

			log.fine("Fin du traitement des personnes physiques");
		 }catch (DAOException de) {
	        	throw new DAOException(de);
	     }
	}

	private void traitementPersonnesMorales(CardexAuthenticationSubject subject, Connection connection) throws DAOException, AssertionError {
		log.fine("Traitement des soci�t�s et des personnes physiques de RDD dans Cardex");
		 try{	
			FabriqueCardexDAO.getInstance().getSocieteDAO().traitementPersonnesMorales(subject, connection);

			log.fine("Fin du traitement des personnes morales");
		 }catch (DAOException de){
	        	throw new DAOException(de);
	     }
	}

	private String obtenirNomRapport(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String rapportTemporaire = AppConfig.INSTANCE.get(GlobalConstants.Rapport.REPERTOIRE_SAUVEGARDE_RAPPORT_CHANGEMENTS_DETAILLANTS);
		String dateRapport = dateFormat.format(new Date());
		return rapportTemporaire+"Rapport sur les changements aux d�tallants de l'�chantillon ("+dateRapport+").pdf";
	}
	
	private void produireRapportChangementsDetaillants(CardexAuthenticationSubject subject, Connection connection) throws FileNotFoundException, BusinessResourceException, DAOException, JRException {
		log.fine("produireRapportChangementsDetaillants d�but ");
		
		//On v�rifie d'abord s'il y a des dossiers de visite en cours.
		Integer nombre = FabriqueCardexDAO.getInstance().getDossierDAO().verifierNombreDossierVisite(subject);
		//Si oui, on poursuit le traitement.
		if(nombre > 0){
			Map parameters = new HashMap();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateRapport = dateFormat.format(new Date());
			InputStream gabarit = RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_CHANGEMENTS_DETAILLANTS);

			// Utilisation d'un resultSet comme source de donn�es
			//ResultSet resultSet = delegate.rapportActivites(dateDebut, dateFin, procedure);
			log.fine("produireRapportChangementsDetaillants");
			ResultSet resultSet = FabriqueCardexDAO.getInstance().getRapportDAO().rapportChangementsDetaillants(connection);
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
			// log.fine(context.getRealPath("/rapports/"));
			parameters.put("SUBREPORT_DIR", "/rapports/");
			parameters.put("REPORT_CONNECTION", connection);
			parameters.put("UTILISATEUR", "Diff�r� Cardex");
			JasperPrint print = JasperFillManager.fillReport(gabarit, parameters, resultSetDataSource);

			// Sauvegarde dans un fichier
			log.fine("produireRapportChangementsDetaillants (Sauvegarde dans un fichier)");
			String nomRapport = obtenirNomRapport();
			(new PDFImpressionRapport()).impression(nomRapport, print);
			//log.fine("Choix nom rapport : '"+nomRapport+"'");
			log.fine("Envoi du courriel");
			String objectMessage = CourrielAction.constuireObjectMessage(subject, GlobalConstants.TypesIntervention.ChangementsDetaillants);
			CourrielAction.envoyerCourrielEtFichierDestinataire(subject, connection, objectMessage, "", GlobalConstants.TypesIntervention.ChangementsDetaillants, "A", nomRapport);
		}
		log.fine("produireRapportChangementsDetaillants Fin");

	}

}
