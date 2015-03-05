package com.lotoquebec.cardex.presentation.model.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * Permet de transiter les informations relatives à la consultation d'une
 * adresse de la couche présentation à la couche d'affaire.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.8 $, $Date: 2002/02/28 17:28:14 $
 * @see com.lotoquebec.cardex.business.Adresse
 */
public class IntervenantForm extends ValidatorForm {

    private String cle = "";
    private String site = "";
    private String code = "";
    private String codeParent = "";
    private String commentaire = "";
    private String confidentialite = "";
    private String hierarchie = "";
    private String courriel = "";
    private String DN = "";
    private String statut = "";
    private String createur = "";
    private String modificateur = "";
    private String motPasse = "";
    private String dateCreation = null;
    private String dateModification = null;
    private String langue = "";
    private String nom = "";
    private String prenom = "";
    private String numero = "";
    private String entite = "";
    private String groupe = "";
    private String secteur = "";
    private String secteurDescription = "";
    private String sousSecteurDescription = "";
    private String statutDescription = "";
    private String autoriteDescription = "";
    private String confidentialiteDescription = "";
    private String siteDescription = "";
    
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
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCommentaire() {
		return commentaire;
	}
	
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	public String getConfidentialite() {
		return confidentialite;
	}
	
	public void setConfidentialite(String confidentialite) {
		this.confidentialite = confidentialite;
	}
	
	public String getHierarchie() {
		return hierarchie;
	}
	
	public void setHierarchie(String hierarchie) {
		this.hierarchie = hierarchie;
	}
	
	public String getCourriel() {
		return courriel;
	}
	
	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}
	
	public String getDN() {
		return DN;
	}
	
	public void setDN(String dn) {
		DN = dn;
	}
	
	public String getStatut() {
		return statut;
	}
	
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	public String getCreateur() {
		return createur;
	}
	
	public void setCreateur(String createur) {
		this.createur = createur;
	}
	
	public String getModificateur() {
		return modificateur;
	}
	
	public void setModificateur(String modificateur) {
		this.modificateur = modificateur;
	}
	
	public String getMotPasse() {
		return motPasse;
	}
	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}
	
	public String getDateCreation() {
		return dateCreation;
	}
	
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	public String getDateModification() {
		return dateModification;
	}
	
	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}
	
	public String getLangue() {
		return langue;
	}
	
	public void setLangue(String langue) {
		this.langue = langue;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getEntite() {
		return entite;
	}
	
	public void setEntite(String entite) {
		this.entite = entite;
	}
	
	public String getGroupe() {
		return groupe;
	}
	
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	/**
	 * @return codeParent
	 */
	public String getCodeParent() {
		return codeParent;
	}


	/**
	 * @param codeParent codeParent à définir
	 */
	public void setCodeParent(String codeParent) {
		this.codeParent = codeParent;
	}


	/**
	 * @return secteur
	 */
	public String getSecteur() {
		return secteur;
	}


	/**
	 * @param secteur secteur à définir
	 */
	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	/**
	 * @return secteurDescription
	 */
	public String getSecteurDescription() {
		return secteurDescription;
	}

	/**
	 * @param secteurDescription secteurDescription à définir
	 */
	public void setSecteurDescription(String secteurDescription) {
		this.secteurDescription = secteurDescription;
	}

	/**
	 * @return statutDescription
	 */
	public String getStatutDescription() {
		return statutDescription;
	}

	/**
	 * @param statutDescription statutDescription à définir
	 */
	public void setStatutDescription(String statutDescription) {
		this.statutDescription = statutDescription;
	}

	/**
	 * @return autoriteDescription
	 */
	public String getAutoriteDescription() {
		return autoriteDescription;
	}

	/**
	 * @param autoriteDescription autoriteDescription à définir
	 */
	public void setAutoriteDescription(String autoriteDescription) {
		this.autoriteDescription = autoriteDescription;
	}

	/**
	 * @return confidentialiteDescription
	 */
	public String getConfidentialiteDescription() {
		return confidentialiteDescription;
	}

	/**
	 * @param confidentialiteDescription confidentialiteDescription à définir
	 */
	public void setConfidentialiteDescription(String confidentialiteDescription) {
		this.confidentialiteDescription = confidentialiteDescription;
	}

	/**
	 * @return siteDescription
	 */
	public String getSiteDescription() {
		return siteDescription;
	}

	/**
	 * @param siteDescription siteDescription à définir
	 */
	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}

	/**
	 * @return sousSecteurDescription
	 */
	public String getSousSecteurDescription() {
		return sousSecteurDescription;
	}

	/**
	 * @param sousSecteurDescription sousSecteurDescription à définir
	 */
	public void setSousSecteurDescription(String sousSecteurDescription) {
		this.sousSecteurDescription = sousSecteurDescription;
	}
	
}