package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.CriteresRechercheJournal;

/**
 * Permet de transiter les informations relatives à la recherche d'un sujet de
 * la couche présentation à la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.12 $, $Date: 2002/02/22 21:34:48 $
 * @see com.lotoquebec.cardex.business.CriteresRechercheSujet
 */
public class CriteresRechercheJournalVO implements CriteresRechercheJournal {

    private long entite = 0;
    private long site = 0;
    private long genre = 0;
    private long nature = 0;
    private long type = 0;
    private String descriptionType = "";
    private long categorie = 0;
    private long fonde = 0;
    private String intervenant = "";
    private String numeroDossier = "";
    private String secteur = "";
    private long origine = 0;
    private String descriptif = "";
    private String numeroEmploye = "";
    private String reference2 = "";
    private String reference3 = "";
    private Timestamp dateCreationDu = null;
    private Timestamp dateCreationAu = null;
    private long endroit = 0;
    private long localisation = 0;
    private String typeRapport = "";
    private String    ordreTriRecherche = "";
    private Boolean   ordreCroissantRecherche = true;
    private long      maximumResultatsRecherche = 0;
    private int sequence = 0;
    

    /**
     * Constructeur de CriteresRechercheSujetVO par défaut.
     */
    public CriteresRechercheJournalVO() {}


    // Getters


	/**
	 * Returns the categorie.
	 * @return long
	 */
	public long getCategorie() {
		return categorie;
	}

	/**
	 * Returns the dateCreationAu.
	 * @return Timestamp
	 */
	public Timestamp getDateCreationAu() {
		return dateCreationAu;
	}

	/**
	 * Returns the dateCreationDu.
	 * @return Timestamp
	 */
	public Timestamp getDateCreationDu() {
		return dateCreationDu;
	}

	/**
	 * Returns the entite.
	 * @return long
	 */
	public long getEntite() {
		return entite;
	}

	/**
	 * Returns the intervenant.
	 * @return String
	 */
	public String getIntervenant() {
		return intervenant;
	}

	/**
	 * Returns the maximumResultatsRecherche.
	 * @return long
	 */
	public long getMaximumResultatsRecherche() {
		return maximumResultatsRecherche;
	}

	/**
	 * Returns the ordreCroissantRecherche.
	 * @return boolean
	 */
	public Boolean isOrdreCroissantRecherche() {
		return ordreCroissantRecherche;
	}

	/**
	 * Returns the ordreTriRecherche.
	 * @return String
	 */
	public String getOrdreTriRecherche() {
		return ordreTriRecherche;
	}

	/**
	 * Returns the site.
	 * @return long
	 */
	public long getSite() {
		return site;
	}

	/**
	 * Returns the type.
	 * @return long
	 */
	public long getType() {
		return type;
	}

	/**
	 * Returns the typeRapport.
	 * @return String
	 */
	public String getTypeRapport() {
		return typeRapport;
	}

	/**
	 * Sets the categorie.
	 * @param categorie The categorie to set
	 */
	public void setCategorie(long categorie) {
		this.categorie = categorie;
	}

	/**
	 * Sets the dateCreationAu.
	 * @param dateCreationAu The dateCreationAu to set
	 */
	public void setDateCreationAu(Timestamp dateCreationAu) {
		this.dateCreationAu = dateCreationAu;
	}

	/**
	 * Sets the dateCreationDu.
	 * @param dateCreationDu The dateCreationDu to set
	 */
	public void setDateCreationDu(Timestamp dateCreationDu) {
		this.dateCreationDu = dateCreationDu;
	}

	/**
	 * Sets the entite.
	 * @param entite The entite to set
	 */
	public void setEntite(long entite) {
		this.entite = entite;
	}

	/**
	 * Sets the intervenant.
	 * @param intervenant The intervenant to set
	 */
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	/**
	 * Sets the maximumResultatsRecherche.
	 * @param maximumResultatsRecherche The maximumResultatsRecherche to set
	 */
	public void setMaximumResultatsRecherche(long maximumResultatsRecherche) {
		this.maximumResultatsRecherche = maximumResultatsRecherche;
	}

