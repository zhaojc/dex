package com.lotoquebec.cardex.ejb.flux;


/**
 * Sert � produire un rapport quotidien sur la coh�rence des donn�es.
 * Plusieurs contr�les de qualit� sont ainsi automatis�es. Le rapport
 * aide les utilisateurs � apporter les correctifs voulus.
 * @author guerinf
 *
 */
public class CDX00_00007 implements Flux{

	public void execute() throws Exception {
		(new CDX00_00007_CoherenceDonnees()).execute();
	}
	
}
