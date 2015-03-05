package com.lotoquebec.cardex.presentation.model.form.rapport.regroupement;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.regroupement.IntervenantEndroitRegroupementGenerateurRapport_CDX_0085;

public class IntervenantEndroitRegroupementRapportForm_CDX_0085 extends RegroupementRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new IntervenantEndroitRegroupementGenerateurRapport_CDX_0085();
	}

}
