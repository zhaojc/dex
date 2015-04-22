/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.facade;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.CriteresRechercheAdresses;
import com.lotoquebec.cardex.business.CriteresRechercheSociete;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.exception.SocieteBusinessRuleException;
import com.lotoquebec.cardex.business.fabrique.dossier.predicate.UrgenceFiltrerSocietesRA0022;
import com.lotoquebec.cardex.business.fabrique.societe.AjoutLienDossierSocieteBusinessValidationFabrique;
import com.lotoquebec.cardex.business.fabrique.societe.SupprimerLienDossierSocieteBusinessValidationFabrique;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
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
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SocieteParClasseCle;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.ListeCacheUtils;
import com.lotoquebec.cardexCommun.util.ViderCacheUtils;

/**
 * Le SocieteSessionFacade offre les services d'affaires, g�re les int�ractions
 * et les validations de r�gles d'affaires applicable aux societes.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/23 21:09:27 $
 */
public class SocieteSessionFacade {

	/**
     * Cr�ation d'une soci�t�
     *
     * @param subject Le sujet qui cr�� la soci�t�
     * @param info Le sujet � cr�er
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public Societe create(CardexAuthenticationSubject subject,
                       Societe info) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, info);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, info, GlobalConstants.ActionSecurite.AJOUT);
            Societe societe = FabriqueCardexDAO.getInstance().getSocieteDAO().insert(subject, info);
            ViderCacheUtils.getInstance().assignerViderCaches();
            return societe;
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Chargement d'une soci�t� particuli�re.
     *
     * @param subject Le sujet qui recherche une soci�t�
     * @param criteria Les crit�res de recherche
     *
     * @return Le sujet recherch�
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Societe find(CardexAuthenticationSubject subject,
                        Societe criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	Societe societe = FabriqueCardexDAO.getInstance().getSocieteDAO().find(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulterDossierIntervenantEstLeCreateur(subject, societe);
            return societe;
        } catch (DAOException dae) {
            handleDAOException(dae);
            return null;
        }
    }
    
    /**
     * Chargement d'une soci�t� particuli�re � partir de l'audit des changements.
     *
     * @param subject Le sujet qui recherche une soci�t�
     * @param criteria Les crit�res de recherche
     *
     * @return Le sujet recherch�
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Societe findAudit(CardexAuthenticationSubject subject,
                        Societe criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	Societe societe = FabriqueCardexDAO.getInstance().getSocieteDAO().findAudit(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulterDossierIntervenantEstLeCreateur(subject, societe);
            return societe;
        } catch (DAOException dae) {
            handleDAOException(dae);
            return null;
        }
    }
    
    /**
     * Chargement d'une soci�t� particuli�re (avec audit).
     *
     * @param subject Le sujet qui recherche une soci�t�
     * @param criteria Les crit�res de recherche
     *
     * @return Le sujet recherch�
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Societe findAcces(CardexAuthenticationSubject subject,
                        Societe criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            Societe societe = FabriqueCardexDAO.getInstance().getSocieteDAO().findAcces(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulterDossierIntervenantEstLeCreateur(subject, societe);
            return societe;
        } catch (DAOException dae) {
            handleDAOException(dae);
            return null;
        }
    }    

    /**
     * Recherche des sujets cr��s dans les derni�res 48 heures
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les sujets recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List<Societe> selectDefault(CardexAuthenticationSubject subject,
                                           CriteresRechercheSociete criteria) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            List<Societe> societeList = FabriqueCardexDAO.getInstance().getSocieteDAO().selectDefault(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, societeList);            
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche de soci�t�s
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les sujets recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List<Societe> select(CardexAuthenticationSubject subject,
                                    CriteresRechercheSociete criteria) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteria, GlobalConstants.ActionSecurite.RECHERCHE);
            checkNombreEnregistrementRechercheSociete(subject, criteria);
            List<Societe> societeList = FabriqueCardexDAO.getInstance().getSocieteDAO().select(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, societeList);            
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }
    
    public List<Societe> liaisonSelect(CardexAuthenticationSubject subject,
            CriteresRechercheSociete criteria) throws BusinessRuleException, BusinessException {
		try {
			BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
			GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteria, GlobalConstants.ActionSecurite.RECHERCHE);
			checkNombreEnregistrementRechercheSociete(subject, criteria);
			List<Societe> societeList = FabriqueCardexDAO.getInstance().getSocieteDAO().select(subject, criteria);
			
			// Filtrer dans le cas d'une liaison dossier.  Il faut utilisez l'onglet service d'urgence
			if (criteria.getEntiteCardexLiaison() instanceof DossierVO)
				CollectionUtils.filter(societeList, new UrgenceFiltrerSocietesRA0022());
			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, societeList);            
		} catch (DAOException dae) {
			handleDAOException(dae);return null;
		}
	}    

    private void checkNombreEnregistrementRechercheSociete(CardexAuthenticationSubject subject, CriteresRechercheSociete criteria)  throws BusinessException{
    	try {
    		NumberFormat numberFormat = NumberFormat.getInstance();
    		Integer nbEnregistrement = FabriqueCardexDAO.getInstance().getSocieteDAO().nombreDeSocieteRecherche(subject, criteria);

    		if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_SOCIETE){
    			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_SOCIETE );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
    			throw businessRuleExceptionHandle.getBusinessException();
    		}
    	} catch (DAOException e) {
    		e.printStackTrace();
    		throw new BusinessException("Probl�me avec checkNombreEnregistrementRechercheSociete");
    	}
    }
    
    /**
     * Recherche de l'audit de changements d'une soci�t�
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les sujets recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List audit(CardexAuthenticationSubject subject,
                                    Societe criteria) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getSocieteDAO().audit(subject, criteria);            
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Mise � jour d'une soci�t�
     *
     * @param subject Le sujet qui modifie la soci�t�
     * @param info Le sujet � modifier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void update(CardexAuthenticationSubject subject,
                       Societe info) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, info);
            Societe societeSource = find(subject, info);
            GestionnaireSecuriteCardex.validerSecuriteModificationIntervenantEstLeCreateur(subject, societeSource, info);
            
            if (GestionnaireSecuriteCardex.isModificiation(societeSource, info)){
            	FabriqueCardexDAO.getInstance().getSocieteDAO().update(subject, info);
            	ListeCache.getInstance().vider(SocieteParClasseCle.class);
            }
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Ajout d'un lien entre une narration et une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param narration La narration � lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Narration addLienNarration(CardexAuthenticationSubject subject,
                             Societe societe,
                             Narration narration) throws BusinessRuleException,
                             BusinessResourceException
    {
        try {
        	GestionnaireSecuriteCardex.assignerBooleanNull(subject, narration, GlobalConstants.ActionSecurite.AJOUT);
        	find(subject, societe);
        	GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, narration, GlobalConstants.ActionSecurite.AJOUT);
        	return FabriqueCardexDAO.getInstance().getSocieteDAO().addLienNarration(subject, societe, narration);
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Ajout d'un lien entre une soci�t� et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param dossier Le dossier � lier au sujet.
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessException 
     */
    public void addLienDossier(CardexAuthenticationSubject subject,
                           Societe societe,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessException
    {
        try {
        	Societe societeFind = find(subject, societe);
        	Dossier dossierFind = FabriqueFacade.getDossierSessionFacade().find(subject, dossier);
        	
        	(new AjoutLienDossierSocieteBusinessValidationFabrique(subject, societeFind)).executer(dossierFind);
        	
        	FabriqueCardexDAO.getInstance().getSocieteDAO().addLienDossier(subject, societe, dossier);            
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Suppression d'un lien entre un dossier et une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration Le dossier � supprimer
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessException 
     */
    public void deleteLienDossier(CardexAuthenticationSubject subject,
                             Societe societe,
                             Dossier dossier) throws BusinessRuleException,
                             BusinessException {
        try {
        	Societe societeFind = find(subject, societe);
        	Dossier dossierFind = FabriqueFacade.getDossierSessionFacade().find(subject, dossier);
        	
        	(new SupprimerLienDossierSocieteBusinessValidationFabrique(subject, societeFind)).executer(dossierFind);
        	
            FabriqueCardexDAO.getInstance().getSocieteDAO().deleteLienDossier(subject, societe, dossier);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Suppression d'un lien entre une adresse et une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration L'adresse � supprimer
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienAdresse(CardexAuthenticationSubject subject,
                             Societe societe,
                             Adresse adresse) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	find(subject, societe);
        	FabriqueFacade.getAdresseSessionFacade().find(subject, adresse);
            FabriqueCardexDAO.getInstance().getSocieteDAO().deleteLienAdresse(subject, societe, adresse);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }
    
    /**
     * Suppression des soci�t�s de confidentialit� 8.
     *
     * @param subject Le sujet qui effectue le lien
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void delete(CardexAuthenticationSubject subject) 
    			throws BusinessRuleException, BusinessResourceException {
        try {
            FabriqueCardexDAO.getInstance().getSocieteDAO().delete(subject);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }    

    /**
     * Suppression d'un lien entre une narration et une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration � supprimer
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienNarration(CardexAuthenticationSubject subject,
                             Societe societe,
                             Narration narration) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	find(subject, societe);
        	FabriqueFacade.getNarrationSessionFacade().find(subject, narration);
            FabriqueCardexDAO.getInstance().getSocieteDAO().deleteLienNarration(subject, societe, narration);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Suppression d'un lien entre un sujet et une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La soci�t� � supprimer
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienSujet(CardexAuthenticationSubject subject,
                             Societe societe,
                             Sujet sujet) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	find(subject, societe);
        	FabriqueFacade.getSujetSessionFacade().find(subject, sujet);
            FabriqueCardexDAO.getInstance().getSocieteDAO().deleteLienSujet(subject, societe, sujet);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }
    /**
     * Suppression d'un lien entre une soci�t� et une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La soci�t� � supprimer
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienSociete(CardexAuthenticationSubject subject,
                             Societe societe,
                             Societe deleted) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	find(subject, societe);
        	find(subject, deleted);
            FabriqueCardexDAO.getInstance().getSocieteDAO().deleteLienSociete(subject, societe, deleted);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Suppression d'un lien entre une v�hicule et une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration Le v�hicule � supprimer
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienVehicule(CardexAuthenticationSubject subject,
                             Societe societe,
                             Vehicule vehicule) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	find(subject, societe);
        	FabriqueFacade.getVehiculeSessionFacade().find(subject, vehicule);
            FabriqueCardexDAO.getInstance().getSocieteDAO().deleteLienVehicule(subject, societe, vehicule);
        } catch (DAOException dae) {
            handleDAOException(dae);
        } catch (BusinessException e) {
        	throw new BusinessResourceException(e);
		}
    }

    /**
     * Suppression d'un lien entre une photo et une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param photo La photo � supprimer
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienPhoto(CardexAuthenticationSubject subject,
                             Societe societe,
                             Photo photo) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	find(subject, societe);
            FabriqueCardexDAO.getInstance().getSocieteDAO().deleteLienPhoto(subject, societe, photo);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Recherche des liens entre deux sujets.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les sujets li�s
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensSujet(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_SUJETS_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSocieteDAO().findLiensSujet(subject, societe);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche de l'historique des propri�taires.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les propri�taires li�s
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensProprietaires(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_PROPRIETAIRES_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSocieteDAO().findLiensProprietaires(subject, societe);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

/**
     * Recherche des liens dossiers.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les dossiers li�s
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensDossier(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {

        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_DOSSIERS_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSocieteDAO().findLiensDossier(subject, societe);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossier(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens adresses.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La soci�t�
     * @return  Les adresses li�es
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensAdresse(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_ADRESSES_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSocieteDAO().findLiensAdresse(subject, societe);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, collection);
        	}else
        		return new ArrayList();            
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens adresses historiques.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La soci�t�
     * @return  Les adresses li�es
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensAdresseAudit(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_ADRESSES_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSocieteDAO().findLiensAdresseAudit(subject, societe);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, collection);
        	}else
        		return new ArrayList();            
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    public List<Societe> rechercheAdresseSociete(CardexAuthenticationSubject subject,
    		CriteresRechercheAdresses criteres) throws BusinessRuleException,
            BusinessException
	{
		try {
    		GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteres, GlobalConstants.ActionSecurite.RECHERCHE);
    		checkNombreEnregistrementRechercheAdresseSociete(subject, criteres);
			List list = FabriqueCardexDAO.getInstance().getSocieteDAO().rechercheAdresseSociete(subject, criteres);
			GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, list);
			return list;			
		} catch (DAOException dae) {
			handleDAOException(dae);
			return null;
		}
	}    
    
    private void checkNombreEnregistrementRechercheAdresseSociete(CardexAuthenticationSubject subject, CriteresRechercheAdresses criteria)  throws BusinessException{
    	try {
    		NumberFormat numberFormat = NumberFormat.getInstance();
    		Integer nbEnregistrement = FabriqueCardexDAO.getInstance().getSocieteDAO().nombreDeSocieteRechercheAdresse(subject, criteria);

    		if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_ADRESSE_SOCIETE){
    			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_ADRESSE_SOCIETE );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
    			throw businessRuleExceptionHandle.getBusinessException();
    		}
    	} catch (DAOException e) {
    		e.printStackTrace();
    		throw new BusinessException("Probl�me avec checkNombreEnregistrementRechercheDossier");
    	}
    }    
    
    public Collection rechercheAdresseInvalideSociete(CardexAuthenticationSubject subject,
    		CriteresRechercheAdresses criteres) throws BusinessRuleException,
            BusinessResourceException
	{
		try {
			GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteres, GlobalConstants.ActionSecurite.RECHERCHE);
			Collection collection = FabriqueCardexDAO.getInstance().getSocieteDAO().rechercheAdresseInvalideSociete(subject, criteres);
			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, collection);			
		} catch (DAOException dae) {
			handleDAOException(dae);
			return null;
		}
	}        
    
    /**
     * Recherche des liens narrations
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La soci�t�
     * @return  Les narrations li�es
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensNarration(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_NARRATIONS_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSocieteDAO().findLiensNarration(subject, societe);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsultationNarrationIntervenantEstLeCreateur(subject, collection);
        	}else
        		return new ArrayList();        	
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens soci�t�s.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La soci�t�
     * @return  Les soci�t�s li�es
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensSociete(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_RELATIONS_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSocieteDAO().findLiensSociete(subject, societe);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, collection);
        	}else
        		return new ArrayList();            
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens photos.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La soci�t�
     * @return  Les photos li�es
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensPhoto(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_PH0TOS_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSocieteDAO().findLiensPhoto(subject, societe);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, collection);
        	}else
        		return new ArrayList();        	
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens v�hicules.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La soci�t�
     * @return  Les v�hicules li�s
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensVehicule(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_VEHICULES_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSocieteDAO().findLiensVehicule(subject, societe);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, collection);
        	}else
        		return new ArrayList();            
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Ajout d'un lien entre une soci�t� et une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param societe La soci�t� � lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void addLienSociete(CardexAuthenticationSubject subject,
                           Societe societe,
                           Societe linkedSociete) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	find(subject, societe);
        	find(subject, linkedSociete);        	
            FabriqueCardexDAO.getInstance().getSocieteDAO().addLienSociete(subject, societe, linkedSociete);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Ajout d'un lien entre une soci�t� et une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet initial
     * @param linkedSujet Le sujet � lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void addLienSujet(CardexAuthenticationSubject subject,
                         Societe societe,
                         Sujet sujet) throws BusinessRuleException,
                                             BusinessResourceException
    {
        try {
        	find(subject, societe);
        	FabriqueFacade.getSujetSessionFacade().find(subject, sujet);        	
            FabriqueCardexDAO.getInstance().getSocieteDAO().addLienSujet(subject, societe, sujet);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }


    /**
     * Ajout d'un lien entre une adresse et une soci�t� .
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param adresse L'adresse � lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Adresse addLienAdresse(CardexAuthenticationSubject subject,
                         Societe societe,
                         Adresse adresse) throws BusinessRuleException,
                                             BusinessResourceException
    {
        try {
            find(subject, societe);
        	GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, adresse, GlobalConstants.ActionSecurite.AJOUT);
        	return FabriqueCardexDAO.getInstance().getSocieteDAO().addLienAdresse(subject, societe, adresse);            
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Ajout d'un lien entre une photo et une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param photo La photo � lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessException 
     */
    public Photo addLienPhoto(CardexAuthenticationSubject subject,
                         Societe societe,
                         Photo photo) throws BusinessRuleException,
                                             BusinessException
    {
        try {
        	find(subject, societe);
            return FabriqueCardexDAO.getInstance().getSocieteDAO().addLienPhoto(subject, societe, photo);
        } catch (DAOException dae) {
			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle("erreur.ajout.document");
			throw businessRuleExceptionHandle.getBusinessException();
        }
    }


    /**
     * Ajout d'un lien entre un v�hicule et une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param vehicule Le v�hicule a lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void addLienVehicule(CardexAuthenticationSubject subject,
                            Societe societe,
                            Vehicule vehicule) throws BusinessRuleException,
                            BusinessResourceException
    {
        try {
        	find(subject, societe);
        	FabriqueFacade.getVehiculeSessionFacade().find(subject, vehicule);        	
            FabriqueCardexDAO.getInstance().getSocieteDAO().addLienVehicule(subject, societe, vehicule);
        } catch (DAOException dae) {
            handleDAOException(dae);
        } catch (BusinessException e) {
        	throw new BusinessResourceException(e);
		}
    }

    /**
     * Mise � jour d'un lien entre une adresse et une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration L'adresse � modifier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessException 
     */
    public void updateLienAdresse(CardexAuthenticationSubject subject,
                             Adresse linked) throws BusinessRuleException,
                             BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, linked);
            Adresse adresseSource = FabriqueFacade.getAdresseSessionFacade().find(subject, linked);
            GestionnaireSecuriteCardex.validerSecuriteModificationSansSecuritePredicate(subject, adresseSource, linked);
            
            if (GestionnaireSecuriteCardex.isModificiation(adresseSource, linked))
            	FabriqueCardexDAO.getInstance().getSocieteDAO().updateLienAdresse(subject, null, linked);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Mise � jour d'un lien entre une narration et une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration � modifier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void updateLienNarration(CardexAuthenticationSubject subject,
                             Narration linked) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	Narration narrationSource = FabriqueFacade.getNarrationSessionFacade().find(subject, linked);
        	GestionnaireSecuriteCardex.validerSecuriteModificationIntervenantEstLeCreateur(subject, narrationSource, linked);
        	
        	if (GestionnaireSecuriteCardex.isModificiation(narrationSource, linked))
            	FabriqueCardexDAO.getInstance().getSocieteDAO().updateLienNarration(subject, null, linked);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Approbation d'une narration li�e � une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration � approuver
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void approuveLienNarration(CardexAuthenticationSubject subject,
                             Narration narration) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	GestionnaireSecuriteCardex.assignerBooleanNull(subject, narration, GlobalConstants.ActionSecurite.MODIFICATION);
        	find(subject, new SocieteVO(narration.getLien(), narration.getLienSite()));
        	FabriqueFacade.getNarrationSessionFacade().find(subject, narration);
            FabriqueCardexDAO.getInstance().getSocieteDAO().approuveLienNarration(subject, null, narration);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /*
     * Construit une BusinessResourceException si l'exception SQL n'est pas
     * n'est pas reli�e � une r�gle d'affaire contenu dans
     * les r�gles d'int�grit� de la base de donn�es du cardex.
     * Si l'erreur SQl est reli�e � une r�gle d'affaire, cette m�thode
     * fait la mise en correspondance entre les codes
     * d'erreur SQL re�us et les r�gles d'affaires applicable.
     *
     * @param daoe DAOException
     *
     * @throw BusinessRuleException si l'exception est reli�e � une r�gle d'affaire
     * @throw BusinessResourceException si l'exception n'est pas reli�e � une r�gle
     *                                  d'affaire
     *
     */
    private void handleDAOException(DAOException daoe)
            throws BusinessResourceException,BusinessRuleException {

          Exception sqlex = daoe.getAncestor();
          if (sqlex instanceof SQLException) {
            SQLException sqlException = (SQLException)sqlex;
            BusinessRuleException bre = new BusinessRuleException();

            //Le code d'erreur est extrait du message contenu dans l'exception
            //SQL puisque le driver JDBC Oracle ne donne pas toutes les exception
            //ORACLE qui devrait �tre cha�n�.  Normalement on devrait extraire le
            //code d'erreur � partir de la m�thode SQLException.getErrorCode().
            if (sqlException.getMessage().indexOf("ORA-20001") != -1) {
                bre.setBusinessRule(SocieteBusinessRuleException.SOCIETE_RELIEE_A_ELLE_MEME);
                throw bre;
            }
            if (sqlException.getMessage().indexOf("ORA-20002") != -1) {
                bre.setBusinessRule(SocieteBusinessRuleException .SOCIETE_RELIEE_PLUS_UNE_FOIS);
                throw bre;
            }
          }
          throw new BusinessResourceException(daoe);
    }

    /**
     * Modification du r�le d'un lien � une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi � modifier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void updateLien(CardexAuthenticationSubject subject,
                             Societe societe) throws BusinessRuleException, BusinessException {
        try {
        	Societe societeSource = find(subject, societe);
        	if (GestionnaireSecuriteCardex.isModificiation(societeSource, societe))          
        		FabriqueCardexDAO.getInstance().getSocieteDAO().updateLien(subject, societe);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Copie des donn�es d'une soci�t� � une autre.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi � modifier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void copier(CardexAuthenticationSubject subject,
    		long cleSource, long siteSource, long cleDestination, long siteDestination) throws BusinessRuleException, BusinessException {
        try {
        		FabriqueCardexDAO.getInstance().getSocieteDAO().copier(subject, cleSource, siteSource, cleDestination, siteDestination);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }


 }
