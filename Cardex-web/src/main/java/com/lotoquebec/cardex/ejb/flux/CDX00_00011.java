package com.lotoquebec.cardex.ejb.flux;


/**
 * �puration de la table AC_ACCES, sauf les insertions. On supprime les donn�es ant�rieures � 3 ans.
 * @author guerinf
 *
 */
public class CDX00_00011 implements Flux{

	public void execute() throws Exception {
		(new CDX00_00011_EpurationAcces()).execute();
	}
	
}
