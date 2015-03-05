package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;



/**
 * Cette classe est la TableValeurCleSQLListeCache obligatoire
 * @author levassc
 */
public class TableValeurCleAbreviationSQLListeCache extends TableValeurCleSQLListeCache{

	public TableValeurCleAbreviationSQLListeCache() {
		super();
	}

	
	public TableValeurCleAbreviationSQLListeCache(String langue,
			String valeurTableValeur, long[] discriminantValeurs,
			String actionSecurite) {
		super(langue, valeurTableValeur, discriminantValeurs, actionSecurite);
		this.abreviation = true;
	}
	
	public TableValeurCleAbreviationSQLListeCache(
			CardexAuthenticationSubject subject, String valeurTableValeur,
			long discriminantValeur, String actionSecurite) {
		super(subject, valeurTableValeur, discriminantValeur, actionSecurite);
		this.abreviation = true;
	}

	public TableValeurCleAbreviationSQLListeCache(
			CardexAuthenticationSubject subject, String valeurTableValeur,
			String discriminantValeur, String actionSecurite) {
		super(subject, valeurTableValeur, discriminantValeur, actionSecurite);
		this.abreviation = true;
	}

	public TableValeurCleAbreviationSQLListeCache(
			CardexAuthenticationSubject subject, String valeurTableValeur,
			String actionSecurite) {
		super(subject, valeurTableValeur, actionSecurite);
		this.abreviation = true;
	}

	public TableValeurCleAbreviationSQLListeCache(String langue,
			String valeurTableValeur, long discriminantValeur,
			String actionSecurite) {
		super(langue, valeurTableValeur, discriminantValeur, actionSecurite);
		this.abreviation = true;
	}

	public TableValeurCleAbreviationSQLListeCache(String langue,
			String valeurTableValeur, String discriminantValeur,
			String actionSecurite) {
		super(langue, valeurTableValeur, discriminantValeur, actionSecurite);
		this.abreviation = true;
	}

	public TableValeurCleAbreviationSQLListeCache nouveauTableValeurCleSQLListeCache(String actionSecurite){
		return new TableValeurCleAbreviationSQLListeCache(getLangue(), getValeurTableValeur(), getDiscriminantValeurs(), actionSecurite);
	}

}
