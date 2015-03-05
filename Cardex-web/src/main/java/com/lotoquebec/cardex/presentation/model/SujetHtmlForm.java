package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;
import java.util.List;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � un formulaire de consultation de sujet.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.10 $, $Date: 2002/03/11 16:49:00 $
 */
public interface SujetHtmlForm {


	// Getters


	/**
	 * Retourne la cle.
	 *
	 * @return String Valeur de la cle en caract�re.
	 */
	public String getCle();

	/**
	 * Retourne le site.
	 *
	 * @return String Valeur du site en caract�re.
	 */
	public String getSite();

	/**
	 * Retourne l'entit�.
	 *
	 * @return String Valeur de l'entit� en caract�re.
	 */
	public String getEntite();

	/**
	 * Retourne le num�ro de fiche.
	 *
	 * @return String Valeur du num�ro de fiche en caract�re.
	 */
	public String getNumeroFiche();

	/**
	 * Retourne le nom.
	 *
	 * @return String Valeur du nom en caract�re.
	 */
	public String getNom();

	/**
	 * Retourne le prenom.
	 *
	 * @return String Valeur du prenom en caract�re.
	 */
	public String getPrenom();

	/**
	 * Retourne l'alias.
	 *
	 * @return String Valeur de l'alias en caract�re.
	 */
	public String getAlias();

	/**
	 * Retourne la date de naissance.
	 *
	 * @return String Valeur de la date de naissance en caract�re.
	 */
	public String getDateNaissance();

	/**
	 * Retourne l'�ge.
	 *
	 * @return String Valeur de l'�ge en caract�re.
	 */
	public String getAge();

	/**
	 * Retourne la premi�re r�f�rence.
	 *
	 * @return String Valeur de la premi�re r�f�rence en caract�re.
	 */
	public String getReference1();

	/**
	 * Retourne la deuxi�me r�f�rence.
	 *
	 * @return String Valeur de la deuxi�me r�f�rence en caract�re.
	 */
	public String getReference2();

	/**
	 * Retourne le num�ro de client ou employ�.
	 *
	 * @return String Valeur du num�ro de client ou employ� en caract�re.
	 */
	public String getNumeroClientEmploye();

	/**
	 * Retourne le sexe.
	 *
	 * @return String Valeur du sexe en caract�re.
	 */
	public String getSexe();

	/**
	 * Retourne la langue.
	 *
	 * @return String Valeur de la langue en caract�re.
	 */
	public String getLangue();

	/**
	 * Retourne l'ethnie.
	 *
	 * @return String Valeur de l'ethnie en caract�re.
	 */
	public String getEthnie();

	/**
	 * Retourne la race.
	 *
	 * @return String Valeur de la race en caract�re.
	 */
	public String getRace();

	/**
	 * Retourne le statut.
	 *
	 * @return String Valeur du statut en caract�re.
	 */
	public String getStatut();

	/**
	 * Retourne la s�v�rit� Investigation.
	 *
	 * @return String Valeur de la s�v�rit� en caract�re.
	 */
	public String getSeverite();

	/**
	 * Retourne la s�v�rit�.
	 *
	 * @return String Valeur de la s�v�rit� en caract�re.
	 */
	public String getSeveriteAutres();

    /**
     * Retourne la s�v�rit� du casino.
     *
     * @return String Valeur de la s�v�rit� en caract�re.
     */
    public String getSeveriteCasino();

	/**
	 * Retourne le num�ro d'assurance sociale.
	 *
	 * @return String Valeur du num�ro d'assurance sociale en caract�re.
	 */
	public String getNumeroAssuranceSociale();

	/**
	 * Retourne le num�ro d'assurance maladie.
	 *
	 * @return String Valeur du num�ro d'assurance maladie en caract�re.
	 */
	public String getNumeroAssuranceMaladie();

	/**
	 * Retourne le num�ro de permis de conduire.
	 *
	 * @return String Valeur du num�ro de permis de conduire en caract�re.
	 */
	public String getNumeroPermisConduire();

	/**
	 * Retourne la confidentialit�.
	 *
	 * @return String Valeur de la confidentialit� en caract�re.
	 */
	public String getConfidentialite();

	/**
	 * Retourne le passeport.
	 *
	 * @return String Valeur du passeport en caract�re.
	 */
	public String getPasseport();

	/**
	 * Retourne le mot de passe.
	 *
	 * @return String Valeur du mot de passe en caract�re.
	 */
	public String getMotPasse();

	/**
	 * Retourne la confirmation du mot de passe.
	 *
	 * @return String Valeur de la confirmation du mot de passe en caract�re.
	 */
	public String getConfirmationMotPasse();

	/**
	 * Retourne le lien.
	 *
	 * @return String Valeur du lien en caract�re.
	 */
	public String getLien();

	/**
	 * Retourne le lien du site.
	 *
	 * @return String Valeur du lien du site en caract�re.
	 */
	public String getLienSite();

