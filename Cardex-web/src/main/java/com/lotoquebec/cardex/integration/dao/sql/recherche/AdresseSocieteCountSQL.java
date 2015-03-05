/*
 * Created on 18-Apr-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.recherche;


/**
 * @author levassc
 */
public class AdresseSocieteCountSQL extends AdresseSocieteSQL{

	protected String selectArgument(){
		return "count(so.l_so_cle) ";
	}
	
}
