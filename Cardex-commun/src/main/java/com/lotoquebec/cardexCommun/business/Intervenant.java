package com.lotoquebec.cardexCommun.business;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � une adresse.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.7 $, $Date: 2002/02/28 17:28:10 $
 */
public interface Intervenant {


    // Getters

    /**
     * Ajoute les groupes ClearTrust
     *
     * @param groupeCT Valeur du groupe.
     */
    public void addGroupesCT(String groupeCT);

    /**
     * Retourne la liste de groupes ClearTrust de l'intervenant
     *
     * @return Collection Valeur de la liste des groupes.
     */
    public Collection getGroupesCT();

    /**
     * Affecte une collection de groupes.
     *
     * @param groupesChoisis Valeur de la liste de cha�ne de caract�re
     * des groupes.
     */
    public void addGroupesChoisis(String groupe);

    /**
     * Retourne une collection de groupes.
     *
     * @return String [] Valeur de la liste de cha�ne de caract�re de
     * groupes.
     */
    public Collection getGroupesChoisis();

    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle en caract�re.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site en caract�re.
     */
    public long getSite();

    /**
     * Retourne le nom.
     *
     * @return String Valeur du nom en caract�re.
     */
    public String getNom();

    /**
     * Retourne le "prenom, nom"
     * @return
     */
    public String getNomComplet();
    
    /**
     * Retourne le pr�nom
     *
     * @return String Valeur du pr�nom en caract�re.
     */
    public String getPrenom();

    /**
     * Retourne le code de l'intervenant
     *
     * @return String Valeur du code en caract�re.
     */
    public String getCode();


    /**
     * Retourne la langue d'utilisation.
     *
     * @return long Valeur de la langue en caract�re.
     */
    public long getLangue();


    /**
     * Retourne le statut (actif ou inactif).
     *
     * @return long Valeur du statut en caract�re.
     */
    public long getStatut();

    /**
     * Retourne la date de cr�ation.
     *
     * @return Timestamp Valeur de la date de cr�ation en caract�re.
     */
    public Timestamp getDateCreation();

    /**
     * Retourne le cr�ateur de la fiche de l'intervenant.
     *
     * @return String Valeur du code du cr�ateur en caract�re.
     */
    public String getCreateur();
    
    /**
     * Retourne le code du modificateur d'une fiche Intervenant
     *
     * @return String Valeur du code en caract�re.
     */

    public String getModificateur();

    /**
     * Retourne la date de modification.
     *
     * @return Timestamp Valeur de la date de modification en caract�re.
     */

    public Timestamp getDateModification();

    /**
     * Retourne le mot de passe initial assign� � l'utilisateur.
     *
     * @return String Valeur du mot de passe en caract�re.
     */

    public String getMotPasse();

    /**
     * Retourne le niveau de confidentialit�.
     *
     * @return long Valeur de la confidentialit�.
     */
    public long getConfidentialite();

    /**
     * Retourne le niveau hi�rarchique de l'intervenant.
     *
     * @return long Valeur de la hierarchie.
     */
    public long getHierarchie();

    /**
     * Retourne le num�ro de l'intervenant
     *
     * @return String Valeur du num�ro
     */
    public String getNumero();

    /**
     * Retourne un commentaire.
     *
     * @return String Valeur du commentaire
     */
    public String getCommentaire();

    /**
     * Retourne l'adresse �lectronique de l'intervenant.
     *
     * @return String Valeur du courriel
     */
    public String getCourriel();
    
    /**
     * Retourne un DN (distinguished Name dans ClearTrust).
     *
     * @return String Valeur du DN
     */
    public String getDN();
    
    /**
     * Retourne l'entit� de l'intervenant.
     *
     * @return long Valeur du l'entit�
     */
    public long getEntite();

    /**
     * Retourne le groupe de s�curit� auquel appartient l'intervenant.
     *
     * @return String Valeur du groupe
     */
    public String getGroupe();

    /**
     * Retourne la description du secteur auquel appartient l'intervenant.
     *
     * @return String Valeur du groupe
     */
    public String getSecteurDescription();

    /**
     * Retourne la description du statut de l'intervenant.
     *
     * @return String Valeur du groupe
     */
    public String getStatutDescription();

    /**
     * Retourne la description du niveau d'autorit� de l'intervenant.
     *
     * @return String Valeur de l'autorit�
     */
    public String getAutoriteDescription();

    /**
     * Retourne la description de la confidentialit� de l'intervenant.
     *
     * @return String Valeur de confidentialit�
     */
    public String getConfidentialiteDescription();

    /**
     * Retourne la description du site de l'intervenant.
     *
     * @return String Valeur du site
     */
    public String getSiteDescription();

