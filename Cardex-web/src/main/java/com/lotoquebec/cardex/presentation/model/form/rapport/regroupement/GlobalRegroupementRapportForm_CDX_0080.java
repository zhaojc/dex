package com.lotoquebec.cardex.presentation.model.form.rapport.regroupement;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.regroupement.GlobalRegroupementGenerateurRapport_CDX_0080;

public class GlobalRegroupementRapportForm_CDX_0080 extends RegroupementRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new GlobalRegroupementGenerateurRapport_CDX_0080();
	}

}
