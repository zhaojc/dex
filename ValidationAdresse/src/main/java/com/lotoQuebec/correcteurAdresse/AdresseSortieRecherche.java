/*
 * Created on 26-Sep-2008
 */
package com.lotoQuebec.correcteurAdresse;

/**
 * @author levassc
 */
public class AdresseSortieRecherche extends AdresseSortie implements AdresseEntree{

	private long numeroMunicipalMin = 0;
	private long numeroMunicipalMax = 0;

	private String prefixNumeroUnite = "";
	private String sufixNumeroUnite = "";
	
	private long numeroUniteMin = 0;
	private long numeroUniteMax = 0;

	public long getNumeroMunicipalMin() {
		return numeroMunicipalMin;
	}
	public void setNumeroMunicipalMin(long numeroMunicipalMin) {
		this.numeroMunicipalMin = numeroMunicipalMin;
	}
	public long getNumeroMunicipalMax() {
		return numeroMunicipalMax;
	}
	public void setNumeroMunicipalMax(long numeroMunicipalMax) {
		this.numeroMunicipalMax = numeroMunicipalMax;
	}
	public long getNumeroUniteMin() {
		return numeroUniteMin;
	}
	public void setNumeroUniteMin(long numeroUniteMin) {
		this.numeroUniteMin = numeroUniteMin;
	}
	public long getNumeroUniteMax() {
		return numeroUniteMax;
	}
	public void setNumeroUniteMax(long numeroUniteMax) {
		this.numeroUniteMax = numeroUniteMax;
	}
	public String getPrefixNumeroUnite() {
		return prefixNumeroUnite;
	}
	public void setPrefixNumeroUnite(String prefixNumeroUnite) {
		this.prefixNumeroUnite = prefixNumeroUnite;
	}
	public String getSufixNumeroUnite() {
		return sufixNumeroUnite;
	}
	public void setSufixNumeroUnite(String sufixNumeroUnite) {
		this.sufixNumeroUnite = sufixNumeroUnite;
	}
	public String getCodePostalAE() {
		return getCodePostal();
	}
	public String getLigneAdresseAE() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(numeroMunicipalMin);
		stringBuffer.append(" ");
		stringBuffer.append(getNomRue());
		return stringBuffer.toString();
	}
	public String getPaysAE() {
		return getPays();
	}
	public String getProvinceAE() {
		return getProvince();
	}
	public String getVilleAE() {
		return getVille();
	}
	
}
