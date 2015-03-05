package com.lotoquebec.cardex.integration.dao.sql.rapport;

import com.lotoQuebec.correcteurAdresse.util.StringUtils;
import com.lotoquebec.cardex.business.vo.rapport.StatistiqueDossierRapportVO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Ce rapport ne devrait pas utilisez la liaison d4.v_do_reference1 = d2.v_do_ancienne_reference
 * I14-1227 : Rapport de cohérence - Client mystère - erreur dans la validation du numéro de vague
 * @author levassc
 *
 */
public class ClientsMysteresDetaillantsInfractionSQL_CDX_0257{
	
	/* Ce script complexe repose sur plusieurs règles d'affaire. Il a pour but de retrouver tous les détaillants en infraction, 
	 * c'est-à-dire n'ayant pas carté le client mystère qui leur a acheté un billet. Le rapport produit est ensuite acheminé
	 * au Service aux détaillants pour donner suite selon les procédures mises en place. 
	 * Le script est divisé en 2 sections reliées par une clause UNION. La première partie sert à aller chercher le sujet physique responsable 
	 * quand la société morale n'existe pas. La 2e partie sert cette fois à trouver le responsable à partir de la société morale reliée.
	 * Dans les deux cas, il faut prendre l'enregistrement relié le plus récent.
	 * Chaque section recherche ensuite les données selon les principaux critères suivants :
	 * - La société doit avoir un dossier d'infraction (d.i_ca_cle in (23668, 23669, 23670, 23735, 24863))
	 * - La société doit être reliée à un dossier Échantillon et suivi (and d2.i_ca_cle = 662) actif (and d2.i_st_cle = 359)
	 * - Les dossiers d'infraction doivent être reliés à ce dossier d'Échantillon et suivi (and d2.v_do_ancienne_reference = d.v_do_reference1)
	 * - La société (détaillant) traitée ne doit pas être une société respondable (and lr.i_ro_cle = 659)
	 * - Pour la vague de visite concernée (d2.v_do_ancienne_reference = d.v_do_reference1), il ne doit pas y avoir de dossiers de visite conforme (and d5.i_ca_cle in (23667, 653, 654, 655, 656)).
	 * - Le dernier dossier ajouté au détaillant n'est pas un dossier de Visite non complétée ou Gestion nouveau détaillant et il ne contient rien dans le champ
	 * Référence 2 (v_do_reference3), ce qui indiquerait que le détaillant a déjà été traité par le Service au détaillant (and (d4.i_ca_cle in (27769, 657) or (d4.v_do_reference3 is not null))
	 * 
	 * Comme ce rapport doit être présenté comme un format Excel, le script est encadré par un SELECT qui traite les données retournées et identifiées par "x". Cela permet d'obtenir
	 * une seule ligne par détaillant avec toutes les données qui le concerne.
	 */

