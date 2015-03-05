package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;
import java.util.List;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à un formulaire de consultation de sujet.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.10 $, $Date: 2002/03/11 16:49:00 $
 */
public interface SujetHtmlForm {


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
	 * @return String Valeur de l'entité en caractère.
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
	 * Retourne le prenom.
	 *
	 * @return String Valeur du prenom en caractère.
	 */
	public String getPrenom();

	/**
	 * Retourne l'alias.
	 *
	 * @return String Valeur de l'alias en caractère.
	 */
	public String getAlias();

	/**
	 * Retourne la date de naissance.
	 *
	 * @return String Valeur de la date de naissance en caractère.
	 */
	public String getDateNaissance();

	/**
	 * Retourne l'âge.
	 *
	 * @return String Valeur de l'âge en caractère.
	 */
	public String getAge();

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
	 * Retourne le numéro de client ou employé.
	 *
	 * @return String Valeur du numéro de client ou employé en caractère.
	 */
	public String getNumeroClientEmploye();

	/**
	 * Retourne le sexe.
	 *
	 * @return String Valeur du sexe en caractère.
	 */
	public String getSexe();

	/**
	 * Retourne la langue.
	 *
	 * @return String Valeur de la langue en caractère.
	 */
	public String getLangue();

	/**
	 * Retourne l'ethnie.
	 *
	 * @return String Valeur de l'ethnie en caractère.
	 */
	public String getEthnie();

	/**
	 * Retourne la race.
	 *
	 * @return String Valeur de la race en caractère.
	 */
	public String getRace();

	/**
	 * Retourne le statut.
	 *
	 * @return String Valeur du statut en caractère.
	 */
	public String getStatut();

	/**
	 * Retourne la sévérité Investigation.
	 *
	 * @return String Valeur de la sévérité en caractère.
	 */
	public String getSeverite();

	/**
	 * Retourne la sévérité.
	 *
	 * @return String Valeur de la sévérité en caractère.
	 */
	public String getSeveriteAutres();

    /**
     * Retourne la sévérité du casino.
     *
     * @return String Valeur de la sévérité en caractère.
     */
    public String getSeveriteCasino();

	/**
	 * Retourne le numéro d'assurance sociale.
	 *
	 * @return String Valeur du numéro d'assurance sociale en caractère.
	 */
	public String getNumeroAssuranceSociale();

	/**
	 * Retourne le numéro d'assurance maladie.
	 *
	 * @return String Valeur du numéro d'assurance maladie en caractère.
	 */
	public String getNumeroAssuranceMaladie();

	/**
	 * Retourne le numéro de permis de conduire.
	 *
	 * @return String Valeur du numéro de permis de conduire en caractère.
	 */
	public String getNumeroPermisConduire();

	/**
	 * Retourne la confidentialité.
	 *
	 * @return String Valeur de la confidentialité en caractère.
	 */
	public String getConfidentialite();

	/**
	 * Retourne le passeport.
	 *
	 * @return String Valeur du passeport en caractère.
	 */
	public String getPasseport();

	/**
	 * Retourne le mot de passe.
	 *
	 * @return String Valeur du mot de passe en caractère.
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
	 * Retourne le créateur du lien.
	 *
	 * @return String Valeur du créateur en caractère.
	 */
	public String getLienCreateur();

   /**
	 * Retourne le rôle.
	 *
	 * @return String Valeur numérique du rôle.
	 */
	public String getRole();

	/**
	 * Retourne le type de lien.
	 *
	 * @return String Valeur numérique du type de lien.
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
	 * Retourne la date de liaison.
	 *
	 * @return String date de liaison
	 */
	public String getLienDateCreation();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur numérique de date.
     */
    public String getDateChangement();

    /**
     * Retourne la date de fin d'emploi (par rapport à la société liée).
     *
     * @return String Valeur numérique de date.
     */
    public String getDateFinEmploi();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur numérique de l'auteur.
     */
    public String getChangePar();

