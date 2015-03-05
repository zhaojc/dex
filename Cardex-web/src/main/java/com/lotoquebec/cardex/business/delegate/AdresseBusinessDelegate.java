/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.delegate;

import java.util.List;

import com.lotoQuebec.correcteurAdresse.AdresseSortie;
import com.lotoQuebec.correcteurAdresse.AdresseSortieRecherche;
import com.lotoQuebec.correcteurAdresse.CorrigerAdresse;
import com.lotoQuebec.correcteurAdresse.RechercherAdresse;
import com.lotoquebec.cardex.business.Adresse;
import com.lotoquebec.cardex.business.facade.AdresseSessionFacade;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Le DossierBusinessDelegate offre les
 * services d'affaires concernant l'objet
 * dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/03/20 19:44:32 $
 */
public class AdresseBusinessDelegate extends BusinessDelegate {
    AdresseSessionFacade adresseSessionFacade;

    /**
     * Construit une instance de AdresseBusinessDelegate
     */
    public AdresseBusinessDelegate() {
        this.adresseSessionFacade = new AdresseSessionFacade();
    }

    /**
     * Recherche d'une adresse
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La adresse recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Adresse find(CardexAuthenticationSubject subject,
                        Adresse criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return adresseSessionFacade.find(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleAdresseBusinessRuleException(e);
        }
    }

    /**
     * Corriger l'adresse dans streetperfect
     * @param adresseCorrection
     * @return
     * @throws BusinessResourceException 
     */
    public AdresseSortie validationAdresse(CardexAuthenticationSubject subject, Adresse adresse) throws BusinessResourceException {
    	adresse.assignerDescription(subject);
    	return CorrigerAdresse.corriger(adresse);
	}
    
    /**
     * Retourne une liste d'"AdresseSortieRecherche" qui représente 
     * @param adresseCorrection
     * @return
     * @throws BusinessResourceException 
     */
    public List rechercheValidationAdresse(CardexAuthenticationSubject subject, Adresse adresse) throws BusinessResourceException {
    	adresse.assignerDescription(subject);
		return RechercherAdresse.rechercher(adresse);
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
    private BusinessException handleAdresseBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }

    /**
     * Recherche de l'audit des changements d'une adresse
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les adresses recherchées
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List audit(CardexAuthenticationSubject subject,
                                           Adresse criteria) throws BusinessException,
                                           BusinessResourceException {
        try {
            return adresseSessionFacade.audit(subject,criteria);
        }catch (BusinessRuleException e) {
            throw handleAdresseBusinessRuleException(e);
        }
    }

}

