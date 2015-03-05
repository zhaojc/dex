/*
 * Created on 24-Jan-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement;

import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

/**
 * @author levassc
 */
public class IntervenantEndroitRegroupementRapportSQL_CDX_0085 extends
		RegroupementRapportSQL {

	public final static String CLE_INTERVENANT = "cleIntervenant";
	public final static String NOM_INTERVENANT = "nomIntervenant";
	public final static String PRENOM_INTERVENANT = "prenomIntervenant";
	public final static String ENDROIT = "endroit";
	
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraSelect()
	 */
	protected String getExtraSelect() {
		String SQL = "";
		SQL += ", int.l_in_cle as "+CLE_INTERVENANT+" ";
		SQL += ", int.v_in_nom as "+NOM_INTERVENANT+" ";
		SQL += ", int.v_in_prenom as "+PRENOM_INTERVENANT+" ";
		SQL += ", en.v_tr_description as "+ENDROIT+" ";
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
		SQL += ", en.v_tr_description ";
		return SQL;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraFrom()
	 */
	protected String getExtraFrom() {
		String SQL = "";
		SQL += ", tr_traduction en ";
		return SQL;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraWhere(int)
	 */
	protected String getExtraWhere(PreparerSQL preparerSQL, int codeLangue) {
		String SQL = "";
		SQL += "and do.i_or_cle = en.l_tr_cle ";
		SQL += "and en.i_la_cle = ? ";
		preparerSQL.addParametre(codeLangue);
		return SQL;
	}

}
