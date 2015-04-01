package com.lotoquebec.cardex.ejb.flux;


/**
 * Sert � v�rifier les demandes de habilitation s�curitaire imcompl�te et � envoyer des courriels aux
 * personnes responsables.
 * @author guerinf
 *
 */
public class CDX00_00017 implements Flux{

	public void execute() throws Exception {
		(new CDX00_00017_DemandesIncompletes()).execute();
	}
	
}
