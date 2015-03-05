package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardexCommun.model.EntiteCardexForm;

/**
 * Conserve les différentes valeurs relatives au lien entre entité.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $, $Date: 2002/04/12 14:58:54 $
 * @see com.lotoquebec.cardex.presentation.model.DossierHtmlForm
 */
public class LienForm extends ValidatorForm implements Serializable {

    private long       cle = 0;
    private long       site = 0;
    private long       cleSource = 0;
    private long       siteSource = 0;
    private long       cleDestination = 0;
    private long       siteDestination = 0;
    private String     role = "";
    //Sert dans l'écran de choix de rôle lors d'une liaison
    private String     siteString = "";
    
    private long       typeLien = 0;
    private EntiteCardexForm entiteCardexSource = null;
    private EntiteCardexForm entiteCardexDestination = null;

    /**
     * Retourne le lien.
     *
     * @return long Valeur numérique du lien.
     */
    public long getCle() {
        return this.cle;
    }

    /**
     * Retourne le lien.
     *
     * @return long Valeur numérique du lien.
     */
    public long getCleSource() {
        return this.cleSource;
    }

    /**
     * Retourne le lien.
     *
     * @return long Valeur numérique du lien.
     */
    public long getCleDestination() {
        return this.cleDestination;
    }

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur numérique du lien du site.
     */
    public long getSite() {
        return this.site;
    }

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur numérique du lien du site.
     */
    public long getSiteSource() {
        return this.siteSource;
    }

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur numérique du lien du site.
     */
    public long getSiteDestination() {
        return this.siteDestination;
    }

    /**
     * Retourne le rôle.
     *
     * @return long Valeur numérique du rôle.
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Retourne le type de lien.
     *
     * @return long Valeur numérique du type de lien.
     */
    public long getTypeLien() {
        return this.typeLien;
    }

    /**
     * Affecte le lien
     *
     * @param lienSite Valeur numérique du lien du site.
     */
    public void setCle(long lien) {
        this.cle = lien;
    }

    /**
     * Affecte le lien
     *
     * @param lienSite Valeur numérique du lien du site.
     */
    public void setCleSource(long lien) {
        this.cleSource = lien;
    }

    /**
     * Affecte le lien
     *
     * @param lienSite Valeur numérique du lien du site.
     */
    public void setCleDestination(long lien) {
        this.cleDestination = lien;
    }

    /**
     * Affecte le lien du site.
     *
     * @param lienSite Valeur numérique du lien du site.
     */
    public void setSite(long lienSite) {
        this.site = lienSite;
    }

    /**
     * Affecte le lien du site.
     *
     * @param lienSite Valeur numérique du lien du site.
     */
    public void setSiteSource(long lienSite) {
        this.siteSource = lienSite;
    }

    /**
     * Affecte le lien du site.
     *
     * @param lienSite Valeur numérique du lien du site.
     */
    public void setSiteDestination(long lienSite) {
        this.siteDestination = lienSite;
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
    public void setTypeLien(long typeLien) {
        this.typeLien = typeLien;
    }


    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du LienForm.
     *
     * @return long Valeur de tout les attributs du LienForm en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[LienForm : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("',cleSource = '" + cleSource);
        stringBuffer.append("', siteSource = '" + siteSource);
        stringBuffer.append("', cleDestination = '" + cleDestination);
        stringBuffer.append("', siteDestination = '" + siteDestination);
        stringBuffer.append("', role = '" + role);
        stringBuffer.append("', typeLien = '" + typeLien + "']");
        return stringBuffer.toString();
    }

    /**
     * Réinitialise toute les attributs à leur valeur par défaut.
     *
     * @param mapping The mapping used to select this instance
     * @param request The servlet request we are processing
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.cleSource = 0;
        this.siteSource = 0;
        this.cleDestination = 0;
        this.siteDestination = 0;
        this.role = "";
        this.typeLien = 0;
    }

    public void assignerSource(EntiteCardexForm entiteCardex){
    	this.entiteCardexSource = entiteCardex;
        this.cleSource = Long.valueOf(entiteCardex.getCle()).longValue();
        this.siteSource = Long.valueOf(entiteCardex.getSite()).longValue();
    }
    
    public void assignerDestination(EntiteCardexForm entiteCardex){
    	this.entiteCardexDestination = entiteCardex;
        this.cleDestination = Long.valueOf(entiteCardex.getCle()).longValue();
        this.siteDestination = Long.valueOf(entiteCardex.getSite()).longValue();
    }

	public String getSiteString() {
		return String.valueOf(siteSource);
	}

	public void setSiteString(String siteString) {
		this.siteString = siteString;
	}

}
