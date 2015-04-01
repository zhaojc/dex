/*
 * Created on 28-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe;


/**
 * @author levassc
 */
public class LangueCleHardListe extends CleHardListe {

	/**
	 * @param langue
	 */
	public LangueCleHardListe(String langue) {
		super(langue);
	}
	
	public LangueCleHardListe() {
		super(null);
	}

	@Override
	protected void genererListe() {
        add("1", "Français");
        add("15472", "Anglais");
	}

}
