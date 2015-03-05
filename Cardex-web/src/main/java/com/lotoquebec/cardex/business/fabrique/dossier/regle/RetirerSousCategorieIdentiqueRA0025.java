package com.lotoquebec.cardex.business.fabrique.dossier.regle;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.vo.SousCategorieVO;
import com.lotoquebec.cardex.business.vo.SousCategoriesVO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.regle.RegleAffaire;
import com.lotoquebec.cardexCommun.exception.DAOException;


/**
 * RA0025	Sauvegarde dossier.  Retirer la sous-catégorie identique à la catégorie du dossier.
 * 
 * @author levassc
 *
 */
public class RetirerSousCategorieIdentiqueRA0025 implements RegleAffaire<Dossier>{

	private CardexAuthenticationSubject subject;

	/**
	 * @param subject
	 * @param dossierOrigine
	 * @param dossierEchantillon
	 */
	public RetirerSousCategorieIdentiqueRA0025(CardexAuthenticationSubject subject) {
		super();
		this.subject = subject;
	}

	public void executer(Dossier dossier) throws DAOException {
    	SousCategoriesVO sousCategoriesVO = FabriqueCardexDAO.getInstance().getSousCategorieDAO().findLiensSousCategorie(subject, dossier.getCle(), dossier.getSite());
    	
		for (SousCategorieVO sousCategorieVO:sousCategoriesVO.getSousCategories()){
			
			if (sousCategorieVO.getCle() == dossier.getCategorie()){
				sousCategoriesVO.getSousCategories().remove(sousCategorieVO);
				FabriqueCardexDAO.getInstance().getSousCategorieDAO().supprimer(subject, sousCategorieVO);
				break;
			}
		}
	}

}
