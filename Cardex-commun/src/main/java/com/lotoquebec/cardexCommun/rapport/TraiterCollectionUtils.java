/*
 * Created on 29-Jan-2008
 */
package com.lotoquebec.cardexCommun.rapport;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author levassc
 */
public class TraiterCollectionUtils {

	
	public static void ajouterSousCollection(Collection listeAtraiter, Discriminant discriminant){
		SousCollection temoin = null;
		Iterator iter = listeAtraiter.iterator();
		
		while (iter.hasNext()) {
			SousCollection element = (SousCollection) iter.next();
			
			if (temoin == null){
				temoin = element;
				temoin.addSousCollection( element.cloneSansCollection() );
				continue;
			}
			
			if (discriminant.comparer(temoin, element)){
				iter.remove();
			}else{
				temoin = element;
			}
			temoin.addSousCollection( element.cloneSansCollection() );
		}
	}

	public static void ajouterSousCollection(Collection listeAtraiter, List listediscriminant){
		ajouterSousCollection(listeAtraiter, listediscriminant, 0);
	}
	
	private static void ajouterSousCollection(Collection listeAtraiter, List listediscriminant, int increment){
		ajouterSousCollection(listeAtraiter, (Discriminant) listediscriminant.get(increment));
		Iterator iter = listeAtraiter.iterator();
		int iterIncrement = increment + 1; 
		
		if (iterIncrement < listediscriminant.size()){

			while (iter.hasNext()) {
				SousCollection element = (SousCollection) iter.next();
				ajouterSousCollection(element.getSousCollection(), listediscriminant, iterIncrement);
			}
		}
	}
}
