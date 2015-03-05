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
 * Conserve les différentes valeurs relatives au formulatire du sujet.
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
    //Les autres champs de l'audit sont identiques à ceux du dossier.
    private String dateChangement= "";
    private String changePar = "";
    //Pour l'ajout d'une photo
    private PhotoForm ajoutPhoto = null;
    //Indique si la donnée vient de RDD
	private boolean indicateurRdd = false;
    //Mai 2013, ajout de la date de fin d'enquête d'un sujet pour affichage dans l'onglet Sujets d'une Société
    private String dateFinEnquete = "";
    //Ce champ sert au choix de rôle dans le formulaire de sujet
    //lors de l'ajout d'un nouveau véhicule.
    private String     roleVehicule = "";
    //La liaison entre un sujet RDD et une société RDD ne peut être supprimé.
    private boolean permettreSuppressionLiaison = true;
    
	/**
	 * Constructeur de SujetForm par défaut.
	 */
	public SujetForm() {}

    // La liaison entre un sujet RDD et une société RDD ne peut être supprimé.
	// Le seul cas où la liaison n'est pas permise est si le sujet et la société sont de RDD.
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
	 * @return String Valeur de la cle en caractère.
	 */
	public String getCle() {
		return this.cle;
	}

	/**
	 * Retourne le site.
	 *
	 * @return String Valeur du site en caractère.
	 */
	public String getSite() {
		return this.site;
	}

	/**
	 * Retourne le numéro de fiche.
	 *
	 * @return String Valeur du numéro de fiche en caractère.
	 */
	public String getNumeroFiche() {
		return this.numeroFiche;
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
	 * Retourne le prenom.
	 *
	 * @return String Valeur du prenom en caractère.
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * Retourne l'alias.
	 *
	 * @return String Valeur de l'alias en caractère.
	 */
	public String getAlias() {
		return this.alias;
	}

	/**
	 * Retourne la date de naissance.
	 *
	 * @return String Valeur de la date de naissance en caractère.
	 */
	public String getDateNaissance() {
		return this.dateNaissance;
	}

	/**
	 * Retourne l'âge.
	 *
	 * @return String Valeur de l'âge en caractère.
	 */
	public String getAge() {
		return this.age;
	}

	/**
	 * Retourne la première référence.
	 *
	 * @return String Valeur de la première référence en caractère.
	 */
	public String getReference1() {
		return this.reference1;
	}

	/**
	 * Retourne la deuxième référence.
	 *
	 * @return String Valeur de la deuxième référence en caractère.
	 */
	public String getReference2() {
		return this.reference2;
	}

	/**
	 * Retourne le numéro de client ou employé.
	 *
	 * @return String Valeur du numéro de client ou employé en caractère.
	 */
	public String getNumeroClientEmploye() {
		return this.numeroClientEmploye;
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
	 * Retourne la langue.
	 *
	 * @return String Valeur de la langue en caractère.
	 */
	public String getLangue() {
		return this.langue;
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
	 * Retourne la race.
	 *
	 * @return String Valeur de la race en caractère.
	 */
	public String getRace() {
		return this.race;
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
	 * Retourne la sévérité.
	 *
	 * @return String Valeur de la sévérité en caractère.
	 */
	public String getSeverite() {
		return this.severite;
	}

	/**
	 * Retourne le numéro d'assurance sociale.
	 *
	 * @return String Valeur du numéro d'assurance sociale en caractère.
	 */
	public String getNumeroAssuranceSociale() {
		return this.numeroAssuranceSociale;
	}

	/**
	 * Retourne le numéro de permis de conduire.
	 *
	 * @return String Valeur du numéro de permis de conduire en caractère.
	 */
	public String getNumeroPermisConduire() {
		return this.numeroPermisConduire;
	}

	/**
	 * Retourne la confidentialité.
	 *
	 * @return String Valeur de la confidentialité en caractère.
	 */
	public String getConfidentialite() {
		return this.confidentialite;
	}

	/**
	 * Retourne le passeport.
	 *
	 * @return String Valeur du passeport en caractère.
	 */
	public String getPasseport() {
		return this.passeport;
	}

	/**
	 * Retourne le mot de passe.
	 *
	 * @return String Valeur du mot de passe en caractère.
	 */
	public String getMotPasse() {
		return this.motPasse;
	}

	/**
	 * Retourne la confirmation du mot de passe.
	 *
	 * @return String Valeur de la confirmation du mot de passe en caractère.
	 */
	public String getConfirmationMotPasse() {
		return this.confirmationMotPasse;
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
	 * Retourne le rôle.
	 *
	 * @return String Valeur numérique du rôle.
	 */
	public String getRole() {
		return this.role;
	}

	/**
	 * Retourne le type de lien.
	 *
	 * @return String Valeur numérique du type de lien.
	 */
	public String getTypeLien() {
		return this.typeLien;
	}

	/**
	 * Retourne le créateur du sujet.
	 *
	 * @return String Code du créateur.
	 */
	public String getCreateur() {
		return this.createur;
	}

	/**
	 * Retourne la date de création.
	 *
	 * @return String Valeur de la date.
	 */
	public String getDateCreation() {
		return this.dateCreation;
	}

	/**
	 * Retourne les narrations associés.
	 *
	 * @return Collection
	 */
	public Collection getNarrations() {
		return this.listeNarrations.getResultatComplet();
	}

	/**
	 * Retourne les dossiers associés.
	 *
	 * @return Collection
	 */
	public Collection getDossiers() {
		return this.listeDossiers.getResultatComplet();
	}

	/**
	 * Retourne les societes associés.
	 *
	 * @return Collection
	 */
	public Collection getSocietes() {
		return this.listeSocietes.getResultatComplet();
	}

	/**
	 * Retourne les sujets associés.
	 *
	 * @return Collection
	 */
	public Collection getSujets() {
		return this.listeSujets.getResultatComplet();
	}

	/**
	 * Retourne les adresses associés.
	 *
	 * @return Collection
	 */
	public List getAdresses() {
		return this.listeAdresses.getResultatComplet();
	}

	/**
	 * Retourne les caracteristiques associées.
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
	 * Retourne les  vehicules associés.
	 *
	 * @return Collection
	 */
	public Collection getVehicules() {
		return this.listeVehicules.getResultatComplet();
	}

	/**
	 * Test si le sujet est un nouvellement créée.
	 *
	 * @return True si le sujet est nouvellement créée.
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
	 * @param site Valeur du site en caractère.
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * Affecte une cle.
	 *
	 * @param cle Valeur de la cle en caractère.
	 */
	public void setCle(String cle) {
		this.cle = cle;
	}

	/**
	 * Affecte un numéro de fiche.
	 *
	 * @param numeroFiche Valeur du numéro de fiche en caractère.
	 */
	public void setNumeroFiche(String numeroFiche) {
		this.numeroFiche = numeroFiche;
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
	 * Affecte un prenom.
	 *
	 * @param prenom Valeur du prenom en caractère.
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Affecte un alias.
	 *
	 * @param alias Valeur de l'alias en caractère.
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Affecte une date de naissance.
	 *
	 * @param dateNaissance Valeur de la date de naissance en caractère.
	 */
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * Affecte un âge.
	 *
	 * @param age Valeur de l'âge en caractère.
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * Affecte une première référence.
	 *
	 * @param reference1 Valeur de la première référence en caractère.
	 */
	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}

	/**
	 * Affecte une deuxième référence.
	 *
	 * @param reference1 Valeur de la deuxième référence en caractère.
	 */
	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}

	/**
	 * Affecte un numéro de client ou employé.
	 *
	 * @param numeroClientEmploye Valeur du numéro de client ou employé en caractère.
	 */
	public void setNumeroClientEmploye(String numeroClientEmploye) {
		this.numeroClientEmploye = numeroClientEmploye;
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
	 * Affecte une langue.
	 *
	 * @param langue Valeur de la langue en caractère.
	 */
	public void setLangue(String langue) {
		this.langue = langue;
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
	 * Affecte une race.
	 *
	 * @param race Valeur de la race en caractère.
	 */
	public void setRace(String race) {
		this.race = race;
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
	 * Affecte une sévérité.
	 *
	 * @param severite Valeur de la sévérité en caractère.
	 */
	public void setSeverite(String severite) {
		this.severite = severite;
	}

	/**
	 * Affecte un numéro d'assurance sociale.
	 *
	 * @param numeroAssuranceSociale Valeur du numéro d'assurance sociale en caractère.
	 */
	public void setNumeroAssuranceSociale(String numeroAssuranceSociale) {
		this.numeroAssuranceSociale = numeroAssuranceSociale;
	}

	/**
	 * Affecte un numéro de permis de conduire.
	 *
	 * @param numeroPermisConduire Valeur du numéro de permis de conduire en caractère.
	 */
	public void setNumeroPermisConduire(String numeroPermisConduire) {
		this.numeroPermisConduire = numeroPermisConduire;
	}

	/**
	 * Affecte une confidentialité.
	 *
	 * @param confidentialite Valeur de la confidentialité en caractère.
	 */
	public void setConfidentialite(String confidentialite) {
		this.confidentialite = confidentialite;
	}

	/**
	 * Affecte un passeport.
	 *
	 * @param passeport Valeur du passeport en caractère.
	 */
	public void setPasseport(String passeport) {
		this.passeport = passeport;
	}

	/**
	 * Affecte un mot de passe.
	 *
	 * @param motPasse Valeur du mot de passe en caractère.
	 */
	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	/**
	 * Affecte une confirmation de mot de passe.
	 *
	 * @param confirmationMotPasse Valeur de la confirmation du mot de passe en
	 * caractère.
	 */
	public void setConfirmationMotPasse(String confirmationMotPasse) {
		this.confirmationMotPasse = confirmationMotPasse;
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
	 * Affecte le rôle.
	 *
	 * @param role Valeur numérique du rôle.
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Affecte le type de lien.
	 *
	 * @param typeLien Valeur numérique du type de lien.
	 */
	public void setTypeLien(String typeLien) {
		this.typeLien = typeLien;
	}


	/**
	 * Affecte le créateur du sujet.
	 *
	 * @param createur Code du créateur.
	 */
	public void setCreateur(String createur) {
		this.createur = createur;
	}

	/**
	 * Affecte la date de création.
	 *
	 * @param dateCreation Date de création.
	 */
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Ajoute une narrations associés.
	 */
	public void addNarration(NarrationHtmlForm narration) {
	  this.listeNarrations.add(narration);
	}

	/**
	 * Ajoute un dossiers associés.
	 */
	public void addDossier(DossierHtmlForm dossier) {
		this.listeDossiers.add(dossier);
	}

	/**
	 * Ajoute un societes associés.
	 */
	public void addSociete(SocieteHtmlForm societe) {
		this.listeSocietes.add(societe);
	}

	/**
	 * Ajoute un sujets associés.
	 */
	public void addSujet(SujetHtmlForm sujet){
		this.listeSujets.add(sujet);
	}

	/**
	 * Ajoute une adresses associés.
	 */
	public void addAdresse(AdresseHtmlForm adresse) {
		this.listeAdresses.add(adresse);
	}

	/**
	 * Ajoute une caracteristiques  associés.
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
	 * Ajoute un  vehicules associés.
	 */
	public void addVehicule(VehiculeHtmlForm vehicule) {
		this.listeVehicules.add(vehicule);
	}

	/**
	 * Détermine si le sujet est nouvellement créer.
	 *
	 * @param isNew Valeur si le sujet est nouvellement créer.
	 */
	public void setNew(boolean nouveau){
		this.nouveau = nouveau;
	}

	/**
	 * Réinitialise toute les attributs à leur valeur par défaut.
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
	 * Réinitialise des onglets
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
	 * Retourne une chaîne de caractère reflétant la valeur de tout les
	 * attributs du SujetForm.
	 *
	 * @return String Valeur de tout les attributs du SujetForm en caractère.
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
	 * @param numeroAssuranceMaladie numeroAssuranceMaladie à définir
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
	 * @param lienDateCreation lienDateCreation à définir
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
	 * @param severiteAutres severiteAutres à définir
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
	 * @param dateChangement dateChangement à définir
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
	 * @param changePar changePar à définir
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
	 * @param siteDescription siteDescription à définir
	 */
	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}


	/**
	 * @param sexeDescription sexeDescription à définir
	 */
	public void setSexeDescription(String sexeDescription) {
		this.sexeDescription = sexeDescription;
	}


	/**
	 * @param langueDescription langueDescription à définir
	 */
	public void setLangueDescription(String langueDescription) {
		this.langueDescription = langueDescription;
	}

    /**
     * @param typeAgeDescription typeAgeDescription à définir
     */
	public void setTypeAgeDescription(String typeAgeDescription) {
	    this.typeAgeDescription = typeAgeDescription;
	}

	/**
	 * @param ethnieDescription ethnieDescription à définir
	 */
	public void setEthnieDescription(String ethnieDescription) {
		this.ethnieDescription = ethnieDescription;
	}


	/**
	 * @param raceDescription raceDescription à définir
	 */
	public void setRaceDescription(String raceDescription) {
		this.raceDescription = raceDescription;
	}


	/**
	 * @param severiteDescription severiteDescription à définir
	 */
	public void setSeveriteDescription(String severiteDescription) {
		this.severiteDescription = severiteDescription;
	}


	/**
	 * @param severiteDescriptionAutres severiteDescriptionAutres à définir
	 */
	public void setSeveriteDescriptionAutres(String severiteDescriptionAutres) {
		this.severiteDescriptionAutres = severiteDescriptionAutres;
	}


	/**
	 * @param confidentialiteDescription confidentialiteDescription à définir
	 */
	public void setConfidentialiteDescription(String confidentialiteDescription) {
		this.confidentialiteDescription = confidentialiteDescription;
	}


	/**
	 * @param roleDescription roleDescription à définir
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
	 * @param statutDescription statutDescription à définir
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
	 * Indique si la donnée provient de l'audit des changements
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
	 * @param createurDescription createurDescription à définir
	 */
	public void setCreateurDescription(String createurDescription) {
		this.createurDescription = createurDescription;
	}

	//Retourne l'année de naissance pour l'affichage dans l'onglet Sujet d'une Société.
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
	 * @param ajoutPhoto ajoutPhoto à définir
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
	 * @param indicateurRdd indicateurRdd à définir
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
	 * @param dateFinEnquete dateFinEnquete à définir
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