/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class VehiculeTrieListe extends TrierListeColumns {

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

	public final static String CLE_COMMENTAIRE = "v_co_commentaire_t";
	public final static String COMMENTAIRE = "commentaire";


	public VehiculeTrieListe() {
		addNewComparator( CLE_CLE, CLE );
		
		addComparator(CLE_IMMATRICULATION, IMMATRICULATION, CLE);
		
		addComparator(CLE_MARQUE, MARQUE, CLE);
		
		addComparator(CLE_MODELE, MODELE, CLE);
		
		addComparator(CLE_PROVINCE, PROVINCE, CLE);
		
		addComparator(CLE_ANNEE, ANNEE, CLE);

		addComparator(CLE_COMMENTAIRE, COMMENTAIRE, CLE);
	}
	
}
