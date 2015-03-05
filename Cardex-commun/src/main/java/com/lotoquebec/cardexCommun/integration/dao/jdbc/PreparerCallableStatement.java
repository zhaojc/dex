package com.lotoquebec.cardexCommun.integration.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.SQLException;


public interface PreparerCallableStatement {

	/**
	 * Cette m�thode assigne les param�tres pour appeler la store proc.
	 * Elle doit retourner l'index de param�tre de retour
	 * @param callableStatement
	 * @return
	 * @throws SQLException
	 */
	void preparer(CallableStatement callableStatement) throws SQLException;

}
