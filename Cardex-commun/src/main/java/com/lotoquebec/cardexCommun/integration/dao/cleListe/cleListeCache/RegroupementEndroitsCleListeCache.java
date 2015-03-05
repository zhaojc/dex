/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class RegroupementEndroitsCleListeCache extends CleListeCache {

	public RegroupementEndroitsCleListeCache(String langue) {
		super(langue, GlobalConstants.ListeCache.REGROUPEMENTS_ENDROITS);
	}

	public RegroupementEndroitsCleListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), GlobalConstants.ListeCache.REGROUPEMENTS_ENDROITS);
	}

}
