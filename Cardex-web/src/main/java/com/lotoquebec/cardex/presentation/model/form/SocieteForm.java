package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.business.fabrique.dossier.predicate.UrgenceFiltrerSocietesRA0022;
import com.lotoquebec.cardex.presentation.model.AdresseHtmlForm;
import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.NarrationHtmlForm;
import com.lotoquebec.cardex.presentation.model.SocieteHtmlForm;
import com.lotoquebec.cardex.presentation.model.SujetHtmlForm;
import com.lotoquebec.cardex.presentation.model.VehiculeHtmlForm;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ClasseCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ConfidentialiteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.LangueCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.RoleCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.SeveriteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.LongUtils;

/**
 * Conserve les diff�rentes valeurs relatives au formulatire de la soci�t�.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.14 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.form.SocieteHtmlForm
 */
public class SocieteForm extends ValidatorForm implements SocieteHtmlForm, EntiteCardexForm, Serializable, Cloneable {

    private String cle = "";
    private String site = "";
	private String entite = "";
    private String siteDescription = "";
    private String numeroFiche = "";
    private String nom = "";
    private String referenceNom = "";
    private String referencePrenom = "";
    private String raisonEtre = "";
    private String classe = "";
    private String classeDescription = "";
    private String confidentialiteDescription = "";
    private String dateDeFondation = "";
    private String reference1 = "";
    private String reference2 = "";
    private String statut = "";
    private String statutDescription = "";
    private String langue = "";
    private String langueDescription = "";
    private String severite = "";
    private String severiteDescription = "";
    private String severiteCasino = "";
    private String severiteCasinoDescription = "";
    //2013-05-07 - modifi� 2014-06-19
    //Les soci�t�s sont maintenant cr��es en confidentialit� 1 pour toutes les entit�s
    private String confidentialite = String.valueOf(GlobalConstants.Confidentialite.UN);
    private String motPasse = "";
    private String confirmationMotPasse = "";
    private String lien = "";
    private String lienSite = "";
	private String lienCreateur = "";
	private String lienDateCreation = "";
    private String role = "";
    private List<String> roles = new ArrayList<String>();
    private String roleDescription = "";
    private String typeLien = "";
    private String createur = "";
    private String createurDescription = "";
    private String dateCreation = "";
    private String ongletDefaut = "";
    private String audit = "";
    private boolean nouveau = false;
	private EntiteCardexForm entiteCardexLiaison = null;
    public ArrayList photos = new ArrayList();

    private ListeResultat listeAdresses = new ListeResultat();
    private ListeResultat listeSujets = new ListeResultat();
    private ListeResultat listeNarrations = new ListeResultat();
    private ListeResultat listeDossiers = new ListeResultat();
    private ListeResultat listeVehicules = new ListeResultat();
    private ListeResultat listeSocietes = new ListeResultat();
    private ListeResultat listeProprietaires = new ListeResultat();
    
    //Mars 2011. Ajout de deux champs pour la lecture de l'audit.
    //Les autres champs de l'audit sont identiques � ceux du dossier.
    private String dateChangement= "";
    private String changePar = "";
    //Pour l'ajout d'une photo
    private PhotoForm ajoutPhoto = null;

    //Septembre 2012, donn�es du syst�me RDD (R�seau des d�taillants)
    private String centreRegional = "";
    private String centreRegionalDescription = "";
    private String codeCompte = "";
    private String codeCompteDescription = "";
    private String district = "";
    private String districtDescription = "";
    private boolean actif = false;
    private String dateInactif = "";
    private String commentaire = "";
	private boolean indicateurRdd = false;
    private String raisonDesactivation = "";
    //M�thode d'�chantillonnage des d�taillants RDD
    private String echantillonnage = "";
    //La liaison entre une soci�t� RDD et une soci�t� RDD ne peut �tre supprim�.
    private boolean permettreSuppressionLiaison = true;

	/**
     * Constructeur de SocieteForm par d�faut.
     */
    public SocieteForm() {}


