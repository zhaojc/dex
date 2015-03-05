/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class PSUMandatTrieListe extends TrierListeColumns {

	public final static String CLE_NUMERO_MANDAT = "mandat_t";
	public final static String NUMERO_MANDAT = "numeroMandat";

	public final static String CLE_STATUT = "tabpage_statut";
	public final static String STATUT = "statutDescription";
	
	public final static String CLE_DESCRIPTION = "v_description_t";
	public final static String DESCRIPTION = "description";
	
	public final static String CLE_DATE_DEBUT = "d_is_date_debut_t";
	public final static String DATE_DEBUT = "dateDebut10";
	
	public final static String CLE_DATE_FIN = "d_is_date_fin_t";
	public final static String DATE_FIN = "dateFin10";

	public final static String CLE_TOTAL = "total";
	public final static String TOTAL = "total";


	public PSUMandatTrieListe() {
		addNewComparator(CLE_NUMERO_MANDAT, NUMERO_MANDAT);
		
		addComparator(CLE_STATUT, STATUT, NUMERO_MANDAT);
		
		addComparator(CLE_DESCRIPTION, DESCRIPTION, NUMERO_MANDAT);
		
		addComparator(CLE_DATE_DEBUT, DATE_DEBUT, NUMERO_MANDAT);
		
		addComparator(CLE_DATE_FIN, DATE_FIN, NUMERO_MANDAT);
		
		addComparatorNumerique(CLE_TOTAL, TOTAL, NUMERO_MANDAT);
	}
	
}