	/**
	 * Retourne le cr�ateur du lien.
	 *
	 * @return String Valeur du cr�ateur en caract�re.
	 */
	public String getLienCreateur();

   /**
	 * Retourne le r�le.
	 *
	 * @return String Valeur num�rique du r�le.
	 */
	public String getRole();

	/**
	 * Retourne le type de lien.
	 *
	 * @return String Valeur num�rique du type de lien.
	 */
	public String getTypeLien();

	/**
	 * Retourne le cr�ateur du sujet.
	 *
	 * @return String Code du cr�ateur.
	 */
	public String getCreateur();

	/**
	 * Retourne la date de cr�ation.
	 *
	 * @return String date de cr�ation
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
     * @return String Valeur num�rique de date.
     */
    public String getDateChangement();

    /**
     * Retourne la date de fin d'emploi (par rapport � la soci�t� li�e).
     *
     * @return String Valeur num�rique de date.
     */
    public String getDateFinEmploi();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur num�rique de l'auteur.
     */
    public String getChangePar();

	/**
	 * Retourne les narrations associ�s.
	 *
	 * @return Collection
	 */
	public Collection getNarrations();

	/**
	 * Retourne les dossiers associ�s.
	 *
	 * @return Collection
	 */
	public Collection getDossiers();
	/**
	 * Retourne les societes associ�s.
	 *
	 * @return Collection
	 */
	public Collection getSocietes();

	/**
	 * Retourne les sujets associ�s.
	 *
	 * @return Collection
	 */
	public Collection getSujets();

	/**
	 * Retourne les adresses associ�s.
	 *
	 * @return List
	 */
	public List getAdresses();

	/**
	 * Retourne les caracteristiques  associ�s.
	 *
	 * @return Collection
	 */
	public CaracteristiquesHtmlForm getCaracteristiques();

	/**
	 * Retourne les  photos associ�s.
	 *
	 * @return Collection
	 */
	public Collection getPhotos();

	/**
	 * Retourne les  vehicules associ�s.
	 *
	 * @return Collection
	 */
	public Collection getVehicules();
	
    /**
     * Retourne le typ d'�ge
     *
     * @return String valeur num�rique de typeAge
     */
	public String getTypeAge();

	// Setters


	/**
	 * Affecte un site.
	 *
	 * @param site Valeur du site en caract�re.
	 */
	public void setSite(String site);

	/**
	 * Affecte une entit�.
	 *
	 * @param entite Valeur de l'entit� en caract�re.
	 */
	public void setEntite(String entite);

	/**
	 * Affecte une cle.
	 *
	 * @param cle Valeur de la cle en caract�re.
	 */
	public void setCle(String cle);

	/**
	 * Affecte un num�ro de fiche.
	 *
	 * @param numeroFiche Valeur du num�ro de fiche en caract�re.
	 */
	public void setNumeroFiche(String numeroFiche);

	/**
	 * Affecte un nom.
	 *
	 * @param nom Valeur du nom en caract�re.
	 */
	public void setNom(String nom);

	/**
	 * Affecte un prenom.
	 *
	 * @param prenom Valeur du prenom en caract�re.
	 */
	public void setPrenom(String prenom);

	/**
	 * Affecte un alias.
	 *
	 * @param alias Valeur de l'alias en caract�re.
	 */
	public void setAlias(String alias);

	/**
	 * Affecte une date de naissance.
	 *
	 * @param dateNaissance Valeur de la date de naissance en caract�re.
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
	 * @param lienDateCreation Valeur de la date de liaison en caract�re.
	 */
	public void setLienDateCreation(String lienDateCreation);

	/**
	 * Affecte un �ge.
	 *
	 * @param age Valeur de l'�ge en caract�re.
	 */
	public void setAge(String age);

	/**
	 * Affecte une premi�re r�f�rence.
	 *
	 * @param reference1 Valeur de la premi�re r�f�rence en caract�re.
	 */
	public void setReference1(String reference1);

	/**
	 * Affecte une deuxi�me r�f�rence.
	 *
	 * @param reference1 Valeur de la deuxi�me r�f�rence en caract�re.
	 */
	public void setReference2(String reference2);

	/**
	 * Affecte un num�ro de client ou employ�.
	 *
	 * @param numeroClientEmploye Valeur du num�ro de client ou employ� en caract�re.
	 */
	public void setNumeroClientEmploye(String numeroClientEmploye);

	/**
	 * Affecte un sexe.
	 *
	 * @param sexe Valeur du sexe en caract�re.
	 */
	public void setSexe(String sexe);

	/**
	 * Affecte une langue.
	 *
	 * @param langue Valeur de la langue en caract�re.
	 */
	public void setLangue(String langue);

	/**
	 * Affecte un ethnie.
	 *
	 * @param ethnie Valeur de l'ethnie en caract�re.
	 */
	public void setEthnie(String ethnie);

	/**
	 * Affecte une race.
	 *
	 * @param race Valeur de la race en caract�re.
	 */
	public void setRace(String race);

