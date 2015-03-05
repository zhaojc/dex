/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.CleMultiExterneListeCache;

/**
 * @author levassc
 */
public class EntiteCleMultiListeCache extends CleMultiExterneListeCache{

	public EntiteCleMultiListeCache(String langue) {
		super(langue, "");
		assignerValeurDefault();
	}

	private void assignerValeurDefault() {
		this.nomTable = "EN_ENTITE";
		this.colonneCle = "I_EN_CLE";
		this.colonneDescription = "V_EN_COMMENTAIRE";
	}

	public EntiteCleMultiListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), "");
		assignerValeurDefault();
	}	


}
