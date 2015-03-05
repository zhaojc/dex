package com.lotoquebec.cardex.util.comparator;

import java.util.Comparator;

import com.lotoquebec.cardex.business.Dossier;


/**
 * 
 * @author levassc
 *
 */
public class DateDebutDossierComparator implements Comparator<Dossier>{

	public int compare(Dossier dossier1, Dossier dossier2) {
		return dossier1.getDateDebut().compareTo(dossier2.getDateDebut());
	}

}
