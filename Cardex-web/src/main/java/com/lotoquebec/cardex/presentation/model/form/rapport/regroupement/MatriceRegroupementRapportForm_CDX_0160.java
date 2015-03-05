package com.lotoquebec.cardex.presentation.model.form.rapport.regroupement;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.regroupement.MatriceRegroupementGenerateurRapport_CDX_0160;

public class MatriceRegroupementRapportForm_CDX_0160 extends RegroupementRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new MatriceRegroupementGenerateurRapport_CDX_0160();
	}

}
