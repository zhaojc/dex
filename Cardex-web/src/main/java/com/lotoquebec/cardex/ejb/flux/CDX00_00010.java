package com.lotoquebec.cardex.ejb.flux;


/**
 * Sert � d�sactiver (statut = Inactif) les dossiers expir�s d'autoexclusion
 * @author guerinf
 *
 */
public class CDX00_00010 implements Flux{

	public void execute() throws Exception {
		(new CDX00_00010_DesactivationAutoexclusion()).execute();
	}
	
}
