package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class SocieteCompletSQL extends SocieteSQL{

	protected String selectArgument(){
        StringBuffer query = new StringBuffer();
        query.append("DISTINCT S.L_SO_CLE,S.L_SI_CLE,SI.I_EN_CLE,S.I_ST_CLE,S.I_LS_CLE, ");
        query.append("S.V_SO_RAISON_SOCIALE, S.V_SO_NOM, S.I_CL_CLE, ");
        query.append("S.D_SO_DATE_FONDATION,S.I_CC_CLE,S.V_SO_MOT_PASSE,S.V_SO_REFERENCE_NOM, ");
        query.append("S.V_SO_REFERENCE_PRENOM,S.V_SO_REFERENCE_1,S.V_SO_REFERENCE_2, ");
        query.append("S.V_SO_REFERENCE_3,S.V_SO_CREE_PAR,S.D_SO_DATE_CREATION,S.I_SE_CLE,S.I_SE_CLE_CASINO, ");
        query.append("S.L_SO_CENTRE_REGIONAL,S.V_SO_DISTRICT,S.V_SO_CODE_COMPTE,S.B_SO_ACTIF, ");
        query.append("S.D_SO_DATE_INACTIVATION,S.B_SO_IND_RDD,S.V_SO_COMMENTAIRE,S.V_SO_RAISON_INACTIVATION, ");
        query.append("CR.NOM_CENTREREG,CC.NOM_COC, D.NOM_DISTRICT, S.I_SO_ECHANTILLONAGE ");
 
		return query.toString();
	}
		
}
