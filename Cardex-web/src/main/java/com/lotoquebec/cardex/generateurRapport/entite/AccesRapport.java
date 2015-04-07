package com.lotoquebec.cardex.generateurRapport.entite;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

import com.lotoquebec.cardex.business.Acces;
import com.lotoquebec.cardex.presentation.model.AccesHtmlForm;
import com.lotoquebec.cardexCommun.authentication.CardexAuthenticationSubject;
import com.lotoquebec.cardexCommun.exception.BusinessResourceException;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.IntervenantParSiteCle;
import com.lotoquebec.cardexCommun.integration.dao.cleListe.cleSQLListeCache.SiteOrigineTableValeurCle;
import com.lotoquebec.cardexCommun.util.ListeCache;

public class AccesRapport implements Serializable {

	private Acces acces;
    private String siteDescription = "";
    private String actionDescription = "";
    private String utilisateurDescription = "";

    /**
     * Constructeur de AccesForm par d�faut.
     */
    public AccesRapport() {}
    
    // Getters

    /**
     * Retourne la cl� (L_AC_CLE).
     *
     * @return String Valeur num�rique de la cl�
     */
    public long getCle(){
        return acces.getCle();
    }


    /**
     * Retourne le site (L_SI_SITE).
     *
     * @return String Valeur num�rique du site d'o� provient l'acc�s
     */

    public String getSite(){
        return this.site;
    }

    /**
     * Retourne le site d'origine (L_ORI_SITE).
     *
     * @return String Valeur num�rique du site d'origine.
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
     * Retourne la cl� d'origine (L_ORI_CLE).
     *
     * @return String Valeur num�rique de la cl�.
     */
    public String getCleOrigine(){
        return this.cleOrigine;
    }

    /**
     * Retourne la cl� de r�f�rence (L_REF_CLE).
     *
     * @return String Valeur num�rique dela cl�.
     */
    public String getCleRef(){
        return this.cleRef;
    }

    /**
     * Retourne la r�f�rence du fichier (C_GF_ORIGINE).
     * Correspond au fichier sur lequel on conserve un audit.
     *
     * @return String Valeur du genre en caract�re.
     */
    public String getGenreOrigine(){
        return this.genreOrigine;
    }

    /**
     * Retourne le site de r�f�rence (L_REF_SITE).
     *
     * @return String Valeur num�rique du site.
     */
    public String getSiteRef(){
        return this.siteRef;
    }

    /**
     * Retourne le code de l'utilisateur qui fait l'acc�s (V_AC_NAME).
     *
     * @return String Valeur du code de l'utilisateur.
     */
    public String getUtilisateur(){
        return this.utilisateur;
    }

    /**
     * Retourne la date de l'acc�s (D_AC_DATE_ACCES).
     *
     * @return Timestamp Valeur de la date (yyyy-MM-dd).
     */
    public String getDateAcces(){
        return this.dateAcces;
    }

     /**
     * Retourne la cl� de r�f�rence 2 (L_REF2_CLE).
     *
     * @return String Valeur de la cl�.
     */
    public String getCleRef2(){
        return this.cleRef2;
    }

    /**
     * Retourne le type d'action (C_AC_ACTION).
     * S=S�lection, I=Insertion, D=Suppression, U=Modification
     *
     * @return String Valeur de l'action.
     */
    public String getAction(){
        return this.action;
    }

    /**
     * Retourne le genre de la r�f�rence 2 (C_GF_REF2).
     *
     * @return String Valeur du genre
     */
    public String getGenreRef2(){
        return this.genreRef2;
    }

    /**
     * Retourne le site de la r�f�rence 2 (L_REF2_SITE ).
     *
     * @return String Valeur du site.
     */
    public String getSiteRef2(){
        return this.siteRef2;
    }

    /**
     * Retourne le cr�ateur du dossier principal pour lequel on consulte
     * les acc�s.
     *
     * @return String Valeur du code de l'intervenant.
     */
    public String getCreateur(){
        return this.createur;
    }


