package com.lotoquebec.cardex.ejb.flux;


/**
 * V�rification des dossiers d'�valuation et envoi d'un formulaire de r�admission si �a fait plus d'un an.
 * @author guerinf
 *
 */
public class CDX00_00012 implements Flux{

	public void execute() throws Exception {
		(new CDX00_00012_EvaluationReadmission()).execute();
	}
	
}
