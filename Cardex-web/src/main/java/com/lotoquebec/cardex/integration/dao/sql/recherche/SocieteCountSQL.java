package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class SocieteCountSQL extends SocieteSQL{

	protected String selectArgument(){
		return "count(S.L_SO_CLE) ";
	}
	
		
}
