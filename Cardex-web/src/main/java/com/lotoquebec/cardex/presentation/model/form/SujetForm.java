package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.AdresseHtmlForm;
import com.lotoquebec.cardex.presentation.model.CaracteristiquesHtmlForm;
import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.NarrationHtmlForm;
import com.lotoquebec.cardex.presentation.model.SocieteHtmlForm;
import com.lotoquebec.cardex.presentation.model.SujetHtmlForm;
import com.lotoquebec.cardex.presentation.model.VehiculeHtmlForm;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ConfidentialiteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.EthnieCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.LangueCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.RaceCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.RoleCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.SeveriteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.SexeCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeAgeCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.ListeCacheUtils;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Conserve les diff�rentes valeurs relatives au formulatire du sujet.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.14 $, $Date: 2002/04/10 15:53:12 $
 * @see com.lotoquebec.cardex.presentation.model.SujetHtmlForm
 */
public class SujetForm extends ValidatorForm implements SujetHtmlForm, EntiteCardexForm, Serializable, Cloneable {

	private String cle = "";
	private String site = "";
    private String audit = "";
	private String siteDescription = "";
	private String entite = "";
	private String numeroFiche = GlobalConstants.NumeroFiche.DEFAULT;
	private String nom = "";
	private String prenom = "";
	private String alias = "";
	private String dateNaissance = "";
	private String age = "";
	private String reference1 = "";
	private String reference2 = "";
	private String numeroClientEmploye = "";
	private String sexe = "";
	private String sexeDescription = "";
	private String langue = "";
	private String langueDescription = "";
	private String ethnie = "";
	private String ethnieDescription = "";
	private String race = "";
	private String raceDescription = "";
	private String statut = "";
	private String statutDescription = "";
	private String severite = "";
	private String severiteAutres = "";
	private String severiteDescription = "";
	private String severiteDescriptionAutres = "";
    private String severiteCasino = "";
    private String severiteCasinoDescription = "";
	private String numeroAssuranceSociale = "";
	private boolean NASCanadien = true;
	private String numeroAssuranceMaladie = "";
	private String numeroPermisConduire = "";
	private String confidentialite = "";
	private String confidentialiteDescription = "";
	private String passeport = "";
	private String motPasse = "";
	private String confirmationMotPasse = "";
	private String lien = "";
	private String lienSite = "";
	private String lienCreateur = "";
	private String lienDateCreation = "";
	private String role = "";
	private String roleDescription = "";
	private String typeLien = "";
	private String createur = "";
	private String createurDescription = "";
	private String dateCreation = "";
	private String dateFinEmploi = "";
	private String ongletDefaut = "";
	private EntiteCardexForm entiteCardexLiaison = null;
	private boolean nouveau = false;
	private String typeAge = "";
	private String typeAgeDescription = "";
	private CaracteristiquesHtmlForm caracteristiques = null;
	private ArrayList photos = new ArrayList();
	private ListeResultat listeSujets = new ListeResultat();
	private ListeResultat listeDossiers = new ListeResultat();
	private ListeResultat listeNarrations = new ListeResultat();
	private ListeResultat listeSocietes = new ListeResultat();
	private ListeResultat listeAdresses = new ListeResultat();
	private ListeResultat listeVehicules = new ListeResultat();
    //Mars 2011. Ajout de deux champs pour la lecture de l'audit.
    //Les autres champs de l'audit sont identiques � ceux du dossier.
    private String dateChangement= "";
    private String changePar = "";
    //Pour l'ajout d'une photo
    private PhotoForm ajoutPhoto = null;
    //Indique si la donn�e vient de RDD
	private boolean indicateurRdd = false;
    //Mai 2013, ajout de la date de fin d'enqu�te d'un sujet pour affichage dans l'onglet Sujets d'une Soci�t�
    private String dateFinEnquete = "";
    //Ce champ sert au choix de r�le dans le formulaire de sujet
    //lors de l'ajout d'un nouveau v�hicule.
    private String     roleVehicule = "";
    //La liaison entre un sujet RDD et une soci�t� RDD ne peut �tre supprim�.
    private boolean permettreSuppressionLiaison = true;
    
