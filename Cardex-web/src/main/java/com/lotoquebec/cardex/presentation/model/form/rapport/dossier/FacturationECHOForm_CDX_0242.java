package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.FacturationECHOGenerateurRapport_CDX_0242;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class FacturationECHOForm_CDX_0242 extends CriteresRapportForm{
	
	public FacturationECHOForm_CDX_0242() {
		super();
	}
	 
	@Override
	public GenererRapport getGenererRapport() {
		return new FacturationECHOGenerateurRapport_CDX_0242();
	}
	
}