	/**
	 * Affecte un statut.
	 *
	 * @param statut Valeur du statut en caract�re.
	 */
	public void setStatut(String statut);

	/**
	 * Affecte une s�v�rit� Investigation.
	 *
	 * @param severite Valeur de la s�v�rit� en caract�re.
	 */
	public void setSeverite(String severite);

	/**
	 * Affecte une s�v�rit�.
	 *
	 * @param severite Valeur de la s�v�rit� en caract�re.
	 */
	public void setSeveriteAutres(String severiteAutres);

    /**
     * Affecte une s�v�rit�.
     *
     * @param severite Valeur de la s�v�rit� en caract�re.
     */
    public void setSeveriteCasino(String severiteCasino);

	/**
	 * Affecte un num�ro d'assurance sociale.
	 *
	 * @param numeroAssuranceSociale Valeur du num�ro d'assurance sociale en
	 * caract�re.
	 */
	public void setNumeroAssuranceSociale(String numeroAssuranceSociale);

	/**
	 * Affecte un num�ro d'assurance maladie.
	 *
	 * @param numeroAssuranceMaladie Valeur du num�ro d'assurance maladie en caract�re.
	 */
	public void setNumeroAssuranceMaladie(String numeroAssuranceMaladie);

	/**
	 * Affecte un num�ro de permis de conduire.
	 *
	 * @param numeroPermisConduire Valeur du num�ro de permis de conduire en
	 * caract�re.
	 */
	public void setNumeroPermisConduire(String numeroPermisConduire);

	/**
	 * Affecte une confidentialit�.
	 *
	 * @param confidentialite Valeur de la confidentialit� en caract�re.
	 */
	public void setConfidentialite(String confidentialite);

	/**
	 * Affecte un passeport.
	 *
	 * @param passeport Valeur du passeport en caract�re.
	 */
	public void setPasseport(String passeport);

	/**
	 * Affecte un mot de passe.
	 *
	 * @param motPasse Valeur du mot de passe en caract�re.
	 */
	public void setMotPasse(String motPasse);

	/**
	 * Affecte une confirmation de mot de passe.
	 *
	 * @param confirmationMotPasse Valeur de la confirmation du mot de passe en
	 * caract�re.
	 */
	public void setConfirmationMotPasse(String confirmationMotPasse);

	/**
	 * Affecte un lien.
	 *
	 * @param lien Valeur du lien en caract�re.
	 */
	public void setLien(String lien);

	/**
	 * Affecte un lien du site.
	 *
	 * @param lienSite Valeur du lien du site en caract�re.
	 */
	public void setLienSite(String lienSite);

	/**
	 * Affecte un cr�ateur du lien.
	 *
	 * @param lienCreateur Valeur du cr�ateur en caract�re.
	 */
	public void setLienCreateur(String lienCreateur);

	/**
	 * Affecte le r�le.
	 *
	 * @param role Valeur num�rique du r�le.
	 */
	public void setRole(String role);

	/**
	 * Affecte le type de lien.
	 *
	 * @param typeLien Valeur num�rique du type de lien.
	 */
	public void setTypeLien(String typeLien);

	/**
	 * Affecte le cr�ateur du sujet.
	 *
	 * @param createur Code du cr�ateur.
	 */
	public void setCreateur(String createur);

	/**
	 * Affecte la date de cr�ation.
	 *
	 * @param dateCreation Date de cr�ation.
	 */
	public void setDateCreation(String dateCreation);

	/**
	 * Ajoute une narrations associ�s.
	 */
	public void addNarration(NarrationHtmlForm narration);

	/**
	 * Ajoute un dossiers associ�s.
	 */
	public void addDossier(DossierHtmlForm dossier);

	/**
	 * Ajoute un societes associ�s.
	 */
	public void addSociete(SocieteHtmlForm societe);

	/**
	 * Ajoute un sujets associ�s.
	 */
	public void addSujet(SujetHtmlForm sujet);

	/**
	 * Ajoute une adresses associ�s.
	 */
	public void addAdresse(AdresseHtmlForm adresse);

	/**
	 * Ajoute une caracteristiques  associ�s.
	 */
	public void setCaracteristiques(CaracteristiquesHtmlForm caracteristique);

	/**
	 * Ajoute une sous liste de photo.
	 *
	 * @param photos sous liste de photo.
	 */
	public void addPhoto(Collection photos);

	/**
	 * Ajoute un  vehicules associ�s.
	 */
	public void addVehicule(VehiculeHtmlForm vehicule);

	//Indique si la donn�e vient de RDD
	public boolean isIndicateurRdd();
	
	public void setIndicateurRdd(boolean indicateurRdd);

	/**
	 * Indique si la donn�e provient de l'audit des changements
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
	 * @param dateFinEnquete dateFinEnquete � d�finir
	 */
	public void setDateFinEnquete(String dateFinEnquete);

    /**
     * @param typeAge type d'�ge � d�finir
     */
	public void setTypeAge(String typeAge);
}
