package com.lotoquebec.cardex.business.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lotoquebec.cardex.business.Consignation;
import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Inscription;
import com.lotoquebec.cardex.business.Narration;
import com.lotoquebec.cardex.business.Photo;
import com.lotoquebec.cardex.business.Societe;
import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardex.business.Sujet;
import com.lotoquebec.cardex.business.Vehicule;
import com.lotoquebec.cardexCommun.business.vo.LiaisonEntiteVO;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives � un dossier de la couche
 * pr�sentation � la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.33 $, $Date: 2002/04/09 20:44:35 $
 * @see com.lotoquebec.cardex.business.Dossier
 */
public class DossierVO implements Dossier, Serializable {
    private long       siteOrigine = 0;
    private long       genre = 0;
    private long       nature = 0;
    private long       type = 0;
    private long       typeCategorie = 0;
    private long       categorie = 0;
    private String     numeroDossier = "";
    private long       statut = 0;
    private long       fonde = 0;
    private long       entite = 0;
    private String     numeroCardex = "";
    private Timestamp  dateDebut = null;
    private Timestamp  dateFin = null;
    private String     mois = ""; 
    private String     reference1 = "";
    private String     reference2 = "";
    private String     reference3 = "";
    private long       typeVideo = 0;
    private String     referenceVideo = "";
    private String     enregistrementNumerique = "";
    private String     enregistrementConserve = "";
    private String     intervenant = "";
    private String     intervenantDescription = "";
    private long       origine = 0;
    private long       severite = 0;
    private long       confidentialite = 0;
    private long       hierarchie = 0;
    private long       endroit = 0;
    private long       localisation = 0;
    private String     descriptif = "";
    private long       cle = 0;
    private long       site = 0;
    private long       periode = 0;
    private String     groupesIntervenants = "";
    private String     motPasse = "";
    private String     confirmationMotPasse = "";
    private String     motPasseCourant = "";
    private String     duree = "";
    private Timestamp  dateRapportee = null;
    private long       classe = 0;
    private long       race = 0;
    private Timestamp  dateAssignation = null;
    private Timestamp  dateEvenement = null;
    private String     reference4 = "";
    private String     reference5 = "";
    private String     reference6 = "";
    private String     reference7 = "";
    private String     fondeDescription = "";
    private String 	   createur = "";
    private long       lienCle = 0;
    private long       lienSite = 0;
	private String 	   lienCreateur = "";
    private Timestamp  lienDateCreation = null;
    private long       role = 0;
    private long       typeLien = 0;
    private Boolean    inscription = false;
    private Boolean    modifiable = false;
    private Boolean    nouveau = false;
    private String     choixRapport = "";
    private Collection narrations = new ArrayList();
    private Collection dossiers = new ArrayList();
    private Collection jeux = new ArrayList();
    private Set<SousCategorieVO> sousCategories = new HashSet<SousCategorieVO>();
    private Collection vehicules = new ArrayList();
    private Collection societes = new ArrayList();
    private Collection sujets = new ArrayList();
    private Collection inscriptions = new ArrayList();
    private Collection photos = new ArrayList();
    private Collection suivis = new ArrayList();
    private Collection consignations = new ArrayList();

    //Mars 2011. Ajout de deux champs pour la lecture de l'audit.
    //Les autres champs de l'audit sont identiques � ceux du dossier.
    private Timestamp dateChangement= null;
    private String changePar = "";
    private Timestamp dateCreation= null;
    
    private Set<LiaisonEntiteVO> liaisonEntites = new HashSet<LiaisonEntiteVO>();
    
    /**
     * Constructeur de DossierVO par d�faut.
     */
    public DossierVO() {}

    /**
	 * @param cle
	 * @param site
	 */
	public DossierVO(long cle, long site) {
		super();
		this.cle = cle;
		this.site = site;
	}

