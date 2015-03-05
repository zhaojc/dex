package com.lotoquebec.cardex.business.delegate;

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
import com.lotoquebec.cardex.business.facade.DossierSessionFacade;
import com.lotoquebec.cardex.business.facade.FabriqueFacade;
import com.lotoquebec.cardex.business.facade.SocieteSessionFacade;
import com.lotoquebec.cardex.business.facade.SujetSessionFacade;
import com.lotoquebec.cardex.business.facade.VehiculeSessionFacade;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;

/**
 * Le VehiculeBusinessDelegate offre les
 * services d'affaires concernant l'objet
 * Véhicule.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/23 21:09:33 $
 */

public class VehiculeBusinessDelegate extends BusinessDelegate {
    VehiculeSessionFacade vehiculeSessionFacade;
    DossierSessionFacade dossierSessionFacade;
    SocieteSessionFacade societeSessionFacade;
    SujetSessionFacade sujetSessionFacade;

    /**
     * Construit une instance de VehiculeBusinessDelegate
     */
    public VehiculeBusinessDelegate() {
        this.vehiculeSessionFacade = new VehiculeSessionFacade();
        this.dossierSessionFacade = new DossierSessionFacade();
        this.societeSessionFacade = new SocieteSessionFacade();
        this.sujetSessionFacade = new SujetSessionFacade();
    }

