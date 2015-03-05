/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class VilleCleMultiExterneListeCache extends CleMultiExterneListeCache {

	public VilleCleMultiExterneListeCache(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
		assignerValeurDefault();
	}

	private void assignerValeurDefault() {
		this.nomTable = "VI_VILLE";
		this.colonneCle = "L_VI_CLE";
		this.colonneDescription = "V_VI_VILLE";
		this.colonneDiscriminantCle = "L_PR_CLE";
		this.discriminantValeurRequis = true;
	}

	public VilleCleMultiExterneListeCache(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
		assignerValeurDefault();
	}

	public VilleCleMultiExterneListeCache(CardexAuthenticationSubject subject, long colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), String.valueOf(colonneDiscriminantValeur));
		assignerValeurDefault();
	}

}
