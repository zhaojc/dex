/*
 * Created on 27-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class DeviseCleListeCache extends CleListeCache {

	/**
	 * @param langue
	 * @param cleListe
	 */
	public DeviseCleListeCache(String langue) {
		super(langue, GlobalConstants.ListeCache.DEVISE);
	}

	public DeviseCleListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), GlobalConstants.ListeCache.DEVISE);
	}
}
