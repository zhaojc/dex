package com.lotoquebec.cardex.presentation.model;

/**
 * Définit la signature des méthodes des différentes valeurs relatives au
 * formulatire de consultation de suivi.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $, $Date: 2002/03/26 22:18:39 $
 */
public interface SuiviHtmlForm {


    // Getters


    /**
     * Retourne la clé.
     *
     * @return String Valeur de la clé en caractère.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caractère.
     */
    public String getSite();

    /**
     * Retourne l'activité.
     *
     * @return String Valeur de l'activité.
     */
    public String getActivite();

    /**
     * Retourne le suivi.
     *
     * @return String Valeur du suivi en caractère.
     */
    public String getSuivi();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caractère.
     */
    public String getStatut();

    /**
     * Retourne la date prévue.
     *
     * @return String Valeur de la date prévue en caractère.
     */
    public String getDatePrevue();

    /**
     * Retourne la date complétée.
     *
     * @return String Valeur de la date complétée en caractère.
     */
    public String getDateCompletee();

    /**
     * Retourne le secteur d'origine.
     *
     * @return String Valeur du secteur d'origine en caractère.
     */
    public String getSecteurOrigine();

    /**
     * Retourne le demandeur.
     *
     * @return String Valeur du demandeur en caractère.
     */
    public String getDemandeur();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur numérique de date.
     */
    public String getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur numérique de l'auteur.
     */
    public String getChangePar();

	/**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();

    /**
     * Retourne le secteur assigné.
     *
     * @return String Valeur du secteur assigné en caractère.
     */
    public String getSecteurAssigne();

    /**
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère en caractère.
     */
    public String getCreateur();

    /**
     * Retourne la confidentialité du suivi.
     *
     * @return String Valeur de la confidentialité du suivi en caractère.
     */
    public String getConfidentialiteSuivi();

    /**
     * Retourne le niveau hierarchique du suivi.
     *
     * @return String Valeur du niveau hierarchique du suivi en caractère.
     */
    public String getNiveauHierarchiqueSuivi();

    /**
     * Retourne la confidentialité du créateur.
     *
     * @return String Valeur de la confidentialité du créateur en caractère.
     */
    public String getConfidentialiteCreateur();

    /**
     * Retourne le niveau hierarchique du créateur.
     *
     * @return String Valeur du niveau hierarchique du créateur en caractère.
     */
    public String getNiveauHierarchiqueCreateur();

    /**
     * Retourne la date de création.
     *
     * @return String Valeur de la date de création en caractère.
     */
    public String getDateCreation();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caractère.
     */
    public String getModificateur();

    /**
     * Retourne la date de modification.
     *
     * @return String Valeur de la date de modification en caractère.
     */
    public String getDateModification();

    /**
     * Retourne l'approbateur.
     *
     * @return String Valeur de l'approbateur en caractère.
     */
    public String getApprobateur();

    /**
     * Retourne la confidentialité de l'approbateur.
     *
     * @return String Valeur de la confidentialité de l'approbateur en
     * caractère.
     */
    public String getConfidentialiteApprobateur();

    /**
     * Retourne le niveau hierarchique de l'approbateur.
     *
     * @return String Valeur du niveau hierarchique de l'approbateur en
     * caractère.
     */
    public String getNiveauHierarchiqueApprobateur();

    /**
     * Retourne la date d'approbateur.
     *
     * @return String Valeur de la date d'approbateur en caractère.
     */
    public String getDateApprobation();

    /**
     * Retourne la première référence.
     *
     * @return String Valeur de la première référence en caractère.
     */
    public String getReference1();

    /**
     * Retourne la deuxième référence.
     *
     * @return String Valeur de la deuxième référence en caractère.
     */
    public String getReference2();

    /**
     * Retourne la troisième référence.
     *
     * @return String Valeur de la troisième référence en caractère.
     */
    public String getReference3();

    /**
     * Retourne le lien.
     *
     * @return String Valeur du lien en caractère.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caractère.
     */
    public String getLienSite();

	/**
	 * Retourne une entité.
	 *
	 * @param entite Valeur de l'entité en caractère.
	 */
	public String getEntite();

    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la clé en caractère.
     */
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caractère.
     */
    public void setSite(String site);

    /**
     * Affecte une activité.
     *
     * @param activite Valeur de l'activité en caractère.
     */
    public void setActivite(String activite);

    /**
     * Affecte un suivi.
     *
     * @param suivi Valeur du suivi en caractère en caractère.
     */
    public void setSuivi(String suivi);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caractère.
     */
    public void setStatut(String statut);

    /**
     * Affecte une date prévue.
     *
     * @param datePrevue Valeur de la date prévue en caractère.
     */
    public void setDatePrevue(String datePrevue);

    /**
     * Affecte une date complétée.
     *
     * @param dateCompletee Valeur de la date complétée en caractère.
     */
    public void setDateCompletee(String dateCompletee);

