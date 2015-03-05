package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.EntiteCardex;

/**
 * Les sujets renferment des donn�es sur des personnes qui font
 * l'objet d'un dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revison: $, $Date: 2002/04/11 19:20:26 $
 */
public interface Sujet extends EntiteCardex{


	// Getters

	/**
	 * Retourne la cl�.
	 *
	 * @return long Num�ro de cl� du sujet.
	 */

	public long getCle();

	/**
	 * Retourne le site.
	 *
	 * @return long num�ro de site du sujet.
	 */
	public long getSite();

	/**
	 * Retourne l'entit�.
	 *
	 * @return long Valeur de l'entit� en caract�re.
	 */
	public long getEntite();

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
	 * @return Timestamp Valeur de la date de naissance (yyy-MM-dd).
	 */
	public Timestamp getDateNaissance();

	/**
	 * Retourne la date de cr�ation de la liaison.
	 *
	 * @return Timestamp Valeur de la date de liaison (yyy-MM-dd).
	 */
	public Timestamp getLienDateCreation();

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
	 * Correspond au champ R�f�rence_3
	 * @return String Valeur du num�ro de client ou employ� en caract�re.
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
     * Retourne la s�v�rit� Investigation.
     *
     * @return long Valeur de la s�v�rit� en caract�re.
     */
    public long getSeverite();

    /**
     * Retourne la s�v�rit� du Casino.
     *
     * @return long Valeur de la s�v�rit�.
     */
    public long getSeveriteCasino();

	/**
	 * Retourne la s�v�rit�.
	 *
	 * @return long Valeur de la s�v�rit� en caract�re.
	 */
	public long getSeveriteAutres();

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
	 * @return long Valeur de la confidentialit�.
	 */
	public long getConfidentialite();

	/**
	 * Retourne le passeport.
	 *
	 * @return String Valeur du passeport en caract�re.
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
	 * @return String Valeur du mot de passe en caract�re.
	 */
	public String getConfirmationMotPasse();

	/**
	 * Retourne le lien.
	 *
	 * @return String Valeur num�rique du lien.
	 */
	public long getLien();

	/**
	 * Retourne le lien du site.
	 *
	 * @return String Valeur num�rique du lien du site.
	 */
	public long getLienSite();

	/**
	 * Retourne le cr�ateur du lien.
	 *
	 * @return String Valeur du cr�ateur en caract�re.
	 */
	public String getLienCreateur();

	/**
	 * Retourne le r�le.
	 *
	 * @return long Valeur num�rique du r�le.
	 */
	public long getRole();

	/**
	 * Retourne le type de lien.
	 *
	 * @return long Valeur num�rique du type de lien.
	 */
	public long getTypeLien();

	/**
	 * Retourne le cr�ateur du sujet.
	 *
	 * @return String Code du cr�ateur
	 */
	public String getCreateur();

	/**
	 * Retourne la date de cr�ation.
	 *
	 * @return Timestamp date de cr�ation
	 */
	public Timestamp getDateCreation();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur num�rique de date.
     */
    public Timestamp getDateChangement();

    /**
     * Retourne la date de fin d'emploi par rapport � la soci�t� li�e au sujet.
     *
     * @return String Valeur num�rique de date.
     */
    public Timestamp getDateFinEmploi();

    /**
     * Retourne la date de fin d'enqu�te pour l'onglet Sujets.
     *
     * @return String Valeur num�rique de date.
     */
    public Timestamp getDateFinEnquete();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur num�rique de l'auteur.
     */
    public String getChangePar();

    /**
     * Retourne le type d'�ge.
     *
     * @return long Valeur num�rique du type d'�ge.
     */
    public long getTypeAge();
    
	// Setters


	/**
	 * Affecte un num�ro de cl�.
	 *
	 * @param cle Valeur du num�ro de cl� du sujet
	 */
	public void setCle(long cle);

	/**
	 * Affecte un num�ro de site.
	 *
	 * @param site Valeur du num�ro de site d'un sujet.
	 */
	public void setSite(long Site);

