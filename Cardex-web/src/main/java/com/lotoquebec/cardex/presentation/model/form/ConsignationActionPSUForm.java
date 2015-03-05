package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.ConsignationActionPSUHtmlForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleListeCache.TypeActionCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.StatutCleListeCache;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleMultiListeCache.cleMultiExterneListeCache.GenreFichierCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantParSiteCle;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Permet de transiter les informations relatives à un suivi de la couche
 * présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.SuiviHtmlForm
 */
public class ConsignationActionPSUForm extends ValidatorForm  implements ConsignationActionPSUHtmlForm, Serializable {

    private String cle = "";
    private String typeAction = "";
    private String typeActionDescription = "";
    private String numeroMandat = "";
    private String dateConsignation = "";
	private String dateCourriel = "";
    private String genreFichierAction = "";
    private String genreFichierActionDescription = "";
    private String genreFichier = "";
    private String referenceSourceCle = "";
    private String referenceSourceSite = "";
    private String referenceActionCle = "";
    private String referenceActionSite = "";
    private String referenceSource = "";
    private String referenceAction = "";
    private String intervenant = "";
    private String intervenantDescription = "";

	private DossierForm dossier = null;

    /**
     * Constructeur de SuiviForm par défaut.
     */
    public ConsignationActionPSUForm() {}


    // Getters

   
    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
       this.cle = "";
       this.typeAction = "";
       this.numeroMandat = "";
       this.dateConsignation = "";
	   this.dateCourriel = "";
	   this.genreFichier = "";
       this.genreFichierAction = "";
       this.referenceAction = "";
       this.referenceActionCle = "";
       this.referenceActionSite = "";
       this.referenceSource = "";
       this.referenceSourceCle = "";
       this.referenceSourceSite = "";
	   this.intervenant = "";
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du SuiviForm.
     *
     * @return String Valeur de tout les attributs du ConsignationForm en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[ConsignationForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', referenceAction = '" + referenceAction);
        stringBuffer.append("', referenceSource = '" + referenceSource);
        stringBuffer.append("', numeroMandat = '" + numeroMandat);
        stringBuffer.append("', referenceCle = '" + referenceSourceCle);
        stringBuffer.append("', referenceSite = '" + referenceSourceSite );
        stringBuffer.append("', numeroMandat = '" + numeroMandat );
        stringBuffer.append("', typeAction = '" + typeAction );
        stringBuffer.append("']");
        return stringBuffer.toString();
    }


	/**
	 * @return
	 */
	public String getCle() {
		return cle;
	}

	/**
	 * @return
	 */
	public String getDateConsignation() {
		return dateConsignation;
	}

	/**
	 * @return
	 */
	public String getDateCourriel() {
		return dateCourriel;
	}

	/**
	 * @return
	 */
	public DossierForm getDossier() {
		return dossier;
	}

	/**
	 * @return
	 */
	public String getGenreFichier() {
		return genreFichier;
	}

	/**
	 * @return
	 */
	public String getGenreFichierAction() {
		return genreFichierAction;
	}

	/**
	 * @return
	 */
	public String getIntervenant() {
		return intervenant;
	}

	/**
	 * @return
	 */
	public String getNumeroMandat() {
		return numeroMandat;
	}

	/**
	 * @return
	 */
	public String getReferenceAction() {
		return referenceAction;
	}

	/**
	 * @return
	 */
	public String getReferenceActionCle() {
		return referenceActionCle;
	}

	/**
	 * @return
	 */
	public String getReferenceActionSite() {
		return referenceActionSite;
	}

	/**
	 * @return
	 */
	public String getReferenceSource() {
		return referenceSource;
	}

	/**
	 * @return
	 */
	public String getReferenceSourceCle() {
		return referenceSourceCle;
	}

	/**
	 * @return
	 */
	public String getReferenceSourceSite() {
		return referenceSourceSite;
	}

	/**
	 * @return
	 */
	public String getTypeAction() {
		return typeAction;
	}

	/**
	 * @param string
	 */
	public void setCle(String cle) {
		this.cle = cle;
	}

	/**
	 * @param string
	 */
	public void setDateConsignation(String dateConsignation) {
		this.dateConsignation = dateConsignation;
	}

	/**
	 * @param string
	 */
	public void setDateCourriel(String dateCourriel) {
		this.dateCourriel = dateCourriel;
	}

	/**
	 * @param form
	 */
	public void setDossier(DossierForm dossier) {
		this.dossier = dossier;
	}

	/**
	 * @param string
	 */
	public void setGenreFichier(String genreFichier) {
		this.genreFichier = genreFichier;
	}

	/**
	 * @param string
	 */
	public void setGenreFichierAction(String genreFichierAction) {
		this.genreFichierAction = genreFichierAction;
	}

	/**
	 * @param string
	 */
	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}

	/**
	 * @param string
	 */
	public void setNumeroMandat(String numeroMandat) {
		this.numeroMandat = numeroMandat;
	}

	/**
	 * @param string
	 */
	public void setReferenceAction(String referenceAction) {
		this.referenceAction = referenceAction;
	}

	/**
	 * @param string
	 */
	public void setReferenceActionCle(String referenceActionCle) {
		this.referenceActionCle = referenceActionCle;
	}

	/**
	 * @param string
	 */
	public void setReferenceActionSite(String referenceActionSite) {
		this.referenceActionSite = referenceActionSite;
	}

	/**
	 * @param string
	 */
	public void setReferenceSource(String referenceSource) {
		this.referenceSource = referenceSource;
	}

	/**
	 * @param string
	 */
	public void setReferenceSourceCle(String referenceSourceCle) {
		this.referenceSourceCle = referenceSourceCle;
	}

	/**
	 * @param string
	 */
	public void setReferenceSourceSite(String referenceSourceSite) {
		this.referenceSourceSite = referenceSourceSite;
	}

	/**
	 * @param string
	 */
	public void setTypeAction(String typeAction) {
		this.typeAction = typeAction;
	}

	public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException {
    	ListeCache cache = ListeCache.getInstance();
    	
    	typeActionDescription = cache.obtenirLabel(subject, getTypeAction(), new TypeActionCleListeCache(subject));
		intervenantDescription = cache.obtenirLabel(subject, getIntervenant(), new IntervenantCle(subject.getLocale().getLanguage()));
		genreFichierActionDescription = cache.obtenirLabel(subject, getGenreFichierAction(), new GenreFichierCle(subject.getLocale().getLanguage()));
	}


	/**
	 * @return typeActionDescription
	 */
	public String getTypeActionDescription() {
		return typeActionDescription;
	}


	/**
	 * @param typeActionDescription typeActionDescription à définir
	 */
	public void setTypeActionDescription(String typeActionDescription) {
		this.typeActionDescription = typeActionDescription;
	}


	/**
	 * @return intervenantDescription
	 */
	public String getIntervenantDescription() {
		return intervenantDescription;
	}


	/**
	 * @param intervenantDescription intervenantDescription à définir
	 */
	public void setIntervenantDescription(String intervenantDescription) {
		this.intervenantDescription = intervenantDescription;
	}


	/**
	 * @return genreFichierActionDescription
	 */
	public String getGenreFichierActionDescription() {
		return genreFichierActionDescription;
	}


	/**
	 * @param genreFichierActionDescription genreFichierActionDescription à définir
	 */
	public void setGenreFichierActionDescription(
			String genreFichierActionDescription) {
		this.genreFichierActionDescription = genreFichierActionDescription;
	}	
	
}