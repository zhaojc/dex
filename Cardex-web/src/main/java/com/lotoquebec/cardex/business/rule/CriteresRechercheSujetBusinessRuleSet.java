/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.rule;

import java.util.logging.Logger;

import com.lotoquebec.cardex.business.CriteresRechercheSujet;
import com.lotoquebec.cardex.business.exception.CriteresRechercheSujetBusinessRuleException;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * Cette classe valide l'ensemble des règles d'affaire applicable
 * aux critères de recherche pour les sujets.
 *
 * @see com.lotoquebec.cardexCommun.business.BusinessRuleSet
 * @author $Author: mdemers $
 * @version $Revision: 1.1 $, $Date: 2002/02/28 15:31:04 $
 */
public class CriteresRechercheSujetBusinessRuleSet
    implements BusinessRuleSet {

    /**
     * L'instance du gestionnaire de journalisation.
     */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

    /**
     * Construit une instance de SujetBusinessRuleSet
     */
    public CriteresRechercheSujetBusinessRuleSet() {}

    /**
     * Valide les règles d'affaires applicable
     * aux critères de recherche pour les sujets.
     *
     * @param businessObject Les critères de recherche
     *
     * @throws BusinessRuleException si les règles d'affaire
     * d'un objet critère de recherche de sujets ne sont pas respectées.
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.CriteresRechercheSujet
     */
    public void checkRules(CardexAuthenticationSubject subject, Object businessObject)
            throws BusinessRuleException {
        log.fine("checkRules()");

        if (businessObject instanceof CriteresRechercheSujet) {
            // Pas de règles pour l'instant.
        } else {
            throw new IllegalArgumentException("L'objet d'affaire doit être une instance de '"
                                               + CriteresRechercheSujet.class.getName()
                                               + "'");
        }
    }
    
}

