package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import com.lotoquebec.cardex.business.Acces;
/**
 * Implantation de l'interface Acces.
 * Les accès aux données du système sont compilés dans la table AC_ACCES.
 * Les données compilées peuvent être consultés avec le bouton Audit sur 
 * les écrans de consultation (Dossiers, Sujets, Sociétés et Véhicules).
 *
 * @author $Author: François Guérin $
 * @version $Revison: $, $Date: 2002/06/11 00:25:27 $
 */
public class AccesVO implements Acces {
    private long       cle = 0;
    private long       site = 0;
    private long       siteOrigine = 0;
    private long       cleOrigine = 0;
    private long       cleRef = 0;
    private long       cleRef2 = 0;
    private long       siteRef = 0;
    private long       siteRef2 = 0;
    private String     action = "";
    private Timestamp  dateAcces = null;
    private Timestamp  dateCreation = null;
    private String     createur = "";
    private String     genreOrigine = "";
    private String     genreRef = "";
    private String     genreRef2 = "";
    private long       hierarchie = 0;
    private String     utilisateur = "";
    private Collection dossiers = new ArrayList();

    /**
     * Constructeur de AccesVO par défaut.
     */
    public AccesVO() {}
    
    // Getters

    /**
     * Retourne la clé (L_AC_CLE).
     *
     * @return long Valeur numérique de la clé
     */
    public long getCle(){
        return this.cle;
    }


    /**
     * Retourne le site (L_SI_SITE).
     *
     * @return long Valeur numérique du site d'où provient l'accès
     */

    public long getSite(){
        return this.site;
    }

    /**
     * Retourne le site d'origine (L_ORI_SITE).
     *
     * @return long Valeur numérique du site d'origine.
     */
    public long getSiteOrigine(){
        return this.siteOrigine;
    }
    /**
     * Retourne le genre (C_GF_REFERENCE).
     *
     * @return String Valeur du genre.
     */
    public String getGenreRef(){
        return this.genreRef;
    }

    /**
     * Retourne la clé d'origine (L_ORI_CLE).
     *
     * @return long Valeur numérique de la clé.
     */
    public long getCleOrigine(){
        return this.cleOrigine;
    }

    /**
     * Retourne la clé de référence (L_REF_CLE).
     *
     * @return long Valeur numérique dela clé.
     */
    public long getCleRef(){
        return this.cleRef;
    }

    /**
     * Retourne la référence du fichier (C_GF_ORIGINE).
     * Correspond au fichier sur lequel on conserve un audit.
     *
     * @return String Valeur du genre en caractère.
     */
    public String getGenreOrigine(){
        return this.genreOrigine;
    }

    /**
     * Retourne le site de référence (L_REF_SITE).
     *
     * @return long Valeur numérique du site.
     */
    public long getSiteRef(){
        return this.siteRef;
    }

    /**
     * Retourne le code de l'utilisateur qui fait l'accès (V_AC_NAME).
     *
     * @return String Valeur du code de l'utilisateur.
     */
    public String getUtilisateur(){
        return this.utilisateur;
    }

    /**
     * Retourne la date de l'accès (D_AC_DATE_ACCES).
     *
     * @return Timestamp Valeur de la date (yyyy-MM-dd).
     */
    public Timestamp getDateAcces(){
        return this.dateAcces;
    }

     /**
     * Retourne la clé de référence 2 (L_REF2_CLE).
     *
     * @return long Valeur de la clé.
     */
    public long getCleRef2(){
        return this.cleRef2;
    }

    /**
     * Retourne le type d'action (C_AC_ACTION).
     * S=Sélection, I=Insertion, D=Suppression, U=Modification
     *
     * @return String Valeur de l'action.
     */
    public String getAction(){
        return this.action;
    }

    /**
     * Retourne le genre de la référence 2 (C_GF_REF2).
     *
     * @return String Valeur du genre
     */
    public String getGenreRef2(){
        return this.genreRef2;
    }

    /**
     * Retourne le site de la référence 2 (L_REF2_SITE ).
     *
     * @return long Valeur du site.
     */
    public long getSiteRef2(){
        return this.siteRef2;
    }

