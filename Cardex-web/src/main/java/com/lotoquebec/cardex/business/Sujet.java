package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.EntiteCardex;

/**
 * Les sujets renferment des données sur des personnes qui font
 * l'objet d'un dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revison: $, $Date: 2002/04/11 19:20:26 $
 */
public interface Sujet extends EntiteCardex{


	// Getters

	/**
	 * Retourne la clé.
	 *
	 * @return long Numéro de clé du sujet.
	 */

	public long getCle();

	/**
	 * Retourne le site.
	 *
	 * @return long numéro de site du sujet.
	 */
	public long getSite();

	/**
	 * Retourne l'entité.
	 *
	 * @return long Valeur de l'entité en caractère.
	 */
	public long getEntite();

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
	 * @return Timestamp Valeur de la date de naissance (yyy-MM-dd).
	 */
	public Timestamp getDateNaissance();

	/**
	 * Retourne la date de création de la liaison.
	 *
	 * @return Timestamp Valeur de la date de liaison (yyy-MM-dd).
	 */
	public Timestamp getLienDateCreation();

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
	 * Correspond au champ Référence_3
	 * @return String Valeur du numéro de client ou employé en caractère.
	 */
	public String getNumeroClientEmploye();

	/**
	 * Retourne le sexe.
	 *
	 * @return long Valeur du sexe.
	 */
	public long getSexe();

	/**
	 * Retourne la langue.
	 *
	 * @return long Valeur de la langue.
	 */
	public long getLangue();

	/**
	 * Retourne l'ethnie.
	 *
	 * @return long Valeur de l'ethnie.
	 */
	public long getEthnie();

	/**
	 * Retourne la race.
	 *
	 * @return long Valeur de la race.
	 */
	public long getRace();

	/**
	 * Retourne le statut.
	 *
	 * @return long Valeur du statut.
	 */
	public long getStatut();

    /**
     * Retourne la sévérité Investigation.
     *
     * @return long Valeur de la sévérité en caractère.
     */
    public long getSeverite();

    /**
     * Retourne la sévérité du Casino.
     *
     * @return long Valeur de la sévérité.
     */
    public long getSeveriteCasino();

	/**
	 * Retourne la sévérité.
	 *
	 * @return long Valeur de la sévérité en caractère.
	 */
	public long getSeveriteAutres();

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
	 * @return long Valeur de la confidentialité.
	 */
	public long getConfidentialite();

	/**
	 * Retourne le passeport.
	 *
	 * @return String Valeur du passeport en caractère.
	 */
	public String getPasseport();

	/**
	 * Retourne le mot de passe.
	 *
	 * @return String Valeur du mot de passe.
	 */
	public String getMotPasse();

	/**
	 * Retourne le mot de passe.
	 *
	 * @return String Valeur du mot de passe en caractère.
	 */
	public String getConfirmationMotPasse();

	/**
	 * Retourne le lien.
	 *
	 * @return String Valeur numérique du lien.
	 */
	public long getLien();

	/**
	 * Retourne le lien du site.
	 *
	 * @return String Valeur numérique du lien du site.
	 */
	public long getLienSite();

	/**
	 * Retourne le créateur du lien.
	 *
	 * @return String Valeur du créateur en caractère.
	 */
	public String getLienCreateur();

	/**
	 * Retourne le rôle.
	 *
	 * @return long Valeur numérique du rôle.
	 */
	public long getRole();

	/**
	 * Retourne le type de lien.
	 *
	 * @return long Valeur numérique du type de lien.
	 */
	public long getTypeLien();

	/**
	 * Retourne le créateur du sujet.
	 *
	 * @return String Code du créateur
	 */
	public String getCreateur();

	/**
	 * Retourne la date de création.
	 *
	 * @return Timestamp date de création
	 */
	public Timestamp getDateCreation();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur numérique de date.
     */
    public Timestamp getDateChangement();

    /**
     * Retourne la date de fin d'emploi par rapport à la société liée au sujet.
     *
     * @return String Valeur numérique de date.
     */
    public Timestamp getDateFinEmploi();

    /**
     * Retourne la date de fin d'enquête pour l'onglet Sujets.
     *
     * @return String Valeur numérique de date.
     */
    public Timestamp getDateFinEnquete();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur numérique de l'auteur.
     */
    public String getChangePar();

    /**
     * Retourne le type d'âge.
     *
     * @return long Valeur numérique du type d'âge.
     */
    public long getTypeAge();
    
	// Setters


	/**
	 * Affecte un numéro de clé.
	 *
	 * @param cle Valeur du numéro de clé du sujet
	 */
	public void setCle(long cle);

	/**
	 * Affecte un numéro de site.
	 *
	 * @param site Valeur du numéro de site d'un sujet.
	 */
	public void setSite(long Site);

	/**
	 * Affecte une entité.
	 *
	 * @param entite Valeur de l'entité en caractère.
	 */
	public void setEntite(long entite);

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
	 * @param dateNaissance Valeur de la date de naissance (yyyy-MM-dd).
	 */
	public void setDateNaissance(Timestamp dateNaissance);
	
