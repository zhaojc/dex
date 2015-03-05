/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class TypeRueCleListeCache extends CleListeCache {

	public TypeRueCleListeCache(String langue) {
		super(langue, GlobalConstants.ListeCache.TYPE_RUE);
	}

	public TypeRueCleListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), GlobalConstants.ListeCache.TYPE_RUE);
	}
}
