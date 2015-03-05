package com.lotoquebec.cardex.presentation.model.form.rapport.suivi;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.suivi.Heures24IntervenantSuiviGenerateurRapport_CDX_0091;

public class Heures24IntervenantSuiviRapportForm_CDX_0091 extends SuiviRapportForm{

	@Override
	public GenererRapport getGenererRapport(){
		return new Heures24IntervenantSuiviGenerateurRapport_CDX_0091();
	}
	 
}
