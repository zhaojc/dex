/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.lotoquebec.cardex.business.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.CriteresRechercheSujet;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Cette classe valide l'ensemble des r�gles d'affaire applicable
 * aux crit�res de recherche pour les sujets.
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
        LoggerFactory.getLogger((this.getClass()));

    /**
     * Construit une instance de SujetBusinessRuleSet
     */
    public CriteresRechercheSujetBusinessRuleSet() {}

    /**
     * Valide les r�gles d'affaires applicable
     * aux crit�res de recherche pour les sujets.
     *
     * @param businessObject Les crit�res de recherche
     *
     * @throws BusinessRuleException si les r�gles d'affaire
     * d'un objet crit�re de recherche de sujets ne sont pas respect�es.
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.CriteresRechercheSujet
     */
    public void checkRules(CardexAuthenticationSubject subject, Object businessObject)
            throws BusinessRuleException {
        log.debug("checkRules()");

        if (businessObject instanceof CriteresRechercheSujet) {
            // Pas de r�gles pour l'instant.
        } else {
            throw new IllegalArgumentException("L'objet d'affaire doit �tre une instance de '"
                                               + CriteresRechercheSujet.class.getName()
                                               + "'");
        }
    }
    
}

