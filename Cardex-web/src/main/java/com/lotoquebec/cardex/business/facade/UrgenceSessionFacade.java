package com.lotoquebec.cardex.business.facade;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.lotoquebec.cardex.business.CriteresRechercheUrgence;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Urgence;
import com.lotoquebec.cardex.business.vo.UrgenceVO;
import com.lotoquebec.cardex.businessRules.BusinessRulesValidator;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.integration.dao.UrgenceDAO;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;

/**
 * Le UrgenceSessionFacade offre les
 * services d'affaires, g�re les int�ractions
 * et les validations de r�gles d'affaires applicable
 * aux urgences.
 * @author levassc
 *
 */
public class UrgenceSessionFacade {
	
	//private final Logger log = LoggerFactory.getLogger((this.getClass()));

	public void ajouter(CardexAuthenticationSubject subject, UrgenceVO urgenceVO) throws BusinessRuleException, BusinessResourceException {
		try {
			GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, urgenceVO, GlobalConstants.ActionSecurite.AJOUT);
			FabriqueCardexDAO.getInstance().getUrgenceDAO().ajouter(subject, urgenceVO);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}
	
	public void supprimer(CardexAuthenticationSubject subject, Urgence urgenceVO) throws BusinessRuleException, BusinessResourceException {
		try {
			find(subject, urgenceVO);
			FabriqueCardexDAO.getInstance().getUrgenceDAO().supprimer(subject, urgenceVO);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}	
	
	public void modifier(CardexAuthenticationSubject subject, Urgence urgenceVO) throws BusinessRuleException, BusinessResourceException {
		try {
			Urgence urgenceVOSource = find(subject, urgenceVO);
			GestionnaireSecuriteCardex.validerSecuriteModificationSansSecuritePredicate(subject, urgenceVOSource, urgenceVO);
			
			if (GestionnaireSecuriteCardex.isModificiation(urgenceVOSource, urgenceVO))
				FabriqueCardexDAO.getInstance().getUrgenceDAO().modifier(subject, urgenceVO);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}

    public List<UrgenceVO> trouverLiensUrgence(CardexAuthenticationSubject subject, Dossier dossier) throws BusinessRuleException, BusinessResourceException {
		try {

        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_BILLETS_ONGLET))
        		return FabriqueCardexDAO.getInstance().getUrgenceDAO().findLiensUrgence(subject, dossier); 
        	else
        		return new ArrayList<UrgenceVO>();
			
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	} 
    
    public Urgence find(CardexAuthenticationSubject subject, Urgence criteria) throws BusinessRuleException, BusinessResourceException {
		try {
			Urgence urgenceVO = FabriqueCardexDAO.getInstance().getUrgenceDAO().find(subject, criteria);
			GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulter(subject, urgenceVO);
			return urgenceVO;
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}

    private void checkNombreEnregistrementRechercheUrgence(CardexAuthenticationSubject subject, CriteresRechercheUrgence criteria)  throws BusinessException{
        try {
            NumberFormat numberFormat = NumberFormat.getInstance();
            Integer nbEnregistrement = UrgenceDAO.nombreDeUrgenceRecherche(subject, criteria);

            if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_URGENCE){
                BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
                String nombreRetourne = numberFormat.format( nbEnregistrement );
                String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_URGENCE );
                businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
                throw businessRuleExceptionHandle.getBusinessException();
            }
        } catch (DAOException e) {
            e.printStackTrace();
            throw new BusinessException("Probl�me avec checkNombreEnregistrementRechercheDossier");
        }
    }    
    
    /**
     * Recherche des services d'urgence
     *
     * @param subject L'utilisateur qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les services d'urgence recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List<Urgence> select(CardexAuthenticationSubject subject,
                                    CriteresRechercheUrgence criteria) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteria, GlobalConstants.ActionSecurite.RECHERCHE);
            checkNombreEnregistrementRechercheUrgence(subject, criteria);
            List<Urgence> urgenceList = UrgenceDAO.select(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, urgenceList);            
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }
    
    /**
     * Recherche des services d'urgence cr��s dans les derni�res 48 heures
     *
     * @param subject L'utilisateur qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les services d'urgence recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List<Urgence> selectDefault(CardexAuthenticationSubject subject,
                                           CriteresRechercheUrgence criteria) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            List<Urgence> urgenceList =  UrgenceDAO.selectDefault(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, urgenceList);            
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }
}
