/*
 * Created on 27-Nov-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class LocalisationSiteCleMultiListeCache extends CleMultiListeCache {

	public LocalisationSiteCleMultiListeCache(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
		assignerValeurDefault();
	}

	private void assignerValeurDefault() {
		this.nomTable = "CR_CARDINALITE";
		this.colonneCle = "I_CR_CLE";
		this.colonneDiscriminantCle = "L_SI_CLE";
		this.discriminantValeurRequis = true;
	}

	public LocalisationSiteCleMultiListeCache(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
		assignerValeurDefault();
	}
	
}
