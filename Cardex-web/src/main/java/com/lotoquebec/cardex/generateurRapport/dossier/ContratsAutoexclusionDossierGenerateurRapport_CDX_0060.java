package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.ContratsAutoexclusionDossierRapportVO_CDX_0060;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class ContratsAutoexclusionDossierGenerateurRapport_CDX_0060 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersContratsAutoexclusion");
	}
	
	@Override
	public RapportVO construireNouveauRapportVO() {
		return new ContratsAutoexclusionDossierRapportVO_CDX_0060();
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		ContratsAutoexclusionDossierRapportVO_CDX_0060 contratsAutoexclusionDossierRapportVO_CDX_0060 = (ContratsAutoexclusionDossierRapportVO_CDX_0060) rapportVO;
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		ResultSet resultSet = delegate.rapportContrats(contratsAutoexclusionDossierRapportVO_CDX_0060);
       	return new JRResultSetDataSource(resultSet);
	}


	@Override
	protected InputStream obtenirGabarit() {
		//return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.MENSUEL_AUTOEXCLUSIONS);
		return this.getClass().getResourceAsStream("/rapports/"+RapportsConfiguration.MENSUEL_AUTOEXCLUSIONS);
	}

}
