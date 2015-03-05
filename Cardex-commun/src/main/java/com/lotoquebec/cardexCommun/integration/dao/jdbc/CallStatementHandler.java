

package com.lotoquebec.cardexCommun.integration.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.SQLException;


public interface CallStatementHandler {

	/**
	 * Cette méthode est appeler à la fin de la requête
	 * @param callableStatement
	 * @return
	 * @throws SQLException
	 */
	void process(CallableStatement callableStatement) throws SQLException;

}
