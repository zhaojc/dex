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
public class SousCategoriesNatureCle extends CleSQLListeCache{

	public SousCategoriesNatureCle(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
	}

	public SousCategoriesNatureCle(CardexAuthenticationSubject subject, String colonneDiscriminantValeur) {
		super(subject.getLocale().getLanguage(), colonneDiscriminantValeur);
	}
	
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		SQL.append("select ca.i_ca_cle as "+ListeCacheSQL.CLE+", try.v_tr_description ||'/'|| tra.v_tr_description as "+ListeCacheSQL.DESCRIPTION+" ");
		SQL.append("from ty_type ty, tr_traduction try, ca_categorie ca, tr_traduction tra ");
		SQL.append("where ty.i_ty_cle = try.l_tr_cle ");
		SQL.append("and ca.i_ca_cle = tra.l_tr_cle ");
		SQL.append("and ty.i_ty_cle = ca.i_ty_cle ");
		SQL.append("and try.i_la_cle = tra.i_la_cle ");
		SQL.append("and try.i_la_cle = ? ");
		preparerSQL.addParametre(OracleDAOUtils.getLongLangue(langue));
		SQL.append("and ty.i_na_cle = ? ");
		preparerSQL.addParametre(Integer.valueOf( discriminantValeur ));
		SQL.append(OracleDAOUtils.sqlOrderBy(ListeCacheSQL.DESCRIPTION));
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}

}
