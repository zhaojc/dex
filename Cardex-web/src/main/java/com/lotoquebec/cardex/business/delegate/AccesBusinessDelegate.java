/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.delegate;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.facade.AccesSessionFacade;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Le AccesBusinessDelegate offre les
 * services d'affaires concernant l'objet
 * acces.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/03/20 19:44:32 $
 */
public class AccesBusinessDelegate extends BusinessDelegate {
    AccesSessionFacade accesSessionFacade;

    /**
     * Construit une instance de AccesBusinessDelegate
     */
    public AccesBusinessDelegate() {
        this.accesSessionFacade = new AccesSessionFacade();
    }

    /**
     * Recherche des accès.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La liste des accès.
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public ValueListIterator selectAccesDossier(CardexAuthenticationSubject subject,
                        Dossier criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return accesSessionFacade.findAccesDossier(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Recherche des accès.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La liste des accès.
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public ValueListIterator selectAccesSujet(CardexAuthenticationSubject subject,
                        Sujet criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return accesSessionFacade.findAccesSujet(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }
    
    /**
     * Recherche des accès.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La liste des accès.
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public ValueListIterator selectAccesSociete(CardexAuthenticationSubject subject,
                        Societe criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return accesSessionFacade.findAccesSociete(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }
    
    /**
     * Recherche des accès.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La liste des accès.
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public ValueListIterator selectAccesVehicule(CardexAuthenticationSubject subject,
                        Vehicule criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return accesSessionFacade.findAccesVehicule(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
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
    private BusinessException handleAccesBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }



}

