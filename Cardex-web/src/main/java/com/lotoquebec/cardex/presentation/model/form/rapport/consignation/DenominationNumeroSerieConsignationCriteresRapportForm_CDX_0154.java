package com.lotoquebec.cardex.presentation.model.form.rapport.consignation;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.consignation.DenominationNumeroSerieConsignationGenerateurRapport_CDX_0154;

public class DenominationNumeroSerieConsignationCriteresRapportForm_CDX_0154 extends ConsignationCriteresRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new DenominationNumeroSerieConsignationGenerateurRapport_CDX_0154();
	}
	
}
