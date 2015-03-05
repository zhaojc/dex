package com.lotoquebec.cardex.business.fabrique.dossier;

import com.lotoquebec.cardex.business.SujetCriteresRecherche;
import com.lotoquebec.cardex.business.fabrique.dossier.validateur.CriteresRechercheAgeSujetValidateurRV0003;
import com.lotoquebec.cardexCommun.business.fabrique.BusinessValidationFabrique;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

public class RechercheSujetOptionAgeValidationFabrique extends BusinessValidationFabrique<SujetCriteresRecherche>{

    private SujetCriteresRecherche critere; 
    
    /**
     * @param CriteresRechercheSujet
     */
    public RechercheSujetOptionAgeValidationFabrique(SujetCriteresRecherche criteres) {
        super();
        this.critere = criteres;
    }

    protected void constuireValidation(SujetCriteresRecherche criteres) throws BusinessResourceException, BusinessRuleException{

        validationValidateur.addValidateur( new CriteresRechercheAgeSujetValidateurRV0003(critere));
    }

}