    /**
     * Retourne la hi�rarchie (applicable dans le cas des Dossiers).
     *
     * @return String Valeur num�rique de la hi�rarchie.
     */
    public String getHierarchie(){
        return this.hierarchie;
    }


    /**
     * Retourne la date de cr�ation du dossier principal.
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
     * @param cle Valeur num�rique de la cle.
     */
    public void setCle(String cle) {
        this.cle = cle;
    }

    /**
     * Affecte le site.
     *
     * @param site Valeur num�rique le site.
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * Affecte un site d'origine.
     *
     * @param siteOrigine Valeur num�rique du site d'origine.
     */
    public void setSiteOrigine(String siteOrigine) {
        this.siteOrigine = siteOrigine;
    }



    /**
     * Affecte la cl� de r�f�rence.
     *
     * @param cleRef Valeur num�rique de la cl�.
     */
    public void setCleRef(String cleRef) {
        this.cleRef = cleRef;
    }

    /**
     * Affecte la cl� de r�f�rence 2.
     *
     * @param cleRef2 Valeur num�rique de la cl�.
     */
    public void setCleRef2(String cleRef2) {
        this.cleRef2 = cleRef2;
    }

    /**
     * Affecte une cl� d'origine.
     *
     * @param cleOrigine Valeur num�rique de la cl�.
     */
    public void setCleOrigine(String cleOrigine) {
        this.cleOrigine = cleOrigine;
    }

    /**
     * Affecte un code de cr�ateur.
     *
     * @param createur Code du cr�ateur du dossier.
     */
    public void setCreateur(String createur) {
        this.createur = createur;
    }

    /**
     * Affecte la date d'acc�s.
     *
     * @param dateAcces Valeur de la date d'acc�s (yyyy-MM-dd).
     */
    public void setDateAcces(String dateAcces) {
        this.dateAcces = dateAcces;
    }

    /**
     * Affecte la date de cr�ation.
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
     * Affecte le genre de la premi�re r�f�rence.
     *
     * @param genreRef Valeur de la premi�re r�f�rence en caract�re.
     */
    public void setGenreRef(String genreRef) {
        this.genreRef = genreRef;
    }

    /**
     * Affecte le genre de la deuxi�me r�f�rence.
     *
     * @param genreRef2 Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public void setGenreRef2(String genreRef2) {
        this.genreRef2 = genreRef2;
    }

     /**
     * Affecte l'utilisateur.
     *
     * @param utilisateur Valeur de l'utilisateur en caract�re.
     */
    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

 

    /**
     * Affecte la hierarchie.
     *
     * @param hierarchie Valeur num�rique de la hierarchie.
     */
    public void setHierarchie(String hierarchie) {
        this.hierarchie = hierarchie;
    }

    /**
     * Affecte le site de r�f�rence.
     *
     * @param siteRef Valeur num�rique du site.
     */
    public void setSiteRef(String siteRef) {
        this.siteRef = siteRef;
    }

    /**
     * Affecte le site de r�f�rence 2.
     *
     * @param siteRef2 Valeur num�rique du site.
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
	 * @param siteDescription siteDescription � d�finir
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
	 * @param actionDescription actionDescription � d�finir
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
	 * @param utilisateurDescription utilisateurDescription � d�finir
	 */
	public void setUtilisateurDescription(String utilisateurDescription) {
		this.utilisateurDescription = utilisateurDescription;
	}

	public void assignerValeurDeListe(CardexAuthenticationSubject subject) throws BusinessResourceException {
    	ListeCache cache = ListeCache.getInstance();
    	if(action.equals("S")) actionDescription = "S=S�lection";
    	if(action.equals("D")) actionDescription = "D=Suppression";
    	if(action.equals("I")) actionDescription = "I=Insertion";
    	if(action.equals("U")) actionDescription = "U=Mise � jour";
		siteDescription = cache.obtenirLabel(subject, getSite(), new SiteOrigineTableValeurCle(subject, getSite()));
		utilisateurDescription = cache.obtenirLabel(subject, getUtilisateur(), new IntervenantParSiteCle(subject.getLocale().getLanguage(), getSite()));
	}

}