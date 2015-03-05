/*
 * Created on 28-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe;


/**
 * @author levassc
 */
public class MaximumCleHardListe extends CleHardListe {

	/**
	 * @param langue
	 */
	public MaximumCleHardListe(String langue) {
		super(langue);
	}
	
	protected void genererListe(){
        add("10", "10");
        add("25", "25");
        add("50", "50");
        add("100", "100");
        add("200", "200");
        add("300", "300");
        add("500", "500");
        add("1000", "1000");
        add("2000", "2000");		
	}
	
}
