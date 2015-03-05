package com.lotoquebec.cardexCommun.model;

/**
 * Cet object repr�sente une section escamotable � l'�cran.  
 * Nous conservons un statut si la section est ouverte.
 * 
 * @author levassc
 *
 */
public interface SectionEscamotable {

	public boolean isSectionOuverte();
	
	public void setSectionOuverte(boolean sectionOuverte);
	
}
