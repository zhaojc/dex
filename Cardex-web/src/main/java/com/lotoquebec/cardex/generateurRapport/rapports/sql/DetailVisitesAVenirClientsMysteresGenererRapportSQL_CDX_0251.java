package com.lotoquebec.cardex.generateurRapport.rapports.sql;

import java.io.InputStream;

import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.integration.dao.sql.rapport.DetailVisitesAVenirClientsMysteresSQL_CDX_0251;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;


public class DetailVisitesAVenirClientsMysteresGenererRapportSQL_CDX_0251 extends StatistiqueDossierGenererRapportSQL {

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_DETAIL_VISITES_A_VENIR);
	}

	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/detailVisitesAVenirClientsMysteres");
	}

	@Override
	protected PreparerSQL construirePreparerSQL(RapportVO rapportVO) {
		return DetailVisitesAVenirClientsMysteresSQL_CDX_0251.construireSQL((StatistiqueDossierRapportVO) rapportVO);
	}

}
