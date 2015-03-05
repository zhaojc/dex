package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;

/**
 * Définit la signature des méthodes des différentes valeurs relatives au
 * formulaire de consultation d'un véhicule.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.11 $, $Date: 2002/04/10 15:53:10 $
 */
public interface VehiculeHtmlForm {


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
     * Retourne entite.
     *
     * @return String Valeur de entite en caractère.
     */
    public String getEntite();

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
     * @return String Valeur de la marque en caractère.
     */
    public String getMarque();

    /**
     * Retourne le modele.
     *
     * @return String Valeur du modele en caractère.
     */
    public String getModele();

    /**
     * Champ utilitaire pour retrouver un modèle.
     *
     * @return String Valeur du modèle en caractère.
     */
    public String getModeleMarque();

    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caractère.
     */
    public String getImmatriculation();

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province en caractère.
     */
    public String getProvince();

    /**
     * Retourne l'annee.
     *
     * @return String Valeur de l'annee en caractère.
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
     * @return String Valeur de la date d'expiration de la vignette en
     * caractère.
     */
    public String getDateExpirationVignette();

    /**
     * Retourne le numéro de série.
     *
     * @return String Valeur du numéro de série en caractère.
     */
    public String getNumeroSerie();

    /**
     * Retourne la date d'expiration de l'assurance.
     *
     * @return String Valeur de la date d'expiration de l'assurance en
     * caractère.
     */
    public String getDateExpirationAssurance();

    /**
     * Retourne la police.
     *
     * @return String Valeur de la police en caractère.
     */
    public String getPolice();

    /**
     * Retourne la confidentialité.
     *
     * @return String Valeur de la confidentialité en caractère.
     */
    public String getConfidentialite();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur numérique de date.
     */
    public String getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur numérique de l'auteur.
     */
    public String getChangePar();

    /**
     * Retourne le mot de passe.
     *
     * @return String Valeur du mot de passe en caractère.
     */
    public String getMotPasse();

    /**
     * Retourne la confirmation du mot de passe.
     *
     * @return String Valeur du mot de la confirmation du passe en caractère.
     */
    public String getConfirmationMotPasse();

    /**
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur();

    /**
     * Retourne la date de création.
     *
     * @return String Valeur de la date de création en caractère.
     */
    public String getDateCreation();

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
	 * Retourne la date de liaison.
	 *
	 * @return String date de liaison
	 */
	public String getLienDateCreation();

    /**
     * Retourne le rôle.
     *
     * @return String Valeur du rôle en caractère.
     */
    public String getRole();

    /**
     * Retourne le type de lien.
     *
     * @return String Valeur du type de lien en caractère.
     */
    public String getTypeLien();

    /**
     * Retourne les narrations associées.
     *
     * @return Collection Valeur de la collection de narrations.
     */
    public Collection getNarrations();

    /**
     * Retourne les dossiers associés.
     *
     * @return Collection Valeur de la collection de dossiers.
     */
    public Collection getDossiers();

    /**
     * Retourne les particularités associées.
     *
     * @return Collection Valeur de la collection de particularités.
     */
    public ParticularitesHtmlForm getParticularites();

    /**
     * Retourne les sujets associés.
     *
     * @return Collection Valeur de la collection de sujets.
     */
    public Collection getSujets();

    /**
     * Retourne les sociétés associées.
     *
     * @return Collection Valeur de la collection de sociétés.
     */
    public Collection getSocietes();

    /**
     * Retourne les photos associés.
     *
     * @return Collection Valeur de la collection de photos.
     */
    public Collection getPhotos();

    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caractère.
     */
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(String site);

    /**
     * Affecte entite.
     *
     * @param site Valeur entite en caractère.
     */
    public void setEntite(String entite);

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
     * @param marque Valeur de la marque en caractère.
     */
    public void setMarque(String marque);

    /**
     * Affecte un modele.
     *
     * @param modele Valeur du modele en caractère.
     */
    public void setModele(String modele);

    /**
     * Champ utilitaire.
     *
     * @param modele Valeur du modèle en caractère.
     */
    public void setModeleMarque(String modeleMarque);

    /**
     * Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caractère.
     */
    public void setImmatriculation(String immatriculation);

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province en caractère.
     */
    public void setProvince(String province);

    /**
     * Affecte une annee.
     *
     * @param annee Valeur de l'annee en caractère.
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
     * vignette en caractère.
     */
    public void setDateExpirationVignette(String dateExpirationVignette);

    /**
     * Affecte un numéro de série.
     *
     * @param numeroSerie Valeur du numéro de série en caractère.
     */
    public void setNumeroSerie(String numeroSerie);

    /**
     * Affecte une date d'expiration de l'assurance.
     *
     * @param dateExpirationAssurance Valeur de la date d'expiration de
     * l'assurance en caractère.
     */
    public void setDateExpirationAssurance(String dateExpirationAssurance);

    /**
     * Affecte une police.
     *
     * @param police Valeur de la police en caractère.
     */
    public void setPolice(String police);

    /**
     * Affecte une confidentialité.
     *
     * @param confidentialite Valeur de la confidentialité en caractère.
     */
    public void setConfidentialite(String confidentialite);

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
     * Affecte un créateur.
     *
     * @param createur Valeur du créateur en caractère.
     */
    public void setCreateur(String createur);

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création en caractère.
     */
    public void setDateCreation(String dateCreation);

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
	 * Affecte une date de liaison.
	 *
	 * @param lienDateCreation Valeur de la date de liaison en caractère.
	 */
	public void setLienDateCreation(String lienDateCreation);

    /**
     * Affecte un rôle.
     *
     * @param role Valeur du rôle en caractère.
     */
    public void setRole(String role);

    /**
     * Affecte un type de lien.
     *
     * @param typeLien Valeur du type de lien en caractère.
     */
    public void setTypeLien(String typeLien);

	/**
	 * @return
	 */
	public String getLienCreateur();

	/**
	 * @param string
	 */
	public void setLienCreateur(String lienCreateur);

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
     * Retourne la clé de la province.
     *
     */
    public String getCleProvince();

    /**
     * Affecte la clé de la province.
     *
     * @param cleProvince Valeur cleProvince.
     */
    public void setCleProvince(String cleProvince);

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

    /**
     * Ajoute une narration associée.
     *
     * @param narration Valeur de la narration à ajouter.
     */
    public void addNarration(NarrationHtmlForm narration);

    /**
     * Ajoute un dossier associé.
     *
     * @param dossier Valeur du dossier à ajouter.
     */
    public void addDossier(DossierHtmlForm dossier);

    /**
     * Ajoute une particularité associée.
     *
     * @param particularite Valeur de la particularité à ajouter.
     */
//    public void addParticularite(ParticularitesHtmlForm particularite);

    /**
     * Ajoute un sujet associé.
     *
     * @param Collection Valeur du sujet à ajouter.
     */
    public void addSujet(SujetHtmlForm sujet);

    /**
     * Ajoute une sociétés associée.
     *
     * @param societe Valeur de la société à ajouter.
     */
    public void addSociete(SocieteHtmlForm societe);

    /**
     * Ajoute une photo associée.
     *
     * @param photo Valeur de la photo à ajouter.
     */
    public void addPhoto(Collection photo);

	/**
	 * @param subject
	 */
	public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException;
}