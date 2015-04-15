package com.lotoquebec.cardex.presentation.model.form.rapport.regroupement;

import java.io.Serializable;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;

/**
 * Conserve les diff�rentes valeurs relatives au formulaire pour la production
 * des rapports de regroupement.
 *
 * @see com.lotoquebec.cardex.presentation.model.CriteresRechercheJournalHtmlForm
 */
public abstract class RegroupementRapportForm extends CriteresRapportForm implements Serializable {

	private static final long serialVersionUID = -285640418993503203L;
	private String site = "";
    private HierarchieEGNTC cascadeEGNTC = new HierarchieEGNTC();
    private String intervenant = "";
    private String secteur = "";
    private String endroit = "";
    private String regroupement = "";
    
	public RegroupementRapportForm(GenererRapport genererRapport) {
		super(genererRapport);
	}

	/**
	 * Returns the categorie.
	 * @return String
	 */
	public String getCategorie() {
		return cascadeEGNTC.getCategorie();
	}


	/**
	 * Returns the entite.
	 * @return String
	 */
	public String getEntite() {
		return cascadeEGNTC.getEntite();
	}

	/**
	 * Returns the intervenant.
	 * @return String
	 */
	public String getIntervenant() {
		return intervenant;
	}

	/**
	 * Returns the site.
	 * @return String
	 */
	public String getSite() {
		return site;
	}

	/**
	 * Returns the type.
	 * @return String
	 */
	public String getType() {
		return cascadeEGNTC.getType();
	}

	/**
	 * Sets the categorie.
	 * @param categorie The categorie to set
	 */
	public void setCategorie(String categorie) {
		cascadeEGNTC.setCategorie(categorie);
	}


	/**
	 * Sets the entite.
	 * @param entite The entite to set
	 */
	public void setEntite(String entite) {
		cascadeEGNTC.setEntite(entite);
	}

	/**
	 * Sets the intervenant.
	 * @param intervenant The intervenant to set
	 */
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	/**
	 * Sets the site.
	 * @param site The site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * Sets the type.
	 * @param type The type to set
	 */
	public void setType(String type) {
		cascadeEGNTC.setType(type);
	}

	/**
	 * Returns the groupe de s�curit�.
	 * @return String
	 */
	public String getSecteur() {
		return secteur;
	}

	/**
	 * Sets the groupe.
	 * @param groupe The groupe to set
	 */
	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	/**
	 * Returns the genre.
	 * @return String
	 */
	public String getGenre() {
		return cascadeEGNTC.getGenre();
	}

	/**
	 * Returns the nature.
	 * @return String
	 */
	public String getNature() {
		return cascadeEGNTC.getNature();	
	}

	/**
	 * Sets the genre.
	 * @param genre The genre to set
	 */
	public void setGenre(String genre) {
		cascadeEGNTC.setGenre(genre);
	}

	/**
	 * Sets the nature.
	 * @param nature The nature to set
	 */
	public void setNature(String nature) {
		cascadeEGNTC.setNature(nature);
	}

	/**
	 * @return Returns the regroupement.
	 */
	public String getRegroupement() {
		return regroupement;
	}
	/**
	 * @param regroupement The regroupement to set.
	 */
	public void setRegroupement(String regroupement) {
		this.regroupement = regroupement;
	}
	
	public String getEndroit() {
		return endroit;
	}
	
	public void setEndroit(String endroit) {
		this.endroit = endroit;
	}
	
	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
    	CardexUser user = (CardexUser) subject.getUser();
    	
    	cascadeEGNTC = new HierarchieEGNTC();
	    intervenant = "";
	    endroit = "";
	    regroupement = "";
	    
        setEntite(String.valueOf(user.getEntite()));
        setSite(String.valueOf(user.getSite()));
        setSecteur( GlobalConstants.Secteur.TECHNICIEN_SURVEILLANCE );
	}


}