/*
 * Created on 18-Apr-2008
 */
package com.lotoquebec.cardex.business.vo;

import java.util.Date;

import com.lotoquebec.cardex.business.CriteresRechercheAdresses;


/**
 * @author levassc
 */
public class CriteresRechercheAdressesVO implements CriteresRechercheAdresses {

    private long entite = 0;
    private long siteOrigine = 0;
    private String numeroMunicipal = "";
    private long typeRue  = 0;
    private String nomRue = "";
    private long pointCardinal = 0;
    private long unite = 0;
    private String numeroUnite = "";
    private String adressePostal = "";
    private String telephone = "";
    private long pays = 0;
    private long province = 0;
    private long ville = 0;
    private String codePostal = "";
    private String adresseElectronique = "";
    private Date dateCreationDu = null;
    private Date dateCreationAu = null;
    private int sequence = 0;
    
	public String getAdressePostal() {
		return adressePostal;
	}
	public void setAdressePostal(String adressePostal) {
		this.adressePostal = adressePostal;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public long getEntite() {
		return entite;
	}
	public void setEntite(long entite) {
		this.entite = entite;
	}
	public String getNomRue() {
		return nomRue;
	}
	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}
	public String getNumeroMunicipal() {
		return numeroMunicipal;
	}
	public void setNumeroMunicipal(String numeroMunicipal) {
		this.numeroMunicipal = numeroMunicipal;
	}
	public String getNumeroUnite() {
		return numeroUnite;
	}
	public void setNumeroUnite(String numeroUnite) {
		this.numeroUnite = numeroUnite;
	}
	public long getPays() {
		return pays;
	}
	public void setPays(long pays) {
		this.pays = pays;
	}
	public long getPointCardinal() {
		return pointCardinal;
	}
	public void setPointCardinal(long pointCardinal) {
		this.pointCardinal = pointCardinal;
	}
	public long getProvince() {
		return province;
	}
	public void setProvince(long province) {
		this.province = province;
	}
	public long getSiteOrigine() {
		return siteOrigine;
	}
	public void setSiteOrigine(long siteOrigine) {
		this.siteOrigine = siteOrigine;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public long getTypeRue() {
		return typeRue;
	}
	public void setTypeRue(long typeRue) {
		this.typeRue = typeRue;
	}
	public long getUnite() {
		return unite;
	}
	public void setUnite(long unite) {
		this.unite = unite;
	}
	public long getVille() {
		return ville;
	}
	public void setVille(long ville) {
		this.ville = ville;
	}
	public String getAdresseElectronique() {
		return adresseElectronique;
	}
	public void setAdresseElectronique(String adresseElectronique) {
		this.adresseElectronique = adresseElectronique;
	}
	public Date getDateCreationDu() {
		return dateCreationDu;
	}
	public void setDateCreationDu(Date dateCreationDu) {
		this.dateCreationDu = dateCreationDu;
	}
	public Date getDateCreationAu() {
		return dateCreationAu;
	}
	public void setDateCreationAu(Date dateCreationAu) {
		this.dateCreationAu = dateCreationAu;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
}
