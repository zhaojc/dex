package com.lotoquebec.cardex.business.fabrique.dossier.regle;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Urgence;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.regle.RegleAffaire;
import com.lotoquebec.cardexCommun.business.vo.LiaisonEntiteVO;
import com.lotoquebec.cardexCommun.exception.DAOException;

/**
 * RA0023 : Service d'urgence : Le retrait d'une société dans l'onglet 
 * "Service d'urgence" retire également cette société qui est liée au dossier.  
 * Le cas réciproque est aussi vrai, le retrait d'une société liée au dossier 
 * retire la société dans l'onglet "Service d'urgence".
 * 
 * @author levassc
 *
 */
public class RetirerSocieteUrgenceRA0023 implements RegleAffaire<Dossier>{

	private CardexAuthenticationSubject subject;
	private Urgence urgence;
	
	/**
	 * @param subject
	 * @param dossierOrigine
	 * @param dossierEchantillon
	 */
	public RetirerSocieteUrgenceRA0023(CardexAuthenticationSubject subject,
			Urgence urgence) {
		super();
		this.subject = subject;
		this.urgence = urgence;
	}

	public void executer(Dossier dossier) throws DAOException {
		Societe societe = new SocieteVO();
        societe.setCle(urgence.getLienSociete());
        societe.setSite(urgence.getLienSiteSociete());
        LiaisonEntiteVO liaisonEntiteVO = FabriqueCardexDAO.getInstance().getLiaisonDAO().obtenirLiaison(subject, dossier, societe);
		FabriqueCardexDAO.getInstance().getLiaisonDAO().supprimerLiaison(subject, liaisonEntiteVO);
	}

}
