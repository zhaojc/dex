/*
 * Created on 28-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe;


/**
 * @author levassc
 */
public class ApplicationCleHardListe extends CleHardListe {

	/**
	 * @param langue
	 */
	public ApplicationCleHardListe(String langue) {
		super(langue);
	}

	protected void genererListe(){
        add("Cardex", "Cardex");
        add("Pilotage", "Pilotage");
	}
}
