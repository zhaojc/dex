/*
 * Created on 18-Apr-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.recherche;


/**
 * @author levassc
 */
public class AdresseSujetCompletSQL extends AdresseSujetSQL{

	protected String selectArgument(){
		StringBuffer query = new StringBuffer();
		query.append("su.l_su_cle as "+CLE_SUJET+", su.l_si_cle as "+SITE_SUJET+", su.v_su_reference_3 as "+NUMERO_FICHE+", ");
		query.append("su.i_se_cle as "+SEVERITE+", su.v_su_nom as "+NOM+", su.v_su_prenom as "+PRENOM+", ");
		query.append("su.v_su_surnom as "+ALIAS+", su.i_sx_cle as "+SEXE+", su.i_nt_cle as "+ETHNIE+", ");
		query.append("su.i_ra_cle as "+RACE+", su.i_ls_cle as "+LANGUE+", su.d_su_date_naissance as "+DATE_NAISSANCE+" ");
        return query.toString();
	}
	
}
