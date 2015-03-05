package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.CriteresRechercheConsignation;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.sql.ConstruireRechercheSQL;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

public abstract class ConsignationSQL extends ConstruireRechercheSQL{

	protected abstract String selectArgument();
	
	protected String groupBy(){
		return "";
	}
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche){
		CriteresRechercheConsignation criteresRechercheConsignation = (CriteresRechercheConsignation) criteresRecherche;
		PreparerSQL preparerSQL = new PreparerSQL();
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();
        
        StringBuilder query = new StringBuilder();
  
        query.append("SELECT ");

        query.append( selectArgument() );
        
		query.append("FROM CN_CONSIGNATION P, V_DO_DOSSIER_CA_TY D ");
		query.append("WHERE ");
		query.append(" P.L_CN_REF_CLE = D.L_DO_CLE ");
		query.append(" AND P.L_CN_REF_SITE = D.L_SI_CLE AND P.C_CN_REF_GENRE = 'DO' " );
		
		if ((criteresRechercheConsignation.getEntite() != 0) && (criteresRechercheConsignation.getSiteOrigine() == 0)) {
			query.append(" AND P.L_SI_CLE IN (SELECT S.L_SI_CLE FROM SI_SITE S ");
			query.append(" WHERE S.I_EN_CLE = ?)" );
			preparerSQL.addParametre(criteresRechercheConsignation.getEntite());
		}else{
			if (criteresRechercheConsignation.getEntite() == 0) {
				query.append(" AND P.L_SI_CLE IN (SELECT S.L_SI_CLE FROM SI_SITE S WHERE S.I_EN_CLE = ?)" );
				preparerSQL.addParametre(user.getEntite());
			}else{
				OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.L_SI_CLE", criteresRechercheConsignation.getSiteOrigine());
			}
		}
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.I_TN_CLE", criteresRechercheConsignation.getTypeConsignation());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.I_DN_CLE", criteresRechercheConsignation.getDenomination());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.I_DE_CLE", criteresRechercheConsignation.getDevise());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "D.I_GE_CLE", criteresRechercheConsignation.getGenre());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "D.I_NA_CLE", criteresRechercheConsignation.getNature());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "D.I_TY_CLE", criteresRechercheConsignation.getType());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "D.I_CA_CLE", criteresRechercheConsignation.getCategorie());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.V_CN_CREE_PAR", criteresRechercheConsignation.getIntervenant());
		OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, query, "P.V_CN_NUMERO_SERIE", criteresRechercheConsignation.getNumeroSerie());
		//On ne tient compte ni des accents ni des majuscules.
		OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, query, "convert(upper(P.V_CN_DESCRIPTION),'US7ASCII')", criteresRechercheConsignation.getDescription());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.D_CN_DATE_CREATION", ">=", criteresRechercheConsignation.getDateDebut());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "trunc(P.D_CN_DATE_CREATION)", "<=", criteresRechercheConsignation.getDateFin());
		OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, query, "upper(P.V_CN_MARQUE)", criteresRechercheConsignation.getMarque().toUpperCase());
        OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, query, "upper(P.V_CN_MODELE)", criteresRechercheConsignation.getModele().toUpperCase());
        OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, query, "upper(P.V_CN_FOURNISSEUR)", criteresRechercheConsignation.getFournisseur().toUpperCase());

        if(criteresRechercheConsignation.getApprouve().equals("on")){
        	query.append(" AND P.C_CN_APPROUVABLE = 'yes' AND P.C_CN_APPROUVE = 'yes' ");
        }
        if(criteresRechercheConsignation.getNonApprouve().equals("on")){
			query.append(" AND P.C_CN_APPROUVABLE = 'yes' AND P.C_CN_APPROUVE != 'yes' ");
		}
        
		//On vérifie si le dossier est partagé et si l'utilisateur a le droit d'accéder au dossier.
		query.append(" AND (NOT EXISTS (SELECT * FROM LPD_PARTAGE_DOSSIER LPD WHERE LPD.L_LPD_REFERENCE = D.L_DO_CLE AND LPD.L_LPD_REF_SITE = D.L_SI_CLE AND LPD.C_LPD_GENRE = 'RES') ");
		query.append(" AND D.I_CC_CLE <= ?");
		query.append(" AND D.V_DO_MOT_PASSE IS NULL) ");
		preparerSQL.addParametre(privilege.getNiveauConfidentialite());
	
		//Ordre de tri
		String ordreTriRecherche = criteresRechercheConsignation.getOrdreTriRecherche();
		
		if ( StringUtils.isEmpty(ordreTriRecherche) ) {
			query.append(" order by D_CN_DATE_CREATION desc");
		}else {
			query.append(" order by ?");
			preparerSQL.addParametre(criteresRechercheConsignation.getOrdreTriRecherche());
			//Ascendant ou descendant
			if (criteresRechercheConsignation.isOrdreCroissantRecherche()){
				query.append(" asc");
			}else{
				query.append(" desc");
			}
		}
		preparerSQL.setSQL(query.toString());
		
		return preparerSQL;
	}
		
}
