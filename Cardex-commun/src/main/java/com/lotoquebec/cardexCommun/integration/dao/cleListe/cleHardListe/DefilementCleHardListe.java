/*
 * Created on 28-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe;


/**
 * @author levassc
 */
public class DefilementCleHardListe extends CleHardListe {

	/**
	 * @param langue
	 */
	public DefilementCleHardListe(String langue) {
		super(langue);
	}
	
	protected void genererListe(){
		
		for (int i=5;i<60;i++){
			add(String.valueOf(i), String.valueOf(i));
		}
	}
	
}
