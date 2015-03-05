/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache;


/**
 * @author levassc
 */
public class RegroupementCleMultiListeCache extends CleMultiListeCache {

	/**
	 * @param cleListe
	 * @param langue
	 * @param colonneDiscriminantValeur
	 */
	public RegroupementCleMultiListeCache(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
		this.nomTable = "RG_REGROUPEMENT";
		this.colonneCle = "I_RG_CLE";
		this.colonneDiscriminantCle = "L_IN_SECTEUR";
		this.discriminantValeurRequis = true;
	}


}
