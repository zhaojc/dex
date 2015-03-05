/*
 * Created on 27-May-2008
 */
package com.lotoquebec.cardex.presentation.model.form.lienCascade;

import com.lotoquebec.cardexCommun.model.LienCascade;

/**
 * @author levassc
 */
public class HierarchieEGN extends LienCascade{

	public final static String ENTITE = "entite";
	public final static String GENRE = "genre";
	public final static String NATURE = "nature";

	public HierarchieEGN(String entite, String genre, String nature) {
		assignerValeurCommun();
		set(ENTITE, entite);
		set(GENRE, genre);
		set(NATURE, nature);
	}
	
	public HierarchieEGN() {
		assignerValeurCommun();
	}
	
	public void assignerValeurCommun() {
		ajout(ENTITE);
		ajout(GENRE);
		ajout(NATURE);
	}
	
}
