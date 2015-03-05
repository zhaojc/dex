/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class ReferenceVideoCleListeCache extends CleListeCache {

	public ReferenceVideoCleListeCache(String langue) {
		super(langue, GlobalConstants.ListeCache.REFERENCE_VIDEO);
	}

	public ReferenceVideoCleListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), GlobalConstants.ListeCache.REFERENCE_VIDEO);
	}

}
