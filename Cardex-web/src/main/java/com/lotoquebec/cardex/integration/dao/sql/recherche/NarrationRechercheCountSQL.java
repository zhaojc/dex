package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class NarrationRechercheCountSQL extends NarrationRechercheSQL{

	protected String selectArgument(){
		return "count(P.L_CO_CLE) ";
	}
	
		
}
