package com.lotoquebec.cardex.generateurRapport.sujet;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;

public class SujetGenerateurRapport_CDX_0002 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteAdhoc(subject, "cardex.sujet.base.imprimer");
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, VO vo, Connection connection) throws BusinessResourceException, BusinessException {
		Sujet sujet = (Sujet) vo;
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		ResultSet resultSet = delegate.rapportImpressionFiche(sujet.getCle(), sujet.getSite(), "CARDEX_RAPPORT.SP_IMPRESSION_SUJET", connection);
        return new JRResultSetDataSource(resultSet);
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.SUJET_CDX_0002);
	}

	protected Map<String,Object> construireParametres(CardexAuthenticationSubject subject, VO vo, Connection connection) throws JRException {
		CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();
		Map<String,Object> parameters = super.construireParametres(subject, vo, connection);
		parameters.put("CONFIDENTIALITE", Long.toString(privilege.getNiveauConfidentialite()));
		return parameters;
	}
	
}
