package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * Définit les signatures de méthodes nécessaire à l'obtention des valeurs
 * relatives à un formulaire de recherche de photo.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.6 $, $Date: 2002/02/25 18:15:26 $
 */
public interface CriteresRecherchePhotoHtmlForm extends CriteresRecherche{


    // Getters


    /**
     * Retourne le site applicable.
     *
     * @return String Valeur du site applicable en caractère.
     */
    public String getSiteApplicable();

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caractère.
     */
    public String getSiteOrigine();

    /**
     * Retourne la sévérité.
     *
     * @return String Valeur de la sévérité en caractère.
     */
    public String getSeverite();

    /**
     * Pour sélectionner le nombre de repérages dans la Galerie.
     *
     * @return String 
     */
    public String getNombreReperages();

    /**
     * Retourne la race.
     *
     * @return String Valeur de la race en caractère.
     */
    public String getRace();

    /**
     * Retourne la nature.
     *
     * @return String Valeur de la nature en caractère.
     */
    public String getNature();

    /**
     * Retourne la date d'ajout au début.
     *
     * @return String Valeur de la date d'ajout au début en caractère.
     */
    public String getDateAjoutDebut();

    /**
     * Retourne la langue.
     *
     * @return String Valeur de la langue en caractère.
     */
    public String getLangue();

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caractère.
     */
    public String getType();

    /**
     * Retourne la date d'ajout à la fin.
     *
     * @return String Valeur de la date d'ajout à la fin en caractère.
     */
    public String getDateAjoutFin();

    /**
     * Retourne le sexe.
     *
     * @return String Valeur du sexe en caractère.
     */
    public String getSexe();

    /**
     * Retourne la catégorie.
     *
     * @return String Valeur de la catégorie en caractère.
     */
    public String getCategorie();

    /**
     * Retourne la date de validité au début.
     *
     * @return String Valeur de la date de validité au début en caractère.
     */
    public String getDateValideDebut();

    /**
     * Retourne l'ethnie.
     *
     * @return String Valeur de l'ethnie en caractère.
     */
    public String getEthnie();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caractère.
     */
    public String getStatut();

    /**
     * Retourne la date de validité à la fin.
     *
     * @return String Valeur de la date de validité à la fin en caractère.
     */
    public String getDateValideFin();

    /**
     * Retourne le jeu.
     *
     * @return String Valeur du jeu en caractère.
     */
    public String getJeu();

    /**
     * Retourne la fondation.
     *
     * @return String Valeur de la fondation en caractère.
     */
    public String getFonde();

    /**
     * Retourne la date se terminant au début.
     *
     * @return String Valeur de la date se terminant au début en caractère.
     */
    public String getDateTermineDebut();

    /**
     * Retourne la première caractéristique.
     *
     * @return String Valeur de la première caractéristique en caractère.
     */
    public String getCaracteristique1();

    /**
     * Retourne la période.
     *
     * @return String Valeur de la période en caractère.
     */
    public String getPeriode();

    /**
     * Retourne la date se terminant à la fin en caractère.
     *
     * @return String Valeur de la date se terminant à la fin en caractère.
     */
    public String getDateTermineFin();

    /**
     * Retourne la deuxième caractéristique.
     *
     * @return String Valeur de la deuxième caractéristique en caractère.
     */
    public String getCaracteristique2();

    /**
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro de dossier en caractère.
     */
    public String getNumeroDossier();

    /**
     * Retourne le numéro de sujet.
     *
     * @return String Valeur du numéro de sujet en caractère.
     */
    public String getNumeroSujet();

    /**
     * Retourne la troisième caractéristique.
     *
     * @return String Valeur de la troisième caractéristique en caractère.
     */
    public String getCaracteristique3();

    /**
     * Retourne la référence vidéo.
     *
     * @return String Valeur de la référence vidéo en caractère.
     */
    public String getReferenceVideo();

    /**
     * Retourne si un enregistrement existe.
     *
     * @return String Valeur en caractère.
     */
    public String getEnregistrementNumerique();

    /**
     * Retourne si un enregistrement est conservé.
     *
     * @return String Valeur en caractère.
     */
    public String getEnregistrementConserve();

    /**
     * Retourne le nom.
     *
     * @return String Valeur du nom en caractère.
     */
    public String getNom();

    /**
     * Retourne si le sujet est mis en attache.
     *
     * @return boolean Valeur booléenne indiquant si le sujet est mis en
     * attache.
     */
    public boolean isSujetAttache();