    // La liaison entre un sujet RDD et une soci�t� RDD ne peut �tre supprim�.
	// Le seul cas o� la liaison n'est pas permise est si le sujet et la soci�t� sont de RDD.
	public void assignerPermettreSuppressionLiaison(CardexAuthenticationSubject subject, SujetForm sujetForm){
		CardexUser cardexUser = (CardexUser)subject.getUser();
		boolean isCreateurIdentique = lienCreateur.equals(cardexUser.getCode());
		boolean isSiteIdentique = LongUtils.valueOf(site) == cardexUser.getSite();
		boolean isSocieteUrgence = isSocieteUrgence();
		boolean isRoleResponsableRDD = GlobalConstants.Role.RESPONSABLE == LongUtils.valueOf(role)
			&& indicateurRdd == true && sujetForm.isIndicateurRdd() == true;
		
		permettreSuppressionLiaison = (isSiteIdentique || isCreateurIdentique) && isSocieteUrgence == false && isRoleResponsableRDD == false;
	}
    
	public void assignerPermettreSuppressionLiaison(CardexAuthenticationSubject subject, DossierForm dossierForm){
		CardexUser cardexUser = (CardexUser)subject.getUser();
		boolean isDossierActif = dossierForm.isDossierActif();
		boolean isCreateurIdentique = lienCreateur.equals(cardexUser.getCode());
		boolean isSiteIdentique = LongUtils.valueOf(site) == cardexUser.getSite();
		boolean isSocieteUrgence = isSocieteUrgence();
		
		permettreSuppressionLiaison = isDossierActif && (isSiteIdentique || isCreateurIdentique) && isSocieteUrgence == false;
	}
	
    // Getters


    /**
     * Test si la societe est un nouvellement cr��e.
     *
     * @return True si le societe est nouvellement cr��e.
     */
    public boolean isNew() {
      return this.nouveau;
    }

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
     * Retourne le nom de r�f�rence.
     *
     * @return String Valeur du nom de r�f�rence en caract�re.
     */
    public String getReferenceNom() {
        return this.referenceNom;
    }

    /**
     * Retourne le pr�nom de r�f�rence.
     *
     * @return String Valeur du pr�nom de r�f�rence en caract�re.
     */
    public String getReferencePrenom() {
        return this.referencePrenom;
    }

    /**
     * Retourne la raison d'�tre.
     *
     * @return String Valeur de la raison d'�tre en caract�re.
     */
    public String getRaisonEtre() {
        return this.raisonEtre;
    }

    /**
     * Retourne la classe.
     *
     * @return String Valeur de la classe en caract�re.
     */
    public String getClasse() {
        return this.classe;
    }

