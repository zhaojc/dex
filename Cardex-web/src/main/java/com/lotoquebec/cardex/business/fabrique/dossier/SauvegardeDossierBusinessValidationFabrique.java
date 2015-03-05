package com.lotoquebec.cardex.business.fabrique.dossier;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.fabrique.dossier.regle.RetirerSousCategorieIdentiqueRA0025;
import com.lotoquebec.cardex.business.fabrique.dossier.regle.ViderSujetInteretDossierSujetInteret;
import com.lotoquebec.cardex.business.fabrique.dossier.validateur.ConformiteBilletDossierClientMystereValidateurRV0006;
import com.lotoquebec.cardex.business.fabrique.dossier.validateur.InfractionBilletDossierClientMystereValidateurRV0005;
import com.lotoquebec.cardex.business.fabrique.dossier.validateur.NumeroVagueClientMystereValidateurRV0002;
import com.lotoquebec.cardex.business.fabrique.dossier.validateur.PrecedentDossierOrdreDossierClientMystereValidateurRV0004;
import com.lotoquebec.cardex.business.fabrique.dossier.validateur.SuivantDossierOrdreDossierClientMystereValidateurRV0004;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.fabrique.BusinessValidationFabrique;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Traitement d'affaire lors de la sauvegarde d'un dossier
 * @author levassc
 *
 */
public class SauvegardeDossierBusinessValidationFabrique extends BusinessValidationFabrique<Dossier>{

	private CardexAuthenticationSubject subject; 
	
	/**
	 * @param subject
	 */
	public SauvegardeDossierBusinessValidationFabrique(CardexAuthenticationSubject subject) {
		super();
		this.subject = subject;
	}

	protected void constuireValidation(Dossier dossier) throws BusinessResourceException, BusinessRuleException{
		validationValidateur.addValidateur( new NumeroVagueClientMystereValidateurRV0002(subject, dossier) );
		validationValidateur.addValidateur( new PrecedentDossierOrdreDossierClientMystereValidateurRV0004(subject, dossier) );
		validationValidateur.addValidateur( new SuivantDossierOrdreDossierClientMystereValidateurRV0004(subject, dossier) );
		validationValidateur.addValidateur( new InfractionBilletDossierClientMystereValidateurRV0005(subject, dossier) );
		validationValidateur.addValidateur( new ConformiteBilletDossierClientMystereValidateurRV0006(subject, dossier) );
		
		validationRegleAffaire.addRegleAffaire( new ViderSujetInteretDossierSujetInteret() );
		validationRegleAffaire.addRegleAffaire( new RetirerSousCategorieIdentiqueRA0025(subject) );
	}

}
