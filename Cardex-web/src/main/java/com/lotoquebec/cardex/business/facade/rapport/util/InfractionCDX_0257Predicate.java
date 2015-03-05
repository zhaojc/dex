package com.lotoquebec.cardex.business.facade.rapport.util;

import java.util.Iterator;

import org.apache.commons.collections.Predicate;

import com.lotoquebec.cardex.business.vo.rapport.ClientMystereVO_CDX_0255;
import com.lotoquebec.cardex.business.vo.rapport.SectionSocieteVO_CDX_0255;
import com.lotoquebec.cardex.business.vo.rapport.VisiteVO_CDX_0255;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * RA0014	Le rapport CDX_02547 contient un sous-ensemble des données du rapport 
 * CDX_0255.  Il faut retirer les dossiers qui ne se terminent pas par un dossier 
 * d'infraction avec la date d'envoi vide.
 * 
 * @author levassc
 *
 */
public class InfractionCDX_0257Predicate implements Predicate {

	public boolean evaluate(Object o) {
		ClientMystereVO_CDX_0255 clientMystereVO_CDX_0255 = (ClientMystereVO_CDX_0255) o;
		Iterator<SectionSocieteVO_CDX_0255> clientMystereIterator = clientMystereVO_CDX_0255.getSectionSocieteCDX255VOs().iterator();		
		
		while (clientMystereIterator.hasNext()) {
			SectionSocieteVO_CDX_0255 sectionSocieteVO_CDX_0255 = clientMystereIterator.next();
			
			// retirer dans le cas d'une annulation
			// ou d'un dossier "gestion nouveau détaillant"  Bref, ne prendre que le dernier enregistrement
			if (sectionSocieteVO_CDX_0255.isAnnulation()
			|| clientMystereIterator.hasNext()){
				clientMystereIterator.remove();
				continue;
			}
			VisiteVO_CDX_0255 derniereVisite = derniereVisite(sectionSocieteVO_CDX_0255);
			
			if (isInfraction(derniereVisite.getCategorie())
			&& StringUtils.isEmpty(derniereVisite.getEnvoieAvisDate()))
				continue;
			clientMystereIterator.remove();
		}
		
		return clientMystereVO_CDX_0255.getSectionSocieteCDX255VOs().size() > 0;
	}

	private boolean isInfraction(long categorie){
		return GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_1 == categorie 
			|| GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_2 == categorie 
			|| GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_3 == categorie 
			|| GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_4 == categorie 
			|| GlobalConstants.CategorieClientMystere.INFRACTION_VISITE_5 == categorie;
	}

	
	private VisiteVO_CDX_0255 derniereVisite(SectionSocieteVO_CDX_0255 sectionSocieteVO_CDX_0255){
		
		for (int i=sectionSocieteVO_CDX_0255.getVisites().length-1;i>0;i--){
			VisiteVO_CDX_0255 visiteVO_CDX_0255 = sectionSocieteVO_CDX_0255.getVisites()[i];
			
			if (visiteVO_CDX_0255.getCategorie() != 0)
				return visiteVO_CDX_0255;
		}
		// tous vide...
		return sectionSocieteVO_CDX_0255.getVisites()[0];
	}
	
}
