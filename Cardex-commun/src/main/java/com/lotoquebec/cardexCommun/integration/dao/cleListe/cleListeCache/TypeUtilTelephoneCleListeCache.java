/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class TypeUtilTelephoneCleListeCache extends CleListeCache {

	public TypeUtilTelephoneCleListeCache(String langue) {
		super(langue, GlobalConstants.ListeCache.TELEPHONE_UTIL);
	}

	public TypeUtilTelephoneCleListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), GlobalConstants.ListeCache.TELEPHONE_UTIL);
	}

}
