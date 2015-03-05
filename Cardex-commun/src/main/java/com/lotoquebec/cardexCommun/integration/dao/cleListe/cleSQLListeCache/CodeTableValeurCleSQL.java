/*
 * Created on 12-May-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;



/**
 * @author levassc
 */
public class CodeTableValeurCleSQL extends CleSQLListeCache{

	public CodeTableValeurCleSQL(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.oracle.cleListe.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		SQL.append("select distinct t.v_tv_code as "+ListeCacheSQL.CLE+", t.v_tv_code as "+ListeCacheSQL.DESCRIPTION+" ");
		SQL.append("from tv_table_valeur t ");
		SQL.append("order by "+ListeCacheSQL.DESCRIPTION+" ");
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}
}
