package com.lotoquebec.cardex.presentation.model.form.rapport.consignation;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.consignation.DenominationConsignationGenerateurRapport_CDX_0152;

public class DenominationConsignationCriteresRapportForm_CDX_0152 extends ConsignationCriteresRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new DenominationConsignationGenerateurRapport_CDX_0152();
	}
	
}
