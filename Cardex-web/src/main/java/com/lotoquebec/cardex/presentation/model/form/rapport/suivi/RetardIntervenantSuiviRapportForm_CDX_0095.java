package com.lotoquebec.cardex.presentation.model.form.rapport.suivi;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.suivi.RetardIntervenantSuiviGenerateurRapport_CDX_0095;

public class RetardIntervenantSuiviRapportForm_CDX_0095 extends SuiviRapportForm{

	@Override
	public GenererRapport getGenererRapport(){
		return new RetardIntervenantSuiviGenerateurRapport_CDX_0095();
	}
	
}
