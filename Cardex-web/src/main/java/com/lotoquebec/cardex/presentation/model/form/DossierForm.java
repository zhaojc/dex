package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.business.fabrique.dossier.validateur.util.ClientMystereUtils;
import com.lotoquebec.cardex.presentation.model.ConsignationHtmlForm;
import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.EvaluationHtmlForm;
import com.lotoquebec.cardex.presentation.model.InscriptionHtmlForm;
import com.lotoquebec.cardex.presentation.model.JeuxHtmlForm;
import com.lotoquebec.cardex.presentation.model.NarrationHtmlForm;
import com.lotoquebec.cardex.presentation.model.NumeroCardex;
import com.lotoquebec.cardex.presentation.model.PartageHtmlForm;
import com.lotoquebec.cardex.presentation.model.PhotoHtmlForm;
import com.lotoquebec.cardex.presentation.model.SocieteHtmlForm;
import com.lotoquebec.cardex.presentation.model.SuiviHtmlForm;
import com.lotoquebec.cardex.presentation.model.SujetHtmlForm;
import com.lotoquebec.cardex.presentation.model.VehiculeHtmlForm;
import com.lotoquebec.cardex.presentation.model.form.lienCascade.HierarchieEGNTC;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ConfidentialiteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.EndroitCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.FondeCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.LocalisationCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.PeriodeCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.RoleCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.SeveriteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.CategorieCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.TypeCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.GenreConfidentialiteCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantParSiteCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Conserve les diff�rentes valeurs relatives au formulatire du dossier.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.31 $, $Date: 2002/04/18 21:17:52 $
 * @see com.lotoquebec.cardex.presentation.model.DossierHtmlForm
 */
public class DossierForm extends ValidatorForm implements DossierHtmlForm, EntiteCardexForm, Serializable, Cloneable {
    private String       siteOrigine = "";
    private HierarchieEGNTC cascadeEGNTC = new HierarchieEGNTC();
    private String		 genreDescription = "";
    private String		 natureDescription = "";
    private String		 typeDescription = "";
    private String		 categorieDescription = "";
    private String       numeroDossier = "";
    private String       statut = "";
    private String       statutDescription = "";
    private String       fonde = "";
    private NumeroCardex numeroCardex = new NumeroCardex();
    private String       dateDebut = "";
    private String       dateFin = "";
    private String       heureDebut = "";
    private String       heureFin = "";
    private String       mois = "";
    private String       reference1 = "";
    private String       reference2 = "";
    private String       reference3 = "";
    private String       typeVideo = "";
    private String       enregistrementNumerique = "";
    private String       enregistrementConserve = "";
    private String       typeCategorie = "";
    private String       groupesIntervenants = "";
    private String       referenceVideo = "";
    private String       intervenant = ""; //Code
    private String       intervenantDescription = ""; //Nom, pr�nom et Secteur
    private String       origine = "";
    private String       origineDescription = "";
    private String       severite = "";
    private String		 severiteDescription = "";
    private String       confidentialite = "";
    private String       confidentialiteDescription = "";
    private String       hierarchie = "";
    private String       endroit = "";
    private String       endroitDescription = "";
    private String       localisation = "";
    private String       localisationDescription = "";
    private String       descriptif = "";
    private String       cle = "";
    private String       site = "";
    private String 		 siteDescription = "";
	private String       periode = "";
    private String 		 periodeDescription = "";
    private String       motPasse = "";
    private String       confirmationMotPasse = "";
    private String       motPasseCourant = "";
    private String       duree = "";
    private String       dateRapportee = "";
    private String       classe = "";
    private String       race = "";
    private String       dateAssignation = "";
    private String       dateEvenement = "";
    private String       reference4 = "";
    private String       reference5 = "";
    private String       reference6 = "";
    private String       reference7 = "";
    private String       fondeDescription = "";
    private String       lien = "";
    private String       lienSite = "";
	private String 		 lienCreateur = "";
	private String 		 lienDateCreation = "";
    private String       role = "";
    private String       roleDescription = "";
    private String       typeLien = "";
    private String       formulaire = "";
    private String       langue = "";
    private String 		 dateCreation = "";
    private String 		 createur = "";
    private String 		 createurDescription = "";
    private boolean      inscription = false;
    private boolean      modifiable = false;
    private boolean      nouveau = false;
	private String 		 ongletDefaut = "";    
    private String       choixRapport = "";
    private String	choixClasse = ""; // choix de la classe d'ajout de l'onglet service d'urgence
    public JeuxHtmlForm  jeux = null;
    //public PartageHtmlForm  partage = new PartageForm();
    public ArrayList     photos = new ArrayList();
    public ArrayList     piecesJointes = new ArrayList();
    //Les collections suivantes servent � l'impression du rapport uniformis�
    private ArrayList narrationsEnquete = new ArrayList();
    private ArrayList narrationsTitre = new ArrayList();
    private ArrayList narrationsIdentification = new ArrayList();
    private ArrayList narrationsIntroduction = new ArrayList();
    private ArrayList narrationsTable = new ArrayList();
    private ArrayList narrationsConstats = new ArrayList();
    private ArrayList narrationsConclusion = new ArrayList();
    private ArrayList narrationsRecommandations = new ArrayList();
    private ArrayList narrationsAutres1 = new ArrayList();
    private ArrayList narrationsAutres2 = new ArrayList();
    private ArrayList narrationsAutres3 = new ArrayList();
    private List listeDossierALier = new ArrayList(); //DossierForm
    private ListeResultat listeSujets = new ListeResultat();
    private ListeResultat listeDossiers = new ListeResultat();
    private ListeResultat listeNarrations = new ListeResultat();
    private ListeResultat listeBillets = new ListeResultat();
    private ListeResultat listeUrgence = new ListeResultat();
    private ListeResultat listeVehicules = new ListeResultat();
    private ListeResultat listeSocietes = new ListeResultat();
    private ListeResultat listeInscriptions = new ListeResultat();
    private ListeResultat listeSuivis = new ListeResultat();
    private ListeResultat listeConsignations = new ListeResultat();
    private List listeSousCategories = new ArrayList();
    private ListeResultat listePartage = new ListeResultat();
	private ListeResultat listeEvaluations = new ListeResultat();

    //Mars 2011. Ajout de champs pour la lecture de l'audit.
    //Les autres champs de l'audit sont identiques � ceux du dossier.
    private String dateChangement= "";
    private String changePar = "";
    private String numeroCardexTexte = ""; //Sert au formatage du num�ro Cardex sur les rapports Jasper et dans les �crans.
    //Pour l'ajout d'une photo ou d'une pi�ce jointe
    private PhotoForm ajoutPhoto = null;
    private PhotoForm ajoutPieceJointe = null;
    //Ces deux variables servent � distinguer le choix de r�le dans le formulaire de dossier
    //lors de l'ajout d'un nouveau sujet ou d'un nouveau v�hicule.
    private String     roleSujet = "";
    private String     roleVehicule = "";

