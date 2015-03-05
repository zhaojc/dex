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
 * RA0004:Pour un dossier de vente aux mineurs à l'exception du dossier d'echantillon.  
 * Le retrait d'une société RDD va retirer le lien entre dossier de vente au 
 * mineur et le dossier d'échantillon
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
 * Bref le retrait du lien 3 entraine la suppression du lien 1 (qu'on fait ici)
 * 
 * @author levassc
 *
 */
public class RetirerLiaisonEntreDossierVenteMineurEtSocieteRA0004 implements RegleAffaire<Dossier>{

	private CardexAuthenticationSubject subject;
	private Dossier venteMineurDossier;
	private Societe societe;

	/**
	 * @param subject
	 * @param dossierOrigine
	 * @param dossierEchantillon
	 */
	public RetirerLiaisonEntreDossierVenteMineurEtSocieteRA0004(CardexAuthenticationSubject subject,
			Dossier venteMineurDossier, Societe societe) {
		super();
		this.subject = subject;
		this.venteMineurDossier = venteMineurDossier;
		this.societe = societe;
	}

	public void executer(Dossier t) throws DAOException {
    	
    	// retirer la liaison entre les dossiers 
        if(venteMineurDossier.getSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)
        && venteMineurDossier.getType() == GlobalConstants.Type.VENTE_MINEUR
        && venteMineurDossier.getCategorie() != GlobalConstants.CategorieClientMystere.ECHANTILLON){
    		Map<Dossier,Dossier> dossierEchantillons = FabriqueCardexDAO.getInstance().getLiaisonDAO().obtenirEntiteEntreDeuxEntite(subject, venteMineurDossier, societe, Dossier.class);
    		
    		if (dossierEchantillons.size() == 1){
    			Dossier echantillonDossier = dossierEchantillons.values().iterator().next();
    			
    			for(LiaisonEntiteVO liaisonEntiteVO:echantillonDossier.getLiaisonEntites()){
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
