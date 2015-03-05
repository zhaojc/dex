package com.lotoquebec.cardex.presentation.model.form.rapport;

public abstract class EntiteRapportForm extends CriteresRapportForm {

	private String cle = "";
	private String site = "";
	
	public EntiteRapportForm() {
		super();
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
