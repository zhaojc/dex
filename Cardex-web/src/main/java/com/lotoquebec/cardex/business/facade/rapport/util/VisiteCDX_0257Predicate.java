package com.lotoquebec.cardex.business.facade.rapport.util;

import java.util.Iterator;

import org.apache.commons.collections.Predicate;

import com.lotoquebec.cardex.business.vo.rapport.ClientMystereVO_CDX_0255;
import com.lotoquebec.cardex.business.vo.rapport.SectionSocieteVO_CDX_0255;
import com.lotoquebec.cardex.business.vo.rapport.VisiteVO_CDX_0255;

/**
 * Filtrer pour un nombre de visite
 * @author levassc
 *
 */
public class VisiteCDX_0257Predicate implements Predicate {

	private int numeroVisite;
	

	public VisiteCDX_0257Predicate(int numeroVisite) {
		super();
		this.numeroVisite = numeroVisite;
	}

	public boolean evaluate(Object o) {
		ClientMystereVO_CDX_0255 clientMystereVO_CDX_0255 = (ClientMystereVO_CDX_0255) o;
		Iterator<SectionSocieteVO_CDX_0255> clientMystereIterator = clientMystereVO_CDX_0255.getSectionSocieteCDX255VOs().iterator();		
		
		while (clientMystereIterator.hasNext()) {
			SectionSocieteVO_CDX_0255 sectionSocieteVO_CDX_0255 = clientMystereIterator.next();
			
			if (numeroDerniereVisite(sectionSocieteVO_CDX_0255) == numeroVisite)
				continue;
			clientMystereIterator.remove();
		}
		
		return clientMystereVO_CDX_0255.getSectionSocieteCDX255VOs().size() > 0;
	}

	private int numeroDerniereVisite(SectionSocieteVO_CDX_0255 sectionSocieteVO_CDX_0255){
		
		for (int i=sectionSocieteVO_CDX_0255.getVisites().length-1;i>0;i--){
			VisiteVO_CDX_0255 visiteVO_CDX_0255 = sectionSocieteVO_CDX_0255.getVisites()[i];
			
			if (visiteVO_CDX_0255.getCategorie() != 0)
				return i;
		}
		// tous vide...
		return 0;
	}
	
}
