/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.delegate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

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
import com.lotoquebec.cardex.business.exception.CriteresRechercheDossierBusinessRuleException;
import com.lotoquebec.cardex.business.exception.DossierBusinessRuleException;
import com.lotoquebec.cardex.business.exception.InscriptionBusinessRuleException;
import com.lotoquebec.cardex.business.exception.NarrationBusinessRuleException;
import com.lotoquebec.cardex.business.exception.SuiviBusinessRuleException;
import com.lotoquebec.cardex.business.facade.BilletSessionFacade;
import com.lotoquebec.cardex.business.facade.DossierSessionFacade;
import com.lotoquebec.cardex.business.facade.SocieteSessionFacade;
import com.lotoquebec.cardex.business.facade.SujetSessionFacade;
import com.lotoquebec.cardex.business.facade.UrgenceSessionFacade;
import com.lotoquebec.cardex.business.facade.VehiculeSessionFacade;
import com.lotoquebec.cardex.business.vo.BilletVO;
import com.lotoquebec.cardex.business.vo.CriteresRechercheDossierVO;
import com.lotoquebec.cardex.business.vo.SousCategorieVO;
import com.lotoquebec.cardex.business.vo.SousCategoriesVO;
import com.lotoquebec.cardex.business.vo.rapport.AmbulanceDossierRapportVO;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleAbreviationSQLListeCache;
import com.lotoquebec.cardexCommun.log.LoggerCardex;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Le DossierBusinessDelegate offre les
 * services d'affaires concernant l'objet
 * dossier.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.38 $, $Date: 2002/04/12 19:42:03 $
 */
public class DossierBusinessDelegate extends BusinessDelegate {
    DossierSessionFacade dossierSessionFacade;
    SujetSessionFacade sujetSessionFacade;
    VehiculeSessionFacade vehiculeSessionFacade;
    SocieteSessionFacade societeSessionFacade;
    BilletSessionFacade billetSessionFacade;
    UrgenceSessionFacade urgenceSessionFacade;

	/**
	 * L'instance du gestionnaire de journalisation.
	 */
    private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    /**
     * Construit une instance de DossierBusinessDelegate
     */
    public DossierBusinessDelegate() {
        this.dossierSessionFacade = new DossierSessionFacade();
        this.sujetSessionFacade = new SujetSessionFacade();
        this.vehiculeSessionFacade = new VehiculeSessionFacade();
        this.societeSessionFacade = new SocieteSessionFacade();
        this.billetSessionFacade = new BilletSessionFacade();
        this.urgenceSessionFacade = new UrgenceSessionFacade();
    }

    /**
     * Création d'un dossier
     *
     * @param subject Le sujet qui créé le dossier
     * @param info Le dossier à créer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Dossier create(CardexAuthenticationSubject subject,
                       Dossier info) throws BusinessException,
                                            BusinessResourceException {
        try {
            return dossierSessionFacade.create(subject, info);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Détermine si un dossier est avec inscription.
     *
     * @param subject Le sujet qui créé le dossier
     * @param info Le dossier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public boolean isAvecInscription(CardexAuthenticationSubject subject,
                       Dossier info) throws BusinessException,
                                            BusinessResourceException {
        try {
            return dossierSessionFacade.isAvecInscription(subject, info);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche d'un dossier (sans audit, lors de relecture du dossier)
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return Le dossier recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Dossier find(CardexAuthenticationSubject subject,
                        Dossier criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return dossierSessionFacade.find(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }
    
	/**
     * Recherche d'une entrée de journal pour consultation à l'écran
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return Le dossier recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Journal findJournal(CardexAuthenticationSubject subject,
                        Journal criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return dossierSessionFacade.findJournal(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }
    
	/**
     * Consultation à l'écran d'une inscription.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return Le dossier recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Inscription findInscription(CardexAuthenticationSubject subject,
                        Inscription criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return dossierSessionFacade.findInscription(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }
    
	/**
     * Consultation à l'écran du partage de dossier à partir de l'onglet Partage
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return Le dossier recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Partage ouvrirPartage(CardexAuthenticationSubject subject,
                        Dossier criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return dossierSessionFacade.ouvrirPartage(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }
    
    /**
     * Recherche des changements d'un dossier.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return Le dossier recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List audit(CardexAuthenticationSubject subject,
                        Dossier criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return dossierSessionFacade.audit(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }    

    /**
     * Recherche d'un dossier (avec écriture d'un audit dans AC_ACCES)
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return Le dossier recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Dossier findAcces(CardexAuthenticationSubject subject,
                        Dossier criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return dossierSessionFacade.findAcces(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
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
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Dossier> selectDefault(CardexAuthenticationSubject subject,
                                           CriteresRechercheDossier criteria) throws BusinessException,
                                           BusinessResourceException {
        try {
            return dossierSessionFacade.selectDefault(subject, criteria);
        }catch (BusinessRuleException e) {
            throw handleCriteresRecherDossierBusinessRuleException(e);
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
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Journal> selectDefaultJournal(CardexAuthenticationSubject subject,
                                           CriteresRechercheJournal criteria) throws BusinessException,
                                           BusinessResourceException {
        try {
            return dossierSessionFacade.selectDefaultJournal(subject,
                                                      criteria);
        }catch (BusinessRuleException e) {
            throw handleCriteresRecherDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche de dossiers
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les dosiers recherchés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Dossier> select(CardexAuthenticationSubject subject,
                                    CriteresRechercheDossier criteria) throws BusinessException,
                                    BusinessResourceException {
        try {
            return dossierSessionFacade.select(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleCriteresRecherDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche du nombre de dossiers retournés par une recherche
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Le nombre de dosiers recherchés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public int nombreDossiers(CardexAuthenticationSubject subject,
                                    CriteresRechercheDossier criteria) throws BusinessException,
                                    BusinessResourceException {
        try {
            return dossierSessionFacade.nombreDossiers(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleCriteresRecherDossierBusinessRuleException(e);
        }
    }
    
    /**
     * Recherche des entrées du journal de surveillance
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les dossiers recherchés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Journal> selectJournal(CardexAuthenticationSubject subject,
                                    CriteresRechercheJournal criteria) throws BusinessException,
                                    BusinessResourceException {
        try {
            return dossierSessionFacade.selectJournal(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleCriteresRecherDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche des entrées du journal de surveillance pour les rapports.
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les dossiers recherchés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
/*    public ValueListIterator selectRapportJournal(CardexAuthenticationSubject subject,
                                    CriteresRechercheJournal criteria) throws BusinessException,
                                    BusinessResourceException {
        try {
            return dossierSessionFacade.selectRapportJournal(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleCriteresRecherDossierBusinessRuleException(e);
        }
    }
*/
    public List<BusinessMessage> preSauvegardeMessage(CardexAuthenticationSubject subject,Dossier info) throws BusinessException, BusinessResourceException {
		try {
			return dossierSessionFacade.preSauvegardeMessage(subject, info);
		} catch (BusinessRuleException e) {
			throw handleDossierBusinessRuleException(e);
		}
	}
    
