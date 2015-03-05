package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.FacturationSQGenerateurRapport_CDX_0241;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class FacturationSQForm_CDX_0241 extends CriteresRapportForm{
	
	public FacturationSQForm_CDX_0241() {
		super();
	}
	 
	@Override
	public GenererRapport getGenererRapport() {
		return new FacturationSQGenerateurRapport_CDX_0241();
	}
	
}
