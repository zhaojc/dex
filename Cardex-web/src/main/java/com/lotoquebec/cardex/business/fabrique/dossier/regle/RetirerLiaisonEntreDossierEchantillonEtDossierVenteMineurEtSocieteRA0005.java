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
 * RA0005:Pour un dossier d'échantillon.  Le retrait d'une société RDD va retirer 
 * tous les liaisons entre le dossier d'échantillon et les dossiers de vente au mineur.  
 * Ces dossiers de vente au mineur perdent la liaison avec la société RDD retiré.
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
 * Bref le retrait du lien 2 entraine la suppression des liens 1 et 3 qui sont lié au dossier d'échantillon
 * 
 * @author levassc
 *
 */
public class RetirerLiaisonEntreDossierEchantillonEtDossierVenteMineurEtSocieteRA0005 implements RegleAffaire<Dossier>{

	private CardexAuthenticationSubject subject;
	private Dossier echantillonDossier;
	private Societe societe;

	/**
	 * @param subject
	 * @param dossierOrigine
	 * @param dossierEchantillon
	 */
	public RetirerLiaisonEntreDossierEchantillonEtDossierVenteMineurEtSocieteRA0005(CardexAuthenticationSubject subject,
			Dossier dossierOrigine, Societe societe) {
		super();
		this.subject = subject;
		this.echantillonDossier = dossierOrigine;
		this.societe = societe;
	}

	public void executer(Dossier t) throws DAOException {
        
        if(echantillonDossier.getSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)
        && echantillonDossier.getCategorie() == GlobalConstants.CategorieClientMystere.ECHANTILLON){
        	societe = FabriqueCardexDAO.getInstance().getSocieteDAO().find(subject, societe);
        	
        	if (societe.isIndicateurRdd()){
	    		Map<Dossier,Dossier> dossierVenteAuxMineurs = FabriqueCardexDAO.getInstance().getLiaisonDAO().obtenirEntiteEntreDeuxEntite(subject, echantillonDossier, societe, Dossier.class);
	
	    		for (Dossier dossierVenteAuMineur:dossierVenteAuxMineurs.values()){
	    			
	    			for(LiaisonEntiteVO liaisonEntiteVO:dossierVenteAuMineur.getLiaisonEntites())
	    				FabriqueCardexDAO.getInstance().getLiaisonDAO().supprimerLiaison(subject, liaisonEntiteVO);
		        		
	        		//effacer ne numéro de vague
					dossierVenteAuMineur = FabriqueCardexDAO.getInstance().getDossierDAO().find(subject, dossierVenteAuMineur);
					dossierVenteAuMineur.setReference1("");
	        		FabriqueCardexDAO.getInstance().getDossierDAO().update(subject, dossierVenteAuMineur);
		        }
        	}
        }    	
	}

}
