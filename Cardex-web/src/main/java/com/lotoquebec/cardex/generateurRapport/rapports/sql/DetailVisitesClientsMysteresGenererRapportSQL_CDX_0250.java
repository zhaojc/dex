package com.lotoquebec.cardex.generateurRapport.rapports.sql;

import java.io.InputStream;

import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.integration.dao.sql.rapport.DetailVisitesClientsMysteresSQL_CDX_0250;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;


public class DetailVisitesClientsMysteresGenererRapportSQL_CDX_0250 extends StatistiqueDossierGenererRapportSQL {

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_DETAIL_VISITES);
	}

	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/detailVisitesClientsMysteres");
	}

	@Override
	protected PreparerSQL construirePreparerSQL(RapportVO rapportVO) {
		return DetailVisitesClientsMysteresSQL_CDX_0250.construireSQL((StatistiqueDossierRapportVO) rapportVO);
	}

}
