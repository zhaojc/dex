package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.RapportConsignationHtmlForm;

/**
 * Permet de transiter les informations relatives à un suivi de la couche
 * présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.SuiviHtmlForm
 */
public class RapportConsignationForm extends ValidatorForm  implements RapportConsignationHtmlForm, Serializable {

    private String site = "";
    private String devise = "";
	private String denomination = "";
    private String prix = "";
    private String perte = "";
	private String quantite = "";
    private String montant = "";
    private String numeroSerie = "";
    private String description = "";
    private String typeConsignation = "";
    private String dateDebut = "";
    private String dateFin = "";

	private DossierForm dossier = null;


    /**
     * Constructeur de SuiviForm par défaut.
     */
    public RapportConsignationForm() {}



	/**
	 * @return
	 */
	public String getDateDebut() {
		return dateDebut;
	}

	/**
	 * @return
	 */
	public String getDateFin() {
		return dateFin;
	}

	/**
	 * @return
	 */
	public String getDenomination() {
		return denomination;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return
	 */
	public String getDevise() {
		return devise;
	}

	/**
	 * @return
	 */
	public DossierForm getDossier() {
		return dossier;
	}

	/**
	 * @return
	 */
	public String getMontant() {
		return montant;
	}

	/**
	 * @return
	 */
	public String getNumeroSerie() {
		return numeroSerie;
	}

	/**
	 * @return
	 */
	public String getPerte() {
		return perte;
	}

	/**
	 * @return
	 */
	public String getPrix() {
		return prix;
	}

	/**
	 * @return
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @return
	 */
	public String getTypeConsignation() {
		return typeConsignation;
	}

	/**
	 * @param string
	 */
	public void setDateDebut(String string) {
		dateDebut = string;
	}

	/**
	 * @param string
	 */
	public void setDateFin(String string) {
		dateFin = string;
	}

	/**
	 * @param string
	 */
	public void setDenomination(String string) {
		denomination = string;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @param string
	 */
	public void setDevise(String string) {
		devise = string;
	}

	/**
	 * @param form
	 */
	public void setDossier(DossierForm form) {
		dossier = form;
	}

	/**
	 * @param string
	 */
	public void setMontant(String string) {
		montant = string;
	}

	/**
	 * @param string
	 */
	public void setNumeroSerie(String string) {
		numeroSerie = string;
	}

	/**
	 * @param string
	 */
	public void setPerte(String string) {
		perte = string;
	}

	/**
	 * @param string
	 */
	public void setPrix(String string) {
		prix = string;
	}

	/**
	 * @param string
	 */
	public void setSite(String string) {
		site = string;
	}

	/**
	 * @param string
	 */
	public void setTypeConsignation(String string) {
		typeConsignation = string;
	}

	/**
	 * @return
	 */
	public String getQuantite() {
		return quantite;
	}

	/**
	 * @param string
	 */
	public void setQuantite(String string) {
		quantite = string;
	}

}