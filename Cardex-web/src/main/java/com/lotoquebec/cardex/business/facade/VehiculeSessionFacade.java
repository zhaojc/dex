package com.lotoquebec.cardex.business.facade;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.lotoquebec.cardex.business.CriteresRechercheVehicule;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Particularites;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.exception.VehiculeBusinessRuleException;
import com.lotoquebec.cardex.business.vo.ParticularitesVO;
import com.lotoquebec.cardex.business.vo.VehiculeVO;
import com.lotoquebec.cardex.businessRules.BusinessRulesValidator;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.integration.dao.VehiculeDAO;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;

/**
 * Le VehiculeSessionFacade offre les
 * services d'affaires, gère les intéractions
 * et les validations de règles d'affaires applicables
 * aux véhicules.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.9 $, $Date: 2002/04/23 21:09:27 $
 */

public class VehiculeSessionFacade {


    /**
     * Création d'un vehicule
     *
     * @param subject L'utilisateur qui créé le vehicule
     * @param info Le vehicule à créer
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public Vehicule create(CardexAuthenticationSubject subject,
                       Vehicule info) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, info);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, info, GlobalConstants.ActionSecurite.AJOUT);
            return VehiculeDAO.insert(subject, info);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche d'un vehicule
     *
     * @param subject L'utilisateur qui recherche un vehicule
     * @param criteria Les critères de recherche
     *
     * @return Le vehicule recherché
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public Vehicule find(CardexAuthenticationSubject subject,
                        Vehicule criteria) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            Vehicule vehicule = VehiculeDAO.find(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulterDossierIntervenantEstLeCreateur(subject, vehicule);
            return vehicule;
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }
    
    /**
     * Recherche d'un vehicule dans l'audit des changements
     *
     * @param subject L'utilisateur qui recherche un vehicule
     * @param criteria Les critères de recherche
     *
     * @return Le vehicule recherché
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public Vehicule findAudit(CardexAuthenticationSubject subject,
                        Vehicule criteria) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            Vehicule vehicule = VehiculeDAO.findAudit(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulterDossierIntervenantEstLeCreateur(subject, vehicule);
            return vehicule;
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }
    
    /**
     * Recherche d'un vehicule (avec audit)
     *
     * @param subject L'utilisateur qui recherche un vehicule
     * @param criteria Les critères de recherche
     *
     * @return Le vehicule recherché
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public Vehicule findAcces(CardexAuthenticationSubject subject,
                        Vehicule criteria) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            Vehicule vehicule =  VehiculeDAO.findAcces(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulterDossierIntervenantEstLeCreateur(subject, vehicule);
            return vehicule;
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }    

    /**
     * Recherche des vehicules créés dans les dernières 48 heures
     *
     * @param subject L'utilisateur qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les vehicules recherchés
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List<Vehicule> selectDefault(CardexAuthenticationSubject subject,
                                           CriteresRechercheVehicule criteria) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            List<Vehicule> vehiculeList =  VehiculeDAO.selectDefault(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, vehiculeList);            
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche de vehicules
     *
     * @param subject L'utilisateur qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les vehicules recherchés
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List<Vehicule> select(CardexAuthenticationSubject subject,
                                    CriteresRechercheVehicule criteria) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteria, GlobalConstants.ActionSecurite.RECHERCHE);
            checkNombreEnregistrementRechercheVehicule(subject, criteria);
            List<Vehicule> vehiculeList = VehiculeDAO.select(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, vehiculeList);            
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    private void checkNombreEnregistrementRechercheVehicule(CardexAuthenticationSubject subject, CriteresRechercheVehicule criteria)  throws BusinessException{
    	try {
    		NumberFormat numberFormat = NumberFormat.getInstance();
    		Integer nbEnregistrement = VehiculeDAO.nombreDeVehiculeRecherche(subject, criteria);

    		if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_VEHICULE){
    			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_VEHICULE );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
    			throw businessRuleExceptionHandle.getBusinessException();
    		}
    	} catch (DAOException e) {
    		e.printStackTrace();
    		throw new BusinessException("Problème avec checkNombreEnregistrementRechercheDossier");
    	}
    }    
    
    /**
     * Mise à jour d'un vehicule
     *
     * @param subject L'utilisateur qui modifie le vehicule
     * @param info Le vehicule à modifier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void update(CardexAuthenticationSubject subject,
                       Vehicule info) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, info);
            Vehicule vehiculeSource = find(subject, info);
            GestionnaireSecuriteCardex.validerSecuriteModificationIntervenantEstLeCreateur(subject, vehiculeSource, info);
            
            if (GestionnaireSecuriteCardex.isModificiation(vehiculeSource, info))            
            	VehiculeDAO.update(subject, info);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Ajout d'un lien entre une narration et un vehicule.
     *
     * @param subject L'utilisateur qui effectue le lien
     * @param vehicule Le vehicule à lier
     * @param narration La narration à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void addLienNarration(CardexAuthenticationSubject subject,
                             Vehicule vehicule,
                             Narration narration) throws BusinessRuleException,
                             BusinessException {
        try {
        	GestionnaireSecuriteCardex.assignerBooleanNull(subject, narration, GlobalConstants.ActionSecurite.AJOUT);
        	find(subject, vehicule);
        	GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, narration, GlobalConstants.ActionSecurite.AJOUT);        	
            VehiculeDAO.addLienNarration(subject, vehicule, narration);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

     /**
     * Approbation d'une narration liée à un véhicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration à approuver
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void approuveLienNarration(CardexAuthenticationSubject subject,
                             Narration narration) throws BusinessRuleException,
                             BusinessException {
        try {
        	GestionnaireSecuriteCardex.assignerBooleanNull(subject, narration, GlobalConstants.ActionSecurite.MODIFICATION);
        	find(subject, new VehiculeVO(narration.getLien(), narration.getLienSite()));
        	FabriqueFacade.getNarrationSessionFacade().find(subject, narration);
            VehiculeDAO.approuveLienNarration(subject, new VehiculeVO(), narration);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Ajout d'un lien entre un vehicule et un dossier.
     *
     * @param subject L'utilisateur qui effectue le lien
     * @param vehicule Le vehicule à lier
     * @param dossier Le dossier à lier au vehicule.
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void addLienDossier(CardexAuthenticationSubject subject,
                           Vehicule vehicule,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessException {
        try {
        	find(subject, vehicule);
        	FabriqueFacade.getDossierSessionFacade().find(subject, dossier);
            VehiculeDAO.addLienDossier(subject, vehicule, dossier);
        } catch (DAOException dae) {
        	handleDAOException(dae);
        }
    }

    /**
     * Recherche des liens entre deux vehicules.
     *
     * @param subject L'utilisateur qui effectue le lien
     * @param vehicule Le vehicule lié
     * @return  Les vehicules liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
/* Pas nécessaire dans cette version (2002-01-01)
    public Collection findLiensVehicule(CardexAuthenticationSubject subject,
                           Vehicule vehicule) throws BusinessRuleException,
                           BusinessResourceException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(vehicule);
            return VehiculeDAO.findLiensVehicule(subject, vehicule);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }
*/
    /**
     * Recherche des liens dossiers.
     *
     * @param subject L'utilisateur qui effectue le lien
     * @param vehicule Le vehicule lié
     * @return  Les dossiers liés
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public Collection findLiensDossier(CardexAuthenticationSubject subject,
                           Vehicule vehicule) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, vehicule);

        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.VEHICULE_DOSSIERS_ONGLET)){
    			Collection collection = VehiculeDAO.findLiensDossier(subject, vehicule);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossier(subject, collection);
        	}else
        		return new ArrayList();            
            
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Ajout d'un lien entre une société et un vehicule.
     *
     * @param subject L'utilisateur qui effectue le lien
     * @param vehicule Le vehicule à lier
     * @param societe La société à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void addLienSociete(CardexAuthenticationSubject subject,
                           Vehicule vehicule,
                           Societe societe) throws BusinessRuleException,
                           BusinessException {
        try {
        	find(subject, vehicule);
        	FabriqueFacade.getSocieteSessionFacade().find(subject, societe);
            VehiculeDAO.addLienSociete(subject, vehicule, societe);
        } catch (DAOException dae) {
        	handleDAOException(dae);
        }
    }

    /**
     * Ajout d'un lien entre un sujet et un véhicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param vehicule
     * @param linkedSujet Le sujet à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void addLienSujet(CardexAuthenticationSubject subject,
                         Vehicule vehicule,
                         Sujet sujet) throws BusinessRuleException,
                                             BusinessException
    {
        try {
        	find(subject, vehicule);
        	FabriqueFacade.getSujetSessionFacade().find(subject, sujet);
            VehiculeDAO.addLienSujet(subject, vehicule, sujet);
        } catch (DAOException dae) {
        	handleDAOException(dae);
        }
    }

    /**
     * Recherche des liens Particularites.
     *
     * @param subject L'utilisateur qui effectue le lien
     * @param vehicule Le vehicule lié
     * @return  Les particularités liées
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public Particularites findLiensParticularite(CardexAuthenticationSubject subject,
                           Vehicule vehicule) throws BusinessRuleException,
                           BusinessException {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.VEHICULE_PARTICULARITES_ONGLET)){
        		return VehiculeDAO.findLiensParticularite(subject, vehicule);
        	}else
        		return new ParticularitesVO();            
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Ajout d'un lien entre des particularités et un véhicule avec un audit.
     *
     * @param subject Le sujet qui effectue le lien
     * @param vehicule Le vehicule à lier
     * @param particularites La particularité à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void updateLienParticularite(CardexAuthenticationSubject subject,
                               Vehicule vehicule,
                               Particularites particularites) throws BusinessRuleException,
                               BusinessException
    {
        try {
        	find(subject, vehicule);
        	Particularites particularitesSource = VehiculeDAO.findLiensParticularite(subject, vehicule);
        	GestionnaireSecuriteCardex.validerSecuriteModificationSansSecuritePredicate(subject, particularitesSource, particularites);
        	
        	if (GestionnaireSecuriteCardex.isModificiation(particularitesSource, particularites))        	
        		VehiculeDAO.updateLienParticularite(subject, vehicule, particularites);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Ajout d'un lien entre une photo et un vehicule.
     *
     * @param subject Le vehicule qui effectue le lien
     * @param vehicule Le vehicule à lier
     * @param photo La photo à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public Photo addLienPhoto(CardexAuthenticationSubject subject,
                         Vehicule vehicule,
                         Photo photo) throws BusinessRuleException,
                                             BusinessException {
        try {
        	find(subject, vehicule);
            return VehiculeDAO.addLienPhoto(subject, vehicule, photo);
        } catch (DAOException dae) {
			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle("erreur.ajout.document");
			throw businessRuleExceptionHandle.getBusinessException();
        }
    }


    /**
     * Suppression d'un lien entre une narration et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void deleteLienNarration(CardexAuthenticationSubject subject,
                             Vehicule vehicule,
                             Narration narration) throws BusinessRuleException,
                             BusinessException {
        try {
        	find(subject, vehicule);
        	FabriqueFacade.getNarrationSessionFacade().find(subject, narration);        	
            VehiculeDAO.deleteLienNarration(subject, vehicule, narration);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Suppression d'un lien entre un dossier et un vehicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void deleteLienDossier(CardexAuthenticationSubject subject,
                             Vehicule vehicule,
                             Dossier dossier) throws BusinessRuleException,
                             BusinessException {
        try {
        	find(subject, vehicule);
        	FabriqueFacade.getDossierSessionFacade().find(subject, dossier);
            VehiculeDAO.deleteLienDossier(subject, vehicule, dossier);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }
    
    /**
     * Suppression des véhicules de confidentialité 8.
     *
     * @param subject Le sujet qui effectue le lien
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void delete(CardexAuthenticationSubject subject) 
    			throws BusinessRuleException, BusinessResourceException {
        try {
            VehiculeDAO.delete(subject);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }       

    /**
     * Suppression d'un lien entre un sujet et un vehicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La société à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void deleteLienSujet(CardexAuthenticationSubject subject,
                             Vehicule vehicule,
                             Sujet sujet) throws BusinessRuleException,
                             BusinessException {
        try {
        	find(subject, vehicule);
        	FabriqueFacade.getSujetSessionFacade().find(subject, sujet);
            VehiculeDAO.deleteLienSujet(subject, vehicule, sujet);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }
    /**
     * Suppression d'un lien entre une société et un vehicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La société à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void deleteLienSociete(CardexAuthenticationSubject subject,
                             Vehicule vehicule,
                             Societe deleted) throws BusinessRuleException,
                             BusinessException {
        try {
        	find(subject, vehicule);
        	FabriqueFacade.getSocieteSessionFacade().find(subject, deleted);
            VehiculeDAO.deleteLienSociete(subject, vehicule, deleted);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Suppression d'un lien entre une photo et un vehicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param photo La photo à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void deleteLienPhoto(CardexAuthenticationSubject subject,
                             Vehicule vehicule,
                             Photo photo) throws BusinessRuleException,
                             BusinessException {
        try {
        	find(subject, vehicule);
            VehiculeDAO.deleteLienPhoto(subject, vehicule, photo);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche des liens sujets
     *
     * @param subject Le sujet qui effectue le lien
     * @param vehicule
     * @return  Les sujets liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public Collection findLiensSujet(CardexAuthenticationSubject subject,
                           Vehicule vehicule) throws BusinessRuleException,
                           BusinessException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.VEHICULE_SUJETS_ONGLET)){
    			Collection collection = VehiculeDAO.findLiensSujet(subject, vehicule);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, collection);
        	}else
        		return new ArrayList();            
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche des liens narrations
     *
     * @param subject Le sujet qui effectue le lien
     * @param vehicule
     * @return  Les narrations liées
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public Collection findLiensNarration(CardexAuthenticationSubject subject,
                           Vehicule vehicule) throws BusinessRuleException,
                           BusinessException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.VEHICULE_NARRATIONS_ONGLET)){
    			Collection collection = VehiculeDAO.findLiensNarration(subject, vehicule);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsultationNarrationIntervenantEstLeCreateur(subject, collection);
        	}else
        		return new ArrayList();            
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche des liens sociétés.
     *
     * @param subject Le sujet qui effectue le lien
     * @param vehicule
     * @return  Les sociétés liées
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public Collection findLiensSociete(CardexAuthenticationSubject subject,
                           Vehicule vehicule) throws BusinessRuleException,
                           BusinessException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.VEHICULE_SOCIETES_ONGLET)){
    			Collection collection = VehiculeDAO.findLiensSociete(subject, vehicule);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, collection);
        	}else
        		return new ArrayList();            
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche des liens photos.
     *
     * @param subject Le sujet qui effectue le lien
     * @param vehicule
     * @return  Les photos liées
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public Collection findLiensPhoto(CardexAuthenticationSubject subject,
                           Vehicule vehicule) throws BusinessRuleException,
                           BusinessException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.VEHICULE_PHOTOS_ONGLET)){
    			Collection collection = VehiculeDAO.findLiensPhoto(subject, vehicule);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstAssigne(subject, collection);
        	}else
        		return new ArrayList();            
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }
    /**
     * Mise à jour d'un lien entre une narration et un vehicule.
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
        		VehiculeDAO.updateLienNarration(subject, null, linked);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
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
                bre.setBusinessRule(VehiculeBusinessRuleException.VEHICULE_RELIE_A_LUI_MEME);
                throw bre;
            }
            if (sqlException.getMessage().indexOf("ORA-20002") != -1) {
                bre.setBusinessRule(VehiculeBusinessRuleException.VEHICULE_RELIE_PLUS_UNE_FOIS);
                throw bre;
            }
          }
          throw new BusinessResourceException(daoe);
    }

    /**
     * Recherche de l'audit des changements d'un véhicule
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les véhicules recherchés
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List audit(CardexAuthenticationSubject subject,
                                           Vehicule criteria) throws BusinessRuleException, BusinessException {
        try {
            return VehiculeDAO.audit(subject, criteria);
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Modification du rôle d'un lien à un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi à modifier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void updateLien(CardexAuthenticationSubject subject,
                             Vehicule vehicule) throws BusinessRuleException, BusinessException {
        try {
        	Vehicule vehiculeSource = find(subject, vehicule);
        	if (GestionnaireSecuriteCardex.isModificiation(vehiculeSource, vehicule))            
        		FabriqueCardexDAO.getInstance().getVehiculeDAO().updateLien(subject, vehicule);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }
    
    /**
     * Chargement d'un véhicule à partir de la recherche directe.
     *
     * Cette méthode sert également à sécuriser l'accès.
     *
     * @param subject Le sujet qui recherche un véhicule
     * @param criteria Les critères de recherche
     *
     * @return Le véhicule recherché
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Vehicule rechercheDirecte(CardexAuthenticationSubject subject,
    		Vehicule criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	Vehicule vehicule = FabriqueCardexDAO.getInstance().getVehiculeDAO().rechercheDirecte(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulterDossierIntervenantEstLeCreateur(subject, vehicule);
            return vehicule;
        } catch (DAOException dae) {
            handleDAOException(dae);
            return null;
        }
    }

    /**
     * Ajout d'une entrée dans la table des accès
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void ajoutAcces(CardexAuthenticationSubject subject,
    		Vehicule criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	FabriqueCardexDAO.getInstance().getVehiculeDAO().ajoutAcces(subject, criteria);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

}
