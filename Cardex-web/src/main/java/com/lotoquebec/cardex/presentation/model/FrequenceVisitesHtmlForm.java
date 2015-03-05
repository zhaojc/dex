package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.model.DoubleListe;

/**
 * D�finit la signature des m�thodes des diff�rentes valeurs relatives au
 * formulaire d'�valuation du comit� de vigilance, section de la fr�quence des visites.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/03/12 21:41:27 $
 */
public interface FrequenceVisitesHtmlForm {


    // Getters


    /**
     * Retourne la cle.
     *
     * @return String Valeur de la cle en caract�re.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caract�re.
     */
    public String getSite();

    /**
     * Retourne la date de cr�ation.
     *
     * @return String Valeur de la date en caract�re.
     */
    public String getDateCreation();

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caract�re.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caract�re.
     */
    public String getLienSite();

    /**
     * Retourne le nombre de visites.
     *
     * @return String Valeur du nombre en caract�re.
     */
    public String getNombreVisites();

    /**
     * Retourne le gain/perte.
     *
     * @return String Valeur du gain/perte en caract�re.
     */
    public String getGainPerte();

    /**
     * Retourne la p�riode.
     *
     * @return String Valeur de la p�riode en caract�re.
     */
    public String getPeriode();

    /**
     * Retourne l'ann�e de la p�riode.
     *
     * @return String Valeur de la p�riode en caract�re.
     */
    public String getAnnee();

    /**
     * Retourne le mois de la p�riode.
     *
     * @return String Valeur de la p�riode en caract�re.
     */
    public String getMois();

    /**
     * Retourne le cr�ateur.
     *
     * @return String Valeur du cr�ateur en caract�re.
     */
    public String getCreateur();

    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caract�re.
     */
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(String site);

    /**
     * Affecte un createur.
     *
     * @param createur Valeur du createur en caract�re.
     */
    public void setCreateur(String createur);

    /**
     * Affecte un gain ou une perte.
     *
     * @param gainPerte Valeur en caract�re.
     */
    public void setGainPerte(String gainPerte);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSite(String lienSite);

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation en caract�re.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte le nombre de visites.
     *
     * @param nombreVisites Valeur du nombre en caract�re.
     */
    public void setNombreVisites(String nombreVisites);

    /**
     * Affecte la p�riode.
     *
     * @param periode Valeur de la p�riode en caract�re.
     */
    public void setPeriode(String periode);
	
    /**
     * Affecte l'ann�e de la p�riode.
     *
     * @param periode Valeur de la p�riode en caract�re.
     */
    public void setAnnee(String annee);

    /**
     * Affecte le mois de la p�riode.
     *
     * @param periode Valeur de la p�riode en caract�re.
     */
    public void setMois(String mois);

}