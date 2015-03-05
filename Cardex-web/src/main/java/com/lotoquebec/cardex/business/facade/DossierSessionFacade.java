/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.facade;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.lotoquebec.cardex.business.Consignation;
import com.lotoquebec.cardex.business.CriteresRechercheDossier;
import com.lotoquebec.cardex.business.CriteresRechercheJournal;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Evaluation;
import com.lotoquebec.cardex.business.Inscription;
import com.lotoquebec.cardex.business.Jeux;
import com.lotoquebec.cardex.business.Journal;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Partage;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Urgence;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.exception.DossierBusinessRuleException;
import com.lotoquebec.cardex.business.fabrique.dossier.AjoutPieceJointeBusinessValidationFabrique;
import com.lotoquebec.cardex.business.fabrique.dossier.AjouterLienSocieteDossierBusinessValidationFabrique;
import com.lotoquebec.cardex.business.fabrique.dossier.PreSauvegardeDossierBusinessValidationFabrique;
import com.lotoquebec.cardex.business.fabrique.dossier.SauvegardeDossierBusinessValidationFabrique;
import com.lotoquebec.cardex.business.fabrique.dossier.SupprimerLienDossierDossierBusinessValidationFabrique;
import com.lotoquebec.cardex.business.fabrique.dossier.SupprimerLienSocieteDossierBusinessValidationFabrique;
import com.lotoquebec.cardex.business.fabrique.dossier.UrgenceDossierBusinessValidationFabrique;
import com.lotoquebec.cardex.business.fabrique.dossier.UrgenceSupprimerLienSocieteDossierBusinessValidationFabrique;
import com.lotoquebec.cardex.business.fabrique.dossier.regle.ViderSujetInteretDossierSujetInteret;
import com.lotoquebec.cardex.business.vo.DossierVO;
import com.lotoquebec.cardex.business.vo.JeuxVO;
import com.lotoquebec.cardex.business.vo.JournalVO;
import com.lotoquebec.cardex.business.vo.LiaisonBilletSocieteVO;
import com.lotoquebec.cardex.business.vo.SousCategorieVO;
import com.lotoquebec.cardex.business.vo.SousCategoriesVO;
import com.lotoquebec.cardex.business.vo.rapport.AmbulanceDossierRapportVO;
import com.lotoquebec.cardex.businessRules.BusinessRulesValidator;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardex.securite.SecuriteAdHoc;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.AutentificationCardex;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.user.CardexUser;

/**
 * Le DossierSessionFacade offre les
 * services d'affaires, gère les intéractions
 * et les validations de règles d'affaires applicable
 * aux dossiers.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.42 $, $Date: 2002/04/23 21:09:27 $
 */
public class DossierSessionFacade {
    
