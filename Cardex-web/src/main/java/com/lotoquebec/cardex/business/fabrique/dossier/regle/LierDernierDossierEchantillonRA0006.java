package com.lotoquebec.cardex.business.fabrique.dossier.regle;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.regle.RegleAffaire;
import com.lotoquebec.cardexCommun.exception.DAOException;

/**
 * RA0006 : Pour un dossier de vente aux mineurs.  L'ajout d'un lien avec une 
 * société RDD crée un lien entre le dossier de vente aux mineurs et le dossier 
 * d'échantillon rattaché à la société RDD.  Copier le numéro de vague du dossier 
 * d'échantillon
 * 
 * @author levassc
 *
 */
public class LierDernierDossierEchantillonRA0006 implements RegleAffaire<Dossier>{

	private CardexAuthenticationSubject subject;
	private Dossier venteMineurDossier;
	private Societe societe;

	/**
	 * @param subject
	 * @param dossierOrigine
	 * @param dossierEchantillon
	 */
	public LierDernierDossierEchantillonRA0006(CardexAuthenticationSubject subject,
			Dossier venteMineurDossier, Societe societe) {
		super();
		this.subject = subject;
		this.venteMineurDossier = venteMineurDossier;
		this.societe = societe;
	}

	public void executer(Dossier t) throws DAOException {
        
		if(venteMineurDossier.getSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)
        && venteMineurDossier.getType() == GlobalConstants.Type.VENTE_MINEUR
        && venteMineurDossier.getCategorie() != GlobalConstants.CategorieClientMystere.ECHANTILLON
        && societe.isIndicateurRdd()){
			Dossier dossierEchantillon = FabriqueCardexDAO.getInstance().getDossierDAO().dernierDossierEchantillionClientMystere(subject, societe);
			
			if (dossierEchantillon != null){
				venteMineurDossier.setReference1(dossierEchantillon.getNumeroDossier());
	    		FabriqueCardexDAO.getInstance().getDossierDAO().update(subject, venteMineurDossier);
	    		// Il faut lier le dossier d'infraction avec le dossier d'échantillion
	    		dossierEchantillon.setRole( GlobalConstants.Role.ECHANTILLON );
	    		FabriqueCardexDAO.getInstance().getDossierDAO().addLienDossier(subject, venteMineurDossier, dossierEchantillon);
			}
        }
	}

}
