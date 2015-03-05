package com.lotoquebec.cardex.integration.dao.sql.recherche.galerie;

import com.lotoquebec.cardex.integration.dao.PhotoDAO;


public class CompletDossierGalerieConstruireRechercheSQL extends DossierGalerieConstruireRechercheSQL{

	protected String selectArgument(){
        StringBuffer query = new StringBuffer();
        query.append(" distinct mm.v_mm_extension, lm.l_lmm_cle, lm.l_si_cle, lm.l_lmm_reference, ");
        query.append(" lm.l_lmm_ref_site, lm.c_lmm_ref_genre, lm.l_mm_cle, ");
        query.append(" lm.l_mm_ref_site, lm.v_lmm_cree_par, ");
        query.append(" lm.d_lmm_date_creation, lm.v_lmm_modifie_par, ");
        query.append(" lm.d_lmm_date_modification, mm.l_em_cle, mm.i_tm_cle, ");
        query.append(" mm.v_mm_description, mm.v_mm_cree_par, mm.d_mm_date_creation, ");
        query.append(" mm.l_em_si_cle, do.V_DO_NUMERO_DOSSIER as \"reference\", ");
        query.append(" do.i_se_cle as \"severite\", ");
        query.append(" do.D_DO_DATE_DEBUT as \""+PhotoDAO.DATE_DEBUT_DOSSIER+"\", ");
        query.append(" do.D_DO_DATE_FIN as \""+PhotoDAO.DATE_FIN_DOSSIER+"\" ");
/*        query.append(" do.i_se_cle as \"severite\", do.v_do_ancienne_reference as \"dossier\", ");
        query.append(" do.l_do_cle, do.l_si_cle as \"SITE_DOSSIER\", ");
        query.append(" do.D_DO_DATE_DEBUT as \""+PhotoDAO.DATE_DEBUT_DOSSIER+"\", ");
        query.append(" do.D_DO_DATE_FIN as \""+PhotoDAO.DATE_FIN_DOSSIER+"\", ");
        query.append(" do.i_ge_cle as \""+PhotoDAO.GENRE_DOSSIER+"\", ");
        query.append(" do.i_na_cle as \""+PhotoDAO.NATURE_DOSSIER+"\", ");
        query.append(" do.i_cc_cle as \""+PhotoDAO.CONFIDENTIALITE_DOSSIER+"\" ");
*/
		return query.toString();
	}
		
}
