package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.CriteresRechercheUrgence;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.sql.ConstruireRechercheSQL;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.StringUtils;

public abstract class UrgenceSQL extends ConstruireRechercheSQL{

	protected abstract String selectArgument();
	
	protected String groupBy(){
		return "";
	}
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche)
    {
        CriteresRechercheUrgence criteresRechercheUrgence = (CriteresRechercheUrgence) criteresRecherche;
        PreparerSQL preparerSQL = new PreparerSQL();
        CardexUser user = (CardexUser) subject.getUser();
        CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();
        StringBuilder query = new StringBuilder();

        query.append("SELECT ");

        query.append(selectArgument());

        query.append(" FROM UR_URGENCE U, SI_SITE S, DO_DOSSIER D, SO_SOCIETE SO ");

        if (criteresRechercheUrgence.getConfidentialite() != 0)
        {
            query.append(" WHERE D.I_CC_CLE = ? AND ? >= ? ");
            preparerSQL.addParametre(criteresRechercheUrgence.getConfidentialite());
            preparerSQL.addParametre(privilege.getNiveauConfidentialite());
            preparerSQL.addParametre(criteresRechercheUrgence.getConfidentialite());
        }
        else
        {
            query.append(" WHERE (D.I_CC_CLE <= ? OR U.V_UR_CREE_PAR = ?)");
            preparerSQL.addParametre(privilege.getNiveauConfidentialite());
            preparerSQL.addParametre(user.getCode());
        }
        query.append(" AND U.L_SI_CLE = S.L_SI_CLE ");
        query.append(" AND D.L_DO_CLE = U.L_UR_REF_CLE ");
        query.append(" AND D.L_SI_CLE = U.L_UR_REF_SITE ");
        query.append(" AND SO.L_SO_CLE = U.L_SO_REF_CLE ");
        query.append(" AND SO.L_SI_CLE = U.L_SO_REF_SITE ");

        if (criteresRechercheUrgence.getClasse() != 0)
        {
            query.append("and U.I_CL_CLE = ?");
            preparerSQL.addParametre(criteresRechercheUrgence.getClasse());
        }
        if (criteresRechercheUrgence.getStatut() != 0)
        {
            query.append("and U.I_ST_CLE = ?");
            preparerSQL.addParametre(criteresRechercheUrgence.getStatut());
        }
        if (criteresRechercheUrgence.getMotif() != 0)
        {
            query.append("and U.L_UR_MOTIF = ?");
            preparerSQL.addParametre(criteresRechercheUrgence.getMotif());
        }
        if (StringUtils.isNotEmpty(criteresRechercheUrgence.getContact()))
        {
            query.append(" and " + OracleDAOUtils.champRecherche(preparerSQL, "U.V_UR_CONTACT", criteresRechercheUrgence.getContact().toUpperCase()));
        }
        if (StringUtils.isNotEmpty(criteresRechercheUrgence.getContactPrenom()))
        {
            query.append(" and " + OracleDAOUtils.champRecherche(preparerSQL, "U.V_UR_CONTACT_PRENOM", criteresRechercheUrgence.getContactPrenom().toUpperCase()));
        }
        if (StringUtils.isNotEmpty(criteresRechercheUrgence.getDistrict()))
        {
            query.append(" and " + OracleDAOUtils.champRecherche(preparerSQL, "U.V_UR_DISTRICT", criteresRechercheUrgence.getDistrict().toUpperCase()));
        }
        if (criteresRechercheUrgence.getEntite() != 0)
        {
            query.append("and S.I_EN_CLE = ?");
            preparerSQL.addParametre(criteresRechercheUrgence.getEntite());
        }
        if (StringUtils.isNotEmpty(criteresRechercheUrgence.getEvenement()))
        {
            query.append(" and " + OracleDAOUtils.champRecherche(preparerSQL, "U.V_UR_EVENEMENT", criteresRechercheUrgence.getEvenement().toUpperCase()));
        }
        if (StringUtils.isNotEmpty(criteresRechercheUrgence.getFonction()))
        {
            query.append(" and " + OracleDAOUtils.champRecherche(preparerSQL, "U.V_UR_FONCTION", criteresRechercheUrgence.getFonction().toUpperCase()));
        }
        if (StringUtils.isNotEmpty(criteresRechercheUrgence.getMatricule()))
        {
            query.append(" and U.V_UR_MATRICULE = ?");
            preparerSQL.addParametre(criteresRechercheUrgence.getMatricule());
        }
        if (StringUtils.isNotEmpty(criteresRechercheUrgence.getUnite()))
        {
            query.append(" and " + OracleDAOUtils.champRecherche(preparerSQL, "U.V_UR_UNITE", criteresRechercheUrgence.getUnite().toUpperCase()));
        }
        if (StringUtils.isNotEmpty(criteresRechercheUrgence.getVille()))
        {
            query.append(" and " + OracleDAOUtils.champRecherche(preparerSQL, "U.V_UR_VILLE", criteresRechercheUrgence.getVille().toUpperCase()));
        }
        if (criteresRechercheUrgence.getLienSiteSociete() != 0)
        {
            query.append(" and U.L_SO_REF_SITE = ?");
            preparerSQL.addParametre(criteresRechercheUrgence.getLienSiteSociete());
        }
        if (criteresRechercheUrgence.getLienSociete() != 0)
        {
            query.append(" and U.L_SO_REF_CLE = ?");
            preparerSQL.addParametre(criteresRechercheUrgence.getLienSociete());
        }
        if (criteresRechercheUrgence.getEntite() != 0)
        {
            query.append(" and S.I_EN_CLE = ?");
            preparerSQL.addParametre(criteresRechercheUrgence.getEntite());
        }
        if (criteresRechercheUrgence.getSiteOrigine() != 0)
        {
            query.append(" and U.L_SI_CLE = ?");
            preparerSQL.addParametre(criteresRechercheUrgence.getSiteOrigine());
        }
        // Ordre de tri
        if (StringUtils.isEmpty(criteresRechercheUrgence.getOrdreTriRecherche()))
        {
            query.append(" order by D.V_DO_NUMERO_DOSSIER, U.V_UR_CONTACT ASC");
        }
        else
        {
            query.append(" order by ?");
            preparerSQL.addParametre(criteresRechercheUrgence.getOrdreTriRecherche());
            // Ascendant ou descendant
            if (criteresRechercheUrgence.isOrdreCroissantRecherche())
            {
                query.append(" asc");
            }
            else
            {
                query.append(" desc");
            }
        }

        preparerSQL.setSQL(query.toString());
        return preparerSQL;
    }
		
}
