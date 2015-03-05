package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class VehiculeCompletSQL extends VehiculeSQL{

	protected String selectArgument(){
        StringBuffer query = new StringBuffer();
        query.append("VE.L_VE_CLE, VE.L_SI_CLE, SI.I_EN_CLE, VE.I_MD_CLE, VE.I_CC_CLE,VE.V_VE_IMMATRICULATION, ");
        query.append("VE.V_VE_PROVINCE,VE.C_VE_ANNEE,VE.V_VE_VIGNETTE,VE.V_VE_NUMERO_SERIE,VE.V_VE_ASSUREUR,  ");
        query.append("VE.V_VE_POLICE,VE.D_VE_EXPIRATION_VIGNETTE,VE.D_VE_EXPIRATION_POLICE,VE.V_VE_CREE_PAR,  ");
        query.append("VE.D_VE_DATE_CREATION,VE.V_VE_MOT_PASSE,MD.I_MA_CLE,VE.V_VE_COMMENTAIRE,VE.L_PR_CLE ");

		return query.toString();
	}
		
}
