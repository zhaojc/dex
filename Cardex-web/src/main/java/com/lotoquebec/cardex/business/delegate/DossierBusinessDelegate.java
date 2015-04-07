/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.delegate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        LoggerFactory.getLogger((this.getClass()));

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
     * Cr�ation d'un dossier
     *
     * @param subject Le sujet qui cr�� le dossier
     * @param info Le dossier � cr�er
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * D�termine si un dossier est avec inscription.
     *
     * @param subject Le sujet qui cr�� le dossier
     * @param info Le dossier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param criteria Les crit�res de recherche
     *
     * @return Le dossier recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Recherche d'une entr�e de journal pour consultation � l'�cran
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return Le dossier recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Consultation � l'�cran d'une inscription.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return Le dossier recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Consultation � l'�cran du partage de dossier � partir de l'onglet Partage
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return Le dossier recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param criteria Les crit�res de recherche
     *
     * @return Le dossier recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Recherche d'un dossier (avec �criture d'un audit dans AC_ACCES)
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return Le dossier recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Recherche des dossiers cr��s dans les derni�res 48 heures
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Recherche des entr�es de la journ�e pour afficher par d�faut dans l'�cran 
     * de recherche des entr�es du journal de surveillance.
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Recherche du nombre de dossiers retourn�s par une recherche
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Le nombre de dosiers recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Recherche des entr�es du journal de surveillance
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dossiers recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Recherche des entr�es du journal de surveillance pour les rapports.
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dossiers recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Mise � jour d'un dossier
     *
     * @param subject Le sujet qui modifie le dossier
     * @param info Le dossier � modifier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param narration La narration � supprimer
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Mise � jour d'une inscription
     *
     * @param subject Le sujet qui modifie le dossier
     * @param info L'inscription � modifier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Suppression des dossiers de confidentialit� 8.
     *
     * @param subject Le sujet qui modifie le dossier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier � lier
     * @param narration La narration � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier � lier
     * @param narration La narration � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param narration La narration � supprimer
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param suivi La suivi � supprimer
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param suivi La consignation � supprimer
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param suivi La consignation � supprimer
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param photo La photon � supprimer
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param narration La narration � supprimer
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param suivi La suivi � supprimer
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Modification du r�le d'un lien entre un dossier et une soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi � supprimer
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Mise � jour d'un lien entre une consignation et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi � supprimer
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Mise � jour d'un lien entre un service d'urgence et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi � supprimer
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Approbation d'une narration li�e � un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration � aprouver
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Approbation d'une suivi li�e � un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi � aprouver
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Approuver les sous cat�gories
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
     * Approbation d'une consignation li�e � un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La consignation � aprouver
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier � lier
     * @param addedDossier Le 2e dossier � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier � lier
     * @param addedDossier Le 2e dossier � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Destruction d'un lien entre un dossier et un v�hicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier lier
     * @param sujet Le v�hicule lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier li�
     * @return  Les dossiers li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Recherche des liens soci�t�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier li�
     * @return  Les sujets li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier li�
     * @return  Les sujets li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Recherche des liens v�hicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier li�
     * @return  Les sujets li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier li�
     * @return  Les inscriptions li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier li�
     * @return  Les sujets li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier li�
     * @return  Les sujets li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier li�
     * @return  Les narrations li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier li�
     * @return  Les narrations li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Recherche des liens entre un dossier et ses narrations pour un rapport uniformis�.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier li�
     * @return  Les narrations li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier li�
     * @return  Les suivis li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier li�
     * @return  Les consignations li�es
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier li�
     * @return  Les urgences li�es
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier li�
     * @return  Les photos li�es
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier li�
     * @return  Les photos li�es
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Ajout d'un lien entre un v�hicule et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier � li�
     * @param vehicule Le v�hicule � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
					// on ne prends pas cette exception pour pouvoir garder la premi�re
					businessRuleExceptionHandle.add("cardex_lien_existe");
				}
				throw businessRuleExceptionHandle.getBusinessException();
        	}else
        		throw handleDossierBusinessRuleException(e);		
        }
    }

    /**
     * Ajout d'un lien entre une soci�t� et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier � lier
     * @param societe La soci�t� � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
    					// on ne prends pas cette exception pour pouvoir garder la premi�re
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
     * @param dossier Le dossier � lier
     * @param sujet Le sujet � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
					// on ne prends pas cette exception pour pouvoir garder la premi�re
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
     * @param dossier Le dossier � lier
     * @param photo La photo � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Ajout d'un lien entre une pi�ce jointe et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier � lier
     * @param pieceJointe La pi�ce jointe � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier � lier
     * @param suivi Le suivi � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier � lier
     * @param suivi La consignation � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier � lier
     * @param suivi La consignation � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier � lier
     * @param inscription L'inscription � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param dossier Le dossier � lier
     * @param inscription L'inscription � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Mise � jour des liens entre jeux et un dossier .
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier � lier
     * @param jeux Les jeux � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Mise � jour des liens du partage de dossiers.
     *
     * @param subject Le sujet qui effectue le lien
     * @param dossier Le dossier � lier
     * @param jeux Les intervenants � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Recherche des dossiers partag�s de l'utilisateur
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Inscription de la date de paiement dans les billets associ�s au dossier
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les dosiers recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * doivent �tre pr�sent� � un utilisateur. Cette m�thode fait la mise en
     * correspondance entre les codes de r�gles d'affaires re�us d'une
     * BusinessRuleException et les messsages qui doivent �tre affich�
     * � un utilisateur.
     *
     * @param bre BusinessRuleException BusinessRuleException contenant
     *            les codes de r�gles d'affaires
     *
     * @return BusinessException BusinessException contenant les messages
     * d'erreurs qui doivent �tre pr�sent� � un utilisateur.
     */
    private BusinessException handleDossierBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        int                   businessRule = bre.getBusinessRule();

		log.debug("handleDossierBusinessRuleException" );
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
            throw new IllegalArgumentException("La r�gle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une r�gle support�e pour un objet d'affaire du type '"
                                               + Dossier.class.getName()
                                               + "'");
        }
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }

    /**
     * Construit une BusinessException contenant les messages d'erreurs qui
     * doivent �tre pr�sent� � un utilisateur. Cette m�thode fait la mise en
     * correspondance entre les codes de r�gles d'affaires re�us d'une
     * BusinessRuleException et les messsages qui doivent �tre affich�
     * � un utilisateur.
     *
     * @param bre BusinessRuleException BusinessRuleException contenant
     *            les codes de r�gles d'affaires
     *
     * @return BusinessException BusinessException contenant les messages
     * d'erreurs qui doivent �tre pr�sent� � un utilisateur.
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
            throw new IllegalArgumentException("La r�gle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une r�gle support�e pour un objet d'affaire du type '"
                                               + Dossier.class.getName()
                                               + "'");
        }
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }


    /**
     * Construit une BusinessException contenant les messages d'erreurs qui
     * doivent �tre pr�sent� � un utilisateur. Cette m�thode fait la mise en
     * correspondance entre les codes de r�gles d'affaires re�us d'une
     * BusinessRuleException et les messsages qui doivent �tre affich�
     * � un utilisateur.
     *
     * @param bre BusinessRuleException BusinessRuleException contenant
     *            les codes de r�gles d'affaires
     *
     * @return BusinessException BusinessException contenant les messages
     * d'erreurs qui doivent �tre pr�sent� � un utilisateur.
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
            throw new IllegalArgumentException("La r�gle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une r�gle support�e pour un objet d'affaire du type '"
                                               + Dossier.class.getName()
                                               + "'");
        }
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }

	/**
	 * Construit une BusinessException contenant les messages d'erreurs qui
	 * doivent �tre pr�sent� � un utilisateur. Cette m�thode fait la mise en
	 * correspondance entre les codes de r�gles d'affaires re�us d'une
	 * BusinessRuleException et les messsages qui doivent �tre affich�
	 * � un utilisateur.
	 *
	 * @param bre BusinessRuleException BusinessRuleException contenant
	 *            les codes de r�gles d'affaires
	 *
	 * @return BusinessException BusinessException contenant les messages
	 * d'erreurs qui doivent �tre pr�sent� � un utilisateur.
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
			throw new IllegalArgumentException("La r�gle d'affaire '"
											   + businessRule
											   + "' n'est pas une r�gle support�e pour un objet d'affaire du type '"
											   + Dossier.class.getName()
											   + "'");
		}
		messages.addMessage(message);
		be.setBusinessMessageResult(messages);
		return be;
	}

    /**
     * Construit une BusinessException contenant les messages d'erreurs qui
     * doivent �tre pr�sent� � un utilisateur. Cette m�thode fait la mise en
     * correspondance entre les codes de r�gles d'affaires re�us d'une
     * BusinessRuleException et les messsages qui doivent �tre affich�
     * � un utilisateur.
     *
     * @param bre BusinessRuleException BusinessRuleException contenant
     *            les codes de r�gles d'affaires
     *
     * @return BusinessException BusinessException contenant les messages
     * d'erreurs qui doivent �tre pr�sent� � un utilisateur.
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
            throw new IllegalArgumentException("La r�gle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une r�gle support�e pour un objet d'affaire du type '"
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
     * Ajout d'un lien entre une �valuation et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le suejt � lier
     * @param evaluation L'�valuation � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Suppression d'un lien entre une �valuation et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi L'�valuation � supprimer
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Recherche des liens �valuation.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les adresse li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * Mise � jour d'un lien entre une �valuation et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param adresse L'�valuation � mettre � jour
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
   
    //Recherche directe d'un dossier � partir du menu principal
    public Dossier rechercheDirecte(CardexAuthenticationSubject subject, Dossier dossier) throws BusinessException, BusinessResourceException {

		try {
			return dossierSessionFacade.rechercheDirecte(subject, dossier);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}

    //Ajout d'une entr�e dans la table des acc�s
    public void ajoutAcces(CardexAuthenticationSubject subject, Dossier dossier) throws BusinessException, BusinessResourceException {

		try {
			dossierSessionFacade.ajoutAcces(subject, dossier);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}

}