    /**
     * Création d'un dossier
     *
     * @param subject Le sujet qui créé le dossier
     * @param info Le dossier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public synchronized Dossier create(CardexAuthenticationSubject subject,
                       Dossier info) throws BusinessRuleException, BusinessException {
        try { 
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, info);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, info, GlobalConstants.ActionSecurite.AJOUT);
            return FabriqueCardexDAO.getInstance().getDossierDAO().insert(subject, info);
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Détermine si un dossier est avec inscription.
     *
     * @param subject Le sujet qui créé le dossier
     * @param info Le dossier à créer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public boolean isAvecInscription(CardexAuthenticationSubject subject,
                       Dossier info) throws BusinessRuleException,
                                            BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getDossierDAO().isAvecInscription(subject, info);
        } catch (DAOException dae) {
            handleDAOException(dae);return false;
        }
    }

    /**
     * Recherche d'un dossier (sans audit)
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return Le dossier recherché
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Dossier find(CardexAuthenticationSubject subject,
                        Dossier criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	CardexUser user = (CardexUser)subject.getUser();
        	Dossier dossier = FabriqueCardexDAO.getInstance().getDossierDAO().find(subject, criteria);
        	//On vérifie s'il s'agit d'un dossier partagé et si l'utilisateur y a droit.
        	SecuriteAdHoc.validerSecuriteConsultationDossierPartage(subject, dossier);
            return dossier;
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }
    
    /**
     * Recherche d'une entrée de journal
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return Le dossier recherché
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Journal findJournal(CardexAuthenticationSubject subject,
                        Journal criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            Journal journal = FabriqueCardexDAO.getInstance().getDossierDAO().findJournal(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulter(subject, journal);
            return journal;            
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }
    
    /**
     * Recherche d'une entrée d'inscription
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return Le dossier recherché
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Inscription findInscription(CardexAuthenticationSubject subject,
    		Inscription criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            Inscription inscription = FabriqueCardexDAO.getInstance().getDossierDAO().findInscription(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulter(subject, inscription);
            return inscription;            
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Ouverture du partage à partir de l'onglet Partage
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return Le dossier recherché
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Partage ouvrirPartage(CardexAuthenticationSubject subject,
    		Dossier criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getDossierDAO().ouvrirPartage(subject, criteria);
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche de l'audit des changements d'un dossier.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return Le dossier recherché
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List audit(CardexAuthenticationSubject subject,
    		Dossier criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
            return FabriqueCardexDAO.getInstance().getDossierDAO().audit(subject, criteria);
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche d'un dossier (avec audit)
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return Le dossier recherché
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Dossier findAcces(CardexAuthenticationSubject subject,
                        Dossier criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	CardexUser user = (CardexUser)subject.getUser();
        	Dossier dossier = FabriqueCardexDAO.getInstance().getDossierDAO().findAcces(subject, criteria);
        	//On vérifie s'il s'agit d'un dossier partagé et si l'utilisateur y a droit.
        	SecuriteAdHoc.validerSecuriteConsultationDossierPartage(subject, dossier);
            return dossier;
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }    

    /**
     * Recherche des dossiers créés dans les dernières 48 heures
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les dosiers recherchés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Dossier> selectDefault(CardexAuthenticationSubject subject,
                                           CriteresRechercheDossier criteria) throws BusinessRuleException,
                                           BusinessResourceException {
        try {
            List<Dossier> dossierList = FabriqueCardexDAO.getInstance().getDossierDAO().selectDefault(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossier(subject, dossierList);
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des entrées de la journée pour afficher par défaut dans l'écran 
     * de recherche des entrées du journal de surveillance.
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les dosiers recherchés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Journal> selectDefaultJournal(CardexAuthenticationSubject subject,
                                           CriteresRechercheJournal criteria) throws BusinessRuleException,
                                           BusinessResourceException {
        try {
            List<Journal> journalList = FabriqueCardexDAO.getInstance().getDossierDAO().selectDefaultJournal(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossier(subject, journalList);
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche de dossiers
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les dosiers recherchés
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List<Dossier> select(CardexAuthenticationSubject subject, CriteresRechercheDossier criteria) throws BusinessRuleException, BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateurRechercheDossier(subject, criteria);
            List<Dossier> listDossier = null;
        	
            if (criteria.getNumeroFicheSujet() != null
			&& criteria.getNumeroFicheSujet().trim().length() > 0
	        && !criteria.getNumeroFicheSujet().equals("*")) {
        		listDossier = FabriqueCardexDAO.getInstance().getDossierDAO().rechercheFicheSujet(subject, criteria.getNumeroFicheSujet());
	        }            
        	else
        		checkNombreEnregistrementRechercheDossier(subject, criteria);
            listDossier = FabriqueCardexDAO.getInstance().getDossierDAO().select(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossier(subject, listDossier);            
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche du nombre de dossiers retournés par une recherche
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Le nombre de dossiers recherchés
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public int nombreDossiers(CardexAuthenticationSubject subject, CriteresRechercheDossier criteria) throws BusinessRuleException, BusinessException {
        try {
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateurRechercheDossier(subject, criteria);
            return FabriqueCardexDAO.getInstance().getDossierDAO().nombreDeDossierRecherche(subject, criteria);
        } catch (DAOException dae) {
            handleDAOException(dae);
            return 0;
        }
    }

  	private void checkNombreEnregistrementRechercheDossier(CardexAuthenticationSubject subject, CriteresRechercheDossier criteria)  throws BusinessException{
  		try {
  			NumberFormat numberFormat = NumberFormat.getInstance();
			Integer nbEnregistrement = FabriqueCardexDAO.getInstance().getDossierDAO().nombreDeDossierRecherche(subject, criteria);
			
			if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_DOSSIER){
				BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_DOSSIER );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
				throw businessRuleExceptionHandle.getBusinessException();
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new BusinessException("Problème avec checkNombreEnregistrementRechercheDossier");
		}
	}
    
    /**
     * Recherche des dossiers partagés
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les dosiers recherchés
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List<Dossier> recherchePartage(CardexAuthenticationSubject subject,
                                    CriteresRechercheDossier criteria) throws BusinessRuleException, BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateurRechercheDossier(subject, criteria);
            return FabriqueCardexDAO.getInstance().getDossierDAO().recherchePartage(subject, criteria);
            //Si le l'utilisateur a undossier partagé à son nom, on ne vérifie pas ses accès. La ligne suivante n'est donc plus
            //nécessaire (2011-07-05).
            //return GestionnaireSecurite.validerEtFiltrerSecuriteRechercheDossier(subject, valueListIterator);
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Inscription de la date de paiement dans les billets associés au dossier
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les dosiers recherchés
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void inscrireDatePaiement(CardexAuthenticationSubject subject,
											 long cle,
											 long site,
											 String datePaiement) throws BusinessRuleException, BusinessException {
        try {
            FabriqueCardexDAO.getInstance().getDossierDAO().inscrireDatePaiement(subject, cle, site, datePaiement);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }
    
    /**
     * Recherche des entrées du journal de surveillance
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les dosiers recherchés
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public List<Journal> selectJournal(CardexAuthenticationSubject subject,
                                    CriteresRechercheJournal criteria) throws BusinessRuleException, BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateurRechercheDossier(subject, criteria);
            checkNombreEnregistrementRechercheJournal(subject, criteria);
            List<Journal> journalList = FabriqueCardexDAO.getInstance().getDossierDAO().selectJournal(subject, criteria);
            
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossier(subject, journalList);            
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }
    
  	private void checkNombreEnregistrementRechercheJournal(CardexAuthenticationSubject subject, CriteresRechercheJournal criteria)  throws BusinessException{
  		try {
  			NumberFormat numberFormat = NumberFormat.getInstance();
			Integer nbEnregistrement = FabriqueCardexDAO.getInstance().getDossierDAO().nombreDeJournalRecherche(subject, criteria);
			
			if (nbEnregistrement > GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_JOURNAL){
				BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
				String nombreRetourne = numberFormat.format( nbEnregistrement );
				String nombreMax = numberFormat.format( GlobalConstants.NombreEnregistrementRetourneRecherche.RECHERCHE_JOURNAL );
				businessRuleExceptionHandle.add("err.trop.enregistrements.retournes", nombreRetourne, nombreMax);
				throw businessRuleExceptionHandle.getBusinessException();
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new BusinessException("Problème avec checkNombreEnregistrementRechercheDossier");
		}
	}    

    /**
     * Recherche des entrées du journal de surveillance pour les rapports
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les dosiers recherchés
     * @throws BusinessRuleException
     * @throws BusinessException
     */
/*    public ValueListIterator selectRapportJournal(CardexAuthenticationSubject subject,
                                    CriteresRechercheJournal criteria) throws BusinessRuleException, BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteria);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateurRechercheDossier(subject, criteria);
            ValueListIterator valueListIterator = FabriqueDAO.getInstance().getDossierDAO().selectRapportJournal(subject, criteria);
            return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossier(subject, valueListIterator);            
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }
*/
  	/**
  	 * Méthode pour retourner les messages d'avertissement et d'information
  	 */
    public List<BusinessMessage> preSauvegardeMessage(CardexAuthenticationSubject subject, Dossier dossier) throws BusinessRuleException, BusinessException {
		
    	try {
			Dossier dossierSource = find(subject, dossier);

			if (GestionnaireSecuriteCardex.isModificiation(dossierSource, dossier)) {
				PreSauvegardeDossierBusinessValidationFabrique preSauvegardeDossierBusinessValidationFabrique = new PreSauvegardeDossierBusinessValidationFabrique(subject);
				preSauvegardeDossierBusinessValidationFabrique.executer(dossier);
				return preSauvegardeDossierBusinessValidationFabrique.getMessages();
			}
		} catch (DAOException dae) {
			handleDAOException(dae);
		}
		return null;
	}

    
    /**
     * Mise à jour d'un dossier
     *
     * @param subject Le sujet qui modifie le dossier
     * @param info Le dossier à modifier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void update(CardexAuthenticationSubject subject,
                       Dossier dossier) throws BusinessRuleException, BusinessException {
        try {
        	validerDossier(dossier);
            
            Dossier dossierSource = find(subject, dossier);
            SecuriteAdHoc.validerSecuriteModification(subject, dossierSource);
            GestionnaireSecuriteCardex.validerSecuriteModificationDossierIntervenantEstAssigne(subject, dossierSource, dossier);
            
            (new SauvegardeDossierBusinessValidationFabrique(subject)).executer(dossier);
            
            if (GestionnaireSecuriteCardex.isModificiation(dossierSource, dossier)){
            	FabriqueCardexDAO.getInstance().getDossierDAO().update(subject, dossier);
            }
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }
    
    /**
     * Mise à jour d'un dossier
     *
     * @param subject Le sujet qui modifie le dossier
     * @param info Le dossier à modifier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void updateInscription(CardexAuthenticationSubject subject,
                       Inscription info) throws BusinessRuleException, BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, info);
            Inscription inscriptionSource = findInscription(subject, info);
            GestionnaireSecuriteCardex.validerSecuriteModificationSansSecuritePredicate(subject, inscriptionSource, info);
            
            if (GestionnaireSecuriteCardex.isModificiation(inscriptionSource, info))            
            	FabriqueCardexDAO.getInstance().getDossierDAO().updateInscription(subject, info);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Ajout d'un lien entre une narration et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param narration La narration à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void addLienNarration(CardexAuthenticationSubject subject,
                             Dossier dossier,
                             Narration narration) throws BusinessRuleException,
                             BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, narration);
        	GestionnaireSecuriteCardex.assignerBooleanNull(subject, narration, GlobalConstants.ActionSecurite.AJOUT);
        	find(subject, dossier);
        	GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, narration, GlobalConstants.ActionSecurite.AJOUT);
        	FabriqueCardexDAO.getInstance().getDossierDAO().addLienNarration(subject, dossier, narration);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    public void addLienNarrationApprouve(CardexAuthenticationSubject subject,
            Dossier dossier,
            Narration narration) throws BusinessRuleException,
            BusinessException {
		try {
			BusinessRulesValidator.getInstance().checkBusinessRules(subject, narration);
			GestionnaireSecuriteCardex.assignerBooleanNull(subject, narration, GlobalConstants.ActionSecurite.AJOUT);
        	find(subject, dossier);
        	GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, narration, GlobalConstants.ActionSecurite.AJOUT);
        	FabriqueCardexDAO.getInstance().getDossierDAO().addLienNarrationApprouve(subject, dossier, narration);
		} catch (DAOException dae) {
			handleDAOException(dae);
		}
	}    
    
    /**
     * Ajout d'un lien entre une narration et un dossier 
     * lors d'une sauvegtarde automatique.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param narration La narration à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    /*public Narration addLienNarrationAuto(CardexAuthenticationSubject subject,
                             Dossier dossier,
                             Narration narration) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	find(subject, dossier);
        	GestionnaireSecurite.validerSecuriteEntreeUtilisateur(subject, narration, GlobalConstants.ActionSecurite.AJOUT);
            narration = FabriqueDAO.getInstance().getDossierDAO().addLienNarration(subject, dossier, narration);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
         return narration;
    }*/

