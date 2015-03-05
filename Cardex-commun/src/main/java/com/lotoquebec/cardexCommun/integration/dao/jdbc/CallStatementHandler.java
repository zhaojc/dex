

package com.lotoquebec.cardexCommun.integration.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.SQLException;


public interface CallStatementHandler {

	/**
	 * Cette m�thode est appeler � la fin de la requ�te
	 * @param callableStatement
	 * @return
	 * @throws SQLException
	 */
	void process(CallableStatement callableStatement) throws SQLException;

}
