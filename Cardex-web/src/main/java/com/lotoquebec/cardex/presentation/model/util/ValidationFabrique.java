/*
 * Created on 17-Mar-2008
 */
package com.lotoquebec.cardex.presentation.model.util;

import org.apache.struts.util.MessageResources;

import com.lotoquebec.cardex.presentation.controller.util.narration.SujetNarration;
import com.lotoquebec.cardex.presentation.controller.util.validation.narrationSujet.DateNaissance;
import com.lotoquebec.cardex.presentation.controller.util.validation.narrationSujet.SujetNotPresent;
import com.lotoquebec.cardex.presentation.controller.util.validation.narrationSujet.Ville;
import com.lotoquebec.cardex.presentation.model.form.BilletForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.util.validation.RequisValidation;
import com.lotoquebec.cardexCommun.util.validation.Validation;
import com.lotoquebec.cardexCommun.util.validation.ValidationList;

/**
 * @author levassc
 */
public class ValidationFabrique {
	
	public static Validation valideSujetNarration(CardexAuthenticationSubject subject, SujetNarration sujetNarration, MessageResources mResources) throws BusinessResourceException{
		Validation validationList = new ValidationList();
		
		validationList.add(new RequisValidation( subject, "v_su_nom", sujetNarration.getNom(), mResources ));
		validationList.add(new RequisValidation( subject, "v_su_prenom", sujetNarration.getPrenom(), mResources ));
		validationList.add(new Ville( subject, sujetNarration ));
		validationList.add(new DateNaissance(sujetNarration.getDateNaissance()));
		validationList.add(new SujetNotPresent(subject, sujetNarration.getNom(), sujetNarration.getPrenom(), sujetNarration.getDateNaissanceDefault()));
		
		return validationList;
	}

	public static Validation valideRechercheValidation(CardexAuthenticationSubject subject, BilletForm billetForm, MessageResources mResources) {
		Validation validationList = new ValidationList();
		
		validationList.add(new RequisValidation( subject, "detaillant", billetForm.getNumeroDetaillantValidation(), mResources ));
		
		return validationList;
	}

	public static Validation valideRechercheProvenance(CardexAuthenticationSubject subject, BilletForm billetForm, MessageResources mResources) {
		Validation validationList = new ValidationList();
		
		validationList.add(new RequisValidation( subject, "detaillant", billetForm.getNumeroDetaillantProvenance(), mResources ));
		
		return validationList;
	}

	public static Validation valideRechercheVerification(CardexAuthenticationSubject subject, BilletForm billetForm, MessageResources mResources) {
		Validation validationList = new ValidationList();
		
		validationList.add(new RequisValidation( subject, "detaillant", billetForm.getNumeroDetaillantVerification(), mResources ));
		
		return validationList;
	}

	public static Validation valideRechercheFautif(CardexAuthenticationSubject subject, BilletForm billetForm, MessageResources mResources) {
		Validation validationList = new ValidationList();
		
		validationList.add(new RequisValidation( subject, "detaillant", billetForm.getNumeroDetaillantFautif(), mResources ));
		
		return validationList;
	}

	public static Validation valideRechercheDetaillant(CardexAuthenticationSubject subject, String numeroDetaillant, MessageResources mResources) {
		Validation validationList = new ValidationList();
		
		validationList.add(new RequisValidation( subject, "detaillant", numeroDetaillant, mResources ));
		
		return validationList;
	}

	
}
