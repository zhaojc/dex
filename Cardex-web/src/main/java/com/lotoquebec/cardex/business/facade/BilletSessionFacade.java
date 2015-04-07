package com.lotoquebec.cardex.business.facade;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.vo.BilletVO;
import com.lotoquebec.cardex.business.vo.CriteresRechercheBilletVO;
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
 * Le BilletSessionFacade offre les
 * services d'affaires, g�re les int�ractions
 * et les validations de r�gles d'affaires applicable
 * aux billets.
 * @author levassc
 *
 */
public class BilletSessionFacade {
	
	//private final Logger log = LoggerFactory.getLogger((this.getClass()));

	public void ajouter(CardexAuthenticationSubject subject, 
	        BilletVO billetVO) throws BusinessRuleException, BusinessResourceException, BusinessException {
		try {
		    BusinessRulesValidator.getInstance().checkBusinessRules(subject, billetVO);
			GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, billetVO, GlobalConstants.ActionSecurite.AJOUT);
			FabriqueCardexDAO.getInstance().getBilletDAO().ajouter(subject, billetVO);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}
	
	public void supprimer(CardexAuthenticationSubject subject, BilletVO billetVO) throws BusinessRuleException, BusinessResourceException {
		try {
			trouverBillet(subject, billetVO);
			FabriqueCardexDAO.getInstance().getBilletDAO().supprimer(subject, billetVO);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}	
	
	public void modifier(CardexAuthenticationSubject subject, BilletVO billetVO) throws BusinessRuleException, BusinessResourceException, BusinessException {
		try {
		    BusinessRulesValidator.getInstance().checkBusinessRules(subject, billetVO);
			BilletVO billetVOSource = trouverBillet(subject, billetVO);
			GestionnaireSecuriteCardex.validerSecuriteModificationSansSecuritePredicate(subject, billetVOSource, billetVO);
			
			if (GestionnaireSecuriteCardex.isModificiation(billetVOSource, billetVO))
				FabriqueCardexDAO.getInstance().getBilletDAO().modifier(subject, billetVO);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}

	public List<BilletVO> recherche(CardexAuthenticationSubject subject, CriteresRechercheBilletVO criteresRechercheBilletVO) throws BusinessRuleException, BusinessException {
		try {
			checkNombreEnregistrementRechercheBillet(subject, criteresRechercheBilletVO);
			List<BilletVO> listBillet = FabriqueCardexDAO.getInstance().getBilletDAO().recherche(subject, criteresRechercheBilletVO);
			GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossier(subject, listBillet);
			return listBillet;
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}
	
	private void checkNombreEnregistrementRechercheBillet(CardexAuthenticationSubject subject, CriteresRechercheBilletVO criteria)  throws BusinessException{
		try {
			NumberFormat numberFormat = NumberFormat.getInstance();
			Integer nbEnregistrement = FabriqueCardexDAO.getInstance().getBilletDAO().nombreDeBilletRecherche(subject, criteria);

			if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_BILLET){
				BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();

				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_BILLET );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
				throw businessRuleExceptionHandle.getBusinessException();
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new BusinessException("Probl�me avec checkNombreEnregistrementRechercheBillet");
		}
	}
	
    public List<BilletVO> trouverLiensBillet(CardexAuthenticationSubject subject, Dossier dossier) throws BusinessRuleException, BusinessResourceException {
		try {

        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_BILLETS_ONGLET))
        		return FabriqueCardexDAO.getInstance().getBilletDAO().trouverLiensBillet(subject, dossier);
        	else
        		return new ArrayList<BilletVO>();
			
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	} 
    
    public BilletVO trouverBillet(CardexAuthenticationSubject subject, BilletVO criteria) throws BusinessRuleException, BusinessResourceException {
		try {
			BilletVO billetVO = FabriqueCardexDAO.getInstance().getBilletDAO().trouverBillet(subject, criteria);
			GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulter(subject, billetVO);
			return billetVO;
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}
    
}
