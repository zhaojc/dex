/*
 * Created on 27-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class DenominationCleListeCache extends CleListeCache {

	/**
	 * @param langue
	 * @param cleListe
	 */
	public DenominationCleListeCache(String langue) {
		super(langue, GlobalConstants.ListeCache.DENOMINATION);
	}

	public DenominationCleListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), GlobalConstants.ListeCache.DENOMINATION);
	}
}
