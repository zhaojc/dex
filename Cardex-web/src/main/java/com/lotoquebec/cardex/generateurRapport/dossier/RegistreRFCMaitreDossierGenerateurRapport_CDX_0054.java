package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.vo.VO;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class RegistreRFCMaitreDossierGenerateurRapport_CDX_0054 extends CritereGenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersRegistreRFCMaitre");
	}
	
	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new CritereRapportVO();
	}
	
	@Override
	protected Map construireParametres(CardexAuthenticationSubject subject, VO rapportVO, Connection connection) throws JRException {
		Map parameters = super.construireParametres(subject, rapportVO, connection);
		parameters.put("sous_rapport_registre_RFC_total", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.REGISTRE_RFC_MAITRE_SOUS_RAPPORT_TOTAL)));
		parameters.put("sous_rapport_registre_RFC", JRLoader.loadObject(RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.REGISTRE_RFC_MAITRE_SOUS_RAPPORT)));
		return parameters;
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
       	ResultSet resultSet = delegate.rapportProcedure(rapportVO, "CARDEX_RAPPORT.SP_RAP_REGISTRE_RFC_MAITRE",connection);
       	return new JRResultSetDataSource(resultSet);
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.REGISTRE_RFC_MAITRE);
	}

}
