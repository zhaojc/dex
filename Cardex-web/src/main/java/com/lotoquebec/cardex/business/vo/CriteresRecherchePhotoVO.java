package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.CriteresRecherchePhoto;
import com.lotoquebec.cardexCommun.text.TimestampFormat;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Permet de transiter les informations relatives à la recherche d'une
 * photo de la couche présentation à la couche d'affaire.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.10 $, $Date: 2002/03/19 17:03:07 $
 * @see com.lotoquebec.cardex.business.CriteresRecherchePhoto
 */
public class CriteresRecherchePhotoVO implements CriteresRecherchePhoto {

	// Section sujet
	private String numeroSujet = "";
	private String nomOrdinaire = "";
	private String nom = "";
	private String prenom = "";
	private String alias = "";
	private long race = 0;
	private long langue = 0;
	private long sexe = 0;
	private long ethnie = 0;
	private long age = 0;
    private long caracteristique1 = 0;
    private String caracteristique1et2 = "";
    private long caracteristique2 = 0;
    private String caracteristique2et3 = "";
    private long caracteristique3 = 0;
    private String caracteristique3et4 = "";
    private long caracteristique4 = 0;
    private Boolean sujetAttache = false;
    
    // Section dossier
    private long entite = 0;
    private long siteApplicable = 0;
    private long siteOrigine = 0;
    private long nature = 0;
    private long type = 0;
    private long categorie = 0;
    private String numeroDossier = "";
    private long referenceVideo = 0;
    private String enregistrementNumerique = "";
    private String enregistrementConserve = "";
    private long endroit = 0;
    private long localisation = 0;
    private long origine = 0;
    private String reference1 = "";
    private String reference2 = "";
    private String intervenant = "";
    private long severite = 0;
    private Timestamp dateAjoutDebut = null;
    private Timestamp dateAjoutFin = null;
    private Timestamp dateValideDebut = null;
    private Timestamp dateValideFin = null;
    private Timestamp dateTermineDebut = null;
    private Timestamp dateTermineFin = null;
    private long periode = 0;
    private long fonde = 0;
    private long statut = 0;
    private String descriptif = "";
    private long typeJeu = 0;
    private long jeu = 0;
    private Boolean dossierAttache = false;

    private long lien = 0;
    private long lienSite = 0;
    private String ordreTriRecherche = "";
    private Boolean ordreCroissantRecherche = true;
    private long  maximumResultatsRecherche = 0;
    private String ordreTri = "";
    private String sensTri = "";
    private int sequence = 0;
    private long nombreReperages = 1;    
    private Boolean ageEstime = false;
    private Boolean ageReel = false;
    private Boolean ageReelPlusMoins = false;
    private Boolean ageInconnu = false;

    /**
     * Constructeur de CriteresRecherchePhotoVO par défaut.
     */
    public CriteresRecherchePhotoVO() {}

    // Getters


    /**
     * Retourne le site applicable.
     *
     * @return long Valeur du site applicable.
     */
    public long getSiteApplicable() {
        return this.siteApplicable;
    }

    /**
     * Retourne le site d'origine.
     *
     * @return long Valeur du site d'origine.
     */
    public long getSiteOrigine() {
        return this.siteOrigine;
    }

    /**
     * Retourne la sévérité.
     *
     * @return long Valeur de la sévérité.
     */
    public long getSeverite() {
        return this.severite;
    }

    /**
     * Retourne la race.
     *
     * @return long Valeur de la race.
     */
    public long getRace() {
        return this.race;
    }

    /**
     * Retourne la nature.
     *
     * @return long Valeur de la nature.
     */
    public long getNature() {
        return this.nature;
    }

    /**
     * Retourne la date d'ajout au début.
     *
     * @return Timestamp Valeur de la date d'ajout au début (yyyy-MM-dd).
     */
    public Timestamp getDateAjoutDebut() {
        return this.dateAjoutDebut;
    }

    /**
     * Retourne la langue.
     *
     * @return long Valeur de la langue.
     */
    public long getLangue() {
        return this.langue;
    }

    /**
     * Retourne le type.
     *
     * @return long Valeur du type.
     */
    public long getType() {
        return this.type;
    }

