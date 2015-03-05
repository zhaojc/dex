package com.lotoquebec.cardex.business;

import java.util.Collection;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives aux particularités.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.8 $, $Date: 2002/04/11 18:47:34 $
 */
public interface Particularites {


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
     * Retourne le site du dossier.
     *
     * @return String Valeur du site du dossier en caractère.
     */
    public String getSiteDossier();

    /**
     * Retourne la marque.
     *
     * @return long Valeur de la marque.
     */
    public long getMarque();

    /**
     * Retourne le modele.
     *
     * @return long Valeur du modele.
     */
    public long getModele();

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
     * Retourne la collection de particularités choisis.
     *
     * @return String [] Valeur de la liste de particularités choisis.
     */
    public Collection getParticularitesChoisis();


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
     * Affecte un site du dossier.
     *
     * @param siteDossier Valeur du site du dossier en caractère.
     */
    public void setSiteDossier(String siteDossier);

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque.
     */
    public void setMarque(long marque);

    /**
     * Affecte un modele.
     *
     * @param modele Valeur du modele.
     */
    public void setModele(long modele);

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
     * Affecte liste de particularités choisis.
     *
     * @param particularitesChoisis Valeur de la liste de particularités
     * choisis.
     */
    public void setCaracteristiquesChoisis(String [] particularitesChoisis);

    /**
     * Ajoute une particularité.
     *
     * @param particularite Valeur de la particularité.
     */
    public void addParticularite(String particularite);

}