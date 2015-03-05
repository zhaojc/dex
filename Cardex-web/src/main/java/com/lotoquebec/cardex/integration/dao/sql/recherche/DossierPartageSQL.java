package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.CriteresRechercheDossier;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.sql.ConstruireRechercheSQL;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.CodeLangue;
import com.lotoquebec.cardexCommun.util.StringUtils;

public class DossierPartageSQL extends ConstruireRechercheSQL{

	protected String selectArgument(){
		return "";
	}
	
	protected String groupBy(){
		return "";
	}
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche){
		CriteresRechercheDossier criteresRechercheDossier = (CriteresRechercheDossier) criteresRecherche;
		int codeLangue = CodeLangue.valueOf(subject.getLocale()).intValue();
		PreparerSQL preparerSQL = new PreparerSQL();
        CardexUser user = (CardexUser)subject.getUser();
        StringBuilder query = new StringBuilder();

        query.append("SELECT DO.L_DO_CLE,DO.L_SI_CLE, ");
        query.append("DO.V_DO_NUMERO_DOSSIER,DO.I_CA_CLE,DO.I_SE_CLE, ");
        query.append(" DO.I_CC_CLE,DO.I_NH_CLE,DO.I_PE_CLE, ");
        query.append("DO.I_ST_CLE,DO.V_DO_MOT_PASSE,DO.D_DO_DATE_DEBUT, ");
        query.append(" DO.D_DO_DATE_FIN,DO.V_DO_DUREE, ");
        query.append("DO.V_DO_ANCIENNE_REFERENCE, DO.V_DO_REFERENCE_VIDEO, ");
        query.append(" DO.D_DO_DATE_RAPPORTEE,DO.I_DO_CLASSE, ");
        query.append("DO.I_DO_RACE,DO.V_DO_LIEU,DO.I_OR_CLE, DO.I_CR_CLE, ");
        query.append(" DO.V_DO_REFERENCE1,DO.V_DO_REFERENCE2,DO.L_DO_ORIGINE, ");
        query.append("DO.V_DO_ASSIGNE_A,DO.D_DO_DATE_ASSIGNATION, ");
        query.append(" DO.D_DO_DATE_EVENEMENT,DO.V_DO_CREE_PAR, ");
        query.append("DO.D_DO_DATE_CREATION,CA.I_TY_CLE,TY.I_NA_CLE, ");
        query.append(" NA.I_GE_CLE, GE.I_GE_ENTITE, DO.V_DO_REFERENCE3, ");
        query.append("DO.V_DO_REFERENCE4,DO.V_DO_REFERENCE5, DO.C_DO_FONDE, ");
        query.append(" DO.V_DO_REFERENCE6,DO.V_DO_REFERENCE7, ");
        query.append("DO.I_DO_FONDE,DO.I_RF_CLE, DO.B_DO_ENREGISTREMENT_CONSERVE, ");
        query.append(" DO.B_DO_ENREGISTREMENT_NUMERIQUE, TRGE.V_TR_DESCRIPTION as \"GENRE\", ");
        query.append("TRNA.V_TR_DESCRIPTION as \"NATURE\", ");
        query.append(" TRTY.V_TR_DESCRIPTION as \"TYPE\", " );
        query.append("TRCA.V_TR_DESCRIPTION as \"CATEGORIE\", ");
        query.append(" TRST.V_TR_DESCRIPTION as \"STATUT\" ");
        query.append(" FROM DO_DOSSIER DO, CA_CATEGORIE CA, ");
        query.append("NA_NATURE NA, TY_TYPE TY, GE_GENRE GE, ");
        query.append(" TR_TRADUCTION TRGE, TR_TRADUCTION TRNA, TR_TRADUCTION TRTY, ");
        query.append(" TR_TRADUCTION TRCA, TR_TRADUCTION TRST, " );
        query.append(" LPD_PARTAGE_DOSSIER LPD " );

        query.append(" WHERE ( NA.I_NA_CLE=TY.I_NA_CLE ) ");
        query.append(" AND ( TY.I_TY_CLE=CA.I_TY_CLE ) ");
        query.append(" AND ( CA.I_CA_CLE = DO.I_CA_CLE ) ");
        query.append(" AND GE.I_GE_CLE = NA.I_GE_CLE ");
        query.append(" AND ( NA.I_GE_CLE=TRGE.L_TR_CLE ) ");
        query.append(" AND ( NA.I_NA_CLE=TRNA.L_TR_CLE ) ");
        query.append(" AND ( TY.I_TY_CLE=TRTY.L_TR_CLE ) ");
        query.append(" AND (CA.I_CA_CLE=TRCA.L_TR_CLE ) " );
        query.append(" AND TRGE.I_LA_CLE = ? AND TRNA.I_LA_CLE = ? " );
        query.append(" AND TRTY.I_LA_CLE = ? AND TRCA.I_LA_CLE = ? " );
        query.append(" AND DO.I_ST_CLE = TRST.L_TR_CLE AND TRST.I_LA_CLE = ? " );
        preparerSQL.addParametre(codeLangue);
        preparerSQL.addParametre(codeLangue);
        preparerSQL.addParametre(codeLangue);
        preparerSQL.addParametre(codeLangue);
        preparerSQL.addParametre(codeLangue);
        
        OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "DO.I_ST_CLE", criteresRechercheDossier.getStatut());

        query.append(" AND LPD.V_LPD_NAME = ? and LPD.L_LPD_REFERENCE = DO.L_DO_CLE AND LPD.L_LPD_REF_SITE = DO.L_SI_CLE " );
        preparerSQL.addParametre(user.getCode());
        
            //Ordre de tri
        String ordreTriRecherche = criteresRechercheDossier.getOrdreTriRecherche();
        if ( StringUtils.isEmpty(ordreTriRecherche) ) {
            query.append(" order by d_do_date_debut desc");
        }
        else {
            query.append(" order by " + criteresRechercheDossier.getOrdreTriRecherche());
            //Ascendant ou descendant
            if (criteresRechercheDossier.isOrdreCroissantRecherche()){
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
