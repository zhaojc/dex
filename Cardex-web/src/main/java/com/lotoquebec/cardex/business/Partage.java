package com.lotoquebec.cardex.business;

import java.util.Collection;

/**
 * D�finit la signature des m�thodes des diff�rentes valeurs relatives au
 * formulaire de l'onglet Partage.
 *
 * @author $Author: guerinf $
 * @version $Revision: 1.4 $, $Date: 2009/11/02 19:10:18 $
 */
public interface Partage {


    // Getters


    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle en caract�re.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site en caract�re.
     */
    public long getSite();

    /**
     * Retourne le nom de l'intervenant qui cr�e le partage.
     *
     * @return String Valeur du nom en caract�re.
     */
    public String getIntervenant();


    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien en caract�re.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site en caract�re.
     */
    public long getLienSite();

    /**
     * Retourne le profil de l'intervenant.
     *
     * @return String Valeur du profil en caract�re.
     */
    public String getProfil();

    /**
     * Retourne le genrePartage.
     *
     * @return String Valeur du genrePartage en caract�re.
     */
    public String getGenrePartage();

    /**
     * Retourne le site de l'intervenant.
     *
     * @return long Valeur du site en caract�re.
     */
    public long getSiteIntervenant();


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caract�re.
     */
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(long site);

    /**
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caract�re.
     */
    public void setIntervenant(String nom);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSite(long lienSite);

    /**
     * Affecte un profil de l'intervenant.
     *
     * @param profil Valeur du profil en caract�re.
     */
    public void setProfil(String profil);

    /**
     * Affecte un genrePartage de l'intervenant.
     *
     * @param profil Valeur du genrePartage en caract�re.
     */
    public void setGenrePartage(String genrePartage);

    /**
     * Affecte un site.
     *
     * @param siteIntervenant Valeur du site de l'intervenant en caract�re.
     */
    public void setSiteIntervenant(long siteIntervenant);

 
    /**
     * Retourne la liste des intervenants choisis.
     *
     * @return Collection Valeur de la liste des intervenants choisis.
     */
    public Collection getIntervenantsChoisis();

    /**
     * Ajoute un intervenant.
     *
     * @param caracteristique Valeur de l'intervenant.
     */
    public void addIntervenant(String intervenant);   
}