    /**
     * Retourne la date d'ajout à la fin.
     *
     * @return Timestamp Valeur de la date d'ajout à la fin (yyyy-MM-dd).
     */
    public Timestamp getDateAjoutFin() {
        return this.dateAjoutFin;
    }

    /**
     * Retourne le sexe.
     *
     * @return long Valeur du sexe.
     */
    public long getSexe() {
        return this.sexe;
    }

    /**
     * Retourne la catégorie.
     *
     * @return long Valeur de la catégorie.
     */
    public long getCategorie() {
        return this.categorie;
    }

    /**
     * Retourne la date de validité au début.
     *
     * @return Timestamp Valeur de la date de validité au début (yyyy-MM-dd).
     */
    public Timestamp getDateValideDebut() {
        return this.dateValideDebut;
    }

    /**
     * Retourne l'ethnie.
     *
     * @return long Valeur de l'ethnie.
     */
    public long getEthnie() {
        return this.ethnie;
    }

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut.
     */
    public long getStatut() {
        return this.statut;
    }

    /**
     * Retourne la date de validité à la fin.
     *
     * @return Timestamp Valeur de la date de validité à la fin (yyyy-MM-dd).
     */
    public Timestamp getDateValideFin() {
        return this.dateValideFin;
    }

    /**
     * Retourne le jeu.
     *
     * @return long Valeur du jeu.
     */
    public long getJeu() {
        return this.jeu;
    }

    /**
     * Retourne la fondation.
     *
     * @return long Valeur de la fondation.
     */
    public long getFonde() {
        return this.fonde;
    }

    /**
     * Retourne la date se terminant au début.
     *
     * @return Timestamp Valeur de la date se terminant au début (yyyy-MM-dd).
     */
    public Timestamp getDateTermineDebut() {
        return this.dateTermineDebut;
    }

    /**
     * Retourne la première caractéristique.
     *
     * @return long Valeur de la première caractéristique.
     */
    public long getCaracteristique1() {
        return this.caracteristique1;
    }

    /**
     * Retourne la période.
     *
     * @return long Valeur de la période.
     */
    public long getPeriode() {
        return this.periode;
    }

    /**
     * Retourne la date se terminant à la fin.
     *
     * @return Timestamp Valeur de la date se terminant à la fin (yyyy-MM-dd).
     */
    public Timestamp getDateTermineFin() {
        return this.dateTermineFin;
    }

