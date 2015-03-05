package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.CriteresRechercheSujet;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

public abstract class SujetSQL extends SujetAgeCriteresRecherche{

	protected abstract String selectArgument();
	
	protected String groupBy(){
		return "";
	}
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche){
		CriteresRechercheSujet criteresRechercheSujet = (CriteresRechercheSujet) criteresRecherche;
		PreparerSQL preparerSQL = new PreparerSQL();
		CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();
  
        preparerSQL.getSQL().append("SELECT ");
        
        preparerSQL.getSQL().append(selectArgument() );
        
		 preparerSQL.getSQL().append("FROM SU_SUJET S, SI_SITE SI ");
		 
		 if (criteresRechercheSujet.getVille() != 0 || criteresRechercheSujet.getProvince() != 0 || criteresRechercheSujet.getPays() != 0){
			  preparerSQL.getSQL().append(", AD_ADRESSE A ");
		 }
		 if (criteresRechercheSujet.getVille() == 0 && (criteresRechercheSujet.getProvince() != 0 || criteresRechercheSujet.getPays() != 0)){
			preparerSQL.getSQL().append(", PR_PROVINCE P, VI_VILLE V ");
		 }
		 if (criteresRechercheSujet.getRole() != 0){
			  preparerSQL.getSQL().append(", LDD_LIEN_DOSSIER D ");
		 }
		 if (criteresRechercheSujet.getCaracteristique1() != 0){
			 preparerSQL.getSQL().append(", lsc_caracteristique lsc1 ");
		 }
		 if (criteresRechercheSujet.getCaracteristique2() != 0){
			 preparerSQL.getSQL().append(", lsc_caracteristique lsc2 ");
		 }
		 if (criteresRechercheSujet.getCaracteristique3() != 0){
			 preparerSQL.getSQL().append(", lsc_caracteristique lsc3 ");
		 }
		 if (criteresRechercheSujet.getCaracteristique4() != 0){
			 preparerSQL.getSQL().append(", lsc_caracteristique lsc4 ");
		 }

		 if (criteresRechercheSujet.getConfidentialite() != 0){
			 preparerSQL.getSQL().append("WHERE S.I_CC_CLE = ? AND ? >= ? ");
			 preparerSQL.addParametre(criteresRechercheSujet.getConfidentialite());
			 preparerSQL.addParametre(privilege.getNiveauConfidentialite());
			 preparerSQL.addParametre(criteresRechercheSujet.getConfidentialite());
			 
		 }else{
			 preparerSQL.getSQL().append(" WHERE (S.I_CC_CLE <= ? OR S.V_SU_CREE_PAR = ?)" );
			 preparerSQL.addParametre(privilege.getNiveauConfidentialite());
			 preparerSQL.addParametre(user.getCode());
		 }
         preparerSQL.getSQL().append(" AND S.L_SI_CLE = SI.L_SI_CLE ");
         OracleDAOUtils.ajouterChampSQL(preparerSQL, "S.V_SU_REFERENCE_3", criteresRechercheSujet.getNumeroFiche().toUpperCase());
        // Un sujet peut être recherché par le nom, soit d'une manière phonétique, soit avec
        // des caractères de contrôles (% et _).  
		 if (StringUtils.isNotEmpty(criteresRechercheSujet.getNom())){
			  OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, "UPPER(S.V_SU_NOM)", criteresRechercheSujet.getNom());
		 }else{
			 if (StringUtils.isNotEmpty(criteresRechercheSujet.getNomPhonetique())){
				 preparerSQL.getSQL().append(" AND (S.C_SU_SNDX_NOM = soundex(?)" );
				 preparerSQL.addParametre(criteresRechercheSujet.getNomPhonetique());
				 preparerSQL.getSQL().append(" OR " + OracleDAOUtils.champRecherche(preparerSQL, "S.V_SU_NOM", criteresRechercheSujet.getNomPhonetique().toUpperCase()) + " ) " );
			 }
		 }
		 if (StringUtils.isNotEmpty(criteresRechercheSujet.getPrenomPhonetique())){
			  preparerSQL.getSQL().append(" AND (S.C_SU_SNDX_PRENOM = soundex(?)" );
			  preparerSQL.addParametre(criteresRechercheSujet.getPrenomPhonetique());
			  //Pour le prénom on ajoute la recherche alphabétique
			  preparerSQL.getSQL().append(" OR " + OracleDAOUtils.champRecherche(preparerSQL, "S.V_SU_PRENOM", criteresRechercheSujet.getPrenomPhonetique().toUpperCase()) + " ) " );
		 }
		 if (StringUtils.isNotEmpty(criteresRechercheSujet.getAlias())){
			  preparerSQL.getSQL().append(" AND S.v_Su_Surnom is not null and ( S.C_SU_SNDX_SURNOM = soundex(?)" );
			  preparerSQL.addParametre(criteresRechercheSujet.getAlias());
			  //Pour l'alias on ajoute la recherche alphabétique
			  preparerSQL.getSQL().append(" OR " + OracleDAOUtils.champRecherche(preparerSQL, "S.v_Su_Surnom", criteresRechercheSujet.getAlias()) + " ) " );
		 }

		 OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, "S.V_SU_NO_PASSEPORT", criteresRechercheSujet.getPasseport().toUpperCase());
		 OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, "S.V_SU_ASSURANCE_MALADIE", criteresRechercheSujet.getNumeroAssuranceMaladie());
		 OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, "S.V_SU_PERMIS_CONDUIRE", criteresRechercheSujet.getNumeroPermisConduire());
		 OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, "S.V_SU_REFERENCE_2", criteresRechercheSujet.getNumeroClient());
		 OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, "S.V_SU_REFERENCE_1", criteresRechercheSujet.getReference());
		 
		 long age = criteresRechercheSujet.getAge();
		 assignerAgeCriteresRecherche(preparerSQL, criteresRechercheSujet, age);
		 
		 OracleDAOUtils.ajouterChampSQL(preparerSQL, "S.I_SX_CLE", criteresRechercheSujet.getSexe());
		 OracleDAOUtils.ajouterChampSQL(preparerSQL, "S.I_NT_CLE", criteresRechercheSujet.getEthnie());
		 OracleDAOUtils.ajouterChampSQL(preparerSQL, "S.I_LS_CLE", criteresRechercheSujet.getLangue());
		 OracleDAOUtils.ajouterChampSQL(preparerSQL, "S.I_RA_CLE", criteresRechercheSujet.getRace());
		 OracleDAOUtils.ajouterChampSQL(preparerSQL, "S.I_ST_CLE", criteresRechercheSujet.getStatut());
		 OracleDAOUtils.ajouterChampSQL(preparerSQL, "S.I_SE_CLE", criteresRechercheSujet.getSeverite());
		 OracleDAOUtils.ajouterChampSQL(preparerSQL, "S.I_SE_CLE_CASINO", criteresRechercheSujet.getSeveriteCasino());
		 OracleDAOUtils.ajouterChampSQL(preparerSQL, "S.I_SE_CLE_AUTRES", criteresRechercheSujet.getSeveriteAutres());
		 if (criteresRechercheSujet.getCaracteristique1() != 0) {
            preparerSQL.getSQL().append(" and ( s.l_su_cle = lsc1.l_lsc_reference ) and ( s.l_si_cle = lsc1.l_lsc_ref_site ) ");
        }
        if (criteresRechercheSujet.getCaracteristique2() != 0) {
            preparerSQL.getSQL().append(" and ( s.l_su_cle = lsc2.l_lsc_reference ) and ( s.l_si_cle = lsc2.l_lsc_ref_site ) ");
        }
        if (criteresRechercheSujet.getCaracteristique3() != 0) { 
            preparerSQL.getSQL().append(" and ( s.l_su_cle = lsc3.l_lsc_reference ) and ( s.l_si_cle = lsc3.l_lsc_ref_site ) ");
        }
        if (criteresRechercheSujet.getCaracteristique4() != 0) {
            preparerSQL.getSQL().append(" and ( s.l_su_cle = lsc4.l_lsc_reference ) and ( s.l_si_cle = lsc4.l_lsc_ref_site ) ");
        }

		 //On encadre les caractéristiques avec des parenthèses pour la condition OR 
		 if (criteresRechercheSujet.getCaracteristique1() != 0 ) {
			 preparerSQL.getSQL().append(" and (lsc1.l_cr_cle = ?" );
		 	 preparerSQL.addParametre(criteresRechercheSujet.getCaracteristique1());
		 	 
			 if (criteresRechercheSujet.getCaracteristique2() != 0){
				 if (GlobalConstants.Operateur.OU.equals( criteresRechercheSujet.isOr1() )){
			 		preparerSQL.getSQL().append(" or lsc2.l_cr_cle = ?");
			 		preparerSQL.addParametre(criteresRechercheSujet.getCaracteristique2());
			    }else{
				     preparerSQL.getSQL().append(") and (lsc2.l_cr_cle = ?");
				     preparerSQL.addParametre(criteresRechercheSujet.getCaracteristique2());
			    }
				if (criteresRechercheSujet.getCaracteristique3() != 0){
				 	if(GlobalConstants.Operateur.OU.equals(criteresRechercheSujet.isOr2())){
					   preparerSQL.getSQL().append(" or lsc3.l_cr_cle = ?");
					   preparerSQL.addParametre(criteresRechercheSujet.getCaracteristique3());
				    }else{
				       preparerSQL.getSQL().append(") and (lsc3.l_cr_cle = ?");
				 	   preparerSQL.addParametre(criteresRechercheSujet.getCaracteristique3());
				    }
					if (criteresRechercheSujet.getCaracteristique4() != 0){
					 	if(GlobalConstants.Operateur.OU.equals(criteresRechercheSujet.isOr3())){
						   preparerSQL.getSQL().append(" or lsc4.l_cr_cle = ?");
						   preparerSQL.addParametre(criteresRechercheSujet.getCaracteristique4());
					    }else{
					 	   preparerSQL.getSQL().append(") and (lsc4.l_cr_cle = ?");
					 	   preparerSQL.addParametre(criteresRechercheSujet.getCaracteristique4());
					    }
					 }
			    }
			 }
		 	preparerSQL.getSQL().append(" )");
		 }

		 if (criteresRechercheSujet.getEntite() != 0){
			 preparerSQL.getSQL().append(" and si.i_en_cle = ?" );
			 preparerSQL.addParametre(criteresRechercheSujet.getEntite());
		 }
		 if (criteresRechercheSujet.getSiteOrigine() != 0){
			 preparerSQL.getSQL().append(" and s.l_si_cle = ?" );
			 preparerSQL.addParametre(criteresRechercheSujet.getSiteOrigine());
		 }
		 if (criteresRechercheSujet.getRole() != 0){
			 preparerSQL.getSQL().append(" AND D.L_LDD_DOSSIER_ASSOCIE = S.L_SU_CLE AND D.L_LDD_SITE = S.L_SI_CLE AND D.I_RO_CLE = ?");
			 preparerSQL.addParametre(criteresRechercheSujet.getRole());
		 }
		 if (criteresRechercheSujet.getVille() != 0 || criteresRechercheSujet.getProvince() != 0 || criteresRechercheSujet.getPays() != 0){
			 preparerSQL.getSQL().append(" AND A.L_AD_REFERENCE = S.L_SU_CLE AND A.L_AD_REF_SITE = S.L_SI_CLE " );
			 preparerSQL.getSQL().append(" AND A.C_AD_REF_GENRE = 'SU' ");
		 }
		 if (criteresRechercheSujet.getVille() != 0){
			 preparerSQL.getSQL().append(" AND A.L_VI_CLE = ?");
			 preparerSQL.addParametre(criteresRechercheSujet.getVille());
		 }else{
			 if (criteresRechercheSujet.getProvince() != 0){
				preparerSQL.getSQL().append(" AND P.L_PR_CLE = ?" );
				preparerSQL.getSQL().append(" AND P.L_PR_CLE = V.L_PR_CLE " );
				preparerSQL.getSQL().append(" AND V.L_VI_CLE = A.L_VI_CLE " );
				preparerSQL.addParametre(criteresRechercheSujet.getProvince());
			 }else{
				 if (criteresRechercheSujet.getPays() != 0){
					 preparerSQL.getSQL().append(" AND P.I_PA_CLE = ?" );
					 preparerSQL.getSQL().append(" AND P.L_PR_CLE = V.L_PR_CLE " );
					 preparerSQL.getSQL().append(" AND V.L_VI_CLE = A.L_VI_CLE ");
					 preparerSQL.addParametre(criteresRechercheSujet.getPays());
				 }
			 }
		 }
		 
		 //Traitement de la date de naissance
		 if (StringUtils.isNotEmpty(criteresRechercheSujet.getDateNaissance())){
			  //La recherche permet de retrouver toutes les dates de naissance pour
			  //une année donnée, un mois et une année ou un mois. On décortique le
			  //critère en conséquence.
			  String naissance = criteresRechercheSujet.getDateNaissance();
			  
			  if (naissance.length() == 10){
				  //Date spécifique
				  if(!naissance.substring(0,4).equals("0000") && !naissance.substring(5,7).equals("00") && !naissance.substring(8,10).equals("00")){
					  preparerSQL.getSQL().append(" AND S.D_SU_DATE_NAISSANCE = TO_DATE(?,'YYYY-MM-DD')" );
					  preparerSQL.addParametre(naissance);
				  }
				  //Mois spécifique
				  if(naissance.substring(0,4).equals("0000") && !naissance.substring(5,7).equals("00") && naissance.substring(8,10).equals("00")){
					  preparerSQL.getSQL().append(" AND to_char(S.D_SU_DATE_NAISSANCE,'MM') = ?" );
					  preparerSQL.addParametre(naissance.substring(5,7));
				  }
				  //Mois et jour spécifiques
				  if(naissance.substring(0,4).equals("0000") && !naissance.substring(5,7).equals("00") && !naissance.substring(8,10).equals("00")){
					  preparerSQL.getSQL().append(" AND to_char(S.D_SU_DATE_NAISSANCE,'MM-DD') = ?" );
					  preparerSQL.addParametre(naissance.substring(5,10));
				  }
				  //Année spécifique
				  if(!naissance.substring(0,4).equals("0000") && naissance.substring(5,7).equals("00")){
					  preparerSQL.getSQL().append(" AND S.D_SU_DATE_NAISSANCE between TO_DATE(?','YYYY-MM-DD') and TO_DATE(?,'YYYY-MM-DD') + 365" );
					  preparerSQL.addParametre(naissance.substring(0,4)+"-01-01");
					  preparerSQL.addParametre(naissance.substring(0,4)+"-01-01");
				  }
				  //Année et mois spécifiques
				  if(!naissance.substring(0,4).equals("0000") && !naissance.substring(5,7).equals("00") && naissance.substring(8,10).equals("00")){
					  preparerSQL.getSQL().append(" AND S.D_SU_DATE_NAISSANCE between TO_DATE(?,'YYYY-MM-DD') and TO_DATE(?,'YYYY-MM-DD') + 30" );
					  preparerSQL.addParametre(naissance.substring(0,7)+"-01");
					  preparerSQL.addParametre(naissance.substring(0,7)+"-01");
				  }
			  }else{
			  	System.err.println("DateNaissance="+naissance+" Usager:"+((CardexUser)subject.getUser()).getCode());
			  }
		 }
		 
		 //Ordre de tri
		 if (StringUtils.isEmpty(criteresRechercheSujet.getOrdreTriRecherche())){
			  preparerSQL.getSQL().append(" order by convert(upper(V_SU_NOM), 'US7ASCII'), convert(upper(V_SU_PRENOM), 'US7ASCII') asc");
		  }else{
			  preparerSQL.getSQL().append(" order by ?");
			  preparerSQL.addParametre(criteresRechercheSujet.getOrdreTriRecherche());
			  //Ascendant ou descendant
			  if (criteresRechercheSujet.isOrdreCroissantRecherche()){
				  preparerSQL.getSQL().append(" asc");
			  }else{
				  preparerSQL.getSQL().append(" desc");
			  }
		}
		return preparerSQL;
	}
		
}
