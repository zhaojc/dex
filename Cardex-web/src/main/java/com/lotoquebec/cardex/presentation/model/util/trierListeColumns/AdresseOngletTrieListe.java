/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import java.io.Serializable;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class AdresseOngletTrieListe extends TrierListeColumns implements Serializable {
 
	public final static String CLE_SITE = "tabpage_site";
	public final static String SITE = "siteDescription";

	public final static String CLE_ADRESSE = "adresse_t";
	public final static String ADRESSE = "adresseTag1";

	public final static String CLE_VILLE = "tabpage_ville";
	public final static String VILLE = "villeDescription";
	
	public final static String CLE_CODE_POSTAL = "codepostal_t";
	public final static String CODE_POSTAL = "codePostal";

	public final static String CLE_TELEPHONE = "telephones";
	public final static String TELEPHONE = "telephone1";
	
	public final static String CLE_STATUT = "tabpage_statut";
	public final static String STATUT = "statutDescription";

	public final static String CLE_DATE_CREATION = "dateCreation";
	public final static String DATE_CREATION = "dateCreation";

	public AdresseOngletTrieListe() {
		addComparator(CLE_SITE, SITE, DATE_CREATION);
		
		addNewComparator(CLE_DATE_CREATION, DATE_CREATION);
		
		addComparator(CLE_ADRESSE, ADRESSE, DATE_CREATION);
		
		addComparator(CLE_VILLE, VILLE, DATE_CREATION);

		addComparator(CLE_CODE_POSTAL, CODE_POSTAL, DATE_CREATION);
		
		addComparator(CLE_TELEPHONE, TELEPHONE, DATE_CREATION);
		
		addComparator(CLE_STATUT, STATUT, DATE_CREATION);
	}
	
}