    /**
     * Suppression d'un lien entre une narration et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienNarration(CardexAuthenticationSubject subject,
                             Dossier dossier,
                             Narration narration) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	find(subject, dossier);
        	FabriqueFacade.getNarrationSessionFacade().find(subject, narration);        	
        	FabriqueCardexDAO.getInstance().getDossierDAO().deleteLienNarration(subject,dossier,narration);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Suppression d'un lien entre un suivi et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi Le suivi à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienSuivi(CardexAuthenticationSubject subject,
                             Dossier dossier,
                             Suivi suivi) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	find(subject, dossier);
        	FabriqueFacade.getSuiviSessionFacade().find(subject, suivi);        	
        	FabriqueCardexDAO.getInstance().getDossierDAO().deleteLienSuivi(subject,dossier,suivi);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Suppression d'un lien entre une consignation et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param consignation La consignation à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienConsignation(CardexAuthenticationSubject subject,
                             Dossier dossier,
                             Consignation consignation) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	find(subject, dossier);
        	FabriqueFacade.getConsignationSessionFacade().find(subject, consignation);
        	FabriqueCardexDAO.getInstance().getDossierDAO().deleteLienConsignation(subject,dossier,consignation);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Suppression d'un lien entre une Urgence et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param consignation La consignation à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void deleteLienUrgence(CardexAuthenticationSubject subject,
                             Dossier dossier,
                             Urgence urgence) throws BusinessRuleException,
                             BusinessException {
        try {
        	find(subject, dossier); 
        	FabriqueFacade.getUrgenceSessionFacade().find(subject, urgence);
        	(new UrgenceSupprimerLienSocieteDossierBusinessValidationFabrique(subject,urgence)).executer(dossier);
        	FabriqueCardexDAO.getInstance().getDossierDAO().deleteLienUrgence(subject,dossier,urgence);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Suppression d'un lien entre une photo et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param photo La photon à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienPhoto(CardexAuthenticationSubject subject,
                             Dossier dossier,
                             Photo photo) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	find(subject, dossier);
        	FabriqueCardexDAO.getInstance().getDossierDAO().deleteLienPhoto(subject, dossier, photo);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Mise à jour d'un lien entre une narration et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration à modifier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void updateLienNarration(CardexAuthenticationSubject subject,
                             Narration narration) throws BusinessRuleException,
                             BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, narration);
        	GestionnaireSecuriteCardex.assignerBooleanNull(subject, narration, GlobalConstants.ActionSecurite.MODIFICATION);
        	Narration narrationSource = FabriqueFacade.getNarrationSessionFacade().find(subject, narration);
        	SecuriteAdHoc.validerSecuriteModificationNarration(subject, narrationSource, narration);
            GestionnaireSecuriteCardex.validerSecuriteModificationIntervenantEstLeCreateur(subject, narrationSource, narration);
            
            if (GestionnaireSecuriteCardex.isModificiation(narrationSource, narration))        	
            	FabriqueCardexDAO.getInstance().getDossierDAO().updateLienNarration(subject, new DossierVO(), narration);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }
    
    /**
     * Mise à jour d'un lien entre un journal et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration à modifier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void updateLienJournal(CardexAuthenticationSubject subject,
                             Narration narration) throws BusinessRuleException,
                             BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, narration);
        	GestionnaireSecuriteCardex.assignerBooleanNull(subject, narration, GlobalConstants.ActionSecurite.MODIFICATION);
        	
        	Journal journalSource = findJournal(subject, new JournalVO( narration.getLien(), narration.getLienSite() ));
        	Narration narrationSource = FabriqueFacade.getNarrationSessionFacade().find(subject, narration);
        	
        	SecuriteAdHoc.validerSecuriteModification(subject, journalSource);
            GestionnaireSecuriteCardex.validerSecuriteModificationIntervenantEstLeCreateur(subject, narrationSource, narration);
            
            if (GestionnaireSecuriteCardex.isModificiation(narrationSource, narration))        	
            	FabriqueCardexDAO.getInstance().getDossierDAO().updateLienNarration(subject, new DossierVO(), narration);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }    
    
    /**
     * Suppression des dossiers de confidentialité 8.
     *
     * @param subject Le sujet qui effectue le lien
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void delete(CardexAuthenticationSubject subject) 
    			throws BusinessRuleException, BusinessResourceException {
        try {
        	FabriqueCardexDAO.getInstance().getDossierDAO().delete(subject);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }    

    /**
     * Mise à jour d'un lien entre une suivi et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi à modifier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void updateLienSuivi(CardexAuthenticationSubject subject,
                             Suivi suivi) throws BusinessRuleException, BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, suivi);
        	Suivi suiviSource = FabriqueFacade.getSuiviSessionFacade().find(subject, suivi);
        	SecuriteAdHoc.validerSecuriteModification(subject, suiviSource);
        	GestionnaireSecuriteCardex.validerSecuriteModificationSansSecuritePredicate(subject, suiviSource, suivi);
        	
        	if (GestionnaireSecuriteCardex.isModificiation(suiviSource, suivi))            
        		FabriqueCardexDAO.getInstance().getDossierDAO().updateLienSuivi(subject, new DossierVO(), suivi);
        } catch (DAOException dae) {
            handleDAOException(dae);
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
                             Dossier dossier) throws BusinessRuleException, BusinessException {
        try {
            Dossier dossierSource = find(subject, dossier);
            SecuriteAdHoc.validerSecuriteModification(subject, dossierSource);
        	GestionnaireSecuriteCardex.validerSecuriteModificationDossierIntervenantEstAssigne(subject, dossierSource, dossier);
        	
        	if (GestionnaireSecuriteCardex.isModificiation(dossierSource, dossier))            
        		FabriqueCardexDAO.getInstance().getDossierDAO().updateLien(subject, dossier);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Mise à jour d'un lien entre une consignation et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La consignation à modifier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void updateLienConsignation(CardexAuthenticationSubject subject,
                             Consignation consignation) throws BusinessRuleException, BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, consignation);
            Consignation consignationSource = FabriqueFacade.getConsignationSessionFacade().find(subject, consignation);
            SecuriteAdHoc.validerSecuriteModification(subject, consignationSource);
            GestionnaireSecuriteCardex.validerSecuriteModificationSansSecuritePredicate(subject, consignationSource, consignation);
            
            if (GestionnaireSecuriteCardex.isModificiation(consignationSource, consignation))            
            	FabriqueCardexDAO.getInstance().getDossierDAO().updateLienConsignation(subject, new DossierVO(), consignation);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Mise à jour d'un lien entre un service d'urgence et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La consignation à modifier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void updateLienUrgence(CardexAuthenticationSubject subject,
    		Dossier dossier, Urgence urgence) throws BusinessRuleException, BusinessException {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, urgence);
        	Urgence urgenceSource = FabriqueFacade.getUrgenceSessionFacade().find(subject, urgence);
            //SecuriteAdHoc.validerSecuriteModification(subject, urgenceSource); 
            GestionnaireSecuriteCardex.validerSecuriteModificationSansSecuritePredicate(subject, urgenceSource, urgence);

            if (GestionnaireSecuriteCardex.isModificiation(urgenceSource, urgence)){  
            	(new UrgenceDossierBusinessValidationFabrique(subject, urgence)).executer(dossier);
            	FabriqueCardexDAO.getInstance().getDossierDAO().updateLienUrgence(subject, new DossierVO(), urgence);
            }
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Mise à jour d'un lien entre une narration et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration à aprouver
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void approuveLienNarration(CardexAuthenticationSubject subject,
                             Narration narration) throws BusinessRuleException,
                             BusinessResourceException {
        try {
			//validator.checkBusinessRules(narration);
        	GestionnaireSecuriteCardex.assignerBooleanNull(subject, narration, GlobalConstants.ActionSecurite.MODIFICATION);
        	find(subject, new DossierVO(narration.getLien(), narration.getLienSite()));
        	FabriqueFacade.getNarrationSessionFacade().find(subject, narration);
        	FabriqueCardexDAO.getInstance().getDossierDAO().approuveLienNarration(subject, new DossierVO(), narration);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Mise à jour d'un lien entre un suivi et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi Le suivi à aprouver
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void approuveLienSuivi(CardexAuthenticationSubject subject,
                             Suivi suivi) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	find(subject, new DossierVO(suivi.getLien(), suivi.getLienSite()));
        	FabriqueFacade.getSuiviSessionFacade().find(subject, suivi);        	
        	FabriqueCardexDAO.getInstance().getDossierDAO().approuveLienSuivi(subject, new DossierVO(), suivi);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Approbation d'un lien entre une consignation et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La consignation à aprouver
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void approuveLienConsignation(CardexAuthenticationSubject subject,
                             Consignation consignation) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	find(subject, new DossierVO(consignation.getLien(), consignation.getLienSite()));
        	FabriqueFacade.getConsignationSessionFacade().find(subject, consignation);
        	FabriqueCardexDAO.getInstance().getDossierDAO().approuveLienConsignation(subject, new DossierVO(), consignation);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    public void modifierApprouveLienSousCategorie(CardexAuthenticationSubject subject,
    		Dossier dossier, boolean approuver) throws BusinessRuleException,
            BusinessResourceException {
		try {
			find(subject, dossier);
			FabriqueCardexDAO.getInstance().getSousCategorieDAO().modifierApprouveLienSousCategorie(subject, dossier, approuver);
		} catch (DAOException dae) {
			handleDAOException(dae);
		}
	}    
    
    /**
     * Ajout d'un lien entre deux dossiers.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param addedDossier Le 2e dossier à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public BusinessRuleExceptionHandle addLienDossier(CardexAuthenticationSubject subject,
                           Dossier dossier,
                           Dossier addedDossier) throws BusinessRuleException,
                           BusinessResourceException {
        BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
        try {
            Dossier dossierOrigine = find(subject, dossier);
            Dossier dossierAjoute = find(subject, addedDossier);
        	FabriqueCardexDAO.getInstance().getDossierDAO().addLienDossier(subject, dossier, addedDossier);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
        return businessRuleExceptionHandle;
    }

    /**
     * Destruction d'un lien entre deux dossiers.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @param liaisonDossier Le 2e dossier lié
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public void deleteLienDossier(CardexAuthenticationSubject subject,
                           Dossier dossier,
                           Dossier liaisonDossier) throws BusinessRuleException,
                           BusinessException {
        try {
        	Dossier dossierOrigine = find(subject, dossier);
        	Dossier liaisonDossierFind = find(subject, liaisonDossier);
        	
        	(new SupprimerLienDossierDossierBusinessValidationFabrique(subject, liaisonDossierFind)).executer(dossierOrigine);
        	
        	FabriqueCardexDAO.getInstance().getDossierDAO().deleteLienDossier(subject, dossier, liaisonDossier);
        	
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Destruction d'un lien entre deux dossiers.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @param sujet Le sujet lié
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienSujet(CardexAuthenticationSubject subject,
                           Dossier dossier,
                           Sujet sujet) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	find(subject, dossier);
        	FabriqueFacade.getSujetSessionFacade().find(subject, sujet);    
        	new ViderSujetInteretDossierSujetInteret().executer(dossier);
        	FabriqueCardexDAO.getInstance().getDossierDAO().deleteLienSujet(subject, dossier, sujet);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Destruction d'un lien entre un dossier et un véhicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @param sujet Le véhicule lié
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienVehicule(CardexAuthenticationSubject subject,
                           Dossier dossier,
                           Vehicule vehicule) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	find(subject, dossier);
        	FabriqueFacade.getVehiculeSessionFacade().find(subject, vehicule);        	
        	FabriqueCardexDAO.getInstance().getDossierDAO().deleteLienVehicule(subject, dossier, vehicule);
        } catch (DAOException dae) {
            handleDAOException(dae);
        } catch (BusinessException e) {
        	throw new BusinessResourceException(e);
		}
    }

    /**
     * Destruction d'un lien entre deux dossiers.
     *
     * @param subject Le societe qui effectue le lien
     * @param dossier Le dossier lié
     * @param societe Le societe lié
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienSociete(CardexAuthenticationSubject subject,
                           Dossier dossier,
                           Societe societe) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	Dossier dossierOrigine = find(subject, dossier);
        	FabriqueFacade.getSocieteSessionFacade().find(subject, societe);
        	
        	(new SupprimerLienSocieteDossierBusinessValidationFabrique(subject, societe)).executer(dossierOrigine);
        	
        	FabriqueCardexDAO.getInstance().getDossierDAO().deleteLienSociete(subject, dossier, societe);

        } catch (BusinessException e) {
            throw new BusinessResourceException(e);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }


    /**
     * Recherche des liens entre deux dossiers.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les dossiers liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensDossier(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_RELATIONS_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensDossier(subject, dossier);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheDossier(subject, collection);
        	}else
        		return new ArrayList();            
            
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens sujets.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les sujets liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensSujet(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	//Accès à tous les sujets de l'onglet.
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_SUJETS_ONGLET)
        	&& GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_RESULTAT_SUJETS_ONGLET)){
        		Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensSujet(subject, dossier);
        		return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, collection);
        	}else{
        		//Accès aux sujets provisoires seulement
        		if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_SUJETS_ONGLET)){
            		Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensSujetProvisoire(subject, dossier);
            		return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, collection);
        		}else
        			//Pas d'affichage de sujets
        			return new ArrayList(); 
        	}
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens societes.
     *
     * @param subject Le societe qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les societes liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensSociete(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_SOCIETES_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensSociete(subject, dossier);
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
     * @param dossier Le dossier lié
     * @return  Les sujets liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensVehicule(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_VEHICULES_ONGLET)
        	&& GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_RESULTAT_VEHICULES_ONGLET)){
        		Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensVehicule(subject, dossier);
        		return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstLeCreateur(subject, collection);
        	}else
        		return new ArrayList();             
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens inscriptions.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les inscriptions liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensInscription(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_INSCRIPTIONS_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensInscription(subject, dossier);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens jeux.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les sujets liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Jeux findLiensJeux(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_JEUX_ONGLET))
        		return FabriqueCardexDAO.getInstance().getDossierDAO().findLiensJeux(subject, dossier);
        	else
        		return new JeuxVO();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens Partage.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les sujets liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensPartage(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_PARTAGE_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensPartage(subject, dossier);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    public SousCategoriesVO findLiensSousCategories(CardexAuthenticationSubject subject, Dossier dossier) throws BusinessRuleException, BusinessResourceException {
    	
		try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_SOUSCATEGORIES_ONGLET))
        		return FabriqueCardexDAO.getInstance().getDossierDAO().findLiensSousCategories(subject, dossier);
        	else
        		return new SousCategoriesVO();
		} catch (DAOException dae) {
			handleDAOException(dae);return null;
		}
	}    
    
    /**
     * Recherche des liens entre un dossier et ses narrations.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les narrations liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensNarration(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_NARRATIONS_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensNarration(subject, dossier);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsultationNarrationIntervenantEstLeCreateur(subject, collection);
        	}else
        		return new ArrayList();           
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }
    
    /**
     * Recherche des liens entre un dossier et ses narrations pour un rapport.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les narrations liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensNarrationRapport(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException {
        try {
			Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensNarrationRapport(subject, dossier);
			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsultationNarrationIntervenantEstLeCreateur(subject, collection);
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens entre un dossier et ses narrations pour un rapport uniformisé.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les narrations liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensNarrationRapportUniforme(CardexAuthenticationSubject subject,
                           Dossier dossier, String section) throws BusinessRuleException,
                           BusinessResourceException {
        try {
			Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensNarrationRapportUniforme(subject, dossier, section);
			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsultationNarrationIntervenantEstLeCreateur(subject, collection);
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens entre un dossier et ses suivis.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les suivis liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensSuivi(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_SUIVIS_ONGLET)){
        		Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensSuivi(subject, dossier);
        		return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens entre un dossier et ses consignations.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les consignations liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensConsignation(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_CONSIGNATIONS_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensConsignation(subject, dossier);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens entre un dossier et ses urgences.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les consignations liés
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensUrgence(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_URGENCE_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensUrgence(subject, dossier);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens entre un dossier et ses photos.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les photos liérs
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensPhoto(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_PHOTOS_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensPhoto(subject, dossier);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstAssigne(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Recherche des liens entre un dossier et ses photos.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les photos liérs
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensPieceJointe(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_PIECES_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensPieceJointe(subject, dossier);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheIntervenantEstAssigne(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Ajout d'un lien entre un véhicule et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param vehicule Le véhicule a lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienVehicule(CardexAuthenticationSubject subject,
                            Dossier dossier,
                            Vehicule vehicule) throws BusinessRuleException,
                            BusinessResourceException
    {
        try {
        	find(subject, dossier);
        	FabriqueFacade.getVehiculeSessionFacade().find(subject, vehicule);
        	FabriqueCardexDAO.getInstance().getDossierDAO().addLienVehicule(subject, dossier, vehicule);
        } catch (DAOException dae) {
            handleDAOException(dae);
        } catch (BusinessException e) {
			throw new BusinessResourceException(e);
		}
    }

    /**
     * Ajout d'un lien entre une société et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param societe La société à lier
     * @throws BusinessRuleException 
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     * @throws BusinessException 
     */
    public BusinessRuleExceptionHandle addLienSociete(CardexAuthenticationSubject subject,
                           Dossier dossier,
                           Societe societe) throws BusinessRuleException, BusinessException {
        BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
        
        try {
            Dossier dossierOrigine = find(subject, dossier);
            Societe findSociete = FabriqueCardexDAO.getInstance().getSocieteDAO().find(subject, societe);
            findSociete.setRole(societe.getRole());
            dossierOrigine.setLien(findSociete.getCle());
            dossierOrigine.setLienSite(findSociete.getSite());
            FabriqueFacade.getSocieteSessionFacade().find(subject, findSociete);
            
            (new AjouterLienSocieteDossierBusinessValidationFabrique(subject, findSociete)).executer(dossierOrigine);
            
            FabriqueCardexDAO.getInstance().getDossierDAO().addLienSociete(subject, dossierOrigine, findSociete);
        	
        } catch (DAOException dae) {
            handleDAOException(dae);
        } /*catch (BusinessException e) {
           throw new BusinessResourceException(e);
        }*/
        return businessRuleExceptionHandle;
    }

