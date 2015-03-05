/*
 * Created on 22-Sep-2008
 */
package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.Site;
import com.lotoquebec.cardexCommun.text.DateFormat;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc

 */
public class SiteVO implements Site{

	private long cle = 0;
	private int entite = 0;
	private String abreviation = "";
	private String nom = "";
	private String createur = "";
	private Timestamp dateCreation = null;
	private String modifierPar = "";
	private Timestamp dateModification = null;
	private long numeroSequence = 0;
	private Timestamp dateSequence = null;
	private Boolean siteOrigine = false;
	private Boolean siteApplicable = false;
	
	public String getAbreviation() {
		return abreviation;
	}
	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}
	public long getCle() {
		return cle;
	}
	public void setCle(long cle) {
		this.cle = cle;
	}
	public String getCreateur() {
		return createur;
	}
	public void setCreateur(String createur) {
		this.createur = createur;
	}
	public Timestamp getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Timestamp getDateModification() {
		return dateModification;
	}
	public void setDateModification(Timestamp dateModification) {
		this.dateModification = dateModification;
	}
	public Timestamp getDateSequence() {
		return dateSequence;
	}
	public void setDateSequence(Timestamp dateSequence) {
		this.dateSequence = dateSequence;
	}
	public int getEntite() {
		return entite;
	}
	public void setEntite(int entite) {
		this.entite = entite;
	}
	public String getModifierPar() {
		return modifierPar;
	}
	public void setModifierPar(String modifierPar) {
		this.modifierPar = modifierPar;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public long getNumeroSequence() {
		return numeroSequence;
	}
	public void setNumeroSequence(long numeroSequence) {
		this.numeroSequence = numeroSequence;
	}
	public Boolean isSiteApplicable() {
		return siteApplicable;
	}
	public void setSiteApplicable(Boolean siteApplicable) {
		this.siteApplicable = siteApplicable;
	}
	public Boolean isSiteOrigine() {
		return siteOrigine;
	}
	public void setSiteOrigine(Boolean siteOrigine) {
		this.siteOrigine = siteOrigine;
	}
	public void incrementerSequence(){
		numeroSequence++;
	}
	
	public String constuireNumeroCardex(){
		String strDate = DateFormat.format( dateSequence, DateFormat.DATE_FORMAT_SANS_TIRET );
		String sequence = StringUtils.right("0000"+numeroSequence, 4);
		return abreviation+strDate+sequence;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.business.Site#initSequence(java.sql.Timestamp)
	 */
	public void initSequence(Timestamp date) {
		setDateSequence( date );
		numeroSequence = 0;
	}
	public Boolean getSiteOrigine() {
		return siteOrigine;
	}
	public Boolean getSiteApplicable() {
		return siteApplicable;
	}
	
}