	/**
	 * Constructeur de SujetForm par d�faut.
	 */
	public SujetForm() {}

    // La liaison entre un sujet RDD et une soci�t� RDD ne peut �tre supprim�.
	// Le seul cas o� la liaison n'est pas permise est si le sujet et la soci�t� sont de RDD.
	public void assignerPermettreSuppressionLiaison(SocieteForm societeForm){
		
		if (GlobalConstants.Role.RESPONSABLE == Integer.valueOf(role)){
			
			if (indicateurRdd == true && societeForm.isIndicateurRdd() == true)
				permettreSuppressionLiaison = false;
		}
	}
	
	// Getters


	/**
	 * Retourne la cle.
	 *
	 * @return String Valeur de la cle en caract�re.
	 */
	public String getCle() {
		return this.cle;
	}

	/**
	 * Retourne le site.
	 *
	 * @return String Valeur du site en caract�re.
	 */
	public String getSite() {
		return this.site;
	}

	/**
	 * Retourne le num�ro de fiche.
	 *
	 * @return String Valeur du num�ro de fiche en caract�re.
	 */
	public String getNumeroFiche() {
		return this.numeroFiche;
	}

	/**
	 * Retourne le nom.
	 *
	 * @return String Valeur du nom en caract�re.
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Retourne le prenom.
	 *
	 * @return String Valeur du prenom en caract�re.
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * Retourne l'alias.
	 *
	 * @return String Valeur de l'alias en caract�re.
	 */
	public String getAlias() {
		return this.alias;
	}

	/**
	 * Retourne la date de naissance.
	 *
	 * @return String Valeur de la date de naissance en caract�re.
	 */
	public String getDateNaissance() {
		return this.dateNaissance;
	}

