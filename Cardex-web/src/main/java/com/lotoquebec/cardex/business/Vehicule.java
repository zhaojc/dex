package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.EntiteCardex;

/**
 * Les véhicules englobent toutes les informations relatives à
 * un véhicule motorisé.
 *
 * @author $Author: pcaron $
 * @version $Revison: $1.0, $Date: 2002/02/28 23:46:46 $
 */
public interface Vehicule extends EntiteCardex{


    // Getters


    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site.
     */
    public long getSite();

    /**
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro de dossier en caractère.
     */
    public String getNumeroDossier();

    /**
     * Retourne le site du dossier.
     *
     * @return String Valeur du site du dossier en caractère.
     */
    public String getSiteDossier();

    /**
     * Retourne la marque.
     *
     * @return long Valeur de la marque.
     */
    public long getMarque();

    /**
     * Champ utilitaire pour retrouver un modèle.
     *
     * @return String Valeur du modèle en caractère.
     */
    public long getModeleMarque();

    /**
     * Retourne le modèle.
     *
     * @return long Valeur du modèle.
     */
    public long getModele();

    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caractère.
     */
    public String getImmatriculation();

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province.
     */
    public String getProvince();

    /**
     * Retourne l'année.
     *
     * @return String Valeur de l'année en caractère.
     */
    public String getAnnee();

    /**
     * Retourne la vignette.
     *
     * @return String Valeur de la vignette en caractère.
     */
    public String getVignette();

    /**
     * Retourne la date d'expiration de la vignette.
     *
     * @return Timestamp Valeur de la date d'expiration de la vignette
     * (yyyy-MM-dd).
     */
    public Timestamp getDateExpirationVignette();

    /**
     * Retourne le numéro de série.
     *
     * @return String Valeur du numéro de série en caractère.
     */
    public String getNumeroSerie();

    /**
     * Retourne l'assurance.
     *
     * @return String Valeur de l'assurance en caractère.
     */
    public String getAssurance();

    /**
     * Retourne la date d'expiration de l'assurance.
     *
     * @return Timestamp Valeur de la date d'expiration de l'assurance
     * (yyyy-MM-dd).
     */
    public Timestamp getDateExpirationAssurance();

    /**
     * Retourne la police.
     *
     * @return String Valeur de la police en caractère.
     */
    public String getPolice();

    /**
     * Retourne la confidentialité.
     *
     * @return long Valeur de la confidentialité.
     */
    public long getConfidentialite();

    /**
     * Retourne le mot de passe.
     *
     * @return String Valeur du mot de passe en caractère.
     */
    public String getMotPasse();

    /**
     * Retourne le créateur de la fiche Véhicule.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur();

    /**
     * Retourne la date de création de la fiche Véhicule
     *
     * @return Timestamp Valeur de création.
     * (yyyy-MM-dd).
     */
    public Timestamp getDateCreation();

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
     * @return long Valeur du rôle.
     */
    public long getRole();

    /**
     * Retourne le type de lien.
     *
     * @return long Valeur du type de lien.
     */
    public long getTypeLien();

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
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte un site du dossier.
     *
     * @param siteDossier Valeur du site du dossier en caractère.
     */
    public void setSiteDossier(String siteDossier);

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque.
     */
    public void setMarque(long marque);

    /**
     * Affecte un modèle.
     *
     * @param modele Valeur du modèle.
     */
    public void setModele(long modele);

    /**
     * Champ utilitaire.
     *
     * @param modele Valeur du modèle en caractère.
     */
    public void setModeleMarque(long modeleMarque);

    /**
     * Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caractère.
     */
    public void setImmatriculation(String immatriculation);

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province.
     */
    public void setProvince(String province);

    /**
     * Affecte une année.
     *
     * @param annee Valeur de l'année en caractère.
     */
    public void setAnnee(String annee);

    /**
     * Affecte une vignette.
     *
     * @param vignette Valeur de la vignette en caractère.
     */
    public void setVignette(String vignette);

    /**
     * Affecte une date d'expiration de la vignette.
     *
     * @param dateExpirationVignette Valeur de la date d'expiration de la
     * vignette (yyyy-MM-dd).
     */
    public void setDateExpirationVignette(Timestamp dateExpirationVignette);

    /**
     * Affecte un numéro de série.
     *
     * @param numeroSerie Valeur du numéro de série en caractère.
     */
    public void setNumeroSerie(String numeroSerie);

    /**
     * Affecte une assurance.
     *
     * @param assurance Valeur de l'assurance en caractère.
     */
    public void setAssurance(String assurance);

    /**
     * Affecte une date d'expiration de l'assurance.
     *
     * @param dateExpirationAssurance Valeur de la date d'expiration de
     * l'assurance (yyyy-MM-dd).
     */
    public void setDateExpirationAssurance(Timestamp dateExpirationAssurance);

    /**
     * Affecte une police.
     *
     * @param police Valeur de la police en caractère.
     */
    public void setPolice(String police);

    /**
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur de la confidentialité.
     */
    public void setConfidentialite(long confidentialite);

    /**
     * Affecte un mot de passe.
     *
     * @param motPasse Valeur du mot de passe en caractère.
     */
    public void setMotPasse(String motPasse);

    /**
     * Affecte un créateur.
     *
     * @param createur Valeur du créateur en caractère.
     */
    public void setCreateur(String createur);

    /**
     * Affecte une date de création de la fiche Véhicule.
     *
     * @param dateCreation Valeur de la date d'expiration de la
     * vignette (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation);

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
     * @param role Valeur du type de lien.
     */
    public void setTypeLien(long typeLien);

	/**
	 * @return
	 */
	public String getLienCreateur();

	/**
	 * @param string
	 */
	public void setLienCreateur(String lienCreateur);

	public long getEntite();

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
     * Retourne la clé de la province.
     *
     */
    public long getCleProvince();

    /**
     * Affecte la clé de la province.
     *
     * @param cleProvince Valeur cleProvince.
     */
    public void setCleProvince(long cleProvince);

    /**
     * Retourne le Commentaire.
     *
     */
    public String getCommentaire();

    /**
     * Affecte le Commentaire
     *
     * @param cleProvince Valeur cleProvince.
     */
    public void setCommentaire(String commentaire);
    
}