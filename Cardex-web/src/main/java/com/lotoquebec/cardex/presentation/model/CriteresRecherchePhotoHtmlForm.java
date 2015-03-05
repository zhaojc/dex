package com.lotoquebec.cardex.presentation.model;

import java.util.Collection;

import com.lotoquebec.cardexCommun.business.CriteresRecherche;

/**
 * D�finit les signatures de m�thodes n�cessaire � l'obtention des valeurs
 * relatives � un formulaire de recherche de photo.
 *
 * @author $Author: pcaron $
 * @version $Revision: 1.6 $, $Date: 2002/02/25 18:15:26 $
 */
public interface CriteresRecherchePhotoHtmlForm extends CriteresRecherche{


    // Getters


    /**
     * Retourne le site applicable.
     *
     * @return String Valeur du site applicable en caract�re.
     */
    public String getSiteApplicable();

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caract�re.
     */
    public String getSiteOrigine();

    /**
     * Retourne la s�v�rit�.
     *
     * @return String Valeur de la s�v�rit� en caract�re.
     */
    public String getSeverite();

    /**
     * Pour s�lectionner le nombre de rep�rages dans la Galerie.
     *
     * @return String 
     */
    public String getNombreReperages();

    /**
     * Retourne la race.
     *
     * @return String Valeur de la race en caract�re.
     */
    public String getRace();

    /**
     * Retourne la nature.
     *
     * @return String Valeur de la nature en caract�re.
     */
    public String getNature();

    /**
     * Retourne la date d'ajout au d�but.
     *
     * @return String Valeur de la date d'ajout au d�but en caract�re.
     */
    public String getDateAjoutDebut();

    /**
     * Retourne la langue.
     *
     * @return String Valeur de la langue en caract�re.
     */
    public String getLangue();

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caract�re.
     */
    public String getType();

    /**
     * Retourne la date d'ajout � la fin.
     *
     * @return String Valeur de la date d'ajout � la fin en caract�re.
     */
    public String getDateAjoutFin();

    /**
     * Retourne le sexe.
     *
     * @return String Valeur du sexe en caract�re.
     */
    public String getSexe();

    /**
     * Retourne la cat�gorie.
     *
     * @return String Valeur de la cat�gorie en caract�re.
     */
    public String getCategorie();

    /**
     * Retourne la date de validit� au d�but.
     *
     * @return String Valeur de la date de validit� au d�but en caract�re.
     */
    public String getDateValideDebut();

    /**
     * Retourne l'ethnie.
     *
     * @return String Valeur de l'ethnie en caract�re.
     */
    public String getEthnie();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caract�re.
     */
    public String getStatut();

    /**
     * Retourne la date de validit� � la fin.
     *
     * @return String Valeur de la date de validit� � la fin en caract�re.
     */
    public String getDateValideFin();

    /**
     * Retourne le jeu.
     *
     * @return String Valeur du jeu en caract�re.
     */
    public String getJeu();

    /**
     * Retourne la fondation.
     *
     * @return String Valeur de la fondation en caract�re.
     */
    public String getFonde();

    /**
     * Retourne la date se terminant au d�but.
     *
     * @return String Valeur de la date se terminant au d�but en caract�re.
     */
    public String getDateTermineDebut();

    /**
     * Retourne la premi�re caract�ristique.
     *
     * @return String Valeur de la premi�re caract�ristique en caract�re.
     */
    public String getCaracteristique1();

    /**
     * Retourne la p�riode.
     *
     * @return String Valeur de la p�riode en caract�re.
     */
    public String getPeriode();

    /**
     * Retourne la date se terminant � la fin en caract�re.
     *
     * @return String Valeur de la date se terminant � la fin en caract�re.
     */
    public String getDateTermineFin();

    /**
     * Retourne la deuxi�me caract�ristique.
     *
     * @return String Valeur de la deuxi�me caract�ristique en caract�re.
     */
    public String getCaracteristique2();

    /**
     * Retourne le num�ro de dossier.
     *
     * @return String Valeur du num�ro de dossier en caract�re.
     */
    public String getNumeroDossier();

    /**
     * Retourne le num�ro de sujet.
     *
     * @return String Valeur du num�ro de sujet en caract�re.
     */
    public String getNumeroSujet();

    /**
     * Retourne la troisi�me caract�ristique.
     *
     * @return String Valeur de la troisi�me caract�ristique en caract�re.
     */
    public String getCaracteristique3();

    /**
     * Retourne la r�f�rence vid�o.
     *
     * @return String Valeur de la r�f�rence vid�o en caract�re.
     */
    public String getReferenceVideo();

    /**
     * Retourne si un enregistrement existe.
     *
     * @return String Valeur en caract�re.
     */
    public String getEnregistrementNumerique();

    /**
     * Retourne si un enregistrement est conserv�.
     *
     * @return String Valeur en caract�re.
     */
    public String getEnregistrementConserve();

    /**
     * Retourne le nom.
     *
     * @return String Valeur du nom en caract�re.
     */
    public String getNom();

