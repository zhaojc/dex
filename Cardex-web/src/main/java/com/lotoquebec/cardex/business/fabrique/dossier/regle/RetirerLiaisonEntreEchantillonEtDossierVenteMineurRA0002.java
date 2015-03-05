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
 * RA0002 : Pour une société RDD le retrait du dossier de vente au mineur 
 * retire la liaison du dossier vente au mineur et dossier d'échantillon
 * 
 * @author levassc
 *
 */
public class RetirerLiaisonEntreEchantillonEtDossierVenteMineurRA0002 implements RegleAffaire<Dossier>{

	private CardexAuthenticationSubject subject;
	private Societe societeRDD;
	private Dossier venteMineurDossier;

	/**
	 * @param subject
	 * @param dossierOrigine
	 * @param dossierEchantillon
	 */
	public RetirerLiaisonEntreEchantillonEtDossierVenteMineurRA0002(CardexAuthenticationSubject subject,
			Societe societeRDD, Dossier venteMineurDossier) {
		super();
		this.subject = subject;
		this.societeRDD = societeRDD;
		this.venteMineurDossier = venteMineurDossier;
	}

	public void executer(Dossier t) throws DAOException {
    	
    	// retirer la liaison entre les dossiers 
        if(societeRDD.isIndicateurRdd()
		&& venteMineurDossier.getSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)
		&& venteMineurDossier.getType() == GlobalConstants.Type.VENTE_MINEUR
		&& venteMineurDossier.getCategorie() != GlobalConstants.CategorieClientMystere.ECHANTILLON){
    		Map<Dossier,Dossier> dossiers = FabriqueCardexDAO.getInstance().getLiaisonDAO().obtenirEntiteEntreDeuxEntite(subject, venteMineurDossier, societeRDD, Dossier.class);
    		
    		if (dossiers.size() == 1){
    			Dossier echantillonDossier = dossiers.values().iterator().next();
				
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
