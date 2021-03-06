/*
 * Created on 27-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class TypeActionCleListeCache extends CleListeCache {

	/**
	 * @param langue
	 * @param cleListe
	 */
	public TypeActionCleListeCache(String langue) {
		super(langue, GlobalConstants.ListeCache.TYPE_ACTION);
	}

	public TypeActionCleListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), GlobalConstants.ListeCache.TYPE_ACTION);
	}
}
