/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache;




/**
 * @author levassc
 */
public class NatureInscriptionCle extends CleMultiExterneListeCache{

	public NatureInscriptionCle() {
		super("", "");
		constructeur();
	}

	private void constructeur() {
		this.nomTable = "NA_NATURE";
		this.colonneCle = "I_NA_CLE";
		this.colonneDescription = "B_NA_INSCRIPTION";
		this.colonneDiscriminantCle = "";
		this.discriminantValeurRequis = false;
	}

}
