/*
 * Created on 28-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;

/**
 * @author levassc
 */
public abstract class CleHardListe extends CleListe {

	private Map map = new LinkedHashMap();
	
	protected abstract void genererListe();
	
	public CleHardListe(String langue) {
		super(langue);
	}

	protected void add(String valeur, String texte){
		map.put( valeur, new LabelValueBean(texte, valeur) );
	}
	
	public Map getMap() {
		map.clear();
		genererListe();
		return map;
	}
	
	public int hashCode() {
		return 1;
	}
	
	/*
	 * Une liste Hard est unique par classe.  Donc, nous vérifions le type de classe et c'est tout.
	 * (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.CleListe#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		
		if (obj instanceof CleHardListe == false)
			return false;
		
		return this.getClass().isAssignableFrom( obj.getClass() );
	}

	private boolean equal(Map map1, Map map2) {

		if (map1.size() != map2.size())
			return false;
		Iterator iter1 = map1.values().iterator();
		Iterator iter2 = map2.values().iterator();
		
		while (iter1.hasNext()) {
			LabelValueBean label1 = (LabelValueBean) iter1.next();
			LabelValueBean label2 = (LabelValueBean) iter2.next();
			
			if (label1.getValue().equals( label2.getValue() ) == false
			|| label1.getLabel().equals( label2.getLabel() ) == false)
				return false;
		}
		
		return true;
	}
	
}
