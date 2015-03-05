package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.CriteresRechercheJournal;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.sql.ConstruireRechercheSQL;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

public abstract class JournalSQL extends ConstruireRechercheSQL{

	protected abstract String selectArgument();
	
	protected String groupBy(){
		return "";
	}
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche){
		CriteresRechercheJournal criteresRechercheJournal = (CriteresRechercheJournal) criteresRecherche;
		PreparerSQL preparerSQL = new PreparerSQL();		
        CardexUser user = (CardexUser)subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();
        StringBuilder query = new StringBuilder();
  
        query.append("SELECT ");

        query.append( selectArgument() );
        
        query.append(" FROM V_DO_DOSSIER_CA_TY DO, CO_COMMENTAIRE2 C ");
        if (criteresRechercheJournal.getEntite() != 0 && criteresRechercheJournal.getSite() == 0 ) {
            query.append(", SI_SITE SI ");
        }
        String secteur = criteresRechercheJournal.getSecteur();
        if (StringUtils.isNotEmpty(secteur)){
                  query.append(", in_intervenant i ");
        }
        query.append(" WHERE (DO.I_CC_CLE <= ? OR DO.V_DO_ASSIGNE_A = ?)" );
        preparerSQL.addParametre(privilege.getNiveauConfidentialite());
		preparerSQL.addParametre(user.getCode());         
		query.append(" AND DO.I_NA_CLE = ?");
		
        if(criteresRechercheJournal.getEntite() == Long.parseLong(GlobalConstants.Entite.LOTO_QUEBEC)){
            //Vérifier si c'est GARDA et mettre JOURNAL_SECURITE dans nature
            preparerSQL.addParametre(GlobalConstants.Nature.JOURNAL_ENQUETES);
        }else{
            preparerSQL.addParametre(GlobalConstants.Nature.JOURNAL);
        }
        query.append(" AND (DO.L_DO_CLE = C.L_CO_REFERENCE");
        query.append(" AND DO.L_SI_CLE = C.L_CO_REF_SITE ) ");
        
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "DO.V_DO_ASSIGNE_A", criteresRechercheJournal.getIntervenant());
        
        if (criteresRechercheJournal.getEntite() != 0 && criteresRechercheJournal.getSite() == 0) {
            query.append(" AND SI.L_SI_CLE = DO.L_SI_CLE");
            query.append(" AND SI.I_EN_CLE = ?");
            preparerSQL.addParametre(criteresRechercheJournal.getEntite());
        }
        if (criteresRechercheJournal.getType() != 0 && criteresRechercheJournal.getCategorie() == 0) {
            query.append(" AND DO.I_TY_CLE = ?");
            preparerSQL.addParametre(criteresRechercheJournal.getType());
        }
        
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "DO.I_CA_CLE", criteresRechercheJournal.getCategorie());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "DO.L_SI_CLE", criteresRechercheJournal.getSite());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "C.D_CO_DATE_CREATION", ">=", criteresRechercheJournal.getDateCreationDu());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "C.D_CO_DATE_CREATION", "<", criteresRechercheJournal.getDateCreationAu());
        //La fonction TRUNC n'est plus utilisée en raison de problèmes de performance. Le SQL a été modifié, d'où l'ajout d'une journée
        //en raison des heures.
        if(criteresRechercheJournal.getDateCreationAu() != null){
        	query.append(" + 1 ");
        }
        
        if (StringUtils.isNotEmpty(secteur)){
			query.append(" AND (i.l_in_secteur = ?");
			query.append(" AND i.NAME = DO.V_DO_ASSIGNE_A)");
			preparerSQL.addParametre(secteur);
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "DO.I_OR_CLE", criteresRechercheJournal.getEndroit());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "DO.I_CR_CLE", criteresRechercheJournal.getLocalisation());
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "DO.I_DO_FONDE", criteresRechercheJournal.getFonde());
        OracleDAOUtils.ajouterChampLikeSQL(preparerSQL, query, "DO.V_DO_LIEU", criteresRechercheJournal.getDescriptif());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "DO.V_DO_ANCIENNE_REFERENCE", criteresRechercheJournal.getNumeroDossier().toUpperCase());

        if (StringUtils.isNotEmpty(criteresRechercheJournal.getNumeroEmploye())) {
            query.append(" AND (DO.V_DO_REFERENCE1 like ?  OR DO.V_DO_REFERENCE3 like ?  OR DO.V_DO_REFERENCE5 like ?)");
            preparerSQL.addParametreWhiteCard(criteresRechercheJournal.getNumeroEmploye().toUpperCase());
            preparerSQL.addParametreWhiteCard(criteresRechercheJournal.getNumeroEmploye().toUpperCase());
            preparerSQL.addParametreWhiteCard(criteresRechercheJournal.getNumeroEmploye().toUpperCase());
        }
		if (StringUtils.isNotEmpty(criteresRechercheJournal.getReference2())) {
            query.append(" AND (DO.V_DO_REFERENCE1 like ? OR DO.V_DO_REFERENCE3 like ? OR DO.V_DO_REFERENCE5 like ?)");
            preparerSQL.addParametreWhiteCard(criteresRechercheJournal.getReference2().toUpperCase());
            preparerSQL.addParametreWhiteCard(criteresRechercheJournal.getReference2().toUpperCase());
            preparerSQL.addParametreWhiteCard(criteresRechercheJournal.getReference2().toUpperCase());
		}
        if (StringUtils.isNotEmpty(criteresRechercheJournal.getReference3())) {
            query.append(" AND (DO.V_DO_REFERENCE1 like ? OR DO.V_DO_REFERENCE3 like ? OR DO.V_DO_REFERENCE5 like ?)");
            preparerSQL.addParametreWhiteCard(criteresRechercheJournal.getReference3().toUpperCase());
            preparerSQL.addParametreWhiteCard(criteresRechercheJournal.getReference3().toUpperCase());
            preparerSQL.addParametreWhiteCard(criteresRechercheJournal.getReference3().toUpperCase());
        }
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "DO.L_DO_ORIGINE", criteresRechercheJournal.getOrigine());
            
        //Ordre de tri
        String ordreTriRecherche = criteresRechercheJournal.getOrdreTriRecherche();
        if ( StringUtils.isEmpty(ordreTriRecherche) ) {
            query.append(" order by d_do_date_debut desc");
        } else {
            query.append(" order by ?");
            preparerSQL.addParametre(criteresRechercheJournal.getOrdreTriRecherche());
            //Ascendant ou descendant
            if (criteresRechercheJournal.isOrdreCroissantRecherche()){
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
