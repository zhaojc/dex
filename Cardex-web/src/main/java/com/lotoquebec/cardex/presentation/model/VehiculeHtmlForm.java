package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;

/**
 * D�finit la signature des m�thodes des diff�rentes valeurs relatives au
 * formulaire de consultation d'un v�hicule.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.11 $, $Date: 2002/04/10 15:53:10 $
 */
public interface VehiculeHtmlForm {


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
     * Retourne entite.
     *
     * @return String Valeur de entite en caract�re.
     */
    public String getEntite();

    /**
     * Retourne le num�ro de dossier.
     *
     * @return String Valeur du num�ro de dossier en caract�re.
     */
    public String getNumeroDossier();

    /**
     * Retourne le site du dossier.
     *
     * @return String Valeur du site du dossier en caract�re.
     */
    public String getSiteDossier();

    /**
     * Retourne la marque.
     *
     * @return String Valeur de la marque en caract�re.
     */
    public String getMarque();

    /**
     * Retourne le modele.
     *
     * @return String Valeur du modele en caract�re.
     */
    public String getModele();

    /**
     * Champ utilitaire pour retrouver un mod�le.
     *
     * @return String Valeur du mod�le en caract�re.
     */
    public String getModeleMarque();

    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caract�re.
     */
    public String getImmatriculation();

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province en caract�re.
     */
    public String getProvince();

    /**
     * Retourne l'annee.
     *
     * @return String Valeur de l'annee en caract�re.
     */
    public String getAnnee();

    /**
     * Retourne la vignette.
     *
     * @return String Valeur de la vignette en caract�re.
     */
    public String getVignette();

    /**
     * Retourne la date d'expiration de la vignette.
     *
     * @return String Valeur de la date d'expiration de la vignette en
     * caract�re.
     */
    public String getDateExpirationVignette();

    /**
     * Retourne le num�ro de s�rie.
     *
     * @return String Valeur du num�ro de s�rie en caract�re.
     */
    public String getNumeroSerie();

    /**
     * Retourne la date d'expiration de l'assurance.
     *
     * @return String Valeur de la date d'expiration de l'assurance en
     * caract�re.
     */
    public String getDateExpirationAssurance();

    /**
     * Retourne la police.
     *
     * @return String Valeur de la police en caract�re.
     */
    public String getPolice();

    /**
     * Retourne la confidentialit�.
     *
     * @return String Valeur de la confidentialit� en caract�re.
     */
    public String getConfidentialite();

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
     * Retourne le mot de passe.
     *
     * @return String Valeur du mot de passe en caract�re.
     */
    public String getMotPasse();

    /**
     * Retourne la confirmation du mot de passe.
     *
     * @return String Valeur du mot de la confirmation du passe en caract�re.
     */
    public String getConfirmationMotPasse();

    /**
     * Retourne le cr�ateur.
     *
     * @return String Valeur du cr�ateur en caract�re.
     */
    public String getCreateur();

    /**
     * Retourne la date de cr�ation.
     *
     * @return String Valeur de la date de cr�ation en caract�re.
     */
    public String getDateCreation();

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
     * Retourne les particularit�s associ�es.
     *
     * @return Collection Valeur de la collection de particularit�s.
     */
    public ParticularitesHtmlForm getParticularites();

    /**
     * Retourne les sujets associ�s.
     *
     * @return Collection Valeur de la collection de sujets.
     */
    public Collection getSujets();

    /**
     * Retourne les soci�t�s associ�es.
     *
     * @return Collection Valeur de la collection de soci�t�s.
     */
    public Collection getSocietes();

    /**
     * Retourne les photos associ�s.
     *
     * @return Collection Valeur de la collection de photos.
     */
    public Collection getPhotos();

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
     * Affecte entite.
     *
     * @param site Valeur entite en caract�re.
     */
    public void setEntite(String entite);

    /**
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte un site du dossier.
     *
     * @param siteDossier Valeur du site du dossier en caract�re.
     */
    public void setSiteDossier(String siteDossier);

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque en caract�re.
     */
    public void setMarque(String marque);

    /**
     * Affecte un modele.
     *
     * @param modele Valeur du modele en caract�re.
     */
    public void setModele(String modele);

    /**
     * Champ utilitaire.
     *
     * @param modele Valeur du mod�le en caract�re.
     */
    public void setModeleMarque(String modeleMarque);

    /**
     * Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caract�re.
     */
    public void setImmatriculation(String immatriculation);

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province en caract�re.
     */
    public void setProvince(String province);

    /**
     * Affecte une annee.
     *
     * @param annee Valeur de l'annee en caract�re.
     */
    public void setAnnee(String annee);

    /**
     * Affecte une vignette.
     *
     * @param vignette Valeur de la vignette en caract�re.
     */
    public void setVignette(String vignette);

    /**
     * Affecte une date d'expiration de la vignette.
     *
     * @param dateExpirationVignette Valeur de la date d'expiration de la
     * vignette en caract�re.
     */
    public void setDateExpirationVignette(String dateExpirationVignette);

    /**
     * Affecte un num�ro de s�rie.
     *
     * @param numeroSerie Valeur du num�ro de s�rie en caract�re.
     */
    public void setNumeroSerie(String numeroSerie);

    /**
     * Affecte une date d'expiration de l'assurance.
     *
     * @param dateExpirationAssurance Valeur de la date d'expiration de
     * l'assurance en caract�re.
     */
    public void setDateExpirationAssurance(String dateExpirationAssurance);

    /**
     * Affecte une police.
     *
     * @param police Valeur de la police en caract�re.
     */
    public void setPolice(String police);

    /**
     * Affecte une confidentialit�.
     *
     * @param confidentialite Valeur de la confidentialit� en caract�re.
     */
    public void setConfidentialite(String confidentialite);

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
     * Affecte un cr�ateur.
     *
     * @param createur Valeur du cr�ateur en caract�re.
     */
    public void setCreateur(String createur);

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation en caract�re.
     */
    public void setDateCreation(String dateCreation);

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
     * Retourne la cl� de la province.
     *
     */
    public String getCleProvince();

    /**
     * Affecte la cl� de la province.
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
     * Ajoute une narration associ�e.
     *
     * @param narration Valeur de la narration � ajouter.
     */
    public void addNarration(NarrationHtmlForm narration);

    /**
     * Ajoute un dossier associ�.
     *
     * @param dossier Valeur du dossier � ajouter.
     */
    public void addDossier(DossierHtmlForm dossier);

    /**
     * Ajoute une particularit� associ�e.
     *
     * @param particularite Valeur de la particularit� � ajouter.
     */
//    public void addParticularite(ParticularitesHtmlForm particularite);

    /**
     * Ajoute un sujet associ�.
     *
     * @param Collection Valeur du sujet � ajouter.
     */
    public void addSujet(SujetHtmlForm sujet);

    /**
     * Ajoute une soci�t�s associ�e.
     *
     * @param societe Valeur de la soci�t� � ajouter.
     */
    public void addSociete(SocieteHtmlForm societe);

    /**
     * Ajoute une photo associ�e.
     *
     * @param photo Valeur de la photo � ajouter.
     */
    public void addPhoto(Collection photo);

	/**
	 * @param subject
	 */
	public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException;
}