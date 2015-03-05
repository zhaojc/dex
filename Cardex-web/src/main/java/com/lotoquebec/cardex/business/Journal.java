package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * Les sujets renferment des donn�es sur des personnes qui font
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
     * @return long Valeur dy type en caract�re.
     */
    public long getType();

    /**
     * Retourne la cat�gorie.
     *
     * @return long Valeur de la cat�gorie en caract�re.
     */
    public long getCategorie();

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
     * Retourne la date de cr�ation.
     *
     * @return String Valeur de la date de cr�ation en caract�re.
     */
    public Timestamp getDateCreation();

    /**
     * Retourne le num�ro de dossier.
     *
     * @return String Valeur du num�ro de dossier en caract�re.
     */
    public String getNumeroDossier();

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
    public long getEndroit();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public long getOrigine();

    /**
     * Retourne la localisation.
     *
     * @return long Valeur de la localisation en caract�re.
     */
    public long getLocalisation();

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
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur num�rique du site.
     */
    public long getSite();

    /**
     * Retourne la cle de la narration.
     *
     * @return long Valeur num�rique de la cle.
     */
    public long getCleNarration();

    /**
     * Retourne le site de la narration.
     *
     * @return long Valeur num�rique du site.
     */
    public long getSiteNarration();

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
    public Boolean isModifiable();

    /**
     * Test si le dossier est nouvellement cr��e.
     *
     * @return True si le dossier est nouvellement cr��e.
     */
    public Boolean isNew();

    // Setters


    /**
     * Affecte un type.
     *
     * @param type Valeur num�rique du type.
     */
    public void setType(long type);

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur num�rique de la cat�gorie.
     */
    public void setCategorie(long categorie);

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
     * Affecte la date de creation.
     *
     * @param dateCreation Valeur de la date de creation (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte le num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
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
    public void setEndroit(long endroit);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur de la localisation en caract�re.
     */
    public void setLocalisation(long localisation);

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
     * Affecte l'origine.
     *
     * @param origine Valeur de l'origine en caract�re.
     */
    public void setOrigine(long origine);

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
    public void setCle(long cle);

    /**
     * Affecte le site.
     *
     * @param site Valeur num�rique le site.
     */
    public void setSite(long site);

    /**
     * Affecte la cle de la narration.
     *
     * @param cleNarration Valeur num�rique de la clede la narration.
     */
    public void setCleNarration(long cleNarration);

    /**
     * Affecte le site de la narration.
     *
     * @param siteNarration Valeur num�rique le site de la narration.
     */
    public void setSiteNarration(long siteNarration);

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
    public void setModifiable(Boolean isModifiable);

    /**
     * D�termine si le dossier est nouvellement cr�er.
     *
     * @param isNew Valeur si le dossier est nouvellement cr�er.
     */
    public void setNew(Boolean isNew);

}