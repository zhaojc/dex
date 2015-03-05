package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.RapportJournalHtmlForm;

/**
 * Conserve les différentes valeurs relatives au formulatire du journal.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.14 $, $Date: 2002/04/10 15:53:12 $
 * @see com.lotoquebec.cardex.presentation.model.JournalHtmlForm
 */
public class RapportJournalForm extends ValidatorForm implements RapportJournalHtmlForm, Serializable {

    private String       dateDebut = "";
    private String       dateFin = "";
    private String       intervenant = "";
    private String       description = "";
    private String       descriptionCategorie = "";
    private String       descriptif = "";
    private String       localisation = "";
    private String       origine = "";
    private String       endroit = "";
    private String       site = "";
    private String       cleType = "";
    private String       duree = "";
    private String       nombre = "";
    private String       secteur = "";
    public ArrayList     rapportJournal = new ArrayList();


    /**
     * Constructeur de RapportJournalForm par défaut.
     */
    public RapportJournalForm() {}


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
	 * @return String
	 */
	public String getDateDebut() {
		return dateDebut;
	}

	/**
	 * Returns the dateFin.
	 * @return String
	 */
	public String getDateFin() {
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
	 * Returns the intervenant.
	 * @return String
	 */
	public String getIntervenant() {
		return intervenant;
	}

	/**
	 * Returns the site.
	 * @return String
	 */
	public String getSite() {
		return site;
	}

	/**
	 * Sets the dateDebut.
	 * @param dateDebut The dateDebut to set
	 */
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * Sets the dateFin.
	 * @param dateFin The dateFin to set
	 */
	public void setDateFin(String dateFin) {
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
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * Returns the nombre.
	 * @return String
	 */
	public String getNombre() {
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
     * Retourne le détail du rapport
     *
     * @return Collection Valeur du détail.
     */
    public Collection getRapportJournal() {
        return this.rapportJournal;
    }

	/**
	 * Sets the nombre.
	 * @param nombre The nombre to set
	 */
	public void setNombre(String nombre) {
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
    public void addRapportJournal(RapportJournalHtmlForm rapportJournal) {
        this.rapportJournal.add(rapportJournal);
    }

	/**
	 * Returns the cleType.
	 * @return String
	 */
	public String getCleType() {
		return cleType;
	}

	/**
	 * Sets the cleType.
	 * @param cleType The cleType to set
	 */
	public void setCleType(String cleType) {
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
	 * @return String
	 */
	public String getEndroit() {
		return endroit;
	}

	/**
	 * Returns the localisation.
	 * @return String
	 */
	public String getLocalisation() {
		return localisation;
	}

	/**
	 * Returns the origine.
	 * @return String
	 */
	public String getOrigine() {
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
	public void setEndroit(String endroit) {
		this.endroit = endroit;
	}

	/**
	 * Sets the localisation.
	 * @param localisation The localisation to set
	 */
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	/**
	 * Sets the origine.
	 * @param origine The origine to set
	 */
	public void setOrigine(String origine) {
		this.origine = origine;
	}

}