package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.PartageIntervenantDossierGenerateurRapport_CDX_0100;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class PartageIntervenantDossierRapportForm_CDX_0100 extends CriteresRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new PartageIntervenantDossierGenerateurRapport_CDX_0100();
	}
	
}