    /**
     * Retourne si le sujet est mis en attache.
     *
     * @return boolean Valeur bool�enne indiquant si le sujet est mis en
     * attache.
     */
    public boolean isSujetAttache();

    /**
     * Retourne la cinqui�me caract�ristique.
     *
     * @return String Valeur de la cinqui�me caract�ristique en caract�re.
     */
    public String getCaracteristique4();

    /**
     * Retourne le pr�nom.
     *
     * @return String Valeur du pr�nom en caract�re.
     */
    public String getPrenom();

    /**
     * Retourne si le dossier est mis en attache.
     *
     * @return boolean Valeur bool�enne indiquant si le dossier est mis en
     * attache.
     */
    public boolean isDossierAttache();

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public String getOrigine();

    /**
     * Retourne le nom ordinaire.
     *
     * @return String Valeur du nom ordinaire en caract�re.
     */
    public String getNomOrdinaire();

    /**
     * Retourne l'�ge approximatif d'un sujet
     *
     * @return String Valeur de l'�ge en caract�re.
     */
    public String getAge();

    /**
     * Retourne le surnom (alias) d'un sujet
     *
     * @return String Valeur de l'alias en caract�re.
     */
    public String getAlias();

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
     * Retourne la r�f�rence 1.
     *
     * @return String Valeur de la r�f�rence en caract�re.
     */
    public String getReference1();

    /**
     * Retourne la r�f�rence 2.
     *
     * @return String Valeur de la r�f�rence en caract�re.
     */
    public String getReference2();

    /**
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caract�re.
     */
    public String getOrdreTriRecherche();

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur bool�anne indiquant si l'ordre de recherche est
     * croissant.
     */
    public boolean isOrdreCroissantRecherche();

    /**
     * Retourne le nombre maximum de r�sultats de recherche.
     *
     * @return String Valeur du nombre maximum de r�sultats de recherche en
     * caract�re.
     */
    public String getMaximumResultatsRecherche();

    /**
     * Retourne une collection de photos.
     *
     * @return Collection Valeur de la collection de photos.
     */
    public Collection getPhotos();

    /**
     * Retourne le type d'�ge
     *
     * @return boolean Valeur du type d'�ge.
     */
    public boolean isAgeEstime();
    
    public boolean isAgeReel();
    
    public boolean isAgeReelPlusMoins();
    
    public boolean isAgeInconnu();

    // Setters


    /**
     * Affecte un site applicable.
     *
     * @param siteApplicable Valeur du site applicable en caract�re.
     */
    public void setSiteApplicable(String siteApplicable);

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine en caract�re.
     */
    public void setSiteOrigine(String siteOrigine);

    /**
     * Affecte une s�v�rit�.
     *
     * @param severite Valeur de la s�v�rit� en caract�re.
     */
    public void setSeverite(String severite);

    /**
     * Affecte une race.
     *
     * @param race Valeur de la race en caract�re.
     */
    public void setRace(String race);

    /**
     * Affecte une nature.
     *
     * @param nature Valeur de la nature en caract�re.
     */
    public void setNature(String nature);

    /**
     * Affecte une date d'ajout au d�but.
     *
     * @param dateAjoutDebut Valeur de la date d'ajout au d�but en caract�re.
     */
    public void setDateAjoutDebut(String dateAjoutDebut);

    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue en caract�re.
     */
    public void setLangue(String langue);

    /**
     * Affecte un type.
     *
     * @param type Valeur du type en caract�re.
     */
    public void setType(String type);

    /**
     * Affecte une date d'ajout � la fin.
     *
     * @param dateAjoutFin Valeur de la date d'ajout � la fin en caract�re.
     */
    public void setDateAjoutFin(String dateAjoutFin);

    /**
     * Affecte un sexe.
     *
     * @param sexe Valeur du sexe en caract�re.
     */
    public void setSexe(String sexe);

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur de la cat�gorie en caract�re.
     */
    public void setCategorie(String categorie);

    /**
     * Affecte une date de validit� au d�but.
     *
     * @param dateValideDebut Valeur de la date de validit� au d�but
     * en caract�re.
     */
    public void setDateValideDebut(String dateValideDebut);

    /**
     * Affecte un ethnie.
     *
     * @param ethnie Valeur de l'ethnie en caract�re.
     */
    public void setEthnie(String ethnie);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caract�re.
     */
    public void setStatut(String statut);

    /**
     * Affecte une date de validit� � la fin.
     *
     * @param dateValideFin Valeur de la date de validit� � la fin en caract�re.
     */
    public void setDateValideFin(String dateValideFin);

    /**
     * Affecte un jeu.
     *
     * @param jeu Valeur du jeu en caract�re.
     */
    public void setJeu(String jeu);

    /**
     * Affecte une fondation.
     *
     * @param fonde Valeur de la fondation en caract�re.
     */
    public void setFonde(String fonde);

