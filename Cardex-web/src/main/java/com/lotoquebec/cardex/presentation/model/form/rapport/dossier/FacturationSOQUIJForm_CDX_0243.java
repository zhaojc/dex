package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.FacturationSOQUIJGenerateurRapport_CDX_0243;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class FacturationSOQUIJForm_CDX_0243 extends CriteresRapportForm{
	
	public FacturationSOQUIJForm_CDX_0243() {
		super();
	}
	 
	@Override
	public GenererRapport getGenererRapport() {
		return new FacturationSOQUIJGenerateurRapport_CDX_0243();
	}
	
}
