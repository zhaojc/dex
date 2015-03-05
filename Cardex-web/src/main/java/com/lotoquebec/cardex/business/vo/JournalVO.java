package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.Journal;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives à la consultation d'un sujet
 * de la couche présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.21 $, $Date: 2002/04/15 14:03:08 $
 * @see com.lotoquebec.cardex.business.Sujet
 */
public class JournalVO implements Journal {

    private long         categorie = 0;
    private long         type = 0;
    private long nature = 0;
    private Timestamp    dateDebut = null;
    private Timestamp    dateFin = null;
    private Timestamp    dateCreation = null;
    private String       numeroDossier = "";
    private String       numeroEmploye = "";
    private String 		 reference2 = "";
    private String 		 reference3 = "";
    private String       numeroIncident = "";
    private String       referenceVideo = "";
    private String       intervenant = "";
    private long         endroit = 0;
    private long         localisation = 0;
    private long       	  origine = 0;
    private String       description = "";
    private String       descriptif = "";
    private long         cle = 0;
    private long         site = 0;
    private long         cleNarration = 0;
    private long         siteNarration = 0;
    private String       duree = "";
    private Boolean      modifiable = false;
    private Boolean      nouveau = false;

    /**
     * Constructeur de SujetVO par défaut.
     */
    public JournalVO() {}


    // Getters

	public JournalVO(long cle, long site) {
		super();
		this.cle = cle;
		this.site = site;
	}


	/**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du SujetVO.
     *
     * @return String Valeur de tout les attributs du SujetVO en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[JournalVO : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', date de début = '" + TimestampFormat.format(dateDebut));
        return stringBuffer.toString();
    }

	/**
	 * Returns the categorie.
	 * @return long
	 */
	public long getCategorie() {
		return categorie;
	}

	/**
	 * Returns the cle.
	 * @return long
	 */
	public long getCle() {
		return cle;
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
	 * Returns the duree.
	 * @return String
	 */
	public String getDuree() {
		return duree;
	}

	/**
	 * Returns the endroit.
	 * @return String
	 */
	public long getEndroit() {
		return endroit;
	}

	/**
	 * Returns the intervenant.
	 * @return String
	 */
	public String getIntervenant() {
		return intervenant;
	}

	/**
	 * Returns the isModifiable.
	 * @return boolean
	 */
	public Boolean isModifiable() {
		return modifiable;
	}

	/**
	 * Returns the isNew.
	 * @return boolean
	 */
	public Boolean isNew() {
		return nouveau;
	}

	public Boolean isNouveau() {
		return nouveau;
	}
	
	/**
	 * Returns the numeroEmploye.
	 * @return String
	 */
	public String getNumeroEmploye() {
		return numeroEmploye;
	}

	/**
	 * Returns the referenceVideo.
	 * @return String
	 */
	public String getReferenceVideo() {
		return referenceVideo;
	}

	/**
	 * Returns the site.
	 * @return long
	 */
	public long getSite() {
		return site;
	}

	/**
	 * Sets the categorie.
	 * @param categorie The categorie to set
	 */
	public void setCategorie(long categorie) {
		this.categorie = categorie;
	}

	/**
	 * Sets the cle.
	 * @param cle The cle to set
	 */
	public void setCle(long cle) {
		this.cle = cle;
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
	 * Sets the duree.
	 * @param duree The duree to set
	 */
	public void setDuree(String duree) {
		this.duree = duree;
	}

	/**
	 * Sets the endroit.
	 * @param endroit The endroit to set
	 */
	public void setEndroit(long endroit) {
		this.endroit = endroit;
	}

	/**
	 * Sets the intervenant.
	 * @param intervenant The intervenant to set
	 */
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	/**
	 * Sets the isModifiable.
	 * @param isModifiable The isModifiable to set
	 */
	public void setModifiable(Boolean modifiable) {
		this.modifiable = modifiable;
	}

	/**
	 * Sets the isNew.
	 * @param isNew The isNew to set
	 */
	public void setNew(Boolean nouveau) {
		this.nouveau = nouveau;
	}

	/**
	 * Sets the numeroEmploye.
	 * @param numeroEmploye The numeroEmploye to set
	 */
	public void setNumeroEmploye(String numeroEmploye) {
		this.numeroEmploye = numeroEmploye;
	}

	/**
	 * Sets the referenceVideo.
	 * @param referenceVideo The referenceVideo to set
	 */
	public void setReferenceVideo(String referenceVideo) {
		this.referenceVideo = referenceVideo;
	}

	/**
	 * Sets the site.
	 * @param site The site to set
	 */
	public void setSite(long site) {
		this.site = site;
	}

	/**
	 * Returns the descriptif.
	 * @return String
	 */
	public String getDescriptif() {
		return descriptif;
	}

	/**
	 * Returns the localisation.
	 * @return long
	 */
	public long getLocalisation() {
		return localisation;
	}

	/**
	 * Returns the origine.
	 * @return String
	 */
	public long getOrigine() {
		return origine;
	}

	/**
	 * Sets the descriptif.
	 * @param descriptif The descriptif to set
	 */
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	/**
	 * Sets the localisation.
	 * @param localisation The localisation to set
	 */
	public void setLocalisation(long localisation) {
		this.localisation = localisation;
	}

	/**
	 * Sets the origine.
	 * @param origine The origine to set
	 */
	public void setOrigine(long origine) {
		this.origine = origine;
	}

	/**
	 * Returns the numeroIncident.
	 * @return String
	 */
	public String getNumeroIncident() {
		return numeroIncident;
	}

	/**
	 * Sets the numeroIncident.
	 * @param numeroIncident The numeroIncident to set
	 */
	public void setNumeroIncident(String numeroIncident) {
		this.numeroIncident = numeroIncident;
	}

	/**
	 * Returns the numeroDossier.
	 * @return String
	 */
	public String getNumeroDossier() {
		return numeroDossier;
	}

	/**
	 * Sets the numeroDossier.
	 * @param numeroDossier The numeroDossier to set
	 */
	public void setNumeroDossier(String numeroDossier) {
		this.numeroDossier = numeroDossier;
	}

	/**
	 * Returns the type.
	 * @return long
	 */
	public long getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * @param type The type to set
	 */
	public void setType(long type) {
		this.type = type;
	}

	/**
	 * Returns the cleNarration.
	 * @return long
	 */
	public long getCleNarration() {
		return cleNarration;
	}

	/**
	 * Returns the dateCreation.
	 * @return Timestamp
	 */
	public Timestamp getDateCreation() {
		return dateCreation;
	}

	/**
	 * Returns the siteNarration.
	 * @return long
	 */
	public long getSiteNarration() {
		return siteNarration;
	}

	/**
	 * Sets the cleNarration.
	 * @param cleNarration The cleNarration to set
	 */
	public void setCleNarration(long cleNarration) {
		this.cleNarration = cleNarration;
	}

	/**
	 * Sets the dateCreation.
	 * @param dateCreation The dateCreation to set
	 */
	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Sets the siteNarration.
	 * @param siteNarration The siteNarration to set
	 */
	public void setSiteNarration(long siteNarration) {
		this.siteNarration = siteNarration;
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


	public void setNouveau(Boolean nouveau) {
		this.nouveau = nouveau;
	}


	public long getNature() {
		return nature;
	}


	public void setNature(long nature) {
		this.nature = nature;
	}


	public Boolean getModifiable() {
		return modifiable;
	}


	public Boolean getNouveau() {
		return nouveau;
	}


	
	
}
