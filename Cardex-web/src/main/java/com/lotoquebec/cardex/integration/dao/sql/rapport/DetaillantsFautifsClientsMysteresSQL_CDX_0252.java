package com.lotoquebec.cardex.integration.dao.sql.rapport;

import com.lotoQuebec.correcteurAdresse.util.StringUtils;
import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;

/**
 * Ce rapport ne devrait pas utilisez la liaison d4.v_do_reference1 = d2.v_do_ancienne_reference
 * I14-1227 : Rapport de cohérence - Client mystère - erreur dans la validation du numéro de vague
 * @author levassc
 *
 */
public class DetaillantsFautifsClientsMysteresSQL_CDX_0252{
	
	public static PreparerSQL construireSQL(StatistiqueDossierRapportVO cumulatifDossierRapportVO) {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder query = new StringBuilder();

		query.append("SELECT  s.v_so_reference_3 as NUMERO, d.d_do_date_debut as DATE_DE_VISITE, s.v_so_nom as NOM, trca.v_tr_description as CATEGORIE, ");
		query.append("a.v_ad_num_municipal||', '||a.v_ad_nom_rue||' '||v.v_vi_ville as ADRESSE, s.v_so_district as REGION, ");
		query.append("d.v_do_reference1 as VAGUE ");
		query.append("FROM v_do_dossier_ca_ty d, ldd_lien_dossier l, so_societe s, tr_traduction trca, ad_adresse a, vi_ville v ");
		query.append("where d.l_do_cle = l.l_do_cle and d.l_si_cle = l.l_do_site and l.c_do_genre = 'DO' ");
		query.append("and l.l_ldd_dossier_associe = s.l_so_cle and l.l_ldd_site = s.l_si_cle and l.c_ldd_genre = 'SO' ");
		query.append("and d.l_si_cle = " + GlobalConstants.Sites.CLIENTS_MYSTERES);
		query.append(" and d.i_cc_cle <> " + GlobalConstants.Confidentialite.HUIT);
		query.append(" and s.i_cc_cle <> " + GlobalConstants.Confidentialite.HUIT);
		query.append(" and d.i_ca_cle = trca.l_tr_cle and trca.i_la_cle = 1 and a.l_vi_cle = v.l_vi_cle(+) ");
		query.append(" and s.l_so_cle = a.l_ad_reference(+) and s.l_si_cle = a.l_ad_ref_site(+) and a.c_ad_ref_genre(+) = 'SO' ");
		query.append(" and d.i_ca_cle in (" + GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1 + ", " + 
				 GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2 + ", " +
				 GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3 + ", " +
				 GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_4 + ", " +
				 GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_5 + ") "); //On prend les catégories d'infraction.
		query.append(" and a.i_st_cle(+) = "  + GlobalConstants.Adresse.RESIDENCE_PRINCIPALE);//résidence principale seulement
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d.i_ge_cle", cumulatifDossierRapportVO.getGenre());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d.i_na_cle", cumulatifDossierRapportVO.getNature());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d.i_ty_cle", cumulatifDossierRapportVO.getType());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d.i_ca_cle", cumulatifDossierRapportVO.getCategorie());
		OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, query, "s.v_so_reference_1", cumulatifDossierRapportVO.getRegion());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d.v_do_reference1", cumulatifDossierRapportVO.getVague().toUpperCase());
		//Si un numéro de vague est inscrit, on ne tient pas compte des dates
		if(StringUtils.isEmpty(cumulatifDossierRapportVO.getVague())){
			query.append("and trunc(d.d_do_date_debut) BETWEEN ? and ? ");
			preparerSQL.addParametre(cumulatifDossierRapportVO.getDateDebutDu());
			preparerSQL.addParametre(cumulatifDossierRapportVO.getDateDebutAu());
		}

		query.append(" and exists (select s2.l_so_cle from do_dossier d2, so_societe s2, ldd_lien_dossier l2 ");
		query.append("where d2.i_ca_cle = " + GlobalConstants.CategorieClientMystere.ECHANTILLON);
		query.append(" and s2.l_so_cle = s.l_so_cle and s2.l_si_cle = s.l_si_cle ");
		query.append(" and d2.l_do_cle = l2.l_do_cle and d2.l_si_cle = l2.l_do_site and l2.c_do_genre = 'DO' ");
		query.append(" and l2.l_ldd_dossier_associe = s2.l_so_cle and l2.l_ldd_site = s2.l_si_cle and l2.c_ldd_genre = 'SO' ");
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d2.v_do_ancienne_reference", cumulatifDossierRapportVO.getVague().toUpperCase());
		if(StringUtils.isEmpty(cumulatifDossierRapportVO.getVague())){
			query.append(" and trunc(d2.d_do_date_debut) BETWEEN ? and ? ");
			preparerSQL.addParametre(cumulatifDossierRapportVO.getDateDebutDu());
			preparerSQL.addParametre(cumulatifDossierRapportVO.getDateDebutAu());
		}
		query.append(" and d2.v_do_ancienne_reference = d.v_do_reference1) ");
		preparerSQL.setSQL(query.toString());
		
		return preparerSQL;
	} 

}
