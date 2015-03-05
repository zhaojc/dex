package com.lotoquebec.cardex.presentation.model;

import java.io.Serializable;

import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Conserve les informations relatives au num�ro de cardex.
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
     * Constructeur de NumeroCardex par d�faut.
     */
    public NumeroCardex() {}

    /**
     * Constructeur de NumeroCardex utilisant une seule cha�ne de caract�res
     * repr�sentant le num�ro de cardex.
     *
     * @param numeroCardex Valeur du num�ro de cardex en caract�re.
     */
    public NumeroCardex(String numeroCardex) {
        if (numeroCardex != null) {
            setNumeroCardex(numeroCardex.substring(0, 3),
                            numeroCardex.substring(3, 11),
                            numeroCardex.substring(11, 15));
        }
    }

    /**
     * Constructeur de NumeroCardex utilisant trois cha�ne de caract�res
     * repr�sentant le num�ro de cardex.
     *
     * @param site Valeur du site en caract�re.
     * @param date Valeur de la date en caract�re (yyyyMMdd).
     * @param sequence Valeur du num�ro de s�quence du cardex en caract�re.
     */
    public NumeroCardex(String site, String date, String sequence) {
        setNumeroCardex(site,date,sequence);
    }


    // Getters


    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caract�re.
     */
    public String getSite() {
        return this.site;
    }

    /**
     * Retourne la date.
     *
     * @return String Valeur de la date en caract�re (yyyyMMdd).
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Retourne le num�ro de s�quence du cardex.
     *
     * @return String Valeur du num�ro de s�quence du cardex en caract�re.
     */
    public String getSequence() {
        return this.sequence;
    }


    // Setters


    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * Affecte une date.
     *
     * @param site Valeur de la date en caract�re (yyyyMMdd).
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Affecte un num�ro de s�quence du cardex.
     *
     * @param site Valeur du num�ro de s�quence du cardex en caract�re.
     */
    public void setSequence(String sequence) {
      this.sequence = sequence;
    }

    /**
     * Affecte un num�ro de cardex en utilisant une seule cha�ne de caract�re.
     *
     * @param numeroCardex Valeur du num�ro de cardex en caract�re.
     */
    public void setNumeroCardex(String numeroCardex) {
        if (numeroCardex != null && numeroCardex.trim().length() != 0 ) {
            this.site = numeroCardex.substring(0, 3);
            this.date = numeroCardex.substring(3, 11);
            this.sequence = numeroCardex.substring(11, 15);
        }
    }

    /**
     * Affecte un num�ro de cardex en utilisant trois cha�ne de caract�re.
     *
     * @param site Valeur du site en caract�re.
     * @param date Valeur de la date en caract�re (yyyyMMdd).
     * @param sequence Valeur du num�ro de s�quence du cardex en caract�re.
     */
    public void setNumeroCardex(String site, String date,
                                String sequence) {
        setSite(site);
        setDate(date);
        setSequence(sequence);
    }

    /**
     * Retourne une cha�ne de caract�re repr�sentant le num�ro de cardex.
     *
     * @return String Valeur du num�ro de cardex en caract�re.
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