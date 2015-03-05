package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.SuiviDossierGenerateurRapport_CDX_0223;
import com.lotoquebec.cardex.presentation.model.form.rapport.EntiteRapportForm;

public class SuiviEntiteRapportForm_CDX_0223 extends EntiteRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new SuiviDossierGenerateurRapport_CDX_0223();
	}
	
}
