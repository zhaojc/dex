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
     * @param criteria Les crit�res de recherche
     *
     * @return La suivi recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
     * @param criteria Les crit�res de recherche
     *
     * @return La suivi recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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
        messages.addMessage(message);
        be.setBusinessMessageResult(messages);
        return be;
    }

    /**
     * Recherche de l'audit des changements d'un suivi
     *
     * @param subject Le sujet qui effectue la recherche
     * @param criteria Les crit�res de recherche
     *
     * @return Les suivis recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
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