    /**
     * Ajout d'un lien entre un sujet et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param sujet Le sujet à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienSujet(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Sujet sujet) throws BusinessRuleException,
                                             BusinessResourceException
    {
        try {
        	find(subject, dossier);
        	FabriqueFacade.getSujetSessionFacade().find(subject, sujet);
        	new ViderSujetInteretDossierSujetInteret().executer(dossier);
        	
        	FabriqueCardexDAO.getInstance().getDossierDAO().addLienSujet(subject, dossier, sujet);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Ajout d'un lien entre une photo et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param photo La photo à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public Photo addLienPhoto(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Photo photo) throws BusinessRuleException,
                                             BusinessException
    {
        try {
        	find(subject, dossier);
            return FabriqueCardexDAO.getInstance().getDossierDAO().addLienPhoto(subject,dossier,photo);
        } catch (DAOException dae) {
			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle("erreur.ajout.document");
			throw businessRuleExceptionHandle.getBusinessException();
        }
    }

    /**
     * Ajout d'un lien entre une pièce jointe et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param pieceJointe La pièce jointe à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessException 
     */
    public Photo addLienPieceJointe(CardexAuthenticationSubject subject,
            Dossier dossier,
            Photo pieceJointe) throws BusinessRuleException, BusinessException {
        try {
            find(subject, dossier);
            (new AjoutPieceJointeBusinessValidationFabrique(subject,dossier)).executer(pieceJointe);
            return FabriqueCardexDAO.getInstance().getDossierDAO().addLienPhoto(subject, dossier, pieceJointe);
        }
        catch (DAOException dae) {
            BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle("erreur.ajout.document");
            throw businessRuleExceptionHandle.getBusinessException();
        }
    }
    
