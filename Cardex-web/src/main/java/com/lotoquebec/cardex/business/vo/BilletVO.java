package com.lotoquebec.cardex.business.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.lotoquebec.cardex.business.Billet;

public class BilletVO implements Billet {

    private long cle = 0;
    private long site = 0;
    private long lien = 0;
    private long lienSite = 0;
    private String lienGenre = "";
    private String nom = "";
    private String numeroControl = "";
    private BigDecimal valeur;
    private Boolean extra = false;
    private Boolean participationTirage = false;
    private Boolean extraGagnant = false;
    private Boolean formuleGroupe = false;
    private long typeMise = 0;
    private BigDecimal montantLot;
    private BigDecimal montantExtra;
    private String numeroDetaillantProvenance = "";
    private String nomDetaillantProvenance = "";
    private long cleSocieteProvenance;
    private long siteSocieteProvenance;
    private Date dateAchat;
    private String numeroDetaillantValidation = "";
    private String nomDetaillantValidation = "";
    private long cleSocieteValidation;
    private long siteSocieteValidation;
    private Date dateValidation;
    private String createur = "";
    private Date dateCreation;
    private DossierVO dossierVO = null;
    private long typeLoterie = 0;
    private String numeroDetaillantVerification = "";
    private String nomDetaillantVerification = "";
    private long cleSocieteVerification;
    private long siteSocieteVerification;
    private Date dateVerification;
    private Date datePaiement = null;
    private String numeroDetaillantFautif = "";
    private String nomDetaillantFautif = "";
    private long cleSocieteFautif;
    private long siteSocieteFautif;
    
    public boolean isNouveau(){
    	return getCle() != 0;
    }
    
	public long getCle() {
		return cle;
	}
	
	public void setCle(long cle) {
		this.cle = cle;
	}
	
	public long getSite() {
		return site;
	}
	
	public void setSite(long site) {
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
	
	public BigDecimal getValeur() {
		return valeur;
	}
	
	public void setValeur(BigDecimal valeur) {
		this.valeur = valeur;
	}
	
	public Boolean isExtra() {
		return extra;
	}
	
	public Boolean getExtra() {
		return extra;
	}

	public void setExtra(Boolean extra) {
		this.extra = extra;
	}

	public Boolean isParticipationTirage() {
		return participationTirage;
	}	
	
	public Boolean getParticipationTirage() {
		return participationTirage;
	}

	public void setParticipationTirage(
			Boolean participationTirage) {
		this.participationTirage = participationTirage;
	}

	public Boolean isExtraGagnant() {
		return extraGagnant;
	}	
	
	public Boolean getExtraGagnant() {
		return extraGagnant;
	}

	public void setExtraGagnant(
			Boolean extraGagnant) {
		this.extraGagnant = extraGagnant;
	}

	public Boolean isFormuleGroupe() {
		return formuleGroupe;
	}
	
	public Boolean getFormuleGroupe() {
		return formuleGroupe;
	}

	public void setFormuleGroupe(Boolean formuleGroupe) {
		this.formuleGroupe = formuleGroupe;
	}

	public long getTypeMise() {
		return typeMise;
	}
	
	public void setTypeMise(long typeMise) {
		this.typeMise = typeMise;
	}
	
	public BigDecimal getMontantLot() {
		return montantLot;
	}
	
	public void setMontantLot(BigDecimal montantLot) {
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
	
	public Date getDateAchat() {
		return dateAchat;
	}
	
	public void setDateAchat(Date dateAchat) {
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
	
	public Date getDateValidation() {
		return dateValidation;
	}
	
	public void setDateValidation(Date dateValidation) {
		this.dateValidation = dateValidation;
	}
	
	public Date getDateCreation() {
		return dateCreation;
	}
	
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
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
	
	public Boolean isPossedeSocieteProvenance(){
		return cleSocieteProvenance != 0 && siteSocieteProvenance != 0;
	}

	public Boolean isPossedeSocieteValidation(){
		return cleSocieteValidation != 0 && siteSocieteValidation != 0;
	}

	public Boolean isPossedeSocieteVerification(){
		return cleSocieteVerification != 0 && siteSocieteVerification != 0;
	}

	public Boolean isPossedeSocieteFautif(){
		return cleSocieteFautif != 0 && siteSocieteFautif != 0;
	}

	public DossierVO getDossierVO() {
		return dossierVO;
	}

	public void setDossierVO(DossierVO dossierVO) {
		this.dossierVO = dossierVO;
	}

	/**
	 * @return typeLoterie
	 */
	public long getTypeLoterie() {
		return typeLoterie;
	}

	/**
	 * @param typeLoterie typeLoterie à définir
	 */
	public void setTypeLoterie(long typeLoterie) {
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
	public Date getDateVerification() {
		return dateVerification;
	}

	/**
	 * @param dateVerification dateVerification à définir
	 */
	public void setDateVerification(Date dateVerification) {
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

	public Date getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(Date datePaiement) {
		this.datePaiement = datePaiement;
	}

	public BigDecimal getMontantExtra() {
		return montantExtra;
	}

	public void setMontantExtra(BigDecimal montantExtra) {
		this.montantExtra = montantExtra;
	}
	
}