/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class CaracteristiqueCleListeCache extends CleListeCache {

	public CaracteristiqueCleListeCache(String langue) {
		super(langue, GlobalConstants.ListeCache.CARACTERISTIQUE);
	}

	public CaracteristiqueCleListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), GlobalConstants.ListeCache.CARACTERISTIQUE);
	}

}
