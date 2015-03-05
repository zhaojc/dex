package com.lotoquebec.cardex.integration.dao.sql.recherche.galerie;

import com.lotoquebec.cardex.business.CriteresRecherchePhoto;
import com.lotoquebec.cardex.integration.dao.sql.recherche.SujetAgeCriteresRecherche;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

public abstract class GalerieConstruireRechercheSQL extends SujetAgeCriteresRecherche{

	public abstract String whereLiaisonPhoto();
	
	protected String ajoutFrom(){
		return "";
	}

	protected void ajoutFromSujet(PreparerSQL preparerSQL, CriteresRecherchePhoto criteresRecherchePhoto){
		preparerSQL.getSQL().append(", su_sujet s, ldd_lien_dossier ld");
	}
	
	protected String havingArgument(CriteresRecherchePhoto criteresRecherchePhoto) {
		return "";
	}
	
	protected void whereLiaisonDossierSujet(PreparerSQL preparerSQL, CriteresRecherchePhoto criteresRecherchePhoto){
		preparerSQL.getSQL().append(" and ld.l_do_cle = do.l_do_cle AND ld.l_do_site = do.l_si_cle ");
		preparerSQL.getSQL().append(" AND ld.c_do_genre = 'DO' and ld.l_ldd_dossier_associe = s.l_su_cle ");
		preparerSQL.getSQL().append(" AND ld.l_ldd_site = s.l_si_cle and ld.c_ldd_genre = 'SU' ");
	}
	
	protected void whereCritereSujet(PreparerSQL preparerSQL, CardexAuthenticationSubject subject, CriteresRecherchePhoto criteresRecherchePhoto){
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();

		preparerSQL.getSQL().append(" and s.v_su_mot_passe is NULL ");
        
		preparerSQL.getSQL().append(" and s.I_CC_CLE <= ?");
        preparerSQL.addParametre(privilege.getNiveauConfidentialite());

        long age = criteresRecherchePhoto.getAge();
        assignerAgeCriteresRecherche(preparerSQL, criteresRecherchePhoto, age);
       
        //Recherche directe par nom
        if (StringUtils.isNotEmpty(criteresRecherchePhoto.getNomOrdinaire()) ){
           OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, "UPPER(S.V_SU_NOM)", criteresRecherchePhoto.getNomOrdinaire());
       }else{ //Recherche phonétique par nom
           if (StringUtils.isNotEmpty(criteresRecherchePhoto.getNom()) ){
        	   	preparerSQL.getSQL().append(" AND (S.C_SU_SNDX_NOM = soundex(?)" );
				preparerSQL.addParametre(criteresRecherchePhoto.getNom());
				preparerSQL.getSQL().append(" OR " + OracleDAOUtils.champRecherche(preparerSQL, "S.V_SU_NOM", criteresRecherchePhoto.getNom()) + " ) " );              
           }
       }
       if (StringUtils.isNotEmpty(criteresRecherchePhoto.getPrenom()) ){
    	   preparerSQL.getSQL().append(" AND (S.c_su_sndx_prenom = soundex(?)" );
    	   preparerSQL.addParametre(criteresRecherchePhoto.getPrenom());
    	   preparerSQL.getSQL().append(" OR " + OracleDAOUtils.champRecherche(preparerSQL, "S.V_SU_PRENOM", criteresRecherchePhoto.getPrenom()) + " ) " );          
       }
       if (StringUtils.isNotEmpty(criteresRecherchePhoto.getAlias()) ){
    	   preparerSQL.getSQL().append(" AND S.v_Su_Surnom is not null and (S.c_su_sndx_surnom = soundex(?)" );
    	   preparerSQL.addParametre(criteresRecherchePhoto.getAlias());
    	   preparerSQL.getSQL().append(" OR " + OracleDAOUtils.champRecherche(preparerSQL, "S.V_SU_SURNOM", criteresRecherchePhoto.getAlias()) + " ) " );          
       }
       OracleDAOUtils.ajouterChampSQL(preparerSQL, "s.i_sx_cle", criteresRecherchePhoto.getSexe());
       OracleDAOUtils.ajouterChampSQL(preparerSQL, "s.i_nt_cle", criteresRecherchePhoto.getEthnie());
       OracleDAOUtils.ajouterChampSQL(preparerSQL, "s.i_ra_cle", criteresRecherchePhoto.getRace());
       OracleDAOUtils.ajouterChampSQL(preparerSQL, "s.i_ls_cle", criteresRecherchePhoto.getLangue());

