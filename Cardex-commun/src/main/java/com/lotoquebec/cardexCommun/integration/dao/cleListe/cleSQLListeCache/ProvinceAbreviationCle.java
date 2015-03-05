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
public class ProvinceAbreviationCle extends CleSQLListeCache{

	public ProvinceAbreviationCle(CardexAuthenticationSubject subject, long colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), String.valueOf(colonneDiscriminantValeur));
	}
	
	public ProvinceAbreviationCle(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
	}
	
	public ProvinceAbreviationCle(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		SQL.append("select tr.l_tr_cle as "+ListeCacheSQL.CLE+", tr.v_tr_abreviation as "+ListeCacheSQL.DESCRIPTION+" ");
		SQL.append("from tr_traduction tr, pr_province pr ");
		SQL.append("where tr.l_tr_cle = pr.l_pr_cle ");
		SQL.append("and tr.i_la_cle = 1 ");
		SQL.append("and pr.i_pa_cle = ? ");
		preparerSQL.addParametre(Integer.valueOf(discriminantValeur));
		SQL.append(OracleDAOUtils.sqlOrderBy(ListeCacheSQL.DESCRIPTION));
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}

}
