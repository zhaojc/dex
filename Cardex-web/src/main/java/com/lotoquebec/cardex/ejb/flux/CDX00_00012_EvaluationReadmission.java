package com.lotoquebec.cardex.ejb.flux;

import java.sql.Connection;
import java.text.DateFormat;
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
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Rapport pour la r�admission.
 * Quand le Comit� de vigilance �met des acc�s interdits, il est pr�venu � chaque date
 * anniversaire de l'avis pour �valuer la r�admission du client. 
 * @author guerinf
 *
 */
public class CDX00_00012_EvaluationReadmission implements Flux{

	private final static Logger log = (Logger)LoggerCardex.getLogger(CDX00_00012_EvaluationReadmission.class);
	private CardexAuthenticationSubject subject = null;

	
	public void execute() throws Exception {
		log.fine("Entr�e flux CDX00_00012");
		
		subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
		
		log.fine("Traitement de la r�admission");
		Connection connection = null; 
		try {
			connection = DAOConnection.getInstance().getConnection(subject);
			envoyerConfirmation(subject, connection);
		} finally{
			connection.close();
			connection = null;
		}		
		
		log.fine("Fin flux CDX00_00012");
	}

	private void envoyerConfirmation(CardexAuthenticationSubject subject, Connection connection) throws AssertionError, BusinessResourceException, DAOException {
		String listeSujet = rechercheSujets(connection);
		String objectMessage = CourrielAction.constuireObjectMessage(subject, GlobalConstants.TypesIntervention.Readmission);
		if(StringUtils.isNotEmpty(listeSujet)){
			log.fine("Envoi du courriel");
			String message = "<br>" + listeSujet;
			CourrielAction.envoyerCourrielDestinataire(subject, connection, objectMessage, message, GlobalConstants.TypesIntervention.Readmission, "A");
		}
	}

	private String rechercheSujets(Connection connection) throws AssertionError, DAOException {
		log.fine("rechercheSujets D�but ");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateRapport = dateFormat.format(new Date());
			String listeSujet = FabriqueCardexDAO.getInstance().getRapportDAO().rapportReadmission(connection);

			log.fine("rechercheSujets Fin");
			return listeSujet;
	}
	
}
