package com.lotoquebec.cardex.business.fabrique.dossier.regle;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.regle.RegleAffaire;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.user.CardexPrivilege;

/**
 * RA0024 : Ajout d'une pièce jointe dans un dossier. 
 * Mettre la confidentialité de l'utilisateur dans la pièce jointe,
 * sauf pour les dossiers de l'entité investigation.
 * Pour ce dernier, mettre la confidentialité à 1.
 * 
 * @author mazzucr
 *
 */
public class AssignerConfidentialitePieceJointeRA0024 implements RegleAffaire<Photo>{
    private CardexAuthenticationSubject subject;
    private Dossier dossier;

    public AssignerConfidentialitePieceJointeRA0024(CardexAuthenticationSubject subject, 
            Dossier dossier) {
        super();
        this.subject = subject;
        this.dossier = dossier;
    }

    public void executer(Photo photo) throws DAOException {
        
        if(dossier.getEntite()==GlobalConstants.Entite.INVESTIGATION){
            photo.setConfidentialite(GlobalConstants.Confidentialite.UN);
            
        }else{
            CardexPrivilege privilege = (CardexPrivilege) subject.getPrivilege();
            photo.setConfidentialite(privilege.getNiveauConfidentialite());
        }
    }
}
