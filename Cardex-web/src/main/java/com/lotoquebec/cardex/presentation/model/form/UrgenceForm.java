package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorException;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.Resources;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.UrgenceHtmlForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ClasseCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Permet de transiter les informations relatives à la consultation d'une
 * adresse de la couche présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/03/20 22:06:56 $
 * @see com.lotoquebec.cardex.presentation.model.AdresseHtmlForm
 */
public class UrgenceForm extends ValidatorForm implements UrgenceHtmlForm, Serializable {

    private String cle = "";
    private String site = "";
    private String siteDescription = "";
    private String district = "";
    private String classe  = "";
    private String classeDescription  = "";
    private String unite = "";
    private String contact = "";
    private String contactPrenom = "";
    private String fonctionGrade = "";
    private String matricule =  "";
    private String evenement = "";
    private String telephone = "";
    private String poste = "";
    private String telecopieur = "";
    private String courriel = "";
    private String motif = "";
    private String motifDescription = "";
	private boolean repondant = false;
    private String statut = "";
    private String statutDescription = "";
    private String ville = "";
    private String createur = "";
    private String dateCreation = "";
    private String lien = "";
    private String lienSite = "";
    private String lienSociete = "";
    private String lienSiteSociete = "";
    private String societe = "";
    private String numeroDossier = "";
    private String choixClasse = ""; //Pour choisir la classe dans l'onglet Services d'urgence avant d'ajouter.
    private DossierForm dossier = null;

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tous les
     * attributs du UrgenceForm.
     *
     * @return String Valeur de tous les attributs du UrgenceForm en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[UrgenceForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', classe = '" + classe);
        stringBuffer.append("', classeDescription = '" + classeDescription);
        stringBuffer.append("', motif = '" + motif);
        stringBuffer.append("', telephone = '" + telephone);
        stringBuffer.append("', poste = '" + poste);
        stringBuffer.append("', telecopieur = '" + telecopieur);
        stringBuffer.append("', courriel = '" + courriel);
        stringBuffer.append("', unite = '" + unite);
        stringBuffer.append("', repondant = '" + repondant);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', ville = '" + ville);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', district = '" + district);
        stringBuffer.append("', contact = '" + contact);
        stringBuffer.append("', contactPrenom = '" + contactPrenom);
        stringBuffer.append("', fonctionGrade = '" + fonctionGrade);
        stringBuffer.append("', matricule = '" + matricule);
        stringBuffer.append("', createur = '" + createur);
        stringBuffer.append("', dateCreation = '" + dateCreation);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', lienSociete = '" + lienSociete);
        stringBuffer.append("', lienSiteSociete = '" + lienSiteSociete);
        stringBuffer.append("', societe = '" + societe);
        stringBuffer.append("', numeroDossier = '" + numeroDossier);
        stringBuffer.append("']");
        return stringBuffer.toString();
    }
	
    public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException{
    	ListeCache cache = ListeCache.getInstance();
    	
    	siteDescription = cache.obtenirLabel(subject, site, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.MODIFICATION));
    	statutDescription = cache.obtenirLabel(subject, statut, new StatutCleListeCache(subject, GlobalConstants.ListeCache.Statut.ADRESSE));
    	classeDescription = cache.obtenirLabel(subject, classe, new ClasseCleListeCache(subject));
    	motifDescription = cache.obtenirLabel(subject, motif, new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.MOTIF, GlobalConstants.ActionSecurite.MODIFICATION));
    }

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ServletContext application = getServlet().getServletContext();
        ActionErrors errors = new ActionErrors();

        String validationKey = getValidationKey(mapping, request);

        Validator validator =
            Resources.initValidator(validationKey, this, application, request,
                errors, page);
        
        try {
       	
            validatorResults = validator.validate();
        } catch (ValidatorException e) {
            e.printStackTrace();
        }

        return errors;
    }

	public void init(){
	    cle = "";
	    site = "";
	    siteDescription = "";
	    classe = "";
	    classeDescription  = "";
	    contact = "";
	    contactPrenom = "";
	    fonctionGrade = "";
	    matricule = "";
	    district = "";
	    motif = "";
	    motifDescription = "";
	    unite = "";
	    evenement = "";
	    telephone = "";
	    poste= "";
	    telecopieur = "";
	    courriel = "";
	    unite = "";
	    statut = "";
	    statutDescription = "";
	    repondant = false;
	    ville = "";
	    createur = "";
    	dateCreation = "";
    	lien = "";
    	lienSite = "";
    	lienSociete = "";
    	lienSiteSociete = "";
    	societe = "";
    	numeroDossier = "";
    	this.dossier = new DossierForm();
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {		  
		  this.repondant = false;
	}

	public String getCle() {
		return cle;
	}

	public void setCle(String cle) {
		this.cle = cle;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getSiteDescription() {
		return siteDescription;
	}

	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getClasseDescription() {
		return classeDescription;
	}

	public void setClasseDescription(String classeDescription) {
		this.classeDescription = classeDescription;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

    public String getPoste() {
        return poste;
    }
    
	public void setPoste(String poste) {
	    this.poste = poste;
	}
	
	public String getTelecopieur() {
		return telecopieur;
	}

	public void setTelecopieur(String telecopieur) {
		this.telecopieur = telecopieur;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getMotifDescription() {
		return motifDescription;
	}

	public void setMotifDescription(String motifDescription) {
		this.motifDescription = motifDescription;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getStatutDescription() {
		return statutDescription;
	}

	public void setStatutDescription(String statutDescription) {
		this.statutDescription = statutDescription;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCreateur() {
		return createur;
	}

	public void setCreateur(String createur) {
		this.createur = createur;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public String getLienSite() {
		return lienSite;
	}

	public void setLienSite(String lienSite) {
		this.lienSite = lienSite;
	}

	public String getEvenement() {
		return evenement;
	}

	public void setEvenement(String evenement) {
		this.evenement = evenement;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPrenom()
    {
        return contactPrenom;
    }

    public void setContactPrenom(String contactPrenom)
    {
        this.contactPrenom = contactPrenom;
    }

    public String getFonctionGrade()
    {
        return fonctionGrade;
    }

    public void setFonctionGrade(String fonctionGrade)
    {
        this.fonctionGrade = fonctionGrade;
    }

    public String getMatricule()
    {
        return matricule;
    }

    public void setMatricule(String matricule)
    {
        this.matricule = matricule;
    }

    public String getLienSociete() {
		return lienSociete;
	}

	public void setLienSociete(String lienSociete) {
		this.lienSociete = lienSociete;
	}

	public String getLienSiteSociete() {
		return lienSiteSociete;
	}

	public void setLienSiteSociete(String lienSiteSociete) {
		this.lienSiteSociete = lienSiteSociete;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	public String getChoixClasse() {
		return choixClasse;
	}

	public void setChoixClasse(String choixClasse) {
		this.choixClasse = choixClasse;
	}

	public boolean isRepondant() {
		return repondant;
	}

	public void setRepondant(boolean repondant) {
		this.repondant = repondant;
	}

    public DossierForm getDossier()
    {
        return dossier;
    }

    public void setDossier(DossierForm dossier)
    {
        this.dossier = dossier;
    }

    public String getNumeroDossier()
    {
        return numeroDossier;
    }

    public void setNumeroDossier(String numeroDossier)
    {
        this.numeroDossier = numeroDossier;
    }
}