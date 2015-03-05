package com.lotoquebec.cardexCommun.integration.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnregistrementPresent implements RowCallbackHandler {
	private boolean trouve = false;
	
	public void processRow(ResultSet rs) throws SQLException {
		trouve = true;
	}

	public boolean isTrouve() {return trouve;}
}