    /**
     * Retourne la cinquième caractéristique.
     *
     * @return String Valeur de la cinquième caractéristique en caractère.
     */
    public String getCaracteristique4();

    /**
     * Retourne le prénom.
     *
     * @return String Valeur du prénom en caractère.
     */
    public String getPrenom();

    /**
     * Retourne si le dossier est mis en attache.
     *
     * @return boolean Valeur booléenne indiquant si le dossier est mis en
     * attache.
     */
    public boolean isDossierAttache();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caractère.
     */
    public String getOrigine();

    /**
     * Retourne le nom ordinaire.
     *
     * @return String Valeur du nom ordinaire en caractère.
     */
    public String getNomOrdinaire();

    /**
     * Retourne l'âge approximatif d'un sujet
     *
     * @return String Valeur de l'âge en caractère.
     */
    public String getAge();

    /**
     * Retourne le surnom (alias) d'un sujet
     *
     * @return String Valeur de l'alias en caractère.
     */
    public String getAlias();

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
     * Retourne la référence 1.
     *
     * @return String Valeur de la référence en caractère.
     */
    public String getReference1();

    /**
     * Retourne la référence 2.
     *
     * @return String Valeur de la référence en caractère.
     */
    public String getReference2();

    /**
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caractère.
     */
    public String getOrdreTriRecherche();

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur booléanne indiquant si l'ordre de recherche est
     * croissant.
     */
    public boolean isOrdreCroissantRecherche();

    /**
     * Retourne le nombre maximum de résultats de recherche.
     *
     * @return String Valeur du nombre maximum de résultats de recherche en
     * caractère.
     */
    public String getMaximumResultatsRecherche();

    /**
     * Retourne une collection de photos.
     *
     * @return Collection Valeur de la collection de photos.
     */
    public Collection getPhotos();

    /**
     * Retourne le type d'âge
     *
     * @return boolean Valeur du type d'âge.
     */
    public boolean isAgeEstime();
    
    public boolean isAgeReel();
    
    public boolean isAgeReelPlusMoins();
    
    public boolean isAgeInconnu();

    // Setters


    /**
     * Affecte un site applicable.
     *
     * @param siteApplicable Valeur du site applicable en caractère.
     */
    public void setSiteApplicable(String siteApplicable);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine en caractère.
     */
    public void setSiteOrigine(String siteOrigine);

    /**
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité en caractère.
     */
    public void setSeverite(String severite);

    /**
     * Affecte une race.
     *
     * @param race Valeur de la race en caractère.
     */
    public void setRace(String race);

    /**
     * Affecte une nature.
     *
     * @param nature Valeur de la nature en caractère.
     */
    public void setNature(String nature);

    /**
     * Affecte une date d'ajout au début.
     *
     * @param dateAjoutDebut Valeur de la date d'ajout au début en caractère.
     */
    public void setDateAjoutDebut(String dateAjoutDebut);

    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue en caractère.
     */
    public void setLangue(String langue);

    /**
     * Affecte un type.
     *
     * @param type Valeur du type en caractère.
     */
    public void setType(String type);

    /**
     * Affecte une date d'ajout à la fin.
     *
     * @param dateAjoutFin Valeur de la date d'ajout à la fin en caractère.
     */
    public void setDateAjoutFin(String dateAjoutFin);

    /**
     * Affecte un sexe.
     *
     * @param sexe Valeur du sexe en caractère.
     */
    public void setSexe(String sexe);

    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur de la catégorie en caractère.
     */
    public void setCategorie(String categorie);

    /**
     * Affecte une date de validité au début.
     *
     * @param dateValideDebut Valeur de la date de validité au début
     * en caractère.
     */
    public void setDateValideDebut(String dateValideDebut);

    /**
     * Affecte un ethnie.
     *
     * @param ethnie Valeur de l'ethnie en caractère.
     */
    public void setEthnie(String ethnie);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caractère.
     */
    public void setStatut(String statut);

    /**
     * Affecte une date de validité à la fin.
     *
     * @param dateValideFin Valeur de la date de validité à la fin en caractère.
     */
    public void setDateValideFin(String dateValideFin);

    /**
     * Affecte un jeu.
     *
     * @param jeu Valeur du jeu en caractère.
     */
    public void setJeu(String jeu);

    /**
     * Affecte une fondation.
     *
     * @param fonde Valeur de la fondation en caractère.
     */
    public void setFonde(String fonde);

    /**
     * Affecte une date se terminant au début.
     *
     * @param dateTermineDebut Valeur de la date se terminant au début en
     * caractère.
     */
    public void setDateTermineDebut(String dateTermineDebut);

