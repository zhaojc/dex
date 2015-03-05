package com.lotoquebec.cardex.business.fabrique.dossier;

import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.fabrique.dossier.validateur.SujetDateDeNaissanceValidateurRV0009;
import com.lotoquebec.cardex.business.fabrique.dossier.validateur.SujetSansTypeAgeValidateurRV0008;
import com.lotoquebec.cardexCommun.business.fabrique.BusinessValidationFabrique;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

public class SauvegardeSujetBusinessValidationFabrique extends BusinessValidationFabrique<Sujet>{

    public SauvegardeSujetBusinessValidationFabrique() {
    super();
    }

    protected void constuireValidation(Sujet sujet) throws BusinessResourceException, BusinessRuleException{

        validationValidateur.addValidateur( new SujetSansTypeAgeValidateurRV0008());
        validationValidateur.addValidateur( new SujetDateDeNaissanceValidateurRV0009());
    }
}