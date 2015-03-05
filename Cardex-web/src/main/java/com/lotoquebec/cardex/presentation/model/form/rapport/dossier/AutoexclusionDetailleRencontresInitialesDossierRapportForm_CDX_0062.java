package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.AutoexclusionDetailleRencontresInitialesDossierGenerateurRapport_CDX_0062;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class AutoexclusionDetailleRencontresInitialesDossierRapportForm_CDX_0062 extends CriteresRapportForm{

	public AutoexclusionDetailleRencontresInitialesDossierRapportForm_CDX_0062() {
		super();
	}
	
	@Override
	public GenererRapport getGenererRapport() {
		return new AutoexclusionDetailleRencontresInitialesDossierGenerateurRapport_CDX_0062();
	}
	
}
