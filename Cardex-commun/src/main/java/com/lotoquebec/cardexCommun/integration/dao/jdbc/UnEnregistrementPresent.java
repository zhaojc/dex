package com.lotoquebec.cardexCommun.integration.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UnEnregistrementPresent<T> implements RowCallbackHandler {
	protected T object;
	
	public abstract void processRow(ResultSet rs) throws SQLException;

	public T getObject() {
		return object;
	}
	
}
