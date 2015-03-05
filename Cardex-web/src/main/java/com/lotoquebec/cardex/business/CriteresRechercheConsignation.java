package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des critères, nécesssaires à la recherche d'un
 * suivi.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/03/25 20:40:38 $
 */
public interface CriteresRechercheConsignation extends CriteresRecherche{


    /**
     * Retourne l'entité.
     *
     * @return long Valeur de l'entité.
     */
    public long getEntite();

    /**
     * Affecte une entité.
     *
     * @param entite Valeur de l'entité.
     */
    public void setEntite(long entite);

    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur du site d'origine.
     */
    public long getSiteOrigine();

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine.
     */
    public void setSiteOrigine(long siteOrigine);

    // Getters


    /**
     * Retourne le genre.
     *
     * @return long Valeur numérique du genre.
     */
    public long getGenre();

    /**
     * Retourne la nature.
     *
     * @return long Valeur numérique de la nature.
     */
    public long getNature();

    /**
     * Retourne le type.
     *
     * @return long Valeur numérique du type.
     */
    public long getType();

    /**
     * Retourne la catégorie.
     *
     * @return long Valeur numérique de la catégorie.
     */
    public long getCategorie();
    
    /**
     * Affecte un genre.
     *
     * @param genre Valeur numérique du genre.
     */
    public void setGenre(long genre);

    /**
     * Affecte une nature.
     *
     * @param nature Valeur numérique de la nature.
     */
    public void setNature(long nature);

    /**
     * Affecte un type.
     *
     * @param type Valeur numérique du type.
     */
    public void setType(long type);

    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur numérique de la catégorie.
     */
    public void setCategorie(long categorie);

    /**
     * Retourne le type de consignation
     *
     * @return long Valeur du type de consignation.
     */
    public long getTypeConsignation();

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
     * @param secteurOrigine Valeur du type de consignation en caractère.
     */
    public void setTypeConsignation(long typeConsignation);

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
     * @return Timestamp Valeur de la date de début au début en caractère.
     */
    public Timestamp getDateDebut();

    /**
     * Affecte une date de début.
     *
     * @param dateDebut Valeur de la date de début en caractère.
     */
    public void setDateDebut(Timestamp dateDebut);

    /**
     * Retourne la date de fin.
     *
     * @return Timestamp Valeur de la date de fin en caractère.
     */
    public Timestamp getDateFin();

    /**
     * Affecte une date de fin.
     *
     * @param dateFin Valeur de la date de fin en caractère.
     */
    public void setDateFin(Timestamp dateFin);

    /**
     * Retourne le numéro de série.
     *
     * @return Timestamp Valeur du numéro de série en caractère.
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
	 * Retourne la devise.
	 *
	 * @return long Valeur de la devise.
	 */
	public long getDevise();

	/**
	 * Retourne la denomination.
	 *
	 * @return long Valeur de la denomination.
	 */
	public long getDenomination();

	/**
	 * Affecte une devise.
	 *
	 * @param devise Valeur fr la devise en caractère.
	 */
	public void setDevise(long devise);

	/**
	 * Affecte une denomination.
	 *
	 * @param denomination Valeur fr la denomination en caractère.
	 */
	public void setDenomination(long denomination);

    /**
     * Affecte une description.
     *
     * @param description Valeur de la description en caractère.
     */
    public void setDescription(String description);

    /**
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caractère.
     */
    public String getOrdreTriRecherche();

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur booléanne indiquant si l'ordre de recherche est
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche();

    /**
     * Retourne le nombre maximum de résultats de recherche.
     *
     * @return long Valeur du nombre maximum de résultats de recherche en
     * caractère.
     */
    public long getMaximumResultatsRecherche();

   /**
     * Affecte l'ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caractère.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche);

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur booléanne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche);

    /**
     * Affecte le nombre maximum de résultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de résultats
     * de recherche caractère.
     */
    public void setMaximumResultatsRecherche(long maximum);

    /**
     * Retourne le type de résultat (liste ou rapport).
     *
     * @return Collection Valeur du type.
     */
    public String getTypeResultat();

    /**
     * Affecte le type de résultat de recherche.
     *
     * @param liste Valeur du type de résultat
     */
    public void setTypeResultat(String TypeResultat);


}