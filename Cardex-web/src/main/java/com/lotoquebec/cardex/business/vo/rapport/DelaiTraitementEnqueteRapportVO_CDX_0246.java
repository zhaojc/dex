package com.lotoquebec.cardex.business.vo.rapport;

import java.sql.Timestamp;


/**
 * @author guerinf
 */
public class DelaiTraitementEnqueteRapportVO_CDX_0246 extends CritereRapportVO{

	protected String site = "";
	protected String nature = "";
	protected String dossier = "";
	protected Timestamp dateCompletee = null;
	protected Timestamp dateCreation = null;
	protected double ecart = 0;
	protected long activite = 0;
	
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getDossier() {
		return dossier;
	}
	public void setDossier(String dossier) {
		this.dossier = dossier;
	}
	public Timestamp getDateCompletee() {
		return dateCompletee;
	}
	public void setDateCompletee(Timestamp dateCompletee) {
		this.dateCompletee = dateCompletee;
	}
	public Timestamp getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}
	public double getEcart() {
		return ecart;
	}
	public void setEcart(double ecart) {
		this.ecart = ecart;
	}
	public long getActivite() {
		return activite;
	}
	public void setActivite(long activite) {
		this.activite = activite;
	}
	
}
