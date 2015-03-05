package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.RapportIncidentsDCSIGenerateurRapport_CDX_0148;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class RapportIncidentsDCSIForm_CDX_0148 extends CriteresRapportForm{
	
	public RapportIncidentsDCSIForm_CDX_0148() {
		super();
	}
	 
	@Override
	public GenererRapport getGenererRapport() {
		return new RapportIncidentsDCSIGenerateurRapport_CDX_0148();
	}
	
}
