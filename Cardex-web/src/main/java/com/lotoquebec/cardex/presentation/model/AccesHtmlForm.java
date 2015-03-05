package com.lotoquebec.cardex.presentation.model;

import java.sql.Timestamp;

/**
 * Permet de transiter les informations relatives à la consultation des
 * accès de la couche présentation à la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.4 $, $Date: 2002/02/28 17:28:36 $
 */
public interface AccesHtmlForm {


    // Getters

    /**
     * Retourne la clé (L_AC_CLE).
     *
     * @return String Valeur numérique de la clé
     */
    public String getCle();

    /**
     * Retourne le site (L_SI_SITE).
     *
     * @return String Valeur numérique du site d'où provient l'accès
     */

    public String getSite();

    /**
     * Retourne le site d'origine (L_ORI_SITE).
     *
     * @return String Valeur numérique du site d'origine.
     */
    public String getSiteOrigine();

    /**
     * Retourne le genre (C_GF_REFERENCE).
     *
     * @return String Valeur du genre.
     */
    public String getGenreRef();

    /**
     * Retourne la clé d'origine (L_ORI_CLE).
     *
     * @return String Valeur numérique de la clé.
     */
    public String getCleOrigine();

    /**
     * Retourne la clé de référence (L_REF_CLE).
     *
     * @return String Valeur numérique dela clé.
     */
    public String getCleRef();

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
     * @return String Valeur numérique du site.
     */
    public String getSiteRef();

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
    public String getDateAcces();

     /**
     * Retourne la clé de référence 2 (L_REF2_CLE).
     *
     * @return String Valeur de la clé.
     */
    public String getCleRef2();

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
     * @return String Valeur du site.
     */
    public String getSiteRef2();

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
     * @return String Valeur numérique de la hiérarchie.
     */
    public String getHierarchie();


    /**
     * Retourne la date de création du dossier principal.
     *
     * @return Timestamp Valeur de la date (yyyy-MM-dd).
     */
    public String getDateCreation();


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
    public void setCle(String cle);

    /**
     * Affecte le site.
     *
     * @param site Valeur numérique le site.
     */
    public void setSite(String site);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur numérique du site d'origine.
     */
    public void setSiteOrigine(String siteOrigine);



    /**
     * Affecte la clé de référence.
     *
     * @param cleRef Valeur numérique de la clé.
     */
    public void setCleRef(String cleRef);

    /**
     * Affecte la clé de référence 2.
     *
     * @param cleRef2 Valeur numérique de la clé.
     */
    public void setCleRef2(String cleRef2);

    /**
     * Affecte une clé d'origine.
     *
     * @param cleOrigine Valeur numérique de la clé.
     */
    public void setCleOrigine(String cleOrigine);

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
    public void setDateAcces(String dateAcces);

    /**
     * Affecte la date de création.
     *
     * @param dateCreation Valeur de la date (yyyy-MM-dd).
     */
    public void setDateCreation(String dateCreation);

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
    public void setHierarchie(String hierarchie);

    /**
     * Affecte le site de référence.
     *
     * @param siteRef Valeur numérique du site.
     */
    public void setSiteRef(String siteRef);

    /**
     * Affecte le site de référence 2.
     *
     * @param siteRef2 Valeur numérique du site.
     */
    public void setSiteRef2(String siteRef2);


}