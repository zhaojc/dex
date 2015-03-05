package com.lotoquebec.cardex.business.vo;

import java.util.ArrayList;
import java.util.Collection;

import com.lotoquebec.cardex.business.Jeux;

/**
 * Permet de transiter les informations relatives � la consultation de jeux de
 * la couche pr�sentation � la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/03/12 21:39:25 $
 * @see com.lotoquebec.cardex.business.Jeux
 */
public class JeuxVO implements Jeux {

    private long site = 0;
    private String numeroDossier = "";
    private long categorie = 0;
    private long lien = 0;
    private long lienSite = 0;
    private ArrayList jeuxChoisis = new ArrayList();

    /**
     * Constructeur de JeuxVO par d�faut.
     */
    public JeuxVO() {}


    // Getters


    /**
     * Retourne le site.
     *
     * @return long Valeur du site.
     */
    public long getSite() {
        return this.site;
    }

    /**
     * Retourne le num�ro de dossier.
     *
     * @return String Valeur du num�ro de fiche en caract�re.
     */
    public String getNumeroDossier() {
        return this.numeroDossier;
    }

    /**
     * Retourne la cat�gorie.
     *
     * @return long Valeur de la cat�gorie en caract�re.
     */
    public long getCategorie() {
        return this.categorie;
    }

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien.
     */
    public long getLien() {
        return this.lien;
    }

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite() {
        return this.lienSite;
    }

    /**
     * Retourne la liste de jeux choisis.
     *
     * @return Collection Valeur de la liste de jeux choisis.
     */
    public Collection getJeuxChoisis() {
        return this.jeuxChoisis;
    }



    // Setters


    /**
     * Affecte un site.
     *
     * @param site Valeur du site.
     */
    public void setSite(long site) {
        this.site = site;
    }

    /**
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur de la cat�gorie en caract�re.
     */
    public void setCategorie(long categorie) {
        this.categorie = categorie;
    }

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien.
     */
    public void setLien(long lien) {
        this.lien = lien;
    }

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite) {
        this.lienSite = lienSite;
    }

    /**
     * Ajoute un jeu.
     *
     * @param jeu Valeur du jeu.
     */
    public void addJeu(String jeu) {
        this.jeuxChoisis.add(jeu);
    }

    /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du JeuxVO.
     *
     * @return String Valeur de tout les attributs du JeuxVO en
     * caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[JeuxVO : ");
        stringBuffer.append("site = '" + site);
        stringBuffer.append("', numeroDossier = '" + numeroDossier);
        stringBuffer.append("', categorie = '" + categorie);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', jeuxChoisis = (");
        for ( int i = 0; i < jeuxChoisis.size(); i++ ) {
            stringBuffer.append("'" + jeuxChoisis.get(i) + "',");
        }
        stringBuffer.append(")]");
        return stringBuffer.toString();
    }

}