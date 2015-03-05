package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à un formulaire de recherche de suivi.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/03/25 20:42:39 $
 */
public interface CriteresRechercheSuiviHtmlForm extends CriteresRecherche{

    /**
     * Retourne le statut approbation.
     *
     * @return String Valeur du statut approbation.
     */
    public String getStatutApprobation();

    /**
     * Affecte un statut approbation.
     *
     * @param statutApprobation Valeur du statut approbation.
     */
    public void setStatutApprobation(String statutApprobation);

    /**
     * Retourne le statut suivi.
     *
     * @return String Valeur du statut suivi.
     */
    public String getStatutSuivi();

    /**
     * Affecte un statut suivi.
     *
     * @param statutSuivi Valeur du statut suivi.
     */
    public void setStatutSuivi(String statutSuivi);

    // Getters


    /**
     * Retourne le secteur assigné.
     *
     * @return String Valeur du secteur assigné en caractère.
     */
    public String getSecteurAssigne();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();

    /**
     * Retourne le secteur d'origine.
     *
     * @return String Valeur du secteur d'origine en caractère.
     */
    public String getSecteurOrigine();

    /**
     * Retourne le demandeur.
     *
     * @return String Valeur du demandeur en caractère.
     */
    public String getDemandeur();

    /**
     * Retourne l'activité.
     *
     * @return String Valeur de l'activité.
     */
    public String getActivite();

    /**
     * Affecte une activité.
     *
     * @param activite Valeur de l'activité en caractère.
     */
    public void setActivite(String activite);

    /**
     * Retourne la date d'émission au début.
     *
     * @return String Valeur de la date d'émission au début en caractère.
     */
    public String getDateEmisDebut();

    /**
     * Retourne la date prévue au début.
     *
     * @return String Valeur de la date prévue au début en caractère.
     */
    public String getDatePrevueDebut();

    /**
     * Retourne la date complétée au début.
     *
     * @return String Valeur de la date complétée au début en caractère.
     */
    public String getDateCompleteeDebut();

    /**
     * Retourne la date d'émission à la fin.
     *
     * @return String Valeur de la date d'émission à la fin en caractère.
     */
    public String getDateEmisFin();

    /**
     * Retourne la date prévue à la fin.
     *
     * @return String Valeur de la date prévue à la fin en caractère.
     */
    public String getDatePrevueFin();

    /**
     * Retourne la date complétée à la fin.
     *
     * @return String Valeur de la date complétée à la fin en caractère).
     */
    public String getDateCompleteeFin();

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public String getChoixRapport();

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caractère.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caractère.
     */
    public String getLienSite();

    /**
     * Retourne l'entité.
     *
     * @return String Valeur de l'entité en caractère.
     */
    public String getEntite();
    
    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caractère.
     */
    public String getSiteOrigine();

    /**
     * Retourne une collection de suivis.
     *
     * @return Collection Valeur de la collection de suivis.
     */
    public Collection getSuivis();


    // Setters


    /**
     * Affecte un secteur assigné.
     *
     * @param secteurAssigne Valeur du secteur assigné en caractère.
     */
    public void setSecteurAssigne(String secteurAssigne);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte un secteur d'origine.
     *
     * @param secteurOrigine Valeur du secteur d'origine en caractère.
     */
    public void setSecteurOrigine(String secteurOrigine);

    /**
     * Affecte un demandeur.
     *
     * @param demandeur Valeur du demandeur en caractère.
     */
    public void setDemandeur(String demandeur);

    /**
     * Affecte une date d'émission au début.
     *
     * @param dateEmisDebut Valeur de la date d'émission au début en caractère.
     */
    public void setDateEmisDebut(String dateEmisDebut);

    /**
     * Affecte une date prévue au début.
     *
     * @param datePrevueDebut Valeur de la date prévue au début en caractère.
     */
    public void setDatePrevueDebut(String datePrevueDebut);

    /**
     * Affecte une date complétée au début.
     *
     * @param dateCompleteeDebut Valeur de la date complétée au début en
     * caractère.
     */
    public void setDateCompleteeDebut(String dateCompleteeDebut);

    /**
     * Affecte une date d'émission à la fin.
     *
     * @param dateEmisFin Valeur de la date d'émission à la fin en caractère.
     */
    public void setDateEmisFin(String dateEmisFin);

    /**
     * Affecte une date prévue à la fin.
     *
     * @param datePrevueFin Valeur de la date prévue à la fin en caractère.
     */
    public void setDatePrevueFin(String datePrevueFin);

    /**
     * Affecte une date complétée à la fin.
     *
     * @param dateCompleteeFin Valeur de la date complétée à la fin en
     * caractère.
     */
    public void setDateCompleteeFin(String dateCompleteeFin);

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public void setChoixRapport(String choixRapport);


    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(String lienSite);

    /**
     * Ajoute un suivi.
     *
     * @param suivi Valeur de la suivi à ajouter.
     */
    public void addSuivi(SuiviHtmlForm suivi);

    /**
     * Affecte le critère d'entité.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setEntite(String entite);

    /**
     * Affecte le critère du site.
     *
     * @param lien Valeur du site en caractère.
     */
    public void setSiteOrigine(String siteOrigine);

    
}