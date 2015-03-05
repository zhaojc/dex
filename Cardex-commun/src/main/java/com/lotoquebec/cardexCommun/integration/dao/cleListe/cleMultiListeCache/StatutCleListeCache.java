/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class StatutCleListeCache extends CleMultiListeCache{

	public StatutCleListeCache(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
		assignerValeurDefault();
	}
	
	public StatutCleListeCache(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
		assignerValeurDefault();
	}
	
	private void assignerValeurDefault() {
		this.nomTable = "ST_STATUT";
		this.colonneCle = "I_ST_CLE";
		this.colonneDiscriminantCle = "C_ST_GENRE_FICHIER";
		this.discriminantValeurRequis = true;
	}	

}
