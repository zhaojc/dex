package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.AmbulanceSommaireDossierGenerateurRapport_CDX_0270;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class AmbulanceSommaireDossierRapportForm_CDX_0270 extends CriteresRapportForm{
	
	public AmbulanceSommaireDossierRapportForm_CDX_0270() {
		super();
	}

	@Override
	public GenererRapport getGenererRapport() {
		return new AmbulanceSommaireDossierGenerateurRapport_CDX_0270();
	}
	
}
