package com.lotoquebec.cardex.integration.dao.sql.recherche.galerie;


public abstract class SujetGalerieConstruireRechercheSQL extends GalerieConstruireRechercheSQL{

	public String whereLiaisonPhoto(){
		StringBuilder query = new StringBuilder();
		
        query.append(" mm.l_mm_cle = lm.l_mm_cle ");
        query.append(" and mm.l_si_cle = lm.l_mm_ref_site ");
        query.append(" and mm.I_TM_CLE < 18826 ");
        query.append(" and s.l_su_cle = lm.l_lmm_reference ");
        query.append(" and s.l_si_cle = lm.l_lmm_ref_site ");
        query.append(" and 'SU' = lm.c_lmm_ref_genre ");
        //Pour les sujets, on n'affiche seulement la photo sélectionné
        query.append(" and mm.b_mm_selectionner = 'yes' ");
        
        return query.toString();
	}
		
}
