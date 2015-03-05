package com.lotoquebec.cardex.integration.dao.sql;

import com.lotoquebec.cardex.business.PSUMandat;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class VerificationMandatSQL {

	public PreparerSQL obtenirPreparerSQL(PSUMandat criteria, String genreFichier, String action){
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT * from psu_mandat p ");
		query.append(" where p.d_psu_date_debut <= sysdate and p.d_psu_date_fin >= sysdate ");
		query.append(" and p.v_psu_approuve_par is not null and p.i_st_cle = 359 ");
		query.append(" and P.I_TA_CLE = ? ");
		preparerSQL.addParametre(Integer.valueOf(action));
		
		//Évaluation de la recherche selon le type de fichier et les critères concernés.
		if(genreFichier.equals(GlobalConstants.GenreFichier.DOSSIER)){
			if (action.equals(GlobalConstants.TypeAction.CONSULTATION)){
				OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.V_PSU_NAME", criteria.getIntervenant());
				query.append(" AND (P.V_PSU_DO_NUMERO_DOSSIER = ? ");
				preparerSQL.addParametre(criteria.getNumeroCardex());

				if(StringUtils.isNotEmpty(criteria.getNumeroDossier())){
					query.append(" OR P.V_PSU_DO_ANCIENNE_REFERENCE = ? ");
					preparerSQL.addParametre(criteria.getNumeroDossier());
				}
				query.append(") ");
			}
			
			if (action.equals(GlobalConstants.TypeAction.MISE_A_JOUR)){
				if (StringUtils.isNotEmpty(criteria.getNumeroCardex())) {
					query.append(" AND P.V_PSU_DO_NUMERO_DOSSIER = ? ");
					preparerSQL.addParametre(criteria.getNumeroCardex());
				}else{
					if(StringUtils.isNotEmpty(criteria.getNumeroDossier())){
						query.append(" AND P.V_PSU_DO_ANCIENNE_REFERENCE = ? ");
						preparerSQL.addParametre(criteria.getNumeroDossier());
					}
				}
			}
			if ((action.equals(GlobalConstants.TypeAction.LIAISON))
			|| (action.equals(GlobalConstants.TypeAction.SUPPRESSION))){
				query.append(" AND (P.V_PSU_DO_NUMERO_DOSSIER = ? ");
				preparerSQL.addParametre(criteria.getNumeroCardex());
				
				if(StringUtils.isNotEmpty(criteria.getNumeroDossier())){
					query.append(" OR P.V_PSU_DO_ANCIENNE_REFERENCE = ? ");
					preparerSQL.addParametre(criteria.getNumeroDossier());
				}
				query.append(") ");
			}
			if (action.equals(GlobalConstants.TypeAction.RECHERCHE)){
				if((criteria.getCategorie() != 0) && (criteria.getFonde() != 0)){
				    query.append(" AND P.I_PSU_CA_CLE = ? ");
					preparerSQL.addParametre(criteria.getCategorie());
					query.append(" AND P.I_PSU_FO_CLE = ? ");
				    preparerSQL.addParametre(criteria.getFonde());
				}
				OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.V_PSU_NAME", criteria.getIntervenant());
			}
			if (action.equals(GlobalConstants.TypeAction.AJOUT)){
			  	//Un insertion peut être vérifée par catégorie, fondé et intervenant
				OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.I_PSU_CA_CLE", criteria.getCategorie());

				if ( criteria.getFonde() != 0) { //On prend aussi "Indéterminé"
					 query.append(" AND (P.I_PSU_FO_CLE = ?  or P.I_PSU_FO_CLE = ?) ");
					 preparerSQL.addParametre(criteria.getFonde());
					 preparerSQL.addParametre(Integer.valueOf(GlobalConstants.Fonde.INDETERMINE));
				}
				if ( StringUtils.isNotEmpty(criteria.getIntervenant())) {
					query.append(" AND (P.V_PSU_NAME = ? or P.V_PSU_NAME is null) ");
					preparerSQL.addParametre(criteria.getIntervenant());
				}
			}
		}//Dossier
		
		if(genreFichier.equals(GlobalConstants.GenreFichier.SUJET)){
			if ( StringUtils.isNotEmpty(criteria.getFicheSujet())) {
				query.append(" AND P.V_PSU_SU_REFERENCE_3 = ? ");
				preparerSQL.addParametre(criteria.getFicheSujet().toUpperCase());
			}else{
				OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "upper(P.V_PSU_SU_NOM)", criteria.getSujetNom().toUpperCase());
				OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "upper(P.V_PSU_SU_PRENOM)", criteria.getSujetPrenom().toUpperCase());
			}
		}//Sujet
		
		if(genreFichier.equals(GlobalConstants.GenreFichier.SOCIETE)){
			if ( StringUtils.isNotEmpty(criteria.getFicheSociete())) {
				query.append(" AND P.V_PSU_SO_REFERENCE_3 = ? ");
				preparerSQL.addParametre(criteria.getFicheSociete());
			}else{
				OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "upper(P.V_PSU_SO_NOM)", criteria.getSocieteNom().toUpperCase());
			}
		}//Société
		
		if(genreFichier.equals(GlobalConstants.GenreFichier.VEHICULE)){
			if (!action.equals(GlobalConstants.TypeAction.AJOUT)){
				if ( StringUtils.isNotEmpty(criteria.getImmatriculation())) {
					query.append(" AND upper(P.V_PSU_VE_IMMATRICULATION) = ? ");
					preparerSQL.addParametre(criteria.getImmatriculation().toUpperCase());
				}else{
					OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.V_PSU_VE_PROVINCE", criteria.getProvince());
				}
			}else{
				query.append(" AND (upper(P.V_PSU_VE_IMMATRICULATION) = ? ");
				preparerSQL.addParametre(criteria.getImmatriculation().toUpperCase());
				query.append(" OR P.V_PSU_VE_PROVINCE = ?) ");
				preparerSQL.addParametre(criteria.getProvince());
		    }
		}//Véhicule
		
		if(genreFichier.equals(GlobalConstants.GenreFichier.NARRATION)){
		 	//Pour les narrations, on retourne tous les mandats de narration. Les mots clés
			//retournés seront comparés avec la narration en cours.
			query.append(" AND trim(P.C_PSU_GENRE_FICHIER) = ? ");
			preparerSQL.addParametre(GlobalConstants.GenreFichier.NARRATION);
		}//Narration		
		
		preparerSQL.setSQL( query.toString() );
		return preparerSQL;		
	}
}
