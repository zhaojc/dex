package com.lotoquebec.cardex.presentation.model.form.rapport.suivi;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.suivi.Jours30SuiviGenerateurRapport_CDX_0090;

public class Jours30SuiviRapportForm_CDX_0090 extends SuiviRapportForm{
	
	@Override
	public GenererRapport getGenererRapport(){
		return new Jours30SuiviGenerateurRapport_CDX_0090();
	}
	
}
