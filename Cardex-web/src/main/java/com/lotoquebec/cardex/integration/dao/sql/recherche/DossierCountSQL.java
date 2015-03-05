package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class DossierCountSQL extends DossierSQL{

	protected String selectArgument(){
		return "count(DO.L_DO_CLE) ";
	}
	
		
}
