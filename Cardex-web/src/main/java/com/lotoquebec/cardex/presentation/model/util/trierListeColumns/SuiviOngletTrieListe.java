/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class SuiviOngletTrieListe extends TrierListeColumns {

	public final static String CLE_SITE = "l_si_cle_t";
	public final static String SITE = "siteDescription";

	public final static String CLE_ACTIVITE = "Activité";
	public final static String ACTIVITE = "activiteDescription";
	
	public final static String CLE_DEMANDEUR = "v_sv_demandeur_t2";
	public final static String DEMANDEUR = "demandeurDescription";

	public final static String CLE_INTERVENANT = "v_co_cree_par_t";
	public final static String INTERVENANT = "intervenantDescription";
	
	public final static String CLE_SUIVI = "v_sv_suivi_t";
	
	public final static String CLE_DATE_PREVUE = "d_sv_date_prevue_t";
	public final static String DATE_PREVUE = "datePrevue";
	
	public final static String CLE_DATE_COMPLETE = "d_sv_date_completee_t";
	public final static String DATE_COMPLETE = "dateCompletee";
	
	public final static String CLE_DATE_DEMANDE = "d_sv_date_creation_t2";
	public final static String DATE_DEMANDE = "dateCreation";

	public final static String CLE_STATUT = "tabpage_statut";
	public final static String STATUT = "statutDescription";
	
	public final static String CLE_CONFIDENTIALITE = "i_cc_commentaire_t";
	public final static String CONFIDENTIALITE = "confidentialiteSuiviDescription";
	
	public final static String CLE_APPROBATEUR = "Approbation";
	public final static String APPROBATEUR = "approbateurDescription";
	
	public final static String CLE_DATE_APPROBATION = "d_co_approbation";
	public final static String DATE_APPROBATION = "dateApprobation";

	public SuiviOngletTrieListe() {
		addComparator(CLE_SITE, SITE, DATE_PREVUE);
		
		addComparator(CLE_ACTIVITE, ACTIVITE, DATE_PREVUE);
		
		addComparator(CLE_DEMANDEUR, DEMANDEUR, DATE_DEMANDE);

		addComparator(CLE_INTERVENANT, INTERVENANT, DATE_PREVUE);
		
		addNewComparator(CLE_DATE_PREVUE, DATE_PREVUE);
		
		addComparator(CLE_DATE_COMPLETE, DATE_COMPLETE, DATE_PREVUE);

		addNewComparator(CLE_CONFIDENTIALITE, CONFIDENTIALITE);
		
		addNewComparator(CLE_STATUT, STATUT);
		
		addNewComparator(CLE_CONFIDENTIALITE, CONFIDENTIALITE);
		
		addNewComparator(CLE_DATE_DEMANDE, DATE_DEMANDE);
		
		addNewComparator(CLE_DATE_APPROBATION, DATE_APPROBATION);
		
		addComparator(CLE_APPROBATEUR, APPROBATEUR, DATE_APPROBATION);
		
	}
	
}
