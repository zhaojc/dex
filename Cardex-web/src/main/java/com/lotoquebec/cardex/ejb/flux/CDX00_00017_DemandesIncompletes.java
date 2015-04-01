package com.lotoquebec.cardex.ejb.flux;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.util.CourrielAction;
import com.lotoquebec.cardexCommun.util.JourneeOuvrable;

/**
 * Habilitation s�curitaire
 * Quand une demande d'enqu�te est incompl�te, un suivi est cr�� dans le dossier pour indiquer les informations manquantes.
 * Ce diff�r� v�rifie ces suivis non compl�t�s apr�s 5, 6 et 11 jours ouvrables et envoit un courriel aux intervenants concern�s. 
 * @author guerinf
 *
 */
public class CDX00_00017_DemandesIncompletes implements Flux{

	private final static Logger log = (Logger)LoggerCardex.getLogger(CDX00_00017_DemandesIncompletes.class);
	private CardexAuthenticationSubject subject = null;

	
	public void execute() throws Exception {
		log.fine("Entr�e flux CDX00_00017");
		
		subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
		
		log.fine("Suivi des demandes incompl�tes");
		Connection connection = null; 
		try {
			connection = DAOConnection.getInstance().getConnection(subject);
			envoyerAvis(subject, connection);
		} finally{
			connection.close();
			connection = null;
		}		
		
		log.fine("Fin flux CDX00_00017");
	}

	private void envoyerAvis(CardexAuthenticationSubject subject, Connection connection) throws AssertionError, BusinessResourceException, DAOException {
		String informationSuivi = "";
		String objectMessage = "";
		String message = "";

		try{
			ResultSet listeSuivis = rechercheSuivis(connection);
			//Pour chaque suivi retourn�, on doit v�rifier un des trois cas suivants :
			//Premier avis : le suivi a �t� cr�� il y a 5 jours ouvrables.
			//Deuxi�me avis : le suivi a �t� cr�� il y a 6 jours ouvrables.
			//Troisi�me avis : le suivi a �t� cr�� il y 11 jours ouvrables.
			while (listeSuivis.next()){
				Date dateSuivi = listeSuivis.getTimestamp("Date du suivi");
				String enquete = listeSuivis.getString("Personne enqu�t�e");
				String demandeur = listeSuivis.getString("Demandeur");
				String suivi = listeSuivis.getString("Suivi");
				Date dateDuJour = new Date();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        dateDuJour = dateFormat.parse(dateFormat.format(dateDuJour));
				informationSuivi = "Personne enqu�t�e : " + enquete + "<br> Demandeur : " + demandeur + "<br> Suivi : " + suivi;
				//V�rification du premier avis
				Date dateSuiviJoursOuvrables = JourneeOuvrable.ajouterJours(dateSuivi, 5);
				//On compare les dates seulement, sans les heures.
				dateSuiviJoursOuvrables = dateFormat.parse(dateFormat.format(dateSuiviJoursOuvrables));
				if(dateSuiviJoursOuvrables.compareTo(dateDuJour) == 0){
					log.fine("Envoi du premier avis");
					objectMessage = CourrielAction.constuireObjectMessage(subject, GlobalConstants.TypesIntervention.DemandesIncompletes1);
					message = "<br>" + informationSuivi;
					CourrielAction.envoyerCourrielDestinataire(subject, connection, objectMessage, message, GlobalConstants.TypesIntervention.DemandesIncompletes1, "A");
				}else{
					dateSuiviJoursOuvrables = JourneeOuvrable.ajouterJours(dateSuivi, 6);
					dateSuiviJoursOuvrables = dateFormat.parse(dateFormat.format(dateSuiviJoursOuvrables));
					if(dateSuiviJoursOuvrables.compareTo(dateDuJour) == 0){
						log.fine("Envoi du deuxi�me avis");
						objectMessage = CourrielAction.constuireObjectMessage(subject, GlobalConstants.TypesIntervention.DemandesIncompletes2);
						message = "<br>" + informationSuivi;
						CourrielAction.envoyerCourrielDestinataire(subject, connection, objectMessage, message, GlobalConstants.TypesIntervention.DemandesIncompletes2, "A");
					}else{
						dateSuiviJoursOuvrables = JourneeOuvrable.ajouterJours(dateSuivi, 11);
						dateSuiviJoursOuvrables = dateFormat.parse(dateFormat.format(dateSuiviJoursOuvrables));
						if(dateSuiviJoursOuvrables.compareTo(dateDuJour) == 0){
							log.fine("Envoi du troisi�me avis");
							objectMessage = CourrielAction.constuireObjectMessage(subject, GlobalConstants.TypesIntervention.DemandesIncompletes3);
							message = "<br>" + informationSuivi;
							CourrielAction.envoyerCourrielDestinataire(subject, connection, objectMessage, message, GlobalConstants.TypesIntervention.DemandesIncompletes3, "A");
						}					
					}
				}
			}//while		
        } catch (SQLException se) {
            throw new DAOException(se);
        } catch (ParseException pe) {
        	System.out.print(pe.toString());
        }
	}

	private ResultSet rechercheSuivis(Connection connection) throws AssertionError, DAOException {
		log.fine("rechercheSuivis D�but ");
		ResultSet resultSet = null;
			resultSet = FabriqueCardexDAO.getInstance().getRapportDAO().listeDemandesIncompletes(connection);
			log.fine("rechercheSuivis Fin");
		return resultSet;
	}
	
}
