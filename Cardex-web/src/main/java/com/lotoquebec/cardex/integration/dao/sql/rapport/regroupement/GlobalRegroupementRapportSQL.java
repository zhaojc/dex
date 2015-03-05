/*
 * Created on 24-Jan-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement;

import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

/**
 * Utilisé par CDX_0080 et CDX_0087
 * @author levassc
 */
public class GlobalRegroupementRapportSQL extends RegroupementRapportSQL {

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraSelect()
	 */
	protected String getExtraSelect() {
		return "";
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraFrom()
	 */
	protected String getExtraFrom() {
		return "";
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraWhere(int)
	 */
	protected String getExtraWhere(PreparerSQL preparerSQL, int codeLangue) {
		return "";
	}


}
