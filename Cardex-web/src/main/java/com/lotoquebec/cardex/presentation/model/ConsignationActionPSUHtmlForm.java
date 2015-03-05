package com.lotoquebec.cardex.presentation.model;

/**
 * D�finit la signature des m�thodes des diff�rentes valeurs consignant toutes
 * les actions d�finies par des mandats PSU.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $, $Date: 2002/03/26 22:18:39 $
 */
public interface ConsignationActionPSUHtmlForm {


    // Getters

    /**
     * Retourne la cl�.
     *
     * @return String Valeur de la cl� en caract�re.
     */
    public String getCle();

    /**
     * Retourne le type de l'action.
     *
     * @return String Valeur du type en caract�re.
     */
    public String getTypeAction();

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
     * @return String Valeur de la cl� en caract�re.
     */
    public String getReferenceSourceCle();

    /**
     * Retourne le site de r�f�rence.
     *
     * @return String Valeur du site en caract�re.
     */
    public String getReferenceSourceSite();

    /**
     * Retourne le site du fichier qui a g�n�r� l'action.
     *
     * @return String Valeur du site en caract�re.
     */
    public String getReferenceActionSite();

    /**
     * Retourne la cl� du fichier qui a g�n�r� l'action.
     *
     * @return String Valeur de l'entit� en caract�re en caract�re.
     */
    public String getReferenceActionCle();

    /**
     * Retourne le genre du fichier qui a g�n�r� l'action.
     *
     * @return String Valeur de la description en caract�re.
     */
    public String getGenreFichierAction();

    /**
     * Retourne la date de l'action.
     *
     * @return String Valeur de la date de l'action en caract�re.
     */
    public String getDateConsignation();

   /**
     * Retourne la date d'envoi du courriel.
     *
     * @return String Valeur de la date du courriel en caract�re.
     */
    public String getDateCourriel();

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
    public void setCle(String cle);

	/**
	 * Affecte la date de consignation.
	 *
	 * @param dateConsignation Valeur de la date de cr�ation en caract�re.
	 */
	public void setDateConsignation(String dateConsignation);

    /**
     * Affecte une date d'envoi du courriel.
     *
     * @param dateCourriel Valeur dateCourriel en caract�re.
     */
    public void setDateCourriel(String dateCourriel);

    /**
     * Affecte un type d'action.
     *
     * @param typeAction Valeur type 
     * en caract�re.
     */
    public void setTypeAction(String typeAction);

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
    public void setReferenceSourceCle(String referenceSourceCle);

	/**
	 * Affecte le site du fichier d�fini dans le mandat.
	 *
	 * @param referenceSourceSite Valeur du site en caract�re.
	 */
	public void setReferenceSourceSite(String referenceSourceSite);

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
    public void setReferenceActionSite(String referenceActionSite);

    /**
     * Affecte la cl� du fichier de l'action.
     *
     * @param referenceActionCle Valeur du referenceActionCle en caract�re.
     */
    public void setReferenceActionCle(String referenceActionCle);

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