	/**
	 * Sets the ordreCroissantRecherche.
	 * @param ordreCroissantRecherche The ordreCroissantRecherche to set
	 */
	public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche) {
		this.ordreCroissantRecherche = ordreCroissantRecherche;
	}

	/**
	 * Sets the ordreTriRecherche.
	 * @param ordreTriRecherche The ordreTriRecherche to set
	 */
	public void setOrdreTriRecherche(String ordreTriRecherche) {
		this.ordreTriRecherche = ordreTriRecherche;
	}

	/**
	 * Sets the site.
	 * @param site The site to set
	 */
	public void setSite(long site) {
		this.site = site;
	}

	/**
	 * Sets the type.
	 * @param type The type to set
	 */
	public void setType(long type) {
		this.type = type;
	}

	/**
	 * Sets the typeRapport.
	 * @param typeRapport The typeRapport to set
	 */
	public void setTypeRapport(String typeRapport) {
		this.typeRapport = typeRapport;
	}

	/**
	 * Returns the endroit.
	 * @return long
	 */
	public long getEndroit() {
		return endroit;
	}

	/**
	 * Returns the localisation.
	 * @return long
	 */
	public long getLocalisation() {
		return localisation;
	}

	/**
	 * Sets the endroit.
	 * @param endroit The endroit to set
	 */
	public void setEndroit(long endroit) {
		this.endroit = endroit;
	}

	/**
	 * Sets the localisation.
	 * @param localisation The localisation to set
	 */
	public void setLocalisation(long localisation) {
		this.localisation = localisation;
	}

	/**
	 * Returns the groupe.
	 * @return String
	 */
	public String getSecteur() {
		return secteur;
	}

	/**
	 * Sets the groupe.
	 * @param groupe The groupe to set
	 */
	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	/**
	 * Returns the descriptionType.
	 * @return String
	 */
	public String getDescriptionType() {
		return descriptionType;
	}

	/**
	 * Sets the descriptionType.
	 * @param descriptionType The descriptionType to set
	 */
	public void setDescriptionType(String descriptionType) {
		this.descriptionType = descriptionType;
	}

	/**
	 * Returns the descriptif.
	 * @return String
	 */
	public String getDescriptif() {
		return descriptif;
	}

	/**
	 * Returns the numeroEmploye.
	 * @return String
	 */
	public String getNumeroEmploye() {
		return numeroEmploye;
	}

	/**
	 * Sets the descriptif.
	 * @param descriptif The descriptif to set
	 */
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	/**
	 * Sets the numeroEmploye.
	 * @param numeroEmploye The numeroEmploye to set
	 */
	public void setNumeroEmploye(String numeroEmploye) {
		this.numeroEmploye = numeroEmploye;
	}

	/**
	 * Returns the origine.
	 * @return String
	 */
	public long getOrigine() {
		return origine;
	}

	/**
	 * Sets the origine.
	 * @param origine The origine to set
	 */
	public void setOrigine(long origine) {
		this.origine = origine;
	}

	/**
	 * Returns the genre.
	 * @return long
	 */
	public long getGenre() {
		return genre;
	}

	/**
	 * Returns the nature.
	 * @return long
	 */
	public long getNature() {
		return nature;
	}

	/**
	 * Sets the genre.
	 * @param genre The genre to set
	 */
	public void setGenre(long genre) {
		this.genre = genre;
	}

	/**
	 * Sets the nature.
	 * @param nature The nature to set
	 */
	public void setNature(long nature) {
		this.nature = nature;
	}

	/**
	 * @return Returns the fonde.
	 */
	public long getFonde() {
		return fonde;
	}
	/**
	 * @param fonde The fonde to set.
	 */
	public void setFonde(long fonde) {
		this.fonde = fonde;
	}


	/**
	 * @return reference2
	 */
	public String getReference2() {
		return reference2;
	}


	/**
	 * @param reference2 reference2 à définir
	 */
	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}


	/**
	 * @return reference3
	 */
	public String getReference3() {
		return reference3;
	}


	/**
	 * @param reference3 reference3 à définir
	 */
	public void setReference3(String reference3) {
		this.reference3 = reference3;
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
	 * @return numeroDossier
	 */
	public String getNumeroDossier() {
		return numeroDossier;
	}


	/**
	 * @param numeroDossier numeroDossier à définir
	 */
	public void setNumeroDossier(String numeroDossier) {
		this.numeroDossier = numeroDossier;
	}
	
	
}
