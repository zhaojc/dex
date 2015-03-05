package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.FrequenceVisitesHtmlForm;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;
import com.lotoquebec.cardexCommun.util.StringUtils;

/**
 * Conserve les différentes valeurs relatives au formulaire d'évaluation du comité de vigilance, 
 * section de la fréquence des visites.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.JeuxHtmlForm
 */
public class FrequenceVisitesForm extends ValidatorForm implements FrequenceVisitesHtmlForm, Serializable {

    private String cle = "";
    private String site = "";
    private String gainPerte = "";
    private String nombreVisites = "";
    private String periode = "";
    private String lien = "";
    private String lienSite = "";
    private String createur = "";
    private String dateCreation = "";
    private String annee = "";
    private String mois = "";
    
    /**
     * Constructeur de JeuxForm par défaut.
     */
    public FrequenceVisitesForm() {

    }


    // Getters


	/**
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void init() {
        this.cle = "";
        this.site = "";
        this.gainPerte = "";
        this.periode = "";
        this.nombreVisites = "";
        this.lien = "";
        this.lienSite = "";
        this.createur = "";
        this.dateCreation = "";
        this.annee = "";
        this.mois = "";
    }

	/**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du JeuxForm.
     *
     * @return String Valeur de tout les attributs du JeuxForm en
     * caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[FrequenceVisitesForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', gainPerte = '" + gainPerte);
        stringBuffer.append("', periode = '" + periode);
        stringBuffer.append("', nombreVisites = '" + nombreVisites);
        stringBuffer.append("', lien = '" + lien);
        stringBuffer.append("', lienSite = '" + lienSite);
        stringBuffer.append("', annee = '" + annee);
        stringBuffer.append("', mois = '" + mois);

        stringBuffer.append("')]");
        return stringBuffer.toString();
    }


    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
    }


	/**
	 * @return cle
	 */
	public String getCle() {
		return cle;
	}


	/**
	 * @param cle cle à définir
	 */
	public void setCle(String cle) {
		this.cle = cle;
	}


	/**
	 * @return site
	 */
	public String getSite() {
		return site;
	}


	/**
	 * @param site site à définir
	 */
	public void setSite(String site) {
		this.site = site;
	}


	/**
	 * @return gainPerte
	 */
	public String getGainPerte() {
		return gainPerte;
	}


	/**
	 * @param gainPerte gainPerte à définir
	 */
	public void setGainPerte(String gainPerte) {
		this.gainPerte = gainPerte;
	}


	/**
	 * @return nombreVisites
	 */
	public String getNombreVisites() {
		return nombreVisites;
	}


	/**
	 * @param nombreVisites nombreVisites à définir
	 */
	public void setNombreVisites(String nombreVisites) {
		this.nombreVisites = nombreVisites;
	}


	/**
	 * @return periode
	 */
	public String getPeriode() {
		return periode;
	}


	/**
	 * @param periode periode à définir
	 */
	public void setPeriode(String periode) {
		this.periode = periode;
	}


	/**
	 * @return lien
	 */
	public String getLien() {
		return lien;
	}


	/**
	 * @param lien lien à définir
	 */
	public void setLien(String lien) {
		this.lien = lien;
	}


	/**
	 * @return lienSite
	 */
	public String getLienSite() {
		return lienSite;
	}


	/**
	 * @param lienSite lienSite à définir
	 */
	public void setLienSite(String lienSite) {
		this.lienSite = lienSite;
	}


	/**
	 * @return createur
	 */
	public String getCreateur() {
		return createur;
	}


	/**
	 * @param createur createur à définir
	 */
	public void setCreateur(String createur) {
		this.createur = createur;
	}


	/**
	 * @return dateCreation
	 */
	public String getDateCreation() {
		return dateCreation;
	}


	/**
	 * @param dateCreation dateCreation à définir
	 */
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}


	public String getAnnee() {
		//return StringUtils.substring(this.periode,0,4);
		return annee;
	}


	public void setAnnee(String annee) {
		this.annee = annee;
	}


	public String getMois() {
		//return StringUtils.substring(this.periode,5,8);
		return mois;
	}


	public void setMois(String mois) {
		this.mois = mois;
	}

}