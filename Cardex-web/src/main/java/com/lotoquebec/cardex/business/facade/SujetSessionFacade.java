/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.facade;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.Caracteristiques;
import com.lotoquebec.cardex.business.CriteresRechercheAdresses;
import com.lotoquebec.cardex.business.CriteresRechercheSujet;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.exception.SujetBusinessRuleException;
import com.lotoquebec.cardex.business.fabrique.dossier.RechercheSujetOptionAgeValidationFabrique;
import com.lotoquebec.cardex.business.fabrique.dossier.SauvegardeSujetBusinessValidationFabrique;
import com.lotoquebec.cardex.business.vo.CaracteristiquesVO;
import com.lotoquebec.cardex.business.vo.PhotoVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardex.businessRules.BusinessRulesValidator;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.EntiteCardex;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;
/**
 * Le SujetSessionFacade offre les services d'affaires, g�re les int�ractions
 * et les validations de r�gles d'affaires applicable aux sujets.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.21 $, $Date: 2002/04/23 21:09:27 $
 */
public class SujetSessionFacade {
	
    /**
     * Cr�ation d'un sujet
     *
     * @param subject Le sujet qui cr�� le sujet
     * @param sujet Le sujet � cr�er
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public Sujet create(CardexAuthenticationSubject subject,
                       Sujet sujet) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, sujet);
            GestionnaireSecuriteCardex.assignerBooleanNull(subject, sujet, GlobalConstants.ActionSecurite.AJOUT);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, sujet, GlobalConstants.ActionSecurite.AJOUT);
            (new SauvegardeSujetBusinessValidationFabrique()).executer(sujet);
            return FabriqueCardexDAO.getInstance().getSujetDAO().insert(subject, sujet);
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Chargement d'un sujet particulier (sans audit).
     *
     * Cette m�thode sert �galement � s�curiser l'acc�s.
     *
     * @param subject Le sujet qui recherche un sujet
     * @param criteria Les crit�res de recherche
     *
     * @return Le sujet recherch�
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Sujet find(CardexAuthenticationSubject subject,
    		EntiteCardex criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	Sujet sujet = FabriqueCardexDAO.getInstance().getSujetDAO().find(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulterDossierIntervenantEstLeCreateur(subject, sujet);
            return sujet;
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }
    
    /**
     * Chargement d'un sujet particulier � partir de l'audit des changements.
     *
     * @param subject Le sujet qui recherche un sujet
     * @param criteria Les crit�res de recherche
     *
     * @return Le sujet recherch�
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Sujet findAudit(CardexAuthenticationSubject subject,
                        Sujet criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	Sujet sujet = FabriqueCardexDAO.getInstance().getSujetDAO().findAudit(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulterDossierIntervenantEstLeCreateur(subject, sujet);
            return sujet;
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }
    
    /**
     * Chargement d'un sujet particulier (avec audit).
     *
     * @param subject Le sujet qui recherche un sujet
     * @param criteria Les crit�res de recherche
     *
     * @return Le sujet recherch�
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Sujet findAcces(CardexAuthenticationSubject subject,
                        Sujet criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	Sujet sujet = FabriqueCardexDAO.getInstance().getSujetDAO().findAcces(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulterDossierIntervenantEstLeCreateur(subject, sujet);
            return sujet;
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
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
    public Collection selectDefault(CardexAuthenticationSubject subject,
                                           CriteresRechercheSujet criteria) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            List<Sujet> listSujet = FabriqueCardexDAO.getInstance().getSujetDAO().selectDefault(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, listSujet);
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche de l'audit des changements d'un sujet
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les sujets recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List<Sujet> audit(CardexAuthenticationSubject subject,
                                           Sujet criteria) throws BusinessRuleException, BusinessException {
        try {
            return FabriqueCardexDAO.getInstance().getSujetDAO().audit(subject, criteria);
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche de sujets
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les sujets recherch�s
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List<Sujet> select(CardexAuthenticationSubject subject,
                                    CriteresRechercheSujet criteria) throws BusinessRuleException, BusinessException {
        BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteria, GlobalConstants.ActionSecurite.RECHERCHE);
            (new RechercheSujetOptionAgeValidationFabrique(criteria)).executer(criteria);
            checkNombreEnregistrementRechercheSujet(subject, criteria);
            List<Sujet> listSujet = FabriqueCardexDAO.getInstance().getSujetDAO().select(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, listSujet);
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }
    
  	private void checkNombreEnregistrementRechercheSujet(CardexAuthenticationSubject subject, CriteresRechercheSujet criteria)  throws BusinessException{
  		try {
  			NumberFormat numberFormat = NumberFormat.getInstance();
			Integer nbEnregistrement = FabriqueCardexDAO.getInstance().getSujetDAO().nombreDeSujetRecherche(subject, criteria);
			
			if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_SUJET){
				BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_SUJET );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
				throw businessRuleExceptionHandle.getBusinessException();
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new BusinessException("Probl�me avec checkNombreEnregistrementRechercheSujet");
		}
	}    

    /**
     * Mise � jour d'un sujet
     *
     * @param subject Le sujet qui modifie le sujet
     * @param sujet Le sujet � modifier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void update(CardexAuthenticationSubject subject,
                       Sujet sujet) throws BusinessRuleException, BusinessException {
        try {
            BusinessRulesValidator.getInstance().checkBusinessRules(subject, sujet);
            GestionnaireSecuriteCardex.assignerBooleanNull(subject, sujet, GlobalConstants.ActionSecurite.MODIFICATION);
            Sujet sujetSource = find(subject, sujet);
            GestionnaireSecuriteCardex.validerSecuriteModificationIntervenantEstLeCreateur(subject, sujetSource, sujet);
            (new SauvegardeSujetBusinessValidationFabrique()).executer(sujet); 
            if (GestionnaireSecuriteCardex.isModificiation(sujetSource, sujet))
            	FabriqueCardexDAO.getInstance().getSujetDAO().update(subject, sujet);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Ajout d'un lien entre une narration et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param narration La narration � lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void addLienNarration(CardexAuthenticationSubject subject,
                             Sujet sujet,
                             Narration narration) throws BusinessRuleException,
                             BusinessResourceException
    {
        try {
        	GestionnaireSecuriteCardex.assignerBooleanNull(subject, narration, GlobalConstants.ActionSecurite.AJOUT);
        	find(subject, sujet);
        	GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, narration, GlobalConstants.ActionSecurite.AJOUT);
        	FabriqueCardexDAO.getInstance().getSujetDAO().addLienNarration(subject, sujet, narration);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Ajout d'un lien entre un sujet et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param dossier Le dossier � lier au sujet.
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void addLienDossier(CardexAuthenticationSubject subject,
                           Sujet sujet,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	find(subject, sujet);
        	FabriqueFacade.getDossierSessionFacade().find(subject, dossier);
            FabriqueCardexDAO.getInstance().getSujetDAO().addLienDossier(subject, sujet, dossier);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Destruction d'un lien entre une photo et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet lier
     * @param photo La photo lier au sujet.
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienPhoto(CardexAuthenticationSubject subject,
                           Sujet sujet,
                           Photo photo) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	find(subject, sujet);
            FabriqueCardexDAO.getInstance().getSujetDAO().deleteLienPhoto(subject,sujet,photo);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Destruction d'un lien entre un dossier et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param dossier Le dossier � lier au sujet.
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienDossier(CardexAuthenticationSubject subject,
                           Sujet sujet,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	find(subject, sujet);
        	FabriqueFacade.getDossierSessionFacade().find(subject, dossier);
            FabriqueCardexDAO.getInstance().getSujetDAO().deleteLienDossier(subject,sujet,dossier);
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
    public Collection findLiensSociete(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_SOCIETES_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSujetDAO().findLiensSociete(subject, sujet);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
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
                           Sujet sujet) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_RELATIONS_ONGLET)
        	&& GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_RESULTAT_SUJETS_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSujetDAO().findLiensSujet(subject, sujet);
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
     * @param sujet Le sujet li�
     * @return  Les v�hicules li�s
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensVehicule(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_VEHICULES_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSujetDAO().findLiensVehicule(subject, sujet);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens entre un sujet et ses adresses.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les adresse li�s
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensAdresse(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_ADRESSES_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSujetDAO().findLiensAdresse(subject, sujet);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens historiques entre un sujet et ses adresses.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les adresse li�s
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensAdresseAudit(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_ADRESSES_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSujetDAO().findLiensAdresseAudit(subject, sujet);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, collection);
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
                           Sujet sujet) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {

        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_DOSSIERS_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSujetDAO().findLiensDossier(subject, sujet);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossier(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    public Collection findLiensDossierGalerie(CardexAuthenticationSubject subject, Sujet sujet) throws BusinessRuleException, BusinessResourceException{
		
    	try {
		
			if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_DOSSIERS_ONGLET)){
				Collection collection = FabriqueCardexDAO.getInstance().getSujetDAO().findLiensDossier(subject, sujet);
				return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheGalerieDossier(subject, collection);
			}else
				return new ArrayList();
		} catch (DAOException dae) {
			handleDAOException(dae);return null;
		}
	}

    /**
     * Recherche des liens caracteristiques.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les caracteristiques li�s
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Caracteristiques findLiensCaracteristique(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_CARACTERISTIQUES_ONGLET)){
        		return FabriqueCardexDAO.getInstance().getSujetDAO().findLiensCaracteristique(subject, sujet);
        	}else
        		return new CaracteristiquesVO();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens photos.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les dossiers li�s
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List<PhotoVO> findLiensPhoto(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_PHOTOS_ONGLET)){
        		List<PhotoVO> photoVOs = FabriqueCardexDAO.getInstance().getSujetDAO().findLiensPhoto(subject, sujet);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstAssigne(subject, photoVOs);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens photos historiques.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les dossiers li�s
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensPhotoAudit(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_PHOTOS_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSujetDAO().findLiensPhotoAudit(subject, sujet);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstAssigne(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens narrations.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les dossiers li�s
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensNarration(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_NARRATIONS_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getSujetDAO().findLiensNarration(subject, sujet);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsultationNarrationIntervenantEstLeCreateur(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Mise � jour d'un lien entre une narration et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration � modifier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void updateLienNarration(CardexAuthenticationSubject subject,
                             Narration narration) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	GestionnaireSecuriteCardex.assignerBooleanNull(subject, narration, GlobalConstants.ActionSecurite.MODIFICATION);
        	Narration narrationSource = FabriqueFacade.getNarrationSessionFacade().find(subject, narration);
        	GestionnaireSecuriteCardex.validerSecuriteModificationIntervenantEstLeCreateur(subject, narrationSource, narration);
        	
        	if (GestionnaireSecuriteCardex.isModificiation(narrationSource, narration))
        		FabriqueCardexDAO.getInstance().getSujetDAO().updateLienNarration(subject, new SujetVO(), narration);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

     /**
     * Approbation d'une narration li�e � un dossier.
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
        	find(subject, new SujetVO(narration.getLien(), narration.getLienSite()));
        	FabriqueFacade.getNarrationSessionFacade().find(subject, narration);
            FabriqueCardexDAO.getInstance().getSujetDAO().approuveLienNarration(subject, new SujetVO(), narration);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

   /**
     * Mise � jour d'un lien entre une adresse et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param adresse La adresse � modifier
	 * @throws BusinessRuleException
	 * @throws BusinessException
     */
    public void updateLienAdresse(CardexAuthenticationSubject subject,
                             Adresse adresse) throws BusinessRuleException, BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, adresse);
            Adresse adresseSource = FabriqueFacade.getAdresseSessionFacade().find(subject, adresse);
            GestionnaireSecuriteCardex.validerSecuriteModificationSansSecuritePredicate(subject, adresseSource, adresse);
            
            if (GestionnaireSecuriteCardex.isModificiation(adresseSource, adresse))
            	FabriqueCardexDAO.getInstance().getSujetDAO().updateLienAdresse(subject, new SujetVO(), adresse);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Ajout d'un lien entre une soci�t� et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param societe La soci�t� � lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void addLienSociete(CardexAuthenticationSubject subject,
                           Sujet sujet,
                           Societe societe) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	find(subject, sujet);
        	FabriqueFacade.getSocieteSessionFacade().find(subject, societe);
            FabriqueCardexDAO.getInstance().getSujetDAO().addLienSociete(subject, sujet, societe);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Ajout d'un lien entre un sujet et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet initial
     * @param linkedSujet Le sujet � lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void addLienSujet(CardexAuthenticationSubject subject,
                         Sujet sujet,
                         Sujet linkedSujet) throws BusinessRuleException,
                                             BusinessResourceException
    {
        try {
        	find(subject, sujet);
        	find(subject, linkedSujet);        	
            FabriqueCardexDAO.getInstance().getSujetDAO().addLienSujet(subject, sujet, linkedSujet);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Destruction d'un lien entre un sujet et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet initial
     * @param linkedSujet Le sujet � lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienSociete(CardexAuthenticationSubject subject,
                         Sujet sujet,
                         Societe linkedSociete) throws BusinessRuleException,
                                             BusinessResourceException
    {
        try {
        	find(subject, sujet);
        	FabriqueFacade.getSocieteSessionFacade().find(subject, linkedSociete);
            FabriqueCardexDAO.getInstance().getSujetDAO().deleteLienSociete(subject, sujet, linkedSociete);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

   /**
     * Destruction d'un lien entre un sujet et un v�hicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet initial
     * @param vehicule Le v�hicule li�
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienVehicule(CardexAuthenticationSubject subject,
                         Sujet sujet,
                         Vehicule vehicule) throws BusinessRuleException,
                                             BusinessResourceException
    {
        try {
        	find(subject, sujet);
        	FabriqueFacade.getVehiculeSessionFacade().find(subject, vehicule);
            FabriqueCardexDAO.getInstance().getSujetDAO().deleteLienVehicule(subject, sujet, vehicule);
        } catch (DAOException dae) {
            handleDAOException(dae);
        } catch (BusinessException e) {
			throw new BusinessResourceException(e);
		}
    }

    /**
     * Destruction d'un lien entre un sujet et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet initial
     * @param linkedSujet Le sujet � lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienSujet(CardexAuthenticationSubject subject,
                         Sujet sujet,
                         Sujet linkedSujet) throws BusinessRuleException,
                                             BusinessResourceException
    {
        try {
        	find(subject, sujet);
        	find(subject, linkedSujet);        	
            FabriqueCardexDAO.getInstance().getSujetDAO().deleteLienSujet(subject, sujet, linkedSujet);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Destruction d'un lien entre une adresse et un sujet .
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param adresse L'adresse � lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienAdresse(CardexAuthenticationSubject subject,
                         Sujet sujet,
                         Adresse adresse) throws BusinessRuleException,
                                             BusinessResourceException
    {
        try {
        	find(subject, sujet);
        	FabriqueFacade.getAdresseSessionFacade().find(subject, adresse);
            FabriqueCardexDAO.getInstance().getSujetDAO().deleteLienAdresse(subject, sujet, adresse);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }
    
    /**
     * Suppression des sujets de confidentialit� 8.
     *
     * @param subject Le sujet qui effectue le lien
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void delete(CardexAuthenticationSubject subject) 
    			throws BusinessRuleException, BusinessResourceException {
        try {
        	FabriqueCardexDAO.getInstance().getSujetDAO().delete(subject);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }    

    /**
     * Destruction d'un lien entre une narration et un sujet .
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param narration L'narration � lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienNarration(CardexAuthenticationSubject subject,
                         Sujet sujet,
                         Narration narration) throws BusinessRuleException,
                                             BusinessResourceException
    {
        try {
        	find(subject, sujet);
        	FabriqueFacade.getNarrationSessionFacade().find(subject, narration);
            FabriqueCardexDAO.getInstance().getSujetDAO().deleteLienNarration(subject, sujet, narration);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Ajout d'un lien entre une adresse et un sujet .
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param adresse L'adresse � lier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void addLienAdresse(CardexAuthenticationSubject subject,
                         Sujet sujet,
                         Adresse adresse) throws BusinessRuleException, BusinessException
    {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, adresse);
        	find(subject, sujet);
        	GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, adresse, GlobalConstants.ActionSecurite.AJOUT);
            FabriqueCardexDAO.getInstance().getSujetDAO().addLienAdresse(subject, sujet, adresse);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }


    /**
     * Ajout d'un lien entre des caract�ristiques et un sujet avec audit.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param inscription L'inscription � lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void updateLiensCaracteristique(CardexAuthenticationSubject subject,
                               Sujet sujet,
                               Caracteristiques caracteristiques) throws BusinessRuleException,
                               BusinessResourceException
    {
        try {
        	find(subject, sujet);
        	Caracteristiques caracteristiquesSource = FabriqueCardexDAO.getInstance().getCaracteristiqueDAO().findLiensCaracteristique(subject, sujet);
        	GestionnaireSecuriteCardex.validerSecuriteModificationSansSecuritePredicate(subject, caracteristiquesSource, caracteristiques);
        	
        	if (GestionnaireSecuriteCardex.isModificiation(caracteristiquesSource, caracteristiques))        	
        		FabriqueCardexDAO.getInstance().getSujetDAO().updateLiensCaracteristique(subject, sujet, caracteristiques);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Ajout d'un lien entre une photo et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param photo La photo � lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessException 
     */
    public Photo addLienPhoto(CardexAuthenticationSubject subject,
                         Sujet sujet,
                         Photo photo) throws BusinessRuleException,
                                             BusinessException
    {
        try {
        	find(subject, sujet);
            return FabriqueCardexDAO.getInstance().getSujetDAO().addLienPhoto(subject,sujet,photo);
        } catch (DAOException dae) {
			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle("erreur.ajout.document");
			throw businessRuleExceptionHandle.getBusinessException();
        }
    }

    /**
     * Ajout d'un lien entre un v�hicule et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param vehicule Le v�hicule a lier
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void addLienVehicule(CardexAuthenticationSubject subject,
                            Sujet sujet,
                            Vehicule vehicule) throws BusinessRuleException,
                            BusinessResourceException
    {
        try {
        	find(subject, sujet);
        	FabriqueFacade.getVehiculeSessionFacade().find(subject, vehicule);
            FabriqueCardexDAO.getInstance().getSujetDAO().addLienVehicule(subject, sujet, vehicule);
        } catch (DAOException dae) {
            handleDAOException(dae);
        } catch (BusinessException e) {
			throw new BusinessResourceException(e);
		}
    }

    public List<Sujet> rechercheAdresseSujet(CardexAuthenticationSubject subject,
    		CriteresRechercheAdresses criteres) throws BusinessException,
            BusinessResourceException, BusinessRuleException {
    	try {
    		GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteres, GlobalConstants.ActionSecurite.RECHERCHE);
    		checkNombreEnregistrementRechercheAdresse(subject, criteres);
			List list = FabriqueCardexDAO.getInstance().getSujetDAO().rechercheAdresseSujet(subject, criteres);
			GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, list);
			return list;
		} catch (DAOException e) {
			handleDAOException(e);
		}
		return null;
	}    
    
    private void checkNombreEnregistrementRechercheAdresse(CardexAuthenticationSubject subject, CriteresRechercheAdresses criteria)  throws BusinessException{
    	try {
    		NumberFormat numberFormat = NumberFormat.getInstance();
    		Integer nbEnregistrement = FabriqueCardexDAO.getInstance().getSujetDAO().nombreDeSujetRechercheAdresse(subject, criteria);

    		if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_ADRESSE_SUJET){
    			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_ADRESSE_SUJET );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
    			throw businessRuleExceptionHandle.getBusinessException();
    		}
    	} catch (DAOException e) {
    		e.printStackTrace();
    		throw new BusinessException("Probl�me avec checkNombreEnregistrementRechercheDossier");
    	}
    } 
    
    public Collection rechercheAdresseInvalideSujet(CardexAuthenticationSubject subject,
    		CriteresRechercheAdresses criteres) throws BusinessException,
            BusinessResourceException, BusinessRuleException {
    	try {
    		GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, criteres, GlobalConstants.ActionSecurite.RECHERCHE);
			Collection collection = FabriqueCardexDAO.getInstance().getSujetDAO().rechercheAdresseInvalideSujet(subject, criteres);
			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, collection);
		} catch (DAOException e) {
			handleDAOException(e);
		}
		return null;
	}        
    
    public void selectionnerPhotoGalerie(CardexAuthenticationSubject subject, Sujet sujetVO, PhotoVO photoVO) throws BusinessException, BusinessResourceException, BusinessRuleException {
		try {
			find(subject, sujetVO);
			FabriqueCardexDAO.getInstance().getSujetDAO().selectionnerPhotoGalerie(subject, sujetVO, photoVO);
		} catch (DAOException e) {
			handleDAOException(e);
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
                bre.setBusinessRule(SujetBusinessRuleException.SUJET_RELIE_A_LUI_MEME);
                throw bre;
            }
            if (sqlException.getMessage().indexOf("ORA-20002") != -1) {
                bre.setBusinessRule(SujetBusinessRuleException.SUJET_RELIE_PLUS_UNE_FOIS);
                throw bre;
            }
          }
          throw new BusinessResourceException(daoe);
    }
	
    /**
     * Modification du r�le d'un lien � un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi � modifier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void updateLien(CardexAuthenticationSubject subject,
                             Sujet sujet) throws BusinessRuleException, BusinessException {
        try {
        	Sujet sujetSource = find(subject, sujet);
        	if (GestionnaireSecuriteCardex.isModificiation(sujetSource, sujet))          
        		FabriqueCardexDAO.getInstance().getSujetDAO().updateLien(subject, sujet);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Copie des donn�es d'un sujet � une autre.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi � modifier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void copier(CardexAuthenticationSubject subject,
    		long cleSource, long siteSource, long cleDestination, long siteDestination) throws BusinessRuleException, BusinessException {
        try {
        		FabriqueCardexDAO.getInstance().getSujetDAO().copier(subject, cleSource, siteSource, cleDestination, siteDestination);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }
    
    /**
     * Chargement d'un sujet � partir de la recherche directe.
     *
     * Cette m�thode sert �galement � s�curiser l'acc�s.
     *
     * @param subject Le sujet qui recherche un sujet
     * @param criteria Les crit�res de recherche
     *
     * @return Le sujet recherch�
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Sujet rechercheDirecte(CardexAuthenticationSubject subject,
                        Sujet criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	Sujet sujet = FabriqueCardexDAO.getInstance().getSujetDAO().rechercheDirecte(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulterDossierIntervenantEstLeCreateur(subject, sujet);
            return sujet;
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Ajout d'une entr�e dans la table des acc�s
     *
     * @throws BusinessRuleException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void ajoutAcces(CardexAuthenticationSubject subject,
                        Sujet criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	FabriqueCardexDAO.getInstance().getSujetDAO().ajoutAcces(subject, criteria);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }
   
    private BusinessRuleExceptionHandle validerCriteresRechercheSujet(CardexAuthenticationSubject subject, List<Sujet> listeSujet)  throws BusinessException, DAOException, BusinessRuleException{
        BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();

       
        return businessRuleExceptionHandle;
    }

}
