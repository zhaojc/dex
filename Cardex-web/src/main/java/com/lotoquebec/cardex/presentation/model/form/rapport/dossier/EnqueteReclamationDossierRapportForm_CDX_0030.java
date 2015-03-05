package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.EnqueteReclamationDossierGenerateurRapport_CDX_0030;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class EnqueteReclamationDossierRapportForm_CDX_0030 extends CriteresRapportForm{
	
	public EnqueteReclamationDossierRapportForm_CDX_0030() {
		super();
	}
	 
	@Override
	public GenererRapport getGenererRapport() {
		return new EnqueteReclamationDossierGenerateurRapport_CDX_0030();
	}
	
}
