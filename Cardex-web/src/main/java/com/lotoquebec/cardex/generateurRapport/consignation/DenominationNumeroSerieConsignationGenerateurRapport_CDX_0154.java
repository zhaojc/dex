package com.lotoquebec.cardex.generateurRapport.consignation;

import java.io.InputStream;

import com.lotoquebec.cardex.business.vo.rapport.ConsignationRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.integration.dao.sql.rapport.RapportDenominationNumeroSerie_CDX_0154;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class DenominationNumeroSerieConsignationGenerateurRapport_CDX_0154 extends ConsignationGenerateurRapport {

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_DENOMINATION_NO_SERIE);
	}

	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/denominationNumeroSerieConsignationRapport");
	}

	@Override
	protected PreparerSQL construirePreparerSQL(RapportVO rapportVO) {
		ConsignationRapportVO consignationRapportVO = (ConsignationRapportVO) rapportVO;
		return RapportDenominationNumeroSerie_CDX_0154.construireSQL(consignationRapportVO);
	}


}
