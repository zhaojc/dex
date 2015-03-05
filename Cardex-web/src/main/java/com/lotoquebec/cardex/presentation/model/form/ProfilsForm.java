package com.lotoquebec.cardex.presentation.model.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.validator.ValidatorForm;

public class ProfilsForm extends ValidatorForm {

	private String choixProfil = "";
	private List<IntervenantForm> listeProfils = new ArrayList<IntervenantForm>();
	
	
	public String getChoixProfil() {
		return choixProfil;
	}
	
	public void setChoixProfil(String choixProfil) {
		this.choixProfil = choixProfil;
	}
	
	public List<IntervenantForm> getListeProfils() {
		return listeProfils;
	}
	
	public void setListeProfils(List<IntervenantForm> listeProfils) {
		this.listeProfils = listeProfils;
	}
	
}