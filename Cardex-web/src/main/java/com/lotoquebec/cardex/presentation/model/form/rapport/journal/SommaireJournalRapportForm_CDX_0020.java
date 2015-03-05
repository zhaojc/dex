package com.lotoquebec.cardex.presentation.model.form.rapport.journal;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.journal.SommaireJournalGenerateurRapport_CDX_0020;

public class SommaireJournalRapportForm_CDX_0020 extends JournalRapportForm{


	@Override
	public GenererRapport getGenererRapport(){
		return new SommaireJournalGenerateurRapport_CDX_0020();
	}
	 
}
