package com.lotoquebec.cardex.business;

import java.sql.Timestamp;

/**
 * Permet de transiter les informations relatives � un suivi de la couche
 * pr�sentation � la couche d'affaire.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.6 $, $Date: 2002/03/26 22:17:44 $
 */
public interface Suivi extends Modifiable{


    // Getters

    /**
     * Retourne certaines informations sur le dossier retourn�
     * par une recherche de narrations.  Ces informations sont
     * utilis�es pour appeler le dossier � l'�cran � partir de la
     * liste de r�sultats.
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
	 * Retourne l'entit�.
	 *
	 * @return long Valeur de l'entit� en caract�re.
	 */
	public long getEntite();

    /**
     * Retourne l'activit�.
     *
     * @return long Valeur de l'activit�.
     */
    public long getActivite();

    /**
     * Retourne le suivi.
     *
     * @return String Valeur du suivi en caract�re.
     */
    public String getSuivi();

    /**
     * Retourne le statut.
     *
     * @return long Valeur du statut.
     */
    public long getStatut();

    /**
     * Retourne la date pr�vue.
     *
     * @return Timestamp Valeur de la date pr�vue (yyyy-MM-dd).
     */
    public Timestamp getDatePrevue();

    /**
     * Retourne la date compl�t�e.
     *
     * @return Timestamp Valeur de la date compl�t�e (yyyy-MM-dd).
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
     * @return String Valeur du demandeur en caract�re.
     */
    public String getDemandeur();

    /**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();

    /**
     * Retourne le secteur assign�.
     *
     * @return long Valeur du secteur assign�.
     */
    public long getSecteurAssigne();

    /**
     * Retourne le cr�ateur.
     *
     * @return String Valeur du cr�ateur en caract�re.
     */
    public String getCreateur();

    /**
     * Retourne la confidentialit� du suivi.
     *
     * @return long Valeur de la confidentialit� du suivi.
     */
    public long getConfidentialiteSuivi();

    /**
     * Retourne le niveau hierarchique du suivi.
     *
     * @return long Valeur du niveau hierarchique du suivi.
     */
    public long getNiveauHierarchiqueSuivi();

    /**
     * Retourne la confidentialit� du cr�ateur.
     *
     * @return long Valeur de la confidentialit� du cr�ateur.
     */
    public long getConfidentialiteCreateur();

    /**
     * Retourne le niveau hierarchique du cr�ateur.
     *
     * @return long Valeur du niveau hierarchique du cr�ateur.
     */
    public long getNiveauHierarchiqueCreateur();

    /**
     * Retourne la date de cr�ation.
     *
     * @return Timestamp Valeur de la date de cr�ation (yyyy-MM-dd).
     */
    public Timestamp getDateCreation();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caract�re.
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
     * @return String Valeur de l'approbateur en caract�re.
     */
    public String getApprobateur();

    /**
     * Retourne la confidentialit� de l'approbateur.
     *
     * @return long Valeur de la confidentialit� de l'approbateur.
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
     * Retourne la premi�re r�f�rence.
     *
     * @return String Valeur de la premi�re r�f�rence en caract�re.
     */
    public String getReference1();

    /**
     * Retourne la deuxi�me r�f�rence.
     *
     * @return String Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public String getReference2();

    /**
     * Retourne la troisi�me r�f�rence.
     *
     * @return String Valeur de la troisi�me r�f�rence en caract�re.
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
     * @return String Valeur num�rique de date.
     */
    public Timestamp getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur num�rique de l'auteur.
     */
    public String getChangePar();

    // Setters

    /**
     * Conserve certaines valeurs de dossier.
     *
     * @param dossier Dossier retourn� par une recherche de narrations.
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
	 * Affecte une entit�.
	 *
	 * @param entite Valeur de l'entit� en caract�re.
	 */
	public void setEntite(long entite);

    
    /**
     * Affecte une activit�.
     *
     * @param activite Valeur de l'activit�.
     */
    public void setActivite(long activite);

    /**
     * Affecte un suivi.
     *
     * @param suivi Valeur du suivi en caract�re.
     */
    public void setSuivi(String suivi);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut.
     */
    public void setStatut(long statut);

