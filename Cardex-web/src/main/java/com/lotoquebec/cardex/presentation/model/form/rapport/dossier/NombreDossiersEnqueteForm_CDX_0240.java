package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.NombreDossiersEnqueteGenerateurRapport_CDX_0240;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class NombreDossiersEnqueteForm_CDX_0240 extends CriteresRapportForm{
	
	public NombreDossiersEnqueteForm_CDX_0240() {
		super();
	}
	 
	@Override
	public GenererRapport getGenererRapport() {
		return new NombreDossiersEnqueteGenerateurRapport_CDX_0240();
	}
	
}
