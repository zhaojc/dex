package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.RapportConsignation;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives à un dossier de la couche
 * présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.33 $, $Date: 2002/04/09 20:44:35 $
 * @see com.lotoquebec.cardex.business.Dossier
 */
public class RapportConsignationVO implements RapportConsignation {
    private long site = 0;
    private long quantite = 0;
    private String devise = "";
	private String denomination = "";
    private double prix = 0;
    private double perte = 0;
    private String dimension = "";
    private double montant = 0;
    private String numeroSerie = "";
    private String description = "";
    private String typeConsignation = "";
    private Timestamp dateDebut = null;
    private Timestamp dateFin = null;

    /**
     * Constructeur de ConsignationVO par défaut.
     */
    public RapportConsignationVO() {}


    // Getters



    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du ConsignationVO.
     *
     * @return String Valeur de tout les attributs du ConsignationVO en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[ConsignationVO : ");
        stringBuffer.append("siteOrigine = '" + site);
        stringBuffer.append("', type = '" + typeConsignation);
        stringBuffer.append("', description = '" + description);
        stringBuffer.append("', numero de série = '" + numeroSerie);
        stringBuffer.append("', dateDebut = '"
                + TimestampFormat.format(dateDebut));
        stringBuffer.append("', dateFin = '"
                + TimestampFormat.format(dateFin) + "']");
        return stringBuffer.toString();
    }
	/**
	 * Returns the dateDebut.
	 * @return Timestamp
	 */
	public Timestamp getDateDebut() {
		return dateDebut;
	}

	/**
	 * Returns the dateFin.
	 * @return Timestamp
	 */
	public Timestamp getDateFin() {
		return dateFin;
	}

	/**
	 * Returns the description.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the devise.
	 * @return String
	 */
	public String getDevise() {
		return devise;
	}

	/**
	 * Returns the dimension.
	 * @return String
	 */
	public String getDimension() {
		return dimension;
	}

	/**
	 * Returns the montant.
	 * @return double
	 */
	public double getMontant() {
		return montant;
	}

	/**
	 * Returns the numeroSerie.
	 * @return String
	 */
	public String getNumeroSerie() {
		return numeroSerie;
	}

	/**
	 * Returns the perte.
	 * @return long
	 */
	public double getPerte() {
		return perte;
	}

	/**
	 * Returns the prix.
	 * @return double
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * Returns the quantite.
	 * @return long
	 */
	public long getQuantite() {
		return quantite;
	}

	/**
	 * Returns the site.
	 * @return long
	 */
	public long getSite() {
		return site;
	}

	/**
	 * Returns the typeConsignation.
	 * @return long
	 */
	public String getTypeConsignation() {
		return typeConsignation;
	}

	/**
	 * Sets the dateDebut.
	 * @param dateDebut The dateDebut to set
	 */
	public void setDateDebut(Timestamp dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Sets the dateFin.
	 * @param dateFin The dateFin to set
	 */
	public void setDateFin(Timestamp dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * Sets the description.
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the devise.
	 * @param devise The devise to set
	 */
	public void setDevise(String devise) {
		this.devise = devise;
	}

	/**
	 * Sets the dimension.
	 * @param dimension The dimension to set
	 */
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	/**
	 * Sets the montant.
	 * @param montant The montant to set
	 */
	public void setMontant(double montant) {
		this.montant = montant;
	}

	/**
	 * Sets the numeroSerie.
	 * @param numeroSerie The numeroSerie to set
	 */
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	/**
	 * Sets the perte.
	 * @param perte The perte to set
	 */
	public void setPerte(double perte) {
		this.perte = perte;
	}

	/**
	 * Sets the prix.
	 * @param prix The prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * Sets the quantite.
	 * @param quantite The quantite to set
	 */
	public void setQuantite(long quantite) {
		this.quantite = quantite;
	}

	/**
	 * Sets the site.
	 * @param site The site to set
	 */
	public void setSite(long site) {
		this.site = site;
	}

	/**
	 * Sets the typeConsignation.
	 * @param typeConsignation The typeConsignation to set
	 */
	public void setTypeConsignation(String typeConsignation) {
		this.typeConsignation = typeConsignation;
	}

	/**
	 * @return
	 */
	public String getDenomination() {
		return denomination;
	}

	/**
	 * @param l
	 */
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

}