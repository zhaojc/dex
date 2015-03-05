package com.lotoquebec.cardex.integration.dao.sql.rapport;

import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;


public class CategorieStatistiqueDossierRapportSQL_CDX_0140{
	
	public static PreparerSQL construireSQL(StatistiqueDossierRapportVO cumulatifDossierRapportVO) {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder query = new StringBuilder();

		query.append("SELECT  tna.v_tr_description as NATURE, tty.v_tr_description as TYPE, tca.v_tr_description as CATEGORIE, count(v.l_do_cle) as TOTAL ");
		query.append("FROM v_do_dossier_ca_ty v, tr_traduction tna, tr_traduction tty, tr_traduction tca ");
		query.append("where v.l_si_cle = ? ");
		preparerSQL.addParametre(cumulatifDossierRapportVO.getSite());		
		
		query.append("and v.i_na_cle = tna.l_tr_cle and tna.i_la_cle = 1 ");
		query.append("and v.i_ty_cle = tty.l_tr_cle and tty.i_la_cle = 1 ");
		query.append("and v.i_ca_cle = tca.l_tr_cle and tca.i_la_cle = 1 ");
		
		query.append("and v.i_cc_cle <> 14920 ");

		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "v.i_na_cle", cumulatifDossierRapportVO.getNature());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "v.i_ty_cle", cumulatifDossierRapportVO.getType());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "v.i_do_fonde", cumulatifDossierRapportVO.getFonde());
		
		query.append("and v.d_do_date_debut BETWEEN ? and ? ");
		preparerSQL.addParametre(cumulatifDossierRapportVO.getDateDebutDu());
		preparerSQL.addParametre(cumulatifDossierRapportVO.getDateDebutAu());
		
		query.append("GROUP BY tna.v_tr_description, tty.v_tr_description, tca.v_tr_description ");
		
		preparerSQL.setSQL(query.toString());
		
		return preparerSQL;
	} 
	
}
