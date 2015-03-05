/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;


/**
 * @author levassc
 */
public class SiteInterventionCle extends CleMultiExterneListeCache{

	public SiteInterventionCle(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), "");
		assignerValeurDefault();
	}
	
	public SiteInterventionCle(String langue) {
		super(langue, "");
		assignerValeurDefault();
	}

	private void assignerValeurDefault() {
		this.nomTable = "SN_SITE_INTERVENTION";
		this.colonneCle = "V_SN_CLE";
		this.colonneDescription = "V_SN_DESCRIPTION";
		this.colonneDiscriminantCle = "";
		this.discriminantValeurRequis = false;
	}

}
