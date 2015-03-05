package com.lotoquebec.cardex.presentation.model;

/**
 * D�finit la signature des m�thodes des diff�rentes valeurs relatives au
 * formulatire de consultation des consignations.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $, $Date: 2002/03/26 22:18:39 $
 */
public interface RapportConsignationHtmlForm {


    // Getters

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caract�re.
     */
    public String getSite();

    /**
     * Retourne la devise.
     *
     * @return String Valeur de la devise.
     */
    public String getDevise();

    /**
     * Retourne le type de consignation.
     *
     * @return String Valeur du type en caract�re.
     */
    public String getTypeConsignation();

    /**
     * Retourne la quantite.
     *
     * @return String Valeur de la quantit� en caract�re.
     */
    public String getQuantite();

    /**
     * Retourne le prix unitaire.
     *
     * @return String Valeur du prix en caract�re.
     */
    public String getPrix();

	/**
	 * Retourne la d�nomination.
	 *
	 * @return String Valeur de la d�nomination en caract�re.
	 */
	public String getDenomination();

    /**
     * Retourne le montant.
     *
     * @return String Valeur du montant en caract�re.
     */
    public String getMontant();

    /**
     * Retourne la perte.
     *
     * @return String Valeur de la perte en caract�re.
     */
    public String getPerte();


    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caract�re.
     */
    public String getDescription();

    /**
     * Retourne le num�ro de s�rie.
     *
     * @return String Valeur du num�ro de s�rie en caract�re.
     */
    public String getNumeroSerie();

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
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(String site);

    /**
     * Affecte une description.
     *
     * @return description Valeur de la description en caract�re.
     */
    public void setDescription(String description);

    /**
     * Affecte une devise.
     *
     * @param devise Valeur de la devise en caract�re.
     */
    public void setDevise(String devise);

	/**
	 * Affecte une d�nomination.
	 *
	 * @param dimension Valeur de la d�nomination en caract�re.
	 */
	public void setDenomination(String denomination);

	/**
	 * Affecte un montant.
	 *
	 * @param modele Valeur du montant en
	 * caract�re.
	 */
	public void setMontant(String montant);

    /**
     * Affecte un num�ro de s�rie.
     *
     * @param numeroSerie Valeur du num�ro de s�rie en
     * caract�re.
     */
    public void setNumeroSerie(String numeroSerie);

    /**
     * Affecte une perte.
     *
     * @param poids Valeur de la perte
     * en caract�re.
     */
    public void setPerte(String perte);

    /**
     * Affecte un prix.
     *
     * @param prix Valeur du prix
     */
    public void setPrix(String prix);

    /**
     * Affecte une quantite.
     *
     * @param quantite Valeur de la quantite du
     * cr�ateur.
     */
    public void setQuantite(String quantite);

    /**
     * Affecte une date de d�but.
     *
     * @param dateDebut Valeur de dateDebut en caract�re.
     */
    public void setDateDebut(String dateDebut);

    /**
     * Affecte une date de fin.
     *
     * @param dateFin Valeur de dateFin en caract�re.
     */
    public void setDateFin(String dateFin);

    /**
     * Affecte un type de consignation.
     *
     * @param typeConsignation Valeur type de consignation 
     * en caract�re.
     */
    public void setTypeConsignation(
            String typeConsignation);

}