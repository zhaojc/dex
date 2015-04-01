package com.lotoquebec.cardex.ejb.flux;


/**
 * D�sactivation des dossiers d'investigation
 * Sert � changer la s�v�riti� des dossiers d'enqu�tes sur les 
 * employ�s dont la p�riode de validit� est termin�e.
 * @author guerinf
 *
 */
public class CDX00_00008 implements Flux{

	public void execute() throws Exception {
		(new CDX00_00008_DesactivationInvestigation()).execute();
	}
	
}
