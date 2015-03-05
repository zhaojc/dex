/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache;




/**
 * @author levassc
 */
public class GenreConfidentialiteCle extends CleMultiExterneListeCache {


	public GenreConfidentialiteCle() {
		super("", "");
		assignerValeurDefault();
	}

	private void assignerValeurDefault() {
		this.nomTable = "GE_GENRE";
		this.colonneCle = "I_GE_CLE";
		this.colonneDescription = "I_CC_CLE_DEFAUT";
		this.discriminantValeurRequis = false;
		this.colonneDiscriminantCle = "";
	}

}