	/**
	 * Affecte une date de liaison.
	 *
	 * @param dateNaissance Valeur de la date de liaison (yyyy-MM-dd).
	 */
	public void setLienDateCreation(Timestamp lienDateCreation);

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
	 * @param sexe Valeur du sexe.
	 */

	public void setSexe(long sexe);

	/**
	 * Affecte une langue.
	 *
	 * @param langue Valeur de la langue.
	 */
	public void setLangue(long langue);

	/**
	 * Affecte un ethnie.
	 *
	 * @param ethnie Valeur de l'ethnie.
	 */
	public void setEthnie(long ethnie);

	/**
	 * Affecte une race.
	 *
	 * @param race Valeur de la race.
	 */
	public void setRace(long race);

	/**
	 * Affecte un statut.
	 *
	 * @param statut Valeur du statut.
	 */
	public void setStatut(long statut);

	/**
	 * Affecte une sévérité Investigation.
	 *
	 * @param severite Valeur de la sévérité en caractère.
	 */
	public void setSeverite(long severite);

    /**
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité.
     */
    public void setSeveriteCasino(long severiteCasino);

	/**
	 * Affecte une sévérité.
	 *
	 * @param severite Valeur de la sévérité en caractère.
	 */
	public void setSeveriteAutres(long severiteAutres);

	/**
	 * Affecte un numéro d'assurance sociale.
	 *
	 * @param numeroAssuranceSociale Valeur du numéro d'assurance sociale en caractère.
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
	 * @param numeroPermisConduire Valeur du numéro de permis de conduire en caractère.
	 */
	public void setNumeroPermisConduire(String numeroPermisConduire);

	/**
	 * Affecte une confidentialité.
	 *
	 * @param confidentialite Valeur de la confidentialité.
	 */
	public void setConfidentialite(long confidentialite);

	/**
	 * Affecte un passeport.
	 *
	 * @param passeport Valeur du passeport en caractère.
	 */
	public void setPasseport(String passeport);

	/**
	 * Affecte un mot de passe.
	 *
	 * @param motPasse Valeur du mot de passe.
	 */
	public void setMotPasse(String motPasse);

	/**
	 * Affecte un mot de passe.
	 *
	 * @param motPasse Valeur du mot de passe en caractère.
	 */
	public void setConfirmationMotPasse(String motPasse);

	// Attributs du lien de dossiers :


	/**
	 * Affecte le lien cle.
	 *
	 * @param lien Valeur numérique du lien cle.
	 */
	public void setLien(long lien);

	/**
	 * Affecte le lien du site.
	 *
	 * @param lienSite Valeur numérique du lien du site.
	 */
	public void setLienSite(long lienSite);

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
	public void setRole(long role);

	/**
	 * Affecte le type de lien.
	 *
	 * @param typeLien Valeur numérique du type de lien.
	 */
	public void setTypeLien(long typeLien);

	/**
	 * Affecte le créateur du sujet.
	 *
	 * @param createur Code du créateur
	 */
	public void setCreateur(String createur);

    /**
     * Affecte de la date du changement (audit).
     *
     * @param dateEvenement Valeur de la date d'événement (yyyy-MM-dd).
     */
    public void setDateChangement(Timestamp dateChangement);

    /**
     * Affecte l'auteur du changement (pour l'audit).
     *
     * @param changePar Valeur changePar.
     */
    public void setChangePar(String changePar);

	/**
	 * Affecte la date de création.
	 *
	 * @param dateCreation Valeur de la date de création.
	 */
	public void setDateCreation(Timestamp dateCreation);
	
	/**
	 * Affecte la date de fin d'emploi.
	 *
	 * @param dateFinEmploi Valeur de la date de fin d'emploi.
	 */
	public void setDateFinEmploi(Timestamp dateFinEmploi);
	
	// générer une cle unique composé de la clé et du site
	public Object getCleUnique();
	
	// Ajout d'une adresse dans la liste des adresses de la société
	public void addAdresse(Adresse adresse);	
	
	public Boolean isNASCanadien();
	
	public void setNASCanadien(Boolean canadien);
	
	public abstract boolean isPossedeCle();

	/** 
	 * Indique si la donnée provient de l'audit des changements
	 * @return
	 */
	public String getAudit();

	/**
	 * @param string
	 */
	public void setAudit(String audit);

	//Indique si la donnée vient de RDD
	public boolean isIndicateurRdd();
	
	public void setIndicateurRdd(boolean indicateurRdd);

    /**
     * Affecte de la date de fin d'enquête du sujet.
     *
     * @param dateEvenement Valeur de la date d'événement (yyyy-MM-dd).
     */
    public void setDateFinEnquete(Timestamp dateChangement);

    /**
     * Affecte le type d'âge.
     *
     * @param typeLien Valeur numérique du type d'âge.
     */
    public void setTypeAge(long typeAge);
	
}