/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;



/**
 * @author levassc
 */
public class HeureDebutSiteCleMultiListeCache extends CleMultiExterneListeCache{

	public HeureDebutSiteCleMultiListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), "");
		assignerValeurDefault();
	}
	
	public HeureDebutSiteCleMultiListeCache(String langue) {
		super(langue, "");
		assignerValeurDefault();
	}

	private void assignerValeurDefault() {
		this.nomTable = "SI_SITE";
		this.colonneCle = "L_SI_CLE";
		this.colonneDescription = "V_SI_HEURE_DEBUT";
		this.colonneDiscriminantCle = "";
		this.discriminantValeurRequis = false;
	}

}