    /**
     * Mise à jour d'un dossier
     *
     * @param subject Le sujet qui modifie le dossier
     * @param info Le dossier à modifier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void update(CardexAuthenticationSubject subject,
                       Dossier info) throws BusinessException,
                                            BusinessResourceException {
        try {
            dossierSessionFacade.update(subject, info);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Mise  d'un lien entre un journal et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLienJournal(CardexAuthenticationSubject subject,
                             Narration narration) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.updateLienJournal(subject, narration);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }
    
    /**
     * Mise à jour d'une inscription
     *
     * @param subject Le sujet qui modifie le dossier
     * @param info L'inscription à modifier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateInscription(CardexAuthenticationSubject subject,
                       Inscription info) throws BusinessException,
                                            BusinessResourceException {
        try {
            dossierSessionFacade.updateInscription(subject, info);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }
    
    /**
     * Suppression des dossiers de confidentialité 8.
     *
     * @param subject Le sujet qui modifie le dossier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void delete(CardexAuthenticationSubject subject) 
    			throws BusinessException, BusinessResourceException {
        try {
            dossierSessionFacade.delete(subject);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }    

    /**
     * Ajout d'un lien entre une narration et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param narration La narration à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienNarration(CardexAuthenticationSubject subject,
                             Dossier dossier,
                             Narration narration) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.addLienNarration(subject, dossier, narration);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    public void addLienNarrationApprouve(CardexAuthenticationSubject subject,
            Dossier dossier,
            Narration narration) throws BusinessException,
            BusinessResourceException {
		try {
			dossierSessionFacade.addLienNarrationApprouve(subject, dossier,
		                             narration);
		} catch (BusinessRuleException e) {
			throw handleDossierBusinessRuleException(e);
		}
	}    
    
    /**
     * Ajout d'un lien entre une narration et un dossier lors d'une sauvegarde automatique.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param narration La narration à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    /*public Narration addLienNarrationAuto(CardexAuthenticationSubject subject,
                             Dossier dossier,
                             Narration narration) throws BusinessException,
                             BusinessResourceException {
        try {
            return dossierSessionFacade.addLienNarrationAuto(subject, dossier,
                                              narration);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }*/

