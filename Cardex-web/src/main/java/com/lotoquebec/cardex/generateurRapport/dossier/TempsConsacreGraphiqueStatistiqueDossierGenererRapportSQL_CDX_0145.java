package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;


public class TempsConsacreGraphiqueStatistiqueDossierGenererRapportSQL_CDX_0145 extends CritereGenererRapport {

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_STATISTIQUE_DOSSIER_TEMPS_CONSACRE);
	}

	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersStatistiqueTempsConsacre");
	}

	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new StatistiqueDossierRapportVO();
	}
	
	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, VO rapportVO, Connection connection) throws JRException {
		StatistiqueDossierRapportVO statistiqueDossierRapportVO = (StatistiqueDossierRapportVO) rapportVO;
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		parameters.put("SITE", statistiqueDossierRapportVO.getSite());
		return parameters;
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
       	ResultSet resultSet = delegate.tempsConsacre((StatistiqueDossierRapportVO) rapportVO,connection);
       	return new JRResultSetDataSource(resultSet);
	}


}
