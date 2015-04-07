package com.lotoquebec.cardex.business.delegate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        LoggerFactory.getLogger((this.getClass()));

    public UrgenceBusinessDelegate() {
		this.urgenceSessionFacade = new UrgenceSessionFacade();
	}
    
	/**
	 * Lors de l'ajout ou la modification d'un urgence il faut ajouter/supprimer (g�rer)
	 * les liaisons entres le dossier et la soci�t�.
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
     * Recherche des services d'urgence cr��s dans les derni�res 48 heures
     *
     * @param subject L'utilisateur qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les services d'urgence recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param criteria Les crit�res de recherche
     *
     * @return Les services d'urgence recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
