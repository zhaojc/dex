package com.lotoquebec.cardex.business.vo;

import java.sql.Timestamp;

import com.lotoquebec.cardex.business.Dossier;
import com.lotoquebec.cardex.business.Suivi;
import com.lotoquebec.cardexCommun.text.TimestampFormat;

/**
 * Permet de transiter les informations relatives � un suivi de la couche
 * pr�sentation � la couche d'affaire.
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
    private String reference2 = ""; // Secteur de r�f�rence du demandeur du suivi (ORIGINE)
    private String reference3 = ""; // Secteur de r�f�rence de l'utilisteur assign� (ASSIGNE
    private long lien = 0;
    private long lienSite = 0;
    private Dossier dossier = new DossierVO();
    private Boolean permettreComplete = false;
    private Boolean modifiable = false;
    private Boolean approuvable = false;
    //Mars 2011. Ajout de deux champs pour la lecture de l'audit.
    //Les autres champs de l'audit sont identiques � ceux du dossier.
    private Timestamp dateChangement= null;
    private String changePar = "";

    /**
     * Constructeur de SuiviVO par d�faut.
     */
    public SuiviVO() {}


    // Getters

    /**
     * Test si un suivi peut �tre modifi�.
     *
     * @return boolean True si la narration est modifiable.
     */
    public Boolean isModifiable() {
        return this.modifiable;
    }

    /**
     * Test si un suivi peut �tre approuv�.
     *
     * @return boolean True si la narration est approuvable.
     */
    public Boolean isApprouvable() {
        return this.approuvable;
    }

    /**
     * Test si on peut compl�ter un suivi.
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
     * caract�re.
     */
    public void setModifiable(Boolean modifiable) {
      this.modifiable = modifiable;
    }

    /**
     * Determine si un suivi est approuvanle
     *
     * @param isApprouvable Est-ce que le suivi est approuvable
     * caract�re.
     */
    public void setApprouvable(Boolean approuvable) {
      this.approuvable = approuvable;
    }

    /**
     * D�termine si on peut compl�ter une suivi.
     *
     * @param isPermettreComplete Est-ce qu'on peut compl�ter une suivi.
     * caract�re.
     */
    public void setPermettreComplete(Boolean permettreComplete){
      this.permettreComplete = permettreComplete;
    }

    /**
     * Retourne certaines informations sur le dossier retourn�
     * par une recherche de narrations.  Ces informations sont
     * utilis�es pour appeler le dossier � l'�cran � partir de la
     * liste de r�sultats.
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
     * Retourne l'activit�.
     *
     * @return long Valeur de l'activit�.
     */
    public long getActivite() {
        return this.activite;
    }

    /**
     * Retourne le suivi.
     *
     * @return String Valeur du suivi en caract�re.
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
     * Retourne la date pr�vue.
     *
     * @return Timestamp Valeur de la date pr�vue (yyyy-MM-dd).
     */
    public Timestamp getDatePrevue() {
        return this.datePrevue;
    }

    /**
     * Retourne la date compl�t�e.
     *
     * @return Timestamp Valeur de la date compl�t�e (yyyy-MM-dd).
     */
    public Timestamp getDateCompletee() {
        return this.dateCompletee;
    }

    /**
     * Conserve certaines valeurs de dossier.
     *
     * @param dossier Dossier retourn� par une recherche de narrations.
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
     * @return long Valeur du secteur assign�.
     */
    public long getSecteurAssigne() {
        return this.secteurAssigne;
    }

    /**
     * Retourne le cr�ateur.
     *
     * @return String Valeur du cr�ateur en caract�re.
     */
    public String getCreateur() {
        return this.createur;
    }

    /**
     * Retourne la confidentialit� du suivi.
     *
     * @return long Valeur de la confidentialit� du suivi.
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
     * Retourne la confidentialit� du cr�ateur.
     *
     * @return long Valeur de la confidentialit� du cr�ateur.
     */
    public long getConfidentialiteCreateur() {
        return this.confidentialiteCreateur;
    }

    /**
     * Retourne le niveau hierarchique du cr�ateur.
     *
     * @return long Valeur du niveau hierarchique du cr�ateur.
     */
    public long getNiveauHierarchiqueCreateur() {
        return this.niveauHierarchiqueCreateur;
    }

    /**
     * Retourne la date de cr�ation.
     *
     * @return Timestamp Valeur de la date de cr�ation (yyyy-MM-dd).
     */
    public Timestamp getDateCreation() {
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
     * @return Timestamp Valeur de la date de modification (yyyy-MM-dd).
     */
    public Timestamp getDateModification() {
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
     * @return long Valeur de la confidentialit� de l'approbateur.
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
     * Affecte une activit�.
     *
     * @param activite Valeur de l'activit�.
     */
    public void setActivite(long activite) {
        this.activite = activite;
    }

    /**
     * Affecte un suivi.
     *
     * @param suivi Valeur du suivi en caract�re.
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
     * Affecte une date pr�vue.
     *
     * @param datePrevue Valeur de la date pr�vue (yyyy-MM-dd).
     */
    public void setDatePrevue(Timestamp datePrevue) {
        this.datePrevue = datePrevue;
    }

    /**
     * Affecte une date compl�t�e.
     *
     * @param dateCompletee Valeur de la date compl�t�e (yyyy-MM-dd).
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
     * @param secteurAssigne Valeur du secteur assign�.
     */
    public void setSecteurAssigne(long secteurAssigne) {
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
     * @param confidentialiteSuivi Valeur de la confidentialit� du suivi.
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
     * Affecte une confidentialit� du cr�ateur.
     *
     * @param confidentialiteCreateur Valeur de la confidentialit� du cr�ateur.
     */
    public void setConfidentialiteCreateur(long confidentialiteCreateur) {
        this.confidentialiteCreateur = confidentialiteCreateur;
    }

    /**
     * Affecte un niveau hierarchique du cr�ateur.
     *
     * @param niveauHierarchiqueCreateur Valeur du niveau hierarchique du
     * cr�ateur.
     */
    public void setNiveauHierarchiqueCreateur(long niveauHierarchiqueCreateur) {
        this.niveauHierarchiqueCreateur = niveauHierarchiqueCreateur;
    }

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation) {
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
     * @param dateModification Valeur de la date de modification (yyyy-MM-dd).
     */
    public void setDateModification(Timestamp dateModification) {
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
     * Retourne une cha�ne de caract�re refl�tant la valeur de tout les
     * attributs du SuiviVO.
     *
     * @return String Valeur de tout les attributs du SuiviVO en caract�re.
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
	 * @param dateChangement dateChangement � d�finir
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
	 * @param changePar changePar � d�finir
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
	 * @param entite entite � d�finir
	 */
	public void setEntite(long entite) {
		this.entite = entite;
	}
    
}