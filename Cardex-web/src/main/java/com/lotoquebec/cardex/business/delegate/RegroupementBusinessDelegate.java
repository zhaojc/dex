/*
 * Created on 23-Jan-2008
 */
package com.lotoquebec.cardex.business.delegate;

import java.sql.Connection;

import com.lotoquebec.cardex.business.facade.RegroupementSessionFacade;
import com.lotoquebec.cardex.business.vo.ResultatRegroupementVO;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.RegroupementRapportVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * @author levassc
 */
public class RegroupementBusinessDelegate extends BusinessDelegate {

	private RegroupementSessionFacade regroupementSessionFacade;
	
	/**
	 * @throws BusinessResourceException
	 */
	public RegroupementBusinessDelegate() {
		super();
		regroupementSessionFacade = new RegroupementSessionFacade();
	}

	public void validerRapport(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException, BusinessResourceException {
		try {
			regroupementSessionFacade.validerRapport(subject, criteresRechercheRegroupement);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
    }	
	
    public ResultatRegroupementVO produireRapportGlobal(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException, BusinessResourceException {
		try {
			return regroupementSessionFacade.produireRapportGlobal(subject, criteresRechercheRegroupement);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
    }

	public ResultatRegroupementVO produireRapportRegroupementEndroit(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException, BusinessResourceException {
		try {
			return regroupementSessionFacade.produireRapportRegroupementEndroit(subject, criteresRechercheRegroupement);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
    }
    public ResultatRegroupementVO produireRapportSecteur(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException, BusinessResourceException {
		try {
			return regroupementSessionFacade.produireRapportSecteur(subject, criteresRechercheRegroupement);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
    }
    public ResultatRegroupementVO produireRapportTendanceMois(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException, BusinessResourceException {
		try {
			return regroupementSessionFacade.produireRapportTendanceMois(subject, criteresRechercheRegroupement);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
    }
    public ResultatRegroupementVO produireRapportMatriceRegroupement(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException, BusinessResourceException {
		try {
			return regroupementSessionFacade.produireRapportMatriceRegroupement(subject, criteresRechercheRegroupement);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
    }
    public ResultatRegroupementVO produireRapportIntervenant(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException, BusinessResourceException {
		try {
			return regroupementSessionFacade.produireRapportIntervenant(subject, criteresRechercheRegroupement);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
    }
    public ResultatRegroupementVO produireRapportIntervenantCategorie(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException, BusinessResourceException {
		try {
			return regroupementSessionFacade.produireRapportIntervenantCategorie(subject, criteresRechercheRegroupement);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
    }
    public ResultatRegroupementVO produireRapportIntervenantEndroit(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessException, BusinessResourceException {
		try {
			return regroupementSessionFacade.produireRapportIntervenantEndroit(subject, criteresRechercheRegroupement);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
    }

}
