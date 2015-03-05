package com.lotoquebec.cardex.presentation.model.form.rapport.regroupement;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.regroupement.GlobalTotalHeuresRegroupementGenerateurRapport_CDX_0087;

public class GlobalTotalHeuresRegroupementRapportForm_CDX_0087 extends RegroupementRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new GlobalTotalHeuresRegroupementGenerateurRapport_CDX_0087();
	}

}
