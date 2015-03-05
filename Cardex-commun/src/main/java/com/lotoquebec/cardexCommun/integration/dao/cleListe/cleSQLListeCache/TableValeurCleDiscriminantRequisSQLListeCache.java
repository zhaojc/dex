package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;



/**
 * Cette classe est la TableValeurCleSQLListeCache obligatoire
 * @author levassc
 */
public class TableValeurCleDiscriminantRequisSQLListeCache extends TableValeurCleSQLListeCache{

	public TableValeurCleDiscriminantRequisSQLListeCache() {
		super();
	}

	public TableValeurCleDiscriminantRequisSQLListeCache(String langue, String valeurTableValeur, long[] discriminantValeurs, String actionSecurite) {
		super(langue, valeurTableValeur, discriminantValeurs, actionSecurite);
		this.discriminantValeurRequis = true;
	}
	
	public TableValeurCleDiscriminantRequisSQLListeCache(
			CardexAuthenticationSubject subject, String valeurTableValeur,
			long discriminantValeur, String actionSecurite) {
		super(subject, valeurTableValeur, discriminantValeur, actionSecurite);
		this.discriminantValeurRequis = true;
	}

	public TableValeurCleDiscriminantRequisSQLListeCache(
			CardexAuthenticationSubject subject, String valeurTableValeur,
			String discriminantValeur, String actionSecurite) {
		super(subject, valeurTableValeur, discriminantValeur, actionSecurite);
		this.discriminantValeurRequis = true;
	}

	public TableValeurCleDiscriminantRequisSQLListeCache(
			CardexAuthenticationSubject subject, String valeurTableValeur,
			String actionSecurite) {
		super(subject, valeurTableValeur, actionSecurite);
		this.discriminantValeurRequis = true;
	}

	public TableValeurCleDiscriminantRequisSQLListeCache(String langue,
			String valeurTableValeur, long discriminantValeur,
			String actionSecurite) {
		super(langue, valeurTableValeur, discriminantValeur, actionSecurite);
		this.discriminantValeurRequis = true;
	}

	public TableValeurCleDiscriminantRequisSQLListeCache(String langue,
			String valeurTableValeur, String discriminantValeur,
			String actionSecurite) {
		super(langue, valeurTableValeur, discriminantValeur, actionSecurite);
		this.discriminantValeurRequis = true;
	}

	public TableValeurCleDiscriminantRequisSQLListeCache nouveauTableValeurCleSQLListeCache(String actionSecurite){
		return new TableValeurCleDiscriminantRequisSQLListeCache(getLangue(), getValeurTableValeur(), getDiscriminantValeurs(), actionSecurite);
	}

}
