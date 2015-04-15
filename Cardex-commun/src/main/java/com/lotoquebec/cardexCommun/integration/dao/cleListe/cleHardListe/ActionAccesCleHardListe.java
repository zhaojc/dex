/*
 * Created on 28-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe;


/**
 * @author levassc
 */
public class ActionAccesCleHardListe extends CleHardListe {

	/**
	 * @param langue
	 */
	public ActionAccesCleHardListe(String langue) {
		super(langue);
	}

	protected void genererListe(){
        add("S","S=Sélection");
        add("D","D=Suppression");
        add("I","I=Insertion");
        add("U","U=Mise à jour");
	}
}
