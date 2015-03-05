package com.lotoquebec.cardex.util;

import org.apache.commons.collections.Predicate;

import com.lotoquebec.cardex.business.Dossier;

public class NatureDossierPredicate implements Predicate {

	private long nature;
	
	public NatureDossierPredicate(long nature) {
		super();
		this.nature = nature;
	}

	public boolean evaluate(Object o) {
		
		if (o instanceof Dossier == false)
			return false;
		
		if (nature == 0)
			return true;
		Dossier dossier = (Dossier)o;
		
		return nature == dossier.getNature();
	}

}
