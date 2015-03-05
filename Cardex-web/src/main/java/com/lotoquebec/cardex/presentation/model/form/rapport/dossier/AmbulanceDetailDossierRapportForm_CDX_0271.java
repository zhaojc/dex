package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.AmbulanceDetailDossierGenerateurRapport_CDX_0271;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class AmbulanceDetailDossierRapportForm_CDX_0271 extends CriteresRapportForm{
	
	public AmbulanceDetailDossierRapportForm_CDX_0271() {
		super();
	}

	@Override
	public GenererRapport getGenererRapport() {
		return new AmbulanceDetailDossierGenerateurRapport_CDX_0271();
	}
	
}
