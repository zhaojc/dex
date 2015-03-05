package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * Les sujets renferment des donn�es sur des personnes qui font
 * l'objet d'un dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revison: $, $Date: 2002/04/11 19:20:26 $
 */
public interface RapportJournal {


    // Getters

    /**
     * Retourne la cl� du type .
     *
     * @return long Valeur de la cl� en caract�re.
     */
    public long getCleType();

    /**
     * Retourne la date de d�but.
     *
     * @return Timestamp Valeur de la date de d�but en caract�re.
     */
    public Timestamp getDateDebut();

    /**
     * Retourne la date de fin.
     *
     * @return Timestamp Valeur de la date de fin en caract�re.
     */
    public Timestamp getDateFin();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();


    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caract�re.
     */
    public String getDescription();

    /**
     * Retourne la description de la cat�gorie.
     *
     * @return String Valeur de la description en caract�re.
     */
    public String getDescriptionCategorie();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public long getOrigine();

    /**
     * Retourne le descriptif.
     *
     * @return String Valeur du descriptif en caract�re.
     */
    public String getDescriptif();

    /**
     * Retourne l'endroit.
     *
     * @return long Valeur de l'endroit en caract�re.
     */
    public long getEndroit();

    /**
     * Retourne la localisation.
     *
     * @return long Valeur de la localisation en caract�re.
     */
    public long getLocalisation();

    /**
     * Retourne le secteur (groupe de s�curit�).
     *
     * @return long Valeur num�rique du secteur.
     */
    public String getSecteur();

    /**
     * Retourne le site.
     *
     * @return long Valeur num�rique du site.
     */
    public long getSite();

    /**
     * Retourne la dur�e.
     *
     * @return String Valeur de la dur�e en caract�re.
     */
    public long getDuree();


    /**
     * Affecte la date de d�but.
     *
     * @param dateDebut Valeur de la date du d�but (yyyy-MM-dd).
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
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte la description de l'intervention.
     *
     * @param description Valeur de la description en caract�re.
     */
    public void setDescription(String description);

    /**
     * Affecte la description de la cat�gorie.
     *
     * @param descriptionCategorie Valeur de la description en caract�re.
     */
    public void setDescriptionCategorie(String descriptionCategorie);

    /**
     * Affecte le descriptif.
     *
     * @param descriptif Valeur du descriptif en caract�re.
     */
    public void setDescriptif(String description);

    /**
     * Affecte l'origine
     *
     * @param origine Valeur de l'origine en caract�re.
     */
    public void setOrigine(long origine);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur de l'endroit en caract�re.
     */
    public void setEndroit(long endroit);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur de localisation en caract�re.
     */
    public void setLocalisation(long localisation);

    /**
     * Affecte le secteur.
     *
     * @param secteur Valeur num�rique le secteur.
     */
    public void setSecteur(String secteur);

    /**
     * Affecte le site.
     *
     * @param site Valeur num�rique le site.
     */
    public void setSite(long site);

    /**
     * Affecte la dur�e (temps consacr�).
     *
     * @param duree Valeur de la dur�e en caract�re.
     */
    public void setDuree(long duree);

    /**
     * Affecte le nombre d'�v�nements concern�s.
     *
     * @param nombre Valeur du nombre en caract�re.
     */
    public void setNombre(long nombre);

    /**
     * Ajoute un d�tail.
     *
     * @param rapportJournal Valeur du d�tail.
     */
    public void addRapportJournal(RapportJournal rapportJournal);

    /**
     * Affecte la cl� du type
     *
     * @param cleType Valeur num�rique de la cl�.
     */
    public void setCleType(long cleType);


}