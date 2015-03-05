/*
 * Created on 27-Nov-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class ServiceSiteCleMultiListeCache extends CleMultiListeCache {

	public ServiceSiteCleMultiListeCache(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
		assignerValeurDefault();
	}

	private void assignerValeurDefault() {
		this.nomTable = "SR_SERVICE";
		this.colonneCle = "L_SR_CLE";
		this.colonneDiscriminantCle = "L_SI_CLE";
		this.discriminantValeurRequis = true;
	}

	public ServiceSiteCleMultiListeCache(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
		assignerValeurDefault();
	}
	
}
