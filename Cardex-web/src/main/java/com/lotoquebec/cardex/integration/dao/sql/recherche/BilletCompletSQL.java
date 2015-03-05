package com.lotoquebec.cardex.integration.dao.sql.recherche;



public class BilletCompletSQL extends BilletSQL{

	protected String selectArgument(){
		StringBuffer query = new StringBuffer();
		query.append("DO.L_DO_CLE, ");
		query.append("DO.L_SI_CLE as L_DO_SI_CLE, ");
		query.append("DO.V_DO_NUMERO_DOSSIER, ");
		query.append("DO.I_CA_CLE, ");
		query.append("DO.I_SE_CLE, ");
		query.append("DO.I_CC_CLE, ");
		query.append("DO.I_PE_CLE, ");
		query.append("DO.I_ST_CLE, ");
		query.append("DO.D_DO_DATE_DEBUT, ");
		query.append("DO.D_DO_DATE_FIN, ");
		query.append("V_DO_ANCIENNE_REFERENCE, ");
		query.append("DO.V_DO_REFERENCE1, ");
		query.append("DO.V_DO_REFERENCE2, ");
		query.append("DO.V_DO_ASSIGNE_A, ");
		query.append("DO.I_TY_CLE, ");
		query.append("DO.I_NA_CLE, ");
		query.append("DO.I_GE_CLE, ");
		query.append("DO.I_GE_ENTITE, ");
		query.append("DO.V_DO_REFERENCE3, ");
		query.append("BI.V_BI_NOM, ");
		query.append("BI.V_BI_NUMERO_CONTROLE, ");
		query.append("BI.L_BI_MONTANT_LOT, ");
		query.append("BI.D_BI_DATE_CREATION, ");
		query.append("BI.L_BI_CLE, ");
		query.append("BI.L_SI_CLE as L_BI_SI_CLE, ");
		query.append("BI.D_BI_DATE_PAIEMENT ");
		
		return query.toString();
	}
		
}