    /**
     * Retourne le gestionnaire de l'intervenant.
     *
     * @return String Valeur du code du gestionnaire.
     */
    public String getGestionnaire();

    
    
    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caract�re.
     */
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(long site);

    /**
     * Affecte le nom.
     *
     * @param nom Valeur du nom en caract�re.
     */
    public void setNom(String nom);

    /**
     * Affecte le pr�nom
     *
     * @param prenom Valeur du pr�nom en caract�re.
     */
    public void setPrenom(String prenom);

    /**
     * Affecte un code d'intervenant.
     *
     * @param code Valeur du code en caract�re.
     */
    public void setCode(String code);

    /**
     * Affecte le num�ro
     *
     * @param numero Valeur du numero en caract�re.
     */
    public void setNumero(String numero);

   /**
     * Affecte la confidentialit�.
     *
     * @param confidentialite Valeur num�rique de la confidentialit�.
     */
    public void setConfidentialite(long confidentialite);

    /**
     * Affecte la hierarchie.
     *
     * @param hierarchie Valeur num�rique de la hierarchie.
     */
    public void setHierarchie(long hierarchie);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caract�re.
     */
    public void setStatut(long statut);

    /**
     * Affecte la langue.
     *
     * @param langue Valeur de la langue en caract�re.
     */
    public void setLangue(long langue);

    /**
     * Affecte un commentaire.
     *
     * @param commentaire Valeur du commentaire en caract�re.
     */
    public void setCommentaire(String commentaire);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caract�re.
     */
    public void setModificateur(String modificateur);

    /**
     * Affecte un mot de passe.
     *
     * @param motPasse Valeur du mot de passe en caract�re.
     */
    public void setMotPasse(String motPasse);

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation en caract�re.
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caract�re.
     */
    public void setDateModification(Timestamp dateModification);

    /**
     * Affecte le cr�ateur.
     *
     * @param createur Valeur du cr�ateur en caract�re.
     */
    public void setCreateur(String createur);

    /**
     * Affecte l'adresse de courriel.
     *
     * @param courriel Valeur du courriel du site en caract�re.
     */
    public void setCourriel(String courriel);

    /**
     * Affecte le DN
     *
     * @param dn Valeur du courriel du site en caract�re.
     */
    public void setDN(String dn);

    /**
     * Affecte l'entit� de l'intervenant.
     *
     * @return long Valeur du l'entit�
     */
    public void setEntite(long entite);

    /**
     * Affecte le groupe de s�curit� auquel appartient l'intervenant.
     *
     * @return String Valeur du groupe
     */
    public void setGroupe(String groupe);

	/**
	 * @return codeParent
	 */
	public String getCodeParent();


	/**
	 * @param codeParent codeParent � d�finir
	 */
	public void setCodeParent(String codeParent);


	/**
	 * @return secteur
	 */
	public long getSecteur();

	/**
	 * @return sous-secteur
	 */
	public long getSousSecteur();


	/**
	 * @param secteur secteur � d�finir
	 */
	public void setSecteur(long secteur);

    /**
     * Affecte le secteur auquel appartient l'intervenant.
     *
     * @return String Valeur du secteur
     */
    public void setSecteurDescription(String secteurDescription);

	/**
	 * @param sousSecteur sous-secteur � d�finir
	 */
	public void setSousSecteur(long sousSecteur);

    /**
     * Affecte le sous-secteur auquel appartient l'intervenant.
     *
     * @return String Valeur du sous-secteur
     */
    public void setSousSecteurDescription(String sousSecteurDescription);

    /**
     * Affecte le statut de l'intervenant.
     *
     * @return String Valeur du statut
     */
    public void setStatutDescription(String statutDescription);

    /**
     * Affecte l'autorit� de l'intervenant.
     *
     * @return String Valeur autorite
     */
    public void setAutoriteDescription(String autoriteDescription);

    /**
     * Affecte la confidentialit� de l'intervenant.
     *
     * @return String Valeur confidentialite
     */
    public void setConfidentialiteDescription(String confidentialiteDescription);
        
    /**
     * Affecte le site de l'intervenant.
     *
     * @return String Valeur site
     */
    public void setSiteDescription(String siteDescription);

	public Timestamp getDateChangement();

	public void setDateChangement(Timestamp parse);

    /**
     * Affecte le gestionnaire de l'intervenant.
     *
     * @return String Valeur du code du gestionnaire
     */
    public void setGestionnaire(String gestionnaire);

    /**
     * Retourne le groupe et l'intervenant.
     *
     * @return String Valeur num�rique du type.
     */
    public String getGroupesIntervenants();

    /**
     * Affecte un groupe et un intervenant
     *
     * @param groupe Valeur num�rique du groupe.
     */
    public void setGroupesIntervenants(String groupe);


}