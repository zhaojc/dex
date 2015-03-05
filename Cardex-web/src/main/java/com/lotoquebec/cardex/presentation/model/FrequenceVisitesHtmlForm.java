package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.model.DoubleListe;

/**
 * Définit la signature des méthodes des différentes valeurs relatives au
 * formulaire d'évaluation du comité de vigilance, section de la fréquence des visites.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/03/12 21:41:27 $
 */
public interface FrequenceVisitesHtmlForm {


    // Getters


    /**
     * Retourne la cle.
     *
     * @return String Valeur de la cle en caractère.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caractère.
     */
    public String getSite();

    /**
     * Retourne la date de création.
     *
     * @return String Valeur de la date en caractère.
     */
    public String getDateCreation();

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caractère.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caractère.
     */
    public String getLienSite();

    /**
     * Retourne le nombre de visites.
     *
     * @return String Valeur du nombre en caractère.
     */
    public String getNombreVisites();

    /**
     * Retourne le gain/perte.
     *
     * @return String Valeur du gain/perte en caractère.
     */
    public String getGainPerte();

    /**
     * Retourne la période.
     *
     * @return String Valeur de la période en caractère.
     */
    public String getPeriode();

    /**
     * Retourne l'année de la période.
     *
     * @return String Valeur de la période en caractère.
     */
    public String getAnnee();

    /**
     * Retourne le mois de la période.
     *
     * @return String Valeur de la période en caractère.
     */
    public String getMois();

    /**
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur();

    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caractère.
     */
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(String site);

    /**
     * Affecte un createur.
     *
     * @param createur Valeur du createur en caractère.
     */
    public void setCreateur(String createur);

    /**
     * Affecte un gain ou une perte.
     *
     * @param gainPerte Valeur en caractère.
     */
    public void setGainPerte(String gainPerte);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(String lienSite);

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création en caractère.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte le nombre de visites.
     *
     * @param nombreVisites Valeur du nombre en caractère.
     */
    public void setNombreVisites(String nombreVisites);

    /**
     * Affecte la période.
     *
     * @param periode Valeur de la période en caractère.
     */
    public void setPeriode(String periode);
	
    /**
     * Affecte l'année de la période.
     *
     * @param periode Valeur de la période en caractère.
     */
    public void setAnnee(String annee);

    /**
     * Affecte le mois de la période.
     *
     * @param periode Valeur de la période en caractère.
     */
    public void setMois(String mois);

}