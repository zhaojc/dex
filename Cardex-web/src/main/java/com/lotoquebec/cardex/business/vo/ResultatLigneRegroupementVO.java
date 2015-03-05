/*
 * Created on 23-Jan-2008
 */
package com.lotoquebec.cardex.business.vo;

import com.lotoquebec.cardex.business.ResultatRegroupement;

/**
 * @author levassc
 */
public class ResultatLigneRegroupementVO implements ResultatRegroupement {

	private long cle = 0;
	private String nomRegroupement = "";
	private String groupe = "";
	private double quota = 0;
	private int quotaMin = 0;
	private long nombreMinutes = 0;
	private String endroit = "";
	private long cleIntervenant = 0;
	private String nomIntervenant = "";
	private String prenomIntervenant = "";
	private String matricule = "";
	private String categorie = "";
	private String type = "";
	private String moisLettres = "";
	private String moisNombre = "";
	
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public long getNombreMinutes() {
		return nombreMinutes;
	}
	public void setNombreMinutes(long nombreMinutes) {
		this.nombreMinutes = nombreMinutes;
	}
	public String getNomRegroupement() {
		return nomRegroupement;
	}
	public void setNomRegroupement(String nomRegroupement) {
		this.nomRegroupement = nomRegroupement;
	}
	public double getQuota() {
		return quota;
	}
	public void setQuota(double quota) {
		this.quota = quota;
	}
	public int getQuotaMin() {
		return quotaMin;
	}
	public void setQuotaMin(int quotaMin) {
		this.quotaMin = quotaMin;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEndroit() {
		return endroit;
	}
	public void setEndroit(String endroit) {
		this.endroit = endroit;
	}
	public String getNomIntervenant() {
		return nomIntervenant;
	}
	public void setNomIntervenant(String nomIntervenant) {
		this.nomIntervenant = nomIntervenant;
	}
	public String getPrenomIntervenant() {
		return prenomIntervenant;
	}
	public void setPrenomIntervenant(String prenomIntervenant) {
		this.prenomIntervenant = prenomIntervenant;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public long getCle() {
		return cle;
	}
	public void setCle(long cle) {
		this.cle = cle;
	}
	public String getGroupe() {
		return groupe;
	}
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
	public long getCleIntervenant() {
		return cleIntervenant;
	}
	public void setCleIntervenant(long cleIntervenant) {
		this.cleIntervenant = cleIntervenant;
	}
	
	public Object clone() throws CloneNotSupportedException{
		super.clone();
		ResultatRegroupement regroupement = new ResultatLigneRegroupementVO();
		
		regroupement.setCle( getCle() );
		regroupement.setNomRegroupement( getNomRegroupement() );
		regroupement.setGroupe( getGroupe() );
		regroupement.setQuota( getQuota() );
		regroupement.setQuotaMin( getQuotaMin() );
		regroupement.setNombreMinutes( getNombreMinutes() );
		regroupement.setEndroit( getEndroit() );
		regroupement.setCleIntervenant( getCleIntervenant() );
		regroupement.setNomIntervenant( getNomIntervenant() );
		regroupement.setPrenomIntervenant( getPrenomIntervenant() );
		regroupement.setMatricule( getMatricule() );
		regroupement.setCategorie( getCategorie() );
		regroupement.setType( getType() );		
		
		return regroupement;
	}
	/**
	 * @return mois_lettres
	 */
	public String getMoisLettres() {
		return moisLettres;
	}
	/**
	 * @param annee_mois_lettres annee_mois_lettres à définir
	 */
	public void setMoisLettres(String moisLettres) {
		this.moisLettres = moisLettres;
	}
	/**
	 * @return annee_mois_nombre
	 */
	public String getMoisNombre() {
		return moisNombre;
	}
	/**
	 * @param annee_mois_nombre annee_mois_nombre à définir
	 */
	public void setMoisNombre(String moisNombre) {
		this.moisNombre = moisNombre;
	}	
}
