package com.lotoquebec.cardex.generateurRapport.consignation;

import java.io.InputStream;

import com.lotoquebec.cardex.business.vo.rapport.ConsignationRapportVO;
import com.lotoquebec.cardex.business.vo.rapport.RapportVO;
import com.lotoquebec.cardex.generateurRapport.rapports.RapportsConfiguration;
import com.lotoquebec.cardex.integration.dao.sql.rapport.RapportNumeroSerieDenomination_CDX_0153;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.securite.GestionnaireSecurite;

public class NumeroSerieDenominationConsignationGenerateurRapport_CDX_0153 extends ConsignationGenerateurRapport {

	@Override
	protected InputStream obtenirGabarit() {
		return RapportsConfiguration.class.getResourceAsStream(RapportsConfiguration.RAPPORT_NO_SERIE_DENOMINATION);
	}

	public void validerSecurite(CardexAuthenticationSubject subject) {
		GestionnaireSecurite.validerSecuriteURL(subject, "/rapport/numeroSerieDenominationConsignationRapport");
	}

	@Override
	protected PreparerSQL construirePreparerSQL(RapportVO rapportVO) {
		ConsignationRapportVO consignationRapportVO = (ConsignationRapportVO) rapportVO;
		return RapportNumeroSerieDenomination_CDX_0153.construireSQL(consignationRapportVO);
	}


}
