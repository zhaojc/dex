package com.lotoquebec.cardex.integration.dao.sql.rapport;

import com.lotoquebec.cardex.business.vo.rapport.ConsignationRapportVO;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

public class RapportSommaireConsignation_CDX_0150{
	
	public static PreparerSQL construireSQL(ConsignationRapportVO consignationRapportVO) {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder query = new StringBuilder();

		query.append("SELECT si.v_si_site as SITE, to_char(c.d_cn_date_creation, 'yyyy') || '-' || to_char(c.d_cn_date_creation, 'MM') as PERIODE, ");
		query.append("i.v_in_nom || ', ' || i.v_in_prenom as INTERVENANT, t.v_tr_description as TYPE_CONSIGNATION, C.l_Cn_Quantite as NOMBRE, abs(C.R_CN_MONTANT) as TOTAL ");
		query.append("FROM CN_CONSIGNATION C, tr_traduction t, si_site si, in_intervenant i, v_do_dossier_ca_ty d ");
		query.append("where C.L_SI_CLE = ? ");
		preparerSQL.addParametre(consignationRapportVO.getSite());		
		
		query.append("and c.i_tn_cle = t.l_tr_cle and t.i_la_cle = 1 ");
		query.append("and c.l_si_cle = si.l_si_cle and c.v_cn_cree_par = i.name ");
		query.append("and c.l_cn_ref_cle = d.l_do_cle and c.l_cn_ref_site = d.l_si_cle ");

		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "i.name", consignationRapportVO.getIntervenant());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d.i_na_cle", consignationRapportVO.getNature());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d.i_ty_cle", consignationRapportVO.getType());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d.i_ca_cle", consignationRapportVO.getCategorie());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "C.I_TN_CLE", consignationRapportVO.getTypeConsignation());		
		
		query.append("and trunc(C.D_CN_DATE_CREATION) BETWEEN ? and ? ");
		preparerSQL.addParametre(consignationRapportVO.getDateDebutDu());
		preparerSQL.addParametre(consignationRapportVO.getDateDebutAu());		
		
		preparerSQL.setSQL(query.toString());
		
		return preparerSQL;
	} 
	
}


