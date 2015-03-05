/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class ParticularitesCleListeCache extends CleListeCache {

	public ParticularitesCleListeCache(String langue) {
		super(langue, GlobalConstants.ListeCache.PARTICULARITE);
	}

	public ParticularitesCleListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), GlobalConstants.ListeCache.PARTICULARITE);
	}

}
