/*
 * Created on 24-Jan-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement;

import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

/**
 * @author levassc
 */
public class IntervenantRegroupementRapportSQL_CDX_0086 extends
		RegroupementRapportSQL {

	public final static String CLE_INTERVENANT = "cleIntervenant";
	public final static String NOM_INTERVENANT = "nomIntervenant";
	public final static String PRENOM_INTERVENANT = "prenomIntervenant";
	public final static String MATRICULE = "matricule";
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraSelect()
	 */
	protected String getExtraSelect() {
		String SQL = "";
		SQL += ", int.l_in_cle as "+CLE_INTERVENANT+" ";
		SQL += ", int.v_in_nom as "+NOM_INTERVENANT+" ";
		SQL += ", int.v_in_prenom as "+PRENOM_INTERVENANT+" ";
		SQL += ", int.v_in_no_employe as "+MATRICULE+" ";
		return SQL;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraGroupeBy()
	 */
	protected String getGroupeBy() {
		String SQL = super.getGroupeBy();
		SQL += ", int.l_in_cle ";
		SQL += ", int.v_in_nom ";
		SQL += ", int.v_in_prenom ";
		SQL += ", int.v_in_no_employe ";
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
