/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.rule;

import java.util.Iterator;
import java.util.logging.Logger;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Inscription;
import com.lotoquebec.cardex.business.vo.SousCategorieVO;
import com.lotoquebec.cardex.business.vo.SousCategoriesVO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.CategorieTypeCle;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Cette classe valide l'ensemble des r�gles d'affaire applicable
 * aux SousCategories.
 */
public class SousCategoriesBusinessRuleSet implements BusinessRuleSet {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));
	
    //private DAOFactory factory;
	//private DossierDAO dossierDAO;
    /**
     * Construit une instance de InscriptionBusinessRuleSet
     * @throws DAOException
     */
    public SousCategoriesBusinessRuleSet() {
        //this.factory = DAOFactory.getInstance();
        //this.dossierDAO = factory.getDossierDAO();
    }


    /**
     * Valide les r�gles d'affaires applicable � Sous Categories.
     *
     * @param businessObject Le dossier
     *
     * @throws BusinessRuleException si les r�gles d'affaire
     * d'un objet dossier ne sont pas respect�es.
     * @throws BusinessException
     * @throws 
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.Dossier
     */
    public void checkRules(CardexAuthenticationSubject subject, Object businessObject)
            throws BusinessRuleException, BusinessException {
        log.fine("checkRules()");

        if (businessObject instanceof SousCategoriesVO) {
        	SousCategoriesVO sousCategories = (SousCategoriesVO) businessObject;

        	checkCategorieReclamation(subject, sousCategories);
    		check4SousCategorieMax(sousCategories);
				
        } else {
            throw new IllegalArgumentException("L'objet d'affaire doit �tre une instance de '"
                                               + SousCategoriesVO.class.getName()
                                               + "'");
        }
    }

    /**
     * Un maximum de 4 sous cat�gories peuvent �tre associ�es � un dossier.
     * @param sousCategories
     * @throws BusinessException
     */
    private void check4SousCategorieMax(SousCategoriesVO sousCategories) throws BusinessException{
		
    	if (sousCategories.getSousCategories().size() > 4){
	    	BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
	    	businessRuleExceptionHandle.add("cardex_max4_sousCategories" );
	    	throw businessRuleExceptionHandle.getBusinessException();
    	}
	}

    /**
     * Une sous-cat�gorie de r�clamations (Dossier POL, type Consommateur ou Personne au registre
     * utilis�e par les enqu�teurs Loterie doit�tre coh�rente avec le dossier. 
     * La fonction suivante v�rifie si une sous-cat�gorie de type Consommateur a �t� associ�e
     * � une cat�gorie de dossier de type Personne au registre et vice-versa.     * 
     *  
     * @param sousCategories
     * @throws BusinessException
     */
    private void checkCategorieReclamation(CardexAuthenticationSubject subject, SousCategoriesVO sousCategories) throws BusinessException{
		
    	ListeCache listeCache = ListeCache.getInstance();
    	String type = "";
    	
    	for(SousCategorieVO sousCategorieVO:sousCategories.getSousCategories()){
			type = listeCache.obtenirLabel(subject, sousCategorieVO.getCle(), new CategorieTypeCle(subject));
			
			if(type.equals(GlobalConstants.Type.RECLAMATION_CONSOMMATEUR) || type.equals(GlobalConstants.Type.RECLAMATION_PAR)){
				
				if(!type.equals(String.valueOf(sousCategories.getType()))){
			    	BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
			    	businessRuleExceptionHandle.add("cardex_type_PAR" );
			    	throw businessRuleExceptionHandle.getBusinessException();
				}
    		}
    	}

	}

}
