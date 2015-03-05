/*
 * Created on 18-Apr-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.recherche;


/**
 * @author levassc
 */
public class AdresseSujetCountSQL extends AdresseSujetSQL{

	protected String selectArgument(){
		return "count(su.l_su_cle) ";
	}
	
}
