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
     * Recherche d'une évaluation
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return L'évaluation demandée
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Recherche des jeux liés à la mise
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return L'évaluation demandée
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Recherche des états d'esprit liés à l'évaluation
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return L'évaluation demandée
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Recherche des propos du client liés à l'évaluation
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return L'évaluation demandée
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Recherche des fréquences de visites du client liés à la mise
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les critères de recherche
     *
     * @return L'évaluation demandée
     *
     * @throws BusinessException si une règle d'affaire n'est pas respectée
     * @throws BusinessResourceException si une erreur système survient
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
     * Recherche des mises d'une évaluation
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