    /**
     * Affecte un première caractéristique.
     *
     * @param caracteristique1 Valeur de la première caractéristique en
     * caractère.
     */
    public void setCaracteristique1(String caracteristique1);

    /**
     * Affecte une période.
     *
     * @param periode Valeur de la période en caractère.
     */
    public void setPeriode(String periode);

    /**
     * Affecte une date se terminant à la fin.
     *
     * @param dateTermineFin Valeur de la date se terminant à la fin en
     * caractère.
     */
    public void setDateTermineFin(String dateTermineFin);

    /**
     * Affecte une deuxième caractéristique.
     *
     * @param caracteristique2 Valeur de la deuxième caractéristique en
     * caractère.
     */
    public void setCaracteristique2(String caracteristique2);

    /**
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte un numéro de sujet.
     *
     * @param numeroSujet Valeur du numéro de sujet en caractère.
     */
    public void setNumeroSujet(String numeroSujet);

    /**
     * Affecte une troisième caractéristique.
     *
     * @param caracteristique3 Valeur de la troisième caractéristique en
     * caractère.
     */
    public void setCaracteristique3(String caracteristique3);

    /**
     * Affecte une référence vidéo.
     *
     * @param referenceVideo Valeur de la référence vidéo en caractère.
     */
    public void setReferenceVideo(String referenceVideo);

    /**
     * Affecte une quatrième caractéristique.
     *
     * @param caracteristique4 Valeur de la quatrième caractéristique en
     * caractère.
     */
    public void setCaracteristique4(String caracteristique4);

    /**
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caractère.
     */
    public void setNom(String nom);

    /**
     * Affecte si le sujet est mis en attache.
     *
     * @param sujetAttache Valeur booléenne indiquant si le sujet est mis en
     * attache.
     */
    public void setSujetAttache(boolean sujetAttache);

    /**
     * Affecte un prénom.
     *
     * @param prenom Valeur du prénom en caractère.
     */
    public void setPrenom(String prenom);

    /**
     * Affecte si le dossier est mis en attache.
     *
     * @param dossierAttache Valeur booléenne indiquant si le dossier est mis
     * en attache.
     */
    public void setDossierAttache(boolean dossierAttache);

    /**
     * Affecte une origine.
     *
     * @param origine Valeur de l'origine en caractère.
     */
    public void setOrigine(String origine);

    /**
     * Affecte un nom ordinaire.
     *
     * @param nomOrdinaire Valeur du nom ordinaire en caractère.
     */
    public void setNomOrdinaire(String nomOrdinaire);

    /**
     * Affecte l'âge.
     *
     * @param age Valeur de l'âge en caractère.
     */
    public void setAge(String age);

    /**
     * Affecte l'alias.
     *
     * @param age Valeur de l'alias en caractère.
     */
    public void setAlias(String alias);

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
     * Affecte la référence 1.
     *
     * @param lienSite Valeur de la référence en caractère.
     */
    public void setReference1(String reference1);

    /**
     * Affecte la référence 2.
     *
     * @param lienSite Valeur de la référence en caractère.
     */
    public void setReference2(String reference2);

    /**
     * Pour le choix du nombre de repérages dans la sélection des dossiers de la Galerie.
     *
     * @param nombreReperages
     */
    public void setNombreReperages(String nombreReperages);

   /**
     * Affecte une date de création au.
     *
     * @param dateCreationAu Valeur de la date de création au en caractère.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche);

    /**
     * Affecte si la recherche est par ordre croissante.
     *
     * @param ordreCroissantRecherche Valeur booléeanne indiquant si la
     * recherche est par ordre croisante.
     */
    public void setOrdreCroissantRecherche(boolean ordreCroissantRecherche);

    /**
     * Affecte le nombre maximum de résultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de résultats
     * de recherche caractère.
     */
    public void setMaximumResultatsRecherche(String maximum);

    /**
     * Ajoute une photo.
     *
     * @param sujet Valeur de la photo à ajouter.
     */
    public void addPhoto(PhotoHtmlForm photo);

    /**
     * Affecte l'enregistrement numérique.
     *
     * @param enregistrementNumerique Valeur numérique .
     */
    public void setEnregistrementNumerique(String enregistrementNumerique);

    /**
     * Affecte l'enregistrement conservé.
     *
     * @param enregistrementConserve Valeur conserve.
     */
    public void setEnregistrementConserve(String enregistrementConserve);
    
	public int getSequence();

	public void setSequence(int sequence);
	
}