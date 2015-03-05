/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.delegate;

import java.util.List;

import com.lotoquebec.cardex.business.Consignation;
import com.lotoquebec.cardex.business.CriteresRechercheConsignation;
import com.lotoquebec.cardex.business.facade.ConsignationSessionFacade;
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
public class ConsignationBusinessDelegate extends BusinessDelegate {
    ConsignationSessionFacade consignationSessionFacade;

    /**
     * Construit une instance de ConsignationBusinessDelegate
     */
    public ConsignationBusinessDelegate() {
        this.consignationSessionFacade = new ConsignationSessionFacade();
    }

    /**
     * Recherche d'une consignation
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La consignation recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public Consignation find(CardexAuthenticationSubject subject,
                        Consignation criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return consignationSessionFacade.find(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleConsignationBusinessRuleException(e);
        }
    }

    /**
     * Recherche de consignations
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return La consignation recherché
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
     */
    public List<Consignation> select(CardexAuthenticationSubject subject,
                        CriteresRechercheConsignation criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return consignationSessionFacade.select(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleConsignationBusinessRuleException(e);
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
    private BusinessException handleConsignationBusinessRuleException(BusinessRuleException bre)
            throws BusinessException {
        BusinessException     be = new BusinessException();
        BusinessMessageResult messages = new BusinessMessageResult();
        BusinessMessage       message = new BusinessMessage();
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }



}

