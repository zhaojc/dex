package com.lotoquebec.cardex.presentation.model.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.presentation.model.AccesHtmlForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantParSiteCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SiteOrigineTableValeurCle;
import com.lotoquebec.cardexCommun.util.ListeCache;

/**
 * Permet de transiter les informations relatives à la consultation d'une
 * adresse de la couche présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/03/20 22:06:56 $
 * @see com.lotoquebec.cardex.presentation.model.AdresseHtmlForm
 */
public class AccesForm extends ValidatorForm implements AccesHtmlForm, Serializable {

    /**
	 * 
	 */
	private String       cle = "";
    private String       site = "";
    private String		 siteDescription = "";
    private String       siteOrigine = "";
    private String       cleOrigine = "";
    private String       cleRef = "";
    private String       cleRef2 = "";
    private String       siteRef = "";
    private String       siteRef2 = "";
    private String     action = "";
    private String     actionDescription = "";
    private String     dateAcces = "";
    private String     dateCreation = "";
    private String     createur = "";
    private String     genreOrigine = "";
    private String     genreRef = "";
    private String     genreRef2 = "";
    private String     hierarchie = "";
    private String     utilisateur = "";
    private String     utilisateurDescription = "";

    /**
     * Constructeur de AccesForm par défaut.
     */
    public AccesForm() {}
    
    // Getters

    /**
     * Retourne la clé (L_AC_CLE).
     *
     * @return String Valeur numérique de la clé
     */
    public String getCle(){
        return this.cle;
    }


    /**
     * Retourne le site (L_SI_SITE).
     *
     * @return String Valeur numérique du site d'où provient l'accès
     */

    public String getSite(){
        return this.site;
    }

    /**
     * Retourne le site d'origine (L_ORI_SITE).
     *
     * @return String Valeur numérique du site d'origine.
     */
    public String getSiteOrigine(){
        return this.siteOrigine;
    }
    /**
     * Retourne le genre (C_GF_REFERENCE).
     *
     * @return String Valeur du genre.
     */
    public String getGenreRef(){
        return this.genreRef;
    }

    /**
     * Retourne la clé d'origine (L_ORI_CLE).
     *
     * @return String Valeur numérique de la clé.
     */
    public String getCleOrigine(){
        return this.cleOrigine;
    }

    /**
     * Retourne la clé de référence (L_REF_CLE).
     *
     * @return String Valeur numérique dela clé.
     */
    public String getCleRef(){
        return this.cleRef;
    }

    /**
     * Retourne la référence du fichier (C_GF_ORIGINE).
     * Correspond au fichier sur lequel on conserve un audit.
     *
     * @return String Valeur du genre en caractère.
     */
    public String getGenreOrigine(){
        return this.genreOrigine;
    }

    /**
     * Retourne le site de référence (L_REF_SITE).
     *
     * @return String Valeur numérique du site.
     */
    public String getSiteRef(){
        return this.siteRef;
    }

    /**
     * Retourne le code de l'utilisateur qui fait l'accès (V_AC_NAME).
     *
     * @return String Valeur du code de l'utilisateur.
     */
    public String getUtilisateur(){
        return this.utilisateur;
    }

    /**
     * Retourne la date de l'accès (D_AC_DATE_ACCES).
     *
     * @return Timestamp Valeur de la date (yyyy-MM-dd).
     */
    public String getDateAcces(){
        return this.dateAcces;
    }

     /**
     * Retourne la clé de référence 2 (L_REF2_CLE).
     *
     * @return String Valeur de la clé.
     */
    public String getCleRef2(){
        return this.cleRef2;
    }

    /**
     * Retourne le type d'action (C_AC_ACTION).
     * S=Sélection, I=Insertion, D=Suppression, U=Modification
     *
     * @return String Valeur de l'action.
     */
    public String getAction(){
        return this.action;
    }

    /**
     * Retourne le genre de la référence 2 (C_GF_REF2).
     *
     * @return String Valeur du genre
     */
    public String getGenreRef2(){
        return this.genreRef2;
    }

    /**
     * Retourne le site de la référence 2 (L_REF2_SITE ).
     *
     * @return String Valeur du site.
     */
    public String getSiteRef2(){
        return this.siteRef2;
    }

    /**
     * Retourne le créateur du dossier principal pour lequel on consulte
     * les accès.
     *
     * @return String Valeur du code de l'intervenant.
     */
    public String getCreateur(){
        return this.createur;
    }


    /**
     * Retourne la hiérarchie (applicable dans le cas des Dossiers).
     *
     * @return String Valeur numérique de la hiérarchie.
     */
    public String getHierarchie(){
        return this.hierarchie;
    }


