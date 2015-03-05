package com.lotoquebec.cardex.presentation.model.form.rapport.dossier;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.generateurRapport.dossier.ComparatifAnnuelEnquetesDossierGenerateurRapport_CDX_0040;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;


public class ComparatifAnnuelEnquetesDossierRapportForm_CDX_0040 extends CriteresRapportForm{
	
	public ComparatifAnnuelEnquetesDossierRapportForm_CDX_0040() {
		super();
	}

	@Override
	public GenererRapport getGenererRapport() {
		return new ComparatifAnnuelEnquetesDossierGenerateurRapport_CDX_0040();
	}
	
}
