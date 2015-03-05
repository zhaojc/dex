package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.CriteresRecherchePhotoHtmlForm;
import com.lotoquebec.cardex.presentation.model.PhotoHtmlForm;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleHardListe.cleRessourceHardListe.TriGalerieCleRessourceHardListe;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.model.RechercheListeResultat;
import com.lotoquebec.cardexCommun.util.GererTacheUtilisateur;

/**
 * Conserve les différentes valeurs relatives au formulatire de recherche d'une
 * photo.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.11 $, $Date: 2002/03/28 15:56:03 $
 * @see com.lotoquebec.cardex.presentation.model.CriteresRecherchePhotoHtmlForm
 */
public class CriteresRecherchePhotoForm extends ValidatorForm
        implements CriteresRecherchePhotoHtmlForm, RechercheListeResultat, Serializable {

    private String siteApplicable = "";
    private String siteOrigine = "";
    private String severite = "";
    private String race = "";
    private HierarchieEGNTC hierarchieEGNTC = new HierarchieEGNTC();
    private String dateAjoutDebut = "";
    private String langue = "";
    private String dateAjoutFin = "";
	private String alias = "";
	private String sexe = "";
    private String dateValideDebut = "";
    private String ethnie = "";
    private String statut = "";
    private String dateValideFin = "";
    private String typeJeu = "";
    private String jeu = "";
    private String fonde = "";
    private String dateTermineDebut = "";
    private String caracteristique1 = "";
    private String caracteristique1et2 = "";
    private String periode = "";
    private String dateTermineFin = "";
    private String caracteristique2 = "";
    private String caracteristique2et3 = "";
    private String numeroDossier = "";
    private String numeroSujet = "";
    private String caracteristique3 = "";
    private String caracteristique3et4 = "";
    private String referenceVideo = "";
    private String enregistrementNumerique = "";
    private String enregistrementConserve = "";
    private String nombreReperages = "1";    
    private String nom = "";
    private boolean sujetAttache = true;
    private String caracteristique4 = "";
    private String prenom = "";
    private boolean dossierAttache = false;
    private String origine = "";
    private String nomOrdinaire = "";
    private String age = "";
    private boolean ageEstime = true;
    private boolean ageReel = true;
    private boolean ageReelPlusMoins = true;
    private boolean ageInconnu = true;
    private String lien = "";
    private String lienSite = "";
    private String ordreTriRecherche = "";
    private boolean ordreCroissantRecherche = true;
    private String intervenant = "";
    private String endroit = "";
    private String localisation = "";
    private String descriptif = "";
    private String reference1 = "";
    private String reference2 = "";
    private String maximumResultatsRecherche = "6";
    private List photos = new ArrayList();
    private String ordreTri = "";
    private String sensTri = "";
    private int sequence = 0;
    private ListeResultat listeResultat = new ListeResultat();
    private String frequence = "";
    private String lancerDefilement = "";
    private Date dateExecutionGalerie = null;
    
    /**
     * Constructeur de CriteresRecherchePhotoForm par défaut.
     */
    public CriteresRecherchePhotoForm() {}


    // Getters


    /**
     * Retourne le site applicable.
     *
     * @return String Valeur du site applicable en caractère.
     */
    public String getSiteApplicable() {
        return this.siteApplicable;
    }

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caractère.
     */
    public String getSiteOrigine() {
        return this.siteOrigine;
    }

    /**
     * Retourne la sévérité.
     *
     * @return String Valeur de la sévérité en caractère.
     */
    public String getSeverite() {
        return this.severite;
    }

    /**
     * Retourne la race.
     *
     * @return String Valeur de la race en caractère.
     */
    public String getRace() {
        return this.race;
    }

    /**
     * Retourne la nature.
     *
     * @return String Valeur de la nature en caractère.
     */
    public String getNature() {
        return hierarchieEGNTC.get(HierarchieEGNTC.NATURE);
    }

    /**
     * Pour demander plus d'un repérage (par exemple, sélection des dossiers qui ont plus de 5 repérages).
     *
     * @return boolean True si la photo est attaché à un sujet.
     */
    public String getNombreReperages() {
        return nombreReperages;
    }

    /**
     * Retourne la date d'ajout au début.
     *
     * @return String Valeur de la date d'ajout au début en caractère.
     */
    public String getDateAjoutDebut() {
        return this.dateAjoutDebut;
    }

    /**
     * Retourne la langue.
     *
     * @return String Valeur de la langue en caractère.
     */
    public String getLangue() {
        return this.langue;
    }

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caractère.
     */
    public String getType() {
    	return hierarchieEGNTC.get(HierarchieEGNTC.TYPE);
    }

    /**
     * Retourne la date d'ajout à la fin.
     *
     * @return String Valeur de la date d'ajout à la fin en caractère.
     */
    public String getDateAjoutFin() {
        return this.dateAjoutFin;
    }

    /**
     * Retourne le sexe.
     *
     * @return String Valeur du sexe en caractère.
     */
    public String getSexe() {
        return this.sexe;
    }

    /**
     * Retourne la catégorie.
     *
     * @return String Valeur de la catégorie en caractère.
     */
    public String getCategorie() {
    	return hierarchieEGNTC.get(HierarchieEGNTC.CATEGORIE);
    }

    /**
     * Retourne la date de validité au début.
     *
     * @return String Valeur de la date de validité au début en caractère.
     */
    public String getDateValideDebut() {
        return this.dateValideDebut;
    }

    /**
     * Retourne l'ethnie.
     *
     * @return String Valeur de l'ethnie en caractère.
     */
    public String getEthnie() {
        return this.ethnie;
    }

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caractère.
     */
    public String getStatut() {
        return this.statut;
    }

    /**
     * Retourne la date de validité à la fin.
     *
     * @return String Valeur de la date de validité à la fin en caractère.
     */
    public String getDateValideFin() {
        return this.dateValideFin;
    }

    /**
     * Retourne le jeu.
     *
     * @return String Valeur du jeu en caractère.
     */
    public String getJeu() {
        return this.jeu;
    }

    /**
     * Retourne la fondation.
     *
     * @return String Valeur de la fondation en caractère.
     */
    public String getFonde() {
        return this.fonde;
    }

    /**
     * Retourne la date se terminant au début.
     *
     * @return String Valeur de la date se terminant au début en caractère.
     */
    public String getDateTermineDebut() {
        return this.dateTermineDebut;
    }

    /**
     * Retourne la première caractéristique.
     *
     * @return String Valeur de la première caractéristique en caractère.
     */
    public String getCaracteristique1() {
        return this.caracteristique1;
    }

    /**
     * Retourne la période.
     *
     * @return String Valeur de la période en caractère.
     */
    public String getPeriode() {
        return this.periode;
    }

    /**
     * Retourne la date se terminant à la fin en caractère.
     *
     * @return String Valeur de la date se terminant à la fin en caractère.
     */
    public String getDateTermineFin() {
        return this.dateTermineFin;
    }

    /**
     * Retourne la deuxième caractéristique.
     *
     * @return String Valeur de la deuxième caractéristique en caractère.
     */
    public String getCaracteristique2() {
        return this.caracteristique2;
    }

    /**
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro de dossier en caractère.
     */
    public String getNumeroDossier() {
        return this.numeroDossier;
    }

    /**
     * Retourne le numéro de sujet.
     *
     * @return String Valeur du numéro de sujet en caractère.
     */
    public String getNumeroSujet() {
        return this.numeroSujet;
    }

    /**
     * Retourne la troisième caractéristique.
     *
     * @return String Valeur de la troisième caractéristique en caractère.
     */
    public String getCaracteristique3() {
        return this.caracteristique3;
    }

    /**
     * Retourne la référence vidéo.
     *
     * @return String Valeur de la référence vidéo en caractère.
     */
    public String getReferenceVideo() {
        return this.referenceVideo;
    }

    /**
     * Retourne le nom.
     *
     * @return String Valeur du nom en caractère.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Retourne si le sujet est mis en attache.
     *
     * @return boolean Valeur booléenne indiquant si le sujet est mis en
     * attache.
     */
    public boolean isSujetAttache() {
        return this.sujetAttache;
    }

    /**
     * Retourne la cinquième caractéristique.
     *
     * @return String Valeur de la cinquième caractéristique en caractère.
     */
    public String getCaracteristique4() {
        return this.caracteristique4;
    }

    /**
     * Retourne le prénom.
     *
     * @return String Valeur du prénom en caractère.
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Retourne si le dossier est mis en attache.
     *
     * @return boolean Valeur booléenne indiquant si le dossier est mis en
     * attache.
     */
    public boolean isDossierAttache() {
        return this.dossierAttache;
    }

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caractère.
     */
    public String getOrigine() {
        return this.origine;
    }

    /**
     * Retourne le nom ordinaire.
     *
     * @return String Valeur du nom ordinaire en caractère.
     */
    public String getNomOrdinaire() {
        return this.nomOrdinaire;
    }

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caractère.
     */
    public String getLien() {
        return this.lien;
    }

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caractère.
     */
    public String getLienSite() {
        return this.lienSite;
    }

    /**
     * Retourne l'ordre de tri de recherche.
     *
     * @return String Valeur de l'ordre de tri de recherche en caractère.
     */
    public String getOrdreTriRecherche() {
        return this.ordreTriRecherche;
    }

    /**
     * Retourne si l'ordre de recherche est croissant.
     *
     * @return boolean Valeur booléanne indiquant si l'ordre de recherche est
     * croissant.
     */
    public boolean isOrdreCroissantRecherche() {
        return this.ordreCroissantRecherche;
    }

    /**
     * Retourne le nombre maximum de résultats de recherche.
     *
     * @return String Valeur du nombre maximum de résultats de recherche en
     * caractère.
     */
    public String getMaximumResultatsRecherche() {
        return this.maximumResultatsRecherche;
    }

    /**
     * Retourne une collection de photos.
     *
     * @return Collection Valeur de la collection de photos.
     */
    public Collection getPhotos() {
        return photos;
    }


    // Setters


    /**
     * Affecte un site applicable.
     *
     * @param siteApplicable Valeur du site applicable en caractère.
     */
    public void setSiteApplicable(String siteApplicable) {
        this.siteApplicable = siteApplicable;
    }

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine en caractère.
     */
    public void setSiteOrigine(String siteOrigine) {
        this.siteOrigine = siteOrigine;
    }

    /**
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité en caractère.
     */
    public void setSeverite(String severite) {
        this.severite = severite;
    }

    /**
     * Affecte une race.
     *
     * @param race Valeur de la race en caractère.
     */
    public void setRace(String race) {
        this.race = race;
    }

    /**
     * Affecte une nature.
     *
     * @param nature Valeur de la nature en caractère.
     */
    public void setNature(String nature) {
        hierarchieEGNTC.set(HierarchieEGNTC.NATURE, nature);
    }

    /**
     * Affecte une date d'ajout au début.
     *
     * @param dateAjoutDebut Valeur de la date d'ajout au début en caractère.
     */
    public void setDateAjoutDebut(String dateAjoutDebut) {
        this.dateAjoutDebut = dateAjoutDebut;
    }

    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue en caractère.
     */
    public void setLangue(String langue) {
        this.langue = langue;
    }

    /**
     * Affecte un type.
     *
     * @param type Valeur du type en caractère.
     */
    public void setType(String type) {
        hierarchieEGNTC.set(HierarchieEGNTC.TYPE, type);
    }

    /**
     * Affecte une date d'ajout à la fin.
     *
     * @param dateAjoutFin Valeur de la date d'ajout à la fin en caractère.
     */
    public void setDateAjoutFin(String dateAjoutFin) {
        this.dateAjoutFin = dateAjoutFin;
    }

    /**
     * Affecte un sexe.
     *
     * @param sexe Valeur du sexe en caractère.
     */
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur de la catégorie en caractère.
     */
    public void setCategorie(String categorie) {
        hierarchieEGNTC.set(HierarchieEGNTC.CATEGORIE, categorie);
    }

    /**
     * Affecte une date de validité au début.
     *
     * @param dateValideDebut Valeur de la date de validité au début
     * en caractère.
     */
    public void setDateValideDebut(String dateValideDebut) {
        this.dateValideDebut = dateValideDebut;
    }

    /**
     * Affecte un ethnie.
     *
     * @param ethnie Valeur de l'ethnie en caractère.
     */
    public void setEthnie(String ethnie) {
        this.ethnie = ethnie;
    }

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caractère.
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * Affecte une date de validité à la fin.
     *
     * @param dateValideFin Valeur de la date de validité à la fin en caractère.
     */
    public void setDateValideFin(String dateValideFin) {
        this.dateValideFin = dateValideFin;
    }

    /**
     * Affecte un jeu.
     *
     * @param jeu Valeur du jeu en caractère.
     */
    public void setJeu(String jeu) {
        this.jeu = jeu;
    }

    /**
     * Affecte une fondation.
     *
     * @param fonde Valeur de la fondation en caractère.
     */
    public void setFonde(String fonde) {
        this.fonde = fonde;
    }

    /**
     * Affecte une date se terminant au début.
     *
     * @param dateTermineDebut Valeur de la date se terminant au début en
     * caractère.
     */
    public void setDateTermineDebut(String dateTermineDebut) {
        this.dateTermineDebut = dateTermineDebut;
    }

    /**
     * Affecte un première caractéristique.
     *
     * @param caracteristique1 Valeur de la première caractéristique en
     * caractère.
     */
    public void setCaracteristique1(String caracteristique1) {
        this.caracteristique1 = caracteristique1;
    }

    /**
     * Affecte une période.
     *
     * @param periode Valeur de la période en caractère.
     */
    public void setPeriode(String periode) {
        this.periode = periode;
    }

    /**
     * Affecte une date se terminant à la fin.
     *
     * @param dateTermineFin Valeur de la date se terminant à la fin en
     * caractère.
     */
    public void setDateTermineFin(String dateTermineFin) {
        this.dateTermineFin = dateTermineFin;
    }

    /**
     * Affecte une deuxième caractéristique.
     *
     * @param caracteristique2 Valeur de la deuxième caractéristique en
     * caractère.
     */
    public void setCaracteristique2(String caracteristique2) {
        this.caracteristique2 = caracteristique2;
    }

    /**
     * Affecte un numéro de dossier.
     *
     * @param numeroDossier Valeur du numéro de dossier en caractère.
     */
    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    /**
     * Affecte un numéro de sujet.
     *
     * @param numeroSujet Valeur du numéro de sujet en caractère.
     */
    public void setNumeroSujet(String numeroSujet) {
        this.numeroSujet = numeroSujet;
    }

    /**
     * Affecte une troisième caractéristique.
     *
     * @param caracteristique3 Valeur de la troisième caractéristique en
     * caractère.
     */
    public void setCaracteristique3(String caracteristique3) {
        this.caracteristique3 = caracteristique3;
    }

    /**
     * Affecte une référence vidéo.
     *
     * @param referenceVideo Valeur de la référence vidéo en caractère.
     */
    public void setReferenceVideo(String referenceVideo) {
        this.referenceVideo = referenceVideo;
    }

    /**
     * Affecte un nom.
     *
     * @param nom Valeur du nom en caractère.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Affecte si le sujet est mis en attache.
     *
     * @param sujetAttache Valeur booléenne indiquant si le sujet est mis en
     * attache.
     */
    public void setSujetAttache(boolean sujetAttache) {
        this.sujetAttache = sujetAttache;
    }

    /**
     * Affecte une cinquième caractéristique.
     *
     * @param caracteristique5 Valeur de la cinquième caractéristique en
     * caractère.
     */
    public void setCaracteristique4(String caracteristique4) {
        this.caracteristique4 = caracteristique4;
    }

    /**
     * Affecte un prénom.
     *
     * @param prenom Valeur du prénom en caractère.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Affecte si le dossier est mis en attache.
     *
     * @param dossierAttache Valeur booléenne indiquant si le dossier est mis
     * en attache.
     */
    public void setDossierAttache(boolean dossierAttache) {
        this.dossierAttache = dossierAttache;
    }

    /**
     * Affecte une origine.
     *
     * @param origine Valeur de l'origine en caractère.
     */
    public void setOrigine(String origine) {
        this.origine = origine;
    }

    /**
     * Affecte un nom ordinaire.
     *
     * @param nomOrdinaire Valeur du nom ordinaire en caractère.
     */
    public void setNomOrdinaire(String nomOrdinaire) {
        this.nomOrdinaire = nomOrdinaire;
    }

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(String lien) {
        this.lien = lien;
    }

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(String lienSite) {
        this.lienSite = lienSite;
    }

    /**
     * Affecte un ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caractère.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche) {
        this.ordreTriRecherche = ordreTriRecherche;
    }

    /**
     * Affecte si la recherche est par ordre croissante.
     *
     * @param ordreCroissantRecherche Valeur booléeanne indiquant si la
     * recherche est par ordre croisante.
     */
    public void setOrdreCroissantRecherche(boolean ordreCroissantRecherche) {
        this.ordreCroissantRecherche = ordreCroissantRecherche;
    }

    /**
     * Affecte le nombre maximum de résultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de résultats
     * de recherche caractère.
     */
    public void setMaximumResultatsRecherche(String maximumResultatsRecherche) {
        this.maximumResultatsRecherche = maximumResultatsRecherche;
    }

    /**
     * Pour le choix du nombre de repérages dans la sélection des dossiers de la Galerie.
     *
     * @param nombreRepérages.
     * caractère.
     */
    public void setNombreReperages(String nombreReperages) {
      this.nombreReperages = nombreReperages;
    }

    /**
     * Ajoute une photo.
     *
     * @param sujet Valeur de la photo à ajouter.
     */
    public void addPhoto(PhotoHtmlForm photo) {
    	photos.add(photo);
    }

    public void init() {
       this.siteApplicable = "";
       this.siteOrigine = "";
       this.severite = "";
       this.race = "";
       hierarchieEGNTC = new HierarchieEGNTC();
       this.dateAjoutDebut = "";
       this.langue = "";
       this.dateAjoutFin = "";
       this.sexe = "";
       this.dateValideDebut = "";
       this.ethnie = "";
       this.statut = String.valueOf(GlobalConstants.Statut.DOSSIER_ACTIF);
       this.dateValideFin = "";
       this.typeJeu = "";
       this.jeu = "";
       this.fonde = "";
       this.dateTermineDebut = "";
       this.caracteristique1 = "";
       this.periode = "";
       this.dateTermineFin = "";
       this.caracteristique2 = "";
       this.numeroDossier = "";
       this.numeroSujet = "";
       this.caracteristique3 = "";
       this.referenceVideo = "";
       this.reference1 = "";
       this.reference2 = "";
       this.enregistrementNumerique = "";
       this.enregistrementConserve = "";
       this.nom = "";
       this.sujetAttache = true;
       this.caracteristique4 = "";
       this.prenom = "";
       this.dossierAttache = false;
       this.origine = "";
       this.nomOrdinaire = "";
       this.lien = "";
	   this.age = "";
	   this.ageEstime = true;
	   this.ageReel = true;
	   this.ageReelPlusMoins = true;
	   this.ageInconnu = false;
	   this.alias = "";
       this.lienSite = "";
       this.ordreTriRecherche = "";
       this.ordreCroissantRecherche = true;
       this.maximumResultatsRecherche = "6";
       this.nombreReperages = "1";
       photos.clear();
       intervenant = "";
       endroit = "";
       localisation = "";
       descriptif = "";
       origine = "";        
       ordreTri = TriGalerieCleRessourceHardListe.TRI_DATE_DEBUT_DOSSIER;
       sensTri = GlobalConstants.SensTri.DESC;
       caracteristique1et2 = GlobalConstants.Operateur.ET;
       caracteristique2et3 = GlobalConstants.Operateur.ET;
       caracteristique3et4 = GlobalConstants.Operateur.ET;
       genererNumeroSequence();
       frequence="30";
       lancerDefilement="false";
    }
    
    // Après une requête il faut générer un nouveau numéro de séquence.
    public void genererNumeroSequence(){
    	sequence = GererTacheUtilisateur.getInstanceOf().obtenirNumero();
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du CriteresRecherchePhotoForm.
     *
     * @return String Valeur de tout les attributs du CriteresRecherchePhotoForm
     * en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRecherchePhotoForm : ");
        stringBuffer.append("siteApplicable = '" + siteApplicable);
        stringBuffer.append("', siteOrigine = '" + siteOrigine);
        stringBuffer.append("', severite = '" + severite);
        stringBuffer.append("', race = '" + race);
        stringBuffer.append("', dateAjoutDebut = '" + dateAjoutDebut);
        stringBuffer.append("', langue = '" + langue);
        stringBuffer.append("', dateAjoutFin = '" + dateAjoutFin);
        stringBuffer.append("', sexe = '" + sexe);
        stringBuffer.append("', dateValideDebut = '" + dateValideDebut);
        stringBuffer.append("', ethnie = '" + ethnie);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', dateValideFin = '" + dateValideFin);
        stringBuffer.append("', typeJeu = '" + typeJeu);
        stringBuffer.append("', jeu = '" + jeu);
        stringBuffer.append("', fonde = '" + fonde);
        stringBuffer.append("', dateTermineDebut = '" + dateTermineDebut);
        stringBuffer.append("', caracteristique1 = '" + caracteristique1);
        stringBuffer.append("', periode = '" + periode);
        stringBuffer.append("', dateTermineFin = '" + dateTermineFin);
        stringBuffer.append("', caracteristique2 = '" + caracteristique2);
        stringBuffer.append("', numeroDossier = '" + numeroDossier);
        stringBuffer.append("', numeroSujet = '" + numeroSujet);
        stringBuffer.append("', caracteristique3 = '" + caracteristique3);
        stringBuffer.append("', referenceVideo = '" + referenceVideo);
        stringBuffer.append("', nom = '" + nom);
        stringBuffer.append("', sujetAttache = '" + sujetAttache);
        stringBuffer.append("', caracteristique4 = '" + caracteristique4);
        stringBuffer.append("', prenom = '" + prenom);
        stringBuffer.append("', dossierAttache = '" + dossierAttache);
        stringBuffer.append("', origine = '" + origine);
        stringBuffer.append("', nomOrdinaire = '" + nomOrdinaire);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', ordreTriRecherche = '" + ordreTriRecherche);
        stringBuffer.append("', ordreCroissantRecherche = '"
            + ordreCroissantRecherche);
        stringBuffer.append("', maximumResultatsRecherche = '"
            + maximumResultatsRecherche);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/**
	 * Gets the age
	 * @return Returns a String
	 */
	public String getAge() {
		return age;
	}
	/**
	 * Sets the age
	 * @param age The age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	public String getDescriptif() {
		return descriptif;
	}
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	public String getEndroit() {
		return endroit;
	}
	public void setEndroit(String endroit) {
		this.endroit = endroit;
	}
	public String getIntervenant() {
		return intervenant;
	}
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public String getEntite() {
		return hierarchieEGNTC.get(HierarchieEGNTC.ENTITE);
	}
	public void setEntite(String entite) {
		hierarchieEGNTC.set(HierarchieEGNTC.ENTITE, entite);
	}
	public String getGenre() {
		return hierarchieEGNTC.get(HierarchieEGNTC.GENRE);
	}
	public void setGenre(String genre) {
		hierarchieEGNTC.set(HierarchieEGNTC.GENRE, genre);
	}	
	public String getCaracteristique1et2() {
		return caracteristique1et2;
	}
	public void setCaracteristique1et2(String caracteristique1et2) {
		this.caracteristique1et2 = caracteristique1et2;
	}
	public String getCaracteristique2et3() {
		return caracteristique2et3;
	}
	public void setCaracteristique2et3(String caracteristique2et3) {
		this.caracteristique2et3 = caracteristique2et3;
	}
	public String getCaracteristique3et4() {
		return caracteristique3et4;
	}
	public void setCaracteristique3et4(String caracteristique3et4) {
		this.caracteristique3et4 = caracteristique3et4;
	}
	public String getOrdreTri() {
		return ordreTri;
	}
	public void setOrdreTri(String ordreTri) {
		this.ordreTri = ordreTri;
	}
	public String getSensTri() {
		return sensTri;
	}
	public void setSensTri(String sensTri) {
		this.sensTri = sensTri;
	}
	public void setPhotos(List photos) {
		this.photos = photos;
	}


	/**
	 * @return enregistrementNumerique
	 */
	public String getEnregistrementNumerique() {
		return enregistrementNumerique;
	}


	/**
	 * @param enregistrementNumerique enregistrementNumerique à définir
	 */
	public void setEnregistrementNumerique(String enregistrementNumerique) {
		this.enregistrementNumerique = enregistrementNumerique;
	}


	/**
	 * @return enregistrementConserve
	 */
	public String getEnregistrementConserve() {
		return enregistrementConserve;
	}


	/**
	 * @param enregistrementConserve enregistrementConserve à définir
	 */
	public void setEnregistrementConserve(String enregistrementConserve) {
		this.enregistrementConserve = enregistrementConserve;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.sujetAttache = false;
		this.dossierAttache = false;
		this.ageEstime = false;
		this.ageReel = false;
		this.ageReelPlusMoins = false;
		this.ageInconnu = false;
	}


	/**
	 * @return reference1
	 */
	public String getReference1() {
		return reference1;
	}


	/**
	 * @param reference1 reference1 à définir
	 */
	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}


	/**
	 * @return reference2
	 */
	public String getReference2() {
		return reference2;
	}


	/**
	 * @param reference2 reference2 à définir
	 */
	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}
	

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}


	/**
	 * @return alias
	 */
	public String getAlias() {
		return alias;
	}


	/**
	 * @param alias alias à définir
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public ListeResultat getListeResultat() {
		return listeResultat;
	}

	public void setListeResultat(ListeResultat listeResultat) {
		this.listeResultat = listeResultat;
	}

	public String getFrequence() {
		return frequence;
	}

	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}

	public String getLancerDefilement() {
		return lancerDefilement;
	}

	public void setLancerDefilement(String lancerDefilement) {
		this.lancerDefilement = lancerDefilement;
	}

	public void assignerDateExecutionGalerie(){
		dateExecutionGalerie = new Date();
	}
	
	public boolean isRexecutionGalerie(){
		
		if (dateExecutionGalerie == null)
			return false;
		
		// C'est donc un retour à la première page
		if ("true".equals(lancerDefilement)
		&& getListeResultat().isPossedePrecedant() == false){
			long nbMinutes = (((new Date()).getTime() - dateExecutionGalerie.getTime())/1000)/60;
			return nbMinutes > 60;
		}
		return false;
	}

    /**
     * @return the ageEstime
     */
    public boolean isAgeEstime()
    {
        return ageEstime;
    }


    public void setAgeEstime(boolean ageEstime){
        this.ageEstime = ageEstime;
    }

    /**
     * @return the ageReel
     */
    public boolean isAgeReel()
    {
        return ageReel;
    }

    /**
     * @param ageReel the ageReel to set
     */
    public void setAgeReel(boolean ageReel)
    {
        this.ageReel = ageReel;
    }

    /**
     * @return the ageReelPlusMoins
     */
    public boolean isAgeReelPlusMoins()
    {
        return ageReelPlusMoins;
    }

    /**
     * @param ageReelPlusMoins the ageReelPlusMoins to set
     */
    public void setAgeReelPlusMoins(boolean ageReelPlusMoins)
    {
        this.ageReelPlusMoins = ageReelPlusMoins;
    }

    /**
     * @return the ageInconnu
     */
    public boolean isAgeInconnu()
    {
        return ageInconnu;
    }
    
    /**
     * @param ageInconnu the ageInconnu to set
     */
    public void setAgeInconnu(boolean ageInconnu)
    {
        this.ageInconnu = ageInconnu;
    }

	public String getTypeJeu() {
		return typeJeu;
	}

	public void setTypeJeu(String typeJeu) {
		this.typeJeu = typeJeu;
	}
	
    
}
