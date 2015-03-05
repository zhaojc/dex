package com.lotoquebec.cardex.business.vo.rapport;


public class SeveriteRapportVO extends RapportVO{

	private long severite = 0;
	private long site = 0;
	
	public long getSeverite() {
		return severite;
	}
	
	public void setSeverite(long severite) {
		this.severite = severite;
	}

	public long getSite() {
		return site;
	}

	public void setSite(long site) {
		this.site = site;
	}
	
}
