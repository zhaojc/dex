package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * Permet de transiter les informations relatives � une consignation de la couche
 * pr�sentation � la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/03/26 22:17:44 $
 */
public interface Consignation extends Modifiable{


    // Getters

    /**
     * Retourne la cl�.
     *
     * @return long Valeur de la cl� en caract�re.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site en caract�re.
     */
    public long getSite();

	/**
	 * Retourne certaines informations sur le dossier retourn�
	 * par une recherche de consignations.  Ces informations sont
	 * utilis�es pour appeler le dossier � l'�cran � partir de la
	 * liste de r�sultats.
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
     * @return long Valeur du type en caract�re.
     */
    public long getTypeConsignation();

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
     * Retourne le montant.
     *
     * @return double Valeur du montant en caract�re.
     */
    public double getMontant();

    /**
     * Retourne le poids.
     *
     * @return String Valeur du poids d'origine en caract�re.
     */
    public String getPoids();

    /**
     * Retourne la dimension.
     *
     * @return String Valeur de la dimension en caract�re.
     */
    public String getDimension();

    /**
     * Retourne la marque.
     *
     * @return String Valeur de la marque en caract�re.
     */
    public String getMarque();

    /**
     * Retourne le mod�le.
     *
     * @return String Valeur du mod�le assign� en caract�re.
     */
    public String getModele();

    /**
     * Retourne le fournisseur.
     *
     * @return String Valeur du fournisseur en caract�re en caract�re.
     */
    public String getFournisseur();

    /**
     * Retourne la description.
     *
     * @return String Valeur de la description en caract�re.
     */
    public String getDescription();

    /**
     * Retourne le commentaire.
     *
     * @return String Valeur du commentaire en caract�re.
     */
    public String getCommentaire();

    /**
     * Retourne le num�ro de s�rie.
     *
     * @return String Valeur du num�ro de s�rie en caract�re.
     */
    public String getNumeroSerie();

    /**
     * Retourne le cr�ateur.
     *
     * @return String Valeur du cr�ateur en caract�re.
     */
    public String getCreateur();

    /**
     * Retourne la date de cr�ation.
     *
     * @return Timestamp Valeur de la date de cr�ation en caract�re.
     */
    public Timestamp getDateCreation();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caract�re.
     */
    public String getModificateur();

    /**
     * Retourne la date de modification.
     *
     * @return Timestamp Valeur de la date de modification en caract�re.
     */
    public Timestamp getDateModification();

    /**
     * Retourne l'approbateur.
     *
     * @return String Valeur de l'approbateur en caract�re.
     */
    public String getApprobateur();

    /**
     * Retourne la date d'approbateur.
     *
     * @return Timestamp Valeur de la date d'approbateur en caract�re.
     */
    public Timestamp getDateApprobation();

    /**
     * Retourne la premi�re r�f�rence.
     *
     * @return String Valeur de la premi�re r�f�rence en caract�re.
     */
    public String getReference1();

    /**
     * Retourne la deuxi�me r�f�rence.
     *
     * @return String Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public String getReference2();

    /**
     * Retourne le lien.
     *
     * @return long Valeur du lien en caract�re.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site en caract�re.
     */
    public long getLienSite();

    /**
     * Retourne le genre de l'�l�ment auquel la consignation est li�e.
     *
     * @return String Valeur du genre en caract�re.
     */
    public String getLienGenre();


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cl� en caract�re.
     */
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(long site);

    /**
     * Affecte l'approbateur.
     *
     * @param activite Valeur de l'approbateur en caract�re.
     */
    public void setApprobateur(String approbateur);

    /**
     * Affecte un commentaire.
     *
     * @param commentaire Valeur du commentaire en caract�re.
     */
    public void setCommentaire(String commentaire);

    /**
     * Affecte la date d'approbation.
     *
     * @param createur Valeur de la date d'approbation en caract�re.
     */
    public void setDateApprobation(Timestamp dateApprobation);

    /**
     * Affecte une description.
     *
     * @return description Valeur de la description en caract�re.
     */
    public void setDescription(String description);

    /**
     * Affecte une denomination.
     *
     * @param devise Valeur de la devise en caract�re.
     */
    public void setDenomination(long denomination);

	/**
	 * Affecte une devise.
	 *
	 * @param devise Valeur de la devise en caract�re.
	 */
	public void setDevise(long devise);

    /**
     * Affecte une dimension.
     *
     * @param dimension Valeur de la dimension en caract�re.
     */
    public void setDimension(String dimension);

    /**
     * Affecte un fournisseur.
     *
     * @param fournisseur Valeur du fournisseur en caract�re.
     */
    public void setFournisseur(String fournisseur);

    /**
     * Affecte une marque .
     *
     * @param marque Valeur de la marque en caract�re.
     */
    public void setMarque(String marque);

    /**
     * Affecte un modele.
     *
     * @param modele Valeur du modele en
     * caract�re.
     */
    public void setModele(String modele);

    /**
     * Affecte un num�ro de s�rie.
     *
     * @param numeroSerie Valeur du num�ro de s�rie en
     * caract�re.
     */
    public void setNumeroSerie(String numeroSerie);

    /**
     * Affecte un poids.
     *
     * @param poids Valeur du poids
     * en caract�re.
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
     * cr�ateur.
     */
    public void setQuantite(long quantite);

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation en caract�re.
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caract�re.
     */
    public void setModificateur(String modificateur);

	/**
	 * Affecte un montant.
	 *
	 * @param modificateur Valeur du montant en caract�re.
	 */
	public void setMontant(double montant);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caract�re.
     */
    public void setDateModification(Timestamp dateModification);

    /**
     * Affecte un type de consignation.
     *
     * @param typeConsignation Valeur type de consignation 
     * en caract�re.
     */
    public void setTypeConsignation(
            long typeConsignation);

    /**
     * Affecte une premi�re r�f�rence.
     *
     * @param reference1 Valeur de la premi�re r�f�rence en caract�re.
     */
    public void setReference1(String reference1);

    /**
     * Retourne la deuxi�me r�f�rence.
     *
     * @param reference2 Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public void setReference2(String reference2);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caract�re.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSite(long lienSite);

    /**
     * Affecte le genre du fichier.
     *
     * @param lienGenre Valeur du genre du fichier en caract�re.
     */
    public void setLienGenre(String lienGenre);

    /**
     * Test si un suivi peut �tre modifi�.
     *
     * @return boolean True si la narration est modifiable.
     */
    public Boolean isModifiable();


    /**
     * Determine si un suivi est modifiable
     *
     * @param isModifiable Est-ce que le suivi est modifiable
     * caract�re.
     */
    public void setModifiable(Boolean isModifiable);


	/**
	 * Conserve certaines valeurs de dossier.
	 *
	 * @param dossier Dossier retourn� par une recherche de consignations.
	 */
	public void setDossier(Dossier dossier);

	public Boolean getApprouve();
	
}