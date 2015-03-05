/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;



/**
 * @author levassc
 */
public class MarqueCleMultiListeCache extends CleMultiExterneListeCache{

	public MarqueCleMultiListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), "");
		assignerValeurDefault();
	}
	
	public MarqueCleMultiListeCache(String langue) {
		super(langue, "");
		assignerValeurDefault();
	}

	private void assignerValeurDefault() {
		this.nomTable = "MA_MARQUE";
		this.colonneCle = "I_MA_CLE";
		this.colonneDescription = "V_MA_MARQUE";
		this.colonneDiscriminantCle = "";
		this.discriminantValeurRequis = false;
	}

}
