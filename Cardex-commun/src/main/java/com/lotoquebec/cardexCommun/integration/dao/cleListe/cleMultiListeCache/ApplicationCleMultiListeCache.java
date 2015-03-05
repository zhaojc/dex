/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache;

import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.CleMultiExterneListeCache;



/**
 * @author levassc
 */
public class ApplicationCleMultiListeCache extends CleMultiExterneListeCache{


	public ApplicationCleMultiListeCache(String langue) {
		super(langue, "");
		this.nomTable = "RS_ROLE_SECURITE";
		this.colonneCle = "V_RS_APPLICATION";
		this.colonneDescription = "V_RS_APPLICATION";
	}

}
