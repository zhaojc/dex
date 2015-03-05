/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class ConsignationTrieListe extends TrierListeColumns {

	public final static String CLE_NUMERO_CARDEX = "no_dossier_entete_t";
	public final static String NUMERO_CARDEX = "dossier.numeroCardex.affichage";

	public final static String CLE_TYPE = "i_type_t";
	public final static String TYPE = "typeConsignationDescription";
	
	public final static String CLE_DATE_CREATION = "d_su_date_creation";
	public final static String DATE_CREATION = "dateCreation10";
	
	public final static String CLE_DESCRIPTION = "v_description_t";
	public final static String DESCRIPTION = "description";
	
	public final static String CLE_DENOMINATION = "denomination_t2";
	public final static String DENOMINATION = "denominationDescription";

	public final static String CLE_NUMERO_SERIE = "no_serie_t2";
	public final static String NUMERO_SERIE = "numeroSerie";

	public final static String CLE_QUANTITE = "quantite_t";
	public final static String QUANTITE = "quantite";
	
	public final static String CLE_MONTANT = "r_co_montant_t2";
	public final static String MONTANT = "montant";
	
	public final static String CLE_MARQUE = "tabpage_marque";
	public final static String MARQUE = "marque";
	
	public final static String CLE_MODELE = "tabpage_modele";
	public final static String MODELE = "modele";
	
	public final static String CLE_FOURNISSEUR = "fournisseur_t2";
	public final static String FOURNISSEUR = "fournisseur";	

	public ConsignationTrieListe() {
		addNewComparator(CLE_NUMERO_CARDEX, NUMERO_CARDEX);
		
		addComparator(CLE_TYPE, TYPE, NUMERO_CARDEX);

		addComparator(CLE_DATE_CREATION, DATE_CREATION, NUMERO_CARDEX);
		
		addComparator(CLE_DESCRIPTION, DESCRIPTION, NUMERO_CARDEX);
		
		addComparator(CLE_DENOMINATION, DENOMINATION, NUMERO_CARDEX);
		
		addComparator(CLE_NUMERO_SERIE, NUMERO_SERIE, NUMERO_CARDEX);
		
		addComparator(CLE_QUANTITE, QUANTITE, NUMERO_CARDEX);
		
		addComparator(CLE_MONTANT, MONTANT, NUMERO_CARDEX);
		
		addComparator(CLE_MARQUE, MARQUE, NUMERO_CARDEX);
		
		addComparator(CLE_MODELE, MODELE, NUMERO_CARDEX);
		
		addComparator(CLE_FOURNISSEUR, FOURNISSEUR, NUMERO_CARDEX);
	}
	
}
