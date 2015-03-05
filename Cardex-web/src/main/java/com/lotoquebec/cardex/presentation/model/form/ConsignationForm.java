package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.ConsignationHtmlForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.DenominationCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.DeviseCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeConsignationCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Permet de transiter les informations relatives à un suivi de la couche
 * présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.SuiviHtmlForm
 */
public class ConsignationForm extends ValidatorForm  implements ConsignationHtmlForm, EntiteCardexForm, Serializable {

    private String cle = "";
    private String site = "";
    private boolean approuve = false;
    private String quantite = "";
    private String devise = "";
    private String deviseDescription = "";
	private String denomination = "";
	private String denominationDescription = "";
    private String prix = "";
    private String poids = "";
    private String dimension = "";
    private String montant = "";
    private String numeroSerie = "";
    private String marque = "";
    private String modele = "";
    private String fournisseur = "";
    private String description = "";
    private String createur = "";
    private String createurDescription = "";
    private String commentaire = "";
    private String typeConsignation = "";
    private String typeConsignationDescription = "";
    private String dateCreation = "";
    private String modificateur = "";
    private String dateModification = "";
    private String approbateur = "";
    private String dateApprobation = "";
    private String reference1 = "";
    private String reference2 = "";
    private String lien = "";
    private String lienSite = "";
    private String lienGenre = "";
    private boolean approuvable = false;
    private boolean modifiable = false;

	private DossierForm dossier = null;


    /**
     * Constructeur de SuiviForm par défaut.
     */
    public ConsignationForm() {}


    // Getters

    /**
     * Test si une consignation peut être approuvé.
     *
     * @return boolean True si une consignation est approuvable.
     */
    public boolean getApprouvable() {
        return this.approuvable;
    }

	/**
	 * Retourne le dossier asssocie à la consignation .
	 *
	 * @return DossierForm Valeur de la cle en caractère.
	 */
	public DossierForm getDossier() {
		return this.dossier;
	}

