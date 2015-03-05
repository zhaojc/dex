package com.lotoquebec.cardex.presentation.model.form.rapport.regroupement;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.regroupement.IntervenantCategorieRegroupementGenerateurRapport_CDX_0084;

public class IntervenantCategorieRegroupementRapportForm_CDX_0084 extends RegroupementRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new IntervenantCategorieRegroupementGenerateurRapport_CDX_0084();
	}

}
