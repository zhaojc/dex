package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.AutoexclusionDetailleRencontresFinalesDossierGenerateurRapport_CDX_0064;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class AutoexclusionDetailleRencontresInitialesDossierRapportForm_CDX_0064 extends CriteresRapportForm{

	public AutoexclusionDetailleRencontresInitialesDossierRapportForm_CDX_0064() {
		super();
	}

	@Override
	public GenererRapport getGenererRapport() {
		return new AutoexclusionDetailleRencontresFinalesDossierGenerateurRapport_CDX_0064();
	}
	
}
