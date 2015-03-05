package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.SuiviHtmlForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.ConfidentialiteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeActiviteCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.model.EntiteCardexForm;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Permet de transiter les informations relatives à un suivi de la couche
 * présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.SuiviHtmlForm
 */
public class SuiviForm extends ValidatorForm  implements SuiviHtmlForm, EntiteCardexForm, Serializable {

    private String cle = "";
    private String site = "";
    private String entite = "";
    private String siteDescription = "";
    private String activite = "";
    private String activiteDescription = "";
    private String suivi = "";
    private String statut = "";
    private String statutDescription = "";
    private String datePrevue = "";
    private String dateCompletee = "";
    private String secteurOrigine = "";
    private String secteurOrigineDescription = "";
    private String demandeur = "";
    private String demandeurDescription = "";
    private String intervenant = "";
    private String intervenantDescription = "";
    private String secteurAssigne = "";
    private String secteurAssigneDescription = "";
    private String createur = "";
    private String confidentialiteSuivi = "";
    private String confidentialiteSuiviDescription = "";
    private String niveauHierarchiqueSuivi = "";
    private String confidentialiteCreateur = "";
    private String niveauHierarchiqueCreateur = "";
    private String dateCreation = "";
    private String modificateur = "";
    private String dateModification = "";
    private String approbateur = "";
    private String approbateurDescription = "";
    private String confidentialiteApprobateur = "";
    private String niveauHierarchiqueApprobateur = "";
    private String dateApprobation = "";
    private String reference1 = "";
    private String reference2 = "";
    private String reference3 = "";
    private String lien = "";
    private String lienSite = "";
    private DossierForm dossier = null;
    private boolean permettreComplete = false;
    private boolean modifiable = false;
    private boolean approuvable = false;
    //Mars 2011. Ajout de deux champs pour la lecture de l'audit.
    //Les autres champs de l'audit sont identiques à ceux du dossier.
    private String dateChangement= "";
    private String changePar = "";

    /**
     * Constructeur de SuiviForm par défaut.
     */
    public SuiviForm() {}


    // Getters

    /**
     * Test si un suivi peut être modifié.
     *
     * @return boolean True si la narration est modifiable.
     */
    public boolean isModifiable() {
        return this.modifiable;
    }

    /**
     * Test si un suivi est approuvé.
     *
     * @return boolean True si la narration est approuv.
     */
    public boolean isApprouve() {
        return (this.dateApprobation != null && this.dateApprobation.trim().length() > 0);
    }

    /**
     * Test si un suivi peut être approuvé.
     *
     * @return boolean True si la narration est approuvable.
     */
    public boolean isApprouvable() {
        return this.approuvable;
    }

    /**
     * Test si on peut compléter un suivi.
     *
     * @return boolean True si on peut permettre la modification d'une narration.
     */
    public boolean isPermettreComplete(){
      return this.permettreComplete;
    }

    /**
     * Determine si un suivi est modifiable
     *
     * @param isModifiable Est-ce que le suivi est modifiable
     * caractère.
     */
    public void setModifiable(boolean modifiable) {
      this.modifiable = modifiable;
    }

    /**
     * Determine si un suivi est approuvanle
     *
     * @param isApprouvable Est-ce que le suivi est approuvable
     * caractère.
     */
    public void setApprouvable(boolean approuvable) {
      this.approuvable = approuvable;
    }

    /**
     * Détermine si on peut compléter une suivi.
     *
     * @param isPermettreComplete Est-ce qu'on peut compléter une suivi.
     * caractère.
     */
    public void setPermettreComplete(boolean permettreComplete){
      this.permettreComplete = permettreComplete;
    }

    /**
     * Retourne le dossier asssocie à la narration .
     *
     * @return DossierForm Valeur de la cle en caractère.
     */
    public DossierForm getDossier() {
        return this.dossier;
    }

    /**
     * Retourne la clé.
     *
     * @return String Valeur de la clé en caractère.
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
     * Retourne l'activité.
     *
     * @return String Valeur de l'activité.
     */
    public String getActivite() {
        return this.activite;
    }

