package com.lotoquebec.cardex.integration.dao.sql.recherche;


public class VehiculeCountSQL extends VehiculeSQL{

	protected String selectArgument(){
		return "count(VE.L_VE_CLE) ";
	}
	
		
}
