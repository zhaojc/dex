/*
 * Created on 23-Apr-2008
 */
package com.lotoquebec.cardex.presentation.model.util.trierListeColumns;

import java.io.Serializable;

import com.lotoquebec.cardexCommun.model.TrierListeColumns;



/**
 * @author levassc
 */
public class EvaluationOngletTrieListe extends TrierListeColumns implements Serializable {
 
	public final static String CLE_SITE = "tabpage_site";
	public final static String SITE = "siteDescription";

	public final static String CLE_PERIODE_DU = "du";
	public final static String PERIODE_DU = "dateDebutEval";

	public final static String CLE_PERIODE_AU = "au";
	public final static String PERIODE_AU = "dateFinEval";
	
	public final static String CLE_DATE_EVALUATION = "evaluation.effectuee.le";
	public final static String DATE_EVALUATION = "dateEvaluation";

	public final static String CLE_CREATEUR = "v_cn_cree_par";
	public final static String CREATEUR = "createurDescription";

	public final static String CLE_DATE_CREATION = "d_cn_date_creation";
	public final static String DATE_CREATION = "dateCreation";

	public EvaluationOngletTrieListe() {
		addComparator(CLE_SITE, SITE, DATE_CREATION);
		
		addNewComparator(CLE_DATE_CREATION, DATE_CREATION);
		
		addComparator(CLE_PERIODE_DU, PERIODE_DU, DATE_CREATION);
		
		addComparator(CLE_PERIODE_AU, PERIODE_AU, DATE_CREATION);

		addComparator(CLE_DATE_EVALUATION, DATE_EVALUATION, DATE_CREATION);
		
		addComparator(CLE_CREATEUR, CREATEUR, DATE_CREATION);
	}
	
}
