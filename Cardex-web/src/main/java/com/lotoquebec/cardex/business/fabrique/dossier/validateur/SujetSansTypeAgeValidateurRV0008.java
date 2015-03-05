package com.lotoquebec.cardex.business.fabrique.dossier.validateur;

import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.validateur.Validateur;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;

public class SujetSansTypeAgeValidateurRV0008 implements Validateur<Sujet>
{


    public SujetSansTypeAgeValidateurRV0008 (){
        super();
    }

    public boolean isValide(Sujet t) throws BusinessException, DAOException {
        if(t.getTypeAge() == 0) {
            throw new BusinessException( getMessage() );
    }
    return true;
    }
    
    public BusinessMessage getMessage() throws BusinessResourceException {
        return new BusinessMessage("ME0007"); //Le type d’âge est obligatoire pour un sujet. 
                                              //Veuillez choisir entre Réel, Estimé ou Inconnu.

    }
    
}
