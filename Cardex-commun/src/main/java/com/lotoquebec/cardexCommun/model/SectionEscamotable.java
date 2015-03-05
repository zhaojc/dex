package com.lotoquebec.cardexCommun.model;

/**
 * Cet object représente une section escamotable à l'écran.  
 * Nous conservons un statut si la section est ouverte.
 * 
 * @author levassc
 *
 */
public interface SectionEscamotable {

	public boolean isSectionOuverte();
	
	public void setSectionOuverte(boolean sectionOuverte);
	
}
