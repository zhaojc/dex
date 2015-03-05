/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class NarrationOngletTrieListe extends TrierListeColumns {

	public final static String CLE_SITE = "tabpage_site";
	public final static String SITE = "siteDescription";

	public final static String CLE_DATE_CREATION = "d_co_date_creation_t";
	public final static String DATE_CREATION = "dateCreation";
	
	public final static String CLE_INTERVENANT = "v_do_assigne_a";
	public final static String INTERVENANT = "createurDescription";
	
	public final static String CLE_MONTANT = "r_co_montant_t2";
	public final static String MONTANT = "montant";
	
	public final static String CLE_CONFIDENTIALITE = "i_cc_commentaire_t";
	public final static String CONFIDENTIALITE = "confidentialiteNarrationDescription";
	
	public final static String CLE_COMMENTAIRE = "v_co_commentaire_t";
	
	public final static String CLE_SEQUENCE = "no_seq_t2";
	public final static String SEQUENCE = "reference";
	
	
	public NarrationOngletTrieListe() {
		addComparator(CLE_SITE, SITE, DATE_CREATION);
		
		addNewComparator(CLE_DATE_CREATION, DATE_CREATION);
		
		addComparator(CLE_INTERVENANT, INTERVENANT, DATE_CREATION);
		
		addComparatorNumerique(CLE_MONTANT, MONTANT, DATE_CREATION);
		
		addComparator(CLE_CONFIDENTIALITE, CONFIDENTIALITE, DATE_CREATION);
		
		addComparator(CLE_SEQUENCE, SEQUENCE, DATE_CREATION);
	}
	
}
