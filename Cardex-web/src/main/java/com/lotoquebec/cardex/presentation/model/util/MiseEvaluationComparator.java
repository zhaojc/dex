package com.lotoquebec.cardex.presentation.model.util;

import java.util.Comparator;

import com.lotoquebec.cardex.presentation.model.form.MiseEvaluationForm;
import com.lotoquebec.cardexCommun.GlobalConstants;

/**
 * Tri pour les sections mises d'une évaluation.
 * @author levassc
 *
 */
public class MiseEvaluationComparator implements Comparator<MiseEvaluationForm> {

	public int compare(MiseEvaluationForm miseEvaluationForm1, MiseEvaluationForm miseEvaluationForm2) {
		
		if (isTypeJeu(miseEvaluationForm1, GlobalConstants.TypeJeu.MACHINES_A_SOUS)
		&& isTypeJeu(miseEvaluationForm2, GlobalConstants.TypeJeu.MACHINES_A_SOUS))
			return 0;
		
		if (isTypeJeu(miseEvaluationForm1, GlobalConstants.TypeJeu.MACHINES_A_SOUS))
			return -1;

		if (isTypeJeu(miseEvaluationForm2, GlobalConstants.TypeJeu.MACHINES_A_SOUS))
			return 1;

		if (isTypeJeu(miseEvaluationForm1, GlobalConstants.TypeJeu.TABLES_DE_JEUX))
			return -1;

		if (isTypeJeu(miseEvaluationForm2, GlobalConstants.TypeJeu.TABLES_DE_JEUX))
			return 1;
		
		if (isTypeJeu(miseEvaluationForm1, GlobalConstants.TypeJeu.LOTERIES))
			return -1;

		if (isTypeJeu(miseEvaluationForm2, GlobalConstants.TypeJeu.LOTERIES))
			return 1;
		
		return 0;
	}

	private boolean isTypeJeu(MiseEvaluationForm evaluationForm, String typeJeu){
		return typeJeu.equals(evaluationForm.getJeuxForm().getTypeJeu());
	}
	
}
