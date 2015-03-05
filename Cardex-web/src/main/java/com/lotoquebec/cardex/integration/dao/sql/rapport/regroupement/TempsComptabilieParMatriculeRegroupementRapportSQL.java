/*
 * Created on 10-Apr-2008
 */
package com.lotoquebec.cardex.integration.dao.sql.rapport.regroupement;


/**
 * @author levassc
 */
public class TempsComptabilieParMatriculeRegroupementRapportSQL extends TempsComptabilieRegroupementRapportSQL{

	public final static String MATRICULE = "matricule";
	
	
	protected String select(){
		return "RTS.MATRI as "+MATRICULE+",";
	}
	
	protected String groupBy(){
		return "GROUP BY RTS.MATRI ";
	}
	
}
