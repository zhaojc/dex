/*
 * Created on 20-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.util.StringUtils;


/**
 * CleSQLListeCache est utilisé lorsqu'il n'y a aucune autre options que le SQL
 * @author levassc
 */
public abstract class CleSQLListeCache extends CleListe{

	public abstract PreparerSQL getPreparerSQL();

	public CleSQLListeCache(String langue, String discriminantValeur) {
		super(langue);
		this.discriminantValeur = discriminantValeur;
	}
	
	public boolean equals(Object obj) {
		
		if (super.equals(obj) == false)
			return false;
		
		CleSQLListeCache cleSQLListeCache = (CleSQLListeCache) obj;
		
		if (StringUtils.equals( discriminantValeur, cleSQLListeCache.getDiscriminantValeur() )  == false){
			return false;
		}
		
		return true;
	}
	
	public boolean isDiscriminantValeurRequis() {
		return discriminantValeurRequis;
	}

}