	/**
	 * Retourne les narrations associés.
	 *
	 * @return Collection
	 */
	public Collection getNarrations();

	/**
	 * Retourne les dossiers associés.
	 *
	 * @return Collection
	 */
	public Collection getDossiers();
	/**
	 * Retourne les societes associés.
	 *
	 * @return Collection
	 */
	public Collection getSocietes();

	/**
	 * Retourne les sujets associés.
	 *
	 * @return Collection
	 */
	public Collection getSujets();

	/**
	 * Retourne les adresses associés.
	 *
	 * @return List
	 */
	public List getAdresses();

	/**
	 * Retourne les caracteristiques  associés.
	 *
	 * @return Collection
	 */
	public CaracteristiquesHtmlForm getCaracteristiques();

	/**
	 * Retourne les  photos associés.
	 *
	 * @return Collection
	 */
	public Collection getPhotos();

	/**
	 * Retourne les  vehicules associés.
	 *
	 * @return Collection
	 */
	public Collection getVehicules();
	
    /**
     * Retourne le typ d'âge
     *
     * @return String valeur numérique de typeAge
     */
	public String getTypeAge();

	// Setters


	/**
	 * Affecte un site.
	 *
	 * @param site Valeur du site en caractère.
	 */
	public void setSite(String site);

	/**
	 * Affecte une entité.
	 *
	 * @param entite Valeur de l'entité en caractère.
	 */
	public void setEntite(String entite);

	/**
	 * Affecte une cle.
	 *
	 * @param cle Valeur de la cle en caractère.
	 */
	public void setCle(String cle);

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
	 * Affecte un prenom.
	 *
	 * @param prenom Valeur du prenom en caractère.
	 */
	public void setPrenom(String prenom);

	/**
	 * Affecte un alias.
	 *
	 * @param alias Valeur de l'alias en caractère.
	 */
	public void setAlias(String alias);

	/**
	 * Affecte une date de naissance.
	 *
	 * @param dateNaissance Valeur de la date de naissance en caractère.
	 */
	public void setDateNaissance(String dateNaissance);

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
     * Affecte la date de fin d'emploi
     *
     * @param dateFinEmploi Valeur dateFinEmploi.
     */
    public void setDateFinEmploi(String dateFinEmploi);

	/**
	 * Affecte une date de liaison.
	 *
	 * @param lienDateCreation Valeur de la date de liaison en caractère.
	 */
	public void setLienDateCreation(String lienDateCreation);

	/**
	 * Affecte un âge.
	 *
	 * @param age Valeur de l'âge en caractère.
	 */
	public void setAge(String age);

	/**
	 * Affecte une première référence.
	 *
	 * @param reference1 Valeur de la première référence en caractère.
	 */
	public void setReference1(String reference1);

	/**
	 * Affecte une deuxième référence.
	 *
	 * @param reference1 Valeur de la deuxième référence en caractère.
	 */
	public void setReference2(String reference2);

	/**
	 * Affecte un numéro de client ou employé.
	 *
	 * @param numeroClientEmploye Valeur du numéro de client ou employé en caractère.
	 */
	public void setNumeroClientEmploye(String numeroClientEmploye);

	/**
	 * Affecte un sexe.
	 *
	 * @param sexe Valeur du sexe en caractère.
	 */
	public void setSexe(String sexe);

	/**
	 * Affecte une langue.
	 *
	 * @param langue Valeur de la langue en caractère.
	 */
	public void setLangue(String langue);

	/**
	 * Affecte un ethnie.
	 *
	 * @param ethnie Valeur de l'ethnie en caractère.
	 */
	public void setEthnie(String ethnie);

	/**
	 * Affecte une race.
	 *
	 * @param race Valeur de la race en caractère.
	 */
	public void setRace(String race);

	/**
	 * Affecte un statut.
	 *
	 * @param statut Valeur du statut en caractère.
	 */
	public void setStatut(String statut);

	/**
	 * Affecte une sévérité Investigation.
	 *
	 * @param severite Valeur de la sévérité en caractère.
	 */
	public void setSeverite(String severite);

