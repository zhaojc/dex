package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Evaluation;

public class EvaluationVO implements Evaluation {

    private long cle = 0;
    private long site = 0;
    private long lien = 0;
    private long lienSite = 0;
    private Timestamp dateDebutEval = null;
    private Timestamp dateFinEval = null;
    private long numeroClientBijou = 0;
    private String faitsConnus = "";
    private String createur = "";
    private Timestamp dateCreation = null;    
    private String proximite = "";
    private String gradation = "";
    private String transaction = "";
    private String commentairePropos = "";
    private String commentaireEtat = "";
    private String commentaireAutre = "";
    private String commentaireSigne = "";
    private String signataire1 = "";
    private String signataire2 = "";
    private String signataire3 = "";
    private String signataire4 = "";
    private String signataire5 = "";
    private Timestamp dateEvaluation = null;
    private String modificateur = "";
    private Timestamp dateModification = null;
    private Boolean modifiable = false;
	private Dossier dossier = new DossierVO();
    private Collection jeux = new ArrayList();
    private Collection etats = new ArrayList();
    private Collection propos = new ArrayList();
    private List<MiseEvaluationVO> misesEvaluation = new ArrayList<MiseEvaluationVO>();

	/**
	 * @return cle
	 */
	public long getCle() {
		return cle;
	}


	/**
	 * @param cle cle � d�finir
	 */
	public void setCle(long cle) {
		this.cle = cle;
	}


	/**
	 * @return site
	 */
	public long getSite() {
		return site;
	}


	/**
	 * @param site site � d�finir
	 */
	public void setSite(long site) {
		this.site = site;
	}


	/**
	 * @return lien
	 */
	public long getLien() {
		return lien;
	}


	/**
	 * @param lien lien � d�finir
	 */
	public void setLien(long lien) {
		this.lien = lien;
	}


	/**
	 * @return lienSite
	 */
	public long getLienSite() {
		return lienSite;
	}


	/**
	 * @param lienSite lienSite � d�finir
	 */
	public void setLienSite(long lienSite) {
		this.lienSite = lienSite;
	}


	/**
	 * @return dateDebutEval
	 */
	public Timestamp getDateDebutEval() {
		return dateDebutEval;
	}


	/**
	 * @param dateDebutEval dateDebutEval � d�finir
	 */
	public void setDateDebutEval(Timestamp dateDebutEval) {
		this.dateDebutEval = dateDebutEval;
	}


	/**
	 * @return dateFinEval
	 */
	public Timestamp getDateFinEval() {
		return dateFinEval;
	}


	/**
	 * @param dateFinEval dateFinEval � d�finir
	 */
	public void setDateFinEval(Timestamp dateFinEval) {
		this.dateFinEval = dateFinEval;
	}


	/**
	 * @return numeroClientBijou
	 */
	public long getNumeroClientBijou() {
		return numeroClientBijou;
	}


	/**
	 * @param numeroClientBijou numeroClientBijou � d�finir
	 */
	public void setNumeroClientBijou(long numeroClientBijou) {
		this.numeroClientBijou = numeroClientBijou;
	}

	/**
	 * @return faitsConnus
	 */
	public String getFaitsConnus() {
		return faitsConnus;
	}


	/**
	 * @param faitsConnus faitsConnus � d�finir
	 */
	public void setFaitsConnus(String faitsConnus) {
		this.faitsConnus = faitsConnus;
	}


	/**
	 * @return createur
	 */
	public String getCreateur() {
		return createur;
	}


	/**
	 * @param createur createur � d�finir
	 */
	public void setCreateur(String createur) {
		this.createur = createur;
	}


	/**
	 * @return dateCreation
	 */
	public Timestamp getDateCreation() {
		return dateCreation;
	}


