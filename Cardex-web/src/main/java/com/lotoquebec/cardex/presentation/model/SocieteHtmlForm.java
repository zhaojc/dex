package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

/**
 * Définit la signature des méthodes des différentes valeurs relatives au
 * formulatire de consultation d'une société.
 *
 * @author $Author: mdemers $
 * @version $Revision: 1.10 $, $Date: 2002/03/12 23:51:52 $
 */
public interface SocieteHtmlForm {


    // Getters


    /**
     * Retourne la cle.
     *
     * @return String Valeur de la cle en caractère.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caractère.
     */
    public String getSite();

    /**
     * Retourne l'entité.
     *
     * @return String Valeur entite en caractère.
     */
    public String getEntite();

    /**
     * Retourne le numéro de fiche.
     *
     * @return String Valeur du numéro de fiche en caractère.
     */
    public String getNumeroFiche();

    /**
     * Retourne le nom.
     *
     * @return String Valeur du nom en caractère.
     */
    public String getNom();

    /**
     * Retourne le nom de référence.
     *
     * @return String Valeur du nom de référence en caractère.
     */
    public String getReferenceNom();

    /**
     * Retourne le prénom de référence.
     *
     * @return String Valeur du prénom de référence en caractère.
     */
    public String getReferencePrenom();

    /**
     * Retourne la raison d'être.
     *
     * @return String Valeur de la raison d'être en caractère.
     */
    public String getRaisonEtre();

    /**
     * Retourne la classe.
     *
     * @return String Valeur de la classe.
     */
    public String getClasse();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur numérique de date.
     */
    public String getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur numérique de l'auteur.
     */
    public String getChangePar();

    /**
     * Retourne la date de fondation.
     *
     * @return String Valeur de la date de fondation en caractère.
     */
    public String getDateDeFondation();

    /**
     * Retourne la première référence.
     *
     * @return String Valeur de la première référence en caractère.
     */
    public String getReference1();

    /**
     * Retourne la deuxième référence.
     *
     * @return String Valeur de la deuxième référence en caractère.
     */
    public String getReference2();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caractère.
     */
    public String getStatut();

    /**
     * Retourne la langue.
     *
     * @return String Valeur de la langue en caractère.
     */
    public String getLangue();

    /**
     * Retourne la sévérité.
     *
     * @return String Valeur de la sévérité en caractère.
     */
    public String getSeverite();

    /**
     * Retourne la sévérité du casino.
     *
     * @return String Valeur de la sévérité en caractère.
     */
    public String getSeveriteCasino();

    /**
     * Retourne la confidentialité.
     *
     * @return String Valeur de la confidentialité en caractère.
     */
    public String getConfidentialite();

    /**
     * Retourne le mot de passe.
     *
     * @return String Valeur du mot de passe.
     */
    public String getMotPasse();

    /**
     * Retourne la confirmation du mot de passe.
     *
     * @return String Valeur de la confirmation du mot de passe en caractère.
     */
    public String getConfirmationMotPasse();

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caractère.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caractère.
     */
    public String getLienSite();

	/**
	 * Retourne la date de liaison.
	 *
	 * @return String date de liaison
	 */
	public String getLienDateCreation();

    /**
     * Retourne le rôle.
     *
     * @return String Valeur du rôle en caractère.
     */
    public String getRole();

    /**
     * Retourne le type de lien.
     *
     * @return String Valeur du type de lien en caractère.
     */
    public String getTypeLien();

    /**
     * Retourne le créateur du sujet.
     *
     * @return String Code du créateur.
     */
    public String getCreateur();

    /**
     * Retourne la date de création.
     *
     * @return String date de création
     */
    public String getDateCreation();

    /**
     * Retourne les narrations associées.
     *
     * @return Collection Valeur de la collection de narrations.
     */
    public Collection getNarrations();

    /**
     * Retourne les dossiers associés.
     *
     * @return Collection Valeur de la collection de dossiers.
     */
    public Collection getDossiers();

    /**
     * Retourne les sujets associés.
     *
     * @return Collection Valeur de la collection de sujets.
     */
    public Collection getSujets();

    /**
     * Retourne les societes associées.
     *
     * @return Collection Valeur de la collection de sociétés.
     */
    public Collection getSocietes();

    /**
     * Retourne les adresses associées.
     *
     * @return Collection Valeur de la collection d'adresse.
     */
    public Collection getAdresses();

    /**
     * Retourne les  photos associées.
     *
     * @return Collection Valeur de la collection de photos.
     */
    public Collection getPhotos();

    /**
     * Retourne les  véhicules associés.
     *
     * @return Collection Valeur de la collection de véhicules.
     */
    public Collection getVehicules();


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caractère.
     */
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(String site);

    /**
     * Affecte une entité.
     *
     * @param site Valeur entite en caractère.
     */
    public void setEntite(String entite);

    /**
     * Affecte un numéro de fiche.
     *
     * @param numeroFiche Valeur du numéro de fiche en caractère.
     */
    public void setNumeroFiche(String numeroFiche);

    /**
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caractère.
     */
    public void setNom(String nom);

    /**
     * Affecte un nom de référence.
     *
     * @param referenceNom Valeur du nom de référence en caractère.
     */
    public void setReferenceNom(String referenceNom);

    /**
     * Affecte un prénom de référence.
     *
     * @param referencePrenom Valeur du prénom de référence en caractère.
     */
    public void setReferencePrenom(String referencePrenom);

