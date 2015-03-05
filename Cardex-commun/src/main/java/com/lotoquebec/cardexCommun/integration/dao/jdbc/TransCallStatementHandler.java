package com.lotoquebec.cardexCommun.integration.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 * Object utilis� pour le transport d'un object si la requ�te SQL/Store proc 
 * ne retourne qu'une seul rang�.
 * @author levassc
 *
 */
public abstract class TransCallStatementHandler implements CallStatementHandler {

	protected Object contenu = null;
	
	/**
	 * @param o
	 */
	public TransCallStatementHandler(Object contenu) {
		super();
		this.contenu = contenu;
	}

	public abstract void process(CallableStatement callableStatement) throws SQLException;
	
	public Object getContenu(){
		return contenu;
	}
	
}