    /**
     * Mise  d'un lien entre une narration et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienNarration(CardexAuthenticationSubject subject,
                             Dossier dossier,
                             Narration narration) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.deleteLienNarration(subject,dossier,narration);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Suppression d'un lien entre une suivi et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienSuivi(CardexAuthenticationSubject subject,
                             Dossier dossier,
                             Suivi suivi) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.deleteLienSuivi(subject,dossier,suivi);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Suppression d'un lien entre une consignation et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La consignation à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienConsignation(CardexAuthenticationSubject subject,
                             Dossier dossier,
                             Consignation consignation) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.deleteLienConsignation(subject,dossier,consignation);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Suppression d'un lien entre un service d'urgence et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La consignation à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienUrgence(CardexAuthenticationSubject subject,
                             Dossier dossier,
                             Urgence urgence) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.deleteLienUrgence(subject,dossier,urgence);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Suppression d'un lien entre une photo et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param photo La photon à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienPhoto(CardexAuthenticationSubject subject,
                             Dossier dossier,
                             Photo photo) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.deleteLienPhoto(subject,dossier,photo);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Mise  d'un lien entre une narration et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLienNarration(CardexAuthenticationSubject subject,
                             Narration narration) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.updateLienNarration(subject,narration);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Mise  d'un lien entre une suivi et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLienSuivi(CardexAuthenticationSubject subject,
                             Suivi suivi) throws BusinessException,
                             BusinessResourceException {
        try {
        	assignerAbreviationSecteur(subject, suivi);
            dossierSessionFacade.updateLienSuivi(subject,suivi);
        } catch (BusinessRuleException e) {
            throw handleSuiviBusinessRuleException(e);
        }
    }

    /**
     * Modification du rôle d'un lien entre un dossier et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLien(CardexAuthenticationSubject subject,
                             Dossier dossier) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.updateLien(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Mise à jour d'un lien entre une consignation et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLienConsignation(CardexAuthenticationSubject subject,
                             Consignation consignation) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.updateLienConsignation(subject,consignation);
        } catch (BusinessRuleException e) {
            throw handleSuiviBusinessRuleException(e);
        }
    }

    /**
     * Mise à jour d'un lien entre un service d'urgence et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLienUrgence(CardexAuthenticationSubject subject,
    		Dossier dossier, Urgence urgence) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.updateLienUrgence(subject,dossier,urgence);
        } catch (BusinessRuleException e) {
            throw handleSuiviBusinessRuleException(e);
        }
    }

    /**
     * Approbation d'une narration liée à un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration à aprouver
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void approuveLienNarration(CardexAuthenticationSubject subject,
                             Narration narration) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.approuveLienNarration(subject,narration);
        } catch (BusinessRuleException e) {
            throw handleNarrationBusinessRuleException(e);
        }
    }

    /**
     * Approbation d'une suivi liée à un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi à aprouver
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void approuveLienSuivi(CardexAuthenticationSubject subject,
                             Suivi suivi) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.approuveLienSuivi(subject,suivi);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Approuver les sous catégories
     * @param subject
     * @param dossier
     * @param approuver TODO
     * @throws BusinessException
     * @throws BusinessResourceException
     */
    public void modifierApprouveLienSousCategorie(CardexAuthenticationSubject subject,
    		Dossier dossier, boolean approuver) throws BusinessException,
            BusinessResourceException {
		try {
			dossierSessionFacade.modifierApprouveLienSousCategorie(subject,dossier, approuver);
		} catch (BusinessRuleException e) {
			throw handleDossierBusinessRuleException(e);
		}
	}    
    
