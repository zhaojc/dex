package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à un formulaire de recherche des mandats PSU (suivi des utilisateurs).
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/03/25 20:42:39 $
 */
public interface CriteresRecherchePSUMandatHtmlForm extends CriteresRecherche{

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
     * Retourne le type.
     *
     * @return String Valeur du type de consignation.
     */
    public String getTypeAction();

	/**
	 * Retourne approuvé.
	 *
	 * @return boolean Valeur du statut approuvé.
	 */
	public boolean isApprouve();

	/**
	 * Retourne le statut non approuvé.
	 *
	 * @return boolean Valeur du statut non approuvén.
	 */
	public boolean isNonApprouve();

    /**
     * Affecte un type.
     *
     * @param type Valeur du type de consignation en caractère.
     */
    public void setTypeAction(String typeAction);

	/**
	 * Affecte un statut approuvé.
	 *
	 * @param secteurOrigine Valeur du statut approuvé en caractère.
	 */
	public void setApprouve(boolean approuve);

	/**
	 * Affecte un statut non approuvé.
	 *
	 * @param secteurOrigine Valeur du statut non approuvé en caractère.
	 */
	public void setNonApprouve(boolean nonApprouve);

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
     * Retourne le numéro de mandat.
     *
     * @return String Valeur du mandat en caractère.
     */
    public String getNumeroMandat();

    /**
     * Affecte un mandat.
     *
     * @param mandat Valeur de la numeroMandat en caractère.
     */
    public void setNumeroMandat(String numeroMandat);

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
    public boolean isOrdreCroissantRecherche();

    /**
     * Retourne le nombre maximum de résultats de recherche.
     *
     * @return String Valeur du nombre maximum de résultats de recherche en
     * caractère.
     */
    public String getMaximumResultatsRecherche();

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
    public void setOrdreCroissantRecherche(boolean ordreCroissantRecherche);

    /**
     * Affecte le nombre maximum de résultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de résultats
     * de recherche caractère.
     */
    public void setMaximumResultatsRecherche(String maximum);

}