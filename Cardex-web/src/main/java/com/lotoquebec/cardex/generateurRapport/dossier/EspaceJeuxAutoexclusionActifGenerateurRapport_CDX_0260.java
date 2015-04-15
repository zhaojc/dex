package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.EspaceJeuxAutoexclusionActifRapportVO_CDX_0260;
import com.lotoquebec.cardex.generateurRapport.CritereGenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class EspaceJeuxAutoexclusionActifGenerateurRapport_CDX_0260 extends CritereGenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersEspaceJeuxAutoexclusionActif");
	}
	
	@Override
	public CritereRapportVO construireNouveauRapportVO() {
		return new EspaceJeuxAutoexclusionActifRapportVO_CDX_0260();
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, CritereRapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		EspaceJeuxAutoexclusionActifRapportVO_CDX_0260 espaceJeuxAutoexclusionActifRapportVO_CDX_0260 = (EspaceJeuxAutoexclusionActifRapportVO_CDX_0260) rapportVO;
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		ResultSet resultSet = delegate.rapportEspaceJeuxAutoexclusionActif(espaceJeuxAutoexclusionActifRapportVO_CDX_0260,connection);
       	return new JRResultSetDataSource(resultSet);
	}


	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.AUTOEXCLUSION_ACTIF_ESPACEJEUX);
	}

}
