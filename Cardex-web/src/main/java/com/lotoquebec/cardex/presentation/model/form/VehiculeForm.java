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
 * Conserve les différentes valeurs relatives au formulatire de consultation du
 * véhicule.
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
    //Les autres champs de l'audit sont identiques à ceux du dossier.
    private String dateChangement= "";
    private String changePar = "";
    //Pour l'ajout d'une photo
    private PhotoForm ajoutPhoto = null;

    /**
     * Constructeur de VehiculeForm par défaut.
     */
    public VehiculeForm() {}


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
     * Retourne le numéro de dossier.
     *
     * @return String Valeur du numéro de dossier en caractère.
     */
    public String getNumeroDossier() {
        return this.numeroDossier;
    }

    /**
     * Retourne le site du dossier.
     *
     * @return String Valeur du site du dossier en caractère.
     */
    public String getSiteDossier() {
        return this.siteDossier;
    }

    /**
     * Retourne la marque.
     *
     * @return String Valeur de la marque en caractère.
     */
    public String getMarque() {
        return this.marque;
    }

    /**
     * Retourne le modele.
     *
     * @return String Valeur du modele en caractère.
     */
    public String getModele() {
        return this.modele;
    }

    /**
     * Retourne l'immatriculation.
     *
     * @return String Valeur de l'immatriculation en caractère.
     */
    public String getImmatriculation() {
        return this.immatriculation;
    }

    /**
     * Retourne la province.
     *
     * @return String Valeur de la province en caractère.
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * Retourne l'annee.
     *
     * @return String Valeur de l'annee en caractère.
     */
    public String getAnnee() {
        return this.annee;
    }

    /**
     * Retourne la vignette.
     *
     * @return String Valeur de la vignette en caractère.
     */
    public String getVignette() {
        return this.vignette;
    }

    /**
     * Retourne l'assurance.
     *
     * @return String Valeur de l'assurance en caractère.
     */
    public String getAssurance() {
        return this.assurance;
    }

    /**
     * Retourne la date d'expiration de la vignette.
     *
     * @return String Valeur de la date d'expiration de la vignette en
     * caractère.
     */
    public String getDateExpirationVignette() {
        return this.dateExpirationVignette;
    }

    /**
     * Retourne le numéro de série.
     *
     * @return String Valeur du numéro de série en caractère.
     */
    public String getNumeroSerie() {
        return this.numeroSerie;
    }

    /**
     * Retourne la date d'expiration de l'assurance.
     *
     * @return String Valeur de la date d'expiration de l'assurance en
     * caractère.
     */
    public String getDateExpirationAssurance() {
        return this.dateExpirationAssurance;
    }

    /**
     * Retourne la police.
     *
     * @return String Valeur de la police en caractère.
     */
    public String getPolice() {
        return this.police;
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
     * @return String Valeur du mot de la confirmation du passe en caractère.
     */
    public String getConfirmationMotPasse() {
        return this.confirmationMotPasse;
    }

    /**
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur() {
        return this.createur;
    }

    /**
     * Retourne la date de création.
     *
     * @return String Valeur de la date de création en caractère.
     */
    public String getDateCreation() {
        return this.dateCreation;
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
     * @return String Valeur du rôle en caractère.
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Retourne le type de lien.
     *
     * @return String Valeur du type de lien en caractère.
     */
    public String getTypeLien() {
        return this.typeLien;
    }

    /**
     * Retourne les narrations associées.
     *
     * @return Collection Valeur de la collection de narrations.
     */
    public Collection getNarrations() {
        return this.listeNarrations.getResultatComplet();
    }

    /**
     * Retourne les dossiers associés.
     *
     * @return Collection Valeur de la collection de dossiers.
     */
    public Collection getDossiers() {
        return this.listeDossiers.getResultatComplet();
    }

    /**
     * Retourne les particularités associées.
     *
     * @return Collection Valeur de la collection de particularités.
     */
    public ParticularitesHtmlForm getParticularites() {
        return this.particularites;
    }

    /**
     * Retourne les sujets associés.
     *
     * @return Collection Valeur de la collection de sujets.
     */
    public Collection getSujets() {
        return this.listeSujets.getResultatComplet();
    }

    /**
     * Retourne les sociétés associées.
     *
     * @return Collection Valeur de la collection de sociétés.
     */
    public Collection getSocietes() {
        return this.listeSocietes.getResultatComplet();
    }

    /**
     * Retourne les photos associés.
     *
     * @return Collection Valeur de la collection de photos.
     */
    public Collection getPhotos() {
        return this.photos;
    }

    /**
     * Test si le véhicule est un nouvellement créé.
     *
     * @return True si le véhicule est nouvellement créé.
     */
    public boolean isNew() {
      return this.nouveau;
    }


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle en caractère.
     */
    public void setCle(String cle) {
        this.cle = cle;
    }

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(String site) {
        this.site = site;
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
     * Affecte un site du dossier.
     *
     * @param siteDossier Valeur du site du dossier en caractère.
     */
    public void setSiteDossier(String siteDossier) {
        this.siteDossier = siteDossier;
    }

    /**
     * Affecte une marque.
     *
     * @param marque Valeur de la marque en caractère.
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * Affecte un modele.
     *
     * @param modele Valeur du modele en caractère.
     */
    public void setModele(String modele) {
        this.modele = modele;
    }

    /**
     * Affecte une immatriculation.
     *
     * @param immatriculation Valeur de l'immatriculation en caractère.
     */
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    /**
     * Affecte une province.
     *
     * @param province Valeur de la province en caractère.
     */
    public void setProvince(String province) {
        this.province = province;
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
     * Réinitialise des onglets
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
     * @param annee Valeur de l'annee en caractère.
     */
    public void setAnnee(String annee) {
        this.annee = annee;
    }

    /**
     * Affecte une assurance.
     *
     * @param annee Valeur de l'assurance en caractère.
     */
    public void setAssurance(String assurance) {
        this.assurance = assurance;
    }

    /**
     * Affecte une vignette.
     *
     * @param vignette Valeur de la vignette en caractère.
     */
    public void setVignette(String vignette) {
        this.vignette = vignette;
    }

    /**
     * Affecte une date d'expiration de la vignette.
     *
     * @param dateExpirationVignette Valeur de la date d'expiration de la
     * vignette en caractère.
     */
    public void setDateExpirationVignette(String dateExpirationVignette) {
        this.dateExpirationVignette = dateExpirationVignette;
    }

    /**
     * Affecte un numéro de série.
     *
     * @param numeroSerie Valeur du numéro de série en caractère.
     */
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    /**
     * Affecte une date d'expiration de l'assurance.
     *
     * @param dateExpirationAssurance Valeur de la date d'expiration de
     * l'assurance en caractère.
     */
    public void setDateExpirationAssurance(String dateExpirationAssurance) {
        this.dateExpirationAssurance = dateExpirationAssurance;
    }

    /**
     * Affecte une police.
     *
     * @param police Valeur de la police en caractère.
     */
    public void setPolice(String police) {
        this.police = police;
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
     * Affecte un créateur.
     *
     * @param createur Valeur du créateur en caractère.
     */
    public void setCreateur(String createur) {
        this.createur = createur;
    }

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création en caractère.
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
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
     * Affecte un rôle.
     *
     * @param role Valeur du rôle en caractère.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Affecte un type de lien.
     *
     * @param typeLien Valeur du type de lien en caractère.
     */
    public void setTypeLien(String typeLien) {
        this.typeLien = typeLien;
    }

    /**
     * Ajoute une narration associée.
     *
     * @param narration Valeur de la narration à ajouter.
     */
    public void addNarration(NarrationHtmlForm narration) {
        this.listeNarrations.add(narration);
    }

    /**
     * Ajoute un dossier associé.
     *
     * @param dossier Valeur du dossier à ajouter.
     */
    public void addDossier(DossierHtmlForm dossier) {
        this.listeDossiers.add(dossier);
    }

    /**
     * Ajoute une particularité associée.
     *
     * @param particularite Valeur de la particularité à ajouter.
     */
//    public void addParticularite(ParticularitesForm particularite) {
//        this.particularites.add(particularite);
//    }

    /**
     * Ajoute un sujet associé.
     *
     * @param Collection Valeur du sujet à ajouter.
     */
    public void addSujet(SujetHtmlForm sujet) {
        this.listeSujets.add(sujet);
    }

    /**
     * Ajoute une sociétés associée.
     *
     * @param societe Valeur de la société à ajouter.
     */
    public void addSociete(SocieteHtmlForm societe) {
        this.listeSocietes.add(societe);
    }

    /**
     * Ajoute une photo associée.
     *
     * @param photo Valeur de la photo à ajouter.
     */
    public void addPhoto(Collection photo) {
        this.photos.add(photo);
    }

     /**
     * Ajoute une particularité
     */
    public void setParticularites(ParticularitesHtmlForm particularite) {
         this.particularites = particularite;
    }

     /**
     * Détermine si le véhicule est nouvellement créé.
     *
     * @param isNew Valeur si le dossier est nouvellement créer.
     */
    public void setNew(boolean nouveau){
        this.nouveau = nouveau;
    }

  /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du VehiculeForm.
     *
     * @return String Valeur de tout les attributs du VehiculeForm en caractère.
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
	 * @param entite entite à définir
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
	 * @param confidentialiteDescription confidentialiteDescription à définir
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
	 * @param marqueDescription marqueDescription à définir
	 */
	public void setMarqueDescription(String marqueDescription) {
		this.marqueDescription = marqueDescription;
	}


	/**
	 * @param modeleDescription modeleDescription à définir
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
	 * @param createurDescription createurDescription à définir
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
	 * @param ajoutPhoto ajoutPhoto à définir
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
	 * @param siteDescription siteDescription à définir
	 */
	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}
}