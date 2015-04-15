/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.delegate;

import java.util.List;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardex.business.facade.AccesSessionFacade;
import com.lotoquebec.cardex.business.vo.AccesVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;
import com.lotoquebec.cardexCommun.business.EntiteCardex;
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
     * Recherche des acc�s.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List<AccesVO> selectAccesDossier(CardexAuthenticationSubject subject,
                        Dossier criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return accesSessionFacade.findAccesDossier(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }

    /**
     * Recherche des acc�s.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List<AccesVO> selectAccesSujet(CardexAuthenticationSubject subject, EntiteCardex criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return accesSessionFacade.findAccesSujet(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }
    
    /**
     * Recherche des acc�s.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List<AccesVO> selectAccesSociete(CardexAuthenticationSubject subject,
                        Societe criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return accesSessionFacade.findAccesSociete(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleAccesBusinessRuleException(e);
        }
    }
    
    /**
     * Recherche des acc�s.
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La liste des acc�s.
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List<AccesVO> selectAccesVehicule(CardexAuthenticationSubject subject,
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

