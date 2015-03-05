package com.lotoquebec.cardex.presentation.model;

/**
 * D�finit la signature des m�thodes des diff�rentes valeurs relatives au
 * formulatire de consultation des consignations.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $, $Date: 2002/03/26 22:18:39 $
 */
public interface ConsignationHtmlForm {


    // Getters

    /**
     * Retourne la cl�.
     *
     * @return String Valeur de la cl� en caract�re.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caract�re.
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
     * @return String Valeur du type en caract�re.
     */
    public String getTypeConsignation();

    /**
     * Retourne la quantite.
     *
     * @return String Valeur de la quantit� en caract�re.
     */
    public String getQuantite();

    /**
     * Retourne le prix unitaire.
     *
     * @return String Valeur du prix en caract�re.
     */
    public String getPrix();

	/**
	 * Retourne la d�nomination.
	 *
	 * @return String Valeur de la d�nomination en caract�re.
	 */
	public String getDenomination();

    /**
     * Retourne le montant.
     *
     * @return String Valeur du montant en caract�re.
     */
    public String getMontant();

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
     * @return String Valeur de la date de cr�ation en caract�re.
     */
    public String getDateCreation();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caract�re.
     */
    public String getModificateur();

    /**
     * Retourne la date de modification.
     *
     * @return String Valeur de la date de modification en caract�re.
     */
    public String getDateModification();

    /**
     * Retourne l'approbateur.
     *
     * @return String Valeur de l'approbateur en caract�re.
     */
    public String getApprobateur();

    /**
     * Retourne si approuv� ou non.
     *
     * @return String Valeur approuv� en
     * caract�re.
     */
    public boolean getApprouve();

    /**
     * Retourne la date d'approbateur.
     *
     * @return String Valeur de la date d'approbateur en caract�re.
     */
    public String getDateApprobation();

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
     * @return String Valeur du lien en caract�re.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caract�re.
     */
    public String getLienSite();

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
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(String site);

    /**
     * Affecte l'approbateur.
     *
     * @param activite Valeur de l'approbateur en caract�re.
     */
    public void setApprobateur(String approbateur);

    /**
     * Affecte approuv�.
     *
     * @param approuve Valeur de approuve en caract�re.
     */
    public void setApprouve(boolean approuve);

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
    public void setDateApprobation(String dateApprobation);

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
     * Affecte une dimension.
     *
     * @param dimension Valeur de la dimension en caract�re.
     */
    public void setDimension(String dimension);

	/**
	 * Affecte une d�nomination.
	 *
	 * @param dimension Valeur de la d�nomination en caract�re.
	 */
	public void setDenomination(String denomination);

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
	 * Affecte un montant.
	 *
	 * @param modele Valeur du montant en
	 * caract�re.
	 */
	public void setMontant(String montant);

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
    public void setPrix(String prix);

    /**
     * Affecte une quantite.
     *
     * @param quantite Valeur de la quantite du
     * cr�ateur.
     */
    public void setQuantite(String quantite);

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation en caract�re.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caract�re.
     */
    public void setModificateur(String modificateur);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caract�re.
     */
    public void setDateModification(String dateModification);

    /**
     * Affecte un type de consignation.
     *
     * @param typeConsignation Valeur type de consignation 
     * en caract�re.
     */
    public void setTypeConsignation(
            String typeConsignation);

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
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSite(String lienSite);

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
    public boolean isModifiable();

    /**
     * Test si un suivi peut �tre approuv�.
     *
     * @return boolean True si la narration est approuvable.
     */
    public boolean getApprouvable();

    /**
     * Determine si un suivi est modifiable
     *
     * @param isModifiable Est-ce que le suivi est modifiable
     * caract�re.
     */
    public void setModifiable(boolean isModifiable);

    /**
     * Determine si un suivi est approuvanle
     *
     * @param isApprouvable Est-ce que le suivi est approuvable
     * caract�re.
     */
    public void setApprouvable(boolean approuvable);

}