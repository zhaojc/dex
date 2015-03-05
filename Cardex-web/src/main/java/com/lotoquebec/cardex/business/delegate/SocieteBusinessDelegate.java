/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.delegate;

import java.util.Collection;
import java.util.List;

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
import com.lotoquebec.cardex.business.facade.DossierSessionFacade;
import com.lotoquebec.cardex.business.facade.FabriqueFacade;
import com.lotoquebec.cardex.business.facade.SocieteSessionFacade;
import com.lotoquebec.cardex.business.facade.SujetSessionFacade;
import com.lotoquebec.cardex.business.facade.VehiculeSessionFacade;
import com.lotoquebec.cardex.business.vo.CriteresRechercheSocieteVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;

/**
 * Le SocieteBusinessDelegate offre les services d'affaires concernant l'objet
 * sujet.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/11 19:20:33 $
 */
public class SocieteBusinessDelegate extends BusinessDelegate {
    SocieteSessionFacade societeSessionFacade;
    DossierSessionFacade dossierSessionFacade;
    VehiculeSessionFacade vehiculeSessionFacade;
    SujetSessionFacade sujetSessionFacade;

    /**
     * Construit une instance de SocieteBusinessDelegate
     */
    public SocieteBusinessDelegate() {
        this.societeSessionFacade = new SocieteSessionFacade();
        this.dossierSessionFacade = new DossierSessionFacade();
        this.vehiculeSessionFacade = new VehiculeSessionFacade();
        this.sujetSessionFacade = new SujetSessionFacade();
    }

