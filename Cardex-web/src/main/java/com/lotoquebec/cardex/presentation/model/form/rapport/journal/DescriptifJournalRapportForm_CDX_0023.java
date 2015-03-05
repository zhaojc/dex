package com.lotoquebec.cardex.presentation.model.form.rapport.journal;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.journal.DescriptifJournalGenerateurRapport_CDX_0023;

public class DescriptifJournalRapportForm_CDX_0023 extends JournalRapportForm{


	@Override
	public GenererRapport getGenererRapport(){
		return new DescriptifJournalGenerateurRapport_CDX_0023();
	}
	 
}
