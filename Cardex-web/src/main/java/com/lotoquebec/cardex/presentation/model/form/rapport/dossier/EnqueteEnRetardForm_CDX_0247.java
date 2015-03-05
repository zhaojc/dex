package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.EnqueteEnRetardGenerateurRapport_CDX_0247;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class EnqueteEnRetardForm_CDX_0247 extends CriteresRapportForm{
	
	public EnqueteEnRetardForm_CDX_0247() {
		super();
	}
	 
	@Override
	public GenererRapport getGenererRapport() {
		return new EnqueteEnRetardGenerateurRapport_CDX_0247();
	}
	
}
