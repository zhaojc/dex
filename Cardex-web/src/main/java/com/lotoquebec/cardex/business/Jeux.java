package com.lotoquebec.cardex.business;

import java.util.Collection;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives aux jeux.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.8 $, $Date: 2002/03/12 21:39:17 $
 */
public interface Jeux {


    // Getters


    /**
     * Retourne le site.
     *
     * @return long Valeur du site.
     */
    public long getSite();

    /**
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro de dossier en caractère.
     */
    public String getNumeroDossier();

    /**
     * Retourne la catégorie.
     *
     * @return long Valeur de la catégorie en caractère.
     */
    public long getCategorie();

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
     * Retourne la liste de jeux choisis.
     *
     * @return Collection Valeur de la liste de jeux choisis.
     */
    public Collection getJeuxChoisis();


    // Setters


    /**
     * Affecte un site.
     *
     * @param site Valeur du site.
     */
    public void setSite(long site);

    /**
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur de la catégorie en caractère.
     */
    public void setCategorie(long categorie);

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
     * Ajoute un jeu.
     *
     * @param jeuxChoisis Valeur de la liste de jeux choisis.
     */
    public void addJeu(String jeu);

}