package com.lotoquebec.cardex.servlet;

import javax.servlet.http.HttpServletRequest;

import com.lotoquebec.cardex.presentation.model.form.rapport.CriteresRapportForm;
import com.lotoquebec.cardex.presentation.model.form.rapport.RapportForm;

/**
 * Cette classe est utilisé pour lancer une classe en réflexion.
 * La classe lancé est la classe qui prépare le rapport jasper.
 */
public class CritereRapportAffichagePDF extends RapportAffichage {

	@Override
	protected RapportForm obtenirRapportForm(HttpServletRequest request){
        CriteresRapportForm criteresRapportForm = (CriteresRapportForm) super.obtenirRapportForm(request);
        criteresRapportForm.initDateDebut();
        criteresRapportForm.setLancerRapport(false);
    	return criteresRapportForm;
	}
    
}