/*
 * Created on 11-Feb-2008
 */
package com.lotoquebec.cardex.ejb.flux.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.TruePredicate;

import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;

/**
 * @author levassc
 */
public class SocietePredicate {

	private List listPredicate = new ArrayList();
	private List listSociete = new ArrayList();
	
	public SocietePredicate(){
	}
	
	public SocietePredicate(Societe societe){
		add(TruePredicate.INSTANCE, societe);
	}
	
	public Societe obtenirSocieteDe(Sujet sujet){
		Iterator iter = listPredicate.iterator();
		int i = 0;
		
		while (iter.hasNext()) {
			Predicate predicate = (Predicate) iter.next();
			
			if (predicate.evaluate( sujet ))
				return (Societe) listSociete.get( i );
			i++;
		}
		return null;
	}
	
	public void add(Predicate predicate, Societe societe){
		listPredicate.add( predicate );
		listSociete.add( societe );
	}
	
	public List getListSociete() {
		return listSociete;
	}
}
