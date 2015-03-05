package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.business.ConsignationActionPSU;
import com.lotoquebec.cardex.business.Dossier;

/**
 * Permet de transiter les informations relatives à un suivi de la couche
 * présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.7 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.SuiviHtmlForm
 */
public class ConsignationActionPSUVO extends ValidatorForm  implements ConsignationActionPSU {

    private long cle = 0;
    private long typeAction = 0;
    private String numeroMandat = "";
    private Timestamp dateConsignation = null;
	private Timestamp dateCourriel = null;
    private String genreFichierAction = "";
    private String genreFichier = "";
    private long referenceSourceCle = 0;
    private long referenceSourceSite = 0;
    private long referenceActionCle = 0;
    private long referenceActionSite = 0;
    private String referenceSource = "";
    private String referenceAction = "";
    private String intervenant = "";
	private Dossier dossier = new DossierVO();

    /**
     * Constructeur de SuiviForm par défaut.
     */
    public ConsignationActionPSUVO() {}


    // Getters

   
    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
       this.cle = 0;
       this.typeAction = 0;
       this.numeroMandat = "";
       this.dateConsignation = null;
	   this.dateCourriel = null;
	   this.genreFichier = "";
       this.genreFichierAction = "";
       this.referenceAction = "";
       this.referenceActionCle = 0;
       this.referenceActionSite = 0;
       this.referenceSource = "";
       this.referenceSourceCle = 0;
       this.referenceSourceSite = 0;
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
	public long getCle() {
		return cle;
	}

	/**
	 * @return
	 */
	public Timestamp getDateConsignation() {
		return dateConsignation;
	}

	/**
	 * @return
	 */
	public Timestamp getDateCourriel() {
		return dateCourriel;
	}

	/**
	 * @return
	 */
	public Dossier getDossier() {
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
	public long getReferenceActionCle() {
		return referenceActionCle;
	}

	/**
	 * @return
	 */
	public long getReferenceActionSite() {
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
	public long getReferenceSourceCle() {
		return referenceSourceCle;
	}

	/**
	 * @return
	 */
	public long getReferenceSourceSite() {
		return referenceSourceSite;
	}

	/**
	 * @return
	 */
	public long getTypeAction() {
		return typeAction;
	}

	/**
	 * @param string
	 */
	public void setCle(long cle) {
		this.cle = cle;
	}

	/**
	 * @param string
	 */
	public void setDateConsignation(Timestamp dateConsignation) {
		this.dateConsignation = dateConsignation;
	}

	/**
	 * @param string
	 */
	public void setDateCourriel(Timestamp dateCourriel) {
		this.dateCourriel = dateCourriel;
	}

	/**
	 * @param form
	 */
	public void setDossier(Dossier dossier) {
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
	public void setReferenceActionCle(long referenceActionCle) {
		this.referenceActionCle = referenceActionCle;
	}

	/**
	 * @param string
	 */
	public void setReferenceActionSite(long referenceActionSite) {
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
	public void setReferenceSourceCle(long referenceSourceCle) {
		this.referenceSourceCle = referenceSourceCle;
	}

	/**
	 * @param string
	 */
	public void setReferenceSourceSite(long referenceSourceSite) {
		this.referenceSourceSite = referenceSourceSite;
	}

	/**
	 * @param string
	 */
	public void setTypeAction(long typeAction) {
		this.typeAction = typeAction;
	}

}