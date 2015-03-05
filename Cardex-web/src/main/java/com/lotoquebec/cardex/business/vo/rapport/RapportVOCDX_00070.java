package com.lotoquebec.cardex.business.vo.rapport;

import java.sql.Timestamp;

public class RapportVOCDX_00070 {

	private String entite;
	private String site;
	private Timestamp debutDate;
	private String numeroCardex;
	private String texteNarration;
	private String intervenant;
	private String groupe;
	private String nature;
	
	// get / set
	public String getEntite() {
		return entite;
	}
	
	public void setEntite(String entite) {
		this.entite = entite;
	}
	
	public String getSite() {
		return site;
	}
	
	public void setSite(String site) {
		this.site = site;
	}
	
	public Timestamp getDebutDate() {
		return debutDate;
	}
	
	public void setDebutDate(Timestamp debutDate) {
		this.debutDate = debutDate;
	}
	
	public String getNumeroCardex() {
		return numeroCardex;
	}
	
	public void setNumeroCardex(String numeroCardex) {
		this.numeroCardex = numeroCardex;
	}
	
	public String getTexteNarration() {
		return texteNarration;
	}
	
	public void setTexteNarration(String texteNarration) {
		this.texteNarration = texteNarration;
	}
	
	public String getIntervenant() {
		return intervenant;
	}
	
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}
	public String getGroupe() {
		return groupe;
	}
	
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
	
	public String getNature() {
		return nature;
	}
	
	public void setNature(String nature) {
		this.nature = nature;
	}
	
}
