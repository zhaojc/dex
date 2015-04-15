package com.lotoquebec.cardex.business.vo.rapport;


public class AmbulanceDossierRapportVO extends CritereRapportVO{

	private long entite = 0;
	private long site = 0;
	
	public long getEntite() {
		return entite;
	}
	
	public void setEntite(long entite) {
		this.entite = entite;
	}
	
	public long getSite() {
		return site;
	}
	
	public void setSite(long site) {
		this.site = site;
	}
	
}