    /**
     * Determine si une consignation est approuvanle
     *
     * @param approuvable Est-ce que la consignation est approuvable
     * caractère.
     */
    public void setApprouvable(boolean approuvable) {
      this.approuvable = approuvable;
    }

    
    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
       this.cle = "";
       this.site = "";
       this.commentaire = "";
       this.createur = "";
	   this.devise = "";
	   this.dimension = "";
       this.dateCreation = "";
       this.dateModification = "";
       this.description = "";
       this.fournisseur = "";
       this.marque = "";
       this.modele = "";
       this.montant = "";
       this.quantite = "";
       this.numeroSerie = "";
       this.prix = "";
       this.modificateur = "";
       this.approbateur = "";
       this.dateApprobation = "";
       this.reference1 = "";
       this.reference2 = "";
       this.lien = "";
       this.lienSite = "";
       this.lienGenre = "";
       this.approuvable = false;
       this.approuve = false;
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du SuiviForm.
     *
     * @return String Valeur de tout les attributs du ConsignationForm en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[ConsignationForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', description = '" + description);
        stringBuffer.append("', quantite = '" + quantite);
        stringBuffer.append("', numeroSerie = '" + numeroSerie);
        stringBuffer.append("', createur = '" + createur);
        stringBuffer.append("', dateCreation = '"
                + dateCreation);
        stringBuffer.append("', modificateur = '" + modificateur);
        stringBuffer.append("', dateModification = '"
                + dateModification);
        stringBuffer.append("', approbateur = '" + approbateur);
        stringBuffer.append("', dateApprobation = '"
                + dateApprobation);
        stringBuffer.append("', reference1 = '" + reference1);
        stringBuffer.append("', reference2 = '" + reference2);
        stringBuffer.append("', lien = '" + lienSite );
        stringBuffer.append("', lienSite = '" + lienSite );
        stringBuffer.append("', lienGenre = '" + lienGenre );
        stringBuffer.append("']");
        return stringBuffer.toString();
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
	public boolean getApprouve() {
		return approuve;
	}

	/**
	 * Returns the cle.
	 * @return String
	 */
	public String getCle() {
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
	 * @return String
	 */
	public String getDateApprobation() {
		return dateApprobation;
	}

	/**
	 * Returns the dateCreation.
	 * @return String
	 */
	public String getDateCreation() {
		return dateCreation;
	}

	/**
	 * Returns the dateModification.
	 * @return String
	 */
	public String getDateModification() {
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
	 * Returns the fournisseur.
	 * @return String
	 */
	public String getFournisseur() {
		return fournisseur;
	}

	/**
	 * Returns the lien.
	 * @return String
	 */
	public String getLien() {
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
	 * @return String
	 */
	public String getLienSite() {
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
	 * @return String
	 */
	public String getMontant() {
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
	 * Returns the prix.
	 * @return String
	 */
	public String getPrix() {
		return prix;
	}

	/**
	 * Returns the quantite.
	 * @return String
	 */
	public String getQuantite() {
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
	 * @return String
	 */
	public String getSite() {
		return site;
	}

	/**
	 * Returns the typeConsignation.
	 * @return String
	 */
	public String getTypeConsignation() {
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
	 * Sets the approuve.
	 * @param approuve The approuve to set
	 */
	public void setApprouve(boolean approuve) {
		this.approuve = approuve;
	}

	/**
	 * Sets the cle.
	 * @param cle The cle to set
	 */
	public void setCle(String cle) {
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
	public void setDateApprobation(String dateApprobation) {
		this.dateApprobation = dateApprobation;
	}

	/**
	 * Sets the dateCreation.
	 * @param dateCreation The dateCreation to set
	 */
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Sets the dateModification.
	 * @param dateModification The dateModification to set
	 */
	public void setDateModification(String dateModification) {
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
	public void setLien(String lien) {
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
	public void setLienSite(String lienSite) {
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
	public void setMontant(String montant) {
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
	 * Sets the prix.
	 * @param prix The prix to set
	 */
	public void setPrix(String prix) {
		this.prix = prix;
	}

	/**
	 * Sets the quantite.
	 * @param quantite The quantite to set
	 */
	public void setQuantite(String quantite) {
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
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * Sets the typeConsignation.
	 * @param typeConsignation The typeConsignation to set
	 */
	public void setTypeConsignation(String typeConsignation) {
		this.typeConsignation = typeConsignation;
	}

	/**
	 * Returns the isModifiable.
	 * @return boolean
	 */
	public boolean isModifiable() {
		return modifiable;
	}

	/**
	 * Sets the isModifiable.
	 * @param isModifiable The isModifiable to set
	 */
	public void setModifiable(boolean modifiable) {
		this.modifiable = modifiable;
	}

	/**
	 * Returns the dimension.
	 * @return String
	 */
	public String getDimension() {
		return dimension;
	}

	/**
	 * Sets the dimension.
	 * @param dimension The dimension to set
	 */
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	/**
	 * Returns the poids.
	 * @return String
	 */
	public String getPoids() {
		return poids;
	}

	/**
	 * Sets the poids.
	 * @param poids The poids to set
	 */
	public void setPoids(String poids) {
		this.poids = poids;
	}

	/**
	 * Returns the devise.
	 * @return String
	 */
	public String getDevise() {
		return devise;
	}

	/**
	 * Sets the devise.
	 * @param devise The devise to set
	 */
	public void setDevise(String devise) {
		this.devise = devise;
	}

	/**
	 * Affecte un un dossier associe a la consignation.
	 *
	 * @param dossier Le dossier associe.
	 */
	public void setDossier(DossierForm dossier) {
		this.dossier = dossier;
	}

	/**
	 * @return
	 */
	public String getDenomination() {
		return denomination;
	}

	/**
	 * @param string
	 */
	public void setDenomination(String string) {
		denomination = string;
	}
	
	public String getDenominationDescription() {
		return denominationDescription;
	}
	
	public String getTypeConsignationDescription() {
		return typeConsignationDescription;
	}
	
	public String getDateCreation10(){
    	if (StringUtils.isNotEmpty(this.dateCreation))
    		return this.dateCreation.substring(0, 10);
    	return "";
	}
	
	public String getCreateurDescription() {
		return createurDescription;
	}
	
	public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException {
    	ListeCache cache = ListeCache.getInstance();
    	
    	denominationDescription = cache.obtenirLabel(subject, getDenomination(), new DenominationCleListeCache(subject));
    	typeConsignationDescription = cache.obtenirLabel(subject, getTypeConsignation(), new TypeConsignationCleListeCache(subject));
    	createurDescription = cache.obtenirLabel(subject, getCreateur(), new IntervenantCle(subject));
    	deviseDescription = cache.obtenirLabel(subject, getDevise(), new DeviseCleListeCache(subject));
	}
	
	public String getDeviseDescription() {
		return deviseDescription;
	}
	
}