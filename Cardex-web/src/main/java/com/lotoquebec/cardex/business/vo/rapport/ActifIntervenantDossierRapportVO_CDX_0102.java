package com.lotoquebec.cardex.business.vo.rapport;


public class ActifIntervenantDossierRapportVO_CDX_0102 extends CritereRapportVO{

	private long entite = 0;
	private long genre = 0;
	private long site = 0;
	private String intervenant = "";
	
	public long getEntite() {
		return entite;
	}
	
	public void setEntite(long entite) {
		this.entite = entite;
	}
	
	public long getGenre() {
		return genre;
	}
	
	public void setGenre(long genre) {
		this.genre = genre;
	}
	
	public long getSite() {
		return site;
	}
	
	public void setSite(long site) {
		this.site = site;
	}
	
	public String getIntervenant() {
		return intervenant;
	}
	
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}
	
}
