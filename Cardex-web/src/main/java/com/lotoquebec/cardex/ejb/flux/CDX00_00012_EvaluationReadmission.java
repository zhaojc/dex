package com.lotoquebec.cardex.ejb.flux;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.util.CourrielAction;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Rapport pour la r�admission.
 * Quand le Comit� de vigilance �met des acc�s interdits, il est pr�venu � chaque date
 * anniversaire de l'avis pour �valuer la r�admission du client. 
 * @author guerinf
 *
 */
public class CDX00_00012_EvaluationReadmission implements Flux{

	private final static Logger log = LoggerFactory.getLogger(CDX00_00012_EvaluationReadmission.class);
	private CardexAuthenticationSubject subject = null;

	
	public void execute() throws Exception {
		log.info("Entrée flux CDX00_00012");
		
		subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
		
		log.info("Traitement de la réadmission");
		Connection connection = null; 
		try {
			connection = DAOConnection.getInstance().getConnection(subject);
			envoyerConfirmation(subject, connection);
		} finally{
			connection.close();
			connection = null;
		}		
		
		log.info("Fin flux CDX00_00012");
	}

	private void envoyerConfirmation(CardexAuthenticationSubject subject, Connection connection) throws AssertionError, BusinessResourceException, DAOException {
		String listeSujet = rechercheSujets(connection);
		String objectMessage = CourrielAction.constuireObjectMessage(subject, GlobalConstants.TypesIntervention.Readmission);
		if(StringUtils.isNotEmpty(listeSujet)){
			log.info("Envoi du courriel");
			String message = "<br>" + listeSujet;
			CourrielAction.envoyerCourrielDestinataire(subject, connection, objectMessage, message, GlobalConstants.TypesIntervention.Readmission, "A");
		}
	}

	private String rechercheSujets(Connection connection) throws AssertionError, DAOException {
		log.info("rechercheSujets D�but ");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateRapport = dateFormat.format(new Date());
			String listeSujet = FabriqueCardexDAO.getInstance().getRapportDAO().rapportReadmission(connection);

			log.info("rechercheSujets Fin");
			return listeSujet;
	}
	
}
