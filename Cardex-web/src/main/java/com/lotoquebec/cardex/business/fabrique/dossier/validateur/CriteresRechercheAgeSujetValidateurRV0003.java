package com.lotoquebec.cardex.business.fabrique.dossier.validateur;

import com.lotoquebec.cardex.business.SujetCriteresRecherche;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.validateur.Validateur;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.DAOException;

public class CriteresRechercheAgeSujetValidateurRV0003 implements Validateur<SujetCriteresRecherche> {

    private SujetCriteresRecherche criteres;
    
    public CriteresRechercheAgeSujetValidateurRV0003(SujetCriteresRecherche criteres) {
        super();
        this.criteres = criteres;
    }

    public boolean isValide(SujetCriteresRecherche t) throws BusinessException, DAOException {
        
        if(criteres.getAge()!= 0 && criteres.isAgeInconnu()==false){
            if(criteres.isAgeEstime()==false && criteres.isAgeReel()== false && criteres.isAgeReelPlusMoins()== false){
                throw new BusinessException( getMessage() );
            }
        }
        return true;
    }

    
    public BusinessMessage getMessage() {
        return new BusinessMessage("ME0003"); //Lorsque l’âge fait partie des critères de recherche d’un sujet, vous devez 
                                              //sélectionner un ou plusieurs des choix suivants : Réel, Réel ±5 et/ou Estimé ±5
    }

}
