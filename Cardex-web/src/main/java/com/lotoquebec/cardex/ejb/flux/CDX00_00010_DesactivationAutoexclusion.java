package com.lotoquebec.cardex.ejb.flux;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.integration.dao.DossierDAO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;

/**
 * D�sactivation des dossiers d'autoexclusion expir�s
 * @author guerinf
 *
 */
public class CDX00_00010_DesactivationAutoexclusion implements Flux{

	private final static Logger log = LoggerFactory.getLogger(CDX00_00010_DesactivationAutoexclusion.class);
	private CardexAuthenticationSubject subject = null;
	private DossierDAO dossierDAO = null;
	
	private void init(){
		dossierDAO = FabriqueCardexDAO.getInstance().getDossierDAO();
	}

	
	public void execute() throws Exception {
		log.info("Entr�e flux CDX00_00010");
		log.info("Diff�r� de d�sactivation des dossiers d'autoexclusion expir�s");
		Connection connection = null; 
		
		init();
		
		try{
			subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
			
			log.info("Ouverture de connection");
			connection = DAOConnection.getInstance().getConnection(subject);
			log.info("Début du traitement des dossiers");
			traiterDossierExpiration(subject, connection);

		} finally{
			connection.close();
			connection = null;
		}		
		
		log.info("Fin flux CDX00_00010");
	}

	private void traiterDossierExpiration(CardexAuthenticationSubject subject, Connection connection) throws DAOException, BusinessResourceException, SQLException{
		log.info("Désactivation des dossiers d'autoexclusion expir�s");
		//Appel de la proc�dure Cardex_special.sp_active_inscription qui ex�cute tout le traitement
		dossierDAO.desactiveDossiersAutoexclusion(connection);
	}

}
