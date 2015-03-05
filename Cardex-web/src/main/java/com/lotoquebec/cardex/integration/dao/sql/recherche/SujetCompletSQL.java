package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class SujetCompletSQL extends SujetSQL{

	protected String selectArgument(){
        StringBuffer query = new StringBuffer();
		query.append("DISTINCT S.L_SU_CLE,S.L_SI_CLE,S.I_SX_CLE,S.I_ST_CLE, ");
		query.append("S.I_NT_CLE, S.I_RA_CLE, S.I_LS_CLE, SI.I_EN_CLE, ");
		query.append("S.V_SU_NOM,S.V_SU_PRENOM,S.V_SU_SURNOM,S.D_SU_DATE_NAISSANCE, S.L_SU_TYPE_AGE, ");
		query.append("S.C_SU_ASSURANCE_SOCIALE,S.V_SU_PERMIS_CONDUIRE,S.V_SU_GRANDEUR_METRIQUE, ");
		query.append("S.V_SU_GRANDEUR_IMPERIAL,S.V_SU_POIDS_METRIQUE,S.V_SU_POIDS_IMPERIAL, ");
		query.append("S.C_SU_SYSTEME,S.I_CC_CLE,S.V_SU_MOT_PASSE,S.V_SU_REFERENCE_1, ");
		query.append("S.V_SU_REFERENCE_2,S.V_SU_REFERENCE_3,S.V_SU_CREE_PAR,S.D_SU_DATE_CREATION, S.B_SU_IND_RDD, ");
		query.append("S.C_SU_SNDX_NOM,S.C_SU_SNDX_PRENOM,S.C_SU_SNDX_SURNOM,S.I_SE_CLE,S.I_SE_CLE_AUTRES,S.I_SE_CLE_CASINO,S.V_SU_NO_PASSEPORT,S.V_SU_ASSURANCE_MALADIE,S.D_SU_DATE_FIN_EMPLOI ");

		return query.toString();
	}
		
}