	/**
	 * Affecte une entit�.
	 *
	 * @param entite Valeur de l'entit� en caract�re.
	 */
	public void setEntite(long entite);

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
	 * Affecte une s�v�rit� Investigation.
	 *
	 * @param severite Valeur de la s�v�rit� en caract�re.
	 */
	public void setSeverite(long severite);

    /**
     * Affecte une s�v�rit�.
     *
     * @param severite Valeur de la s�v�rit�.
     */
    public void setSeveriteCasino(long severiteCasino);

	/**
	 * Affecte une s�v�rit�.
	 *
	 * @param severite Valeur de la s�v�rit� en caract�re.
	 */
	public void setSeveriteAutres(long severiteAutres);

	/**
	 * Affecte un num�ro d'assurance sociale.
	 *
	 * @param numeroAssuranceSociale Valeur du num�ro d'assurance sociale en caract�re.
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
	 * @param numeroPermisConduire Valeur du num�ro de permis de conduire en caract�re.
	 */
	public void setNumeroPermisConduire(String numeroPermisConduire);

	/**
	 * Affecte une confidentialit�.
	 *
	 * @param confidentialite Valeur de la confidentialit�.
	 */
	public void setConfidentialite(long confidentialite);

	/**
	 * Affecte un passeport.
	 *
	 * @param passeport Valeur du passeport en caract�re.
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
	 * @param motPasse Valeur du mot de passe en caract�re.
	 */
	public void setConfirmationMotPasse(String motPasse);

	// Attributs du lien de dossiers :


	/**
	 * Affecte le lien cle.
	 *
	 * @param lien Valeur num�rique du lien cle.
	 */
	public void setLien(long lien);

	/**
	 * Affecte le lien du site.
	 *
	 * @param lienSite Valeur num�rique du lien du site.
	 */
	public void setLienSite(long lienSite);

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
	public void setRole(long role);

	/**
	 * Affecte le type de lien.
	 *
	 * @param typeLien Valeur num�rique du type de lien.
	 */
	public void setTypeLien(long typeLien);

	/**
	 * Affecte le cr�ateur du sujet.
	 *
	 * @param createur Code du cr�ateur
	 */
	public void setCreateur(String createur);

    /**
     * Affecte de la date du changement (audit).
     *
     * @param dateEvenement Valeur de la date d'�v�nement (yyyy-MM-dd).
     */
    public void setDateChangement(Timestamp dateChangement);

    /**
     * Affecte l'auteur du changement (pour l'audit).
     *
     * @param changePar Valeur changePar.
     */
    public void setChangePar(String changePar);

	/**
	 * Affecte la date de cr�ation.
	 *
	 * @param dateCreation Valeur de la date de cr�ation.
	 */
	public void setDateCreation(Timestamp dateCreation);
	
	/**
	 * Affecte la date de fin d'emploi.
	 *
	 * @param dateFinEmploi Valeur de la date de fin d'emploi.
	 */
	public void setDateFinEmploi(Timestamp dateFinEmploi);
	
	// g�n�rer une cle unique compos� de la cl� et du site
	public Object getCleUnique();
	
	// Ajout d'une adresse dans la liste des adresses de la soci�t�
	public void addAdresse(Adresse adresse);	
	
	public Boolean isNASCanadien();
	
	public void setNASCanadien(Boolean canadien);
	
	public abstract boolean isPossedeCle();

	/** 
	 * Indique si la donn�e provient de l'audit des changements
	 * @return
	 */
	public String getAudit();

	/**
	 * @param string
	 */
	public void setAudit(String audit);

	//Indique si la donn�e vient de RDD
	public boolean isIndicateurRdd();
	
	public void setIndicateurRdd(boolean indicateurRdd);

    /**
     * Affecte de la date de fin d'enqu�te du sujet.
     *
     * @param dateEvenement Valeur de la date d'�v�nement (yyyy-MM-dd).
     */
    public void setDateFinEnquete(Timestamp dateChangement);

    /**
     * Affecte le type d'�ge.
     *
     * @param typeLien Valeur num�rique du type d'�ge.
     */
    public void setTypeAge(long typeAge);
	
}