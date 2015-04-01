package com.lotoquebec.cardex.ejb.flux;


/**
 * Sert � produire les diff�rents fichiers utilis�s dans les casinos pour le 
 * syst�me de reconnaissance plaques. Les fichiers contiennent la liste des
 * num�ros d'immatriculation des sujets autoexclus.
 * @author guerinf
 *
 */
public class CDX00_00015 implements Flux{

	public void execute() throws Exception {
		(new CDX00_00015_ReconnaissancePlaques()).execute();
	}
	
}
