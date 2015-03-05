/*
 * Created on 24-Jan-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement;

import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

/**
 * Utiliser CDX_0082 et CDX_0083
 * @author levassc
 */
public class SecteurRegroupementRapportSQL extends RegroupementRapportSQL {

	public final static String TYPE = "type";
	public final static String CATEGORIE = "categorie";
	
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraSelect()
	 */
	protected String getExtraSelect() {
		String SQL = "";
		SQL += ", tyDesc.v_tr_description as "+TYPE+" ";
		SQL += ", caDesc.v_tr_description as "+CATEGORIE+" ";
		return SQL;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraGroupeBy()
	 */
	protected String getGroupeBy() {
		String SQL = super.getGroupeBy();
		SQL += ", tyDesc.v_tr_description ";
		SQL += ", caDesc.v_tr_description ";
		return SQL;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraFrom()
	 */
	protected String getExtraFrom() {
		String SQL = "";
		SQL += ", tr_traduction tyDesc ";
		SQL += ", tr_traduction caDesc ";
		return SQL;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraWhere(int)
	 */
	protected String getExtraWhere(PreparerSQL preparerSQL, int codeLangue) {
		String SQL = "";
		SQL += "and ca.i_ty_cle = tyDesc.l_tr_cle ";
		SQL += "and tyDesc.i_la_cle = ? ";
		SQL += "and ca.i_ca_cle = caDesc.l_tr_cle ";
		SQL += "and caDesc.i_la_cle = ? ";
		preparerSQL.addParametre(codeLangue);
		preparerSQL.addParametre(codeLangue);
		return SQL;
	}

}
