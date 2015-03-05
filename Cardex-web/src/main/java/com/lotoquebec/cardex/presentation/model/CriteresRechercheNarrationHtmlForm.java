package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à un formulaire de recherche de narration.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.4 $, $Date: 2002/02/25 18:15:26 $
 */
public interface CriteresRechercheNarrationHtmlForm extends CriteresRecherche{


    // Getters


    /**
     * Retourne l'approbation.
     *
     * @return String Valeur de l'approbation en caractère.
     */
    public String getApprouve();

    /**
     * Retourne le secteur.
     *
     * @return String Valeur du secteur en caractère.
     */
    public String getSecteur();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();

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
     * Retourne la valeur Fondé du dossier.
     *
     * @return String Valeur de fondé en caractère.
     */
    public String getFonde();

    /**
     * Retourne la date de création au début.
     *
     * @return String Valeur de la date de création au début en caractère.
     */
    public String getDateCreationDebut();

    /**
     * Retourne la date d'approbation au début.
     *
     * @return String Valeur de la date d'approbation au début en caractère.
     */
    public String getDateApprobationDebut();

    /**
     * Retourne la date de création à la fin.
     *
     * @return String Valeur de la date de création à la fin en caractère.
     */
    public String getDateCreationFin();

    /**
     * Retourne la date d'approbation à la fin.
     *
     * @return String Valeur de la date d'approbation à la fin en caractère.
     */
    public String getDateApprobationFin();

    /**
     * Retourne le premier mot clé.
     *
     * @return String Valeur du premier mot clé en caractère.
     */
    public String getMotCle1();

    /**
     * Retourne le deuxième mot clé.
     *
     * @return String Valeur du deuxième mot clé en caractère.
     */
    public String getMotCle2();

    /**
     * Retourne le troisième mot clé.
     *
     * @return String Valeur du troisième mot clé en caractère.
     */
    public String getMotCle3();

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caractère.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caractère.
     */
    public String getLienSite();

    /**
     * Retourne le type de recherche des narartions.
     *
     * @return String Valeur du critère type de recherche dans l'écran de recherche
     * en caractère.
     */
    public String getTypeRecherche();

    /**
     * Retourne le statut aprobation
     *
     * @return String Valeur du critère statut approbation en caractère.
     */
    public String getStatutApprobation();

    /**
     * Retourne une collection de narrations.
     *
     * @return Collection Valeur de la collection de narrations.
     */
    public Collection getNarrations();


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
     * Affecte la valeur Fondé du dossier.
     *
     * @param localisation Valeur de Fondé en caractère.
     */
    public void setFonde(String fonde);

    /**
     * Affecte l'origine.
     *
     * @param origine Valeur de l'origine en caractère.
     */
    public void setOrigine(String origine);

    /**
     * Affecte une approbation.
     *
     * @param approuve Valeur de l'approbation en caractère.
     */
    public void setApprouve(String approuve);

    /**
     * Affecte un secteur.
     *
     * @param secteur Valeur du secteur en caractère.
     */
    public void setSecteur(String secteur);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte une date de création au début.
     *
     * @param dateCreationDebut Valeur de la date de création au début en
     * caractère.
     */
    public void setDateCreationDebut(String dateCreationDebut);

    /**
     * Affecte une date d'approbation au début.
     *
     * @param dateApprobationDebut Valeur de la date d'approbation au début en
     * caractère.
     */
    public void setDateApprobationDebut(String dateApprobationDebut);

    /**
     * Affecte une date de création à la fin.
     *
     * @param dateCreationFin Valeur de la date de création à la fin en
     * caractère.
     */
    public void setDateCreationFin(String dateCreationFin);

    /**
     * Affecte une date d'approbation à la fin.
     *
     * @param dateApprobationFin Valeur de la date d'approbation à la fin en
     * caractère.
     */
    public void setDateApprobationFin(String dateApprobationFin);

    /**
     * Affecte un premier mot clé.
     *
     * @param motCle1 Valeur du premier mot clé en caractère.
     */
    public void setMotCle1(String motCle1);

    /**
     * Affecte un deuxième mot clé.
     *
     * @param motCle2 Valeur du deuxième mot clé en caractère.
     */
    public void setMotCle2(String motCle2);

    /**
     * Affecte un troisième mot clé.
     *
     * @param motCle3 Valeur du troisième mot clé en caractère.
     */
    public void setMotCle3(String motCle3);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(String lienSite);

    /**
     * Affecte un critère d'approbation.
     *
     * @param statutApprobation Valeur du critère d'approbation dans l'écran de
     * recherche en caractère.
     */
    public void setStatutApprobation(String statutApprobation);

    /**
     * Affecte un type de recherche
     *
     * @param typeRecherche Valeur du type de recherche dans l'écran de
     * recherche en caractère.
     */
    public void setTypeRecherche(String typeRecherche);

    /**
     * Ajoute une narration.
     *
     * @param sujet Valeur de la narration à ajouter.
     */
    public void addNarration(NarrationHtmlForm narration);

}