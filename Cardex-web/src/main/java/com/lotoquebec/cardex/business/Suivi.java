package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * Permet de transiter les informations relatives à un suivi de la couche
 * présentation à la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/03/26 22:17:44 $
 */
public interface Suivi extends Modifiable{


    // Getters

    /**
     * Retourne certaines informations sur le dossier retourné
     * par une recherche de narrations.  Ces informations sont
     * utilisées pour appeler le dossier à l'écran à partir de la
     * liste de résultats.
     *
     * @return Dossier Valeurs du dossier.
     */
    public Dossier getDossier();

    /**
     * Retourne la cle.
     *
     * @return long Valeur de la cle.
     */
    public long getCle();

    /**
     * Retourne le site.
     *
     * @return long Valeur du site.
     */
    public long getSite();

	/**
	 * Retourne l'entité.
	 *
	 * @return long Valeur de l'entité en caractère.
	 */
	public long getEntite();

    /**
     * Retourne l'activité.
     *
     * @return long Valeur de l'activité.
     */
    public long getActivite();

    /**
     * Retourne le suivi.
     *
     * @return String Valeur du suivi en caractère.
     */
    public String getSuivi();

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut.
     */
    public long getStatut();

    /**
     * Retourne la date prévue.
     *
     * @return Timestamp Valeur de la date prévue (yyyy-MM-dd).
     */
    public Timestamp getDatePrevue();

    /**
     * Retourne la date complétée.
     *
     * @return Timestamp Valeur de la date complétée (yyyy-MM-dd).
     */
    public Timestamp getDateCompletee();

    /**
     * Retourne le secteur d'origine.
     *
     * @return long Valeur du secteur d'origine.
     */
    public long getSecteurOrigine();

    /**
     * Retourne le demandeur.
     *
     * @return String Valeur du demandeur en caractère.
     */
    public String getDemandeur();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caractère.
     */
    public String getIntervenant();

    /**
     * Retourne le secteur assigné.
     *
     * @return long Valeur du secteur assigné.
     */
    public long getSecteurAssigne();

    /**
     * Retourne le créateur.
     *
     * @return String Valeur du créateur en caractère.
     */
    public String getCreateur();

    /**
     * Retourne la confidentialité du suivi.
     *
     * @return long Valeur de la confidentialité du suivi.
     */
    public long getConfidentialiteSuivi();

    /**
     * Retourne le niveau hierarchique du suivi.
     *
     * @return long Valeur du niveau hierarchique du suivi.
     */
    public long getNiveauHierarchiqueSuivi();

    /**
     * Retourne la confidentialité du créateur.
     *
     * @return long Valeur de la confidentialité du créateur.
     */
    public long getConfidentialiteCreateur();

    /**
     * Retourne le niveau hierarchique du créateur.
     *
     * @return long Valeur du niveau hierarchique du créateur.
     */
    public long getNiveauHierarchiqueCreateur();

    /**
     * Retourne la date de création.
     *
     * @return Timestamp Valeur de la date de création (yyyy-MM-dd).
     */
    public Timestamp getDateCreation();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caractère.
     */
    public String getModificateur();

    /**
     * Retourne la date de modification.
     *
     * @return Timestamp Valeur de la date de modification (yyyy-MM-dd).
     */
    public Timestamp getDateModification();

    /**
     * Retourne l'approbateur.
     *
     * @return String Valeur de l'approbateur en caractère.
     */
    public String getApprobateur();

    /**
     * Retourne la confidentialité de l'approbateur.
     *
     * @return long Valeur de la confidentialité de l'approbateur.
     */
    public long getConfidentialiteApprobateur();

    /**
     * Retourne le niveau hierarchique de l'approbateur.
     *
     * @return long Valeur du niveau hierarchique de l'approbateur.
     */
    public long getNiveauHierarchiqueApprobateur();

    /**
     * Retourne la date d'approbateur.
     *
     * @return Timestamp Valeur de la date d'approbateur (yyyy-MM-dd).
     */
    public Timestamp getDateApprobation();

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
     * @return long Valeur du lien.
     */
    public long getLien();

    /**
     * Retourne le lien du site.
     *
     * @return long Valeur du lien du site.
     */
    public long getLienSite();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur numérique de date.
     */
    public Timestamp getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur numérique de l'auteur.
     */
    public String getChangePar();

    // Setters

