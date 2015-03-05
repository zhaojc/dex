package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * Définit la signature des méthodes des différentes valeurs consignant toutes
 * les actions définies par des mandats PSU.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $, $Date: 2002/03/26 22:18:39 $
 */
public interface ConsignationActionPSU {


    // Getters

    /**
     * Retourne la clé.
     *
     * @return long Valeur de la clé en caractère.
     */
    public long getCle();

    /**
     * Retourne le type de l'action.
     *
     * @return long Valeur du type en caractère.
     */
    public long getTypeAction();

    /**
     * Retourne le numéro du mandat.
     *
     * @return String Valeur du mandaten caractère.
     */
    public String getNumeroMandat();

    /**
     * Retourne le genre du fichier.
     *
     * @return String Valeur du genre en caractère.
     */
    public String getGenreFichier();

    /**
     * Retourne clé de référence du fichier défini dans le mandat.
     *
     * @return long Valeur de la clé en caractère.
     */
    public long getReferenceSourceCle();

    /**
     * Retourne le site de référence.
     *
     * @return long Valeur du site en caractère.
     */
    public long getReferenceSourceSite();

    /**
     * Retourne le site du fichier qui a généré l'action.
     *
     * @return long Valeur du site en caractère.
     */
    public long getReferenceActionSite();

    /**
     * Retourne la clé du fichier qui a généré l'action.
     *
     * @return long Valeur de l'entité en caractère en caractère.
     */
    public long getReferenceActionCle();

    /**
     * Retourne le genre du fichier qui a généré l'action.
     *
     * @return String Valeur de la description en caractère.
     */
    public String getGenreFichierAction();

    /**
     * Retourne la date de l'action.
     *
     * @return Timestamp Valeur de la date de l'action en caractère.
     */
    public Timestamp getDateConsignation();

   /**
     * Retourne la date d'envoi du courriel.
     *
     * @return Timestamp Valeur de la date du courriel en caractère.
     */
    public Timestamp getDateCourriel();

    /**
     * Retourne l'intervenant à suivre.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();

	/**
	 * Retourne la référence du fichier de l'action (identification autre que la clé).
	 *
	 * @return String Valeur de referenceAction en caractère.
	 */
	public String getReferenceAction();

	/**
	 * Retourne la référence du fichier source (autre que la clé).
	 *
	 * @return String Valeur de referenceAction en caractère.
	 */
	public String getReferenceSource();

    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la clé en caractère.
     */
    public void setCle(long cle);

	/**
	 * Affecte la date de consignation.
	 *
	 * @param dateConsignation Valeur de la date de création en caractère.
	 */
	public void setDateConsignation(Timestamp dateConsignation);

    /**
     * Affecte une date d'envoi du courriel.
     *
     * @param dateCourriel Valeur dateCourriel en caractère.
     */
    public void setDateCourriel(Timestamp dateCourriel);

    /**
     * Affecte un type d'action.
     *
     * @param typeAction Valeur type 
     * en caractère.
     */
    public void setTypeAction(long typeAction);

    /**
     * Affecte le numéro du mandat.
     *
     * @param numeroMandat Valeur de numeroMandat en caractère.
     */
    public void setNumeroMandat(String numeroMandat);

    /**
     * Retourne le genre du fichier défini dans le mandat.
     *
     * @param genreFichier Valeur genreFichier en caractère.
     */
    public void setGenreFichier(String genreFichier);

    /**
     * Affecte la clé du fichier défini dans le mandat.
     *
     * @param referenceSourceCle Valeur de la clé en caractère.
     */
    public void setReferenceSourceCle(long referenceSourceCle);

	/**
	 * Affecte le site du fichier défini dans le mandat.
	 *
	 * @param referenceSourceSite Valeur du site en caractère.
	 */
	public void setReferenceSourceSite(long referenceSourceSite);

	/**
	 * Affecte la référence de l'action.
	 *
	 * @param referenceAction Valeur de referenceAction en caractère.
	 */
	public void setReferenceAction(String referenceAction);

	/**
	 * Affecte la référence source de l'action.
	 *
	 * @param referenceSource Valeur de intervereferenceSourcenant en caractère.
	 */
	public void setReferenceSource(String referenceSource);

    /**
     * Affecte le site du fichier qui a déclenché l'action.
     *
     * @param referenceActionSite Valeur du site en caractère.
     */
    public void setReferenceActionSite(long referenceActionSite);

    /**
     * Affecte la clé du fichier de l'action.
     *
     * @param referenceActionCle Valeur du referenceActionCle en caractère.
     */
    public void setReferenceActionCle(long referenceActionCle);

	/**
	 * Affecte le genre du fichier de l'action.
	 *
	 * @param genreFichierAction Valeur du genreFichierAction en caractère.
	 */
	public void setGenreFichierAction(String genreFichierAction);

	/**
	 * Affecte l'intervenant de l'action.
	 *
	 * @param intervenant Valeur de intervenant en caractère.
	 */
	public void setIntervenant(String intervenant);

}