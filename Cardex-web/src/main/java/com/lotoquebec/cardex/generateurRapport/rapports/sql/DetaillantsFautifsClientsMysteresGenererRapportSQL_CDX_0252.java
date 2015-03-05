package com.lotoquebec.cardex.generateurRapport.rapports.sql;

import java.io.InputStream;

import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.integration.dao.sql.rapport.DetaillantsFautifsClientsMysteresSQL_CDX_0252;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;


public class DetaillantsFautifsClientsMysteresGenererRapportSQL_CDX_0252 extends StatistiqueDossierGenererRapportSQL {

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_DETAILLANTS_FAUTIFS);
	}

	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/detaillantsFautifsClientsMysteres");
	}

	@Override
	protected PreparerSQL construirePreparerSQL(RapportVO rapportVO) {
		return DetaillantsFautifsClientsMysteresSQL_CDX_0252.construireSQL((StatistiqueDossierRapportVO) rapportVO);
	}

}