       if (criteresRecherchePhoto.getCaracteristique1() != 0 ){
           preparerSQL.getSQL().append(" and s.l_su_cle = lsc1.l_lsc_reference ");
           preparerSQL.getSQL().append(" and s.l_si_cle = lsc1.l_lsc_ref_site ");
           preparerSQL.getSQL().append(" AND 'SU' = lsc1.c_lsc_ref_genre  ");
       }
       if (criteresRecherchePhoto.getCaracteristique2() != 0 ){
           preparerSQL.getSQL().append(" and s.l_su_cle = lsc2.l_lsc_reference ");
           preparerSQL.getSQL().append(" and s.l_si_cle = lsc2.l_lsc_ref_site ");
           preparerSQL.getSQL().append(" AND 'SU' = lsc2.c_lsc_ref_genre ");
       }
       if (criteresRecherchePhoto.getCaracteristique3() != 0 ){
           preparerSQL.getSQL().append(" and s.l_su_cle = lsc3.l_lsc_reference ");
           preparerSQL.getSQL().append(" and s.l_si_cle = lsc3.l_lsc_ref_site ");
           preparerSQL.getSQL().append(" AND 'SU' = lsc3.c_lsc_ref_genre " );
       }
       if (criteresRecherchePhoto.getCaracteristique4() != 0 ){
           preparerSQL.getSQL().append(" and s.l_su_cle = lsc4.l_lsc_reference ");
           preparerSQL.getSQL().append(" and s.l_si_cle = lsc4.l_lsc_ref_site ");
           preparerSQL.getSQL().append(" AND 'SU' = lsc4.c_lsc_ref_genre " );
       }
               
