package com.lotoquebec.cardex.business.fabrique.dossier;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Urgence;
import com.lotoquebec.cardex.business.fabrique.dossier.regle.LierSocieteUrgenceRA0021;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.fabrique.BusinessValidationFabrique;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Traitement d'affaire lors de la sauvegarde d'un dossier d'urgence
 * @author levassc
 *
 */
public class UrgenceDossierBusinessValidationFabrique extends BusinessValidationFabrique<Dossier>{

	private CardexAuthenticationSubject subject; 
	private Urgence urgence;
	
	/**
	 * @param subject
	 */
	public UrgenceDossierBusinessValidationFabrique(CardexAuthenticationSubject subject, Urgence urgence) {
		super();
		this.subject = subject;
		this.urgence = urgence;
	}

	protected void constuireValidation(Dossier dossier) throws BusinessResourceException, BusinessRuleException{
		validationRegleAffaire.addRegleAffaire(new LierSocieteUrgenceRA0021(subject,urgence));
	}

}
