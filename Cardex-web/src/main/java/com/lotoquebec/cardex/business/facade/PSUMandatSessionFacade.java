/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/
package com.lotoquebec.cardex.business.facade;

import java.text.NumberFormat;
import java.util.List;

import com.lotoquebec.cardex.business.ConsignationActionPSU;
import com.lotoquebec.cardex.business.CriteresRecherchePSUMandat;
import com.lotoquebec.cardex.business.PSUMandat;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardex.securite.SecuriteAdHoc;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;


/**
 * Le SuiviBusinessFacade offre les
 * services d'affaires concernant l'objet
 * dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/03/25 20:40:33 $
 */
public class PSUMandatSessionFacade {

    /**
     * Recherche d'un mandats PSU
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return Le mandat recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public PSUMandat find(CardexAuthenticationSubject subject,
		   PSUMandat criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            PSUMandat mandat = FabriqueCardexDAO.getInstance().getPSUMandatDAO().find(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulter(subject, mandat);
            return mandat;            
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

	/**
	 * Approbation d'un mandats PSU
	 *
	 * @param subject Le sujet qui recherche un dossier
	 * @param criteria Les critères de recherche
	 *
	 * @return Le mandat recherché
	 *
	 * @throws BusinessException si une règle d'affaire n'est pas respectée
	 * @throws BusinessResourceException si une erreur système survient
	 */
	public void approbation(CardexAuthenticationSubject subject,
		   PSUMandat criteria) throws BusinessRuleException,
						BusinessResourceException {
		try {
			find(subject, criteria);
			FabriqueCardexDAO.getInstance().getPSUMandatDAO().approbation(subject, criteria);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}

	/**
	 * Suppresion ou désactivation d'un mandat PSU
	 *
	 * @param subject Le sujet qui recherche un dossier
	 * @param criteria Les critères de recherche
	 *
	 * @return Le mandat recherché
	 *
	 * @throws BusinessException si une règle d'affaire n'est pas respectée
	 * @throws BusinessResourceException si une erreur système survient
	 */
	public void delete(CardexAuthenticationSubject subject,
		   PSUMandat criteria) throws BusinessRuleException,
						BusinessResourceException {
		try {
			//validator.checkBusinessRules(criteria);
			find(subject, criteria);
			FabriqueCardexDAO.getInstance().getPSUMandatDAO().delete(subject, criteria);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}

	/**
	 * Sauvegarde d'un nouveau mandat PSU
	 *
	 * @param subject Le sujet qui recherche un dossier
	 * @param criteria Les critères de recherche
	 *
	 * @return Le mandat recherché
	 *
	 * @throws BusinessException si une règle d'affaire n'est pas respectée
	 * @throws BusinessResourceException si une erreur système survient
	 */
	public void insert(CardexAuthenticationSubject subject,
		   PSUMandat criteria) throws BusinessRuleException,
						BusinessResourceException {
		try {
			//validator.checkBusinessRules(criteria);
			GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteria, GlobalConstants.ActionSecurite.AJOUT);
			FabriqueCardexDAO.getInstance().getPSUMandatDAO().insert(subject, criteria);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}

	/**
	 * Mise à jour d'un mandat PSU
	 *
	 * @param subject Le sujet qui recherche un dossier
	 * @param criteria Les critères de recherche
	 *
	 * @return Le mandat recherché
	 *
	 * @throws BusinessException si une règle d'affaire n'est pas respectée
	 * @throws BusinessResourceException si une erreur système survient
	 */
	public void update(CardexAuthenticationSubject subject,
		   PSUMandat criteria) throws BusinessRuleException,
						BusinessResourceException {
		try {
			PSUMandat mandatSource = find(subject, criteria);
			SecuriteAdHoc.validerSecuriteModification(subject, mandatSource);
			GestionnaireSecuriteCardex.validerSecuriteModificationSansSecuritePredicate(subject, mandatSource, criteria);
			
			if (GestionnaireSecuriteCardex.isModificiation(mandatSource, criteria))
				FabriqueCardexDAO.getInstance().getPSUMandatDAO().update(subject, criteria);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}

	/**
	 * Vérification d'un mandat PSU à la suite d'une action.
	 *
	 * @param subject Le sujet qui recherche un dossier
	 * @param criteria Les critères de recherche
	 *
	 * @return Le mandat recherché
	 *
	 * @throws BusinessException si une règle d'affaire n'est pas respectée
	 * @throws BusinessResourceException si une erreur système survient
	 */
	public List<PSUMandat> verification(CardexAuthenticationSubject subject,
			PSUMandat criteria, String genreFichier, String action) 
			throws BusinessRuleException, BusinessResourceException {
		try {
			return FabriqueCardexDAO.getInstance().getPSUMandatDAO().verification(subject, criteria, genreFichier, action);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}

    /**
     * Recherche de mandats PSU.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La consignation recherché
     * @throws BusinessException 
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     */
    public List<PSUMandat> select(CardexAuthenticationSubject subject,
                        CriteresRecherchePSUMandat criteria) throws BusinessRuleException,
                        BusinessException {
        try {
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateurRechercheDossier(subject, criteria);
            checkNombreEnregistrementRecherchePSUMandatList(subject, criteria);
            List<PSUMandat> psuMandatList = FabriqueCardexDAO.getInstance().getPSUMandatDAO().select(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossier(subject, psuMandatList);            
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    private void checkNombreEnregistrementRecherchePSUMandatList(CardexAuthenticationSubject subject, CriteresRecherchePSUMandat criteria)  throws BusinessException{
    	try {
    		NumberFormat numberFormat = NumberFormat.getInstance();
    		Integer nbEnregistrement = FabriqueCardexDAO.getInstance().getPSUMandatDAO().nombreDePSUMandatRecherche(subject, criteria);

    		if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_MANDAT_PSU){
    			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_MANDAT_PSU );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
    			throw businessRuleExceptionHandle.getBusinessException();
    		}
    	} catch (DAOException e) {
    		e.printStackTrace();
    		throw new BusinessException("Problème avec checkNombreEnregistrementRechercheDossier");
    	}
    }    
    
	/**
	 * Mise à jour d'un mandat PSU
	 *
	 * @param subject Le sujet qui recherche un dossier
	 * @param criteria Les critères de recherche
	 *
	 * @return Le mandat recherché
	 *
	 * @throws BusinessException si une règle d'affaire n'est pas respectée
	 * @throws BusinessResourceException si une erreur système survient
	 */
	public void ecrireConsignationAction(CardexAuthenticationSubject subject,
			ConsignationActionPSU consignationActionPSU) throws BusinessRuleException,
						BusinessResourceException {
		try {
			//validator.checkBusinessRules(criteria);
			FabriqueCardexDAO.getInstance().getPSUMandatDAO().ecrireConsignationAction(subject, consignationActionPSU);
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}

	/**
	 * Lecture des consignations d'action reliées à un mandat PSU.
	 *
	 * @param subject Le sujet qui recherche un dossier
	 * @param criteria Les critères de recherche
	 *
	 * @return La consignation recherché
	 *
	 * @throws BusinessException si une règle d'affaire n'est pas respectée
	 * @throws BusinessResourceException si une erreur système survient
	 */
	public ValueListIterator findLiensConsignationAction(CardexAuthenticationSubject subject,
						String mandat) throws BusinessRuleException,
						BusinessResourceException {
		try {
            ValueListIterator valueListIterator = FabriqueCardexDAO.getInstance().getPSUMandatDAO().findLiensConsignationAction(subject, mandat);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossier(subject, valueListIterator);			
		} catch (DAOException dae) {
			throw new BusinessResourceException(dae);
		}
	}


}