    /**
     * Création d'un véhicule
     *
     * @param subject L'utilisateur qui crée le dossier
     * @param info Le véhicule à créer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Vehicule create(CardexAuthenticationSubject subject,
                       Vehicule info) throws BusinessException,
                                            BusinessResourceException {
        try {
            return vehiculeSessionFacade.create(subject, info);
        } catch (BusinessRuleException e) {
            throw handleCriteresRechercheVehiculeBusinessRuleException(e);
        }
    }

    /**
     * Recherche d'un vehicule
     *
     * @param subject L'utilisateur qui recherche un véhicule
     * @param criteria Les critères de recherche
     *
     * @return Le véhicule recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Vehicule find(CardexAuthenticationSubject subject,
                        Vehicule criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return vehiculeSessionFacade.find(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleCriteresRechercheVehiculeBusinessRuleException(e);
        }
    }
    
    /**
     * Recherche d'un vehicule dans l'audit des changements
     *
     * @param subject L'utilisateur qui recherche un véhicule
     * @param criteria Les critères de recherche
     *
     * @return Le véhicule recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Vehicule findAudit(CardexAuthenticationSubject subject,
                        Vehicule criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return vehiculeSessionFacade.findAudit(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleCriteresRechercheVehiculeBusinessRuleException(e);
        }
    }
    
    /**
     * Recherche d'un vehicule (avec audit dans AC_ACCES)
     *
     * @param subject L'utilisateur qui recherche un véhicule
     * @param criteria Les critères de recherche
     *
     * @return Le véhicule recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Vehicule findAcces(CardexAuthenticationSubject subject,
                        Vehicule criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return vehiculeSessionFacade.findAcces(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleCriteresRechercheVehiculeBusinessRuleException(e);
        }
    }    

    /**
     * Recherche des véhicules créés dans les dernières 48 heures
     *
     * @param subject L'utilisateur qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les véhicules recherchés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Vehicule> selectDefault(CardexAuthenticationSubject subject,
                                           CriteresRechercheVehicule criteria) throws BusinessException,
                                           BusinessResourceException {
        try {
            return vehiculeSessionFacade.selectDefault(subject,
                                                      criteria);
        }catch (BusinessRuleException e) {
            throw handleCriteresRechercheVehiculeBusinessRuleException(e);
        }
    }

    /**
     * Recherche de véhicules
     *
     * @param subject L'utilisateur qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les véhicules recherchés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Vehicule> select(CardexAuthenticationSubject subject,
                                    CriteresRechercheVehicule criteria) throws BusinessException,
                                    BusinessResourceException {
        try {
            return vehiculeSessionFacade.select(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleCriteresRechercheVehiculeBusinessRuleException(e);
        }
    }

    /**
     * Mise à jour d'un véhicule
     *
     * @param subject L'utilisateur qui modifie le véhicule
     * @param info Le véhicule à modifier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void update(CardexAuthenticationSubject subject,
                       Vehicule info) throws BusinessException,
                                            BusinessResourceException {
        try {
            vehiculeSessionFacade.update(subject, info);
        } catch (BusinessRuleException e) {
            throw handleVehiculeBusinessRuleException(e);
        }
    }


    /**
     * Ajout d'un lien entre une narration et un véhicule.
     *
     * @param subject L'utilisateur qui effectue le lien
     * @param vehicule Le véhicule à lier
     * @param narration La narration à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienNarration(CardexAuthenticationSubject subject,
                             Vehicule vehicule,
                             Narration narration) throws BusinessException,
                             BusinessResourceException {
        try {
            vehiculeSessionFacade.addLienNarration(subject, vehicule, narration);
        } catch (BusinessRuleException e) {
            throw handleVehiculeBusinessRuleException(e);
        }
    }

    /**
     * Approbation d'une narration liée à un véhicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration à approuver
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void approuveLienNarration(CardexAuthenticationSubject subject,
                             Narration narration) throws BusinessException,
                             BusinessResourceException {
        try {
            vehiculeSessionFacade.approuveLienNarration(subject,narration);
        } catch (BusinessRuleException e) {
            throw handleVehiculeBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre un véhicule et un dossier.
     *
     * @param subject L'utilisateur qui effectue le lien
     * @param vehicule Le véhicule à lier
     * @param dossier Le dossier à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienDossier(CardexAuthenticationSubject subject,
                         Vehicule vehicule,
                         Dossier dossier) throws BusinessException,
                                             BusinessResourceException {
        try {
            vehiculeSessionFacade.addLienDossier(subject, vehicule, dossier);
        } catch (BusinessRuleException e) {
        	
        	if (e.getBusinessRule() == VehiculeBusinessRuleException.VEHICULE_RELIE_PLUS_UNE_FOIS){
				BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
	
				try {
					Dossier dossierPopuler = dossierSessionFacade.find(subject, dossier);
					businessRuleExceptionHandle.add("cardex_lien_existe", dossierPopuler.getNumeroCardex());
				} catch (Exception exp) {
					// on ne prends pas cette exception pour pouvoir garder la première
					businessRuleExceptionHandle.add("cardex_lien_existe");
				}
				throw businessRuleExceptionHandle.getBusinessException();
        	}else
        		throw handleVehiculeBusinessRuleException(e);		        	
        }
    }


    /**
     * Ajout d'un lien entre une société et un véhicule.
     *
     * @param subject L'utilisateur qui effectue le lien
     * @param vehicule Le véhicule à lier
     * @param societe La société à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienSociete(CardexAuthenticationSubject subject,
                           Vehicule vehicule,
                           Societe societe) throws BusinessException,
                           BusinessResourceException {
        try {
            vehiculeSessionFacade.addLienSociete(subject, vehicule,
                                            societe);
        } catch (BusinessRuleException e) {
        	
        	if (e.getBusinessRule() == VehiculeBusinessRuleException.VEHICULE_RELIE_PLUS_UNE_FOIS){
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
        		throw handleVehiculeBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre deux vehicules.
     *
     * @param subject L'utilisateur qui effectue le lien
     * @param vehicule Le véhicule à lier
     * @param addedDossier Le 2e véhicule à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
/* Non utilisé dans la version actuelle
    public void addLienVehicule(CardexAuthenticationSubject subject,
                           Vehicule vehicule,
                           Vehicule addedVehicule) throws BusinessException,
                           BusinessResourceException {
        try {
            vehiculeSessionFacade.addLienVehicule(subject, vehicule,
                                            addedVehicule);
        } catch (BusinessRuleException e) {
            throw handleVehiculeBusinessRuleException(e);
        }
    }
*/
    /**
     * Recherche des liens entre deux vehicules.
     *
     * @param subject L'utilisateur qui effectue le lien
     * @param vehicule Le véhicule lié
     * @return  Les véhicules liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
/* Non utilisé dans la version actuelle
    public Collection findLiensVehicule(CardexAuthenticationSubject subject,
                           Vehicule vehicule) throws BusinessException,
                           BusinessResourceException {
        try {
            return vehiculeSessionFacade.findLiensVehicule(subject, vehicule);
        } catch (BusinessRuleException e) {
            throw handleVehiculeBusinessRuleException(e);
        }
    }
*/
    /**
     * Recherche des liens particularités.
     *
     * @param subject Le sujet qui effectue le lien
     * @param vehicule Le véhicule lié
     * @return  Les particularités liées
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Particularites findLiensParticularite(CardexAuthenticationSubject subject,
                           Vehicule vehicule) throws BusinessException,
                           BusinessResourceException {
        try {
            return vehiculeSessionFacade.findLiensParticularite(subject, vehicule);
        } catch (BusinessRuleException e) {
            throw handleVehiculeBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre une photo et un véhicule.
     *
     * @param subject L'utilisateur qui effectue le lien
     * @param vehicule Le véhicule à lier
     * @param photo La photo à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Photo addLienPhoto(CardexAuthenticationSubject subject,
                         Vehicule vehicule,
                         Photo photo) throws BusinessException,
                                             BusinessResourceException {
        try {
            return vehiculeSessionFacade.addLienPhoto(subject, vehicule, photo);
        } catch (BusinessRuleException e) {
            throw handleVehiculeBusinessRuleException(e);
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
    private BusinessException handleVehiculeBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        int                   businessRule = bre.getBusinessRule();

        switch (businessRule) {

        case VehiculeBusinessRuleException.VEHICULE_RELIE_A_LUI_MEME:
            message.setKey("cardex_lien_lui_meme");
            //message.addArgument();
            break;

        case VehiculeBusinessRuleException.VEHICULE_RELIE_PLUS_UNE_FOIS:
            message.setKey("cardex_lien_existe");
            //message.addArgument();
            break;

        default:
            throw new IllegalArgumentException("La règle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une règle supportée pour un objet d'affaire du type '"
                                               + Vehicule.class.getName()
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
    private BusinessException handleCriteresRechercheVehiculeBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }
    /**
     * Ajout d'un lien entre un sujet et un véhicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param societe La société à lier
     * @param sujet Le sujet à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienSujet(CardexAuthenticationSubject subject,
                           Vehicule vehicule,
                           Sujet addedSujet) throws BusinessException,
                           BusinessResourceException {
        try {
            vehiculeSessionFacade.addLienSujet(subject, vehicule,
                                            addedSujet);
        } catch (BusinessRuleException e) {
        	
        	if (e.getBusinessRule() == VehiculeBusinessRuleException.VEHICULE_RELIE_PLUS_UNE_FOIS){
				BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
	
				try {
					Sujet sujetPopuler = sujetSessionFacade.find(subject, addedSujet);
					businessRuleExceptionHandle.add("cardex_lien_existe", sujetPopuler.getNumeroFiche());
				} catch (Exception exp) {
					// on ne prends pas cette exception pour pouvoir garder la première
					businessRuleExceptionHandle.add("cardex_lien_existe");
				}
				throw businessRuleExceptionHandle.getBusinessException();
        	}else
        		throw handleVehiculeBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens sujets.
     *
     * @param subject L'utilisateur qui effectue le lien
     * @param vehicule
     * @return  Les sujets liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensSujet(CardexAuthenticationSubject subject,
                           Vehicule vehicule) throws BusinessException,
                           BusinessResourceException {
        try {
            return vehiculeSessionFacade.findLiensSujet(subject, vehicule);
        } catch (BusinessRuleException e) {
            throw handleVehiculeBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens dossiers.
     *
     * @param subject L'utilisateur qui recherche le lien
     * @param vehicule Le véhicule lié
     * @return  Les véhicules liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensDossier(CardexAuthenticationSubject subject,
                           Vehicule vehicule) throws BusinessException,
                           BusinessResourceException {
        try {
            return vehiculeSessionFacade.findLiensDossier(subject, vehicule);
        } catch (BusinessRuleException e) {
            throw handleVehiculeBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens Narration.
     *
     * @param subject Le sujet qui effectue la recherche
     * @param vehicule
     * @return  Les narrations liées
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensNarration(CardexAuthenticationSubject subject,
                           Vehicule vehicule) throws BusinessException,
                           BusinessResourceException {
        try {
            return vehiculeSessionFacade.findLiensNarration(subject, vehicule);
        } catch (BusinessRuleException e) {
            throw handleVehiculeBusinessRuleException(e);
        }
    }
    /**
     * Recherche des liens Photo.
     *
     * @param subject Le sujet qui effectue la recherche
     * @param vehicule
     * @return  Les photos liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensPhoto(CardexAuthenticationSubject subject,
                           Vehicule vehicule) throws BusinessException,
                           BusinessResourceException {
        try {
            return vehiculeSessionFacade.findLiensPhoto(subject, vehicule);
        } catch (BusinessRuleException e) {
            throw handleVehiculeBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens Societe.
     *
     * @param subject Le sujet qui effectue la recherche
     * @param vehicule
     * @return  Les sociétés liées
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensSociete(CardexAuthenticationSubject subject,
                           Vehicule vehicule) throws BusinessException,
                           BusinessResourceException {
        try {
            return vehiculeSessionFacade.findLiensSociete(subject, vehicule);
        } catch (BusinessRuleException e) {
            throw handleVehiculeBusinessRuleException(e);
        }
    }

    /**
     * Suppression d'un lien entre un dossier et un vehicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param photo Le dossier à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienDossier(CardexAuthenticationSubject subject,
                             Vehicule vehicule,
                             Dossier linked) throws BusinessException,
                             BusinessResourceException {
        try {
            vehiculeSessionFacade.deleteLienDossier(subject, vehicule, linked);
        } catch (BusinessRuleException e) {
//            throw handleBusinessRuleException(e);
        }
    }
    
   /**
     * Suppression des véhicules de confidentialité 8.
     *
     * @param subject Le sujet qui modifie le dossier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void delete(CardexAuthenticationSubject subject) 
    			throws BusinessException, BusinessResourceException {
        try {
            vehiculeSessionFacade.delete(subject);
        } catch (BusinessRuleException e) {
            throw handleVehiculeBusinessRuleException(e);
        }
    }    
    /**
     *
     * Suppression d'un lien entre une société et un vehicule
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La société à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienSociete(CardexAuthenticationSubject subject,
                             Vehicule vehicule,
                             Societe linked) throws BusinessException,
                             BusinessResourceException {
        try {
           vehiculeSessionFacade.deleteLienSociete(subject, vehicule, linked);
        } catch (BusinessRuleException e) {
//            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Suppression d'un lien entre un sujet et un vehicule
     *
     * @param subject Le sujet qui effectue le lien
     * @param photo Le sujet à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienSujet(CardexAuthenticationSubject subject,
                             Vehicule vehicule,
                             Sujet linked) throws BusinessException,
                             BusinessResourceException {
        try {
            vehiculeSessionFacade.deleteLienSujet(subject, vehicule, linked);
        } catch (BusinessRuleException e) {
//            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Suppression d'un lien entre une photo et un vehicule
     *
     * @param subject Le sujet qui effectue le lien
     * @param photo La photo à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienPhoto(CardexAuthenticationSubject subject,
                             Vehicule vehicule,
                             Photo linked) throws BusinessException,
                             BusinessResourceException {
        try {
            vehiculeSessionFacade.deleteLienPhoto(subject, vehicule, linked);
        } catch (BusinessRuleException e) {
//            throw handleBusinessRuleException(e);
        }
    }
    /**
     * Suppression d'un lien entre une narration et un vehicule
     *
     * @param subject Le sujet qui effectue le lien
     * @param photo La narration à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienNarration(CardexAuthenticationSubject subject,
                             Vehicule vehicule,
                             Narration linked) throws BusinessException,
                             BusinessResourceException {
        try {
            vehiculeSessionFacade.deleteLienNarration(subject, vehicule, linked);
        } catch (BusinessRuleException e) {
//            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Mise d'un lien entre une narration et un vehicule
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration Le lien Narration à mettre à jour
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLienNarration(CardexAuthenticationSubject subject,
                             Narration linked) throws BusinessException,
                             BusinessResourceException {
        try {
            vehiculeSessionFacade.updateLienNarration(subject, linked);
        } catch (BusinessRuleException e) {
//            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre des particularites et un véhicule avec un audit.
     *
     * @param subject Le sujet qui effectue le lien
     * @param vehicule Le véhicule à lier
     * @param particularites Les particularites à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLienParticularite(CardexAuthenticationSubject subject,
                           Vehicule vehicule,
                           Particularites particularites) throws BusinessException,
                           BusinessResourceException {
        try {
            vehiculeSessionFacade.updateLienParticularite(subject, vehicule,
                                            particularites);
        } catch (BusinessRuleException e) {
            throw handleVehiculeBusinessRuleException(e);
        }
    }

    /**
     * Recherche de l'audit des changements d'un véhicule
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les véhicules recherchés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List audit(CardexAuthenticationSubject subject,
                                           Vehicule criteria) throws BusinessException,
                                           BusinessResourceException {
        try {
            return vehiculeSessionFacade.audit(subject,criteria);
        }catch (BusinessRuleException e) {
            throw handleCriteresRechercheVehiculeBusinessRuleException(e);
        }
    }

    /**
     * Modification du rôle d'un lien entre un dossier et une véhicule.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLien(CardexAuthenticationSubject subject,
    						Vehicule vehicule) throws BusinessException,
                             BusinessResourceException {
        try {
            vehiculeSessionFacade.updateLien(subject, vehicule);
        } catch (BusinessRuleException e) {
            throw handleVehiculeBusinessRuleException(e);
        }
    }

    //Recherche directe d'un véhicule à partir du menu principal
    public Vehicule rechercheDirecte(CardexAuthenticationSubject subject, Vehicule vehicule) throws BusinessException, BusinessResourceException {

		try {
			return vehiculeSessionFacade.rechercheDirecte(subject, vehicule);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}

    //Ajout d'une entrée dans la table des accès
    public void ajoutAcces(CardexAuthenticationSubject subject, Vehicule vehicule) throws BusinessException, BusinessResourceException {

		try {
			vehiculeSessionFacade.ajoutAcces(subject, vehicule);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}
   
}