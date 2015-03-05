package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

/**
 * D�finit la signature des m�thodes des diff�rentes valeurs relatives au
 * formulatire de consultation d'une soci�t�.
 *
 * @author $Author: mdemers $
 * @version $Revision: 1.10 $, $Date: 2002/03/12 23:51:52 $
 */
public interface SocieteHtmlForm {


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
     * @return String Valeur entite en caract�re.
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
     * @return String Valeur de la classe.
     */
    public String getClasse();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur num�rique de date.
     */
    public String getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur num�rique de l'auteur.
     */
    public String getChangePar();

    /**
     * Retourne la date de fondation.
     *
     * @return String Valeur de la date de fondation en caract�re.
     */
    public String getDateDeFondation();

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
     * @return String Valeur du statut en caract�re.
     */
    public String getStatut();

    /**
     * Retourne la langue.
     *
     * @return String Valeur de la langue en caract�re.
     */
    public String getLangue();

    /**
     * Retourne la s�v�rit�.
     *
     * @return String Valeur de la s�v�rit� en caract�re.
     */
    public String getSeverite();

    /**
     * Retourne la s�v�rit� du casino.
     *
     * @return String Valeur de la s�v�rit� en caract�re.
     */
    public String getSeveriteCasino();

    /**
     * Retourne la confidentialit�.
     *
     * @return String Valeur de la confidentialit� en caract�re.
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
	 * Retourne la date de liaison.
	 *
	 * @return String date de liaison
	 */
	public String getLienDateCreation();

    /**
     * Retourne le r�le.
     *
     * @return String Valeur du r�le en caract�re.
     */
    public String getRole();

    /**
     * Retourne le type de lien.
     *
     * @return String Valeur du type de lien en caract�re.
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
     * Retourne les narrations associ�es.
     *
     * @return Collection Valeur de la collection de narrations.
     */
    public Collection getNarrations();

    /**
     * Retourne les dossiers associ�s.
     *
     * @return Collection Valeur de la collection de dossiers.
     */
    public Collection getDossiers();

    /**
     * Retourne les sujets associ�s.
     *
     * @return Collection Valeur de la collection de sujets.
     */
    public Collection getSujets();

    /**
     * Retourne les societes associ�es.
     *
     * @return Collection Valeur de la collection de soci�t�s.
     */
    public Collection getSocietes();

    /**
     * Retourne les adresses associ�es.
     *
     * @return Collection Valeur de la collection d'adresse.
     */
    public Collection getAdresses();

    /**
     * Retourne les  photos associ�es.
     *
     * @return Collection Valeur de la collection de photos.
     */
    public Collection getPhotos();

    /**
     * Retourne les  v�hicules associ�s.
     *
     * @return Collection Valeur de la collection de v�hicules.
     */
    public Collection getVehicules();


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caract�re.
     */
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(String site);

    /**
     * Affecte une entit�.
     *
     * @param site Valeur entite en caract�re.
     */
    public void setEntite(String entite);

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
     * @param classe Valeur de la classe en caract�re.
     */
    public void setClasse(String classe);

    /**
     * Affecte une date de fondation.
     *
     * @param dateDeFondation Valeur de la date de fondation en caract�re.
     */
    public void setDateDeFondation(String dateDeFondation);

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
     * @param statut Valeur du statut en caract�re.
     */
    public void setStatut(String statut);

    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue en caract�re.
     */
    public void setLangue(String langue);

    /**
     * Affecte une s�v�rit�.
     *
     * @param severite Valeur de la s�v�rit� en caract�re.
     */
    public void setSeverite(String severite);

    /**
     * Affecte une s�v�rit�.
     *
     * @param severite Valeur de la s�v�rit� en caract�re.
     */
    public void setSeveriteCasino(String severiteCasino);

    /**
     * Affecte la confidentialit�
     *
     * @param confidentialite Valeur de la confidentialit� en caract�res.
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
     * @param confirmationMotPasse Valeur de la confirmation du mot de passe en caract�re.
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
	 * Affecte une date de liaison.
	 *
	 * @param lienDateCreation Valeur de la date de liaison en caract�re.
	 */
	public void setLienDateCreation(String lienDateCreation);

    /**
     * Affecte un r�le.
     *
     * @param role Valeur du r�le en caract�re.
     */
    public void setRole(String role);

    /**
     * Affecte un type de lien.
     *
     * @param typeLien Valeur du type de lien en caract�re.
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
	 * Retourne si le d�taillant RDD (R�seau des d�taillants) est actif
	 * @return
	 */
	public boolean isActif();

	/**
	 * @param string
	 */
	public void setActif(boolean actif);
	
	/**
	 * Retourne la date d'inactivation du d�taillant dans RDD
	 * @return
	 */
	public String getDateInactif();

	/**
	 * @param string
	 */
	public void setDateInactif(String dateInactif);
	
	/**
	 * Retourne la raison d'inactivation du d�taillant dans RDD
	 * @return
	 */
	public String getCommentaire();

	/**
	 * @param string
	 */
	public void setCommentaire(String commentaire);
	
	/**
	 * Retourne si le d�taillant provient du syst�me RDD (R�seau des d�taillants)
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
	 * Retourne la m�thode d'�chantillonnage RDD
	 * @return
	 */
	public String getEchantillonnage();

	/**
	 * @param string
	 */
	public void setEchantillonnage(String echantillonnage);

	
}
