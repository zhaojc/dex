package com.lotoquebec.cardex.integration.dao.sql.rapport;

import com.lotoquebec.cardex.business.vo.rapport.AmbulanceDossierRapportVO;
import com.lotoquebec.cardexCommun.integration.dao.jdbc.PreparerSQL;


public class AmbulanceDossierRapportSQL{
	
	public static PreparerSQL obtenirPreparerSQLRapportAmbulance(AmbulanceDossierRapportVO ambulanceDossierRapportVO){
		PreparerSQL preparerSQL = new PreparerSQL();
		StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT ");
		query.append("P.L_CO_REFERENCE, ");
		query.append("P.L_CO_REF_SITE ");
		query.append("FROM CO_COMMENTAIRE2 P ");
        
		query.append("WHERE ");
		// On vérifie les dates. Si elles sont nulles, on prend les six
		// derniers mois.
		if (ambulanceDossierRapportVO.getDateDebutDu() != null) {
			query.append(" P.D_CO_DATE_CREATION >= ?");
			preparerSQL.addParametre(ambulanceDossierRapportVO.getDateDebutDu());
		} else {
			query.append(" P.D_CO_DATE_CREATION >= SYSDATE - 130 ");
		}
		
		if (ambulanceDossierRapportVO.getDateDebutAu() != null) {
			query.append(" AND P.D_CO_DATE_CREATION < ? + 1");
			preparerSQL.addParametre(ambulanceDossierRapportVO.getDateDebutAu());
		} else {
			query.append(" AND P.D_CO_DATE_CREATION < SYSDATE + 1");
		}
		
		query.append(" AND P.C_CO_REF_GENRE = 'DO' ");
		
		if (ambulanceDossierRapportVO.getSite() != 0) {
			query.append(" AND P.L_SI_CLE = ? ");
			preparerSQL.addParametre(ambulanceDossierRapportVO.getSite());
		} else if (ambulanceDossierRapportVO.getEntite() != 0 && ambulanceDossierRapportVO.getSite() == 0){
			query.append(" and P.L_SI_CLE in (select si.l_si_cle from si_site si where si.i_en_cle = ?) ");
			preparerSQL.addParametre(ambulanceDossierRapportVO.getEntite());
		}
		query.append(" AND contains(P.CLOB_CO_TEXTE_NORMAL, '$ambulance',1) > 0 ");
		// Ordre de tri
		query.append(" order by L_CO_REFERENCE asc");
		
		preparerSQL.setSQL( query.toString() );
		return preparerSQL;
	}
	
}

