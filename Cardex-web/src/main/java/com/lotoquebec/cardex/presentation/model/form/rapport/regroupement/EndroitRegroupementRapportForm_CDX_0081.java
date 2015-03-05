package com.lotoquebec.cardex.presentation.model.form.rapport.regroupement;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.regroupement.EndroitRegroupementGenerateurRapport_CDX_0081;

public class EndroitRegroupementRapportForm_CDX_0081 extends RegroupementRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new EndroitRegroupementGenerateurRapport_CDX_0081();
	}

}
