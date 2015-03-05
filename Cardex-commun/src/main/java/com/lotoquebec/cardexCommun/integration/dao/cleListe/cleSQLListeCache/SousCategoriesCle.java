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
public class SousCategoriesCle extends CleSQLListeCache{

	public SousCategoriesCle(String langue, String colonneDiscriminantValeur) {
		super(langue, colonneDiscriminantValeur);
	}

	public SousCategoriesCle(CardexAuthenticationSubject subject) {
		super(subject.getLocale().getLanguage(), "");
	}
	
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardexCommun.integration.dao.cleListe.CleSQLListeCache#getSQL()
	 */
	public PreparerSQL getPreparerSQL() {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder SQL = new StringBuilder();
		SQL.append("select ca.i_ca_cle as "+ListeCacheSQL.CLE+", trg.v_tr_description ||'/'|| trn.v_tr_description ||'/'|| trt.v_tr_description ||'/'|| trc.v_tr_description as "+ListeCacheSQL.DESCRIPTION+" ");
		SQL.append("from ge_genre ge, tr_traduction trg, na_nature na, tr_traduction trn, ty_type ty, tr_traduction trt, ca_categorie ca, tr_traduction trc ");
		SQL.append("where ");
		SQL.append("ge.i_ge_cle = trg.l_tr_cle ");
		SQL.append("and trg.i_la_cle = ? ");
		preparerSQL.addParametre(OracleDAOUtils.getLongLangue(langue));

		SQL.append("and ge.i_ge_cle = na.i_ge_cle ");
		SQL.append("and na.i_na_cle = trn.l_tr_cle ");
		SQL.append("and trn.i_la_cle = ? ");
		preparerSQL.addParametre(OracleDAOUtils.getLongLangue(langue));
		
		SQL.append("and ty.i_na_cle = na.i_na_cle ");
		SQL.append("and ty.i_ty_cle = trt.l_tr_cle ");
		SQL.append("and trt.i_la_cle = ? ");
		preparerSQL.addParametre(OracleDAOUtils.getLongLangue(langue));
		
		SQL.append("and ca.i_ty_cle = ty.i_ty_cle ");
		SQL.append("and ca.i_ca_cle = trc.l_tr_cle ");
		SQL.append("and trc.i_la_cle = ? ");
		preparerSQL.addParametre(OracleDAOUtils.getLongLangue(langue));
		
		SQL.append(OracleDAOUtils.sqlOrderBy(ListeCacheSQL.DESCRIPTION));
		preparerSQL.setSQL(SQL.toString());
		
		return preparerSQL;
	}

}
