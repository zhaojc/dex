package com.lotoquebec.cardex.presentation.model.form.rapport.journal;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.journal.OrigineJournalGenerateurRapport_CDX_0022;

public class OrigineJournalRapportForm_CDX_0022 extends JournalRapportForm{


	@Override
	public GenererRapport getGenererRapport(){
		return new OrigineJournalGenerateurRapport_CDX_0022();
	}
	 
}
