package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à un formulaire de recherche de journal.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.8 $, $Date: 2002/02/22 21:55:43 $
 */
public interface CriteresRechercheJournalHtmlForm extends CriteresRecherche{


    // Getters


    /**
     * Retourne la description du lieu.
     *
     * @return String Valeur de la description en caractère.
     */
    public String getDescriptif();

    /**
     * Retourne le numéro d'employé.
     *
     * @return String Valeur du numéro d'employé en caractère.
     */
    public String getNumeroEmploye();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caractère.
     */
    public String getOrigine();

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caractère.
     */
    public String getSite();

    /**
     * Retourne le genre.
     *
     * @return String Valeur du genre en caractère.
     */
    public String getGenre();

    /**
     * Retourne la nature.
     *
     * @return String Valeur de nature en caractère.
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
     * Retourne l'entité.
     *
     * @return String Valeur de l'entité en caractère.
     */
    public String getEntite();

    /**
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro de dossier en caractère.
     */
    public String getNumeroDossier();

    /**
     * Retourne la deuxième référence.
     *
     * @return String Valeur de la deuxième référence en caractère.
     */
    public String getReference2();

    /**
     * Retourne la troisième référence.
     *
     * @return String Valeur de la troisième référence en caractère.
     */
    public String getReference3();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();

    /**
     * Retourne le secteur de sécurité.
     *
     * @return String Valeur du groupe en caractère.
     */
    public String getSecteur();

    /**
     * Retourne l'attribut "fonde".
     *
     * @return String Valeur de l'attribut "fonde" en caractère.
     */
    public String getFonde();

    /**
     * Retourne la date de création du.
     *
     * @return String Valeur de la date de création du en caractère.
     */
    public String getDateCreationDu();

    /**
     * Retourne la date de création au.
     *
     * @return String Valeur de la date de création au en caractère.
     */
    public String getDateCreationAu();

    /**
     * Retourne l'endroit.
     *
     * @return String Valeur de l'endroit en caractère.
     */
    public String getEndroit();

    /**
     * Retourne la localisation.
     *
     * @return String Valeur de la localisation en caractère.
     */
    public String getLocalisation();

    /**
     * Retourne le type de rapport.
     *
     * @return String Valeur du type de rapport en caractère.
     */
    public String getTypeRapport();

    /**
     * Retourne la description du type (pour les rapports détaillés)..
     *
     * @return String Valeur de la description en caractère.
     */
    public String getDescriptionType();

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
     * Retourne une collection de journaux.
     *
     * @return Collection Valeur de la collection de journaux.
     */
    public Collection getJournaux();


    // Setters


    /**
     * Affecte une entité.
     *
     * @param entite Valeur de l'entité en caractère.
     */
    public void setEntite(String entite);

    /**
     * Affecte un site d'origine.
     *
     * @param site Valeur du site d'origine en caractère.
     */
    public void setSite(String site);

    /**
     * Affecte une description de type.
     *
     * @param type Valeur du type en caractère.
     */
    public void setDescriptionType(String descriptionType);

    /**
     * Affecte un genre.
     *
     * @param type Valeur du genre en caractère.
     */
    public void setGenre(String genre);
    
    /**
     * Affecte une nature.
     *
     * @param type Valeur de nature en caractère.
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
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String numeroDossier);

   /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte l'attribut "fonde".
     *
     * @param fonde Valeur de l'attribut "fonde" en caractère.
     */
    public void setFonde(String fonde);

    /**
     * Affecte un groupe de sécurité.
     *
     * @param secteur Valeur du groupe en caractère.
     */
    public void setSecteur(String secteur);

    /**
     * Affecte une date de création du.
     *
     * @param dateCreationDu Valeur de la date de création du en caractère.
     */
    public void setDateCreationDu(String dateCreationDu);

    /**
     * Affecte une date de création au.
     *
     * @param dateCreationAu Valeur de la date de création au en caractère.
     */
    public void setDateCreationAu(String dateCreationAu);

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur de l'endroit en caractère.
     */
    public void setEndroit(String endroit);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur de la localisation en caractère.
     */
    public void setLocalisation(String localisation);

    /**
     * Affecte une deuxième référence.
     *
     * @param reference2 Valeur de la deuxième référence en caractère.
     */
    public void setReference2(String reference2);

    /**
     * Affecte une troisième référence.
     *
     * @param reference3 Valeur de la troisième référence en caractère.
     */
    public void setReference3(String reference3);

    /**
     * Affecte le type de rapport.
     *
     * @param typeRapport Valeur du type de rapport en caractère.
     */
    public void setTypeRapport(String typeRapport);

    /**
     * Affecte la description du lieu.
     *
     * @param descriptif Valeur de la description en caractère.
     */
    public void setDescriptif(String descriptif);

    /**
     * Affecte le numéro d'employé.
     *
     * @param numeroEmploye Valeur du numéro d'employé en caractère.
     */
    public void setNumeroEmploye(String numeroEmploye);

    /**
     * Affecte l'origine.
     *
     * @param origine Valeur de l'origine en caractère.
     */
    public void setOrigine(String origine);

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

    /**
     * Ajoute un journal.
     *
     * @param journal Valeur du journal à ajouter.
     */
    public void addJournal(JournalHtmlForm journal);

}