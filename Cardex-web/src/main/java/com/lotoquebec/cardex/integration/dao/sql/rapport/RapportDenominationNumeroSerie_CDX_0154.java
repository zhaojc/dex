package com.lotoquebec.cardex.integration.dao.sql.rapport;

import com.lotoquebec.cardex.business.vo.rapport.ConsignationRapportVO;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;


public class RapportDenominationNumeroSerie_CDX_0154{
	
	public static PreparerSQL construireSQL(ConsignationRapportVO consignationRapportVO) {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder query = new StringBuilder();

		query.append("SELECT s.v_si_site as SITE, c.v_cn_numero_serie as NUMERO_SERIE, tde.v_tr_description as DEVISE, ");
		query.append("tdn.v_tr_description as DENOMINATION, nvl(C.l_Cn_Quantite,0) as NOMBRE, Abs(nvl(c.r_cn_montant,0)) as VALEUR_ABSOLUE, nvl(c.r_cn_montant,0) as MONTANT ");
		query.append("FROM CN_CONSIGNATION C, tr_traduction tdn, tr_traduction tde, si_site s ");
		query.append("where C.L_SI_CLE = ? ");
		preparerSQL.addParametre(consignationRapportVO.getSite());
		
		query.append("AND c.i_dn_cle = tdn.l_tr_cle and tdn.i_la_cle = 1 and c.i_de_cle = tde.l_tr_cle and tde.i_la_cle = 1 ");
		query.append("and c.l_si_cle = s.l_si_cle ");

		query.append("and trunc(C.D_CN_DATE_CREATION) BETWEEN ? and ? ");
		preparerSQL.addParametre(consignationRapportVO.getDateDebutDu());
		preparerSQL.addParametre(consignationRapportVO.getDateDebutAu());
		
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "c.v_cn_cree_par", consignationRapportVO.getIntervenant());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d.i_na_cle", consignationRapportVO.getNature());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d.i_ty_cle", consignationRapportVO.getType());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d.i_ca_cle", consignationRapportVO.getCategorie());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "C.I_TN_CLE", consignationRapportVO.getTypeConsignation());		
		
		query.append("order by tde.v_tr_description, tdn.v_tr_description, c.v_cn_numero_serie ");
		
		preparerSQL.setSQL(query.toString());
		
		return preparerSQL;
	} 
	
}


