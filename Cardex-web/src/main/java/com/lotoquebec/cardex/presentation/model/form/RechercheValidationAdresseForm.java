package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.model.RechercheListeResultat;
import com.lotoquebec.cardexCommun.model.RetourFocus;


public class RechercheValidationAdresseForm extends ValidatorForm implements RechercheListeResultat, RetourFocus, Serializable {

	private static final long serialVersionUID = -6801574272378255833L;
	private String nomChampRetourFocus = "";
	private String choixAdresse = "0";
	private AdresseForm adresseSaisie = null;
	private ListeResultat listeResultat = new ListeResultat(); // AdresseRechercheForm
	private int sequence = 0;
	
	
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

	public String getChoixAdresse() {
		return choixAdresse;
	}

	public void setChoixAdresse(String choixAdresse) {
		this.choixAdresse = choixAdresse;
	}

	public ListeResultat getListeResultat() {
		return listeResultat;
	}

	public void addListeResultat(AdresseRechercheForm adresseRechercheForm) {
		this.listeResultat.add(adresseRechercheForm);
	}
	
	public void setListeResultat(ListeResultat listeResultat) {
		this.listeResultat = listeResultat;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	
}