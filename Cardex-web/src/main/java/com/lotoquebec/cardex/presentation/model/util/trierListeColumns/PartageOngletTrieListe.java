/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class PartageOngletTrieListe extends TrierListeColumns {

	public final static String CLE_INTERVENANT = "v_intervenant_t";
	public final static String INTERVENANT = "intervenantDescription";
	
	public final static String CLE_SECTEUR = "v_secteur";
	public final static String SECTEUR = "profilDescription";
	
	public final static String CLE_SITE_INTERVENANT = "l_si_cle_t";
	public final static String SITE_INTERVENANT = "siteIntervenantDescription";
	

	public PartageOngletTrieListe() {
		addNewComparator(CLE_INTERVENANT, INTERVENANT);
		
		addComparator(CLE_SECTEUR, SECTEUR, INTERVENANT);
		
		addComparator(CLE_SITE_INTERVENANT, SITE_INTERVENANT, INTERVENANT);
	}
	
}
