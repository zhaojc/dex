/*
 * Created on 27-May-2008
 */
package com.lotoquebec.cardex.presentation.model.form.lienCascade;

import com.lotoquebec.cardexCommun.model.LienCascade;

/**
 * @author levassc
 */
public class HierarchieEGNTC extends LienCascade{

	public final static String ENTITE = "entite";
	public final static String GENRE = "genre";
	public final static String NATURE = "nature";
	public final static String TYPE = "type";
	public final static String CATEGORIE = "categorie";

	public HierarchieEGNTC(String entite, String genre, String nature, String type, String categorie) {
		assignerValeurCommun();
		set(ENTITE, entite);
		set(GENRE, genre);
		set(NATURE, nature);
		set(TYPE, type);
		set(CATEGORIE, categorie);
	}
	
	public HierarchieEGNTC() {
		assignerValeurCommun();
	}
	
	public void assignerValeurCommun() {
		ajout(ENTITE);
		ajout(GENRE);
		ajout(NATURE);
		ajout(TYPE);
		ajout(CATEGORIE);
	}

	public String getEntite() {
		return this.get(HierarchieEGNTC.ENTITE);
	}

	public void setEntite(String entite) {
		this.set(HierarchieEGNTC.ENTITE, entite);
	}
	
	public String getGenre() {
		return this.get(HierarchieEGNTC.GENRE);
	}

	public void setGenre(String genre) {
		this.set(HierarchieEGNTC.GENRE, genre);
	}

	public String getNature() {
		return this.get(HierarchieEGNTC.NATURE);
	}

	public void setNature(String nature) {
		this.set(HierarchieEGNTC.NATURE, nature);
	}

	public String getType() {
		return this.get(HierarchieEGNTC.TYPE);
	}

	public void setType(String type) {
		this.set(HierarchieEGNTC.TYPE, type);
	}
	
	public String getCategorie() {
		return this.get(HierarchieEGNTC.CATEGORIE);
	}

	public void setCategorie(String categorie) {
		this.set(HierarchieEGNTC.CATEGORIE, categorie);
	}
	
}
