package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.AutoexclusionEspaceJeuxDossierGenerateurRapport_CDX_0221;
import com.lotoquebec.cardex.presentation.model.form.rapport.EntiteRapportForm;

public class AutoexclusionEspaceJeuxEntiteRapportForm_CDX_0221 extends EntiteRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new AutoexclusionEspaceJeuxDossierGenerateurRapport_CDX_0221();
	}
	
}
