package com.lotoquebec.cardex.business.vo;

import java.util.ArrayList;
import java.util.Collection;

import com.lotoquebec.cardex.business.Caracteristiques;

/**
 * Permet de transiter les informations relatives � la consultation d'une
 * caract�ristique de la couche pr�sentation � la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.9 $, $Date: 2002/03/12 21:39:25 $
 * @see com.lotoquebec.cardex.business.Caracteristiques
 */
public class CaracteristiquesVO implements Caracteristiques {

    private long site = 0;
    private long lien = 0;
    private long lienSite = 0;
    private ArrayList caracteristiquesChoisis = new ArrayList();

    /**
     * Constructeur de CaracteristiquesVO par d�faut.
     */
    public CaracteristiquesVO() {}


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
     * Retourne liste de caract�ristiques choisis.
     *
     * @return Collection Valeur de la liste des caract�ristiques choisis.
     */
    public Collection getCaracteristiquesChoisis() {
        return this.caracteristiquesChoisis;
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
     * Ajoute une caract�ristique.
     *
     * @param caracteristique Valeur de la caract�ristique.
     */
    public void addCaracteristique(String caracteristique) {
        this.caracteristiquesChoisis.add(caracteristique);
    }

    /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du CaracteristiquesVO.
     *
     * @return String Valeur de tout les attributs du CaracteristiquesVO en
     * caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CaracteristiquesVO : ");
        stringBuffer.append("site = '" + site);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', caracteristiquesChoisis = (");
        for ( int i = 0; i < caracteristiquesChoisis.size(); i++ ) {
            stringBuffer.append("'" + caracteristiquesChoisis.get(i) + "',");
        }
        stringBuffer.append(")]");
        return stringBuffer.toString();
    }

}