package com.lotoquebec.cardex.presentation.model.form.rapport.consignation;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.consignation.DetailleConsignationGenerateurRapport_CDX_0151;

public class DetailleConsignationCriteresRapportForm_CDX_0151 extends ConsignationCriteresRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new DetailleConsignationGenerateurRapport_CDX_0151();
	}
	
}
