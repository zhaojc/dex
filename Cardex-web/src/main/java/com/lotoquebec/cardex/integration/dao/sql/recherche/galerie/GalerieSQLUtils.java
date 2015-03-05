package com.lotoquebec.cardex.integration.dao.sql.recherche.galerie;

import com.lotoquebec.cardex.integration.dao.PhotoDAO;

public class GalerieSQLUtils {

	static String selectCountArgument(){
		return "count(distinct mm.l_em_cle) ";
	}
	
	static String selectSujetArgument(){
		StringBuffer query = new StringBuffer();
		query.append("distinct mm.v_mm_extension, lm.l_lmm_cle, lm.l_si_cle, lm.l_lmm_reference, ");
		query.append(" lm.l_lmm_ref_site, lm.c_lmm_ref_genre, lm.l_mm_cle, ");
		query.append(" lm.l_mm_ref_site, lm.v_lmm_cree_par, ");
		query.append(" lm.d_lmm_date_creation, lm.v_lmm_modifie_par, ");
		query.append(" lm.d_lmm_date_modification, mm.l_em_cle, mm.i_tm_cle, ");
		query.append(" mm.v_mm_description, mm.v_mm_cree_par, mm.d_mm_date_creation, ");
		query.append(" mm.l_em_si_cle, s.v_su_reference_3 as \"reference\", ");
		query.append(" s.i_se_cle_autres as \"severite\", ");
		query.append(" max(do.D_DO_DATE_DEBUT) as \""+PhotoDAO.DATE_DEBUT_DOSSIER+"\", ");
		query.append(" max(do.D_DO_DATE_FIN) as \""+PhotoDAO.DATE_FIN_DOSSIER+"\" ");
		 
		return query.toString();
	}
	
	static String groupBySujet() {
		StringBuilder query = new StringBuilder();
		
		 query.append(" group by mm.v_mm_extension, lm.l_lmm_cle, lm.l_si_cle, lm.l_lmm_reference, ");
         query.append(" lm.l_lmm_ref_site, lm.c_lmm_ref_genre, lm.l_mm_cle, ");
         query.append(" lm.l_mm_ref_site, lm.v_lmm_cree_par, ");
         query.append(" lm.d_lmm_date_creation, lm.v_lmm_modifie_par, ");
         query.append(" lm.d_lmm_date_modification, mm.l_em_cle, mm.i_tm_cle, ");
         query.append(" mm.v_mm_description, mm.v_mm_cree_par, mm.d_mm_date_creation, ");
         query.append(" mm.l_em_si_cle, s.v_su_reference_3, ");
         query.append(" s.i_se_cle_autres ");
		
		return query.toString();
	}
}
