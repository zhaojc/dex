package com.lotoquebec.cardex.business.fabrique.dossier;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.fabrique.dossier.regle.RetirerLiaisonEntreDossierVenteMineurEtSocieteRA0003;
import com.lotoquebec.cardex.business.fabrique.dossier.regle.RetirerLiaisonEntreDossierVenteMineurEtSocieteRA0007;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.fabrique.BusinessValidationFabrique;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.DAOException;

/**
 * Traitement d'affaire lors de la suppression d'un lien de dossier avec un autre dossier via le dossier
 * @author levassc
 *
 */
public class SupprimerLienDossierDossierBusinessValidationFabrique extends BusinessValidationFabrique<Dossier>{

	private CardexAuthenticationSubject subject; 
	private Dossier liaisonDossier;
	
	/**
	 * @param subject
	 * @param societe
	 */
	public SupprimerLienDossierDossierBusinessValidationFabrique(CardexAuthenticationSubject subject,
			Dossier liaisonDossier) {
		super();
		this.subject = subject;
		this.liaisonDossier = liaisonDossier;
	}

	protected void constuireValidation(Dossier dossier) throws BusinessResourceException, BusinessRuleException, DAOException{
		Dossier dossierOrigine = FabriqueCardexDAO.getInstance().getDossierDAO().find(subject, dossier);
		Dossier liaisonDossierFind = FabriqueCardexDAO.getInstance().getDossierDAO().find(subject, liaisonDossier);
        
		validationRegleAffaire.addRegleAffaire( new RetirerLiaisonEntreDossierVenteMineurEtSocieteRA0003(subject, dossierOrigine, liaisonDossierFind) );
		validationRegleAffaire.addRegleAffaire( new RetirerLiaisonEntreDossierVenteMineurEtSocieteRA0007(subject, dossierOrigine, liaisonDossierFind));
	}

}
