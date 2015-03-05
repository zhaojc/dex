package com.lotoquebec.cardex.presentation.model.form.rapport.regroupement;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.regroupement.GlobalCasinoRegroupementGenerateurRapport_CDX_0089;

public class GlobalCasinoRegroupementRapportForm_CDX_0089 extends RegroupementRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new GlobalCasinoRegroupementGenerateurRapport_CDX_0089();
	}

}
