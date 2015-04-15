package com.lotoquebec.cardex.presentation.model.form.rapport;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;

public abstract class EntiteRapportForm extends CriteresRapportForm {

	private String cle = "";
	private String site = "";

	public EntiteRapportForm(GenererRapport genererRapport) {
		super(genererRapport);
	}

	public String getCle() {
		return cle;
	}

	public void setCle(String cle) {
		this.cle = cle;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
}
