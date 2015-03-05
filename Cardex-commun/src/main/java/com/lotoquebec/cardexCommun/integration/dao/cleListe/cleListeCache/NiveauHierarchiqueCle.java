/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache;

import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;

/**
 * @author levassc
 */
public class NiveauHierarchiqueCle extends CleListeCache {

	public NiveauHierarchiqueCle(String langue) {
		super(langue, GlobalConstants.ListeCache.NIVEAU_HIERARCHIQUE);
	}

	public NiveauHierarchiqueCle(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), GlobalConstants.ListeCache.NIVEAU_HIERARCHIQUE);
	}
}
