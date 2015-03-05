package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.CriteresRechercheSuivi;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

public abstract class SuiviSQL {

	protected abstract String selectArgument();
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche){
		CriteresRechercheSuivi criteresRechercheSuivi = (CriteresRechercheSuivi) criteresRecherche;
		PreparerSQL preparerSQL = new PreparerSQL();
		CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();
        StringBuilder query = new StringBuilder();
  
        query.append("SELECT ");

		query.append( selectArgument() );
		
		query.append("FROM SV_SUIVI P, V_DO_DOSSIER_CA_TY D ");
		query.append("WHERE ");
		query.append("((P.V_SV_CREE_PAR = ?" );
		preparerSQL.addParametre(user.getCode());
		query.append(" OR P.V_SV_INTERVENANT = ?)" );
		preparerSQL.addParametre(user.getCode());
		query.append(" OR P.I_CC_SUIVI <= ?)");
		preparerSQL.addParametre(privilege.getNiveauConfidentialite());
		
		query.append(" AND P.L_SV_REFERENCE = D.L_DO_CLE ");
		query.append(" AND P.L_SV_REF_SITE = D.L_SI_CLE ");
		 
		if (GlobalConstants.StatutEmissionSuivi.EMIS_PAR_MOI.equals(criteresRechercheSuivi.getStatutSuivi())) {
		    query.append(" and P.V_SV_DEMANDEUR = ?" );
		    preparerSQL.addParametre(user.getCode());
		} else {
			OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.V_SV_DEMANDEUR", criteresRechercheSuivi.getDemandeur());
		}
		
		if (GlobalConstants.StatutEmissionSuivi.EMIS_POUR_MOI.equals(criteresRechercheSuivi.getStatutSuivi())) {
		    query.append(" and P.V_SV_INTERVENANT = ?" );
		    preparerSQL.addParametre(user.getCode());
		} else {
			OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.V_SV_INTERVENANT", criteresRechercheSuivi.getIntervenant());
		}		
		
        //À l'exception de ce qui concerne l'utilisateur, on restreint la recherche au site de l'utilisateur.
        if (!GlobalConstants.StatutEmissionSuivi.EMIS_PAR_MOI.equals(criteresRechercheSuivi.getStatutSuivi())
        && !GlobalConstants.StatutEmissionSuivi.EMIS_POUR_MOI.equals(criteresRechercheSuivi.getStatutSuivi())){
			query.append(" AND D.L_SI_CLE = ?");
			preparerSQL.addParametre(user.getSite());
        }
        if (GlobalConstants.StatutApprobation.APPROUVE.equals(criteresRechercheSuivi.getStatutApprobation())) {
            query.append(" and P.V_SV_APPROBATEUR is not null ");
        }
        if (GlobalConstants.StatutApprobation.NON_APPROUVE.equals(criteresRechercheSuivi.getStatutApprobation())){
            query.append(" and P.V_SV_APPROBATEUR is null ");
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.I_TC_CLE", criteresRechercheSuivi.getActivite());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.L_SV_PO_ASSIGNE", criteresRechercheSuivi.getSecteurAssigne());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.L_SV_PO_ORIGINE", criteresRechercheSuivi.getSecteurOrigine());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.D_SV_DATE_CREATION", ">", criteresRechercheSuivi.getDateEmisDebut());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.D_SV_DATE_CREATION", "<=", criteresRechercheSuivi.getDateEmisFin());
        if(criteresRechercheSuivi.getDateEmisFin() != null){
        	query.append(" + 1 ");
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.D_SV_DATE_COMPLETEE", ">", criteresRechercheSuivi.getDateCompleteeDebut());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.D_SV_DATE_COMPLETEE", "<=", criteresRechercheSuivi.getDateCompleteeFin());
        if(criteresRechercheSuivi.getDateCompleteeFin() != null){
        	query.append(" + 1 ");
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.D_SV_DATE_PREVUE", ">", criteresRechercheSuivi.getDatePrevueDebut());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.D_SV_DATE_PREVUE", "<=", criteresRechercheSuivi.getDatePrevueFin());
        if(criteresRechercheSuivi.getDatePrevueFin() != null){
        	query.append(" + 1 ");
        }

        //On élimine les dossiers protégés par un mot de passe
		query.append(" AND D.V_DO_MOT_PASSE is null ");         

		 //On vérifie si le dossier est partagé et si l'utilisateur a le droit d'accéder au dossier.
		query.append(" AND (NOT EXISTS (SELECT * FROM LPD_PARTAGE_DOSSIER LPD WHERE LPD.L_LPD_REFERENCE = D.L_DO_CLE AND LPD.L_LPD_REF_SITE = D.L_SI_CLE AND LPD.C_LPD_GENRE = 'RES') ");
		query.append(" AND (D.I_CC_CLE <= ?");
		preparerSQL.addParametre(privilege.getNiveauConfidentialite());
		query.append(" AND D.V_DO_MOT_PASSE IS NULL)) ");
		 
		//Ordre de tri
        String ordreTriRecherche = criteresRechercheSuivi.getOrdreTriRecherche();
        if ( StringUtils.isEmpty(ordreTriRecherche) ) {
              query.append(" order by v_do_numero_dossier desc");
        }
        else {
              query.append(" order by ?");
              preparerSQL.addParametre(criteresRechercheSuivi.getOrdreTriRecherche());
              //Ascendant ou descendant
              if (criteresRechercheSuivi.isOrdreCroissantRecherche()){
                  query.append(" asc");
              }
              else{
                  query.append(" desc");
              }
        }

  		preparerSQL.setSQL(query.toString());
		return preparerSQL;
	}
		
}
