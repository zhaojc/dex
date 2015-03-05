package com.lotoquebec.cardex.presentation.model;

/**
 * Définit la signature des méthodes des différentes valeurs relatives au
 * formulatire de consultation des consignations.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $, $Date: 2002/03/26 22:18:39 $
 */
public interface ConsignationHtmlForm {


    // Getters

    /**
     * Retourne la clé.
     *
     * @return String Valeur de la clé en caractère.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caractère.
     */
    public String getSite();

    /**
     * Retourne la devise.
     *
     * @return String Valeur de la devise.
     */
    public String getDevise();

    /**
     * Retourne le type de consignation.
     *
     * @return String Valeur du type en caractère.
     */
    public String getTypeConsignation();

    /**
     * Retourne la quantite.
     *
     * @return String Valeur de la quantité en caractère.
     */
    public String getQuantite();

    /**
     * Retourne le prix unitaire.
     *
     * @return String Valeur du prix en caractère.
     */
    public String getPrix();

	/**
	 * Retourne la dénomination.
	 *
	 * @return String Valeur de la dénomination en caractère.
	 */
	public String getDenomination();

    /**
     * Retourne le montant.
     *
     * @return String Valeur du montant en caractère.
     */
    public String getMontant();

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
     * @return String Valeur de la date de création en caractère.
     */
    public String getDateCreation();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caractère.
     */
    public String getModificateur();

    /**
     * Retourne la date de modification.
     *
     * @return String Valeur de la date de modification en caractère.
     */
    public String getDateModification();

    /**
     * Retourne l'approbateur.
     *
     * @return String Valeur de l'approbateur en caractère.
     */
    public String getApprobateur();

    /**
     * Retourne si approuvé ou non.
     *
     * @return String Valeur approuvé en
     * caractère.
     */
    public boolean getApprouve();

    /**
     * Retourne la date d'approbateur.
     *
     * @return String Valeur de la date d'approbateur en caractère.
     */
    public String getDateApprobation();

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
     * @return String Valeur du lien en caractère.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caractère.
     */
    public String getLienSite();

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
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(String site);

    /**
     * Affecte l'approbateur.
     *
     * @param activite Valeur de l'approbateur en caractère.
     */
    public void setApprobateur(String approbateur);

    /**
     * Affecte approuvé.
     *
     * @param approuve Valeur de approuve en caractère.
     */
    public void setApprouve(boolean approuve);

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
    public void setDateApprobation(String dateApprobation);

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
     * Affecte une dimension.
     *
     * @param dimension Valeur de la dimension en caractère.
     */
    public void setDimension(String dimension);

	/**
	 * Affecte une dénomination.
	 *
	 * @param dimension Valeur de la dénomination en caractère.
	 */
	public void setDenomination(String denomination);

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
	 * Affecte un montant.
	 *
	 * @param modele Valeur du montant en
	 * caractère.
	 */
	public void setMontant(String montant);

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
    public void setPrix(String prix);

    /**
     * Affecte une quantite.
     *
     * @param quantite Valeur de la quantite du
     * créateur.
     */
    public void setQuantite(String quantite);

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création en caractère.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caractère.
     */
    public void setModificateur(String modificateur);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caractère.
     */
    public void setDateModification(String dateModification);

    /**
     * Affecte un type de consignation.
     *
     * @param typeConsignation Valeur type de consignation 
     * en caractère.
     */
    public void setTypeConsignation(
            String typeConsignation);

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
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(String lienSite);

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
    public boolean isModifiable();

    /**
     * Test si un suivi peut être approuvé.
     *
     * @return boolean True si la narration est approuvable.
     */
    public boolean getApprouvable();

    /**
     * Determine si un suivi est modifiable
     *
     * @param isModifiable Est-ce que le suivi est modifiable
     * caractère.
     */
    public void setModifiable(boolean isModifiable);

    /**
     * Determine si un suivi est approuvanle
     *
     * @param isApprouvable Est-ce que le suivi est approuvable
     * caractère.
     */
    public void setApprouvable(boolean approuvable);

}