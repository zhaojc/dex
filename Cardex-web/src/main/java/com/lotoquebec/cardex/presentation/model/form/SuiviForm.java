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
 * Permet de transiter les informations relatives � un suivi de la couche
 * pr�sentation � la couche d'affaire.
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
    //Les autres champs de l'audit sont identiques � ceux du dossier.
    private String dateChangement= "";
    private String changePar = "";

    /**
     * Constructeur de SuiviForm par d�faut.
     */
    public SuiviForm() {}


    // Getters

    /**
     * Test si un suivi peut �tre modifi�.
     *
     * @return boolean True si la narration est modifiable.
     */
    public boolean isModifiable() {
        return this.modifiable;
    }

    /**
     * Test si un suivi est approuv�.
     *
     * @return boolean True si la narration est approuv.
     */
    public boolean isApprouve() {
        return (this.dateApprobation != null && this.dateApprobation.trim().length() > 0);
    }

    /**
     * Test si un suivi peut �tre approuv�.
     *
     * @return boolean True si la narration est approuvable.
     */
    public boolean isApprouvable() {
        return this.approuvable;
    }

    /**
     * Test si on peut compl�ter un suivi.
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
     * caract�re.
     */
    public void setModifiable(boolean modifiable) {
      this.modifiable = modifiable;
    }

    /**
     * Determine si un suivi est approuvanle
     *
     * @param isApprouvable Est-ce que le suivi est approuvable
     * caract�re.
     */
    public void setApprouvable(boolean approuvable) {
      this.approuvable = approuvable;
    }

    /**
     * D�termine si on peut compl�ter une suivi.
     *
     * @param isPermettreComplete Est-ce qu'on peut compl�ter une suivi.
     * caract�re.
     */
    public void setPermettreComplete(boolean permettreComplete){
      this.permettreComplete = permettreComplete;
    }

    /**
     * Retourne le dossier asssocie � la narration .
     *
     * @return DossierForm Valeur de la cle en caract�re.
     */
    public DossierForm getDossier() {
        return this.dossier;
    }

    /**
     * Retourne la cl�.
     *
     * @return String Valeur de la cl� en caract�re.
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
     * Retourne l'activit�.
     *
     * @return String Valeur de l'activit�.
     */
    public String getActivite() {
        return this.activite;
    }

    /**
     * Retourne le suivi.
     *
     * @return String Valeur du suivi en caract�re.
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
     * @return String Valeur du statut en caract�re.
     */
    public String getStatut() {
        return this.statut;
    }

    /**
     * Retourne la date pr�vue.
     *
     * @return String Valeur de la date pr�vue en caract�re.
     */
    public String getDatePrevue() {
        return this.datePrevue;
    }

    /**
     * Retourne la date compl�t�e.
     *
     * @return String Valeur de la date compl�t�e en caract�re.
     */
    public String getDateCompletee() {
        return this.dateCompletee;
    }

    /**
     * Retourne le secteur d'origine.
     *
     * @return String Valeur du secteur d'origine en caract�re.
     */
    public String getSecteurOrigine() {
        return this.secteurOrigine;
    }

    /**
     * Retourne le demandeur.
     *
     * @return String Valeur du demandeur en caract�re.
     */
    public String getDemandeur() {
        return this.demandeur;
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
     * Retourne le secteur assign�.
     *
     * @return String Valeur du secteur assign� en caract�re.
     */
    public String getSecteurAssigne() {
        return this.secteurAssigne;
    }

    /**
     * Retourne le cr�ateur.
     *
     * @return String Valeur du cr�ateur en caract�re en caract�re.
     */
    public String getCreateur() {
        return this.createur;
    }

    /**
     * Retourne la confidentialit� du suivi.
     *
     * @return String Valeur de la confidentialit� du suivi en caract�re.
     */
    public String getConfidentialiteSuivi() {
        return this.confidentialiteSuivi;
    }

    /**
     * Retourne le niveau hierarchique du suivi.
     *
     * @return String Valeur du niveau hierarchique du suivi en caract�re.
     */
    public String getNiveauHierarchiqueSuivi() {
        return this.niveauHierarchiqueSuivi;
    }

    /**
     * Retourne la confidentialit� du cr�ateur.
     *
     * @return String Valeur de la confidentialit� du cr�ateur en caract�re.
     */
    public String getConfidentialiteCreateur() {
        return this.confidentialiteCreateur;
    }

    /**
     * Retourne le niveau hierarchique du cr�ateur.
     *
     * @return String Valeur du niveau hierarchique du cr�ateur en caract�re.
     */
    public String getNiveauHierarchiqueCreateur() {
        return this.niveauHierarchiqueCreateur;
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
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caract�re.
     */
    public String getModificateur() {
        return this.modificateur;
    }

    /**
     * Retourne la date de modification.
     *
     * @return String Valeur de la date de modification en caract�re.
     */
    public String getDateModification() {
        return this.dateModification;
    }

    /**
     * Retourne l'approbateur.
     *
     * @return String Valeur de l'approbateur en caract�re.
     */
    public String getApprobateur() {
        return this.approbateur;
    }

    /**
     * Retourne la confidentialit� de l'approbateur.
     *
     * @return String Valeur de la confidentialit� de l'approbateur en
     * caract�re.
     */
    public String getConfidentialiteApprobateur() {
        return this.confidentialiteApprobateur;
    }

    /**
     * Retourne le niveau hierarchique de l'approbateur.
     *
     * @return String Valeur du niveau hierarchique de l'approbateur en
     * caract�re.
     */
    public String getNiveauHierarchiqueApprobateur() {
        return this.niveauHierarchiqueApprobateur;
    }

    /**
     * Retourne la date d'approbateur.
     *
     * @return String Valeur de la date d'approbateur en caract�re.
     */
    public String getDateApprobation() {
        return this.dateApprobation;
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
     * @param cle Valeur de la cl� en caract�re.
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
     * Affecte une activit�.
     *
     * @param activite Valeur de l'activit� en caract�re.
     */
    public void setActivite(String activite) {
        this.activite = activite;
    }

    /**
     * Affecte un suivi.
     *
     * @param suivi Valeur du suivi en caract�re en caract�re.
     */
    public void setSuivi(String suivi) {
        this.suivi = suivi;
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
     * Affecte une date pr�vue.
     *
     * @param datePrevue Valeur de la date pr�vue en caract�re.
     */
    public void setDatePrevue(String datePrevue) {
        this.datePrevue = datePrevue;
    }

    /**
     * Affecte une date compl�t�e.
     *
     * @param dateCompletee Valeur de la date compl�t�e en caract�re.
     */
    public void setDateCompletee(String dateCompletee) {
        this.dateCompletee = dateCompletee;
    }

    /**
     * Affecte un secteur d'origine.
     *
     * @return secteurOrigine Valeur du secteur d'origine en caract�re.
     */
    public void setSecteurOrigine(String secteurOrigine) {
        this.secteurOrigine = secteurOrigine;
    }

    /**
     * Affecte un demandeur.
     *
     * @param demandeur Valeur du demandeur en caract�re.
     */
    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant) {
        this.intervenant = intervenant;
    }

    /**
     * Affecte un secteur assign�.
     *
     * @param secteurAssigne Valeur du secteur assign� en caract�re.
     */
    public void setSecteurAssigne(String secteurAssigne) {
        this.secteurAssigne = secteurAssigne;
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
     * Affecte une confidentialit� du suivi.
     *
     * @param confidentialiteSuivi Valeur de la confidentialit� du suivi en
     * caract�re.
     */
    public void setConfidentialiteSuivi(String confidentialiteSuivi) {
        this.confidentialiteSuivi = confidentialiteSuivi;
    }

    /**
     * Affecte un niveau hierarchique du suivi.
     *
     * @param niveauHierarchiqueSuivi Valeur du niveau hierarchique du suivi en
     * caract�re.
     */
    public void setNiveauHierarchiqueSuivi(String niveauHierarchiqueSuivi) {
        this.niveauHierarchiqueSuivi = niveauHierarchiqueSuivi;
    }

    /**
     * Affecte une confidentialit� du cr�ateur.
     *
     * @param confidentialiteCreateur Valeur de la confidentialit� du cr�ateur
     * en caract�re.
     */
    public void setConfidentialiteCreateur(String confidentialiteCreateur) {
        this.confidentialiteCreateur = confidentialiteCreateur;
    }

    /**
     * Affecte un niveau hierarchique du cr�ateur.
     *
     * @param niveauHierarchiqueCreateur Valeur du niveau hierarchique du
     * cr�ateur.
     */
    public void setNiveauHierarchiqueCreateur(String niveauHierarchiqueCreateur) {
        this.niveauHierarchiqueCreateur = niveauHierarchiqueCreateur;
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
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caract�re.
     */
    public void setModificateur(String modificateur) {
        this.modificateur = modificateur;
    }

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caract�re.
     */
    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }

    /**
     * Affecte un approbateur.
     *
     * @param approbateur Valeur de l'approbateur en caract�re.
     */
    public void setApprobateur(String approbateur) {
        this.approbateur = approbateur;
    }

    /**
     * Affecte une confidentialit� de l'approbateur.
     *
     * @param confidentialiteApprobateur Valeur de la confidentialit� de
     * l'approbateur en caract�re.
     */
    public void setConfidentialiteApprobateur(
            String confidentialiteApprobateur) {
        this.confidentialiteApprobateur = confidentialiteApprobateur;
    }

    /**
     * Affecte un niveau hierarchique de l'approbateur.
     *
     * @param niveauHierarchiqueApprobateur Valeur du niveau hierarchique de
     * l'approbateur en caract�re.
     */
    public void setNiveauHierarchiqueApprobateur(
            String niveauHierarchiqueApprobateur) {
        this.niveauHierarchiqueApprobateur = niveauHierarchiqueApprobateur;
    }

    /**
     * Affecte une date d'approbateur.
     *
     * @param dateApprobation Valeur de la date d'approbateur en caract�re.
     */
    public void setDateApprobation(String dateApprobation) {
        this.dateApprobation = dateApprobation;
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
     * Retourne la deuxi�me r�f�rence.
     *
     * @param reference2 Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public void setReference2(String reference2) {
        this.reference2 = reference2;
    }

    /**
     * Affecte une troisi�me r�f�rence.
     *
     * @param reference3 Valeur de la troisi�me r�f�rence en caract�re.
     */
    public void setReference3(String reference3) {
        this.reference3 = reference3;
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
     * R�initialise toute les attributs � leur valeur par d�faut.
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
     * R�initialise toute les attributs � leur valeur par d�faut.
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
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du SuiviForm.
     *
     * @return String Valeur de tout les attributs du SuiviForm en caract�re.
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
	 * @return secteurOrigineDescription
	 */
	public String getSecteurOrigineDescription() {
		return secteurOrigineDescription;
	}


	/**
	 * @param secteurOrigineDescription secteurOrigineDescription � d�finir
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
	 * @param secteurAssigneDescription secteurAssigneDescription � d�finir
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
	 * @param confidentialiteSuiviDescription confidentialiteSuiviDescription � d�finir
	 */
	public void setConfidentialiteSuiviDescription(
			String confidentialiteSuiviDescription) {
		this.confidentialiteSuiviDescription = confidentialiteSuiviDescription;
	}


	/**
	 * @param siteDescription siteDescription � d�finir
	 */
	public void setSiteDescription(String siteDescription) {
		this.siteDescription = siteDescription;
	}


	/**
	 * @param activiteDescription activiteDescription � d�finir
	 */
	public void setActiviteDescription(String activiteDescription) {
		this.activiteDescription = activiteDescription;
	}


	/**
	 * @param demandeurDescription demandeurDescription � d�finir
	 */
	public void setDemandeurDescription(String demandeurDescription) {
		this.demandeurDescription = demandeurDescription;
	}


	/**
	 * @param intervenantDescription intervenantDescription � d�finir
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
	 * @param approbateurDescription approbateurDescription � d�finir
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
	 * @param entite entite � d�finir
	 */
	public void setEntite(String entite) {
		this.entite = entite;
	}
}