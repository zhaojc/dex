package com.lotoquebec.cardex.business.fabrique.societe;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.fabrique.dossier.regle.LierDernierDossierEchantillonRA0006;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.fabrique.BusinessValidationFabrique;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.DAOException;

/**
 * Traitement d'affaire lors de l'ajout d'un lien de dossier via une société
 * @author levassc
 *
 */
public class AjoutLienDossierSocieteBusinessValidationFabrique extends BusinessValidationFabrique<Dossier>{

	private CardexAuthenticationSubject subject; 
	private Societe societeRDD;
	
	/**
	 * @param subject
	 * @param societeRDD
	 */
	public AjoutLienDossierSocieteBusinessValidationFabrique(CardexAuthenticationSubject subject,
			Societe societeRDD) {
		super();
		this.subject = subject;
		this.societeRDD = societeRDD;
	}

	protected void constuireValidation(Dossier dossier) throws BusinessResourceException, BusinessRuleException, DAOException{
		Dossier dossierOrigine = FabriqueCardexDAO.getInstance().getDossierDAO().find(subject, dossier);
        dossierOrigine.setLien(societeRDD.getCle());
        dossierOrigine.setLienSite(societeRDD.getSite());
        
		validationRegleAffaire.addRegleAffaire( new LierDernierDossierEchantillonRA0006(subject, dossierOrigine, societeRDD) );
	}

}
