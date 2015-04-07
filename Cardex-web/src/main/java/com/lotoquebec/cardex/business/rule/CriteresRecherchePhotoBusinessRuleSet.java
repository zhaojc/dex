package com.lotoquebec.cardex.business.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lotoquebec.cardex.business.CriteresRecherchePhoto;
import com.lotoquebec.cardex.business.exception.CriteresRecherchePhotoBusinessRuleException;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.BusinessRuleSet;
import com.lotoquebec.cardexCommun.exception.BusinessRuleException;

/**
 * Cette classe valide l'ensemble des r�gles d'affaire applicable
 * aux crit�res de recherche pour les dossiers.
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
        LoggerFactory.getLogger((this.getClass()));

  /**
   * Construit une instance de PhotoBusinessRuleSet
   */
  public CriteresRecherchePhotoBusinessRuleSet() {
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
     * une instance de  com.lotoquebec.cardex.business.Photo
     */
  public void checkRules(CardexAuthenticationSubject subject, Object businessObject) throws BusinessRuleException {
        log.debug("checkRules()");
        if (businessObject instanceof CriteresRecherchePhoto) {
          CriteresRecherchePhoto criteria = (CriteresRecherchePhoto)businessObject;
          checkDateDebutSuperieurDateFinRule(criteria);
          checkAuMoinsUneCaseDeCocheeRule(criteria);
        }else {
          throw new IllegalArgumentException("L'objet d'affaire doit �tre une instance de '"+CriteresRecherchePhoto.class.getName()+"'");
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
    private void checkDateDebutSuperieurDateFinRule(CriteresRecherchePhoto businessObject)
            throws BusinessRuleException {
        log.debug("checkDateDebutSuperieurDateFinRule()");

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
     * Dates de d�but inf�rieures ou �gales aux dates de fin.
     *
     * @param businessObject Les crit�res de recherche
     *
     * @throws BusinessRuleException si les dates de d�but sont
     * inf�rieures ou �gales aux dates de fin.
     */
    private void checkAuMoinsUneCaseDeCocheeRule(CriteresRecherchePhoto businessObject)
            throws BusinessRuleException {
        log.debug("checkAuMoinsUneCaseDeCocheeRule()");
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
