/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.delegate;

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
import com.lotoquebec.cardex.business.facade.DossierSessionFacade;
import com.lotoquebec.cardex.business.facade.FabriqueFacade;
import com.lotoquebec.cardex.business.facade.SocieteSessionFacade;
import com.lotoquebec.cardex.business.facade.SujetSessionFacade;
import com.lotoquebec.cardex.business.facade.VehiculeSessionFacade;
import com.lotoquebec.cardex.business.vo.CriteresRechercheSujetVO;
import com.lotoquebec.cardex.business.vo.PhotoVO;
import com.lotoquebec.cardex.business.vo.SocieteVO;
import com.lotoquebec.cardex.business.vo.SujetVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;

/**
 * Le SujetBusinessDelegate offre les services d'affaires concernant l'objet
 * sujet.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.17 $, $Date: 2002/04/12 19:42:03 $
 */
public class SujetBusinessDelegate extends BusinessDelegate {
    SujetSessionFacade sujetSessionFacade;
    DossierSessionFacade dossierSessionFacade;
    SocieteSessionFacade societeSessionFacade;
    VehiculeSessionFacade vehiculeSessionFacade;

    /**
     * Construit une instance de SujetBusinessDelegate
     */
    public SujetBusinessDelegate() {
        this.sujetSessionFacade = new SujetSessionFacade();
        this.dossierSessionFacade = new DossierSessionFacade();
        this.societeSessionFacade = new SocieteSessionFacade();
        this.vehiculeSessionFacade = new VehiculeSessionFacade();
    }

