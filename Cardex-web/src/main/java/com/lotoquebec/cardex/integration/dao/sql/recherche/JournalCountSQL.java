package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class JournalCountSQL extends JournalSQL{

	protected String selectArgument(){
		return "count(DO.L_DO_CLE) ";
	}
	
		
}
