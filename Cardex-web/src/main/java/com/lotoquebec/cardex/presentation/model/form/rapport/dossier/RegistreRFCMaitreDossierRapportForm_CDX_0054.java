package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.RegistreRFCMaitreDossierGenerateurRapport_CDX_0054;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class RegistreRFCMaitreDossierRapportForm_CDX_0054 extends CriteresRapportForm{

	public RegistreRFCMaitreDossierRapportForm_CDX_0054() {
		super();
	}

	@Override
	public GenererRapport getGenererRapport() {
		return new RegistreRFCMaitreDossierGenerateurRapport_CDX_0054();
	}
	
}
