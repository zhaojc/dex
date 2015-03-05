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
 * RA0007:Pour un dossier de vente au mineur.  Le retrait du dossier 
 * d'échantillon retire le lien avec la société RDD et le dossier de vente au mineur
 * 
 * @author levassc
 *
 */
public class RetirerLiaisonEntreDossierVenteMineurEtSocieteRA0007 implements RegleAffaire<Dossier>{

	private CardexAuthenticationSubject subject;
	private Dossier venteMineurDossier;
	private Dossier echantillonDossier;

	/**
	 * @param subject
	 * @param dossierOrigine
	 * @param dossierEchantillon
	 */
	public RetirerLiaisonEntreDossierVenteMineurEtSocieteRA0007(CardexAuthenticationSubject subject,
			Dossier dossierOrigine, Dossier liaisonDossier) {
		super();
		this.subject = subject;
		this.venteMineurDossier = dossierOrigine;
		this.echantillonDossier = liaisonDossier;
	}

	public void executer(Dossier t) throws DAOException {
    	
    	// retirer la liaison entre les dossiers 
        if(echantillonDossier.getSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)
        && echantillonDossier.getCategorie() == GlobalConstants.CategorieClientMystere.ECHANTILLON
		&& venteMineurDossier.getSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)
		&& venteMineurDossier.getType() == GlobalConstants.Type.VENTE_MINEUR){
    		Map<Societe,Societe> societes = FabriqueCardexDAO.getInstance().getLiaisonDAO().obtenirEntiteEntreDeuxEntite(subject, venteMineurDossier, echantillonDossier, Societe.class);
    		
    		if (societes.size() == 1){
    			Societe societe = societes.values().iterator().next();
				
    			for(LiaisonEntiteVO liaisonEntiteVO:societe.getLiaisonEntites()){
    				if (venteMineurDossier.equals(liaisonEntiteVO.getDestinationEntite()))
    					FabriqueCardexDAO.getInstance().getLiaisonDAO().supprimerLiaison(subject, liaisonEntiteVO);
    			}
	
	    		//effacer ne numéro de vague
				echantillonDossier.setReference1("");
	    		FabriqueCardexDAO.getInstance().getDossierDAO().update(subject, venteMineurDossier);
    		}
		}   
	}

}
