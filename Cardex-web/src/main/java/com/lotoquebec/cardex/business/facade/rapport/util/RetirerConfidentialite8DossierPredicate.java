package com.lotoquebec.cardex.business.facade.rapport.util;

import org.apache.commons.collections.Predicate;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardexCommun.GlobalConstants;

/**
 * 
 * @author levassc
 *
 */
public class RetirerConfidentialite8DossierPredicate implements Predicate {

	public boolean evaluate(Object o) {

		if (o instanceof Dossier == false)
			return false;
		
		return ((Dossier)o).getConfidentialite() != GlobalConstants.Confidentialite.HUIT;
	}
	
}
