package com.lotoquebec.cardex.integration.dao.sql.recherche;

import com.lotoquebec.cardex.business.CriteresRecherchePSUMandat;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.integration.dao.OracleDAOUtils;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;
import com.lotoquebec.cardexCommun.integration.dao.sql.ConstruireRechercheSQL;
import com.lotoquebec.cardexCommun.util.StringUtils;

public abstract class PSUMandatSQL extends ConstruireRechercheSQL{

	protected abstract String selectArgument();
	
	public PreparerSQL construireSQL(CardexAuthenticationSubject subject, CriteresRecherche criteresRecherche){
		CriteresRecherchePSUMandat criteresRecherchePSUMandat = (CriteresRecherchePSUMandat) criteresRecherche;
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder query = new StringBuilder();
  
        query.append("SELECT ");

        query.append( selectArgument() );
        
		query.append("FROM PSU_MANDAT P, CS_CONSIGNATION_ACTION C ");
		query.append("WHERE ");
		query.append(" P.V_PSU_NUMERO_MANDAT = C.V_CS_PSU_NUMERO_MANDAT(+) ");
		
		if ((criteresRecherchePSUMandat.getEntite() != 0) && (criteresRecherchePSUMandat.getSiteOrigine() == 0)) {
			query.append(" AND P.L_SI_CLE IN (SELECT S.L_SI_CLE FROM SI_SITE S ");
			query.append(" WHERE S.I_EN_CLE = ?)" );
			preparerSQL.addParametre(criteresRecherchePSUMandat.getEntite());
		}
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.L_SI_CLE", criteresRecherchePSUMandat.getSiteOrigine());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.V_PSU_NUMERO_MANDAT", criteresRecherchePSUMandat.getNumeroMandat());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.V_PSU_CREE_PAR", criteresRecherchePSUMandat.getIntervenant());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "P.D_PSU_DATE_CREATION", ">=", criteresRecherchePSUMandat.getDateDebut());
		OracleDAOUtils.ajouterChampSQL(preparerSQL, query, "trunc(P.D_PSU_DATE_CREATION)", "<=", criteresRecherchePSUMandat.getDateFin());
		
		if(criteresRecherchePSUMandat.isApprouve() == true){
			query.append(" AND trim(P.V_PSU_APPROUVE_PAR) IS NOT NULL ");
		}
		if(criteresRecherchePSUMandat.isNonApprouve() == true){
			query.append(" AND (P.V_PSU_APPROUVE_PAR = '' OR P.V_PSU_APPROUVE_PAR IS NULL) ");
		}
		query.append( groupBy() );
		 
		// Ordre de tri
		String ordreTriRecherche = criteresRecherchePSUMandat.getOrdreTriRecherche();
		
		if (StringUtils.isEmpty(ordreTriRecherche)) {
			query.append(" order by P.V_PSU_NUMERO_MANDAT desc");
		} else {
			query.append(" order by ?");
			preparerSQL.addParametre(criteresRecherchePSUMandat.getOrdreTriRecherche());
			// Ascendant ou descendant
			if (criteresRecherchePSUMandat.isOrdreCroissantRecherche()) {
				query.append(" asc");
			} else {
				query.append(" desc");
			}
		}
	
		preparerSQL.setSQL(query.toString());
		return preparerSQL;
	}

}
