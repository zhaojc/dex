package com.lotoquebec.cardexCommun.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

public class ListeCacheString {

	private List<String> listeString = new ArrayList<String>();
	private String caractereDepart = "";
	

	public ListeCacheString(String caractereDepart, List<String> listeString) {
		super();
		this.caractereDepart = caractereDepart;
		this.listeString = listeString;
	}
	
	public List<String> obtenirListe(String critereStartsWith){
		Predicate predicate = new StringStartsWithPredicate(critereStartsWith);
		return (List<String>) CollectionUtils.select(listeString, predicate);
	}

	public boolean isCaratereStartsWith(String critereStartsWith){
		return critereStartsWith.startsWith(caractereDepart);
	}
	
}
