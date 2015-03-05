package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class SujetCountSQL extends SujetSQL{

	protected String selectArgument(){
		return "count(S.L_SU_CLE) ";
	}
	
		
}
