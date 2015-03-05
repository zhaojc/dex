package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des crit�res, n�cesssaires � la recherche d'un
 * suivi.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/03/25 20:40:38 $
 */
public interface CriteresRechercheConsignation extends CriteresRecherche{


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
     * Retourne le genre.
     *
     * @return long Valeur num�rique du genre.
     */
    public long getGenre();

    /**
     * Retourne la nature.
     *
     * @return long Valeur num�rique de la nature.
     */
    public long getNature();

    /**
     * Retourne le type.
     *
     * @return long Valeur num�rique du type.
     */
    public long getType();

    /**
     * Retourne la cat�gorie.
     *
     * @return long Valeur num�rique de la cat�gorie.
     */
    public long getCategorie();
    
    /**
     * Affecte un genre.
     *
     * @param genre Valeur num�rique du genre.
     */
    public void setGenre(long genre);

    /**
     * Affecte une nature.
     *
     * @param nature Valeur num�rique de la nature.
     */
    public void setNature(long nature);

    /**
     * Affecte un type.
     *
     * @param type Valeur num�rique du type.
     */
    public void setType(long type);

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur num�rique de la cat�gorie.
     */
    public void setCategorie(long categorie);

    /**
     * Retourne le type de consignation
     *
     * @return long Valeur du type de consignation.
     */
    public long getTypeConsignation();

	/**
	 * Retourne approuv�.
	 *
	 * @return String Valeur du statut approuv�.
	 */
	public String getApprouve();

	/**
	 * Retourne le statut non approuv�.
	 *
	 * @return String Valeur du statut non approuv�n.
	 */
	public String getNonApprouve();

    /**
     * Affecte un type.
     *
     * @param secteurOrigine Valeur du type de consignation en caract�re.
     */
    public void setTypeConsignation(long typeConsignation);

	/**
	 * Affecte un statut approuv�.
	 *
	 * @param secteurOrigine Valeur du statut approuv� en caract�re.
	 */
	public void setApprouve(String approuve);

	/**
	 * Affecte un statut non approuv�.
	 *
	 * @param secteurOrigine Valeur du statut non approuv� en caract�re.
	 */
	public void setNonApprouve(String nonApprouve);

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
     * Retourne la marque.
     *
     * @return String Valeur de la marque en caract�re.
     */
    public String getMarque();

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque en caract�re.
     */
    public void setMarque(String marque);

    /**
     * Retourne le mod�le.
     *
     * @return String Valeur du mod�le en caract�re.
     */
    public String getModele();

    /**
     * Affecte un mod�le.
     *
     * @param modele Valeur du mod�le en caract�re.
     */
    public void setModele(String modele);

    /**
     * Retourne le fournisseur.
     *
     * @return String Valeur du fournisseur en caract�re.
     */
    public String getFournisseur();

    /**
     * Affecte un fournisseur.
     *
     * @param fournisseur Valeur du fournisseur en
     * caract�re.
     */
    public void setFournisseur(String fournisseur);

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
     * Retourne le num�ro de s�rie.
     *
     * @return Timestamp Valeur du num�ro de s�rie en caract�re.
     */
    public String getNumeroSerie();

    /**
     * Affecte un num�ro de s�rie.
     *
     * @param numeroSerie Valeur du num�ro de s�rie.
     */
    public void setNumeroSerie(String numeroSerie);

    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caract�re.
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
	 * @param devise Valeur fr la devise en caract�re.
	 */
	public void setDevise(long devise);

	/**
	 * Affecte une denomination.
	 *
	 * @param denomination Valeur fr la denomination en caract�re.
	 */
	public void setDenomination(long denomination);

    /**
     * Affecte une description.
     *
     * @param description Valeur de la description en caract�re.
     */
    public void setDescription(String description);

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

    /**
     * Retourne le type de r�sultat (liste ou rapport).
     *
     * @return Collection Valeur du type.
     */
    public String getTypeResultat();

    /**
     * Affecte le type de r�sultat de recherche.
     *
     * @param liste Valeur du type de r�sultat
     */
    public void setTypeResultat(String TypeResultat);


}