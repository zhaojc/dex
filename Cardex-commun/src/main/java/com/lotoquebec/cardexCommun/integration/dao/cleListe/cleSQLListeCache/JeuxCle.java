/*
 * Created on 27-Nov-2008
 */
package com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache;

import com.lotoquebec.cardexCommun.integration.dao.ListeCacheSQL;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

/**
 * @author levassc
 */ 
public class JeuxCle extends CleSQLListeCache {

	/**
	 * @param langue
	 * @param colonneDiscriminantValeur
	 */
	public JeuxCle(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
		this.discriminantValeurRequis = true;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		SQL.append("select jen.l_lje_cle as "+ListeCacheSQL.CLE+", t.v_tr_description as "+ListeCacheSQL.DESCRIPTION+" ");
		SQL.append("from tr_traduction t, je_jeu je, lje_jeu_entite jen ");
		SQL.append("where je.l_je_cle = t.l_tr_cle ");
		SQL.append("and jen.l_je_cle = je.l_je_cle ");
		SQL.append("and T.I_LA_CLE = ? ");
		preparerSQL.addParametre(OracleDAOUtils.getLongLangue(langue));
		SQL.append("and jen.i_en_cle = ? ");
		preparerSQL.addParametre(Integer.valueOf(discriminantValeur));
		SQL.append("order by "+ListeCacheSQL.DESCRIPTION+" ");
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}
	
}