    /**
     * Affecte un secteur d'origine.
     *
     * @return secteurOrigine Valeur du secteur d'origine en caractère.
     */
    public void setSecteurOrigine(String secteurOrigine);

    /**
     * Affecte un demandeur.
     *
     * @param demandeur Valeur du demandeur en caractère.
     */
    public void setDemandeur(String demandeur);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caractère.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte un secteur assigné.
     *
     * @param secteurAssigne Valeur du secteur assigné en caractère.
     */
    public void setSecteurAssigne(String secteurAssigne);

    /**
     * Affecte un créateur.
     *
     * @param createur Valeur du créateur en caractère.
     */
    public void setCreateur(String createur);

    /**
     * Affecte une confidentialité du suivi.
     *
     * @param confidentialiteSuivi Valeur de la confidentialité du suivi en
     * caractère.
     */
    public void setConfidentialiteSuivi(String confidentialiteSuivi);

    /**
     * Affecte un niveau hierarchique du suivi.
     *
     * @param niveauHierarchiqueSuivi Valeur du niveau hierarchique du suivi en
     * caractère.
     */
    public void setNiveauHierarchiqueSuivi(String niveauHierarchiqueSuivi);

    /**
     * Affecte une confidentialité du créateur.
     *
     * @param confidentialiteCreateur Valeur de la confidentialité du créateur
     * en caractère.
     */
    public void setConfidentialiteCreateur(String confidentialiteCreateur);

    /**
     * Affecte un niveau hierarchique du créateur.
     *
     * @param niveauHierarchiqueCreateur Valeur du niveau hierarchique du
     * créateur.
     */
    public void setNiveauHierarchiqueCreateur(
            String niveauHierarchiqueCreateur);

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création en caractère.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caractère.
     */
    public void setModificateur(String modificateur);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caractère.
     */
    public void setDateModification(String dateModification);

    /**
     * Affecte un approbateur.
     *
     * @param approbateur Valeur de l'approbateur en caractère.
     */
    public void setApprobateur(String approbateur);

    /**
     * Affecte une confidentialité de l'approbateur.
     *
     * @param confidentialiteApprobateur Valeur de la confidentialité de
     * l'approbateur en caractère.
     */
    public void setConfidentialiteApprobateur(
            String confidentialiteApprobateur);

    /**
     * Affecte un niveau hierarchique de l'approbateur.
     *
     * @param niveauHierarchiqueApprobateur Valeur du niveau hierarchique de
     * l'approbateur en caractère.
     */
    public void setNiveauHierarchiqueApprobateur(
            String niveauHierarchiqueApprobateur);

    /**
     * Affecte une date d'approbateur.
     *
     * @param dateApprobation Valeur de la date d'approbateur en caractère.
     */
    public void setDateApprobation(String dateApprobation);

    /**
     * Affecte une première référence.
     *
     * @param reference1 Valeur de la première référence en caractère.
     */
    public void setReference1(String reference1);

    /**
     * Retourne la deuxième référence.
     *
     * @param reference2 Valeur de la deuxième référence en caractère.
     */
    public void setReference2(String reference2);

    /**
     * Affecte une troisième référence.
     *
     * @param reference3 Valeur de la troisième référence en caractère.
     */
    public void setReference3(String reference3);

    /**
     * Affecte la date du changement (pour l'audit).
     *
     * @param dateChangement Valeur dateChangement.
     */
    public void setDateChangement(String dateChangement);

    /**
     * Affecte l'auteur du changement (pour l'audit).
     *
     * @param changePar Valeur changePar.
     */
    public void setChangePar(String changePar);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien en caractère.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caractère.
     */
    public void setLienSite(String lienSite);

    /**
     * Test si un suivi peut être modifié.
     *
     * @return boolean True si la narration est modifiable.
     */
    public boolean isModifiable();

    /**
     * Test si un suivi peut être approuvé.
     *
     * @return boolean True si la narration est approuvable.
     */
    public boolean isApprouvable();

    /**
     * Test si on peut compléter un suivi.
     *
     * @return boolean True si on peut permettre la modification d'une narration.
     */
    public boolean isPermettreComplete();

    /**
     * Determine si un suivi est modifiable
     *
     * @param isModifiable Est-ce que le suivi est modifiable
     * caractère.
     */
    public void setModifiable(boolean isModifiable);

    /**
     * Determine si un suivi est approuvanle
     *
     * @param isApprouvable Est-ce que le suivi est approuvable
     * caractère.
     */
    public void setApprouvable(boolean isApprouvable);

    /**
     * Détermine si on peut compléter une suivi.
     *
     * @param isPermettreComplete Est-ce qu'on peut compléter une suivi.
     * caractère.
     */
    public void setPermettreComplete(boolean isPermettreComplete);

	/**
	 * Affecte une entité.
	 *
	 * @param entite Valeur de l'entité en caractère.
	 */
	public void setEntite(String entite);

}