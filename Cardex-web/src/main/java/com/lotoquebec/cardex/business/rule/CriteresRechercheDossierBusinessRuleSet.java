package com.lotoquebec.cardex.business.rule;

import java.util.logging.Logger;

import com.lotoquebec.cardex.business.CriteresRechercheDossier;
import com.lotoquebec.cardex.business.exception.CriteresRechercheDossierBusinessRuleException;
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
 * @version $Revision: 1.6 $, $Date: 2002/04/09 20:44:30 $
 */
public class CriteresRechercheDossierBusinessRuleSet implements BusinessRuleSet {

  /**
   * L'instance du gestionnaire de journalisation.
   */
	private final Logger      log =
        (Logger)LoggerCardex.getLogger((this.getClass()));

  /**
   * Construit une instance de DossierBusinessRuleSet
   */
  public CriteresRechercheDossierBusinessRuleSet() {
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
     * une instance de  com.lotoquebec.cardex.business.Dossier
     */
  public void checkRules(CardexAuthenticationSubject subject, Object businessObject) throws BusinessRuleException {
        log.fine("checkRules()");
        if (businessObject instanceof CriteresRechercheDossier) {
          CriteresRechercheDossier criteria = (CriteresRechercheDossier)businessObject;
          checkDateDebutSuperieurDateFinRule(criteria);
          checkAuMoinsUnCritereSaisiEnPlusDesValeursParDefault(criteria);
        }else {
          throw new IllegalArgumentException("L'objet d'affaire doit être une instance de '"+CriteresRechercheDossier.class.getName()+"'");
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
    private void checkDateDebutSuperieurDateFinRule(CriteresRechercheDossier businessObject)
            throws BusinessRuleException {
        log.fine("checkDateDebutSuperieurDateFinRule()");

        if ((businessObject.getDateDebutAu() != null && businessObject.getDateFinAu() != null)) {
          if (businessObject.getDateFinAu().before(businessObject.getDateDebutAu())) {
            CriteresRechercheDossierBusinessRuleException be =
                new CriteresRechercheDossierBusinessRuleException();
            be.setBusinessRule(CriteresRechercheDossierBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN);
            throw be;
          }
        }

        if ((businessObject.getDateDebutDu() != null && businessObject.getDateFinDu() != null)) {
          if (businessObject.getDateFinDu().before(businessObject.getDateDebutDu())) {
            CriteresRechercheDossierBusinessRuleException be =
                new CriteresRechercheDossierBusinessRuleException();
            be.setBusinessRule(CriteresRechercheDossierBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN);
            throw be;
          }
        }

        if ((businessObject.getDateDebutDu() != null && businessObject.getDateDebutAu() != null)) {
          if (businessObject.getDateDebutAu().before(businessObject.getDateDebutDu())) {
            CriteresRechercheDossierBusinessRuleException be =
                new CriteresRechercheDossierBusinessRuleException();
            be.setBusinessRule(CriteresRechercheDossierBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN);
            throw be;
          }
        }

        if ((businessObject.getDateFinDu() != null && businessObject.getDateFinAu() != null)) {
          if (businessObject.getDateFinAu().before(businessObject.getDateFinDu())) {
            CriteresRechercheDossierBusinessRuleException be =
                new CriteresRechercheDossierBusinessRuleException();
            be.setBusinessRule(CriteresRechercheDossierBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN);
            throw be;
          }
        }
        if ((businessObject.getDateCreationDu() != null && businessObject.getDateCreationAu() != null)) {
          if (businessObject.getDateCreationAu().before(businessObject.getDateCreationDu())) {
            CriteresRechercheDossierBusinessRuleException be =
                new CriteresRechercheDossierBusinessRuleException();
            be.setBusinessRule(CriteresRechercheDossierBusinessRuleException.DATE_DEBUT_SUPERIEUR_DATE_FIN);
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
    private void checkAuMoinsUnCritereSaisiEnPlusDesValeursParDefault(CriteresRechercheDossier businessObject)
            throws BusinessRuleException {
        log.fine("checkAuMoinsUnCritereSaisiEnPlusDesValeursParDefault()");
        int count = 0;

        /*
        if (businessObject.getSiteApplicable() != 0 ) {count++;}
        if (businessObject.getType() != 0 ) {count++;}
        if (businessObject.getCategorie() != 0 ) {count++;}
        if (businessObject.getStatut() != 0 ) {count++;}
        if (businessObject.getFonde() != 0 ) {count++;}
        if (businessObject.getSeverite() != 0 ) {count++;}
        if (businessObject.getConfidentialite() != 0 ) {count++;}
        if (businessObject.getReferenceVideo() != 0 ) {count++;}
        if (businessObject.getPeriode() != 0 ) {count++;}
        if (businessObject.getMaximumResultatsRecherche() != 0 ) {count++;}
        if (businessObject.getIntervenant() != null && businessObject.getIntervenant().trim().length() > 0) {count++;}
        if (businessObject.getNumeroCardex() != null && businessObject.getNumeroCardex().trim().length() > 0) {count++;}
        if (businessObject.getNumeroDossier() != null && businessObject.getNumeroDossier().trim().length() > 0) {count++;}
        if (businessObject.getNumeroFicheSujet() != null && businessObject.getNumeroFicheSujet().trim().length() > 0) {count++;}
        if (businessObject.getReference1() != null && businessObject.getReference1().trim().length() > 0) {count++;}
        if (businessObject.getReference2() != null && businessObject.getReference2().trim().length() > 0) {count++;}
        if (businessObject.getReference3() != null && businessObject.getReference3().trim().length() > 0) {count++;}
        if (businessObject.getReference5() != null && businessObject.getReference5().trim().length() > 0) {count++;}
        if (businessObject.getOrigine() != null && businessObject.getOrigine().trim().length() > 0) {count++;}
        if (businessObject.getDateDebutDu() != null) {count++;}
        if (businessObject.getDateFinDu() != null) {count++;}
        if (businessObject.getDateDebutAu() != null) {count++;}
        if (businessObject.getDateFinAu() != null) {count++;}
        if (businessObject.getDateCreationDu() != null) {count++;}
        if (businessObject.getDateCreationAu() != null) {count++;}

        if (count == 0) {
            CriteresRechercheDossierBusinessRuleException be =
                new CriteresRechercheDossierBusinessRuleException();
            be.setBusinessRule(CriteresRechercheDossierBusinessRuleException.PAS_DE_CRITERES_SAISIS_EN_PLUS_DES_VALEURS_PAR_DEFAULT);
            throw be;
          }
        */
        }




}
