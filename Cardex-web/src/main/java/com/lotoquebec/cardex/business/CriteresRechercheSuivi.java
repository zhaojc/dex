package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des critères, nécesssaires à la recherche d'un
 * suivi.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/03/25 20:40:38 $
 */
public interface CriteresRechercheSuivi extends CriteresRecherche{


    /**
     * Retourne l'activité.
     *
     * @return long Valeur de l'activité.
     */
    public long getActivite();

    /**
     * Affecte une activité.
     *
     * @param activite Valeur de l'activité.
     */
    public void setActivite(long activite);

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

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public String getChoixRapport();


    /**
     * Retourne le secteur assigné.
     *
     * @return String Valeur du secteur assigné en caractère.
     */
    public long getSecteurAssigne();

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
    public long getSecteurOrigine();

    /**
     * Retourne le demandeur.
     *
     * @return String Valeur du demandeur en caractère.
     */
    public String getDemandeur();

    /**
     * Retourne la date d'émission au début.
     *
     * @return Timestamp Valeur de la date d'émission au début (yyyy-MM-dd).
     */
    public Timestamp getDateEmisDebut();

    /**
     * Retourne la date prévue au début.
     *
     * @return Timestamp Valeur de la date prévue au début (yyyy-MM-dd).
     */
    public Timestamp getDatePrevueDebut();

    /**
     * Retourne la date complétée au début.
     *
     * @return Timestamp Valeur de la date complétée au début (yyyy-MM-dd).
     */
    public Timestamp getDateCompleteeDebut();

    /**
     * Retourne la date d'émission à la fin.
     *
     * @return Timestamp Valeur de la date d'émission à la fin (yyyy-MM-dd).
     */
    public Timestamp getDateEmisFin();

    /**
     * Retourne la date prévue à la fin.
     *
     * @return Timestamp Valeur de la date prévue à la fin (yyyy-MM-dd).
     */
    public Timestamp getDatePrevueFin();

    /**
     * Retourne la date complétée à la fin.
     *
     * @return Timestamp Valeur de la date complétée à la fin (yyyy-MM-dd).
     */
    public Timestamp getDateCompleteeFin();

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite();

    /**
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caractère.
     */
    public String getOrdreTriRecherche();

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur booléanne indiquant si l'ordre de recherche est
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche();

    /**
     * Retourne le nombre maximum de résultats de recherche.
     *
     * @return String Valeur du nombre maximum de résultats de recherche en
     * caractère.
     */
    public long getMaximumResultatsRecherche();

    /**
     * Retourne l'entité.
     *
     * @return long Valeur de l'entité en caractère.
     */
    public long getEntite();
    
    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur du site d'origine en caractère.
     */
    public long getSiteOrigine();


    // Setters


    /**
     * Affecte un secteur assigné.
     *
     * @param secteurAssigne Valeur du secteur assigné en caractère.
     */
    public void setSecteurAssigne(long secteurAssigne);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant);

    /**
     * Retourne le rapport choisi.
     *
     * @return String Valeur du rapport.
     */
    public void setChoixRapport(String choixRapport);
    
    /**
     * Affecte un secteur d'origine.
     *
     * @param secteurOrigine Valeur du secteur d'origine en caractère.
     */
    public void setSecteurOrigine(long secteurOrigine);

    /**
     * Affecte un demandeur.
     *
     * @param demandeur Valeur du demandeur en caractère.
     */
    public void setDemandeur(String demandeur);

    /**
     * Affecte une date d'émission au début.
     *
     * @param dateEmisDebut Valeur de la date d'émission au début (yyyy-MM-dd).
     */
    public void setDateEmisDebut(Timestamp dateEmisDebut);

    /**
     * Affecte une date prévue au début.
     *
     * @param datePrevueDebut Valeur de la date prévue au début (yyyy-MM-dd).
     */
    public void setDatePrevueDebut(Timestamp datePrevueDebut);

    /**
     * Affecte une date complétée au début.
     *
     * @param dateCompleteeDebut Valeur de la date complétée au début
     * (yyyy-MM-dd).
     */
    public void setDateCompleteeDebut(Timestamp dateCompleteeDebut);

    /**
     * Affecte une date d'émission à la fin.
     *
     * @param dateEmisFin Valeur de la date d'émission à la fin (yyyy-MM-dd).
     */
    public void setDateEmisFin(Timestamp dateEmisFin);

    /**
     * Affecte une date prévue à la fin.
     *
     * @param datePrevueFin Valeur de la date prévue à la fin (yyyy-MM-dd).
     */
    public void setDatePrevueFin(Timestamp datePrevueFin);

    /**
     * Affecte une date complétée à la fin.
     *
     * @param dateCompleteeFin Valeur de la date complétée à la fin
     * (yyyy-MM-dd).
     */
    public void setDateCompleteeFin(Timestamp dateCompleteeFin);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite);

    /**
     * Affecte l'ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caractère.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche);

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur booléanne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche);

    /**
     * Affecte le nombre maximum de résultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de résultats
     * de recherche caractère.
     */
    public void setMaximumResultatsRecherche(long maximum);

    /**
     * Affecte le critère d'entité.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setEntite(long entite);

    /**
     * Affecte le critère du site.
     *
     * @param lien Valeur du site en caractère.
     */
    public void setSiteOrigine(long siteOrigine);


}