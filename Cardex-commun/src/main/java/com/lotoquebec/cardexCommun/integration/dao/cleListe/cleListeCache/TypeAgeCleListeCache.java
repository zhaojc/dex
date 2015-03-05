
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class TypeAgeCleListeCache extends CleListeCache {

	public TypeAgeCleListeCache(String langue) {
		super(langue, GlobalConstants.ListeCache.TYPE_AGE);
	}

	public TypeAgeCleListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), GlobalConstants.ListeCache.TYPE_AGE);
	}

}
