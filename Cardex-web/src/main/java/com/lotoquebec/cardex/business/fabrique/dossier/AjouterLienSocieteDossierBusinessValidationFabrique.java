package com.lotoquebec.cardex.business.fabrique.dossier;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.fabrique.dossier.regle.LierDernierDossierEchantillonRA0006;
import com.lotoquebec.cardex.business.fabrique.dossier.validateur.SocieteOrdreDossierClientMystereValidateurRV0004;
import com.lotoquebec.cardex.business.fabrique.dossier.validateur.UneSocieteClientMystereValidateurRV0001;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.fabrique.BusinessValidationFabrique;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.DAOException;

public class AjouterLienSocieteDossierBusinessValidationFabrique extends BusinessValidationFabrique<Dossier>{

	private CardexAuthenticationSubject subject; 
	private Societe societe;
	
	/**
	 * @param subject
	 * @param societe
	 */
	public AjouterLienSocieteDossierBusinessValidationFabrique(CardexAuthenticationSubject subject,
			Societe societe) {
		super();
		this.subject = subject;
		this.societe = societe;
	}

	protected void constuireValidation(Dossier dossier) throws BusinessResourceException, BusinessRuleException, DAOException{
		Dossier dossierOrigine = FabriqueCardexDAO.getInstance().getDossierDAO().find(subject, dossier);
        dossierOrigine.setLien(societe.getCle());
        dossierOrigine.setLienSite(societe.getSite());

        validationValidateur.addValidateur( new UneSocieteClientMystereValidateurRV0001(subject, dossierOrigine, societe) );
        validationValidateur.addValidateur( new SocieteOrdreDossierClientMystereValidateurRV0004(subject, dossierOrigine, new SocieteVO(dossierOrigine.getLienCle(), dossierOrigine.getLienSite())) );
        validationRegleAffaire.addRegleAffaire( new LierDernierDossierEchantillonRA0006(subject, dossierOrigine, societe) );
	}
	

}
