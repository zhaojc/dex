package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives aux rapports du journal.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.10 $, $Date: 2002/03/11 16:49:00 $
 */
public interface RapportJournalHtmlForm {


    /**
     * Retourne la date de d�but.
     *
     * @return String Valeur de la date de d�but en caract�re.
     */
    public String getDateDebut();


    /**
     * Retourne la date de fin.
     *
     * @return String Valeur de la date de fin en caract�re.
     */
    public String getDateFin();

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
    public String getOrigine();

    /**
     * Retourne le descriptif.
     *
     * @return String Valeur du descriptif en caract�re.
     */
    public String getDescriptif();

    /**
     * Retourne l'endroit.
     *
     * @return String Valeur de l'endroit en caract�re.
     */
    public String getEndroit();

    /**
     * Retourne la localisation.
     *
     * @return String Valeur de la localisation en caract�re.
     */
    public String getLocalisation();

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
    public String getSite();

    /**
     * Retourne la dur�e.
     *
     * @return String Valeur de la dur�e en caract�re.
     */
    public String getDuree();


    /**
     * Retourne la cl� du type .
     *
     * @return String Valeur de la cl� en caract�re.
     */
    public String getCleType();

    /**
     * Affecte la date de d�but.
     *
     * @param dateDebut Valeur de la date du d�but (yyyy-MM-dd).
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
    public void setOrigine(String origine);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur de l'endroit en caract�re.
     */
    public void setEndroit(String endroit);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur de localisation en caract�re.
     */
    public void setLocalisation(String localisation);

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
    public void setSite(String site);

    /**
     * Affecte la dur�e (temps consacr�).
     *
     * @param duree Valeur de la dur�e en caract�re.
     */
    public void setDuree(String duree);

    /**
     * Affecte le nombre d'�v�nements concern�s.
     *
     * @param nombre Valeur du nombre en caract�re.
     */
    public void setNombre(String nombre);

    /**
     * Affecte la cl� du type
     *
     * @param cleType Valeur num�rique de la cl�.
     */
    public void setCleType(String cleType);


}