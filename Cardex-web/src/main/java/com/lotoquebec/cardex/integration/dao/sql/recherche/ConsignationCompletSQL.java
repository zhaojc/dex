package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class ConsignationCompletSQL extends ConsignationSQL{

	protected String selectArgument(){
        StringBuffer query = new StringBuffer();
		query.append("P.L_CN_CLE,P.L_SI_CLE,P.L_CN_REF_CLE,P.L_CN_REF_SITE,P.C_CN_REF_GENRE, ");
		query.append("P.I_DE_CLE,P.I_TN_CLE,P.L_CN_QUANTITE,P.R_CN_PRIX, P.R_CN_MONTANT, ");
		query.append("P.V_CN_POIDS,P.V_CN_DIMENSION,P.V_CN_MARQUE,P.V_CN_MODELE,P.V_CN_FOURNISSEUR, ");
		query.append("P.V_CN_DESCRIPTION, P.V_CN_COMMENTAIRE, P.V_CN_NUMERO_SERIE, P.V_CN_CREE_PAR, ");
		query.append("P.D_CN_DATE_CREATION, P.V_CN_MODIFIE_PAR, P.D_CN_DATE_MODIFICATION, ");
		query.append("P.V_CN_APPROUVE_PAR, P.D_CN_DATE_APPROBATION, P.C_CN_APPROUVABLE,");
		query.append("P.C_CN_APPROUVE,  P.V_CN_REFERENCE1,  P.V_CN_REFERENCE2, P.I_DN_CLE,  ");
		query.append("D.L_DO_CLE, D.L_SI_CLE as \"SITE_DOSSIER\", ");
		query.append("D.V_DO_CREE_PAR, D.V_DO_NUMERO_DOSSIER, ");
		query.append("D.V_DO_MOT_PASSE, D.i_ge_entite, D.i_ge_cle, d.I_NA_CLE, d.i_ty_cle, D.i_ca_cle, D.I_CC_CLE, D.I_NH_CLE ");

		return query.toString();
	}
		
}
