package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � un formulaire de recherche de consignation d'informations.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.2 $, $Date: 2002/03/25 20:42:39 $
 */
public interface CriteresRechercheConsignationHtmlForm extends CriteresRecherche{

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
     * Retourne le genre.
     *
     * @return String Valeur du genre en caract�re.
     */
    public String getGenre();

    /**
     * Retourne la nature.
     *
     * @return String Valeur de la nature en caract�re.
     */
    public String getNature();

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caract�re.
     */
    public String getType();

    /**
     * Retourne la cat�gorie.
     *
     * @return String Valeur de la cat�gorie en caract�re.
     */
    public String getCategorie();

    /**
     * Affecte un genre.
     *
     * @param genre Valeur du genre en caract�re.
     */
    public void setGenre(String genre);

    /**
     * Affecte une nature.
     *
     * @param nature Valeur de la nature en caract�re.
     */
    public void setNature(String nature);

    /**
     * Affecte un type.
     *
     * @param type Valeur du type en caract�re.
     */
    public void setType(String type);

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur d'une cat�gorie en caract�re.
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
     * @param type Valeur du type de consignation en caract�re.
     */
    public void setTypeConsignation(String typeConsignation);

	/**
	 * Affecte une devise.
	 *
	 * @param devise Valeur fr la devise en caract�re.
	 */
	public void setDevise(String devise);

	/**
	 * Affecte une denomination.
	 *
	 * @param denomination Valeur fr la denomination en caract�re.
	 */
	public void setDenomination(String denomination);

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
     * Retourne le num�ro de s�rie.
     *
     * @return String Valeur du num�ro de s�rie en caract�re.
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
     * Affecte une description.
     *
     * @param description Valeur de la description en caract�re.
     */
    public void setDescription(String description);


}