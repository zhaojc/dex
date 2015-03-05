/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class ApprobationTrieListe extends TrierListeColumns {

	public final static String CLE_NUMERO_CARDEX = "v_do_numero_dossier";
	public final static String NUMERO_CARDEX = "dossier.numeroCardex.affichage";

	public final static String CLE_COMMENTAIRE = "v_co_commentaire_t";
	
	public final static String CLE_CREATEUR = "v_co_cree_par_t";
	public final static String CREATEUR = "createurDescription";
	
	public final static String CLE_DATE_CREATION = "d_co_date_creation_t";
	public final static String DATE_CREATION = "dateCreation";

	public final static String CLE_APPROBATEUR = "v_co_approbateur_t";
	public final static String APPROBATEUR = "approbateurDescription";
	
	public final static String CLE_DATE_APPROBATION = "d_co_approbation_t2";
	public final static String DATE_APPROBATION = "dateApprobation";

	public ApprobationTrieListe() {
		addNewComparator( CLE_NUMERO_CARDEX, NUMERO_CARDEX );
		
		addComparator(CLE_CREATEUR, CREATEUR, NUMERO_CARDEX);
		
		addComparator(CLE_DATE_CREATION, DATE_CREATION, NUMERO_CARDEX);
		
		addComparator(CLE_APPROBATEUR, APPROBATEUR, NUMERO_CARDEX);
		
		addComparator(CLE_DATE_APPROBATION, DATE_APPROBATION, NUMERO_CARDEX);
	}
	
}
