package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class BilletCountSQL extends BilletSQL{

	protected String selectArgument(){
		return "count(DO.L_DO_CLE) ";
	}
	
		
}
