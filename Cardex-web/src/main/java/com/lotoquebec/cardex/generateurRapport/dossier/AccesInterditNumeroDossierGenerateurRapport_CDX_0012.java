package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.ActifIntervenantDossierRapportVO_CDX_0102;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class AccesInterditNumeroDossierGenerateurRapport_CDX_0012 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersPartagesParIntervenant");
	}
	
	@Override
	public RapportVO construireNouveauRapportVO() {
		return new ActifIntervenantDossierRapportVO_CDX_0102();
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		ResultSet resultSet = delegate.listesPartage("CARDEX_RAPPORT.SP_LISTES_PARTAGE");
       	return new JRResultSetDataSource(resultSet);
	}

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.DOSSIER_PARTAGE_RESPONSABLE);
	}

}
