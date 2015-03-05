package com.lotoquebec.cardex.business.fabrique.dossier;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.fabrique.dossier.regle.RetirerLiaisonEntreDossierEchantillonEtDossierVenteMineurEtSocieteRA0005;
import com.lotoquebec.cardex.business.fabrique.dossier.regle.RetirerLiaisonEntreDossierVenteMineurEtSocieteRA0004;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
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
public class SupprimerLienSocieteDossierBusinessValidationFabrique extends BusinessValidationFabrique<Dossier>{

	private CardexAuthenticationSubject subject; 
	private Societe societe;
	
	/**
	 * @param subject
	 * @param societe
	 */
	public SupprimerLienSocieteDossierBusinessValidationFabrique(CardexAuthenticationSubject subject,
			Societe societe) {
		super();
		this.subject = subject;
		this.societe = societe;
	}

	protected void constuireValidation(Dossier dossier) throws DAOException, BusinessResourceException, BusinessRuleException{
		Dossier dossierOrigine = FabriqueCardexDAO.getInstance().getDossierDAO().find(subject, dossier);
        dossierOrigine.setLien(societe.getCle());
        dossierOrigine.setLienSite(societe.getSite());
        
		validationRegleAffaire.addRegleAffaire( new RetirerLiaisonEntreDossierVenteMineurEtSocieteRA0004(subject, dossierOrigine, societe));	
		validationRegleAffaire.addRegleAffaire( new RetirerLiaisonEntreDossierEchantillonEtDossierVenteMineurEtSocieteRA0005(subject, dossierOrigine, societe));
		
	}
	

}
