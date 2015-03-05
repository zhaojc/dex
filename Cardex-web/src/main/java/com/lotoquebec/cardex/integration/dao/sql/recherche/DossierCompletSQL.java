package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class DossierCompletSQL extends DossierSQL{

	protected String selectArgument(){
        StringBuffer query = new StringBuffer();
        query.append("DO.L_DO_CLE,DO.L_SI_CLE,"
                + "DO.V_DO_NUMERO_DOSSIER,DO.I_CA_CLE,"
                + "DO.I_SE_CLE, ");
        query.append(" DO.I_CC_CLE,DO.I_NH_CLE,DO.I_PE_CLE,"
                + "DO.I_ST_CLE,DO.V_DO_MOT_PASSE,DO.D_DO_DATE_DEBUT"
                + ", ");
        query.append(" DO.D_DO_DATE_FIN,DO.V_DO_DUREE,"
                + "DO.V_DO_ANCIENNE_REFERENCE,"
                + "DO.V_DO_REFERENCE_VIDEO, ");
        query.append(" DO.D_DO_DATE_RAPPORTEE,DO.I_DO_CLASSE,"
                + "DO.I_DO_RACE,DO.V_DO_LIEU,DO.I_OR_CLE,"
                + "DO.I_CR_CLE, ");
        query.append(" DO.V_DO_REFERENCE1,DO.L_DO_ORIGINE,DO.V_DO_REFERENCE2,"
                + "DO.V_DO_ASSIGNE_A,DO.D_DO_DATE_ASSIGNATION, ");
        query.append(" DO.D_DO_DATE_EVENEMENT,DO.V_DO_CREE_PAR,"
                + "DO.D_DO_DATE_CREATION,DO.I_TY_CLE,DO.I_NA_CLE,");
        query.append(" DO.I_GE_CLE, DO.I_GE_ENTITE, DO.V_DO_REFERENCE3,"
                + "DO.V_DO_REFERENCE4,DO.V_DO_REFERENCE5,"
                + "DO.C_DO_FONDE, ");
        query.append(" DO.V_DO_REFERENCE6,DO.V_DO_REFERENCE7,"
                + "DO.I_DO_FONDE,DO.I_RF_CLE, DO.B_DO_ENREGISTREMENT_CONSERVE, ");
        query.append(" DO.B_DO_ENREGISTREMENT_NUMERIQUE ");

		return query.toString();
	}
		
}
