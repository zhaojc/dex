/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;


/**
 * @author levassc
 */
public class CategorieCleMultiListeCache extends CleMultiListeCache {


	public CategorieCleMultiListeCache(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
		assignerValeurDefault();
	}

	private void assignerValeurDefault() {
		this.nomTable = "CA_CATEGORIE";
		this.colonneCle = "I_CA_CLE";
		this.colonneDiscriminantCle = "I_TY_CLE";
		this.discriminantValeurRequis = true;
	}

	public CategorieCleMultiListeCache(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
		assignerValeurDefault();
	}

	public CategorieCleMultiListeCache(CardexAuthenticationSubject subject, long colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
		assignerValeurDefault();
	}
	
}
