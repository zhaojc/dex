/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class ProvinceCleMultiListeCache extends CleMultiListeCache {

	public ProvinceCleMultiListeCache(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
		assignerValeurDefault();
	}
	
	public ProvinceCleMultiListeCache(CardexAuthenticationSubject subject, long colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), String.valueOf(colonneDiscriminantValeur));
		assignerValeurDefault();
	}
	
	public ProvinceCleMultiListeCache(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
		assignerValeurDefault();
	}
	
	private void assignerValeurDefault() {
		this.nomTable = "PR_PROVINCE";
		this.colonneCle = "L_PR_CLE";
		this.colonneDiscriminantCle = "I_PA_CLE";
		this.discriminantValeurRequis = true;
	}	
}
