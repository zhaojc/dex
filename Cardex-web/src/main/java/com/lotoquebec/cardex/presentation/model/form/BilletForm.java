package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardexCommun.model.EntiteCardexForm;

public class BilletForm extends ValidatorForm implements EntiteCardexForm, Serializable {

    private String cle = "";
    private String site = "";
    private long lien = 0;
    private long lienSite = 0;
    private String lienGenre = "";
    private String nom = "";
    private String numeroControl = "";
    private String valeur = "";
    private boolean extra = false;
    private boolean participationTirage = false;
    private boolean extraGagnant = false;
    private boolean formuleGroupe = false;
    private String typeMise = "";
    private String montantLot = "";
    private String montantExtra = "";
    private String numeroDetaillantProvenance = "";
    private String nomDetaillantProvenance = "";
    private long cleSocieteProvenance;
    private long siteSocieteProvenance;
    private String dateAchat = "";
    private String numeroDetaillantValidation = "";
    private String nomDetaillantValidation = "";
    private long cleSocieteValidation;
    private long siteSocieteValidation;
    private String dateValidation = "";
    private String createur = "";
    private String dateCreation = "";    
    private DossierForm dossierForm = null;
    private String typeLoterie = "";
    private String numeroDetaillantVerification = "";
    private String nomDetaillantVerification = "";
    private long cleSocieteVerification;
    private long siteSocieteVerification;
    private String dateVerification = "";
    private String numeroDetaillantFautif = "";
    private String nomDetaillantFautif = "";
    private long cleSocieteFautif;
    private long siteSocieteFautif;
    private String datePaiement = "";

    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     */
    public void init() {
       this.cle = "";
       this.site = "";
       this.nom = "";
	   this.numeroControl = "";
	   this.valeur = "";
       this.typeMise = "";
       this.montantLot = "";
       this.montantExtra = "";
       this.numeroDetaillantProvenance = "";
       this.nomDetaillantProvenance = "";
       this.cleSocieteProvenance = 0;
       this.siteSocieteProvenance = 0;
       this.dateAchat = "";
       this.numeroDetaillantValidation = "";
       this.nomDetaillantValidation = "";
       this.cleSocieteValidation = 0;
       this.siteSocieteValidation = 0;
       this.dateValidation = "";
       this.datePaiement = "";
       this.createur = "";
       this.dateCreation = "";     
       this.dossierForm = null;
       this.cleSocieteVerification = 0;
       this.siteSocieteVerification = 0;
       this.dateVerification = "";
       this.typeLoterie = "";
       this.cleSocieteFautif = 0;
       this.siteSocieteFautif = 0;
       this.nomDetaillantVerification = "";
       this.numeroDetaillantVerification = "";
       this.nomDetaillantFautif = "";
       this.numeroDetaillantFautif = "";
       this.extra = false;
       this.participationTirage= false;
       this.extraGagnant= false;
       this.formuleGroupe = false;
    }    
    
    
    public String getCle() {
		return cle;
	}

