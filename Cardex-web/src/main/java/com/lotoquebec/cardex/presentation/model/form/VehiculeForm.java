package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.DossierHtmlForm;
import com.lotoquebec.cardex.presentation.model.NarrationHtmlForm;
import com.lotoquebec.cardex.presentation.model.ParticularitesHtmlForm;
import com.lotoquebec.cardex.presentation.model.SocieteHtmlForm;
import com.lotoquebec.cardex.presentation.model.SujetHtmlForm;
import com.lotoquebec.cardex.presentation.model.VehiculeHtmlForm;
import com.lotoquebec.cardex.securite.GestionnaireSecuriteCardex;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ConfidentialiteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.MarqueCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.ModeleCleMultiListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.ProvinceEtPaysCleSQLListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.model.ListeResultat;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Conserve les diff�rentes valeurs relatives au formulatire de consultation du
 * v�hicule.
 *
 * @author $Author: fguerin $
 * @version $Revision: 1.11 $, $Date: 2002/04/30 13:20:22 $
 * @see com.lotoquebec.cardex.presentation.model.VehiculeHtmlForm
 */
public class VehiculeForm extends ValidatorForm implements VehiculeHtmlForm, EntiteCardexForm, Serializable, Cloneable {

    private String cle = "";
    private String site = "";
    private String siteDescription = "";
	private String entite = "";
    private String numeroDossier = "";
    private String siteDossier = "";
    private String marque = "";
    private String commentaire = "";
    private String marqueDescription = "";
    private String modele = "";
    private String modeleDescription = "";
    private String modeleMarque = "";
    private String immatriculation = "";
    private String cleProvince = "";    
    private String province = "";
    private String annee = "";
    private String vignette = "";
    private String dateExpirationVignette = "";
    private String numeroSerie = "";
    private String assurance = "";
    private String dateExpirationAssurance = "";
    private String police = "";
    private String confidentialite = "";
    private String confidentialiteDescription = "";
    private String motPasse = "";
    private String confirmationMotPasse = "";
    private String createur = "";
    private String createurDescription = "";
    private String dateCreation = "";
    private String lien = "";
    private String lienSite = "";
	private String lienCreateur = "";
    private String lienDateCreation = "";
    private String role = "";
    private String typeLien = "";
    private String ongletDefaut = "";
    private boolean nouveau = false;
	private EntiteCardexForm entiteCardexLiaison = null;

    private ParticularitesHtmlForm particularites = null;
    public ArrayList photos = new ArrayList();
    
    private ListeResultat listeSujets = new ListeResultat();
    private ListeResultat listeDossiers = new ListeResultat();
    private ListeResultat listeNarrations = new ListeResultat();
    private ListeResultat listeSocietes = new ListeResultat();
    //Mars 2011. Ajout de deux champs pour la lecture de l'audit.
    //Les autres champs de l'audit sont identiques � ceux du dossier.
    private String dateChangement= "";
    private String changePar = "";
    //Pour l'ajout d'une photo
    private PhotoForm ajoutPhoto = null;

    /**
     * Constructeur de VehiculeForm par d�faut.
     */
    public VehiculeForm() {}


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
     * Retourne le num�ro de dossier.
     *
     * @return String Valeur du num�ro de dossier en caract�re.
     */
    public String getNumeroDossier() {
        return this.numeroDossier;
    }

    /**
     * Retourne le site du dossier.
     *
     * @return String Valeur du site du dossier en caract�re.
     */
    public String getSiteDossier() {
        return this.siteDossier;
    }

    /**
     * Retourne la marque.
     *
     * @return String Valeur de la marque en caract�re.
     */
    public String getMarque() {
        return this.marque;
    }

    /**
     * Retourne le modele.
     *
     * @return String Valeur du modele en caract�re.
     */
    public String getModele() {
        return this.modele;
    }

    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caract�re.
     */
    public String getImmatriculation() {
        return this.immatriculation;
    }

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province en caract�re.
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * Retourne l'annee.
     *
     * @return String Valeur de l'annee en caract�re.
     */
    public String getAnnee() {
        return this.annee;
    }

    /**
     * Retourne la vignette.
     *
     * @return String Valeur de la vignette en caract�re.
     */
    public String getVignette() {
        return this.vignette;
    }

