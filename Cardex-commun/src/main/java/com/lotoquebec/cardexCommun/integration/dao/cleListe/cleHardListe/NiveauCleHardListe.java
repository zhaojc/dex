/*
 * Created on 28-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe;


/**
 * @author levassc
 */
public class NiveauCleHardListe extends CleHardListe {

	/**
	 * @param langue
	 */
	public NiveauCleHardListe(String langue) {
		super(langue);
	}

	@Override
	protected void genererListe() {
        add("1", "Pop-Up + Message en rouge");
        add("2", "Message en rouge");
        add("3", "Message en bleu");
	}

}
