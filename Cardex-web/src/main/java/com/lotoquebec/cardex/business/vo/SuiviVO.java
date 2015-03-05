package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives à un suivi de la couche
 * présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.4 $, $Date: 2002/03/26 22:17:50 $
 * @see com.lotoquebec.cardex.business.Suivi
 */
public class SuiviVO implements Suivi {

    private long cle = 0;
    private long site = 0;
    private long entite = 0;
    private long activite = 0;
    private String suivi = "";
    private long statut = 0;
    private Timestamp datePrevue = null;
    private Timestamp dateCompletee = null;
    private long secteurOrigine = 0;
    private String demandeur = "";
    private String intervenant = "";
    private long secteurAssigne = 0;
    private String createur = "";
    private long confidentialiteSuivi = 0;
    private long niveauHierarchiqueSuivi = 0;
    private long confidentialiteCreateur = 0;
    private long niveauHierarchiqueCreateur = 0;
    private Timestamp dateCreation = null;
    private String modificateur = "";
    private Timestamp dateModification = null;
    private String approbateur = "";
    private long confidentialiteApprobateur = 0;
    private long niveauHierarchiqueApprobateur = 0;
    private Timestamp dateApprobation = null;
    private String reference1 = "";
    private String reference2 = ""; // Secteur de référence du demandeur du suivi (ORIGINE)
    private String reference3 = ""; // Secteur de référence de l'utilisteur assigné (ASSIGNE
    private long lien = 0;
    private long lienSite = 0;
    private Dossier dossier = new DossierVO();
    private Boolean permettreComplete = false;
    private Boolean modifiable = false;
    private Boolean approuvable = false;
    //Mars 2011. Ajout de deux champs pour la lecture de l'audit.
    //Les autres champs de l'audit sont identiques à ceux du dossier.
    private Timestamp dateChangement= null;
    private String changePar = "";

    /**
     * Constructeur de SuiviVO par défaut.
     */
    public SuiviVO() {}


    // Getters

    /**
     * Test si un suivi peut être modifié.
     *
     * @return boolean True si la narration est modifiable.
     */
    public Boolean isModifiable() {
        return this.modifiable;
    }

    /**
     * Test si un suivi peut être approuvé.
     *
     * @return boolean True si la narration est approuvable.
     */
    public Boolean isApprouvable() {
        return this.approuvable;
    }

    /**
     * Test si on peut compléter un suivi.
     *
     * @return boolean True si on peut permettre la modification d'une narration.
     */
    public Boolean isPermettreComplete(){
      return this.permettreComplete;
    }

    /**
     * Determine si un suivi est modifiable
     *
     * @param isModifiable Est-ce que le suivi est modifiable
     * caractère.
     */
    public void setModifiable(Boolean modifiable) {
      this.modifiable = modifiable;
    }

    /**
     * Determine si un suivi est approuvanle
     *
     * @param isApprouvable Est-ce que le suivi est approuvable
     * caractère.
     */
    public void setApprouvable(Boolean approuvable) {
      this.approuvable = approuvable;
    }

    /**
     * Détermine si on peut compléter une suivi.
     *
     * @param isPermettreComplete Est-ce qu'on peut compléter une suivi.
     * caractère.
     */
    public void setPermettreComplete(Boolean permettreComplete){
      this.permettreComplete = permettreComplete;
    }

    /**
     * Retourne certaines informations sur le dossier retourné
     * par une recherche de narrations.  Ces informations sont
     * utilisées pour appeler le dossier à l'écran à partir de la
     * liste de résultats.
     *
     * @return Dossier Valeurs du dossier.
     */
    public Dossier getDossier() {
        return this.dossier;
    }

    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle.
     */
    public long getCle() {
        return this.cle;
    }

    /**
     * Retourne le site.
     *
     * @return long Valeur du site.
     */
    public long getSite() {
        return this.site;
    }

    /**
     * Retourne l'activité.
     *
     * @return long Valeur de l'activité.
     */
    public long getActivite() {
        return this.activite;
    }

    /**
     * Retourne le suivi.
     *
     * @return String Valeur du suivi en caractère.
     */
    public String getSuivi() {
        return this.suivi;
    }

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut.
     */
    public long getStatut() {
        return this.statut;
    }

    /**
     * Retourne la date prévue.
     *
     * @return Timestamp Valeur de la date prévue (yyyy-MM-dd).
     */
    public Timestamp getDatePrevue() {
        return this.datePrevue;
    }

