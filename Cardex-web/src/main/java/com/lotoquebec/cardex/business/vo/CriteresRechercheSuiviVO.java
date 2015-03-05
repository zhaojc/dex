package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.CriteresRechercheSuivi;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives à la recherche d'un suivi de
 * la couche présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.3 $, $Date: 2002/03/29 15:50:58 $
 * @see com.lotoquebec.cardex.business.CriteresRechercheSuivi
 */
public class CriteresRechercheSuiviVO
        implements CriteresRechercheSuivi {

    private long secteurAssigne = 0;
    private String intervenant = "";
    private long secteurOrigine = 0;
    private long activite = 0;
    private String demandeur = "";
    private Timestamp dateEmisDebut = null;
    private Timestamp datePrevueDebut = null;
    private Timestamp dateCompleteeDebut = null;
    private Timestamp dateEmisFin = null;
    private Timestamp datePrevueFin = null;
    private Timestamp dateCompleteeFin = null;
    private long lien = 0;
    private long lienSite = 0;
    private String choixRapport = "";    
    private String ordreTriRecherche = "";
    private Boolean ordreCroissantRecherche = true;
    private long  maximumResultatsRecherche = 0;
    private String statutSuivi = "";
    private long entite = 0;
    private long siteOrigine = 0;
    private int sequence = 0;
    
    /**
     * Retourne le statut suivi.
     *
     * @return String Valeur du statut suivi.
     */
    public String getStatutSuivi() {
        return this.statutSuivi;
    }

    /**
     * Affecte un statut suivi.
     *
     * @param statutSuivi Valeur du statut suivi.
     */
    public void setStatutSuivi(String statutSuivi) {
        this.statutSuivi = statutSuivi;
    }

    private String statutApprobation = "";

    /**
     * Retourne le statut approbation.
     *
     * @return String Valeur du statut approbation.
     */
    public String getStatutApprobation() {
        return this.statutApprobation;
    }

    /**
     * Affecte un statut approbation.
     *
     * @param statutApprobation Valeur du statut approbation.
     */
    public void setStatutApprobation(String statutApprobation) {
        this.statutApprobation = statutApprobation;
    }

    /**
     * Constructeur de CriteresRechercheSuiviVO par défaut.
     */
    public CriteresRechercheSuiviVO() {}


    // Getters

    /**
     * Retourne le secteur assigné.
     *
     * @return String Valeur du secteur assigné en caractère.
     */
    public long getSecteurAssigne() {
        return this.secteurAssigne;
    }

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant() {
        return this.intervenant;
    }

    /**
     * Retourne le secteur d'origine.
     *
     * @return String Valeur du secteur d'origine en caractère.
     */
    public long getSecteurOrigine() {
        return this.secteurOrigine;
    }

    /**
     * Retourne le demandeur.
     *
     * @return String Valeur du demandeur en caractère.
     */
    public String getDemandeur() {
        return this.demandeur;
    }

    /**
     * Retourne la date d'émission au début.
     *
     * @return Timestamp Valeur de la date d'émission au début (yyyy-MM-dd).
     */
    public Timestamp getDateEmisDebut() {
        return this.dateEmisDebut;
    }

    /**
     * Retourne la date prévue au début.
     *
     * @return Timestamp Valeur de la date prévue au début (yyyy-MM-dd).
     */
    public Timestamp getDatePrevueDebut() {
        return this.datePrevueDebut;
    }

    /**
     * Retourne la date complétée au début.
     *
     * @return Timestamp Valeur de la date complétée au début (yyyy-MM-dd).
     */
    public Timestamp getDateCompleteeDebut() {
        return this.dateCompleteeDebut;
    }

    /**
     * Retourne la date d'émission à la fin.
     *
     * @return Timestamp Valeur de la date d'émission à la fin (yyyy-MM-dd).
     */
    public Timestamp getDateEmisFin() {
        return this.dateEmisFin;
    }

    /**
     * Retourne la date prévue à la fin.
     *
     * @return Timestamp Valeur de la date prévue à la fin (yyyy-MM-dd).
     */
    public Timestamp getDatePrevueFin() {
        return this.datePrevueFin;
    }

    /**
     * Retourne la date complétée à la fin.
     *
     * @return Timestamp Valeur de la date complétée à la fin (yyyy-MM-dd).
     */
    public Timestamp getDateCompleteeFin() {
        return this.dateCompleteeFin;
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
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caractère.
     */
    public String getOrdreTriRecherche() {
        return this.ordreTriRecherche;
    }

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur booléanne indiquant si l'ordre de recherche est
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche() {
        return this.ordreCroissantRecherche;
    }

    /**
     * Retourne le nombre maximum de résultats de recherche.
     *
     * @return String Valeur du nombre maximum de résultats de recherche en
     * caractère.
     */
    public long getMaximumResultatsRecherche() {
        return this.maximumResultatsRecherche;
    }


    // Setters

    /**
     * Affecte un secteur assigné.
     *
     * @param secteurAssigne Valeur du secteur assigné en caractère.
     */
    public void setSecteurAssigne(long secteurAssigne) {
        this.secteurAssigne = secteurAssigne;
    }

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant) {
        this.intervenant = intervenant;
    }

    /**
     * Affecte un secteur d'origine.
     *
     * @param secteurOrigine Valeur du secteur d'origine en caractère.
     */
    public void setSecteurOrigine(long secteurOrigine) {
        this.secteurOrigine = secteurOrigine;
    }

    /**
     * Affecte un demandeur.
     *
     * @param demandeur Valeur du demandeur en caractère.
     */
    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    /**
     * Affecte une date d'émission au début.
     *
     * @param dateEmisDebut Valeur de la date d'émission au début (yyyy-MM-dd).
     */
    public void setDateEmisDebut(Timestamp dateEmisDebut) {
        this.dateEmisDebut = dateEmisDebut;
    }

    /**
     * Affecte une date prévue au début.
     *
     * @param datePrevueDebut Valeur de la date prévue au début (yyyy-MM-dd).
     */
    public void setDatePrevueDebut(Timestamp datePrevueDebut) {
        this.datePrevueDebut = datePrevueDebut;
    }

    /**
     * Affecte une date complétée au début.
     *
     * @param dateCompleteeDebut Valeur de la date complétée au début
     * (yyyy-MM-dd).
     */
    public void setDateCompleteeDebut(Timestamp dateCompleteeDebut) {
        this.dateCompleteeDebut = dateCompleteeDebut;
    }

    /**
     * Affecte une date d'émission à la fin.
     *
     * @param dateEmisFin Valeur de la date d'émission à la fin (yyyy-MM-dd).
     */
    public void setDateEmisFin(Timestamp dateEmisFin) {
        this.dateEmisFin = dateEmisFin;
    }

    /**
     * Affecte une date prévue à la fin.
     *
     * @param datePrevueFin Valeur de la date prévue à la fin (yyyy-MM-dd).
     */
    public void setDatePrevueFin(Timestamp datePrevueFin) {
        this.datePrevueFin = datePrevueFin;
    }

    /**
     * Affecte une date complétée à la fin.
     *
     * @param dateCompleteeFin Valeur de la date complétée à la fin
     * (yyyy-MM-dd).
     */
    public void setDateCompleteeFin(Timestamp dateCompleteeFin) {
        this.dateCompleteeFin = dateCompleteeFin;
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
     * Affecte l'ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caractère.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche) {
        this.ordreTriRecherche = ordreTriRecherche;
    }

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur booléanne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche) {
        this.ordreCroissantRecherche = ordreCroissantRecherche;
    }

    /**
     * Affecte le nombre maximum de résultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de résultats
     * de recherche caractère.
     */
    public void setMaximumResultatsRecherche(long maximumResultatsRecherche) {
        this.maximumResultatsRecherche = maximumResultatsRecherche;
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du CriteresRechercheSuiviVO.
     *
     * @return String Valeur de tout les attributs du CriteresRechercheSuiviVO
     * en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRechercheSuiviVO : ");
        stringBuffer.append("secteurAssigne = '" + secteurAssigne);
        stringBuffer.append("', statutSuivi = '" + statutSuivi);
        stringBuffer.append("', statutApprobation = '" + statutApprobation);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("', secteurOrigine = '" + secteurOrigine);
        stringBuffer.append("', demandeur = '" + demandeur);
        stringBuffer.append("', dateEmisDebut = '"
                                + TimestampFormat.format(dateEmisDebut));
        stringBuffer.append("', datePrevueDebut = '"
                                + TimestampFormat.format(datePrevueDebut));
        stringBuffer.append("', dateCompleteeDebut = '"
                                + TimestampFormat.format(dateCompleteeDebut));
        stringBuffer.append("', dateEmisFin = '"
                                + TimestampFormat.format(dateEmisFin));
        stringBuffer.append("', datePrevueFin = '"
                                + TimestampFormat.format(datePrevueFin));
        stringBuffer.append("', dateCompleteeFin = '"
                                + TimestampFormat.format(dateCompleteeFin));
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', ordreTriRecherche = '" + ordreTriRecherche);
        stringBuffer.append("', ordreCroissantRecherche = '"
            + ordreCroissantRecherche);
        stringBuffer.append("', maximumResultatsRecherche = '"
            + maximumResultatsRecherche);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/**
	 * @return choixRapport
	 */
	public String getChoixRapport() {
		return choixRapport;
	}

	/**
	 * @param choixRapport choixRapport à définir
	 */
	public void setChoixRapport(String choixRapport) {
		this.choixRapport = choixRapport;
	}

	/**
	 * @return entite
	 */
	public long getEntite() {
		return entite;
	}

	/**
	 * @param entite entite à définir
	 */
	public void setEntite(long entite) {
		this.entite = entite;
	}

	/**
	 * @return siteOrigine
	 */
	public long getSiteOrigine() {
		return siteOrigine;
	}

	/**
	 * @param siteOrigine siteOrigine à définir
	 */
	public void setSiteOrigine(long siteOrigine) {
		this.siteOrigine = siteOrigine;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Boolean getOrdreCroissantRecherche() {
		return ordreCroissantRecherche;
	}

	/**
	 * @return activite
	 */
	public long getActivite() {
		return activite;
	}

	/**
	 * @param activite activite à définir
	 */
	public void setActivite(long activite) {
		this.activite = activite;
	}

	
	
}