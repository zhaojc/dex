/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;



/**
 * @author levassc
 */
public class ProvinceEtPaysCleSQLListeCache extends CleSQLListeCache{

	public ProvinceEtPaysCleSQLListeCache(String langue) {
		super(langue, "");
	}

	public ProvinceEtPaysCleSQLListeCache(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), "");
	}

	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		SQL.append("select distinct tr.l_tr_cle as "+ListeCacheSQL.CLE+", tr.v_tr_description as "+ListeCacheSQL.DESCRIPTION+" "); 
		SQL.append("from pr_province pr, tr_traduction tr ");
		SQL.append("where pr.l_pr_cle = tr.l_tr_cle AND TR.I_LA_CLE = ? ");
		preparerSQL.addParametre(OracleDAOUtils.getLongLangue(langue));
		SQL.append("UNION ");
		SQL.append("select distinct tr.l_tr_cle as "+ListeCacheSQL.CLE+", tr.v_tr_description as "+ListeCacheSQL.DESCRIPTION+" "); 
		SQL.append("from pa_pays pa, tr_traduction tr ");
		SQL.append("where pa.i_pa_cle = tr.l_tr_cle AND TR.I_LA_CLE = ? ");
		preparerSQL.addParametre(OracleDAOUtils.getLongLangue(langue));
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}

}
