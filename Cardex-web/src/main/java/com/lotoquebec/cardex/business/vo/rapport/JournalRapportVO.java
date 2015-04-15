package com.lotoquebec.cardex.business.vo.rapport;


/**
 * Utiliser par 
 * CDX_0020	Rapport journal sommaire
 * CDX_0021	Rapport journal d�taill�
 * CDX_0022	Rapport journal par origines
 * CDX_0023	Rapport journal par descriptifs
 * 
 * @author levassc
 *
 */
public class JournalRapportVO extends CritereRapportVO{

	protected long entite = 0;
	protected long site = 0;
	protected long genre = 0;
	protected long nature = 0;
	protected long type = 0;
	protected long categorie = 0;
	protected String intervenant = "";
	protected String numeroDossier = "";
	protected String reference = "";
	protected long secteur = 0;
	protected String descriptif = "";
    private long fonde = 0;
    private long origine = 0;
    private long endroit = 0;
    private long localisation = 0;
	
    
    
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
	
	public long getGenre() {
		return genre;
	}
	
	public void setGenre(long genre) {
		this.genre = genre;
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
	
	public long getCategorie() {
		return categorie;
	}
	
	public void setCategorie(long categorie) {
		this.categorie = categorie;
	}
	
	public String getIntervenant() {
		return intervenant;
	}
	
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}
	
	public long getSecteur() {
		return secteur;
	}
	
	public void setSecteur(long secteur) {
		this.secteur = secteur;
	}
	
	public String getDescriptif() {
		return descriptif;
	}
	
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	
	public long getFonde() {
		return fonde;
	}
	
	public void setFonde(long fonde) {
		this.fonde = fonde;
	}
	
	public long getOrigine() {
		return origine;
	}
	
	public void setOrigine(long origine) {
		this.origine = origine;
	}
	
	public long getEndroit() {
		return endroit;
	}
	
	public void setEndroit(long endroit) {
		this.endroit = endroit;
	}
	
	public long getLocalisation() {
		return localisation;
	}

	public void setLocalisation(long localisation) {
		this.localisation = localisation;
	}

	/**
	 * @return numeroDossier
	 */
	public String getNumeroDossier() {
		return numeroDossier;
	}

	/**
	 * @param numeroDossier numeroDossier � d�finir
	 */
	public void setNumeroDossier(String numeroDossier) {
		this.numeroDossier = numeroDossier;
	}

	/**
	 * @return reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference reference � d�finir
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}	
	
}
