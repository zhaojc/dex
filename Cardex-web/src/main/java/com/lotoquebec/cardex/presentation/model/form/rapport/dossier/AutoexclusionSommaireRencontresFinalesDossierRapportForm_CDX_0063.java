package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.AutoexclusionSommaireRencontresFinalesDossierGenerateurRapport_CDX_0063;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class AutoexclusionSommaireRencontresFinalesDossierRapportForm_CDX_0063 extends CriteresRapportForm{

	public AutoexclusionSommaireRencontresFinalesDossierRapportForm_CDX_0063() {
		super();
	}
	
	@Override
	public GenererRapport getGenererRapport() {
		return new AutoexclusionSommaireRencontresFinalesDossierGenerateurRapport_CDX_0063();
	}
	
}
