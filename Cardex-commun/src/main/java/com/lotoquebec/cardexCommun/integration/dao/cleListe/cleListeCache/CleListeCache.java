/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache;

import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * La CleListeCache est utilisé lorsque seulement "tr_traduction.c_tr_genre_fichier"
 * peut différencier une liste d'une autre.
 * @author levassc
 */
public class CleListeCache extends CleListe{

	private String cleListe = "";

	public CleListeCache(String langue, String cleListe) {
		super(langue);
		this.cleListe = cleListe;
	}

	public String getCleListe() {
		return cleListe;
	}
	public void setCleListe(String cleListe) {
		this.cleListe = cleListe;
	}
	public String getLangue() {
		return langue;
	}
	public void setLangue(String langue) {
		this.langue = langue;
	}
	public int hashCode() {
		return 1;
	}
	
	public boolean equals(Object obj) {
		
		if (obj instanceof CleListeCache == false)
			return false;
		
		CleListeCache cleListeCache = (CleListeCache) obj;
		
		if (StringUtils.isNotEmpty( cleListe )
		&& StringUtils.isNotEmpty( cleListeCache.getCleListe() )
		&& cleListe.equals( cleListeCache.getCleListe() ) == false){
				return false;
		}
		
		return super.equals(obj);
	}
}
