package com.lotoquebec.cardex.ejb.flux;


/**
 * Sert � produire un rapport quotidien pour v�rifier si tous les �l�ments multim�dia (photos et pi�ces jointes) associ�s
 * dans Cardex sont bel et bien pr�sents sur le serveur multimedia.
 * @author guerinf
 *
 */
public class CDX00_00016 implements Flux{

	public void execute() throws Exception {
		(new CDX00_00016_VerificationMultimedia()).execute();
	}
	
}
