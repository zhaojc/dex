/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class UrgenceOngletTrieListe extends TrierListeColumns {
	
	public final static String CLE_CLASSE = "tabpage_classe";
	public final static String CLASSE = "classeDescription";
	
	public final static String CLE_NOM = "v_so_nom";
	public final static String NOM = "societe";

	public final static String CLE_LIEN_CREATEUR = "lie.par";
	public final static String LIEN_CREATEUR = "createur";	

	public final static String CLE_DATE_CREATION = "lie.le";
	public final static String DATE_CREATION = "dateCreation";

	public UrgenceOngletTrieListe() {
		addNewComparator(CLE_CLASSE, CLASSE);
		
		addNewComparator(CLE_NOM, NOM);
		
		addNewComparator(CLE_LIEN_CREATEUR, LIEN_CREATEUR);

		addNewComparator(CLE_DATE_CREATION, DATE_CREATION);

	}
	
}