    /**
     * Ajout d'un lien entre un suivi et un dossier .
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param suivi Le suivi à lier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void addLienSuivi(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Suivi suivi) throws BusinessRuleException, BusinessException
    {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, suivi);
        	find(subject, dossier);
        	GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, suivi, GlobalConstants.ActionSecurite.AJOUT);
        	FabriqueCardexDAO.getInstance().getDossierDAO().addLienSuivi(subject, dossier, suivi);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Ajout d'un lien entre une consignation et un dossier .
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param consignation La consignation à lier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void addLienConsignation(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Consignation consignation) throws BusinessRuleException, BusinessException
    {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, consignation);
        	GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, consignation, GlobalConstants.ActionSecurite.AJOUT);
        	FabriqueCardexDAO.getInstance().getDossierDAO().addLienConsignation(subject, dossier, consignation);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Ajout d'un lien entre un service d'urgence et un dossier .
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param consignation La consignation à lier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public void addLienUrgence(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Urgence urgence) throws BusinessRuleException, BusinessException
    {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, urgence);
        	GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, urgence, GlobalConstants.ActionSecurite.AJOUT);
        	(new UrgenceDossierBusinessValidationFabrique(subject, urgence)).executer(dossier);
        	FabriqueCardexDAO.getInstance().getDossierDAO().addLienUrgence(subject, dossier, urgence);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Ajout d'un lien entre une inscription et un dossier .
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param inscription L'inscription à lier
     * @throws BusinessRuleException
     * @throws BusinessException
     */ 
    public void addLienInscription(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Inscription inscription) throws BusinessRuleException, BusinessException
    {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, inscription);
            