	/**
	 * @param dateCreation dateCreation � d�finir
	 */
	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}


	/**
	 * @return proximite
	 */
	public String getProximite() {
		return proximite;
	}


	/**
	 * @param proximite proximite � d�finir
	 */
	public void setProximite(String proximite) {
		this.proximite = proximite;
	}


	/**
	 * @return gradation
	 */
	public String getGradation() {
		return gradation;
	}


	/**
	 * @param gradation gradation � d�finir
	 */
	public void setGradation(String gradation) {
		this.gradation = gradation;
	}


	/**
	 * @return transaction
	 */
	public String getTransaction() {
		return transaction;
	}


	/**
	 * @param transaction transaction � d�finir
	 */
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}


	/**
	 * @return commentairePropos
	 */
	public String getCommentairePropos() {
		return commentairePropos;
	}


	/**
	 * @param commentairePropos commentairePropos � d�finir
	 */
	public void setCommentairePropos(String commentairePropos) {
		this.commentairePropos = commentairePropos;
	}


	/**
	 * @return commentaireEtat
	 */
	public String getCommentaireEtat() {
		return commentaireEtat;
	}


	/**
	 * @param commentaireEtat commentaireEtat � d�finir
	 */
	public void setCommentaireEtat(String commentaireEtat) {
		this.commentaireEtat = commentaireEtat;
	}


	/**
	 * @return commentaireAutre
	 */
	public String getCommentaireAutre() {
		return commentaireAutre;
	}


	/**
	 * @param commentaireAutre commentaireAutre � d�finir
	 */
	public void setCommentaireAutre(String commentaireAutre) {
		this.commentaireAutre = commentaireAutre;
	}


	/**
	 * @return commentaireSigne
	 */
	public String getCommentaireSigne() {
		return commentaireSigne;
	}


	/**
	 * @param commentaireSigne commentaireSigne � d�finir
	 */
	public void setCommentaireSigne(String commentaireSigne) {
		this.commentaireSigne = commentaireSigne;
	}


	/**
	 * @return signataire1
	 */
	public String getSignataire1() {
		return signataire1;
	}


	/**
	 * @param signataire1 signataire1 � d�finir
	 */
	public void setSignataire1(String signataire1) {
		this.signataire1 = signataire1;
	}


	/**
	 * @return signataire2
	 */
	public String getSignataire2() {
		return signataire2;
	}


	/**
	 * @param signataire2 signataire2 � d�finir
	 */
	public void setSignataire2(String signataire2) {
		this.signataire2 = signataire2;
	}


	/**
	 * @return signataire3
	 */
	public String getSignataire3() {
		return signataire3;
	}


	/**
	 * @param signataire3 signataire3 � d�finir
	 */
	public void setSignataire3(String signataire3) {
		this.signataire3 = signataire3;
	}


	/**
	 * @return signataire4
	 */
	public String getSignataire4() {
		return signataire4;
	}


	/**
	 * @param signataire4 signataire4 � d�finir
	 */
	public void setSignataire4(String signataire4) {
		this.signataire4 = signataire4;
	}


	/**
	 * @return signataire5
	 */
	public String getSignataire5() {
		return signataire5;
	}


	/**
	 * @param signataire5 signataire5 � d�finir
	 */
	public void setSignataire5(String signataire5) {
		this.signataire5 = signataire5;
	}


	/**
	 * @return dateEvaluation
	 */
	public Timestamp getDateEvaluation() {
		return dateEvaluation;
	}


	/**
	 * @param dateEvaluation dateEvaluation � d�finir
	 */
	public void setDateEvaluation(Timestamp dateEvaluation) {
		this.dateEvaluation = dateEvaluation;
	}


	/**
	 * @return modificateur
	 */
	public String getModificateur() {
		return modificateur;
	}


	/**
	 * @param modificateur modificateur � d�finir
	 */
	public void setModificateur(String modificateur) {
		this.modificateur = modificateur;
	}


	/**
	 * @return dateModification
	 */
	public Timestamp getDateModification() {
		return dateModification;
	}


	/**
	 * @param dateModification dateModification � d�finir
	 */
	public void setDateModification(Timestamp dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * Returns the isModifiable.
	 * @return boolean
	 */
	public Boolean isModifiable() {
		return modifiable;
	}

	public Boolean getModifiable() {
		return modifiable;
	}

	public void setModifiable(Boolean modifiable) {
		this.modifiable = modifiable;
	}

	 public Dossier getDossier() {
		 return this.dossier;
	 }

	/**
	 * Conserve certaines valeurs de dossier.
	 *
	 * @param dossier Dossier retourn� par une recherche de consignations.
	 */
	public void setDossier(Dossier dossier){
		this.dossier = dossier;
	}

    /**
     * Retourne les jeux.
     *
     * @return Collection Valeur des jeux.
     */
    public Collection getJeux() {
        return this.jeux;
    }

    /**
     * Ajoute un jeu.
     *
     * @param jeu Valeur du jeu.
     */
    public void addJeu(String jeu) {
      this.jeux.add(jeu);
    }
    
    /**
     * Retourne les �tats d'esprit.
     *
     * @return Collection Valeur des �tats.
     */
    public Collection getEtats() {
        return this.etats;
    }

    /**
     * Ajoute un �tat d'esprit.
     *
     * @param jeu Valeur de l'�tat.
     */
    public void addEtat(String etat) {
      this.etats.add(etat);
    }
    
    /**
     * Retourne les propos du client.
     *
     * @return Collection Valeur des propos.
     */
    public Collection getPropos() {
        return this.propos;
    }

    /**
     * Ajoute un propos du client.
     *
     * @param jeu Valeur du propos.
     */
    public void addPropos(String propos) {
      this.propos.add(propos);
    }

	public List<MiseEvaluationVO> getMisesEvaluation() {
		return misesEvaluation;
	}

	public void setMisesEvaluation(List<MiseEvaluationVO> misesEvaluation) {
		this.misesEvaluation = misesEvaluation;
	}

    
}