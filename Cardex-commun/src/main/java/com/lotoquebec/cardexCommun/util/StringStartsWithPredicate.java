package com.lotoquebec.cardexCommun.util;

import org.apache.commons.collections.Predicate;


public class StringStartsWithPredicate implements Predicate {

	private String valeur = "";
	
	public StringStartsWithPredicate(String valeur) {
		super();
		this.valeur = valeur;
	}

	public boolean evaluate(Object string) {
		return ((String)string).toUpperCase().startsWith(valeur.toUpperCase());
	}

}
