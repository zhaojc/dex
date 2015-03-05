package com.lotoquebec.cardex.business.rule;

import java.util.logging.Logger;

import com.lotoquebec.cardex.business.CriteresRecherchePhoto;
import com.lotoquebec.cardex.business.exception.CriteresRecherchePhotoBusinessRuleException;
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
 * @version $Revision: 1.1 $, $Date: 2002/04/04 20:40:55 $
 */
public class CriteresRecherchePhotoBusinessRuleSet implements BusinessRuleSet {

  /**
   * L'instance du gestionnaire de journalisation.
   */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

  /**
   * Construit une instance de PhotoBusinessRuleSet
   */
  public CriteresRecherchePhotoBusinessRuleSet() {
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
     * une instance de  com.lotoquebec.cardex.business.Photo
     */
  public void checkRules(CardexAuthenticationSubject subject, Object businessObject) throws BusinessRuleException {
        log.fine("checkRules()");
        if (businessObject instanceof CriteresRecherchePhoto) {
          CriteresRecherchePhoto criteria = (CriteresRecherchePhoto)businessObject;
          checkDateDebutSuperieurDateFinRule(criteria);
          checkAuMoinsUneCaseDeCocheeRule(criteria);
        }else {
          throw new IllegalArgumentException("L'objet d'affaire doit être une instance de '"+CriteresRecherchePhoto.class.getName()+"'");
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
    private void checkDateDebutSuperieurDateFinRule(CriteresRecherchePhoto businessObject)
            throws BusinessRuleException {
        log.fine("checkDateDebutSuperieurDateFinRule()");

        if ((businessObject.getDateAjoutDebut() != null && businessObject.getDateAjoutFin() != null)) {
          if (businessObject.getDateAjoutFin().before(businessObject.getDateAjoutDebut())) {
            CriteresRecherchePhotoBusinessRuleException be =
                new CriteresRecherchePhotoBusinessRuleException();
            be.setBusinessRule(CriteresRecherchePhotoBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN);
            throw be;
          }
        }

        if ((businessObject.getDateTermineDebut() != null && businessObject.getDateTermineFin() != null)) {
          if (businessObject.getDateTermineFin().before(businessObject.getDateTermineDebut())) {
            CriteresRecherchePhotoBusinessRuleException be =
                new CriteresRecherchePhotoBusinessRuleException();
            be.setBusinessRule(CriteresRecherchePhotoBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN);
            throw be;
          }
        }

        if ((businessObject.getDateValideDebut() != null && businessObject.getDateValideFin() != null)) {
          if (businessObject.getDateValideFin().before(businessObject.getDateValideDebut())) {
            CriteresRecherchePhotoBusinessRuleException be =
                new CriteresRecherchePhotoBusinessRuleException();
            be.setBusinessRule(CriteresRecherchePhotoBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN);
            throw be;
          }
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
    private void checkAuMoinsUneCaseDeCocheeRule(CriteresRecherchePhoto businessObject)
            throws BusinessRuleException {
        log.fine("checkAuMoinsUneCaseDeCocheeRule()");
        int count = 0;


        if (businessObject.isDossierAttache() || businessObject.isSujetAttache()) {

        }else {
            CriteresRecherchePhotoBusinessRuleException be =
                new CriteresRecherchePhotoBusinessRuleException();
            be.setBusinessRule(CriteresRecherchePhotoBusinessRuleException.PAS_AU_MOINS_UNE_CASE_COCHEE);
            throw be;
        }
    }




}