    /**
     * Affecte une date pr�vue.
     *
     * @param datePrevue Valeur de la date pr�vue (yyyy-MM-dd).
     */
    public void setDatePrevue(Timestamp datePrevue);

    /**
     * Affecte une date compl�t�e.
     *
     * @param dateCompletee Valeur de la date compl�t�e (yyyy-MM-dd).
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
     * @param demandeur Valeur du demandeur en caract�re.
     */
    public void setDemandeur(String demandeur);

    /**
     * Affecte un intervenant.
     *
     * @param intervenant Valeur de l'intervenant en caract�re.
     */
    public void setIntervenant(String intervenant);

    /**
     * Affecte un secteur assign�.
     *
     * @param secteurAssigne Valeur du secteur assign�.
     */
    public void setSecteurAssigne(long secteurAssigne);

    /**
     * Affecte un cr�ateur.
     *
     * @param createur Valeur du cr�ateur en caract�re.
     */
    public void setCreateur(String createur);

    /**
     * Affecte une confidentialit� du suivi.
     *
     * @param confidentialiteSuivi Valeur de la confidentialit� du suivi.
     */
    public void setConfidentialiteSuivi(long confidentialiteSuivi);

    /**
     * Affecte un niveau hierarchique du suivi.
     *
     * @param niveauHierarchiqueSuivi Valeur du niveau hierarchique du suivi.
     */
    public void setNiveauHierarchiqueSuivi(long niveauHierarchiqueSuivi);

    /**
     * Affecte une confidentialit� du cr�ateur.
     *
     * @param confidentialiteCreateur Valeur de la confidentialit� du cr�ateur.
     */
    public void setConfidentialiteCreateur(long confidentialiteCreateur);

    /**
     * Affecte un niveau hierarchique du cr�ateur.
     *
     * @param niveauHierarchiqueCreateur Valeur du niveau hierarchique du
     * cr�ateur.
     */
    public void setNiveauHierarchiqueCreateur(long niveauHierarchiqueCreateur);

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation (yyyy-MM-dd).
     */
    public void setDateCreation(Timestamp dateCreation);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caract�re.
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
     * @param approbateur Valeur de l'approbateur en caract�re.
     */
    public void setApprobateur(String approbateur);

    /**
     * Affecte une confidentialit� de l'approbateur.
     *
     * @param confidentialiteApprobateur Valeur de la confidentialit� de
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
     * Affecte une premi�re r�f�rence.
     *
     * @param reference1 Valeur de la premi�re r�f�rence en caract�re.
     */
    public void setReference1(String reference1);

    /**
     * Retourne la deuxi�me r�f�rence.
     *
     * @param reference2 Valeur de la deuxi�me r�f�rence en caract�re.
     */
    public void setReference2(String reference2);

    /**
     * Affecte une troisi�me r�f�rence.
     *
     * @param reference3 Valeur de la troisi�me r�f�rence en caract�re.
     */
    public void setReference3(String reference3);

    /**
     * Affecte de la date du changement (audit).
     *
     * @param dateEvenement Valeur de la date d'�v�nement (yyyy-MM-dd).
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
     * Test si un suivi peut �tre modifi�.
     *
     * @return boolean True si la narration est modifiable.
     */
    public Boolean isModifiable();

    /**
     * Test si un suivi peut �tre approuv�.
     *
     * @return boolean True si la narration est approuvable.
     */
    public Boolean isApprouvable();

    /**
     * Test si on peut compl�ter un suivi.
     *
     * @return boolean True si on peut permettre la modification d'une narration.
     */
    public Boolean isPermettreComplete();

    /**
     * Determine si un suivi est modifiable
     *
     * @param isModifiable Est-ce que le suivi est modifiable
     * caract�re.
     */
    public void setModifiable(Boolean isModifiable);

    /**
     * Determine si un suivi est approuvanle
     *
     * @param isApprouvable Est-ce que le suivi est approuvable
     * caract�re.
     */
    public void setApprouvable(Boolean isApprouvable);

    /**
     * D�termine si on peut compl�ter une suivi.
     *
     * @param isPermettreComplete Est-ce qu'on peut compl�ter une suivi.
     * caract�re.
     */
    public void setPermettreComplete(Boolean isPermettreComplete);


}