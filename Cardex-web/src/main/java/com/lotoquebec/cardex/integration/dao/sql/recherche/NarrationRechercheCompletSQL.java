package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class NarrationRechercheCompletSQL extends NarrationRechercheSQL{

	protected String selectArgument(){
        StringBuffer query = new StringBuffer();
        query.append("P.L_CO_CLE, ");
        query.append("P.L_SI_CLE, ");
        query.append("P.L_CO_REFERENCE, ");
        query.append("P.V_CO_COMMENTAIRE, ");
        query.append("P.V_CO_CREE_PAR, ");
        query.append("P.D_CO_DATE_CREATION, ");
        query.append("P.V_CO_MODIFIE_PAR, ");
        query.append("P.D_CO_MODIFICATION, ");
        query.append("P.R_CO_MONTANT, ");
        query.append("P.L_CO_REF_SITE, ");
        query.append("P.C_CO_REF_GENRE, ");
		query.append("P.I_CC_COMMENTAIRE, ");
		query.append("P.I_NH_COMMENTAIRE, ");
		query.append("P.I_CC_CREATEUR, ");
		query.append("P.I_NH_CREATEUR, ");
		query.append("P.V_CO_APPROBATEUR, ");
		query.append("P.I_CC_APPROBATEUR, ");
		query.append("P.I_NH_APPROBATEUR, ");
		query.append("P.D_CO_APPROBATION, ");
		query.append("P.V_CO_REFERENCE, ");
		query.append("P.V_CO_RAPPORTE_PAR, ");
		query.append("P.V_CO_TEMPS, ");
		query.append("P.CLOB_CO_TEXTE_FORMAT, ");
		query.append("P.CLOB_CO_TEXTE_NORMAL, ");
		query.append("D.L_DO_CLE, ");
		query.append("D.L_SI_CLE, ");
		query.append("D.V_DO_MOT_PASSE, ");
		query.append("D.I_GE_ENTITE, ");
		query.append("D.I_GE_CLE, ");
		query.append("D.I_NA_CLE, ");
		query.append("D.I_TY_CLE, ");
		query.append("D.I_CA_CLE, ");
		query.append("D.I_NH_CLE, ");
		query.append("D.V_DO_CREE_PAR, ");
		query.append("D.V_DO_NUMERO_DOSSIER, ");
		query.append("D.I_CC_CLE ");

		return query.toString();
	}
		
}
