package com.lotoquebec.cardex.business;

import java.util.Collection;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import java.sql.Timestamp;
/**
 * D�finit la signature des m�thodes des diff�rentes valeurs relatives au
 * formulaire d'�valuation du comit� de vigilance, section de la fr�quence des visites.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/03/12 21:41:27 $
 */
public interface FrequenceVisites {


    // Getters


    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle en caract�re.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caract�re.
     */
    public long getSite();

    /**
     * Retourne la date de cr�ation.
     *
     * @return String Valeur de la date en caract�re.
     */
    public Timestamp getDateCreation();

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caract�re.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caract�re.
     */
    public long getLienSite();

    /**
     * Retourne le nombre de visites.
     *
     * @return long Valeur du nombre en caract�re.
     */
    public long getNombreVisites();

    /**
     * Retourne le gain/perte.
     *
     * @return long Valeur du gain/perte en caract�re.
     */
    public long getGainPerte();

    /**
     * Retourne la p�riode.
     *
     * @return String Valeur de la p�riode en caract�re.
     */
    public String getPeriode();

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
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(long site);

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
    public void setGainPerte(long gainPerte);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSite(long lienSite);

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation en caract�re.
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte le nombre de visites.
     *
     * @param nombreVisites Valeur du nombre en caract�re.
     */
    public void setNombreVisites(long nombreVisites);

    /**
     * Affecte la p�riode.
     *
     * @param periode Valeur de la p�riode en caract�re.
     */
    public void setPeriode(String periode);
	
}