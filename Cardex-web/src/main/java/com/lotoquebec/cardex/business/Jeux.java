package com.lotoquebec.cardex.business;

import java.util.Collection;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
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
     * Retourne le num�ro de dossier.
     *
     * @return String Valeur du num�ro de dossier en caract�re.
     */
    public String getNumeroDossier();

    /**
     * Retourne la cat�gorie.
     *
     * @return long Valeur de la cat�gorie en caract�re.
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
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur de la cat�gorie en caract�re.
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