	public static PreparerSQL construireSQL(StatistiqueDossierRapportVO detaillantInfractionRapportVO) {
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder query = new StringBuilder();

		query.append("select x.District, x.Region_administrative, x.Numero, x.Nom, x.Adresse, x.Ville, ");
		query.append("x.Code_postal, x.Langue, x.Code_regional, x.Telephone, x.Code_commerce, x.Nom_compte, x.Responsable, ");
		query.append("Max(decode(x.Cle_categorie, 23667, x.Date_visite, 23668, x.Date_visite)) Date_visite_1, ");
		query.append("Max(decode(x.Cle_categorie, 23667, x.Heure_visite, 23668, x.Heure_visite)) Heure_visite_1, ");
		query.append("Max(decode(x.Cle_categorie, 23667, 'Oui', 23668, 'Non')) Resultat1, ");
		query.append("Max(decode(x.Cle_categorie, 23668, x.Billet)) Billet1, ");
		query.append("Max(decode(x.Cle_categorie, 653, x.Date_visite, 23669, x.Date_visite)) Date_visite_2, ");
		query.append("Max(decode(x.Cle_categorie, 653, x.Heure_visite, 23669, x.Heure_visite)) Heure_visite_2, ");
		query.append("Max(decode(x.Cle_categorie, 653, 'Oui', 23669, 'Non')) Resultat2, ");
		query.append("Max(decode(x.Cle_categorie, 23669, x.Billet)) Billet2, ");
		query.append("Max(decode(x.Cle_categorie, 654, x.Date_visite, 23670, x.Date_visite)) Date_visite_3, ");
		query.append("Max(decode(x.Cle_categorie, 654, x.Heure_visite, 23670, x.Heure_visite)) Heure_visite_3, ");
		query.append("Max(decode(x.Cle_categorie, 654, 'Oui', 23670, 'Non')) Resultat3, ");
		query.append("Max(decode(x.Cle_categorie, 23670, x.Billet)) Billet3, ");
		query.append("Max(decode(x.Cle_categorie, 655, x.Date_visite, 23735, x.Date_visite)) Date_visite_4, ");
		query.append("Max(decode(x.Cle_categorie, 655, x.Heure_visite, 23735, x.Heure_visite)) Heure_visite_4, ");
		query.append("Max(decode(x.Cle_categorie, 655, 'Oui', 23735, 'Non')) Resultat4, ");
		query.append("Max(decode(x.Cle_categorie, 23735, x.Billet)) Billet4, ");
		query.append("Max(decode(x.Cle_categorie, 656, x.Date_visite, 24863, x.Date_visite)) Date_visite_5, ");
		query.append("Max(decode(x.Cle_categorie, 656, x.Heure_visite, 24863, x.Heure_visite)) Heure_visite_5, ");
		query.append("Max(decode(x.Cle_categorie, 656, 'Oui', 24863, 'Non')) Resultat5, ");
		query.append("Max(decode(x.Cle_categorie, 24863, x.Billet)) Billet5, ");
		query.append("x.Vague ");
	    //Section du sujet Responsable
		query.append("from (select distinct d.i_ca_cle as Cle_categorie, s.v_so_district District, s.v_so_reference_3 Numero, ");
		query.append("s.v_so_nom Nom, a.v_ad_num_municipal || ', ' ||trtr.v_tr_description || ' ' || a.v_ad_nom_rue Adresse, ");
		query.append("v.v_vi_ville Ville, a.v_ad_code_postal Code_postal, substr(a.v_ad_telephone_1, 1, 4) Code_regional, ");
		query.append("substr(a.v_ad_telephone_1, 5, length(a.v_ad_telephone_1)) Telephone, ");
		query.append("trls.v_tr_description Langue, ");
		query.append("s.v_so_code_compte Code_commerce, '(a/s ' || su.v_su_nom || ' ' || su.v_su_prenom || ')' Responsable, ");
		query.append("to_char(d.d_do_date_debut, 'YYYY-MM-DD') Date_visite, to_char(d.d_do_date_debut, 'HH24:MI') Heure_visite, ");
		query.append("trca.v_tr_description Categorie, b.v_bi_numero_controle Billet, d2.v_do_ancienne_reference Vague, ");
		query.append("cc.NOM_COC Nom_compte, s.v_so_reference_1 Region_administrative ");
		query.append("from so_societe               s, ");
		query.append("ad_adresse               a, ");
	    query.append("vi_ville                 v, ");
	    query.append("tr_traduction            trls, ");
	    query.append("tr_traduction            trtr, ");
	    query.append("v_do_dossier_ca_ty       d, ");
	    query.append("ldd_lien_dossier         ld, ");
	    query.append("tr_traduction            trca, ");
	    query.append("bi_billet                b, ");
	    query.append("do_dossier               d2, ");
	    query.append("ldd_lien_dossier         l2, ");
	    query.append("su_sujet                 su, ");
	    query.append("ldd_lien_dossier         lsu, ");
	    query.append("vexi_cdx_dds_code_compte cc ");
	    query.append("where s.l_so_cle = a.l_ad_reference(+) and s.l_si_cle = a.l_ad_ref_site(+) ");
	    query.append("and a.c_ad_ref_genre(+) = 'SO' and a.l_vi_cle = v.l_vi_cle(+) and a.i_st_cle(+) = 621 ");
	    query.append("and a.l_ad_type_rue = trtr.l_tr_cle(+) and trtr.i_la_cle(+) = 1 and s.i_ls_cle = trls.l_tr_cle(+) ");
	    query.append("and trls.i_la_cle(+) = 1 and s.l_si_cle = 8 and s.b_so_ind_rdd = 'yes' and s.i_cc_cle <> 14920 ");
	    query.append("and s.v_so_reference_3 is not null and s.b_so_actif = 'yes' and d.l_do_cle = ld.l_do_cle ");
	    query.append("and d.l_si_cle = ld.l_do_site and ld.c_do_genre = 'DO' and ld.l_ldd_dossier_associe = s.l_so_cle ");
	    query.append("and ld.l_ldd_site = s.l_si_cle and ld.c_ldd_genre = 'SO' and d.i_ca_cle = trca.l_tr_cle and trca.i_la_cle = 1 ");
	    query.append("and d.i_cc_cle <> 14920 and d.i_ge_entite = 3 ");
	    //Dossiers d'infraction
	    if(detaillantInfractionRapportVO.getCategorie() != 0){
	        if(detaillantInfractionRapportVO.getCategorie() == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1){
	            query.append(" and d.i_ca_cle = ? ");
	            preparerSQL.addParametre(detaillantInfractionRapportVO.getCategorie());
	        }
            if(detaillantInfractionRapportVO.getCategorie() == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2){
                query.append(" and d.i_ca_cle in (" + GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1 + ", " + 
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2 + ") ");
            }
            if(detaillantInfractionRapportVO.getCategorie() == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3){
                query.append(" and d.i_ca_cle in (" + GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1 + ", " + 
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3 + ") ");
            }
            if(detaillantInfractionRapportVO.getCategorie() == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_4){
                query.append(" and d.i_ca_cle in (" + GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1 + ", " + 
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_4 + ") ");
            }
            if(detaillantInfractionRapportVO.getCategorie() == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_5){
                query.append(" and d.i_ca_cle in (" + GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1 + ", " + 
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_4 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_5 + ") ");
            }
        }else{
            query.append(" and d.i_ca_cle in (" + GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1 + ", " + 
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_4 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_5 + ") "); //On prend les catégories d'infraction.
        }

	    query.append("and d.i_ge_cle = 16500 ");
	    query.append("and d.l_do_cle = b.l_bi_ref_cle(+) and d.l_si_cle = b.l_bi_ref_site(+)  ");
	    //Liaison à un dossier actif Échantillon et suivi
	    query.append("and d2.i_ca_cle = 662 and d2.v_do_ancienne_reference = d.v_do_reference1 ");
	    query.append("and d2.l_do_cle = l2.l_do_cle and d2.l_si_cle = l2.l_do_site and l2.c_do_genre = 'DO' ");
	    query.append("and l2.l_ldd_dossier_associe = s.l_so_cle and l2.l_ldd_site = s.l_si_cle and l2.c_ldd_genre = 'SO' ");
	    query.append("and d2.i_st_cle = 359 and s.v_so_code_compte = cc.CODE_CPTE_COC(+) ");
        if(!StringUtils.equals(detaillantInfractionRapportVO.getDistrict(),"0")){
            query.append(" and substr(s.v_so_district,1,2) = ? ");
            preparerSQL.addParametre(detaillantInfractionRapportVO.getDistrict());
        }
	    //Détaillant vendeur et non Responsable
	    query.append("and not exists (select lr.l_ldd_cle from ldd_lien_dossier lr, so_societe sr ");
	    query.append("where s.l_so_cle = lr.l_do_cle and s.l_si_cle = lr.l_do_site and lr.c_do_genre = 'SO' ");
	    query.append("and lr.l_ldd_dossier_associe = sr.l_so_cle and lr.l_ldd_site = sr.l_si_cle and lr.c_ldd_genre = 'SO' ");
	    query.append("and lr.i_ro_cle = 659 and sr.i_cc_cle <> 14920) and su.l_su_cle = lsu.l_do_cle and su.l_si_cle = lsu.l_do_site ");
	    query.append("and lsu.c_do_genre = 'SU' and lsu.l_ldd_dossier_associe = s.l_so_cle and lsu.l_ldd_site = s.l_si_cle ");
	    query.append("and lsu.c_ldd_genre = 'SO' and su.b_su_ind_rdd = 'yes' ");
	    //Sujet Responsable RDD le plus récent
	    query.append("and lsu.d_ldd_date_creation = (select max(lsu2.d_ldd_date_creation) from ldd_lien_dossier lsu2, su_sujet su2 ");
	    query.append("where su2.l_su_cle = lsu2.l_do_cle and su2.l_si_cle = lsu2.l_do_site and lsu2.c_do_genre = 'SU' ");
	    query.append("and lsu2.l_ldd_dossier_associe = s.l_so_cle and lsu2.l_ldd_site = s.l_si_cle and lsu2.c_ldd_genre = 'SO' ");
	    query.append("and su2.i_cc_cle <> 14920 and su2.b_su_ind_rdd = 'yes') ");
	    //Pas de dossier conforme ou visite non complétée
	    query.append("and not exists (select * from v_do_dossier_ca_ty d5, ldd_lien_dossier l5 ");
	    query.append("where d5.l_do_cle = l5.l_do_cle and d5.l_si_cle = l5.l_do_site and l5.c_do_genre = 'DO' ");
	    query.append("and l5.l_ldd_dossier_associe = s.l_so_cle and l5.l_ldd_site = s.l_si_cle and l5.c_ldd_genre = 'SO' ");
	    query.append("and d5.i_ca_cle in (23667, 653, 654, 655, 656) and d2.v_do_ancienne_reference = d5.v_do_reference1) ");
	    //Le dernier dossier ajouté au détaillant n'est pas un dossier de Visite non compplétée ou Gestion nouveau détaillant
	    query.append("and not exists (select d3.v_do_numero_dossier from ldd_lien_dossier ld3, v_do_dossier_ca_ty d3 ");
	    query.append("where d3.l_do_cle = ld3.l_do_cle and d3.l_si_cle = ld3.l_do_site and ld3.c_do_genre = 'DO' ");
	    query.append("and ld3.l_ldd_dossier_associe = s.l_so_cle and ld3.l_ldd_site = s.l_si_cle and ld3.c_ldd_genre = 'SO' ");
	    query.append("and d3.i_cc_cle <> 14920 and d3.i_ge_entite = 3 and d3.i_ge_cle = 16500 ");
	    query.append("and d3.d_do_date_debut = (select max(d4.d_do_date_debut) from ldd_lien_dossier ld4, v_do_dossier_ca_ty d4 ");
	    query.append("where d4.l_do_cle = ld4.l_do_cle and d4.l_si_cle = ld4.l_do_site and ld4.c_do_genre = 'DO' ");
	    query.append("and ld4.l_ldd_dossier_associe = s.l_so_cle and ld4.l_ldd_site = s.l_si_cle and ld4.c_ldd_genre = 'SO' ");
	    query.append("and d4.i_cc_cle <> 14920 and d4.i_ge_entite = 3 and d4.i_ge_cle = 16500 and (d4.i_ca_cle in (27769, 657) or ");
	    query.append("(d4.v_do_reference3 is not null)))) ");                   
	    query.append("union ");
	    query.append("select distinct d.i_ca_cle as Cle_categorie, s.v_so_district District, s.v_so_reference_3 Numero, s.v_so_nom Nom, ");
	    query.append("a.v_ad_num_municipal || ', ' || trtr.v_tr_description || ' ' || a.v_ad_nom_rue Adresse, v.v_vi_ville Ville, ");
	    query.append("a.v_ad_code_postal Code_postal, substr(a.v_ad_telephone_1, 1, 4) Code_regional, substr(a.v_ad_telephone_1, 5, length(a.v_ad_telephone_1)) Telephone, ");
	    query.append("trls.v_tr_description Langue, s.v_so_code_compte Code_commerce, ");
	    query.append("decode(s.v_so_reference_nom, '', '', sr.v_so_nom || ' (a/s ' || s.v_so_reference_prenom || ' ' || s.v_so_reference_nom || ')') Responsable, ");
	    query.append("to_char(d.d_do_date_debut, 'YYYY-MM-DD') Date_visite, to_char(d.d_do_date_debut, 'HH24:MI') Heure_visite, ");
	    query.append("trca.v_tr_description Categorie, b.v_bi_numero_controle Billet, d2.v_do_ancienne_reference Vague, cc.NOM_COC Nom_compte, s.v_so_reference_1 Region_administrative ");
	    query.append("from so_societe               s, ");
	    query.append("ad_adresse               a, ");
	    query.append("vi_ville                 v, ");
	    query.append("tr_traduction            trls, ");
	    query.append("tr_traduction            trtr, ");
	    query.append("ldd_lien_dossier         lr, ");
	    query.append("so_societe               sr, ");
	    query.append("v_do_dossier_ca_ty       d, ");
	    query.append("ldd_lien_dossier         ld, ");
	    query.append("tr_traduction            trca, ");
	    query.append("bi_billet                b, ");
	    query.append("do_dossier               d2, ");
	    query.append("ldd_lien_dossier         l2, ");
	    query.append("vexi_cdx_dds_code_compte cc ");
	    query.append("where s.l_so_cle = a.l_ad_reference(+) and s.l_si_cle = a.l_ad_ref_site(+) and a.c_ad_ref_genre(+) = 'SO' ");
	    query.append("and a.l_vi_cle = v.l_vi_cle(+) and a.i_st_cle(+) = 621 and a.l_ad_type_rue = trtr.l_tr_cle(+) and trtr.i_la_cle(+) = 1 ");
	    query.append("and s.i_ls_cle = trls.l_tr_cle(+) and trls.i_la_cle(+) = 1 and s.l_si_cle = 8 and s.b_so_ind_rdd = 'yes' ");
	    query.append("and s.i_cc_cle <> 14920 and s.v_so_reference_3 is not null and s.b_so_actif = 'yes' ");
	    query.append("and s.l_so_cle = lr.l_do_cle and s.l_si_cle = lr.l_do_site and lr.c_do_genre = 'SO' ");
	    query.append("and lr.l_ldd_dossier_associe = sr.l_so_cle and lr.l_ldd_site = sr.l_si_cle and lr.c_ldd_genre = 'SO' ");
	    query.append("and lr.i_ro_cle = 659 and sr.i_cc_cle <> 14920 ");
	    //Société responsable la plus récente
	    query.append("and lr.d_ldd_date_creation = (select max(lr2.d_ldd_date_creation) from ldd_lien_dossier lr2, so_societe sr2 ");
	    query.append("where s.l_so_cle = lr2.l_do_cle and s.l_si_cle = lr2.l_do_site and lr2.c_do_genre = 'SO'");
	    query.append("and lr2.l_ldd_dossier_associe = sr2.l_so_cle and lr2.l_ldd_site = sr2.l_si_cle and lr2.c_ldd_genre = 'SO' ");
	    query.append("and lr2.i_ro_cle = 659 and sr2.i_cc_cle <> 14920) and d.l_do_cle = ld.l_do_cle and d.l_si_cle = ld.l_do_site and ld.c_do_genre = 'DO' ");
	    query.append("and ld.l_ldd_dossier_associe = s.l_so_cle and ld.l_ldd_site = s.l_si_cle and ld.c_ldd_genre = 'SO' ");
	    query.append("and d.i_ca_cle = trca.l_tr_cle and trca.i_la_cle = 1 and d.i_cc_cle <> 14920 and d.i_ge_entite = 3 ");
	    if(detaillantInfractionRapportVO.getCategorie() != 0){
	        if(detaillantInfractionRapportVO.getCategorie() == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1){
	            query.append(" and d.i_ca_cle = ? ");
	            preparerSQL.addParametre(detaillantInfractionRapportVO.getCategorie());
	        }
            if(detaillantInfractionRapportVO.getCategorie() == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2){
                query.append(" and d.i_ca_cle in (" + GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1 + ", " + 
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2 + ") ");
            }
            if(detaillantInfractionRapportVO.getCategorie() == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3){
                query.append(" and d.i_ca_cle in (" + GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1 + ", " + 
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3 + ") ");
            }
            if(detaillantInfractionRapportVO.getCategorie() == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_4){
                query.append(" and d.i_ca_cle in (" + GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1 + ", " + 
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_4 + ") ");
            }
            if(detaillantInfractionRapportVO.getCategorie() == GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_5){
                query.append(" and d.i_ca_cle in (" + GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1 + ", " + 
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_4 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_5 + ") ");
            }
        }else{
            query.append(" and d.i_ca_cle in (" + GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1 + ", " + 
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_4 + ", " +
                    GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_5 + ") "); //On prend les catégories d'infraction.
        }
	    query.append("and d.i_ge_cle = 16500 and d.l_do_cle = b.l_bi_ref_cle(+) and d.l_si_cle = b.l_bi_ref_site(+) ");
	    query.append("and d2.i_ca_cle = 662 and d2.v_do_ancienne_reference = d.v_do_reference1 and d2.l_do_cle = l2.l_do_cle and d2.l_si_cle = l2.l_do_site and l2.c_do_genre = 'DO' ");
	    query.append("and l2.l_ldd_dossier_associe = s.l_so_cle and l2.l_ldd_site = s.l_si_cle and l2.c_ldd_genre = 'SO' and d2.i_st_cle = 359 ");
        if(!StringUtils.equals(detaillantInfractionRapportVO.getDistrict(),"0")){
            query.append(" and substr(s.v_so_district,1,2) = ? ");
            preparerSQL.addParametre(detaillantInfractionRapportVO.getDistrict());
        }
	    query.append("and s.v_so_code_compte = cc.CODE_CPTE_COC(+) ");
	    query.append("and not exists (select * from v_do_dossier_ca_ty d5, ldd_lien_dossier l5 where d5.l_do_cle = l5.l_do_cle and d5.l_si_cle = l5.l_do_site and l5.c_do_genre = 'DO' ");
	    query.append("and l5.l_ldd_dossier_associe = s.l_so_cle and l5.l_ldd_site = s.l_si_cle and l5.c_ldd_genre = 'SO' ");
	    query.append("and d5.i_ca_cle in (23667, 653, 654, 655, 656) and d2.v_do_ancienne_reference = d5.v_do_reference1) ");
	    query.append("and not exists (select d3.v_do_numero_dossier from ldd_lien_dossier ld3, v_do_dossier_ca_ty d3 ");
	    query.append("where d3.l_do_cle = ld3.l_do_cle and d3.l_si_cle = ld3.l_do_site and ld3.c_do_genre = 'DO' ");
	    query.append("and ld3.l_ldd_dossier_associe = s.l_so_cle and ld3.l_ldd_site = s.l_si_cle and ld3.c_ldd_genre = 'SO' ");
	    query.append("and d3.i_cc_cle <> 14920 and d3.i_ge_entite = 3 and d3.i_ge_cle = 16500 ");
	    query.append("and d3.d_do_date_debut = (select max(d4.d_do_date_debut) from ldd_lien_dossier ld4, v_do_dossier_ca_ty d4 ");
	    query.append("where d4.l_do_cle = ld4.l_do_cle and d4.l_si_cle = ld4.l_do_site and ld4.c_do_genre = 'DO' ");
	    query.append("and ld4.l_ldd_dossier_associe = s.l_so_cle and ld4.l_ldd_site = s.l_si_cle and ld4.c_ldd_genre = 'SO' ");
	    query.append("and d4.i_cc_cle <> 14920 and d4.i_ge_entite = 3 and d4.i_ge_cle = 16500 and (d4.i_ca_cle in (27769, 657) or (d4.v_do_reference3 is not null))))) x ");
	    query.append("group by x.District, x.Numero, x.Nom, x.Adresse, x.Ville, x.Code_postal, x.Langue, x.Code_commerce, ");
	    query.append("x.Responsable, x.Vague, x.Region_administrative, x.Code_regional, x.Telephone, x.Nom_compte ");

		preparerSQL.setSQL(query.toString());
		System.err.println(preparerSQL.toString());
		return preparerSQL;
	} 

}
