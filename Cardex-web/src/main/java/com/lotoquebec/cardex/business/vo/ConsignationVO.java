package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.Consignation;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives à un dossier de la couche
 * présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.33 $, $Date: 2002/04/09 20:44:35 $
 * @see com.lotoquebec.cardex.business.Dossier
 */
public class ConsignationVO implements Consignation {
    private long cle = 0;
    private long site = 0;
    private Boolean approuve = false;
    private long quantite = 0;
    private long devise = 0;
	private long denomination = 0;
    private double prix = 0;
    private String poids = "";
    private String dimension = "";
    private double montant = 0;
    private String numeroSerie = "";
    private String marque = "";
    private String modele = "";
    private String fournisseur = "";
    private String description = "";
    private String createur = "";
    private String commentaire = "";
    private long typeConsignation = 0;
    private Timestamp dateCreation = null;
    private String modificateur = "";
    private Timestamp dateModification = null;
    private String approbateur = "";
    private Timestamp dateApprobation = null;
    private String reference1 = "";
    private String reference2 = "";
    private long lien = 0;
    private long lienSite = 0;
    private String lienGenre = "";
    private Boolean approuvable = false;
    private Boolean modifiable = false;
	private Dossier dossier = new DossierVO();

    /**
     * Constructeur de ConsignationVO par défaut.
     */
    public ConsignationVO() {}


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
        stringBuffer.append("', clé = '" + cle);
        stringBuffer.append("', type = '" + typeConsignation);
        stringBuffer.append("', description = '" + description);
        stringBuffer.append("', numero de série = '" + numeroSerie);
        stringBuffer.append("', marque = '" + marque);
        stringBuffer.append("', fournisseur = '" + fournisseur);
        stringBuffer.append("', dateCreation = '"
                + TimestampFormat.format(dateCreation));
        stringBuffer.append("', dateModification = '"
                + TimestampFormat.format(dateModification) );
        stringBuffer.append("', reference1 = '" + reference1);
        stringBuffer.append("', reference2 = '" + reference2);
        stringBuffer.append("', intervenant = '" + createur);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', lienGenre = '" + lienGenre + "']");
        return stringBuffer.toString();
    }
	 /**
	  * Retourne certaines informations sur le dossier retourné
	  * par une recherche de consignations.  Ces informations sont
	  * utilisées pour appeler le dossier à l'écran à partir de la
	  * liste de résultats.
	  *
	  * @return Dossier Valeurs du dossier.
	  */
	 public Dossier getDossier() {
		 return this.dossier;
	 }

	/**
	 * Returns the approbateur.
	 * @return String
	 */
	public String getApprobateur() {
		return approbateur;
	}

	/**
	 * Returns the approuve.
	 * @return String
	 */
	public Boolean isApprouve() {
		return approuve;
	}
	
	/**
	 * Returns the cle.
	 * @return long
	 */
	public long getCle() {
		return cle;
	}

	/**
	 * Returns the commentaire.
	 * @return String
	 */
	public String getCommentaire() {
		return commentaire;
	}

	/**
	 * Returns the createur.
	 * @return String
	 */
	public String getCreateur() {
		return createur;
	}

	/**
	 * Returns the dateApprobation.
	 * @return Timestamp
	 */
	public Timestamp getDateApprobation() {
		return dateApprobation;
	}

	/**
	 * Returns the dateCreation.
	 * @return Timestamp
	 */
	public Timestamp getDateCreation() {
		return dateCreation;
	}

	/**
	 * Returns the dateModification.
	 * @return Timestamp
	 */
	public Timestamp getDateModification() {
		return dateModification;
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
	 * @return long
	 */
	public long getDevise() {
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
	 * Returns the fournisseur.
	 * @return String
	 */
	public String getFournisseur() {
		return fournisseur;
	}

	/**
	 * Returns the isModifiable.
	 * @return boolean
	 */
	public Boolean isModifiable() {
		return modifiable;
	}

	/**
	 * Returns the lien.
	 * @return long
	 */
	public long getLien() {
		return lien;
	}

	/**
	 * Returns the lienGenre.
	 * @return String
	 */
	public String getLienGenre() {
		return lienGenre;
	}

	/**
	 * Returns the lienSite.
	 * @return long
	 */
	public long getLienSite() {
		return lienSite;
	}

	/**
	 * Returns the marque.
	 * @return String
	 */
	public String getMarque() {
		return marque;
	}

	/**
	 * Returns the modele.
	 * @return String
	 */
	public String getModele() {
		return modele;
	}

	/**
	 * Returns the modificateur.
	 * @return String
	 */
	public String getModificateur() {
		return modificateur;
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
	 * Returns the poids.
	 * @return String
	 */
	public String getPoids() {
		return poids;
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
	 * Returns the reference1.
	 * @return String
	 */
	public String getReference1() {
		return reference1;
	}

	/**
	 * Returns the reference2.
	 * @return String
	 */
	public String getReference2() {
		return reference2;
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
	public long getTypeConsignation() {
		return typeConsignation;
	}

	/**
	 * Sets the approbateur.
	 * @param approbateur The approbateur to set
	 */
	public void setApprobateur(String approbateur) {
		this.approbateur = approbateur;
	}

	/**
	 * Sets the cle.
	 * @param cle The cle to set
	 */
	public void setCle(long cle) {
		this.cle = cle;
	}

	/**
	 * Sets the commentaire.
	 * @param commentaire The commentaire to set
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	/**
	 * Sets the createur.
	 * @param createur The createur to set
	 */
	public void setCreateur(String createur) {
		this.createur = createur;
	}

	/**
	 * Sets the dateApprobation.
	 * @param dateApprobation The dateApprobation to set
	 */
	public void setDateApprobation(Timestamp dateApprobation) {
		this.dateApprobation = dateApprobation;
	}

	/**
	 * Sets the dateCreation.
	 * @param dateCreation The dateCreation to set
	 */
	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Sets the dateModification.
	 * @param dateModification The dateModification to set
	 */
	public void setDateModification(Timestamp dateModification) {
		this.dateModification = dateModification;
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
	public void setDevise(long devise) {
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
	 * Sets the fournisseur.
	 * @param fournisseur The fournisseur to set
	 */
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	/**
	 * Sets the lien.
	 * @param lien The lien to set
	 */
	public void setLien(long lien) {
		this.lien = lien;
	}

	/**
	 * Sets the lienGenre.
	 * @param lienGenre The lienGenre to set
	 */
	public void setLienGenre(String lienGenre) {
		this.lienGenre = lienGenre;
	}

	/**
	 * Sets the lienSite.
	 * @param lienSite The lienSite to set
	 */
	public void setLienSite(long lienSite) {
		this.lienSite = lienSite;
	}

	/**
	 * Sets the marque.
	 * @param marque The marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
	 * Sets the modele.
	 * @param modele The modele to set
	 */
	public void setModele(String modele) {
		this.modele = modele;
	}

	/**
	 * Sets the modificateur.
	 * @param modificateur The modificateur to set
	 */
	public void setModificateur(String modificateur) {
		this.modificateur = modificateur;
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
	 * Sets the poids.
	 * @param poids The poids to set
	 */
	public void setPoids(String poids) {
		this.poids = poids;
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
	 * Sets the reference1.
	 * @param reference1 The reference1 to set
	 */
	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}

	/**
	 * Sets the reference2.
	 * @param reference2 The reference2 to set
	 */
	public void setReference2(String reference2) {
		this.reference2 = reference2;
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
	public void setTypeConsignation(long typeConsignation) {
		this.typeConsignation = typeConsignation;
	}

	/**
	 * Conserve certaines valeurs de dossier.
	 *
	 * @param dossier Dossier retourné par une recherche de consignations.
	 */
	public void setDossier(Dossier dossier){
		this.dossier = dossier;
	}

	/**
	 * @return
	 */
	public long getDenomination() {
		return denomination;
	}

	/**
	 * @param l
	 */
	public void setDenomination(long l) {
		denomination = l;
	}

	public Boolean isApprouvable() {
		return approuvable;
	}

	public Boolean getApprouvable() {
		return approuvable;
	}

	public void setApprouvable(Boolean approuvable) {
		this.approuvable = approuvable;
	}

	public Boolean getModifiable() {
		return modifiable;
	}

	public void setModifiable(Boolean modifiable) {
		this.modifiable = modifiable;
	}

	public void setApprouve(Boolean approuve) {
		this.approuve = approuve;
	}

	public Boolean getApprouve() {
		return approuve;
	}

	
}