/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.delegate;

import java.util.Collection;
import java.util.List;

import com.lotoquebec.cardex.business.Evaluation;
import com.lotoquebec.cardex.business.FrequenceVisites;
import com.lotoquebec.cardex.business.Jeux;
import com.lotoquebec.cardex.business.facade.EvaluationSessionFacade;
import com.lotoquebec.cardex.business.vo.FrequenceVisitesVO;
import com.lotoquebec.cardex.business.vo.MiseEvaluationVO;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessDelegate;
import com.lotoquebec.cardexCommun.business.BusinessMessage;
import com.lotoquebec.cardexCommun.business.BusinessMessageResult;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Le EvaluationBusinessDelegate offre les
 * services d'affaires concernant l'objet
 * dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/03/25 20:40:29 $
 */
public class EvaluationBusinessDelegate extends BusinessDelegate {
    EvaluationSessionFacade evaluationSessionFacade;

    /**
     * Construit une instance de ConsignationBusinessDelegate
     */
    public EvaluationBusinessDelegate() {
        this.evaluationSessionFacade = new EvaluationSessionFacade();
    }

    /**
     * Recherche d'une �valuation
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return L'�valuation demand�e
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Evaluation find(CardexAuthenticationSubject subject,
    		Evaluation criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return evaluationSessionFacade.find(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleConsignationBusinessRuleException(e);
        }
    }

    /**
     * Recherche des jeux li�s � la mise
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return L'�valuation demand�e
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Jeux findLiensJeux(CardexAuthenticationSubject subject,
    		MiseEvaluationVO miseEvaluationVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return evaluationSessionFacade.findLiensJeux(subject, miseEvaluationVO);
        } catch (BusinessRuleException e) {
            throw handleConsignationBusinessRuleException(e);
        }
    }

    /**
     * Recherche des �tats d'esprit li�s � l'�valuation
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return L'�valuation demand�e
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List findLiensEtats(CardexAuthenticationSubject subject,
    		Evaluation criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return evaluationSessionFacade.findLiensEtats(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleConsignationBusinessRuleException(e);
        }
    }

    /**
     * Recherche des propos du client li�s � l'�valuation
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return L'�valuation demand�e
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List findLiensPropos(CardexAuthenticationSubject subject,
    		Evaluation criteria) throws BusinessException,
                        BusinessResourceException {
        try {
            return evaluationSessionFacade.findLiensPropos(subject, criteria);
        } catch (BusinessRuleException e) {
            throw handleConsignationBusinessRuleException(e);
        }
    }

    /**
     * Recherche des fr�quences de visites du client li�s � la mise
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return L'�valuation demand�e
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List<FrequenceVisites> findLiensFrequenceVisites(CardexAuthenticationSubject subject,
    		MiseEvaluationVO miseEvaluationVO) throws BusinessException,
                        BusinessResourceException {
        try {
            return evaluationSessionFacade.findLiensFrequenceVisites(subject, miseEvaluationVO);
        } catch (BusinessRuleException e) {
            throw handleConsignationBusinessRuleException(e);
        }
    }
    
    /**
     * Recherche des mises d'une �valuation
     * @param subject
     * @param evaluation
     * @return
     * @throws BusinessException
     * @throws BusinessResourceException
     */
    public List<MiseEvaluationVO> findLiensMisesEvaluation(CardexAuthenticationSubject subject,
    		Evaluation evaluation) throws BusinessException,
                        BusinessResourceException {
        try {
            return evaluationSessionFacade.findLiensMisesEvaluation(subject, evaluation);
        } catch (BusinessRuleException e) {
            throw handleConsignationBusinessRuleException(e);
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

