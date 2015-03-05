package com.lotoquebec.cardex.business.rule;

import java.util.logging.Logger;

import com.lotoquebec.cardex.business.CriteresRechercheNarration;
import com.lotoquebec.cardex.business.exception.CriteresRechercheNarrationBusinessRuleException;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;
import com.lotoquebec.cardexCommun.log.LoggerCardex;

/**
 * Cette classe valide l'ensemble des règles d'affaire applicable
 * aux critères de recherche pour les dossiers.
 *
 * @see com.lotoquebec.cardexCommun.business.BusinessRuleSet
 * @author $Author: mlibersan $
 * @version $Revision: 1.1 $, $Date: 2002/04/11 15:05:53 $
 */
public class CriteresRechercheNarrationBusinessRuleSet implements BusinessRuleSet {

  /**
   * L'instance du gestionnaire de journalisation.
   */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

  /**
   * Construit une instance de NarrationBusinessRuleSet
   */
  public CriteresRechercheNarrationBusinessRuleSet() {
  }

    /**
     * Valide les règles d'affaires applicable
     * aux critères de recherche pour les dossiers.
     *
     * @param businessObject Les critères de recherche
     *
     * @throws BusinessRuleException si les règles d'affaire
     * d'un objet critère de recherche dossier ne sont pas respectées.
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.Narration
     */
  public void checkRules(CardexAuthenticationSubject subject, Object businessObject) throws BusinessRuleException {
        log.fine("checkRules()");
        if (businessObject instanceof CriteresRechercheNarration) {
          CriteresRechercheNarration criteria = (CriteresRechercheNarration)businessObject;
          checkDateDebutSuperieurDateFinRule(criteria);
        }else {
          throw new IllegalArgumentException("L'objet d'affaire doit être une instance de '"+CriteresRechercheNarration.class.getName()+"'");
        }
  }

    /**
     * Dates de début inférieures ou égales aux dates de fin.
     *
     * @param businessObject Les critères de recherche
     *
     * @throws BusinessRuleException si les dates de début sont
     * inférieures ou égales aux dates de fin.
     */
    private void checkDateDebutSuperieurDateFinRule(CriteresRechercheNarration businessObject)
            throws BusinessRuleException {
        log.fine("checkDateDebutSuperieurDateFinRule()");

        if ((businessObject.getDateApprobationDebut() != null && businessObject.getDateApprobationFin() != null)) {
          if (businessObject.getDateApprobationFin().before(businessObject.getDateApprobationDebut())) {
            CriteresRechercheNarrationBusinessRuleException be =
                new CriteresRechercheNarrationBusinessRuleException();
            be.setBusinessRule(CriteresRechercheNarrationBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN);
            throw be;
          }
        }

        if ((businessObject.getDateCreationDebut() != null && businessObject.getDateCreationFin() != null)) {
          if (businessObject.getDateCreationFin().before(businessObject.getDateCreationDebut())) {
            CriteresRechercheNarrationBusinessRuleException be =
                new CriteresRechercheNarrationBusinessRuleException();
            be.setBusinessRule(CriteresRechercheNarrationBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN);
            throw be;
          }
        }
    }


}
