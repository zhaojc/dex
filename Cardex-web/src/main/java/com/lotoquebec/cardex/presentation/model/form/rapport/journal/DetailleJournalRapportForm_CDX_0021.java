package com.lotoquebec.cardex.presentation.model.form.rapport.journal;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.journal.DetailleJournalGenerateurRapport_CDX_0021;

public class DetailleJournalRapportForm_CDX_0021 extends JournalRapportForm{


	@Override
	public GenererRapport getGenererRapport(){
		return new DetailleJournalGenerateurRapport_CDX_0021();
	}
	 
}
