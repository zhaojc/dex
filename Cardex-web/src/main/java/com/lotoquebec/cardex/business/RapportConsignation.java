package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * Permet de transiter les informations relatives à une consignation de la couche
 * présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/03/26 22:17:44 $
 */
public interface RapportConsignation {


    // Getters

	/**
	 * Retourne le site.
	 *
	 * @return long Valeur du site en caractère.
	 */
	public long getSite();

	/**
	 * Retourne la devise.
	 *
	 * @return String Valeur de la devise.
	 */
	public String getDevise();

	/**
	 * Retourne le type de consignation.
	 *
	 * @return long Valeur du type en caractère.
	 */
	public String getTypeConsignation();

	/**
	 * Retourne la quantite.
	 *
	 * @return long Valeur de la quantité en caractère.
	 */
	public long getQuantite();

	/**
	 * Retourne le prix unitaire.
	 *
	 * @return double Valeur du prix en caractère.
	 */
	public double getPrix();

	/**
	 * Retourne la dénomination.
	 *
	 * @return String Valeur de la dénomination en caractère.
	 */
	public String getDenomination();

	/**
	 * Retourne le montant.
	 *
	 * @return double Valeur du montant en caractère.
	 */
	public double getMontant();

	/**
	 * Retourne la perte.
	 *
	 * @return double Valeur de la perte en caractère.
	 */
	public double getPerte();


	/**
	 * Retourne la description.
	 *
	 * @return String Valeur de la description en caractère.
	 */
	public String getDescription();

	/**
	 * Retourne le numéro de série.
	 *
	 * @return String Valeur du numéro de série en caractère.
	 */
	public String getNumeroSerie();

	/**
	 * Retourne la date de début.
	 *
	 * @return Timestamp Valeur de la date de début en caractère.
	 */
	public Timestamp getDateDebut();

	/**
	 * Retourne la date de fin.
	 *
	 * @return Timestamp Valeur de la date de fin en caractère.
	 */
	public Timestamp getDateFin();

	/**
	 * Affecte un site.
	 *
	 * @param site Valeur du site en caractère.
	 */
	public void setSite(long site);

	/**
	 * Affecte une description.
	 *
	 * @return description Valeur de la description en caractère.
	 */
	public void setDescription(String description);

	/**
	 * Affecte une devise.
	 *
	 * @param devise Valeur de la devise en caractère.
	 */
	public void setDevise(String devise);

	/**
	 * Affecte une dénomination.
	 *
	 * @param dimension Valeur de la dénomination en caractère.
	 */
	public void setDenomination(String denomination);

	/**
	 * Affecte un montant.
	 *
	 * @param modele Valeur du montant en
	 * caractère.
	 */
	public void setMontant(double montant);

	/**
	 * Affecte un numéro de série.
	 *
	 * @param numeroSerie Valeur du numéro de série en
	 * caractère.
	 */
	public void setNumeroSerie(String numeroSerie);

	/**
	 * Affecte une perte.
	 *
	 * @param poids Valeur de la perte
	 * en caractère.
	 */
	public void setPerte(double perte);

	/**
	 * Affecte un prix.
	 *
	 * @param prix Valeur du prix
	 */
	public void setPrix(double prix);

	/**
	 * Affecte une quantite.
	 *
	 * @param quantite Valeur de la quantite du
	 * créateur.
	 */
	public void setQuantite(long quantite);

	/**
	 * Affecte une date de début.
	 *
	 * @param dateDebut Valeur de dateDebut en caractère.
	 */
	public void setDateDebut(Timestamp dateDebut);

	/**
	 * Affecte une date de fin.
	 *
	 * @param dateFin Valeur de dateFin en caractère.
	 */
	public void setDateFin(Timestamp dateFin);

	/**
	 * Affecte un type de consignation.
	 *
	 * @param typeConsignation Valeur type de consignation 
	 * en caractère.
	 */
	public void setTypeConsignation(
	String typeConsignation);

}