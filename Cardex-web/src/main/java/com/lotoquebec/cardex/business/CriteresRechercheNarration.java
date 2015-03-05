package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � une recherche de narration par crit�res.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.10 $, $Date: 2002/04/22 18:01:04 $
 */
public interface CriteresRechercheNarration extends CriteresRecherche{


    // Getters


    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public long getOrigine();

    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur du site d'origine en caract�re.
     */
    public long getSite();

    /**
     * Retourne le genre.
     *
     * @return long Valeur du genre en caract�re.
     */
    public long getGenre();

    /**
     * Retourne la nature.
     *
     * @return long Valeur de nature en caract�re.
     */
    public long getNature();

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caract�re.
     */
    public long getType();

    /**
     * Retourne la cat�gorie.
     *
     * @return long Valeur de la cat�gorie en caract�re.
     */
    public long getCategorie();

    /**
     * Retourne l'entit�.
     *
     * @return long Valeur de l'entit� en caract�re.
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
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();

    /**
     * Retourne l'endroit.
     *
     * @return long Valeur de l'endroit en caract�re.
     */
    public long getEndroit();

    /**
     * Retourne la localisation.
     *
     * @return long Valeur de la localisation en caract�re.
     */
    public long getLocalisation();

    /**
     * Retourne la date de cr�ation au d�but.
     *
     * @return Timestamp Valeur de la date de cr�ation au d�but (yyyy-MM-dd).
     */
    public Timestamp getDateCreationDebut();

    /**
     * Retourne la date d'approbation au d�but.
     *
     * @return Timestamp Valeur de la date d'approbation au d�but (yyyy-MM-dd).
     */
    public Timestamp getDateApprobationDebut();

    /**
     * Retourne la date de cr�ation � la fin.
     *
     * @return Timestamp Valeur de la date de cr�ation � la fin (yyyy-MM-dd).
     */
    public Timestamp getDateCreationFin();

    /**
     * Retourne la date d'approbation � la fin.
     *
     * @return Timestamp Valeur de la date d'approbation � la fin (yyyy-MM-dd).
     */
    public Timestamp getDateApprobationFin();

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
     * Retourne ala valeur Fond� du dossier.
     *
     * @return long Valeur de fonde.
     */
    public long getFonde();

    /**
     * Retourne le crit�re d'approbation.
     *
     * @return String Valeur du crit�re d'approbation dans l'�cran de recherche
     * en caract�re.
     */
    public String getStatutApprobation();

    /**
     * Retourne le type de recherche des narartions.
     *
     * @return String Valeur du crit�re type de recherche dans l'�cran de recherche
     * en caract�re.
     */
    public String getTypeRecherche();

    /**
     * Obtenir l'ordre de tri de l'affichage;
     * date de d�but de dossier ou
     * date de cr�ation narration
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
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte une date de cr�ation au d�but.
     *
     * @param dateCreationDebut Valeur de la date de cr�ation au d�but
     * (yyyy-MM-dd).
     */
    public void setDateCreationDebut(Timestamp dateCreationDebut);

    /**
     * Affecte une date d'approbation au d�but.
     *
     * @param dateApprobationDebut Valeur de la date d'approbation au d�but
     * (yyyy-MM-dd).
     */
    public void setDateApprobationDebut(Timestamp dateApprobationDebut);

    /**
     * Affecte une date de cr�ation � la fin.
     *
     * @param dateCreationFin Valeur de la date de cr�ation � la fin
     * (yyyy-MM-dd).
     */
    public void setDateCreationFin(Timestamp dateCreationFin);

    /**
     * Affecte une date d'approbation � la fin.
     *
     * @param dateApprobationFin Valeur de la date d'approbation � la fin
     * (yyyy-MM-dd).
     */
    public void setDateApprobationFin(Timestamp dateApprobationFin);

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
     * Affecte la valeur Fond� du dossier.
     *
     * @param fonde Valeur de fonde.
     */
    public void setFonde(long fonde);

    /**
     * Affecte un crit�re d'approbation.
     *
     * @param statut Valeur du crit�re d'approbation dans l'�cran de recherche
     * en caract�re.
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
     * Obtenir l'ordre de tri de l'affichage;
     * date de d�but de dossier ou
     * date de cr�ation narration
     */
 	public void setOrdreAffichage(String ordreAffichage);
 	
}