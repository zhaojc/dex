package com.lotoquebec.cardex.presentation.model;

/**
 * D�finit la signature des m�thodes des diff�rentes valeurs relatives au
 * formulatire de consultation de suivi.
 *
 * @author $Author: mlibersan $
 * @version $Revision: 1.5 $, $Date: 2002/03/26 22:18:39 $
 */
public interface SuiviHtmlForm {


    // Getters


    /**
     * Retourne la cl�.
     *
     * @return String Valeur de la cl� en caract�re.
     */
    public String getCle();

    /**
     * Retourne le site.
     *
     * @return String Valeur du site en caract�re.
     */
    public String getSite();

    /**
     * Retourne l'activit�.
     *
     * @return String Valeur de l'activit�.
     */
    public String getActivite();

    /**
     * Retourne le suivi.
     *
     * @return String Valeur du suivi en caract�re.
     */
    public String getSuivi();

    /**
     * Retourne le statut.
     *
     * @return String Valeur du statut en caract�re.
     */
    public String getStatut();

    /**
     * Retourne la date pr�vue.
     *
     * @return String Valeur de la date pr�vue en caract�re.
     */
    public String getDatePrevue();

    /**
     * Retourne la date compl�t�e.
     *
     * @return String Valeur de la date compl�t�e en caract�re.
     */
    public String getDateCompletee();

    /**
     * Retourne le secteur d'origine.
     *
     * @return String Valeur du secteur d'origine en caract�re.
     */
    public String getSecteurOrigine();

    /**
     * Retourne le demandeur.
     *
     * @return String Valeur du demandeur en caract�re.
     */
    public String getDemandeur();

    /**
     * Retourne la date du changement (pour l'audit).
     *
     * @return String Valeur num�rique de date.
     */
    public String getDateChangement();

    /**
     * Retourne l'auteur du changement (pour l'audit).
     *
     * @return String Valeur num�rique de l'auteur.
     */
    public String getChangePar();

	/**
     * Retourne l'intervenant.
     *
     * @return String Valeur de l'intervenant en caract�re.
     */
    public String getIntervenant();

    /**
     * Retourne le secteur assign�.
     *
     * @return String Valeur du secteur assign� en caract�re.
     */
    public String getSecteurAssigne();

    /**
     * Retourne le cr�ateur.
     *
     * @return String Valeur du cr�ateur en caract�re en caract�re.
     */
    public String getCreateur();

    /**
     * Retourne la confidentialit� du suivi.
     *
     * @return String Valeur de la confidentialit� du suivi en caract�re.
     */
    public String getConfidentialiteSuivi();

    /**
     * Retourne le niveau hierarchique du suivi.
     *
     * @return String Valeur du niveau hierarchique du suivi en caract�re.
     */
    public String getNiveauHierarchiqueSuivi();

    /**
     * Retourne la confidentialit� du cr�ateur.
     *
     * @return String Valeur de la confidentialit� du cr�ateur en caract�re.
     */
    public String getConfidentialiteCreateur();

    /**
     * Retourne le niveau hierarchique du cr�ateur.
     *
     * @return String Valeur du niveau hierarchique du cr�ateur en caract�re.
     */
    public String getNiveauHierarchiqueCreateur();

    /**
     * Retourne la date de cr�ation.
     *
     * @return String Valeur de la date de cr�ation en caract�re.
     */
    public String getDateCreation();

    /**
     * Retourne le modificateur.
     *
     * @return String Valeur du modificateur en caract�re.
     */
    public String getModificateur();

    /**
     * Retourne la date de modification.
     *
     * @return String Valeur de la date de modification en caract�re.
     */
    public String getDateModification();

    /**
     * Retourne l'approbateur.
     *
     * @return String Valeur de l'approbateur en caract�re.
     */
    public String getApprobateur();

    /**
     * Retourne la confidentialit� de l'approbateur.
     *
     * @return String Valeur de la confidentialit� de l'approbateur en
     * caract�re.
     */
    public String getConfidentialiteApprobateur();

    /**
     * Retourne le niveau hierarchique de l'approbateur.
     *
     * @return String Valeur du niveau hierarchique de l'approbateur en
     * caract�re.
     */
    public String getNiveauHierarchiqueApprobateur();

    /**
     * Retourne la date d'approbateur.
     *
     * @return String Valeur de la date d'approbateur en caract�re.
     */
    public String getDateApprobation();

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
     * @return String Valeur du lien en caract�re.
     */
    public String getLien();

    /**
     * Retourne le lien du site.
     *
     * @return String Valeur du lien du site en caract�re.
     */
    public String getLienSite();

	/**
	 * Retourne une entit�.
	 *
	 * @param entite Valeur de l'entit� en caract�re.
	 */
	public String getEntite();

    // Setters


    /**
     * Affecte une cle.
     *
     * @param cle Valeur de la cl� en caract�re.
     */
    public void setCle(String cle);

    /**
     * Affecte un site.
     *
     * @param site Valeur du site en caract�re.
     */
    public void setSite(String site);

