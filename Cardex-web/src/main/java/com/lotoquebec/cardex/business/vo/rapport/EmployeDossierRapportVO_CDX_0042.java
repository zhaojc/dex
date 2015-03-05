package com.lotoquebec.cardex.business.vo.rapport;


/**
 * Utiliser par 
 * CDX_0042	Rapport employés liés à des dossiers
 * 
 * @author levassc
 *
 */
public class EmployeDossierRapportVO_CDX_0042 extends RapportVO{

	private long entite = 0;
	private long site = 0;
	private long genre = 0;
	private long nature = 0;
	private long type = 0;
	private long fonde = 0;
	private long categorie = 0;
	private long statut = 0;
	private long role = 0;
	
	public long getSite() {
		return site;
	}
	
	public void setSite(long site) {
		this.site = site;
	}
	
	public long getNature() {
		return nature;
	}
	
	public void setNature(long nature) {
		this.nature = nature;
	}
	
	public long getType() {
		return type;
	}
	
	public void setType(long type) {
		this.type = type;
	}
	
	public long getFonde() {
		return fonde;
	}
	
	public void setFonde(long fonde) {
		this.fonde = fonde;
	}

	public long getGenre() {
		return genre;
	}

	public void setGenre(long genre) {
		this.genre = genre;
	}

	/**
	 * @return categorie
	 */
	public long getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie categorie à définir
	 */
	public void setCategorie(long categorie) {
		this.categorie = categorie;
	}

	public long getStatut() {
		return statut;
	}

	public void setStatut(long statut) {
		this.statut = statut;
	}

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
	}

	public long getEntite() {
		return entite;
	}

	public void setEntite(long entite) {
		this.entite = entite;
	}
	
}
