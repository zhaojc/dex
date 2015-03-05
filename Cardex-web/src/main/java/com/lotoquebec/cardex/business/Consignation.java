package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * Permet de transiter les informations relatives à une consignation de la couche
 * présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/03/26 22:17:44 $
 */
public interface Consignation extends Modifiable{


    // Getters

    /**
     * Retourne la clé.
     *
     * @return long Valeur de la clé en caractère.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site en caractère.
     */
    public long getSite();

	/**
	 * Retourne certaines informations sur le dossier retourné
	 * par une recherche de consignations.  Ces informations sont
	 * utilisées pour appeler le dossier à l'écran à partir de la
	 * liste de résultats.
	 *
	 * @return Dossier Valeurs du dossier.
	 */
	public Dossier getDossier();

    /**
     * Retourne la devise.
     *
     * @return long Valeur de la devise.
     */
    public long getDevise();

	/**
	 * Retourne la denomination.
	 *
	 * @return long Valeur de la denomination.
	 */
	public long getDenomination();

    /**
     * Retourne le type de consignation.
     *
     * @return long Valeur du type en caractère.
     */
    public long getTypeConsignation();

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
     * Retourne le montant.
     *
     * @return double Valeur du montant en caractère.
     */
    public double getMontant();

    /**
     * Retourne le poids.
     *
     * @return String Valeur du poids d'origine en caractère.
     */
    public String getPoids();

    /**
     * Retourne la dimension.
     *
     * @return String Valeur de la dimension en caractère.
     */
    public String getDimension();

    /**
     * Retourne la marque.
     *
     * @return String Valeur de la marque en caractère.
     */
    public String getMarque();

    /**
     * Retourne le modèle.
     *
     * @return String Valeur du modèle assigné en caractère.
     */
    public String getModele();

    /**
     * Retourne le fournisseur.
     *
     * @return String Valeur du fournisseur en caractère en caractère.
     */
    public String getFournisseur();

    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caractère.
     */
    public String getDescription();

    /**
     * Retourne le commentaire.
     *
     * @return String Valeur du commentaire en caractère.
     */
    public String getCommentaire();

    /**
     * Retourne le numéro de série.
     *
     * @return String Valeur du numéro de série en caractère.
     */
    public String getNumeroSerie();

    /**
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur();

    /**
     * Retourne la date de création.
     *
     * @return Timestamp Valeur de la date de création en caractère.
     */
    public Timestamp getDateCreation();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caractère.
     */
    public String getModificateur();

    /**
     * Retourne la date de modification.
     *
     * @return Timestamp Valeur de la date de modification en caractère.
     */
    public Timestamp getDateModification();

    /**
     * Retourne l'approbateur.
     *
     * @return String Valeur de l'approbateur en caractère.
     */
    public String getApprobateur();

    /**
     * Retourne la date d'approbateur.
     *
     * @return Timestamp Valeur de la date d'approbateur en caractère.
     */
    public Timestamp getDateApprobation();

    /**
     * Retourne la première référence.
     *
     * @return String Valeur de la première référence en caractère.
     */
    public String getReference1();

    /**
     * Retourne la deuxième référence.
     *
     * @return String Valeur de la deuxième référence en caractère.
     */
    public String getReference2();

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien en caractère.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site en caractère.
     */
    public long getLienSite();

    /**
     * Retourne le genre de l'élément auquel la consignation est liée.
     *
     * @return String Valeur du genre en caractère.
     */
    public String getLienGenre();


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la clé en caractère.
     */
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(long site);

    /**
     * Affecte l'approbateur.
     *
     * @param activite Valeur de l'approbateur en caractère.
     */
    public void setApprobateur(String approbateur);

    /**
     * Affecte un commentaire.
     *
     * @param commentaire Valeur du commentaire en caractère.
     */
    public void setCommentaire(String commentaire);

    /**
     * Affecte la date d'approbation.
     *
     * @param createur Valeur de la date d'approbation en caractère.
     */
    public void setDateApprobation(Timestamp dateApprobation);

    /**
     * Affecte une description.
     *
     * @return description Valeur de la description en caractère.
     */
    public void setDescription(String description);

    /**
     * Affecte une denomination.
     *
     * @param devise Valeur de la devise en caractère.
     */
    public void setDenomination(long denomination);

	/**
	 * Affecte une devise.
	 *
	 * @param devise Valeur de la devise en caractère.
	 */
	public void setDevise(long devise);

    /**
     * Affecte une dimension.
     *
     * @param dimension Valeur de la dimension en caractère.
     */
    public void setDimension(String dimension);

    /**
     * Affecte un fournisseur.
     *
     * @param fournisseur Valeur du fournisseur en caractère.
     */
    public void setFournisseur(String fournisseur);

    /**
     * Affecte une marque .
     *
     * @param marque Valeur de la marque en caractère.
     */
    public void setMarque(String marque);

    /**
     * Affecte un modele.
     *
     * @param modele Valeur du modele en
     * caractère.
     */
    public void setModele(String modele);

    /**
     * Affecte un numéro de série.
     *
     * @param numeroSerie Valeur du numéro de série en
     * caractère.
     */
    public void setNumeroSerie(String numeroSerie);

    /**
     * Affecte un poids.
     *
     * @param poids Valeur du poids
     * en caractère.
     */
    public void setPoids(String poids);

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
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création en caractère.
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caractère.
     */
    public void setModificateur(String modificateur);

	/**
	 * Affecte un montant.
	 *
	 * @param modificateur Valeur du montant en caractère.
	 */
	public void setMontant(double montant);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caractère.
     */
    public void setDateModification(Timestamp dateModification);

    /**
     * Affecte un type de consignation.
     *
     * @param typeConsignation Valeur type de consignation 
     * en caractère.
     */
    public void setTypeConsignation(
            long typeConsignation);

    /**
     * Affecte une première référence.
     *
     * @param reference1 Valeur de la première référence en caractère.
     */
    public void setReference1(String reference1);

    /**
     * Retourne la deuxième référence.
     *
     * @param reference2 Valeur de la deuxième référence en caractère.
     */
    public void setReference2(String reference2);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(long lienSite);

    /**
     * Affecte le genre du fichier.
     *
     * @param lienGenre Valeur du genre du fichier en caractère.
     */
    public void setLienGenre(String lienGenre);

    /**
     * Test si un suivi peut être modifié.
     *
     * @return boolean True si la narration est modifiable.
     */
    public Boolean isModifiable();


    /**
     * Determine si un suivi est modifiable
     *
     * @param isModifiable Est-ce que le suivi est modifiable
     * caractère.
     */
    public void setModifiable(Boolean isModifiable);


	/**
	 * Conserve certaines valeurs de dossier.
	 *
	 * @param dossier Dossier retourné par une recherche de consignations.
	 */
	public void setDossier(Dossier dossier);

	public Boolean getApprouve();
	
}