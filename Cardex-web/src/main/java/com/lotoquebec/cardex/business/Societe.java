package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.EntiteCardex;
import com.lotoquebec.cardexCommun.business.LiaisonEntiteCardex;

/**
 * Les soci�t�s contiennent des informations ayant trait � des entreprises,
 * de nature commerciale ou autres, ainsi qu'� des regroupements d'individus.
 *
 * @author $Author: mlibersan $
 * @version $Revison: $1.0, $Date: 2002/04/11 19:20:26 $
 */
public interface Societe extends LiaisonEntiteCardex{


    // Getters


    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle.
     */
    public long getCle();
    
    /**
     * Retourne une composition de la cle et du site
     * @return
     */
    public String getCleUnique();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site.
     */
    public long getSite();

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
     * Retourne le nom de r�f�rence.
     *
     * @return String Valeur du nom de r�f�rence en caract�re.
     */
    public String getReferenceNom();

    /**
     * Retourne le pr�nom de r�f�rence.
     *
     * @return String Valeur du pr�nom de r�f�rence en caract�re.
     */
    public String getReferencePrenom();

    /**
     * Retourne la raison d'�tre.
     *
     * @return String Valeur de la raison d'�tre en caract�re.
     */
    public String getRaisonEtre();

    /**
     * Retourne la classe.
     *
     * @return long Valeur de la classe.
     */
    public long getClasse();

    /**
     * Retourne la date de fondation.
     *
     * @return Timestamp Valeur de la date de fondation (yyyy-MM-dd).
     */
    public Timestamp getDateDeFondation();

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
     * Retourne le statut.
     *
     * @return long Valeur du statut.
     */
    public long getStatut();

    /**
     * Retourne la langue.
     *
     * @return long Valeur de la langue.
     */
    public long getLangue();

    /**
     * Retourne la s�v�rit�.
     *
     * @return long Valeur de la s�v�rit�.
     */
    public long getSeverite();

    /**
     * Retourne la s�v�rit� du Casino.
     *
     * @return long Valeur de la s�v�rit�.
     */
    public long getSeveriteCasino();

    /**
     * Retourne le confidentialite.
     *
     * @return long Valeur du confidentialite.
     */
    public long getConfidentialite();

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
     * @return long Valeur du lien.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite();

	/**
	 * Retourne la date de cr�ation de la liaison.
	 *
	 * @return Timestamp Valeur de la date de liaison (yyy-MM-dd).
	 */
	public Timestamp getLienDateCreation();

    /**
     * Retourne le r�le.
     *
     * @return long Valeur du lien du r�le.
     */
    public long getRole();

    /**
     * Retourne le type de lien.
     *
     * @return long Valeur du lien du type de lien.
     */
    public long getTypeLien();

    /**
     * Retourne le cr�ateur de la soci�t�.
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
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur num�rique de l'auteur.
     */
    public String getChangePar();

    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle.
     */
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site.
     */
    public void setSite(long site);

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
     * Affecte un nom de r�f�rence.
     *
     * @param referenceNom Valeur du nom de r�f�rence en caract�re.
     */
    public void setReferenceNom(String referenceNom);

    /**
     * Affecte un pr�nom de r�f�rence.
     *
     * @param referencePrenom Valeur du pr�nom de r�f�rence en caract�re.
     */
    public void setReferencePrenom(String referencePrenom);

    /**
     * Affecte une raison d'�tre.
     *
     * @param raisonEtre Valeur de la raison d'�tre en caract�re.
     */
    public void setRaisonEtre(String raisonEtre);

    /**
     * Affecte une classe.
     *
     * @param classe Valeur de la classe.
     */
    public void setClasse(long classe);

    /**
     * Affecte une date de fondation.
     *
     * @param dateDeFondation Valeur de la date de fondation (yyyy-MM-dd).
     */
    public void setDateDeFondation(Timestamp dateDeFondation);

    /**
     * Affecte une premi�re r�f�rence.
     *
     * @param reference1 Valeur de la premi�re r�f�rence en caract�re.
     */
    public void setReference1(String reference1);

    /**
     * Affecte une deuxi�me r�f�rence.
     *
     * @param reference2 Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public void setReference2(String reference2);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut.
     */
    public void setStatut(long statut);

    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue.
     */
    public void setLangue(long langue);

    /**
     * Affecte une s�v�rit�.
     *
     * @param severite Valeur de la s�v�rit�.
     */
    public void setSeverite(long severite);

    /**
     * Affecte une s�v�rit�.
     *
     * @param severite Valeur de la s�v�rit�.
     */
    public void setSeveriteCasino(long severiteCasino);

    /**
     * Affecte un confidentialite.
     *
     * @param confidentialite Valeur du confidentialite.
     */
    public void setConfidentialite(long confidentialite);

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

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite);

	/**
	 * Affecte une date de liaison.
	 *
	 * @param dateNaissance Valeur de la date de liaison (yyyy-MM-dd).
	 */
	public void setLienDateCreation(Timestamp lienDateCreation);

    /**
     * Affecte un r�le.
     *
     * @param role Valeur du r�le.
     */
    public void setRole(long role);

    /**
     * Affecte un type de lien.
     *
     * @param typeLien Valeur du type de lien.
     */
    public void setTypeLien(long typeLien);

    /**
     * Affecte le cr�ateur de la soci�t�.
     *
     * @param createur Code du cr�ateur
     */
    public void setCreateur(String createur);

    /**
     * Affecte la date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation.
     */
    public void setDateCreation(Timestamp dateCreation);

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
	 * @return
	 */
	public String getLienCreateur();

	/**
	 * @param string
	 */
	public void setLienCreateur(String lienCreateur);

	// Ajout d'une adresse dans la liste des adresses de la soci�t�
	public void addAdresse(Adresse adresse);

	public long getEntite();

	/** 
	 * Indique si la donn�e provient de l'audit des changements
	 * @return
	 */
	public String getAudit();

	/**
	 * @param string
	 */
	public void setAudit(String audit);

	/**
	 * Retourne le centre r�gional
	 * @return
	 */
	public String getCentreRegional();

	/**
	 * @param String
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
	public void setDistrict(String District);

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
	 * Retourne si le d�taillant RDD (R�seau des d�taillants) est actif
	 * @return
	 */
	public boolean isActif();

	/**
	 * @param Boolean
	 */
	public void setActif(boolean actif);
	
	/**
	 * Retourne la date d'inactivation du d�taillant dans RDD
	 * @return
	 */
	public Timestamp getDateInactif();

	/**
	 * @param string
	 */
	public void setDateInactif(Timestamp dateInactif);
	
	/**
	 * Retourne la raison d'inactivation du d�taillant dans RDD
	 * @return
	 */
	public String getCommentaire();

	/**
	 * @param string
	 */
	public void setCommentaire(String commentaire);
	
	//Indique si la donn�e vient de RDD
	public boolean isIndicateurRdd();

	/**
	 * @param Boolean
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
	 * Retourne la description du centre r�gional
	 * @return
	 */
	public String getCentreRegionalDescription();

	/**
	 * @param string
	 */
	public void setCentreRegionalDescription(String centreRegionalDescription);

	/**
	 * Retourne la raison de la d�sactivation
	 * @return
	 */
	public String getRaisonDesactivation();

	/**
	 * @param string
	 */
	public void setRaisonDesactivation(String raisonDesactivation);

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
	 * Retourne la m�thode d'�chantillonnage RDD
	 * @return
	 */
	public long getEchantillonnage();

	/**
	 * @param string
	 */
	public void setEchantillonnage(long echantillonnage);

}