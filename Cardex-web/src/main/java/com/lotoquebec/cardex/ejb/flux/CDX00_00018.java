package com.lotoquebec.cardex.ejb.flux;


/**
 * Sert � produire les fichiers pour alimenter les syst�mes de laissez-passer dans les casinos.
 * Il y a deux fichiers, un pour les sujets et l'autre pour les soci�t�s.
 * @author guerinf
 *
 */
public class CDX00_00018 implements Flux{

	public void execute() throws Exception {
		(new CDX00_00018_LaissezPasser()).execute();
	}
	
}
