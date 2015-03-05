package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives aux rapports du journal.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.10 $, $Date: 2002/03/11 16:49:00 $
 */
public interface RapportJournalHtmlForm {


    /**
     * Retourne la date de début.
     *
     * @return String Valeur de la date de début en caractère.
     */
    public String getDateDebut();


    /**
     * Retourne la date de fin.
     *
     * @return String Valeur de la date de fin en caractère.
     */
    public String getDateFin();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();


    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caractère.
     */
    public String getDescription();

    /**
     * Retourne la description de la catégorie.
     *
     * @return String Valeur de la description en caractère.
     */
    public String getDescriptionCategorie();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caractère.
     */
    public String getOrigine();

    /**
     * Retourne le descriptif.
     *
     * @return String Valeur du descriptif en caractère.
     */
    public String getDescriptif();

    /**
     * Retourne l'endroit.
     *
     * @return String Valeur de l'endroit en caractère.
     */
    public String getEndroit();

    /**
     * Retourne la localisation.
     *
     * @return String Valeur de la localisation en caractère.
     */
    public String getLocalisation();

    /**
     * Retourne le secteur (groupe de sécurité).
     *
     * @return long Valeur numérique du secteur.
     */
    public String getSecteur();

    /**
     * Retourne le site.
     *
     * @return long Valeur numérique du site.
     */
    public String getSite();

    /**
     * Retourne la durée.
     *
     * @return String Valeur de la durée en caractère.
     */
    public String getDuree();


    /**
     * Retourne la clé du type .
     *
     * @return String Valeur de la clé en caractère.
     */
    public String getCleType();

    /**
     * Affecte la date de début.
     *
     * @param dateDebut Valeur de la date du début (yyyy-MM-dd).
     */
    public void setDateDebut(String dateDebut);

    /**
     * Affecte la date de fin.
     *
     * @param dateFin Valeur de la date de fin (yyyy-MM-dd).
     */
    public void setDateFin(String dateFin);

    /**
     * Affecte l'intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte la description de l'intervention.
     *
     * @param description Valeur de la description en caractère.
     */
    public void setDescription(String description);

    /**
     * Affecte la description de la catégorie.
     *
     * @param descriptionCategorie Valeur de la description en caractère.
     */
    public void setDescriptionCategorie(String descriptionCategorie);

    /**
     * Affecte le descriptif.
     *
     * @param descriptif Valeur du descriptif en caractère.
     */
    public void setDescriptif(String description);

    /**
     * Affecte l'origine
     *
     * @param origine Valeur de l'origine en caractère.
     */
    public void setOrigine(String origine);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur de l'endroit en caractère.
     */
    public void setEndroit(String endroit);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur de localisation en caractère.
     */
    public void setLocalisation(String localisation);

    /**
     * Affecte le secteur.
     *
     * @param secteur Valeur numérique le secteur.
     */
    public void setSecteur(String secteur);

    /**
     * Affecte le site.
     *
     * @param site Valeur numérique le site.
     */
    public void setSite(String site);

    /**
     * Affecte la durée (temps consacré).
     *
     * @param duree Valeur de la durée en caractère.
     */
    public void setDuree(String duree);

    /**
     * Affecte le nombre d'événements concernés.
     *
     * @param nombre Valeur du nombre en caractère.
     */
    public void setNombre(String nombre);

    /**
     * Affecte la clé du type
     *
     * @param cleType Valeur numérique de la clé.
     */
    public void setCleType(String cleType);


}