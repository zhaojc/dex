/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.delegate;

import java.util.List;

import com.lotoquebec.cardex.business.CriteresRechercheSuivi;
import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardex.business.facade.SuiviSessionFacade;
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
 * @version $Revision: 1.1 $, $Date: 2002/03/25 20:40:29 $
 */
public class SuiviBusinessDelegate extends BusinessDelegate {
    SuiviSessionFacade suiviSessionFacade;

    /**
     * Construit une instance de SuiviBusinessDelegate
     */
    public SuiviBusinessDelegate() {
        this.suiviSessionFacade = new SuiviSessionFacade();
    }

    /**
     * Recherche d'une suivi
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La suivi recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Suivi find(CardexAuthenticationSubject subject,
                        Suivi criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return suiviSessionFacade.find(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleSuiviBusinessRuleException(e);
        }
    }

    /**
     * Recherche de suivis
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La suivi recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Suivi> select(CardexAuthenticationSubject subject,
                        CriteresRechercheSuivi criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return suiviSessionFacade.select(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleSuiviBusinessRuleException(e);
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
    private BusinessException handleSuiviBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }

    /**
     * Recherche de l'audit des changements d'un suivi
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les critères de recherche
     *
     * @return Les suivis recherchés
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List audit(CardexAuthenticationSubject subject,
                                           Suivi criteria) throws BusinessException,
                                           BusinessResourceException {
        try {
            return suiviSessionFacade.audit(subject,criteria);
        }catch (BusinessRuleException e) {
            throw handleSuiviBusinessRuleException(e);
        }
    }

}

