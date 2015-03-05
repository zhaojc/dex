package com.lotoquebec.cardex.business.fabrique.dossier.regle;

import java.util.Map;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.regle.RegleAffaire;
import com.lotoquebec.cardexCommun.business.vo.LiaisonEntiteVO;
import com.lotoquebec.cardexCommun.exception.DAOException;


/**
 * RA0003:Pour un dossier d'échantillon.  Le retrait du dossier de type vente au 
 * mineur à l'exception de la catégorie échantillon, retirer le lien entre le 
 * dossier de vente au mineur et de ça société RDD
 * 
 * Dossier échantillon
 *  |    \ 
 *  |   (lien 2)
 *  |        \
 *(lien 1)		Société RDD
 *  |         /
 *  |     (lien 3)
 *  |    /
 * Dossier de vente aux mineur
 * 
 * Bref le retrait du lien 1 entraine la suppression du lien 2 et 3 (qu'on fait ici)
 * 
 * @author levassc
 *
 */
public class RetirerLiaisonEntreDossierVenteMineurEtSocieteRA0003 implements RegleAffaire<Dossier>{

	private CardexAuthenticationSubject subject;
	private Dossier echantillonDossier;
	private Dossier venteMineurDossier;

	/**
	 * @param subject
	 * @param dossierOrigine
	 * @param dossierEchantillon
	 */
	public RetirerLiaisonEntreDossierVenteMineurEtSocieteRA0003(CardexAuthenticationSubject subject,
			Dossier dossierOrigine, Dossier liaisonDossier) {
		super();
		this.subject = subject;
		this.echantillonDossier = dossierOrigine;
		this.venteMineurDossier = liaisonDossier;
	}

	public void executer(Dossier t) throws DAOException {
    	
    	// retirer la liaison entre les dossiers 
        if(echantillonDossier.getSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)
        && echantillonDossier.getCategorie() == GlobalConstants.CategorieClientMystere.ECHANTILLON
		&& venteMineurDossier.getSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)
		&& venteMineurDossier.getType() == GlobalConstants.Type.VENTE_MINEUR
		&& venteMineurDossier.getCategorie() != GlobalConstants.CategorieClientMystere.ECHANTILLON){
    		Map<Societe,Societe> societes = FabriqueCardexDAO.getInstance().getLiaisonDAO().obtenirEntiteEntreDeuxEntite(subject, echantillonDossier, venteMineurDossier, Societe.class);
    		
    		if (societes.size() == 1){
    			Societe societe = societes.values().iterator().next();
				
    			for(LiaisonEntiteVO liaisonEntiteVO:societe.getLiaisonEntites()){
    				if (venteMineurDossier.equals(liaisonEntiteVO.getDestinationEntite()))
    					FabriqueCardexDAO.getInstance().getLiaisonDAO().supprimerLiaison(subject, liaisonEntiteVO);
    			}
	
	    		//effacer ne numéro de vague
				venteMineurDossier.setReference1("");
	    		FabriqueCardexDAO.getInstance().getDossierDAO().update(subject, venteMineurDossier);
    		}
		}   
	}

}
