/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class TypeUniteCleListeCache extends CleListeCache {

	public TypeUniteCleListeCache(String langue) {
		super(langue, GlobalConstants.ListeCache.TYPE_UNITE);
	}

	public TypeUniteCleListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), GlobalConstants.ListeCache.TYPE_UNITE);
	}

}
