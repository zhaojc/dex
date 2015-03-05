/*
 * Created on 24-Jan-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class FonctionTemplate {

	private CardexAuthenticationSubject subject = null;
	private Connection connection = null;
	private CallableStatement callableStatement = null;
	
	
	/**
	 * @throws DAOException
	 */
	public FonctionTemplate(CardexAuthenticationSubject s) throws DAOException {
		super();
		subject = s;
		connection = DAOConnection.getInstance().getConnection(subject);
	}

	public FonctionTemplate() throws DAOException {
		connection = DAOConnection.getInstance().getConnection();
	}
	
	public FonctionTemplate(Connection conn) throws DAOException {
		super();
		connection = conn;
		
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DAOException(e);
		}		
	}
	
	public CallableStatement prepareCall(String nom, int nbParametre, PreparerCallableStatement rowCallHandler) throws DAOException {
        try {
			callableStatement = connection.prepareCall(
					construireNomFonction(nom, nbParametre));
			
			if (rowCallHandler != null)
				rowCallHandler.preparer( callableStatement );
		} catch (SQLException e) {
			throw new DAOException(e);
		}
        return callableStatement;
	}

	public Object query (boolean closeConnection) throws DAOException{
		try {
			callableStatement.execute();
			
			return callableStatement.getObject(1);
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
			throw new DAOException(e);
		} finally {
			try {
				
				if (closeConnection){
					fermerConnection();
				}
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
		}
	}
	
	private String construireNomFonction(String nom, int nbParametre){
		String param = StringUtils.repeat("?,", nbParametre);
		param = StringUtils.left(param, param.length()-1 );
        return "{? = call "+nom+" ("+param+")}";
	}
	
	public void fermerConnection() throws SQLException {
		callableStatement.close();
		
		if (connection != null){
			//connection.commit();
			connection.setAutoCommit(true);
	        connection.close();
		}
	}


}
