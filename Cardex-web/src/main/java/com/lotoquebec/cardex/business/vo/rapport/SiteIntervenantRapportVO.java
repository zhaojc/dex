package com.lotoquebec.cardex.business.vo.rapport;


public class SiteIntervenantRapportVO extends CritereRapportVO{

	private String intervenant = "";
	private long site = 0;
	private long activite = 0;
	private long nature = 0;
	
	public String getIntervenant() {
		return intervenant;
	}
	
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	public long getSite() {
		return site;
	}

	public void setSite(long site) {
		this.site = site;
	}
	
	public long getActivite() {
		return activite;
	}

	public void setActivite(long activite) {
		this.activite = activite;
	}

	public long getNature() {
		return nature;
	}

	public void setNature(long nature) {
		this.nature = nature;
	}

}
