package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � un formulaire de recherche de narration.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.4 $, $Date: 2002/02/25 18:15:26 $
 */
public interface CriteresRechercheNarrationHtmlForm extends CriteresRecherche{


    // Getters


    /**
     * Retourne l'approbation.
     *
     * @return String Valeur de l'approbation en caract�re.
     */
    public String getApprouve();

    /**
     * Retourne le secteur.
     *
     * @return String Valeur du secteur en caract�re.
     */
    public String getSecteur();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public String getOrigine();

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caract�re.
     */
    public String getSite();

    /**
     * Retourne le genre.
     *
     * @return String Valeur du genre en caract�re.
     */
    public String getGenre();

    /**
     * Retourne la nature.
     *
     * @return String Valeur de nature en caract�re.
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
     * Retourne l'entit�.
     *
     * @return String Valeur de l'entit� en caract�re.
     */
    public String getEntite();
 
    /**
     * Retourne l'endroit.
     *
     * @return String Valeur de l'endroit en caract�re.
     */
    public String getEndroit();

    /**
     * Retourne la localisation.
     *
     * @return String Valeur de la localisation en caract�re.
     */
    public String getLocalisation();

    /**
     * Retourne la valeur Fond� du dossier.
     *
     * @return String Valeur de fond� en caract�re.
     */
    public String getFonde();

    /**
     * Retourne la date de cr�ation au d�but.
     *
     * @return String Valeur de la date de cr�ation au d�but en caract�re.
     */
    public String getDateCreationDebut();

    /**
     * Retourne la date d'approbation au d�but.
     *
     * @return String Valeur de la date d'approbation au d�but en caract�re.
     */
    public String getDateApprobationDebut();

    /**
     * Retourne la date de cr�ation � la fin.
     *
     * @return String Valeur de la date de cr�ation � la fin en caract�re.
     */
    public String getDateCreationFin();

    /**
     * Retourne la date d'approbation � la fin.
     *
     * @return String Valeur de la date d'approbation � la fin en caract�re.
     */
    public String getDateApprobationFin();

    /**
     * Retourne le premier mot cl�.
     *
     * @return String Valeur du premier mot cl� en caract�re.
     */
    public String getMotCle1();

    /**
     * Retourne le deuxi�me mot cl�.
     *
     * @return String Valeur du deuxi�me mot cl� en caract�re.
     */
    public String getMotCle2();

    /**
     * Retourne le troisi�me mot cl�.
     *
     * @return String Valeur du troisi�me mot cl� en caract�re.
     */
    public String getMotCle3();

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caract�re.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caract�re.
     */
    public String getLienSite();

    /**
     * Retourne le type de recherche des narartions.
     *
     * @return String Valeur du crit�re type de recherche dans l'�cran de recherche
     * en caract�re.
     */
    public String getTypeRecherche();

    /**
     * Retourne le statut aprobation
     *
     * @return String Valeur du crit�re statut approbation en caract�re.
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
     * Affecte une entit�.
     *
     * @param entite Valeur de l'entit� en caract�re.
     */
    public void setEntite(String entite);

    /**
     * Affecte un site d'origine.
     *
     * @param site Valeur du site d'origine en caract�re.
     */
    public void setSite(String site);

    /**
     * Affecte un genre.
     *
     * @param type Valeur du genre en caract�re.
     */
    public void setGenre(String genre);
    
    /**
     * Affecte une nature.
     *
     * @param type Valeur de nature en caract�re.
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
     * Affecte l'endroit.
     *
     * @param endroit Valeur de l'endroit en caract�re.
     */
    public void setEndroit(String endroit);

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur de la localisation en caract�re.
     */
    public void setLocalisation(String localisation);

    /**
     * Affecte la valeur Fond� du dossier.
     *
     * @param localisation Valeur de Fond� en caract�re.
     */
    public void setFonde(String fonde);

    /**
     * Affecte l'origine.
     *
     * @param origine Valeur de l'origine en caract�re.
     */
    public void setOrigine(String origine);

    /**
     * Affecte une approbation.
     *
     * @param approuve Valeur de l'approbation en caract�re.
     */
    public void setApprouve(String approuve);

    /**
     * Affecte un secteur.
     *
     * @param secteur Valeur du secteur en caract�re.
     */
    public void setSecteur(String secteur);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte une date de cr�ation au d�but.
     *
     * @param dateCreationDebut Valeur de la date de cr�ation au d�but en
     * caract�re.
     */
    public void setDateCreationDebut(String dateCreationDebut);

    /**
     * Affecte une date d'approbation au d�but.
     *
     * @param dateApprobationDebut Valeur de la date d'approbation au d�but en
     * caract�re.
     */
    public void setDateApprobationDebut(String dateApprobationDebut);

    /**
     * Affecte une date de cr�ation � la fin.
     *
     * @param dateCreationFin Valeur de la date de cr�ation � la fin en
     * caract�re.
     */
    public void setDateCreationFin(String dateCreationFin);

    /**
     * Affecte une date d'approbation � la fin.
     *
     * @param dateApprobationFin Valeur de la date d'approbation � la fin en
     * caract�re.
     */
    public void setDateApprobationFin(String dateApprobationFin);

    /**
     * Affecte un premier mot cl�.
     *
     * @param motCle1 Valeur du premier mot cl� en caract�re.
     */
    public void setMotCle1(String motCle1);

    /**
     * Affecte un deuxi�me mot cl�.
     *
     * @param motCle2 Valeur du deuxi�me mot cl� en caract�re.
     */
    public void setMotCle2(String motCle2);

    /**
     * Affecte un troisi�me mot cl�.
     *
     * @param motCle3 Valeur du troisi�me mot cl� en caract�re.
     */
    public void setMotCle3(String motCle3);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSite(String lienSite);

    /**
     * Affecte un crit�re d'approbation.
     *
     * @param statutApprobation Valeur du crit�re d'approbation dans l'�cran de
     * recherche en caract�re.
     */
    public void setStatutApprobation(String statutApprobation);

    /**
     * Affecte un type de recherche
     *
     * @param typeRecherche Valeur du type de recherche dans l'�cran de
     * recherche en caract�re.
     */
    public void setTypeRecherche(String typeRecherche);

    /**
     * Ajoute une narration.
     *
     * @param sujet Valeur de la narration � ajouter.
     */
    public void addNarration(NarrationHtmlForm narration);

}