package com.lotoquebec.cardex.business;
import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Contient les informations des critères nécesssaires à la recherche 
 * dans le journal de surveillance.
 *
 * @author $Author: François Guérin $
 * @version $Revision: 1.8 $, $Date: 2004/04/20 21:22:03 $
 */
public interface CriteresRechercheJournal extends CriteresRecherche{


    // Getters


    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caractère.
     */
    public long getOrigine();

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
     * Retourne le site d'origine.
     *
     * @return long Valeur du site d'origine en caractère.
     */
    public long getSite();

    /**
     * Retourne le genre.
     *
     * @return long Valeur du genre en caractère.
     */
    public long getGenre();

    /**
     * Retourne la nature.
     *
     * @return long Valeur de nature en caractère.
     */
    public long getNature();

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caractère.
     */
    public long getType();

    /**
     * Retourne la catégorie.
     *
     * @return long Valeur de la catégorie en caractère.
     */
    public long getCategorie();

    /**
     * Retourne l'entité.
     *
     * @return long Valeur de l'entité en caractère.
     */
    public long getEntite();

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
     * Retourne l'attribut fonde.
     *
     * @return long Valeur numérique de l'attribut fonde.
     */
    public long getFonde();

    /**
     * Retourne la date de création du.
     *
     * @return Timestamp Valeur de la date de création du en caractère.
     */
    public Timestamp getDateCreationDu();

    /**
     * Retourne la date de création au.
     *
     * @return Timestamp Valeur de la date de création au en caractère.
     */
    public Timestamp getDateCreationAu();

    /**
     * Retourne la description du type (pour les rapports détaillés)..
     *
     * @return String Valeur de la description en caractère.
     */
    public String getDescriptionType();

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
     * Retourne l'endroit.
     *
     * @return long Valeur de l'endroit en caractère.
     */
    public long getEndroit();

    /**
     * Retourne la localisation.
     *
     * @return long Valeur de la localisation en caractère.
     */
    public long getLocalisation();

    /**
     * Retourne le type de rapport.
     *
     * @return String Valeur du type de rapport en caractère.
     */
    public String getTypeRapport();

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
     * @return String Valeur du nombre maximum de résultats de recherche en
     * caractère.
     */
    public long getMaximumResultatsRecherche();


    // Setters


    /**
     * Affecte une entité.
     *
     * @param entite Valeur de l'entité en caractère.
     */
    public void setEntite(long entite);

    /**
     * Affecte un site d'origine.
     *
     * @param site Valeur du site d'origine en caractère.
     */
    public void setSite(long site);

    /**
     * Affecte un genre.
     *
     * @param type Valeur du genre en caractère.
     */
    public void setGenre(long genre);
    
    /**
     * Affecte une nature.
     *
     * @param nature Valeur de nature en caractère.
     */
    public void setNature(long nature);
    
    /**
     * Affecte un type.
     *
     * @param type Valeur du type en caractère.
     */
    public void setType(long type);

    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur d'une catégorie en caractère.
     */
    public void setCategorie(long categorie);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte un groupe.
     *
     * @param groupe Valeur du secteur en caractère.
     */
    public void setSecteur(String secteur);

    /**
     * Affecte une date de création du.
     *
     * @param dateCreationDu Valeur de la date de création du en caractère.
     */
    public void setDateCreationDu(Timestamp dateCreationDu);

    /**
     * Affecte une date de création au.
     *
     * @param dateCreationAu Valeur de la date de création au en caractère.
     */
    public void setDateCreationAu(Timestamp dateCreationAu);

    /**
     * Affecte une description de type.
     *
     * @param type Valeur du type en caractère.
     */
    public void setDescriptionType(String descriptionType);

    /**
     * Affecte le type de rapport.
     *
     * @param typeRapport Valeur du type de rapport en caractère.
     */
    public void setTypeRapport(String typeRapport);

    /**
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte l'origine.
     *
     * @param origine Valeur de l'origine en caractère.
     */
    public void setOrigine(long origine);

    /**
     * Affecte l'attribut fonde.
     *
     * @param fonde Valeur numérique de l'attribut fonde.
     */
    public void setFonde(long fonde);

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