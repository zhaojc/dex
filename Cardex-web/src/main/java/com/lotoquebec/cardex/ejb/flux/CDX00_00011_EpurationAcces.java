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
 * �puration de la table AC_ACCES, sauf les insertions.
 * @author guerinf
 *
 */
public class CDX00_00011_EpurationAcces implements Flux{

	private final static Logger log = LoggerFactory.getLogger(CDX00_00011_EpurationAcces.class);
	private CardexAuthenticationSubject subject = null;
	private DossierDAO dossierDAO = null;
	
	private void init(){
		dossierDAO = FabriqueCardexDAO.getInstance().getDossierDAO();
	}

	
	public void execute() throws Exception {
		log.info("Entr�e flux CDX00_00011");
		log.info("Diff�r� pour l'épuration de la table AC_ACCES.");
		Connection connection = null; 
		
		init();
		
		try{
			subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
			
			log.info("Ouverture de connection");
			connection = DAOConnection.getInstance().getConnection(subject);
			log.info("D�but de l'�puration");
			traiterExpiration(subject, connection);

		} finally{
			connection.close();
			connection = null;
		}		
		
		log.info("Fin flux CDX00_00011");
	}

	private void traiterExpiration(CardexAuthenticationSubject subject, Connection connection) throws DAOException, BusinessResourceException, SQLException{
		log.info("�puration de la table AC_ACCES.");
		//Appel de la proc�dure Cardex_special.sp_epuration_acces qui ex�cute tout le traitement
		dossierDAO.epurationAcces(connection);
	}

}
