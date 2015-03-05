/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache;

import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.CleMultiListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * CleMultiExterneListeCache est utilisé dans le cas ou CleMultiListeCache
 * ne fonctionne pas, donc, le cas ou la colonne de description n'est
 * pas dans la table TR_TRADUCTION.
 * @author levassc
 */
public abstract class CleMultiExterneListeCache extends CleMultiListeCache {

	// valeur-table liste
	protected String colonneDescription = "";

	public CleMultiExterneListeCache(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
	}
	
	public String getColonneDescription() {
		return colonneDescription;
	}
	
	public int hashCode() {
		return 1;
	}
	
	public boolean equals(Object obj) {
		
		if (obj instanceof CleMultiExterneListeCache == false)
			return false;
		
		CleMultiExterneListeCache cleMultiListeCache = (CleMultiExterneListeCache) obj;

		if (StringUtils.isNotEmpty( colonneDescription )
		&& StringUtils.isNotEmpty( cleMultiListeCache.getColonneDescription() )
		&& colonneDescription.equals( cleMultiListeCache.getColonneDescription() ) == false){
			return false;
		}
		
		return super.equals(obj);
	}	
}