    /**
     * Approbation d'une consignation liée à un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La consignation à aprouver
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void approuveLienConsignation(CardexAuthenticationSubject subject,
                             Consignation consignation) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.approuveLienConsignation(subject,consignation);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre deux dossiers.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param addedDossier Le 2e dossier à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public BusinessRuleExceptionHandle addLienDossier(CardexAuthenticationSubject subject,
                           Dossier dossier,
                           Dossier addedDossier) throws BusinessException,
                           BusinessResourceException {
        BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
        try {
            businessRuleExceptionHandle = dossierSessionFacade.addLienDossier(subject, dossier,
                                            addedDossier);
        } catch (BusinessRuleException e) {
        	Dossier dossierPopuler = null;
        	
			try {
				dossierPopuler = dossierSessionFacade.find(subject, addedDossier);
			} catch (Exception exp) {
				throw handleDossierBusinessRuleException(e);
			}
        	
        	if (e.getBusinessRule() == DossierBusinessRuleException.DOSSIER_RELIE_A_LUI_MEME){
				businessRuleExceptionHandle.add("cardex_lien_lui_meme", dossierPopuler.getNumeroCardex());
				
        	}else if (e.getBusinessRule() == DossierBusinessRuleException.DOSSIER_RELIE_PLUS_UNE_FOIS){
        		businessRuleExceptionHandle.add("cardex_lien_existe", dossierPopuler.getNumeroCardex());
        		
        	}else
        		throw handleDossierBusinessRuleException(e);
        	throw businessRuleExceptionHandle.getBusinessException();
        }
        return businessRuleExceptionHandle;
    }

    /**
     * Destruction d'un lien entre deux dossiers.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param addedDossier Le 2e dossier à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienDossier(CardexAuthenticationSubject subject,
                           Dossier dossier,
                           Dossier addedDossier) throws BusinessException,
                           BusinessResourceException {
        try {
            dossierSessionFacade.deleteLienDossier(subject, dossier,
                                            addedDossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Destruction d'un lien entre un dossier et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lier
     * @param sujet Le sujet lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienSujet(CardexAuthenticationSubject subject,
                           Dossier dossier,
                           Sujet sujet) throws BusinessException,
                           BusinessResourceException {
        try {
            dossierSessionFacade.deleteLienSujet(subject, dossier,
                                            sujet);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Destruction d'un lien entre un dossier et un véhicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lier
     * @param sujet Le véhicule lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienVehicule(CardexAuthenticationSubject subject,
                           Dossier dossier,
                           Vehicule vehicule) throws BusinessException,
                           BusinessResourceException {
        try {
            dossierSessionFacade.deleteLienVehicule(subject, dossier,
                                            vehicule);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Destruction d'un lien entre un dossier et un societe.
     *
     * @param subject Le societe qui effectue le lien
     * @param dossier Le dossier lier
     * @param societe Le societe lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienSociete(CardexAuthenticationSubject subject,
                           Dossier dossier,
                           Societe societe) throws BusinessException,
                           BusinessResourceException {
        try {
            dossierSessionFacade.deleteLienSociete(subject, dossier,
                                            societe);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens entre deux dossiers.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les dossiers liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensDossier(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensDossier(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les sujets liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensSociete(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensSociete(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens sujets.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les sujets liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensSujet(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensSujet(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens véhicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les sujets liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensVehicule(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensVehicule(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens inscriptions.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les inscriptions liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensInscription(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensInscription(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens jeux.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les sujets liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Jeux findLiensJeux(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensJeux(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens Partage.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les sujets liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensPartage(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensPartage(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    public SousCategoriesVO findLiensSousCategories(CardexAuthenticationSubject subject,
            Dossier dossier) throws BusinessException,
            BusinessResourceException {
		try {
			return dossierSessionFacade.findLiensSousCategories(subject, dossier);
		} catch (BusinessRuleException e) {
			throw handleDossierBusinessRuleException(e);
		}
	}
    
    /**
     * Recherche des liens entre un dossier et ses narrations.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les narrations liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensNarration(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensNarration(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    public List<BilletVO> trouverLiensBillet(CardexAuthenticationSubject subject, Dossier dossier) throws BusinessException, BusinessResourceException {
		try {
			BilletBusinessDelegate billetBusinessDelegate = new BilletBusinessDelegate();
			List<BilletVO> listeBillet =  billetSessionFacade.trouverLiensBillet(subject, dossier);
			
			for(BilletVO billetVO:listeBillet){
				billetBusinessDelegate.assignerSocieteProvenanceEtValidation(subject, billetVO);
			}
			
			return listeBillet;
		} catch (BusinessRuleException e) {
			throw handleDossierBusinessRuleException(e);
		}
	}
    
    /**
     * Recherche des liens entre un dossier et ses narrations pour un rapport.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les narrations liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensNarrationRapport(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensNarrationRapport(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens entre un dossier et ses narrations pour un rapport uniformisé.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les narrations liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensNarrationRapportUniforme(CardexAuthenticationSubject subject,
                           Dossier dossier, String section) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensNarrationRapportUniforme(subject, dossier, section);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens entre un dossier et ses suivis.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les suivis liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensSuivi(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensSuivi(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens entre un dossier et ses consignations.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les consignations liées
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensConsignation(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensConsignation(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens entre un dossier et les services d'urgence.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les urgences liées
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensUrgence(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensUrgence(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens entre un dossier et ses photos.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les photos liées
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensPhoto(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensPhoto(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens entre un dossier et ses photos.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lié
     * @return  Les photos liées
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensPieceJointe(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensPieceJointe(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre un véhicule et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lié
     * @param vehicule Le véhicule à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienVehicule(CardexAuthenticationSubject subject,
                            Dossier dossier,
                            Vehicule vehicule) throws BusinessException,
                            BusinessResourceException {
        try {
            dossierSessionFacade.addLienVehicule(subject, dossier,
                                             vehicule);
        } catch (BusinessRuleException e) {
        	
        	if (e.getBusinessRule() == DossierBusinessRuleException.DOSSIER_RELIE_PLUS_UNE_FOIS){
				BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
	
				try {
					Vehicule vehiculePopuler = vehiculeSessionFacade.find(subject, vehicule);
					businessRuleExceptionHandle.add("cardex_lien_existe", vehiculePopuler.getImmatriculation());
				} catch (Exception exp) {
					// on ne prends pas cette exception pour pouvoir garder la première
					businessRuleExceptionHandle.add("cardex_lien_existe");
				}
				throw businessRuleExceptionHandle.getBusinessException();
        	}else
        		throw handleDossierBusinessRuleException(e);		
        }
    }

    /**
     * Ajout d'un lien entre une société et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param societe La société à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public BusinessRuleExceptionHandle addLienSociete(CardexAuthenticationSubject subject,
                           Dossier dossier,
                           Societe societe) throws BusinessException,
                           BusinessResourceException {
        try {
            
            return dossierSessionFacade.addLienSociete(subject, dossier,
                                            societe);
        } catch (BusinessRuleException e) {
        	
        	if (e.getBusinessRule() == DossierBusinessRuleException.DOSSIER_RELIE_PLUS_UNE_FOIS){
    			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();

    			try {
    				Societe societePopuler = societeSessionFacade.find(subject, societe);
    				businessRuleExceptionHandle.add("cardex_lien_existe", societePopuler.getNom());
    				} catch (Exception exp) {
    					// on ne prends pas cette exception pour pouvoir garder la première
    					businessRuleExceptionHandle.add("cardex_lien_existe");
    				}
    			throw businessRuleExceptionHandle.getBusinessException();
	    	}else
	    		throw handleDossierBusinessRuleException(e);		
        }
    }

    /**
     * Ajout d'un lien entre un sujet et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param sujet Le sujet à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienSujet(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Sujet sujet) throws BusinessException,
                                             BusinessResourceException {
        try {
            dossierSessionFacade.addLienSujet(subject, dossier, sujet);
        } catch (BusinessRuleException e) {

        	if (e.getBusinessRule() == DossierBusinessRuleException.DOSSIER_RELIE_PLUS_UNE_FOIS){
	        	BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
	        	
	        	try {
	        		Sujet sujetPopuler = sujetSessionFacade.find(subject, sujet);
	        		businessRuleExceptionHandle.add("cardex_lien_existe", sujetPopuler.getNumeroFiche());
				} catch (Exception exp) {
					// on ne prends pas cette exception pour pouvoir garder la première
					businessRuleExceptionHandle.add("cardex_lien_existe");
				}
	        	throw businessRuleExceptionHandle.getBusinessException();
        	}else
        		throw handleDossierBusinessRuleException(e);		
        }
    }

    /**
     * Ajout d'un lien entre une photo et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param photo La photo à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Photo addLienPhoto(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Photo photo) throws BusinessException,
                                             BusinessResourceException {
        try {
            return dossierSessionFacade.addLienPhoto(subject, dossier, photo);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre une pièce jointe et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param pieceJointe La pièce jointe à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Photo addLienPieceJointe(CardexAuthenticationSubject subject,
            Dossier dossier,
            Photo pieceJointe) throws BusinessException,
                                BusinessResourceException {
        try {
            return dossierSessionFacade.addLienPieceJointe(subject, dossier, pieceJointe);
        }
        catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }
    
    /**
     * Ajout d'un lien entre un suivi et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param suivi Le suivi à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienSuivi(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Suivi suivi) throws BusinessException,
                                             BusinessResourceException {
        try {
        	assignerAbreviationSecteur(subject, suivi);
            dossierSessionFacade.addLienSuivi(subject, dossier, suivi);
        } catch (BusinessRuleException e) {
            throw handleSuiviBusinessRuleException(e);
        }
    }
    
    public void assignerAbreviationSecteur(CardexAuthenticationSubject subject, Suivi suivi) throws BusinessResourceException{
    	ListeCache listeCache = ListeCache.getInstance();
        String abreviationSecteurAssigne = listeCache.obtenirLabel(subject, suivi.getSecteurAssigne(), new TableValeurCleAbreviationSQLListeCache(subject,GlobalConstants.TableValeur.SECTEUR,GlobalConstants.ActionSecurite.SELECTION));
        String abreviationSecteurOrigine = listeCache.obtenirLabel(subject, suivi.getSecteurOrigine(), new TableValeurCleAbreviationSQLListeCache(subject,GlobalConstants.TableValeur.SECTEUR,GlobalConstants.ActionSecurite.SELECTION));

        suivi.setReference2(abreviationSecteurAssigne);
        suivi.setReference3(abreviationSecteurOrigine);

    }

    /**
     * Ajout d'un lien entre une consignation et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param suivi La consignation à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienConsignation(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Consignation consignation) throws BusinessException,
                                             BusinessResourceException {
        try {
            dossierSessionFacade.addLienConsignation(subject, dossier, consignation);
        } catch (BusinessRuleException e) {
            throw handleSuiviBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre un service d'urgence et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param suivi La consignation à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienUrgence(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Urgence urgence) throws BusinessException,
                                             BusinessResourceException {
        try {
            dossierSessionFacade.addLienUrgence(subject, dossier, urgence);
        } catch (BusinessRuleException e) {
            throw handleSuiviBusinessRuleException(e);
        }
    }
    
    /**
     * Ajout d'un lien entre une inscription et un dossier .
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param inscription L'inscription à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienInscription(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Inscription inscription)  throws BusinessException,
                                             BusinessResourceException
    {
        try {
            dossierSessionFacade.addLienInscription(subject, dossier, inscription);
        } catch (BusinessRuleException e) {
            throw handleInscriptionBusinessRuleException(e);
        }
    }

    /**
     * Destruction d'un lien entre une inscription et un dossier .
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param inscription L'inscription à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienInscription(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Inscription inscription)  throws BusinessException,
                                             BusinessResourceException
    {
        try {
            dossierSessionFacade.deleteLienInscription(subject, dossier, inscription);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Mise à jour des liens entre jeux et un dossier .
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param jeux Les jeux à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLiensJeu(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Jeux jeux) throws BusinessException,
                                             BusinessResourceException {
        try {
            dossierSessionFacade.updateLiensJeu(subject, dossier, jeux);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    /**
     * Mise à jour des liens du partage de dossiers.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier à lier
     * @param jeux Les intervenants à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLiensPartage(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Partage partage) throws BusinessException,
                                             BusinessResourceException {
        try {
            dossierSessionFacade.updateLiensPartage(subject, dossier, partage);
        } catch (BusinessRuleException e) {
            throw handleDossierBusinessRuleException(e);
        }
    }

    public void updateLiensSousCategorie(CardexAuthenticationSubject subject, SousCategoriesVO sousCategoriesVO) throws BusinessException, BusinessResourceException {
    	try {
    		dossierSessionFacade.updateLiensSousCategorie(subject, sousCategoriesVO);
		} catch (BusinessRuleException e) {
			throw handleDossierBusinessRuleException(e);
		}
	}

    /**
     * Recherche des dossiers partagés de l'utilisateur
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les dosiers recherchés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Dossier> recherchePartage(CardexAuthenticationSubject subject,
                                    CriteresRechercheDossier criteria) throws BusinessException,
                                    BusinessResourceException {
        try {
            return dossierSessionFacade.recherchePartage(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleCriteresRecherDossierBusinessRuleException(e);
        }
    }
    
    /**
     * Inscription de la date de paiement dans les billets associés au dossier
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les dosiers recherchés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void inscrireDatePaiement(CardexAuthenticationSubject subject,
    								 long cle,
    								 long site,
    								 String datePaiement) throws BusinessException,
                                     BusinessResourceException {
        try {
            dossierSessionFacade.inscrireDatePaiement(subject, cle, site, datePaiement);

        } catch (BusinessRuleException e) {
            throw handleCriteresRecherDossierBusinessRuleException(e);
        }
    }

    public List obtenirDossierNumeroDossier(CardexAuthenticationSubject subject, Dossier dossierOrigine, String numeroDossierOriginal) throws BusinessResourceException, BusinessException{
		List dossierRetour = new ArrayList();
		CriteresRechercheDossierVO criteresRechercheDossier = new CriteresRechercheDossierVO();
		DossierBusinessDelegate delegate = new DossierBusinessDelegate();
		String numeroDossier = StringUtils.capitalise( dossierOrigine.getNumeroDossier() );
		//dossierOrigine.setNumeroDossier( numeroDossier );
		criteresRechercheDossier.setNumeroDossier( numeroDossier );
		CardexUser cardexUser = (CardexUser)subject.getUser();
		criteresRechercheDossier.setSiteOrigine( cardexUser.getSite() );
		criteresRechercheDossier.setEntite( cardexUser.getEntite() );
		Collection results = delegate.select(subject,criteresRechercheDossier);
		
		if (results.size() > 0){
		    Iterator iter = results.iterator();
		    
		    while (iter.hasNext()) {
				Dossier dossierTrouve = (Dossier) iter.next();
				
				if (dossierTrouve.getNumeroCardex().equals( dossierOrigine.getNumeroCardex() ) == false)
					dossierRetour.add( dossierTrouve );
			}
		    return dossierRetour;

		}else if (numeroDossier.indexOf("-") == -1){
			String numeroDossierAvecTiret = "";
			// Ajout d'un '-'
			numeroDossierAvecTiret = StringUtils.left(numeroDossier, 2)+"-"+StringUtils.right(numeroDossier, numeroDossier.length()-2);
			dossierOrigine.setNumeroDossier( numeroDossierAvecTiret );
			
			return obtenirDossierNumeroDossier(subject, dossierOrigine, numeroDossierOriginal);
		}

		throw AucunDossierTrouve(numeroDossierOriginal);
    }    
    
	private BusinessException AucunDossierTrouve(String numeroDossier) throws BusinessException{
		BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
		businessRuleExceptionHandle.add("cardex_aucun_dossier_trouve", numeroDossier);
		return businessRuleExceptionHandle.getBusinessException();	
	}
	
	public void validerImpressionDossier(CardexAuthenticationSubject subject, Dossier dossier) throws BusinessException{
    	try {
    		dossierSessionFacade.validerImpressionDossier(subject, dossier);
		} catch (BusinessRuleException e) {
			throw handleDossierBusinessRuleException(e);
		}
	}
	
    public List<Dossier> rapportAmbulance(CardexAuthenticationSubject subject, AmbulanceDossierRapportVO ambulanceDossierRapportVO) throws BusinessException, BusinessResourceException {
		try {
			return dossierSessionFacade.rapportAmbulance(subject, ambulanceDossierRapportVO);
		} catch (BusinessRuleException e) {
			throw handleNarrationBusinessRuleException(e);
		}
	}  

	
    /**
     * Construit une BusinessException contenant les messages d'erreurs qui
     * doivent être présenté à un utilisateur. Cette méthode fait la mise en
     * correspondance entre les codes de règles d'affaires reçus d'une
     * BusinessRuleException et les messsages qui doivent être affiché
     * à un utilisateur.
     *
     * @param bre BusinessRuleException BusinessRuleException contenant
     *            les codes de règles d'affaires
     *
     * @return BusinessException BusinessException contenant les messages
     * d'erreurs qui doivent être présenté à un utilisateur.
     */
    private BusinessException handleDossierBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        int                   businessRule = bre.getBusinessRule();

