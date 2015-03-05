/*
 * Created on 27-Nov-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class EndroitServiceCleMultiListeCache extends CleMultiListeCache {

	public EndroitServiceCleMultiListeCache(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
		assignerValeurDefault();
	}

	private void assignerValeurDefault() {
		this.nomTable = "OR_ORIENTATION";
		this.colonneCle = "I_OR_CLE";
		this.colonneDiscriminantCle = "L_SR_CLE";
		this.discriminantValeurRequis = true;
	}

	public EndroitServiceCleMultiListeCache(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
		assignerValeurDefault();
	}
	
}
