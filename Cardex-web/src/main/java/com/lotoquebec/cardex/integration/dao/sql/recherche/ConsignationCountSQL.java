package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class ConsignationCountSQL extends ConsignationSQL{

	protected String selectArgument(){
		return "count(P.L_CN_CLE) ";
	}
	
		
}
