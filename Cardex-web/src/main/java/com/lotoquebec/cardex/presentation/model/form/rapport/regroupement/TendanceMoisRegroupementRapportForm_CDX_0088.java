package com.lotoquebec.cardex.presentation.model.form.rapport.regroupement;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.regroupement.TendanceMoisRegroupementGenerateurRapport_CDX_0088;

public class TendanceMoisRegroupementRapportForm_CDX_0088 extends RegroupementRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new TendanceMoisRegroupementGenerateurRapport_CDX_0088();
	}

}
