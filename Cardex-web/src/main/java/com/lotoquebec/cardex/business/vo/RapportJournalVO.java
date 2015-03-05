package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.lotoquebec.cardex.business.RapportJournal;

/**
 * Permet de transiter les informations relatives à la consultation d'un sujet
 * de la couche présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.21 $, $Date: 2002/04/15 14:03:08 $
 * @see com.lotoquebec.cardex.business.Sujet
 */
public class RapportJournalVO implements RapportJournal {

    private Timestamp    dateDebut = null;
    private Timestamp    dateFin = null;
    private String       intervenant = "";
    private String       description = "";
    private String       descriptionCategorie = "";
    private String       descriptif = "";
    private long         localisation = 0;
    private long       	origine = 0;
    private long         endroit = 0;
    private long         site = 0;
    private long         cleType = 0;
    private long         duree = 0;
    private long         nombre = 0;
    private String       secteur = "";
    public ArrayList     rapportJournal = new ArrayList();

    /**
     * Constructeur de DossierVO par défaut.
     */
    public RapportJournalVO() {}



    // Getters


    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du JournalForm.
     *
     * @return String Valeur de tout les attributs du JournalForm en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[RapportJournalForm : ");
        stringBuffer.append("description = '" + description);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', dateDebut = '" + dateDebut);
        stringBuffer.append("', dateFin = '" + dateFin);
        stringBuffer.append("', duree = '" + duree);
        stringBuffer.append("', nombre = '" + nombre);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("']");
        return stringBuffer.toString();
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
	 * @return long
	 */
	public long getDuree() {
		return duree;
	}

	/**
	 * Returns the intervenant.
	 * @return String
	 */
	public String getIntervenant() {
		return intervenant;
	}

	/**
	 * Returns the site.
	 * @return long
	 */
	public long getSite() {
		return site;
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
	public void setDuree(long duree) {
		this.duree = duree;
	}

	/**
	 * Sets the intervenant.
	 * @param intervenant The intervenant to set
	 */
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	/**
	 * Sets the site.
	 * @param site The site to set
	 */
	public void setSite(long site) {
		this.site = site;
	}

	/**
	 * Returns the nombre.
	 * @return String
	 */
	public long getNombre() {
		return nombre;
	}

	/**
	 * Returns the secteur.
	 * @return String
	 */
	public String getSecteur() {
		return secteur;
	}

	/**
	 * Sets the nombre.
	 * @param nombre The nombre to set
	 */
	public void setNombre(long nombre) {
		this.nombre = nombre;
	}

	/**
	 * Sets the secteur.
	 * @param secteur The secteur to set
	 */
	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

    /**
     * Ajouteun détail du rapport.
     *
     * @param rapportJournal Valeur du détail.
     */
    public void addRapportJournal(RapportJournal rapportJournal) {
        this.rapportJournal.add(rapportJournal);
    }

	/**
	 * Returns the cleType.
	 * @return long
	 */
	public long getCleType() {
		return cleType;
	}

	/**
	 * Sets the cleType.
	 * @param cleType The cleType to set
	 */
	public void setCleType(long cleType) {
		this.cleType = cleType;
	}

	/**
	 * Returns the descriptif.
	 * @return String
	 */
	public String getDescriptif() {
		return descriptif;
	}

	/**
	 * Returns the descriptionCategorie.
	 * @return String
	 */
	public String getDescriptionCategorie() {
		return descriptionCategorie;
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
	 * Sets the descriptionCategorie.
	 * @param descriptionCategorie The descriptionCategorie to set
	 */
	public void setDescriptionCategorie(String descriptionCategorie) {
		this.descriptionCategorie = descriptionCategorie;
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
	 * Sets the origine.
	 * @param origine The origine to set
	 */
	public void setOrigine(long origine) {
		this.origine = origine;
	}

}
