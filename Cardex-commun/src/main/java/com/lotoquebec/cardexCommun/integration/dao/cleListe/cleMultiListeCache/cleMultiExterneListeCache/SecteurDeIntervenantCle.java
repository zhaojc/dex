/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;



/**
 * Cette classe est requise pour avoir un secteur d'un intervenant donnée
 * @author levassc
 */
public class SecteurDeIntervenantCle extends CleMultiExterneListeCache{

	public SecteurDeIntervenantCle() {
		super("", "");
		constructeur();
	}

	private void constructeur() {
		this.nomTable = "IN_INTERVENANT";
		this.colonneCle = "NAME";
		this.colonneDescription = "l_in_secteur";
		this.colonneDiscriminantCle = "";
		this.discriminantValeurRequis = false;
	}

	public SecteurDeIntervenantCle(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), "");
		constructeur();
	}

}