       //On encadre les caractéristiques avec des parenthèses pour la condition OR 
       if (criteresRecherchePhoto.getCaracteristique1() != 0 ){
			preparerSQL.getSQL().append(" and (lsc1.l_cr_cle = ?" );
			preparerSQL.addParametre(criteresRecherchePhoto.getCaracteristique1());
			
			if (criteresRecherchePhoto.getCaracteristique2() != 0){
				
				if (GlobalConstants.Operateur.OU.equals( criteresRecherchePhoto.getCaracteristique1et2() )){
					preparerSQL.getSQL().append(" or lsc2.l_cr_cle = ?");
					preparerSQL.addParametre(criteresRecherchePhoto.getCaracteristique2());
				}else{
					preparerSQL.getSQL().append(") and (lsc2.l_cr_cle = ?");
					preparerSQL.addParametre(criteresRecherchePhoto.getCaracteristique2());
				}
			}
			
			if (criteresRecherchePhoto.getCaracteristique3() != 0){
				
				if (GlobalConstants.Operateur.OU.equals( criteresRecherchePhoto.getCaracteristique2et3() )){
					preparerSQL.getSQL().append(" or lsc3.l_cr_cle = ?");
					preparerSQL.addParametre(criteresRecherchePhoto.getCaracteristique3());
			}else{	
					preparerSQL.getSQL().append(") and (lsc3.l_cr_cle = ?");
					preparerSQL.addParametre(criteresRecherchePhoto.getCaracteristique3());
				}
			}
			 
			if (criteresRecherchePhoto.getCaracteristique4() != 0){
				
				if (GlobalConstants.Operateur.OU.equals( criteresRecherchePhoto.getCaracteristique3et4() )){
					preparerSQL.getSQL().append(" or lsc4.l_cr_cle = ?");
					preparerSQL.addParametre(criteresRecherchePhoto.getCaracteristique4());
				}else{
					preparerSQL.getSQL().append(") and (lsc4.l_cr_cle = ?");
					preparerSQL.addParametre(criteresRecherchePhoto.getCaracteristique4());
				}
			}
			preparerSQL.getSQL().append(" ) ");
       }
	}
	
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

        if (criteresRecherchePhoto.getSiteApplicable() != 0 ){
        	preparerSQL.getSQL().append(" and do.i_na_cle = na.i_na_cle and na.b_na_inscription = 1 ");
        	preparerSQL.getSQL().append(" AND ist.L_IS_REFERENCE = DO.L_DO_CLE AND ist.L_IS_REF_SITE = DO.L_SI_CLE "); 
           
        	preparerSQL.getSQL().append(" and ist.d_is_date_creation = ( "); // cette ligne semble créer un lien cartésien
			preparerSQL.getSQL().append(" select max(ist2.d_is_date_creation) from is_inscription ist2 "); 
			preparerSQL.getSQL().append(" where ist2.L_IS_REFERENCE = DO.L_DO_CLE ");
			preparerSQL.getSQL().append(" AND ist2.L_IS_REF_SITE = DO.L_SI_CLE) ");
			   
			preparerSQL.getSQL().append(" AND (exists ");
			preparerSQL.getSQL().append(" (select * ");
			preparerSQL.getSQL().append(" from SIS_SITE_INSCRIPTION SIS ");
			preparerSQL.getSQL().append(" where SIS.L_IS_CLE = ist.L_IS_CLE ");
			preparerSQL.getSQL().append(" AND SIS.L_SI_CLE = ist.L_SI_CLE ");
			preparerSQL.getSQL().append(" AND SIS.L_IS_SITE = ?) or ");
			preparerSQL.addParametre(criteresRecherchePhoto.getSiteApplicable());
			preparerSQL.getSQL().append(" (ist.b_is_tous_site_applicable = 'yes' and not exists ");
			preparerSQL.getSQL().append(" (select * ");
			preparerSQL.getSQL().append(" from SIS_SITE_INSCRIPTION SIS ");
			preparerSQL.getSQL().append(" where SIS.L_IS_CLE = ist.L_IS_CLE ");
			preparerSQL.getSQL().append(" AND SIS.L_SI_CLE = ist.L_SI_CLE ");
			preparerSQL.getSQL().append(" AND SIS.L_IS_SITE = ?))) ");
			preparerSQL.addParametre(criteresRecherchePhoto.getSiteApplicable());
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "do.l_si_cle", criteresRecherchePhoto.getSiteOrigine());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "do.i_ca_cle", criteresRecherchePhoto.getCategorie());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "do.i_ty_cle", criteresRecherchePhoto.getType());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "do.i_na_cle", criteresRecherchePhoto.getNature());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.B_DO_ENREGISTREMENT_NUMERIQUE", criteresRecherchePhoto.getEnregistrementNumerique());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.i_do_fonde", criteresRecherchePhoto.getFonde());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.i_or_cle", criteresRecherchePhoto.getEndroit());
        OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, "DO.V_DO_LIEU", criteresRecherchePhoto.getDescriptif());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "do.v_do_assigne_a", criteresRecherchePhoto.getIntervenant());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "do.i_cr_cle", criteresRecherchePhoto.getLocalisation());

        OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.D_DO_DATE_CREATION", ">=", criteresRecherchePhoto.getDateAjoutDebut());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.D_DO_DATE_CREATION", "<", criteresRecherchePhoto.getDateAjoutFin());
        //La fonction TRUNC n'est plus utilisée en raison de problèmes de performance. Le SQL a été modifié, d'où l'ajout d'une journée
        //en raison des heures.
        if(criteresRecherchePhoto.getDateAjoutFin()!= null){
        	preparerSQL.getSQL().append(" + 1 ");
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.D_DO_DATE_DEBUT", ">=", criteresRecherchePhoto.getDateValideDebut());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.D_DO_DATE_DEBUT", "<", criteresRecherchePhoto.getDateValideFin());
        if(criteresRecherchePhoto.getDateValideFin()!= null){
        	preparerSQL.getSQL().append(" + 1 ");
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.D_DO_DATE_FIN", ">=", criteresRecherchePhoto.getDateTermineDebut());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.D_DO_DATE_FIN", "<", criteresRecherchePhoto.getDateTermineFin());
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
        
        
        OracleDAOUtils.ajouterChampSQL(preparerSQL, "do.L_DO_ORIGINE", criteresRecherchePhoto.getOrigine());

        OracleDAOUtils.ajouterChampSQL(preparerSQL, "do.i_st_cle", criteresRecherchePhoto.getStatut());
		
		//Recherche étendue dans les champs référence 1 et 2, peu importe dans quel critère de référence
		//la valeur a été saisie.
        if (StringUtils.isNotEmpty(criteresRecherchePhoto.getReference1())) {
            preparerSQL.getSQL().append(" AND (DO.V_DO_REFERENCE1 like ?  OR DO.V_DO_REFERENCE3 like ?)");
            preparerSQL.addParametreWhiteCard(criteresRecherchePhoto.getReference1().toUpperCase());
            preparerSQL.addParametreWhiteCard(criteresRecherchePhoto.getReference1().toUpperCase());
        }
		if (StringUtils.isNotEmpty(criteresRecherchePhoto.getReference2())) {
            preparerSQL.getSQL().append(" AND (DO.V_DO_REFERENCE1 like ? OR DO.V_DO_REFERENCE3 like ?)");
            preparerSQL.addParametreWhiteCard(criteresRecherchePhoto.getReference2().toUpperCase());
            preparerSQL.addParametreWhiteCard(criteresRecherchePhoto.getReference2().toUpperCase());
		}
	}
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche){
		CriteresRecherchePhoto criteresRecherchePhoto = (CriteresRecherchePhoto) criteresRecherche;
		PreparerSQL preparerSQL = new PreparerSQL();
  
        preparerSQL.getSQL().append("SELECT ");

        preparerSQL.getSQL().append( selectArgument() );
        // FROM
        preparerSQL.getSQL().append(" FROM lmm_lien_multimedia lm, mm_multimedia mm, v_do_dossier_ca_ty do ");
        ajoutFromSujet(preparerSQL, criteresRecherchePhoto);
        ajouterFrom(preparerSQL, criteresRecherchePhoto.getSiteApplicable(), "is_inscription ist, na_nature na");
        ajouterFrom(preparerSQL, criteresRecherchePhoto.getCaracteristique1(), "lsc_caracteristique lsc1");
        ajouterFrom(preparerSQL, criteresRecherchePhoto.getCaracteristique2(), "lsc_caracteristique lsc2");
        ajouterFrom(preparerSQL, criteresRecherchePhoto.getCaracteristique3(), "lsc_caracteristique lsc3");
        ajouterFrom(preparerSQL, criteresRecherchePhoto.getCaracteristique4(), "lsc_caracteristique lsc4");

        preparerSQL.getSQL().append( ajoutFrom() );
        
        // WHERE
        preparerSQL.getSQL().append(" WHERE ");
        
        preparerSQL.getSQL().append( whereLiaisonPhoto() );
        whereLiaisonDossierSujet(preparerSQL, criteresRecherchePhoto);
        whereCritereSujet(preparerSQL, subject, criteresRecherchePhoto);
        whereCritereDossier(preparerSQL, subject, criteresRecherchePhoto);
		
		preparerSQL.getSQL().append( groupBy() );
		preparerSQL.getSQL().append( havingArgument(criteresRecherchePhoto) );
		
        return preparerSQL;
	}

	private void ajouterFrom(PreparerSQL preparerSQL, long x, String table){
        if (x != 0 ){
            preparerSQL.getSQL().append(", ");
            preparerSQL.getSQL().append(table);
            preparerSQL.getSQL().append(" ");
         }
	}
}
