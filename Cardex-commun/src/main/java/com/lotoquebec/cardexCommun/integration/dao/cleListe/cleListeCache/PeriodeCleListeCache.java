/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class PeriodeCleListeCache extends CleListeCache {

	public PeriodeCleListeCache(String langue) {
		super(langue, GlobalConstants.ListeCache.PERIODE);
	}

	public PeriodeCleListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), GlobalConstants.ListeCache.PERIODE);
	}
}
