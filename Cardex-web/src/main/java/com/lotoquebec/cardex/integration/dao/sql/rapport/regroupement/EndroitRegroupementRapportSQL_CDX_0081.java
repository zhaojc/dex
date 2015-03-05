/*
 * Created on 24-Jan-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement;

import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

/**
 * @author levassc
 */
public class EndroitRegroupementRapportSQL_CDX_0081 extends RegroupementRapportSQL {

	public final static String ENDROIT = "endroit";
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraSelect()
	 */
	protected String getExtraSelect() {
		return ", en.v_tr_description as "+ENDROIT+" ";
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraGroupeBy()
	 */
	protected String getGroupeBy() {
		return super.getGroupeBy()+", en.v_tr_description ";
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraFrom()
	 */
	protected String getExtraFrom() {
		return ", tr_traduction en ";
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraWhere()
	 */
	protected String getExtraWhere(PreparerSQL preparerSQL, int codeLangue) {
		String SQL = "";
		SQL += "and do.i_or_cle = en.l_tr_cle ";
		SQL += "and en.i_la_cle = ? ";
		preparerSQL.addParametre(codeLangue);
		return SQL;
	}

}