    /**
     * Retourne le créateur du dossier principal pour lequel on consulte
     * les accès.
     *
     * @return String Valeur du code de l'intervenant.
     */
    public String getCreateur(){
        return this.createur;
    }


    /**
     * Retourne la hiérarchie (applicable dans le cas des Dossiers).
     *
     * @return long Valeur numérique de la hiérarchie.
     */
    public long getHierarchie(){
        return this.hierarchie;
    }


    /**
     * Retourne la date de création du dossier principal.
     *
     * @return Timestamp Valeur de la date (yyyy-MM-dd).
     */
    public Timestamp getDateCreation(){
        return this.dateCreation;
    }

   /**
     * Retourne les dossiers.
     *
     * @return Collection Valeur des dossiers.
     */
    public Collection getDossiers(){
        return this.dossiers;
    }


    // Setters

    /**
     * Affecte l'action.
     *
     * @param action Valeur de l'action.
     */
    public void setAction(String action) {
        this.action = action;
    }
    /**
     * Affecte la cle.
     *
     * @param cle Valeur numérique de la cle.
     */
    public void setCle(long cle) {
        this.cle = cle;
    }

    /**
     * Affecte le site.
     *
     * @param site Valeur numérique le site.
     */
    public void setSite(long site) {
        this.site = site;
    }

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur numérique du site d'origine.
     */
    public void setSiteOrigine(long siteOrigine) {
        this.siteOrigine = siteOrigine;
    }



    /**
     * Affecte la clé de référence.
     *
     * @param cleRef Valeur numérique de la clé.
     */
    public void setCleRef(long cleRef) {
        this.cleRef = cleRef;
    }

    /**
     * Affecte la clé de référence 2.
     *
     * @param cleRef2 Valeur numérique de la clé.
     */
    public void setCleRef2(long cleRef2) {
        this.cleRef2 = cleRef2;
    }

    /**
     * Affecte une clé d'origine.
     *
     * @param cleOrigine Valeur numérique de la clé.
     */
    public void setCleOrigine(long cleOrigine) {
        this.cleOrigine = cleOrigine;
    }

    /**
     * Affecte un code de créateur.
     *
     * @param createur Code du créateur du dossier.
     */
    public void setCreateur(String createur) {
        this.createur = createur;
    }

    /**
     * Affecte la date d'accès.
     *
     * @param dateAcces Valeur de la date d'accès (yyyy-MM-dd).
     */
    public void setDateAcces(Timestamp dateAcces) {
        this.dateAcces = dateAcces;
    }

    /**
     * Affecte la date de création.
     *
     * @param dateCreation Valeur de la date (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Affecte un genre .
     *
     * @param genreOrigine Valeur du genre du dossier d'origine.
     */
    public void setGenreOrigine(String genreOrigine) {
        this.genreOrigine = genreOrigine;
    }

    /**
     * Affecte le genre de la première référence.
     *
     * @param genreRef Valeur de la première référence en caractère.
     */
    public void setGenreRef(String genreRef) {
        this.genreRef = genreRef;
    }

    /**
     * Affecte le genre de la deuxième référence.
     *
     * @param genreRef2 Valeur de la deuxième référence en caractère.
     */
    public void setGenreRef2(String genreRef2) {
        this.genreRef2 = genreRef2;
    }

     /**
     * Affecte l'utilisateur.
     *
     * @param utilisateur Valeur de l'utilisateur en caractère.
     */
    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

 

    /**
     * Affecte la hierarchie.
     *
     * @param hierarchie Valeur numérique de la hierarchie.
     */
    public void setHierarchie(long hierarchie) {
        this.hierarchie = hierarchie;
    }

    /**
     * Affecte le site de référence.
     *
     * @param siteRef Valeur numérique du site.
     */
    public void setSiteRef(long siteRef) {
        this.siteRef = siteRef;
    }

    /**
     * Affecte le site de référence 2.
     *
     * @param siteRef2 Valeur numérique du site.
     */
    public void setSiteRef2(long siteRef2) {
        this.siteRef2 = siteRef2;
    }


}