	/**
	 * Affecte une sévérité.
	 *
	 * @param severite Valeur de la sévérité en caractère.
	 */
	public void setSeveriteAutres(String severiteAutres);

    /**
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité en caractère.
     */
    public void setSeveriteCasino(String severiteCasino);

	/**
	 * Affecte un numéro d'assurance sociale.
	 *
	 * @param numeroAssuranceSociale Valeur du numéro d'assurance sociale en
	 * caractère.
	 */
	public void setNumeroAssuranceSociale(String numeroAssuranceSociale);

	/**
	 * Affecte un numéro d'assurance maladie.
	 *
	 * @param numeroAssuranceMaladie Valeur du numéro d'assurance maladie en caractère.
	 */
	public void setNumeroAssuranceMaladie(String numeroAssuranceMaladie);

	/**
	 * Affecte un numéro de permis de conduire.
	 *
	 * @param numeroPermisConduire Valeur du numéro de permis de conduire en
	 * caractère.
	 */
	public void setNumeroPermisConduire(String numeroPermisConduire);

	/**
	 * Affecte une confidentialité.
	 *
	 * @param confidentialite Valeur de la confidentialité en caractère.
	 */
	public void setConfidentialite(String confidentialite);

	/**
	 * Affecte un passeport.
	 *
	 * @param passeport Valeur du passeport en caractère.
	 */
	public void setPasseport(String passeport);

	/**
	 * Affecte un mot de passe.
	 *
	 * @param motPasse Valeur du mot de passe en caractère.
	 */
	public void setMotPasse(String motPasse);

	/**
	 * Affecte une confirmation de mot de passe.
	 *
	 * @param confirmationMotPasse Valeur de la confirmation du mot de passe en
	 * caractère.
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
	 * Affecte un créateur du lien.
	 *
	 * @param lienCreateur Valeur du créateur en caractère.
	 */
	public void setLienCreateur(String lienCreateur);

	/**
	 * Affecte le rôle.
	 *
	 * @param role Valeur numérique du rôle.
	 */
	public void setRole(String role);

	/**
	 * Affecte le type de lien.
	 *
	 * @param typeLien Valeur numérique du type de lien.
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
	 * Ajoute une narrations associés.
	 */
	public void addNarration(NarrationHtmlForm narration);

	/**
	 * Ajoute un dossiers associés.
	 */
	public void addDossier(DossierHtmlForm dossier);

	/**
	 * Ajoute un societes associés.
	 */
	public void addSociete(SocieteHtmlForm societe);

	/**
	 * Ajoute un sujets associés.
	 */
	public void addSujet(SujetHtmlForm sujet);

	/**
	 * Ajoute une adresses associés.
	 */
	public void addAdresse(AdresseHtmlForm adresse);

	/**
	 * Ajoute une caracteristiques  associés.
	 */
	public void setCaracteristiques(CaracteristiquesHtmlForm caracteristique);

	/**
	 * Ajoute une sous liste de photo.
	 *
	 * @param photos sous liste de photo.
	 */
	public void addPhoto(Collection photos);

	/**
	 * Ajoute un  vehicules associés.
	 */
	public void addVehicule(VehiculeHtmlForm vehicule);

	//Indique si la donnée vient de RDD
	public boolean isIndicateurRdd();
	
	public void setIndicateurRdd(boolean indicateurRdd);

	/**
	 * Indique si la donnée provient de l'audit des changements
	 * @return
	 */
	public String getAudit();

	/**
	 * @param string
	 */
	public void setAudit(String audit);	

	public String getAnneeNaissance();

	/**
	 * @return dateFinEnquete
	 */
	public String getDateFinEnquete();


	/**
	 * @param dateFinEnquete dateFinEnquete à définir
	 */
	public void setDateFinEnquete(String dateFinEnquete);

    /**
     * @param typeAge type d'âge à définir
     */
	public void setTypeAge(String typeAge);
}