    /**
     * Affecte une raison d'être.
     *
     * @param raisonEtre Valeur de la raison d'être en caractère.
     */
    public void setRaisonEtre(String raisonEtre);

    /**
     * Affecte une classe.
     *
     * @param classe Valeur de la classe en caractère.
     */
    public void setClasse(String classe);

    /**
     * Affecte une date de fondation.
     *
     * @param dateDeFondation Valeur de la date de fondation en caractère.
     */
    public void setDateDeFondation(String dateDeFondation);

    /**
     * Affecte une première référence.
     *
     * @param reference1 Valeur de la première référence en caractère.
     */
    public void setReference1(String reference1);

    /**
     * Affecte une deuxième référence.
     *
     * @param reference2 Valeur de la deuxième référence en caractère.
     */
    public void setReference2(String reference2);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caractère.
     */
    public void setStatut(String statut);

    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue en caractère.
     */
    public void setLangue(String langue);

    /**
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité en caractère.
     */
    public void setSeverite(String severite);

    /**
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité en caractère.
     */
    public void setSeveriteCasino(String severiteCasino);

    /**
     * Affecte la confidentialité
     *
     * @param confidentialite Valeur de la confidentialité en caractères.
     */
    public void setConfidentialite(String confidentialite);

    /**
     * Affecte un mot de passe.
     *
     * @param motPasse Valeur du mot de passe.
     */
    public void setMotPasse(String motPasse);

    /**
     * Affecte une confirmation de mot de passe.
     *
     * @param confirmationMotPasse Valeur de la confirmation du mot de passe en caractère.
     */
    public void setConfirmationMotPasse(String confirmationMotPasse);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(String lienSite);

	/**
	 * Affecte une date de liaison.
	 *
	 * @param lienDateCreation Valeur de la date de liaison en caractère.
	 */
	public void setLienDateCreation(String lienDateCreation);

    /**
     * Affecte un rôle.
     *
     * @param role Valeur du rôle en caractère.
     */
    public void setRole(String role);

    /**
     * Affecte un type de lien.
     *
     * @param typeLien Valeur du type de lien en caractère.
     */
    public void setTypeLien(String typeLien);

    /**
     * Affecte le créateur du sujet.
     *
     * @param createur Code du créateur.
     */
    public void setCreateur(String createur);

    /**
     * Affecte la date de création.
     *
     * @param dateCreation Date de création.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte la date du changement (pour l'audit).
     *
     * @param dateChangement Valeur dateChangement.
     */
    public void setDateChangement(String dateChangement);

    /**
     * Affecte l'auteur du changement (pour l'audit).
     *
     * @param changePar Valeur changePar.
     */
    public void setChangePar(String changePar);

	/**
	 * @return
	 */
	public String getLienCreateur();

	/**
	 * @param string
	 */
	public void setLienCreateur(String lienCreateur);

	/**
	 * Indique si la donnée provient de l'audit des changements
	 * @return
	 */
	public String getAudit();

	/**
	 * @param string
	 */
	public void setAudit(String audit);

	/**
	 * Retourne le centre régional
	 * @return
	 */
	public String getCentreRegional();

	/**
	 * @param string
	 */
	public void setCentreRegional(String centreRegional);
	
	/**
	 * Retourne le district
	 * @return
	 */
	public String getDistrict();

	/**
	 * @param string
	 */
	public void setDistrict(String district);

	/**
	 * Retourne la description du district
	 * @return
	 */
	public String getDistrictDescription();

	/**
	 * @param string
	 */
	public void setDistrictDescription(String districtDescription);

	/**
	 * Retourne le code du compte
	 * @return
	 */
	public String getCodeCompte();

	/**
	 * @param string
	 */
	public void setCodeCompte(String codeCompte);
	
	/**
	 * Retourne si le détaillant RDD (Réseau des détaillants) est actif
	 * @return
	 */
	public boolean isActif();

	/**
	 * @param string
	 */
	public void setActif(boolean actif);
	
	/**
	 * Retourne la date d'inactivation du détaillant dans RDD
	 * @return
	 */
	public String getDateInactif();

	/**
	 * @param string
	 */
	public void setDateInactif(String dateInactif);
	
	/**
	 * Retourne la raison d'inactivation du détaillant dans RDD
	 * @return
	 */
	public String getCommentaire();

	/**
	 * @param string
	 */
	public void setCommentaire(String commentaire);
	
	/**
	 * Retourne si le détaillant provient du système RDD (Réseau des détaillants)
	 * @return
	 */
	public boolean isIndicateurRdd();

	/**
	 * @param string
	 */
	public void setIndicateurRdd(boolean indicateurRdd);

	/**
	 * Retourne la description du code de compte
	 * @return
	 */
	public String getCodeCompteDescription();

	/**
	 * @param string
	 */
	public void setCodeCompteDescription(String codeCompteDescription);
	
	/**
	 * Retourne la description du centre régional
	 * @return
	 */
	public String getCentreRegionalDescription();

	/**
	 * @param string
	 */
	public void setCentreRegionalDescription(String centreRegionalDescription);
	
	/**
	 * Retourne la raison de la désactivation
	 * @return
	 */
	public String getRaisonDesactivation();

	/**
	 * @param string
	 */
	public void setRaisonDesactivation(String raisonDesactivation);

	/**
	 * Retourne la méthode d'échantillonnage RDD
	 * @return
	 */
	public String getEchantillonnage();

	/**
	 * @param string
	 */
	public void setEchantillonnage(String echantillonnage);

	
}
