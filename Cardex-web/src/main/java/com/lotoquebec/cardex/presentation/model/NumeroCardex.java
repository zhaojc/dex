package com.lotoquebec.cardex.presentation.model;

import java.io.Serializable;

import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Conserve les informations relatives au numéro de cardex.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.9 $, $Date: 2002/04/23 21:08:55 $
 */
public class NumeroCardex implements Serializable {

	private String codeSite = "";
	private String site = "";
    private String date = "";
    private String sequence = "";

    /**
     * Constructeur de NumeroCardex par défaut.
     */
    public NumeroCardex() {}

    /**
     * Constructeur de NumeroCardex utilisant une seule chaîne de caractères
     * représentant le numéro de cardex.
     *
     * @param numeroCardex Valeur du numéro de cardex en caractère.
     */
    public NumeroCardex(String numeroCardex) {
        if (numeroCardex != null) {
            setNumeroCardex(numeroCardex.substring(0, 3),
                            numeroCardex.substring(3, 11),
                            numeroCardex.substring(11, 15));
        }
    }

    /**
     * Constructeur de NumeroCardex utilisant trois chaîne de caractères
     * représentant le numéro de cardex.
     *
     * @param site Valeur du site en caractère.
     * @param date Valeur de la date en caractère (yyyyMMdd).
     * @param sequence Valeur du numéro de séquence du cardex en caractère.
     */
    public NumeroCardex(String site, String date, String sequence) {
        setNumeroCardex(site,date,sequence);
    }


    // Getters


    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caractère.
     */
    public String getSite() {
        return this.site;
    }

    /**
     * Retourne la date.
     *
     * @return String Valeur de la date en caractère (yyyyMMdd).
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Retourne le numéro de séquence du cardex.
     *
     * @return String Valeur du numéro de séquence du cardex en caractère.
     */
    public String getSequence() {
        return this.sequence;
    }


    // Setters


    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * Affecte une date.
     *
     * @param site Valeur de la date en caractère (yyyyMMdd).
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Affecte un numéro de séquence du cardex.
     *
     * @param site Valeur du numéro de séquence du cardex en caractère.
     */
    public void setSequence(String sequence) {
      this.sequence = sequence;
    }

    /**
     * Affecte un numéro de cardex en utilisant une seule chaîne de caractère.
     *
     * @param numeroCardex Valeur du numéro de cardex en caractère.
     */
    public void setNumeroCardex(String numeroCardex) {
        if (numeroCardex != null && numeroCardex.trim().length() != 0 ) {
            this.site = numeroCardex.substring(0, 3);
            this.date = numeroCardex.substring(3, 11);
            this.sequence = numeroCardex.substring(11, 15);
        }
    }

    /**
     * Affecte un numéro de cardex en utilisant trois chaîne de caractère.
     *
     * @param site Valeur du site en caractère.
     * @param date Valeur de la date en caractère (yyyyMMdd).
     * @param sequence Valeur du numéro de séquence du cardex en caractère.
     */
    public void setNumeroCardex(String site, String date,
                                String sequence) {
        setSite(site);
        setDate(date);
        setSequence(sequence);
    }

    /**
     * Retourne une chaîne de caractère représentant le numéro de cardex.
     *
     * @return String Valeur du numéro de cardex en caractère.
     */
    public String toString() {
        return site + StringUtils.replace(date,"-","") + sequence;
    }

    public String getAffichage() {
        return site + '-' + date + '-' + sequence;
    }

	public String getCodeSite() {
		return codeSite;
	}

	public void setCodeSite(String codeSite) {
		this.codeSite = codeSite;
	}
    
}