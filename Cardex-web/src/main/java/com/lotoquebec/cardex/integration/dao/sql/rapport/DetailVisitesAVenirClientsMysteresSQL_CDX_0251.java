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
public class DetailVisitesAVenirClientsMysteresSQL_CDX_0251{
	
	public static PreparerSQL construireSQL(StatistiqueDossierRapportVO cumulatifDossierRapportVO) {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder query = new StringBuilder();
		//Pour ce rapport, prend les détaillants qui font partie de l'échantillon et qui n'ont pas encore été visités
		query.append("SELECT s.v_so_reference_3 as NUMERO, d.d_do_date_debut as DATE_DE_VISITE, s.v_so_nom as NOM, decode(trca.l_tr_cle, 662, 'Pas de visite', trca.v_tr_description) as CATEGORIE, ");
		query.append("a.v_ad_num_municipal||', '||a.v_ad_nom_rue||' '||v.v_vi_ville as ADRESSE, s.v_so_district as REGION, ");
		query.append("nvl(d.v_do_ancienne_reference,d.v_do_reference1) as VAGUE ");
		query.append("FROM v_do_dossier_ca_ty d, ldd_lien_dossier l, so_societe s, tr_traduction trca, ad_adresse a, vi_ville v ");
		query.append(" where d.l_do_cle = l.l_do_cle ");
		query.append(" and d.l_si_cle = l.l_do_site ");
		query.append(" and l.c_do_genre = 'DO' ");
		query.append(" and l.l_ldd_dossier_associe = s.l_so_cle ");
		query.append(" and l.l_ldd_site = s.l_si_cle ");
		query.append(" and l.c_ldd_genre = 'SO' ");
		query.append(" and d.l_si_cle = " + GlobalConstants.Sites.CLIENTS_MYSTERES);
		query.append(" and d.i_cc_cle <> " + GlobalConstants.Confidentialite.HUIT);
		query.append(" and s.i_cc_cle <> " + GlobalConstants.Confidentialite.HUIT);
		query.append(" and s.b_so_actif = 'yes' ");
		query.append(" and d.i_ca_cle = trca.l_tr_cle and trca.i_la_cle = 1 and a.l_vi_cle = v.l_vi_cle(+) ");
		query.append(" and s.l_so_cle = a.l_ad_reference(+) and s.l_si_cle = a.l_ad_ref_site(+) and a.c_ad_ref_genre(+) = 'SO' ");
		query.append(" and a.i_st_cle(+) = "  + GlobalConstants.Adresse.RESIDENCE_PRINCIPALE);//résidence principale seulement
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d.i_ge_cle", cumulatifDossierRapportVO.getGenre());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d.i_na_cle", cumulatifDossierRapportVO.getNature());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d.i_ty_cle", cumulatifDossierRapportVO.getType());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d.i_ca_cle", cumulatifDossierRapportVO.getCategorie());
		//Détaillants de l'échantillon non encore visités
		query.append(" and exists  ");
		query.append(" (select l2.l_do_cle from cardex.v_do_dossier_ca_ty d2, cardex.ldd_lien_dossier l2 ");
		query.append(" where d2.l_do_cle = l2.l_do_cle and d2.l_si_cle = l2.l_do_site and l2.c_do_genre = 'DO' ");
        query.append(" and l2.l_ldd_dossier_associe = s.l_so_cle and l2.l_ldd_site = s.l_si_cle and l2.c_ldd_genre = 'SO' ");
		query.append(" and d2.i_cc_cle <> 14920 ");
        query.append(" and d2.i_ca_cle = " + GlobalConstants.CategorieClientMystere.ECHANTILLON);
        query.append(" and d2.i_st_cle = " + GlobalConstants.Statut.DOSSIER_ACTIF);
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "d2.v_do_ancienne_reference", cumulatifDossierRapportVO.getVague().toUpperCase());
		query.append(" and not exists (select * ");
		query.append(" from cardex.v_do_dossier_ca_ty d4, cardex.ldd_lien_dossier l4 ");
		query.append(" where d4.l_do_cle = l4.l_do_cle and d4.l_si_cle = l4.l_do_site and l4.c_do_genre = 'DO' ");
		query.append(" and l4.l_ldd_dossier_associe = s.l_so_cle and l4.l_ldd_site = s.l_si_cle and l4.c_ldd_genre = 'SO' ");
		query.append(" and d4.i_cc_cle <> 14920 ");
		query.append(" and d4.v_do_reference1 = d2.v_do_ancienne_reference and d4.i_ca_cle in (23667, 653, 654, 655, 657, 656, 24863))) ");
		query.append(" and d.d_do_date_debut = (select max(d3.d_do_date_debut) from cardex.v_do_dossier_ca_ty d3, cardex.ldd_lien_dossier l3 ");
		query.append(" where d3.l_do_cle = l3.l_do_cle and d3.l_si_cle = l3.l_do_site and l3.c_do_genre = 'DO' ");
		query.append(" and l3.l_ldd_dossier_associe = s.l_so_cle and l3.l_ldd_site = s.l_si_cle and l3.c_ldd_genre = 'SO' ");
		query.append(" and d3.i_cc_cle <> 14920 ");
		query.append(" and d3.i_ca_cle in (662, 27769, 23668, 23669, 23670, 23735)) ");
		
		preparerSQL.setSQL(query.toString());

		return preparerSQL;
	}  	
}
