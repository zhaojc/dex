package com.lotoquebec.cardex.business.fabrique.dossier;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.fabrique.dossier.regle.AssignerConfidentialitePieceJointeRA0024;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.fabrique.BusinessValidationFabrique;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.DAOException;

public class AjoutPieceJointeBusinessValidationFabrique extends BusinessValidationFabrique<Photo>{
    private CardexAuthenticationSubject subject; 
    private Dossier dossier;

    public AjoutPieceJointeBusinessValidationFabrique(CardexAuthenticationSubject subject, Dossier dossier) {
        super();
        this.subject = subject;
        this.dossier = dossier;
    }

    protected void constuireValidation(Photo photo) throws BusinessResourceException, BusinessRuleException, DAOException{
        validationRegleAffaire.addRegleAffaire(new AssignerConfidentialitePieceJointeRA0024(subject, dossier));
    }

}
