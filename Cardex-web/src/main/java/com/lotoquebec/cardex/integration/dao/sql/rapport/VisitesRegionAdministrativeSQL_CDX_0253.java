package com.lotoquebec.cardex.integration.dao.sql.rapport;

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
public class VisitesRegionAdministrativeSQL_CDX_0253{
	
	public static PreparerSQL construireSQL(StatistiqueDossierRapportVO cumulatifDossierRapportVO) {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder query = new StringBuilder();

		query.append("SELECT '1ère visite - Formation' as DESCRIPTION, ");
		query.append("trca.v_tr_description as CATEGORIE, s.v_so_reference_1 as REGION, s.v_so_reference_3 ");
		query.append("FROM v_do_dossier_ca_ty d, ldd_lien_dossier l, so_societe s, tr_traduction trca ");
		query.append("where d.l_do_cle = l.l_do_cle and d.l_si_cle = l.l_do_site and l.c_do_genre = 'DO' ");
		query.append("and l.l_ldd_dossier_associe = s.l_so_cle and l.l_ldd_site = s.l_si_cle and l.c_ldd_genre = 'SO' ");
		query.append("and d.i_ge_entite = 3 and d.i_ca_cle = trca.l_tr_cle and trca.i_la_cle = 1 ");
		query.append("and d.i_cc_cle <> 14920 and s.i_cc_cle <> 14920 and d.i_ca_cle <> 662 AND d.i_ge_cle = 16500 and d.i_ca_cle in (23667, 23668) ");
		query.append("and exists (select d2.d_do_date_debut ");
		query.append("from do_dossier d2, so_societe s2, ldd_lien_dossier l2 where d2.i_ca_cle = 662 ");
		query.append("and s2.l_so_cle = s.l_so_cle and s2.l_si_cle = s.l_si_cle and d2.l_do_cle = l2.l_do_cle ");
		query.append("and d2.l_si_cle = l2.l_do_site and l2.c_do_genre = 'DO' and l2.l_ldd_dossier_associe = s2.l_so_cle ");
		query.append("and l2.l_ldd_site = s2.l_si_cle and l2.c_ldd_genre = 'SO' ");
		query.append("and d2.v_do_ancienne_reference = ? ");
		preparerSQL.addParametre(cumulatifDossierRapportVO.getVague().toUpperCase());
		query.append("and d.v_do_reference1 = d2.v_do_ancienne_reference ");
		query.append("and d2.i_st_cle = 359) ");
		query.append("union ");
		query.append("SELECT '2e visite - Avis formel' as DESCRIPTION, trca.v_tr_description as CATEGORIE, s.v_so_reference_1 as REGION, s.v_so_reference_3 ");
		query.append("FROM v_do_dossier_ca_ty d, ldd_lien_dossier l, so_societe s, tr_traduction trca ");
		query.append("where d.l_do_cle = l.l_do_cle and d.l_si_cle = l.l_do_site and l.c_do_genre = 'DO' ");
		query.append("and l.l_ldd_dossier_associe = s.l_so_cle and l.l_ldd_site = s.l_si_cle and l.c_ldd_genre = 'SO' ");
		query.append("and d.i_ge_entite = 3 and d.i_ca_cle = trca.l_tr_cle and trca.i_la_cle = 1 and d.i_ca_cle <> 662 ");
		query.append("and d.i_cc_cle <> 14920 and s.i_cc_cle <> 14920 and d.i_ge_cle = 16500 and d.i_ca_cle in (23669, 653) and exists ");
		query.append("(select d2.d_do_date_debut ");
		query.append("from do_dossier d2, so_societe s2, ldd_lien_dossier l2 ");
		query.append("where d2.i_ca_cle = 662 and s2.l_so_cle = s.l_so_cle and s2.l_si_cle = s.l_si_cle ");
		query.append("and d2.l_do_cle = l2.l_do_cle and d2.l_si_cle = l2.l_do_site and l2.c_do_genre = 'DO' ");
		query.append("and l2.l_ldd_dossier_associe = s2.l_so_cle and l2.l_ldd_site = s2.l_si_cle and l2.c_ldd_genre = 'SO' ");
		query.append("and d2.v_do_ancienne_reference = ? ");
		preparerSQL.addParametre(cumulatifDossierRapportVO.getVague().toUpperCase());
		query.append("and d2.i_st_cle = 359 ");           
		query.append("and d.v_do_reference1 = d2.v_do_ancienne_reference) ");
		query.append("union ");
		query.append("SELECT '3e visite - Suspension 15 jours' as DESCRIPTION, trca.v_tr_description as CATEGORIE, s.v_so_reference_1 as REGION, s.v_so_reference_3 ");
		query.append("FROM v_do_dossier_ca_ty d, ldd_lien_dossier l, so_societe s, tr_traduction trca ");
		query.append("where d.l_do_cle = l.l_do_cle and d.l_si_cle = l.l_do_site and l.c_do_genre = 'DO' ");
		query.append("and l.l_ldd_dossier_associe = s.l_so_cle and l.l_ldd_site = s.l_si_cle and l.c_ldd_genre = 'SO' ");
		query.append("and d.i_ge_entite = 3 and d.i_ca_cle = trca.l_tr_cle and trca.i_la_cle = 1 ");
		query.append("and d.i_cc_cle <> 14920 and s.i_cc_cle <> 14920 and d.i_ca_cle <> 662 AND d.i_ge_cle = 16500 and d.i_ca_cle in (654, 23670) and exists ");
		query.append("(select d2.d_do_date_debut from do_dossier d2, so_societe s2, ldd_lien_dossier l2 ");
		query.append("where d2.i_ca_cle = 662 and s2.l_so_cle = s.l_so_cle and s2.l_si_cle = s.l_si_cle and d2.l_do_cle = l2.l_do_cle ");
		query.append("and d2.l_si_cle = l2.l_do_site and l2.c_do_genre = 'DO' and l2.l_ldd_dossier_associe = s2.l_so_cle ");
		query.append("and l2.l_ldd_site = s2.l_si_cle and l2.c_ldd_genre = 'SO' ");
		query.append("and d2.v_do_ancienne_reference = ? ");
		preparerSQL.addParametre(cumulatifDossierRapportVO.getVague().toUpperCase());
		query.append("and d2.i_st_cle = 359 ");           
		query.append("and d.v_do_reference1 = d2.v_do_ancienne_reference) ");
		query.append("union ");
		query.append("SELECT '4e visite - Suspension 30 jours' as DESCRIPTION, trca.v_tr_description as CATEGORIE, s.v_so_reference_1 as REGION, s.v_so_reference_3 ");
		query.append("FROM v_do_dossier_ca_ty d, ldd_lien_dossier l, so_societe s, tr_traduction trca ");
		query.append("where d.l_do_cle = l.l_do_cle and d.l_si_cle = l.l_do_site and l.c_do_genre = 'DO' ");
		query.append("and l.l_ldd_dossier_associe = s.l_so_cle and l.l_ldd_site = s.l_si_cle and l.c_ldd_genre = 'SO' ");
		query.append("and d.i_ge_entite = 3 and d.i_ca_cle = trca.l_tr_cle and trca.i_la_cle = 1 ");
		query.append("and d.i_cc_cle <> 14920 and s.i_cc_cle <> 14920 and d.i_ca_cle <> 662 AND d.i_ge_cle = 16500 and d.i_ca_cle in (655, 23735) and exists ");
		query.append("(select d2.d_do_date_debut from do_dossier d2, so_societe s2, ldd_lien_dossier l2 ");
		query.append("where d2.i_ca_cle = 662 and s2.l_so_cle = s.l_so_cle and s2.l_si_cle = s.l_si_cle ");
		query.append("and d2.l_do_cle = l2.l_do_cle and d2.l_si_cle = l2.l_do_site and l2.c_do_genre = 'DO' ");
		query.append("and l2.l_ldd_dossier_associe = s2.l_so_cle and l2.l_ldd_site = s2.l_si_cle and l2.c_ldd_genre = 'SO' ");
		query.append("and d2.v_do_ancienne_reference = ? ");
		preparerSQL.addParametre(cumulatifDossierRapportVO.getVague().toUpperCase());
		query.append("and d2.i_st_cle = 359 ");           
		query.append("and d.v_do_reference1 = d2.v_do_ancienne_reference) ");
		query.append("union ");
		query.append("SELECT '5e visite - Suspension 1 an' as DESCRIPTION, trca.v_tr_description as CATEGORIE, s.v_so_reference_1 as REGION, s.v_so_reference_3 ");
		query.append("FROM v_do_dossier_ca_ty d, ldd_lien_dossier l, so_societe s, tr_traduction trca ");
		query.append("where d.l_do_cle = l.l_do_cle and d.l_si_cle = l.l_do_site and l.c_do_genre = 'DO' ");
		query.append("and l.l_ldd_dossier_associe = s.l_so_cle and l.l_ldd_site = s.l_si_cle and l.c_ldd_genre = 'SO' ");
		query.append("and d.i_ge_entite = 3 and d.i_ca_cle = trca.l_tr_cle and trca.i_la_cle = 1 ");
		query.append("and d.i_cc_cle <> 14920 and s.i_cc_cle <> 14920 and d.i_ca_cle <> 662 AND d.i_ge_cle = 16500 and d.i_ca_cle in (656, 24863) and exists ");
		query.append("(select d2.d_do_date_debut from do_dossier d2, so_societe s2, ldd_lien_dossier l2 ");
		query.append("where d2.i_ca_cle = 662 and s2.l_so_cle = s.l_so_cle and s2.l_si_cle = s.l_si_cle and d2.l_do_cle = l2.l_do_cle ");
		query.append("and d2.l_si_cle = l2.l_do_site and l2.c_do_genre = 'DO' and l2.l_ldd_dossier_associe = s2.l_so_cle and l2.l_ldd_site = s2.l_si_cle ");
		query.append("and l2.c_ldd_genre = 'SO' ");
		query.append("and d2.v_do_ancienne_reference = ? ");
		preparerSQL.addParametre(cumulatifDossierRapportVO.getVague().toUpperCase());
		query.append("and d2.i_st_cle = 359 ");           
		query.append("and d.v_do_reference1 = d2.v_do_ancienne_reference) ");
		preparerSQL.setSQL(query.toString());
		
		return preparerSQL;
	} 

}
