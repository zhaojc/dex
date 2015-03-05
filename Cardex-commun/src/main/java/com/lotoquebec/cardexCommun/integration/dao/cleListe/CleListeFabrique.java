package com.lotoquebec.cardexCommun.integration.dao.cleListe;

import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;

public class CleListeFabrique {

	public static TableValeurCleSQLListeCache obtenir(String classe, String langue, String valeurTableValeur, String valeurDiscriminant, String actionSecurite){
		try {
			TableValeurCleSQLListeCache tableValeurCleSQLListeCache = (TableValeurCleSQLListeCache) Class.forName( classe ).newInstance();
			
			tableValeurCleSQLListeCache.setLangue(langue);
			tableValeurCleSQLListeCache.setValeurTableValeur(valeurTableValeur);;
			tableValeurCleSQLListeCache.setDiscriminantValeur(valeurDiscriminant);
			tableValeurCleSQLListeCache.setActionSecurite(actionSecurite);
			
			return tableValeurCleSQLListeCache;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Problème de construction dynamique");
	}
	
}
