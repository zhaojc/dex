package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.AutoexclusionSommaireRencontresInitialesDossierGenerateurRapport_CDX_0061;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class AutoexclusionSommaireRencontresInitialesDossierRapportForm_CDX_0061 extends CriteresRapportForm{

	public AutoexclusionSommaireRencontresInitialesDossierRapportForm_CDX_0061() {
		super();
	}

	@Override
	public GenererRapport getGenererRapport() {
		return new AutoexclusionSommaireRencontresInitialesDossierGenerateurRapport_CDX_0061();
	}
	
}
