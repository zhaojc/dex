package com.lotoquebec.cardex.business.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.CriteresRechercheNarration;
import com.lotoquebec.cardex.business.exception.CriteresRechercheNarrationBusinessRuleException;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Cette classe valide l'ensemble des r�gles d'affaire applicable
 * aux crit�res de recherche pour les dossiers.
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
        LoggerFactory.getLogger((this.getClass()));

  /**
   * Construit une instance de NarrationBusinessRuleSet
   */
  public CriteresRechercheNarrationBusinessRuleSet() {
  }

    /**
     * Valide les r�gles d'affaires applicable
     * aux crit�res de recherche pour les dossiers.
     *
     * @param businessObject Les crit�res de recherche
     *
     * @throws BusinessRuleException si les r�gles d'affaire
     * d'un objet crit�re de recherche dossier ne sont pas respect�es.
     * @throws IllegalArgumentException si l'objet d'affaire n'est pas
     * une instance de  com.lotoquebec.cardex.business.Narration
     */
  public void checkRules(CardexAuthenticationSubject subject, Object businessObject) throws BusinessRuleException {
        log.debug("checkRules()");
        if (businessObject instanceof CriteresRechercheNarration) {
          CriteresRechercheNarration criteria = (CriteresRechercheNarration)businessObject;
          checkDateDebutSuperieurDateFinRule(criteria);
        }else {
          throw new IllegalArgumentException("L'objet d'affaire doit �tre une instance de '"+CriteresRechercheNarration.class.getName()+"'");
        }
  }

    /**
     * Dates de d�but inf�rieures ou �gales aux dates de fin.
     *
     * @param businessObject Les crit�res de recherche
     *
     * @throws BusinessRuleException si les dates de d�but sont
     * inf�rieures ou �gales aux dates de fin.
     */
    private void checkDateDebutSuperieurDateFinRule(CriteresRechercheNarration businessObject)
            throws BusinessRuleException {
        log.debug("checkDateDebutSuperieurDateFinRule()");

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
