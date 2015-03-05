package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à un formulaire de consultation de journal.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.10 $, $Date: 2002/03/11 16:49:00 $
 */
public interface JournalHtmlForm {


    // Getters

    /**
     * Retourne la catégorie.
     *
     * @return String Valeur de la catégorie en caractère.
     */
    public String getCategorie();

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caractère.
     */
    public String getType();

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
     * Retourne la date de création.
     *
     * @return String Valeur de la date de création en caractère.
     */
    public String getDateCreation();

    /**
     * Retourne le numéro de cardex.
     *
     * @return NumeroCardex Valeur du numéro de cardex.
     */
    public NumeroCardex getNumeroDossier();

    /**
     * Retourne le numéro d'employé.
     *
     * @return String Valeur du numéro d'employé en caractère.
     */
    public String getNumeroEmploye();

    /**
     * Retourne la deuxième référence.
     *
     * @return String Valeur de la deuxième référence en caractère.
     */
    public String getReference2();

    /**
     * Retourne la troisième référence.
     *
     * @return String Valeur de la troisième référence en caractère.
     */
    public String getReference3();

    /**
     * Retourne le numéro d'incident.
     *
     * @return String Valeur du numéro d'incident en caractère.
     */
    public String getNumeroIncident();

    /**
     * Retourne la référence de vidéo.
     *
     * @return String Valeur de la référencede vidéo en caractère.
     */
    public String getReferenceVideo();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();


    /**
     * Retourne l'endroit.
     *
     * @return long Valeur numérique de l'endroit.
     */
    public String getEndroit();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caractère.
     */
    public String getOrigine();

    /**
     * Retourne la localisation.
     *
     * @return String Valeur de la localisation en caractère.
     */
    public String getLocalisation();

    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caractère.
     */
    public String getDescription();

    /**
     * Retourne la description du lieu.
     *
     * @return String Valeur de la description en caractère.
     */
    public String getDescriptif();

    /**
     * Retourne la cle.
     *
     * @return long Valeur numérique de la cle.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur numérique du site.
     */
    public String getSite();

    /**
     * Retourne la cle de la narration.
     *
     * @return long Valeur numérique de la cle.
     */
    public String getCleNarration();

    /**
     * Retourne le site de la narration.
     *
     * @return long Valeur numérique du site.
     */
    public String getSiteNarration();

    /**
     * Retourne la durée.
     *
     * @return String Valeur de la durée en caractère.
     */
    public String getDuree();


    /**
     * Test si le dossier est modifiable.
     *
     * @return True si le dossier est modifiable.
     */
    public boolean isModifiable();

    /**
     * Test si le dossier est nouvellement créée.
     *
     * @return True si le dossier est nouvellement créée.
     */
    public boolean isNew();

    // Setters


    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur numérique de la catégorie.
     */
    public void setCategorie(String categorie);

    /**
     * Affecte un type.
     *
     * @param type Valeur numérique dy type.
     */
    public void setType(String type);

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
     * Affecte la date de creation.
     *
     * @param dateCreation Valeur de la date de creation (yyyy-MM-dd).
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte un numéro de cardex.
     *
     * @param numeroCardex Valeur du numéro de cardex.
     */
    public void setNumeroDossier(NumeroCardex numeroCardex);

    /**
     * Affecte un numéro de cardex.
     *
     * @param stringNumeroDossier Valeur du numéro de cardex en caractère.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte le numéro d'employé.
     *
     * @param numeroEmploye Valeur du numéro d'employé en caractère.
     */
    public void setNumeroEmploye(String numeroEmploye);

    /**
     * Affecte le numéro d'incident.
     *
     * @param numeroIncident Valeur du numéro d'incident en caractère.
     */
    public void setNumeroIncident(String numeroIncident);

    /**
     * Affecte la référence vidéo.
     *
     * @param referenceVideo Valeur de la référence vidéo en caractère.
     */
    public void setReferenceVideo(String reference);

    /**
     * Affecte l'intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur numérique de l'endroit.
     */
    public void setEndroit(String endroit);

    /**
     * Affecte une deuxième référence.
     *
     * @param reference2 Valeur de la deuxième référence en caractère.
     */
    public void setReference2(String reference2);

    /**
     * Affecte une troisième référence.
     *
     * @param reference3 Valeur de la troisième référence en caractère.
     */
    public void setReference3(String reference3);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur de la localisation en caractère.
     */
    public void setLocalisation(String localisation);

    /**
     * Affecte l'origine.
     *
     * @param origine Valeur de l'origine en caractère.
     */
    public void setOrigine(String origine);

    /**
     * Affecte la description de l'intervention.
     *
     * @param description Valeur de la description en caractère.
     */
    public void setDescription(String description);

    /**
     * Affecte la description du lieu.
     *
     * @param descriptif Valeur de la description en caractère.
     */
    public void setDescriptif(String descriptif);

    /**
     * Affecte la cle.
     *
     * @param cle Valeur numérique de la cle.
     */
    public void setCle(String cle);

    /**
     * Affecte le site.
     *
     * @param site Valeur numérique le site.
     */
    public void setSite(String site);

    /**
     * Affecte la cle de la narration.
     *
     * @param cleNarration Valeur numérique de la clede la narration.
     */
    public void setCleNarration(String cleNarration);

    /**
     * Affecte le site de la narration.
     *
     * @param siteNarration Valeur numérique le site de la narration.
     */
    public void setSiteNarration(String siteNarration);

    /**
     * Affecte la durée.
     *
     * @param duree Valeur de la durée en caractère.
     */
    public void setDuree(String duree);

    /**
     * Détermine si le dossier est modifiable.
     *
     * @param isInscription Valeur si le dossier est modifiable.
     */
    public void setModifiable(boolean isModifiable);

    /**
     * Détermine si le dossier est nouvellement créer.
     *
     * @param isNew Valeur si le dossier est nouvellement créer.
     */
    public void setNew(boolean isNew);

}