/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/
package com.lotoquebec.cardex.business.facade;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;

import com.lotoquebec.cardex.business.Evaluation;
import com.lotoquebec.cardex.business.FrequenceVisites;
import com.lotoquebec.cardex.business.Jeux;
import com.lotoquebec.cardex.business.vo.MiseEvaluationVO;
import com.lotoquebec.cardex.businessRules.BusinessRulesValidator;
import com.lotoquebec.cardex.integration.dao.EvaluationDAO;
import com.lotoquebec.cardex.integration.dao.FabriqueCardexDAO;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.ValueListIterator;
import com.lotoquebec.cardexCommun.exception.BusinessException;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.exception.BusinessRuleExceptionHandle;
import com.lotoquebec.cardexCommun.exception.DAOException;
import com.lotoquebec.cardexCommun.util.VerificationSyntaxe;


/**
 * Le SuiviBusinessFacade offre les
 * services d'affaires concernant l'objet
 * �valuation.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/03/25 20:40:33 $
 */
public class EvaluationSessionFacade {


    /**
     * Recherche d'une �valuation
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La consignation recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Evaluation find(CardexAuthenticationSubject subject,
    		Evaluation criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	Evaluation evaluation = FabriqueCardexDAO.getInstance().getEvaluationDAO().find(subject, criteria);
            GestionnaireSecuriteCardex.validerEtFiltrerSecuriteConsulter(subject, evaluation);
            return evaluation;
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche des jeux li�s � l'�valuation
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return La consignation recherch�
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public Jeux findLiensJeux(CardexAuthenticationSubject subject,
    		MiseEvaluationVO miseEvaluationVO) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	Jeux jeux = FabriqueCardexDAO.getInstance().getEvaluationDAO().findLiensJeux(subject, miseEvaluationVO);
            return jeux;
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche des �tats d'esprit li�s � l'�valuation
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return Les �tats recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List findLiensEtats(CardexAuthenticationSubject subject,
    		Evaluation criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	return FabriqueCardexDAO.getInstance().getEvaluationDAO().findLiensEtats(subject, criteria);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche des propos du client li�s � l'�valuation
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return Les �tats recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List findLiensPropos(CardexAuthenticationSubject subject,
    		Evaluation criteria) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	return FabriqueCardexDAO.getInstance().getEvaluationDAO().findLiensPropos(subject, criteria);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

    /**
     * Recherche des fr�quences de visites du client li�s � l'�valuation
     *
     * @param subject Le sujet qui recherche un dossier
     * @param criteria Les crit�res de recherche
     *
     * @return Les �tats recherch�s
     *
     * @throws BusinessException si une r�gle d'affaire n'est pas respect�e
     * @throws BusinessResourceException si une erreur syst�me survient
     */
    public List<FrequenceVisites> findLiensFrequenceVisites(CardexAuthenticationSubject subject,
    		MiseEvaluationVO miseEvaluationVO) throws BusinessRuleException,
                        BusinessResourceException {
        try {
        	return FabriqueCardexDAO.getInstance().getEvaluationDAO().findLiensFrequenceVisites(subject, miseEvaluationVO);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
    }

	public List<MiseEvaluationVO> findLiensMisesEvaluation(CardexAuthenticationSubject subject, Evaluation evaluation) throws BusinessRuleException, BusinessResourceException {
        try {
        	return FabriqueCardexDAO.getInstance().getEvaluationDAO().findLiensMisesEvaluation(subject, evaluation);
        } catch (DAOException dae) {
            throw new BusinessResourceException(dae);
        }
	}
    
}

