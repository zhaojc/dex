/*
 * Created on 24-Jan-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement;

import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

/**
 * @author levassc
 */
public class IntervenantCategorieRegroupementRapportSQL_CDX_0084 extends
		RegroupementRapportSQL {

	public final static String CLE_INTERVENANT = "cleIntervenant";
	public final static String NOM_INTERVENANT = "nomIntervenant";
	public final static String PRENOM_INTERVENANT = "prenomIntervenant";
	public final static String TYPE = "type";
	public final static String CATEGORIE = "categorie";
	
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.integration.dao.sql.RapportRegroupementSQL#getExtraSelect()
	 */
	protected String getExtraSelect() {
		String SQL = "";
		SQL += ", int.l_in_cle as "+CLE_INTERVENANT+" ";
		SQL += ", int.v_in_nom as "+NOM_INTERVENANT+" ";
		SQL += ", int.v_in_prenom as "+PRENOM_INTERVENANT+" ";
		SQL += ", tyDesc.v_tr_description as "+TYPE+" ";
		SQL += ", caDesc.v_tr_description as "+CATEGORIE+" ";
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
