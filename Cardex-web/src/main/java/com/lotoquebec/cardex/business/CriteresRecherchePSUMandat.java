package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des crit�res, n�cesssaires � la recherche d'un
 * mandat.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/03/25 20:40:38 $
 */
public interface CriteresRecherchePSUMandat extends CriteresRecherche{


    /**
     * Retourne l'entit�.
     *
     * @return long Valeur de l'entit�.
     */
    public long getEntite();

    /**
     * Affecte une entit�.
     *
     * @param entite Valeur de l'entit�.
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
     * @param typeAction Valeur du typeAction en caract�re.
     */
    public void setTypeAction(long typeAction);

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
     * Retourne la date de d�but.
     *
     * @return Timestamp Valeur de la date de d�but au d�but en caract�re.
     */
    public Timestamp getDateDebut();

    /**
     * Affecte une date de d�but.
     *
     * @param dateDebut Valeur de la date de d�but en caract�re.
     */
    public void setDateDebut(Timestamp dateDebut);

    /**
     * Retourne la date de fin.
     *
     * @return Timestamp Valeur de la date de fin en caract�re.
     */
    public Timestamp getDateFin();

    /**
     * Affecte une date de fin.
     *
     * @param dateFin Valeur de la date de fin en caract�re.
     */
    public void setDateFin(Timestamp dateFin);

    /**
     * Retourne le num�ro de mandat.
     *
     * @return String Valeur du num�ro de mandat en caract�re.
     */
    public String getNumeroMandat();

    /**
     * Affecte un num�ro de mandat.
     *
     * @param numeroMandat Valeur du num�ro de mandat.
     */
    public void setNumeroMandat(String numeroMandat);

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
    public Boolean isOrdreCroissantRecherche();

    /**
     * Retourne le nombre maximum de r�sultats de recherche.
     *
     * @return long Valeur du nombre maximum de r�sultats de recherche en
     * caract�re.
     */
    public long getMaximumResultatsRecherche();

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
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche);

    /**
     * Affecte le nombre maximum de r�sultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de r�sultats
     * de recherche caract�re.
     */
    public void setMaximumResultatsRecherche(long maximum);
}