package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class PSUMandatCompletSQL extends PSUMandatSQL{

	protected String selectArgument(){
        StringBuffer query = new StringBuffer();
	    query.append("P.L_PSU_CLE,P.L_SI_CLE,P.V_PSU_NUMERO_MANDAT,P.I_TA_CLE,P.V_PSU_DESCRIPTION, ");
		query.append("P.V_PSU_DESTINATAIRE_A,P.V_PSU_DESTINATAIRE_CC, P.V_PSU_DESTINATAIRE_CCI,P.I_ST_CLE, ");
		query.append("P.V_PSU_MESSAGE,P.D_PSU_DATE_DEBUT,P.D_PSU_DATE_FIN,P.C_PSU_GENRE_FICHIER,P.L_PSU_REFERENCE_CLE, ");
		query.append("P.L_PSU_REFERENCE_SITE,P.I_PSU_EN_CLE,P.L_PSU_SI_CLE,P.I_PSU_GE_CLE,P.I_PSU_NA_CLE,P.I_PSU_TY_CLE, ");
		query.append("P.I_PSU_CA_CLE,P.I_PSU_FO_CLE,P.V_PSU_NAME,P.V_PSU_DO_ANCIENNE_REFERENCE,P.V_PSU_DO_NUMERO_DOSSIER, ");
		query.append("P.V_PSU_DO_REFERENCE_1,P.V_PSU_SU_REFERENCE_3,P.V_PSU_SU_NOM,P.V_PSU_SU_PRENOM,P.V_PSU_SO_REFERENCE_3, ");
		query.append("P.V_PSU_SO_NOM,P.V_PSU_VE_IMMATRICULATION,P.V_PSU_VE_PROVINCE,P.V_PSU_MOT_CLE1,P.V_PSU_MOT_CLE2,  ");
		query.append("P.V_PSU_MOT_CLE3,P.V_PSU_CREE_PAR,P.D_PSU_DATE_CREATION,P.V_PSU_MODIFIE_PAR,P.D_PSU_DATE_MODIFICATION, ");
		query.append("P.V_PSU_APPROUVE_PAR,P.D_PSU_DATE_APPROBATION, NVL(COUNT(C.L_CS_CLE),0) AS \"TOTAL\" ");

		return query.toString();
	}

	@Override
	protected String groupBy() {
        StringBuffer query = new StringBuffer();		
		query.append(" group by P.L_PSU_CLE,P.L_SI_CLE,P.V_PSU_NUMERO_MANDAT,P.I_TA_CLE,P.V_PSU_DESCRIPTION, P.V_PSU_DESTINATAIRE_A,P.V_PSU_DESTINATAIRE_CC, P.V_PSU_DESTINATAIRE_CCI,P.I_ST_CLE, ");
		query.append("P.V_PSU_MESSAGE,P.D_PSU_DATE_DEBUT,P.D_PSU_DATE_FIN,P.C_PSU_GENRE_FICHIER,P.L_PSU_REFERENCE_CLE, P.L_PSU_REFERENCE_SITE,P.I_PSU_EN_CLE,P.L_PSU_SI_CLE,P.I_PSU_GE_CLE,P.I_PSU_NA_CLE,P.I_PSU_TY_CLE, ");
		query.append("P.I_PSU_CA_CLE,P.I_PSU_FO_CLE,P.V_PSU_NAME,P.V_PSU_DO_ANCIENNE_REFERENCE,P.V_PSU_DO_NUMERO_DOSSIER,P.V_PSU_DO_REFERENCE_1,P.V_PSU_SU_REFERENCE_3,P.V_PSU_SU_NOM,P.V_PSU_SU_PRENOM,P.V_PSU_SO_REFERENCE_3, ");
		query.append("P.V_PSU_SO_NOM,P.V_PSU_VE_IMMATRICULATION,P.V_PSU_VE_PROVINCE,P.V_PSU_MOT_CLE1,P.V_PSU_MOT_CLE2, P.V_PSU_MOT_CLE3,P.V_PSU_CREE_PAR,P.D_PSU_DATE_CREATION,P.V_PSU_MODIFIE_PAR,P.D_PSU_DATE_MODIFICATION, P.V_PSU_APPROUVE_PAR,P.D_PSU_DATE_APPROBATION ");

		return query.toString();
	}
		
}
