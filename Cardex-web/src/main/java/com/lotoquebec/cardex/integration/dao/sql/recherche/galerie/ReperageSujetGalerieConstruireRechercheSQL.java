package com.lotoquebec.cardex.integration.dao.sql.recherche.galerie;

import com.lotoquebec.cardex.business.CriteresRecherchePhoto;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;


public abstract class ReperageSujetGalerieConstruireRechercheSQL extends SujetGalerieConstruireRechercheSQL{

	@Override
	protected String ajoutFrom() {
		return ", v_do_dossier_ca_ty doReperage, ldd_lien_dossier lddReperage ";
	}

	@Override
	protected void whereCritereDossier(PreparerSQL preparerSQL, CardexAuthenticationSubject subject, CriteresRecherchePhoto criteresRecherchePhoto){
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();

        //On vérifie si le dossier est partagé. Si non, on applique le niveau de confidentialité.
        //Si oui, on vérifie si l'utilisateur a le droit d'accéder au dossier.
        preparerSQL.getSQL().append(" AND ((DO.V_DO_ASSIGNE_A = ?) OR " );
        preparerSQL.getSQL().append(" (NOT EXISTS (SELECT * FROM LPD_PARTAGE_DOSSIER LPD " );
        preparerSQL.getSQL().append(" WHERE LPD.L_LPD_REFERENCE = DO.L_DO_CLE AND LPD.L_LPD_REF_SITE = DO.L_SI_CLE AND LPD.C_LPD_GENRE = 'RES'))) ");
    	preparerSQL.getSQL().append(" AND DO.V_DO_MOT_PASSE IS NULL ");
        preparerSQL.getSQL().append(" AND DO.I_CC_CLE <= ? ");
        preparerSQL.addParametre(user.getCode());
        preparerSQL.addParametre(privilege.getNiveauConfidentialite());

        preparerSQL.getSQL().append(" and ((lddReperage.l_ldd_dossier_associe = doReperage.l_do_cle ");
        preparerSQL.getSQL().append(" and lddReperage.l_ldd_site = doReperage.l_si_cle ");
        preparerSQL.getSQL().append(" and lddReperage.c_ldd_genre = 'DO' ");
        preparerSQL.getSQL().append(" and lddReperage.l_do_cle = do.l_do_cle ");
        preparerSQL.getSQL().append(" and lddReperage.l_do_site = do.l_si_cle "); 
        preparerSQL.getSQL().append(" and lddReperage.c_do_genre = 'DO') or ");
        preparerSQL.getSQL().append(" (lddReperage.l_ldd_dossier_associe = do.l_do_cle ");
        preparerSQL.getSQL().append(" and lddReperage.l_ldd_site = do.l_si_cle ");
        preparerSQL.getSQL().append(" and lddReperage.c_ldd_genre = 'DO' ");
        preparerSQL.getSQL().append(" and lddReperage.l_do_cle = doReperage.l_do_cle ");
        preparerSQL.getSQL().append(" and lddReperage.l_do_site = doReperage.l_si_cle ");
        preparerSQL.getSQL().append(" and lddReperage.c_do_genre = 'DO' )) ");
        /*preparerSQL.getSQL().append(" and ld.l_do_cle = do.l_do_cle ");
        preparerSQL.getSQL().append(" and ld.l_do_site = do.l_si_cle ");
        preparerSQL.getSQL().append(" and ld.c_do_genre = 'DO' ");
        preparerSQL.getSQL().append(" and s.l_su_cle = ld.l_ldd_dossier_associe ");
        preparerSQL.getSQL().append(" and s.l_si_cle = ld.l_ldd_site "); 
        preparerSQL.getSQL().append(" and ld.c_ldd_genre = 'SU'");*/
        //d2 = doReperage
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "doReperage.l_si_cle", criteresRecherchePhoto.getSiteOrigine());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "doReperage.i_ca_cle", criteresRecherchePhoto.getCategorie());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "doReperage.i_ty_cle", GlobalConstants.Type.REPERAGE);
        //OracleDAOUtils.ajouterChampSQL(preparerSQL, "do.i_na_cle", criteresRecherchePhoto.getNature()); pas requis
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.B_DO_ENREGISTREMENT_NUMERIQUE", criteresRecherchePhoto.getEnregistrementNumerique());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "doReperage.i_do_fonde", criteresRecherchePhoto.getFonde());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "doReperage.i_or_cle", criteresRecherchePhoto.getEndroit());
        OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, "doReperage.V_DO_LIEU", criteresRecherchePhoto.getDescriptif());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "doReperage.v_do_assigne_a", criteresRecherchePhoto.getIntervenant());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "doReperage.i_cr_cle", criteresRecherchePhoto.getLocalisation());

        OracleDAOUtils.ajouterChampSQL(preparerSQL, "doReperage.D_DO_DATE_CREATION", ">=", criteresRecherchePhoto.getDateAjoutDebut());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "doReperage.D_DO_DATE_CREATION", "<", criteresRecherchePhoto.getDateAjoutFin());
        //La fonction TRUNC n'est plus utilisée en raison de problèmes de performance. Le SQL a été modifié, d'où l'ajout d'une journée
        //en raison des heures.
        if(criteresRecherchePhoto.getDateAjoutFin()!= null){
        	preparerSQL.getSQL().append(" + 1 ");
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "doReperage.D_DO_DATE_DEBUT", ">=", criteresRecherchePhoto.getDateValideDebut());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "doReperage.D_DO_DATE_DEBUT", "<", criteresRecherchePhoto.getDateValideFin());
        if(criteresRecherchePhoto.getDateValideFin()!= null){
        	preparerSQL.getSQL().append(" + 1 ");
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "doReperage.D_DO_DATE_FIN", ">=", criteresRecherchePhoto.getDateTermineDebut());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "doReperage.D_DO_DATE_FIN", "<", criteresRecherchePhoto.getDateTermineFin());
        if(criteresRecherchePhoto.getDateTermineFin()!= null){
        	preparerSQL.getSQL().append(" + 1 ");
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "do.i_pe_cle", criteresRecherchePhoto.getPeriode());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "do.i_se_cle", criteresRecherchePhoto.getSeverite()); //C'est la sévérité du dossier qui s'applique et non celle du sujet
        
        if (criteresRecherchePhoto.getJeu() != 0 ){
           preparerSQL.getSQL().append(" AND (do.l_do_cle, do.l_si_cle) ");
           preparerSQL.getSQL().append(" in (select je.l_do_cle, je.l_ljd_ref_site ");
           preparerSQL.getSQL().append(" from LJD_LIEN_JEU_DOSSIER je ");
           preparerSQL.getSQL().append(" where je.i_je_cle = ?)");
           preparerSQL.addParametre(criteresRecherchePhoto.getJeu());
        }else if (criteresRecherchePhoto.getEntite() != 0
        && criteresRecherchePhoto.getTypeJeu() != 0){
            preparerSQL.getSQL().append(" AND (do.l_do_cle, do.l_si_cle) ");
            preparerSQL.getSQL().append(" in (select je.l_do_cle, je.l_ljd_ref_site ");
            preparerSQL.getSQL().append(" from LJD_LIEN_JEU_DOSSIER je ");
            preparerSQL.getSQL().append(" where je.i_je_cle in (select t.l_tv_valeur ");
            preparerSQL.getSQL().append("   from tv_table_valeur t ");
            preparerSQL.getSQL().append("   where t.v_tv_code = 'Jeux' ");
            preparerSQL.getSQL().append("   AND 2 = (select count(*) ");
            preparerSQL.getSQL().append("   	from ctv_critere_table_valeur ctv ");
            preparerSQL.getSQL().append(" 		where ctv.l_tv_valeur = t.l_tv_valeur ");
			OracleDAOUtils.ajouterChampSQL(preparerSQL, "ctv.l_tv_parent_valeur", new long[]{criteresRecherchePhoto.getEntite(),criteresRecherchePhoto.getTypeJeu()});
			preparerSQL.getSQL().append("))) ");                
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "doReperage.L_DO_ORIGINE", criteresRecherchePhoto.getOrigine());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "do.i_st_cle", criteresRecherchePhoto.getStatut());
        
       	/*if (criteresRecherchePhoto.getStatut() != 0 ){
			if (criteresRecherchePhoto.getStatut() != Integer.parseInt(GlobalConstants.Statut.DOSSIER_ACTIF )){
			//Pour les requêtes sur les dossiers qui ne sont pas actifs, on ne
			//ramène que le dossier inactif le plus récent.
				preparerSQL.getSQL().append(" and doReperage.l_do_cle in (select max(do2.l_do_cle) "); 
				preparerSQL.getSQL().append(" from ldd_lien_dossier lddMaxInactif, v_do_dossier_ca_ty doMaxInactif where ");
				preparerSQL.getSQL().append(" lddMaxInactif.l_ldd_dossier_associe = s.l_su_cle ");
				preparerSQL.getSQL().append(" and lddMaxInactif.l_ldd_site = s.l_si_cle ");
				preparerSQL.getSQL().append(" and lddMaxInactif.c_ldd_genre = 'SU' ");
				preparerSQL.getSQL().append(" and lddMaxInactif.l_do_cle = doMaxInactif.l_do_cle ");
				preparerSQL.getSQL().append(" and lddMaxInactif.l_do_site = doMaxInactif.l_si_cle ");
				preparerSQL.getSQL().append(" and do2.c_do_genre = 'DO' ");
				preparerSQL.getSQL().append(" and doReperage.i_st_cle != ?");
				preparerSQL.addParametre(Integer.parseInt(GlobalConstants.Statut.DOSSIER_ACTIF));
				OracleDAOUtils.ajouterChampSQL(preparerSQL, "d2.i_na_cle", criteresRecherchePhoto.getNature());
		        preparerSQL.getSQL().append(")");
			}
			if (criteresRecherchePhoto.getStatut() == Integer.parseInt(GlobalConstants.Statut.DOSSIER_ACTIF )){
				preparerSQL.getSQL().append(" and doReperage.i_st_cle = ?");
				preparerSQL.addParametre(Integer.parseInt(GlobalConstants.Statut.DOSSIER_ACTIF));
			}
		}*/
		
		//Recherche étendue dans les champs référence 1 et 2, peu importe dans quel critère de référence
		//la valeur a été saisie.
        if (StringUtils.isNotEmpty(criteresRecherchePhoto.getReference1())) {
            preparerSQL.getSQL().append(" AND (doReperage.V_DO_REFERENCE1 like ?  OR doReperage.V_DO_REFERENCE3 like ?)");
            preparerSQL.addParametreWhiteCard(criteresRecherchePhoto.getReference1().toUpperCase());
            preparerSQL.addParametreWhiteCard(criteresRecherchePhoto.getReference1().toUpperCase());
        }
		if (StringUtils.isNotEmpty(criteresRecherchePhoto.getReference2())) {
            preparerSQL.getSQL().append(" AND (doReperage.V_DO_REFERENCE1 like ? OR doReperage.V_DO_REFERENCE3 like ?)");
            preparerSQL.addParametreWhiteCard(criteresRecherchePhoto.getReference2().toUpperCase());
            preparerSQL.addParametreWhiteCard(criteresRecherchePhoto.getReference2().toUpperCase());
		}
	}
	
	@Override
	protected String havingArgument(CriteresRecherchePhoto criteresRecherchePhoto){
		if(criteresRecherchePhoto.getNombreReperages() > 0)
			return " having count(doReperage.l_do_cle) >= " + criteresRecherchePhoto.getNombreReperages();
		else
			return "";
	}
	
}