    /**
     * Affecte une activit�.
     *
     * @param activite Valeur de l'activit� en caract�re.
     */
    public void setActivite(String activite);

    /**
     * Affecte un suivi.
     *
     * @param suivi Valeur du suivi en caract�re en caract�re.
     */
    public void setSuivi(String suivi);

    /**
     * Affecte un statut.
     *
     * @param statut Valeur du statut en caract�re.
     */
    public void setStatut(String statut);

    /**
     * Affecte une date pr�vue.
     *
     * @param datePrevue Valeur de la date pr�vue en caract�re.
     */
    public void setDatePrevue(String datePrevue);

    /**
     * Affecte une date compl�t�e.
     *
     * @param dateCompletee Valeur de la date compl�t�e en caract�re.
     */
    public void setDateCompletee(String dateCompletee);

    /**
     * Affecte un secteur d'origine.
     *
     * @return secteurOrigine Valeur du secteur d'origine en caract�re.
     */
    public void setSecteurOrigine(String secteurOrigine);

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
     * @param secteurAssigne Valeur du secteur assign� en caract�re.
     */
    public void setSecteurAssigne(String secteurAssigne);

    /**
     * Affecte un cr�ateur.
     *
     * @param createur Valeur du cr�ateur en caract�re.
     */
    public void setCreateur(String createur);

    /**
     * Affecte une confidentialit� du suivi.
     *
     * @param confidentialiteSuivi Valeur de la confidentialit� du suivi en
     * caract�re.
     */
    public void setConfidentialiteSuivi(String confidentialiteSuivi);

    /**
     * Affecte un niveau hierarchique du suivi.
     *
     * @param niveauHierarchiqueSuivi Valeur du niveau hierarchique du suivi en
     * caract�re.
     */
    public void setNiveauHierarchiqueSuivi(String niveauHierarchiqueSuivi);

    /**
     * Affecte une confidentialit� du cr�ateur.
     *
     * @param confidentialiteCreateur Valeur de la confidentialit� du cr�ateur
     * en caract�re.
     */
    public void setConfidentialiteCreateur(String confidentialiteCreateur);

    /**
     * Affecte un niveau hierarchique du cr�ateur.
     *
     * @param niveauHierarchiqueCreateur Valeur du niveau hierarchique du
     * cr�ateur.
     */
    public void setNiveauHierarchiqueCreateur(
            String niveauHierarchiqueCreateur);

    /**
     * Affecte une date de cr�ation.
     *
     * @param dateCreation Valeur de la date de cr�ation en caract�re.
     */
    public void setDateCreation(String dateCreation);

    /**
     * Affecte un modificateur.
     *
     * @param modificateur Valeur du modificateur en caract�re.
     */
    public void setModificateur(String modificateur);

    /**
     * Affecte une date de modification.
     *
     * @param dateModification Valeur de la date de modification en caract�re.
     */
    public void setDateModification(String dateModification);

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
     * l'approbateur en caract�re.
     */
    public void setConfidentialiteApprobateur(
            String confidentialiteApprobateur);

    /**
     * Affecte un niveau hierarchique de l'approbateur.
     *
     * @param niveauHierarchiqueApprobateur Valeur du niveau hierarchique de
     * l'approbateur en caract�re.
     */
    public void setNiveauHierarchiqueApprobateur(
            String niveauHierarchiqueApprobateur);

    /**
     * Affecte une date d'approbateur.
     *
     * @param dateApprobation Valeur de la date d'approbateur en caract�re.
     */
    public void setDateApprobation(String dateApprobation);

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
     * @param lien Valeur du lien en caract�re.
     */
    public void setLien(String lien);

    /**
     * Affecte un lien du site.
     *
     * @param lienSite Valeur du lien du site en caract�re.
     */
    public void setLienSite(String lienSite);

    /**
     * Test si un suivi peut �tre modifi�.
     *
     * @return boolean True si la narration est modifiable.
     */
    public boolean isModifiable();

    /**
     * Test si un suivi peut �tre approuv�.
     *
     * @return boolean True si la narration est approuvable.
     */
    public boolean isApprouvable();

    /**
     * Test si on peut compl�ter un suivi.
     *
     * @return boolean True si on peut permettre la modification d'une narration.
     */
    public boolean isPermettreComplete();

    /**
     * Determine si un suivi est modifiable
     *
     * @param isModifiable Est-ce que le suivi est modifiable
     * caract�re.
     */
    public void setModifiable(boolean isModifiable);

    /**
     * Determine si un suivi est approuvanle
     *
     * @param isApprouvable Est-ce que le suivi est approuvable
     * caract�re.
     */
    public void setApprouvable(boolean isApprouvable);

    /**
     * D�termine si on peut compl�ter une suivi.
     *
     * @param isPermettreComplete Est-ce qu'on peut compl�ter une suivi.
     * caract�re.
     */
    public void setPermettreComplete(boolean isPermettreComplete);

	/**
	 * Affecte une entit�.
	 *
	 * @param entite Valeur de l'entit� en caract�re.
	 */
	public void setEntite(String entite);

}