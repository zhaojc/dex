package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.CriteresRechercheDossier;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.sql.ConstruireRechercheSQL;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

public abstract class DossierSQL extends ConstruireRechercheSQL{

	protected abstract String selectArgument();

	protected String groupBy(){
		return "";
	}
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche){
		CriteresRechercheDossier criteresRechercheDossier = (CriteresRechercheDossier) criteresRecherche;
		PreparerSQL preparerSQL = new PreparerSQL();
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();

        preparerSQL.getSQL().append("SELECT distinct ");

        preparerSQL.getSQL().append( selectArgument() );

        preparerSQL.getSQL().append(" FROM V_DO_DOSSIER_CA_TY DO " );
        
        if (criteresRechercheDossier.getEntite() != 0
                && criteresRechercheDossier.getSiteOrigine() == 0 ) {
            preparerSQL.getSQL().append(", SI_SITE SI ");
        }
        if (criteresRechercheDossier.getSiteApplicable() != 0  && StringUtils.isEmpty(criteresRechercheDossier.getNumeroCardex())) {
            preparerSQL.getSQL().append(", IS_INSCRIPTION K ");
        }
        if (criteresRechercheDossier.getCategorie() != 0
        && (criteresRechercheDossier.getRechercherSousCategorie().equals(GlobalConstants.BooleanString.TRUE) 
         || criteresRechercheDossier.getRechercherTous().equals(GlobalConstants.BooleanString.TRUE))){
        	preparerSQL.getSQL().append(", LDC_LIEN_DOSSIER_CATEGORIE LDC ");
        }
        if (criteresRechercheDossier.getNumeroFicheSujet() != null
        && criteresRechercheDossier.getNumeroFicheSujet().trim().length() > 0
        && !criteresRechercheDossier.getNumeroFicheSujet().equals("*")) {
        	preparerSQL.getSQL().append(", LDD_LIEN_DOSSIER LDD, SU_SUJET SU ");
        }        
        if (criteresRechercheDossier.getClasse() != 0) {
            preparerSQL.getSQL().append(", LDD_LIEN_DOSSIER LDDSO, SO_SOCIETE SO ");
        }        
        //Recherche directe par numéro de dossier (Cardex).
        
        if (criteresRechercheDossier.getConfidentialite() != 0) {
        	// La deuxième partie doit être laisser au cas où l'usager aurait le rôle
        	// de confidentialité sans avoir la bonne valeur dans la table d'intervenant 
            preparerSQL.getSQL().append(" WHERE DO.I_CC_CLE = ? AND ? >= ?");
            preparerSQL.addParametre(criteresRechercheDossier.getConfidentialite());
            preparerSQL.addParametre(privilege.getNiveauConfidentialite());
            preparerSQL.addParametre(criteresRechercheDossier.getConfidentialite());                
        } else {
            preparerSQL.getSQL().append(" WHERE (DO.I_CC_CLE <= ? OR DO.V_DO_CREE_PAR = ?)");
            preparerSQL.addParametre(privilege.getNiveauConfidentialite());
			preparerSQL.addParametre(user.getCode());            
        }
        
        if (StringUtils.isNotEmpty(criteresRechercheDossier.getNumeroCardex())) {
            preparerSQL.getSQL().append(" AND DO.V_DO_NUMERO_DOSSIER like ? ");
            preparerSQL.addParametre(criteresRechercheDossier.getNumeroCardex());
            
            if (criteresRechercheDossier.getEntite() != 0
            && criteresRechercheDossier.getSiteOrigine() == 0) {
                preparerSQL.getSQL().append(" AND SI.L_SI_CLE = DO.L_SI_CLE AND SI.I_EN_CLE = ? ");
                preparerSQL.addParametre(criteresRechercheDossier.getEntite());
            }
            //On vérifie si le dossier est partagé. Si non, on applique le niveau de confidentialité.
            //Si oui, on vérifie si l'utilisateur a le droit d'accéder au dossier.
            preparerSQL.getSQL().append(" AND ((DO.V_DO_ASSIGNE_A = ?) OR " );
            preparerSQL.addParametre(user.getCode());
            preparerSQL.getSQL().append(" (NOT EXISTS (SELECT * FROM LPD_PARTAGE_DOSSIER LPD ");
            preparerSQL.getSQL().append("WHERE LPD.L_LPD_REFERENCE = DO.L_DO_CLE AND LPD.L_LPD_REF_SITE = DO.L_SI_CLE AND LPD.C_LPD_GENRE = 'RES') ");

            if (criteresRechercheDossier.getConfidentialite() != 0) {
            	// La deuxième partie doit être laisser au cas où l'usager aurait le rôle
            	// de confidentialité sans avoir la bonne valeur dans la table d'intervenant 
                preparerSQL.getSQL().append("  AND DO.I_CC_CLE = ? AND ? >= ? ))");
                preparerSQL.addParametre(criteresRechercheDossier.getConfidentialite());
                preparerSQL.addParametre(privilege.getNiveauConfidentialite());
                preparerSQL.addParametre(criteresRechercheDossier.getConfidentialite());                
            } else {
                preparerSQL.getSQL().append(" AND DO.I_CC_CLE <= ? )) ");
                preparerSQL.addParametre(privilege.getNiveauConfidentialite());
            }
            
        }  else {
            if (criteresRechercheDossier.getSiteApplicable() != 0) {
                preparerSQL.getSQL().append(" AND K.L_IS_REFERENCE = DO.L_DO_CLE AND K.L_IS_REF_SITE = DO.L_SI_CLE ");

                preparerSQL.getSQL().append(" and k.d_is_date_creation = ( "); // cette ligne semble créer un lien cartésien
                preparerSQL.getSQL().append(" select max(ist2.d_is_date_creation) from is_inscription ist2 ");
                preparerSQL.getSQL().append(" where ist2.L_IS_REFERENCE = DO.L_DO_CLE ");
                preparerSQL.getSQL().append(" AND ist2.L_IS_REF_SITE = DO.L_SI_CLE) ");

                preparerSQL.getSQL().append(" AND (exists ");
                preparerSQL.getSQL().append(" (select * ");
                preparerSQL.getSQL().append(" from SIS_SITE_INSCRIPTION SIS ");
                preparerSQL.getSQL().append(" where SIS.L_IS_CLE = K.L_IS_CLE ");
                preparerSQL.getSQL().append(" AND SIS.L_SI_CLE = K.L_SI_CLE ");
                preparerSQL.getSQL().append(" AND SIS.L_IS_SITE = ?) or ");
                preparerSQL.addParametre(criteresRechercheDossier.getSiteApplicable());
                preparerSQL.getSQL().append(" (K.b_is_tous_site_applicable = 'yes' and not exists ");
                preparerSQL.getSQL().append(" (select * ");
                preparerSQL.getSQL().append(" from SIS_SITE_INSCRIPTION SIS ");
                preparerSQL.getSQL().append(" where SIS.L_IS_CLE = K.L_IS_CLE ");
                preparerSQL.getSQL().append(" AND SIS.L_SI_CLE = K.L_SI_CLE ");
                preparerSQL.getSQL().append(" AND SIS.L_IS_SITE = ?))) ");
                preparerSQL.addParametre(criteresRechercheDossier.getSiteApplicable());
            }
            String intervenant = criteresRechercheDossier.getIntervenant();
            if ( StringUtils.isNotEmpty(intervenant) ) {
                preparerSQL.getSQL().append(" AND (DO.V_DO_ASSIGNE_A = ?)");
                preparerSQL.addParametre(criteresRechercheDossier.getIntervenant());
            }
            if (criteresRechercheDossier.getEntite() != 0
                    && criteresRechercheDossier.getSiteOrigine() == 0) {
                preparerSQL.getSQL().append(" AND SI.L_SI_CLE = DO.L_SI_CLE" );
                preparerSQL.getSQL().append(" AND SI.I_EN_CLE = ? " );
                preparerSQL.addParametre(criteresRechercheDossier.getEntite());
            }
			//Ajout du critère NumeroDossier ici.  Dans ce cas, on ne tient pas
			//compte de la classification du dossier, ni du site d'origine
			if (criteresRechercheDossier.getNumeroDossier() != null
                && criteresRechercheDossier.getNumeroDossier().trim().length() > 0
                && !criteresRechercheDossier.getNumeroDossier().equals("*")) {
                	 preparerSQL.getSQL().append(" AND DO.V_DO_ANCIENNE_REFERENCE = ? " );
                	 preparerSQL.addParametre(criteresRechercheDossier.getNumeroDossier().trim().toUpperCase());
                	 
                	 if (criteresRechercheDossier.getEntite() != 0) {
                        preparerSQL.getSQL().append(" AND DO.L_SI_CLE in ");
                        preparerSQL.getSQL().append("(SELECT SIDO.L_SI_CLE FROM SI_SITE SIDO WHERE SIDO.I_EN_CLE = ?) ");
                        preparerSQL.addParametre(criteresRechercheDossier.getEntite());
                    }
            }else{
            	    preparerSQL.getSQL().append(" AND  DO.V_DO_MOT_PASSE is null ");
                    if (criteresRechercheDossier.getGenre() != 0
                    && criteresRechercheDossier.getNature() == 0) {
                        preparerSQL.getSQL().append(" AND DO.I_GE_CLE = ? ");
                        preparerSQL.addParametre(criteresRechercheDossier.getGenre());
                    }
                    if (criteresRechercheDossier.getNature() != 0
                    && criteresRechercheDossier.getType() == 0) {
                        preparerSQL.getSQL().append(" AND DO.I_NA_CLE = ? " );
                        preparerSQL.addParametre(criteresRechercheDossier.getNature());
                    }
                    if (criteresRechercheDossier.getType() != 0
                    && criteresRechercheDossier.getCategorie() == 0) {
                        preparerSQL.getSQL().append(" AND DO.I_TY_CLE = ? " );
                        preparerSQL.addParametre(criteresRechercheDossier.getType());
                    }
                    if (criteresRechercheDossier.getCategorie() != 0) {

                    	if (criteresRechercheDossier.getRechercherSousCategorie().equals(GlobalConstants.BooleanString.TRUE)){ //On ne recherche que les sous catégories
                            preparerSQL.getSQL().append(" AND LDC.I_CA_CLE = ? ");
                            preparerSQL.addParametre(criteresRechercheDossier.getCategorie());
                            preparerSQL.getSQL().append(" AND DO.L_DO_CLE = LDC.L_DO_CLE ");
                            preparerSQL.getSQL().append(" AND DO.L_SI_CLE = LDC.L_SI_CLE ");
                    	}else{
                        	if (criteresRechercheDossier.getRechercherTous().equals(GlobalConstants.BooleanString.TRUE)){ //On recherche les sous catégories et les dossiers
                        		preparerSQL.getSQL().append(" AND (DO.I_CA_CLE = ? " );
	                            preparerSQL.getSQL().append(" OR LDC.I_CA_CLE = ? ) " );
	                            preparerSQL.addParametre(criteresRechercheDossier.getCategorie());
	                            preparerSQL.addParametre(criteresRechercheDossier.getCategorie());
	                            preparerSQL.getSQL().append(" AND DO.L_DO_CLE = LDC.L_DO_CLE(+) ");
	                            preparerSQL.getSQL().append(" AND DO.L_SI_CLE = LDC.L_SI_CLE(+) ");
                        	}else{
	                            preparerSQL.getSQL().append(" AND DO.I_CA_CLE = ? " ); //On ne recherche que les dossiers
	                            preparerSQL.addParametre(criteresRechercheDossier.getCategorie());
                        	}
                    	}
                    }

                    OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.L_SI_CLE", criteresRechercheDossier.getSiteOrigine());
            }
            OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.I_ST_CLE", criteresRechercheDossier.getStatut());
            OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.L_DO_ORIGINE", criteresRechercheDossier.getOrigine());
            OracleDAOUtils.ajouterChampSQL(preparerSQL, "rtrim(DO.B_DO_ENREGISTREMENT_CONSERVE)", criteresRechercheDossier.getEnregistrementConserve());
            OracleDAOUtils.ajouterChampSQL(preparerSQL, "rtrim(DO.B_DO_ENREGISTREMENT_NUMERIQUE)", criteresRechercheDossier.getEnregistrementNumerique());
            OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.I_SE_CLE", criteresRechercheDossier.getSeverite());
            // On teste les dates pour vérifier si une heure a été
            //indiquée.  Si oui, la commande est différente.
            OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.D_DO_DATE_DEBUT", ">=", criteresRechercheDossier.getDateDebutDu());
            OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.D_DO_DATE_FIN", ">=", criteresRechercheDossier.getDateFinDu());
            OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.D_DO_DATE_DEBUT", "<", criteresRechercheDossier.getDateDebutAu());
            //La fonction TRUNC n'est plus utilisée en raison de problèmes de performance. Le SQL a été modifié, d'où l'ajout d'une journée
            //en raison des heures.
            if(criteresRechercheDossier.getDateDebutAu() != null){
            	preparerSQL.getSQL().append(" + 1 ");
            }
            OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.D_DO_DATE_FIN", "<", criteresRechercheDossier.getDateFinAu());
            if(criteresRechercheDossier.getDateFinAu() != null){
            	preparerSQL.getSQL().append(" + 1 ");
            }
            OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.D_DO_DATE_CREATION", ">=", criteresRechercheDossier.getDateCreationDu());
            OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.D_DO_DATE_CREATION", "<", criteresRechercheDossier.getDateCreationAu());
            if(criteresRechercheDossier.getDateCreationAu() != null){
            	preparerSQL.getSQL().append(" + 1 ");
            }
            //Recherche étendue dans tous les champs référence, peu importe dans quel critère de référence
            //la valeur a été saisie.
            if (StringUtils.isNotEmpty(criteresRechercheDossier.getReference1())) {
                preparerSQL.getSQL().append(" AND (DO.V_DO_REFERENCE1 like ?  OR DO.V_DO_REFERENCE3 like ?  OR DO.V_DO_REFERENCE5 like ?)");
                preparerSQL.addParametreWhiteCard(criteresRechercheDossier.getReference1().toUpperCase());
                preparerSQL.addParametreWhiteCard(criteresRechercheDossier.getReference1().toUpperCase());
                preparerSQL.addParametreWhiteCard(criteresRechercheDossier.getReference1().toUpperCase());
            }
			if (StringUtils.isNotEmpty(criteresRechercheDossier.getReference2())) {
                preparerSQL.getSQL().append(" AND (DO.V_DO_REFERENCE1 like ? OR DO.V_DO_REFERENCE3 like ? OR DO.V_DO_REFERENCE5 like ?)");
                preparerSQL.addParametreWhiteCard(criteresRechercheDossier.getReference2().toUpperCase());
                preparerSQL.addParametreWhiteCard(criteresRechercheDossier.getReference2().toUpperCase());
                preparerSQL.addParametreWhiteCard(criteresRechercheDossier.getReference2().toUpperCase());
			}
            if (StringUtils.isNotEmpty(criteresRechercheDossier.getReference3())) {
                preparerSQL.getSQL().append(" AND (DO.V_DO_REFERENCE1 like ? OR DO.V_DO_REFERENCE3 like ? OR DO.V_DO_REFERENCE5 like ?)");
                preparerSQL.addParametreWhiteCard(criteresRechercheDossier.getReference3().toUpperCase());
                preparerSQL.addParametreWhiteCard(criteresRechercheDossier.getReference3().toUpperCase());
                preparerSQL.addParametreWhiteCard(criteresRechercheDossier.getReference3().toUpperCase());
            }
            OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, "DO.V_DO_LIEU", criteresRechercheDossier.getDescriptif());
            OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.I_DO_FONDE", criteresRechercheDossier.getFonde());
            OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, "upper(DO.v_do_reference_video)", criteresRechercheDossier.getReferenceVideo());
            OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.I_OR_CLE", criteresRechercheDossier.getEndroit());
            OracleDAOUtils.ajouterChampSQL(preparerSQL, "DO.I_CR_CLE", criteresRechercheDossier.getLocalisation());
            
            if (criteresRechercheDossier.getJeu() != 0 ){
                preparerSQL.getSQL().append(" AND (do.l_do_cle, do.l_si_cle) ");
                preparerSQL.getSQL().append(" in (select je.l_do_cle, je.l_ljd_ref_site ");
                preparerSQL.getSQL().append(" from LJD_LIEN_JEU_DOSSIER je ");
                preparerSQL.getSQL().append(" where je.i_je_cle = ?)" );
                preparerSQL.addParametre(criteresRechercheDossier.getJeu());
                
            }else if (criteresRechercheDossier.getEntite() != 0
            && criteresRechercheDossier.getTypeJeu() != 0){
                preparerSQL.getSQL().append(" AND (do.l_do_cle, do.l_si_cle) ");
                preparerSQL.getSQL().append(" in (select je.l_do_cle, je.l_ljd_ref_site ");
                preparerSQL.getSQL().append(" from LJD_LIEN_JEU_DOSSIER je ");
                preparerSQL.getSQL().append(" where je.i_je_cle in (select t.l_tv_valeur ");
                preparerSQL.getSQL().append("   from tv_table_valeur t ");
                preparerSQL.getSQL().append("   where t.v_tv_code = 'Jeux' ");
                preparerSQL.getSQL().append("   AND 2 = (select count(*) ");
                preparerSQL.getSQL().append("   	from ctv_critere_table_valeur ctv ");
                preparerSQL.getSQL().append(" 		where ctv.l_tv_valeur = t.l_tv_valeur ");
    			OracleDAOUtils.ajouterChampSQL(preparerSQL, "ctv.l_tv_parent_valeur", new long[]{criteresRechercheDossier.getEntite(),criteresRechercheDossier.getTypeJeu()});
    			preparerSQL.getSQL().append("))) ");                
            }
            
            if (StringUtils.isNotEmpty(criteresRechercheDossier.getNumeroFicheSujet())) {
                preparerSQL.getSQL().append(" AND SU.V_SU_REFERENCE_3 = ? "); 
                preparerSQL.getSQL().append(" AND (LDD.L_DO_CLE = DO.L_DO_CLE AND LDD.L_DO_SITE = DO.L_SI_CLE AND");
                preparerSQL.getSQL().append(" LDD.C_DO_GENRE = 'DO' AND");
                preparerSQL.getSQL().append(" LDD.L_LDD_DOSSIER_ASSOCIE = SU.L_SU_CLE AND");
                preparerSQL.getSQL().append(" LDD.L_LDD_SITE = SU.L_SI_CLE AND LDD.C_LDD_GENRE = 'SU') ");
                preparerSQL.addParametre(criteresRechercheDossier.getNumeroFicheSujet().toUpperCase());
            }
                 
            if (criteresRechercheDossier.getClasse() != 0) {
                preparerSQL.getSQL().append(" AND SO.I_CL_CLE = ? "); 
                preparerSQL.getSQL().append(" AND (LDDSO.L_DO_CLE = DO.L_DO_CLE AND LDDSO.L_DO_SITE = DO.L_SI_CLE AND");
                preparerSQL.getSQL().append(" LDDSO.C_DO_GENRE = 'DO' AND");
                preparerSQL.getSQL().append(" LDDSO.L_LDD_DOSSIER_ASSOCIE = SO.L_SO_CLE AND");
                preparerSQL.getSQL().append(" LDDSO.L_LDD_SITE = SO.L_SI_CLE AND LDDSO.C_LDD_GENRE = 'SO') ");
                preparerSQL.addParametre(criteresRechercheDossier.getClasse());
            }

            //On vérifie si le dossier est partagé. Si non, on applique le niveau de confidentialité.
            //Si oui, on vérifie si l'utilisateur a le droit d'accéder au dossier.
            preparerSQL.getSQL().append(" AND ((DO.V_DO_MOT_PASSE IS NULL ");
            preparerSQL.getSQL().append(" AND DO.V_DO_ASSIGNE_A = ?) OR " );
            preparerSQL.addParametre(user.getCode());
            preparerSQL.getSQL().append(" (NOT EXISTS (SELECT * FROM LPD_PARTAGE_DOSSIER LPD ");
            preparerSQL.getSQL().append("WHERE LPD.L_LPD_REFERENCE = DO.L_DO_CLE AND LPD.L_LPD_REF_SITE = DO.L_SI_CLE AND LPD.C_LPD_GENRE = 'RES') ");
            preparerSQL.getSQL().append(" AND DO.V_DO_MOT_PASSE IS NULL ");
            if (criteresRechercheDossier.getConfidentialite() != 0) {
            	preparerSQL.getSQL().append(" )) ");
            }else{
                preparerSQL.getSQL().append(" AND DO.I_CC_CLE <= ? )) ");
                preparerSQL.addParametre(privilege.getNiveauConfidentialite());
            }
        } //Numéro de dossier (Cardex)
        //Ordre de tri
        String ordreTriRecherche = criteresRechercheDossier.getOrdreTriRecherche();
        if ( StringUtils.isEmpty(ordreTriRecherche) ) {
            preparerSQL.getSQL().append(" order by d_do_date_debut desc");
        }
        else {
            preparerSQL.getSQL().append(" order by ?");
            preparerSQL.addParametre(criteresRechercheDossier.getOrdreTriRecherche());
            //Ascendant ou descendant
            if (criteresRechercheDossier.isOrdreCroissantRecherche()){
                preparerSQL.getSQL().append(" asc");
            }
            else{
                preparerSQL.getSQL().append(" desc");
            }
        }
        
        return preparerSQL;
	}

	public PreparerSQL construireSQLDossierVisite(CardexAuthenticationSubject subject){
		PreparerSQL preparerSQL = new PreparerSQL();
        preparerSQL.getSQL().append("SELECT ");

        preparerSQL.getSQL().append( selectArgument() );

        preparerSQL.getSQL().append(" FROM DO_DOSSIER DO " );
        preparerSQL.getSQL().append(" WHERE DO.I_CA_CLE = ? ");
        preparerSQL.addParametre(GlobalConstants.CategorieClientMystere.ECHANTILLON);
        preparerSQL.getSQL().append(" AND DO.D_DO_DATE_FIN IS NULL ");

        return preparerSQL;
	}
	
}
