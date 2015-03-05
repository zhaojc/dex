package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à un formulaire de recherche de consignation d'informations.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/03/25 20:42:39 $
 */
public interface CriteresRechercheConsignationHtmlForm extends CriteresRecherche{

    /**
     * Retourne l'entité.
     *
     * @return String Valeur de l'entité.
     */
    public String getEntite();

    /**
     * Affecte une entité.
     *
     * @param entite Valeur de l'entité.
     */
    public void setEntite(String entite);

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine.
     */
    public String getSiteOrigine();

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine.
     */
    public void setSiteOrigine(String siteOrigine);

    // Getters


    /**
     * Retourne le genre.
     *
     * @return String Valeur du genre en caractère.
     */
    public String getGenre();

    /**
     * Retourne la nature.
     *
     * @return String Valeur de la nature en caractère.
     */
    public String getNature();

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caractère.
     */
    public String getType();

    /**
     * Retourne la catégorie.
     *
     * @return String Valeur de la catégorie en caractère.
     */
    public String getCategorie();

    /**
     * Affecte un genre.
     *
     * @param genre Valeur du genre en caractère.
     */
    public void setGenre(String genre);

    /**
     * Affecte une nature.
     *
     * @param nature Valeur de la nature en caractère.
     */
    public void setNature(String nature);

    /**
     * Affecte un type.
     *
     * @param type Valeur du type en caractère.
     */
    public void setType(String type);

    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur d'une catégorie en caractère.
     */
    public void setCategorie(String categorie);

    /**
     * Retourne le type de consignation.
     *
     * @return String Valeur du type de consignation.
     */
    public String getTypeConsignation();

	/**
	 * Retourne la devise.
	 *
	 * @return String Valeur de la devise.
	 */
	public String getDevise();

	/**
	 * Retourne la denomination.
	 *
	 * @return String Valeur de la denomination.
	 */
	public String getDenomination();

	/**
	 * Retourne approuvé.
	 *
	 * @return String Valeur du statut approuvé.
	 */
	public String getApprouve();

	/**
	 * Retourne le statut non approuvé.
	 *
	 * @return String Valeur du statut non approuvén.
	 */
	public String getNonApprouve();

    /**
     * Affecte un type.
     *
     * @param type Valeur du type de consignation en caractère.
     */
    public void setTypeConsignation(String typeConsignation);

	/**
	 * Affecte une devise.
	 *
	 * @param devise Valeur fr la devise en caractère.
	 */
	public void setDevise(String devise);

	/**
	 * Affecte une denomination.
	 *
	 * @param denomination Valeur fr la denomination en caractère.
	 */
	public void setDenomination(String denomination);

	/**
	 * Affecte un statut approuvé.
	 *
	 * @param secteurOrigine Valeur du statut approuvé en caractère.
	 */
	public void setApprouve(String approuve);

	/**
	 * Affecte un statut non approuvé.
	 *
	 * @param secteurOrigine Valeur du statut non approuvé en caractère.
	 */
	public void setNonApprouve(String nonApprouve);

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant);

    /**
     * Retourne la marque.
     *
     * @return String Valeur de la marque en caractère.
     */
    public String getMarque();

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque en caractère.
     */
    public void setMarque(String marque);

    /**
     * Retourne le modèle.
     *
     * @return String Valeur du modèle en caractère.
     */
    public String getModele();

    /**
     * Affecte un modèle.
     *
     * @param modele Valeur du modèle en caractère.
     */
    public void setModele(String modele);

    /**
     * Retourne le fournisseur.
     *
     * @return String Valeur du fournisseur en caractère.
     */
    public String getFournisseur();

    /**
     * Affecte un fournisseur.
     *
     * @param fournisseur Valeur du fournisseur en
     * caractère.
     */
    public void setFournisseur(String fournisseur);

     /**
     * Retourne la date de début.
     *
     * @return String Valeur de la date de début au début en caractère.
     */
    public String getDateDebut();

    /**
     * Affecte une date de début.
     *
     * @param dateDebut Valeur de la date de début en caractère.
     */
    public void setDateDebut(String dateDebut);

    /**
     * Retourne la date de fin.
     *
     * @return String Valeur de la date de fin en caractère.
     */
    public String getDateFin();

    /**
     * Affecte une date de fin.
     *
     * @param dateFin Valeur de la date de fin en caractère.
     */
    public void setDateFin(String dateFin);

    /**
     * Retourne le numéro de série.
     *
     * @return String Valeur du numéro de série en caractère.
     */
    public String getNumeroSerie();

    /**
     * Affecte un numéro de série.
     *
     * @param numeroSerie Valeur du numéro de série.
     */
    public void setNumeroSerie(String numeroSerie);

    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caractère.
     */
    public String getDescription();

    /**
     * Affecte une description.
     *
     * @param description Valeur de la description en caractère.
     */
    public void setDescription(String description);


}