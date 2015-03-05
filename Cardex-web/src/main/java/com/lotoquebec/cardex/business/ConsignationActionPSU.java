package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * D�finit la signature des m�thodes des diff�rentes valeurs consignant toutes
 * les actions d�finies par des mandats PSU.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $, $Date: 2002/03/26 22:18:39 $
 */
public interface ConsignationActionPSU {


    // Getters

    /**
     * Retourne la cl�.
     *
     * @return long Valeur de la cl� en caract�re.
     */
    public long getCle();

    /**
     * Retourne le type de l'action.
     *
     * @return long Valeur du type en caract�re.
     */
    public long getTypeAction();

    /**
     * Retourne le num�ro du mandat.
     *
     * @return String Valeur du mandaten caract�re.
     */
    public String getNumeroMandat();

    /**
     * Retourne le genre du fichier.
     *
     * @return String Valeur du genre en caract�re.
     */
    public String getGenreFichier();

    /**
     * Retourne cl� de r�f�rence du fichier d�fini dans le mandat.
     *
     * @return long Valeur de la cl� en caract�re.
     */
    public long getReferenceSourceCle();

    /**
     * Retourne le site de r�f�rence.
     *
     * @return long Valeur du site en caract�re.
     */
    public long getReferenceSourceSite();

    /**
     * Retourne le site du fichier qui a g�n�r� l'action.
     *
     * @return long Valeur du site en caract�re.
     */
    public long getReferenceActionSite();

    /**
     * Retourne la cl� du fichier qui a g�n�r� l'action.
     *
     * @return long Valeur de l'entit� en caract�re en caract�re.
     */
    public long getReferenceActionCle();

    /**
     * Retourne le genre du fichier qui a g�n�r� l'action.
     *
     * @return String Valeur de la description en caract�re.
     */
    public String getGenreFichierAction();

    /**
     * Retourne la date de l'action.
     *
     * @return Timestamp Valeur de la date de l'action en caract�re.
     */
    public Timestamp getDateConsignation();

   /**
     * Retourne la date d'envoi du courriel.
     *
     * @return Timestamp Valeur de la date du courriel en caract�re.
     */
    public Timestamp getDateCourriel();

    /**
     * Retourne l'intervenant � suivre.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();

	/**
	 * Retourne la r�f�rence du fichier de l'action (identification autre que la cl�).
	 *
	 * @return String Valeur de referenceAction en caract�re.
	 */
	public String getReferenceAction();

	/**
	 * Retourne la r�f�rence du fichier source (autre que la cl�).
	 *
	 * @return String Valeur de referenceAction en caract�re.
	 */
	public String getReferenceSource();

    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cl� en caract�re.
     */
    public void setCle(long cle);

	/**
	 * Affecte la date de consignation.
	 *
	 * @param dateConsignation Valeur de la date de cr�ation en caract�re.
	 */
	public void setDateConsignation(Timestamp dateConsignation);

    /**
     * Affecte une date d'envoi du courriel.
     *
     * @param dateCourriel Valeur dateCourriel en caract�re.
     */
    public void setDateCourriel(Timestamp dateCourriel);

    /**
     * Affecte un type d'action.
     *
     * @param typeAction Valeur type 
     * en caract�re.
     */
    public void setTypeAction(long typeAction);

    /**
     * Affecte le num�ro du mandat.
     *
     * @param numeroMandat Valeur de numeroMandat en caract�re.
     */
    public void setNumeroMandat(String numeroMandat);

    /**
     * Retourne le genre du fichier d�fini dans le mandat.
     *
     * @param genreFichier Valeur genreFichier en caract�re.
     */
    public void setGenreFichier(String genreFichier);

    /**
     * Affecte la cl� du fichier d�fini dans le mandat.
     *
     * @param referenceSourceCle Valeur de la cl� en caract�re.
     */
    public void setReferenceSourceCle(long referenceSourceCle);

	/**
	 * Affecte le site du fichier d�fini dans le mandat.
	 *
	 * @param referenceSourceSite Valeur du site en caract�re.
	 */
	public void setReferenceSourceSite(long referenceSourceSite);

	/**
	 * Affecte la r�f�rence de l'action.
	 *
	 * @param referenceAction Valeur de referenceAction en caract�re.
	 */
	public void setReferenceAction(String referenceAction);

	/**
	 * Affecte la r�f�rence source de l'action.
	 *
	 * @param referenceSource Valeur de intervereferenceSourcenant en caract�re.
	 */
	public void setReferenceSource(String referenceSource);

    /**
     * Affecte le site du fichier qui a d�clench� l'action.
     *
     * @param referenceActionSite Valeur du site en caract�re.
     */
    public void setReferenceActionSite(long referenceActionSite);

    /**
     * Affecte la cl� du fichier de l'action.
     *
     * @param referenceActionCle Valeur du referenceActionCle en caract�re.
     */
    public void setReferenceActionCle(long referenceActionCle);

	/**
	 * Affecte le genre du fichier de l'action.
	 *
	 * @param genreFichierAction Valeur du genreFichierAction en caract�re.
	 */
	public void setGenreFichierAction(String genreFichierAction);

	/**
	 * Affecte l'intervenant de l'action.
	 *
	 * @param intervenant Valeur de intervenant en caract�re.
	 */
	public void setIntervenant(String intervenant);

}