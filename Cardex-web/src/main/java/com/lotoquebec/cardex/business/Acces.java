package com.lotoquebec.cardex.business;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * Les accès aux données du système sont compilés dans la table AC_ACCES.
 * Les données compilées peuvent être consultés avec le bouton Audit sur 
 * les écrans de consultation (Dossiers, Sujets, Sociétés et Véhicules).
 *
 * @author $Author: François Guérin $
 * @version $Revison: $, $Date: 2002/06/11 00:25:27 $
 */
public interface Acces {


    // Getters

    /**
     * Retourne la clé (L_AC_CLE).
     *
     * @return long Valeur numérique de la clé
     */
    public long getCle();

    /**
     * Retourne le site (L_SI_SITE).
     *
     * @return long Valeur numérique du site d'où provient l'accès
     */

    public long getSite();

    /**
     * Retourne le site d'origine (L_ORI_SITE).
     *
     * @return long Valeur numérique du site d'origine.
     */
    public long getSiteOrigine();

    /**
     * Retourne le genre (C_GF_REFERENCE).
     *
     * @return String Valeur du genre.
     */
    public String getGenreRef();

    /**
     * Retourne la clé d'origine (L_ORI_CLE).
     *
     * @return long Valeur numérique de la clé.
     */
    public long getCleOrigine();

    /**
     * Retourne la clé de référence (L_REF_CLE).
     *
     * @return long Valeur numérique dela clé.
     */
    public long getCleRef();

    /**
     * Retourne la référence du fichier (C_GF_ORIGINE).
     * Correspond au fichier sur lequel on conserve un audit.
     *
     * @return String Valeur du genre en caractère.
     */
    public String getGenreOrigine();

    /**
     * Retourne le site de référence (L_REF_SITE).
     *
     * @return long Valeur numérique du site.
     */
    public long getSiteRef();

    /**
     * Retourne le code de l'utilisateur qui fait l'accès (V_AC_NAME).
     *
     * @return String Valeur du code de l'utilisateur.
     */
    public String getUtilisateur();

    /**
     * Retourne la date de l'accès (D_AC_DATE_ACCES).
     *
     * @return Timestamp Valeur de la date (yyyy-MM-dd).
     */
    public Timestamp getDateAcces();

     /**
     * Retourne la clé de référence 2 (L_REF2_CLE).
     *
     * @return long Valeur de la clé.
     */
    public long getCleRef2();

    /**
     * Retourne le type d'action (C_AC_ACTION).
     * S=Sélection, I=Insertion, D=Suppression, U=Modification
     *
     * @return String Valeur de l'action.
     */
    public String getAction();

    /**
     * Retourne le genre de la référence 2 (C_GF_REF2).
     *
     * @return String Valeur du genre
     */
    public String getGenreRef2();

    /**
     * Retourne le site de la référence 2 (L_REF2_SITE ).
     *
     * @return long Valeur du site.
     */
    public long getSiteRef2();

    /**
     * Retourne le créateur du dossier principal pour lequel on consulte
     * les accès.
     *
     * @return String Valeur du code de l'intervenant.
     */
    public String getCreateur();


    /**
     * Retourne la hiérarchie (applicable dans le cas des Dossiers).
     *
     * @return long Valeur numérique de la hiérarchie.
     */
    public long getHierarchie();


    /**
     * Retourne la date de création du dossier principal.
     *
     * @return Timestamp Valeur de la date (yyyy-MM-dd).
     */
    public Timestamp getDateCreation();

   /**
     * Retourne les dossiers.
     *
     * @return Collection Valeur des dossiers.
     */
    public Collection getDossiers();


    // Setters

    /**
     * Affecte l'action.
     *
     * @param action Valeur de l'action.
     */
    public void setAction(String action);

    /**
     * Affecte la cle.
     *
     * @param cle Valeur numérique de la cle.
     */
    public void setCle(long cle);

    /**
     * Affecte le site.
     *
     * @param site Valeur numérique le site.
     */
    public void setSite(long site);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur numérique du site d'origine.
     */
    public void setSiteOrigine(long siteOrigine);



    /**
     * Affecte la clé de référence.
     *
     * @param cleRef Valeur numérique de la clé.
     */
    public void setCleRef(long cleRef);

    /**
     * Affecte la clé de référence 2.
     *
     * @param cleRef2 Valeur numérique de la clé.
     */
    public void setCleRef2(long cleRef2);

    /**
     * Affecte une clé d'origine.
     *
     * @param cleOrigine Valeur numérique de la clé.
     */
    public void setCleOrigine(long cleOrigine);

    /**
     * Affecte un code de créateur.
     *
     * @param createur Code du créateur du dossier.
     */
    public void setCreateur(String createur);

    /**
     * Affecte la date d'accès.
     *
     * @param dateAcces Valeur de la date d'accès (yyyy-MM-dd).
     */
    public void setDateAcces(Timestamp dateAcces);

    /**
     * Affecte la date de création.
     *
     * @param dateCreation Valeur de la date (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte un genre .
     *
     * @param genreOrigine Valeur du genre du dossier d'origine.
     */
    public void setGenreOrigine(String genreOrigine);

    /**
     * Affecte le genre de la première référence.
     *
     * @param genreRef Valeur de la première référence en caractère.
     */
    public void setGenreRef(String genreRef);

    /**
     * Affecte le genre de la deuxième référence.
     *
     * @param genreRef2 Valeur de la deuxième référence en caractère.
     */
    public void setGenreRef2(String genreRef2);

     /**
     * Affecte l'utilisateur.
     *
     * @param utilisateur Valeur de l'utilisateur en caractère.
     */
    public void setUtilisateur(String utilisateur);

 

    /**
     * Affecte la hierarchie.
     *
     * @param hierarchie Valeur numérique de la hierarchie.
     */
    public void setHierarchie(long hierarchie);

    /**
     * Affecte le site de référence.
     *
     * @param siteRef Valeur numérique du site.
     */
    public void setSiteRef(long siteRef);

    /**
     * Affecte le site de référence 2.
     *
     * @param siteRef2 Valeur numérique du site.
     */
    public void setSiteRef2(long siteRef2);


}