package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class PSUMandatCountSQL extends PSUMandatSQL{

	protected String selectArgument(){
		return "count(P.L_PSU_CLE) ";
	}

	@Override
	protected String groupBy() {
		return "";
	}
	
		
}
