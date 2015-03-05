package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * Permet de transiter les informations relatives � une consignation de la couche
 * pr�sentation � la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/03/26 22:17:44 $
 */
public interface RapportConsignation {


    // Getters

	/**
	 * Retourne le site.
	 *
	 * @return long Valeur du site en caract�re.
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
	 * @return long Valeur du type en caract�re.
	 */
	public String getTypeConsignation();

	/**
	 * Retourne la quantite.
	 *
	 * @return long Valeur de la quantit� en caract�re.
	 */
	public long getQuantite();

	/**
	 * Retourne le prix unitaire.
	 *
	 * @return double Valeur du prix en caract�re.
	 */
	public double getPrix();

	/**
	 * Retourne la d�nomination.
	 *
	 * @return String Valeur de la d�nomination en caract�re.
	 */
	public String getDenomination();

	/**
	 * Retourne le montant.
	 *
	 * @return double Valeur du montant en caract�re.
	 */
	public double getMontant();

	/**
	 * Retourne la perte.
	 *
	 * @return double Valeur de la perte en caract�re.
	 */
	public double getPerte();


	/**
	 * Retourne la description.
	 *
	 * @return String Valeur de la description en caract�re.
	 */
	public String getDescription();

	/**
	 * Retourne le num�ro de s�rie.
	 *
	 * @return String Valeur du num�ro de s�rie en caract�re.
	 */
	public String getNumeroSerie();

	/**
	 * Retourne la date de d�but.
	 *
	 * @return Timestamp Valeur de la date de d�but en caract�re.
	 */
	public Timestamp getDateDebut();

	/**
	 * Retourne la date de fin.
	 *
	 * @return Timestamp Valeur de la date de fin en caract�re.
	 */
	public Timestamp getDateFin();

	/**
	 * Affecte un site.
	 *
	 * @param site Valeur du site en caract�re.
	 */
	public void setSite(long site);

	/**
	 * Affecte une description.
	 *
	 * @return description Valeur de la description en caract�re.
	 */
	public void setDescription(String description);

	/**
	 * Affecte une devise.
	 *
	 * @param devise Valeur de la devise en caract�re.
	 */
	public void setDevise(String devise);

	/**
	 * Affecte une d�nomination.
	 *
	 * @param dimension Valeur de la d�nomination en caract�re.
	 */
	public void setDenomination(String denomination);

	/**
	 * Affecte un montant.
	 *
	 * @param modele Valeur du montant en
	 * caract�re.
	 */
	public void setMontant(double montant);

	/**
	 * Affecte un num�ro de s�rie.
	 *
	 * @param numeroSerie Valeur du num�ro de s�rie en
	 * caract�re.
	 */
	public void setNumeroSerie(String numeroSerie);

	/**
	 * Affecte une perte.
	 *
	 * @param poids Valeur de la perte
	 * en caract�re.
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
	 * cr�ateur.
	 */
	public void setQuantite(long quantite);

	/**
	 * Affecte une date de d�but.
	 *
	 * @param dateDebut Valeur de dateDebut en caract�re.
	 */
	public void setDateDebut(Timestamp dateDebut);

	/**
	 * Affecte une date de fin.
	 *
	 * @param dateFin Valeur de dateFin en caract�re.
	 */
	public void setDateFin(Timestamp dateFin);

	/**
	 * Affecte un type de consignation.
	 *
	 * @param typeConsignation Valeur type de consignation 
	 * en caract�re.
	 */
	public void setTypeConsignation(
	String typeConsignation);

}