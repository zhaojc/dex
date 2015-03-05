package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * Les sujets renferment des données sur des personnes qui font
 * l'objet d'un dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revison: $, $Date: 2002/04/11 19:20:26 $
 */
public interface Journal extends Modifiable{


    // Getters

    /**
     * Retourne le type.
     *
     * @return long Valeur dy type en caractère.
     */
    public long getType();

    /**
     * Retourne la catégorie.
     *
     * @return long Valeur de la catégorie en caractère.
     */
    public long getCategorie();

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
     * Retourne la date de création.
     *
     * @return String Valeur de la date de création en caractère.
     */
    public Timestamp getDateCreation();

    /**
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro de dossier en caractère.
     */
    public String getNumeroDossier();

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
    public long getEndroit();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caractère.
     */
    public long getOrigine();

    /**
     * Retourne la localisation.
     *
     * @return long Valeur de la localisation en caractère.
     */
    public long getLocalisation();

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
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur numérique du site.
     */
    public long getSite();

    /**
     * Retourne la cle de la narration.
     *
     * @return long Valeur numérique de la cle.
     */
    public long getCleNarration();

    /**
     * Retourne le site de la narration.
     *
     * @return long Valeur numérique du site.
     */
    public long getSiteNarration();

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
    public Boolean isModifiable();

    /**
     * Test si le dossier est nouvellement créée.
     *
     * @return True si le dossier est nouvellement créée.
     */
    public Boolean isNew();

    // Setters


    /**
     * Affecte un type.
     *
     * @param type Valeur numérique du type.
     */
    public void setType(long type);

    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur numérique de la catégorie.
     */
    public void setCategorie(long categorie);

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
     * Affecte la date de creation.
     *
     * @param dateCreation Valeur de la date de creation (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte le numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
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
    public void setEndroit(long endroit);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur de la localisation en caractère.
     */
    public void setLocalisation(long localisation);

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
     * Affecte l'origine.
     *
     * @param origine Valeur de l'origine en caractère.
     */
    public void setOrigine(long origine);

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
    public void setCle(long cle);

    /**
     * Affecte le site.
     *
     * @param site Valeur numérique le site.
     */
    public void setSite(long site);

    /**
     * Affecte la cle de la narration.
     *
     * @param cleNarration Valeur numérique de la clede la narration.
     */
    public void setCleNarration(long cleNarration);

    /**
     * Affecte le site de la narration.
     *
     * @param siteNarration Valeur numérique le site de la narration.
     */
    public void setSiteNarration(long siteNarration);

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
    public void setModifiable(Boolean isModifiable);

    /**
     * Détermine si le dossier est nouvellement créer.
     *
     * @param isNew Valeur si le dossier est nouvellement créer.
     */
    public void setNew(Boolean isNew);

}