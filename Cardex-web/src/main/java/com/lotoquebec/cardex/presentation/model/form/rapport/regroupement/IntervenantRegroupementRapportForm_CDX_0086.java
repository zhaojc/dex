package com.lotoquebec.cardex.presentation.model.form.rapport.regroupement;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.regroupement.IntervenantRegroupementGenerateurRapport_CDX_0086;

public class IntervenantRegroupementRapportForm_CDX_0086 extends RegroupementRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new IntervenantRegroupementGenerateurRapport_CDX_0086();
	}

}
