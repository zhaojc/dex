package com.lotoquebec.cardex.ejb.flux;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.integration.dao.DossierDAO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.integration.dao.SocieteDAO;
import com.lotoquebec.cardex.integration.dao.SujetDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * D�sactivation des dossiers expir�s
 * et mise � jour des s�v�rit�s des sujets et des soci�t�s
 * @author guerinf
 *
 */
public class CDX00_00008_DesactivationInvestigation implements Flux{

	private final static Logger log = (Logger)LoggerCardex.getLogger(CDX00_00008_DesactivationInvestigation.class);
	private CardexAuthenticationSubject subject = null;
	private DossierDAO dossierDAO = null;
	private SocieteDAO societeDAO = null;
	private SujetDAO sujetDAO = null;
	
	private void init(){
		dossierDAO = FabriqueCardexDAO.getInstance().getDossierDAO();
		societeDAO = FabriqueCardexDAO.getInstance().getSocieteDAO();
		sujetDAO = FabriqueCardexDAO.getInstance().getSujetDAO();
	}

	
	public void execute() throws Exception {
		log.fine("Entr�e flux CDX00_00008");
		
		subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
		
		log.fine("D�sactivation des dossiers expir�s d'Investigation");
		Connection connection = null; 
		
		init();
		
		try{
			subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
			
			log.fine("Ouverture de connection");
			connection = DAOConnection.getInstance().getConnection(subject);

			log.fine("D�but du traitement des dossiers");
			traiterDossierExpiration(subject, connection);
			log.fine("D�but du traitement des sujets");
			traiterSujetSeverite(subject, connection);
			log.fine("D�but du traitement des soci�t�s");
			traiterSocieteSeverite(subject, connection);

		} finally{
			connection.close();
			connection = null;
		}		
		
		log.fine("Fin flux CDX00_00008");
	}

	private void traiterSujetSeverite(CardexAuthenticationSubject subject, Connection connection) throws DAOException, BusinessResourceException, SQLException{
		//Mise � jour de la s�v�rit� des sujets
		log.fine("Mise � jour de la s�v�rit� Casino des sujets");
		sujetDAO.assignerCourantLog(log);
		sujetDAO.severiteSujetInvestigationCasino(connection);
		log.fine("Mise � jour de la s�v�rit� Autre des sujets");
		sujetDAO.severiteSujetInvestigationAutre(connection);
		sujetDAO.assignerLocalCourantLog();
	}
	
	private void traiterSocieteSeverite(CardexAuthenticationSubject subject, Connection connection) throws DAOException, BusinessResourceException, SQLException{
		//Mise � jour de la s�v�rit� des soci�t�s
		log.fine("Mise � jour de la s�v�rit� Casino des soci�t�s");
		societeDAO.assignerCourantLog(log);
		societeDAO.severiteSocieteInvestigationCasino(connection);
		log.fine("Mise � jour de la s�v�rit� Autre des soci�t�s");
		societeDAO.severiteSocieteInvestigationAutre(connection);
		sujetDAO.assignerLocalCourantLog();
	}

	private void traiterDossierExpiration(CardexAuthenticationSubject subject, Connection connection) throws DAOException, BusinessResourceException, SQLException{
		//Mise � jour de la s�v�rit� � 3 des dossiers qui ont un dossier actif
		log.fine("Mise � jour de la s�v�rit� � 3 des dossiers qui ont un dossier actif");
		dossierDAO.severite3DossierInvestigationDossierActif(connection);
		//Changement de la s�v�rit� � 4 des dossiers d'investigation dont la date d'expiration aura lieu dans 90 jours.
		//log.fine("Changement de la s�v�rit� � 4 des dossiers dont l'expiration des dossiers d'investigation aura lieu dans 90 jours");
		//Requ�te R13-0408 : retrait du traitement de la s�v�rit� 4
		//dossierDAO.severite4DossierInvestigationExpiration90Jours(connection);
		//Changement de la s�v�rit� � 2 des soci�t�s dont les dossiers d'investigation sont expir�s.
		log.fine("Changement de la s�v�rit� � 2 des dossiers dont les dossiers d'investigation sont expir�s");
		dossierDAO.severite2DossierInvestigationExpiration(connection); 
	}

}
