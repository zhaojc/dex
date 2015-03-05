package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class UrgenceCountSQL extends UrgenceSQL{

	protected String selectArgument(){
		return "count(U.L_UR_CLE) ";
	}
	
		
}
