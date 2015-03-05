/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/
package com.lotoquebec.cardex.business.facade;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;

import com.lotoquebec.cardex.business.CriteresRechercheNarration;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.vo.NarrationVO;
import com.lotoquebec.cardex.businessRules.BusinessRulesValidator;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;


/**
 * Le NarrationBusinessFacade offre les
 * services d'affaires concernant l'objet
 * dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.3 $, $Date: 2002/04/11 15:06:01 $
 */
public class NarrationSessionFacade {

    /**
     * Recherche d'une narration
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La narration recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Narration find(CardexAuthenticationSubject subject,
                        Narration criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getNarrationDAO().find(subject, criteria);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche de narrations
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La narration recherché
     * @throws BusinessRuleException
     * @throws BusinessException
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     */
    public Collection selectNarration(CardexAuthenticationSubject subject,
                        CriteresRechercheNarration criteria) throws BusinessRuleException, BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateurRechercheDossier(subject, criteria);
            checkNombreEnregistrementRechercheNarration(subject, criteria);
            List<NarrationVO> narrationList = FabriqueCardexDAO.getInstance().getNarrationDAO().selectNarration(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossierConsulterNarration(subject, narrationList);            
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche de l'audit des changements d'un sujet
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les sujets recherchés
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List audit(CardexAuthenticationSubject subject,
                                           Narration criteria) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getNarrationDAO().audit(subject, criteria);
        } catch (DAOException dae) {
        	throw new BusinessResourceException(dae);
        }
    }

    /*
     * Recherche de narrations approuvées
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La narration recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection selectApprobation(CardexAuthenticationSubject subject,
                        CriteresRechercheNarration criteria) throws BusinessRuleException, BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateurRechercheDossier(subject, criteria);
            checkNombreEnregistrementRechercheApprobation(subject, criteria);
            List<NarrationVO> narrationList = FabriqueCardexDAO.getInstance().getNarrationDAO().selectApprobation(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossierConsulterNarration(subject, narrationList);            
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }
            
  	private void checkNombreEnregistrementRechercheNarration(CardexAuthenticationSubject subject, CriteresRechercheNarration criteria)  throws BusinessException{
  		try {
  			NumberFormat numberFormat = NumberFormat.getInstance();
			Integer nbEnregistrement = FabriqueCardexDAO.getInstance().getNarrationDAO().nombreDeNarrationRechercheNarration(subject, criteria);
			
			if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_NARRATION){
				BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_NARRATION );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
				throw businessRuleExceptionHandle.getBusinessException();
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new BusinessException("Problème avec checkNombreEnregistrementRechercheNarration");
		}
	}
  	
  	private void checkNombreEnregistrementRechercheApprobation(CardexAuthenticationSubject subject, CriteresRechercheNarration criteria)  throws BusinessException{
  	  	
  		try {
  			NumberFormat numberFormat = NumberFormat.getInstance();
			Integer nbEnregistrement = FabriqueCardexDAO.getInstance().getNarrationDAO().nombreDeNarrationRechercheApprobation(subject, criteria);
			
			if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_APPROBATION){
				BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_APPROBATION );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
				throw businessRuleExceptionHandle.getBusinessException();
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new BusinessException("Problème avec checkNombreEnregistrementRechercheApprobation");
		}
	}
 
  	public void insertionNarrationTemporaire(CardexAuthenticationSubject subject, Narration narration) throws BusinessRuleException, BusinessException {
		try {
			FabriqueCardexDAO.getInstance().getNarrationDAO().insertionNarrationTemporaire(subject, narration);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}
  	
    public void sauvegarderNarrationTemporaire(CardexAuthenticationSubject subject, Narration narration) throws BusinessRuleException, BusinessException {
		try {
			FabriqueCardexDAO.getInstance().getNarrationDAO().sauvegarderNarrationTemporaire(subject, narration);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}
    
    public Narration obtenirNarrationTemporaire(CardexAuthenticationSubject subject, Narration narration) throws BusinessRuleException, BusinessException {
		try {
			return FabriqueCardexDAO.getInstance().getNarrationDAO().obtenirNarrationTemporaire(subject, narration);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}    
}

