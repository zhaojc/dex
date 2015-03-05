package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * Les sujets renferment des données sur des personnes qui font
 * l'objet d'un dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revison: $, $Date: 2002/04/11 19:20:26 $
 */
public interface RapportJournal {


    // Getters

    /**
     * Retourne la clé du type .
     *
     * @return long Valeur de la clé en caractère.
     */
    public long getCleType();

    /**
     * Retourne la date de début.
     *
     * @return Timestamp Valeur de la date de début en caractère.
     */
    public Timestamp getDateDebut();

    /**
     * Retourne la date de fin.
     *
     * @return Timestamp Valeur de la date de fin en caractère.
     */
    public Timestamp getDateFin();

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
    public long getOrigine();

    /**
     * Retourne le descriptif.
     *
     * @return String Valeur du descriptif en caractère.
     */
    public String getDescriptif();

    /**
     * Retourne l'endroit.
     *
     * @return long Valeur de l'endroit en caractère.
     */
    public long getEndroit();

    /**
     * Retourne la localisation.
     *
     * @return long Valeur de la localisation en caractère.
     */
    public long getLocalisation();

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
    public long getSite();

    /**
     * Retourne la durée.
     *
     * @return String Valeur de la durée en caractère.
     */
    public long getDuree();


    /**
     * Affecte la date de début.
     *
     * @param dateDebut Valeur de la date du début (yyyy-MM-dd).
     */
    public void setDateDebut(Timestamp dateDebut);

    /**
     * Affecte la date de fin.
     *
     * @param dateFin Valeur de la date de fin (yyyy-MM-dd).
     */
    public void setDateFin(Timestamp dateFin);

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
    public void setOrigine(long origine);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur de l'endroit en caractère.
     */
    public void setEndroit(long endroit);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur de localisation en caractère.
     */
    public void setLocalisation(long localisation);

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
    public void setSite(long site);

    /**
     * Affecte la durée (temps consacré).
     *
     * @param duree Valeur de la durée en caractère.
     */
    public void setDuree(long duree);

    /**
     * Affecte le nombre d'événements concernés.
     *
     * @param nombre Valeur du nombre en caractère.
     */
    public void setNombre(long nombre);

    /**
     * Ajoute un détail.
     *
     * @param rapportJournal Valeur du détail.
     */
    public void addRapportJournal(RapportJournal rapportJournal);

    /**
     * Affecte la clé du type
     *
     * @param cleType Valeur numérique de la clé.
     */
    public void setCleType(long cleType);


}