    /**
     * Retourne la date de création du dossier principal.
     *
     * @return Timestamp Valeur de la date (yyyy-MM-dd).
     */
    public String getDateCreation(){
        return this.dateCreation;
    }



    // Setters

    /**
     * Affecte l'action.
     *
     * @param action Valeur de l'action.
     */
    public void setAction(String action) {
        this.action = action;
    }
    /**
     * Affecte la cle.
     *
     * @param cle Valeur numérique de la cle.
     */
    public void setCle(String cle) {
        this.cle = cle;
    }

    /**
     * Affecte le site.
     *
     * @param site Valeur numérique le site.
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur numérique du site d'origine.
     */
    public void setSiteOrigine(String siteOrigine) {
        this.siteOrigine = siteOrigine;
    }



    /**
     * Affecte la clé de référence.
     *
     * @param cleRef Valeur numérique de la clé.
     */
    public void setCleRef(String cleRef) {
        this.cleRef = cleRef;
    }

    /**
     * Affecte la clé de référence 2.
     *
     * @param cleRef2 Valeur numérique de la clé.
     */
    public void setCleRef2(String cleRef2) {
        this.cleRef2 = cleRef2;
    }

    /**
     * Affecte une clé d'origine.
     *
     * @param cleOrigine Valeur numérique de la clé.
     */
    public void setCleOrigine(String cleOrigine) {
        this.cleOrigine = cleOrigine;
    }

    /**
     * Affecte un code de créateur.
     *
     * @param createur Code du créateur du dossier.
     */
    public void setCreateur(String createur) {
        this.createur = createur;
    }

    /**
     * Affecte la date d'accès.
     *
     * @param dateAcces Valeur de la date d'accès (yyyy-MM-dd).
     */
    public void setDateAcces(String dateAcces) {
        this.dateAcces = dateAcces;
    }

    /**
     * Affecte la date de création.
     *
     * @param dateCreation Valeur de la date (yyyy-MM-dd).
     */
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Affecte un genre .
     *
     * @param genreOrigine Valeur du genre du dossier d'origine.
     */
    public void setGenreOrigine(String genreOrigine) {
        this.genreOrigine = genreOrigine;
    }

    /**
     * Affecte le genre de la première référence.
     *
     * @param genreRef Valeur de la première référence en caractère.
     */
    public void setGenreRef(String genreRef) {
        this.genreRef = genreRef;
    }

    /**
     * Affecte le genre de la deuxième référence.
     *
     * @param genreRef2 Valeur de la deuxième référence en caractère.
     */
    public void setGenreRef2(String genreRef2) {
        this.genreRef2 = genreRef2;
    }

     /**
     * Affecte l'utilisateur.
     *
     * @param utilisateur Valeur de l'utilisateur en caractère.
     */
    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

 

    /**
     * Affecte la hierarchie.
     *
     * @param hierarchie Valeur numérique de la hierarchie.
     */
    public void setHierarchie(String hierarchie) {
        this.hierarchie = hierarchie;
    }

    /**
     * Affecte le site de référence.
     *
     * @param siteRef Valeur numérique du site.
     */
    public void setSiteRef(String siteRef) {
        this.siteRef = siteRef;
    }

    /**
     * Affecte le site de référence 2.
     *
     * @param siteRef2 Valeur numérique du site.
     */
    public void setSiteRef2(String siteRef2) {
        this.siteRef2 = siteRef2;
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

	/**
	 * @return actionDescription
	 */
	public String getActionDescription() {
		return actionDescription;
	}

	/**
	 * @param actionDescription actionDescription à définir
	 */
	public void setActionDescription(String actionDescription) {
		this.actionDescription = actionDescription;
	}

	/**
	 * @return utilisateurDescription
	 */
	public String getUtilisateurDescription() {
		return utilisateurDescription;
	}

	/**
	 * @param utilisateurDescription utilisateurDescription à définir
	 */
	public void setUtilisateurDescription(String utilisateurDescription) {
		this.utilisateurDescription = utilisateurDescription;
	}

	public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException {
    	ListeCache cache = ListeCache.getInstance();
    	if(action.equals("S")) actionDescription = "S=Sélection";
    	if(action.equals("D")) actionDescription = "D=Suppression";
    	if(action.equals("I")) actionDescription = "I=Insertion";
    	if(action.equals("U")) actionDescription = "U=Mise à jour";
		siteDescription = cache.obtenirLabel(subject, getSite(), new SiteOrigineTableValeurCle(subject, getSite()));
		utilisateurDescription = cache.obtenirLabel(subject, getUtilisateur(), new IntervenantParSiteCle(subject.getLocale().getLanguage(), getSite()));
	}

}