    // Getters


    /**
     * Retourne le formulaire d'impression
     *
     * @return String le formulaire d'impression
     */
    public String getFormulaire() {
        return this.formulaire;
    }

    /**
     * Retourne la langue d'impression
     *
     * @return String la langue d'impression
     */
    public String getLangue() {
        return this.langue;
    }

    /**
     * Retourne le site d'origine.
     *
     * @return String Valeur du site d'origine en caract�re.
     */
    public String getSiteOrigine() {
        return this.siteOrigine;
    }

    /**
     * Retourne le genre.
     *
     * @return String Valeur du genre en caract�re.
     */
    public String getGenre() {
    	return cascadeEGNTC.get(HierarchieEGNTC.GENRE);
    }

    /**
     * Retourne la nature.
     *
     * @return String Valeur de la nature en caract�re.
     */
    public String getNature() {
    	return cascadeEGNTC.get(HierarchieEGNTC.NATURE);
    }

    /**
     * Retourne le type.
     *
     * @return String Valeur du type en caract�re.
     */
    public String getType() {
    	return cascadeEGNTC.get(HierarchieEGNTC.TYPE);
    }

    /**
     * Retourne la cat�gorie.
     *
     * @return String Valeur de la cat�gorie en caract�re.
     */
    public String getCategorie() {
    	return cascadeEGNTC.get(HierarchieEGNTC.CATEGORIE);
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
     * @return String Valeur du statut en caract�re.
     */
    public String getStatut() {
        return this.statut;
    }

    /**
     * Retourne l'attribut "fonde".
     *
     * @return String Valeur de l'attribut "fonde" en caract�re.
     */
    public String getFonde() {
        return this.fonde;
    }

    /**
     * Retourne le num�ro de cardex.
     *
     * @return NumeroCardex Valeur du num�ro de cardex.
     */
    public NumeroCardex getNumeroCardex() {
        return this.numeroCardex;
    }

    /**
     * Retourne la date de d�but.
     *
     * @return String Valeur de la date de d�but en caract�re.
     */
    public String getDateDebut() {
        return this.dateDebut;
    }

    public String getDateDebut16() {
    	if (StringUtils.isNotEmpty(this.dateDebut))
    		return this.dateDebut.substring(0, 16);
    	return "";
    }
    
    public String getDateDebutLeft(int i) {
    	if (StringUtils.isNotEmpty(this.dateDebut))
    		return this.dateDebut.substring(0, i);
    	return "";
    }    
    
    /**
     * Retourne la date de fin.
     *
     * @return String Valeur de la date de fin en caract�re.
     */
    public String getDateFin() {
        return this.dateFin;
    }
    
    public String getDateFin10() {
    	if (StringUtils.isNotEmpty(this.dateFin))
    		return this.dateFin.substring(0, 10);
    	return "";
    }

    public String getDateFinLeft(int i) {
    	if (StringUtils.isNotEmpty(this.dateFin))
    		return this.dateFin.substring(0, i);
    	return "";
    }
    
    /**
     * Retourne l'heure de d�but.
     *
     * @return String Valeur de l'heure de d�but en caract�re.
     */
    public String getHeureDebut() {
        return this.heureDebut;
    }

    /**
     * Retourne l'heure de fin.
     *
     * @return String Valeur de l'heure de fin en caract�re.
     */
    public String getHeureFin() {
        return this.heureFin;
    }

    /**
     * Retourne le nombre de mois.
     *
     * @return String Valeur du nombre de mois en caract�re.
     */
    public String getMois() {
        return this.mois;
    }

    /**
     * Retourne la premi�re r�f�rence.
     *
     * @return String Valeur de la premi�re r�f�rence en caract�re.
     */
    public String getReference1() {
        return this.reference1;
    }

    /**
     * Retourne la deuxi�me r�f�rence.
     *
     * @return String Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public String getReference2() {
        return this.reference2;
    }

    /**
     * Retourne la troisi�me r�f�rence.
     *
     * @return String Valeur de la troisi�me r�f�rence en caract�re.
     */
    public String getReference3() {
        return this.reference3;
    }

    /**
     * Retourne le type de vid�o.
     *
     * @return String Valeur du type de vid�o en caract�re.
     */
    public String getTypeVideo() {
        return this.typeVideo;
    }

    /**
     * Retourne la r�f�rence de vid�o.
     *
     * @return String Valeur de la r�f�rencede vid�o en caract�re.
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
     * Retourne la confirmation du mot de passe.
     *
     * @return String Valeur de la confirmation du mot de passe en caract�re.
     */
    public String getConfirmationMotPasse() {
        return this.confirmationMotPasse;
    }

    /**
     * Retourne l'origine.
     *
     * @return String Valeur de l'origine en caract�re.
     */
    public String getOrigine() {
        return this.origine;
    }

    /**
     * Retourne la s�v�rit�.
     *
     * @return long Valeur num�rique de la s�v�rit�.
     */
    public String getSeverite() {
        return this.severite;
    }

    /**
     * Retourne la confidentialit�.
     *
     * @return long Valeur num�rique de la confidentialit�.
     */
    public String getConfidentialite() {
        return this.confidentialite;
    }

    /**
     * Retourne la hierarchie.
     *
     * @return long Valeur num�rique de la hierarchie.
     */
    public String getHierarchie() {
        return this.hierarchie;
    }

    /**
     * Retourne l'endroit.
     *
     * @return long Valeur num�rique de l'endroit.
     */
    public String getEndroit() {
        return this.endroit;
    }

    /**
     * Retourne la localisation.
     *
     * @return long Valeur num�rique de la localisation.
     */
    public String getLocalisation() {
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
    public String getCle() {
        return this.cle;
    }

    /**
     * Retourne le site.
     *
     * @return long Valeur num�rique du site.
     */
    public String getSite() {
        return this.site;
    }

    /**
     * Retourne la p�riode.
     *
     * @return long Valeur num�rique de la pr�riode.
     */
    public String getPeriode() {
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
     * @return String Valeur de la date rapport�e en caract�re.
     */
    public String getDateRapportee() {
        return this.dateRapportee;
    }

    /**
     * Retourne la classe.
     *
     * @return long Valeur de la classe.
     */
    public String getClasse() {
        return this.classe;
    }

    /**
     * Retourne la race.
     *
     * @return long Valeur de la race.
     */
    public String getRace() {
        return this.race;
    }

    /**
     * Retourne la date d'assignation.
     *
     * @return String Valeur de la date d'assignation en caract�re.
     */
    public String getDateAssignation() {
        return this.dateAssignation;
    }

    /**
     * Retourne la date d'�v�nement.
     *
     * @return String Valeur de la date d'�v�nement en caract�re.
     */
    public String getDateEvenement() {
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
    public String getLien() {
        return this.lien;
    }

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur num�rique du lien du site.
     */
    public String getLienSite() {
        return this.lienSite;
    }

    /**
     * Retourne le r�le.
     *
     * @return String Valeur num�rique du r�le.
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Retourne le type de lien.
     *
     * @return String Valeur num�rique du type de lien.
     */
    public String getTypeLien() {
        return this.typeLien;
    }

    /**
     * Test si le dossier est avec inscription.
     *
     * @return True si le dossier est avec inscription.
     */
    public boolean isInscription(){
      return this.inscription;
    }

    /**
     * Test si le dossier est modifiable.
     *
     * @return True si le dossier est modifiable.
     */
    public boolean isModifiable(){
      return this.modifiable;
    }

    /**
     * Test si le dossier est un nouvellement cr��e.
     *
     * @return True si le dossier est nouvellement cr��e.
     */
    public boolean isNew() {
      return this.nouveau;
    }

    /**
     * Retourne les narrations.
     *
     * @return Collection Valeur des narrations.
     */
    public Collection getNarrations() {
        return this.listeNarrations.getResultatComplet();
    }

    public Collection getBillets() {
        return this.listeBillets.getResultatComplet();
    }
    
    public Collection getUrgence() {
        return this.listeUrgence.getResultatComplet();
    }

    /**
     * Retourne les dossier.
     *
     * @return Collection Valeur des dossier.
     */
    public Collection getDossiers() {
        return this.listeDossiers.getResultatComplet();
    }

    /**
     * Retourne les consignations.
     *
     * @return Collection Valeur des consignations.
     */
    public Collection getConsignations() {
        return this.listeConsignations.getResultatComplet();
    }

	/**
	 * Retourne les �valuations associ�es.
	 *
	 * @return Collection
	 */
	public List getEvaluations() {
		return this.listeEvaluations.getResultatComplet();
	}
 
    /**
     * Retourne les jeux.
     *
     * @return JeuxHtmlForm Valeur des jeux.
     */
    public JeuxHtmlForm getJeux() {
        return this.jeux;
    }

    /**
     * Retourne les v�hicules.
     *
     * @return Collection Valeur des v�hicules.
     */
    public Collection getVehicules() {
        return this.listeVehicules.getResultatComplet();
    }

    /**
     * Retourne les soci�t�s.
     *
     * @return Collection Valeur des soci�t�s.
     */
    public Collection getSocietes() {
        return this.listeSocietes.getResultatComplet();
    }

    /**
     * Retourne les sujets.
     *
     * @return Collection Valeur des sujets.
     */
    public Collection getSujets() {
        return getListeSujets().getResultatComplet();
    }

    /**
     * Retourne les inscriptions.
     *
     * @return Collection Valeur des inscriptions.
     */
    public Collection getInscriptions() {
        return this.listeInscriptions.getResultatComplet();
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
     * Retourne les pieces jointes.
     *
     * @return Collection Valeur des pieces jointes.
     */
    public Collection getPiecesJointes() {
        return this.piecesJointes;
    }

    /**
     * Retourne les suivis.
     *
     * @return Collection Valeur des suivis.
     */
    public Collection getSuivis() {
        return this.listeSuivis.getResultatComplet();
    }


    // Setters

    /**
     * Affecte un formulaire d'impression
     *
     * @param formulaire Formulaire d'impression
     */
    public void setFormulaire(String formulaire) {
        this.formulaire = formulaire;
    }

    /**
     * Affecte une langue d'impression
     *
     * @param langue Langue d'impression
     */
    public void setLangue(String langue) {
        this.langue = langue;
    }

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur num�rique du site d'origine.
     */
    public void setSiteOrigine(String origine) {
        this.siteOrigine = origine;
    }

    /**
     * Affecte un genre.
     *
     * @param genre Valeur num�rique du genre.
     */
    public void setGenre(String genre) {
    	cascadeEGNTC.set(HierarchieEGNTC.GENRE, genre);
    }

    /**
     * Affecte une nature.
     *
     * @param nature Valeur num�rique de la nature.
     */
    public void setNature(String nature) {
    	cascadeEGNTC.set(HierarchieEGNTC.NATURE, nature);
    }

    /**
     * Affecte un type.
     *
     * @param type Valeur num�rique du type.
     */
    public void setType(String type) {
    	cascadeEGNTC.set(HierarchieEGNTC.TYPE, type);
    }

    /**
     * Affecte une cat�gorie.
     *
     * @param categorie Valeur num�rique de la cat�gorie.
     */
    public void setCategorie(String categorie) {
    	cascadeEGNTC.set(HierarchieEGNTC.CATEGORIE, categorie);
    }

    /**
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String noDossier) {
        this.numeroDossier = noDossier;
    }

    /**
     * Affecte un statut.
     *
     * @param statut Valeur num�rique du statut.
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * Affecte l'attribut "fonde".
     *
     * @param fonde Valeur num�rique de l'attribut "fonde".
     */
    public void setFonde(String fonde) {
        this.fonde = fonde;
    }

    /**
     * Affecte un num�ro de cardex.
     *
     * @param numeroCardex Valeur du num�ro de cardex.
     */
    public void setNumeroCardex(NumeroCardex numeroCardex) {
        this.numeroCardex = numeroCardex;
    }

    /**
     * Affecte un num�ro de cardex.
     *
     * @param stringNumeroCardex Valeur du num�ro de cardex en caract�re.
     */
    public void setNumeroCardex(String stringNumeroCardex) {
        numeroCardex.setNumeroCardex(stringNumeroCardex);
    }

    /**
     * Affecte la date de d�but.
     *
     * @param dateDebut Valeur de la date du d�but (yyyy-MM-dd).
     */
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Affecte la date de fin.
     *
     * @param dateFin Valeur de la date de fin (yyyy-MM-dd).
     */
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Affecte l'heure de d�but.
     *
     * @param heureDebut Valeur de l'heure du d�but (hh:mm:ss).
     */
    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    /**
     * Affecte l'heure de fin.
     *
     * @param heureFin Valeur de l'heure du fin (hh:mm:ss).
     */
    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
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
    public void setTypeVideo(String type) {
        this.typeVideo = type;
    }

    /**
     * Affecte la r�f�rence vid�o.
     *
     * @param referenceVideo Valeur de la r�f�rence vid�o en caract�re.
     */
    public void setReferenceVideo(String reference) {
        this.referenceVideo = reference;
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
     * Affecte la confirmation du mot de passe.
     *
     * @param confirmationMotPasse Valeur de la confirmation du mot de passe en
     * caract�re.
     */
    public void setConfirmationMotPasse(String confirmationMotPasse) {
        this.confirmationMotPasse = confirmationMotPasse;
    }

    /**
     * Affecte de l'origine.
     *
     * @param String Valeur de l'origine en caract�re.
     */
    public void setOrigine(String origine) {
        this.origine = origine;
    }

    /**
     * Affecte la s�v�rit�.
     *
     * @param severite Valeur num�rique de la s�v�rit�.
     */
    public void setSeverite(String severite) {
        this.severite = severite;
    }

    /**
     * Affecte la confidentialit�.
     *
     * @param confidentialite Valeur num�rique de la confidentialit�.
     */
    public void setConfidentialite(String confidentialite) {
        this.confidentialite = confidentialite;
    }

    /**
     * Affecte la hierarchie.
     *
     * @param hierarchie Valeur num�rique de la hierarchie.
     */
    public void setHierarchie(String hierarchie) {
        this.hierarchie = hierarchie;
    }

    /**
     * Affecte l'endroit.
     *
     * @param endroit Valeur num�rique de l'endroit.
     */
    public void setEndroit(String endroit) {
        this.endroit = endroit;
    }

    /**
     * Affecte la localisation.
     *
     * @param localisation Valeur num�rique de la localisation.
     */
    public void setLocalisation(String localisation) {
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
    public void setCle(String cle) {
        this.cle = cle;
    }

    /**
     * Affecte le site.
     *
     * @param site Valeur num�rique le site.
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * Affecte la p�riode.
     *
     * @param periode Valeur num�rique de la p�riode.
     */
    public void setPeriode(String periode) {
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
     * @param dateRapportee Valeur de la date rapport�e en caract�re.
     */
    public void setDateRapportee(String dateRapportee) {
        this.dateRapportee = dateRapportee;
    }

    /**
     * Affecte la classe.
     *
     * @param classe Valeur num�rique de la classe.
     */
    public void setClasse(String classe) {
        this.classe = classe;
    }

    /**
     * Affecte la race.
     *
     * @param race Valeur num�rique de la classe.
     */
    public void setRace(String race) {
        this.race = race;
    }

    /**
     * Affecte de la date d'assignation.
     *
     * @param dateAssignation Valeur de la date d'assignation (yyyy-MM-dd).
     */
    public void setDateAssignation(String dateAssignation) {
        this.dateAssignation = dateAssignation;
    }

    /**
     * Affecte la date d'�v�nement.
     *
     * @param dateEvenement Valeur la date d'�v�nement (yyyy-MM-dd).
     */
    public void setDateEvenement(String dateEvenement) {
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
     * @param lien Valeur num�rique du lien cle.
     */
    public void setLien(String lien) {
        this.lien = lien;
    }

    /**
     * Affecte le lien du site.
     *
     * @param lienSite Valeur num�rique du lien du site.
     */
    public void setLienSite(String lienSite) {
        this.lienSite = lienSite;
    }

    /**
     * Affecte le r�le.
     *
     * @param role Valeur num�rique du r�le.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Affecte le type de lien.
     *
     * @param typeLien Valeur num�rique du type de lien.
     */
    public void setTypeLien(String typeLien) {
        this.typeLien = typeLien;
    }

    /**
     * D�termine si le dossier est avec inscription.
     *
     * @param isInscription Valeur si le dossier est avec inscription.
     */
    public void setInscription(boolean inscription) {
        this.inscription = inscription;
    }

    /**
     * D�termine si le dossier est modifiable.
     *
     * @param isInscription Valeur si le dossier est modifiable.
     */
    public void setModifiable(boolean modifiable){
        this.modifiable = modifiable;
    }

    /**
     * D�termine si le dossier est nouvellement cr�er.
     *
     * @param isNew Valeur si le dossier est nouvellement cr�er.
     */
    public void setNew(boolean nouveau){
        this.nouveau = nouveau;
    }

    /**
     * Ajoute une narration.
     *
     * @param narration Valeur de la narration.
     */
    public void addNarration(NarrationHtmlForm narration) {
        this.listeNarrations.add(narration);
    }

    public void addBillet(BilletForm billetForm) {
        this.listeBillets.add(billetForm);
    }
    
    public void addUrgence(UrgenceForm urgenceForm) {
        this.listeUrgence.add(urgenceForm);
    }

    /**
     * Ajoute un dossier.
     *
     * @param dossier Valeur du dossier.
     */
    public void addDossier(DossierHtmlForm dossier) {
        this.listeDossiers.add(dossier);
    }

	/**
	 * Ajoute une �valuation associ�es.
	 */
	public void addEvaluation(EvaluationHtmlForm evaluation) {
		this.listeEvaluations.add(evaluation);
	}

    /**
     * Ajoute une consignation.
     *
     * @param consignation Valeur de la consignation.
     */
    public void addConsignation(ConsignationHtmlForm consignations) {
        this.listeConsignations.add(consignations);
    }
    
    /**
     * Affecte les jeux.
     *
     * @param jeux Valeur du jeu.
     */
    public void setJeux(JeuxHtmlForm jeux) {
        this.jeux = jeux;
    }

    /**
     * Ajoute un v�hicule.
     *
     * @param vehicule Valeur du v�hicule.
     */
    public void addVehicule(VehiculeHtmlForm vehicule) {
        this.listeVehicules.add(vehicule);
    }

    /**
     * Ajoute une soci�t�.
     *
     * @param societe Valeur de la soci�t�.
     */
    public void addSociete(SocieteHtmlForm societe) {
        this.listeSocietes.add(societe);
    }

    /**
     * Ajoute un sujet.
     *
     * @param sujet Valeur du sujet.
     */
    public void addSujet(SujetHtmlForm sujet) {
    	listeSujets.add( sujet );
    }

    /**
     * Ajoute une inscription.
     *
     * @param inscription Valeur de l'inscription.
     */
    public void addInscription(InscriptionHtmlForm inscription) {
        this.listeInscriptions.add(inscription);
    }

    /**
     * Ajoute une sous liste de photo.
     *
     * @param photos sous liste de photo.
     */
    public void addPhoto(Collection photos) {
        this.photos.add(photos);
    }

    /**
     * Ajoute une  pieces jointes.
     *
     * @param pieceJointes sous liste de pieces jointes.
     */
    public void addPieceJointe(PhotoHtmlForm pieceJointes) {
        this.piecesJointes.add(pieceJointes);
    }

    /**
     * Ajoute un suivi.
     *
     * @param suivi Valeur du suivi.
     */
    public void addSuivi(SuiviHtmlForm suivi) {
        this.listeSuivis.add(suivi);
    }

    /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du DossierForm.
     *
     * @return String Valeur de tout les attributs du DossierForm en caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[DossierForm : ");
        stringBuffer.append("siteOrigine = '" + siteOrigine);
        stringBuffer.append("', genre = '" + getGenre());
        stringBuffer.append("', nature = '" + getNature());
        stringBuffer.append("', type = '" + getType());
        stringBuffer.append("', categorie = '" + getCategorie());
        stringBuffer.append("', numeroDossier = '" + numeroDossier);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', fonde = '" + fonde);
        stringBuffer.append("', numeroCardex = '" + numeroCardex);
        stringBuffer.append("', dateDebut = '" + dateDebut);
        stringBuffer.append("', dateFin = '" + dateFin);
        stringBuffer.append("', heureDebut = '" + heureDebut);
        stringBuffer.append("', heureFin = '" + heureFin);
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
        stringBuffer.append("', confirmationMotPasse = '"
                            + confirmationMotPasse);
        stringBuffer.append("', duree = '" + duree);
        stringBuffer.append("', dateRapportee = '" + dateRapportee);
        stringBuffer.append("', classe = '" + classe);
        stringBuffer.append("', race = '" + race);
        stringBuffer.append("', dateAssignation = '" + dateAssignation);
        stringBuffer.append("', dateEvenement = '" + dateEvenement);
        stringBuffer.append("', reference4 = '" + reference4);
        stringBuffer.append("', reference5 = '" + reference5);
        stringBuffer.append("', reference6 = '" + reference6);
        stringBuffer.append("', reference7 = '" + reference7);
        stringBuffer.append("', fondeDescription = '" + fondeDescription);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', role = '" + role);
        stringBuffer.append("', typeLien = '" + typeLien + "']");
        return stringBuffer.toString();
    }

    /**
     * R�initialise toute les attributs � leur valeur par d�faut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
	      this.ajoutPhoto = new PhotoForm();
	      this.ajoutPieceJointe = new PhotoForm();

    }

    public void assignerConfidentialiteDefaut(CardexAuthenticationSubject subject) throws BusinessResourceException{
    	ListeCache listeCache = ListeCache.getInstance();
    	String condifentialiteDefaut = listeCache.obtenirLabel(subject, getGenre(), new GenreConfidentialiteCle());
    	
    	if (StringUtils.isNotEmpty(condifentialiteDefaut))
    		setConfidentialite(condifentialiteDefaut);
    }
    
    /**
     * R�initialise des onglets
     */
    public void resetOnglets() {
        this.listeNarrations.vider();
        this.listeBillets.vider();
        this.listeUrgence.vider();
        this.listeDossiers.vider();
        this.jeux = new JeuxForm();
        this.listeVehicules.vider();
        this.listeSocietes.vider();
        this.listeSujets.vider();
        this.listeInscriptions.vider();
        this.listePartage.vider();
        this.photos = new ArrayList();
        this.listeSuivis.vider();
        this.piecesJointes = new ArrayList();
		this.listeConsignations.vider();
        this.narrationsEnquete = new ArrayList();
        this.narrationsTitre = new ArrayList();
        this.narrationsIdentification = new ArrayList();
        this.narrationsIntroduction = new ArrayList();
        this.narrationsTable = new ArrayList();
        this.narrationsConstats = new ArrayList();
        this.narrationsConclusion = new ArrayList();
        this.narrationsRecommandations = new ArrayList();
        this.narrationsAutres1 = new ArrayList();
        this.narrationsAutres2 = new ArrayList();
        this.narrationsAutres3 = new ArrayList();
        this.listeSousCategories = new ArrayList();
	    this.ajoutPhoto = new PhotoForm();
	    this.ajoutPieceJointe = new PhotoForm();
	    this.listeEvaluations.vider();
    }

	/**
	 * Gets the entite
	 * @return Returns a String
	 */
	public String getEntite() {
		return cascadeEGNTC.get(HierarchieEGNTC.ENTITE);
	}
	/**
	 * Sets the entite
	 * @param entite The entite to set
	 */
	public void setEntite(String entite) {
		cascadeEGNTC.set(HierarchieEGNTC.ENTITE, entite);
	}

//Les m�thodes suivantes servent au rapport uniformis�
    /**
     * Ajoute une narration.
     *
     * @param narration Valeur de la narration.
     */
    public void addNarrationEnquete(NarrationHtmlForm narration) {
      this.narrationsEnquete.add(narration);
    }

    /**
     * Ajoute une narration.
     *
     * @param narration Valeur de la narration.
     */
    public void addNarrationAutres1(NarrationHtmlForm narration) {
      this.narrationsAutres1.add(narration);
    }
    /**
     * Ajoute une narration.
     *
     * @param narration Valeur de la narration.
     */
    public void addNarrationAutres2(NarrationHtmlForm narration) {
      this.narrationsAutres2.add(narration);
    }
    /**
     * Ajoute une narration.
     *
     * @param narration Valeur de la narration.
     */
    public void addNarrationAutres3(NarrationHtmlForm narration) {
      this.narrationsAutres3.add(narration);
    }
    /**
     * Ajoute une narration.
     *
     * @param narration Valeur de la narration.
     */
    public void addNarrationTitre(NarrationHtmlForm narration) {
      this.narrationsTitre.add(narration);
    }
    /**
     * Ajoute une narration.
     *
     * @param narration Valeur de la narration.
     */
    public void addNarrationTable(NarrationHtmlForm narration) {
      this.narrationsTable.add(narration);
    }
    /**
     * Ajoute une narration.
     *
     * @param narration Valeur de la narration.
     */
    public void addNarrationIdentification(NarrationHtmlForm narration) {
      this.narrationsIdentification.add(narration);
    }
    /**
     * Ajoute une narration.
     *
     * @param narration Valeur de la narration.
     */
    public void addNarrationIntroduction(NarrationHtmlForm narration) {
      this.narrationsIntroduction.add(narration);
    }
    /**
     * Ajoute une narration.
     *
     * @param narration Valeur de la narration.
     */
    public void addNarrationConstats(NarrationHtmlForm narration) {
      this.narrationsConstats.add(narration);
    }
    /**
     * Ajoute une narration.
     *
     * @param narration Valeur de la narration.
     */
    public void addNarrationConclusion(NarrationHtmlForm narration) {
      this.narrationsConclusion.add(narration);
    }
    /**
     * Ajoute une narration.
     *
     * @param narration Valeur de la narration.
     */
    public void addNarrationRecommandations(NarrationHtmlForm narration) {
      this.narrationsRecommandations.add(narration);
    }

	/**
	 * Returns the narrationsAutres1.
	 * @return Collection
	 */
	public Collection getNarrationsAutres1() {
		return narrationsAutres1;
	}

	/**
	 * Returns the narrationsAutres2.
	 * @return Collection
	 */
	public Collection getNarrationsAutres2() {
		return narrationsAutres2;
	}

	/**
	 * Returns the narrationsAutres3.
	 * @return Collection
	 */
	public Collection getNarrationsAutres3() {
		return narrationsAutres3;
	}

	/**
	 * Returns the narrationsConclusion.
	 * @return Collection
	 */
	public Collection getNarrationsConclusion() {
		return narrationsConclusion;
	}

	/**
	 * Returns the narrationsConstats.
	 * @return Collection
	 */
	public Collection getNarrationsConstats() {
		return narrationsConstats;
	}

	/**
	 * Returns the narrationsEnquete.
	 * @return Collection
	 */
	public Collection getNarrationsEnquete() {
		return narrationsEnquete;
	}

	/**
	 * Returns the narrationsIdentification.
	 * @return Collection
	 */
	public Collection getNarrationsIdentification() {
		return narrationsIdentification;
	}

	/**
	 * Returns the narrationsIntroduction.
	 * @return Collection
	 */
	public Collection getNarrationsIntroduction() {
		return narrationsIntroduction;
	}

	/**
	 * Returns the narrationsRecommandations.
	 * @return Collection
	 */
	public Collection getNarrationsRecommandations() {
		return narrationsRecommandations;
	}

	/**
	 * Returns the narrationsTable.
	 * @return Collection
	 */
	public Collection getNarrationsTable() {
		return narrationsTable;
	}

	/**
	 * Returns the narrationsTitre.
	 * @return Collection
	 */
	public Collection getNarrationsTitre() {
		return narrationsTitre;
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

	// on imprime le logo SQC si le site est autre que les ludoplex
	public boolean getImprimerLogoSCQ(){
		boolean isLudoplexTroisRiviere = GlobalConstants.SiteMaisonJeux.LUDOPLEX_TROIS_RIVIERE.equals( getSite() );
		boolean isLudoplexQuebec = GlobalConstants.SiteMaisonJeux.LUDOPLEX_QUEBEC.equals( getSite() );
		
		boolean isDomremy = GlobalConstants.SiteMaisonJeux.DOMREMY.equals( getSite() );
		boolean isCentreCasa = GlobalConstants.SiteMaisonJeux.CENTRE_CASA.equals( getSite() );
		boolean isCentreUbaldVilleneuve = GlobalConstants.SiteMaisonJeux.CENTRE_UBALD_VILLENEUVE.equals( getSite() );
		
		return isLudoplexTroisRiviere == false 
		&& isLudoplexQuebec == false && isDomremy == false
		&& isCentreCasa == false && isCentreUbaldVilleneuve == false;
	}
	public List getListeDossierALier() {
		return listeDossierALier;
	}
	
	public void setListeDossierALier(List listeDossierALier) {
		this.listeDossierALier = listeDossierALier;
	}

	public void addDossierALier(DossierForm dossierForm) {
		listeDossierALier.add( dossierForm );
	}	
	
	public void trierDossierALierNumeroCardex(){
		ComparatorChain chainActif = new ComparatorChain(); 
		chainActif.addComparator(new BeanComparator("stringNumeroCardex")); 
		Collections.sort(listeDossierALier, chainActif);		
	}
	
	public void viderListeDossierALier(){
		listeDossierALier = new ArrayList();
	}
	
	public String getStringNumeroCardex(){
		return numeroCardex.toString();
	}
	
	public String getCategorieDescription() {
		return categorieDescription;
	}
	public String getConfidentialiteDescription() {
		return confidentialiteDescription;
	}
	public String getPeriodeDescription() {
		return periodeDescription;
	}
	public String getSeveriteDescription() {
		return severiteDescription;
	}
	public String getStatutDescription() {
		return statutDescription;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	public String getNatureDescription() {
		return natureDescription;
	}
	
	/* (non-Javadoc)
	 * @see com.lotoquebec.cardex.presentation.model.DossierHtmlForm#init()
	 */
	public void init(CardexAuthenticationSubject subject) {
		siteOrigine = "";
	    cascadeEGNTC = new HierarchieEGNTC();
	    natureDescription = "";
	    typeDescription = "";
	    categorieDescription = "";
	    numeroDossier = "";
	    statut = "";
	    statutDescription = "";
	    fonde = "";
	    numeroCardex = new NumeroCardex();
	    numeroCardexTexte = "";
	    dateDebut = "";
	    dateFin = "";
	    heureDebut = "";
	    heureFin = "";
	    mois = "";
	    reference1 = "";
	    reference2 = "";
	    reference3 = "";
	    typeVideo = "";
	    referenceVideo = "";
	    enregistrementNumerique = "";
        enregistrementConserve = "";
	    intervenant = "";
	    intervenantDescription = "";
	    origine = "";
	    severite = "";
	    severiteDescription = "";
	    confidentialite = "";
	    confidentialiteDescription = "";
	    hierarchie = "";
	    endroit = "";
	    localisation = "";
	    descriptif = "";
	    cle = "";
	    site = "";
	    siteDescription = "";
	    periode = "";
	    periodeDescription = "";
	    motPasse = "";
	    motPasseCourant = "";
	    confirmationMotPasse = "";
	    duree = "";
	    dateRapportee = "";
	    classe = "";
	    race = "";
	    dateAssignation = "";
	    dateEvenement = "";
	    reference4 = "";
	    reference5 = "";
	    reference6 = "";
	    reference7 = "";
	    fondeDescription = "";
	    lien = "";
	    lienSite = "";
		lienCreateur = "";
		lienDateCreation = "";
		role = "";
	    roleDescription = "";
	    typeLien = "";
	    formulaire = "";
	    langue = "";
	    inscription = false;
	    modifiable = false;
	    nouveau = false;
	    jeux = new JeuxForm();
	    photos = new ArrayList();
	    piecesJointes = new ArrayList();
	    createur = "";
	    dateCreation = "";
	    choixRapport = "";
	    narrationsEnquete = new ArrayList();
	    narrationsTitre = new ArrayList();
	    narrationsIdentification = new ArrayList();
	    narrationsIntroduction = new ArrayList();
	    narrationsTable = new ArrayList();
	    narrationsConstats = new ArrayList();
	    narrationsConclusion = new ArrayList();
	    narrationsRecommandations = new ArrayList();
	    narrationsAutres1 = new ArrayList();
	    narrationsAutres2 = new ArrayList();
	    narrationsAutres3 = new ArrayList();
	    listeDossierALier = new ArrayList(); //DossierForm
	    listeSujets = new ListeResultat();
	    listeDossiers = new ListeResultat();
	    listeNarrations = new ListeResultat();
	    listeBillets = new ListeResultat();
	    listeUrgence = new ListeResultat();
	    listeVehicules = new ListeResultat();
	    listeSocietes = new ListeResultat();
	    listeInscriptions = new ListeResultat();
	    listeSuivis = new ListeResultat();
	    listeConsignations = new ListeResultat();	
	    listePartage = new ListeResultat();	
	    listeEvaluations = new ListeResultat();	
	    this.listeSousCategories = new ArrayList();
	    this.ajoutPhoto = new PhotoForm();
	    this.ajoutPieceJointe = new PhotoForm();
        roleSujet = "";
        roleVehicule = "";

	    assignerOngletDefaut(subject);
	}

	public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException {
    	ListeCache cache = ListeCache.getInstance();
    	
    	genreDescription = cache.obtenirLabel(subject, getGenre(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.GENRE, getEntite(), GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER));
    	natureDescription = cache.obtenirLabel(subject, getNature(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.NATURE, getGenre(), GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER));
    	typeDescription = cache.obtenirLabel(subject, getType(), new TypeCleMultiListeCache(subject, getNature()));
		categorieDescription = cache.obtenirLabel(subject, getCategorie(), new CategorieCleMultiListeCache(subject, getType()));
		statutDescription = cache.obtenirLabel(subject, getStatut(), new StatutCleListeCache(subject, GlobalConstants.ListeCache.Statut.DOSSIER));
		severiteDescription = cache.obtenirLabel(subject, getSeverite(), new SeveriteCleListeCache(subject));
		confidentialiteDescription = cache.obtenirLabel(subject, getConfidentialite(), new ConfidentialiteCleListeCache(subject));
		periodeDescription = cache.obtenirLabel(subject, getPeriode(), new PeriodeCleListeCache(subject));
		roleDescription = cache.obtenirLabel(subject, getRole(), new RoleCleListeCache(subject));
		fondeDescription = cache.obtenirLabel(subject, getFonde(), new FondeCleListeCache(subject));
		endroitDescription = cache.obtenirLabel(subject, getEndroit(), new EndroitCleListeCache(subject));
		localisationDescription = cache.obtenirLabel(subject, getLocalisation(), new LocalisationCleListeCache(subject));
		siteDescription = cache.obtenirLabel(subject, getSite(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, getEntite(), GlobalConstants.ActionSecurite.SELECTION));
		intervenantDescription = cache.obtenirLabel(subject, getIntervenant(), new IntervenantParSiteCle(subject.getLocale().getLanguage(), getSite()));
		createurDescription = cache.obtenirLabel(subject, getCreateur(), new IntervenantCle(subject.getLocale().getLanguage()));
    	origineDescription = cache.obtenirLabel(subject, getOrigine(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.ORIGINE, getEntite(), GlobalConstants.ActionSecurite.RECHERCHE_DOSSIER));
	}
	
	public ListeResultat getListeSujets() {
		return listeSujets;
	}
	public ListeResultat getListeEvaluations() {
		return listeEvaluations;
	}
	public ListeResultat getListeDossiers() {
		return listeDossiers;
	}
	public ListeResultat getListeVehicules() {
		return listeVehicules;
	}
	public ListeResultat getListeConsignations() {
		return listeConsignations;
	}
	public ListeResultat getListeInscriptions() {
		return listeInscriptions;
	}
	public ListeResultat getListeNarrations() {
		return listeNarrations;
	}
	public ListeResultat getListeBillets() {
		return listeBillets;
	}	
	public ListeResultat getListeUrgence() {
		return listeUrgence;
	}	
	public ListeResultat getListeSocietes() {
		return listeSocietes;
	}
	public ListeResultat getListeSuivis() {
		return listeSuivis;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	
	public List getListeSousCategories() {
		return listeSousCategories;
	}

	public void setListeSousCategories(List listeSousCategories) {
		this.listeSousCategories = listeSousCategories;
	}

	public void addSousCategorie(SousCategorieForm sousCategorie) {
		this.listeSousCategories.add( sousCategorie );
	}	
	
    /**
     * Retourne les intervenants de l'onglet Partage.
     *
     * @return PartageHtmlForm Valeur Partage.
     */
	public ListeResultat getListePartage() {
		return listePartage;
	}

	public void addPartage(PartageHtmlForm partage) {
		this.listePartage.add( partage );
	}	

    /**
     * Retourne le partage.
     *
     * @return Collection Valeur des intervenants du partage.
     */
    public Collection getPartage() {
        return getListePartage().getResultatComplet();
    }

	
	/**
	 * @return Returns the typeCategorie.
	 */
	public String getTypeCategorie() {
		return typeCategorie;
	}
	/**
	 * @param typeCategorie The typeCategorie to set.
	 */
	public void setTypeCategorie(String typeCategorie) {
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
	
	public void assignerEGNTC(long entite, long genre, long nature, long type, long categorie){
		cascadeEGNTC = new HierarchieEGNTC(String.valueOf(entite), String.valueOf(genre)
	    		, String.valueOf(nature), String.valueOf(type), String.valueOf(categorie) );
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
	
	public int getListeSousCategoriesSize(){
		return listeSousCategories.size();
	}
	
	/*
	 * Si les sous-cat�gories sont approuv�es (tous) il faut permettre la 
	 * modification. 
	 */
	public boolean isPermettreModificationApprobation(){
		
		if (listeSousCategories.size() == 0)
			return false;
		
		Iterator iter = listeSousCategories.iterator();
		
		while (iter.hasNext()) {
			SousCategorieForm sousCategorie = (SousCategorieForm) iter.next();
			
			if (sousCategorie.isApprouve() == false)
				return false;
		}
		
		return true;
	}

	public void addPieceJointe(Collection sublist) {
		piecesJointes.addAll(sublist);
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

	/**
	 * @return D�termine s'il s'agit d'un dossier de rep�rage. Si oui, la valeur sera utilis�e dans l'onglet Sujet du dossier
	 * pour cacher les boutons Lier et Ajouter, afin d'interdire l'acc�s d'un sujet � un dossier de rep�rage. 
	 */
	public boolean getReperage() {
		if((this.getCategorie().equals(GlobalConstants.Categorie.REPERAGE_AUTOEXCLUSION))
				|| (this.getCategorie().equals(GlobalConstants.Categorie.REPERAGE_ACCES_INTERDIT))
				|| (this.getCategorie().equals(GlobalConstants.Categorie.REPERAGE_AVIS_GUET))){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @return dateChangement
	 */
	public String getDateChangement() {
		return dateChangement;
	}

	/**
	 * @param dateChangement dateChangement � d�finir
	 */
	public void setDateChangement(String dateChangement) {
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
	public String getDateCreation() {
		return dateCreation;
	}
	
	/**
	 * @param dateCreation dateCreation � d�finir
	 */
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return createur
	 */
	public String getCreateur() {
		return createur;
	}

	/**
	 * @param createur createur � d�finir
	 */
	public void setCreateur(String createur) {
		this.createur = createur;
	}

	/**
	 * @return endroitDescription
	 */
	public String getEndroitDescription() {
		return endroitDescription;
	}

	/**
	 * @param endroitDescription endroitDescription � d�finir
	 */
	public void setEndroitDescription(String endroitDescription) {
		this.endroitDescription = endroitDescription;
	}

	/**
	 * @return localisationDescription
	 */
	public String getLocalisationDescription() {
		return localisationDescription;
	}

	/**
	 * @param localisationDescription localisationDescription � d�finir
	 */
	public void setLocalisationDescription(String localisationDescription) {
		this.localisationDescription = localisationDescription;
	}

	/**
	 * @param natureDescription natureDescription � d�finir
	 */
	public void setNatureDescription(String natureDescription) {
		this.natureDescription = natureDescription;
	}

	/**
	 * @param typeDescription typeDescription � d�finir
	 */
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	/**
	 * @param categorieDescription categorieDescription � d�finir
	 */
	public void setCategorieDescription(String categorieDescription) {
		this.categorieDescription = categorieDescription;
	}

	/**
	 * @param statutDescription statutDescription � d�finir
	 */
	public void setStatutDescription(String statutDescription) {
		this.statutDescription = statutDescription;
	}

	/**
	 * @param severiteDescription severiteDescription � d�finir
	 */
	public void setSeveriteDescription(String severiteDescription) {
		this.severiteDescription = severiteDescription;
	}

	/**
	 * @param confidentialiteDescription confidentialiteDescription � d�finir
	 */
	public void setConfidentialiteDescription(String confidentialiteDescription) {
		this.confidentialiteDescription = confidentialiteDescription;
	}

	/**
	 * @param periodeDescription periodeDescription � d�finir
	 */
	public void setPeriodeDescription(String periodeDescription) {
		this.periodeDescription = periodeDescription;
	}

	/**
	 * @param roleDescription roleDescription � d�finir
	 */
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	/**
	 * @return genreDescription
	 */
	public String getGenreDescription() {
		return genreDescription;
	}

	/**
	 * @param genreDescription genreDescription � d�finir
	 */
	public void setGenreDescription(String genreDescription) {
		this.genreDescription = genreDescription;
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

	/**
	 * @return numeroCardexTexte
	 */
	public String getNumeroCardexTexte() {
		if(StringUtils.isEmpty(this.numeroCardexTexte)){
			return this.numeroCardex.getSite()+"-"+this.numeroCardex.getDate()+"-"+this.numeroCardex.getSequence();
		}else{
			return this.numeroCardexTexte;
		}
	}

	/**
	 * @param numeroCardexTexte numeroCardexTexte � d�finir
	 */
	public void setNumeroCardexTexte(String numeroCardexTexte) {
		this.numeroCardexTexte = numeroCardexTexte;
	}

	private void assignerOngletDefaut(CardexAuthenticationSubject subject){
		
		if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_NARRATIONS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.NARRATION;

		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_SUJETS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.SUJET;
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_INSCRIPTIONS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.INSCRIPTION;		

		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_PHOTOS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.PHOTOS;
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_SUIVIS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.SUIVI;		
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_BILLETS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.BILLET;		
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_PIECES_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.PIECES_JOINTES;

		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_RELATIONS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.DOSSIER;
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_JEUX_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.JEUX;
				
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_VEHICULES_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.VEHICULES;		

		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_SOCIETES_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.SOCIETIES;

		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_CONSIGNATIONS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.CONSIGNATION;

		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_SOUSCATEGORIES_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.SOUS_CATEGORIES;

		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_PARTAGE_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.PARTAGE;

		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.DOSSIER_EVALUATIONS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.EVALUATIONS;		
		
		else 
			ongletDefaut = GlobalConstants.Onglet.NARRATION; 
	}

	public String getOngletDefaut() {
		return ongletDefaut;
	}
    /**
	 * @return siteDescription
	 */
	public String getSiteDescription() {
		return siteDescription;
	}

	/**
	 * @param siteDescription siteDescription � d�finir
	 */
	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}

	public Object clone() throws CloneNotSupportedException{
		super.clone();
		DossierForm dossierForm = new DossierForm();
		
		dossierForm.setCle( getCle() );
		dossierForm.setSite( getSite() );
		
		return dossierForm;
	}

	/**
	 * @return createurDescription
	 */
	public String getCreateurDescription() {
		return createurDescription;
	}

	/**
	 * @param createurDescription createurDescription � d�finir
	 */
	public void setCreateurDescription(String createurDescription) {
		this.createurDescription = createurDescription;
	}

	/**
	 * @return origineDescription
	 */
	public String getOrigineDescription() {
		return origineDescription;
	}

	/**
	 * @param origineDescription origineDescription � d�finir
	 */
	public void setOrigineDescription(String origineDescription) {
		this.origineDescription = origineDescription;
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

	/**
	 * @return ajoutPhoto
	 */
	public PhotoForm getAjoutPhoto() {
		return ajoutPhoto;
	}

	/**
	 * @param ajoutPhoto ajoutPhoto � d�finir
	 */
	public void setAjoutPhoto(PhotoForm ajoutPhoto) {
		this.ajoutPhoto = ajoutPhoto;
	}
	
	/**
	 * @return ajoutPieceJointe
	 */
	public PhotoForm getAjoutPieceJointe() {
		return ajoutPieceJointe;
	}

	/**
	 * @param ajoutPieceJointe ajoutPieceJointe � d�finir
	 */
	public void setAjoutPieceJointe(PhotoForm ajoutPieceJointe) {
		this.ajoutPieceJointe = ajoutPieceJointe;
	}

	public String getLienDateCreation() {
		return lienDateCreation;
	}

	public void setLienDateCreation(String lienDateCreation) {
		this.lienDateCreation = lienDateCreation;
	}

	public String getRoleSujet() {
		return roleSujet;
	}

	public void setRoleSujet(String roleSujet) {
		this.roleSujet = roleSujet;
	}

	public String getRoleVehicule() {
		return roleVehicule;
	}

	public void setRoleVehicule(String roleVehicule) {
		this.roleVehicule = roleVehicule;
	}
 
	public boolean isAfficherBoutonAjouterBillet(){
		
		return isDossierActif() && isConformiteClientMystere() == false;
	}

	/**
	 * RP0001	Pour un dossier de type "Vente aux mineur", seulement les 
	 * dossiers de cat�gorie infraction ont le bouton "Ajouter" dans l'onglet Billet.
	 * @return
	 */
	private boolean isConformiteClientMystere() {
		
		if (StringUtils.isNotEmpty(getType())
		&& StringUtils.isNotEmpty(getCategorie())
		&& Long.valueOf(getType()) == GlobalConstants.Type.VENTE_MINEUR
		&& ClientMystereUtils.isConforme(Long.valueOf(getCategorie())))
			return true;
		return false;
	}
	
	public boolean isDossierActif(){
		return String.valueOf(GlobalConstants.Statut.DOSSIER_ACTIF).equals(statut);
	}

	public String getChoixClasse() {
		return choixClasse;
	}

	public void setChoixClasse(String choixClasse) {
		this.choixClasse = choixClasse;
	}
	
	
	
}
