package com.lotoquebec.cardex.business.fabrique.dossier.regle;

import java.util.Map;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.facade.FabriqueFacade;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.regle.RegleAffaire;
import com.lotoquebec.cardexCommun.business.vo.LiaisonEntiteVO;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.DAOException;


/**
 * RA0001 : Pour une société RDD le retrait du dossier d'échantillon va retirer 
 * tous les liaisons entre le dossier d'échantillon et les dossiers de vente au 
 * mineur pour cette société RDD.  Ces dossiers de vente au mineur perdent la 
 * liaison avec la société RDD retiré.
 * 
 * @author levassc
 *
 */
public class RetirerLiaisonEntreEchantillonEtDossierVenteMineurRA0001 implements RegleAffaire<Dossier>{

	private CardexAuthenticationSubject subject;
	private Societe societeRDD;
	private Dossier echantillonDossier;

	/**
	 * @param subject
	 * @param dossierOrigine
	 * @param dossierEchantillon
	 */
	public RetirerLiaisonEntreEchantillonEtDossierVenteMineurRA0001(CardexAuthenticationSubject subject,
			Societe societeRDD, Dossier echantillonDossier) {
		super();
		this.subject = subject;
		this.societeRDD = societeRDD;
		this.echantillonDossier = echantillonDossier;
	}

	public void executer(Dossier t) throws DAOException {
    	
    	// retirer la liaison entre les dossiers 
        if(echantillonDossier.getSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)
        && echantillonDossier.getCategorie() == GlobalConstants.CategorieClientMystere.ECHANTILLON
		&& societeRDD.isIndicateurRdd()){
    		Map<Dossier,Dossier> dossiers = FabriqueCardexDAO.getInstance().getLiaisonDAO().obtenirEntiteEntreDeuxEntite(subject, echantillonDossier, societeRDD, Dossier.class);
    		
    		for(Dossier venteMineurDossier:dossiers.values()){
				
    			for(LiaisonEntiteVO liaisonEntiteVO:venteMineurDossier.getLiaisonEntites()){
					FabriqueCardexDAO.getInstance().getLiaisonDAO().supprimerLiaison(subject, liaisonEntiteVO);
    			}
	
	    		//effacer ne numéro de vague
    			try {
					venteMineurDossier = FabriqueFacade.getDossierSessionFacade().find(subject, venteMineurDossier);
					venteMineurDossier.setReference1("");
		    		FabriqueCardexDAO.getInstance().getDossierDAO().update(subject, venteMineurDossier);
				} catch (BusinessResourceException e) {
					throw new DAOException(e);
				} catch (BusinessRuleException e) {
					throw new DAOException(e);
				}
    		}
		}   
	}

}
