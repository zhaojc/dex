package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.EnqueteTraitementEnRetardGenerateurRapport_CDX_0248;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class EnqueteTraitementEnRetardForm_CDX_0248 extends CriteresRapportForm{
	
	public EnqueteTraitementEnRetardForm_CDX_0248() {
		super();
	}
	 
	@Override
	public GenererRapport getGenererRapport() {
		return new EnqueteTraitementEnRetardGenerateurRapport_CDX_0248();
	}
	
}
