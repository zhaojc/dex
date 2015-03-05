package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class UrgenceCompletSQL extends UrgenceSQL{

	protected String selectArgument(){
        StringBuffer query = new StringBuffer();
        query.append("U.L_UR_CLE, U.L_SI_CLE, S.I_EN_CLE, U.I_ST_CLE, U.I_CL_CLE, U.L_UR_MOTIF, U.L_SO_REF_CLE, U.L_SO_REF_SITE, ");
        query.append("SO.V_SO_NOM, U.L_UR_REF_CLE, U.L_UR_REF_SITE, U.V_UR_UNITE, U.V_UR_DISTRICT, U.V_UR_CONTACT, ");
        query.append("U.V_UR_CONTACT_PRENOM, U.V_UR_VILLE, U.V_UR_EVENEMENT, U.V_UR_REPONDANT, U.V_UR_FONCTION, ");
        query.append("U.V_UR_MATRICULE, U.V_UR_CREE_PAR, U.D_UR_DATE_CREATION, D.V_DO_NUMERO_DOSSIER, D.L_DO_CLE");
        
		return query.toString();
	}
		
}
