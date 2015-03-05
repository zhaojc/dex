package com.lotoquebec.cardex.presentation.model;

/**
 * Définit la signature des méthodes des différentes valeurs relatives au
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
     * @return String Valeur du site en caractère.
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
     * @return String Valeur du type en caractère.
     */
    public String getTypeConsignation();

    /**
     * Retourne la quantite.
     *
     * @return String Valeur de la quantité en caractère.
     */
    public String getQuantite();

    /**
     * Retourne le prix unitaire.
     *
     * @return String Valeur du prix en caractère.
     */
    public String getPrix();

	/**
	 * Retourne la dénomination.
	 *
	 * @return String Valeur de la dénomination en caractère.
	 */
	public String getDenomination();

    /**
     * Retourne le montant.
     *
     * @return String Valeur du montant en caractère.
     */
    public String getMontant();

    /**
     * Retourne la perte.
     *
     * @return String Valeur de la perte en caractère.
     */
    public String getPerte();


    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caractère.
     */
    public String getDescription();

    /**
     * Retourne le numéro de série.
     *
     * @return String Valeur du numéro de série en caractère.
     */
    public String getNumeroSerie();

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
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(String site);

    /**
     * Affecte une description.
     *
     * @return description Valeur de la description en caractère.
     */
    public void setDescription(String description);

    /**
     * Affecte une devise.
     *
     * @param devise Valeur de la devise en caractère.
     */
    public void setDevise(String devise);

	/**
	 * Affecte une dénomination.
	 *
	 * @param dimension Valeur de la dénomination en caractère.
	 */
	public void setDenomination(String denomination);

	/**
	 * Affecte un montant.
	 *
	 * @param modele Valeur du montant en
	 * caractère.
	 */
	public void setMontant(String montant);

    /**
     * Affecte un numéro de série.
     *
     * @param numeroSerie Valeur du numéro de série en
     * caractère.
     */
    public void setNumeroSerie(String numeroSerie);

    /**
     * Affecte une perte.
     *
     * @param poids Valeur de la perte
     * en caractère.
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
     * créateur.
     */
    public void setQuantite(String quantite);

    /**
     * Affecte une date de début.
     *
     * @param dateDebut Valeur de dateDebut en caractère.
     */
    public void setDateDebut(String dateDebut);

    /**
     * Affecte une date de fin.
     *
     * @param dateFin Valeur de dateFin en caractère.
     */
    public void setDateFin(String dateFin);

    /**
     * Affecte un type de consignation.
     *
     * @param typeConsignation Valeur type de consignation 
     * en caractère.
     */
    public void setTypeConsignation(
            String typeConsignation);

}