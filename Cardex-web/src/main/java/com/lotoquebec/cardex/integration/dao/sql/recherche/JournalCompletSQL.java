package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class JournalCompletSQL extends JournalSQL{

	protected String selectArgument(){
        StringBuffer query = new StringBuffer();
        query.append("DO.L_DO_CLE,DO.L_SI_CLE, "
                + "DO.V_DO_NUMERO_DOSSIER,DO.I_CA_CLE, ");
        query.append(" DO.D_DO_DATE_DEBUT, DO.D_DO_DATE_FIN, " 
                + "DO.V_DO_ANCIENNE_REFERENCE,"
                + "DO.V_DO_REFERENCE_VIDEO, ");
        query.append(" DO.V_DO_LIEU,DO.I_OR_CLE,"
                + "DO.I_CR_CLE, ");
        query.append(" DO.V_DO_REFERENCE1,DO.L_DO_ORIGINE,DO.V_DO_REFERENCE2,DO.V_DO_REFERENCE3,DO.V_DO_REFERENCE5,"
                + "DO.V_DO_ASSIGNE_A, ");
        query.append(" DO.V_DO_CREE_PAR,"
                + "DO.D_DO_DATE_CREATION,DO.I_TY_CLE,DO.I_NA_CLE,");
        query.append(" C.L_CO_CLE, C.V_CO_TEMPS, C.CLOB_CO_TEXTE_NORMAL ");


		return query.toString();
	}
		
}