    /**
     * Retourne l'assurance.
     *
     * @return String Valeur de l'assurance en caract�re.
     */
    public String getAssurance() {
        return this.assurance;
    }

    /**
     * Retourne la date d'expiration de la vignette.
     *
     * @return String Valeur de la date d'expiration de la vignette en
     * caract�re.
     */
    public String getDateExpirationVignette() {
        return this.dateExpirationVignette;
    }

    /**
     * Retourne le num�ro de s�rie.
     *
     * @return String Valeur du num�ro de s�rie en caract�re.
     */
    public String getNumeroSerie() {
        return this.numeroSerie;
    }

    /**
     * Retourne la date d'expiration de l'assurance.
     *
     * @return String Valeur de la date d'expiration de l'assurance en
     * caract�re.
     */
    public String getDateExpirationAssurance() {
        return this.dateExpirationAssurance;
    }

    /**
     * Retourne la police.
     *
     * @return String Valeur de la police en caract�re.
     */
    public String getPolice() {
        return this.police;
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
     * @return String Valeur du mot de la confirmation du passe en caract�re.
     */
    public String getConfirmationMotPasse() {
        return this.confirmationMotPasse;
    }

    /**
     * Retourne le cr�ateur.
     *
     * @return String Valeur du cr�ateur en caract�re.
     */
    public String getCreateur() {
        return this.createur;
    }

    /**
     * Retourne la date de cr�ation.
     *
     * @return String Valeur de la date de cr�ation en caract�re.
     */
    public String getDateCreation() {
        return this.dateCreation;
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
     * Retourne les particularit�s associ�es.
     *
     * @return Collection Valeur de la collection de particularit�s.
     */
    public ParticularitesHtmlForm getParticularites() {
        return this.particularites;
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
     * Retourne les soci�t�s associ�es.
     *
     * @return Collection Valeur de la collection de soci�t�s.
     */
    public Collection getSocietes() {
        return this.listeSocietes.getResultatComplet();
    }

    /**
     * Retourne les photos associ�s.
     *
     * @return Collection Valeur de la collection de photos.
     */
    public Collection getPhotos() {
        return this.photos;
    }

    /**
     * Test si le v�hicule est un nouvellement cr��.
     *
     * @return True si le v�hicule est nouvellement cr��.
     */
    public boolean isNew() {
      return this.nouveau;
    }


    // Setters


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
     * Affecte un num�ro de dossier.
     *
     * @param numeroDossier Valeur du num�ro de dossier en caract�re.
     */
    public void setNumeroDossier(String numeroDossier) {
        this.numeroDossier = numeroDossier;
    }

    /**
     * Affecte un site du dossier.
     *
     * @param siteDossier Valeur du site du dossier en caract�re.
     */
    public void setSiteDossier(String siteDossier) {
        this.siteDossier = siteDossier;
    }

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque en caract�re.
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * Affecte un modele.
     *
     * @param modele Valeur du modele en caract�re.
     */
    public void setModele(String modele) {
        this.modele = modele;
    }

    /**
     * Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caract�re.
     */
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province en caract�re.
     */
    public void setProvince(String province) {
        this.province = province;
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
          this.entite = "";
          this.annee = "";
          this.assurance = "";
          this.commentaire = "";
          this.confidentialite = String.valueOf(GlobalConstants.Confidentialite.UN);
          this.dateExpirationAssurance = "";
          this.dateExpirationVignette = "";
          this.immatriculation = "";
          this.marque = "";
          this.modele = "";
          this.numeroDossier = "";
          this.numeroSerie = "";
          this.police = "";
          this.province = "";
          this.cleProvince = "";
          this.siteDossier = "";
          this.vignette = "";
          this.motPasse = "";
          this.confirmationMotPasse = "";
          this.lien = "";
          this.lienSite = "";
          this.lienDateCreation = "";
          this.role = "";
          this.typeLien = "";
		  this.entiteCardexLiaison = null;
		  this.createur = "";
		  this.dateCreation = "";
          this.changePar = "";
	      this.dateChangement = "";
		  this.ajoutPhoto = new PhotoForm();	      
	      assignerOngletDefaut(subject);
    }

    /**
     * R�initialise des onglets
     */
    public void resetOnglets() {
          this.listeNarrations.vider();
          this.listeDossiers.vider();
          this.listeSocietes.vider();
          this.listeSujets.vider();
          this.particularites = new ParticularitesForm();
          this.photos = new ArrayList();
		  this.ajoutPhoto = new PhotoForm();
    }

    /**
     * Affecte une annee.
     *
     * @param annee Valeur de l'annee en caract�re.
     */
    public void setAnnee(String annee) {
        this.annee = annee;
    }

    /**
     * Affecte une assurance.
     *
     * @param annee Valeur de l'assurance en caract�re.
     */
    public void setAssurance(String assurance) {
        this.assurance = assurance;
    }

    /**
     * Affecte une vignette.
     *
     * @param vignette Valeur de la vignette en caract�re.
     */
    public void setVignette(String vignette) {
        this.vignette = vignette;
    }

    /**
     * Affecte une date d'expiration de la vignette.
     *
     * @param dateExpirationVignette Valeur de la date d'expiration de la
     * vignette en caract�re.
     */
    public void setDateExpirationVignette(String dateExpirationVignette) {
        this.dateExpirationVignette = dateExpirationVignette;
    }

    /**
     * Affecte un num�ro de s�rie.
     *
     * @param numeroSerie Valeur du num�ro de s�rie en caract�re.
     */
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    /**
     * Affecte une date d'expiration de l'assurance.
     *
     * @param dateExpirationAssurance Valeur de la date d'expiration de
     * l'assurance en caract�re.
     */
    public void setDateExpirationAssurance(String dateExpirationAssurance) {
        this.dateExpirationAssurance = dateExpirationAssurance;
    }

    /**
     * Affecte une police.
     *
     * @param police Valeur de la police en caract�re.
     */
    public void setPolice(String police) {
        this.police = police;
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
     * Affecte un cr�ateur.
     *
     * @param createur Valeur du cr�ateur en caract�re.
     */
    public void setCreateur(String createur) {
        this.createur = createur;
    }

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation en caract�re.
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
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
     * Ajoute une particularit� associ�e.
     *
     * @param particularite Valeur de la particularit� � ajouter.
     */
//    public void addParticularite(ParticularitesForm particularite) {
//        this.particularites.add(particularite);
//    }

    /**
     * Ajoute un sujet associ�.
     *
     * @param Collection Valeur du sujet � ajouter.
     */
    public void addSujet(SujetHtmlForm sujet) {
        this.listeSujets.add(sujet);
    }

    /**
     * Ajoute une soci�t�s associ�e.
     *
     * @param societe Valeur de la soci�t� � ajouter.
     */
    public void addSociete(SocieteHtmlForm societe) {
        this.listeSocietes.add(societe);
    }

    /**
     * Ajoute une photo associ�e.
     *
     * @param photo Valeur de la photo � ajouter.
     */
    public void addPhoto(Collection photo) {
        this.photos.add(photo);
    }

     /**
     * Ajoute une particularit�
     */
    public void setParticularites(ParticularitesHtmlForm particularite) {
         this.particularites = particularite;
    }

     /**
     * D�termine si le v�hicule est nouvellement cr��.
     *
     * @param isNew Valeur si le dossier est nouvellement cr�er.
     */
    public void setNew(boolean nouveau){
        this.nouveau = nouveau;
    }

  /**
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du VehiculeForm.
     *
     * @return String Valeur de tout les attributs du VehiculeForm en caract�re.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[VehiculeForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("numeroDossier = '" + numeroDossier);
        stringBuffer.append("', siteDossier = '" + siteDossier);
        stringBuffer.append("', marque = '" + marque);
        stringBuffer.append("', modele = '" + modele);
        stringBuffer.append("', commentaire = '" + commentaire);
        stringBuffer.append("', immatriculation = '" + immatriculation);
        stringBuffer.append("', province = '" + province);
        stringBuffer.append("', cleProvince = '" + cleProvince);
        stringBuffer.append("', annee = '" + annee);
        stringBuffer.append("', vignette = '" + vignette);
        stringBuffer.append("', dateExpirationVignette = '"
                + dateExpirationVignette);
        stringBuffer.append("', numeroSerie = '" + numeroSerie);
        stringBuffer.append("', assurance = '" + assurance);
        stringBuffer.append("', dateExpirationAssurance = '"
                + dateExpirationAssurance);
        stringBuffer.append("', police = '" + police);
        stringBuffer.append("', confidentialite = '" + confidentialite);
        stringBuffer.append("', motPasse = '" + motPasse);
        stringBuffer.append("', confirmationMotPasse = '"
                + confirmationMotPasse);
        stringBuffer.append("', createur = '" + createur);
        stringBuffer.append("', dateCreation = '" + dateCreation);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', role = '" + role);
        stringBuffer.append("', typeLien = '" + typeLien);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }

	/**
	 * Returns the modeleMarque.
	 * @return String
	 */
	public String getModeleMarque() {
		return modeleMarque;
	}

	/**
	 * Sets the modeleMarque.
	 * @param modeleMarque The modeleMarque to set
	 */
	public void setModeleMarque(String modeleMarque) {
		this.modeleMarque = modeleMarque;
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

	public String getMarqueDescription() {
		return marqueDescription;
	}

	public String getModeleDescription() {
		return modeleDescription;
	}
	
	public EntiteCardexForm getEntiteCardexLiaison() {
		return entiteCardexLiaison;
	}
	public void setEntiteCardexLiaison(EntiteCardexForm entiteCardex) {
		this.entiteCardexLiaison = entiteCardex;
	}

	
    public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException{
    	ListeCache cache = ListeCache.getInstance();
    	
    	confidentialiteDescription = cache.obtenirLabel(subject, confidentialite, new ConfidentialiteCleListeCache(subject));
    	marqueDescription = cache.obtenirLabel(subject, marque, new MarqueCleMultiListeCache(subject));
    	modeleDescription = cache.obtenirLabel(subject, modele, new ModeleCleMultiListeCache(subject, marque));
    	province = cache.obtenirLabel(subject, cleProvince, new ProvinceEtPaysCleSQLListeCache(subject));
		createurDescription = cache.obtenirLabel(subject, getCreateur(), new IntervenantCle(subject.getLocale().getLanguage()));
		siteDescription = cache.obtenirLabel(subject, site, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, getEntite(), GlobalConstants.ActionSecurite.SELECTION));		
    }

    public ListeResultat getListeSujets() {
		return listeSujets;
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
		
		if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.VEHICULE_NARRATIONS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.NARRATION;
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.VEHICULE_DOSSIERS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.DOSSIER;
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.VEHICULE_SUJETS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.SUJET;
	
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.VEHICULE_SOCIETES_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.SOCIETIES;		
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.VEHICULE_PARTICULARITES_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.PARTICULARITES;
		
		else if ( GestionnaireSecuriteCardex.isRoleAdhoc(subject, GlobalConstants.SecuriteRoleAdhoc.VEHICULE_PHOTOS_ONGLET) )
			ongletDefaut = GlobalConstants.Onglet.PHOTOS;
		
		else 
			ongletDefaut = GlobalConstants.Onglet.NARRATION; 
	}

	public String getOngletDefaut() {
		return ongletDefaut;
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
	 * @return confidentialiteDescription
	 */
	public String getConfidentialiteDescription() {
		return confidentialiteDescription;
	}


	/**
	 * @param confidentialiteDescription confidentialiteDescription � d�finir
	 */
	public void setConfidentialiteDescription(String confidentialiteDescription) {
		this.confidentialiteDescription = confidentialiteDescription;
	}
	
	public Object clone() throws CloneNotSupportedException{
		super.clone();
		VehiculeForm vehiculeForm = new VehiculeForm();
		
		vehiculeForm.setCle( getCle() );
		vehiculeForm.setSite( getSite() );
		
		return vehiculeForm;
	}
	


	/**
	 * @param marqueDescription marqueDescription � d�finir
	 */
	public void setMarqueDescription(String marqueDescription) {
		this.marqueDescription = marqueDescription;
	}


	/**
	 * @param modeleDescription modeleDescription � d�finir
	 */
	public void setModeleDescription(String modeleDescription) {
		this.modeleDescription = modeleDescription;
	}


	public String getLienDateCreation() {
		return lienDateCreation;
	}


	public void setLienDateCreation(String lienDateCreation) {
		this.lienDateCreation = lienDateCreation;
	}


	public String getCleProvince() {
		return cleProvince;
	}


	public void setCleProvince(String cleProvince) {
		this.cleProvince = cleProvince;
	}


	public String getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
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
}