package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardexCommun.model.RetourFocus;


public class ValidationAdresseForm extends ValidatorForm implements RetourFocus, Serializable {

	public static final String ADRESSE_SAISIE = "adresseSaisie";
	public static final String ADRESSE_VALIDE = "adresseValide";
	
	private String nomChampRetourFocus = "";
	private String choixAdresse = ADRESSE_VALIDE;
	private AdresseForm adresseSaisie = null;
	private AdresseForm adresseValide = null;
	
	
	public String getNomChampRetourFocus() {
		return nomChampRetourFocus;
	}

	public void setNomChampRetourFocus(String nomChampRetourFocus) {
		this.nomChampRetourFocus = nomChampRetourFocus;
	}

	public AdresseForm getAdresseSaisie() {
		return adresseSaisie;
	}

	public void setAdresseSaisie(AdresseForm adresseSaisie) {
		this.adresseSaisie = adresseSaisie;
	}

	public AdresseForm getAdresseValide() {
		return adresseValide;
	}

	public void setAdresseValide(AdresseForm adresseValide) {
		this.adresseValide = adresseValide;
	}

	public String getChoixAdresse() {
		return choixAdresse;
	}

	public void setChoixAdresse(String choixAdresse) {
		this.choixAdresse = choixAdresse;
	}

	public AdresseForm obtenirAdresseChoisi() {
	
		if (ADRESSE_SAISIE.equals(choixAdresse))
			return adresseSaisie;
		
		if (ADRESSE_VALIDE.equals(choixAdresse)){
			adresseValide.copierExtentionDansAdresse(adresseSaisie);
			return adresseValide;
		}
		return null;
	}
	
}