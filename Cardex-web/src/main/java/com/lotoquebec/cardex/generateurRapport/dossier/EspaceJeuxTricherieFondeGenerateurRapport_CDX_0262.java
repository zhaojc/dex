package com.lotoquebec.cardex.generateurRapport.dossier;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;

import com.lotoquebec.cardex.business.delegate.RapportBusinessDelegate;
import com.lotoquebec.cardex.business.vo.rapport.EspaceJeuxTricherieFondeRapportVO_CDX_0262;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class EspaceJeuxTricherieFondeGenerateurRapport_CDX_0262 extends GenererRapport {

	@Override
	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersEspaceJeuxTricherieFonde");
	}
	
	@Override
	public RapportVO construireNouveauRapportVO() {
		return new EspaceJeuxTricherieFondeRapportVO_CDX_0262();
	}

	@Override
	public JRDataSource construireDataSource(CardexAuthenticationSubject subject, RapportVO rapportVO, Connection connection) throws BusinessResourceException, BusinessException {
		EspaceJeuxTricherieFondeRapportVO_CDX_0262 espaceJeuxTricherieFondeRapportVO_CDX_0262 = (EspaceJeuxTricherieFondeRapportVO_CDX_0262) rapportVO;
		RapportBusinessDelegate delegate = new RapportBusinessDelegate();
		ResultSet resultSet = delegate.rapportEspaceJeuxTricherieFonde(espaceJeuxTricherieFondeRapportVO_CDX_0262);
       	return new JRResultSetDataSource(resultSet);
	}


	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.TRICHERIE_FONDE_ESPACEJEUX);
	}

}
