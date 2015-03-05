package com.lotoquebec.cardex.business.vo;

import java.util.ArrayList;
import java.util.Collection;

import com.lotoquebec.cardex.business.Particularites;

/**
 * Permet de transiter les informations relatives à la consultation de
 * particularités de la couche présentation à la couche d'affaire.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.8 $, $Date: 2002/04/11 18:47:39 $
 * @see com.lotoquebec.cardex.business.Particularites
 */
public class ParticularitesVO implements Particularites {

    private long site = 0;
    private String numeroDossier = "";
    private String siteDossier = "";
    private long marque = 0;
    private long modele = 0;
    private long lien = 0;
    private long lienSite = 0;
    private ArrayList particularitesChoisis = new ArrayList();

    /**
     * Constructeur de ParticularitesVO par défaut.
     */
    public ParticularitesVO() {}


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
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro de dossier en caractère.
     */
    public String getNumeroDossier() {
        return this.numeroDossier;
    }

    /**
     * Retourne le site du dossier.
     *
     * @return String Valeur du site du dossier en caractère.
     */
    public String getSiteDossier() {
        return this.siteDossier;
    }

    /**
     * Retourne la marque.
     *
     * @return long Valeur de la marque.
     */
    public long getMarque() {
        return this.marque;
    }

    /**
     * Retourne le modele.
     *
     * @return long Valeur du modele.
     */
    public long getModele() {
        return this.modele;
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
     * Retourne la liste de particularités choisis.
     *
     * @return Collection Valeur de la liste de particularités choisis.
     */
    public Collection getParticularitesChoisis() {
        return this.particularitesChoisis;
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
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    /**
     * Affecte un site du dossier.
     *
     * @param siteDossier Valeur du site du dossier en caractère.
     */
    public void setSiteDossier(String siteDossier) {
        this.siteDossier = siteDossier;
    }

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque.
     */
    public void setMarque(long marque) {
        this.marque = marque;
    }

    /**
     * Affecte un modele.
     *
     * @param modele Valeur du modele.
     */
    public void setModele(long modele) {
        this.modele = modele;
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
     * Affecte liste de particularités choisis.
     *
     * @param particularitesChoisis Valeur de la liste de particularités
     * choisis.
     */
    public void setCaracteristiquesChoisis(String [] particularitesChoisis) {
        for (int i = 0; i < particularitesChoisis.length; i++) {
            this.particularitesChoisis.add(particularitesChoisis[i]);
        }
    }

    /**
     * Ajoute une particularité.
     *
     * @param particularite Valeur de la particularité.
     */
    public void addParticularite(String particularite) {
        this.particularitesChoisis.add(particularite);
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du ParticularitesVO.
     *
     * @return String Valeur de tout les attributs du ParticularitesVO en
     * caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[ParticularitesVO : ");
        stringBuffer.append("'site = '" + site);
        stringBuffer.append("', numeroDossier = '" + numeroDossier);
        stringBuffer.append("', siteDossier = '" + siteDossier);
        stringBuffer.append("', marque = '" + marque);
        stringBuffer.append("', modele = '" + modele);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', particularitesChoisis = (");
        for ( int i = 0; i < particularitesChoisis.size(); i++ ) {
            stringBuffer.append("'" + particularitesChoisis.get(i) + "',");
        }
        stringBuffer.append(")]");
        return stringBuffer.toString();
    }

}