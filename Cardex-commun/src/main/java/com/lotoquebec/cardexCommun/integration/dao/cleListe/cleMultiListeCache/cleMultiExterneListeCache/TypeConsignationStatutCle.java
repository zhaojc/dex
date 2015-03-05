/*
 * Created on 21-Jul-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;


/**
 * @author levassc
 */
public class TypeConsignationStatutCle extends CleMultiExterneListeCache {

	public TypeConsignationStatutCle(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), "");
		assignerValeurDefault();
	}
	
	public TypeConsignationStatutCle(String langue) {
		super(langue, "");
		assignerValeurDefault();
	}
	
	private void assignerValeurDefault() {
		this.nomTable = "TN_TYPE_CONSIGNATION";
		this.colonneCle = "I_TN_CLE";
		this.colonneDescription = "B_TN_APPROBATION";
		this.colonneDiscriminantCle = "";
		this.discriminantValeurRequis = false;
	}

}
