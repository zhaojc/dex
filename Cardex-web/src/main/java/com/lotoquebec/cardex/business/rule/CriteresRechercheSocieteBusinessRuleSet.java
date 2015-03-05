/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.rule;

import java.util.logging.Logger;

import com.lotoquebec.cardex.business.CriteresRechercheSociete;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * Cette classe valide l'ensemble des règles d'affaire applicable
 * aux critères de recherche pour les societes.
 *
 * @see com.lotoquebec.cardexCommun.business.BusinessRuleSet
 * @author $Author: mdemers $
 * @version $Revision: 1.1 $, $Date: 2002/02/28 15:31:00 $
 */
public class CriteresRechercheSocieteBusinessRuleSet
    implements BusinessRuleSet {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    /**
     * Construit une instance de SocieteBusinessRuleSet
     */
    public CriteresRechercheSocieteBusinessRuleSet() {}

    /**
     * Valide les règles d'affaires applicable
     * aux critères de recherche pour les societes.
     *
     * @param businessObject Les critères de recherche
     *
     * @throws BusinessRuleException si les règles d'affaire
     * d'un objet critère de recherche de sociétés ne sont pas respectées.
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.CriteresRechercheSociete
     */
    public void checkRules(CardexAuthenticationSubject subject, Object businessObject)
            throws BusinessRuleException {
        log.fine("checkRules()");

        if (businessObject instanceof CriteresRechercheSociete) {
            // Pas de règles pour l'instant.
        } else {
            throw new IllegalArgumentException("L'objet d'affaire doit être une instance de '"
                                               + CriteresRechercheSociete.class.getName()
                                               + "'");
        }
    }

}
