/*
 * Created on 14-Apr-2008
 */
package com.lotoquebec.cardex.business.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.exception.SocieteBusinessRuleException;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.RegroupementRapportVO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.business.Intervenant;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class RegroupementBusinessRuleSet implements BusinessRuleSet {

	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

	/* (non-Javadoc)
	 * @see com.myriap.business.BusinessRuleSet#checkRules(java.lang.Object)
	 */
	public void checkRules(CardexAuthenticationSubject subject, Object businessObject) throws BusinessRuleException, BusinessException {
        log.debug("checkRules()");

        if (businessObject instanceof RegroupementRapportVO) {
        	RegroupementRapportVO criteresRechercheRegroupement = (RegroupementRapportVO) businessObject;
            checkNumeroMatriculeExiste(criteresRechercheRegroupement);
            checkDateDebutSuperieurDateFinRule(criteresRechercheRegroupement);
        } else {
            throw new IllegalArgumentException("L'objet d'affaire doit �tre une instance de '"
                                               + Societe.class.getName()
                                               + "'");
        }
	}

	/**
	 * @param criteresRechercheRegroupement
	 * @throws BusinessException
	 * @throws BusinessRuleException
	 * @throws BusinessException
	 */
	private void checkNumeroMatriculeExiste(RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException {
    	if (StringUtils.isNotEmpty( criteresRechercheRegroupement.getIntervenant() )){
    		Intervenant intervenant;
			try {
				intervenant = FabriqueCardexDAO.getInstance().getIntervenantDAO().findIntervenant( criteresRechercheRegroupement.getIntervenant() );

				if (StringUtils.isEmpty( intervenant.getNumero() )){
	            	BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
	            	businessRuleExceptionHandle.add("cardex_aucun_numero_employe", intervenant.getPrenom() + " " + intervenant.getNom());
	            	throw businessRuleExceptionHandle.getBusinessException();
	    		}
			} catch (DAOException e) {
				e.printStackTrace();
				throw new Error("Probl�me dans 'checkNumeroMatriculeExiste' ");
			}
    	}
	}

    /**
     * Dates de d�but inf�rieures ou �gales aux dates de fin.
     *
     * @param CriteresRechercheRegroupement
     *
     * @throws BusinessRuleException si les dates de d�but sont
     * inf�rieures ou �gales aux dates de fin.
     * @throws BusinessException 
     */
    private void checkDateDebutSuperieurDateFinRule(RegroupementRapportVO criteresRechercheRegroupement)
            throws BusinessException {
        log.debug("checkDateDebutSuperieurDateFinRule()");

        if (criteresRechercheRegroupement.getDateDebutDu() != null && criteresRechercheRegroupement.getDateDebutAu() != null
        && criteresRechercheRegroupement.getDateDebutAu().before(criteresRechercheRegroupement.getDateDebutDu())) {
        	BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
        	businessRuleExceptionHandle.add("cardex_required_validate_date");
        	throw businessRuleExceptionHandle.getBusinessException();
        }
    }	
	
    protected BusinessRuleException createException(int ruleId) {
        SocieteBusinessRuleException exc =
            new SocieteBusinessRuleException();

        exc.setBusinessRule(ruleId);
        

        return exc;
    }
    
}
