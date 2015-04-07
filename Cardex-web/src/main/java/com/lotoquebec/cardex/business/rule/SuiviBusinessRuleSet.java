/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.rule;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardex.business.exception.SuiviBusinessRuleException;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Cette classe valide l'ensemble des r�gles d'affaire applicable
 * aux dossiers.
 *
 * @see com.lotoquebec.cardexCommun.business.BusinessRuleSet
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/04/04 20:40:58 $
 */
public class SuiviBusinessRuleSet implements BusinessRuleSet {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        LoggerFactory.getLogger((this.getClass()));

    /**
     * Construit une instance de SuiviBusinessRuleSet
     */
    public SuiviBusinessRuleSet() {}

    /**
     * Retourne un SuiviBusinessRuleException initialis� avec
     * l'identificateur de r�gle.
     *
     *
     * @param ruleId
     *
     * @return
     *
     * @see
     */
    protected BusinessRuleException createException(int ruleId) {
        SuiviBusinessRuleException exc =
            new SuiviBusinessRuleException();

        exc.setBusinessRule(ruleId);

        return exc;
    }

    /**
     * Valide les r�gles d'affaires applicable � un dossier.
     *
     * @param businessObject Le dossier
     *
     * @throws BusinessRuleException si les r�gles d'affaire
     * d'un objet dossier ne sont pas respect�es.
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.Dossier
     */
    public void checkRules(CardexAuthenticationSubject subject, Object businessObject)
            throws BusinessRuleException {
        log.debug("checkRules()");

        if (businessObject instanceof Suivi) {
            Suivi suivi = (Suivi) businessObject;

            checkDateCompleteRule(suivi);
            checkDateCompleteDemandeeRule(suivi);
            checkDatePrevueDemandeeRule(suivi);
        } else {
            throw new IllegalArgumentException("L'objet d'affaire doit �tre une instance de '"
                                               + Dossier.class.getName()
                                               + "'");
        }
    }


    /**
     * Dates de d�but sup�rieure ou �gale � 1993-01-01.
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si la date de d�but est trop petite.
     *
     */
    private void checkDateCompleteRule(Suivi suivi)
            throws BusinessRuleException {
        log.debug("checkDateCompleteRule()");

        Date date = suivi.getDateCompletee();
        if (date == null) {
            return;
        }

        Date now = new Date(System.currentTimeMillis());

        if (date.after(now)) {
            throw createException(SuiviBusinessRuleException.DATE_COMPLETE_INVALIDE);
        }
    }

    /**
     * Dates compl�t�e sup�rieure � date demand�e.
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si la date de d�but est trop petite.
     *
     */
    private void checkDateCompleteDemandeeRule(Suivi suivi)
            throws BusinessRuleException {
        log.debug("checkDateCompleteDemandeeRule()");

        if (suivi.getDateCompletee() != null && suivi.getDateCreation() != null) {
            if (suivi.getDateCompletee().before(suivi.getDateCreation())) {
                throw createException(SuiviBusinessRuleException.DATE_COMPLETE_INVALIDE);
            }
        }
    }
    /**
     * Dates pr�vue sup�rieure � date demand�e.
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si la date de d�but est trop petite.
     *
     */
    private void checkDatePrevueDemandeeRule(Suivi suivi)
            throws BusinessRuleException {
        log.debug("checkDatePrevueDemandeeRule()");

        if (suivi.getDatePrevue() != null && suivi.getDateCreation() != null) {
            if (suivi.getDatePrevue().before(suivi.getDateCreation())) {
                throw createException(SuiviBusinessRuleException.DATE_PREVUE_INVALIDE);
            }
        }

    }


}
