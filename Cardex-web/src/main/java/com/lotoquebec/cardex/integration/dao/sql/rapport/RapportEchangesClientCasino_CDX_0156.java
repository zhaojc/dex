package com.lotoquebec.cardex.integration.dao.sql.rapport;

import com.lotoquebec.cardex.business.vo.rapport.ConsignationRapportVO;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

//Rapport ce consignation sur les échanges entre les casinos et les clients
public class RapportEchangesClientCasino_CDX_0156{
	
	public static PreparerSQL construireSQL(ConsignationRapportVO consignationRapportVO) {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder query = new StringBuilder();

		query.append("select s.v_si_site Site, t.v_tr_description Type, sum(c.r_cn_montant) Total from cardex.cn_consignation c, ");
		query.append("cardex.si_site s, cardex.tr_traduction t ");
		query.append("where c.i_tn_cle = t.l_tr_cle and t.i_la_cle = 1 ");
		query.append("and c.l_si_cle = s.l_si_cle ");
		
		if(consignationRapportVO.getSite() != 0){
			OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "C.L_SI_CLE", consignationRapportVO.getSite());
		}

		query.append(" and trunc(C.D_CN_DATE_CREATION) BETWEEN ? and ? ");
		preparerSQL.addParametre(consignationRapportVO.getDateDebutDu());
		preparerSQL.addParametre(consignationRapportVO.getDateDebutAu());
		
		if(consignationRapportVO.getTypeConsignation() != 0){
			OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "C.I_TN_CLE", consignationRapportVO.getTypeConsignation());
		}else{		
			query.append(" and c.i_tn_cle in (25616,24924,25617,24923) ");
		}
		
		query.append(" group by s.v_si_site, t.v_tr_description ");
		
		preparerSQL.setSQL(query.toString());
		
		return preparerSQL;
	} 
	
}


