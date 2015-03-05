/*
 * Created on 18-Apr-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.recherche;


/**
 * @author levassc
 */
public class AdresseSocieteCompletSQL extends AdresseSocieteSQL{

	protected String selectArgument(){
        StringBuffer query = new StringBuffer();
		
		query.append("so.l_so_cle as "+CLE_SOCIETE+", so.l_si_cle as "+SITE_SOCIETE+", so.v_so_reference_3 as "+NUMERO_FICHE+", ");
		query.append("so.i_se_cle as "+SEVERITE+", so.v_so_raison_sociale as "+RAISON_SOCIALE+", so.i_cl_cle as "+CLASSE+", ");
		query.append("so.v_so_nom as "+NOM_SOCIETE+", so.v_so_reference_nom as "+NOM_REFERENCE+", so.v_so_reference_prenom as "+PRENOM_REFERENCE+" ");

		return query.toString();
	}
	
}