	// Equals
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cle ^ (cle >>> 32));
		result = prime * result + (int) (site ^ (site >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DossierVO other = (DossierVO) obj;
		if (cle != other.cle)
			return false;
		if (site != other.site)
			return false;
		return true;
	}	
	
    // Getters

	/**
     * Retourne le site d'origine.
     *
     * @return long Valeur num�rique du site d'origine.
     */
    public long getSiteOrigine() {
        return this.siteOrigine;
    }


	/**
     * Retourne le genre.
     *
     * @return long Valeur num�rique du genre.
     */
    public long getGenre() {
        return this.genre;
    }

    /**
     * Retourne la nature.
     *
     * @return long Valeur num�rique de la nature.
     */
    public long getNature() {
        return this.nature;
    }

    /**
     * Retourne le type.
     *
     * @return long Valeur num�rique du type.
     */
    public long getType() {
        return this.type;
    }

    /**
     * Retourne la cat�gorie.
     *
     * @return long Valeur num�rique de la cat�gorie.
     */
    public long getCategorie() {
        return this.categorie;
    }

    /**
     * Retourne le num�ro de dossier.
     *
     * @return String Valeur du num�ro de dossier en caract�re.
     */
    public String getNumeroDossier() {
        return this.numeroDossier;
    }

    /**
     * Retourne le statut.
     *
     * @return long Valeur num�rique du statut.
     */
    public long getStatut() {
        return this.statut;
    }

    /**
     * Retourne l'attribut fonde.
     *
     * @return long Valeur num�rique de l'attribut fonde.
     */
    public long getFonde() {
        return this.fonde;
    }

    /**
     * Retourne le num�ro de cardex.
     *
     * @return String Valeur du num�ro de cardex en caract�re.
     */
    public String getNumeroCardex() {
        return this.numeroCardex;
    }

    /**
     * Retourne la date de d�but.
     *
     * @return Timestamp Valeur de la date de d�but (yyyy-MM-dd).
     */
    public Timestamp getDateDebut() {
        return this.dateDebut;
    }

    /**
     * Retourne la date de fin.
     *
     * @return Timestamp Valeur de la date de fin (yyyy-MM-dd).
     */
    public Timestamp getDateFin() {
        return this.dateFin;
    }

    /**
     * Retourne le nombre de mois.
     *
     * @return String Valeur du nombre de mois en caract�res.
     */
    public String getMois() {
        if (this.dateDebut != null && this.dateFin != null) {
          Calendar begin = new GregorianCalendar();
          Calendar end  = new GregorianCalendar();
          begin.setTime(this.dateDebut);
          end.setTime(this.dateFin);
          long beginNbOfMonths = 12*begin.get(Calendar.YEAR) + begin.get(Calendar.MONTH);
          long endNbOfMonths = 12*end.get(Calendar.YEAR) + end.get(Calendar.MONTH);
          return  Long.toString(endNbOfMonths-beginNbOfMonths);
        }else{
          return "";
        }
    }

    /**
     * Retourne la premi�re r�f�rence.
     *
     * @return String Valeur de la premi�re r�f�rence en caract�res.
     */
    public String getReference1() {
        return this.reference1;
    }

    /**
     * Retourne la deuxi�me r�f�rence.
     *
     * @return String Valeur de la deuxi�me r�f�rence en caract�res.
     */
    public String getReference2() {
        return this.reference2;
    }

    /**
     * Retourne la troisi�me r�f�rence.
     *
     * @return String Valeur de la troisi�me r�f�rence en caract�res.
     */
    public String getReference3() {
        return this.reference3;
    }

    /**
     * Retourne le type vid�o.
     *
     * @return long Valeur num�rique du type vid�o.
     */
    public long getTypeVideo() {
        return this.typeVideo;
    }

    /**
     * Retourne la r�f�rence vid�o.
     *
     * @return String Valeur de la r�f�rence vid�o en caract�re.
     */
    public String getReferenceVideo() {
        return this.referenceVideo;
    }

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant() {
        return this.intervenant;
    }

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public long getOrigine() {
        return this.origine;
    }

    /**
     * Retourne la s�v�rit�.
     *
     * @return long Valeur num�rique de la s�v�rit�.
     */
    public long getSeverite() {
        return this.severite;
    }

    /**
     * Retourne la confidentialit�.
     *
     * @return long Valeur num�rique de la confidentialit�.
     */
    public long getConfidentialite() {
        return this.confidentialite;
    }

    /**
     * Retourne la hierarchie.
     *
     * @return long Valeur num�rique de la hierarchie.
     */
    public long getHierarchie() {
        return this.hierarchie;
    }

    /**
     * Retourne l'endroit.
     *
     * @return long Valeur num�rique de l'endroit.
     */
    public long getEndroit() {
        return this.endroit;
    }

    /**
     * Retourne la localisation.
     *
     * @return long Valeur num�rique de la localisation.
     */
    public long getLocalisation() {
        return this.localisation;
    }

    /**
     * Retourne la descriptif.
     *
     * @return String Valeur du descriptif en caract�re.
     */
    public String getDescriptif() {
        return this.descriptif;
    }

    /**
     * Retourne la cle.
     *
     * @return long Valeur num�rique de la cle.
     */
    public long getCle() {
        return this.cle;
    }

    /**
     * Retourne le site.
     *
     * @return long Valeur num�rique du site.
     */
    public long getSite() {
        return this.site;
    }

    /**
     * Retourne la p�riode.
     *
     * @return long Valeur num�rique de la pr�riode.
     */
    public long getPeriode() {
        return this.periode;
    }

    /**
     * Retourne le mot de passe.
     *
     * @return String Valeur du mot de passe en caract�re.
     */
    public String getMotPasse() {
        return this.motPasse;
    }

    /**
     * Retourne la confirmation du mot de passe.
     *
     * @return String Valeur de la confirmation du mot de passe en caract�re.
     */
    public String getConfirmationMotPasse() {
        return this.confirmationMotPasse;
    }

    /**
     * Affecte la confirmation du mot de passe.
     *
     * @param confirmationMotPasse Valeur de la confirmation du mot de passe en
     * caract�re.
     */
    public void setConfirmationMotPasse(String confirmationMotPasse) {
        this.confirmationMotPasse = confirmationMotPasse;
    }

    /**
     * Retourne la dur�e.
     *
     * @return String Valeur de la dur�e en caract�re.
     */
    public String getDuree() {
        return this.duree;
    }

    /**
     * Retourne la date rapport�e.
     *
     * @return Timestamp Valeur de la date rapport�e (yyyy-MM-dd).
     */
    public Timestamp getDateRapportee() {
        return this.dateRapportee;
    }

    /**
     * Retourne la classe.
     *
     * @return long Valeur de la classe.
     */
    public long getClasse() {
        return this.classe;
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
     * Retourne la date d'assignation.
     *
     * @return Timestamp Valeur de la date d'assignation (yyyy-MM-dd).
     */
    public Timestamp getDateAssignation() {
        return this.dateAssignation;
    }

    /**
     * Retourne la date d'�v�nement.
     *
     * @return Timestamp Valeur de la date d'�v�nement (yyyy-MM-dd).
     */
    public Timestamp getDateEvenement() {
        return this.dateEvenement;
    }

    /**
     * Retourne la quatri�me r�f�rence.
     *
     * @return String Valeur de la quatri�me r�f�rence en caract�re.
     */
    public String getReference4() {
        return this.reference4;
    }

    /**
     * Retourne la cinqui�me r�f�rence.
     *
     * @return String Valeur de la cinqui�me r�f�rence en caract�re.
     */
    public String getReference5() {
        return this.reference5;
    }

    /**
     * Retourne la sixi�me r�f�rence.
     *
     * @return String Valeur de la sixi�me r�f�rence en caract�re.
     */
    public String getReference6() {
        return this.reference6;
    }

    /**
     * Retourne la septi�me r�f�rence.
     *
     * @return String Valeur de la septi�me r�f�rence en caract�re.
     */
    public String getReference7() {
        return this.reference7;
    }

    /**
     * Retourne l'attribut "fonde description".
     *
     * @return String Valeur de l'attribut "fonde description" en caract�re.
     */
    public String getFondeDescription() {
        return this.fondeDescription;
    }

    /**
     * Retourne le lien.
     *
     * @return String Valeur num�rique du lien.
     */
    public long getLien() {
        return this.lienCle;
    }    // Liens de dossiers

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur num�rique du lien du site.
     */
    public long getLienSite() {
        return this.lienSite;
    }

    /**
     * Retourne le r�le.
     *
     * @return String Valeur num�rique du r�le.
     */
    public long getRole() {
        return this.role;
    }

    /**
     * Retourne le type de lien.
     *
     * @return String Valeur num�rique du type de lien.
     */
    public long getTypeLien() {
        return this.typeLien;
    }

    /**
     * Test si le dossier est avec inscription.
     *
     * @return True si le dossier est avec inscription.
     */
    public Boolean isInscription(){
      return this.inscription;
    }

    /**
     * Test si le dossier est modifiable.
     *
     * @return True si le dossier est modifiable.
     */
    public Boolean isModifiable(){
      return this.modifiable;
    }

    /**
     * Test si le dossier est un nouvellement cr��e.
     *
     * @return True si le dossier est nouvellement cr��e.
     */
    public Boolean isNouveau() {
      return this.nouveau;
    }

    /**
     * Retourne les narrations.
     *
     * @return Collection Valeur des narrations.
     */
    public Collection getNarrations() {
        return this.narrations;
    }

    /**
     * Retourne les dossier.
     *
     * @return Collection Valeur des dossier.
     */
    public Collection getDossiers() {
        return this.dossiers;
    }

    /**
     * Retourne les consignations.
     *
     * @return Collection Valeur des consignations.
     */
    public Collection getConsignations() {
        return this.consignations;
    }

    /**
     * Retourne les jeux.
     *
     * @return Collection Valeur des jeux.
     */
    public Collection getJeux() {
        return this.jeux;
    }

    /**
     * Retourne les v�hicules.
     *
     * @return Collection Valeur des v�hicules.
     */
    public Collection getVehicules() {
        return this.vehicules;
    }

    /**
     * Retourne les soci�t�s.
     *
     * @return Collection Valeur des soci�t�s.
     */
    public Collection getSocietes() {
        return this.societes;
    }

    /**
     * Retourne les sujets.
     *
     * @return Collection Valeur des sujets.
     */
    public Collection getSujets() {
        return this.sujets;
    }

    /**
     * Retourne les inscriptions.
     *
     * @return Collection Valeur des inscriptions.
     */
    public Collection getInscriptions() {
        return this.inscriptions;
    }

    /**
     * Retourne les photos.
     *
     * @return Collection Valeur des photos.
     */
    public Collection getPhotos() {
        return this.photos;
    }

    /**
     * Retourne les suivis.
     *
     * @return Collection Valeur des suivis.
     */
    public Collection getSuivis() {
        return this.suivis;
    }


    // Setters


    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur num�rique du site d'origine.
     */
    public void setSiteOrigine(long siteOrigine) {
        this.siteOrigine = siteOrigine;
    }

    /**
     * Affecte un genre.
     *
     * @param genre Valeur num�rique du genre.
     */
    public void setGenre(long genre) {
        this.genre = genre;
    }

    /**
     * Affecte une nature.
     *
     * @param nature Valeur num�rique de la nature.
     */
    public void setNature(long nature) {
        this.nature = nature;
    }

    /**
     * Affecte un type.
     *
     * @param type Valeur num�rique du type.
     */
    public void setType(long type) {
        this.type = type;
    }

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur num�rique de la cat�gorie.
     */
    public void setCategorie(long categorie) {
        this.categorie = categorie;
    }

    /**
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    /**
     * Affecte un statut.
     *
     * @param statut Valeur num�rique du statut.
     */
    public void setStatut(long statut) {
        this.statut = statut;
    }

    /**
     * Affecte l'attribut "fonde".
     *
     * @param fonde Valeur num�rique de l'attribut "fonde".
     */
    public void setFonde(long fonde) {
        this.fonde = fonde;
    }

    /**
     * Affecte un num�ro de cardex.
     *
     * @param numeroCardex Valeur du num�ro de cardex en caract�re.
     */
    public void setNumeroCardex(String numeroCardex) {
        this.numeroCardex = numeroCardex;
    }

    /**
     * Affecte la date de d�but.
     *
     * @param Timestamp Valeur de la date du d�but (yyyy-MM-dd).
     */
    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Affecte la date de fin.
     *
     * @param dateFin Valeur de la date de fin (yyyy-MM-dd).
     */
    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Affecte un nombre de mois.
     *
     * @param mois Valeur du nombre de mois en caract�re.
     */
    public void setMois(String mois) {
        this.mois = mois;
    }

    /**
     * Affecte la premi�re r�f�rence.
     *
     * @param reference1 Valeur de la premi�re r�f�rence en caract�re.
     */
    public void setReference1(String reference1) {
        this.reference1 = reference1;
    }

    /**
     * Affecte la deuxi�me r�f�rence.
     *
     * @param reference2 Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public void setReference2(String reference2) {
        this.reference2 = reference2;
    }

    /**
     * Affecte la troisi�me r�f�rence.
     *
     * @param reference3 Valeur de la troisi�me r�f�rence en caract�re.
     */
    public void setReference3(String reference3) {
        this.reference3 = reference3;
    }

    /**
     * Affecte le type vid�o.
     *
     * @param typeVideo Valeur num�rique du type vid�o.
     */
    public void setTypeVideo(long typeVideo) {
        this.typeVideo = typeVideo;
    }

    /**
     * Affecte la r�f�rence vid�o.
     *
     * @param referenceVideo Valeur de la r�f�rence vid�o en caract�re.
     */
    public void setReferenceVideo(String referenceVideo) {
        this.referenceVideo = referenceVideo;
    }

    /**
     * Affecte l'intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant) {
        this.intervenant = intervenant;
    }

    /**
     * Affecte de l'origine.
     *
     * @param String Valeur de l'origine en caract�re.
     */
    public void setOrigine(long origine) {
        this.origine = origine;
    }

    /**
     * Affecte la s�v�rit�.
     *
     * @param severite Valeur num�rique de la s�v�rit�.
     */
    public void setSeverite(long severite) {
        this.severite = severite;
    }

    /**
     * Affecte la hierarchie.
     *
     * @param hierarchie Valeur num�rique de la hierarchie.
     */
    public void setHierarchie(long hierarchie) {
        this.hierarchie = hierarchie;
    }

    /**
     * Affecte la confidentialit�.
     *
     * @param confidentialite Valeur num�rique de la confidentialit�.
     */
    public void setConfidentialite(long confidentialite) {
        this.confidentialite = confidentialite;
    }

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur num�rique de l'endroit.
     */
    public void setEndroit(long endroit) {
        this.endroit = endroit;
    }

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur num�rique de la localisation.
     */
    public void setLocalisation(long localisation) {
        this.localisation = localisation;
    }

    /**
     * Affecte le descriptif.
     *
     * @param descriptif Valeur du descriptif en caract�re.
     */
    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    /**
     * Affecte la cle.
     *
     * @param cle Valeur num�rique de la cle.
     */
    public void setCle(long cle) {
        this.cle = cle;
    }

    /**
     * Affecte le site.
     *
     * @param site Valeur num�rique le site.
     */
    public void setSite(long site) {
        this.site = site;
    }

    /**
     * Affecte la p�riode.
     *
     * @param periode Valeur num�rique de la p�riode.
     */
    public void setPeriode(long periode) {
        this.periode = periode;
    }

    /**
     * Affecte le mot de passe.
     *
     * @param motPasse Valeur du mot de passe en caract�re.
     */
    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    /**
     * Affecte la dur�e.
     *
     * @param duree Valeur de la dur�e en caract�re.
     */
    public void setDuree(String duree) {
        this.duree = duree;
    }

    /**
     * Affecte de la date rapport�e.
     *
     * @param dateRapportee Valeur de la date rapport�e (yyyy-MM-dd).
     */
    public void setDateRapportee(Timestamp dateRapportee) {
        this.dateRapportee = dateRapportee;
    }

    /**
     * Affecte la classe.
     *
     * @param classe Valeur num�rique de la classe.
     */
    public void setClasse(long classe) {
        this.classe = classe;
    }

    /**
     * Affecte la race.
     *
     * @param race Valeur num�rique de la classe.
     */
    public void setRace(long race) {
        this.race = race;
    }

    /**
     * Affecte de la date d'assignation.
     *
     * @param dateAssignation Valeur de la date d'assignation (yyyy-MM-dd).
     */
    public void setDateAssignation(Timestamp dateAssignation) {
        this.dateAssignation = dateAssignation;
    }

    /**
     * Affecte la date d'�v�nement.
     *
     * @param dateEvenement Valeur la date d'�v�nement (yyyy-MM-dd).
     */
    public void setDateEvenement(Timestamp dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    /**
     * Affecte la quatri�me r�f�rence.
     *
     * @param reference4 Valeur de la quatri�me r�f�rence en caract�re.
     */
    public void setReference4(String reference4) {
        this.reference4 = reference4;
    }

    /**
     * Affecte la cinqui�me r�f�rence.
     *
     * @param reference5 Valeur de la cinqui�me r�f�rence en caract�re.
     */
    public void setReference5(String reference5) {
        this.reference5 = reference5;
    }

    /**
     * Affecte la sixi�me r�f�rence.
     *
     * @param reference6 Valeur de la sixi�me r�f�rence en caract�re.
     */
    public void setReference6(String reference6) {
        this.reference6 = reference6;
    }

    /**
     * Affecte la septi�me r�f�rence.
     *
     * @param reference7 Valeur de la septi�me r�f�rence en caract�re.
     */
    public void setReference7(String reference7) {
        this.reference7 = reference7;
    }

    /**
     * Affecte l'attribut "fonde description".
     *
     * @param fondeDescription Valeur de l'attribut "fonde description" en
     * caract�re.
     */
    public void setFondeDescription(String fondeDescription) {
        this.fondeDescription = fondeDescription;
    }

    /**
     * Affecte le lien cle.
     *
     * @param lienCle Valeur num�rique du lien cle.
     */
    public void setLien(long lienCle) {
        this.lienCle = lienCle;
    }

    /**
     * Affecte le lien du site.
     *
     * @param lienSite Valeur num�rique du lien du site.
     */
    public void setLienSite(long lienSite) {
        this.lienSite = lienSite;
    }

    /**
     * Affecte le r�le.
     *
     * @param role Valeur num�rique du r�le.
     */
    public void setRole(long role) {
        this.role = role;
    }

    /**
     * Affecte le type de lien.
     *
     * @param typeLien Valeur num�rique du type de lien.
     */
    public void setTypeLien(long typeLien) {
        this.typeLien = typeLien;
    }

    /**
     * D�termine si le dossier est avec inscription.
     *
     * @param inscription Valeur si le dossier est avec inscription.
     */
    public void setInscription(Boolean inscription) {
        this.inscription = inscription;
    }

    /**
     * D�termine si le dossier est modifiable.
     *
     * @param inscription Valeur si le dossier est modifiable.
     */
    public void setModifiable(Boolean modifiable){
        this.modifiable = modifiable;
    }

    /**
     * D�termine si le dossier est nouvellement cr�er.
     *
     * @param isNew Valeur si le dossier est nouvellement cr�er.
     */
    public void setNouveau(Boolean nouveau){
        this.nouveau = nouveau;
    }

    /**
     * Ajoute une narration.
     *
     * @param narration Valeur de la narration.
     */
    public void addNarration(Narration narration) {
      this.narrations.add(narration);
    }

    /**
     * Ajoute un dossier.
     *
     * @param dossier Valeur du dossier.
     */
    public void addDossier(Dossier dossier) {
      this.dossiers.add(dossier);
    }

    /**
     * Ajoute un jeu.
     *
     * @param jeu Valeur du jeu.
     */
    public void addJeu(String jeux) {
      this.jeux.add(jeux);
    }

    /**
     * Ajoute un v�hicule.
     *
     * @param vehicule Valeur du v�hicule.
     */
    public void addVehicule(Vehicule vehicule) {
      this.vehicules.add(vehicule);
    }

    /**
     * Ajoute une consignation.
     *
     * @param consignation Valeur de la consignation.
     */
    public void addConsignation(Consignation consignation) {
      this.consignations.add(consignation);
    }

    /**
     * Ajoute une soci�t�.
     *
     * @param societe Valeur de la soci�t�.
     */
    public void addSociete(Societe societe) {
      this.societes.add(societe);
    }

    /**
     * Ajoute un sujet.
     *
     * @param sujet Valeur du sujet.
     */
    public void addSujet(Sujet sujet) {
      this.sujets.add(sujet);
    }

    /**
     * Ajoute une inscription.
     *
     * @param inscription Valeur de l'inscription.
     */
    public void addInscription(Inscription inscription) {
      this.inscriptions.add(inscription);
    }

    /**
     * Ajoute une photo.
     *
     * @param photo Valeur de la photo.
     */
    public void addPhoto(Photo photo) {
      this.photos.add(photo);
    }

    /**
     * Ajoute un suivi.
     *
     * @param suivi Valeur du suivi.
     */
    public void addSuivi(Suivi suivi) {
      this.suivis.add(suivi);
    }

    /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du DossierVO.
     *
     * @return String Valeur de tout les attributs du DossierVO en caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[DossierVO : ");
        stringBuffer.append("siteOrigine = '" + siteOrigine);
        stringBuffer.append("', genre = '" + genre);
        stringBuffer.append("', nature = '" + nature);
        stringBuffer.append("', type = '" + type);
        stringBuffer.append("', categorie = '" + categorie);
        stringBuffer.append("', numeroDossier = '" + numeroDossier);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', fonde = '" + fonde);
        stringBuffer.append("', numeroCardex = '" + numeroCardex );
        stringBuffer.append("', dateDebut = '"
                + TimestampFormat.format(dateDebut));
        stringBuffer.append("', dateFin = '"
                + TimestampFormat.format(dateFin) );
        stringBuffer.append("', mois = '" + mois);
        stringBuffer.append("', reference1 = '" + reference1);
        stringBuffer.append("', reference2 = '" + reference2);
        stringBuffer.append("', reference3 = '" + reference3);
        stringBuffer.append("', typeVideo = '" + typeVideo);
        stringBuffer.append("', referenceVideo = '" + referenceVideo);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("', origine = '" + origine);
        stringBuffer.append("', severite = '" + severite);
        stringBuffer.append("', confidentialite = '" + confidentialite);
        stringBuffer.append("', hierarchie = '" + hierarchie);
        stringBuffer.append("', endroit = '" + endroit);
        stringBuffer.append("', localisation = '" + localisation);
        stringBuffer.append("', descriptif = '" + descriptif);
        stringBuffer.append("', cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', periode = '" + periode);
        stringBuffer.append("', motPasse = '" + motPasse);
        stringBuffer.append("', duree = '" + duree);
        stringBuffer.append("', dateRapportee = '"
                + TimestampFormat.format(dateRapportee) );
        stringBuffer.append("', classe = '" + classe);
        stringBuffer.append("', race = '" + race);
        stringBuffer.append("', dateAssignation = '"
                + TimestampFormat.format(dateAssignation) );
        stringBuffer.append("', dateEvenement = '"
                + TimestampFormat.format(dateEvenement) );
        stringBuffer.append("', reference4 = '" + reference4);
        stringBuffer.append("', reference5 = '" + reference5);
        stringBuffer.append("', reference6 = '" + reference6);
        stringBuffer.append("', reference7 = '" + reference7);
        stringBuffer.append("', fondeDescription = '" + fondeDescription);
        stringBuffer.append("', periode = '" + periode + "']");
        return stringBuffer.toString();
    }

	/**
	 * Gets the entite
	 * @return Returns a long
	 */
	public long getEntite() {
		return entite;
	}
	/**
	 * Sets the entite
	 * @param entite The entite to set
	 */
	public void setEntite(long entite) {
		this.entite = entite;
	}

	/**
	 * @return
	 */
	public String getLienCreateur() {
		return lienCreateur;
	}

	/**
	 * @param string
	 */
	public void setLienCreateur(String lienCreateur) {
		this.lienCreateur = lienCreateur;
	}

	/**
	 * @return Returns the typeCategorie.
	 */
	public long getTypeCategorie() {
		return typeCategorie;
	}
	/**
	 * @param typeCategorie The typeCategorie to set.
	 */
	public void setTypeCategorie(long typeCategorie) {
		this.typeCategorie = typeCategorie;
	}
	/**
	 * @return Returns the groupesIntervenants.
	 */
	public String getGroupesIntervenants() {
		return groupesIntervenants;
	}
	/**
	 * @param groupesIntervenants The groupesIntervenants to set.
	 */
	public void setGroupesIntervenants(String groupesIntervenants) {
		this.groupesIntervenants = groupesIntervenants;
	}


	/**
	 * @return enregistrementNumerique
	 */
	public String getEnregistrementNumerique() {
		return enregistrementNumerique;
	}


	/**
	 * @param enregistrementNumerique enregistrementNumerique � d�finir
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
	 * @param enregistrementConserve enregistrementConserve � d�finir
	 */
	public void setEnregistrementConserve(String enregistrementConserve) {
		this.enregistrementConserve = enregistrementConserve;
	}


	public Set<SousCategorieVO> getSousCategories() {
		return sousCategories;
	}

	public void setSousCategories(Set<SousCategorieVO> sousCategories) {
		this.sousCategories = sousCategories;
	}


	/**
	 * @return motPasseCourant
	 */
	public String getMotPasseCourant() {
		return motPasseCourant;
	}


	/**
	 * @param motPasseCourant motPasseCourant � d�finir
	 */
	public void setMotPasseCourant(String motPasseCourant) {
		this.motPasseCourant = motPasseCourant;
	}

	public String getCreateur() {
		return createur;
	}

	public void setCreateur(String createur) {
		this.createur = createur;
	}

	public long getLienCle() {
		return lienCle;
	}

	/**
	 * @return dateChangement
	 */
	public Timestamp getDateChangement() {
		return dateChangement;
	}

	/**
	 * @param dateChangement dateChangement � d�finir
	 */
	public void setDateChangement(Timestamp dateChangement) {
		this.dateChangement = dateChangement;
	}

	/**
	 * @return changePar
	 */
	public String getChangePar() {
		return changePar;
	}

	/**
	 * @param changePar changePar � d�finir
	 */
	public void setChangePar(String changePar) {
		this.changePar = changePar;
	}

	/**
	 * @return dateCreation
	 */
	public Timestamp getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation dateCreation � d�finir
	 */
	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return intervenantDescription
	 */
	public String getIntervenantDescription() {
		return intervenantDescription;
	}


	/**
	 * @param intervenantDescription intervenantDescription � d�finir
	 */
	public void setIntervenantDescription(String intervenantDescription) {
		this.intervenantDescription = intervenantDescription;
	}

	public Boolean getInscription() {
		return inscription;
	}

	public Boolean getModifiable() {
		return modifiable;
	}

	public Boolean getNouveau() {
		return nouveau;
	}

	/**
	 * @return choixRapport
	 */
	public String getChoixRapport() {
		return choixRapport;
	}


	/**
	 * @param choixRapport choixRapport � d�finir
	 */
	public void setChoixRapport(String choixRapport) {
		this.choixRapport = choixRapport;
	}

	public Timestamp getLienDateCreation() {
		return lienDateCreation;
	}

	public void setLienDateCreation(Timestamp lienDateCreation) {
		this.lienDateCreation = lienDateCreation;
	}

	public Set<LiaisonEntiteVO> getLiaisonEntites() {
		return liaisonEntites;
	}
	
/* Ces se devrait �tre utilis�, mais ne le sont pas...
	public void setLienCle(long lienCle) {
		this.lienCle = lienCle;
	}

	public void setNarrations(Collection narrations) {
		this.narrations = narrations;
	}

	public void setDossiers(Collection dossiers) {
		this.dossiers = dossiers;
	}

	public void setJeux(Collection jeux) {
		this.jeux = jeux;
	}

	public void setVehicules(Collection vehicules) {
		this.vehicules = vehicules;
	}

	public void setSocietes(Collection societes) {
		this.societes = societes;
	}

	public void setSujets(Collection sujets) {
		this.sujets = sujets;
	}

	public void setInscriptions(Collection inscriptions) {
		this.inscriptions = inscriptions;
	}

	public void setPhotos(Collection photos) {
		this.photos = photos;
	}

	public void setSuivis(Collection suivis) {
		this.suivis = suivis;
	}

	public void setConsignations(Collection consignations) {
		this.consignations = consignations;
	}*/
	
}