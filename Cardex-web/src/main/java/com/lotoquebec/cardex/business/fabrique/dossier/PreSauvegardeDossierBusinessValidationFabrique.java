package com.lotoquebec.cardex.business.fabrique.dossier;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.fabrique.dossier.validateur.CategorieSansSousCategorieValidateurRV0007;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.fabrique.BusinessValidationFabrique;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Traitement d'affaire lors de la pré sauvegarde d'un dossier
 * @author levassc
 *
 */
public class PreSauvegardeDossierBusinessValidationFabrique extends BusinessValidationFabrique<Dossier>{

	private CardexAuthenticationSubject subject; 
	
	/**
	 * @param subject
	 */
	public PreSauvegardeDossierBusinessValidationFabrique(CardexAuthenticationSubject subject) {
		super();
		this.subject = subject;
	}

	protected void constuireValidation(Dossier dossier) throws BusinessResourceException, BusinessRuleException{
		validationValidateur.addValidateur( new CategorieSansSousCategorieValidateurRV0007(subject) );
	}

}
