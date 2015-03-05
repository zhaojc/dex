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
 * RV0007	Sauvegarde dossier, valider que la cat�gorie enregistrer du dossier 
 * ne ce trouve pas d�j� dans une sous cat�gorie
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
		return new BusinessMessage("MA0001", typeDescription, categorieDescription); //MA0001=La cat�gorie du dossier que vous venez de modifier, soit � {0} - {1} �, existait d�j� en sous-cat�gorie et a ainsi �t� supprim�e de cet onglet (sous-cat�gories). Veuillez vous assurer que la cat�gorie et la ou les sous-cat�gories choisies refl�tent bien le contenu de votre narration et qu'elles respectent l'�chelle de gravit� (voir Guide Cardex). 
  }

}
