package com.lotoquebec.cardexCommun.business;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à une adresse.
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
     * @param groupesChoisis Valeur de la liste de chaîne de caractère
     * des groupes.
     */
    public void addGroupesChoisis(String groupe);

    /**
     * Retourne une collection de groupes.
     *
     * @return String [] Valeur de la liste de chaîne de caractère de
     * groupes.
     */
    public Collection getGroupesChoisis();

    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle en caractère.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site en caractère.
     */
    public long getSite();

    /**
     * Retourne le nom.
     *
     * @return String Valeur du nom en caractère.
     */
    public String getNom();

    /**
     * Retourne le "prenom, nom"
     * @return
     */
    public String getNomComplet();
    
    /**
     * Retourne le prénom
     *
     * @return String Valeur du prénom en caractère.
     */
    public String getPrenom();

    /**
     * Retourne le code de l'intervenant
     *
     * @return String Valeur du code en caractère.
     */
    public String getCode();


    /**
     * Retourne la langue d'utilisation.
     *
     * @return long Valeur de la langue en caractère.
     */
    public long getLangue();


    /**
     * Retourne le statut (actif ou inactif).
     *
     * @return long Valeur du statut en caractère.
     */
    public long getStatut();

    /**
     * Retourne la date de création.
     *
     * @return Timestamp Valeur de la date de création en caractère.
     */
    public Timestamp getDateCreation();

    /**
     * Retourne le créateur de la fiche de l'intervenant.
     *
     * @return String Valeur du code du créateur en caractère.
     */
    public String getCreateur();
    
    /**
     * Retourne le code du modificateur d'une fiche Intervenant
     *
     * @return String Valeur du code en caractère.
     */

    public String getModificateur();

    /**
     * Retourne la date de modification.
     *
     * @return Timestamp Valeur de la date de modification en caractère.
     */

    public Timestamp getDateModification();

    /**
     * Retourne le mot de passe initial assigné à l'utilisateur.
     *
     * @return String Valeur du mot de passe en caractère.
     */

    public String getMotPasse();

    /**
     * Retourne le niveau de confidentialité.
     *
     * @return long Valeur de la confidentialité.
     */
    public long getConfidentialite();

    /**
     * Retourne le niveau hiérarchique de l'intervenant.
     *
     * @return long Valeur de la hierarchie.
     */
    public long getHierarchie();

    /**
     * Retourne le numéro de l'intervenant
     *
     * @return String Valeur du numéro
     */
    public String getNumero();

    /**
     * Retourne un commentaire.
     *
     * @return String Valeur du commentaire
     */
    public String getCommentaire();

    /**
     * Retourne l'adresse électronique de l'intervenant.
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
     * Retourne l'entité de l'intervenant.
     *
     * @return long Valeur du l'entité
     */
    public long getEntite();

    /**
     * Retourne le groupe de sécurité auquel appartient l'intervenant.
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
     * Retourne la description du niveau d'autorité de l'intervenant.
     *
     * @return String Valeur de l'autorité
     */
    public String getAutoriteDescription();

    /**
     * Retourne la description de la confidentialité de l'intervenant.
     *
     * @return String Valeur de confidentialité
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
     * @param cle Valeur de la cle en caractère.
     */
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(long site);

    /**
     * Affecte le nom.
     *
     * @param nom Valeur du nom en caractère.
     */
    public void setNom(String nom);

    /**
     * Affecte le prénom
     *
     * @param prenom Valeur du prénom en caractère.
     */
    public void setPrenom(String prenom);

    /**
     * Affecte un code d'intervenant.
     *
     * @param code Valeur du code en caractère.
     */
    public void setCode(String code);

    /**
     * Affecte le numéro
     *
     * @param numero Valeur du numero en caractère.
     */
    public void setNumero(String numero);

   /**
     * Affecte la confidentialité.
     *
     * @param confidentialite Valeur numérique de la confidentialité.
     */
    public void setConfidentialite(long confidentialite);

    /**
     * Affecte la hierarchie.
     *
     * @param hierarchie Valeur numérique de la hierarchie.
     */
    public void setHierarchie(long hierarchie);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caractère.
     */
    public void setStatut(long statut);

    /**
     * Affecte la langue.
     *
     * @param langue Valeur de la langue en caractère.
     */
    public void setLangue(long langue);

    /**
     * Affecte un commentaire.
     *
     * @param commentaire Valeur du commentaire en caractère.
     */
    public void setCommentaire(String commentaire);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caractère.
     */
    public void setModificateur(String modificateur);

    /**
     * Affecte un mot de passe.
     *
     * @param motPasse Valeur du mot de passe en caractère.
     */
    public void setMotPasse(String motPasse);

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création en caractère.
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caractère.
     */
    public void setDateModification(Timestamp dateModification);

    /**
     * Affecte le créateur.
     *
     * @param createur Valeur du créateur en caractère.
     */
    public void setCreateur(String createur);

    /**
     * Affecte l'adresse de courriel.
     *
     * @param courriel Valeur du courriel du site en caractère.
     */
    public void setCourriel(String courriel);

    /**
     * Affecte le DN
     *
     * @param dn Valeur du courriel du site en caractère.
     */
    public void setDN(String dn);

    /**
     * Affecte l'entité de l'intervenant.
     *
     * @return long Valeur du l'entité
     */
    public void setEntite(long entite);

    /**
     * Affecte le groupe de sécurité auquel appartient l'intervenant.
     *
     * @return String Valeur du groupe
     */
    public void setGroupe(String groupe);

	/**
	 * @return codeParent
	 */
	public String getCodeParent();


	/**
	 * @param codeParent codeParent à définir
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
	 * @param secteur secteur à définir
	 */
	public void setSecteur(long secteur);

    /**
     * Affecte le secteur auquel appartient l'intervenant.
     *
     * @return String Valeur du secteur
     */
    public void setSecteurDescription(String secteurDescription);

	/**
	 * @param sousSecteur sous-secteur à définir
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
     * Affecte l'autorité de l'intervenant.
     *
     * @return String Valeur autorite
     */
    public void setAutoriteDescription(String autoriteDescription);

    /**
     * Affecte la confidentialité de l'intervenant.
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
     * @return String Valeur numérique du type.
     */
    public String getGroupesIntervenants();

    /**
     * Affecte un groupe et un intervenant
     *
     * @param groupe Valeur numérique du groupe.
     */
    public void setGroupesIntervenants(String groupe);


}