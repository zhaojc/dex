package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

import com.lotoquebec.cardexCommun.business.EntiteCardex;

/**
 * Les v�hicules englobent toutes les informations relatives �
 * un v�hicule motoris�.
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
     * @return long Valeur de la marque.
     */
    public long getMarque();

    /**
     * Champ utilitaire pour retrouver un mod�le.
     *
     * @return String Valeur du mod�le en caract�re.
     */
    public long getModeleMarque();

    /**
     * Retourne le mod�le.
     *
     * @return long Valeur du mod�le.
     */
    public long getModele();

    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caract�re.
     */
    public String getImmatriculation();

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province.
     */
    public String getProvince();

    /**
     * Retourne l'ann�e.
     *
     * @return String Valeur de l'ann�e en caract�re.
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
     * @return Timestamp Valeur de la date d'expiration de la vignette
     * (yyyy-MM-dd).
     */
    public Timestamp getDateExpirationVignette();

    /**
     * Retourne le num�ro de s�rie.
     *
     * @return String Valeur du num�ro de s�rie en caract�re.
     */
    public String getNumeroSerie();

    /**
     * Retourne l'assurance.
     *
     * @return String Valeur de l'assurance en caract�re.
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
     * @return String Valeur de la police en caract�re.
     */
    public String getPolice();

    /**
     * Retourne la confidentialit�.
     *
     * @return long Valeur de la confidentialit�.
     */
    public long getConfidentialite();

    /**
     * Retourne le mot de passe.
     *
     * @return String Valeur du mot de passe en caract�re.
     */
    public String getMotPasse();

    /**
     * Retourne le cr�ateur de la fiche V�hicule.
     *
     * @return String Valeur du cr�ateur en caract�re.
     */
    public String getCreateur();

    /**
     * Retourne la date de cr�ation de la fiche V�hicule
     *
     * @return Timestamp Valeur de cr�ation.
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
	 * Retourne la date de cr�ation de la liaison.
	 *
	 * @return Timestamp Valeur de la date de liaison (yyy-MM-dd).
	 */
	public Timestamp getLienDateCreation();

    /**
     * Retourne le r�le.
     *
     * @return long Valeur du r�le.
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
     * @param marque Valeur de la marque.
     */
    public void setMarque(long marque);

    /**
     * Affecte un mod�le.
     *
     * @param modele Valeur du mod�le.
     */
    public void setModele(long modele);

    /**
     * Champ utilitaire.
     *
     * @param modele Valeur du mod�le en caract�re.
     */
    public void setModeleMarque(long modeleMarque);

    /**
     * Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caract�re.
     */
    public void setImmatriculation(String immatriculation);

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province.
     */
    public void setProvince(String province);

    /**
     * Affecte une ann�e.
     *
     * @param annee Valeur de l'ann�e en caract�re.
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
     * vignette (yyyy-MM-dd).
     */
    public void setDateExpirationVignette(Timestamp dateExpirationVignette);

    /**
     * Affecte un num�ro de s�rie.
     *
     * @param numeroSerie Valeur du num�ro de s�rie en caract�re.
     */
    public void setNumeroSerie(String numeroSerie);

    /**
     * Affecte une assurance.
     *
     * @param assurance Valeur de l'assurance en caract�re.
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
     * @param police Valeur de la police en caract�re.
     */
    public void setPolice(String police);

    /**
     * Affecte une confidentialit�.
     *
     * @param confidentialite Valeur de la confidentialit�.
     */
    public void setConfidentialite(long confidentialite);

    /**
     * Affecte un mot de passe.
     *
     * @param motPasse Valeur du mot de passe en caract�re.
     */
    public void setMotPasse(String motPasse);

    /**
     * Affecte un cr�ateur.
     *
     * @param createur Valeur du cr�ateur en caract�re.
     */
    public void setCreateur(String createur);

    /**
     * Affecte une date de cr�ation de la fiche V�hicule.
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
     * Affecte un r�le.
     *
     * @param role Valeur du r�le.
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
     * Retourne la cl� de la province.
     *
     */
    public long getCleProvince();

    /**
     * Affecte la cl� de la province.
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