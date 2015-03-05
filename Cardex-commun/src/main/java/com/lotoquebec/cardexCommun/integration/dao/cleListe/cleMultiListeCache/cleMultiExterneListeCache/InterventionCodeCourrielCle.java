/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache;


/**
 * @author levassc
 */
public class InterventionCodeCourrielCle extends CleMultiExterneListeCache{

	public InterventionCodeCourrielCle() {
		super("", "");
		constructeur();
	}

	private void constructeur() {
		this.nomTable = "IV_INTERVENTION";
		this.colonneCle = "V_TI_CODE";
		this.colonneDescription = "V_IV_COURRIEL";
		this.colonneDiscriminantCle = "";
		this.discriminantValeurRequis = false;
	}

}
