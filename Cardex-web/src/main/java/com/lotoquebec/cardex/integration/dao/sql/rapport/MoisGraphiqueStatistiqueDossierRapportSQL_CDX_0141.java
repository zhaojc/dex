package com.lotoquebec.cardex.integration.dao.sql.rapport;

import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;


public class MoisGraphiqueStatistiqueDossierRapportSQL_CDX_0141{
	
	public static PreparerSQL construireSQL(StatistiqueDossierRapportVO statistiqueDossierRapportVO) {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder query = new StringBuilder();

		query.append("SELECT  to_char(v.d_do_date_debut, 'YYYY-MM') as PERIODE, tge.v_tr_description as GENRE, ");
		query.append("tna.v_tr_description as NATURE, count(v.l_do_cle) as TOTAL ");
		query.append("FROM v_do_dossier_ca_ty v, tr_traduction tge, tr_traduction tna ");
		query.append("where v.l_si_cle = ? ");
		preparerSQL.addParametre(statistiqueDossierRapportVO.getSite());
		
		query.append("and v.i_na_cle = tna.l_tr_cle and tna.i_la_cle = 1 ");
		query.append("and v.i_ge_cle = tge.l_tr_cle and tge.i_la_cle = 1 ");
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "v.i_na_cle", statistiqueDossierRapportVO.getNature());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "v.i_do_fonde", statistiqueDossierRapportVO.getFonde());		
		
		query.append("and v.d_do_date_debut BETWEEN ? and ? ");
		preparerSQL.addParametre(statistiqueDossierRapportVO.getDateDebutDu());
		preparerSQL.addParametre(statistiqueDossierRapportVO.getDateDebutAu());
		
		query.append("GROUP BY to_char(v.d_do_date_debut, 'YYYY-MM'), tge.v_tr_description , tna.v_tr_description ");
		query.append("order by to_char(v.d_do_date_debut, 'YYYY-MM'), tge.v_tr_description , tna.v_tr_description ");
		
		preparerSQL.setSQL(query.toString());
		
		return preparerSQL;
	} 
	
}
