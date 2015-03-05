package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � un formulaire de recherche des mandats PSU (suivi des utilisateurs).
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/03/25 20:42:39 $
 */
public interface CriteresRecherchePSUMandatHtmlForm extends CriteresRecherche{

    /**
     * Retourne l'entit�.
     *
     * @return String Valeur de l'entit�.
     */
    public String getEntite();

    /**
     * Affecte une entit�.
     *
     * @param entite Valeur de l'entit�.
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
	 * Retourne approuv�.
	 *
	 * @return boolean Valeur du statut approuv�.
	 */
	public boolean isApprouve();

	/**
	 * Retourne le statut non approuv�.
	 *
	 * @return boolean Valeur du statut non approuv�n.
	 */
	public boolean isNonApprouve();

    /**
     * Affecte un type.
     *
     * @param type Valeur du type de consignation en caract�re.
     */
    public void setTypeAction(String typeAction);

	/**
	 * Affecte un statut approuv�.
	 *
	 * @param secteurOrigine Valeur du statut approuv� en caract�re.
	 */
	public void setApprouve(boolean approuve);

	/**
	 * Affecte un statut non approuv�.
	 *
	 * @param secteurOrigine Valeur du statut non approuv� en caract�re.
	 */
	public void setNonApprouve(boolean nonApprouve);

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant);

    /**
     * Retourne le num�ro de mandat.
     *
     * @return String Valeur du mandat en caract�re.
     */
    public String getNumeroMandat();

    /**
     * Affecte un mandat.
     *
     * @param mandat Valeur de la numeroMandat en caract�re.
     */
    public void setNumeroMandat(String numeroMandat);

     /**
     * Retourne la date de d�but.
     *
     * @return String Valeur de la date de d�but au d�but en caract�re.
     */
    public String getDateDebut();

    /**
     * Affecte une date de d�but.
     *
     * @param dateDebut Valeur de la date de d�but en caract�re.
     */
    public void setDateDebut(String dateDebut);

    /**
     * Retourne la date de fin.
     *
     * @return String Valeur de la date de fin en caract�re.
     */
    public String getDateFin();

    /**
     * Affecte une date de fin.
     *
     * @param dateFin Valeur de la date de fin en caract�re.
     */
    public void setDateFin(String dateFin);

    /**
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caract�re.
     */
    public String getOrdreTriRecherche();

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur bool�anne indiquant si l'ordre de recherche est
     * croissante.
     */
    public boolean isOrdreCroissantRecherche();

    /**
     * Retourne le nombre maximum de r�sultats de recherche.
     *
     * @return String Valeur du nombre maximum de r�sultats de recherche en
     * caract�re.
     */
    public String getMaximumResultatsRecherche();

   /**
     * Affecte l'ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caract�re.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche);

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur bool�anne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(boolean ordreCroissantRecherche);

    /**
     * Affecte le nombre maximum de r�sultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de r�sultats
     * de recherche caract�re.
     */
    public void setMaximumResultatsRecherche(String maximum);

}