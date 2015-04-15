package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class RapportIncidentsDCSIGenerateurRapport_CDX_0148 extends CritereGenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/rapportIncidentsDCSI");
	}
	
	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new CritereRapportVO();
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
    	RapportBusinessDelegate delegate = new RapportBusinessDelegate();
    	ResultSet resultSet = null;
	 	//Pour ce rapport, il faut utiliser l'ann�e de la date saisie
	 	Calendar dateDebutCal = Calendar.getInstance();
	 	dateDebutCal.setTime(((CritereRapportVO)rapportVO).getDateDebutDu());
	 	String annee = String.valueOf(dateDebutCal.get(Calendar.YEAR));
		resultSet = delegate.rapportIncidentsDCSI(annee,connection);
		return new JRResultSetDataSource(resultSet);
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_INCIDENTS_DCSI);
	}

	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, VO rapportVO, Connection connection) throws JRException {
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		CritereRapportVO critereRapportVO = (CritereRapportVO) rapportVO;
	 	Calendar calendar = Calendar.getInstance();
	 	SimpleDateFormat moisDateFormat = new SimpleDateFormat("MMMM", java.util.Locale.FRENCH);
	 	Calendar dateDebutCal = Calendar.getInstance();
	 	dateDebutCal.setTime(critereRapportVO.getDateDebutDu());
	 	String annee = String.valueOf(dateDebutCal.get(Calendar.YEAR));
		parameters.put("ANNEE", annee);
		return parameters;
	}
		
}