    /**
     * Conserve certaines valeurs de dossier.
     *
     * @param dossier Dossier retourné par une recherche de narrations.
     */
    public void setDossier(Dossier dossier);

    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cle.
     */
    public void setCle(long cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site.
     */
    public void setSite(long site);

	/**
	 * Affecte une entité.
	 *
	 * @param entite Valeur de l'entité en caractère.
	 */
	public void setEntite(long entite);

    
    /**
     * Affecte une activité.
     *
     * @param activite Valeur de l'activité.
     */
    public void setActivite(long activite);

    /**
     * Affecte un suivi.
     *
     * @param suivi Valeur du suivi en caractère.
     */
    public void setSuivi(String suivi);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut.
     */
    public void setStatut(long statut);

    /**
     * Affecte une date prévue.
     *
     * @param datePrevue Valeur de la date prévue (yyyy-MM-dd).
     */
    public void setDatePrevue(Timestamp datePrevue);

    /**
     * Affecte une date complétée.
     *
     * @param dateCompletee Valeur de la date complétée (yyyy-MM-dd).
     */
    public void setDateCompletee(Timestamp dateCompletee);

    /**
     * Affecte un secteur d'origine.
     *
     * @return secteurOrigine Valeur du secteur d'origine.
     */
    public void setSecteurOrigine(long secteurOrigine);

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
     * @param secteurAssigne Valeur du secteur assigné.
     */
    public void setSecteurAssigne(long secteurAssigne);

    /**
     * Affecte un créateur.
     *
     * @param createur Valeur du créateur en caractère.
     */
    public void setCreateur(String createur);

    /**
     * Affecte une confidentialité du suivi.
     *
     * @param confidentialiteSuivi Valeur de la confidentialité du suivi.
     */
    public void setConfidentialiteSuivi(long confidentialiteSuivi);

    /**
     * Affecte un niveau hierarchique du suivi.
     *
     * @param niveauHierarchiqueSuivi Valeur du niveau hierarchique du suivi.
     */
    public void setNiveauHierarchiqueSuivi(long niveauHierarchiqueSuivi);

    /**
     * Affecte une confidentialité du créateur.
     *
     * @param confidentialiteCreateur Valeur de la confidentialité du créateur.
     */
    public void setConfidentialiteCreateur(long confidentialiteCreateur);

    /**
     * Affecte un niveau hierarchique du créateur.
     *
     * @param niveauHierarchiqueCreateur Valeur du niveau hierarchique du
     * créateur.
     */
    public void setNiveauHierarchiqueCreateur(long niveauHierarchiqueCreateur);

    /**
     * Affecte une date de création.
     *
     * @param dateCreation Valeur de la date de création (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caractère.
     */
    public void setModificateur(String modificateur);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification (yyyy-MM-dd).
     */
    public void setDateModification(Timestamp dateModification);

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
     * l'approbateur.
     */
    public void setConfidentialiteApprobateur(long confidentialiteApprobateur);

    /**
     * Affecte un niveau hierarchique de l'approbateur.
     *
     * @param niveauHierarchiqueApprobateur Valeur du niveau hierarchique de
     * l'approbateur.
     */
    public void setNiveauHierarchiqueApprobateur(
            long niveauHierarchiqueApprobateur);

    /**
     * Affecte une date d'approbateur.
     *
     * @param dateApprobation Valeur de la date d'approbateur (yyyy-MM-dd).
     */
    public void setDateApprobation(Timestamp dateApprobation);

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
     * Affecte de la date du changement (audit).
     *
     * @param dateEvenement Valeur de la date d'événement (yyyy-MM-dd).
     */
    public void setDateChangement(Timestamp dateChangement);

    /**
     * Affecte l'auteur du changement (pour l'audit).
     *
     * @param changePar Valeur changePar.
     */
    public void setChangePar(String changePar);

    /**
     * Affecte un lien.
     *
     * @param lien Valeur du lien.
     */
    public void setLien(long lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site.
     */
    public void setLienSite(long lienSite);

    /**
     * Test si un suivi peut être modifié.
     *
     * @return boolean True si la narration est modifiable.
     */
    public Boolean isModifiable();

    /**
     * Test si un suivi peut être approuvé.
     *
     * @return boolean True si la narration est approuvable.
     */
    public Boolean isApprouvable();

    /**
     * Test si on peut compléter un suivi.
     *
     * @return boolean True si on peut permettre la modification d'une narration.
     */
    public Boolean isPermettreComplete();

    /**
     * Determine si un suivi est modifiable
     *
     * @param isModifiable Est-ce que le suivi est modifiable
     * caractère.
     */
    public void setModifiable(Boolean isModifiable);

    /**
     * Determine si un suivi est approuvanle
     *
     * @param isApprouvable Est-ce que le suivi est approuvable
     * caractère.
     */
    public void setApprouvable(Boolean isApprouvable);

    /**
     * Détermine si on peut compléter une suivi.
     *
     * @param isPermettreComplete Est-ce qu'on peut compléter une suivi.
     * caractère.
     */
    public void setPermettreComplete(Boolean isPermettreComplete);


}