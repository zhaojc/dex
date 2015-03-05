/*
 * Created on 23-Jan-2008
 */
package com.lotoquebec.cardex.business.facade;

import java.sql.SQLException;

import com.lotoquebec.cardex.business.exception.SocieteBusinessRuleException;
import com.lotoquebec.cardex.business.vo.ResultatRegroupementVO;
import com.lotoquebec.cardex.business.vo.rapport.regroupement.RegroupementRapportVO;
import com.lotoquebec.cardex.businessRules.BusinessRulesValidator;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.Intervenant;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * @author levassc
 */
public class RegroupementSessionFacade {
	
    public void validerRapport(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessRuleException, BusinessException{
    	GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteresRechercheRegroupement, GlobalConstants.ActionSecurite.RECHERCHE);
    	BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteresRechercheRegroupement);
    }
	
    public ResultatRegroupementVO produireRapportGlobal(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessRuleException, BusinessException{
    	try {
    		BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteresRechercheRegroupement);
    		GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteresRechercheRegroupement, GlobalConstants.ActionSecurite.RECHERCHE);
    		assignerNumeroMatricule( criteresRechercheRegroupement );
    		ResultatRegroupementVO regroupement = FabriqueCardexDAO.getInstance().getRegroupementDAO().produireRapportGlobal(subject, criteresRechercheRegroupement);
			GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, regroupement.getListRegroupement());
			return regroupement;
		} catch (DAOException e) {
			handleDAOException(e);
		}
		return null;		
    }
    public ResultatRegroupementVO produireRapportRegroupementEndroit(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessRuleException, BusinessException{
    	try {
    		BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteresRechercheRegroupement);
    		GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteresRechercheRegroupement, GlobalConstants.ActionSecurite.RECHERCHE);
    		assignerNumeroMatricule( criteresRechercheRegroupement );
    		ResultatRegroupementVO regroupement = FabriqueCardexDAO.getInstance().getRegroupementDAO().produireRapportRegroupementEndroit(subject, criteresRechercheRegroupement);
			GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, regroupement.getListRegroupement());
			return regroupement;	    	
		} catch (DAOException e) {
			handleDAOException(e);
		}
		return null;    	
    }
    public ResultatRegroupementVO produireRapportSecteur(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessRuleException, BusinessException{
    	try {
    		BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteresRechercheRegroupement);
    		GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteresRechercheRegroupement, GlobalConstants.ActionSecurite.RECHERCHE);
    		assignerNumeroMatricule( criteresRechercheRegroupement );
    		ResultatRegroupementVO regroupement = FabriqueCardexDAO.getInstance().getRegroupementDAO().produireRapportSecteur(subject, criteresRechercheRegroupement);
			GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, regroupement.getListRegroupement());
			return regroupement;	    	
		} catch (DAOException e) {
			handleDAOException(e);
		}
		return null;    	
    }
    public ResultatRegroupementVO produireRapportIntervenant(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessRuleException, BusinessException{
    	try {
    		BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteresRechercheRegroupement);
    		GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteresRechercheRegroupement, GlobalConstants.ActionSecurite.RECHERCHE);
    		assignerNumeroMatricule( criteresRechercheRegroupement );
    		ResultatRegroupementVO regroupement = FabriqueCardexDAO.getInstance().getRegroupementDAO().produireRapportIntervenant(subject, criteresRechercheRegroupement);
			GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, regroupement.getListRegroupement());
			return regroupement;	    	
		} catch (DAOException e) {
			handleDAOException(e);
		}
		return null;
    }
    public ResultatRegroupementVO produireRapportIntervenantCategorie(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessRuleException, BusinessException{
    	try {
    		BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteresRechercheRegroupement);
    		GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteresRechercheRegroupement, GlobalConstants.ActionSecurite.RECHERCHE);
    		assignerNumeroMatricule( criteresRechercheRegroupement );
    		ResultatRegroupementVO regroupement = FabriqueCardexDAO.getInstance().getRegroupementDAO().produireRapportIntervenantCategorie(subject, criteresRechercheRegroupement);
			GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, regroupement.getListRegroupement());
			return regroupement;	    	
		} catch (DAOException e) {
			handleDAOException(e);
		}
		return null;
    }
    public ResultatRegroupementVO produireRapportIntervenantEndroit(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessRuleException, BusinessException{
    	try {
    		BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteresRechercheRegroupement);
    		GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteresRechercheRegroupement, GlobalConstants.ActionSecurite.RECHERCHE);
    		assignerNumeroMatricule( criteresRechercheRegroupement );
    		ResultatRegroupementVO regroupement = FabriqueCardexDAO.getInstance().getRegroupementDAO().produireRapportIntervenantEndroit(subject, criteresRechercheRegroupement);
			GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, regroupement.getListRegroupement());
			return regroupement;	    	
		} catch (DAOException e) {
			handleDAOException(e);
		}
		return null;
    }
	
    public ResultatRegroupementVO produireRapportTendanceMois(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessRuleException, BusinessException{
    	try {
    		BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteresRechercheRegroupement);
    		GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteresRechercheRegroupement, GlobalConstants.ActionSecurite.RECHERCHE);
    		assignerNumeroMatricule( criteresRechercheRegroupement );
    		ResultatRegroupementVO regroupement = FabriqueCardexDAO.getInstance().getRegroupementDAO().produireRapportTendanceMois(subject, criteresRechercheRegroupement);
			GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, regroupement.getListRegroupement());
			return regroupement;	    	
		} catch (DAOException e) {
			handleDAOException(e);
		}
		return null;		
    }

    public ResultatRegroupementVO produireRapportMatriceRegroupement(CardexAuthenticationSubject subject, RegroupementRapportVO criteresRechercheRegroupement) throws BusinessRuleException, BusinessException{
    	try {
    		BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteresRechercheRegroupement);
			return FabriqueCardexDAO.getInstance().getRegroupementDAO().produireRapportMatriceRegroupement(subject, criteresRechercheRegroupement);
		} catch (DAOException e) {
			handleDAOException(e);
		}
		return null;		
    }

    private void assignerNumeroMatricule(RegroupementRapportVO criteresRechercheRegroupement) throws DAOException{
    	
    	if (StringUtils.isNotEmpty( criteresRechercheRegroupement.getIntervenant() )){
    		Intervenant intervenant = FabriqueCardexDAO.getInstance().getIntervenantDAO().findIntervenant( criteresRechercheRegroupement.getIntervenant() );
    		
    		if (intervenant != null)
    			criteresRechercheRegroupement.setMatricule( intervenant.getNumero() );
    	}
    }
    
    private void handleDAOException(DAOException daoe)
			throws BusinessResourceException, BusinessRuleException {

		Exception sqlex = daoe.getAncestor();
		if (sqlex instanceof SQLException) {
			SQLException sqlException = (SQLException) sqlex;
			//int errorCode = sqlException.getErrorCode();
			BusinessRuleException bre = new BusinessRuleException();

			//Le code d'erreur est extrait du message contenu dans l'exception
			//SQL puisque le driver JDBC Oracle ne donne pas toutes les exception
			//ORACLE qui devrait être chaîné.  Normalement on devrait extraire le
			//code d'erreur à partir de la méthode SQLException.getErrorCode().
			if (sqlException.getMessage().indexOf("ORA-20001") != -1) {
				bre.setBusinessRule(SocieteBusinessRuleException.SOCIETE_RELIEE_A_ELLE_MEME);
				throw bre;
			}
			if (sqlException.getMessage().indexOf("ORA-20002") != -1) {
				bre.setBusinessRule(SocieteBusinessRuleException.SOCIETE_RELIEE_PLUS_UNE_FOIS);
				throw bre;
			}
		}
		throw new BusinessResourceException(daoe);
	}    
}
