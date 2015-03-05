/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;


/**
 * @author levassc
 */
public class TypeInterventionCle extends CleMultiExterneListeCache {

	public TypeInterventionCle(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), "");
		assignerValeurDefault();
	}
	
	public TypeInterventionCle(String langue) {
		super(langue, "");
		assignerValeurDefault();
	}
	
	private void assignerValeurDefault() {
		this.nomTable = "TI_TYPE_INTERVENTION";
		this.colonneCle = "V_TI_CODE";
		this.colonneDescription = "V_TI_DESCRIPTION";
		this.colonneDiscriminantCle = "";
		this.discriminantValeurRequis = false;
	}

}
