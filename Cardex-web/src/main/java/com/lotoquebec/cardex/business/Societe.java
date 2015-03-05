package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.EntiteCardex;
import com.lotoquebec.cardexCommun.business.LiaisonEntiteCardex;

/**
 * Les sociétés contiennent des informations ayant trait à des entreprises,
 * de nature commerciale ou autres, ainsi qu'à des regroupements d'individus.
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
     * Retourne la sévérité.
     *
     * @return long Valeur de la sévérité.
     */
    public long getSeverite();

    /**
     * Retourne la sévérité du Casino.
     *
     * @return long Valeur de la sévérité.
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
     * @return String Valeur du mot de passe en caractère.
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
	 * Retourne la date de création de la liaison.
	 *
	 * @return Timestamp Valeur de la date de liaison (yyy-MM-dd).
	 */
	public Timestamp getLienDateCreation();

    /**
     * Retourne le rôle.
     *
     * @return long Valeur du lien du rôle.
     */
    public long getRole();

    /**
     * Retourne le type de lien.
     *
     * @return long Valeur du lien du type de lien.
     */
    public long getTypeLien();

    /**
     * Retourne le créateur de la société.
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
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur numérique de l'auteur.
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
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité.
     */
    public void setSeverite(long severite);

    /**
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité.
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
     * @param motPasse Valeur du mot de passe en caractère.
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
     * Affecte un rôle.
     *
     * @param role Valeur du rôle.
     */
    public void setRole(long role);

    /**
     * Affecte un type de lien.
     *
     * @param typeLien Valeur du type de lien.
     */
    public void setTypeLien(long typeLien);

    /**
     * Affecte le créateur de la société.
     *
     * @param createur Code du créateur
     */
    public void setCreateur(String createur);

    /**
     * Affecte la date de création.
     *
     * @param dateCreation Valeur de la date de création.
     */
    public void setDateCreation(Timestamp dateCreation);

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
	 * @return
	 */
	public String getLienCreateur();

	/**
	 * @param string
	 */
	public void setLienCreateur(String lienCreateur);

	// Ajout d'une adresse dans la liste des adresses de la société
	public void addAdresse(Adresse adresse);

	public long getEntite();

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
	 * Retourne si le détaillant RDD (Réseau des détaillants) est actif
	 * @return
	 */
	public boolean isActif();

	/**
	 * @param Boolean
	 */
	public void setActif(boolean actif);
	
	/**
	 * Retourne la date d'inactivation du détaillant dans RDD
	 * @return
	 */
	public Timestamp getDateInactif();

	/**
	 * @param string
	 */
	public void setDateInactif(Timestamp dateInactif);
	
	/**
	 * Retourne la raison d'inactivation du détaillant dans RDD
	 * @return
	 */
	public String getCommentaire();

	/**
	 * @param string
	 */
	public void setCommentaire(String commentaire);
	
	//Indique si la donnée vient de RDD
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
	 * Retourne la description du district
	 * @return
	 */
	public String getDistrictDescription();

	/**
	 * @param string
	 */
	public void setDistrictDescription(String districtDescription);
	
	/**
	 * Retourne la méthode d'échantillonnage RDD
	 * @return
	 */
	public long getEchantillonnage();

	/**
	 * @param string
	 */
	public void setEchantillonnage(long echantillonnage);

}