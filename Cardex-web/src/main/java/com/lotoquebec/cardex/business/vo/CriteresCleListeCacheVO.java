/*
 * Created on 13-May-2008
 */
package com.lotoquebec.cardex.business.vo;

import com.lotoquebec.cardex.business.CriteresCleListeCache;

/**
 * @author levassc
 */
public class CriteresCleListeCacheVO implements CriteresCleListeCache {

	private String cleListe = "";
	private String langue = "";

	
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
}
