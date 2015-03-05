/*
 * Created on 4-Sep-2008
 */
package com.lotoquebec.cardexCommun.model;

/**
 * @author levassc
 */
public interface RetourFocus {

	/*
	 * Cette méthode retourne le nom du champ qu'il faut faire un focus
	 */
	public String getNomChampRetourFocus();

	/*
	 * Cette méthode assigne le nom du champ qu'il faut faire un focus
	 */	
	public void setNomChampRetourFocus(String nomChampRetourFocus);
	
}
