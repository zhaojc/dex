package com.lotoquebec.cardex.presentation.model.form.rapport.journal;

import com.lotoquebec.cardex.generateurRapport.GenererRapport;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;

public abstract class JournalRapportForm extends CriteresRapportForm{

	private HierarchieES hierarchieES = new HierarchieES();
	private HierarchieEGNTC hierarchieEGNTC = new HierarchieEGNTC();
	private String intervenant = "";
    private String secteur = "";
    private String descriptif = "";
    private String fonde = "";
    private String origine = "";
    private String endroit = "";
    private String localisation = "";
    private String numeroDossier = "";
    private String reference = "";

	public JournalRapportForm(GenererRapport genererRapport) {
		super(genererRapport);
	}
    
	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par d�faut
        setEntite(String.valueOf(user.getEntite()));
        setSite(String.valueOf(user.getSite()));
	}
	
	public String getEntite() {
		return hierarchieES.getEntite();
	}

	public void setEntite(String entite) {
		this.hierarchieES.setEntite(entite);
		this.hierarchieEGNTC.setEntite(entite);
	}

	public String getSite() {
		return hierarchieES.getSiteOrigine();
	}

	public void setSite(String site) {
		this.hierarchieES.setSiteOrigine(site);
	}
	
    public String getGenre() {
    	return hierarchieEGNTC.getGenre();
    }

    public void setGenre(String genre) {
    	hierarchieEGNTC.setGenre(genre);
    }
        
	public String getNature() {
		return hierarchieEGNTC.getNature();
	}

	public void setNature(String nature) {
		hierarchieEGNTC.setNature(nature);
	}

	public String getType() {
		return hierarchieEGNTC.getType();
	}

	public void setType(String type) {
		hierarchieEGNTC.setType(type);
	}
	
	public String getCategorie() {
		return hierarchieEGNTC.getCategorie();
	}

	public void setCategorie(String categorie) {
		hierarchieEGNTC.setCategorie(categorie);
	}
	
	public String getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public String getFonde() {
		return fonde;
	}

	public void setFonde(String fonde) {
		this.fonde = fonde;
	}

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public String getEndroit() {
		return endroit;
	}

	public void setEndroit(String endroit) {
		this.endroit = endroit;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
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
