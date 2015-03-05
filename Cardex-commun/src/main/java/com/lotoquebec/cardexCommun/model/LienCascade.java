/*
 * Created on 26-May-2008
 */
package com.lotoquebec.cardexCommun.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Cet objet de service g�re les valeurs des listes cascades.
 * Par exemple, le Pays, la province et les villes.
 * Si on change de pays, ce ne sera plus les m�mes provinces et villes.
 * R�alisation: Nous avons un Map pour pouvoir retrouver les valeurs avec une cle
 * Cette classe doit �tre sous-class� pour �tre utilis�, de cette fa�on les liens
 * de cascade sont impl�ment� par default. 
 * @author levassc
 */
public abstract class LienCascade implements Serializable {

	private Map lienValeurMap = new HashMap();
	private LienValeur precedentAjouter = null;
	
	protected void ajout(String cle){
		LienValeur lienValeur = new LienValeur(precedentAjouter);
		lienValeurMap.put(cle, lienValeur);
		precedentAjouter = lienValeur;
	}
	
	public void set(String cle, String valeur){
		LienValeur lienValeur = (LienValeur) lienValeurMap.get( cle );
		
		if (lienValeur == null)
			throw new AssertionError("La cle n'est pas disponible pour le set: "+cle);
		
		lienValeur.setValeur( valeur );
	}
	
	public String get(String cle){
		LienValeur lienValeur = (LienValeur) lienValeurMap.get( cle );
		
		if (lienValeur == null)
			throw new AssertionError("La cle n'est pas disponible pour le get: "+cle);
		
		return lienValeur.getValeur();
	}
	
}
