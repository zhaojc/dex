package com.lotoquebec.cardex.business;

import java.util.Collection;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à des caractéristiques.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/03/11 16:48:12 $
 */
public interface Caracteristiques {


    // Getters


    /**
     * Retourne le site.
     *
     * @return long Valeur du site.
     */
    public long getSite();

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
     * Retourne liste de caractéristiques choisis.
     *
     * @return Collection Valeur de la liste des caractéristiques choisis.
     */
    public Collection getCaracteristiquesChoisis();


    // Setters


    /**
     * Affecte un site.
     *
     * @param site Valeur du site.
     */
    public void setSite(long site);

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
     * Ajoute une caractéristique.
     *
     * @param caracteristique Valeur de la caractéristique.
     */
    public void addCaracteristique(String caracteristique);

}