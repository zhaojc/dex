/*
 * Created on 27-Nov-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

/**
 * @author levassc
 */ 
public class ModeleMarqueCle extends CleSQLListeCache {

	/**
	 * @param langue
	 * @param colonneDiscriminantValeur
	 */
	public ModeleMarqueCle(String langue) {
		super(langue, "");
		this.discriminantValeurRequis = false;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		SQL.append("select md.i_md_cle || '/' || md.i_ma_cle as "+ListeCacheSQL.CLE+", ");
		SQL.append("md.v_md_modele || '/' || ma.v_ma_marque as "+ListeCacheSQL.DESCRIPTION+" ");
	    SQL.append("from md_modele md, ma_marque ma ");
	    SQL.append("where md.i_ma_cle = ma.i_ma_cle ");
	    SQL.append("order by "+ListeCacheSQL.DESCRIPTION+" ");
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}
	
}
