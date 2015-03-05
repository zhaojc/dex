/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.rule;

import java.util.Date;
import java.util.logging.Logger;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardex.business.exception.SuiviBusinessRuleException;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * Cette classe valide l'ensemble des règles d'affaire applicable
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
        (Logger)LoggerCardex.getLogger((this.getClass()));

    /**
     * Construit une instance de SuiviBusinessRuleSet
     */
    public SuiviBusinessRuleSet() {}

    /**
     * Retourne un SuiviBusinessRuleException initialisé avec
     * l'identificateur de règle.
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
     * Valide les règles d'affaires applicable à un dossier.
     *
     * @param businessObject Le dossier
     *
     * @throws BusinessRuleException si les règles d'affaire
     * d'un objet dossier ne sont pas respectées.
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.Dossier
     */
    public void checkRules(CardexAuthenticationSubject subject, Object businessObject)
            throws BusinessRuleException {
        log.fine("checkRules()");

        if (businessObject instanceof Suivi) {
            Suivi suivi = (Suivi) businessObject;

            checkDateCompleteRule(suivi);
            checkDateCompleteDemandeeRule(suivi);
            checkDatePrevueDemandeeRule(suivi);
        } else {
            throw new IllegalArgumentException("L'objet d'affaire doit être une instance de '"
                                               + Dossier.class.getName()
                                               + "'");
        }
    }


    /**
     * Dates de début supérieure ou égale à 1993-01-01.
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si la date de début est trop petite.
     *
     */
    private void checkDateCompleteRule(Suivi suivi)
            throws BusinessRuleException {
        log.fine("checkDateCompleteRule()");

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
     * Dates complétée supérieure à date demandée.
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si la date de début est trop petite.
     *
     */
    private void checkDateCompleteDemandeeRule(Suivi suivi)
            throws BusinessRuleException {
        log.fine("checkDateCompleteDemandeeRule()");

        if (suivi.getDateCompletee() != null && suivi.getDateCreation() != null) {
            if (suivi.getDateCompletee().before(suivi.getDateCreation())) {
                throw createException(SuiviBusinessRuleException.DATE_COMPLETE_INVALIDE);
            }
        }
    }
    /**
     * Dates prévue supérieure à date demandée.
     *
     * @param dossier Le dossier
     *
     * @throws BusinessRuleException si la date de début est trop petite.
     *
     */
    private void checkDatePrevueDemandeeRule(Suivi suivi)
            throws BusinessRuleException {
        log.fine("checkDatePrevueDemandeeRule()");

        if (suivi.getDatePrevue() != null && suivi.getDateCreation() != null) {
            if (suivi.getDatePrevue().before(suivi.getDateCreation())) {
                throw createException(SuiviBusinessRuleException.DATE_PREVUE_INVALIDE);
            }
        }

    }


}
