package com.lotoquebec.cardex.generateurRapport.suivi;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.business.vo.rapport.SiteIntervenantRapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class Jours30SuiviGenerateurRapport_CDX_0090 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/suivi30Jours");
	}
	
	@Override
	public RapportVO construireNouveauRapportVO() {
		return new SiteIntervenantRapportVO();
	}
	
	protected Map construireParametres(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws JRException {
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		parameters.put("sous_rapport_suivis_plus_30_jours", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.SOUS_RAPPORT_SUIVIS_PLUS_30_JOURS)));
		parameters.put("sous_rapport_suivis_plus_60_jours", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.SOUS_RAPPORT_SUIVIS_PLUS_60_JOURS)));
		parameters.put("sous_rapport_suivis_plus_90_jours", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.SOUS_RAPPORT_SUIVIS_PLUS_90_JOURS)));
		return parameters;
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
       	ResultSet resultSet = delegate.rapportSuivis((SiteIntervenantRapportVO) rapportVO, "CARDEX_RAPPORT.SP_RAP_SUIVI_30_JOURS");
       	return new JRResultSetDataSource(resultSet);
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.SUIVIS_30_JOURS);
	}

}