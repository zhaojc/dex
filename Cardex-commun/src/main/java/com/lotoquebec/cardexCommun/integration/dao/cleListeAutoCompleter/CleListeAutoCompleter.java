/*
 * Created on 4-Aug-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListeAutoCompleter;

/**
 * Cette classe est la base de la recherche pour l'autocomplet.
 * @author levassc
 */
public abstract class CleListeAutoCompleter {

	private String table = "";
	private String champ = "";
	private String champConfidentialite = "";
	
	public CleListeAutoCompleter(String table, String champ, String champConfidentialite) {
		super();
		this.table = table;
		this.champ = champ;
		this.champConfidentialite = champConfidentialite;
	}
	
	public CleListeAutoCompleter(String table, String champ) {
		super();
		this.table = table;
		this.champ = champ;
	}
	
	public String getChamp() {
		return champ;
	}
	public String getTable() {
		return table;
	}
	public String getChampConfidentialite() {
		return champConfidentialite;
	}

}
