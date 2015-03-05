package com.lotoquebec.cardex.business.fabrique.dossier.validateur;

import java.util.List;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.fabrique.dossier.validateur.util.ClientMystereUtils;
import com.lotoquebec.cardex.business.vo.BilletVO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.validateur.Validateur;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.CategorieCleMultiListeCache;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * RV0006	Client myst�re.  Un dossier de conformit� ne doit pas avoir de billet
 * 
 * @author levassc
 *
 */
public class ConformiteBilletDossierClientMystereValidateurRV0006 implements Validateur<Dossier> {

	private CardexAuthenticationSubject subject;
	private Dossier origineDossier;
	
	public ConformiteBilletDossierClientMystereValidateurRV0006(CardexAuthenticationSubject subject, Dossier dossierOrigine) {
		super();
		this.subject = subject;
		this.origineDossier = dossierOrigine;
	}

	public boolean isValide(Dossier t) throws BusinessException, DAOException {
     
		if(origineDossier.getSite() == Long.valueOf(GlobalConstants.Sites.CLIENTS_MYSTERES)
        && ClientMystereUtils.isConforme(origineDossier.getCategorie())
        && isPossedeBilletDossierClientMystere())
            throw new BusinessException( getMessage() );
        return true;
	}
	
   /**
	* V�rification si un billet est associ� au dossier courant.
	* @throws DAOException 
	* @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
	* @throws BusinessResourceException si une erreur syst�me survient
	*/
   private boolean isPossedeBilletDossierClientMystere() throws DAOException  {
      List<BilletVO> listeBilletVO = FabriqueCardexDAO.getInstance().getBilletDAO().trouverLiensBillet(subject, origineDossier);
      return listeBilletVO.size() > 0;
   }
   
   public BusinessMessage getMessage() throws BusinessResourceException {
		ListeCache cache = ListeCache.getInstance();
		String categorieDescription = cache.obtenirLabel(subject, origineDossier.getCategorie(), new CategorieCleMultiListeCache(subject, GlobalConstants.Type.VENTE_MINEUR));
		return new BusinessMessage("ME0006", categorieDescription); //La cat�gorie du dossier �{0}� est erron�e puisqu'un billet a �t� vendu.
  }

}
