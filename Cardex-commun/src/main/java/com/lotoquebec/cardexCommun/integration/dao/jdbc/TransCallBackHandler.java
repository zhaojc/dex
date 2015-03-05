package com.lotoquebec.cardexCommun.integration.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Object utilisé pour le transport d'un object si la requête SQL/Store proc 
 * ne retourne qu'une seul rangé.
 * @author levassc
 *
 */
public abstract class TransCallBackHandler implements RowCallbackHandler {

	protected Object contenu = null;
	
	/**
	 * @param o
	 */
	public TransCallBackHandler(Object contenu) {
		super();
		this.contenu = contenu;
	}

	public abstract void processRow(ResultSet rs) throws SQLException;

	/*protected void setContenu(Object contenu){
		this.contenu = contenu;
	}
	
	public Object getContenu(){
		return contenu;
	}*/
	
}
