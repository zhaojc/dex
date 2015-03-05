package com.lotoquebec.cardex.presentation.model.form.rapport.suivi;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.suivi.Jours30IntervenantSuiviGenerateurRapport_CDX_0093;

public class Jours30IntervenantSuiviRapportForm_CDX_0093 extends SuiviRapportForm{

	@Override
	public GenererRapport getGenererRapport(){
		return new Jours30IntervenantSuiviGenerateurRapport_CDX_0093();
	}
	
}