    /**
     * Création d'un sujet
     *
     * @param subject Le sujet qui créé le dossier
     * @param info La société à créer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Societe create(CardexAuthenticationSubject subject,
                       Societe info) throws BusinessException,
                                            BusinessResourceException {
        try {
            return societeSessionFacade.create(subject, info);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Recherche d'une société
     *
     * @param subject Le sujet qui recherche un sujet
     * @param criteria Les critères de recherche
     *
     * @return La société recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Societe find(CardexAuthenticationSubject subject,
                        Societe criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return societeSessionFacade.find(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Recherche d'une société dans l'audit des changements
     *
     * @param subject Le sujet qui recherche un sujet
     * @param criteria Les critères de recherche
     *
     * @return La société recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Societe findAudit(CardexAuthenticationSubject subject,
                        Societe criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return societeSessionFacade.findAudit(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Recherche d'une société (avec audit dans AC_ACCES)
     *
     * @param subject Le sujet qui recherche un sujet
     * @param criteria Les critères de recherche
     *
     * @return La société recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Societe findAcces(CardexAuthenticationSubject subject,
                        Societe criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return societeSessionFacade.findAcces(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }
    

    /**
     * Recherche des sujets créés dans les dernières 48 heures
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les sujets recherchés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Societe> selectDefault(CardexAuthenticationSubject subject,
                                           CriteresRechercheSociete criteria) throws BusinessException,
                                           BusinessResourceException {
        try {
            return societeSessionFacade.selectDefault(subject,
                                                      criteria);
        }catch (BusinessRuleException e) {
            throw handleCriteresRechercheBusinessRuleException(e);
        }
    }

    /**
     * Recherche de sociétés
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les sujets recherchés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Societe> select(CardexAuthenticationSubject subject,
                                    CriteresRechercheSociete criteria) throws BusinessException,
                                    BusinessResourceException {
        try {
            return societeSessionFacade.select(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleCriteresRechercheBusinessRuleException(e);
        }
    }
    
    public List<Societe> liaisonSelect(CardexAuthenticationSubject subject,
            CriteresRechercheSociete criteria) throws BusinessException,
            BusinessResourceException {
		try {
			return societeSessionFacade.liaisonSelect(subject, criteria);
		} catch (BusinessRuleException e) {
			throw handleCriteresRechercheBusinessRuleException(e);
		}
	}    

    /**
     * Recherche de l'audit de changements d'une société
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les sujets recherchés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List audit(CardexAuthenticationSubject subject,
                                    Societe criteria) throws BusinessException,
                                    BusinessResourceException {
        try {
            return societeSessionFacade.audit(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleCriteresRechercheBusinessRuleException(e);
        }
    }

    /**
     * Mise à jour d'un sujet
     *
     * @param subject Le sujet qui modifie la société
     * @param info La société à modifier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void update(CardexAuthenticationSubject subject,
                       Societe info) throws BusinessException,
                                            BusinessResourceException {
        try {
            societeSessionFacade.update(subject, info);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }


    /**
     * Ajout d'un lien entre une narration et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La société à lier
     * @param narration La narration à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Narration addLienNarration(CardexAuthenticationSubject subject,
                             Societe societe,
                             Narration narration) throws BusinessException,
                             BusinessResourceException {
        try {
        	return societeSessionFacade.addLienNarration(subject, societe, narration);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }


    /**
     * Ajout d'un lien entre un sujet et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La société à lier
     * @param dossier Le dossier à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienDossier(CardexAuthenticationSubject subject,
                         Societe societe,
                         Dossier dossier) throws BusinessException,
                                             BusinessResourceException {
        try {
            societeSessionFacade.addLienDossier(subject, societe, dossier);
        } catch (BusinessRuleException e) {
        	
        	if (e.getBusinessRule() == SocieteBusinessRuleException.SOCIETE_RELIEE_PLUS_UNE_FOIS){
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
        		throw handleBusinessRuleException(e);		        	
        }
    }


    /**
     * Ajout d'un lien entre une société et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param societe La société à lier
     * @param societe La société à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienSociete(CardexAuthenticationSubject subject,
                           Societe societe,
                           Societe addedSociete) throws BusinessException,
                           BusinessResourceException {
        try {
            societeSessionFacade.addLienSociete(subject, societe, addedSociete);
        } catch (BusinessRuleException e) {
            
        	if (e.getBusinessRule() == SocieteBusinessRuleException.SOCIETE_RELIEE_A_ELLE_MEME){
				BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
	
				try {
					Societe societePopuler = societeSessionFacade.find(subject, addedSociete);
					businessRuleExceptionHandle.add("cardex_lien_lui_meme", societePopuler.getNom());
				} catch (Exception exp) {
					// on ne prends pas cette exception pour pouvoir garder la première
					businessRuleExceptionHandle.add("cardex_lien_lui_meme");
				}
				throw businessRuleExceptionHandle.getBusinessException();
        	}else
        		throw handleBusinessRuleException(e);
        }
    }


    /**
     * Ajout d'un lien entre deux sujets.
     *
     * @param subject Le sujet qui effectue le lien
     * @param societe La société à lier
     * @param sujet Le sujet à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienSujet(CardexAuthenticationSubject subject,
                           Societe societe,
                           Sujet addedSujet) throws BusinessException,
                           BusinessResourceException {
        try {
            societeSessionFacade.addLienSujet(subject, societe,
                                            addedSujet);
        } catch (BusinessRuleException e) {
        	
        	if (e.getBusinessRule() == SocieteBusinessRuleException.SOCIETE_RELIEE_PLUS_UNE_FOIS){
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
        		throw handleBusinessRuleException(e);	        	
        }
    }

    /**
     * Recherche des liens sujets.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La société lié
     * @return  Les sujets liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensSujet(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessException,
                           BusinessResourceException {
        try {
            return societeSessionFacade.findLiensSujet(subject, societe);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Recherche de l'historique des propriétaires.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La société lié
     * @return  Les propriétaires liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensProprietaires(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessException,
                           BusinessResourceException {
        try {
            return societeSessionFacade.findLiensProprietaires(subject, societe);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens Adresse.
     *
     * @param subject Le sujet qui effectue la recherche
     * @param sujet La société
     * @return  Les Adresse liées
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensAdresse(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessException,
                           BusinessResourceException {
        try {
            return societeSessionFacade.findLiensAdresse(subject, societe);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens historiques Adresse.
     *
     * @param subject Le sujet qui effectue la recherche
     * @param sujet La société
     * @return  Les Adresse liées
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensAdresseAudit(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessException,
                           BusinessResourceException {
        try {
            return societeSessionFacade.findLiensAdresseAudit(subject, societe);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    public List rechercheAdresseSociete(CardexAuthenticationSubject subject,
    		CriteresRechercheAdresses criteres) throws BusinessException,
            BusinessResourceException {
    	try {
			return societeSessionFacade.rechercheAdresseSociete(subject, criteres);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}
    
    public Collection rechercheAdresseInvalideSociete(CardexAuthenticationSubject subject,
    		CriteresRechercheAdresses criteres) throws BusinessException,
            BusinessResourceException {
    	try {
			return societeSessionFacade.rechercheAdresseInvalideSociete(subject, criteres);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}    
    
    /**
     * Recherche des liens dossiers.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La société lié
     * @return  Les dossier liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensDossier(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessException,
                           BusinessResourceException {
        try {
            return societeSessionFacade.findLiensDossier(subject, societe);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens Narration.
     *
     * @param subject Le sujet qui effectue la recherche
     * @param sujet La société
     * @return  Les narrations liées
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensNarration(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessException,
                           BusinessResourceException {
        try {
            return societeSessionFacade.findLiensNarration(subject, societe);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens Photo.
     *
     * @param subject Le sujet qui effectue la recherche
     * @param sujet La société
     * @return  Les photos liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensPhoto(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessException,
                           BusinessResourceException {
        try {
            return societeSessionFacade.findLiensPhoto(subject, societe);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens Societe.
     *
     * @param subject Le sujet qui effectue la recherche
     * @param sujet La société
     * @return  Les sociétés liées
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensSociete(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessException,
                           BusinessResourceException {
        try {
            return societeSessionFacade.findLiensSociete(subject, societe);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens Vehicule.
     *
     * @param subject Le sujet qui effectue la recherche
     * @param sujet La société
     * @return  Les véhicules liés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Collection findLiensVehicule(CardexAuthenticationSubject subject,
                           Societe societe) throws BusinessException,
                           BusinessResourceException {
        try {
            return societeSessionFacade.findLiensVehicule(subject, societe);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre une adresse et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La société à lier
     * @param societe La société à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Adresse addLienAdresse(CardexAuthenticationSubject subject,
                           Societe societe,
                           Adresse adresse) throws BusinessException,
                           BusinessResourceException {
        try {
            return societeSessionFacade.addLienAdresse(subject, societe, adresse);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre une photo et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La société à lier
     * @param photo La photo à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Photo addLienPhoto(CardexAuthenticationSubject subject,
                         Societe societe,
                         Photo photo) throws BusinessException,
                                             BusinessResourceException {
        try {
            return societeSessionFacade.addLienPhoto(subject, societe, photo);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre un véhicule et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet La société à lié
     * @param vehicule Le véhicule à lier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void addLienVehicule(CardexAuthenticationSubject subject,
                            Societe societe,
                            Vehicule vehicule) throws BusinessException,
                            BusinessResourceException {
        try {
            societeSessionFacade.addLienVehicule(subject, societe, vehicule);
        } catch (BusinessRuleException e) {
        	
        	if (e.getBusinessRule() == SocieteBusinessRuleException.SOCIETE_RELIEE_PLUS_UNE_FOIS){
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
        		throw handleBusinessRuleException(e);		        	
        }
    }

    /**
     * Suppression d'un lien entre une adresse et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param photo L'adresse à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienAdresse(CardexAuthenticationSubject subject,
                             Societe societe,
                             Adresse linked) throws BusinessException,
                             BusinessResourceException {
        try {
            societeSessionFacade.deleteLienAdresse(subject, societe, linked);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }
    
   /**
     * Suppression des sociétés de confidentialité 8.
     *
     * @param subject Le sujet qui modifie le dossier
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void delete(CardexAuthenticationSubject subject) 
    			throws BusinessException, BusinessResourceException {
        try {
            societeSessionFacade.delete(subject);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }    

    /**
     * Suppression d'un lien entre une narration et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param photo La narration à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienNarration(CardexAuthenticationSubject subject,
                             Societe societe,
                             Narration linked) throws BusinessException,
                             BusinessResourceException {
        try {
            societeSessionFacade.deleteLienNarration(subject, societe, linked);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Suppression d'un lien entre un dossier et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param photo Le dossier à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienDossier(CardexAuthenticationSubject subject,
                             Societe societe,
                             Dossier linked) throws BusinessException,
                             BusinessResourceException {
        try {
            societeSessionFacade.deleteLienDossier(subject, societe, linked);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }
    /**
     *
     * Suppression d'un lien entre une société et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La société à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienSociete(CardexAuthenticationSubject subject,
                             Societe societe,
                             Societe linked) throws BusinessException,
                             BusinessResourceException {
        try {
           societeSessionFacade.deleteLienSociete(subject, societe, linked);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Suppression d'un lien entre un sujet et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param photo Le sujet à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienSujet(CardexAuthenticationSubject subject,
                             Societe societe,
                             Sujet linked) throws BusinessException,
                             BusinessResourceException {
        try {
            societeSessionFacade.deleteLienSujet(subject, societe, linked);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Suppression d'un lien entre un véhicule et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param photo Le véhicule à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienVehicule(CardexAuthenticationSubject subject,
                             Societe societe,
                             Vehicule linked) throws BusinessException,
                             BusinessResourceException {
        try {
            societeSessionFacade.deleteLienVehicule(subject, societe, linked);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }
    /**
     * Suppression d'un lien entre une photo et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param photo La photo à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void deleteLienPhoto(CardexAuthenticationSubject subject,
                             Societe societe,
                             Photo linked) throws BusinessException,
                             BusinessResourceException {
        try {
            societeSessionFacade.deleteLienPhoto(subject, societe, linked);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Mise d'un lien entre une adresse et une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration Le lien Adresse à mettre à jour
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLienAdresse(CardexAuthenticationSubject subject,
                             Adresse linked) throws BusinessException,
                             BusinessResourceException {
        try {
            societeSessionFacade.updateLienAdresse(subject, linked);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Mise d'un lien entre une narration et une société.
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
            societeSessionFacade.updateLienNarration(subject, linked);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }

    /**
     * Approbation d'une narration liée à une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration Le lien Narration à approuver
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void approuveLienNarration(CardexAuthenticationSubject subject,
                             Narration linked) throws BusinessException,
                             BusinessResourceException {
        try {
            societeSessionFacade.approuveLienNarration(subject, linked);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
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
    protected BusinessException handleBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        int                   businessRule = bre.getBusinessRule();

        switch (businessRule) {

        case SocieteBusinessRuleException.SOCIETE_RELIEE_A_ELLE_MEME:
            message.setKey("cardex_lien_lui_meme");
            break;

        case SocieteBusinessRuleException.SOCIETE_RELIEE_PLUS_UNE_FOIS:
            message.setKey("cardex_lien_existe");
            break;

        case SocieteBusinessRuleException.SOCIETE_DATE_CREATION:
            message.setKey("cardex_required_created_date");
            break;

        case SocieteBusinessRuleException.MOT_DE_PASSE_INVALID:
            message.setKey("cardex_password_identique");
            break;

        default:
            throw new IllegalArgumentException("La règle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une règle supportée pour un objet d'affaire du type '"
                                               + Sujet.class.getName()
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
    private BusinessException handleCriteresRechercheBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        int businessRule = bre.getBusinessRule();

        switch (businessRule) {

        // Pas encore de règle s'appliquant aux critères de recherche de sujet.

        default:
            throw new IllegalArgumentException("La règle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une règle supportée pour un objet d'affaire du type '"
                                               + CriteresRechercheSociete.class.getName()
                                               + "'");
        }

    }

    /**
     * Modification du rôle d'un lien à une société.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void updateLien(CardexAuthenticationSubject subject,
                             Societe societe) throws BusinessException,
                             BusinessResourceException {
        try {
        	societeSessionFacade.updateLien(subject, societe);
        } catch (BusinessRuleException e) {
            throw handleBusinessRuleException(e);
        }
    }
    
    public SocieteVO rechercheDestinataire(CardexAuthenticationSubject subject, String numeroFiche) throws BusinessException, BusinessResourceException {

		try {
			SocieteVO societeVO = obtenirSocieteParNumeroFiche(subject, numeroFiche);
			return societeVO;
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}

    //Validation de la société recherchée par numéro de détaillant.
    private SocieteVO obtenirSocieteParNumeroFiche(CardexAuthenticationSubject subject, String numeroFiche) throws BusinessException, BusinessRuleException{
    	CriteresRechercheSocieteVO criteria = new CriteresRechercheSocieteVO();
    	criteria.setNumeroFiche(numeroFiche);
    	
    	List<Societe> listSociete = FabriqueFacade.getSocieteSessionFacade().select(subject, criteria);

		if (listSociete.size() == 0){
			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
			businessRuleExceptionHandle.add("err.aucune.societe.trouvee", numeroFiche);
			throw businessRuleExceptionHandle.getBusinessException();
		}
		if (listSociete.size() > 1){
			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
			businessRuleExceptionHandle.add("err.plusieur.societe.trouvee", numeroFiche);
			throw businessRuleExceptionHandle.getBusinessException();				
		}
		return (SocieteVO) listSociete.get(0);    	
    }

    
    /**
     * Copie des données d'une société à une autre.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi à supprimer
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public void copier(CardexAuthenticationSubject subject, long cleSource, long siteSource, long cleDestination, long siteDestination) throws BusinessException, BusinessResourceException {

		try {
			societeSessionFacade.copier(subject, cleSource, siteSource, cleDestination, siteDestination);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}
   
}

