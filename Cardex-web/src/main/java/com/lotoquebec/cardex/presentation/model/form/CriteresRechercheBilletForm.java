package com.lotoquebec.cardex.presentation.model.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardexCommun.business.CriteresRecherche;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.model.RechercheListeResultat;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;


public class CriteresRechercheBilletForm extends ValidatorForm implements RechercheListeResultat, CriteresRecherche {

    private String nom = "";
    private String numeroControl = "";
    private String valeur = "";
    private boolean extra = false;
    private String typeMise = "";
    private String montantLot = "";
    private String numeroDetaillantProvenance = "";
    private String numeroDetaillantValidation = "";
    private String numeroDetaillantVerification = "";
    private String numeroDetaillantFautif = "";
    private String dateDebutCreation = "";
    private String dateFinCreation = "";
    private String typeLoterie = "";
    private String datePaiement = "";
	private HierarchieEGNTC cascadeEGNTC = new HierarchieEGNTC();
    private ListeResultat listeResultat = new ListeResultat();
    private int sequence = 0;
    
    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     */
    public void init() {
       this.nom = "";
	   this.numeroControl = "";
	   this.valeur = "";
	   this.extra = false;
       this.typeMise = "";
       this.montantLot = "";
       this.numeroDetaillantProvenance = "";
       this.numeroDetaillantValidation = "";
       this.numeroDetaillantVerification = "";
       this.numeroDetaillantFautif = "";
       this.dateDebutCreation = "";
       this.dateFinCreation = "";
       this.typeLoterie = "";
       this.datePaiement = "";
       listeResultat.init();
       genererNumeroSequence();
    }

    // Après une requête il faut générer un nouveau numéro de séquence.
    public void genererNumeroSequence(){
    	sequence = GererTacheUtilisateur.getInstanceOf().obtenirNumero();
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

	public String getNumeroDetaillantValidation() {
		return numeroDetaillantValidation;
	}

	public void setNumeroDetaillantValidation(String numeroDetaillantValidation) {
		this.numeroDetaillantValidation = numeroDetaillantValidation;
	}

	public String getDateDebutCreation() {
		return dateDebutCreation;
	}

	public void setDateDebutCreation(String dateDebutCreation) {
		this.dateDebutCreation = dateDebutCreation;
	}

	public String getDateFinCreation() {
		return dateFinCreation;
	}

	public void setDateFinCreation(String dateFinCreation) {
		this.dateFinCreation = dateFinCreation;
	}

	public void setListeResultat(ListeResultat listeResultat) {
		this.listeResultat = listeResultat;
	}

	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.model.form.RechercheListeResultat#getListeResultat()
	 */
	public ListeResultat getListeResultat() {
		return listeResultat;
	}
	
	public void setListeResultat(List list) {
		this.listeResultat.setResultatComplet( list );
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
    	extra = false;
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
     * Retourne l'entité.
     *
     * @return String Valeur de l'entité en caractère.
     */
    public String getEntite() {
    	return cascadeEGNTC.get(HierarchieEGNTC.ENTITE);
    }

    /**
     * Affecte une entité.
     *
     * @param entite Valeur de l'entité en caractère.
     */
    public void setEntite(String entite) {
        cascadeEGNTC.set(HierarchieEGNTC.ENTITE, entite);
    }

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(String datePaiement) {
		this.datePaiement = datePaiement;
	}
   
}