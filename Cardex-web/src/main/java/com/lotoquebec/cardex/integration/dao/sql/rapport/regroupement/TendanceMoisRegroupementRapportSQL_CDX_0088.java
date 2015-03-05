/*
 * Created on 24-Jan-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement;

import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

/**
 * @author levassc
 */
public class TendanceMoisRegroupementRapportSQL_CDX_0088 extends RegroupementRapportSQL {

	public final static String MOIS_NOMBRE = "Mois_Nombre";
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraSelect()
	 */
	protected String getExtraSelect() {
		String SQL = "";
		SQL += ", to_char(com.d_co_date_creation,'YYYY-MM') as "+MOIS_NOMBRE+" ";
		return SQL;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraGroupeBy()
	 */
	protected String getGroupeBy() {
		String SQL = "";
		SQL += "to_char(com.d_co_date_creation,'YYYY-MM'), ";
		SQL += super.getGroupeBy();
		return SQL;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraFrom()
	 */
	protected String getExtraFrom() {
		String SQL = "";
		return SQL;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraWhere(int)
	 */
	protected String getExtraWhere(PreparerSQL preparerSQL, int codeLangue) {
		return "";
	}


}
