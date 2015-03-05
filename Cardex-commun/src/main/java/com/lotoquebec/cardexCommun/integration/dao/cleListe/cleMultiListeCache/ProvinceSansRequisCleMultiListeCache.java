/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache;


/**
 * SVP, le moins utilisé possible, utiliser ProvinceCleMultiListeCache
 * @author levassc
 */
public class ProvinceSansRequisCleMultiListeCache extends CleMultiListeCache {
	
	public ProvinceSansRequisCleMultiListeCache(String langue) {
		super(langue, "");
		assignerValeurDefault();
	}
	
	private void assignerValeurDefault() {
		this.nomTable = "PR_PROVINCE";
		this.colonneCle = "L_PR_CLE";
		this.colonneDiscriminantCle = "";
		this.discriminantValeurRequis = false;
	}	
}
