/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;



/**
 * @author levassc
 */
public class GenreFichierCle extends CleMultiExterneListeCache{

	public GenreFichierCle(String langue) {
		super(langue, "");
		constructeur();
	}

	private void constructeur() {
		this.nomTable = "GF_GENRE_FICHIER";
		this.colonneCle = "C_GF_CLE";
		this.colonneDescription = "V_GF_GENRE_FICHIER";
		this.colonneDiscriminantCle = "";
		this.discriminantValeurRequis = false;
	}

	public GenreFichierCle(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), "");
		constructeur();
	}

}
