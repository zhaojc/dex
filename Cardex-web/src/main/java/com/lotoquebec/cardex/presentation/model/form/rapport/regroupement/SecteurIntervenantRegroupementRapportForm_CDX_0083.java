package com.lotoquebec.cardex.presentation.model.form.rapport.regroupement;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.regroupement.SecteurIntervenantRegroupementGenerateurRapport_CDX_0083;

public class SecteurIntervenantRegroupementRapportForm_CDX_0083 extends RegroupementRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new SecteurIntervenantRegroupementGenerateurRapport_CDX_0083();
	}

}
