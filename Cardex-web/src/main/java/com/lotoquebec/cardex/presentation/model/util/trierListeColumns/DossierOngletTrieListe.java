/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import java.io.Serializable;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class DossierOngletTrieListe extends TrierListeColumns implements Serializable{

	public final static String CLE_NUMERO_DOSSIER = "v_do_ancienne_reference";
	public final static String NUMERO_DOSSIER = "numeroDossier";

	public final static String CLE_NUMERO_CARDEX = "v_do_numero_dossier";
	public final static String NUMERO_CARDEX = "numeroCardex.affichage";

	public final static String CLE_SEVERITE = "i_sev_t";
	public final static String SEVERITE = "severiteDescription";
	
	public final static String CLE_CONFIDENTIALITE = "i_cc_commentaire_t";
	public final static String CONFIDENTIALITE = "confidentialiteDescription";

	public final static String CLE_NATURE = "i_nature_t";
	public final static String NATURE = "natureDescription";
	
	public final static String CLE_TYPE = "i_type_t";
	public final static String TYPE = "typeDescription";

	public final static String CLE_ROLE = "tabpage_role";
	public final static String ROLE = "roleDescription";

	public final static String CLE_CATEGORIE = "tabpage_categorie";
	public final static String CATEGORIE = "categorieDescription";
	
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

	public final static String CLE_LIEN_CREATEUR = "lie.par";
	public final static String LIEN_CREATEUR = "lienCreateur";
	
	public final static String CLE_DATE_LIAISON = "lie.le";
	public final static String DATE_LIAISON = "lienDateCreation";	

	public DossierOngletTrieListe() {
		addComparator(CLE_NUMERO_DOSSIER, NUMERO_DOSSIER, NUMERO_CARDEX);
		
		addNewComparator( CLE_NUMERO_CARDEX, NUMERO_CARDEX );
		
		addComparator(CLE_SEVERITE, SEVERITE, NUMERO_CARDEX);
		
		addComparator(CLE_CONFIDENTIALITE, CONFIDENTIALITE, NUMERO_CARDEX);

		addComparator(CLE_NATURE, NATURE, NUMERO_CARDEX);
		
		addComparator(CLE_TYPE, TYPE, NUMERO_CARDEX);
		
		addComparator(CLE_CATEGORIE, CATEGORIE, NUMERO_CARDEX);
		
		addComparator(CLE_STATUT, STATUT, NUMERO_CARDEX);
		
		addComparator(CLE_FONDE, FONDE, NUMERO_CARDEX);
		
		addComparator(CLE_DATE_DEBUT, DATE_DEBUT, NUMERO_CARDEX);
		
		addComparator(CLE_DATE_FIN, DATE_FIN, NUMERO_CARDEX);
		
		addComparator(CLE_INTERVENANT, INTERVENANT, NUMERO_CARDEX);

		addComparator(CLE_ROLE, ROLE, NUMERO_CARDEX);

		addComparator(CLE_LIEN_CREATEUR, LIEN_CREATEUR, NUMERO_CARDEX);

		addComparator(CLE_DATE_LIAISON, DATE_LIAISON, NUMERO_CARDEX);

	}
	
}
