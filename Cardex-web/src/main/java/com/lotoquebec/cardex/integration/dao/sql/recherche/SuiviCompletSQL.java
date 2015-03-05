package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class SuiviCompletSQL extends SuiviSQL{

	protected String selectArgument(){
        StringBuffer query = new StringBuffer();
        query.append("P.L_SV_CLE, P.L_SI_CLE, P.L_SV_REFERENCE, P.L_SV_REF_SITE, ");
		query.append("P.C_SV_REF_GENRE, P.V_SV_SUIVI, P.I_TC_CLE, P.I_ST_CLE, ");
		query.append("P.D_SV_DATE_PREVUE, P.D_SV_DATE_COMPLETEE, P.L_SV_PO_ORIGINE, ");
		query.append("P.V_SV_DEMANDEUR, P.V_SV_INTERVENANT, P.L_SV_PO_ASSIGNE, ");
		query.append("P.V_SV_CREE_PAR, P.I_CC_SUIVI, P.I_NH_SUIVI, P.I_CC_CREATEUR, ");
		query.append("P.I_NH_CREATEUR, P.D_SV_DATE_CREATION, P.V_SV_MODIFIE_PAR, ");
		query.append("P.D_SV_MODIFICATION, P.V_SV_APPROBATEUR, P.I_CC_APPROBATEUR, ");
		query.append("P.I_NH_APPROBATEUR, P.D_SV_APPROBATION, P.V_SV_REFERENCE_1, ");
		query.append("P.V_SV_REFERENCE_2, P.V_SV_REFERENCE_3, D.L_DO_CLE, D.i_ge_entite, D.L_SI_CLE, ");
		query.append("D.V_DO_MOT_PASSE, D.i_ge_cle, D.I_NA_CLE, d.i_ty_cle, D.i_ca_cle, D.I_CC_CLE, D.I_NH_CLE, ");
		query.append("D.V_DO_CREE_PAR, D.V_DO_NUMERO_DOSSIER ");

		return query.toString();
	}
		
}
