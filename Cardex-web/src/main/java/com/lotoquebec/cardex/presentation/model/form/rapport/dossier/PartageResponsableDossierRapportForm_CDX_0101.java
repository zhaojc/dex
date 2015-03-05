package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.PartageResponsableDossierGenerateurRapport_CDX_0101;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class PartageResponsableDossierRapportForm_CDX_0101 extends CriteresRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new PartageResponsableDossierGenerateurRapport_CDX_0101();
	}
	
}
