package com.lotoquebec.cardex.business.vo.rapport.regroupement;

import com.lotoquebec.cardex.business.vo.rapport.CritereRapportVO;

/**
 * Permet de transiter les informations relatives � la recherche d'un sujet de
 * la couche pr�sentation � la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.12 $, $Date: 2002/02/22 21:34:48 $
 * @see com.lotoquebec.cardex.business.CriteresRechercheSujet
 */
public abstract class RegroupementRapportVO extends CritereRapportVO {

    private long entite = 0;
    private long site = 0;
    private long genre = 0;
    private long nature = 0;
    private long type = 0;
    private long categorie = 0;
    private String intervenant = "";
    private String matricule = "";
    private long secteur = 0;
    private String endroit = "";
    private long regroupement = 0;    
    private Boolean heuresProductiveValide = false;
    private Boolean tousLesCasinos = false;
    
	public long getCategorie() {
		return categorie;
	}
	public void setCategorie(long categorie) {
		this.categorie = categorie;
	}
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
	public long getSecteur() {
		return secteur;
	}
	public void setSecteur(long secteur) {
		this.secteur = secteur;
	}
	public String getIntervenant() {
		return intervenant;
	}
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}
	public long getNature() {
		return nature;
	}
	public void setNature(long nature) {
		this.nature = nature;
	}
	public String getEndroit() {
		return endroit;
	}
	public void setEndroit(String endroit) {
		this.endroit = endroit;
	}
	public long getRegroupement() {
		return regroupement;
	}
	public void setRegroupement(long regroupement) {
		this.regroupement = regroupement;
	}
	public long getSite() {
		return site;
	}
	public void setSite(long site) {
		this.site = site;
	}
	public long getType() {
		return type;
	}
	public void setType(long type) {
		this.type = type;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public Boolean isHeuresProductiveValide() {
		return heuresProductiveValide;
	}
	public void setHeuresProductiveValide(Boolean heuresProductiveValide) {
		this.heuresProductiveValide = heuresProductiveValide;
	}
	public Boolean getHeuresProductiveValide() {
		return heuresProductiveValide;
	}
	public Boolean isTousLesCasinos() {
		return tousLesCasinos;
	}
	public Boolean getTousLesCasinos() {
		return tousLesCasinos;
	}	
	public void setTousLesCasinos(Boolean tousLesCasinos) {
		this.tousLesCasinos = tousLesCasinos;
	}
	
}