		log.fine("handleDossierBusinessRuleException" );
        switch (businessRule) {

        case DossierBusinessRuleException.DATE_DEBUT_INVALIDE:
            message.setKey("cardex_required_date_deb");
            break;

        case DossierBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN:
            message.setKey("cardex_required_validate_date");
            break;

        case DossierBusinessRuleException.DOSSIER_RELIE_A_LUI_MEME:
            message.setKey("cardex_lien_lui_meme");
            break;

        case DossierBusinessRuleException.DOSSIER_RELIE_PLUS_UNE_FOIS:
            message.setKey("cardex_lien_existe");
            break;

        case DossierBusinessRuleException.NARRATIONS_NON_APPROUVE_DOSSIER_INACTIF:
            message.setKey("cardex_non_approuve");
            break;

        case DossierBusinessRuleException.SUIVIS_NON_APPROUVE_DOSSIER_INACTIF:
            message.setKey("cardex_non_approuve");
            break;

        case DossierBusinessRuleException.DOSSIER_SANS_INSCRIPTIONS:
            message.setKey("cardex_required_inscription");
            break;

        case DossierBusinessRuleException.DOSSIER_SANS_SUJETS:
            message.setKey("onglet_sujet");
            break;

        case DossierBusinessRuleException.MOT_DE_PASSE_INVALID:
            message.setKey("cardex_password_identique");
            break;

        case DossierBusinessRuleException.MANQUE_CONCLUSION_EVALUATION_DOSSIER_INACTIF:
            message.setKey("cardex_sans_conclusion");
            break;

        case DossierBusinessRuleException.MANQUE_DATE_FIN_EVALUATION_DOSSIER_INACTIF:
            message.setKey("date_fin_vigilance_inactif");
            break;
            
        case DossierBusinessRuleException.AUTOEXCLUSION_ESPACEJEUX:
            message.setKey("autoexclusion_espacejeux");
            break;

        case DossierBusinessRuleException.FONDE_DOSSIER_INACTIF:
            message.setKey("cardex_fonde_dossier_inactif");
            break;

        default:
            throw new IllegalArgumentException("La règle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une règle supportée pour un objet d'affaire du type '"
                                               + Dossier.class.getName()
                                               + "'");
        }
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }

    /**
     * Construit une BusinessException contenant les messages d'erreurs qui
     * doivent être présenté à un utilisateur. Cette méthode fait la mise en
     * correspondance entre les codes de règles d'affaires reçus d'une
     * BusinessRuleException et les messsages qui doivent être affiché
     * à un utilisateur.
     *
     * @param bre BusinessRuleException BusinessRuleException contenant
     *            les codes de règles d'affaires
     *
     * @return BusinessException BusinessException contenant les messages
     * d'erreurs qui doivent être présenté à un utilisateur.
     */
    private BusinessException handleSuiviBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        int                   businessRule = bre.getBusinessRule();

        switch (businessRule) {

        case SuiviBusinessRuleException.DATE_COMPLETE_INVALIDE:
            message.setKey("cardex_date_completee");
            break;

        case SuiviBusinessRuleException.DATE_PREVUE_INVALIDE:
            message.setKey("cardex_date_prevue");
            break;
            
        default:
            throw new IllegalArgumentException("La règle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une règle supportée pour un objet d'affaire du type '"
                                               + Dossier.class.getName()
                                               + "'");
        }
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }


    /**
     * Construit une BusinessException contenant les messages d'erreurs qui
     * doivent être présenté à un utilisateur. Cette méthode fait la mise en
     * correspondance entre les codes de règles d'affaires reçus d'une
     * BusinessRuleException et les messsages qui doivent être affiché
     * à un utilisateur.
     *
     * @param bre BusinessRuleException BusinessRuleException contenant
     *            les codes de règles d'affaires
     *
     * @return BusinessException BusinessException contenant les messages
     * d'erreurs qui doivent être présenté à un utilisateur.
     */
    private BusinessException handleInscriptionBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        int                   businessRule = bre.getBusinessRule();

        switch (businessRule) {

        case InscriptionBusinessRuleException.DATE_DEBUT_INVALIDE:
            message.setKey("cardex_required_date_deb");
            break;

        case InscriptionBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN:
            message.setKey("cardex_required_validate_date");
            break;

        default:
            throw new IllegalArgumentException("La règle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une règle supportée pour un objet d'affaire du type '"
                                               + Dossier.class.getName()
                                               + "'");
        }
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }

	/**
	 * Construit une BusinessException contenant les messages d'erreurs qui
	 * doivent être présenté à un utilisateur. Cette méthode fait la mise en
	 * correspondance entre les codes de règles d'affaires reçus d'une
	 * BusinessRuleException et les messsages qui doivent être affiché
	 * à un utilisateur.
	 *
	 * @param bre BusinessRuleException BusinessRuleException contenant
	 *            les codes de règles d'affaires
	 *
	 * @return BusinessException BusinessException contenant les messages
	 * d'erreurs qui doivent être présenté à un utilisateur.
	 */
	private BusinessException handleNarrationBusinessRuleException(BusinessRuleException bre)
			throws BusinessException {
		BusinessException     be = new BusinessException();
		BusinessMessageResult messages = new BusinessMessageResult();
		BusinessMessage       message = new BusinessMessage();
		int                   businessRule = bre.getBusinessRule();

		switch (businessRule) {

		case NarrationBusinessRuleException.NARRATION_TEMPS_CONSACRE:
			message.setKey("cardex_temps_consacre");
			break;

		default:
			throw new IllegalArgumentException("La règle d'affaire '"
											   + businessRule
											   + "' n'est pas une règle supportée pour un objet d'affaire du type '"
											   + Dossier.class.getName()
											   + "'");
		}
		messages.addMessage(message);
		be.setBusinessMessageResult(messages);
		return be;
	}

    /**
     * Construit une BusinessException contenant les messages d'erreurs qui
     * doivent être présenté à un utilisateur. Cette méthode fait la mise en
     * correspondance entre les codes de règles d'affaires reçus d'une
     * BusinessRuleException et les messsages qui doivent être affiché
     * à un utilisateur.
     *
     * @param bre BusinessRuleException BusinessRuleException contenant
     *            les codes de règles d'affaires
     *
     * @return BusinessException BusinessException contenant les messages
     * d'erreurs qui doivent être présenté à un utilisateur.
     */
    private BusinessException handleCriteresRecherDossierBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        int                   businessRule = bre.getBusinessRule();

        switch (businessRule) {

        case CriteresRechercheDossierBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN:
            message.setKey("cardex_required_validate_date");
            //message.addArgument();
            break;

        case CriteresRechercheDossierBusinessRuleException.PAS_DE_CRITERES_SAISIS_EN_PLUS_DES_VALEURS_PAR_DEFAULT:
            message.setKey("recherche_avertissement");
            //message.addArgument();
            break;
        default:
            throw new IllegalArgumentException("La règle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une règle supportée pour un objet d'affaire du type '"
                                               + CriteresRechercheDossier.class.getName()
                                               + "'");
        }

        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }    
    
	public void validerRapport(CardexAuthenticationSubject subject, CriteresRechercheDossier criteresRechercheDossier) throws BusinessException, BusinessResourceException {
		try {
			dossierSessionFacade.validerRapport(subject, criteresRechercheDossier);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
    }	

    /**
     * Ajout d'un lien entre une évaluation et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le suejt à lier
     * @param evaluation L'évaluation à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Evaluation addLienEvaluation(CardexAuthenticationSubject subject,
                         Dossier dossier,
                         Evaluation evaluation) throws BusinessException,
                                             BusinessResourceException {
        try {
            return dossierSessionFacade.addLienEvaluation(subject, dossier, evaluation);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Suppression d'un lien entre une évaluation et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi L'évaluation à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienEvaluation(CardexAuthenticationSubject subject,
    						 Dossier dossier,
                             Evaluation evaluation) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.deleteLienEvaluation(subject,dossier, evaluation);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens évaluation.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet lié
     * @return  Les adresse liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensEvaluation(CardexAuthenticationSubject subject,
                           Dossier dossier) throws BusinessException,
                           BusinessResourceException {
        try {
            return dossierSessionFacade.findLiensEvaluation(subject, dossier);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Mise à jour d'un lien entre une évaluation et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param adresse L'évaluation à mettre à jour
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLienEvaluation(CardexAuthenticationSubject subject,
    		Evaluation evaluation) throws BusinessException,
                             BusinessResourceException {
        try {
            dossierSessionFacade.updateLienEvaluation(subject, evaluation);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }
   
    //Recherche directe d'un dossier à partir du menu principal
    public Dossier rechercheDirecte(CardexAuthenticationSubject subject, Dossier dossier) throws BusinessException, BusinessResourceException {

		try {
			return dossierSessionFacade.rechercheDirecte(subject, dossier);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}

    //Ajout d'une entrée dans la table des accès
    public void ajoutAcces(CardexAuthenticationSubject subject, Dossier dossier) throws BusinessException, BusinessResourceException {

		try {
			dossierSessionFacade.ajoutAcces(subject, dossier);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}

}

