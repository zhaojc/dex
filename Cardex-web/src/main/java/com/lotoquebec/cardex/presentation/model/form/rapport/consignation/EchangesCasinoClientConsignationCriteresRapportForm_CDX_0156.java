package com.lotoquebec.cardex.presentation.model.form.rapport.consignation;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.consignation.EchangesCasinoClientConsignationGenerateurRapport_CDX_0156;

public class EchangesCasinoClientConsignationCriteresRapportForm_CDX_0156 extends ConsignationCriteresRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new EchangesCasinoClientConsignationGenerateurRapport_CDX_0156();
	}
	
}