	public void setCle(String cle) {
		this.cle = cle;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNumeroControl() {
		return numeroControl;
	}

	public void setNumeroControl(String numeroControl) {
		this.numeroControl = numeroControl;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public boolean isExtra() {
		return extra;
	}

	public void setExtra(boolean extra) {
		this.extra = extra;
	}

	public boolean isParticipationTirage() {
		return participationTirage;
	}

	public boolean isExtraGagnant() {
		return extraGagnant;
	}

	public void setParticipationTirage(
			boolean participationTirage) {
		this.participationTirage = participationTirage;
	}

	public void setExtraGagnant(
			boolean extraGagnant) {
		this.extraGagnant = extraGagnant;
	}

	public boolean isFormuleGroupe() {
		return formuleGroupe;
	}

	public void setFormuleGroupe(boolean formuleGroupe) {
		this.formuleGroupe = formuleGroupe;
	}

	public String getTypeMise() {
		return typeMise;
	}

	public void setTypeMise(String typeMise) {
		this.typeMise = typeMise;
	}

	public String getMontantLot() {
		return montantLot;
	}

	public void setMontantLot(String montantLot) {
		this.montantLot = montantLot;
	}

	public String getNumeroDetaillantProvenance() {
		return numeroDetaillantProvenance;
	}

	public void setNumeroDetaillantProvenance(String numeroDetaillantProvenance) {
		this.numeroDetaillantProvenance = numeroDetaillantProvenance;
	}

	public long getCleSocieteProvenance() {
		return cleSocieteProvenance;
	}

	public void setCleSocieteProvenance(long cleSocieteProvenance) {
		this.cleSocieteProvenance = cleSocieteProvenance;
	}

	public long getSiteSocieteProvenance() {
		return siteSocieteProvenance;
	}

	public void setSiteSocieteProvenance(long siteSocieteProvenance) {
		this.siteSocieteProvenance = siteSocieteProvenance;
	}

	public String getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(String dateAchat) {
		this.dateAchat = dateAchat;
	}

	public String getNumeroDetaillantValidation() {
		return numeroDetaillantValidation;
	}

	public void setNumeroDetaillantValidation(String numeroDetaillantValidation) {
		this.numeroDetaillantValidation = numeroDetaillantValidation;
	}

	public long getCleSocieteValidation() {
		return cleSocieteValidation;
	}

	public void setCleSocieteValidation(long cleSocieteValidation) {
		this.cleSocieteValidation = cleSocieteValidation;
	}

	public long getSiteSocieteValidation() {
		return siteSocieteValidation;
	}

	public void setSiteSocieteValidation(long siteSocieteValidation) {
		this.siteSocieteValidation = siteSocieteValidation;
	}

	public String getDateValidation() {
		return dateValidation;
	}

	public void setDateValidation(String dateValidation) {
		this.dateValidation = dateValidation;
	}

	public String getNomDetaillantProvenance() {
		return nomDetaillantProvenance;
	}

	public void setNomDetaillantProvenance(String nomDetaillantProvenance) {
		this.nomDetaillantProvenance = nomDetaillantProvenance;
	}

	public String getNomDetaillantValidation() {
		return nomDetaillantValidation;
	}

	public void setNomDetaillantValidation(String nomDetaillantValidation) {
		this.nomDetaillantValidation = nomDetaillantValidation;
	}

	public String getProvenanceBillet(){
		return numeroDetaillantProvenance+" "+nomDetaillantProvenance;
	}

	public String getValidationBillet(){
		return numeroDetaillantValidation+" "+nomDetaillantValidation;
	}
	
	public String getVerificationBillet(){
		return numeroDetaillantVerification+" "+nomDetaillantVerification;
	}

	public String getDetaillantFautif(){
		return numeroDetaillantFautif+" "+nomDetaillantFautif;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
    	extra = false;
    	participationTirage = false;
    	extraGagnant = false;
    	formuleGroupe = false;
    }

	public long getLien() {
		return lien;
	}

	public void setLien(long lien) {
		this.lien = lien;
	}

	public long getLienSite() {
		return lienSite;
	}

	public void setLienSite(long lienSite) {
		this.lienSite = lienSite;
	}

	public String getLienGenre() {
		return lienGenre;
	}

	public void setLienGenre(String lienGenre) {
		this.lienGenre = lienGenre;
	}

	public String getCreateur() {
		return createur;
	}

	public void setCreateur(String createur) {
		this.createur = createur;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public boolean equals(Object o){
		
		if (o instanceof BilletForm == false)
			return false;
		
		BilletForm billetForm = (BilletForm) o;
		
		return billetForm.getCle().equals(getCle())
		&& billetForm.getSite().equals(getSite());
	}
	
	public int hashCode() {
		return 1;
	}

	public DossierForm getDossierForm() {
		return dossierForm;
	}

	public void setDossierForm(DossierForm dossierForm) {
		this.dossierForm = dossierForm;
	}

	/**
	 * @return typeLoterie
	 */
	public String getTypeLoterie() {
		return typeLoterie;
	}

	/**
	 * @param typeLoterie typeLoterie à définir
	 */
	public void setTypeLoterie(String typeLoterie) {
		this.typeLoterie = typeLoterie;
	}

	/**
	 * @return numeroDetaillantVerification
	 */
	public String getNumeroDetaillantVerification() {
		return numeroDetaillantVerification;
	}

	/**
	 * @param numeroDetaillantVerification numeroDetaillantVerification à définir
	 */
	public void setNumeroDetaillantVerification(String numeroDetaillantVerification) {
		this.numeroDetaillantVerification = numeroDetaillantVerification;
	}

	/**
	 * @return nomDetaillantVerification
	 */
	public String getNomDetaillantVerification() {
		return nomDetaillantVerification;
	}

	/**
	 * @param nomDetaillantVerification nomDetaillantVerification à définir
	 */
	public void setNomDetaillantVerification(String nomDetaillantVerification) {
		this.nomDetaillantVerification = nomDetaillantVerification;
	}

	/**
	 * @return cleSocieteVerification
	 */
	public long getCleSocieteVerification() {
		return cleSocieteVerification;
	}

	/**
	 * @param cleSocieteVerification cleSocieteVerification à définir
	 */
	public void setCleSocieteVerification(long cleSocieteVerification) {
		this.cleSocieteVerification = cleSocieteVerification;
	}

	/**
	 * @return siteSocieteVerification
	 */
	public long getSiteSocieteVerification() {
		return siteSocieteVerification;
	}

	/**
	 * @param siteSocieteVerification siteSocieteVerification à définir
	 */
	public void setSiteSocieteVerification(long siteSocieteVerification) {
		this.siteSocieteVerification = siteSocieteVerification;
	}

	/**
	 * @return dateVerification
	 */
	public String getDateVerification() {
		return dateVerification;
	}

	/**
	 * @param dateVerification dateVerification à définir
	 */
	public void setDateVerification(String dateVerification) {
		this.dateVerification = dateVerification;
	}

	/**
	 * @return numeroDetaillantFautif
	 */
	public String getNumeroDetaillantFautif() {
		return numeroDetaillantFautif;
	}

	/**
	 * @param numeroDetaillantFautif numeroDetaillantFautif à définir
	 */
	public void setNumeroDetaillantFautif(String numeroDetaillantFautif) {
		this.numeroDetaillantFautif = numeroDetaillantFautif;
	}

	/**
	 * @return nomDetaillantFautif
	 */
	public String getNomDetaillantFautif() {
		return nomDetaillantFautif;
	}

	/**
	 * @param nomDetaillantFautif nomDetaillantFautif à définir
	 */
	public void setNomDetaillantFautif(String nomDetaillantFautif) {
		this.nomDetaillantFautif = nomDetaillantFautif;
	}

	/**
	 * @return cleSocieteFautif
	 */
	public long getCleSocieteFautif() {
		return cleSocieteFautif;
	}

	/**
	 * @param cleSocieteFautif cleSocieteFautif à définir
	 */
	public void setCleSocieteFautif(long cleSocieteFautif) {
		this.cleSocieteFautif = cleSocieteFautif;
	}

	/**
	 * @return siteSocieteFautif
	 */
	public long getSiteSocieteFautif() {
		return siteSocieteFautif;
	}

	/**
	 * @param siteSocieteFautif siteSocieteFautif à définir
	 */
	public void setSiteSocieteFautif(long siteSocieteFautif) {
		this.siteSocieteFautif = siteSocieteFautif;
	}


	public String getDatePaiement() {
		return datePaiement;
	}


	public void setDatePaiement(String datePaiement) {
		this.datePaiement = datePaiement;
	}


	public String getMontantExtra() {
		return montantExtra;
	}


	public void setMontantExtra(String montantExtra) {
		this.montantExtra = montantExtra;
	}
	
}