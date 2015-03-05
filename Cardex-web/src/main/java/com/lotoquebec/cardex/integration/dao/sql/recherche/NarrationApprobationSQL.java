package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.CriteresRechercheNarration;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.sql.ConstruireRechercheSQL;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

public abstract class NarrationApprobationSQL extends ConstruireRechercheSQL{

	protected abstract String selectArgument();
	
	protected String groupBy(){
		return "";
	}
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche){
		CriteresRechercheNarration criteresRechercheNarration = (CriteresRechercheNarration) criteresRecherche;
		PreparerSQL preparerSQL = new PreparerSQL();
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append( selectArgument() );
        query.append("FROM CO_COMMENTAIRE2 P, V_DO_DOSSIER_CA_TY D ");
        query.append("WHERE ");
        query.append(" P.L_CO_REFERENCE = D.L_DO_CLE ");
        query.append(" AND P.L_CO_REF_SITE = D.L_SI_CLE ");
        query.append(" AND P.C_CO_REF_GENRE = 'DO' ");
        
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.D_CO_DATE_CREATION", ">=", criteresRechercheNarration.getDateCreationDebut());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "trunc(P.D_CO_DATE_CREATION)", "<=", criteresRechercheNarration.getDateCreationFin());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.D_CO_APPROBATION", ">=", criteresRechercheNarration.getDateApprobationDebut());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.D_CO_APPROBATION", "<", criteresRechercheNarration.getDateApprobationFin());
        if(criteresRechercheNarration.getDateApprobationFin() != null ){
		    	query.append(" + 1 ");
        }
        
        query.append(" AND P.I_CC_COMMENTAIRE <= ?");
        preparerSQL.addParametre(privilege.getNiveauConfidentialite());

        if ( StringUtils.isNotEmpty(criteresRechercheNarration.getStatutApprobation())){
        //Si le critère APPROUVE_PAR_MOI (approbations effectuées par l'utilisateur) est sélectionné,
        //la recherche s'applique à tous les sites de l'entité de l'utilisateur (tous les casinos).
        //Sinon, elle est restreinte au site de l'utilisateur.
            if (criteresRechercheNarration.getStatutApprobation().equals(GlobalConstants.StatutApprobation.NON_APPROUVE)){
                query.append(" AND P.V_CO_APPROBATEUR IS NULL ");
                query.append(" AND P.L_SI_CLE = ?" );
                preparerSQL.addParametre(user.getSite());
            }
            if (criteresRechercheNarration.getStatutApprobation().equals(GlobalConstants.StatutApprobation.APPROUVE)){
                query.append(" AND P.V_CO_APPROBATEUR IS NOT NULL ");
                query.append(" AND P.L_SI_CLE = ?");
                preparerSQL.addParametre(user.getSite());
            }
            if (criteresRechercheNarration.getStatutApprobation().equals(GlobalConstants.StatutApprobation.APPROUVE_PAR_MOI)){
                query.append(" AND P.V_CO_APPROBATEUR = ? ");
                query.append(" AND P.L_SI_CLE IN (SELECT S.L_SI_CLE FROM SI_SITE S ");
                query.append(" WHERE S.I_EN_CLE = ?)" );
                preparerSQL.addParametre(user.getCode());
                preparerSQL.addParametre(user.getEntite());
            }
        }else{
        	// Aucun statut, nous prenous le site de l'utilisateur
        	query.append(" AND P.L_SI_CLE = ?");
        	preparerSQL.addParametre(user.getSite());
        }
        //On sélectionne les utilisateurs du secteur retourné par les critères.
        if ( criteresRechercheNarration.getSecteur() != 0 && StringUtils.isEmpty(criteresRechercheNarration.getIntervenant())){
            query.append(" AND P.V_CO_CREE_PAR IN (SELECT I.NAME FROM IN_INTERVENANT I WHERE ");
            query.append(" I.l_in_secteur = ?)" );
            preparerSQL.addParametre(criteresRechercheNarration.getSecteur());
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.V_CO_CREE_PAR", criteresRechercheNarration.getIntervenant());

	    //On vérifie si le dossier est partagé et si l'utilisateur a le droit d'accéder au dossier.
		query.append(" AND (NOT EXISTS (SELECT * FROM LPD_PARTAGE_DOSSIER LPD WHERE LPD.L_LPD_REFERENCE = D.L_DO_CLE AND LPD.L_LPD_REF_SITE = D.L_SI_CLE AND LPD.C_LPD_GENRE = 'RES') ");
		query.append(" AND D.I_CC_CLE <= ?");
		query.append(" AND D.V_DO_MOT_PASSE IS NULL) ");
        preparerSQL.addParametre(privilege.getNiveauConfidentialite());
		
		//Ordre de tri
		query.append(" order by v_do_numero_dossier desc");

		preparerSQL.setSQL(query.toString());
		return preparerSQL;
	}
		
}
