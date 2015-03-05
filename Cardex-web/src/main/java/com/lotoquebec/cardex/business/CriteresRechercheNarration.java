package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à une recherche de narration par critères.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.10 $, $Date: 2002/04/22 18:01:04 $
 */
public interface CriteresRechercheNarration extends CriteresRecherche{


    // Getters


    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caractère.
     */
    public long getOrigine();

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
     * Retourne l'approbation.
     *
     * @return long Valeur de l'approbation.
     */
    public long getApprouve();

    /**
     * Retourne le secteur.
     *
     * @return long Valeur du secteur.
     */
    public long getSecteur();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();

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
     * Retourne la date de création au début.
     *
     * @return Timestamp Valeur de la date de création au début (yyyy-MM-dd).
     */
    public Timestamp getDateCreationDebut();

    /**
     * Retourne la date d'approbation au début.
     *
     * @return Timestamp Valeur de la date d'approbation au début (yyyy-MM-dd).
     */
    public Timestamp getDateApprobationDebut();

    /**
     * Retourne la date de création à la fin.
     *
     * @return Timestamp Valeur de la date de création à la fin (yyyy-MM-dd).
     */
    public Timestamp getDateCreationFin();

    /**
     * Retourne la date d'approbation à la fin.
     *
     * @return Timestamp Valeur de la date d'approbation à la fin (yyyy-MM-dd).
     */
    public Timestamp getDateApprobationFin();

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
     * @return long Valeur du lien.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite();

    /**
     * Retourne ala valeur Fondé du dossier.
     *
     * @return long Valeur de fonde.
     */
    public long getFonde();

    /**
     * Retourne le critère d'approbation.
     *
     * @return String Valeur du critère d'approbation dans l'écran de recherche
     * en caractère.
     */
    public String getStatutApprobation();

    /**
     * Retourne le type de recherche des narartions.
     *
     * @return String Valeur du critère type de recherche dans l'écran de recherche
     * en caractère.
     */
    public String getTypeRecherche();

    /**
     * Obtenir l'ordre de tri de l'affichage;
     * date de début de dossier ou
     * date de création narration
     * @return
     */
	public String getOrdreAffichage();
	
    // Setters


    /**
     * Affecte une approbation.
     *
     * @param approuve Valeur de l'approbation.
     */
    public void setApprouve(long approuve);

    /**
     * Affecte un secteur.
     *
     * @param secteur Valeur du secteur.
     */
    public void setSecteur(long secteur);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte une date de création au début.
     *
     * @param dateCreationDebut Valeur de la date de création au début
     * (yyyy-MM-dd).
     */
    public void setDateCreationDebut(Timestamp dateCreationDebut);

    /**
     * Affecte une date d'approbation au début.
     *
     * @param dateApprobationDebut Valeur de la date d'approbation au début
     * (yyyy-MM-dd).
     */
    public void setDateApprobationDebut(Timestamp dateApprobationDebut);

    /**
     * Affecte une date de création à la fin.
     *
     * @param dateCreationFin Valeur de la date de création à la fin
     * (yyyy-MM-dd).
     */
    public void setDateCreationFin(Timestamp dateCreationFin);

    /**
     * Affecte une date d'approbation à la fin.
     *
     * @param dateApprobationFin Valeur de la date d'approbation à la fin
     * (yyyy-MM-dd).
     */
    public void setDateApprobationFin(Timestamp dateApprobationFin);

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
     * @param lien Valeur du lien.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite);

    /**
     * Affecte la valeur Fondé du dossier.
     *
     * @param fonde Valeur de fonde.
     */
    public void setFonde(long fonde);

    /**
     * Affecte un critère d'approbation.
     *
     * @param statut Valeur du critère d'approbation dans l'écran de recherche
     * en caractère.
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
     * Obtenir l'ordre de tri de l'affichage;
     * date de début de dossier ou
     * date de création narration
     */
 	public void setOrdreAffichage(String ordreAffichage);
 	
}