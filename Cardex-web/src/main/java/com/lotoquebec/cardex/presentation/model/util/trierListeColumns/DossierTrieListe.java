/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class DossierTrieListe extends TrierListeColumns {

	public final static String CLE_NUMERO_DOSSIER = "v_do_ancienne_reference";
	public final static String NUMERO_DOSSIER = "numeroDossier";

	public final static String CLE_NUMERO_CARDEX = "v_do_numero_dossier";
	public final static String NUMERO_CARDEX = "numeroCardex.affichage";

	public final static String CLE_REFERENCE1 = "v_do_reference1";
	public final static String REFERENCE1 = "reference1";

	public final static String CLE_REFERENCE2 = "v_do_reference2";
	public final static String REFERENCE2 = "reference2";

	public final static String CLE_REFERENCE3 = "v_do_reference3";
	public final static String REFERENCE3 = "reference3";

	public final static String CLE_SEVERITE = "i_sev_t";
	public final static String SEVERITE = "severiteDescription";
	
	public final static String CLE_CONFIDENTIALITE = "i_cc_commentaire_t";
	public final static String CONFIDENTIALITE = "confidentialiteDescription";
	
	public final static String CLE_TYPE = "i_type_t";
	public final static String TYPE = "typeDescription";

	public final static String CLE_CATEGORIE = "tabpage_categorie";
	public final static String CATEGORIE = "categorieDescription";
	
	public final static String CLE_PERIODE = "tabpage_periode";
	public final static String PERIODE = "periodeDescription";
	
	public final static String CLE_STATUT = "tabpage_statut";
	public final static String STATUT = "statutDescription";

	public final static String CLE_FONDE = "tabpage_fonde";
	public final static String FONDE = "fondeDescription";
	
	public final static String CLE_DATE_DEBUT = "d_is_date_debut_t";
	public final static String DATE_DEBUT = "dateDebut";
	
	public final static String CLE_DATE_FIN = "d_is_date_fin_t";
	public final static String DATE_FIN = "dateFin";

	public final static String CLE_INTERVENANT = "v_do_assigne_a";
	public final static String INTERVENANT = "intervenant";

	public DossierTrieListe() {
		addComparator(CLE_NUMERO_DOSSIER, NUMERO_DOSSIER, NUMERO_CARDEX);
		
		addNewComparator( CLE_NUMERO_CARDEX, NUMERO_CARDEX );
		
		addComparator(CLE_REFERENCE1, REFERENCE1, NUMERO_CARDEX);
		
		addComparator(CLE_REFERENCE2, REFERENCE2, NUMERO_CARDEX);

		addComparator(CLE_REFERENCE3, REFERENCE3, NUMERO_CARDEX);

		addComparator(CLE_SEVERITE, SEVERITE, NUMERO_CARDEX);
		
		addComparator(CLE_CONFIDENTIALITE, CONFIDENTIALITE, NUMERO_CARDEX);
		
		addComparator(CLE_TYPE, TYPE, NUMERO_CARDEX);
		
		addComparator(CLE_CATEGORIE, CATEGORIE, NUMERO_CARDEX);
		
		addComparator(CLE_PERIODE, PERIODE, NUMERO_CARDEX);
		
		addComparator(CLE_STATUT, STATUT, NUMERO_CARDEX);
		
		addComparator(CLE_FONDE, FONDE, NUMERO_CARDEX);
		
		addComparator(CLE_DATE_DEBUT, DATE_DEBUT, NUMERO_CARDEX);
		
		addComparator(CLE_DATE_FIN, DATE_FIN, NUMERO_CARDEX);
		
		addComparator(CLE_INTERVENANT, INTERVENANT, NUMERO_CARDEX);
	}
	
}
