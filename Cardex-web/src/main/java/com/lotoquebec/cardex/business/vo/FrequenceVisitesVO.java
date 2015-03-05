package com.lotoquebec.cardex.business.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.business.FrequenceVisites;
import com.lotoquebec.cardexCommun.GlobalConstants;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.TableValeurCleSQLListeCache;
import com.lotoquebec.cardexCommun.presentation.util.LabelValueBean;
import com.lotoquebec.cardexCommun.user.CardexUser;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Conserve les différentes valeurs relatives au formulaire d'évaluation du comité de vigilance, 
 * section de la fréquence des visites.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/04/30 12:18:15 $
 * @see com.lotoquebec.cardex.presentation.model.JeuxHtmlForm
 */
public class FrequenceVisitesVO implements FrequenceVisites {

    private long cle = 0;
    private long site = 0;
    private long gainPerte = 0;
    private long nombreVisites = 0;
    private String periode = "";
    private long lien = 0;
    private long lienSite = 0;
    private String createur = "";
    private Timestamp dateCreation = null;
	/**
	 * @return cle
	 */
	public long getCle() {
		return cle;
	}
	/**
	 * @param cle cle à définir
	 */
	public void setCle(long cle) {
		this.cle = cle;
	}
	/**
	 * @return site
	 */
	public long getSite() {
		return site;
	}
	/**
	 * @param site site à définir
	 */
	public void setSite(long site) {
		this.site = site;
	}
	/**
	 * @return gainPerte
	 */
	public long getGainPerte() {
		return gainPerte;
	}
	/**
	 * @param gainPerte gainPerte à définir
	 */
	public void setGainPerte(long gainPerte) {
		this.gainPerte = gainPerte;
	}
	/**
	 * @return nombreVisites
	 */
	public long getNombreVisites() {
		return nombreVisites;
	}
	/**
	 * @param nombreVisites nombreVisites à définir
	 */
	public void setNombreVisites(long nombreVisites) {
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
	public long getLien() {
		return lien;
	}
	/**
	 * @param lien lien à définir
	 */
	public void setLien(long lien) {
		this.lien = lien;
	}
	/**
	 * @return lienSite
	 */
	public long getLienSite() {
		return lienSite;
	}
	/**
	 * @param lienSite lienSite à définir
	 */
	public void setLienSite(long lienSite) {
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
	public Timestamp getDateCreation() {
		return dateCreation;
	}
	/**
	 * @param dateCreation dateCreation à définir
	 */
	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}
    
    
   
}