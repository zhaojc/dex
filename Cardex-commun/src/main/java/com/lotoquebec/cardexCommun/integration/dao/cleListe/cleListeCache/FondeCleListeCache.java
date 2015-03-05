/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class FondeCleListeCache extends CleListeCache {

	public FondeCleListeCache(String langue) {
		super(langue, GlobalConstants.ListeCache.FONDE);
	}

	public FondeCleListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), GlobalConstants.ListeCache.FONDE);
	}
}
