package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.CriteresRechercheSuivi;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives � la recherche d'un suivi de
 * la couche pr�sentation � la couche d'affaire.
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
     * Constructeur de CriteresRechercheSuiviVO par d�faut.
     */
    public CriteresRechercheSuiviVO() {}


    // Getters

    /**
     * Retourne le secteur assign�.
     *
     * @return String Valeur du secteur assign� en caract�re.
     */
    public long getSecteurAssigne() {
        return this.secteurAssigne;
    }

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant() {
        return this.intervenant;
    }

    /**
     * Retourne le secteur d'origine.
     *
     * @return String Valeur du secteur d'origine en caract�re.
     */
    public long getSecteurOrigine() {
        return this.secteurOrigine;
    }

    /**
     * Retourne le demandeur.
     *
     * @return String Valeur du demandeur en caract�re.
     */
    public String getDemandeur() {
        return this.demandeur;
    }

    /**
     * Retourne la date d'�mission au d�but.
     *
     * @return Timestamp Valeur de la date d'�mission au d�but (yyyy-MM-dd).
     */
    public Timestamp getDateEmisDebut() {
        return this.dateEmisDebut;
    }

    /**
     * Retourne la date pr�vue au d�but.
     *
     * @return Timestamp Valeur de la date pr�vue au d�but (yyyy-MM-dd).
     */
    public Timestamp getDatePrevueDebut() {
        return this.datePrevueDebut;
    }

    /**
     * Retourne la date compl�t�e au d�but.
     *
     * @return Timestamp Valeur de la date compl�t�e au d�but (yyyy-MM-dd).
     */
    public Timestamp getDateCompleteeDebut() {
        return this.dateCompleteeDebut;
    }

    /**
     * Retourne la date d'�mission � la fin.
     *
     * @return Timestamp Valeur de la date d'�mission � la fin (yyyy-MM-dd).
     */
    public Timestamp getDateEmisFin() {
        return this.dateEmisFin;
    }

    /**
     * Retourne la date pr�vue � la fin.
     *
     * @return Timestamp Valeur de la date pr�vue � la fin (yyyy-MM-dd).
     */
    public Timestamp getDatePrevueFin() {
        return this.datePrevueFin;
    }

    /**
     * Retourne la date compl�t�e � la fin.
     *
     * @return Timestamp Valeur de la date compl�t�e � la fin (yyyy-MM-dd).
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
     * @return String Valeur de l'ordre de tri de recherche en caract�re.
     */
    public String getOrdreTriRecherche() {
        return this.ordreTriRecherche;
    }

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur bool�anne indiquant si l'ordre de recherche est
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche() {
        return this.ordreCroissantRecherche;
    }

    /**
     * Retourne le nombre maximum de r�sultats de recherche.
     *
     * @return String Valeur du nombre maximum de r�sultats de recherche en
     * caract�re.
     */
    public long getMaximumResultatsRecherche() {
        return this.maximumResultatsRecherche;
    }


    // Setters

    /**
     * Affecte un secteur assign�.
     *
     * @param secteurAssigne Valeur du secteur assign� en caract�re.
     */
    public void setSecteurAssigne(long secteurAssigne) {
        this.secteurAssigne = secteurAssigne;
    }

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant) {
        this.intervenant = intervenant;
    }

    /**
     * Affecte un secteur d'origine.
     *
     * @param secteurOrigine Valeur du secteur d'origine en caract�re.
     */
    public void setSecteurOrigine(long secteurOrigine) {
        this.secteurOrigine = secteurOrigine;
    }

    /**
     * Affecte un demandeur.
     *
     * @param demandeur Valeur du demandeur en caract�re.
     */
    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    /**
     * Affecte une date d'�mission au d�but.
     *
     * @param dateEmisDebut Valeur de la date d'�mission au d�but (yyyy-MM-dd).
     */
    public void setDateEmisDebut(Timestamp dateEmisDebut) {
        this.dateEmisDebut = dateEmisDebut;
    }

    /**
     * Affecte une date pr�vue au d�but.
     *
     * @param datePrevueDebut Valeur de la date pr�vue au d�but (yyyy-MM-dd).
     */
    public void setDatePrevueDebut(Timestamp datePrevueDebut) {
        this.datePrevueDebut = datePrevueDebut;
    }

    /**
     * Affecte une date compl�t�e au d�but.
     *
     * @param dateCompleteeDebut Valeur de la date compl�t�e au d�but
     * (yyyy-MM-dd).
     */
    public void setDateCompleteeDebut(Timestamp dateCompleteeDebut) {
        this.dateCompleteeDebut = dateCompleteeDebut;
    }

    /**
     * Affecte une date d'�mission � la fin.
     *
     * @param dateEmisFin Valeur de la date d'�mission � la fin (yyyy-MM-dd).
     */
    public void setDateEmisFin(Timestamp dateEmisFin) {
        this.dateEmisFin = dateEmisFin;
    }

    /**
     * Affecte une date pr�vue � la fin.
     *
     * @param datePrevueFin Valeur de la date pr�vue � la fin (yyyy-MM-dd).
     */
    public void setDatePrevueFin(Timestamp datePrevueFin) {
        this.datePrevueFin = datePrevueFin;
    }

    /**
     * Affecte une date compl�t�e � la fin.
     *
     * @param dateCompleteeFin Valeur de la date compl�t�e � la fin
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
     * caract�re.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche) {
        this.ordreTriRecherche = ordreTriRecherche;
    }

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur bool�anne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche) {
        this.ordreCroissantRecherche = ordreCroissantRecherche;
    }

    /**
     * Affecte le nombre maximum de r�sultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de r�sultats
     * de recherche caract�re.
     */
    public void setMaximumResultatsRecherche(long maximumResultatsRecherche) {
        this.maximumResultatsRecherche = maximumResultatsRecherche;
    }

    /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du CriteresRechercheSuiviVO.
     *
     * @return String Valeur de tout les attributs du CriteresRechercheSuiviVO
     * en caract�re.
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
	 * @param choixRapport choixRapport � d�finir
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
	 * @param entite entite � d�finir
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
	 * @param siteOrigine siteOrigine � d�finir
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
	 * @param activite activite � d�finir
	 */
	public void setActivite(long activite) {
		this.activite = activite;
	}

	
	
}