/*
 * Created on 24-Jan-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.DAOConnection;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;
import com.lotoquebec.cardexCommun.util.TacheUtilisateur;

/**
 * @author levassc
 */
public class JDBCTemplate {

	private CardexAuthenticationSubject subject = null;
	private Connection connection = null;
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));
	private boolean ignoreWarnings = true;
    
	
	/**
	 * @throws DAOException
	 * 
	 */
	public JDBCTemplate(CardexAuthenticationSubject s) throws DAOException {
		super();
		subject = s;
		connection = DAOConnection.getInstance().getConnection(subject);
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
	
	public JDBCTemplate(Connection conn) {
		super();
		connection = conn;
	}
	
	public JDBCTemplate() throws DAOException{
		Connection connection = DAOConnection.getInstance().getConnection();
		this.connection = connection;
	}

	public void executer (List<String> listeSQL, boolean fermerConnection) throws DAOException{
		for (String sql:listeSQL)
			query(sql, null, false);
		
		if (fermerConnection)
			fermerConnection();
	}
	
	/**
	 * Cette m�thode peut favoriser l'injection SQL.  SVP utiliser PreparerConditionSQL
	 * @param sql
	 * @param sequence
	 * @param callbackHandler
	 * @param fermerConnection
	 * @throws DAOException
	 * @Deprecated Utiliser la m�thode avec PreparerSQL
	 */
	public void query (String sql, RowCallbackHandler callbackHandler) throws DAOException{
		query(sql, callbackHandler, true);
	}

	public void query (PreparerSQL preparerSQL, RowCallbackHandler callbackHandler) throws DAOException{
		query(preparerSQL, null, callbackHandler, true);
	}
	
	/**
	 * Cette m�thode peut favoriser l'injection SQL.  SVP utiliser PreparerConditionSQL
	 * @param sql
	 * @param sequence
	 * @param callbackHandler
	 * @param fermerConnection
	 * @throws DAOException
	 * @Deprecated Utiliser la m�thode avec PreparerSQL
	 */
	public void query (String sql, RowCallbackHandler callbackHandler, boolean fermerConnection) throws DAOException{
		
		try {
			PreparedStatement ps = connection.prepareStatement( sql );
			ResultSet rs = ps.executeQuery();
			
			if (log.isDebugEnabled())
				log.debug("Executer le SQL : '"+sql+"'");

			if (callbackHandler != null)
				while(rs.next()){
					GererTacheUtilisateur.verifierThreadCourrant();
					callbackHandler.processRow(rs);
				}
			SQLWarning warning = ps.getWarnings();
			
			ps.close();
			rs.close();
			throwExceptionOnWarningIfNotIgnoringWarnings(warning);
			
		} catch (SQLException e) {
			log.error("Probl�me avec le SQL :"+sql);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
			throw new DAOException(e);
		} finally {
			if (fermerConnection){
				fermerConnection();
			}
		}
	}
	
	/**
	 * Cette m�thode peut favoriser l'injection SQL.  SVP utiliser PreparerConditionSQL
	 * @param sql
	 * @param sequence
	 * @param callbackHandler
	 * @param fermerConnection
	 * @throws DAOException
	 * @Deprecated Utiliser la m�thode avec PreparerSQL
	 */
	public void query (String sql, Integer sequence, RowCallbackHandler callbackHandler) throws DAOException{
		query(sql, sequence, callbackHandler, true);
	}

	public void query (PreparerSQL preparerSQL, Integer sequence, RowCallbackHandler callbackHandler) throws DAOException{
		query(preparerSQL, sequence, callbackHandler, true);
	}
	
	public void query (PreparerSQL preparerSQL, Integer sequence, RowCallbackHandler callbackHandler, boolean fermerConnection) throws DAOException{
		GererTacheUtilisateur gererTacheUtilisateur = GererTacheUtilisateur.getInstanceOf();
		String SQL = "";
		try {
			GererTacheUtilisateur.verifierThreadCourrant();
			SQL = preparerSQL.getSQL().toString();
			PreparedStatement ps = connection.prepareStatement( SQL );
			preparerSQL.assignerPreparedStatement(ps);
			gererTacheUtilisateur.ajouterTacheUtilisateur(sequence, new TacheUtilisateur(sequence, ps, Thread.currentThread()));
			
			ResultSet rs = ps.executeQuery();
			
			if (log.isDebugEnabled())
				log.debug("Executer le SQL : '"+SQL+"'");
			while(rs.next()){

				GererTacheUtilisateur.verifierThreadCourrant();
				callbackHandler.processRow(rs);
			}
			SQLWarning warning = ps.getWarnings();
			
			ps.close();
			rs.close();
			throwExceptionOnWarningIfNotIgnoringWarnings(warning);
			
		} catch (SQLException e) {
			log.error("Probl�me avec le SQL :"+SQL);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
			throw new DAOException(e);
		} finally {
			gererTacheUtilisateur.retraitTacheUtilisateur(sequence);
			
			if (fermerConnection){
				fermerConnection();
			}
		}
	}	
	
	/**
	 * Cette m�thode peut favoriser l'injection SQL.  SVP utiliser PreparerConditionSQL
	 * @param sql
	 * @param sequence
	 * @param callbackHandler
	 * @param fermerConnection
	 * @throws DAOException
	 * @Deprecated Utiliser la m�thode avec PreparerSQL
	 */
	public void query (String sql, Integer sequence, RowCallbackHandler callbackHandler, boolean fermerConnection) throws DAOException{
		GererTacheUtilisateur gererTacheUtilisateur = GererTacheUtilisateur.getInstanceOf();
		
		try {
			GererTacheUtilisateur.verifierThreadCourrant();
			PreparedStatement ps = connection.prepareStatement( sql );
			gererTacheUtilisateur.ajouterTacheUtilisateur(sequence, new TacheUtilisateur(sequence, ps, Thread.currentThread()));
			
			ResultSet rs = ps.executeQuery();
			
			if (log.isDebugEnabled())
				log.debug("Executer le SQL : '"+sql+"'");
				
			while(rs.next()){
				GererTacheUtilisateur.verifierThreadCourrant();
				callbackHandler.processRow(rs);
			}
			SQLWarning warning = ps.getWarnings();
			
			ps.close();
			rs.close();
			throwExceptionOnWarningIfNotIgnoringWarnings(warning);
			
		} catch (SQLException e) {
			log.error("Probl�me avec le SQL :"+sql);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e1);
			}
			throw new DAOException(e);
		} finally {
			gererTacheUtilisateur.retraitTacheUtilisateur(sequence);
			
			if (fermerConnection){
				fermerConnection();
			}
		}
	}	
	
	private void throwExceptionOnWarningIfNotIgnoringWarnings(SQLWarning warning) throws SQLWarning {
		
		if (warning != null){
			
			if(this.ignoreWarnings){
				log.debug("SQLWarning ignored: "+ warning);
			} else {
				throw new SQLWarning("Warning not ignored : "+warning);
			}
		}
	}
	
	public void fermerConnection() throws DAOException {
		
		if (connection != null){
	         try {
				//connection.commit();
	        	 connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
	}
	
}
