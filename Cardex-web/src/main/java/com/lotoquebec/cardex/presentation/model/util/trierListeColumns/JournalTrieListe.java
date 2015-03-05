/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class JournalTrieListe extends TrierListeColumns {

	public final static String CLE_NUMERO_CARDEX = "v_do_numero_dossier";
	public final static String NUMERO_CARDEX = "numeroDossier.affichage";

	public final static String CLE_DESCRIPTION = "v_co_commentaire_t";
	public final static String DESCRIPTION = "description";
	
	public final static String CLE_INTERVENANT = "v_do_assigne_a";
	public final static String INTERVENANT = "intervenant";
	
	public final static String CLE_DATE_DEBUT = "d_co_date_creation_t";
	public final static String DATE_DEBUT = "dateDebut";
	
	public final static String CLE_TYPE = "tabpage_type";
	public final static String TYPE = "typeDescription";

	public final static String CLE_CATEGORIE = "tabpage_categorie";
	public final static String CATEGORIE = "categorieDescription";

	public final static String CLE_ENDROIT = "tabpage_orientation";
	public final static String ENDROIT = "endroitDescription";


	public JournalTrieListe() {
		addNewComparator(CLE_NUMERO_CARDEX, NUMERO_CARDEX);
		
		//addComparator(CLE_DESCRIPTION, DESCRIPTION, NUMERO_CARDEX);
		
		addComparator(CLE_INTERVENANT, INTERVENANT, NUMERO_CARDEX);

		addComparator(CLE_DATE_DEBUT, DATE_DEBUT, NUMERO_CARDEX);
		
		addComparator(CLE_TYPE, TYPE, NUMERO_CARDEX);

		addComparator(CLE_CATEGORIE, CATEGORIE, NUMERO_CARDEX);
		
		addComparator(CLE_ENDROIT, ENDROIT, NUMERO_CARDEX);
	}
	
}
