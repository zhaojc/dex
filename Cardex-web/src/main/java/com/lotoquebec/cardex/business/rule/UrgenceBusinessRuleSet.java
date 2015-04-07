/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.vo.SousCategoriesVO;
import com.lotoquebec.cardex.business.vo.UrgenceVO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;

/**
 * Cette classe valide l'ensemble des r�gles d'affaire applicable
 * aux formulaires des services d'urgence.
 */
public class UrgenceBusinessRuleSet implements BusinessRuleSet {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));
	
    //private DAOFactory factory;
	//private DossierDAO dossierDAO;
    /**
     * Construit une instance de InscriptionBusinessRuleSet
     * @throws DAOException
     */
    public UrgenceBusinessRuleSet() {
        //this.factory = DAOFactory.getInstance();
        //this.dossierDAO = factory.getDossierDAO();
    }


    /**
     * Valide les r�gles d'affaires applicable � Urgence.
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
        log.debug("checkRules()");

        if (businessObject instanceof UrgenceVO) {
        	UrgenceVO urgenceVO = (UrgenceVO) businessObject;

        	checkSocietePresente(subject, urgenceVO);
        	checkMotifStatutPrsent(subject, urgenceVO);
				
        } else {
            throw new IllegalArgumentException("L'objet d'affaire doit �tre une instance de '"
                                               + SousCategoriesVO.class.getName()
                                               + "'");
        }
    }

    /**
     * Une soci�t� doit �tre pr�sente pour enregistrer le formulaire, sauf dans le cas 
     * d'un refus du client avant appel dans le formulaire ambulance.  
  	 *
     * @param sousCategories
     * @throws BusinessException
     */
    private void checkSocietePresente(CardexAuthenticationSubject subject, UrgenceVO urgence) throws BusinessException{
		
    	BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
    	if(urgence.getLienSociete() == 0 && urgence.getStatut() != GlobalConstants.Statut.REFUS_AVANT_APPEL){
	    	businessRuleExceptionHandle.add("cardex_required_societe" );
	    	throw businessRuleExceptionHandle.getBusinessException();
    	}
	}

    /**
     * Un service Ambulance doit avoir un motif et un statut 
  	 *
     * @param sousCategories
     * @throws BusinessException
     */
    private void checkMotifStatutPrsent(CardexAuthenticationSubject subject, UrgenceVO urgence) throws BusinessException{
		
    	BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
    	if(urgence.getClasse() == GlobalConstants.Classes.AMBULANCE){
	    	if(urgence.getMotif() == 0 || urgence.getStatut() == 0){
		    	businessRuleExceptionHandle.add("cardex_required_statut_motif" );
		    	throw businessRuleExceptionHandle.getBusinessException();
	    	}
    	}
	}

}
