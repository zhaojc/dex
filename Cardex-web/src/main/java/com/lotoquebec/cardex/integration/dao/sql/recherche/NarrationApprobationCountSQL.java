package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class NarrationApprobationCountSQL extends NarrationApprobationSQL{

	protected String selectArgument(){
		return "count(P.L_CO_CLE) ";
	}
	
		
}