    /**
     * Retourne le suivi.
     *
     * @return String Valeur du suivi en caractère.
     */
    public String getSuivi() {
        if (this.suivi != null && this.suivi.length() > 2000) {
          this.suivi = this.suivi.substring(this.suivi.length()-2000,this.suivi.length());
        }
        return this.suivi;
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
     * Retourne la date prévue.
     *
     * @return String Valeur de la date prévue en caractère.
     */
    public String getDatePrevue() {
        return this.datePrevue;
    }

    /**
     * Retourne la date complétée.
     *
     * @return String Valeur de la date complétée en caractère.
     */
    public String getDateCompletee() {
        return this.dateCompletee;
    }

    /**
     * Retourne le secteur d'origine.
     *
     * @return String Valeur du secteur d'origine en caractère.
     */
    public String getSecteurOrigine() {
        return this.secteurOrigine;
    }

    /**
     * Retourne le demandeur.
     *
     * @return String Valeur du demandeur en caractère.
     */
    public String getDemandeur() {
        return this.demandeur;
    }

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant() {
        return this.intervenant;
    }

    /**
     * Retourne le secteur assigné.
     *
     * @return String Valeur du secteur assigné en caractère.
     */
    public String getSecteurAssigne() {
        return this.secteurAssigne;
    }

    /**
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère en caractère.
     */
    public String getCreateur() {
        return this.createur;
    }

    /**
     * Retourne la confidentialité du suivi.
     *
     * @return String Valeur de la confidentialité du suivi en caractère.
     */
    public String getConfidentialiteSuivi() {
        return this.confidentialiteSuivi;
    }

    /**
     * Retourne le niveau hierarchique du suivi.
     *
     * @return String Valeur du niveau hierarchique du suivi en caractère.
     */
    public String getNiveauHierarchiqueSuivi() {
        return this.niveauHierarchiqueSuivi;
    }

    /**
     * Retourne la confidentialité du créateur.
     *
     * @return String Valeur de la confidentialité du créateur en caractère.
     */
    public String getConfidentialiteCreateur() {
        return this.confidentialiteCreateur;
    }

    /**
     * Retourne le niveau hierarchique du créateur.
     *
     * @return String Valeur du niveau hierarchique du créateur en caractère.
     */
    public String getNiveauHierarchiqueCreateur() {
        return this.niveauHierarchiqueCreateur;
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
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caractère.
     */
    public String getModificateur() {
        return this.modificateur;
    }

    /**
     * Retourne la date de modification.
     *
     * @return String Valeur de la date de modification en caractère.
     */
    public String getDateModification() {
        return this.dateModification;
    }

    /**
     * Retourne l'approbateur.
     *
     * @return String Valeur de l'approbateur en caractère.
     */
    public String getApprobateur() {
        return this.approbateur;
    }

    /**
     * Retourne la confidentialité de l'approbateur.
     *
     * @return String Valeur de la confidentialité de l'approbateur en
     * caractère.
     */
    public String getConfidentialiteApprobateur() {
        return this.confidentialiteApprobateur;
    }

    /**
     * Retourne le niveau hierarchique de l'approbateur.
     *
     * @return String Valeur du niveau hierarchique de l'approbateur en
     * caractère.
     */
    public String getNiveauHierarchiqueApprobateur() {
        return this.niveauHierarchiqueApprobateur;
    }

    /**
     * Retourne la date d'approbateur.
     *
     * @return String Valeur de la date d'approbateur en caractère.
     */
    public String getDateApprobation() {
        return this.dateApprobation;
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
     * Retourne la troisième référence.
     *
     * @return String Valeur de la troisième référence en caractère.
     */
    public String getReference3() {
        return this.reference3;
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


    // Setters

    /**
     * Affecte un un dossier associe a la narration.
     *
     * @param dossier Le dossier associe.
     */
    public void setDossier(DossierForm dossier) {
        this.dossier = dossier;
    }

    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la clé en caractère.
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
     * Affecte une activité.
     *
     * @param activite Valeur de l'activité en caractère.
     */
    public void setActivite(String activite) {
        this.activite = activite;
    }

    /**
     * Affecte un suivi.
     *
     * @param suivi Valeur du suivi en caractère en caractère.
     */
    public void setSuivi(String suivi) {
        this.suivi = suivi;
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
     * Affecte une date prévue.
     *
     * @param datePrevue Valeur de la date prévue en caractère.
     */
    public void setDatePrevue(String datePrevue) {
        this.datePrevue = datePrevue;
    }

    /**
     * Affecte une date complétée.
     *
     * @param dateCompletee Valeur de la date complétée en caractère.
     */
    public void setDateCompletee(String dateCompletee) {
        this.dateCompletee = dateCompletee;
    }

    /**
     * Affecte un secteur d'origine.
     *
     * @return secteurOrigine Valeur du secteur d'origine en caractère.
     */
    public void setSecteurOrigine(String secteurOrigine) {
        this.secteurOrigine = secteurOrigine;
    }

    /**
     * Affecte un demandeur.
     *
     * @param demandeur Valeur du demandeur en caractère.
     */
    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant) {
        this.intervenant = intervenant;
    }

    /**
     * Affecte un secteur assigné.
     *
     * @param secteurAssigne Valeur du secteur assigné en caractère.
     */
    public void setSecteurAssigne(String secteurAssigne) {
        this.secteurAssigne = secteurAssigne;
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
     * Affecte une confidentialité du suivi.
     *
     * @param confidentialiteSuivi Valeur de la confidentialité du suivi en
     * caractère.
     */
    public void setConfidentialiteSuivi(String confidentialiteSuivi) {
        this.confidentialiteSuivi = confidentialiteSuivi;
    }

    /**
     * Affecte un niveau hierarchique du suivi.
     *
     * @param niveauHierarchiqueSuivi Valeur du niveau hierarchique du suivi en
     * caractère.
     */
    public void setNiveauHierarchiqueSuivi(String niveauHierarchiqueSuivi) {
        this.niveauHierarchiqueSuivi = niveauHierarchiqueSuivi;
    }

    /**
     * Affecte une confidentialité du créateur.
     *
     * @param confidentialiteCreateur Valeur de la confidentialité du créateur
     * en caractère.
     */
    public void setConfidentialiteCreateur(String confidentialiteCreateur) {
        this.confidentialiteCreateur = confidentialiteCreateur;
    }

    /**
     * Affecte un niveau hierarchique du créateur.
     *
     * @param niveauHierarchiqueCreateur Valeur du niveau hierarchique du
     * créateur.
     */
    public void setNiveauHierarchiqueCreateur(String niveauHierarchiqueCreateur) {
        this.niveauHierarchiqueCreateur = niveauHierarchiqueCreateur;
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
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caractère.
     */
    public void setModificateur(String modificateur) {
        this.modificateur = modificateur;
    }

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caractère.
     */
    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }

    /**
     * Affecte un approbateur.
     *
     * @param approbateur Valeur de l'approbateur en caractère.
     */
    public void setApprobateur(String approbateur) {
        this.approbateur = approbateur;
    }

    /**
     * Affecte une confidentialité de l'approbateur.
     *
     * @param confidentialiteApprobateur Valeur de la confidentialité de
     * l'approbateur en caractère.
     */
    public void setConfidentialiteApprobateur(
            String confidentialiteApprobateur) {
        this.confidentialiteApprobateur = confidentialiteApprobateur;
    }

    /**
     * Affecte un niveau hierarchique de l'approbateur.
     *
     * @param niveauHierarchiqueApprobateur Valeur du niveau hierarchique de
     * l'approbateur en caractère.
     */
    public void setNiveauHierarchiqueApprobateur(
            String niveauHierarchiqueApprobateur) {
        this.niveauHierarchiqueApprobateur = niveauHierarchiqueApprobateur;
    }

    /**
     * Affecte une date d'approbateur.
     *
     * @param dateApprobation Valeur de la date d'approbateur en caractère.
     */
    public void setDateApprobation(String dateApprobation) {
        this.dateApprobation = dateApprobation;
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
     * Retourne la deuxième référence.
     *
     * @param reference2 Valeur de la deuxième référence en caractère.
     */
    public void setReference2(String reference2) {
        this.reference2 = reference2;
    }

    /**
     * Affecte une troisième référence.
     *
     * @param reference3 Valeur de la troisième référence en caractère.
     */
    public void setReference3(String reference3) {
        this.reference3 = reference3;
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
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
/*       this.cle = "";
       this.site = "";
       this.activite = "";
       this.suivi = "";
       this.statut = "";
       this.datePrevue = "";
       this.dateCompletee = "";
       this.secteurOrigine = "";
       this.demandeur = "";
       this.intervenant = "";
       this.secteurAssigne = "";
       this.createur = "";
       this.confidentialiteSuivi = "";
       this.niveauHierarchiqueSuivi = "";
       this.confidentialiteCreateur = "";
       this.niveauHierarchiqueCreateur = "";
       this.dateCreation = "";
       this.modificateur = "";
       this.dateModification = "";
       this.approbateur = "";
       this.confidentialiteApprobateur = "";
       this.niveauHierarchiqueApprobateur = "";
       this.dateApprobation = "";
       this.reference1 = "";
       this.reference2 = "";
       this.reference3 = "";
       this.lien = "";
       this.lienSite = "";
       this.dossier = new DossierForm();
       this.changePar = "";
       this.dateChangement = "";
*/    }

    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void init(ActionMapping mapping,
                      HttpServletRequest request) {
       this.cle = "";
       this.site = "";
       this.entite = "";
       this.activite = "";
       this.suivi = "";
       this.statut = "";
       this.datePrevue = "";
       this.dateCompletee = "";
       this.secteurOrigine = "";
       this.demandeur = "";
       this.intervenant = "";
       this.secteurAssigne = "";
       this.createur = "";
       this.confidentialiteSuivi = "";
       this.niveauHierarchiqueSuivi = "";
       this.confidentialiteCreateur = "";
       this.niveauHierarchiqueCreateur = "";
       this.dateCreation = "";
       this.modificateur = "";
       this.dateModification = "";
       this.approbateur = "";
       this.confidentialiteApprobateur = "";
       this.niveauHierarchiqueApprobateur = "";
       this.dateApprobation = "";
       this.reference1 = "";
       this.reference2 = "";
       this.reference3 = "";
       this.lien = "";
       this.lienSite = "";
       this.dossier = new DossierForm();
       this.changePar = "";
       this.dateChangement = "";
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du SuiviForm.
     *
     * @return String Valeur de tout les attributs du SuiviForm en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[SuiviForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', activite = '" + activite);
        stringBuffer.append("', suivi = '" + suivi);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', datePrevue = '"
                + datePrevue);
        stringBuffer.append("', dateCompletee = '"
                + dateCompletee);
        stringBuffer.append("', secteurOrigine = '" + secteurOrigine);
        stringBuffer.append("', demandeur = '" + demandeur);
        stringBuffer.append("', intervenant = '" + intervenant);
        stringBuffer.append("', secteurAssigne = '" + secteurAssigne);
        stringBuffer.append("', createur = '" + createur);
        stringBuffer.append("', confidentialiteSuivi = '"
                + confidentialiteSuivi);
        stringBuffer.append("', niveauHierarchiqueSuivi = '"
                + niveauHierarchiqueSuivi);
        stringBuffer.append("', confidentialiteCreateur = '"
                + confidentialiteCreateur);
        stringBuffer.append("', niveauHierarchiqueCreateur = '"
                + niveauHierarchiqueCreateur);
        stringBuffer.append("', dateCreation = '"
                + dateCreation);
        stringBuffer.append("', modificateur = '" + modificateur);
        stringBuffer.append("', dateModification = '"
                + dateModification);
        stringBuffer.append("', approbateur = '" + approbateur);
        stringBuffer.append("', confidentialiteApprobateur = '"
                + confidentialiteApprobateur);
        stringBuffer.append("', niveauHierarchiqueApprobateur = '"
                + niveauHierarchiqueApprobateur);
        stringBuffer.append("', dateApprobation = '"
                + dateApprobation);
        stringBuffer.append("', reference1 = '" + reference1);
        stringBuffer.append("', reference2 = '" + reference2);
        stringBuffer.append("', reference2 = '" + reference2);
        stringBuffer.append("', lien = '" + lienSite );
        stringBuffer.append("', lienSite = '" + lienSite );
        stringBuffer.append("']");
        return stringBuffer.toString();
    }
    
    public String getActiviteDescription() {
		return activiteDescription;
	}
    
	public String getDemandeurDescription() {
		return demandeurDescription;
	}
	
	public String getIntervenantDescription() {
		return intervenantDescription;
	}
	
	public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException {
    	ListeCache cache = ListeCache.getInstance();
    	
    	activiteDescription = cache.obtenirLabel(subject, getActivite(), new TypeActiviteCleListeCache(subject));
		statutDescription = cache.obtenirLabel(subject, getStatut(), new StatutCleListeCache(subject, GlobalConstants.ListeCache.Statut.SUIVI));
    	demandeurDescription = cache.obtenirLabel(subject, getDemandeur(), new IntervenantCle(subject));
    	approbateurDescription = cache.obtenirLabel(subject, getApprobateur(), new IntervenantCle(subject));
    	intervenantDescription = cache.obtenirLabel(subject, getIntervenant(), new IntervenantCle(subject));
    	siteDescription = cache.obtenirLabel(subject, getSite(), new TableValeurCleSQLListeCache(subject, GlobalConstants.TableValeur.SITE, GlobalConstants.ActionSecurite.MODIFICATION));
		confidentialiteSuiviDescription = cache.obtenirLabel(subject, getConfidentialiteSuivi(), new ConfidentialiteCleListeCache(subject));
    	secteurAssigneDescription = cache.obtenirLabel(subject, getSecteurAssigne(), new TableValeurCleSQLListeCache(subject,GlobalConstants.TableValeur.SECTEUR,GlobalConstants.ActionSecurite.SELECTION));
    	secteurOrigineDescription = cache.obtenirLabel(subject, getSecteurAssigne(), new TableValeurCleSQLListeCache(subject,GlobalConstants.TableValeur.SECTEUR,GlobalConstants.ActionSecurite.SELECTION));
	}	    
	
	public String getSiteDescription() {
		return siteDescription;
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


	/**
	 * @return secteurOrigineDescription
	 */
	public String getSecteurOrigineDescription() {
		return secteurOrigineDescription;
	}


	/**
	 * @param secteurOrigineDescription secteurOrigineDescription à définir
	 */
	public void setSecteurOrigineDescription(String secteurOrigineDescription) {
		this.secteurOrigineDescription = secteurOrigineDescription;
	}


	/**
	 * @return secteurAssigneDescription
	 */
	public String getSecteurAssigneDescription() {
		return secteurAssigneDescription;
	}


	/**
	 * @param secteurAssigneDescription secteurAssigneDescription à définir
	 */
	public void setSecteurAssigneDescription(String secteurAssigneDescription) {
		this.secteurAssigneDescription = secteurAssigneDescription;
	}


	/**
	 * @return confidentialiteSuiviDescription
	 */
	public String getConfidentialiteSuiviDescription() {
		return confidentialiteSuiviDescription;
	}


	/**
	 * @param confidentialiteSuiviDescription confidentialiteSuiviDescription à définir
	 */
	public void setConfidentialiteSuiviDescription(
			String confidentialiteSuiviDescription) {
		this.confidentialiteSuiviDescription = confidentialiteSuiviDescription;
	}


	/**
	 * @param siteDescription siteDescription à définir
	 */
	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}


	/**
	 * @param activiteDescription activiteDescription à définir
	 */
	public void setActiviteDescription(String activiteDescription) {
		this.activiteDescription = activiteDescription;
	}


	/**
	 * @param demandeurDescription demandeurDescription à définir
	 */
	public void setDemandeurDescription(String demandeurDescription) {
		this.demandeurDescription = demandeurDescription;
	}


	/**
	 * @param intervenantDescription intervenantDescription à définir
	 */
	public void setIntervenantDescription(String intervenantDescription) {
		this.intervenantDescription = intervenantDescription;
	}


	/**
	 * @return approbateurDescription
	 */
	public String getApprobateurDescription() {
		return approbateurDescription;
	}


	/**
	 * @param approbateurDescription approbateurDescription à définir
	 */
	public void setApprobateurDescription(String approbateurDescription) {
		this.approbateurDescription = approbateurDescription;
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
}