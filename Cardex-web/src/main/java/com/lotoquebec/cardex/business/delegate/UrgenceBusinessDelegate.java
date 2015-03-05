package com.lotoquebec.cardex.business.delegate;

import java.util.List;
import java.util.logging.Logger;


import com.lotoquebec.cardex.business.CriteresRechercheUrgence;
import com.lotoquebec.cardex.business.Urgence;
import com.lotoquebec.cardex.business.facade.FabriqueFacade;
import com.lotoquebec.cardex.business.facade.UrgenceSessionFacade;
import com.lotoquebec.cardex.business.vo.UrgenceVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * Le UrgenceBusinessDelegate offre les
 * services d'affaires concernant l'objet
 * billet.
 * @author levassc
 *
 */


public class UrgenceBusinessDelegate extends BusinessDelegate {

    UrgenceSessionFacade urgenceSessionFacade;
    
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    public UrgenceBusinessDelegate() {
		this.urgenceSessionFacade = new UrgenceSessionFacade();
	}
    
	/**
	 * Lors de l'ajout ou la modification d'un urgence il faut ajouter/supprimer (gérer)
	 * les liaisons entres le dossier et la société.
	 * Pour ce urgence
	 * @param subject
	 * @param urgenceVO
	 * @throws BusinessException
	 * @throws BusinessResourceException
	 */
    public void ajouter(CardexAuthenticationSubject subject, UrgenceVO urgenceVO) throws BusinessException, BusinessResourceException {
		try {
			FabriqueFacade.getUrgenceSessionFacade().ajouter(subject, urgenceVO);
		} catch (BusinessRuleException e) {
			e.printStackTrace();
			throw handleBusinessRuleException(e);
		}
	}
    
   
    public void supprimer(CardexAuthenticationSubject subject, UrgenceVO urgenceVO) throws BusinessException, BusinessResourceException {
    	try {
    		FabriqueFacade.getUrgenceSessionFacade().supprimer(subject, urgenceVO);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}
    
    public void modifier(CardexAuthenticationSubject subject, UrgenceVO urgenceVO) throws BusinessException, BusinessResourceException {
		try {
			FabriqueFacade.getUrgenceSessionFacade().modifier(subject, urgenceVO);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}

    public Urgence find(CardexAuthenticationSubject subject, Urgence urgenceVO) throws BusinessException, BusinessResourceException {
		try {
			Urgence urgenceVOSortie =  FabriqueFacade.getUrgenceSessionFacade().find(subject, urgenceVO);
			return urgenceVOSortie;
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}

    /**
     * Recherche des services d'urgence créés dans les dernières 48 heures
     *
     * @param subject L'utilisateur qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les services d'urgence recherchés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Urgence> selectDefault(CardexAuthenticationSubject subject,
                                           CriteresRechercheUrgence criteria) throws BusinessException,
                                           BusinessResourceException {
        try {
            return urgenceSessionFacade.selectDefault(subject, criteria);
        }catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }
    /**
     * Recherche des services d'urgence
     *
     * @param subject L'utilisateur qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les services d'urgence recherchés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Urgence> select(CardexAuthenticationSubject subject,
                                    CriteresRechercheUrgence criteria) throws BusinessException,
                                    BusinessResourceException {
        try {
            return urgenceSessionFacade.select(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }    
}
