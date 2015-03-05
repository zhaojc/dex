/*
 * Created on 10-Apr-2008
 */
package com.lotoquebec.cardex.business.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lotoquebec.cardex.business.RapportDossier;

/**
 * @author levassc
 */
public class RapportDossierVO implements RapportDossier {

	private List listDossier = new ArrayList(); // ResultatRegroupementVO
	private String type		 = "";
	private String categorie = "";
	private long   cleCategorie = 0;
	private long   nombreDossier = 0;
	private long   nombreFonde	 = 0;
	private long   nombreNonFonde = 0;
	private long   nombreIndetermine = 0;
	private long   nombreDossierCumul = 0;
	private long   nombreAuxEnquetes = 0; //Sert à calculer le nombre de dossiers de jeu en ligne avec "ENQ" dans le champ Référence_3 de DO_DOSSIER

	public List getListDossier() {
		return listDossier;
	}
	public void setListDossier(List listDossier) {
		this.listDossier = listDossier;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public long getNombreDossier() {
		return nombreDossier;
	}
	public void setNombreDossier(long nombreDossier) {
		this.nombreDossier = nombreDossier;
	}
	public long getNombreFonde() {
		return nombreFonde;
	}
	public void setNombreFonde(long nombreFonde) {
		this.nombreFonde = nombreFonde;
	}
	public long getNombreNonFonde() {
		return nombreNonFonde;
	}
	public void setNombreNonFonde(long nombreNonFonde) {
		this.nombreNonFonde = nombreNonFonde;
	}
	public long getNombreIndetermine() {
		return nombreIndetermine;
	}
	public void setNombreIndetermine(long nombreIndetermine) {
		this.nombreIndetermine = nombreIndetermine;
	}
	public long getNombreAuxEnquetes() {
		return nombreAuxEnquetes;
	}
	public void setNombreAuxEnquetes(long nombreAuxEnquetes) {
		this.nombreAuxEnquetes = nombreAuxEnquetes;
	}
	public long getCleCategorie() {
		return cleCategorie;
	}
	public void setCleCategorie(long cleCategorie) {
		this.cleCategorie = cleCategorie;
	}
	public long getNombreDossierCumul() {
		return nombreDossierCumul;
	}
	public void setNombreDossierCumul(long nombreDossierCumul) {
		this.nombreDossierCumul = nombreDossierCumul;
	}
	
}
