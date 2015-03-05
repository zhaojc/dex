package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.JournalHtmlForm;
import com.lotoquebec.cardex.presentation.model.NumeroCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.business.EntiteCardex;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.EndroitCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.CategorieCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.TypeCleMultiListeCache;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Conserve les différentes valeurs relatives au formulatire du journal.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.14 $, $Date: 2002/04/10 15:53:12 $
 * @see com.lotoquebec.cardex.presentation.model.JournalHtmlForm
 */
public class JournalForm extends ValidatorForm implements JournalHtmlForm, EntiteCardexForm, Serializable {

	private String       entite = GlobalConstants.Entite.MAISON_JEUX;
	private String       categorie = "";
    private String       categorieDescription = "";
    private String       type = "";
    private String       typeDescription = "";
    private String       nature = "";
    private String       dateDebut = "";
    private String       dateFin = "";
    private String       dateCreation = "";
    private NumeroCardex numeroDossier = new NumeroCardex();
    private String       numeroEmploye = "";
    private String 		 reference2 = "";
    private String 		 reference3 = "";
    private String       numeroIncident = "";
    private String       referenceVideo = "";
    private String       intervenant = "";
    private String       endroit = "";
    private String       endroitDescription = "";
    private String       origine = "";
    private String       localisation = "";
    private String       descriptif = "";
    private String       description = "";
    private String       cle = "";
    private String       site = "";
    private String       cleNarration = "";
    private String       siteNarration = "";
    private String       duree = "";
    private boolean      modifiable = false;
    private boolean      nouveau = false;
	private EntiteCardexForm entiteCardexLiaison = null;

    /**
     * Constructeur de JournalForm par défaut.
     */
    public JournalForm() {}


    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void init() {
		this.categorie = "";
		this.type = "";
		this.dateDebut = "";
		this.dateFin = "";
		this.dateCreation = "";
		this.numeroEmploye = "";
		this.numeroIncident = "";
		this.referenceVideo = "";
		this.descriptif = "";
		this.localisation = "";
		this.numeroDossier = new NumeroCardex();
		this.origine = "";
		this.intervenant = "";
		this.endroit = "";
		this.description = "";
		this.cle = "";
		this.site = "";
		this.cleNarration = "";
		this.siteNarration = "";
		this.duree = "";
		this.modifiable = false;
		this.nouveau = false;
		this.reference2 = "";
		this.reference3 = "";
    	this.entiteCardexLiaison = null;
    	this.nature = "";
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du JournalForm.
     *
     * @return String Valeur de tout les attributs du JournalForm en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[JournalForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', dateDebut = '" + dateDebut);
        stringBuffer.append("', dateFin = '" + dateFin);
        stringBuffer.append("', type = '" + type);
        stringBuffer.append("', categorie = '" + categorie);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/**
	 * Returns the categorie.
	 * @return String
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * Returns the cle.
	 * @return String
	 */
	public String getCle() {
		return cle;
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
	 * Returns the endroit.
	 * @return String
	 */
	public String getEndroit() {
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
	public boolean isModifiable() {
		return modifiable;
	}

	/**
	 * Returns the isNew.
	 * @return boolean
	 */
	public boolean isNew() {
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
	 * @return String
	 */
	public String getSite() {
		return site;
	}

	/**
	 * Sets the categorie.
	 * @param categorie The categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * Sets the cle.
	 * @param cle The cle to set
	 */
	public void setCle(String cle) {
		this.cle = cle;
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
	 * Sets the endroit.
	 * @param endroit The endroit to set
	 */
	public void setEndroit(String endroit) {
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
	public void setModifiable(boolean modifiable) {
		this.modifiable = modifiable;
	}

	/**
	 * Sets the isNew.
	 * @param isNew The isNew to set
	 */
	public void setNew(boolean nouveau) {
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
	public void setSite(String site) {
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
	 * @return NumeroCardex
	 */
	public NumeroCardex getNumeroDossier() {
		return numeroDossier;
	}

	/**
	 * Sets the numeroDossier.
	 * @param numeroDossier The numeroDossier to set
	 */
	public void setNumeroDossier(NumeroCardex numeroDossier) {
		this.numeroDossier = numeroDossier;
	}

    /**
     * Affecte un numéro de cardex.
     *
     * @param stringNumeroCardex Valeur du numéro de cardex en caractère.
     */
    public void setNumeroDossier(String stringNumeroCardex) {
        numeroDossier.setNumeroCardex(stringNumeroCardex);
    }

	/**
	 * Returns the type.
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * @param type The type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Returns the cleNarration.
	 * @return String
	 */
	public String getCleNarration() {
		return cleNarration;
	}

	/**
	 * Returns the dateCreation.
	 * @return String
	 */
	public String getDateCreation() {
		return dateCreation;
	}

	/**
	 * Returns the siteNarration.
	 * @return String
	 */
	public String getSiteNarration() {
		return siteNarration;
	}

	/**
	 * Sets the cleNarration.
	 * @param cleNarration The cleNarration to set
	 */
	public void setCleNarration(String cleNarration) {
		this.cleNarration = cleNarration;
	}

	/**
	 * Sets the dateCreation.
	 * @param dateCreation The dateCreation to set
	 */
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getTypeDescription() {
		return typeDescription;
	}
	
	public String getCategorieDescription() {
		return categorieDescription;
	}
	
	public String getEndroitDescription() {
		return endroitDescription;
	}
	
	/**
	 * Sets the siteNarration.
	 * @param siteNarration The siteNarration to set
	 */
	public void setSiteNarration(String siteNarration) {
		this.siteNarration = siteNarration;
	}

	public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException {
    	ListeCache cache = ListeCache.getInstance();
    	
    	typeDescription = cache.obtenirLabel(subject, getType(), new TypeCleMultiListeCache(subject, getNature()));
    	categorieDescription = cache.obtenirLabel(subject, getCategorie(), new CategorieCleMultiListeCache(subject, getType()));
    	endroitDescription = cache.obtenirLabel(subject, getEndroit(), new EndroitCleListeCache(subject));
	}
	
	public EntiteCardexForm getEntiteCardexLiaison() {
		return entiteCardexLiaison;
	}
	public void setEntiteCardexLiaison(EntiteCardexForm entiteCardex) {
		this.entiteCardexLiaison = entiteCardex;
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

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getEntite() {
		return entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}

}