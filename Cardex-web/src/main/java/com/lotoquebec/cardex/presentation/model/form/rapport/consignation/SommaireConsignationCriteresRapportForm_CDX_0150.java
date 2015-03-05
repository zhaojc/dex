package com.lotoquebec.cardex.presentation.model.form.rapport.consignation;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.consignation.SommaireConsignationGenerateurRapport_CDX_0150;

public class SommaireConsignationCriteresRapportForm_CDX_0150 extends ConsignationCriteresRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new SommaireConsignationGenerateurRapport_CDX_0150();
	}
	
}