    /**
     * Cr�ation d'un sujet
     *
     * @param subject Le sujet qui cr�� le dossier
     * @param info Le sujet � cr�er
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Sujet create(CardexAuthenticationSubject subject,
                       Sujet info) throws BusinessException,
                                            BusinessResourceException {
        try {
            return sujetSessionFacade.create(subject, info);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Recherche d'un sujet (sans audit dans AC_ACCES)
     *
     * @param subject Le sujet qui recherche un sujet
     * @param criteria Les crit�res de recherche
     *
     * @return Le sujet recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Sujet find(CardexAuthenticationSubject subject,
                        Sujet criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return sujetSessionFacade.find(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
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
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Sujet findAudit(CardexAuthenticationSubject subject,
                        Sujet criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return sujetSessionFacade.findAudit(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }
    
    /**
     * Recherche d'un sujet (avec audit dans AC_ACCES)
     *
     * @param subject Le sujet qui recherche un sujet
     * @param criteria Les crit�res de recherche
     *
     * @return Le sujet recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Sujet findAcces(CardexAuthenticationSubject subject,
                        Sujet criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return sujetSessionFacade.findAcces(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }    

    /**
     * Recherche des sujets cr��s dans les derni�res 48 heures
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les sujets recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection selectDefault(CardexAuthenticationSubject subject,
                                           CriteresRechercheSujet criteria) throws BusinessException,
                                           BusinessResourceException {
        try {
            return sujetSessionFacade.selectDefault(subject, criteria);
        }catch (BusinessRuleException e) {
            throw handleCriteresRechercheSujetBusinessRuleException(e);
        }
    }

    /**
     * Recherche de l'audit des changements d'un sujet
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les sujets recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List<Sujet> audit(CardexAuthenticationSubject subject,
                                           Sujet criteria) throws BusinessException,
                                           BusinessResourceException {
        try {
            return sujetSessionFacade.audit(subject,criteria);
        }catch (BusinessRuleException e) {
            throw handleCriteresRechercheSujetBusinessRuleException(e);
        }
    }

    /**
     * Recherche de sujets
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les sujets recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection select(CardexAuthenticationSubject subject,
                                    CriteresRechercheSujet criteria) throws BusinessException,
                                    BusinessResourceException {
        try {
            return sujetSessionFacade.select(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleCriteresRechercheSujetBusinessRuleException(e);
        }
    }

    /**
     * Mise � jour d'un sujet
     *
     * @param subject Le sujet qui modifie le sujet
     * @param info Le sujet � modifier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void update(CardexAuthenticationSubject subject,
                       Sujet info) throws BusinessException,
                                            BusinessResourceException {
        try {
            sujetSessionFacade.update(subject, info);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }


    /**
     * Ajout d'un lien entre une narration et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param narration La narration � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void addLienNarration(CardexAuthenticationSubject subject,
                             Sujet sujet,
                             Narration narration) throws BusinessException,
                             BusinessResourceException {
        try {
            sujetSessionFacade.addLienNarration(subject, sujet, narration);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }


    /**
     * Ajout d'un lien entre un sujet et un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param dossier Le dossier � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void addLienDossier(CardexAuthenticationSubject subject,
                         Sujet sujet,
                         Dossier dossier) throws BusinessException,
                                             BusinessResourceException {
        try {
            sujetSessionFacade.addLienDossier(subject, sujet, dossier);
        } catch (BusinessRuleException e) {
        	
        	if (e.getBusinessRule() == SujetBusinessRuleException.SUJET_RELIE_PLUS_UNE_FOIS){
				BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
	
				try {
					Dossier dossierPopuler = dossierSessionFacade.find(subject, dossier);
					businessRuleExceptionHandle.add("cardex_lien_existe", dossierPopuler.getNumeroCardex());
				} catch (Exception exp) {
					// on ne prends pas cette exception pour pouvoir garder la premi�re
					businessRuleExceptionHandle.add("cardex_lien_existe");
				}
				throw businessRuleExceptionHandle.getBusinessException();
        	}else
        		throw handleSujetBusinessRuleException(e);		
        }
    }

    /**
     * Destruction d'un lien entre une photo et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet lier
     * @param dossier La photo lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienPhoto(CardexAuthenticationSubject subject,
                         Sujet sujet,
                         Photo photo) throws BusinessException,
                                             BusinessResourceException {
        try {
            sujetSessionFacade.deleteLienPhoto(subject, sujet,
                                            photo);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Destruction d'un lien entre un dossier et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param dossier Le dossier � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienDossier(CardexAuthenticationSubject subject,
                         Sujet sujet,
                         Dossier dossier) throws BusinessException,
                                             BusinessResourceException {
        try {
            sujetSessionFacade.deleteLienDossier(subject, sujet,
                                            dossier);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Destruction d'un lien entre un societe et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param societe Le societe � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienSociete(CardexAuthenticationSubject subject,
                         Sujet sujet,
                         Societe societe) throws BusinessException,
                                             BusinessResourceException {
        try {
            sujetSessionFacade.deleteLienSociete(subject, sujet,
                                            societe);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Destruction d'un lien entre un societe et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param societe Le societe � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienVehicule(CardexAuthenticationSubject subject,
                         Sujet sujet,
                         Vehicule vehicule) throws BusinessException,
                                             BusinessResourceException {
        try {
            sujetSessionFacade.deleteLienVehicule(subject, sujet,
                                            vehicule);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre une soci�t� et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param societe La soci�t� � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void addLienSociete(CardexAuthenticationSubject subject,
                           Sujet sujet,
                           Societe societe) throws BusinessException,
                           BusinessResourceException {
        try {
            sujetSessionFacade.addLienSociete(subject, sujet,
                                            societe);
        } catch (BusinessRuleException e) {
        	
        	if (e.getBusinessRule() == SujetBusinessRuleException.SUJET_RELIE_PLUS_UNE_FOIS){
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
        		throw handleSujetBusinessRuleException(e);        	
        }
    }


    /**
     * Ajout d'un lien entre deux sujets.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param addedSujet Le 2e dossier � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void addLienSujet(CardexAuthenticationSubject subject,
                           Sujet sujet,
                           Sujet addedSujet) throws BusinessException,
                           BusinessResourceException {
        try {
            sujetSessionFacade.addLienSujet(subject, sujet,
                                            addedSujet);
        } catch (BusinessRuleException e) {
        	
        	if (e.getBusinessRule() == SujetBusinessRuleException.SUJET_RELIE_A_LUI_MEME){
				BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
	
				try {
					Sujet sujetPopuler = sujetSessionFacade.find(subject, addedSujet);
					businessRuleExceptionHandle.add("cardex_lien_lui_meme", sujetPopuler.getNumeroFiche());
				} catch (Exception exp) {
					// on ne prends pas cette exception pour pouvoir garder la premi�re
					businessRuleExceptionHandle.add("cardex_lien_lui_meme");
				}
				throw businessRuleExceptionHandle.getBusinessException();
        	}else
        		throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Destruction d'un lien entre une adresse et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param societe La soci�t� � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienAdresse(CardexAuthenticationSubject subject,
                           Sujet sujet,
                           Adresse adresse) throws BusinessException,
                           BusinessResourceException {
        try {
            sujetSessionFacade.deleteLienAdresse(subject, sujet,
                                            adresse);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }
    
    /**
     * Suppression des sujets de confidentialit� 8.
     *
     * @param subject Le sujet qui modifie le dossier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void delete(CardexAuthenticationSubject subject) 
    			throws BusinessException, BusinessResourceException {
        try {
            sujetSessionFacade.delete(subject);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }    

    /**
     * Destruction d'un lien entre une narration et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param societe La soci�t� � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienNarration(CardexAuthenticationSubject subject,
                           Sujet sujet,
                           Narration narration) throws BusinessException,
                           BusinessResourceException {
        try {
            sujetSessionFacade.deleteLienNarration(subject, sujet,
                                            narration);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Destruction d'un lien entre un dossier et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param addedSujet Le 2e sujet � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void deleteLienSujet(CardexAuthenticationSubject subject,
                           Sujet sujet,
                           Sujet addedSujet) throws BusinessException,
                           BusinessResourceException {
        try {
            sujetSessionFacade.deleteLienSujet(subject, sujet,
                                            addedSujet);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens entre deux sujets.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les sujets li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensSujet(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessException,
                           BusinessResourceException {
        try {
            return sujetSessionFacade.findLiensSujet(subject, sujet);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens dossiers.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les dossier li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensDossier(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessException,
                           BusinessResourceException {
        try {
            return sujetSessionFacade.findLiensDossier(subject, sujet);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    public Collection findLiensDossierGalerie(CardexAuthenticationSubject subject,
			Sujet sujet) throws BusinessException, BusinessResourceException {
		try {
			return sujetSessionFacade.findLiensDossierGalerie(subject, sujet);
		} catch (BusinessRuleException e) {
			throw handleSujetBusinessRuleException(e);
		}
	}
    
    /**
     * Recherche des liens societes.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les societe li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensSociete(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessException,
                           BusinessResourceException {
        try {
            return sujetSessionFacade.findLiensSociete(subject, sujet);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens v�hicules.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les v�hicules li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensVehicule(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessException,
                           BusinessResourceException {
        try {
            return sujetSessionFacade.findLiensVehicule(subject, sujet);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens caracteristiques.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les caracteristiques li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Caracteristiques findLiensCaracteristique(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessException,
                           BusinessResourceException {
        try {
            return sujetSessionFacade.findLiensCaracteristique(subject, sujet);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens photos.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les dossier li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List<PhotoVO> findLiensPhoto(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessException,
                           BusinessResourceException {
        try {
            return sujetSessionFacade.findLiensPhoto(subject, sujet);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens photos historiques.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les dossier li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensPhotoAudit(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessException,
                           BusinessResourceException {
        try {
            return sujetSessionFacade.findLiensPhotoAudit(subject, sujet);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens narration.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les dossier li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensNarration(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessException,
                           BusinessResourceException {
        try {
            return sujetSessionFacade.findLiensNarration(subject, sujet);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens adresse.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les adresse li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensAdresse(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessException,
                           BusinessResourceException {
        try {
            return sujetSessionFacade.findLiensAdresse(subject, sujet);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Recherche des liens adresse historiques.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet li�
     * @return  Les adresse li�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Collection findLiensAdresseAudit(CardexAuthenticationSubject subject,
                           Sujet sujet) throws BusinessException,
                           BusinessResourceException {
        try {
            return sujetSessionFacade.findLiensAdresseAudit(subject, sujet);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Mise � jour d'un lien entre une narration et un dossier.
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
            sujetSessionFacade.updateLienNarration(subject,narration);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Approbation d'une narration li�e � un dossier.
     *
     * @param subject Le sujet qui effectue le lien
     * @param narration La narration � approuver
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void approuveLienNarration(CardexAuthenticationSubject subject,
                             Narration narration) throws BusinessException,
                             BusinessResourceException {
        try {
            sujetSessionFacade.approuveLienNarration(subject,narration);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Mise � jour d'un lien entre une adresse et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param adresse L'adresse � mettre � jour
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void updateLienAdresse(CardexAuthenticationSubject subject,
                             Adresse adresse) throws BusinessException,
                             BusinessResourceException {
        try {
            sujetSessionFacade.updateLienAdresse(subject,adresse);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre une adresse et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param societe La soci�t� � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void addLienAdresse(CardexAuthenticationSubject subject,
                           Sujet sujet,
                           Adresse adresse) throws BusinessException,
                           BusinessResourceException {
        try {
            sujetSessionFacade.addLienAdresse(subject, sujet,
                                            adresse);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre des caracteristiques et un sujet avec un audit.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param caracteristiques Les caract�ristiques � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void updateLiensCaracteristique(CardexAuthenticationSubject subject,
                           Sujet sujet,
                           Caracteristiques caracteristiques) throws BusinessException,
                           BusinessResourceException {
        try {
            sujetSessionFacade.updateLiensCaracteristique(subject, sujet,
                                            caracteristiques);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre une photo et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � lier
     * @param photo La photo � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Photo addLienPhoto(CardexAuthenticationSubject subject,
                         Sujet sujet,
                         Photo photo) throws BusinessException,
                                             BusinessResourceException {
        try {
            return sujetSessionFacade.addLienPhoto(subject, sujet, photo);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }

    /**
     * Ajout d'un lien entre un v�hicule et un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param sujet Le sujet � li�
     * @param vehicule Le v�hicule � lier
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void addLienVehicule(CardexAuthenticationSubject subject,
                            Sujet sujet,
                            Vehicule vehicule) throws BusinessException,
                            BusinessResourceException {
        try {
            sujetSessionFacade.addLienVehicule(subject, sujet,
                                             vehicule);
        } catch (BusinessRuleException e) {
        	
        	if (e.getBusinessRule() == SujetBusinessRuleException.SUJET_RELIE_PLUS_UNE_FOIS){
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
        		throw handleSujetBusinessRuleException(e);  
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
    private BusinessException handleSujetBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        int                   businessRule = bre.getBusinessRule();

        switch (businessRule) {

        case SujetBusinessRuleException.DATE_NAISSANCE_INVALIDE:
            message.setKey("cardex_required_date_naissance");
            //message.addArgument();
            break;

        case SujetBusinessRuleException.SUJET_RELIE_A_LUI_MEME:
            message.setKey("cardex_lien_lui_meme");
            //message.addArgument();
            break;

        case SujetBusinessRuleException.SUJET_RELIE_PLUS_UNE_FOIS:
            message.setKey("cardex_lien_existe");
            //message.addArgument();
            break;

        case SujetBusinessRuleException.MOT_DE_PASSE_INVALID:
            message.setKey("cardex_password_identique");
            break;

        default:
            throw new IllegalArgumentException("La r�gle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une r�gle support�e pour un objet d'affaire du type '"
                                               + Sujet.class.getName()
                                               + "'");
        }

        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }

    public List rechercheAdresseSujet(CardexAuthenticationSubject subject,
    		CriteresRechercheAdresses criteres) throws BusinessException,
            BusinessResourceException {
    	try {
			return sujetSessionFacade.rechercheAdresseSujet(subject, criteres);
		} catch (BusinessRuleException e) {
			throw handleCriteresRechercheSujetBusinessRuleException(e);
		}
	}
    
    public Collection rechercheAdresseInvalideSujet(CardexAuthenticationSubject subject,
    		CriteresRechercheAdresses criteres) throws BusinessException,
            BusinessResourceException {
    	try {
			return sujetSessionFacade.rechercheAdresseInvalideSujet(subject, criteres);
		} catch (BusinessRuleException e) {
			throw handleCriteresRechercheSujetBusinessRuleException(e);
		}
	}    
    
    public void selectionnerPhotoGalerie(CardexAuthenticationSubject subject, Sujet sujetVO, PhotoVO photoVO) throws BusinessException,
            BusinessResourceException {
    	try {
			sujetSessionFacade.selectionnerPhotoGalerie(subject, sujetVO, photoVO);
		} catch (BusinessRuleException e) {
			throw handleCriteresRechercheSujetBusinessRuleException(e);
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
    private BusinessException handleCriteresRechercheSujetBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {

        int businessRule = bre.getBusinessRule();

        switch (businessRule) {

        // Pas encore de r�gle s'appliquant aux crit�res de recherche de sujet.



        default:
            throw new IllegalArgumentException("La r�gle d'affaire '"
                                               + businessRule
                                               + "' n'est pas une r�gle support�e pour un objet d'affaire du type '"
                                               + CriteresRechercheSujet.class.getName()
                                               + "'");
        }

}

    /**
     * Modification du r�le d'un lien � un sujet.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi � supprimer
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void updateLien(CardexAuthenticationSubject subject,
                             Sujet sujet) throws BusinessException,
                             BusinessResourceException {
        try {
            sujetSessionFacade.updateLien(subject, sujet);
        } catch (BusinessRuleException e) {
            throw handleSujetBusinessRuleException(e);
        }
    }
   
    public SujetVO rechercheDestinataire(CardexAuthenticationSubject subject, String numeroFiche) throws BusinessException, BusinessResourceException {

		try {
			SujetVO sujetVO = obtenirSujetParNumeroFiche(subject, numeroFiche);
			return sujetVO;
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}

    //Validation du sujet recherch� par num�ro de fiche.
    private SujetVO obtenirSujetParNumeroFiche(CardexAuthenticationSubject subject, String numeroFiche) throws BusinessException, BusinessRuleException{
    	CriteresRechercheSujetVO criteria = new CriteresRechercheSujetVO();
    	criteria.setNumeroFiche(numeroFiche);
    	
    	List<Sujet> listSujet = FabriqueFacade.getSujetSessionFacade().select(subject, criteria);

		if (listSujet.size() == 0){
			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
			businessRuleExceptionHandle.add("err.aucun.sujet.trouve", numeroFiche);
			throw businessRuleExceptionHandle.getBusinessException();
		}
		if (listSujet.size() > 1){
			BusinessRuleExceptionHandle businessRuleExceptionHandle = new BusinessRuleExceptionHandle();
			businessRuleExceptionHandle.add("err.plusieur.sujet.trouve", numeroFiche);
			throw businessRuleExceptionHandle.getBusinessException();				
		}
		return (SujetVO) listSujet.get(0);    	
    }

    /**
     * Copie des donn�es d'un sujet � une autre.
     *
     * @param subject Le sujet qui effectue le lien
     * @param suivi La suivi � supprimer
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public void copier(CardexAuthenticationSubject subject, long cleSource, long siteSource, long cleDestination, long siteDestination) throws BusinessException, BusinessResourceException {

		try {
			sujetSessionFacade.copier(subject, cleSource, siteSource, cleDestination, siteDestination);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}

    //Recherche directe d'un sujet � partir du menu principal
    public Sujet rechercheDirecte(CardexAuthenticationSubject subject, Sujet sujet) throws BusinessException, BusinessResourceException {

		try {
			return sujetSessionFacade.rechercheDirecte(subject, sujet);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}

    //Ajout d'une entr�e dans la table des acc�s
    public void ajoutAcces(CardexAuthenticationSubject subject, Sujet sujet) throws BusinessException, BusinessResourceException {

		try {
			sujetSessionFacade.ajoutAcces(subject, sujet);
		} catch (BusinessRuleException e) {
			throw handleBusinessRuleException(e);
		}
	}

}