        	find(subject, dossier);
        	GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, inscription, GlobalConstants.ActionSecurite.AJOUT);
            
        	FabriqueCardexDAO.getInstance().getDossierDAO().addLienInscription(subject, dossier, inscription);
            Dossier newDossier = FabriqueCardexDAO.getInstance().getDossierDAO().find(subject,dossier);
            newDossier.setDateDebut(inscription.getDateDebut());
            newDossier.setDateFin(inscription.getDateFin());
            newDossier.setPeriode(inscription.getPeriode());
            FabriqueCardexDAO.getInstance().getDossierDAO().update(subject,newDossier);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Destruction d'un lien entre une inscription et un dossier .
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param inscription L'inscription à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienInscription(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Inscription inscription) throws BusinessRuleException,
                                             BusinessResourceException
    {
        try {
        	find(subject, dossier);
        	findInscription(subject, inscription);        	
        	FabriqueCardexDAO.getInstance().getDossierDAO().deleteLienInscription(subject, dossier, inscription);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Mise à jour des liens entre jeux et un dossier .
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param jeux Les jeux à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLiensJeu(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Jeux jeux) throws BusinessRuleException,
                                             BusinessResourceException
    {
        try {
        	find(subject, dossier);
        	FabriqueCardexDAO.getInstance().getDossierDAO().updateLiensJeu(subject, dossier, jeux);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    /**
     * Mise à jour des liens du partage de dossiers.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param jeux Les intervenants à lier
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLiensPartage(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Partage partage) throws BusinessRuleException,
                                             BusinessResourceException
    {
        try {
        	find(subject, dossier);
        	FabriqueCardexDAO.getInstance().getDossierDAO().updateLiensPartage(subject, dossier, partage);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

    public void updateLiensSousCategorie(CardexAuthenticationSubject subject, SousCategoriesVO sousCategoriesVO) throws BusinessRuleException, BusinessException{
    	try {
    	    Dossier dossier = find(subject, new DossierVO(sousCategoriesVO.getLien(), sousCategoriesVO.getLienSite()));
    		sousCategoriesVO.setType(dossier.getType());
    		BusinessRulesValidator.getInstance().checkBusinessRules(subject, sousCategoriesVO);

            Set<SousCategorieVO> ancienneSC = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensSousCategories(subject, dossier).getSousCategories();
            Set<SousCategorieVO> nouvelleSC = sousCategoriesVO.getSousCategories();

            Collection<SousCategorieVO> intersection = CollectionUtils.intersection(ancienneSC, nouvelleSC);
            Collection<SousCategorieVO> ajouterSC = CollectionUtils.subtract(nouvelleSC, intersection);
            Collection<SousCategorieVO> retirerSC = CollectionUtils.subtract(ancienneSC, intersection);

            for(SousCategorieVO critereSousCategorieVO:ajouterSC){
                critereSousCategorieVO.setCleDossier(dossier.getCle());
                critereSousCategorieVO.setSiteDossier(dossier.getSite());
                FabriqueCardexDAO.getInstance().getSousCategorieDAO().ajouter(subject, critereSousCategorieVO);
            }

            for(SousCategorieVO critereSousCategorieVO:retirerSC)
                FabriqueCardexDAO.getInstance().getSousCategorieDAO().supprimer(subject, critereSousCategorieVO);
            
    	} catch (DAOException dae) {
    		handleDAOException(dae);
    	}
	}
        
    public List<Dossier> rapportAmbulance(CardexAuthenticationSubject subject, AmbulanceDossierRapportVO ambulanceDossierRapportVO) throws BusinessRuleException, BusinessException{
    	try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, ambulanceDossierRapportVO);
            GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateurRechercheDossier(subject, ambulanceDossierRapportVO);
    		
    		return FabriqueCardexDAO.getInstance().getDossierDAO().rapportAmbulance(subject, ambulanceDossierRapportVO);
    	} catch (DAOException dae) {
    		handleDAOException(dae);
    	}
    	return null;
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
                bre.setBusinessRule(DossierBusinessRuleException.DOSSIER_RELIE_A_LUI_MEME);
                throw bre;
            }
            if (sqlException.getMessage().indexOf("ORA-20002") != -1) {
                bre.setBusinessRule(DossierBusinessRuleException.DOSSIER_RELIE_PLUS_UNE_FOIS);
                throw bre;
            }
          }
          throw new BusinessResourceException(daoe);
    }

	public void validerImpressionDossier(CardexAuthenticationSubject subject, Dossier dossier) throws BusinessRuleException, BusinessException {
		Dossier dossierFind = find(subject, dossier);
		validerDossier(dossierFind);
	}

	private void validerDossier(Dossier dossier) throws BusinessRuleException, BusinessException,
			BusinessResourceException {
		try{
			CardexAuthenticationSubject subject = AutentificationCardex.construireCardexAuthenticationSubjectSystem();
			
	        Collection inscriptions = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensInscription(subject,dossier);
	        Iterator it = inscriptions.iterator();
	        while (it.hasNext()){
	        	dossier.addInscription((Inscription)it.next());
	        }
	
	        Collection sujets = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensSujet(subject,dossier);
	        it = sujets.iterator();
	        while (it.hasNext()){
	        	dossier.addSujet((Sujet)it.next());
	        }
	
	        Collection narrations = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensNarration(subject,dossier);
	        it = narrations.iterator();
	        while (it.hasNext()){
	        	dossier.addNarration((Narration)it.next());
	        }
	
	        SousCategoriesVO sousCategoriesVO = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensSousCategories(subject,dossier);
	        dossier.setSousCategories(sousCategoriesVO.getSousCategories());
	        
	        BusinessRulesValidator.getInstance().checkBusinessRules(subject, dossier);
		} catch (DAOException dae) {
			handleDAOException(dae);
		}
	}

	public List<LiaisonBilletSocieteVO> obtenirLienDossierSocietePresentsBillet(CardexAuthenticationSubject subject, Dossier dossierLien) throws BusinessResourceException, BusinessRuleException {
		try {
			return FabriqueCardexDAO.getInstance().getDossierDAO().obtenirLienDossierSocietePresentsBillet(subject, dossierLien);
		} catch (DAOException e) {
			handleDAOException(e);
		}
		return null;
	}


	public List<LiaisonBilletSocieteVO> obtenirLienDossierSocieteRequisBillet(CardexAuthenticationSubject subject, Dossier dossierLien) throws BusinessResourceException, BusinessRuleException {
		try {	
			return (List<LiaisonBilletSocieteVO>) FabriqueCardexDAO.getInstance().getDossierDAO().obtenirLienDossierSocieteRequisBillet(subject, dossierLien);
		} catch (DAOException e) {
			handleDAOException(e);
		}
		return null;
	}
	
    public void validerRapport(CardexAuthenticationSubject subject, CriteresRechercheDossier criteresRechercheDossier) throws BusinessRuleException, BusinessException{
        GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateurRechercheDossier(subject, criteresRechercheDossier);
    	BusinessRulesValidator.getInstance().checkBusinessRules(subject, criteresRechercheDossier);
    }

    /**
     * Recherche des liens entre un dossier et les évaluations du Comité de vigilance.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet lié
     * @return  Les évaluations liées
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensEvaluation(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessRuleException,
                           BusinessResourceException
    {
        try {
        	
        	if (GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_EVALUATIONS_ONGLET)){
    			Collection collection = FabriqueCardexDAO.getInstance().getDossierDAO().findLiensEvaluation(subject, dossier);
    			return GestionnaireSecuriteCardex.validerEtFiltrerSecuriteRechercheSansSecuritePredicate(subject, collection);
        	}else
        		return new ArrayList();
        } catch (DAOException dae) {
            handleDAOException(dae);return null;
        }
    }

    /**
     * Ajout d'un lien entre une évaluation et un dossier .
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param evaluation L'évaluation à lier
     * @throws BusinessRuleException
     * @throws BusinessException
     */
    public Evaluation addLienEvaluation(CardexAuthenticationSubject subject,
    					 Dossier dossier,
                         Evaluation evaluation) throws BusinessRuleException, BusinessException
    {
        try {
        	BusinessRulesValidator.getInstance().checkBusinessRules(subject, evaluation);
        	GestionnaireSecuriteCardex.validerSecuriteEntreeUtilisateur(subject, evaluation, GlobalConstants.ActionSecurite.AJOUT);
        	evaluation =  FabriqueCardexDAO.getInstance().getDossierDAO().addLienEvaluation(subject, dossier, evaluation);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
        return evaluation;
    }

    /**
     * Suppression d'un lien entre une évaluation et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param evaluation L'évaluation à supprimer
     *
     * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienEvaluation(CardexAuthenticationSubject subject,
    						 Dossier dossier,
                             Evaluation evaluation) throws BusinessRuleException,
                             BusinessResourceException {
        try {
        	find(subject, dossier);
        	FabriqueFacade.getEvaluationSessionFacade().find(subject, evaluation);
        	FabriqueCardexDAO.getInstance().getDossierDAO().deleteLienEvaluation(subject, dossier, evaluation);
        } catch (DAOException dae) {
            handleDAOException(dae);
        }
    }

   /**
    * Mise à jour d'un lien entre une évaluation et un dossier.
    *
    * @param subject Le sujet qui effectue le lien
    * @param adresse L'évaluation à modifier
	 * @throws BusinessRuleException
	 * @throws BusinessException
    */
   public void updateLienEvaluation(CardexAuthenticationSubject subject,
   		Evaluation evaluation) throws BusinessRuleException, BusinessException {
       try {
       	BusinessRulesValidator.getInstance().checkBusinessRules(subject, evaluation);
       	Evaluation evaluationSource = FabriqueFacade.getEvaluationSessionFacade().find(subject, evaluation);
           GestionnaireSecuriteCardex.validerSecuriteModificationSansSecuritePredicate(subject, evaluationSource, evaluation);
           
           //if (GestionnaireSecuriteCardex.isModificiation(evaluationSource, evaluation))
           	FabriqueCardexDAO.getInstance().getDossierDAO().updateLienEvaluation(subject, new DossierVO(), evaluation);
       } catch (DAOException dae) {
           handleDAOException(dae);
       }
   }
    
   /**
    * Chargement d'un dossier à partir de la recherche directe.
    *
    * Cette méthode sert également à sécuriser l'accès.
    *
    * @param subject Le sujet qui recherche un sujet
    * @param criteria Les critères de recherche
    *
    * @return Le dossier recherché
    *
    * @throws BusinessRuleException si une règle d'affaire n'est pas respectée
    * @throws BusinessResourceException si une erreur système survient
    */
   public Dossier rechercheDirecte(CardexAuthenticationSubject subject,
		   Dossier criteria) throws BusinessRuleException,
                       BusinessResourceException {
       try {
    	   Dossier dossier = FabriqueCardexDAO.getInstance().getDossierDAO().rechercheDirecte(subject, criteria);
           GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulterDossierIntervenantEstLeCreateur(subject, dossier);
           return dossier;
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
		   Dossier criteria) throws BusinessRuleException,
                       BusinessResourceException {
       try {
       	FabriqueCardexDAO.getInstance().getDossierDAO().ajoutAcces(subject, criteria);
       } catch (DAOException dae) {
           handleDAOException(dae);
       }
   }


}

