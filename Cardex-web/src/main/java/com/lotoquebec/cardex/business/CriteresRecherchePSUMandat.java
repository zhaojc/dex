package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des critères, nécesssaires à la recherche d'un
 * mandat.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/03/25 20:40:38 $
 */
public interface CriteresRecherchePSUMandat extends CriteresRecherche{


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
     * Retourne le type.
     *
     * @return long Valeur du type d'action.
     */
    public long getTypeAction();

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
     * @param typeAction Valeur du typeAction en caractère.
     */
    public void setTypeAction(long typeAction);

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
     * Retourne le numéro de mandat.
     *
     * @return String Valeur du numéro de mandat en caractère.
     */
    public String getNumeroMandat();

    /**
     * Affecte un numéro de mandat.
     *
     * @param numeroMandat Valeur du numéro de mandat.
     */
    public void setNumeroMandat(String numeroMandat);

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
}