    /**
     * Retourne la date de fondation.
     *
     * @return String Valeur de la date de fondation en caract�re.
     */
    public String getDateDeFondation() {
        return this.dateDeFondation;
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
     * Retourne le statut.
     *
     * @return String Valeur du statut en caract�re.
     */
    public String getStatut() {
        return this.statut;
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
     * Retourne la s�v�rit�.
     *
     * @return String Valeur de la s�v�rit� en caract�re.
     */
    public String getSeverite() {
        return this.severite;
    }

    /**
     * Retourne la confidentialit�
     *
     * @return String Valeur de la confidentialit� en caract�re.
     */
    public String getConfidentialite() {
        return this.confidentialite;
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
     * @return String Valeur du r�le en caract�re.
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Retourne le type de lien.
     *
     * @return String Valeur du type de lien en caract�re.
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
     * Retourne les narrations associ�es.
     *
     * @return Collection Valeur de la collection de narrations.
     */
    public Collection getNarrations() {
        return this.listeNarrations.getResultatComplet();
    }

    /**
     * Retourne les dossiers associ�s.
     *
     * @return Collection Valeur de la collection de dossiers.
     */
    public Collection getDossiers() {
        return this.listeDossiers.getResultatComplet();
    }

    /**
     * Retourne les sujets associ�s.
     *
     * @return Collection Valeur de la collection de sujets.
     */
    public Collection getSujets() {
        return this.listeSujets.getResultatComplet();
    }

    /**
     * Retourne les societes associ�es.
     *
     * @return Collection Valeur de la collection de soci�t�s.
     */
    public Collection getSocietes() {
        return this.listeSocietes.getResultatComplet();
    }

    /**
     * Retourne l'historique des proprietaires associ�es.
     *
     * @return Collection Valeur de la collection de soci�t�s.
     */
    public Collection getProprietaires() {
        return this.listeProprietaires.getResultatComplet();
    }

    /**
     * Retourne les adresses associ�es.
     *
     * @return Collection Valeur de la collection d'adresse.
     */
    public Collection getAdresses() {
        return this.listeAdresses.getResultatComplet();
    }

    /**
     * Retourne les  photos associ�es.
     *
     * @return Collection Valeur de la collection de photos.
     */
    public Collection getPhotos() {
        return this.photos;
    }

    /**
     * Retourne les  v�hicules associ�s.
     *
     * @return Collection Valeur de la collection de v�hicules.
     */
    public Collection getVehicules() {
        return this.listeVehicules.getResultatComplet();
    }


    // Setters

    /**
     * D�termine si la societe est nouvellement cr�er.
     *
     * @param isNew Valeur si la societe est nouvellement cr�er.
     */
    public void setNew(boolean nouveau){
        this.nouveau = nouveau;
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
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(String site) {
        this.site = site;
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
     * Affecte un nom de r�f�rence.
     *
     * @param referenceNom Valeur du nom de r�f�rence en caract�re.
     */
    public void setReferenceNom(String referenceNom) {
        this.referenceNom = referenceNom;
    }

    /**
     * Affecte un pr�nom de r�f�rence.
     *
     * @param referencePrenom Valeur du pr�nom de r�f�rence en caract�re.
     */
    public void setReferencePrenom(String referencePrenom) {
        this.referencePrenom = referencePrenom;
    }

    /**
     * Affecte une raison d'�tre.
     *
     * @param raisonEtre Valeur de la raison d'�tre en caract�re.
     */
    public void setRaisonEtre(String raisonEtre) {
        this.raisonEtre = raisonEtre;
    }

    /**
     * Affecte une classe.
     *
     * @param classe Valeur de la classe en caract�re.
     */
    public void setClasse(String classe) {
        this.classe = classe;
    }

    /**
     * Affecte une date de fondation.
     *
     * @param dateDeFondation Valeur de la date de fondation en caract�re.
     */
    public void setDateDeFondation(String dateDeFondation) {
        this.dateDeFondation = dateDeFondation;
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
     * @param reference2 Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public void setReference2(String reference2) {
        this.reference2 = reference2;
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
     * Affecte une langue.
     *
     * @param langue Valeur de la langue en caract�re.
     */
    public void setLangue(String langue) {
        this.langue = langue;
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
     * Affecte une confidentialit�
     *
     * @param confidentialite Valeur de la confidentialit� en caract�re.
     */
    public void setConfidentialite(String confidentialite) {
        this.confidentialite = confidentialite;
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
     * @param confirmationMotPasse Valeur de la confirmation du mot de passe en caract�re.
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
     * Affecte un r�le.
     *
     * @param role Valeur du r�le en caract�re.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Affecte un type de lien.
     *
     * @param typeLien Valeur du type de lien en caract�re.
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
     * Ajoute une narration associ�e.
     *
     * @param narration Valeur de la narration � ajouter.
     */
    public void addNarration(NarrationHtmlForm narration) {
        this.listeNarrations.add(narration);
    }

    /**
     * Ajoute un dossier associ�.
     *
     * @param dossier Valeur du dossier � ajouter.
     */
    public void addDossier(DossierHtmlForm dossier) {
        this.listeDossiers.add(dossier);
    }

    /**
     * Ajoute un sujet associ�.
     *
     * @param sujet Valeur du sujet � ajouter.
     */
    public void addSujet(SujetHtmlForm sujet) {
        this.listeSujets.add(sujet);
    }

    /**
     * Ajoute une soci�t� associ�e.
     *
     * @param Collection Valeur de la soci�t� � ajouter.
     */
    public void addSociete(SocieteHtmlForm societe) {
        this.listeSocietes.add(societe);
    }

    /**
     * Ajoute les propri�taires.
     *
     * @param Collection Valeur de l'historique des propri�taires � ajouter.
     */
    public void addProprietaires(SujetHtmlForm proprietaires) {
        this.listeProprietaires.add(proprietaires);
    }

    /**
     * Ajoute une adresse associ�e.
     *
     * @param adresse Valeur de l'adresse � ajouter.
     */
    public void addAdresse(AdresseHtmlForm adresse) {
        this.listeAdresses.add(adresse);
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
     * Ajoute un v�hicule associ�e.
     *
     * @param vehicule Valeur du v�hicule � ajouter.
     */
    public void addVehicule(VehiculeHtmlForm vehicule) {
        this.listeVehicules.add(vehicule);
    }

    /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du SocieteForm.
     *
     * @return String Valeur de tout les attributs du SocieteForm en caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[SocieteForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', numeroFiche = '" + numeroFiche);
        stringBuffer.append("', nom = '" + nom);
        stringBuffer.append("', referenceNom = '" + referenceNom);
        stringBuffer.append("', referencePrenom = '" + referencePrenom);
        stringBuffer.append("', raisonEtre = '" + raisonEtre);
        stringBuffer.append("', classe = '" + classe);
        stringBuffer.append("', dateDeFondation = '" + dateDeFondation);
        stringBuffer.append("', reference1 = '" + reference1);
        stringBuffer.append("', reference2 = '" + reference2);
        stringBuffer.append("', statut = '" + statut );
        stringBuffer.append("', langue = '" + langue);
        stringBuffer.append("', confidentialite = '" + confidentialite);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', role = '" + role);
        stringBuffer.append("', typeLien = '" + typeLien);
        stringBuffer.append("']");
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
	  	  this.indicateurRdd = false;
	  	  this.actif = false;
        //this.nouveau = false;
    }

    /**
     * R�initialise toute les attributs � leur valeur par d�faut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void init(CardexAuthenticationSubject subject) {
    	CardexUser user = (CardexUser) subject.getUser();
    	
        this.cle = "";
        this.site = "";
        this.entite = "";
        this.numeroFiche = "";
        this.nom = "";
        this.referenceNom = "";
        this.referencePrenom = "";
        this.raisonEtre = "";
        this.classe = "";
        this.dateDeFondation = "";
        this.reference1 = "";
        this.reference2 = "";
        this.statut = "";
        this.langue = "";
        this.severite = "";
        this.severiteCasino = "";
        this.motPasse = "";
        this.confirmationMotPasse = "";
        this.lien = "";
        this.lienSite = "";
        this.lienDateCreation = "";
        this.role = "";
        this.typeLien = "";
        this.changePar = "";
        this.dateChangement = "";
        this.entiteCardexLiaison = null;
      	this.confidentialite = String.valueOf(GlobalConstants.Confidentialite.UN);
        this.ajoutPhoto = new PhotoForm();
        this.centreRegional = "";
        this.codeCompte = "";
        this.district = "";
        this.actif = false;
        this.dateInactif = "";
        this.commentaire = "";
        this.indicateurRdd = false;
        this.permettreSuppressionLiaison = true;
        this.centreRegionalDescription = "";
        this.codeCompteDescription = "";
        this.districtDescription = "";
        this.raisonDesactivation = "";
        assignerOngletDefaut(subject);
    }

    /**
     * R�initialise des onglets
     */
    public void resetOnglets() {
        this.listeAdresses.vider();
        this.listeNarrations.vider();
        this.listeDossiers.vider();
        this.listeVehicules.vider();
        this.listeSocietes.vider();
        this.listeSujets.vider();
        this.listeProprietaires.vider();
        this.photos = new ArrayList();
	    this.ajoutPhoto = new PhotoForm();
    }

    public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException{
    	ListeCache cache = ListeCache.getInstance();
    	
    	classeDescription = cache.obtenirLabel(subject, classe, new ClasseCleListeCache(subject));
    	confidentialiteDescription = cache.obtenirLabel(subject, confidentialite, new ConfidentialiteCleListeCache(subject));
    	severiteDescription = cache.obtenirLabel(subject, severite, new SeveriteCleListeCache(subject));
    	severiteCasinoDescription = cache.obtenirLabel(subject, severiteCasino, new SeveriteCleListeCache(subject));
    	langueDescription = cache.obtenirLabel(subject, langue, new LangueCleListeCache(subject));
    	roleDescription = cache.obtenirLabel(subject, role, new RoleCleListeCache(subject));
		siteDescription = cache.obtenirLabel(subject, site, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, getEntite(), GlobalConstants.ActionSecurite.SELECTION));
		statutDescription = cache.obtenirLabel(subject, getStatut(), new StatutCleListeCache(subject, GlobalConstants.ListeCache.Statut.SOCIETE));
		createurDescription = cache.obtenirLabel(subject, getCreateur(), new IntervenantCle(subject.getLocale().getLanguage()));
    }

	public String getRoleDescription() {
		return roleDescription;
	}

	public String getClasseDescription() {
		return classeDescription;
	}

	public String getConfidentialiteDescription() {
		return confidentialiteDescription;
	}

	public String getLangueDescription() {
		return langueDescription;
	}

	public String getSeveriteDescription() {
		return severiteDescription;
	}
	
	public ListeResultat getListeSujets() {
		return listeSujets;
	}
	public ListeResultat getListeAdresses() {
		return listeAdresses;
	}
	public ListeResultat getListeDossiers() {
		return listeDossiers;
	}
	public ListeResultat getListeNarrations() {
		return listeNarrations;
	}
	public ListeResultat getListeSocietes() {
		return listeSocietes;
	}
	public ListeResultat getListeProprietaires() {
		return listeProprietaires;
	}
	public ListeResultat getListeVehicules() {
		return listeVehicules;
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
	
	private void assignerOngletDefaut(CardexAuthenticationSubject subject){
		
		if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_NARRATIONS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.NARRATION;
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_DOSSIERS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.DOSSIER;
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_SUJETS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.SUJET;
	
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_RELATIONS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.SOCIETIES;		
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_ADRESSES_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.ADRESSE;
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_PH0TOS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.PHOTOS;
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_VEHICULES_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.VEHICULES;		

		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.SOCIETE_PROPRIETAIRES_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.PROPRIETAIRES;
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


	/**
	 * @return entite
	 */
	public String getEntite() {
		return entite;
	}


	/**
	 * @param entite entite � d�finir
	 */
	public void setEntite(String entite) {
		this.entite = entite;
	}


	/**
	 * @param langueDescription langueDescription � d�finir
	 */
	public void setLangueDescription(String langueDescription) {
		this.langueDescription = langueDescription;
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


	/**
	 * @param classeDescription classeDescription � d�finir
	 */
	public void setClasseDescription(String classeDescription) {
		this.classeDescription = classeDescription;
	}

	public EntiteCardexForm getEntiteCardexLiaison() {
		return entiteCardexLiaison;
	}
	public void setEntiteCardexLiaison(EntiteCardexForm entiteCardex) {
		this.entiteCardexLiaison = entiteCardex;
	}

	/**
	 * @param confidentialiteDescription confidentialiteDescription � d�finir
	 */
	public void setConfidentialiteDescription(String confidentialiteDescription) {
		this.confidentialiteDescription = confidentialiteDescription;
	}


	/**
	 * @param severiteDescription severiteDescription � d�finir
	 */
	public void setSeveriteDescription(String severiteDescription) {
		this.severiteDescription = severiteDescription;
	}


	/**
	 * @param roleDescription roleDescription � d�finir
	 */
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
	public Object clone() throws CloneNotSupportedException{
		super.clone();
		SocieteForm societeForm = new SocieteForm();
		
		societeForm.setCle( getCle() );
		societeForm.setSite( getSite() );
		
		return societeForm;
	}


	public List<String> getRoles() {
		return roles;
	}


	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public int hashCode(){
		return 1;
	}
	
	public boolean equals (Object o) {
		
		if (o instanceof SocieteForm == false)
			return false;

		SocieteForm societeForm = (SocieteForm) o;
		
		return cle.equals( societeForm.getCle() )
		&& site.equals( societeForm.getSite() );
	}


	public String getLienDateCreation() {
		return lienDateCreation;
	}


	public void setLienDateCreation(String lienDateCreation) {
		this.lienDateCreation = lienDateCreation;
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
	 * @return centreRegional
	 */
	public String getCentreRegional() {
		return centreRegional;
	}


	/**
	 * @param centreRegional centreRegional � d�finir
	 */
	public void setCentreRegional(String centreRegional) {
		this.centreRegional = centreRegional;
	}


	/**
	 * @return codeCompte
	 */
	public String getCodeCompte() {
		return codeCompte;
	}


	/**
	 * @param codeCompte codeCompte � d�finir
	 */
	public void setCodeCompte(String codeCompte) {
		this.codeCompte = codeCompte;
	}


	/**
	 * @return district
	 */
	public String getDistrict() {
		return district;
	}


	/**
	 * @param district district � d�finir
	 */
	public void setDistrict(String district) {
		this.district = district;
	}


	/**
	 * @return actif
	 */
	public boolean isActif() {
		return actif;
	}


	/**
	 * @param actif actif � d�finir
	 */
	public void setActif(boolean actif) {
		this.actif = actif;
	}


	/**
	 * @return dateInactif
	 */
	public String getDateInactif() {
		return dateInactif;
	}


	/**
	 * @param dateInactif dateInactif � d�finir
	 */
	public void setDateInactif(String dateInactif) {
		this.dateInactif = dateInactif;
	}


	/**
	 * @return commentaire
	 */
	public String getCommentaire() {
		return commentaire;
	}


	/**
	 * @param commentaire commentaire � d�finir
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
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
	 * @return centreRegionalDescription
	 */
	public String getCentreRegionalDescription() {
		return centreRegionalDescription;
	}


	/**
	 * @param centreRegionalDescription centreRegionalDescription � d�finir
	 */
	public void setCentreRegionalDescription(String centreRegionalDescription) {
		this.centreRegionalDescription = centreRegionalDescription;
	}


	/**
	 * @return codeCompteDescription
	 */
	public String getCodeCompteDescription() {
		return codeCompteDescription;
	}


	/**
	 * @param codeCompteDescription codeCompteDescription � d�finir
	 */
	public void setCodeCompteDescription(String codeCompteDescription) {
		this.codeCompteDescription = codeCompteDescription;
	}


	/**
	 * @return raisonDesactivation
	 */
	public String getRaisonDesactivation() {
		return raisonDesactivation;
	}


	/**
	 * @param raisonDesactivation raisonDesactivation � d�finir
	 */
	public void setRaisonDesactivation(String raisonDesactivation) {
		this.raisonDesactivation = raisonDesactivation;
	}


	/**
	 * @return districtDescription
	 */
	public String getDistrictDescription() {
		return districtDescription;
	}


	/**
	 * @param districtDescription districtDescription � d�finir
	 */
	public void setDistrictDescription(String districtDescription) {
		this.districtDescription = districtDescription;
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
	
    public String getEchantillonnage() {
		return echantillonnage;
	}


	public void setEchantillonnage(String echantillonnage) {
		this.echantillonnage = echantillonnage;
	}

	public boolean isPermettreSuppressionLiaison() {
		return permettreSuppressionLiaison;
	}

	public void setPermettreSuppressionLiaison(boolean permettreSuppressionLiaison) {
		this.permettreSuppressionLiaison = permettreSuppressionLiaison;
	}
	
	/**
	 * RP0003 Onglet soci�t� : Il n'est pas possible de retirer une soci�t� 
	 * d'urgence via l'onglet "Soci�t�".  Il faut le faire via l'onglet "Service d'urgence".
	 * 
	 * @return
	 */
	public boolean isSocieteUrgence(){
		return UrgenceFiltrerSocietesRA0022.isSocieteUrgence( LongUtils.valueOf(classe) );
	}
	
}
