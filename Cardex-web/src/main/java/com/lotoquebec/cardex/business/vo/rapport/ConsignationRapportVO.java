package com.lotoquebec.cardex.business.vo.rapport;


/**
 * Utiliser par 
 * CDX_0150	Rapport sommaire sur les consignations
 * CDX_0151	Rapport détaillé sur les consignations
 * CDX_0152	Rapport par dénomination
 * CDX_0153	Rapport par numéro de série et dénomination
 * CDX_0154	Rapport par dénomination et numéro de série
 * 
 * @author levassc
 *
 */
public class ConsignationRapportVO extends RapportVO{

	protected long site = 0;
	protected String intervenant = "";
	protected long nature = 0;
	protected long type = 0;
	protected long categorie = 0;
	protected long typeConsignation = 0;
	
	
	public long getSite() {
		return site;
	}
	
	public void setSite(long site) {
		this.site = site;
	}
	
	public String getIntervenant() {
		return intervenant;
	}
	
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}
	
	public long getNature() {
		return nature;
	}
	
	public void setNature(long nature) {
		this.nature = nature;
	}
	
	public long getType() {
		return type;
	}
	
	public void setType(long type) {
		this.type = type;
	}
	
	public long getCategorie() {
		return categorie;
	}
	
	public void setCategorie(long categorie) {
		this.categorie = categorie;
	}
	
	public long getTypeConsignation() {
		return typeConsignation;
	}
	
	public void setTypeConsignation(long typeConsignation) {
		this.typeConsignation = typeConsignation;
	}

	
}
