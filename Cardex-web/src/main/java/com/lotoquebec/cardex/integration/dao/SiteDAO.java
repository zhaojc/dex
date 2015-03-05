/*
 * Created on 22-Sep-2008
 */
package com.lotoquebec.cardex.integration.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.lotoquebec.cardex.business.Site;
import com.lotoquebec.cardex.business.vo.SiteVO;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerCallableStatement;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.RowCallbackHandler;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.StoreProcTemplate;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.UnEnregistrementPresent;

/**
 * @author levassc
 */
public class SiteDAO {

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.SiteDAO#obtenirListeSite(java.sql.Connection)
	 */
	public List obtenirListeSite(Connection connection) throws DAOException {
		final List liste = new ArrayList();
		
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			}
    	};		
		
		storeProcTemplate.prepareCall("CARDEX_LIRE_PILOTAGE.SP_L2_SI_SITE", 1, 1, rch);
		
    	RowCallbackHandler rcbh = new RowCallbackHandler(){
			public void processRow(ResultSet rs) throws SQLException {
				Site site = new SiteVO();
				
				site.setCle( rs.getLong(1) );
				site.setEntite( rs.getInt(2) );
				site.setAbreviation( rs.getString(3) );
				site.setNom( rs.getString(4) );
				site.setCreateur( rs.getString(5) );
				site.setDateCreation( rs.getTimestamp(6) );
				site.setModifierPar( rs.getString(7) );
				site.setDateModification(rs.getTimestamp(8));
				site.setNumeroSequence(rs.getLong(9));
				site.setDateSequence(rs.getTimestamp(10));
				site.setSiteOrigine(rs.getBoolean(11));
				site.setSiteApplicable(rs.getBoolean(12));

				liste.add(site);
			}
    	};		
		
		storeProcTemplate.query(rcbh, false);
		
		return liste;
	}

	public Site obtenirSite(final long numeroSite, Connection connection) throws DAOException {
		final Site site = new SiteVO();
		
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			callableStatement.setLong(1, numeroSite);
    			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			}
    	};		
		
		storeProcTemplate.prepareCall("CARDEX_LIRE_PILOTAGE.SP_L3_SI_SITE", 2, 2, rch);
		
    	UnEnregistrementPresent enregistrementPresent = new UnEnregistrementPresent(){
			public void processRow(ResultSet rs) throws SQLException {
				
				site.setCle( rs.getLong(1) );
				site.setEntite( rs.getInt(2) );
				site.setAbreviation( rs.getString(3) );
				site.setNom( rs.getString(4) );
				site.setCreateur( rs.getString(5) );
				site.setDateCreation( rs.getTimestamp(6) );
				site.setModifierPar( rs.getString(7) );
				site.setDateModification(rs.getTimestamp(8));
				site.setNumeroSequence(rs.getLong(9));
				site.setDateSequence(rs.getTimestamp(10));
				site.setSiteOrigine(rs.getBoolean(11));
				site.setSiteApplicable(rs.getBoolean(12));
			}
    	};		
		
		storeProcTemplate.query(enregistrementPresent, false);
		
		return site;
	}
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.SiteDAO#enregistrerSequence(com.lotoquebec.cardex.business.Site)
	 */
	public void enregistrerSequence(final Site site, Connection connection) throws DAOException{
		StoreProcTemplate storeProcTemplate = new StoreProcTemplate(connection);
		
		PreparerCallableStatement rch = new PreparerCallableStatement(){

    		public void preparer(CallableStatement callableStatement) throws SQLException {
    			OracleDAOUtils.setLong(callableStatement, 1, site.getCle());
    			callableStatement.setTimestamp(2, site.getDateSequence());
    			OracleDAOUtils.setLong(callableStatement, 3, site.getNumeroSequence());
			}
    	};
    	
    	storeProcTemplate.prepareCall("CARDEX_DOC.SP_E_SI_SITE_SEQUENCE", 3, rch);
    	
    	storeProcTemplate.query( false );
	}

}
