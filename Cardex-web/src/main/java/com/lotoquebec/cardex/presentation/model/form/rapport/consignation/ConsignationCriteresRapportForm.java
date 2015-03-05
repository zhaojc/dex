package com.lotoquebec.cardex.presentation.model.form.rapport.consignation;

import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieES;
import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.user.CardexUser;

public abstract class ConsignationCriteresRapportForm extends CriteresRapportForm{

	private HierarchieEGNTC cascadeEGNTC = new HierarchieEGNTC();
	private HierarchieES cascadeES = new HierarchieES();
	
	private String typeConsignation = "";
	private String typeCategorie = "";
	private String intervenant = "";
	
	public void init(CardexAuthenticationSubject subject){
		super.init(subject);
        CardexUser user = (CardexUser) subject.getUser();
        // Valeurs par défaut
        setEntite(String.valueOf(user.getEntite()));
        setSite(String.valueOf(user.getSite()));
	}
	
	public String getEntite() {
		return cascadeEGNTC.getEntite();
	}

	public void setEntite(String entite) {
		cascadeEGNTC.setEntite(entite);
		cascadeES.setEntite(entite);
	}
	
	public String getGenre() {
		return cascadeEGNTC.getGenre();
	}

	public void setGenre(String genre) {
		cascadeEGNTC.setGenre(genre);
	}

	public String getNature() {
		return cascadeEGNTC.getNature();
	}

	public void setNature(String nature) {
		cascadeEGNTC.setNature(nature);
	}

	public String getType() {
		return cascadeEGNTC.getType();
	}

	public void setType(String type) {
		cascadeEGNTC.setType(type);
	}
	
	public String getCategorie() {
		return cascadeEGNTC.getCategorie();
	}

	public void setCategorie(String categorie) {
		cascadeEGNTC.setCategorie(categorie);
	}

	public String getSite() {
		return cascadeES.getSiteOrigine();
	}
	public void setSite(String siteOrigine) {
		cascadeES.setSiteOrigine(siteOrigine);
	}
	
	public String getTypeConsignation() {
		return typeConsignation;
	}

	public void setTypeConsignation(String typeConsignation) {
		this.typeConsignation = typeConsignation;
	}

	public String getIntervenant() {
		return intervenant;
	}

	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	public String getTypeCategorie() {
		return typeCategorie;
	}

	public void setTypeCategorie(String typeCategorie) {
		this.typeCategorie = typeCategorie;
	}
	
	
}
