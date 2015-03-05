package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class AutoexclusionDetailleRencontresFinalesDossierGenerateurRapport_CDX_0064 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersAutoexclusionDetailleRencontresFinales");
	}
	
	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
       	ResultSet resultSet = delegate.rapportProcedure(rapportVO, "CARDEX_RAPPORT.SP_MENSUEL_RENC_FINALES_DETAIL");
       	return new JRResultSetDataSource(resultSet);
	}


	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.MENSUEL_RENCONTRES_FINALES_DETAIL);
	}

}
