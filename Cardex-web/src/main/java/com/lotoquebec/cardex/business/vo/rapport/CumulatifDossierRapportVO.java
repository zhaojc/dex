package com.lotoquebec.cardex.business.vo.rapport;

/**
 * Utiliser par 
 * CDX_0143	Rapport cumulatif des dossiers Espacejeux
 * CDX_0144	Rapport hebdomadaire des dossiers Espacejeux
 * CDX_0146	Rapport hebdomadaire des dossiers
 * CDX_0147	Rapport cumulatif des dossiers
 * 
 * @author levassc
 *
 */
public class CumulatifDossierRapportVO extends RapportVO{

	private long genre = 0;
	private long site = 0;

	
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

	
}