	/**
	 * Retourne l'�ge.
	 *
	 * @return String Valeur de l'�ge en caract�re.
	 */
	public String getAge() {
		return this.age;
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
	 * Retourne le num�ro de client ou employ�.
	 *
	 * @return String Valeur du num�ro de client ou employ� en caract�re.
	 */
	public String getNumeroClientEmploye() {
		return this.numeroClientEmploye;
	}

	/**
	 * Retourne le sexe.
	 *
	 * @return String Valeur du sexe en caract�re.
	 */
	public String getSexe() {
		return this.sexe;
	}

	/**
	 * Retourne la langue.
	 *
	 * @return String Valeur de la langue en caract�re.
	 */
	public String getLangue() {
		return this.langue;
	}

	/**
	 * Retourne l'ethnie.
	 *
	 * @return String Valeur de l'ethnie en caract�re.
	 */
	public String getEthnie() {
		return this.ethnie;
	}

	/**
	 * Retourne la race.
	 *
	 * @return String Valeur de la race en caract�re.
	 */
	public String getRace() {
		return this.race;
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
	 * Retourne la s�v�rit�.
	 *
	 * @return String Valeur de la s�v�rit� en caract�re.
	 */
	public String getSeverite() {
		return this.severite;
	}

	/**
	 * Retourne le num�ro d'assurance sociale.
	 *
	 * @return String Valeur du num�ro d'assurance sociale en caract�re.
	 */
	public String getNumeroAssuranceSociale() {
		return this.numeroAssuranceSociale;
	}

	/**
	 * Retourne le num�ro de permis de conduire.
	 *
	 * @return String Valeur du num�ro de permis de conduire en caract�re.
	 */
	public String getNumeroPermisConduire() {
		return this.numeroPermisConduire;
	}

	/**
	 * Retourne la confidentialit�.
	 *
	 * @return String Valeur de la confidentialit� en caract�re.
	 */
	public String getConfidentialite() {
		return this.confidentialite;
	}

	/**
	 * Retourne le passeport.
	 *
	 * @return String Valeur du passeport en caract�re.
	 */
	public String getPasseport() {
		return this.passeport;
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
	 * Retourne le lien.
	 *
	 * @return String Valeur du lien en caract�re.
	 */
	public String getLien() {
		return this.lien;
	}

	/**
	 * Retourne le lien du site.
	 *
	 * @return String Valeur du lien du site en caract�re.
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
	 * Retourne le cr�ateur du sujet.
	 *
	 * @return String Code du cr�ateur.
	 */
	public String getCreateur() {
		return this.createur;
	}

	/**
	 * Retourne la date de cr�ation.
	 *
	 * @return String Valeur de la date.
	 */
	public String getDateCreation() {
		return this.dateCreation;
	}

	/**
	 * Retourne les narrations associ�s.
	 *
	 * @return Collection
	 */
	public Collection getNarrations() {
		return this.listeNarrations.getResultatComplet();
	}

	/**
	 * Retourne les dossiers associ�s.
	 *
	 * @return Collection
	 */
	public Collection getDossiers() {
		return this.listeDossiers.getResultatComplet();
	}

	/**
	 * Retourne les societes associ�s.
	 *
	 * @return Collection
	 */
	public Collection getSocietes() {
		return this.listeSocietes.getResultatComplet();
	}

	/**
	 * Retourne les sujets associ�s.
	 *
	 * @return Collection
	 */
	public Collection getSujets() {
		return this.listeSujets.getResultatComplet();
	}

	/**
	 * Retourne les adresses associ�s.
	 *
	 * @return Collection
	 */
	public List getAdresses() {
		return this.listeAdresses.getResultatComplet();
	}

	/**
	 * Retourne les caracteristiques associ�es.
	 *
	 * @return Collection
	 */
	public CaracteristiquesHtmlForm getCaracteristiques() {
		return this.caracteristiques;
	}

	/**
	 * Retourne les photos.
	 *
	 * @return Collection Valeur des photos.
	 */
	public Collection getPhotos() {
		return this.photos;
	}

	public Collection getToutesPhotos() {
		List toutesPhotos = new ArrayList();
		Iterator iter = photos.iterator();
		
		while (iter.hasNext()) {
			List list3Photo = (List) iter.next();
			toutesPhotos.addAll( list3Photo );
		}
		return toutesPhotos;
	}	
	
	/**
	 * Retourne les  vehicules associ�s.
	 *
	 * @return Collection
	 */
	public Collection getVehicules() {
		return this.listeVehicules.getResultatComplet();
	}

	/**
	 * Test si le sujet est un nouvellement cr��e.
	 *
	 * @return True si le sujet est nouvellement cr��e.
	 */
	public boolean isNew() {
	  return this.nouveau;
	}

	public String getTypeAge() {
	    return this.typeAge;
	}
	
	// Setters


	/**
	 * Affecte un site.
	 *
	 * @param site Valeur du site en caract�re.
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * Affecte une cle.
	 *
	 * @param cle Valeur de la cle en caract�re.
	 */
	public void setCle(String cle) {
		this.cle = cle;
	}

	/**
	 * Affecte un num�ro de fiche.
	 *
	 * @param numeroFiche Valeur du num�ro de fiche en caract�re.
	 */
	public void setNumeroFiche(String numeroFiche) {
		this.numeroFiche = numeroFiche;
	}

	/**
	 * Affecte un nom.
	 *
	 * @param nom Valeur du nom en caract�re.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Affecte un prenom.
	 *
	 * @param prenom Valeur du prenom en caract�re.
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Affecte un alias.
	 *
	 * @param alias Valeur de l'alias en caract�re.
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Affecte une date de naissance.
	 *
	 * @param dateNaissance Valeur de la date de naissance en caract�re.
	 */
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * Affecte un �ge.
	 *
	 * @param age Valeur de l'�ge en caract�re.
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * Affecte une premi�re r�f�rence.
	 *
	 * @param reference1 Valeur de la premi�re r�f�rence en caract�re.
	 */
	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}

	/**
	 * Affecte une deuxi�me r�f�rence.
	 *
	 * @param reference1 Valeur de la deuxi�me r�f�rence en caract�re.
	 */
	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}

	/**
	 * Affecte un num�ro de client ou employ�.
	 *
	 * @param numeroClientEmploye Valeur du num�ro de client ou employ� en caract�re.
	 */
	public void setNumeroClientEmploye(String numeroClientEmploye) {
		this.numeroClientEmploye = numeroClientEmploye;
	}

	/**
	 * Affecte un sexe.
	 *
	 * @param sexe Valeur du sexe en caract�re.
	 */
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	/**
	 * Affecte une langue.
	 *
	 * @param langue Valeur de la langue en caract�re.
	 */
	public void setLangue(String langue) {
		this.langue = langue;
	}

	/**
	 * Affecte un ethnie.
	 *
	 * @param ethnie Valeur de l'ethnie en caract�re.
	 */
	public void setEthnie(String ethnie) {
		this.ethnie = ethnie;
	}

	/**
	 * Affecte une race.
	 *
	 * @param race Valeur de la race en caract�re.
	 */
	public void setRace(String race) {
		this.race = race;
	}

	/**
	 * Affecte un statut.
	 *
	 * @param statut Valeur du statut en caract�re.
	 */
	public void setStatut(String statut) {
		this.statut = statut;
	}

	/**
	 * Affecte une s�v�rit�.
	 *
	 * @param severite Valeur de la s�v�rit� en caract�re.
	 */
	public void setSeverite(String severite) {
		this.severite = severite;
	}

	/**
	 * Affecte un num�ro d'assurance sociale.
	 *
	 * @param numeroAssuranceSociale Valeur du num�ro d'assurance sociale en caract�re.
	 */
	public void setNumeroAssuranceSociale(String numeroAssuranceSociale) {
		this.numeroAssuranceSociale = numeroAssuranceSociale;
	}

	/**
	 * Affecte un num�ro de permis de conduire.
	 *
	 * @param numeroPermisConduire Valeur du num�ro de permis de conduire en caract�re.
	 */
	public void setNumeroPermisConduire(String numeroPermisConduire) {
		this.numeroPermisConduire = numeroPermisConduire;
	}

	/**
	 * Affecte une confidentialit�.
	 *
	 * @param confidentialite Valeur de la confidentialit� en caract�re.
	 */
	public void setConfidentialite(String confidentialite) {
		this.confidentialite = confidentialite;
	}

	/**
	 * Affecte un passeport.
	 *
	 * @param passeport Valeur du passeport en caract�re.
	 */
	public void setPasseport(String passeport) {
		this.passeport = passeport;
	}

	/**
	 * Affecte un mot de passe.
	 *
	 * @param motPasse Valeur du mot de passe en caract�re.
	 */
	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	/**
	 * Affecte une confirmation de mot de passe.
	 *
	 * @param confirmationMotPasse Valeur de la confirmation du mot de passe en
	 * caract�re.
	 */
	public void setConfirmationMotPasse(String confirmationMotPasse) {
		this.confirmationMotPasse = confirmationMotPasse;
	}

	/**
	 * Affecte un lien.
	 *
	 * @param lien Valeur du lien en caract�re.
	 */
	public void setLien(String lien) {
		this.lien = lien;
	}

	/**
	 * Affecte un lien du site.
	 *
	 * @param lienSite Valeur du lien du site en caract�re.
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
	 * Affecte le cr�ateur du sujet.
	 *
	 * @param createur Code du cr�ateur.
	 */
	public void setCreateur(String createur) {
		this.createur = createur;
	}

	/**
	 * Affecte la date de cr�ation.
	 *
	 * @param dateCreation Date de cr�ation.
	 */
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Ajoute une narrations associ�s.
	 */
	public void addNarration(NarrationHtmlForm narration) {
	  this.listeNarrations.add(narration);
	}

	/**
	 * Ajoute un dossiers associ�s.
	 */
	public void addDossier(DossierHtmlForm dossier) {
		this.listeDossiers.add(dossier);
	}

	/**
	 * Ajoute un societes associ�s.
	 */
	public void addSociete(SocieteHtmlForm societe) {
		this.listeSocietes.add(societe);
	}

	/**
	 * Ajoute un sujets associ�s.
	 */
	public void addSujet(SujetHtmlForm sujet){
		this.listeSujets.add(sujet);
	}

	/**
	 * Ajoute une adresses associ�s.
	 */
	public void addAdresse(AdresseHtmlForm adresse) {
		this.listeAdresses.add(adresse);
	}

	/**
	 * Ajoute une caracteristiques  associ�s.
	 */
	public void setCaracteristiques(CaracteristiquesHtmlForm caracteristique) {
		 this.caracteristiques = caracteristique;
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
	 * Ajoute un  vehicules associ�s.
	 */
	public void addVehicule(VehiculeHtmlForm vehicule) {
		this.listeVehicules.add(vehicule);
	}

	/**
	 * D�termine si le sujet est nouvellement cr�er.
	 *
	 * @param isNew Valeur si le sujet est nouvellement cr�er.
	 */
	public void setNew(boolean nouveau){
		this.nouveau = nouveau;
	}

	/**
	 * R�initialise toute les attributs � leur valeur par d�faut.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void init(CardexAuthenticationSubject subject) {
		  this.cle = "";
		  this.site = "";
		  this.numeroFiche = GlobalConstants.NumeroFiche.DEFAULT;
		  this.nom = "";
		  this.prenom = "";
		  this.alias = "";
		  this.dateNaissance = "";
		  this.age = "";
		  this.reference1 = "";
		  this.reference2 = "";
		  this.numeroClientEmploye = "";
		  this.sexe = "";
		  this.langue = "";
		  this.ethnie = "";
		  this.race = "";
		  this.statut = "";
		  this.severite = "";
		  this.severiteCasino = "";
		  this.severiteAutres = "";
		  this.numeroAssuranceSociale = "";
		  this.numeroAssuranceMaladie = "";
		  this.numeroPermisConduire = "";
		  this.confidentialite = String.valueOf(GlobalConstants.Confidentialite.UN);
		  this.passeport = "";
		  this.motPasse = "";
		  this.confirmationMotPasse = "";
		  this.lien = "";
		  this.lienSite = "";
		  this.lienDateCreation = "";
		  this.role = "";
		  this.typeLien = "";
		  this.createur = "";
		  this.dateCreation = "";
		  this.dateFinEmploi = "";
		  this.typeAge = "";
		  this.indicateurRdd = false;
		  this.permettreSuppressionLiaison = true;
		  this.entiteCardexLiaison = null;
		  this.NASCanadien = true;
          this.changePar = "";
	      this.dateChangement = "";
	      this.ajoutPhoto = new PhotoForm();
	      this.dateFinEnquete = "";
	      assignerOngletDefaut(subject);
	      this.roleVehicule = "";
	}
		  
	public void reset(ActionMapping mapping, HttpServletRequest request) {		  
		  this.indicateurRdd = false;
		  NASCanadien = false;
		  this.ajoutPhoto = new PhotoForm();
	}

	/**
	 * R�initialise des onglets
	 */
	public void resetOnglets() {
		  this.listeNarrations.vider();
		  this.listeDossiers.vider();
		  this.listeSocietes.vider();
		  this.listeSujets.vider();
		  this.listeAdresses.vider();
		  this.caracteristiques = new CaracteristiquesForm();
		  this.photos = new ArrayList();
		  this.listeVehicules.vider();
		  this.ajoutPhoto = new PhotoForm();
	}

	/**
	 * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
	 * attributs du SujetForm.
	 *
	 * @return String Valeur de tout les attributs du SujetForm en caract�re.
	 */
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("[SujetForm : ");
		stringBuffer.append("cle = '" + cle);
		stringBuffer.append("', site = '" + site);
		stringBuffer.append("', numeroFiche = '" + numeroFiche);
		stringBuffer.append("', nom = '" + nom);
		stringBuffer.append("', prenom = '" + prenom);
		stringBuffer.append("', alias = '" + alias);
		stringBuffer.append("', dateNaissance = '" + dateNaissance);
		stringBuffer.append("', age = '" + age);
        stringBuffer.append("', typeAge = '" + typeAge);
		stringBuffer.append("', reference1 = '" + reference1);
		stringBuffer.append("', numeroClientEmploye = '" + numeroClientEmploye);
		stringBuffer.append("', sexe = '" + sexe );
		stringBuffer.append("', langue = '" + langue);
		stringBuffer.append("', ethnie = '" + ethnie);
		stringBuffer.append("', race = '" + race);
		stringBuffer.append("', statut = '" + statut);
		stringBuffer.append("', severite = '" + severite);
		stringBuffer.append("', numeroAssuranceSociale = '" + numeroAssuranceSociale);
		stringBuffer.append("', numeroAssuranceMaladie = '" + numeroAssuranceMaladie);
		stringBuffer.append("', numeroPermisConduire = '" + numeroPermisConduire);
		stringBuffer.append("', confidentialite = '" + confidentialite);
		stringBuffer.append("', passeport = '" + passeport);
		stringBuffer.append("', motPasse = '" + motPasse);
		stringBuffer.append("', confirmationMotPasse = '" + confirmationMotPasse);
		stringBuffer.append("', lien = '" + lien);
		stringBuffer.append("', lienSite = '" + lienSite);
		stringBuffer.append("', role = '" + role);
		stringBuffer.append("', typeLien = '" + typeLien);
		stringBuffer.append("', createur = '" + createur);
		stringBuffer.append("', dateCreation = '" + dateCreation);
		stringBuffer.append("', dateFinEmploi = '" + dateFinEmploi);
		stringBuffer.append("']");
		return stringBuffer.toString();
	}

	/**
	 * @return
	 */
	public String getEntite() {
		return entite;
	}

	/**
	 * @param string
	 */
	public void setEntite(String string) {
		entite = string;
	}

	/**
	 * @return
	 */
	public String getLienCreateur() {
		return lienCreateur;
	}
	
    public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException{
    	ListeCache cache = ListeCache.getInstance();
    	
    	ethnieDescription = cache.obtenirLabel(subject, ethnie, new EthnieCleListeCache(subject));
    	langueDescription = cache.obtenirLabel(subject, langue, new LangueCleListeCache(subject));
    	raceDescription = cache.obtenirLabel(subject, race, new RaceCleListeCache(subject));
    	severiteDescription = cache.obtenirLabel(subject, severite, new SeveriteCleListeCache(subject));
    	severiteDescriptionAutres = cache.obtenirLabel(subject, severiteAutres, new SeveriteCleListeCache(subject));
    	severiteCasinoDescription = cache.obtenirLabel(subject, severiteCasino, new SeveriteCleListeCache(subject));
    	confidentialiteDescription = cache.obtenirLabel(subject, confidentialite, new ConfidentialiteCleListeCache(subject));
    	sexeDescription = cache.obtenirLabel(subject, sexe, new SexeCleListeCache(subject));
    	roleDescription = cache.obtenirLabel(subject, role, new RoleCleListeCache(subject));
		siteDescription = cache.obtenirLabel(subject, site, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, getEntite(), GlobalConstants.ActionSecurite.SELECTION));
		typeAgeDescription = cache.obtenirLabel(subject, typeAge, new TypeAgeCleListeCache(subject));
		statutDescription = cache.obtenirLabel(subject, getStatut(), new StatutCleListeCache(subject, GlobalConstants.ListeCache.Statut.SUJET));
		createurDescription = cache.obtenirLabel(subject, getCreateur(), new IntervenantCle(subject.getLocale().getLanguage()));
    }	

