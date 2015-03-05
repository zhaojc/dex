package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.CriteresRechercheNarration;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

public abstract class NarrationRechercheSQL {

	protected abstract String selectArgument();
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche){
		CriteresRechercheNarration criteresRechercheNarration = (CriteresRechercheNarration) criteresRecherche;
		PreparerSQL preparerSQL = new PreparerSQL();
		CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append( selectArgument() );

        query.append("FROM CO_COMMENTAIRE2 P, V_DO_DOSSIER_CA_TY D ");
        
        if (criteresRechercheNarration.getEntite() != 0
               && criteresRechercheNarration.getSite() == 0 ) {
           query.append(", SI_SITE SI ");
        }
        
        if (criteresRechercheNarration.getGenre() != 0
               && criteresRechercheNarration.getNature() == 0 ) {
           query.append(", NA_NATURE N ");
        }
        
        query.append("WHERE ");
        query.append(" P.L_CO_REFERENCE = D.L_DO_CLE ");
        query.append(" AND P.L_CO_REF_SITE = D.L_SI_CLE ");
        query.append(" AND P.C_CO_REF_GENRE = 'DO' ");

        String ordreAffichage = criteresRechercheNarration.getOrdreAffichage();
        //Selon le choix, on recherche avec une date soit les dossiers, soit les narrations.
		if ( criteresRechercheNarration.getDateCreationDebut() != null) {
			if ( GlobalConstants.OrdreAffichageRechercheNarration.AFFICHAGE_PAR_DATE_CREATION_NARRATION.equals(ordreAffichage) ) {
				OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.D_CO_DATE_CREATION", ">=", criteresRechercheNarration.getDateCreationDebut());
			}else{
	            OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "D.D_DO_DATE_CREATION", ">=", criteresRechercheNarration.getDateCreationDebut());
			}
		}
		if ( criteresRechercheNarration.getDateCreationFin() != null) {
			if ( GlobalConstants.OrdreAffichageRechercheNarration.AFFICHAGE_PAR_DATE_CREATION_NARRATION.equals(ordreAffichage) ) {
			    OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.D_CO_DATE_CREATION", "<", criteresRechercheNarration.getDateCreationFin());
			    if(criteresRechercheNarration.getDateCreationFin() != null){
			    	query.append(" + 1 ");
			    }
			}else{
				OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "D.D_DO_DATE_CREATION", "<", criteresRechercheNarration.getDateCreationFin());
			    if(criteresRechercheNarration.getDateCreationFin() != null){
			    	query.append(" + 1 ");
			    }
			}
		}
		query.append(" AND (P.I_CC_COMMENTAIRE <= ? OR P.V_CO_CREE_PAR = ?)" );
		preparerSQL.addParametre(privilege.getNiveauConfidentialite());
		preparerSQL.addParametre(user.getCode());
		
        if ( criteresRechercheNarration.getSecteur() != 0 && StringUtils.isEmpty(criteresRechercheNarration.getIntervenant())){
            query.append(" AND P.V_CO_CREE_PAR IN (SELECT I.NAME FROM IN_INTERVENANT I WHERE ");
            query.append(" I.l_in_secteur = ?)" );
            preparerSQL.addParametre(criteresRechercheNarration.getSecteur());
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.V_CO_CREE_PAR", criteresRechercheNarration.getIntervenant());
        
        if (criteresRechercheNarration.getEntite() != 0 && criteresRechercheNarration.getSite() == 0) {
           query.append(" AND SI.L_SI_CLE = D.L_SI_CLE AND SI.I_EN_CLE = ?" );
           preparerSQL.addParametre(criteresRechercheNarration.getEntite());
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "D.L_SI_CLE", criteresRechercheNarration.getSite());

        if (criteresRechercheNarration.getGenre() != 0 && criteresRechercheNarration.getNature() == 0 ) {
           query.append("AND D.I_NA_CLE = N.I_NA_CLE AND N.I_GE_CLE = ?");
           preparerSQL.addParametre(criteresRechercheNarration.getGenre());
        }
        if (criteresRechercheNarration.getNature() != 0 && criteresRechercheNarration.getType() == 0) {
        	query.append(" AND D.I_NA_CLE = ?");
        	preparerSQL.addParametre(criteresRechercheNarration.getNature());
        }
        if (criteresRechercheNarration.getType() != 0 && criteresRechercheNarration.getCategorie() == 0) {
        	query.append(" AND D.I_TY_CLE = ?");
        	preparerSQL.addParametre(criteresRechercheNarration.getType());
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "D.I_CA_CLE", criteresRechercheNarration.getCategorie());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "D.I_OR_CLE", criteresRechercheNarration.getEndroit());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "D.I_CR_CLE", criteresRechercheNarration.getLocalisation());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "D.L_DO_ORIGINE", criteresRechercheNarration.getOrigine());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "D.I_DO_FONDE", criteresRechercheNarration.getFonde());
     	 
		//Recherche indexée. On vérifie le type de recherche et on construit le critère ensuite.
		//La recherche ne tient pas compte de la casse ni des accents.
		String mots = "";
		if (criteresRechercheNarration.getTypeRecherche().equals(GlobalConstants.TypeRecherche.TOUS) || 
		      StringUtils.isNotEmpty(criteresRechercheNarration.getTypeRecherche())){
		      //Tous les mots doivent être retrouvés dans les narrations.
	          if ( StringUtils.isNotEmpty(criteresRechercheNarration.getMotCle1())) {
	          	  mots = "{" + criteresRechercheNarration.getMotCle1() + "}";
	          }
	          if ( StringUtils.isNotEmpty(criteresRechercheNarration.getMotCle2())) {
	              if(StringUtils.isNotEmpty(mots)) {
	          	     mots = mots + " AND " + "{" + criteresRechercheNarration.getMotCle2() + "}";
	              }else{
	          	     mots = "{" + criteresRechercheNarration.getMotCle2() + "}";
	              }
	          }	          
	          if ( StringUtils.isNotEmpty(criteresRechercheNarration.getMotCle3())) {
	              if(StringUtils.isNotEmpty(mots)) {
	          	     mots = mots + " AND " + "{" + criteresRechercheNarration.getMotCle3() + "}";
	              }else{
	          	     mots = "{" + criteresRechercheNarration.getMotCle3() + "}";
	              }
	          }
	      }
		  if (criteresRechercheNarration.getTypeRecherche().equals(GlobalConstants.TypeRecherche.ANY)){
		      //Au moins un des mots doit être retrouvé dans les narrations.
	          if ( StringUtils.isNotEmpty(criteresRechercheNarration.getMotCle1())) {
	          	  mots = "{" + criteresRechercheNarration.getMotCle1() + "}";
	          }
	          if ( StringUtils.isNotEmpty(criteresRechercheNarration.getMotCle2())) {
	              if(StringUtils.isNotEmpty(mots)) {
	          	     mots = mots + " OR {" + criteresRechercheNarration.getMotCle2() + "}";
	              }else{
	          	     mots = "{" + criteresRechercheNarration.getMotCle2() + "}";
	              }
	          }	          
	          if ( StringUtils.isNotEmpty(criteresRechercheNarration.getMotCle3())) {
	              if(StringUtils.isNotEmpty(mots)) {
	          	     mots = mots + " OR {" + criteresRechercheNarration.getMotCle3() + "}";
	              }else{
	          	     mots = "{" + criteresRechercheNarration.getMotCle3() + "}";
	              }
	          }
	      }
		  if (criteresRechercheNarration.getTypeRecherche().equals(GlobalConstants.TypeRecherche.DERIVES)){
		      //Tous les mots retrouvés dans les narrations sont dérivés du critère.
	          if ( StringUtils.isNotEmpty(criteresRechercheNarration.getMotCle1())) {
	          	  mots = "${" + criteresRechercheNarration.getMotCle1() + "}";
	          }
	          if ( StringUtils.isNotEmpty(criteresRechercheNarration.getMotCle2())) {
	              if(StringUtils.isNotEmpty(mots)) {
	          	     mots = mots + " AND ${" + criteresRechercheNarration.getMotCle2() + "}";
	              }else{
	          	     mots = "${" + criteresRechercheNarration.getMotCle2() + "}";
	              }
	          }	          
	          if ( StringUtils.isNotEmpty(criteresRechercheNarration.getMotCle3())) {
	              if(StringUtils.isNotEmpty(mots)) {
	          	     mots = mots + " AND ${" + criteresRechercheNarration.getMotCle3() + "}";
	              }else{
	          	     mots = "${" + criteresRechercheNarration.getMotCle3() + "}";
	              }
	          }
	      }
		  if (criteresRechercheNarration.getTypeRecherche().equals(GlobalConstants.TypeRecherche.LETTRES)){
		      //Tous les mots retrouvés dans les narrations contiennent les lettres du critère.
	          if ( StringUtils.isNotEmpty(criteresRechercheNarration.getMotCle1())) {
	          	  mots = "%"+criteresRechercheNarration.getMotCle1()+"%";
	          }
	          if ( StringUtils.isNotEmpty(criteresRechercheNarration.getMotCle2())) {
	              if(StringUtils.isNotEmpty(mots)) {
	          	     mots = mots + " AND %"+criteresRechercheNarration.getMotCle2()+"%";
	              }else{
	          	     mots = "%"+criteresRechercheNarration.getMotCle2()+"%";
	              }
	          }	          
	          if ( StringUtils.isNotEmpty(criteresRechercheNarration.getMotCle3())) {
	              if(StringUtils.isNotEmpty(mots)) {
	          	     mots = mots + " AND %"+criteresRechercheNarration.getMotCle3()+"%";
	              }else{
	          	     mots = "%"+criteresRechercheNarration.getMotCle3()+"%";
	              }
	          }
	      }
         if ( StringUtils.isNotEmpty(mots)) {
        	 query.append(" AND contains(P.CLOB_CO_TEXTE_NORMAL, ?, 1) > 0 ");
             preparerSQL.addParametre(mots);
         }
         
		//On vérifie si le dossier est partagé et si l'utilisateur a le droit d'accéder au dossier.
		query.append(" AND (NOT EXISTS (SELECT * FROM LPD_PARTAGE_DOSSIER LPD WHERE LPD.L_LPD_REFERENCE = D.L_DO_CLE AND LPD.L_LPD_REF_SITE = D.L_SI_CLE AND LPD.C_LPD_GENRE = 'RES') ");
		query.append(" AND D.I_CC_CLE <= ? AND D.V_DO_MOT_PASSE IS NULL) " );
		preparerSQL.addParametre(privilege.getNiveauConfidentialite());

		preparerSQL.setSQL(query.toString());
		return preparerSQL;
	}
		
}