    /**
     * Retourne la deuxième caractéristique.
     *
     * @return long Valeur de la deuxième caractéristique.
     */
    public long getCaracteristique2() {
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
     * @return long Valeur de la troisième caractéristique.
     */
    public long getCaracteristique3() {
        return this.caracteristique3;
    }

    /**
     * Retourne la référence vidéo.
     *
     * @return long Valeur de la référence vidéo.
     */
    public long getReferenceVideo() {
        return this.referenceVideo;
    }

    /**
     * Retourne la quatrième caractéristique.
     *
     * @return long Valeur de la quatrième caractéristique.
     */
    public long getCaracteristique4() {
        return this.caracteristique4;
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
    public Boolean isSujetAttache() {
        return this.sujetAttache;
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
    public Boolean isDossierAttache() {
        return this.dossierAttache;
    }

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine.
     */
    public long getOrigine() {
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
     * @return long Valeur du lien.
     */
    public long getLien() {
        return this.lien;
    }

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite() {
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
     * croissante.
     */
    public Boolean isOrdreCroissantRecherche() {
        return this.ordreCroissantRecherche;
    }

    /**
     * Retourne le nombre maximum de résultats de recherche.
     *
     * @return String Valeur du nombre maximum de résultats de recherche en
     * caractère.
     */
    public long getMaximumResultatsRecherche() {
        return this.maximumResultatsRecherche;
    }

    /**
     * @return ageEstime
     */
    public Boolean isAgeEstime() {
        return ageEstime;
    }
    
    /**
     * @return ageReel
     */
    public Boolean isAgeReel() {
        return ageReel;
    }
    

    /**
     * @return ageReelPlusMoins
     */
    public Boolean isAgeReelPlusMoins() {
        return ageReelPlusMoins;
    }

    
    /**
     * @return ageInconnu
     */
    public Boolean isAgeInconnu() {
        return ageInconnu;
    }

    
    // Setters


    /**
     * Affecte un site applicable.
     *
     * @param siteApplicable Valeur du site applicable.
     */
    public void setSiteApplicable(long siteApplicable) {
        this.siteApplicable = siteApplicable;
    }

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur du site d'origine.
     */
    public void setSiteOrigine(long siteOrigine) {
        this.siteOrigine = siteOrigine;
    }

    /**
     * Affecte une sévérité.
     *
     * @param severite Valeur de la sévérité.
     */
    public void setSeverite(long severite) {
        this.severite = severite;
    }

    /**
     * Affecte une race.
     *
     * @param race Valeur de la race.
     */
    public void setRace(long race) {
        this.race = race;
    }

    /**
     * Affecte une nature.
     *
     * @param nature Valeur de la nature.
     */
    public void setNature(long nature) {
        this.nature = nature;
    }

    /**
     * Affecte une date d'ajout au début.
     *
     * @param dateAjoutDebut Valeur de la date d'ajout au début (yyyy-MM-dd).
     */
    public void setDateAjoutDebut(Timestamp dateAjoutDebut) {
        this.dateAjoutDebut = dateAjoutDebut;
    }

    /**
     * Affecte une langue.
     *
     * @param langue Valeur de la langue.
     */
    public void setLangue(long langue) {
        this.langue = langue;
    }

    /**
     * Affecte un type.
     *
     * @param type Valeur du type.
     */
    public void setType(long type) {
        this.type = type;
    }

    /**
     * Affecte une date d'ajout à la fin.
     *
     * @param dateAjoutFin Valeur de la date d'ajout à la fin (yyyy-MM-dd).
     */
    public void setDateAjoutFin(Timestamp dateAjoutFin) {
        this.dateAjoutFin = dateAjoutFin;
    }

    /**
     * Affecte un sexe.
     *
     * @param sexe Valeur du sexe.
     */
    public void setSexe(long sexe) {
        this.sexe = sexe;
    }

    /**
     * Affecte une catégorie.
     *
     * @param categorie Valeur de la catégorie.
     */
    public void setCategorie(long categorie) {
        this.categorie = categorie;
    }

    /**
     * Affecte une date de validité au début.
     *
     * @param dateValideDebut Valeur de la date de validité au début
     * (yyyy-MM-dd).
     */
    public void setDateValideDebut(Timestamp dateValideDebut) {
        this.dateValideDebut = dateValideDebut;
    }

    /**
     * Affecte un ethnie.
     *
     * @param ethnie Valeur de l'ethnie.
     */
    public void setEthnie(long ethnie) {
        this.ethnie = ethnie;
    }

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut.
     */
    public void setStatut(long statut) {
        this.statut = statut;
    }

    /**
     * Affecte une date de validité à la fin.
     *
     * @param dateValideFin Valeur de la date de validité à la fin (yyyy-MM-dd).
     */
    public void setDateValideFin(Timestamp dateValideFin) {
        this.dateValideFin = dateValideFin;
    }

    /**
     * Affecte un jeu.
     *
     * @param jeu Valeur du jeu.
     */
    public void setJeu(long jeu) {
        this.jeu = jeu;
    }

    /**
     * Affecte une fondation.
     *
     * @param fonde Valeur de la fondation.
     */
    public void setFonde(long fonde) {
        this.fonde = fonde;
    }

    /**
     * Affecte une date se terminant au début.
     *
     * @param dateTermineDebut Valeur de la date se terminant au début
     * (yyyy-MM-dd).
     */
    public void setDateTermineDebut(Timestamp dateTermineDebut) {
        this.dateTermineDebut = dateTermineDebut;
    }

    /**
     * Affecte un première caractéristique.
     *
     * @param caracteristique1 Valeur de la première caractéristique.
     */
    public void setCaracteristique1(long caracteristique1) {
        this.caracteristique1 = caracteristique1;
    }

    /**
     * Affecte une période.
     *
     * @param periode Valeur de la période.
     */
    public void setPeriode(long periode) {
        this.periode = periode;
    }

    /**
     * Affecte une date se terminant à la fin.
     *
     * @param dateTermineFin Valeur de la date se terminant à la fin
     * (yyyy-MM-dd).
     */
    public void setDateTermineFin(Timestamp dateTermineFin) {
        this.dateTermineFin = dateTermineFin;
    }

    /**
     * Affecte une deuxième caractéristique.
     *
     * @param caracteristique2 Valeur de la deuxième caractéristique.
     */
    public void setCaracteristique2(long caracteristique2) {
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
     * @param caracteristique3 Valeur de la troisième caractéristique.
     */
    public void setCaracteristique3(long caracteristique3) {
        this.caracteristique3 = caracteristique3;
    }

    /**
     * Affecte une référence vidéo.
     *
     * @param referenceVideo Valeur de la référence vidéo.
     */
    public void setReferenceVideo(long referenceVideo) {
        this.referenceVideo = referenceVideo;
    }

    /**
     * Affecte une quatrième caractéristique.
     *
     * @param caracteristique4 Valeur de la quatrième caractéristique.
     */
    public void setCaracteristique4(long caracteristique4) {
        this.caracteristique4 = caracteristique4;
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
    public void setSujetAttache(Boolean sujetAttache) {
        this.sujetAttache = sujetAttache;
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
    public void setDossierAttache(Boolean dossierAttache) {
        this.dossierAttache = dossierAttache;
    }

    /**
     * Affecte une origine.
     *
     * @param origine Valeur de l'origine.
     */
    public void setOrigine(long origine) {
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
     * @param lien Valeur du lien.
     */
    public void setLien(long lien) {
        this.lien = lien;
    }

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite) {
        this.lienSite = lienSite;
    }

    /**
     * Affecte l'ordre de tri de recherche.
     *
     * @param ordreTriRecherche Valeur de l'ordre de tri de recherche en
     * caractère.
     */
    public void setOrdreTriRecherche(String ordreTriRecherche) {
        this.ordreTriRecherche = ordreTriRecherche;
    }

    /**
     * Affecte si l'ordre de recherche est croissant.
     *
     * @param ordreCroissantRecherche Valeur booléanne indiquant si l'ordre de
     * recherche est croissante.
     */
    public void setOrdreCroissantRecherche(Boolean ordreCroissantRecherche) {
        this.ordreCroissantRecherche = ordreCroissantRecherche;
    }

    /**
     * Affecte le nombre maximum de résultats de recherche.
     *
     * @param maximumResultatsRecherche Valeur du nombre maximum de résultats
     * de recherche caractère.
     */
    public void setMaximumResultatsRecherche(long maximumResultatsRecherche) {
        this.maximumResultatsRecherche = maximumResultatsRecherche;
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du CriteresRecherchePhotoVO.
     *
     * @return String Valeur de tout les attributs du CriteresRecherchePhotoVO
     * en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[CriteresRecherchePhotoVO : ");
        stringBuffer.append("'siteApplicable = '" + siteApplicable);
        stringBuffer.append("', siteOrigine = '" + siteOrigine);
        stringBuffer.append("', severite = '" + severite);
        stringBuffer.append("', race = '" + race);
        stringBuffer.append("', nature = '" + nature);
        stringBuffer.append("', dateAjoutDebut = '"
                + TimestampFormat.format(dateAjoutDebut));
        stringBuffer.append("', langue = '" + langue);
        stringBuffer.append("', type = '" + type);
        stringBuffer.append("', dateAjoutFin = '"
                + TimestampFormat.format(dateAjoutFin));
        stringBuffer.append("', sexe = '" + sexe);
        stringBuffer.append("', age = '" + age);
        stringBuffer.append("', ageEstime = '" + ageEstime);
        stringBuffer.append("', ageReel = '" + ageReel);
        stringBuffer.append("', ageReelPlusMoins = '" + ageReelPlusMoins);
        stringBuffer.append("', ageInconnu = '" + ageInconnu);        
        stringBuffer.append("', categorie = '" + categorie);
        stringBuffer.append("', dateValideDebut = '"
                + TimestampFormat.format(dateValideDebut));
        stringBuffer.append("', ethnie = '" + ethnie);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', dateValideFin = '"
                + TimestampFormat.format(dateValideFin));
        stringBuffer.append("', jeu = '" + jeu);
        stringBuffer.append("', fonde = '" + fonde);
        stringBuffer.append("', dateTermineDebut = '"
                + TimestampFormat.format(dateTermineDebut));
        stringBuffer.append("', caracteristique1 = '" + caracteristique1);
        stringBuffer.append("', periode = '" + periode);
        stringBuffer.append("', dateTermineFin = '"
                + TimestampFormat.format(dateTermineFin));
        stringBuffer.append("', caracteristique2 = '" + caracteristique2);
        stringBuffer.append("', numeroDossier = '" + numeroDossier);
        stringBuffer.append("', numeroSujet = '" + numeroSujet);
        stringBuffer.append("', caracteristique3 = '" + caracteristique3);
        stringBuffer.append("', referenceVideo = '" + referenceVideo);
        stringBuffer.append("', caracteristique4 = '" + caracteristique4);
        stringBuffer.append("', nom = '" + nom);
        stringBuffer.append("', sujetAttache = '" + sujetAttache);
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
	 * @return Returns a long
	 */
	public long getAge() {
		return age;
	}
	/**
	 * Sets the age
	 * @param age The age to set
	 */
	public void setAge(long age) {
		this.age = age;
	}

	public String getDescriptif() {
		return descriptif;
	}
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	public long getEndroit() {
		return endroit;
	}
	public void setEndroit(long endroit) {
		this.endroit = endroit;
	}
	public String getIntervenant() {
		return intervenant;
	}
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}
	public long getLocalisation() {
		return localisation;
	}
	public void setLocalisation(long localisation) {
		this.localisation = localisation;
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

    /**
     * Indique le nombre de repérages demandés.
     *
     * @param isNombreRepérages.
     * caractère.
     */
    public void setNombreReperages(long nombreReperages) {
      this.nombreReperages = nombreReperages;
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


    /**
     * Pour demander plus d'un repérage (par exemple, sélection des dossiers qui ont plus de 5 repérages).
     *
     * @return long
     */
    public long getNombreReperages() {
        return nombreReperages;
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


	public Boolean getSujetAttache() {
		return sujetAttache;
	}


	public Boolean getDossierAttache() {
		return dossierAttache;
	}


	public Boolean getOrdreCroissantRecherche() {
		return ordreCroissantRecherche;
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

    /**
     * Affecte l'indicateur d'âge estimé.
     *
     * @param ageEstime Indicateur d'âge estimé.
     */
    public void setAgeEstime(Boolean ageEstime) {
        this.ageEstime = ageEstime;
    }


    /**
     * Affecte l'indicateur d'âge rééel.
     *
     * @param ageReel Indicateur d'âge réel.
     */
    public void setAgeReel(Boolean ageReel) {
        this.ageReel = ageReel;        
    }


    /**
     * Affecte l'indicateur d'âge rééel plus ou moins.
     *
     * @param ageReelPlusMoins Indicateur d'âge réel plus ou moins.
     */
    public void setAgeReelPlusMoins(Boolean ageReelPlusMoins) {
        this.ageReelPlusMoins = ageReelPlusMoins;        
    }

    /**
     * Affecte l'indicateur d'âge inconnu.
     *
     * @param ageInconnu Indicateur d'âge inconnu.
     */
    public void setAgeInconnu(Boolean ageInconnu) {
        this.ageInconnu = ageInconnu;
    }
    
    
    /**
     * @return the ageEstime
     */
    public Boolean getAgeEstime()
    {
        return ageEstime;
    }


    /**
     * @return the ageReel
     */
    public Boolean getAgeReel()
    {
        return ageReel;
    }


    /**
     * @return the ageReelPlusMoins
     */
    public Boolean getAgeReelPlusMoins()
    {
        return ageReelPlusMoins;
    }


    /**
     * @return the ageInconnu
     */
    public Boolean getAgeInconnu(){
        return ageInconnu;
    }

	public long getEntite() {
		return entite;
	}

	public void setEntite(long entite) {
		this.entite = entite;
	}

	public long getTypeJeu() {
		return typeJeu;
	}

	public void setTypeJeu(long typeJeu) {
		this.typeJeu = typeJeu;
	}
    
    
}