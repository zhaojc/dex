package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class SuiviCountSQL extends SuiviSQL{

	protected String selectArgument(){
		return "count(P.L_SV_CLE) ";
	}
	
		
}
