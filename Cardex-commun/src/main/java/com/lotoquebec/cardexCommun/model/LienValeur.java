/*
 * Created on 27-May-2008
 */
package com.lotoquebec.cardexCommun.model;

import java.io.Serializable;

import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
class LienValeur implements Serializable {

	private String valeur = "";
	private LienValeur precedent = null;
	private LienValeur suivant = null;
	

	public LienValeur(LienValeur precedent) {
		this.precedent = precedent;
		
		if (precedent != null)
			precedent.setSuivant( this );
	}
	
	public LienValeur getPrecedent() {
		return precedent;
	}
	public void setPrecedent(LienValeur precedent) {
		this.precedent = precedent;
	}
	public LienValeur getSuivant() {
		return suivant;
	}
	public void setSuivant(LienValeur suivant) {
		this.suivant = suivant;
	}
	
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		
		/* I10-1705 – Cardex - Dans Dossier, la catégorie disparait lorsque la loupe est utilisée
		if (StringUtils.isNotEmpty( this.valeur ) 
		&& this.valeur.equals(valeur) == false 
		&& getSuivant() != null)
			getSuivant().setValeur("");*/
		
		this.valeur = valeur;
		
		if ( StringUtils.isEmpty( this.valeur ) 
		&& getSuivant() != null)
			getSuivant().setValeur("");
	}

}
