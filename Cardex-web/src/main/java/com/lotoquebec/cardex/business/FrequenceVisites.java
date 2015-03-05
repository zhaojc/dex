package com.lotoquebec.cardex.business;

import java.util.Collection;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import java.sql.Timestamp;
/**
 * Définit la signature des méthodes des différentes valeurs relatives au
 * formulaire d'évaluation du comité de vigilance, section de la fréquence des visites.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/03/12 21:41:27 $
 */
public interface FrequenceVisites {


    // Getters


    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle en caractère.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caractère.
     */
    public long getSite();

    /**
     * Retourne la date de création.
     *
     * @return String Valeur de la date en caractère.
     */
    public Timestamp getDateCreation();

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caractère.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caractère.
     */
    public long getLienSite();

    /**
     * Retourne le nombre de visites.
     *
     * @return long Valeur du nombre en caractère.
     */
    public long getNombreVisites();

    /**
     * Retourne le gain/perte.
     *
     * @return long Valeur du gain/perte en caractère.
     */
    public long getGainPerte();

    /**
     * Retourne la période.
     *
     * @return String Valeur de la période en caractère.
     */
    public String getPeriode();

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
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(long site);

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
    public void setGainPerte(long gainPerte);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(long lienSite);

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création en caractère.
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte le nombre de visites.
     *
     * @param nombreVisites Valeur du nombre en caractère.
     */
    public void setNombreVisites(long nombreVisites);

    /**
     * Affecte la période.
     *
     * @param periode Valeur de la période en caractère.
     */
    public void setPeriode(String periode);
	
}