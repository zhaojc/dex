package com.lotoquebec.cardex.presentation.model.form.rapport.regroupement;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.regroupement.SecteurCategorieRegroupementGenerateurRapport_CDX_0082;

public class SecteurCategorieRegroupementRapportForm_CDX_0082 extends RegroupementRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new SecteurCategorieRegroupementGenerateurRapport_CDX_0082();
	}

}
