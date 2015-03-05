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

/**
 * Le SocieteSessionFacade offre les services d'affaires, gère les intéractions
 * et les validations de règles d'affaires applicable aux societes.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/23 21:09:27 $
 */
public class SocieteSessionFacade {

	/**
     * Création d'une société
     *
     * @param subject Le sujet qui créé la société
     * @param info Le sujet à créer
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public Societe create(CardexAuthenticationSubject subject,
                       Societe info) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, info);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, info, GlobalConstants.ActionSecurite.AJOUT);
            Societe societe = FabriqueCardexDAO.getInstance().getSocieteDAO().insert(subject, info);
            ListeCache.getInstance().vider(SocieteParClasseCle.class);
            return societe;
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Chargement d'une société particulière.
     *
     * @param subject Le sujet qui recherche une société
     * @param criteria Les critères de recherche
     *
     * @return Le sujet recherché
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Chargement d'une société particulière à partir de l'audit des changements.
     *
     * @param subject Le sujet qui recherche une société
     * @param criteria Les critères de recherche
     *
     * @return Le sujet recherché
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Chargement d'une société particulière (avec audit).
     *
     * @param subject Le sujet qui recherche une société
     * @param criteria Les critères de recherche
     *
     * @return Le sujet recherché
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Recherche des sujets créés dans les dernières 48 heures
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les sujets recherchés
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
     * Recherche de sociétés
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les sujets recherchés
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
    		throw new BusinessException("Problème avec checkNombreEnregistrementRechercheSociete");
    	}
    }
    
    /**
     * Recherche de l'audit de changements d'une société
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les sujets recherchés
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
     * Mise à jour d'une société
     *
     * @param subject Le sujet qui modifie la société
     * @param info Le sujet à modifier
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
     * Ajout d'un lien entre une narration et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet à lier
     * @param narration La narration à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Ajout d'un lien entre une société et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet à lier
     * @param dossier Le dossier à lier au sujet.
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
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
     * Suppression d'un lien entre un dossier et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration Le dossier à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
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
     * Suppression d'un lien entre une adresse et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration L'adresse à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Suppression des sociétés de confidentialité 8.
     *
     * @param subject Le sujet qui effectue le lien
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Suppression d'un lien entre une narration et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Suppression d'un lien entre un sujet et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La société à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Suppression d'un lien entre une société et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La société à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Suppression d'un lien entre une véhicule et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration Le véhicule à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Suppression d'un lien entre une photo et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param photo La photo à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * @param sujet Le sujet lié
     * @return  Les sujets liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Recherche de l'historique des propriétaires.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet lié
     * @return  Les propriétaires liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * @param sujet Le sujet lié
     * @return  Les dossiers liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * @param sujet La société
     * @return  Les adresses liées
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * @param sujet La société
     * @return  Les adresses liées
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
    		throw new BusinessException("Problème avec checkNombreEnregistrementRechercheDossier");
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
     * @param sujet La société
     * @return  Les narrations liées
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Recherche des liens sociétés.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La société
     * @return  Les sociétés liées
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * @param sujet La société
     * @return  Les photos liées
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Recherche des liens véhicules.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La société
     * @return  Les véhicules liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Ajout d'un lien entre une société et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet à lier
     * @param societe La société à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Ajout d'un lien entre une société et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet initial
     * @param linkedSujet Le sujet à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Ajout d'un lien entre une adresse et une société .
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet à lier
     * @param adresse L'adresse à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Ajout d'un lien entre une photo et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet à lier
     * @param photo La photo à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
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
     * Ajout d'un lien entre un véhicule et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet à lier
     * @param vehicule Le véhicule a lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Mise à jour d'un lien entre une adresse et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration L'adresse à modifier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
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
     * Mise à jour d'un lien entre une narration et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration à modifier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Approbation d'une narration liée à une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration à approuver
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * n'est pas reliée à une règle d'affaire contenu dans
     * les règles d'intégrité de la base de données du cardex.
     * Si l'erreur SQl est reliée à une règle d'affaire, cette méthode
     * fait la mise en correspondance entre les codes
     * d'erreur SQL reçus et les règles d'affaires applicable.
     *
     * @param daoe DAOException
     *
     * @throw BusinessRuleException si l'exception est reliée à une règle d'affaire
     * @throw BusinessResourceException si l'exception n'est pas reliée à une règle
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
            //ORACLE qui devrait être chaîné.  Normalement on devrait extraire le
            //code d'erreur à partir de la méthode SQLException.getErrorCode().
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
     * Modification du rôle d'un lien à une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi à modifier
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
     * Copie des données d'une société à une autre.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi à modifier
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
