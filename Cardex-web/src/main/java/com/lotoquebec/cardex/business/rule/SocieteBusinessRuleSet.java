/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.rule;

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Logger;

import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.exception.SocieteBusinessRuleException;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * Cette classe valide l'ensemble des règles d'affaire applicable aux societes.
 *
 * @see com.lotoquebec.cardexCommun.business.BusinessRuleSet
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $, $Date: 2002/04/11 19:20:27 $
 */
public class SocieteBusinessRuleSet implements BusinessRuleSet {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    /**
     * Construit une instance de SocieteBusinessRuleSet
     */
    public SocieteBusinessRuleSet() {}

    /**
     * Valide les règles d'affaires applicable à un societe.
     *
     * @param businessObject La société
     *
     * @throws BusinessRuleException si les règles d'affaire
     * d'un objet Societe ne sont pas respectées.
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.Societe
     */
    public void checkRules(CardexAuthenticationSubject subject, Object businessObject)
            throws BusinessRuleException {
        log.fine("checkRules()");

        if (businessObject instanceof Societe) {

            Societe societe = (Societe) businessObject;

            checkDateFondationRule(societe);
            checkMotDePasseRule(societe);
        } else {
            throw new IllegalArgumentException("L'objet d'affaire doit être une instance de '"
                                               + Societe.class.getName()
                                               + "'");
        }
    }

    /**
     * Validation de la date de fondation.
     *
     * @param businessObject La société
     *
     * @throws BusinessRuleException si la date de fondation n'est pas
     * inférieure à la date courante.
     */
    private void checkDateFondationRule(Societe societe)
            throws BusinessRuleException {
        log.fine("checkDateFondationRule()");

        Timestamp dateNaissance = societe.getDateDeFondation();
        if (dateNaissance != null) {

            Date now = new Date(System.currentTimeMillis());

            if (dateNaissance.after(now)) {
                throw createException(SocieteBusinessRuleException.SOCIETE_DATE_CREATION);
            }
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
    private void checkMotDePasseRule(Societe societe)
            throws BusinessRuleException {
        log.fine("checkMotDePasseRule()");

        if (societe.getMotPasse().trim().equals(societe.getConfirmationMotPasse().trim())) {
        }else {
            throw createException(SocieteBusinessRuleException.MOT_DE_PASSE_INVALID);
        }
    }

    /**
     * Retourne un DossierBusinessRuleException initialisé avec
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
        SocieteBusinessRuleException exc =
            new SocieteBusinessRuleException();

        exc.setBusinessRule(ruleId);

        return exc;
    }

}
