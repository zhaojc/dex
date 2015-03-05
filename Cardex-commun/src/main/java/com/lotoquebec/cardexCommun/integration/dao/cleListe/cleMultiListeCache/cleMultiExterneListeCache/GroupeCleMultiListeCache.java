/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache;
 
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;


/**
 * @author levassc
 */
public class GroupeCleMultiListeCache extends CleMultiExterneListeCache {

	public GroupeCleMultiListeCache(String langue) {
		super(langue, "");
		constructeur();
	}

	private void constructeur() {
		this.nomTable = "GS_GROUPE_SECURITE";
		this.colonneCle = "L_GS_CLE";
		this.colonneDescription = "V_GS_NOM";
		this.colonneDiscriminantCle = "";
		this.discriminantValeurRequis = false;
	}

	public GroupeCleMultiListeCache(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), "");
		constructeur();
	}

}
