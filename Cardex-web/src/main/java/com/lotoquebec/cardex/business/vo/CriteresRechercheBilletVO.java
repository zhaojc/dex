package com.lotoquebec.cardex.business.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Permet de transiter les informations relatives à la recherche d'un billet de
 * la couche présentation à la couche d'affaire.
 */
public class CriteresRechercheBilletVO  implements CriteresRecherche{

    private String nom = "";
    private String numeroControl = "";
    private BigDecimal valeur;
    private Boolean extra = false;
    private long typeMise = 0;
    private BigDecimal montantLot;
    private String numeroDetaillantProvenance = "";
    private String numeroDetaillantValidation = "";
    private Date dateDebutCreation = null;
    private Date dateFinCreation = null;
    private Date datePaiement = null;
    private String numeroDetaillantVerification = "";
    private String numeroDetaillantFautif = "";
    private long typeLoterie = 0;
    private int sequence = 0;
    
    
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
	
	public String getNumeroDetaillantValidation() {
		return numeroDetaillantValidation;
	}
	
	public void setNumeroDetaillantValidation(String numeroDetaillantValidation) {
		this.numeroDetaillantValidation = numeroDetaillantValidation;
	}
	
	public Date getDateDebutCreation() {
		return dateDebutCreation;
	}
	
	public void setDateDebutCreation(Date dateDebutCreation) {
		this.dateDebutCreation = dateDebutCreation;
	}
	
	public Date getDateFinCreation() {
		return dateFinCreation;
	}
	
	public void setDateFinCreation(Date dateFinCreation) {
		this.dateFinCreation = dateFinCreation;
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

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Boolean getExtra() {
		return extra;
	}

	public void setExtra(Boolean extra) {
		this.extra = extra;
	}

	public Date getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(Date datePaiement) {
		this.datePaiement = datePaiement;
	}

}
