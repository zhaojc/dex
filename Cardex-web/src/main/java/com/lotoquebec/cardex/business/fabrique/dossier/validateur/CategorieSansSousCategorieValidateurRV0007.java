package com.lotoquebec.cardex.business.fabrique.dossier.validateur;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.vo.SousCategorieVO;
import com.lotoquebec.cardex.business.vo.SousCategoriesVO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.validateur.Validateur;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.CategorieCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.TypeCleMultiListeCache;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * RV0007	Sauvegarde dossier, valider que la catégorie enregistrer du dossier 
 * ne ce trouve pas déjà dans une sous catégorie
 * 
 * @author levassc
 *
 */
public class CategorieSansSousCategorieValidateurRV0007 implements Validateur<Dossier> {

	private CardexAuthenticationSubject subject;
	private long categorie = 0;
	private long type = 0;
	private long nature = 0;
	
	public CategorieSansSousCategorieValidateurRV0007(CardexAuthenticationSubject subject) {
		super();
		this.subject = subject;
	}

	public boolean isValide(Dossier dossier) throws BusinessException, DAOException {
		categorie = dossier.getCategorie();
		type = dossier.getType();
		nature = dossier.getNature();
		
		SousCategoriesVO sousCategoriesVO = FabriqueCardexDAO.getInstance().getSousCategorieDAO().findLiensSousCategorie(subject, dossier.getCle(), dossier.getSite());
		
		for (SousCategorieVO sousCategorieVO:sousCategoriesVO.getSousCategories()){
			
			if (sousCategorieVO.getCle() == categorie)
				return false;
		}
        return true;
	}
   
   public BusinessMessage getMessage() throws BusinessResourceException {
		ListeCache cache = ListeCache.getInstance();
		String categorieDescription = cache.obtenirLabel(subject, categorie, new CategorieCleMultiListeCache(subject, type));
		String typeDescription = cache.obtenirLabel(subject, type, new TypeCleMultiListeCache(subject, nature));
		return new BusinessMessage("MA0001", typeDescription, categorieDescription); //MA0001=La catégorie du dossier que vous venez de modifier, soit « {0} - {1} », existait déjà en sous-catégorie et a ainsi été supprimée de cet onglet (sous-catégories). Veuillez vous assurer que la catégorie et la ou les sous-catégories choisies reflètent bien le contenu de votre narration et qu'elles respectent l'échelle de gravité (voir Guide Cardex). 
  }

}
