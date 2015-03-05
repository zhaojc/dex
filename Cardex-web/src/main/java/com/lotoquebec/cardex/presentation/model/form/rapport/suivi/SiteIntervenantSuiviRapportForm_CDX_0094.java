package com.lotoquebec.cardex.presentation.model.form.rapport.suivi;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.suivi.SiteIntervenantSuiviGenerateurRapport_CDX_0094;

public class SiteIntervenantSuiviRapportForm_CDX_0094 extends SuiviRapportForm{
	
	@Override
	public GenererRapport getGenererRapport(){
		return new SiteIntervenantSuiviGenerateurRapport_CDX_0094();
	}
}
