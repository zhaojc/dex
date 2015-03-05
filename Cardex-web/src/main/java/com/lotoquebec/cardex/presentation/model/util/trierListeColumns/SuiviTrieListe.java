/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class SuiviTrieListe extends TrierListeColumns {

	public final static String CLE_NUMERO_CARDEX = "v_do_numero_dossier";
	public final static String NUMERO_CARDEX = "dossier.numeroCardex.affichage";

	public final static String CLE_ACTIVITE = "Activité";
	public final static String ACTIVITE = "activiteDescription";
	
	public final static String CLE_DEMANDEUR = "v_sv_demandeur_t2";
	public final static String DEMANDEUR = "demandeurDescription";

	public final static String CLE_INTERVENANT = "v_co_cree_par_t";
	public final static String INTERVENANT = "intervenantDescription";
	
	public final static String CLE_SUIVI = "v_sv_suivi_t";
	//public final static String SUIVI = "suivi";
	
	public final static String CLE_DATE_PREVUE = "d_sv_date_prevue_t";
	public final static String DATE_PREVUE = "datePrevue";
	
	public final static String CLE_DATE_COMPLETE = "Complété";
	public final static String DATE_COMPLETE = "dateCompletee";
	

	public SuiviTrieListe() {
		addNewComparator(CLE_NUMERO_CARDEX, NUMERO_CARDEX);
		
		addComparator(CLE_ACTIVITE, ACTIVITE, NUMERO_CARDEX);
		
		addComparator(CLE_DEMANDEUR, DEMANDEUR, NUMERO_CARDEX);

		addComparator(CLE_INTERVENANT, INTERVENANT, NUMERO_CARDEX);
		
		addComparator(CLE_DATE_PREVUE, DATE_PREVUE, NUMERO_CARDEX);
		
		addComparator(CLE_DATE_COMPLETE, DATE_COMPLETE, NUMERO_CARDEX);
	}
	
}
