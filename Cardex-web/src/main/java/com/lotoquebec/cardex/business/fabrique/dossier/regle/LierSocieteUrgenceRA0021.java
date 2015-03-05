package com.lotoquebec.cardex.business.fabrique.dossier.regle;

import java.util.List;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Urgence;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.business.vo.UrgenceVO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.regle.RegleAffaire;
import com.lotoquebec.cardexCommun.business.vo.LiaisonEntiteVO;
import com.lotoquebec.cardexCommun.exception.DAOException;

/**
 * RA0021 : Dossier : L'ajout d'un service d'urgence ajoute une liaison entre cette sociétés et le dossier de vigilance
 * 
 * @author levassc
 *
 */
public class LierSocieteUrgenceRA0021 implements RegleAffaire<Dossier>{

	private CardexAuthenticationSubject subject;
	private Urgence urgence;
	
	/**
	 * @param subject
	 * @param dossierOrigine
	 * @param dossierEchantillon
	 */
	public LierSocieteUrgenceRA0021(CardexAuthenticationSubject subject,
			Urgence urgence) {
		super();
		this.subject = subject;
		this.urgence = urgence;
	}

	public void executer(Dossier dossier) throws DAOException {
        //S'il y a une société, on ajoute le lien.
        //Il n'y a qu'un cas où la société n'est pas obligatoire (ambulance avec le statut Refus du client avant appel)
		// Si une société est spécifiée, il faut l'ajouter
        if(urgence.getLienSociete() != 0){
            Societe societe = new SocieteVO();
            //Dans le cas d'un lien entre un dossier et une société, le rôle est sans objet.
            societe.setRole(GlobalConstants.Role.SANS_OBJET);
        	
            societe.setCle(urgence.getLienSociete());
            societe.setSite(urgence.getLienSiteSociete());

        	LiaisonEntiteVO existanceLiaisonEntiteVO = obtenirLiaisonExistance(dossier);
        	boolean isLiaisonACreer = existanceLiaisonEntiteVO == null;
        	boolean isLiaisonAChanger = isLiaisonACreer == false && existanceLiaisonEntiteVO.getDestinationEntite().getCle() != societe.getCle();
        	
        	// La liaison n'existe pas ou n'est pas la bonne, il faut la créer
        	if (isLiaisonACreer || isLiaisonAChanger){
        		FabriqueCardexDAO.getInstance().getDossierDAO().addLienSociete(subject, dossier, societe);

	        	if (isLiaisonAChanger)
	        		FabriqueCardexDAO.getInstance().getLiaisonDAO().supprimerLiaison(subject, existanceLiaisonEntiteVO);
        	}
        }
	}

	private LiaisonEntiteVO obtenirLiaisonExistance(Dossier dossier) throws DAOException {
		
		if (urgence.getCle() == 0)
			return null;
		List<UrgenceVO> listeUrgenceVO = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensUrgence(subject, dossier);
		
		for (UrgenceVO liensUrgence:listeUrgenceVO){
			
			if (urgence.getCle() == liensUrgence.getCle()
			&& urgence.getSite() == liensUrgence.getSite()){
				Societe societe = new SocieteVO(liensUrgence.getLienSociete(),liensUrgence.getLienSiteSociete());
				return FabriqueCardexDAO.getInstance().getLiaisonDAO().obtenirLiaison(subject, dossier, societe);
			}
		}
		return null;
	}

}
