/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;



/**
 * @author levassc
 */
public class ModeleCleMultiListeCache extends CleMultiExterneListeCache{

	public ModeleCleMultiListeCache(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
		assignerValeurDefault();
	}
	
	public ModeleCleMultiListeCache(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
		assignerValeurDefault();
	}

	private void assignerValeurDefault() {
		this.nomTable = "MD_MODELE";
		this.colonneCle = "I_MD_CLE";
		this.colonneDescription = "V_MD_MODELE";
		this.colonneDiscriminantCle = "I_MA_CLE";
		this.discriminantValeurRequis = true;
	}

}
