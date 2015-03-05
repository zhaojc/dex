/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class ConsignationOngletTrieListe extends TrierListeColumns {

	public final static String CLE_TYPE = "i_type_t";
	public final static String TYPE = "typeConsignationDescription";
	
	public final static String CLE_QUANTITE = "quantite_t";
	public final static String QUANTITE = "quantite";

	public final static String CLE_NUMERO_SERIE = "no_serie_t2";
	public final static String NUMERO_SERIE = "numeroSerie";

	public final static String CLE_PRIX_UNITAIRE = "prix_t2";
	public final static String PRIX_UNITAIRE = "prix";

	public final static String CLE_DEVISE = "devise_t2";
	public final static String DEVISE = "deviseDescription";
	
	public final static String CLE_DESCRIPTION = "v_description_t";
	public final static String DESCRIPTION = "description";

	public final static String CLE_DATE_CREATION = "d_su_date_creation";
	public final static String DATE_CREATION = "dateCreation10";
	
	
	public ConsignationOngletTrieListe() {
		addComparator(CLE_TYPE, TYPE, DATE_CREATION);

		addComparatorNumerique(CLE_QUANTITE, QUANTITE, DATE_CREATION);

		addComparator(CLE_NUMERO_SERIE, NUMERO_SERIE, DATE_CREATION);
		
		addComparatorNumerique(CLE_PRIX_UNITAIRE, PRIX_UNITAIRE, DATE_CREATION);
		
		addComparator(CLE_DEVISE, DEVISE, DATE_CREATION);
		
		addComparator(CLE_DESCRIPTION, DESCRIPTION, DATE_CREATION);

		addNewComparator(CLE_DATE_CREATION, DATE_CREATION);
	}
	
}
