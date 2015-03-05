package com.lotoquebec.cardex.presentation.model.form.rapport.consignation;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.consignation.NumeroSerieDenominationConsignationGenerateurRapport_CDX_0153;

public class NumeroSerieDenominationConsignationCriteresRapportForm_CDX_0153 extends ConsignationCriteresRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new NumeroSerieDenominationConsignationGenerateurRapport_CDX_0153();
	}
	
}
