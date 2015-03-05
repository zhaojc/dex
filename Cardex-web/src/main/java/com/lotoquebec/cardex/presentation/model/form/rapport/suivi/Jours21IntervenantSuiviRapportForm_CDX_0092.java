package com.lotoquebec.cardex.presentation.model.form.rapport.suivi;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.suivi.Jours21IntervenantSuiviGenerateurRapport_CDX_0092;

public class Jours21IntervenantSuiviRapportForm_CDX_0092 extends SuiviRapportForm{
	
	@Override
	public GenererRapport getGenererRapport(){
		return new Jours21IntervenantSuiviGenerateurRapport_CDX_0092();
	}
	
}
