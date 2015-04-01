package com.lotoquebec.cardex.ejb.flux;


/**
 * Mouvements de personnel
 * @author levassc
 *
 */
public class CDX00_00004 implements Flux{

	public void execute() throws Exception {
		(new CDX00_00004_MouvementPersonnel()).execute();
	}
	
}
