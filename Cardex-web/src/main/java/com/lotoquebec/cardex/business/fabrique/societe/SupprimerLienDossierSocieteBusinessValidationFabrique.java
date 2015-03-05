package com.lotoquebec.cardex.business.fabrique.societe;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.fabrique.dossier.regle.RetirerLiaisonEntreEchantillonEtDossierVenteMineurRA0001;
import com.lotoquebec.cardex.business.fabrique.dossier.regle.RetirerLiaisonEntreEchantillonEtDossierVenteMineurRA0002;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.fabrique.BusinessValidationFabrique;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Traitement d'affaire lors de la suppression d'un lien de dossier via une société
 * @author levassc
 *
 */
public class SupprimerLienDossierSocieteBusinessValidationFabrique extends BusinessValidationFabrique<Dossier>{

	private CardexAuthenticationSubject subject; 
	private Societe societeRDD;
	
	/**
	 * @param subject
	 * @param societeRDD
	 */
	public SupprimerLienDossierSocieteBusinessValidationFabrique(CardexAuthenticationSubject subject,
			Societe societeRDD) {
		super();
		this.subject = subject;
		this.societeRDD = societeRDD;
	}

	protected void constuireValidation(Dossier liaisonDossier) throws BusinessResourceException, BusinessRuleException{
		validationRegleAffaire.addRegleAffaire( new RetirerLiaisonEntreEchantillonEtDossierVenteMineurRA0001(subject, societeRDD, liaisonDossier) );
		validationRegleAffaire.addRegleAffaire( new RetirerLiaisonEntreEchantillonEtDossierVenteMineurRA0002(subject, societeRDD, liaisonDossier));
	}

}