    /**
     * Affecte une date se terminant au d�but.
     *
     * @param dateTermineDebut Valeur de la date se terminant au d�but en
     * caract�re.
     */
    public void setDateTermineDebut(String dateTermineDebut);

    /**
     * Affecte un premi�re caract�ristique.
     *
     * @param caracteristique1 Valeur de la premi�re caract�ristique en
     * caract�re.
     */
    public void setCaracteristique1(String caracteristique1);

    /**
     * Affecte une p�riode.
     *
     * @param periode Valeur de la p�riode en caract�re.
     */
    public void setPeriode(String periode);

    /**
     * Affecte une date se terminant � la fin.
     *
     * @param dateTermineFin Valeur de la date se terminant � la fin en
     * caract�re.
     */
    public void setDateTermineFin(String dateTermineFin);

    /**
     * Affecte une deuxi�me caract�ristique.
     *
     * @param caracteristique2 Valeur de la deuxi�me caract�ristique en
     * caract�re.
     */
    public void setCaracteristique2(String caracteristique2);

    /**
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String numeroDossier);

    /**
     * Affecte un num�ro de sujet.
     *
     * @param numeroSujet Valeur du num�ro de sujet en caract�re.
     */
    public void setNumeroSujet(String numeroSujet);

    /**
     * Affecte une troisi�me caract�ristique.
     *
     * @param caracteristique3 Valeur de la troisi�me caract�ristique en
     * caract�re.
     */
    public void setCaracteristique3(String caracteristique3);

    /**
     * Affecte une r�f�rence vid�o.
     *
     * @param referenceVideo Valeur de la r�f�rence vid�o en caract�re.
     */
    public void setReferenceVideo(String referenceVideo);

    /**
     * Affecte une quatri�me caract�ristique.
     *
     * @param caracteristique4 Valeur de la quatri�me caract�ristique en
     * caract�re.
     */
    public void setCaracteristique4(String caracteristique4);

    /**
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caract�re.
     */
    public void setNom(String nom);

    /**
     * Affecte si le sujet est mis en attache.
     *
     * @param sujetAttache Valeur bool�enne indiquant si le sujet est mis en
     * attache.
     */
    public void setSujetAttache(boolean sujetAttache);

    /**
     * Affecte un pr�nom.
     *
     * @param prenom Valeur du pr�nom en caract�re.
     */
    public void setPrenom(String prenom);

    /**
     * Affecte si le dossier est mis en attache.
     *
     * @param dossierAttache Valeur bool�enne indiquant si le dossier est mis
     * en attache.
     */
    public void setDossierAttache(boolean dossierAttache);

    /**
     * Affecte une origine.
     *
     * @param origine Valeur de l'origine en caract�re.
     */
    public void setOrigine(String origine);

    /**
     * Affecte un nom ordinaire.
     *
     * @param nomOrdinaire Valeur du nom ordinaire en caract�re.
     */
    public void setNomOrdinaire(String nomOrdinaire);

    /**
     * Affecte l'�ge.
     *
     * @param age Valeur de l'�ge en caract�re.
     */
    public void setAge(String age);

    /**
     * Affecte l'alias.
     *
     * @param age Valeur de l'alias en caract�re.
     */
    public void setAlias(String alias);

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
     * Affecte la r�f�rence 1.
     *
     * @param lienSite Valeur de la r�f�rence en caract�re.
     */
    public void setReference1(String reference1);

    /**
     * Affecte la r�f�rence 2.
     *
     * @param lienSite Valeur de la r�f�rence en caract�re.
     */
    public void setReference2(String reference2);

    /**
     * Pour le choix du nombre de rep�rages dans la s�lection des dossiers de la Galerie.
     *
     * @param nombreReperages
     */
    public void setNombreReperages(String nombreReperages);

   /**
     * Affecte une date de cr�ation au.
     *
     * @param dateCreationAu Valeur de la date de cr�ation au en caract�re.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche);

    /**
     * Affecte si la recherche est par ordre croissante.
     *
     * @param ordreCroissantRecherche Valeur bool�eanne indiquant si la
     * recherche est par ordre croisante.
     */
    public void setOrdreCroissantRecherche(boolean ordreCroissantRecherche);

    /**
     * Affecte le nombre maximum de r�sultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de r�sultats
     * de recherche caract�re.
     */
    public void setMaximumResultatsRecherche(String maximum);

    /**
     * Ajoute une photo.
     *
     * @param sujet Valeur de la photo � ajouter.
     */
    public void addPhoto(PhotoHtmlForm photo);

    /**
     * Affecte l'enregistrement num�rique.
     *
     * @param enregistrementNumerique Valeur num�rique .
     */
    public void setEnregistrementNumerique(String enregistrementNumerique);

    /**
     * Affecte l'enregistrement conserv�.
     *
     * @param enregistrementConserve Valeur conserve.
     */
    public void setEnregistrementConserve(String enregistrementConserve);
    
	public int getSequence();

	public void setSequence(int sequence);
	
}