    /**
     * Retourne la date complétée.
     *
     * @return Timestamp Valeur de la date complétée (yyyy-MM-dd).
     */
    public Timestamp getDateCompletee() {
        return this.dateCompletee;
    }

    /**
     * Conserve certaines valeurs de dossier.
     *
     * @param dossier Dossier retourné par une recherche de narrations.
     */
    public void setDossier(Dossier dossier){
        this.dossier = dossier;
    }

    /**
     * Retourne le secteur d'origine.
     *
     * @return long Valeur du secteur d'origine.
     */
    public long getSecteurOrigine() {
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
     * @return long Valeur du secteur assigné.
     */
    public long getSecteurAssigne() {
        return this.secteurAssigne;
    }

    /**
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur() {
        return this.createur;
    }

    /**
     * Retourne la confidentialité du suivi.
     *
     * @return long Valeur de la confidentialité du suivi.
     */
    public long getConfidentialiteSuivi() {
        return this.confidentialiteSuivi;
    }

    /**
     * Retourne le niveau hierarchique du suivi.
     *
     * @return long Valeur du niveau hierarchique du suivi.
     */
    public long getNiveauHierarchiqueSuivi() {
        return this.niveauHierarchiqueSuivi;
    }

    /**
     * Retourne la confidentialité du créateur.
     *
     * @return long Valeur de la confidentialité du créateur.
     */
    public long getConfidentialiteCreateur() {
        return this.confidentialiteCreateur;
    }

    /**
     * Retourne le niveau hierarchique du créateur.
     *
     * @return long Valeur du niveau hierarchique du créateur.
     */
    public long getNiveauHierarchiqueCreateur() {
        return this.niveauHierarchiqueCreateur;
    }

    /**
     * Retourne la date de création.
     *
     * @return Timestamp Valeur de la date de création (yyyy-MM-dd).
     */
    public Timestamp getDateCreation() {
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
     * @return Timestamp Valeur de la date de modification (yyyy-MM-dd).
     */
    public Timestamp getDateModification() {
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
     * @return long Valeur de la confidentialité de l'approbateur.
     */
    public long getConfidentialiteApprobateur() {
        return this.confidentialiteApprobateur;
    }

    /**
     * Retourne le niveau hierarchique de l'approbateur.
     *
     * @return long Valeur du niveau hierarchique de l'approbateur.
     */
    public long getNiveauHierarchiqueApprobateur() {
        return this.niveauHierarchiqueApprobateur;
    }

    /**
     * Retourne la date d'approbateur.
     *
     * @return Timestamp Valeur de la date d'approbateur (yyyy-MM-dd).
     */
    public Timestamp getDateApprobation() {
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
     * @return long Valeur du lien.
     */
    public long getLien() {
        return this.lien;
    }

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite() {
        return this.lienSite;
    }


    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle.
     */
    public void setCle(long cle) {
        this.cle = cle;
    }

    /**
     * Affecte un site.
     *
     * @param site Valeur du site.
     */
    public void setSite(long site) {
        this.site = site;
    }

    /**
     * Affecte une activité.
     *
     * @param activite Valeur de l'activité.
     */
    public void setActivite(long activite) {
        this.activite = activite;
    }

    /**
     * Affecte un suivi.
     *
     * @param suivi Valeur du suivi en caractère.
     */
    public void setSuivi(String suivi) {
        this.suivi = suivi;
    }

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut.
     */
    public void setStatut(long statut) {
        this.statut = statut;
    }

    /**
     * Affecte une date prévue.
     *
     * @param datePrevue Valeur de la date prévue (yyyy-MM-dd).
     */
    public void setDatePrevue(Timestamp datePrevue) {
        this.datePrevue = datePrevue;
    }

    /**
     * Affecte une date complétée.
     *
     * @param dateCompletee Valeur de la date complétée (yyyy-MM-dd).
     */
    public void setDateCompletee(Timestamp dateCompletee) {
        this.dateCompletee = dateCompletee;
    }

    /**
     * Affecte un secteur d'origine.
     *
     * @return secteurOrigine Valeur du secteur d'origine.
     */
    public void setSecteurOrigine(long secteurOrigine) {
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
     * @param secteurAssigne Valeur du secteur assigné.
     */
    public void setSecteurAssigne(long secteurAssigne) {
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
     * @param confidentialiteSuivi Valeur de la confidentialité du suivi.
     */
    public void setConfidentialiteSuivi(long confidentialiteSuivi) {
        this.confidentialiteSuivi = confidentialiteSuivi;
    }

    /**
     * Affecte un niveau hierarchique du suivi.
     *
     * @param niveauHierarchiqueSuivi Valeur du niveau hierarchique du suivi.
     */
    public void setNiveauHierarchiqueSuivi(long niveauHierarchiqueSuivi) {
        this.niveauHierarchiqueSuivi = niveauHierarchiqueSuivi;
    }

    /**
     * Affecte une confidentialité du créateur.
     *
     * @param confidentialiteCreateur Valeur de la confidentialité du créateur.
     */
    public void setConfidentialiteCreateur(long confidentialiteCreateur) {
        this.confidentialiteCreateur = confidentialiteCreateur;
    }

    /**
     * Affecte un niveau hierarchique du créateur.
     *
     * @param niveauHierarchiqueCreateur Valeur du niveau hierarchique du
     * créateur.
     */
    public void setNiveauHierarchiqueCreateur(long niveauHierarchiqueCreateur) {
        this.niveauHierarchiqueCreateur = niveauHierarchiqueCreateur;
    }

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation) {
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
     * @param dateModification Valeur de la date de modification (yyyy-MM-dd).
     */
    public void setDateModification(Timestamp dateModification) {
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
     * l'approbateur.
     */
    public void setConfidentialiteApprobateur(long confidentialiteApprobateur) {
        this.confidentialiteApprobateur = confidentialiteApprobateur;
    }

    /**
     * Affecte un niveau hierarchique de l'approbateur.
     *
     * @param niveauHierarchiqueApprobateur Valeur du niveau hierarchique de
     * l'approbateur.
     */
    public void setNiveauHierarchiqueApprobateur(
            long niveauHierarchiqueApprobateur) {
        this.niveauHierarchiqueApprobateur = niveauHierarchiqueApprobateur;
    }

    /**
     * Affecte une date d'approbateur.
     *
     * @param dateApprobation Valeur de la date d'approbateur (yyyy-MM-dd).
     */
    public void setDateApprobation(Timestamp dateApprobation) {
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
     * @param lien Valeur du lien.
     */
    public void setLien(long lien) {
        this.lien = lien;
    }

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite) {
        this.lienSite = lienSite;
    }

    /**
     * Retourne une chaîne de caractère reflétant la valeur de tout les
     * attributs du SuiviVO.
     *
     * @return String Valeur de tout les attributs du SuiviVO en caractère.
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[SuiviVO : ");
        stringBuffer.append("cle = '" + cle);
        stringBuffer.append("', site = '" + site);
        stringBuffer.append("', activite = '" + activite);
        stringBuffer.append("', suivi = '" + suivi);
        stringBuffer.append("', statut = '" + statut);
        stringBuffer.append("', datePrevue = '"
                + TimestampFormat.format(datePrevue));
        stringBuffer.append("', dateCompletee = '"
                + TimestampFormat.format(dateCompletee));
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
                + TimestampFormat.format(dateCreation));
        stringBuffer.append("', modificateur = '" + modificateur);
        stringBuffer.append("', dateModification = '"
                + TimestampFormat.format(dateModification));
        stringBuffer.append("', approbateur = '" + approbateur);
        stringBuffer.append("', confidentialiteApprobateur = '"
                + confidentialiteApprobateur);
        stringBuffer.append("', niveauHierarchiqueApprobateur = '"
                + niveauHierarchiqueApprobateur);
        stringBuffer.append("', dateApprobation = '"
                + TimestampFormat.format(dateApprobation));
        stringBuffer.append("', reference1 = '" + reference1);
        stringBuffer.append("', reference2 = '" + reference2);
        stringBuffer.append("', reference2 = '" + reference2);
        stringBuffer.append("', lien = '" + lienSite );
        stringBuffer.append("', lienSite = '" + lienSite );
        stringBuffer.append("']");
        return stringBuffer.toString();
    }


	/**
	 * @return dateChangement
	 */
	public Timestamp getDateChangement() {
		return dateChangement;
	}


	/**
	 * @param dateChangement dateChangement à définir
	 */
	public void setDateChangement(Timestamp dateChangement) {
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


	public Boolean getPermettreComplete() {
		return permettreComplete;
	}


	public Boolean getModifiable() {
		return modifiable;
	}


	public Boolean getApprouvable() {
		return approuvable;
	}


	/**
	 * @return entite
	 */
	public long getEntite() {
		return entite;
	}


	/**
	 * @param entite entite à définir
	 */
	public void setEntite(long entite) {
		this.entite = entite;
	}
    
}