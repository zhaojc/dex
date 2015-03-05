package com.lotoquebec.cardex.generateurRapport.rapports.sql;

import java.io.InputStream;

import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.integration.dao.sql.rapport.MoisGraphiqueStatistiqueDossierRapportSQL_CDX_0141;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;


public class MoisGraphiqueStatistiqueDossierGenererRapportSQL_CDX_0141 extends StatistiqueDossierGenererRapportSQL {

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_STATISTIQUE_DOSSIER_MOIS);
	}

	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/dossiersStatistiqueMoisGraphique");
	}

	@Override
	protected PreparerSQL construirePreparerSQL(RapportVO rapportVO) {
		return MoisGraphiqueStatistiqueDossierRapportSQL_CDX_0141.construireSQL((StatistiqueDossierRapportVO) rapportVO);
	}

}
