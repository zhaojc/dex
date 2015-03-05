/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class VehiculeOngletTrieListe extends TrierListeColumns {

	public final static String CLE_CLE = "co_no_fiche_t2";
	public final static String CLE = "cle";

	public final static String CLE_IMMATRICULATION = "V_VE_IMMATRICULATION";
	public final static String IMMATRICULATION = "immatriculation";
	
	public final static String CLE_MARQUE = "tabpage_marque";
	public final static String MARQUE = "marqueDescription";
	
	public final static String CLE_MODELE = "tabpage_modele";
	public final static String MODELE = "modeleDescription";
	
	public final static String CLE_PROVINCE = "tabpage_province";
	public final static String PROVINCE = "province";

	public final static String CLE_ANNEE = "c_ve_annee_t2";
	public final static String ANNEE = "annee";

	public final static String CLE_LIEN_CREATEUR = "lie.par";
	public final static String LIEN_CREATEUR = "lienCreateur";	

	public final static String CLE_DATE_LIAISON = "lie.le";
	public final static String DATE_LIAISON = "lienDateCreation";	

	public VehiculeOngletTrieListe() {
		addNewComparator( CLE_CLE, CLE );
		
		addComparator(CLE_IMMATRICULATION, IMMATRICULATION, CLE);
		
		addComparator(CLE_MARQUE, MARQUE, CLE);
		
		addComparator(CLE_MODELE, MODELE, CLE);
		
		addComparator(CLE_PROVINCE, PROVINCE, CLE);
		
		addComparator(CLE_ANNEE, ANNEE, CLE);
		
		addComparator(CLE_LIEN_CREATEUR, LIEN_CREATEUR, CLE);

		addComparator(CLE_DATE_LIAISON, DATE_LIAISON, CLE);

	}
	
}