	/**
	 * @param string
	 */
	public void setLienCreateur(String lienCreateur) {
		this.lienCreateur = lienCreateur;
	}
	public String getEthnieDescription() {
		return ethnieDescription;
	}
	public String getLangueDescription() {
		return langueDescription;
	}
	public String getTypeAgeDescription() {
	    return typeAgeDescription;
	}
	public String getRaceDescription() {
		return raceDescription;
	}
	public String getSeveriteDescription() {
		return severiteDescription;
	}
	public String getSeveriteDescriptionAutres() {
		return severiteDescriptionAutres;
	}
	public String getSexeDescription() {
		return sexeDescription;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public ListeResultat getListeSujets() {
		return listeSujets;
	}
	public String getConfidentialiteDescription() {
		return confidentialiteDescription;
	}
	public ListeResultat getListeDossiers() {
		return listeDossiers;
	}
	public ListeResultat getListeAdresses() {
		return listeAdresses;
	}
	public ListeResultat getListeNarrations() {
		return listeNarrations;
	}
	public ListeResultat getListeSocietes() {
		return listeSocietes;
	}
	public ListeResultat getListeVehicules() {
		return listeVehicules;
	}

	public void setTypeAge(String typeAge) {
	    this.typeAge = typeAge;
	}
	
	public EntiteCardexForm getEntiteCardexLiaison() {
		return entiteCardexLiaison;
	}
	public void setEntiteCardexLiaison(EntiteCardexForm entiteCardex) {
		this.entiteCardexLiaison = entiteCardex;
	}


	/**
	 * @return numeroAssuranceMaladie
	 */
	public String getNumeroAssuranceMaladie() {
		return numeroAssuranceMaladie;
	}


	/**
	 * @param numeroAssuranceMaladie numeroAssuranceMaladie � d�finir
	 */
	public void setNumeroAssuranceMaladie(String numeroAssuranceMaladie) {
		this.numeroAssuranceMaladie = numeroAssuranceMaladie;
	}


	/**
	 * @return lienDateCreation
	 */
	public String getLienDateCreation() {
		return lienDateCreation;
	}


	/**
	 * @param lienDateCreation lienDateCreation � d�finir
	 */
	public void setLienDateCreation(String lienDateCreation) {
		this.lienDateCreation = lienDateCreation;
	}

	public boolean isNASCanadien() {
		return NASCanadien;
	}

	public void setNASCanadien(boolean canadien) {
		NASCanadien = canadien;
	}


	/**
	 * @return severiteAutres
	 */
	public String getSeveriteAutres() {
		return severiteAutres;
	}


	/**
	 * @param severiteAutres severiteAutres � d�finir
	 */
	public void setSeveriteAutres(String severiteAutres) {
		this.severiteAutres = severiteAutres;
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


	/**
	 * @param sexeDescription sexeDescription � d�finir
	 */
	public void setSexeDescription(String sexeDescription) {
		this.sexeDescription = sexeDescription;
	}


	/**
	 * @param langueDescription langueDescription � d�finir
	 */
	public void setLangueDescription(String langueDescription) {
		this.langueDescription = langueDescription;
	}

    /**
     * @param typeAgeDescription typeAgeDescription � d�finir
     */
	public void setTypeAgeDescription(String typeAgeDescription) {
	    this.typeAgeDescription = typeAgeDescription;
	}

	/**
	 * @param ethnieDescription ethnieDescription � d�finir
	 */
	public void setEthnieDescription(String ethnieDescription) {
		this.ethnieDescription = ethnieDescription;
	}


	/**
	 * @param raceDescription raceDescription � d�finir
	 */
	public void setRaceDescription(String raceDescription) {
		this.raceDescription = raceDescription;
	}


	/**
	 * @param severiteDescription severiteDescription � d�finir
	 */
	public void setSeveriteDescription(String severiteDescription) {
		this.severiteDescription = severiteDescription;
	}


	/**
	 * @param severiteDescriptionAutres severiteDescriptionAutres � d�finir
	 */
	public void setSeveriteDescriptionAutres(String severiteDescriptionAutres) {
		this.severiteDescriptionAutres = severiteDescriptionAutres;
	}


	/**
	 * @param confidentialiteDescription confidentialiteDescription � d�finir
	 */
	public void setConfidentialiteDescription(String confidentialiteDescription) {
		this.confidentialiteDescription = confidentialiteDescription;
	}


	/**
	 * @param roleDescription roleDescription � d�finir
	 */
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}


	/**
	 * @return statutDescription
	 */
	public String getStatutDescription() {
		return statutDescription;
	}


	/**
	 * @param statutDescription statutDescription � d�finir
	 */
	public void setStatutDescription(String statutDescription) {
		this.statutDescription = statutDescription;
	}
	
	
	private void assignerOngletDefaut(CardexAuthenticationSubject subject){
		
		if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_NARRATIONS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.NARRATION;
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_DOSSIERS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.DOSSIER;
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_SOCIETES_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.SOCIETIES;
	
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_RELATIONS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.SUJET;		
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_ADRESSES_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.ADRESSE;

		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_CARACTERISTIQUES_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.CARACTERISTICS;
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_PHOTOS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.PHOTOS;
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SUJET_VEHICULES_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.VEHICULES;		

		else 
			ongletDefaut = GlobalConstants.Onglet.NARRATION; 
	}

	public String getOngletDefaut() {
		return ongletDefaut;
	}
	
	public Object clone() throws CloneNotSupportedException{
		super.clone();
		SujetForm sujetForm = new SujetForm();
		
		sujetForm.setCle( getCle() );
		sujetForm.setSite( getSite() );
		
		return sujetForm;
	}

	/**
	 * Indique si la donn�e provient de l'audit des changements
	 * @return
	 */
	public String getAudit(){
		return audit;
	}

	/**
	 * @param string
	 */
	public void setAudit(String audit){
		this.audit = audit;
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

	//Retourne l'ann�e de naissance pour l'affichage dans l'onglet Sujet d'une Soci�t�.
	public String getAnneeNaissance() {
		return StringUtils.substring(this.dateNaissance, 0, 4);
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
	 * @return indicateurRdd
	 */
	public boolean isIndicateurRdd() {
		return indicateurRdd;
	}


	/**
	 * @param indicateurRdd indicateurRdd � d�finir
	 */
	public void setIndicateurRdd(boolean indicateurRdd) {
		this.indicateurRdd = indicateurRdd;
	}

	/**
	 * @return dateFinEnquete
	 */
	public String getDateFinEnquete() {
		return dateFinEnquete;
	}


	/**
	 * @param dateFinEnquete dateFinEnquete � d�finir
	 */
	public void setDateFinEnquete(String dateFinEnquete) {
		this.dateFinEnquete = dateFinEnquete;
	}


	public String getSeveriteCasino() {
		return severiteCasino;
	}


	public void setSeveriteCasino(String severiteCasino) {
		this.severiteCasino = severiteCasino;
	}


	public String getSeveriteCasinoDescription() {
		return severiteCasinoDescription;
	}


	public void setSeveriteCasinoDescription(String severiteCasinoDescription) {
		this.severiteCasinoDescription = severiteCasinoDescription;
	}


	public String getDateFinEmploi() {
		return dateFinEmploi;
	}


	public void setDateFinEmploi(String dateFinEmploi) {
		this.dateFinEmploi = dateFinEmploi;
	}


	public String getRoleVehicule() {
		return roleVehicule;
	}


	public void setRoleVehicule(String roleVehicule) {
		this.roleVehicule = roleVehicule;
	}

	public boolean isPermettreSuppressionLiaison() {
		return permettreSuppressionLiaison;
	}

	public void setPermettreSuppressionLiaison(boolean permettreSuppressionLiaison) {
		this.permettreSuppressionLiaison = permettreSuppressionLiaison;
	}

    public boolean isAgeActif() {
        return typeAge.equals(String.valueOf(GlobalConstants.TypeAge.INCONNU)) == false;
    }   

}