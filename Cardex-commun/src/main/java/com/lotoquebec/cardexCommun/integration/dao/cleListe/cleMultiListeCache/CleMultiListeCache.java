/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache;

import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * CleMultiListeCache est utilisé lorsqu'une table étrangère donne une
 * valeur discriminante.  Si la colonne de description n'est pas dans TR_TRADUCTION
 * il faut utilisé CleMultiExterneListeCache.
 * @author levassc
 */
public abstract class CleMultiListeCache extends CleListe {

	// multi-table liste
	protected String nomTable = "";
	protected String colonneCle = "";
	protected String colonneDiscriminantCle = "";

	public CleMultiListeCache(String langue, String discriminantValeur) {
		super(langue);
		this.discriminantValeur = discriminantValeur;
	}
	
	public CleMultiListeCache(String langue, long discriminantValeur) {
		super(langue);
		this.discriminantValeur = String.valueOf(discriminantValeur);
	}
	
	public String getColonneCle() {
		return colonneCle;
	}
	public String getColonneDiscriminantCle() {
		return colonneDiscriminantCle;
	}
	public String getNomTable() {
		return nomTable;
	}
	
	public int hashCode() {
		return 1;
	}
	
	public boolean equals(Object obj) {
		
		if (obj instanceof CleMultiListeCache == false)
			return false;
		
		CleMultiListeCache cleMultiListeCache = (CleMultiListeCache) obj;
		
		if (StringUtils.isNotEmpty( nomTable )
		&& StringUtils.isNotEmpty( cleMultiListeCache.getNomTable() )
		&& nomTable.equals( cleMultiListeCache.getNomTable() ) == false){
			return false;
		}

		if (StringUtils.isNotEmpty( colonneCle )
		&& StringUtils.isNotEmpty( cleMultiListeCache.getColonneCle() )
		&& colonneCle.equals( cleMultiListeCache.getColonneCle() ) == false){
			return false;
		}
		
		if (StringUtils.isNotEmpty( colonneDiscriminantCle )
		&& StringUtils.isNotEmpty( cleMultiListeCache.getColonneDiscriminantCle() )
		&& colonneDiscriminantCle.equals( cleMultiListeCache.getColonneDiscriminantCle() )
		&& StringUtils.isNotEmpty( discriminantValeur )
		&& StringUtils.isNotEmpty( cleMultiListeCache.getDiscriminantValeur() )
		&& discriminantValeur.equals( cleMultiListeCache.getDiscriminantValeur() ) == false){
			return false;
		}
		
		if ((StringUtils.isEmpty( colonneDiscriminantCle )
		 && StringUtils.isNotEmpty( cleMultiListeCache.getColonneDiscriminantCle()))
		|| (StringUtils.isNotEmpty( colonneDiscriminantCle )
		 && StringUtils.isEmpty( cleMultiListeCache.getColonneDiscriminantCle()))){
			return false;
		}

		if ((StringUtils.isEmpty( discriminantValeur )
		 && StringUtils.isNotEmpty( cleMultiListeCache.getDiscriminantValeur()))
		|| (StringUtils.isNotEmpty( discriminantValeur )
		 && StringUtils.isEmpty( cleMultiListeCache.getDiscriminantValeur()))){
			return false;
		}		
		
		return super.equals(obj);
	}	
}
