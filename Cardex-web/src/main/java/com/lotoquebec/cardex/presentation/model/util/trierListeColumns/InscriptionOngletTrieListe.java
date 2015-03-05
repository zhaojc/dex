/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class InscriptionOngletTrieListe extends TrierListeColumns {

	public final static String CLE_DATE_INSCRIPTION = "d_is_date_creation_t2";
	public final static String DATE_INSCRIPTION = "dateInscription";
	
	public final static String CLE_SITE_CHOISIS = "l_si_cle_inclus_t3";
	
	public final static String CLE_DUREE = "v_is_duree_t";
	public final static String DUREE = "duree";
	
	public final static String CLE_DATE_DEBUT = "d_is_date_debut_t";
	public final static String DATE_DEBUT = "dateDebut";
	
	public final static String CLE_DATE_FIN = "d_is_date_fin_t";
	public final static String DATE_FIN = "dateFin";
	
	public final static String CLE_PERIODE = "i_is_periode_t";
	public final static String PERIODE = "periodeDescription";

	public final static String CLE_STATUT = "tabpage_statut";
	public final static String STATUT = "statutDescription";

	public final static String CLE_INTERVENANT = "v_intervenant_t";
	public final static String INTERVENANT = "createurDescription";

	public final static String CLE_RENCONTRE_FINALE = "rencontre_finale_t";
	public final static String DATE_RENCONTRE_FINALE = "dateRencontreFinale";
	
	public final static String CLE_RENCONTRE_INITIALE = "aide_initiale";
	public final static String DATE_RENCONTRE_INITIALE = "dateRencontreInitiale";
	
	public InscriptionOngletTrieListe() {
		addNewComparator(CLE_DATE_INSCRIPTION, DATE_INSCRIPTION);
		
		addNewComparator(CLE_RENCONTRE_INITIALE, DATE_RENCONTRE_INITIALE);
		
		addNewComparator(CLE_RENCONTRE_FINALE, DATE_RENCONTRE_FINALE);
		
		addComparator(CLE_INTERVENANT, INTERVENANT, DATE_INSCRIPTION);
		
		addComparator(CLE_DUREE, DUREE, DATE_INSCRIPTION);
		
		addComparator(CLE_DATE_DEBUT, DATE_DEBUT, DATE_INSCRIPTION);
		
		addComparator(CLE_DATE_FIN, DATE_FIN, DATE_INSCRIPTION);
		
		addComparator(CLE_PERIODE, PERIODE, DATE_INSCRIPTION);
		
		addComparator(CLE_STATUT, STATUT, DATE_INSCRIPTION);
		
		addComparator(CLE_INTERVENANT, INTERVENANT, DATE_INSCRIPTION);
	}
	
}
