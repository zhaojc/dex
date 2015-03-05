package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.AutoexclusionDossierGenerateurRapport_CDX_0220;
import com.lotoquebec.cardex.presentation.model.form.rapport.EntiteRapportForm;

public class AutoexclusionEntiteRapportForm_CDX_0220 extends EntiteRapportForm{

	@Override
	public GenererRapport getGenererRapport() {
		return new AutoexclusionDossierGenerateurRapport_CDX_0220();
	}
	
}
