package com.lotoquebec.cardex.ejb.flux;


/**
 * Rapport d'activit�s quotiennes
 * @author levassc
 *
 */
public class CDX00_00006 implements Flux{

	public void execute() throws Exception {
		(new CDX00_00006_RAQ()).execute();
	}
	
}