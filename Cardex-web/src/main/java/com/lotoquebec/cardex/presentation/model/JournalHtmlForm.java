package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � un formulaire de consultation de journal.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.10 $, $Date: 2002/03/11 16:49:00 $
 */
public interface JournalHtmlForm {


    // Getters

    /**
     * Retourne la cat�gorie.
     *
     * @return String Valeur de la cat�gorie en caract�re.
     */
    public String getCategorie();

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caract�re.
     */
    public String getType();

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
     * Retourne la date de cr�ation.
     *
     * @return String Valeur de la date de cr�ation en caract�re.
     */
    public String getDateCreation();

    /**
     * Retourne le num�ro de cardex.
     *
     * @return NumeroCardex Valeur du num�ro de cardex.
     */
    public NumeroCardex getNumeroDossier();

    /**
     * Retourne le num�ro d'employ�.
     *
     * @return String Valeur du num�ro d'employ� en caract�re.
     */
    public String getNumeroEmploye();

    /**
     * Retourne la deuxi�me r�f�rence.
     *
     * @return String Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public String getReference2();

    /**
     * Retourne la troisi�me r�f�rence.
     *
     * @return String Valeur de la troisi�me r�f�rence en caract�re.
     */
    public String getReference3();

    /**
     * Retourne le num�ro d'incident.
     *
     * @return String Valeur du num�ro d'incident en caract�re.
     */
    public String getNumeroIncident();

    /**
     * Retourne la r�f�rence de vid�o.
     *
     * @return String Valeur de la r�f�rencede vid�o en caract�re.
     */
    public String getReferenceVideo();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();


    /**
     * Retourne l'endroit.
     *
     * @return long Valeur num�rique de l'endroit.
     */
    public String getEndroit();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public String getOrigine();

    /**
     * Retourne la localisation.
     *
     * @return String Valeur de la localisation en caract�re.
     */
    public String getLocalisation();

    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caract�re.
     */
    public String getDescription();

    /**
     * Retourne la description du lieu.
     *
     * @return String Valeur de la description en caract�re.
     */
    public String getDescriptif();

    /**
     * Retourne la cle.
     *
     * @return long Valeur num�rique de la cle.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur num�rique du site.
     */
    public String getSite();

    /**
     * Retourne la cle de la narration.
     *
     * @return long Valeur num�rique de la cle.
     */
    public String getCleNarration();

    /**
     * Retourne le site de la narration.
     *
     * @return long Valeur num�rique du site.
     */
    public String getSiteNarration();

    /**
     * Retourne la dur�e.
     *
     * @return String Valeur de la dur�e en caract�re.
     */
    public String getDuree();


    /**
     * Test si le dossier est modifiable.
     *
     * @return True si le dossier est modifiable.
     */
    public boolean isModifiable();

    /**
     * Test si le dossier est nouvellement cr��e.
     *
     * @return True si le dossier est nouvellement cr��e.
     */
    public boolean isNew();

    // Setters


    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur num�rique de la cat�gorie.
     */
    public void setCategorie(String categorie);

    /**
     * Affecte un type.
     *
     * @param type Valeur num�rique dy type.
     */
    public void setType(String type);

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
     * Affecte la date de creation.
     *
     * @param dateCreation Valeur de la date de creation (yyyy-MM-dd).
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte un num�ro de cardex.
     *
     * @param numeroCardex Valeur du num�ro de cardex.
     */
    public void setNumeroDossier(NumeroCardex numeroCardex);

    /**
     * Affecte un num�ro de cardex.
     *
     * @param stringNumeroDossier Valeur du num�ro de cardex en caract�re.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte le num�ro d'employ�.
     *
     * @param numeroEmploye Valeur du num�ro d'employ� en caract�re.
     */
    public void setNumeroEmploye(String numeroEmploye);

    /**
     * Affecte le num�ro d'incident.
     *
     * @param numeroIncident Valeur du num�ro d'incident en caract�re.
     */
    public void setNumeroIncident(String numeroIncident);

    /**
     * Affecte la r�f�rence vid�o.
     *
     * @param referenceVideo Valeur de la r�f�rence vid�o en caract�re.
     */
    public void setReferenceVideo(String reference);

    /**
     * Affecte l'intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur num�rique de l'endroit.
     */
    public void setEndroit(String endroit);

    /**
     * Affecte une deuxi�me r�f�rence.
     *
     * @param reference2 Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public void setReference2(String reference2);

    /**
     * Affecte une troisi�me r�f�rence.
     *
     * @param reference3 Valeur de la troisi�me r�f�rence en caract�re.
     */
    public void setReference3(String reference3);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur de la localisation en caract�re.
     */
    public void setLocalisation(String localisation);

    /**
     * Affecte l'origine.
     *
     * @param origine Valeur de l'origine en caract�re.
     */
    public void setOrigine(String origine);

    /**
     * Affecte la description de l'intervention.
     *
     * @param description Valeur de la description en caract�re.
     */
    public void setDescription(String description);

    /**
     * Affecte la description du lieu.
     *
     * @param descriptif Valeur de la description en caract�re.
     */
    public void setDescriptif(String descriptif);

    /**
     * Affecte la cle.
     *
     * @param cle Valeur num�rique de la cle.
     */
    public void setCle(String cle);

    /**
     * Affecte le site.
     *
     * @param site Valeur num�rique le site.
     */
    public void setSite(String site);

    /**
     * Affecte la cle de la narration.
     *
     * @param cleNarration Valeur num�rique de la clede la narration.
     */
    public void setCleNarration(String cleNarration);

    /**
     * Affecte le site de la narration.
     *
     * @param siteNarration Valeur num�rique le site de la narration.
     */
    public void setSiteNarration(String siteNarration);

    /**
     * Affecte la dur�e.
     *
     * @param duree Valeur de la dur�e en caract�re.
     */
    public void setDuree(String duree);

    /**
     * D�termine si le dossier est modifiable.
     *
     * @param isInscription Valeur si le dossier est modifiable.
     */
    public void setModifiable(boolean isModifiable);

    /**
     * D�termine si le dossier est nouvellement cr�er.
     *
     * @param isNew Valeur si le dossier est nouvellement cr�er.
     */
    public void setNew(boolean isNew);

}