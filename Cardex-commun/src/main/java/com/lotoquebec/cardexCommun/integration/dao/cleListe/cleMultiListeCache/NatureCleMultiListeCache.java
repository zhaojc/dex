/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;


/**
 * @author levassc
 */
public class NatureCleMultiListeCache extends CleMultiListeCache {

	public NatureCleMultiListeCache(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
		assignerValeurDefault();
	}

	private void assignerValeurDefault() {
		this.nomTable = "NA_NATURE";
		this.colonneCle = "I_NA_CLE";
		this.colonneDiscriminantCle = "I_GE_CLE";
		this.discriminantValeurRequis = true;
	}

	public NatureCleMultiListeCache(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
		assignerValeurDefault();
	}	
}
