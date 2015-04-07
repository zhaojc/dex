/*
 * Created on 24-Jan-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class StoreProcTemplate {

	private CardexAuthenticationSubject subject = null;
	private Connection connection = null;
	private final Logger      log = LoggerFactory.getLogger((this.getClass()));
	private boolean ignoreWarnings = true;
    private String nom = "";
	private CallableStatement callableStatement = null;
	private int indexResultSetParameter = 0;
	
	
	/**
	 * @throws DAOException
	 */
	public StoreProcTemplate(CardexAuthenticationSubject s) throws DAOException {
		super();
		subject = s;
		connection = DAOConnection.getInstance().getConnection(subject);
	}

	public StoreProcTemplate() throws DAOException {
		this( DAOConnection.getInstance().getConnection() );
	}
	
	public StoreProcTemplate(Connection conn) throws DAOException {
		super();
		connection = conn;
		
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DAOException(e);
		}		
	}
	
	public CallableStatement prepareCall(String nom, int nbParametre, PreparerCallableStatement rowCallHandler) throws DAOException {
		return prepareCall(nom, nbParametre, 0, rowCallHandler);
	}
	
	public CallableStatement prepareCall(String nom, int nbParametre, int indexResultSetParameter, PreparerCallableStatement rowCallHandler) throws DAOException {
        try {
        	this.nom = construireNomStoreProc(nom, nbParametre);
			callableStatement = connection.prepareCall(this.nom);
			this.indexResultSetParameter = indexResultSetParameter;
			
			if (rowCallHandler != null)
				rowCallHandler.preparer( callableStatement );
		} catch (SQLException e) {
			throw new DAOException(e);
		}
        return callableStatement;
	}

	public void query () throws DAOException{
		query(true);
	}
	
	public void query (boolean closeConnection) throws DAOException{
		try {
			callableStatement.execute();
		} catch (SQLException e) {
			log.error("Probl�me avec la StoreProc : "+nom);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
			throw new DAOException(e);
		} finally {
			if (closeConnection)
				fermerConnection();
		}
	}
	
	public void query (RowCallbackHandler callbackHandler) throws DAOException{
		query(callbackHandler, true);
	}
	
	public void query (CallStatementHandler callStatementHandler,  boolean closeConnection) throws DAOException{
		try {
			callableStatement.execute();
			callStatementHandler.process( callableStatement );
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
			throw new DAOException(e);
		} finally {
			if (closeConnection)
				fermerConnection();
		}
	}	
	
	public void query (RowCallbackHandler callbackHandler, boolean closeConnection) throws DAOException{
		
		try {
			callableStatement.execute();
			
			ResultSet rs  = (ResultSet)callableStatement.getObject( indexResultSetParameter );

			while(rs.next()){
				callbackHandler.processRow(rs);
			}
			SQLWarning warning = rs.getWarnings();
			
			rs.close();
			throwExceptionOnWarningIfNotIgnoringWarnings(warning);
			
		} catch (SQLException e) {
			log.error("Probl�me avec la StoreProc : "+nom);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
			throw new DAOException(e);
		} finally {
				if (closeConnection)
					fermerConnection();
		}
	}
	
	public ResultSet queryResultSet() throws DAOException{
		
		try {
			callableStatement.execute();
			return (ResultSet)callableStatement.getObject( indexResultSetParameter );
			
		} catch (SQLException e) {
			log.error("Probl�me avec la StoreProc : "+nom);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
			throw new DAOException(e);
		}
	}
	
	private String construireNomStoreProc(String nom, int nbParametre){
		
		if (nbParametre == 0)
			return "begin "+nom+"; end;";
		
		String param = StringUtils.repeat("?,", nbParametre);
		param = StringUtils.left(param, param.length()-1 );
        return "begin "+nom+" ("+param+"); end;";
	}

	
	private void throwExceptionOnWarningIfNotIgnoringWarnings(SQLWarning warning) throws SQLWarning {
		
		if (warning != null){
			
			if(this.ignoreWarnings){
				//log.debug("SQLWarning ignored: "+ warning);
			} else {
				throw new SQLWarning("Warning not ignored : "+warning);
			}
		}
	}
	
	public void fermerConnection() throws DAOException  {
		try {
			callableStatement.close();
			
			if (connection != null){
				//connection.commit();
				connection.setAutoCommit(true);
		        connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		
	}


}
