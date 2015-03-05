package com.lotoquebec.cardex.business.fabrique.dossier;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Urgence;
import com.lotoquebec.cardex.business.fabrique.dossier.regle.RetirerSocieteUrgenceRA0023;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.fabrique.BusinessValidationFabrique;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.DAOException;

/**
 * Traitement affaire lors de la suppression d'un lien entre une société et un dossier via le dossier.
 * @author levassc
 *
 */
public class UrgenceSupprimerLienSocieteDossierBusinessValidationFabrique extends BusinessValidationFabrique<Dossier>{

	private CardexAuthenticationSubject subject; 
	private Urgence urgence;
	
	/**
	 * @param subject
	 * @param societe
	 */
	public UrgenceSupprimerLienSocieteDossierBusinessValidationFabrique(CardexAuthenticationSubject subject,
			Urgence urgence) {
		super();
		this.subject = subject;
		this.urgence = urgence;
	}

	protected void constuireValidation(Dossier dossier) throws DAOException, BusinessResourceException, BusinessRuleException{
		validationRegleAffaire.addRegleAffaire( new RetirerSocieteUrgenceRA0023(subject, urgence));	
	}
	

}
